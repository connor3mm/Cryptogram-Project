package cryptogram;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Random;

public class Cryptogram {

    private String phrase;
    private String cryptogramAlphabet;
    private boolean numberMapping;
    private int[] gameMapping;
    private double[] letterFrequency;

    public void getFrequencies() {

    }

    public void Cryptogram(int cryptoType) {
        setMapping(cryptoType);
    }

    private void setMapping(int cryptoType) {
        if (cryptoType == 1) {
            this.numberMapping = true;
        } else {
            this.numberMapping = false;
        }
    }

    private ArrayList<String> getCryptoPhrases() {
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

    public void getRandomCryptoPhrase() {
        ArrayList<String> cryptoPhrases = getCryptoPhrases();
        int numberOfCryptoPhrases = cryptoPhrases.size();

        phrase = cryptoPhrases.get(new Random().nextInt(numberOfCryptoPhrases));
    }

    private void generateNumberMapping() {
        Random random = new Random();
        ArrayList<Integer> alphabetIndex = new ArrayList<>();
        for(int i=0;i<26;i++)
            alphabetIndex.add(i);

        int randomNumber;
        int i = 0;
        while(i < 25) {
            randomNumber = random.nextInt(25-i);

            while(alphabetIndex.get(randomNumber)==i) {
                randomNumber = random.nextInt(25 - i);
            }

            gameMapping[i] = alphabetIndex.remove(randomNumber);
            i++;
        }
        if(alphabetIndex.get(25)==25)
            generateNumberMapping();
        else {
            gameMapping[25] = alphabetIndex.remove(25);
        }
    }
}

class LetterCryptogram extends Cryptogram {

    private String cryptogramAlphabet;

    public void Cryptogram(String file) {

    }

    public void Cryptogram() {

    }

    public void getPlainLetter(char cryptoLetter) {

    }


}

class numberCryptogram extends Cryptogram {

    private String cryptogramAlphabet;

    public void Cryptogram(String file) {

    }

    public void Cryptogram() {

    }

    public void getPlainLetter(int cryptoLetter) {

    }


}