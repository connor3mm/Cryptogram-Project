package cryptogram;

import java.util.Random;

public class Game {

    //variables
    private Player currentPlayer;
    private Cryptogram currentGame;
    private int cryptType;

    //Constructors x2
    public Game(Player p, int cryptType) {
        this.currentPlayer = p;
        this.cryptType = cryptType;

    }
    //Constructor with one perimeter
    public Game(Player p) {
        this.currentPlayer = p;

        Random random = new Random();
        int rand = random.nextInt(2);

        this.cryptType = rand;
    }

    //Generating number or letter cryptogram
    public void generateCryptogram() {
        if (cryptType == 1) {

            currentGame = new NumberCryptogram();
        } else {
            currentGame = new LetterCryptogram();
        }
    }


    public void getHint() {

    }

    public void loadPlayer() {

    }

    public void playGame() {

    }

    public void enterLetter() {


    }

    public void undoLetter() {

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

