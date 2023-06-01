package Arnaldo;

public class MainRovinePerdute { 
    
    public static final String[] LISTA_FILE_INPUT = {"PgAr_Map_5.xml", "PgAr_Map_12.xml", "PgAr_Map_50.xml", "PgAr_Map_200.xml", "PgAr_Map_2000.xml", "PgAr_Map_10000.xml"};
    public static final String[] LISTA_FILE_OUTPUT = {"Routes_5.xml", "Routes_12.xml", "Routes_50.xml", "Routes_200.xml", "Routes_2000.xml", "Routes_10000.xml"};
    
    public static void main(String[] args) {

        Squadra tonatiuh = new Squadra("Tonatiuh");
        Squadra metztli = new Squadra("Metztli");

        for (int i = 0; i < LISTA_FILE_INPUT.length; i++) {
            System.out.print(String.format("%-20s", LISTA_FILE_OUTPUT[i]));
            LettoreXML.setNomeFileInput(LISTA_FILE_INPUT[i]); 
            ScrittoreXML.setNomeFileOutput(LISTA_FILE_OUTPUT[i]);

            LettoreXML.impostaPercorsoFileInput();
            ScrittoreXML.impostaPercorsoFileOutput();
            LettoreXML.leggiCitta();

            tonatiuh.riempiListaNodi();
            metztli.riempiListaNodi();

            tonatiuh.generaAlbero(GestorePercorso.getListaCollegamenti());
            metztli.generaAlbero(GestorePercorso.getListaCollegamenti());

            ScrittoreXML.generaFileOutput(new Squadra[] { tonatiuh, metztli });
            System.out.println("FATTO");
        }

    }
}