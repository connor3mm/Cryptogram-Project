package cryptogram;

public class Main {

    //Test
    public static void main (String args[]) {
        Player p = new Player("John");
        Game game = new Game(p, 0);
        game.generateCryptogram();
        game.enterLetter();
    }
}
