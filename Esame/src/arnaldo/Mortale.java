package arnaldo;

public interface Mortale {

    boolean inVita();

    void morte();

    void attacca(Mortale mortale);

    void subisciDanno(int danno);
}
