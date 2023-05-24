package Arnaldo;

/**
 * Classe per rappresentare le coordinate di una città
 */
public class Coordinate {
    private int x;
    private int y;
    private int h;

    /**
     * Costruttore coordinate
     * @param x
     * @param y
     * @param h
     */
    public Coordinate(int x, int y, int h){
        this.x = x;
        this.y = y;
        this.h = h;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getH() {
        return h;
    }

    /**
     * Calcola la distanza euclidea tra le coordinate di due città
     * @param coordinate seconda città
     * @return distanza
     */
    public double calcolaDistanza(Coordinate coordinate){
        return Math.sqrt(Math.pow(x - coordinate.getX(), 2) + Math.pow(y - coordinate.getY(), 2));
    }
    
    /**
     * Calcola la differenza di altituidine tra due città
     * @param coordinate seconda città
     * @return differenza di altitudine
     */
    public double calcolaDislivello(Coordinate coordinate) {
        return Math.abs(h - coordinate.getH());
    }
        
}