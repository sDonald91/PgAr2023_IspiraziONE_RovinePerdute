package Arnaldo;

public class MainRovinePerdute {
    public static void main(String[] args) {

        LettoreXML.impostaPercorsoFileCitta();
        LettoreXML.leggiCitta();
        
        System.out.println(LettoreXML.getPercorsoFileCitta());

        Squadra tonatiuh = new Squadra("Tonatiuh");
        Squadra metztli = new Squadra("Metztli");
        tonatiuh.riempiListaNodi();
        metztli.riempiListaNodi();

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

        System.out.println("\n\n");

        System.out.println("Percorso ottimale per " + tonatiuh.getNome());
        for (String citta : GestorePercorso.calcolaPercorsoOttimale(tonatiuh)) {
            System.out.println(citta);
        }
        System.out.println("Carburante consumato: " + tonatiuh.getCarburanteConsumato());

        System.out.println("\n\n");

        System.out.println("Percorso ottimale per " + metztli.getNome());
        for (String citta : GestorePercorso.calcolaPercorsoOttimale(metztli)) {
            System.out.println(citta);
        }
        System.out.println("Carburante consumato: " + metztli.getCarburanteConsumato());

    }
}