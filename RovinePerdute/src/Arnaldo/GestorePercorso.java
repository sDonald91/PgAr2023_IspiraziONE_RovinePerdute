package Arnaldo;

import java.util.ArrayList;

/**
 * Classe static contenente metodi per gestire i percorsi
 */
public class GestorePercorso {
    private static ArrayList<Citta> listaCitta = new ArrayList<>();
    private static ArrayList<ArrayList<Integer>> listaCollegamenti = new ArrayList<>();
    

    public static ArrayList<Citta> getListaCitta() {
        return listaCitta;
    }

    public static void setListaCitta(ArrayList<Citta> listaCitta) {
        GestorePercorso.listaCitta = listaCitta;
    }

    public static ArrayList<ArrayList<Integer>> getListaCollegamenti() {
        return listaCollegamenti;
    }

    public static void setListaCollegamenti(ArrayList<ArrayList<Integer>> listaCollegamenti) {
        GestorePercorso.listaCollegamenti = listaCollegamenti;
    }


    /**
     * Aggiunge una città alla lista delle città dell'albero
     * @param citta da aggiungere
     */
    public static void aggiungiCitta(Citta citta) {
        listaCitta.add(citta);
    }
    
    public static Citta[] calcolaPercorsoOttimaleOttimizzato(Squadra squadra) {
        int dimensione = squadra.getListaNodi().size();
        Nodo[] listaNodiCopia = new ArrayList<>(squadra.getListaNodi()).toArray(new Nodo[dimensione]);
        Nodo[] nodiT = new ArrayList<>(squadra.getListaNodi()).toArray(new Nodo[dimensione]);
        Nodo[] nodiPrecedenti = new Nodo[dimensione];
        double[] distanzeOrigine = new double[dimensione];
        Nodo nodoConDistanzaMinore;
        double distanzaMinore, distanza;
        int indiceNodoMinore, indiceVicino;
        ArrayList<Citta> percorsoOttimale = new ArrayList<>();
        Nodo campoBase = squadra.getCampoBase();
        Nodo iteratore = listaNodiCopia[dimensione - 1];

        distanzeOrigine[0] = 0.0;

        for (int i = 1; i < dimensione; i++) {
            distanzeOrigine[i] = Double.MAX_VALUE;
        }

        while (listaNodiCopia[dimensione - 1] != null) {
            indiceNodoMinore = dimensione - 1;
            distanzaMinore = distanzeOrigine[indiceNodoMinore];
            for (int i = 0; i < dimensione; i++) {
                if (listaNodiCopia[i] != null && distanzeOrigine[i] < distanzaMinore) {
                    indiceNodoMinore = i;
                    distanzaMinore = distanzeOrigine[indiceNodoMinore];
                }
            }
            nodoConDistanzaMinore = nodiT[indiceNodoMinore];
            for (Nodo vicino : nodoConDistanzaMinore.getArchi().keySet()) {
                distanza = distanzaMinore + nodoConDistanzaMinore.pesoArco(vicino);

                indiceVicino = vicino.getCitta().getId();

                if (distanza < distanzeOrigine[indiceVicino]) {
                    distanzeOrigine[indiceVicino] = distanza;
                    nodiPrecedenti[indiceVicino] = nodoConDistanzaMinore;
                }
            }
            listaNodiCopia[indiceNodoMinore] = null;
        }

        squadra.setCarburanteConsumato(distanzeOrigine[dimensione - 1]);

        while (iteratore != campoBase) {
            percorsoOttimale.add(0, iteratore.getCitta());
            iteratore = nodiPrecedenti[iteratore.getCitta().getId()];
        }

        percorsoOttimale.add(0, iteratore.getCitta());

        return percorsoOttimale.toArray(new Citta[percorsoOttimale.size()]);
    }
    
}