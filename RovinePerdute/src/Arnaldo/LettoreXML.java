package Arnaldo;

import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLDecoder;
import java.util.ArrayList;

public class LettoreXML {

    public static String FILE_CITTA;

    public ArrayList<Citta> leggiCitta() {

    }

    /**
     * Trova il percorso assoluto della classe LettoreXML.
     * In questo modo, da qualunque parte si esegua il codice si riuscirà sempre a trovare il percorso corretto e, quindi, ad orientarsi all'interno delle cartelle del progetto.
     */
    public static void nonCapiscoPercheJavaRendaCosiComplicatoPrendereIlPercorsoAssolutoDelFileCheSiStaEseguendo() {
        URL percorsoDiQuestoFile = LettoreXML.class.getResource("LettoreXML.class");
        if (!"file".equalsIgnoreCase(percorsoDiQuestoFile.getProtocol())) {
            throw new IllegalStateException("La classe 'lettoreXML' non è contenuta in alcun file");
        }
        try {
            FILE_CITTA = URLDecoder.decode(percorsoDiQuestoFile.getPath(), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

}