package arnaldo;

import java.util.ArrayList;
import java.util.Iterator;

import it.unibs.fp.mylib.BelleStringhe;
import it.unibs.fp.mylib.MyMenu;

public class InterazioneUtente {
    
    public static final String FRASE_DI_BENVENUTO = "Benvenuto nel Metave... eh, volevo dire... Arnaldoverse!\nScegli un mondo per iniziare a giocare\n";
    public static final String TITOLO_MENU_SELEZIONE_MONDO = "Seleziona un mondo";
    public static final String[] MONDI_DISPONIBILI = { "Base" };
    public static final String FRASE_SCONFITTA = "Hai Perso ahahah!\nOra vergognati per la tua sconfitta";

    public static void inizioPartita() {
        System.out.println(BelleStringhe.incornicia(FRASE_DI_BENVENUTO));
    }

    public static int scegliMondo() {
        MyMenu menu = new MyMenu(TITOLO_MENU_SELEZIONE_MONDO, MONDI_DISPONIBILI);
        return menu.scegli();
    }

    public static void stampaNodiCollegati(Nodo nodo) {
        System.out.println(nodo.getCollegamenti().toString());
    }

    public static Nodo selezionaProssimoNodo(Nodo nodoAttuale) {
        int dimensione = nodoAttuale.getCollegamenti().size();
        String[] vociMenu = new String[dimensione];
        ArrayList<Nodo> listaCollegamenti = new ArrayList<>(nodoAttuale.getCollegamenti());
        for (int i = 0; i < dimensione; i++){
            vociMenu[i] = String.valueOf(listaCollegamenti.get(i).getId());
        }
        MyMenu menu = new MyMenu("Seleziona il nodo in cui andare", vociMenu);
        return listaCollegamenti.get(menu.scegli() - 1);
    }

    public static void dichiaraSconfitta() {
        System.out.println(FRASE_SCONFITTA);
    }
}
