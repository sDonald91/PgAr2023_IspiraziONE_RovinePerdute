package Arnaldo;

import java.util.HashMap;

public class Nodo {
    private HashMap<Nodo, Integer> archi;
    private Citta citta;

    public Nodo (HashMap<Nodo, Integer> archi, Citta citta) {
        this.archi = archi;
        this.citta = citta;
    }

    public HashMap<Nodo, Integer> getArchi() {
        return archi;
    }

    public Citta getCitta() {
        return citta;
    }

    public double pesoArco(Nodo nodo) {

    }

    public boolean equals(Nodo nodo) {

    }

    public boolean equals(String nome) {

    }
}