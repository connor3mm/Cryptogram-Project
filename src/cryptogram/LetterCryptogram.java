package cryptogram;

import java.util.Scanner;

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


    public void getPlainLetter(char letter) {
        Scanner scan = new Scanner(System.in);

        if(checkIfGuessMapped(letter)) {
            System.out.println("The letter is already mapped, do you want to overwrite? (y/n)");
            scan = new Scanner(System.in);

            String userInput = scan.next();
            char answer = userInput.charAt(0);

            if (answer == 'n') {
                return;
            }
        }
        System.out.println("Which letter would you like to map '" + letter + "' to?");
        String userInput2 = scan.next();
        char letterToReplace = userInput2.charAt(0);
        if(guessIsValid(letterToReplace)) {
            enterLetter(letter, letterToReplace);
        } else
        {
            System.out.println("Invalid guess. This value is already mapped.");
        }
    }

    public boolean enterLetter(char letterInput, char guessInput) {
        int letterInputAscii = letterInput - 97;
        int guessInputAscii = guessInput - 97;

        for(int i = 0; i < gameMapping.length; i++) {
            if(gameMapping[i] == letterInputAscii) {
                playerMapping[i] = guessInputAscii;
                return i == guessInputAscii;
            }
        }
        return false;
    }

    public boolean checkIfGuessMapped(char letter) {
        int letterAsciiValue = letter - 97;

        for(int i = 0; i < playerMapping.length; i++) {
            if(playerMapping[i]==letterAsciiValue) {
                return true;
            }
        }
        return false;
    }

    public boolean checkValueIsAlreadyMapped(char input) {
        int letterToCheck = input - 97;

        for(int i = 0; i < gameMapping.length; i++) {
            if(gameMapping[i] == letterToCheck) {
                if(playerMapping[i] == -1)
                    return false;
                else
                    return true;
            }
        }
        return true;
    }

    private boolean guessIsValid(char guess) {
        if(guess >= 97 && guess <= 122) {
            if(checkIfGuessMapped(guess))
                return false;
            else
                return true;
        }
        return false;
    }
    
    public boolean checkIfGameCompleted() {
        for (int i = 0; i < playerMapping.length; i++) {
            if(playerMapping[i] == -1) {
                return false;
            }
        }
        return true;
    }

    public void lol(){};
}
