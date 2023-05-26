package Arnaldo;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Classe static contenente metodi per gestire i percorsi
 */
public class GestorePercorso {
    public static Nodo CAMPO_BASE;
    private static ArrayList<Citta> listaCitta = new ArrayList<>();
    private static ArrayList<ArrayList<Integer>> listaCollegamenti = new ArrayList<>();
    private static ArrayList<Nodo> listaNodi = new ArrayList<>();

    public static ArrayList<Citta> getListaCitta() {
        return listaCitta;
    }

    public static void setListaCitta(ArrayList<Citta> listaCitta) {
        GestorePercorso.listaCitta = listaCitta;
    }

    public static ArrayList<ArrayList<Integer>> getListaCollegamenti() {
        return listaCollegamenti;
    }

    public static void setListaCollegamenti(ArrayList<ArrayList<Integer>> listaCollegamenti) {
        GestorePercorso.listaCollegamenti = listaCollegamenti;
    }

    public static ArrayList<Nodo> getListaNodi(){
        return listaNodi;
    }

    public static void setListaNodi(ArrayList<Nodo> listaNodi) {
        GestorePercorso.listaNodi = listaNodi;
    }

    /**
     * Aggiunge una città alla lista delle città dell'albero
     * @param citta
     */
    public static void aggiungiCitta(Citta citta) {
        listaCitta.add(citta);
    }
    
    /**
     * Assegna il primo nodo alla costante CAMPO_BASE
     */
    public static void assegnaCampoBase() {
        CAMPO_BASE = listaNodi.get(0);
    }
    
    /**
     * Crea tutti i nodi associati alle città
     */
    public static void riempiListaNodi() {
        for (Citta citta : listaCitta) {
            listaNodi.add(new Nodo(citta));
        }
    }

    public Citta[] calcolaPercorsoOttimale (Squadra squadra) {    
        HashMap<Nodo, HashMap<Nodo, Integer>> distanzeMinimeDaOrigine = new HashMap<>();
        double distanza;

        for (Nodo nodo : listaNodi) {
            distanzeMinimeDaOrigine.put(nodo, new HashMap<>(1));
            distanzeMinimeDaOrigine.get(nodo).put(nodo.getArchi().keySet().iterator().next(), -1); // Viene preso il primo nodo vicino del nodo che stiamo inserendo, non ci importa quale nodo è
        }

        for (Nodo nodo : listaNodi) {
            Nodo nodoVicino = distanzeMinimeDaOrigine.get(nodo).keySet().iterator().next();
            double distanzaOrigine = distanzeMinimeDaOrigine.get(nodo).get(nodoVicino);

            distanza = distanzaOrigine + nodo.pesoArco(nodoVicino);

            // Devi continuare da qui

        }

        return null;
    }

}