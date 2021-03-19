package cryptogram;

import javax.swing.*;
import java.util.Scanner;

public class Main {

    //Test
    public static void main(String args[]) {
        Game game = null;

        Scanner scan = new Scanner(System.in);
        String name = "";
        int choice = -1;
        do {
            try {
                int detailsChoice;
                int loadChoice;
                System.out.println("Enter 0 if you have already created an account.");
                System.out.println("Enter 1 if you would like to create an account.");
                detailsChoice = scan.nextInt();
                if(detailsChoice < 0 || detailsChoice > 1){
                    System.out.println("The value must be between 0 and 1.");
                }
                if (detailsChoice == 0) {
                    System.out.println("Please enter your username");
                    name = scan.next();
                    Player p = new Player(name);
                    Players ps = new Players();
                    if (ps.findPlayer(p)) {
                        ps.loadPlayer();
                        System.out.println("Successfully loaded players details.");
                    } else {
                        System.out.println("Username does not exist.");
                        while(!ps.findPlayer(p)){
                            System.out.println("Please enter your username");
                            name = scan.next();
                            Player p2 = new Player(name);
                            Players ps2 = new Players();
                            if (ps2.findPlayer(p2)) {
                                ps2.loadPlayer();
                                System.out.println("Successfully loaded players details.");
                            }
                            System.out.println("Username does not exist.");
                        }
                    }
                    System.out.println("Enter 0 if you would like to load your cryptogram.");
                    System.out.println("Enter 1 if you would like to start a new cryptogram.");
                    loadChoice = scan.nextInt();
                    if(loadChoice == 0){

                    } else if (loadChoice == 1){
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
                        if(choice == 0){
                            game = new Game(p, 0);
                            game.generateCryptogram();
                        } else if(choice == 1){
                            game = new Game(p, 1);
                            game.generateCryptogram();
                        }
                    }
                    }
                if (detailsChoice == 1) {
                    System.out.println("Please enter your desired username.");
                    name = scan.next();
                    Player p = new Player(name);
                    Players ps = new Players();
                    if (ps.findPlayer(p)) {
                        System.out.println("A player with this username has already been created.");
                        System.exit(0);
                    }
                    ps.addPlayer(p);
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
                    if(choice == 0){
                        game = new Game(p, 0);
                        game.generateCryptogram();
                    } else if(choice == 1){
                        game = new Game(p, 1);
                        game.generateCryptogram();
                    }
                }
            } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(null, "The value entered is not an option.");
                }
        }
        while (choice == -1);


        choice = -1;
        do {
            try {
                //displays the menu
                System.out.println(
                        "\nPlease choose from the following options.\n"
                                + "Enter 1 to enter a letter \n"
                                + "Enter 2 to undo a letter \n"
                                + "Enter 3 to exit the game \n"
                );
                choice = scan.nextInt();
                //checks the input value is a valid option
                if (choice < 1 || choice > 3) {
                    System.out.println("The value must be between 1-3.");
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

            } else if (choice == 3) {
                Player p = new Player(name);
                p.savePlayersDetails();
                System.exit(0);
            }
        } while (choice == -1);

        }
}