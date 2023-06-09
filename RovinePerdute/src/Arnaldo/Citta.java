package Arnaldo;

/**
 * Classe istanziabile per rappresentare le città
 */
public class Citta {

    private Coordinate coordinate;
    private int ID;
    private String nome;

    /**
     * Costruttore città
     * @param ID
     * @param nome
     * @param coordinate
     */
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

    /**
     * Calcola distanza euclidea tra due città
     * @param citta
     * @return distanza
     */
    public double distanza(Citta citta) {
        return this.coordinate.calcolaDistanza(citta.coordinate);
    }

    /**
     * Calcola dislivello tra due città
     * @param citta
     * @return differenza di altitudine
     */
    public double dislivello(Citta citta) {
        return this.coordinate.calcolaDislivello(citta.coordinate);
    }
}  