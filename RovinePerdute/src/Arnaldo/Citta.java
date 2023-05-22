package Arnaldo;

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
        return 0;
    }

    public int dislivello(Citta citta) {
        return 0;
    }
}  