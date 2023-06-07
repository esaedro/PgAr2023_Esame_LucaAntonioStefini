package arnaldo;

public class Battaglia {

    public static boolean scontro(Mortale sfidante1, Mortale sfidante2) {
        Mortale vincitore;

        if (sfidante1 instanceof Giocatore) {
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
