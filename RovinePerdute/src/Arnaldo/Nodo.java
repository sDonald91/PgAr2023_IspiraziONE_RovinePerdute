package Arnaldo;

import java.util.HashMap;

public class Nodo {
    private HashMap<Nodo, Integer> archi;
    private Citta citta;

    public Nodo (HashMap<Nodo, Integer> archi, Citta citta) {
        this.archi = archi;
        this.citta = citta;
    }

    public Nodo(Citta citta) {
        this.citta = citta;
    }

    public HashMap<Nodo, Integer> getArchi() {
        return archi;
    }

    public Citta getCitta() {
        return citta;
    }

    public void setArchi(HashMap<Nodo, Integer> archi) {
        this.archi = archi;
    }

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