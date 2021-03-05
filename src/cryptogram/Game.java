package cryptogram;

import java.util.Random;

public class Game {

    //variables could be wrong
    private Player currentPlayer;
    private Cryptogram currentGame;
    private int cryptType;


    public Game(Player p, int cryptType){
        this.cryptType = cryptType;
        this.currentPlayer = p;
        this.cryptType = cryptType;
        generateCryptogram();
    }

    public Game(Player p){
        currentGame = new Cryptogram();
        this.currentPlayer = p;

        Random random = new Random();
        int rand = random.nextInt(2);

        this.cryptType = rand;
        generateCryptogram();
    }


    public void getHint() {

    }

    public void loadPlayer() {

    }

    public void playGame() {

    }

    public void generateCryptogram(){
        if(cryptType == 1) {
            currentGame = new NumberCryptogram();
        }
        else
        {
            currentGame = new LetterCryptogram();
        }
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
