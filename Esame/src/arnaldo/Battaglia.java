package arnaldo;

import it.unibs.fp.mylib.EstrazioneCasuale;

public class Battaglia {
    private Giocatore giocatore;
    private Mostro mostro;

    public Battaglia(Giocatore giocatore, Mostro mostro) {
        this.giocatore = giocatore;
        this.mostro = mostro;
    }

    public Giocatore getGiocatore() {
        return giocatore;
    }

    public Mostro getMostro() {
        return mostro;
    }

    public boolean scontro(Mortale sfidante1, Mortale sfidante2) {
        int turnoIniziale = EstrazioneCasuale.estraiIntero(0, 1);
        Mortale vincitore;

        if (turnoIniziale == 0) {
            while (sfidante1.inVita() && sfidante2.inVita()) {
                sfidante1.attacca(sfidante2);
                sfidante2.attacca(sfidante1);
            }
        }
        else {
            while (sfidante1.inVita() && sfidante2.inVita()) {
                sfidante2.attacca(sfidante1);
                sfidante1.attacca(sfidante2);
            }
        }

        vincitore = sfidante1.inVita() ? sfidante1 : sfidante2;

        return vincitore instanceof Giocatore ? true : false;
    }
}
