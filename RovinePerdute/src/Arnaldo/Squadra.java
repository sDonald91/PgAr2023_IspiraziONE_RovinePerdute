package Arnaldo;

import java.util.ArrayList;

public class Squadra {
    private String nome;
    private Nodo campoBase;
    private ArrayList<Nodo> grafoPesato;
    private double carburanteConsumato;
    
    public Squadra(String nome, Nodo campoBase, ArrayList<Nodo> grafoPesato, double carburanteConsumato){
        this.nome = nome;
        this.campoBase = campoBase;
        this.grafoPesato = grafoPesato;
        this.carburanteConsumato = carburanteConsumato;
    }
    
    public String nome() {
        return nome;
    }

    public Nodo campoBase() {
        return campoBase;
    }

    public ArrayList<Nodo> getGrafoPesato() {
        return grafoPesato;
    }

    public double getCarburanteConsumato() {
        return carburanteConsumato;
    }

    public void setCarburanteConsumato(double carburanteConsumato) { //necessario?
        this.carburanteConsumato = carburanteConsumato;   
    }
    
}