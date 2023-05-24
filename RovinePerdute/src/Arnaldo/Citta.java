package Arnaldo;

/**
 * Classe istanziabile per rappresentare le città
 */
public class Citta {

    private Coordinate coordinate;
    private int ID;
    private String nome;

    public Citta( int ID, String nome, Coordinate coordinate){
        this.ID = ID;
        this.nome = nome;
        this.coordinate = coordinate;
    }

    public int getId() {
        return ID;
    }

    public String getNome() {
        return nome;
    }

    public Coordinate getCoordinate() {
        return coordinate;
    }

    public double distanza(Citta citta) {
        double distanza = this.coordinate.calcolaDistanza(citta.coordinate);
        return distanza;
    }

    public double dislivello(Citta citta) {
        double dislivello = this.coordinate.calcolaDislivello(citta.coordinate);
        return dislivello;
    }
}  