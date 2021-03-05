package cryptogram;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Random;

public class Cryptogram {

    private String phrase;
    private String cryptogramAlphabet;
    private boolean numberMapping;
    public ArrayList<Integer> gameMapping = new ArrayList<>();
    private double[] letterFrequency;

    public void getFrequencies() {

    }

    public Cryptogram() {
    }

    public Cryptogram(int cryptoType) {
        System.out.println("New create is being created...");
        setMapping(cryptoType);
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

    public void setRandomCryptoPhrase() {
        ArrayList<String> cryptoPhrases = getCryptoPhrases();
        int numberOfCryptoPhrases = cryptoPhrases.size();

        phrase = cryptoPhrases.get(new Random().nextInt(numberOfCryptoPhrases));
    }

    private void createCryptoMapping() {
        Random random = new Random();
        ArrayList<Integer> alphabetIndex = new ArrayList<>();
        for(int i=0;i<26;i++)
            alphabetIndex.add(i);

        int randomNumber;
        int i = 0;
        while(i < 25) {
            randomNumber = random.nextInt(26-i);

            while(alphabetIndex.get(randomNumber)==i) {
                randomNumber = random.nextInt(26 - i);
            }

            gameMapping.add(i, alphabetIndex.remove(randomNumber));
            i++;
        }
        if(alphabetIndex.get(0)==25)
            createCryptoMapping();
        else {
            gameMapping.add(0,alphabetIndex.remove(0));
        }
    }
}

class LetterCryptogram extends Cryptogram {

    private String cryptogramAlphabet;

    public LetterCryptogram(String phrase) {
        String newPhrase = "";
        char[] ch = new char[phrase.length()];
        for (int i = 0; i < phrase.length(); i++) {
            ch[i] = phrase.charAt(i);
        }

        for (char c : ch) {
            if(c == 32) {
                newPhrase = newPhrase + " ";
            }
            else
            {
                newPhrase = newPhrase + (char)(gameMapping.get(c-97)+97) + "    ";
            }
            System.out.println(newPhrase);
        }
    }

    public void Cryptogram() {

    }

    public void getPlainLetter(char cryptoLetter) {

    }


}

class numberCryptogram extends Cryptogram {

    private String cryptogramAlphabet;

    public numberCryptogram(String phrase) {
        String newPhrase = "";
        char[] ch = new char[phrase.length()];
        for (int i = 0; i < phrase.length(); i++) {
            ch[i] = phrase.charAt(i);
        }

        for (char c : ch) {
            if(c == 32) {
                newPhrase = newPhrase + " ";
            }
        }
    }

    public void Cryptogram() {

    }

    public void getPlainLetter(int cryptoLetter) {

    }


}