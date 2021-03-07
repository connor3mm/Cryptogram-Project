package cryptogram;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Random;

public class Cryptogram {

    //Variables
    private String phrase;
    private String cryptogramAlphabet;
    private boolean numberMapping;
    public int[] gameMapping = new int[26];
    private double[] letterFrequency;
    public int[] playerMapping = new int [26];

    /**
     * Getters and setters
     * @return
     */
    public int[] getGameMapping() {
        return gameMapping;
    }

    public int[] getPlayerMapping() {
        return playerMapping;
    }


    public String getPhrase() {
        return phrase;
    }

    public void setPhrase(String phrase) {
        this.phrase = phrase;
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

    public double[] getLetterFrequency() {
        return letterFrequency;
    }

    public void setLetterFrequency(double[] letterFrequency) {
        this.letterFrequency = letterFrequency;
    }

    /**
     * Cryptogram Constructor
     */
    public Cryptogram() {
        System.out.println("New create is being created...");
        setRandomCryptoPhrase();
        createCryptoMapping();
        System.out.println("Successfully created a new game...");
        System.out.println(getPhrase());

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
        String phrase;

        try {
            cryptoFileName = new File("solvedCryptos.txt");
            fileReader = new BufferedReader(new FileReader(cryptoFileName));
            phrase = fileReader.readLine();

            //While a phrase exists, process it
            while (phrase != null) {
                cryptoPhrases.add(phrase);
                phrase = fileReader.readLine();
            }
        } catch (Exception ex) {
            System.out.println("There was an error while trying to read from the file.");
            System.exit(-1);
        }

        return cryptoPhrases;
    }


    //Sets 1 out of 15 crypto phrases
    public void setRandomCryptoPhrase() {
        ArrayList<String> cryptoPhrases = getCryptoPhrases();
        int numberOfCryptoPhrases = cryptoPhrases.size();

        phrase = cryptoPhrases.get(new Random().nextInt(numberOfCryptoPhrases));
    }


    //Maps a number of the alphabet(in order) to random number
    public void createCryptoMapping() {
        Random random = new Random();

        ArrayList<Integer> alphabetIndex = new ArrayList<>();
        for (int i = 0; i < 26; i++)
            alphabetIndex.add(i);

        for (int i = 0; i < 26; i++) {
            playerMapping[i] = -1;
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

    public void getPlainLetter(char letter) {}
    public void undoGivenLetter(char letter) {}
}


