package cryptogram;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Random;

public class Cryptogram {

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

    private String phrase;
    private String cryptogramAlphabet;
    private boolean numberMapping;
    public int[] gameMapping = new int[26];;
    private double[] letterFrequency;

    public void getFrequencies() {

    }

    public Cryptogram() {
    }

    public Cryptogram(int cryptoType) {
        System.out.println("New create is being created...");
        setRandomCryptoPhrase();
        createCryptoMapping();
        System.out.println("Successfully created a new game...");
        //for (int i = 0; i < gameMapping.size(); i++) {
         //   System.out.println("index - " + i + " " + (char)gameMapping.get(i));
        //}
    }

    private void setMapping(int cryptoType) {
        if (cryptoType == 1) {
            this.numberMapping = true;
        } else {
            this.numberMapping = false;
        }
    }

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

    public void setRandomCryptoPhrase() {
        ArrayList<String> cryptoPhrases = getCryptoPhrases();
        int numberOfCryptoPhrases = cryptoPhrases.size();

        phrase = cryptoPhrases.get(new Random().nextInt(numberOfCryptoPhrases));
    }

    public void createCryptoMapping() {
        Random random = new Random();
        ArrayList<Integer> alphabetIndex = new ArrayList<>();
        for(int i=0;i<26;i++)
            alphabetIndex.add(i);

        int randomNumber;
        int i = 0;
        while(i < 25) {
            randomNumber = random.nextInt(26-i);

            while(alphabetIndex.get(randomNumber)==i) {
                randomNumber = random.nextInt(26 - i);            }

            gameMapping[i] = alphabetIndex.remove(randomNumber);
            i++;
        }
        if(alphabetIndex.get(0)==25)
            createCryptoMapping();
        else {
            gameMapping[25] = alphabetIndex.remove(0);
        }
    }
}

