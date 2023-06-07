package arnaldo;

import it.unibs.fp.mylib.EstrazioneCasuale;

public class Mostro implements Mortale, Posizionabile{
    public static final int PUNTI_MOSTRO_NOMRALE = 5;
    public static final int PUNTI_BOSS = 10;
    public static final int PUNTI_BOSS_FINALE = 15;
    public static final int VITA_BASE_MOSTRO_NOMRALE = 6;
    public static final int VITA_BASE_BOSS = 12;
    public static final int VITA_BASE_BOSS_FINALE = 18;
    public static final int ATTACCO_BASE_MOSTRO_NOMRALE = 1;
    public static final int ATTACCO_BASE_BOSS = 3;
    public static final int ATTACCO_BASE_BOSS_FINALE = 4;
    private int puntiVita;
    private int attacco;
    private int puntiOttenibili;

    public Mostro(int puntiVita, int attacco, int puntiOttenibili) {
        this.puntiVita = puntiVita;
        this.attacco = attacco;
        this.puntiOttenibili = puntiOttenibili;
    }

    public int getPuntiVita() {
        return puntiVita;
    }

    public int getAttacco() {
        return attacco;
    }

    public int getPuntiOttenibili() {
        return puntiOttenibili;
    }

    public static Mostro creaMostroNormale(Nodo posizione) {
        return new Mostro(VITA_BASE_MOSTRO_NOMRALE, ATTACCO_BASE_MOSTRO_NOMRALE, PUNTI_MOSTRO_NOMRALE);
    }
    
    public static Mostro creaBoss(Nodo posizione) {
        int puntiVitaBoss = VITA_BASE_BOSS + EstrazioneCasuale.estraiIntero(-5, 5);
        int attaccoBoss = ATTACCO_BASE_BOSS + EstrazioneCasuale.estraiIntero(-2, 2);
        return new Mostro(puntiVitaBoss, attaccoBoss, PUNTI_BOSS);
    }

    public static Mostro creaBossFinale(Nodo posizione) {
        int puntiVitaBossFinale = VITA_BASE_BOSS_FINALE + EstrazioneCasuale.estraiIntero(-5, 5);
        int attaccoBossFinale = ATTACCO_BASE_BOSS_FINALE + EstrazioneCasuale.estraiIntero(-2, 2);
        return new Mostro(puntiVitaBossFinale, attaccoBossFinale, PUNTI_BOSS_FINALE);
    }

    public void attacca(Mortale mortale) {
        mortale.subisciDanno(attacco);
    }

    public void subisciDanno(int danno) {
        puntiVita -= danno;
        if (!inVita()) {
            morte();
        }
    }

    public boolean inVita() {
        return puntiVita > 0;
    }

    public void morte() {
        puntiOttenibili = 0;
    }

    public void assegnaPosizione(Nodo nodo) {
        nodo.setElemento(this);
    }

    public void eseguiAzione(Partita partita) {
        partita.eseguiScontro(this);
    }
}
