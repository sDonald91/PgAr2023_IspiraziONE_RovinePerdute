package Arnaldo;

import java.io.FileOutputStream;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

/*
 * Classe static contenente metodi per la generazione e scrittura dei file XML da produrre in output
 */
public class ScrittoreXML {
    
    private static String PercorsoFileOutput;
    private static String nomeFileOutput;
    
    public static void impostaPercorsoFileOutput() {
        String percorso = System.getProperty("user.dir");
        if (System.getProperty("os.name").equals("Linux")) {
            PercorsoFileOutput = percorso + "/file_xml/output/" + nomeFileOutput;
        }
        else {
            PercorsoFileOutput = percorso + "/RovinePerdute/file_xml/output/" + nomeFileOutput;   
        }
    }

	public static void setNomeFileOutput(String nomeFileOutput) {
		ScrittoreXML.nomeFileOutput = nomeFileOutput;
	}

    public static void generaFileOutput(Squadra[] squadre) {
        XMLOutputFactory xmlof = null;
        XMLStreamWriter xmlw = null;
        try {
            xmlof = XMLOutputFactory.newInstance();
            xmlw = xmlof.createXMLStreamWriter(new FileOutputStream(PercorsoFileOutput), "utf-8");
            xmlw.writeStartDocument("utf-8", "1.0");
        } catch (Exception e) {
            System.out.println("Errore nell'inizializzazione del writer:");
            System.out.println(e.getMessage());
        }

        try {
            Citta[][] percorsiSquadre = new Citta[squadre.length][];

            for (int i = 0; i < squadre.length; i++) {
                percorsiSquadre[i] = GestorePercorso.calcolaPercorsoOttimale(squadre[i]);
            }

            xmlw.writeStartElement("routes");
            
            for (int i = 0; i < squadre.length; i++) {
                xmlw.writeStartElement("route");
                xmlw.writeAttribute("team", squadre[i].getNome());
                xmlw.writeAttribute("cost", String.format("%.2f", squadre[i].getCarburanteConsumato()));
                xmlw.writeAttribute("cities", String.valueOf(percorsiSquadre[i].length));

                for (int j = 0; j < percorsiSquadre[i].length; j++) {
                    xmlw.writeStartElement("city");
                    xmlw.writeAttribute("id", String.valueOf(percorsiSquadre[i][j].getId()));
                    xmlw.writeAttribute("nome", percorsiSquadre[i][j].getNome());
                    xmlw.writeEndElement(); // </city>
                }

                xmlw.writeEndElement(); // </route>
            }

            xmlw.writeEndElement(); // </routes>
            xmlw.writeEndDocument();
            xmlw.flush();
            xmlw.close();

        } catch (XMLStreamException e) {
            System.out.println("Errore nella generazione del file");
            System.out.println(e.getMessage());
        }
    }
}