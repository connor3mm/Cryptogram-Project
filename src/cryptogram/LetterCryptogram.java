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


    public void getPlainLetter(char cryptoLetter, char answerLetter) {
        int cryptoLetterAscii = cryptoLetter - 97;
        int answerLetterAscii = answerLetter - 97;
        boolean isComplete = false;

        if(playerMapping[cryptoLetterAscii] !=  -1) { //Only triggered if the mapping already exists
            System.out.println("This letter has been mapped. Would you like to overwrite? (y/n)");

            Scanner scan = new Scanner(System.in);
            String userString = scan.next();
            char answer = userString.charAt(0);
            scan.close();

            if(answer == 'y') {
                for(int i=0; i < gameMapping.length; i++) {
                    if(gameMapping[i] == cryptoLetterAscii) {
                        playerMapping[answerLetterAscii] = cryptoLetterAscii;
                    }
                }

                for (int i = 0; i < playerMapping.length; i++) {
                    System.out.println("index - " + i + " " + playerMapping[i]);
                }

                isComplete = checkIfGameCompleted();
            } else {
                return;
            }
        } else if(playerMapping[answerLetterAscii] !=  -1) {
            System.out.println("This letter has been mapped. Would you like to overwrite? (y/n)");

            Scanner scan = new Scanner(System.in);
            String userString = scan.next();
            char answer = userString.charAt(0);
            scan.close();

            if(answer == 'y') {
                for(int i=0; i < gameMapping.length; i++) {
                    if(gameMapping[i] == cryptoLetterAscii) {
                        playerMapping[answerLetterAscii] = cryptoLetterAscii;
                    }
                }

                for (int i = 0; i < playerMapping.length; i++) {
                    System.out.println("index - " + i + " " + playerMapping[i]);
                }

                isComplete = checkIfGameCompleted();
            } else {
                return;
            }
        }

        for(int i = 0; i < gameMapping.length; i++) {
            if(gameMapping[i] == cryptoLetterAscii) {
                playerMapping[i] = answerLetterAscii;
            }
        }

        for (int i = 0; i < playerMapping.length; i++) {
            System.out.println("index - " + i + " " + playerMapping[i]);
        }

        isComplete = checkIfGameCompleted();
    }

    public boolean checkIfMapped(char letter) {
        int letterAsciiValue = letter - 97;

        for(int i = 0; i < playerMapping.length; i++) {
            if(playerMapping[i]==letterAsciiValue) {
                return true;
            }
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
}
