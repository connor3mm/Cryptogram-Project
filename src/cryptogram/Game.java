package cryptogram;

import java.util.Random;
import java.util.Scanner;

public class Game {

    /**
     * variables
     */
    private Player currentPlayer;
    private Cryptogram currentGame;
    private int cryptType;


    /**
     * Constructors
     *
     * @param p         - Player
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


    /**
     * Generating number or letter cryptogram
     */
    public void generateCryptogram() {
        if (cryptType == 1) {
            gamesPlayedInc();
            currentGame = new NumberCryptogram();
        } else {
            gamesPlayedInc();
            currentGame = new LetterCryptogram();
        }
    }

    /**
     * Incrementing total games played
     */
    public void gamesPlayedInc() {
        currentPlayer.incrementCryptogramPlayed();
    }


    public void enterLetter() {
        boolean guess;
        boolean completed;
        Scanner scan = new Scanner(System.in);
        if (currentGame.getClass().getName().equals(LetterCryptogram.class.getName())) {
            System.out.println("Enter a letter to map: ");
            String result = scan.next();
            char charResult = result.charAt(0);
            guess = currentGame.getPlainLetter(charResult);
            currentPlayer.updateAccuracy(guess);
            completed = currentGame.checkIfGameCompleted();
            if (completed) {
                System.out.println("Game has been Completed");
            } else {
                System.out.println("Game had not been completed");
            }

        } else {
            System.out.println("Enter a number to map (1-25): ");
            int result = scan.nextInt();
            guess = currentGame.getPlainNumber(result);
            currentPlayer.updateAccuracy(guess);
            completed = currentGame.checkIfGameCompleted();
            if (completed) {
                System.out.println("Game has been Completed");
            } else {
                System.out.println("Game had not been completed");
            }
        }
    }

    public void undoLetter() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter a letter to undo: ");
        String result = scan.next();
        char charResult = result.charAt(0);
        currentGame.undoGivenLetter(charResult);
    }


    public void getHint() {

    }

    public void loadPlayer() {

    }

    public void playGame() {

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

