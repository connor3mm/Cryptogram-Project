package cryptogram;

public class NumberCryptogram extends Cryptogram {

    private String cryptogramAlphabet;

    public NumberCryptogram() {
        System.out.println("New create is being created...");
        setRandomCryptoPhrase();
        createCryptoMapping();
        mapNumbers();
        System.out.println("Successfully created a new game...");
        for (int i = 0; i < gameMapping.length; i++) {
            System.out.println("index - " + i + " " + gameMapping[i]);
        }
    }

    public void mapNumbers() {
        String newPhrase = "";
        for (int i = 0; i < getPhrase().length(); i++) {
            char currentLetter = getPhrase().charAt(i);

            if (currentLetter == 32) {
                newPhrase = newPhrase + " ";
            } else {
                if(gameMapping[currentLetter-97]>8) {
                    newPhrase = newPhrase + (gameMapping[currentLetter - 97]) + "   ";
                }
                else {
                    newPhrase = newPhrase + " " + (gameMapping[currentLetter - 97]) + "   ";
                }
            }
        }
        System.out.println(getPhrase());
        System.out.println(newPhrase);
    }

    public void Cryptogram() {

    }

    public void getPlainLetter(int cryptoLetter) {

    }


}
