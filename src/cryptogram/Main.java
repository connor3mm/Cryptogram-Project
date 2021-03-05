package cryptogram;

public class Main {

    public static void main (String args[]) {
        Player p = new Player("Jackson");
        Game game = new Game(p, 2);

        game.generateCryptogram();
    }
}
