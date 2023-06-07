package arnaldo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.TreeSet;

import it.unibs.fp.mylib.EstrazioneCasuale;

public class Mappa {
    public static final int NUMERO_MASSIMO_NODI = 12;
    public static final int NUMERO_MINIMO_NODI = 3;

    private Mondo mondo;
    private Nodo inizio;
    private Nodo fine;
    private Nodo nodoAttuale;

    public Mappa(Mondo mondo) {
        this.mondo = mondo;
    }

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
        inizio = nodoAttuale = nodi[0];
        fine = nodi[6];
    }

    public void generaMappaCasuale() {
        int numeroDiNodi = EstrazioneCasuale.estraiIntero(NUMERO_MINIMO_NODI, NUMERO_MASSIMO_NODI);
        int numeroDiCollegamenti;
        int idEstratto;
        ArrayList<Nodo> nodi = new ArrayList<>();
        ArrayList<Nodo> nodiFittizi;
        ArrayList<ArrayList<Integer>> collegamenti = new ArrayList<>();
        TreeSet<Nodo> insiemeCollegamenti = new TreeSet<>();
        for (int i = 0; i < numeroDiNodi; i++) {
            nodi.add(new Nodo(i));
        }
        for (int i = 0; i < numeroDiNodi - 1; i++) {
            numeroDiCollegamenti = EstrazioneCasuale.estraiIntero(0, numeroDiNodi);
            nodiFittizi = new ArrayList<>(nodi);
            for (int j = 0; j < numeroDiCollegamenti; j++) {
                idEstratto = EstrazioneCasuale.estraiIntero(0, nodiFittizi.size());
                collegamenti.get(i).add(nodiFittizi.remove(idEstratto).getId());
            }
            collegamenti.get(i).forEach(id -> insiemeCollegamenti.add(nodi.get(id)));
            nodi.get(i).setCollegamenti(insiemeCollegamenti);
        }
    }

    public void prosegui() {
        while (!nodoAttuale.equals(fine)) {
            nodoAttuale = InterazioneUtente.selezionaProssimoNodo(nodoAttuale);
        }
    }
}
