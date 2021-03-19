package cryptogram;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Cryptogram {

    /**
     * Variables
     */
    private String cryptoPhrase;
    private String cryptogramAlphabet;
    private boolean numberMapping;
    public int[] gameMapping = new int[26];
    private int[] letterFrequency;
    public int[] playerMapping = new int[26];
    public int numberOfLettersInPhrase;
    private String newPhrase = "";

    /**
     * Getters and setters
     *
     * @return
     */
    public String getNewPhrase() {
        return newPhrase;
    }

    public int[] getGameMapping() {
        return gameMapping;
    }

    public void setNewPhrase(String newPhrase) {
        this.newPhrase = newPhrase;
    }

    public int[] getPlayerMapping() {
        return playerMapping;
    }

    public boolean getPlainLetter(char letter) {
        return true;
    }

    public boolean getPlainNumber(int number) {
        return true;
    }

    public String getPhrase() {
        return cryptoPhrase;
    }

    public void setPhrase(String phrase) {
        this.cryptoPhrase = phrase;
    }

    public String getCryptogramAlphabet() {
        return cryptogramAlphabet;
    }

    public void setCryptogramAlphabet(String cryptogramAlphabet) {
        this.cryptogramAlphabet = cryptogramAlphabet;
    }

    public boolean isNumberMapping() {
        return numberMapping;
    }

    public void setNumberMapping(boolean numberMapping) {
        this.numberMapping = numberMapping;
    }

    public int[] getLetterFrequency() {
        return letterFrequency;
    }

    public void setLetterFrequency(int[] letterFrequency) {
        this.letterFrequency = letterFrequency;
    }


    /**
     * Cryptogram Constructor
     */
    public Cryptogram() {
        System.out.println("New game is being created...");
        setRandomCryptoPhrase();
        createCryptoMapping();
        setLetterFrequency();
        getNumberOfLetters();
        System.out.println("Successfully created a new game...\n");
        System.out.println(getPhrase() + "\n");


        for (int i = 0; i < gameMapping.length; i++) {
            System.out.println("index - " + i + " " + gameMapping[i]);
        }
    }


    /**
     * Generates cryptogram phrases and puts into an Arraylist
     */
    public ArrayList<String> getCryptoPhrases() {
        ArrayList<String> cryptoPhrases = new ArrayList<>();

        File cryptoFileName;
        BufferedReader fileReader;
        String cryptoPhrase;

        try {
            cryptoFileName = new File("solvedCryptos.txt");
            fileReader = new BufferedReader(new FileReader(cryptoFileName));
            cryptoPhrase = fileReader.readLine();

            if (cryptoPhrase == null) {
                throw new Exception("The file is empty.");
            }

            //while theres a phrase left, add to list
            while (cryptoPhrase != null) {
                cryptoPhrases.add(cryptoPhrase);
                cryptoPhrase = fileReader.readLine();
            }
            fileReader.close(); //Close reader when the file has been read
        } catch (Exception ex) {
            System.out.println("There was an error while trying to read from the file.");
            System.exit(-1);
        }

        return cryptoPhrases;
    }


    /**
     * Sets 1 out of 15 crypto phrases
     */
    public void setRandomCryptoPhrase() {
        ArrayList<String> cryptoPhrases = getCryptoPhrases();
        int numberOfCryptoPhrases = cryptoPhrases.size();

        cryptoPhrase = cryptoPhrases.get(new Random().nextInt(numberOfCryptoPhrases));
    }


    /**
     * Maps a number of the alphabet(in order) to random number
     */
    public void createCryptoMapping() {
        Random random = new Random();

        ArrayList<Integer> alphabetIndex = new ArrayList<>();
        for (int i = 0; i < 26; i++)
            alphabetIndex.add(i);

        for (int j = 0; j < 26; j++) {
            playerMapping[j] = -1;
        }

        int randomNumber;
        int i = 0;
        while (i < 25) {
            randomNumber = random.nextInt(26 - i);

            while (alphabetIndex.get(randomNumber) == i) {
                randomNumber = random.nextInt(26 - i);
            }

            gameMapping[i] = alphabetIndex.remove(randomNumber);
            i++;
        }

        if (alphabetIndex.get(0) == 25)
            createCryptoMapping();
        else {
            gameMapping[25] = alphabetIndex.remove(0);
        }
    }


    public boolean undoGivenLetter(char Letter) {
        int letterAsciiValue = Letter - 97;
        boolean worked = false;

        for (int i = 0; i < playerMapping.length; i++) {
            if (playerMapping[i] == letterAsciiValue && validUndoCheck(Letter)) {
                playerMapping[i] = -1;

                System.out.println("Letter has been successfully undone.");
                return worked = true;
            }
        }
        if (worked == false) {
            System.out.println("Not a valid undo request");
            return worked = false;
        }
        return false;
    }

    public boolean validUndoCheck(char Letter) {
        int letterAsciiValue = Letter - 97;

        int i = 0;
        while (i < playerMapping.length) {
            if (playerMapping[i] == letterAsciiValue) {
                return true;
            }
            i++;
        }
        return false;
    }


    public boolean checkIfGameCompleted() {
        int count = 0;
        for (int i = 0; i < playerMapping.length; i++) {
            if (playerMapping[i] > -1) {
                count++;
            }
        }
        if (count == numberOfLettersInPhrase) {
            return true;
        }
        return false;
    }

    /**
     * Puts letter Frequency into array
     */
    private void setLetterFrequency() {
        int CurrLetter;
        letterFrequency = new int[26];

        for (int j = 0; j < cryptoPhrase.length(); j++) {
            CurrLetter = cryptoPhrase.charAt(j);
            if (CurrLetter >= 97 && CurrLetter <= 122) {
                letterFrequency[CurrLetter - 97]++;
            }
        }
    }

    /**
     * Calculates the letter frequency of letters
     */
    private void getNumberOfLetters() {
        int count = 0;
        for (int i = 0; i < letterFrequency.length; i++) {
            if (letterFrequency[i] > 0) {
                count++;
            }
        }
        numberOfLettersInPhrase = count;
    }

    /**
     * Checks if the guess has been mapped
     *
     * @param letter
     * @return True if its mapped, false if not
     */
    public boolean checkIfGuessMapped(char letter) {
        int letterAsciiValue = letter - 97;

        for (int i = 0; i < playerMapping.length; i++) {
            if (playerMapping[i] == letterAsciiValue) {
                return true;
            }
        }
        return false;
    }

    /**
     * Error checking on guess
     *
     * @param guess
     * @return true if valid, false if not
     */
    public boolean guessIsValid(char guess) {
        if (guess >= 97 && guess <= 122) {
            if (checkIfGuessMapped(guess))
                return false;
            else
                return true;
        }
        return false;
    }


    public void showMappedLetters() {


    }


    public boolean gameSuccess() {
        int count = 0;

        for (int i = 0; i < gameMapping.length; i++) {
            if (playerMapping[i] > -1 && i == playerMapping[i]) {
                count++;
            }
        }

        if (count == numberOfLettersInPhrase) {
            return true;
        }

        return false;
    }

    public boolean saveCryptogram(Player player) {
        boolean successfullySaved = false;

        File fileToSaveCryptogramTo;
        BufferedWriter fileWriter;

        try {
            //Open the file to write. (Creates new one if required)
            fileToSaveCryptogramTo = new File(player.getUsername() + ".txt");
            fileWriter = new BufferedWriter(new FileWriter(fileToSaveCryptogramTo));

            //Write cryptogram information to file
            fileWriter.write(this.cryptoPhrase + "\n");
            fileWriter.write(this.cryptogramAlphabet + "\n");
            fileWriter.write(this.numberMapping + "\n");
            fileWriter.write(Arrays.toString(this.gameMapping) + "\n");
            fileWriter.write(Arrays.toString(this.letterFrequency) + "\n");
            fileWriter.write(Arrays.toString(this.playerMapping) + "\n");
            fileWriter.write(this.numberOfLettersInPhrase + "\n");
            fileWriter.write(this.newPhrase + "\n");

            //All info is stored at this point, close the reader
            fileWriter.close();

            //File was saved successfully
            successfullySaved = true;
        } catch (Exception ex) {
            System.out.println("There was an error while trying to write to the file.");
            System.exit(-1);
        }

        return successfullySaved;
    }

}