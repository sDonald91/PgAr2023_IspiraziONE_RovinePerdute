package Arnaldo;

import java.util.HashMap;

/**
 * Classe istanziabile per rappresentare i nodi dell'albero
 */
public class Nodo {

    private HashMap<Nodo, Double> archi = new HashMap<>();
    private Citta citta;

    /**
     * Costruttore con la sola città
     * @param citta corrispondente al nodo
     */
    public Nodo(Citta citta) {
        this.citta = citta;
    }

    public HashMap<Nodo, Double> getArchi() {
        return archi;
    }

    public Citta getCitta() {
        return citta;
    }
    
    /**
    * Aggiunge un arco con il corrispettivo peso all'elenco dei collegamenti
    * @param nodo
    * @param peso
    */
    public void aggiungiArco (Nodo nodo , double peso) {
        archi.put(nodo, peso);
    }

    /**
     * Restituisce il peso dell'arco che collega il nodo corrente a quello interessato
     * @param nodo
     * @return Il peso dell'arco
     */
    public double pesoArco(Nodo nodo) {
        double pesoArco = archi.get(nodo);
        return pesoArco;
    }
}