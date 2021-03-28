package cryptogram;

import java.util.Scanner;

class NumberCryptogram extends Cryptogram {

    /**
     * Constructor
     */
    public NumberCryptogram() {
        this.numberMapping = true;
        mapNumbers();
    }

    /**
     * Constructor to map a crypto from a file
     *
     * @param cryptoPhrase
     * @param numberMapping
     * @param gameMapping
     * @param letterFrequency
     * @param playerMapping
     * @param numberOfLettersInPhrase
     * @param newPhrase
     */
    public NumberCryptogram(String cryptoPhrase, boolean numberMapping, int[] gameMapping, int[] letterFrequency, int[] playerMapping,
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
    public void mapNumbers() {
        for (int i = 0; i < getPhrase().length(); i++) {
            char currentLetter = getPhrase().charAt(i);

            if (currentLetter == 32) {
                newPhrase = newPhrase + " ";
            } else {
                if (gameMapping[currentLetter - 97] > 8) {
                    newPhrase = newPhrase + (gameMapping[currentLetter - 97]) + "  ";
                } else {
                    newPhrase = newPhrase + " " + (gameMapping[currentLetter - 97]) + "   ";
                }
            }
        }
        setNewPhrase(newPhrase);
        System.out.println(newPhrase);
    }


    /**
     * Gets users input phrase
     */
    public void showMappedLetters() {
        String userPhrase = "";

        for (int i = 0; i < newPhrase.length(); i++) {

            char letter = newPhrase.charAt(i);

            if (letter == 32) {
                userPhrase += "  ";
                continue;
            }

            String numberString = "";

            while (letter != 32) {
                numberString = numberString + letter;
                i++;
                letter = newPhrase.charAt(i);
            }


            int letterToBeChecked = Integer.parseInt(numberString);

            for (int j = 0; j < gameMapping.length; j++) {
                if (gameMapping[j] == letterToBeChecked) {

                    if (playerMapping[j] == -1) {
                        userPhrase += "";

                    } else {
                        userPhrase = userPhrase + ((char) (playerMapping[j] + 97));
                    }
                }
            }
        }
        System.out.println(userPhrase);
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
            System.out.println("\n" + newPhrase + "\n");
            return enterLetter(number, letterToReplace);

        } else {
            System.out.println("\nInvalid guess. This value is already mapped.\n");
            System.out.println(newPhrase);
            return false;
        }
    }


    /**
     * Enters input to the mapping
     *
     * @param numberInput
     * @param guessInput
     * @return true if letter is mapped. false if not
     */
    public boolean enterLetter(int numberInput, char guessInput) {
        int letterInputAscii = numberInput;
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
     *
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


    public boolean getNumberHint(int number) {

        for (int i = 0; i < gameMapping.length; i++) {
            if (gameMapping[i] == number) {

                if(playerMapping[i] == i){
                    System.out.println("\nThe letter " + (char) (playerMapping[i] + 97)  + " has already been mapped to " + number + " correctly.\n");
                    return false;
                }

                for (int j = 0; j < playerMapping.length; j++) {
                    if (playerMapping[j] == i) {
                        playerMapping[j] = -1;
                    }
                }
                playerMapping[i] = i;
                System.out.println("\nThe letter " + (char) (playerMapping[i] + 97)  + " has been mapped to " + number + "\n");
                break;
            }
        }
        return true;
    }

    public void printLetterFrequency() {

        StringBuilder phrase = new StringBuilder();
        for (int i = 0; i < letterFrequency.length; i++) {

            if (letterFrequency[i] > 0) {
                phrase.append(String.format("%d - %d", gameMapping[i], letterFrequency[i])).append(" - ").append((letterFrequency[i] * 100) / cryptoPhrase.length()).append("%\n");
            }
        }
        System.out.println(phrase);
        System.out.println("A:8  B:1.5  C:3  D:4  E:13  F:2  G:2  H:6  I:7  J:0.2  K:1  L:4  M:2.5  N:7  O:8  P:2  Q:1  R:6  S:6  T:9  U:3  V:1  W:2.5  X:0.2  Y:2  Z:0.01 \n");

    }



}



