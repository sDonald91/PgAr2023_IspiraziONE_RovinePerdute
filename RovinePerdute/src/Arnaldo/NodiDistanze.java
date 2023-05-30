package Arnaldo;

public class NodiDistanze {
    private Nodo nodo;
    private Nodo nodoPrecedente;
    private Double distanzaOrigine;

    public NodiDistanze(Nodo nodo, Nodo nodoPrecedente, Double distanzaOrigine) {
        this.nodo = nodo;
        this.nodoPrecedente = nodoPrecedente;
        this.distanzaOrigine = distanzaOrigine;
    }

    public Nodo getNodo() {
        return nodo;
    }

    public void setNodo(Nodo nodo) {
        this.nodo = nodo;
    }

    public Nodo getNodoPrecedente() {
        return nodoPrecedente;
    }

    public void setNodoPrecedente(Nodo nodoPrecedente) {
        this.nodoPrecedente = nodoPrecedente;
    }

    public Double getDistanzaOrigine() {
        return distanzaOrigine;
    }

    public void setDistanzaOrigine(Double distanzaOrigine) {
        this.distanzaOrigine = distanzaOrigine;
    } 

}
