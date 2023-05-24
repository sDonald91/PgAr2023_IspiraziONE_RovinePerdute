package Arnaldo;

import java.util.ArrayList;

/**
 * Classe istanziabile che rappresenta una squadra in viaggio verso le rovine
 */
public class Squadra {

    private String nome;
    private Nodo campoBase;
    private double carburanteConsumato;
    
    public Squadra(String nome){
        this.nome = nome;
    }
    
    public String getNome() {
        return nome;
    }

    public Nodo getCampoBase() {
        return campoBase;
    }

    public double getCarburanteConsumato() {
        return carburanteConsumato;
    }

    public void setCarburanteConsumato(double carburanteConsumato) { //necessario?
        this.carburanteConsumato = carburanteConsumato;   
    }

    public void generaAlbero(ArrayList<ArrayList<Integer>> collegamenti) {
        ArrayList<Nodo> nodi = new ArrayList<>();

        for (Citta citta : GestorePercorso.getListaCitta()) {
            nodi.add(new Nodo(citta));
        }

        for (Nodo nodo : nodi) {
            for (Integer collegamento : collegamenti.get(nodi.indexOf(nodo))) {
                nodo.aggiungiArco(nodi.get(collegamento), pesoCollegamento(nodo, nodi.get(collegamento)));
            }
        }

        campoBase = nodi.get(0);
    }

    public double pesoCollegamento(Nodo partenza, Nodo arrivo) {
        if (this.nome.equals("Tonatiuh")) {
            return partenza.getCitta().distanza(arrivo.getCitta());
        }

        else if (this.nome.equals("Metztli")) {
            return partenza.getCitta().dislivello(arrivo.getCitta());
        }

        return -1;
    }
    
}