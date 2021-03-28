package cryptogram;
import javax.swing.*;
import java.util.List;
import java.util.Scanner;

public class Main {

    //Test
    public static void main(String[] args) {
        Game game = null;

        Players ps = new Players();
        try {
            ps.loadAllPlayers();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        Scanner scan = new Scanner(System.in);
        String name;
        int choice = -1;
        do {
            try {

                int detailsChoice;
                int loadChoice;

                System.out.println("Enter 0 if you have already created an account.");
                System.out.println("Enter 1 if you would like to create an account.");
                System.out.println("Enter 2 if you would like to see the top 10 scores.");
                detailsChoice = scan.nextInt();

                if (detailsChoice < 0 || detailsChoice > 2) {
                    System.out.println("The value must be between 0 and 2.");
                }


                if (detailsChoice == 0) {
                    System.out.println("\nPlease enter your username");

                    name = scan.next();
                    Player loadedPlayer = new Player();
                    try {
                        loadedPlayer = loadedPlayer.loadPlayersDetails(name);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }


                    if (loadedPlayer == null) {
                        System.out.println("\nThis username does not exist. Create a new account.\n");
                        continue;
                    }

                    System.out.println("Enter 0 if you would like to load your cryptogram.");
                    System.out.println("Enter 1 if you would like to start a new cryptogram.");
                    loadChoice = scan.nextInt();

                    if (loadChoice == 0) {
                        game = new Game(loadedPlayer, 0);
                        try {
                            game.loadGame();
                        } catch (Exception ex) {
                            System.out.println(ex.getMessage());
                            continue;
                        }
                        if (game.currentGame == null) continue;
                        break; //Go to the game options
                    } else if (loadChoice == 1) {
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

                        if (choice == 0) {
                            game = new Game(loadedPlayer, 0);
                            game.generateCryptogram();

                        } else if (choice == 1) {
                            game = new Game(loadedPlayer, 1);
                            game.generateCryptogram();
                        }
                    }
                }

                if (detailsChoice == 1) {
                    System.out.println("\nPlease enter your desired username.\n");
                    name = scan.next();
                    Player p = new Player(name);
                    if (ps.findPlayer(p)) {
                        System.out.println("A player with this username has already been created.\n");
                        continue;
                    }
                    ps.addPlayer(p);

                    //displays the menu
                    System.out.println(
                            "\nPlease choose from the following options.\n"
                                    + "Enter 0 for letter Cryptogram \n"
                                    + "Enter 1 for Number Cryptogram\n"
                    );

                    choice = scan.nextInt();
                    //checks the input value is a valid option
                    if (choice < 0 || choice > 1) {
                        System.out.println("The value must be between 0-1.");
                        choice = -1;
                    }
                    if (choice == 0) {
                        game = new Game(p, 0);
                        game.generateCryptogram();
                    } else if (choice == 1) {
                        game = new Game(p, 1);
                        game.generateCryptogram();
                    }
                }

                if (detailsChoice == 2) {
                    if (ps.getAllPlayers().size() == 0) {
                        System.out.println("There are no loaded players. Cannot display top ten\n");
                    } else {
                        System.out.printf("Total players: %d\n", ps.getAllPlayers().size());
                        List<Player> topTenScores = ps.getTopTenScores();
                        int position = 1;

                        for (int i = topTenScores.size() - 1; i >= 0; --i) {
                            System.out.printf("Position: %d, %s, score: %d\n", position, topTenScores.get(i).getUsername(), topTenScores.get(i).getCryptogramsCompleted());
                            position++;
                        }
                        System.out.println("\n");
                    }
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "The value entered is not an option.");
            } catch (Exception e) {
                e.printStackTrace();
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
                                + "Enter 3 to show phrase frequencies \n"
                                + "Enter 4 to get a hint \n"
                                + "Enter 5 to show solution \n"
                                + "Enter 6 to exit the game \n"
                );
                choice = scan.nextInt();
                //checks the input value is a valid option
                if (choice < 1 || choice > 6) {
                    System.out.println("The value must be between 1-4.");
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

                game.showFrequencies();
                choice = -1;

            } else if (choice == 4) {

                game.getHint();
                choice = -1;

            } else if (choice == 5) {
                int yes;
                System.out.println("Are you sure you want to show solution ? The Cryptogram with finish as unsuccessful.\nEnter 0 to show solution \nEnter 1 To keep trying");
                yes = scan.nextInt();
                if (yes == 0) {
                    game.showSolution();
                    choice = 0;

                } else {
                    System.out.println(game.currentGame.newPhrase);
                    game.currentGame.showMappedLetters();
                    choice = -1;
                }

            } else if (choice == 6) {
                game.savePlayer();
                game.saveGame();
                System.exit(0);
            }
        } while (choice == -1);
    }
}