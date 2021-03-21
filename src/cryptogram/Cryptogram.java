package cryptogram;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Cryptogram {

    /**
     * Variables
     */
    public String cryptoPhrase;
    private String cryptogramAlphabet;
    public boolean numberMapping;
    public int[] gameMapping = new int[26];
    public int[] letterFrequency;
    public int[] playerMapping = new int[26];
    public int numberOfLettersInPhrase;
    public String newPhrase = "";


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

    public String getCryptoPhrase() {
        return cryptoPhrase;
    }

    public void setCryptoPhrase(String cryptoPhrase) {
        this.cryptoPhrase = cryptoPhrase;
    }

    public void setGameMapping(int[] gameMapping) {
        this.gameMapping = gameMapping;
    }

    public void setPlayerMapping(int[] playerMapping) {
        this.playerMapping = playerMapping;
    }

    public int getNumberOfLettersInPhrase() {
        return numberOfLettersInPhrase;
    }

    public void setNumberOfLettersInPhrase(int numberOfLettersInPhrase) {
        this.numberOfLettersInPhrase = numberOfLettersInPhrase;
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
    }

    /**
     * Blank constructor used for loading cryptos from file
     *
     * @param currentPlayer
     */
    public Cryptogram(Player currentPlayer) {

    }


    /**
     * Used to set varibales from a file, potentially not needed
     *
     * @param cryptoPhrase
     * @param numberMapping
     * @param gameMapping
     * @param letterFrequency
     * @param playerMapping
     * @param numberOfLettersInPhrase
     * @param newPhrase
     */
    public Cryptogram(String cryptoPhrase, boolean numberMapping, int[] gameMapping, int[] letterFrequency, int[] playerMapping,
                      int numberOfLettersInPhrase, String newPhrase) //COULD POSSIBLY BE REMOVED.
    {
        this.cryptoPhrase = cryptoPhrase;
        this.numberMapping = numberMapping;
        this.gameMapping = gameMapping;
        this.letterFrequency = letterFrequency;
        this.playerMapping = playerMapping;
        this.numberOfLettersInPhrase = numberOfLettersInPhrase;
        this.newPhrase = newPhrase;
        System.out.println(getPhrase() + "\n");
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


    /**
     * Undo a user input character
     *
     * @param Letter
     * @return true if successful, false if not
     */
    public boolean undoGivenLetter(char Letter) {
        int letterAsciiValue = Letter - 97;
        boolean worked = false;

        for (int i = 0; i < playerMapping.length; i++) {
            if (playerMapping[i] == letterAsciiValue && validUndoCheck(Letter)) {
                playerMapping[i] = -1;

                System.out.println("\nLetter has been successfully undone.\n");
                return worked = true;
            }
        }
        if (worked == false) {
            System.out.println("\nNot a valid undo request\n");
            return worked = false;
        }
        return false;
    }


    /**
     * Checks if the input letter exists for an undo
     *
     * @param Letter
     * @return true if value can be undo, false if non existent
     */
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


    /**
     * Checks if the game has been completed
     *
     * @return
     */
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


    /**
     * Checks if the game has been won
     *
     * @return true if won, false if not
     */
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


    /**
     * Checks if crypto folder exists
     *
     * @param pathToCryptograms
     * @return file path
     */
    private boolean cryptogramFolderExists(Path pathToCryptograms) {
        return Files.exists(pathToCryptograms);
    }


    /**
     * Create a cryptogram folder to save files
     *
     * @param pathToCryptograms
     * @throws Exception
     */
    private void createCryptogramFolder(Path pathToCryptograms) throws Exception {
        System.out.println("Folder to store cryptograms does not exist. Creating one...");
        try {
            Files.createDirectory(pathToCryptograms);
            System.out.println("Folder successfully created.");
        } catch (Exception e) {
            throw new Exception("Error creating directory for cryptograms.");
        }
    }


    /**
     * Checks if player already had crypto
     *
     * @param fileToSaveCryptogramTo
     * @return File path
     */
    private boolean playerHasCryptoSaved(File fileToSaveCryptogramTo) {
        return Files.exists(fileToSaveCryptogramTo.toPath());
    }


    /**
     * Saves the players cryptogram
     *
     * @param player
     * @return
     */
    public boolean saveCryptogram(Player player) {
        try {
            //Set up variables to be used
            File fileToSaveCryptogramTo;
            BufferedWriter fileWriter;
            BufferedReader fileReader;
            String pathsToCryptoString = Paths.get("").toAbsolutePath().toString() + "\\cryptograms";
            Path pathToCryptograms = Paths.get(pathsToCryptoString);

            //Create the folder if it doesn't exist already
            if (!cryptogramFolderExists(pathToCryptograms)) {
                try {
                    createCryptogramFolder(pathToCryptograms);
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                    return false;
                }
            }

            //Open the file to write. (Creates new one if required)
            fileToSaveCryptogramTo = new File(pathToCryptograms + "\\" + player.getUsername() + ".txt");

            if (playerHasCryptoSaved(fileToSaveCryptogramTo)) { //If the player has a file saved, ask to overwrite
                fileReader = new BufferedReader(new FileReader(fileToSaveCryptogramTo));
                Scanner scan = new Scanner(System.in);
                String sUserAnswer;
                char answer;
                if (!fileReader.readLine().equals(getPhrase())) {
                    System.out.println("You already have a cryptogram saved, do you want to overwrite? (y/n)");
                    sUserAnswer = scan.next();
                    answer = sUserAnswer.charAt(0);

                    if (answer == 'n') {
                        return false;
                    }
                } else {
                    System.out.println("Do you want to save the game? (y/n)");
                    sUserAnswer = scan.next();
                    answer = sUserAnswer.charAt(0);

                    if(answer == 'n') {
                        return false;
                    }
                }
            }

            //Write cryptogram information to file
            fileWriter = new BufferedWriter(new FileWriter(fileToSaveCryptogramTo));
            fileWriter.write(this.cryptoPhrase + "\n");
            fileWriter.write(this.numberMapping + "\n");
            fileWriter.write(Arrays.toString(this.gameMapping) + "\n");
            fileWriter.write(Arrays.toString(this.letterFrequency) + "\n");
            fileWriter.write(Arrays.toString(this.playerMapping) + "\n");
            fileWriter.write(this.numberOfLettersInPhrase + "\n");
            fileWriter.write(this.newPhrase);

            //All info is stored at this point, close the writer
            fileWriter.close();

            //File was saved successfully
            System.out.println("Cryptogram saved successfully.");
            return true;
        } catch (Exception ex) {
            //Something went wrong :(
            System.out.println("There was an error while trying to write to the file.");
            System.out.println("Cryptogram was not saved successfully.");
        }
        return false;
    }


    /**
     * Loading a players cryptogram from a file
     *
     * @param player
     * @return
     */
    public Cryptogram loadCryptogram(Player player) {
        try {
            //Set up variables to be used
            File fileToReadCryptogramFrom;
            BufferedReader fileReader;
            String pathsToCryptoString = Paths.get("").toAbsolutePath().toString() + "\\cryptograms";
            Path pathToCryptograms = Paths.get(pathsToCryptoString);

            //Check if the cryptogram folder exists
            if (!cryptogramFolderExists(pathToCryptograms)) {
                System.out.println("Folder does not exist, no saved cryptograms.");
                return null;
            }

            //Folder exists, check if it contains files
            String pathToUsersCryptogram = "";
            File cryptogramDirectory = new File(pathsToCryptoString);
            String[] fileNames = cryptogramDirectory.list();

            if (fileNames.length == 0) { //Error, the file is empty
                System.out.println("Folder is empty, no saved cryptograms.");
                return null;
            }

            //Folder contains files, check if user has a saved cryptogram
            int hasCryptoSaved = 0;
            for (String file : cryptogramDirectory.list()) {
                if (file.contains(player.getUsername())) {
                    pathToUsersCryptogram = pathToCryptograms + "\\" + file;
                    hasCryptoSaved++;
                }
            }

            if (hasCryptoSaved == 0) { //Error, they have no saved cryptogram
                System.out.println("You do not have a cryptogram saved.");
                return null;
            }

            //User has a saved cryptogram, read the file
            fileToReadCryptogramFrom = new File(pathToUsersCryptogram);
            fileReader = new BufferedReader(new FileReader(fileToReadCryptogramFrom));
            String cryptoPhrase = fileReader.readLine();
            boolean numberMapping = Boolean.parseBoolean(fileReader.readLine());
            int[] gameMapping = parseArrayFromFile(fileReader.readLine());
            int[] letterFrequency = parseArrayFromFile(fileReader.readLine());
            int[] playerMapping = parseArrayFromFile(fileReader.readLine());
            int numberOfLettersInPhrase = Integer.parseInt(fileReader.readLine());
            String newPhrase = fileReader.readLine();

            //Information read at this point, close the reader
            fileReader.close();

            //Create the object depending on its type
            Cryptogram loadedCryptogram;
            if (numberMapping) { //If the cryptogram is number mapping
                loadedCryptogram = new NumberCryptogram(
                        cryptoPhrase, numberMapping, gameMapping, letterFrequency, playerMapping, numberOfLettersInPhrase, newPhrase
                );
            } else { //If the cryptogram is letter mapping
                loadedCryptogram = new LetterCryptogram(
                        cryptoPhrase, numberMapping, gameMapping, letterFrequency, playerMapping, numberOfLettersInPhrase, newPhrase
                );
            }

            return loadedCryptogram;
        } catch (Exception ex) {
            //Something went wrong :(
            System.out.println("There was an error while trying to read from the file.");
            System.out.println("Cryptogram was not loaded successfully.");
        }
        return null;
    }


    /**
     * Parsing file
     * @param string
     * @return result of parse
     */
    private int[] parseArrayFromFile(String string) {
        String[] strings = string.replace("[", "").replace("]", "").split(", ");
        int[] result = new int[strings.length];
        for (int i = 0; i < result.length; i++) {
            result[i] = Integer.parseInt(strings[i]);
        }
        return result;
    }
}