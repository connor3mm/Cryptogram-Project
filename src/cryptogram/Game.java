package cryptogram;

import java.util.Random;

public class Game {

    //variables could be wrong
    private Player currentPlayer;
    private String cryptType;
    private Cryptogram currentGame;


    public void Game(Player p, String cryptType) {

        currentPlayer = p;
        this.cryptType = cryptType;
    }

    public void Game(Player p) {
        Random rNum = new Random();
        boolean value = rNum.nextBoolean();

        currentPlayer = p;
        if(value == true){
            currentGame = new LetterCryptogram();

        }else{
            currentGame = new numberCryptogram();
        }

    }


    public void getHint() {

    }

    public void loadPlayer() {

    }

    public void playGame() {

    }

    public void generateCryptogram() {

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
