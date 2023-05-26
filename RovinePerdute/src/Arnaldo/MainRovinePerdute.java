package Arnaldo;

public class MainRovinePerdute {
    public static void main(String[] args) {

        System.out.println(System.getProperty("os.name"));


        LettoreXML.impostaPercorsoFileCitta();
        LettoreXML.leggiCitta();
        System.out.println(LettoreXML.getPercorsoFileCitta());

        Squadra tonatiuh = new Squadra("Tonatiuh");
        Squadra metztli = new Squadra("Metztli");
    
        tonatiuh.generaAlbero(GestorePercorso.getListaCollegamenti());
        metztli.generaAlbero(GestorePercorso.getListaCollegamenti());

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