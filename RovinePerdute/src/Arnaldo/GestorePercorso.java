package Arnaldo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Classe static contenente metodi per gestire i percorsi
 */
public class GestorePercorso {
    private static final double PRECISIONE = 1e-2;
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
    
    public static String[] calcolaPercorsoOttimale(Squadra squadra) {
        ArrayList<Nodo> listaNodiCopia = new ArrayList<>(squadra.getListaNodi());
        HashMap<Nodo, NodiDistanze> tabella = new HashMap<>();
        NodiDistanze nodoConDistanzaMinore;
        double distanza;
        ArrayList<String> percorsoOttimale = new ArrayList<>();
        Nodo campoBase = squadra.getCampoBase();
        Nodo iteratore = listaNodiCopia.get(listaNodiCopia.size() - 1);

        tabella.put(campoBase, new NodiDistanze(campoBase, null, 0.0));

        for (int i = 0; i < listaNodiCopia.size() - 1; i++) {
            Nodo nodoPrecedente = listaNodiCopia.get(i);
            Nodo nodo = listaNodiCopia.get(i + 1);
            tabella.put(nodo, new NodiDistanze(nodo, nodoPrecedente, Double.MAX_VALUE));
        }

        while (listaNodiCopia.size() > 0) {
            nodoConDistanzaMinore = tabella.get(listaNodiCopia.get(0));
            for (Map.Entry<Nodo, NodiDistanze> riga : tabella.entrySet()) {
                if (listaNodiCopia.contains(riga.getKey()) && riga.getValue().getDistanzaOrigine() < nodoConDistanzaMinore.getDistanzaOrigine()) {
                    nodoConDistanzaMinore = riga.getValue();
                }
            }
            for (Nodo vicino : nodoConDistanzaMinore.getNodo().getArchi().keySet()) {
                distanza = nodoConDistanzaMinore.getDistanzaOrigine() + nodoConDistanzaMinore.getNodo().pesoArco(vicino);

                if (distanza < tabella.get(vicino).getDistanzaOrigine()) {
                    tabella.get(vicino).setDistanzaOrigine(distanza);
                    tabella.get(vicino).setNodoPrecedente(nodoConDistanzaMinore.getNodo());
                }
            }
            listaNodiCopia.remove(nodoConDistanzaMinore.getNodo());
        }
        
        squadra.setCarburanteConsumato(tabella.get(iteratore).getDistanzaOrigine());

        while (iteratore != campoBase) {
            percorsoOttimale.add(0, iteratore.getCitta().getNome());
            iteratore = tabella.get(iteratore).getNodoPrecedente();
        }

        percorsoOttimale.add(0, iteratore.getCitta().getNome());

        return percorsoOttimale.toArray(new String[percorsoOttimale.size()]);
    }
    

    public static String[] calcolaPercorsoOttimaleSbagliato(Squadra squadra) {    
        HashMap<Nodo, HashMap<Nodo, Double>> distanzeMinimeDaOrigine = new HashMap<>();
        double distanzaTotale = 0, calc_dist;
        String nomeCitta = "";
        Nodo nodoIteratore = squadra.getCampoBase();
        ArrayList<String> percorsoOttimale = new ArrayList<>();

        ArrayList<Nodo> listaNodi = new ArrayList<>();        
        listaNodi.addAll(squadra.getListaNodi());

        Map.Entry<Nodo, Double> primoArcoCampoBase = listaNodi.get(0).getArchi().entrySet().iterator().next();
        distanzeMinimeDaOrigine.put(listaNodi.get(0), new HashMap<>(0));
        distanzeMinimeDaOrigine.get(listaNodi.get(0)).put(primoArcoCampoBase.getKey(), 0.0);

        for (int i = 1; i < listaNodi.size(); i++) {
            Nodo nodo = listaNodi.get(i);
            distanzeMinimeDaOrigine.put(nodo, new HashMap<>(1));
            distanzeMinimeDaOrigine.get(nodo).put(nodo.getArchi().keySet().iterator().next(), Double.MAX_VALUE);
        }

        /* for (Nodo nodo : listaNodi) {
            // Viene preso il primo nodo vicino del nodo che stiamo inserendo, non ci importa quale nodo è
        } */
 
        /* Si itera su tutti i nodi in Q, prendendo di volta in volta il nodo T che, rispetto agli altri nodi di Q, ha distanza minore
        dall’origine. La precedenza è importantissima per il principio di ottimalità */
        while (listaNodi.size() > 0) {
            for (Nodo nodo : squadra.getListaNodi()) {   //per ciclare usiamo la lista della squadra di modo che non si creino problemi andando ad eliminare i nodi
                Nodo primoNodo = distanzeMinimeDaOrigine.get(nodo).keySet().iterator().next();
                double distanzaOrigine = distanzeMinimeDaOrigine.get(nodo).get(primoNodo);
                for (Nodo nodoVicino: nodo.getArchi().keySet()) {
                    calc_dist = distanzaOrigine + nodo.pesoArco(nodoVicino);
                    double temp = distanzeMinimeDaOrigine.get(nodo).get(primoNodo);

                    if (calc_dist < temp ^ Math.abs((calc_dist - temp)) <= PRECISIONE) {  // se calc_dist è minore della distanza presente in tabella per il nodo N
                        distanzeMinimeDaOrigine.get(nodo).clear();
                        distanzeMinimeDaOrigine.get(nodo).put(nodoVicino, calc_dist);
                        primoNodo = nodoVicino;
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