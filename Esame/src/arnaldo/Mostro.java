package arnaldo;

public class Mostro implements Mortale{
    public static final int PUNTI_MOSTRO_NOMRALE = 5;
    public static final int PUNTI_BOSS = 10;
    public static final int PUNTI_BOSS_FINALE = 15;
    private int puntiVita;
    private int attacco;
    private int puntiOttenibili;
    private Nodo posizione;

    public Mostro(int puntiOttenibili, Nodo posizione) {
        this.puntiOttenibili = puntiOttenibili;
        this.posizione = posizione;
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

    public Nodo getPosizione() {
        return posizione;
    }

    public void setPosizione(Nodo posizione) {
        this.posizione = posizione;
    }

    public Mostro creaMostroNormale(Nodo posizione) {
        return new Mostro(PUNTI_MOSTRO_NOMRALE, posizione);
    }
    
    public Mostro creaBoss(Nodo posizione) {
        return new Mostro(PUNTI_BOSS, posizione);
    }

    public Mostro creaBossFinale(Nodo posizione) {
        return new Mostro(PUNTI_BOSS_FINALE, posizione);
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
}
