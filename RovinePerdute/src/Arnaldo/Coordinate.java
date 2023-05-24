package Arnaldo;

public class Coordinate {
    private int x;
    private int y;
    private int h;

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

    public double calcolaDistanza(Coordinate coordinate){
        return Math.sqrt(Math.pow(x - coordinate.getX(), 2) + Math.pow(y - coordinate.getY(), 2));
    }
    
    public long calcolaDislivello(Coordinate coordinate) {
        return Math.abs(h - coordinate.getH());
    }
        
}