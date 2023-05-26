package Arnaldo;

import java.util.ArrayList;
import java.util.HashMap;

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
     * @param citta
     */
    public static void aggiungiCitta(Citta citta) {
        listaCitta.add(citta);
    }
     
    

    public String[] calcolaPercorsoOttimale (Squadra squadra) {    
        HashMap<Nodo, HashMap<Nodo, Double>> distanzeMinimeDaOrigine = new HashMap<>();
        double distanzaTotale = 0, calc_dist;
        String nomeCitta = "";
        Nodo nodoIteratore = squadra.getCampoBase();
        ArrayList<String> percorsoOttimale = new ArrayList<>();

        ArrayList<Nodo> listaNodi = new ArrayList<>();        
        listaNodi.addAll(squadra.getListaNodi());

        for (Nodo nodo : listaNodi) {
            distanzeMinimeDaOrigine.put(nodo, new HashMap<>(1));
            distanzeMinimeDaOrigine.get(nodo).put(nodo.getArchi().keySet().iterator().next(), Double.MAX_VALUE); // Viene preso il primo nodo vicino del nodo che stiamo inserendo, non ci importa quale nodo è
        }

        /* Si itera su tutti i nodi in Q, prendendo di volta in volta il nodo T che, rispetto agli altri nodi di Q, ha distanza minore
        dall’origine. La precedenza è importantissima per il principio di ottimalità */
        while (listaNodi.size() > 0) {
            for (Nodo nodo : squadra.getListaNodi()) {
                for (Nodo nodoVicino: nodo.getArchi().keySet()) {
                    double distanzaOrigine = distanzeMinimeDaOrigine.get(nodo).get(nodoVicino);
                    calc_dist = distanzaOrigine + nodo.pesoArco(nodoVicino);

                    if (calc_dist < distanzeMinimeDaOrigine.get(nodo).get(nodoVicino)) {  // se calc_dist è minore della distanza presente in tabella per il nodo N
                        distanzeMinimeDaOrigine.get(nodo).clear();
                        distanzeMinimeDaOrigine.get(nodo).put(nodoVicino, calc_dist);
                        //allora nella riga N della tabella si scrivono calc_dist come distanza e T come nodo precedente
                    }
                }
                listaNodi.remove(nodo); // Terminata l’iterazione, T viene rimosso da listaNodi. Quando listaNodi è vuota l’algoritmo termina.
            }
        }

        percorsoOttimale.add(squadra.getCampoBase().getCitta().getNome());

        while(!nomeCitta.equalsIgnoreCase("Rovine Perdute")) {
            Nodo nodoProssimo = distanzeMinimeDaOrigine.get(nodoIteratore).keySet().iterator().next();
            nomeCitta = nodoProssimo.getCitta().getNome();
            percorsoOttimale.add(nomeCitta);
           
            double distanzaParziale = distanzeMinimeDaOrigine.get(nodoIteratore).values().iterator().next();
            distanzaTotale += distanzaParziale;

            nodoIteratore = nodoProssimo;
        }

        squadra.setCarburanteConsumato(distanzaTotale);
        
        return percorsoOttimale.toArray(new String[percorsoOttimale.size()]);
    }

}