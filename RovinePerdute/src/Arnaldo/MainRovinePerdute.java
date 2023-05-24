package Arnaldo;

import java.util.ArrayList;

public class MainRovinePerdute {
    public static void main(String[] args) {
        LettoreXML.impostaPercorsoFileCitta();
        ArrayList<ArrayList<Integer>> listaCitta = LettoreXML.leggiCitta();
        Squadra tonatiuh = new Squadra("Tonatiuh");
        Squadra metztli = new Squadra("Metztli");
        tonatiuh.generaAlbero(listaCitta);
        metztli.generaAlbero(listaCitta);

/*         System.out.println("\n");

        for (Nodo nodo : tonatiuh.getCampoBase().getArchi().keySet()) {
            System.out.println(nodo.getCitta().getNome() + ": " + tonatiuh.getCampoBase().getArchi().get(nodo));
        }
        System.out.println("\n");
        for (Nodo nodo : metztli.getCampoBase().getArchi().keySet()) {
            System.out.println(nodo.getCitta().getNome() + ": " + metztli.getCampoBase().getArchi().get(nodo));
        } */
    }
}