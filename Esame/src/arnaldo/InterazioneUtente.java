package arnaldo;

import java.util.ArrayList;

import it.unibs.fp.mylib.BelleStringhe;
import it.unibs.fp.mylib.MyMenu;

/**
 * Classe che gestisce l'interazione con l'utente
 */
public class InterazioneUtente {
    
    private static final String ARRIVO_IN_UN_NODO = "Sei arrivato al nodo ";
    private static final String PULISCI_SHELL = "\033[H\033[2J";
    private static final String STATISTICHE = "Statistiche dei combattenti:\n";
    private static final String VICOLO_CIECO = "Questo nodo è un vicolo cieco, scegline un altro";
    private static final String TITOLO_SELEZIONE_NODI = "Seleziona il nodo in cui andare";
    private static final String VALORE_ATTACCO2 = " di attacco!\n";
    private static final String PUNTI_VITA2 = " punti vita!\n";
    private static final String VALORE_ATTACCO = "Attacco attuale: ";
    private static final String PUNTI_VITA = "Punti vita attuali: ";
    private static final String PUNTI_GUADAGNATI = "Hai guadagnato ";
    private static final String PUNTI_PERSI = "Hai perso ";
    private static final String PUNTI_TOTALIZZATI = "Punti totalizzati: ";
    private static final String FRASE_VITTORIA = "Hai vinto!";
    public static final String FRASE_DI_BENVENUTO = "Benvenuto nell'Arnaldoverse!\nScegli un mondo per iniziare a giocare";
    public static final String TITOLO_MENU_SELEZIONE_MONDO = "Seleziona un mondo";
    public static final String[] MONDI_DISPONIBILI = { "Base" };
    public static final String FRASE_SCONFITTA = "Hai Perso!";

    /**
     * Mostra un messaggio di benvenuto
     */
    public static void inizioPartita() {
        System.out.println(BelleStringhe.incornicia(FRASE_DI_BENVENUTO));
    }

    /**
     * Permette di scegliere un mondo in cui giocare
     * @return
     */
    public static Mondo scegliMondo() {
        MyMenu menu = new MyMenu(TITOLO_MENU_SELEZIONE_MONDO, MONDI_DISPONIBILI);
        int mondoScelto = menu.scegli() - 1;
        if (mondoScelto == -1) {
            return null;
        }
        return new Mondo(MONDI_DISPONIBILI[mondoScelto]);
    }

    /**
     * Genera le voci del menu per la selezione dei nodi
     * @param dimensione
     * @param listaCollegamenti
     * @return vociMenu
     */
    public static String[] generaVociMenuNodi(int dimensione, ArrayList<Nodo> listaCollegamenti) {
        String[] vociMenu = new String[dimensione];

        for (int i = 0; i < dimensione; i++){
            vociMenu[i] = "Nodo " + String.valueOf(listaCollegamenti.get(i).getId());
        }

        return vociMenu;
    }

    /**
     * Permette di selezionare il prossimo nodo in cui andare
     * @param nodoAttuale
     * @return prossimoNodo
     */
    public static Nodo selezionaProssimoNodo(Nodo nodoAttuale) {
        int dimensione = nodoAttuale.getCollegamenti().size();
        int indiceScelto;
        ArrayList<Nodo> listaCollegamenti = new ArrayList<>(nodoAttuale.getCollegamenti());

        MyMenu menu = new MyMenu(TITOLO_SELEZIONE_NODI, generaVociMenuNodi(dimensione, listaCollegamenti));
        indiceScelto = menu.scegli() - 1;

        if (indiceScelto == -1) {
            return null;
        }

        while (listaCollegamenti.get(indiceScelto).getCollegamenti().isEmpty() && listaCollegamenti.get(indiceScelto).getId() != Mappa.getListaNodi().size() - 1){
            System.out.println(VICOLO_CIECO);
            indiceScelto = menu.scegli() - 1;

            if (indiceScelto == -1) {
                return null;
            }
        }

        Mappa.rimuviCollegamentoPerTutti(listaCollegamenti.get(indiceScelto));
        return listaCollegamenti.get(indiceScelto);
    }

    /**
     * Esegue l'azione contenuta nel nodo
     * @param nodoAttuale
     * @param partita
     */
    public static void visitaNodo(Nodo nodoAttuale, Partita partita) {
        System.out.println(ARRIVO_IN_UN_NODO + nodoAttuale.getId());
        if (nodoAttuale.equals(Mappa.getListaNodi().get(Mappa.getListaNodi().size() - 1))) {
            System.out.println("Hai raggiunto Cammo!");
        }
        System.out.println("C'è un " + nodoAttuale.getElemento().toString());
        nodoAttuale.getElemento().eseguiAzione(partita);
    }
    
    /**
     * Mostra i punti vita e attacco del giocatore e del mostro
     * @param mortale1
     * @param mortale2
     */
    public static void mostraStatistiche(Mortale mortale1, Mortale mortale2) {
        System.out.print(PULISCI_SHELL);
        System.out.println(STATISTICHE);
        System.out.println(mortale1.toString());
        System.out.println();
        System.out.println(mortale2.toString());
    }

    /**
     * Dichiara la sconfitta del giocatore
     * @param giocatore
     */
    public static void dichiaraSconfitta(Giocatore giocatore) {
        System.out.println(FRASE_SCONFITTA);
        System.out.println(PUNTI_TOTALIZZATI + giocatore.getPuntiAcquisiti());
    }

    /**
     * Dichiara la vittoria del giocatore
     * @param giocatore
     */
    public static void dichiaraVittoria(Giocatore giocatore) {
        System.out.println(FRASE_VITTORIA);
        System.out.println(PUNTI_TOTALIZZATI + giocatore.getPuntiAcquisiti());
    }

    /**
     * Ferma il programma per un secondo per rendere più leggibile l'output
     */
    public static void sleep() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    /**
     * Mostra l'incremento dei punti vita del giocatore
     * @param punti
     * @param giocatore
     */
    public static void mostraIncrementoPuntiVita(int punti, Giocatore giocatore) {
        if (punti < 0) {
            System.out.println(PUNTI_PERSI + Math.abs(punti) + PUNTI_VITA2);
        }
        System.out.println(PUNTI_GUADAGNATI + punti + PUNTI_VITA2);
        System.out.println(PUNTI_VITA + giocatore.getPuntiVita() + "\n");
    }

    /**
     * Mostra l'incremento dell'attacco del giocatore
     * @param punti
     * @param giocatore
     */
    public static void mostraIncrementoAttacco(int punti, Giocatore giocatore) {
        if (punti < 0) {
            System.out.println(PUNTI_PERSI + Math.abs(punti) + VALORE_ATTACCO2);
        }
        System.out.println(PUNTI_GUADAGNATI + punti + VALORE_ATTACCO2);
        System.out.println(VALORE_ATTACCO + giocatore.getAttacco() + "\n");
    }
}
