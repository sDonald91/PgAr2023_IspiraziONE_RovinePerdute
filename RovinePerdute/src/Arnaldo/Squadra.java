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

    public ArrayList<Nodo> getListaNodi(){
        return listaNodi;
    }

    public void setListaNodi(ArrayList<Nodo> listaNodi) {
        this.listaNodi = listaNodi;
    }

    /**
     * Crea tutti i nodi associati alle città
     */
    public void riempiListaNodi() {
        for (Citta citta : GestorePercorso.getListaCitta()) {
            listaNodi.add(new Nodo(citta));
        }
    }

    public void generaAlbero( ArrayList<ArrayList<Integer>> collegamenti) {
        campoBase = listaNodi.get(0);

        for (Nodo nodo : listaNodi) {
            for (Integer collegamento : collegamenti.get(listaNodi.indexOf(nodo))) {
                nodo.aggiungiArco(listaNodi.get(collegamento), pesoCollegamento(nodo, listaNodi.get(collegamento)));
            }
        }
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