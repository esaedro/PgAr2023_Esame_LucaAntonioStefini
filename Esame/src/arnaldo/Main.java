package arnaldo;

public class Main {
    /*
     * Nel far partire il programma a volte si verifica un errore nella generazione della mappa, non sono riuscito a risolverlo.
     * La maggior parte delle volte il programma funziona senza problemi
     */
    public static void main(String[] args) {
        InterazioneUtente.inizioPartita();
        Mondo mondo = InterazioneUtente.scegliMondo();
        Giocatore giocatore = new Giocatore("G1");
        while (mondo != null && giocatore.getVite() > 0) {
            Partita partita = new Partita(mondo, giocatore);
            partita.getMondoScelto().entra(partita);
            mondo = InterazioneUtente.scegliMondo();
        }
    }
}
