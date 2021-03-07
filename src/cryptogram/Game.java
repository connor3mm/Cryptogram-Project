package cryptogram;

import java.util.Random;

public class Game {

    //variables
    private Player currentPlayer;
    private Cryptogram currentGame;
    private int cryptType;

    /**
     * Constructors
     * @param p - Player
     * @param cryptType - letter or number
     */
    //Constructors x2 parameter
    public Game(Player p, int cryptType) {
        this.currentPlayer = p;
        this.cryptType = cryptType;
    }

    //Constructor with one parameter
    public Game(Player p) {
        this.currentPlayer = p;

        Random random = new Random();
        int rand = random.nextInt(2);

        this.cryptType = rand;
    }

    //Generating number or letter cryptogram
    public void generateCryptogram() {
        if (cryptType == 1) {
            gamesPlayedInc();
            currentGame = new NumberCryptogram();
        } else {
            gamesPlayedInc();
            currentGame = new LetterCryptogram();
        }
        undoLetter();
    }

    public void gamesPlayedInc(){
        int games = currentPlayer.getCryptogramsPlayed();
        currentPlayer.setCryptogramsPlayed(games+1);
    }

    public void getHint() {

    }

    public void loadPlayer() {

    }

    public void playGame() {

    }

    public void enterLetter() {
        currentGame.getPlainLetter('s'); //asked for an input 'h'
        currentGame.getPlainLetter('h'); //asked for input 'g'
        currentGame.getPlainLetter('g'); //asked for input 'x'
        currentGame.getPlainLetter('a');
    }

    public void undoLetter() {
        currentGame.undoGivenLetter('l');
    }


    public void viewFrequencies() {

    }

    public void saveGame() {

    }

    public void loadGame() {

    }

    public void showSolution() {

    }
}

