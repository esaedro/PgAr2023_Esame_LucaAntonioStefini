package arnaldo;

/**
 * Classe che gestisce gli scontri tra due mortali
 */
public class Battaglia {

    /**
     * Esegue uno scontro tra due mortali
     * @param sfidante1
     * @param sfidante2
     * @return true se vince il giocatore, false altrimenti
     */
    public static boolean scontro(Mortale sfidante1, Mortale sfidante2) {
        Mortale vincitore;

        if (sfidante1 instanceof Giocatore) {
            while (sfidante1.inVita() && sfidante2.inVita()) {
                sfidante1.attacca(sfidante2);
                InterazioneUtente.sleep();
                InterazioneUtente.mostraStatistiche(sfidante1, sfidante2);
                if (!sfidante2.inVita()) {
                    break;
                }
                sfidante2.attacca(sfidante1);
                InterazioneUtente.mostraStatistiche(sfidante1, sfidante2);
                InterazioneUtente.sleep();
            }
        }
        else {
            while (sfidante1.inVita() && sfidante2.inVita()) {
                sfidante2.attacca(sfidante1);
                InterazioneUtente.sleep();
                InterazioneUtente.mostraStatistiche(sfidante1, sfidante2);
                if (!sfidante1.inVita()) {
                    break;
                }
                sfidante1.attacca(sfidante2);
                InterazioneUtente.sleep();
                InterazioneUtente.mostraStatistiche(sfidante1, sfidante2);
            }
        }

        vincitore = sfidante1.inVita() ? sfidante1 : sfidante2;

        return vincitore instanceof Giocatore ? true : false;
    }
}
