package Arnaldo;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLDecoder;
import java.util.ArrayList;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

/**
 * Classe static contenente metodi per la lettura dei dati dai file XML
 */
public class LettoreXML {

    public static final String NOME_FILE_CITTA = "PgAr_Map_12.xml";
    public static String percorsoFileCitta;

    /**
     * Trova il percorso assoluto del file LettoreXML.class. 
     * In questo modo, da qualunque parte si esegua il codice si riuscirà sempre a trovare il percorso corretto e, quindi, ad orientarsi all'interno delle cartelle del progetto.
     */
    public static String nonCapiscoPercheJavaRendaCosiComplicatoPrendereIlPercorsoAssolutoDelFileCheSiStaEseguendo() {
        URL percorsoDiQuestoFile = LettoreXML.class.getResource("LettoreXML.class");

        if (!"file".equalsIgnoreCase(percorsoDiQuestoFile.getProtocol())) {
            throw new IllegalStateException("La classe 'lettoreXML' non è contenuta in alcun file");
        }

        try {
            return URLDecoder.decode(percorsoDiQuestoFile.getPath(), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return "";
    }

    /**
     * Assegna alla variabile "percorsoFileCitta" il valore del percorso del file delle città che si vuole leggere
     */
    public static void impostaPercorsoFileCitta() {
        String percorso = nonCapiscoPercheJavaRendaCosiComplicatoPrendereIlPercorsoAssolutoDelFileCheSiStaEseguendo();
        percorsoFileCitta = percorso.substring(0, percorso.lastIndexOf("/")) + "/../../file_xml/input/" + NOME_FILE_CITTA;
    }

    /**
     * Restituisce i collegamenti di ogni città con le altre
     * @return ArrayList<ArratList<Integer>> collegamenti
     */
    public static ArrayList<ArrayList<Integer>> leggiCitta() {
        ArrayList<ArrayList<Integer>> listaCollegamenti = new ArrayList<>();

        String nome = "";
        int id = 0, x, y, h, contatore = -1;
        Coordinate coordinate = null;
        Citta cittaDaInserire = null;

        String tag = "";
        XMLInputFactory xmlif = null;
        XMLStreamReader xmlr = null;
        
        try {
            xmlif = XMLInputFactory.newInstance();
            xmlr = xmlif.createXMLStreamReader(percorsoFileCitta, new FileInputStream(percorsoFileCitta));
        } catch (FileNotFoundException | XMLStreamException e) {
            System.out.println("Errore nell'inizializzazione del reader:");
            System.out.println(e.getMessage());
        }

        try {
            while (xmlr.hasNext()) {
                if (xmlr.getEventType() == XMLStreamReader.START_ELEMENT) {
                    tag = xmlr.getLocalName();

                    switch (tag) {
                        case "city":
                            id = Integer.parseInt(xmlr.getAttributeValue(0));
                            nome = xmlr.getAttributeValue(1);
                            x = Integer.parseInt(xmlr.getAttributeValue(2));
                            y = Integer.parseInt(xmlr.getAttributeValue(3));
                            h = Integer.parseInt(xmlr.getAttributeValue(4));
                            coordinate = new Coordinate(x, y, h);
                            cittaDaInserire = new Citta(id, nome, coordinate);
                            GestorePercorso.aggiungiCitta(cittaDaInserire);
                            listaCollegamenti.add(new ArrayList<>());
                            contatore++;
                            break;

                        case "link":
                            listaCollegamenti.get(contatore).add(Integer.parseInt(xmlr.getAttributeValue(0)));
                            break;

                        default:
                            break;
                    }
                }
                xmlr.next();
            }
        }
        catch (XMLStreamException e) {
            System.out.println(e.getMessage());
        }

        return listaCollegamenti;
    }

}