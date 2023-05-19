package Arnaldo;

public class Citta {

    private Coordinate coordinate;
    private int ID;
    private String nome;

    public Citta(Coordinate coordinate, int ID, String nome){
        this.coordinate = coordinate;
        this.ID = ID;
        this.nome = nome;
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