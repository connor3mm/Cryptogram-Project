package cryptogram;

import javax.swing.*;
import java.util.Scanner;

public class Main {

    //Test
    public static void main(String args[]) {
        Game game = null;

        Scanner scan = new Scanner(System.in);
        int choice = -1;
        //gets the menu option entered while checking the entered value is both within parameters and a valid input.
        do {
            try {
                //displays the menu
                System.out.println(
                        "Please choose from the following options.\n"
                                + "Enter 0 for letter Cryptogram \n"
                                + "Enter 1 for Number Cryptogram\n"
                );
                choice = scan.nextInt();
                //checks the input value is a valid option
                if (choice < 0 || choice > 1) {
                    System.out.println("The value must be between 0-1.");
                    choice = -1;
                }
                //checks the input value is of a valid format
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "The value entered is not an option.");
            }

            if (choice == 0) {
                Player p = new Player("Player 1");
                game = new Game(p, 0);
                game.generateCryptogram();

            } else if (choice == 1) {
                Player p = new Player("Player 1");
                game = new Game(p, 1);
                game.generateCryptogram();
            }
        } while (choice == -1);


        choice =-1;
        do {
            try {
                //displays the menu
                System.out.println(
                        "\nPlease choose from the following options.\n"
                                + "Enter 1 to enter a letter \n"
                                + "Enter 2 to undo a letter \n"
                );
                choice = scan.nextInt();
                //checks the input value is a valid option
                if (choice < 1 || choice > 2) {
                    System.out.println("The value must be between 1-2.");
                    choice = -1;
                }
                //checks the input value is of a valid format
            } catch (NumberFormatException e) {
                System.out.println("The value entered is not an option.");
            }

            if (choice == 1) {
                game.enterLetter();
                choice = -1;

            } else if (choice == 2) {

                game.undoLetter();
                choice = -1;

            }
        } while (choice == -1);

    }
}