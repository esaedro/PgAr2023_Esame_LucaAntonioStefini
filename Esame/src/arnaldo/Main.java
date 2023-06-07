package arnaldo;

public class Main {
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
