package arnaldo;

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

    public static void dichiaraSconfitta() {
        System.out.println(FRASE_SCONFITTA);
    }
}
