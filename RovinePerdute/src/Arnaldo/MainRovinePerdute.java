package Arnaldo;

public class MainRovinePerdute {
    public static void main(String[] args) {

        LettoreXML.impostaPercorsoFileCitta();
        LettoreXML.leggiCitta();
        GestorePercorso.riempiListaNodi();
        System.out.println(LettoreXML.getPercorsoFileCitta());

        Squadra tonatiuh = new Squadra("Tonatiuh");
        Squadra metztli = new Squadra("Metztli");

        GestorePercorso.assegnaCampoBase();
        tonatiuh.generaAlbero(GestorePercorso.getListaNodi(), GestorePercorso.getListaCollegamenti());
        metztli.generaAlbero(GestorePercorso.getListaNodi(), GestorePercorso.getListaCollegamenti());

         System.out.println("\n");

        for (Nodo nodo : tonatiuh.getCampoBase().getArchi().keySet()) {
            System.out.println(nodo.getCitta().getNome() + ": " + tonatiuh.getCampoBase().getArchi().get(nodo));
        }
        System.out.println("\n");
        for (Nodo nodo : metztli.getCampoBase().getArchi().keySet()) {
            System.out.println(nodo.getCitta().getNome() + ": " + metztli.getCampoBase().getArchi().get(nodo));
        }
    }
}