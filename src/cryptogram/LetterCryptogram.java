package cryptogram;

public class LetterCryptogram extends Cryptogram {

    private String cryptogramAlphabet;

    public LetterCryptogram() {
        System.out.println("New create is being created...");
        setRandomCryptoPhrase();
        createCryptoMapping();
        mapLetters();
        System.out.println("Successfully created a new game...");
    }

    public void mapLetters() {
        String newPhrase = "";
        for (int i = 0; i < getPhrase().length(); i++) {
            char currentLetter = getPhrase().charAt(i);

            if (currentLetter == 32) {
                newPhrase = newPhrase + " ";
            } else {
                newPhrase = newPhrase + (char)(gameMapping[currentLetter-97]+97) + "    ";
            }
        }
        System.out.println(getPhrase());
        System.out.println(newPhrase);
    }

    public void Cryptogram() {

    }

    public void getPlainLetter(char cryptoLetter) {

    }


}
