package Arnaldo;

import java.util.ArrayList;

/**
 * Classe istanziabile che rappresenta una squadra in viaggio verso le rovine
 */
public class Squadra {

    private String nome;
    private Nodo campoBase;
    private double carburanteConsumato;
    private ArrayList<Nodo> listaNodi = new ArrayList<>();
    
    /**
     * Costruttore squadra
     * @param nome
     */
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

    public void setCarburanteConsumato(double carburanteConsumato) {
        this.carburanteConsumato = carburanteConsumato;   
    }

    public ArrayList<Nodo> getListaNodi(){
        return listaNodi;
    }

    /**
     * Crea tutti i nodi associati alle città
     */
    public void riempiListaNodi() {
        listaNodi.clear();
        for (Citta citta : GestorePercorso.getListaCitta()) {
            listaNodi.add(new Nodo(citta));
        }
    }

    /**
     * Riempie l'HashMap di ogni nodo con i suoi collegamenti e il rispettivo peso
     * @param collegamenti tra nodi
     */
    public void generaAlbero( ArrayList<ArrayList<Integer>> collegamenti) {
        campoBase = listaNodi.get(0);

        for (Nodo nodo : listaNodi) {
            for (Integer collegamento : collegamenti.get(listaNodi.indexOf(nodo))) {
                nodo.aggiungiArco(listaNodi.get(collegamento), pesoCollegamento(nodo, listaNodi.get(collegamento)));
            }
        }
    }

    /**
     * Calcola il peso del collegamento tra due nodi
     * @param partenza primo nodo
     * @param arrivo secondo nodo
     * @return peso collegamento
     */
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