package cryptogram;

class NumberCryptogram extends Cryptogram {
    //variables
    private String cryptogramAlphabet;

    //Constructor
    public NumberCryptogram() {
        mapNumbers();
    }

    //maps the game mapping numbers to each letter it represents in the phrase
    public void mapNumbers() {
        String newPhrase = "";
        for (int i = 0; i < getPhrase().length(); i++) {
            char currentLetter = getPhrase().charAt(i);

            if (currentLetter == 32) {
                newPhrase = newPhrase + " ";
            } else {
                if (gameMapping[currentLetter - 97] > 8) {
                    newPhrase = newPhrase + (gameMapping[currentLetter - 97]) + "   ";
                } else {
                    newPhrase = newPhrase + " " + (gameMapping[currentLetter - 97]) + "   ";
                }
            }
        }
        System.out.println(newPhrase);
    }


    public void getPlainLetter(int cryptoLetter) {




    }
}