package Arnaldo;

import java.io.File;
import java.net.URLDecoder;
import java.util.ArrayList;

public class LettoreXML {

    private static String FILE_CITTA;

    public ArrayList<Citta> leggiCitta() {

    }

    public static void nonCapiscoPercheJavaRendaCosiComplicatoPrendereIlPercorsoAssolutoDelFileCheSiStaEseguendo() {
        try{
            String percorso = LettoreXML.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath();
            String percorsoDecodificato = URLDecoder.decode(percorso, "UTF-8");
            FILE_CITTA = percorsoDecodificato;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

}