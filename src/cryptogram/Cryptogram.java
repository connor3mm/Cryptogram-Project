package cryptogram;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class Cryptogram {

    /**
     * Variables
     */
    public String cryptoPhrase;
    public boolean numberMapping;
    public int[] gameMapping = new int[26];
    public int[] letterFrequency;
    public int[] playerMapping = new int[26];
    public int numberOfLettersInPhrase;
    public String newPhrase = "";


    /**
     * Getters and setters
     *
     * @return the new phrase
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



    public boolean getPlainLetter(char letter) {
        return true;
    }

    public boolean getLetterHint(char letter) {
        return true;
    }

    public boolean getNumberHint(int number) {
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


    /**
     * Cryptogram Constructor
     */
    public Cryptogram() {
        System.out.println("New game is being created...");
        //setPhrase("abc");
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
     * Used to set variables from a file, potentially not needed
     *
     * @param cryptoPhrase cryptoPhrase
     * @param numberMapping numberMapping
     * @param gameMapping gameMapping
     * @param letterFrequency letterFrequency
     * @param playerMapping playerMapping
     * @param numberOfLettersInPhrase numberOfLettersInPhrase
     * @param newPhrase newPhrase
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
     * @param Letter Letter
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
     * @return True if game complete else false
     */
    public boolean checkIfGameCompleted() {
        int count = 0;
        for (int i = 0; i < playerMapping.length; i++) {
            if (playerMapping[i] > -1) {
                count++;
            }
        }
        return count == numberOfLettersInPhrase;
    }


    /**
     * Puts letter Frequency into array
     */
    public void setLetterFrequency() {
        int CurrLetter;
        letterFrequency = new int[26];

        for (int j = 0; j < cryptoPhrase.length(); j++) {
            CurrLetter = cryptoPhrase.charAt(j);
            if (CurrLetter >= 97 && CurrLetter <= 122) {
                letterFrequency[CurrLetter - 97]++;
            }
        }
    }

    public void printLetterFrequency() {

        StringBuilder phrase = new StringBuilder();
        for (int i = 0; i < letterFrequency.length; i++) {

            if (letterFrequency[i] > 0) {
                phrase.append(String.format("%c - %d", (char) gameMapping[i] + 97, letterFrequency[i])).append(" - ").append((letterFrequency[i] * 100) / cryptoPhrase.length()).append("%\n");
            }
        }
        System.out.println(phrase);
        System.out.println("A:8  B:1.5  C:3  D:4  E:13  F:2  G:2  H:6  I:7  J:0.2  K:1  L:4  M:2.5  N:7  O:8  P:2  Q:1  R:6  S:6  T:9  U:3  V:1  W:2.5  X:0.2  Y:2  Z:0.01 \n");

    }

    /**
     * Calculates the letter frequency of letters
     */
    public void getNumberOfLetters() {
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
            return !checkIfGuessMapped(guess);
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


    public void showSolution() {

        for (int i = 0; i < gameMapping.length; i++) {
            playerMapping[i] = i;
        }
    }

    /**
     * Checks if crypto folder exists
     *
     * @param pathToCryptograms pathToCryptograms
     * @return file path
     */
    private boolean cryptogramFolderExists(Path pathToCryptograms) {
        return Files.exists(pathToCryptograms);
    }


    /**
     * Create a cryptogram folder to save files
     *
     * @param pathToCryptograms pathToCryptograms
     * @throws Exception error
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
     * @param fileToSaveCryptogramTo fileToSaveCryptogramTo
     * @return File path
     */
    private boolean playerHasCryptoSaved(File fileToSaveCryptogramTo) {
        return Files.exists(fileToSaveCryptogramTo.toPath());
    }


    /**
     * Saves the players cryptogram
     *
     * @param player player
     * @return False if fails, true if succeeds
     */
    public boolean saveCryptogram(Player player) throws Exception {
        try {
            //Set up variables to be used
            File fileToSaveCryptogramTo;
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

            if (userChoosesNotToOverwriteOrSave(fileToSaveCryptogramTo)) return false;

            saveCryptogram(fileToSaveCryptogramTo);

            //File was saved successfully
            System.out.println("Cryptogram saved successfully.");
            return true;
        } catch (Exception ex) {
            //Something went wrong :(
            throw new Exception("There was an error while trying to save the cryptogram.");
        }
    }

    /**
     * @param fileToSaveCryptogramTo - File which crypto will be saved to
     * @throws Exception - Error saving the crypto
     */
    private void saveCryptogram(File fileToSaveCryptogramTo) throws Exception {
        BufferedWriter fileWriter;
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
    }

    /**
     * @param fileToSaveCryptogramTo - File which cryptogram is being saved
     * @return - If user chose to overwrite or save (true/false)
     * @throws IOException - Error with input
     */
    private boolean userChoosesNotToOverwriteOrSave(File fileToSaveCryptogramTo) throws IOException {
        BufferedReader fileReader;
        if (playerHasCryptoSaved(fileToSaveCryptogramTo)) { //If the player has a file saved, ask to overwrite
            fileReader = new BufferedReader(new FileReader(fileToSaveCryptogramTo));
            Scanner scan = new Scanner(System.in);
            String sUserAnswer;
            char answer;
            if (!fileReader.readLine().equals(getPhrase())) {
                System.out.println("You already have a cryptogram saved, do you want to overwrite? (y/n)");

            } else {
                System.out.println("Do you want to save the game? (y/n)");

            }
            sUserAnswer = scan.next();
            answer = sUserAnswer.charAt(0);
            return answer == 'n';
        }
        return false;
    }


    /**
     * @param player - Player object
     * @return - Loaded cryptogram object
     * @throws Exception - Problem loading the cryptogram
     */
    public Cryptogram loadCryptogram(Player player) throws Exception {
        try {
            //Set up variables to be used
            String pathsToCryptoString = Paths.get("").toAbsolutePath().toString() + "\\cryptograms";
            Path pathToCryptograms = Paths.get(pathsToCryptoString);

            //Check if the cryptogram folder exists
            if (!cryptogramFolderExists(pathToCryptograms)) {
                System.out.println("Folder does not exist, no saved cryptograms.");
                return null;
            }

            String pathToUsersCryptogram = getPathToUsersCryptogram(player, pathsToCryptoString, pathToCryptograms);
            if (pathToUsersCryptogram == null) return null;

            return loadCryptogram(pathToUsersCryptogram);
        } catch (Exception ex) {
            //Something went wrong :(
            throw new Exception("There was an error trying to load the cryptogram.");
        }
    }

    /**
     * @param pathToUsersCryptogram - Path to the users cryptogram file
     * @return - Players saved cryptogram
     * @throws IOException - Problem with file
     */
    private Cryptogram loadCryptogram(String pathToUsersCryptogram) throws IOException {
        File fileToReadCryptogramFrom;
        BufferedReader fileReader;
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
    }

    /**
     * @param player              - Player object
     * @param pathsToCryptoString - String representation of the path to the cryptogram folder
     * @param pathToCryptograms   - Path to the cryptogram folder
     * @return - Path to users cryptogram file
     */
    private String getPathToUsersCryptogram(Player player, String pathsToCryptoString, Path pathToCryptograms) {
        //Folder exists, check if it contains files
        String pathToUsersCryptogram = "";
        File cryptogramDirectory = new File(pathsToCryptoString);
        String[] fileNames = cryptogramDirectory.list();

        if (fileNames == null) return null;

        if (fileNames.length == 0) { //Error, the file is empty
            System.out.println("Folder is empty, no saved cryptograms.");
            return null;
        }

        //Folder contains files, check if user has a saved cryptogram
        int hasCryptoSaved = 0;
        for (String file : Objects.requireNonNull(cryptogramDirectory.list())) {
            if (file.contains(player.getUsername())) {
                pathToUsersCryptogram = pathToCryptograms + "\\" + file;
                hasCryptoSaved++;
            }
        }

        if (hasCryptoSaved == 0) { //Error, they have no saved cryptogram
            System.out.println("You do not have a cryptogram saved.");
            return null;
        }
        return pathToUsersCryptogram;
    }


    /**
     * Parsing file
     *
     * @param string string
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