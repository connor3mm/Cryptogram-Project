package cryptogram;

import java.sql.SQLOutput;
import java.util.Scanner;

class LetterCryptogram extends Cryptogram {

    /**
     * variables
     */
    private String cryptogramAlphabet;
    private String newPhrase = getNewPhrase();


    /**
     * constructor
     */
    public LetterCryptogram() {
        mapLetters();
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
        System.out.println(newPhrase);

    }

    public void showMappedLetters() {
        String userPhrase = "";

        System.out.println(newPhrase.length());
        for (int i = 0; i < newPhrase.length(); i++) {

            char letter = newPhrase.charAt(i);

            int j = 0;
            while(letter == 32){
                i++;
                letter = newPhrase.charAt(i);
            }

            int letterInputAscii = letter - 97;


            if (gameMapping[j] == letterInputAscii)
            if (playerMapping[letterInputAscii] > -1) {
                userPhrase += (char) playerMapping[letterInputAscii] + 97 + " ";
            }
            else{
                userPhrase+= " ";
            }
            j++;
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
            System.out.println("Invalid guess. This value is already mapped.");
            return false;
        }
    }


    /**
     * Enters input to the mapping
     *
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