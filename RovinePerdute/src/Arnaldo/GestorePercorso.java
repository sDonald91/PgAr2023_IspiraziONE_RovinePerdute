package Arnaldo;

import java.util.ArrayList;

/**
 * Classe static contenente metodi per gestire i percorsi
 */
public class GestorePercorso {

    private static ArrayList<Citta> listaCitta = new ArrayList<>();
    private static ArrayList<ArrayList<Integer>> listaCollegamenti = new ArrayList<>();


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

    /**
     * Aggiunge una città alla lista delle città dell'albero
     * @param citta
     */
    public static void aggiungiCitta(Citta citta) {
        listaCitta.add(citta);
    }

    /* public Citta[] calcolaPercorsoOttimale (Squadra squadra) {    
        return null;
    } */

}