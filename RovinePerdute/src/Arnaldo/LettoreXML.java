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

    public static String nomeFileInput;
    private static String percorsoFileCitta;
    
    public static void setNomeFileInput(String _nomeFileInput) {
        nomeFileInput = _nomeFileInput;
    }

    /**
    * Assegna alla variabile "percorsoFileCitta" il valore del percorso del file delle città che si vuole leggere
    */ 
    /* Sappiamo che non è una bella soluzione, ma cercando assiduamente non siamo comunque riusciti a trovare un modo
    per ottenere il percorso della root del progetto a prescindere dalla working directory */
    
    public static void impostaPercorsoFileInput() {
        String percorso = System.getProperty("user.dir");
        if (System.getProperty("os.name").equals("Linux")) {
            percorsoFileCitta = percorso + "/file_xml/input/" + nomeFileInput;
        }
        else {
            percorsoFileCitta = percorso + "/RovinePerdute/file_xml/input/" + nomeFileInput;
            
        }
    }

    /**
     * Legge il file XML e riempie una lista con le città e una con i collegamenti presenti tra esse
     */
    public static void leggiCitta() {

        String nome = "";
        int id = 0, x, y, h, contatore = -1;
        Coordinate coordinate = null;
        Citta cittaDaInserire = null;

        String tag = "";
        XMLInputFactory xmlif = null;
        XMLStreamReader xmlr = null;

        GestorePercorso.getListaCitta().clear();
        GestorePercorso.getListaCollegamenti().clear();
        
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
                            GestorePercorso.getListaCitta().add(cittaDaInserire);
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