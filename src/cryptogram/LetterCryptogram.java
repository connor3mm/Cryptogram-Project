package cryptogram;

import java.sql.SQLOutput;
import java.util.Scanner;

class LetterCryptogram extends Cryptogram {

    /**
     * variables
     */
    private String cryptogramAlphabet;
    // public String newPhrase = getNewPhrase();


    /**
     * constructor
     */
    public LetterCryptogram() {
        this.numberMapping = false;
        mapLetters();
    }

    /**
     * Constructor to map a crypto from a file
     * @param cryptoPhrase
     * @param numberMapping
     * @param gameMapping
     * @param letterFrequency
     * @param playerMapping
     * @param numberOfLettersInPhrase
     * @param newPhrase
     */
    public LetterCryptogram(String cryptoPhrase, boolean numberMapping, int[] gameMapping, int[] letterFrequency, int[] playerMapping,
                            int numberOfLettersInPhrase, String newPhrase) {
        this.cryptoPhrase = cryptoPhrase;
        this.numberMapping = numberMapping;
        this.gameMapping = gameMapping;
        this.letterFrequency = letterFrequency;
        this.playerMapping = playerMapping;
        this.numberOfLettersInPhrase = numberOfLettersInPhrase;
        this.newPhrase = newPhrase;
    }


    /**
     * maps the game mapping numbers to each letter it represents in the phrase
     */
    public void mapLetters() {
        for (int i = 0; i < getPhrase().length(); i++) {
            char currentLetter = getPhrase().charAt(i);

            if (currentLetter == 32) {
                newPhrase = newPhrase + " ";
            } else {
                newPhrase = newPhrase + (char) (gameMapping[currentLetter - 97] + 97) + "    ";
            }
        }
        setNewPhrase(newPhrase);

    }


    /**
     * Gets users input phrase
     */
    public void showMappedLetters() {
        String userPhrase = "";

        for (int i = 0; i < newPhrase.length(); i++) {

            char letter = newPhrase.charAt(i);


            if (letter == 32) {
                userPhrase += " ";
                continue;
            }

            int letterToBeChecked = letter - 97;

            for (int j = 0; j < gameMapping.length; j++) {
                if (gameMapping[j] == letterToBeChecked) {

                    if (playerMapping[j] == -1) {
                        userPhrase += " ";

                    } else {
                        userPhrase = userPhrase + (char) (playerMapping[j] + 97);
                    }
                }
            }
        }
        System.out.println(userPhrase);
    }


    /**
     * @param letter
     * @return true if letter is mapped, false if not
     */
    public boolean getPlainLetter(char letter) {
        Scanner scan = new Scanner(System.in);
        if (checkValueIsAlreadyMapped(letter)) {
            System.out.println("The letter '" + letter + "' is already mapped, do you want to overwrite? (y/n)");
            scan = new Scanner(System.in);

            String userInput = scan.next();
            char answer = userInput.charAt(0);

            if (answer == 'n') {
                return false;
            }
        }
        System.out.println("\nWhich letter would you like to map '" + letter + "' to?");
        String userInput2 = scan.next();
        char letterToReplace = userInput2.charAt(0);

        if (guessIsValid(letterToReplace)) {
            System.out.println("\n" + newPhrase + "\n");
            return enterLetter(letter, letterToReplace);

        } else {
            System.out.println("\nInvalid guess. This value is already mapped.\n");
            System.out.println(newPhrase);
            return false;
        }
    }


    /**
     * Enters input to the mapping
     * @param letterInput
     * @param guessInput
     * @return true if letter is mapped. false if not
     */
    public boolean enterLetter(char letterInput, char guessInput) {
        int letterInputAscii = letterInput - 97;
        int guessInputAscii = guessInput - 97;

        for (int i = 0; i < gameMapping.length; i++) {
            if (gameMapping[i] == letterInputAscii) {
                playerMapping[i] = guessInputAscii;
                return i == guessInputAscii;
            }
        }
        return false;
    }


    /**
     * Checks if the input char is mapped
     * @param input
     * @return true if value is mapped, false if not
     */
    public boolean checkValueIsAlreadyMapped(char input) {
        int letterToBeChecked = input - 97;

        for (int i = 0; i < gameMapping.length; i++) {
            if (gameMapping[i] == letterToBeChecked) {
                if (playerMapping[i] == -1)
                    return false;
                else
                    return true;
            }
        }
        return true;
    }
}