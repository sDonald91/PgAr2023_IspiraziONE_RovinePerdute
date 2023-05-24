package Arnaldo;

import java.util.HashMap;

/**
 * Classe istanziabile per rappresentare i nodi dell'albero
 */
public class Nodo {

    private HashMap<Nodo, Double> archi = new HashMap<>();
    private Citta citta;

    public Nodo (HashMap<Nodo, Double> archi, Citta citta) {
        this.archi = archi;
        this.citta = citta;
    }

    public Nodo(Citta citta) {
        this.citta = citta;
    }

    public HashMap<Nodo, Double> getArchi() {
        return archi;
    }

    public void setArchi(HashMap<Nodo, Double> archi) {
        this.archi = archi;
    }

    public void aggiungiArco (Nodo nodo , double peso) {
        archi.put(nodo, peso);
    }

    public Citta getCitta() {
        return citta;
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

    public boolean equals(Nodo nodo) {
        if (this.citta.equals(nodo.getCitta()) && this.archi.equals(nodo.getArchi())) {    //metodi equals per città da definire?
            return true;
        }
        return false;
    }

    public boolean equals(String nome) {
        if (this.citta.getNome().equals(nome)) {
            return true;
        }
        return false;
    }
}