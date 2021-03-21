package cryptogram;

import java.util.Random;
import java.util.Scanner;

public class Game {

    /**
     * variables
     */
    private Player currentPlayer;
    public Cryptogram currentGame;
    private int cryptType;


    /**
     * Default constuctor
     */
    public Game() {
        this.currentPlayer = null;
        this.cryptType = 0;
    }

    /**
     * Constructor with one parameter
     */
    public Game(Player p) {
        this.currentPlayer = p;

        Random random = new Random();
        int rand = random.nextInt(2);

        this.cryptType = rand;
    }

    /**
     * Constructor x2 parameter
     * @param p         - Player
     * @param cryptType - letter or number
     */
    public Game(Player p, int cryptType) {
        this.currentPlayer = p;
        this.cryptType = cryptType;
    }


    //Methods


    /**
     * Incrementing total games played
     */
    public void gamesPlayedInc() {
        currentPlayer.incrementCryptogramPlayed();
    }


    /**
     * Saves current player
     */
    public void savePlayer() {
        currentPlayer.savePlayersDetails(currentPlayer);
    }


    /**
     * Saves current game
     */
    public void saveGame() {
        try {
            currentGame.saveCryptogram(currentPlayer);
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }


    /**
     * Loads game from a file depending on player
     */
    public void loadGame() {
        currentGame = new Cryptogram(currentPlayer);
        try {
            currentGame = currentGame.loadCryptogram(currentPlayer);
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
        if(currentGame == null) return;
        printVariables();
        currentGame.showMappedLetters();
    }




    /**
     * Generating number or letter cryptogram
     */
    public void generateCryptogram() {
        if (cryptType == 1) {
            gamesPlayedInc();
            currentGame = new NumberCryptogram();
            printVariables();
        } else {
            gamesPlayedInc();
            currentGame = new LetterCryptogram();
            printVariables();
        }
    }


    /**
     * Allows user to enter letter to cryptogram
     */
    public void enterLetter() {
        boolean guess;
        Scanner scan = new Scanner(System.in);

        if (currentGame.getClass().getName().equals(LetterCryptogram.class.getName())) {
            System.out.println("Enter a letter to map: ");

            String result = scan.next();
            char charResult = result.charAt(0);
            guess = currentGame.getPlainLetter(charResult);
            cryptoChecks(guess);

        } else {
            System.out.println("Enter a number to map (1-25): ");

            int result = scan.nextInt();
            guess = currentGame.getPlainNumber(result);
            cryptoChecks(guess);
        }
    }


    /**
     * Undo letter from crypto
     */
    public void undoLetter() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter a letter to undo: ");
        String result = scan.next();
        char charResult = result.charAt(0);
        currentGame.undoGivenLetter(charResult);

        System.out.println(currentGame.newPhrase);
        currentGame.showMappedLetters();
    }


    /**
     * Checks if game has been won or not
     */
    public void cryptoChecks(boolean guess) {

        boolean completed;
        boolean win;

        currentGame.showMappedLetters();
        currentPlayer.updateAccuracy(guess);
        completed = currentGame.checkIfGameCompleted();
        win = currentGame.gameSuccess();

        if (completed && win) {
            System.out.println("\nGame has been Completed and WON");
            currentPlayer.incrementCryptogramCompleted();
            System.exit(1);

        } else if (completed && !win) {
            System.out.println("\nGame has been Completed and NOT WON, try again");

        } else
            System.out.println("\nGame had not been completed");
    }


    /**
     * Prints crypto mapped phrase, normal and mapped index and players input phrase
     */
    public void printVariables() {
        System.out.println(currentGame.getPhrase() + "\n");

        for (int i = 0; i < currentGame.gameMapping.length; i++) {
            System.out.println("index - " + i + " " + currentGame.gameMapping[i]);
        }

        System.out.println(currentGame.newPhrase);
    }


    public void getHint() {

    }


    public void viewFrequencies() {

    }

    public void showSolution() {

    }

}

