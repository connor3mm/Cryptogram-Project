package cryptogram;

class LetterCryptogram extends Cryptogram {

    //variables
    private String cryptogramAlphabet;

    //constructor
    public LetterCryptogram() {
        mapLetters();
    }

    //maps the game mapping numbers to each letter it represents in the phrase
    public void mapLetters() {
        String newPhrase = "";
        for (int i = 0; i < getPhrase().length(); i++) {
            char currentLetter = getPhrase().charAt(i);

            if (currentLetter == 32) {
                newPhrase = newPhrase + " ";
            } else {
                newPhrase = newPhrase + (char) (gameMapping[currentLetter - 97] + 97) + "    ";
            }
        }
        System.out.println(newPhrase);
    }


    public void getPlainLetter(char cryptoLetter) {

    }


}
