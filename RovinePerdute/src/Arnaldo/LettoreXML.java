package Arnaldo;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

/**
 * Classe static contenente metodi per la lettura dei dati dai file XML
 */
public class LettoreXML {

    public static final String NOME_FILE_CITTA = "PgAr_Map_10000.xml";
    private static String percorsoFileCitta;

    public static String getPercorsoFileCitta() {
        return percorsoFileCitta;
    }

    /**
     * Assegna alla variabile "percorsoFileCitta" il valore del percorso del file delle città che si vuole leggere
     */
    public static void impostaPercorsoFileCitta() {
        String percorso = System.getProperty("user.dir");
        if (System.getProperty("os.name").equals("Linux")) {
            percorsoFileCitta = percorso + "/file_xml/input/" + NOME_FILE_CITTA;
        }
        else {
            percorsoFileCitta = percorso + "/RovinePerdute/file_xml/input/" + NOME_FILE_CITTA;
            
        }
    }

    /**
     * Legge il file XML e riempie una lista di città e una con i collegamenti presenti tra esse
     */
    public static void leggiCitta() {

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
        } catch (FileNotFoundException | XMLStreamException e) {          //FileNotFoundException ?? xmlr risulta null 
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
                            GestorePercorso.getListaCollegamenti().add(new ArrayList<>());
                            contatore++;
                            break;

                        case "link":
                            GestorePercorso.getListaCollegamenti().get(contatore).add(Integer.parseInt(xmlr.getAttributeValue(0)));
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

    }

}