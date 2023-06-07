package arnaldo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.TreeSet;

import it.unibs.fp.mylib.EstrazioneCasuale;

/**
 * Classe che rappresenta la mappa di gioco
 */
public class Mappa {
    public static final int NUMERO_MASSIMO_NODI = 12;
    public static final int NUMERO_MINIMO_NODI = 3;

    private Nodo fine;
    private Nodo nodoAttuale;
    private static ArrayList<Nodo> listaNodi;

    public Mappa() {}

    /**
     * Genera la mappa base
     */
    public void generaMappaBase() {
        int dimensione = 7;
        Nodo[] nodi = new Nodo[dimensione];
        for (int i = 0; i < dimensione; i++) {
            nodi[i] = new Nodo(i);
        }
        nodi[0].setCollegamenti(new TreeSet<>(Arrays.asList(nodi[1])));
        nodi[1].setCollegamenti(new TreeSet<>(Arrays.asList(nodi[2], nodi[3])));
        nodi[2].setCollegamenti(new TreeSet<>(Arrays.asList(nodi[4])));
        nodi[3].setCollegamenti(new TreeSet<>(Arrays.asList(nodi[4])));
        nodi[4].setCollegamenti(new TreeSet<>(Arrays.asList(nodi[5])));
        nodi[5].setCollegamenti(new TreeSet<>(Arrays.asList(nodi[6])));
        nodoAttuale = nodi[0];
        fine = nodi[6];

        for (int i = 0; i < dimensione - 1; i++) {
            nodi[i].setTipologia(TipoDiNodo.values()[EstrazioneCasuale.estraiIntero(0, TipoDiNodo.values().length - 1)]);
            switch (nodi[i].getTipologia()) {
                case MOSTRO -> Mostro.creaMostroNormale().assegnaPosizione(nodi[i]);
                case STATISTICA -> new Modificatore(TipoDiModificatore.values()[EstrazioneCasuale.estraiIntero(0, TipoDiModificatore.values().length - 1)]).assegnaPosizione(nodi[i]);
            }
        }

        nodi[dimensione - 1].setTipologia(TipoDiNodo.MOSTRO);
        nodi[dimensione - 1].setElemento(Mostro.creaBossFinale());

        listaNodi = new ArrayList<>(Arrays.asList(nodi));
    }

    /**
     * Genera una mappa in modo casuale
     */
    public void generaMappaCasuale() {
        int numeroDiNodi = EstrazioneCasuale.estraiIntero(NUMERO_MINIMO_NODI, NUMERO_MASSIMO_NODI);
        ArrayList<Nodo> nodi = new ArrayList<>();
        ArrayList<ArrayList<Integer>> collegamenti = new ArrayList<>();
        TreeSet<Nodo> insiemeCollegamenti = new TreeSet<>();
        Nodo nuovoNodo;

        // Creo i nuovi nodi dandogli una tipologia e un elemento casuali
        for (int i = 0; i < numeroDiNodi - 1; i++) {
            nuovoNodo = new Nodo(i);
            nuovoNodo.setTipologia(TipoDiNodo.values()[EstrazioneCasuale.estraiIntero(0, TipoDiNodo.values().length - 1)]);
            switch (nuovoNodo.getTipologia()) {
                case MOSTRO -> Mostro.creaMostroNormale().assegnaPosizione(nuovoNodo);
                case STATISTICA -> new Modificatore(TipoDiModificatore.values()[EstrazioneCasuale.estraiIntero(0, TipoDiModificatore.values().length - 1)]).assegnaPosizione(nuovoNodo);
            }
            nodi.add(nuovoNodo);
        }

        // Creo i collegamenti tra i nodi
        creazioneCollegamentiCasuali(numeroDiNodi, nodi, collegamenti, insiemeCollegamenti);

        nodi.get(nodi.size() - 1).setTipologia(TipoDiNodo.MOSTRO); // Il nodo finale contiene il boss finale
        nodi.get(nodi.size() - 1).setElemento(Mostro.creaBossFinale());

        nodoAttuale = nodi.get(0);
        fine = nodi.get(nodi.size() - 1);
        listaNodi = nodi;
    }

    /**
     * Crea i collegamenti tra i nodi in modo casuale
     * @param numeroDiNodi
     * @param nodi
     * @param collegamenti
     * @param insiemeCollegamenti
     */
    private void creazioneCollegamentiCasuali(int numeroDiNodi, ArrayList<Nodo> nodi, ArrayList<ArrayList<Integer>> collegamenti, TreeSet<Nodo> insiemeCollegamenti) {
        int numeroDiCollegamenti;
        int idEstratto;
        ArrayList<Nodo> nodiFittizi;
        for (int i = 0; i < numeroDiNodi - 1; i++) {
            numeroDiCollegamenti = EstrazioneCasuale.estraiIntero(1, numeroDiNodi - 2);
            nodiFittizi = new ArrayList<>(nodi);
            nodiFittizi.remove(i);
            nodiFittizi.remove(0);
            collegamenti.add(new ArrayList<>());

            for (int j = 0; j < numeroDiCollegamenti; j++) {
                idEstratto = EstrazioneCasuale.estraiIntero(0, nodiFittizi.size() - 1);
                collegamenti.get(i).add(nodiFittizi.remove(idEstratto).getId());
            }

            collegamenti.get(i).forEach(id -> insiemeCollegamenti.add(nodi.get(id)));
            nodi.get(i).setCollegamenti(insiemeCollegamenti);
            insiemeCollegamenti.clear();
        }
    }

    /**
     * Permette di proseguire il gioco nodo per nodo
     * @param partita
     */
    public void prosegui(Partita partita) {
        while (!nodoAttuale.equals(fine) && partita.getGiocatore().inVita()) {
            nodoAttuale = InterazioneUtente.selezionaProssimoNodo(nodoAttuale);

            // Nel caso in cui l'untente abbia scelto di uscire dal gioco
            if (nodoAttuale == null) {
                return;
            }

            InterazioneUtente.visitaNodo(nodoAttuale, partita);
        }
        if (partita.getGiocatore().inVita()) {
            InterazioneUtente.dichiaraVittoria(partita.getGiocatore());
        }
        else {
            partita.getGiocatore().morte();
        }
    }

    /**
     * Rimuove i collegamenti per tutti i nodi
     * @param nodo
     */
    public static void rimuviCollegamentoPerTutti(Nodo nodo) {
        listaNodi.forEach(n -> n.getCollegamenti().remove(nodo));
    }

    /**
     * Restituisce la lista dei nodi
     * @return
     */
    public static ArrayList<Nodo> getListaNodi() {
        return listaNodi;
    }
}
