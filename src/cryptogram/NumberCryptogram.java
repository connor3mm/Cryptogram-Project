package cryptogram;

import java.util.Scanner;

class NumberCryptogram extends Cryptogram {
    /**
     * Variable
     */
    private String cryptogramAlphabet;
    private String newPhrase = getNewPhrase();

    /**
     * Constructor
     */
    public NumberCryptogram() {
        mapNumbers();
    }


    /**
     * maps the game mapping numbers to each letter it represents in the phrase
     */
    public void mapNumbers() {
        for (int i = 0; i < getPhrase().length(); i++) {
            char currentLetter = getPhrase().charAt(i);

            if (currentLetter == 32) {
                newPhrase = newPhrase + " ";
            } else {
                if (gameMapping[currentLetter - 97] > 8) {
                    newPhrase = newPhrase + (gameMapping[currentLetter - 97]) + "   ";
                } else {
                    newPhrase = newPhrase + " " + (gameMapping[currentLetter - 97]) + "   ";
                }
            }
        }
        setNewPhrase(newPhrase);
        System.out.println(newPhrase);
    }


    /**
     * @param number
     * @return true if letter is mapped, false if not
     */
    public boolean getPlainNumber(int number) {
        Scanner scan = new Scanner(System.in);

        if (checkValueIsAlreadyMapped(number)) {
            System.out.println("The number '" + number + "' is already mapped, do you want to overwrite? (y/n)");
            scan = new Scanner(System.in);

            String userInput = scan.next();
            char answer = userInput.charAt(0);

            if (answer == 'n') {
                return false;
            }
        }
        System.out.println("Which letter would you like to map number '" + number + "' to?");
        String userInput2 = scan.next();
        char letterToReplace = userInput2.charAt(0);
        if (guessIsValid(letterToReplace)) {
            return enterLetter(number, letterToReplace);
        } else {
            System.out.println("Invalid guess. This value is already mapped.");
            return false;
        }
    }

    /**
     *Enters input to the mapping
     * @param numberInput
     * @param guessInput
     * @return true if letter is mapped. false if not
     */
    public boolean enterLetter(int numberInput, char guessInput) {
        int letterInputAscii = numberInput - 1;
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
    public boolean checkValueIsAlreadyMapped(int input) {
        int letterToBeChecked = input - 1;

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