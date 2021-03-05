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

    public void getFrequencies() {

    }

    public void Cryptogram(int cryptoType) {
        setMapping(cryptoType);
    }

    private void setMapping(int cryptoType) {
        if(cryptoType == 1){
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
            while(phrase != null) {
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