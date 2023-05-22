package Arnaldo;

public class MainRovinePerdute {
    public static void main(String[] args) {
        LettoreXML.impostaPercorsoFileCitta();
        System.out.println("\nOutput:\n" + LettoreXML.percorsoFileCitta);
        LettoreXML.leggiCitta();
    }
}
