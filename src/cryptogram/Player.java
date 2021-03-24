package cryptogram;

import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.*;
import java.util.Objects;


public class Player {

    /**
     * Variables
     */
    private String username;
    private double accuracy;
    private int correctGuesses;
    private int totalGuesses;
    private int cryptogramsPlayed;
    private int cryptogramsCompleted;


    /**
     * getters setters
     *
     * @return
     */
    public int getCorrectGuesses() {
        return correctGuesses;
    }

    public void setAccuracy(double accuracy) {
        this.accuracy = accuracy;
    }

    public void setCorrectGuesses(int correctGuesses) {
        this.correctGuesses = correctGuesses;
    }

    public void setTotalGuesses(int totalGuesses) {
        this.totalGuesses = totalGuesses;
    }

    public void setCryptogramsPlayed(int cryptogramsPlayed) {
        this.cryptogramsPlayed = cryptogramsPlayed;
    }

    public void setCryptogramsCompleted(int cryptogramsCompleted) {
        this.cryptogramsCompleted = cryptogramsCompleted;
    }

    public double getAccuracy() {
        return accuracy;
    }

    public int getNumCryptogramsPlayed() {
        return cryptogramsPlayed;
    }

    public int getTotalGuesses() {
        return totalGuesses;
    }

    public int getCryptogramsPlayed() {
        return cryptogramsPlayed;
    }

    public int getCryptogramsCompleted() {
        return cryptogramsCompleted;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }


    /**
     * Player Constructor
     */
    public Player() {
    }


    /**
     * Player Constructor
     *
     * @param name
     */
    public Player(String name) {
        username = name;
        accuracy = 0;
        totalGuesses = 0;
        cryptogramsPlayed = 0;
        cryptogramsCompleted = 0;
    }

    /**
     * Constructor for loaded player
     */
    public Player(String username, double accuracy, int correctGuesses, int totalGuesses, int cryptogramsPlayed, int cryptogramsCompleted) {
        this.username = username;
        this.accuracy = accuracy;
        this.correctGuesses = correctGuesses;
        this.totalGuesses = totalGuesses;
        this.cryptogramsPlayed = cryptogramsPlayed;
        this.cryptogramsCompleted = cryptogramsCompleted;
    }


    //methods
    /**
     * Increments cryptos complete
     */
    public void incrementCryptogramCompleted() {
        cryptogramsCompleted = cryptogramsCompleted + 1;
    }


    /**
     * Increments cryptosplayed
     */
    public void incrementCryptogramPlayed() {
        cryptogramsPlayed = cryptogramsPlayed + 1;
    }


    /**
     * Checks if folder to hold players details already exists, if so returns true.
     */
    public boolean detailsFolderExists(Path pathToDetails) {
        return Files.exists(pathToDetails);
    }


    /**
     * Updates player stats
     * @param check
     */
    public void updateAccuracy(boolean check) {
        if (check) {
            correctGuesses++;
        }
        totalGuesses++;
        accuracy = Math.round(((double) correctGuesses / (double) totalGuesses) * 100);
    }


    /**
     * Creates folder to hold players details.
     */
    private void createPlayersDetailsFolder(Path pathToDetails) throws Exception {
        System.out.println("Folder to store player details does not exist. Creating one now...");
        try {
            Files.createDirectory(pathToDetails);
            System.out.println("Folder has been successfully created.");
        } catch (Exception e) {
            throw new Exception("Error when trying to create folder to store player details.");
        }
    }


    /**
     *
     * @param p - Player object
     * @return - Result of saving the details (true/false)
     */
    public boolean savePlayersDetails(Player p) {
        try {
            //File variables to be used when saving.
            File fileToSaveDetailsTo;
            String pathsToDetailsString = Paths.get("").toAbsolutePath().toString() + "\\PlayerDetails";
            Path pathToDetails = Paths.get(pathsToDetailsString);

            //Creates the folder to save player details files if one doesn't already exist.
            if (!detailsFolderExists(pathToDetails)) {
                try {
                    createPlayersDetailsFolder(pathToDetails);
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                    return false;
                }
            }

            savePlayerDetails(p, pathsToDetailsString);

            //Message to tell the user their details have been saved successfully.
            System.out.println("\nPlayers details have been successfully saved to a file.");
            return true;
        } catch (FileNotFoundException e) {
            //Error message to say that an error has occurred while printing to the file.
            System.out.println("An error has occurred when trying to save players details to a file.");
            e.printStackTrace();
        }
        return false;
    }

    /**
     *
     * @param p - Player object
     * @param pathsToDetailsString - Path to the details folder
     * @throws FileNotFoundException - The file doesn't exist
     */
    private void savePlayerDetails(Player p, String pathsToDetailsString) throws FileNotFoundException {
        File fileToSaveDetailsTo;
        //Creates a new file to save details to, if one doesn't already exist.
        fileToSaveDetailsTo = new File(pathsToDetailsString + "\\" + p.getUsername() + ".txt");

        //Prints players details to the text file.
        PrintWriter out = new PrintWriter(fileToSaveDetailsTo);
        out.println(username);
        out.println((int) accuracy);
        out.println(correctGuesses);
        out.println(totalGuesses);
        out.println(cryptogramsPlayed);
        out.print(cryptogramsCompleted);

        //Information has been written at this point, writer can be closed.
        out.close();
    }


    /**
     *
     * @param username - players username
     * @return - player object of player with a given username
     * @throws Exception - Problem reading from the file
     */
    public Player loadPlayersDetails(String username) throws Exception {
        try {
            //File variables to be used when saving.
            String pathsToDetailsString = Paths.get("").toAbsolutePath().toString() + "\\PlayerDetails";
            Path pathToDetails = Paths.get(pathsToDetailsString);

            if (!detailsFolderExists(pathToDetails)) return null;

            String pathToUsersDetails = getPathToUsersDetails(username, pathsToDetailsString, pathToDetails);
            if (pathToUsersDetails == null) return null;

            return loadPlayer(pathToUsersDetails);
        } catch (Exception e) {
            //Error message to say that an error has occurred while printing to the file.
            throw new Exception("An error has occurred when trying to load players details from a file.");
        }
    }


    /**
     *
     * @param pathToUsersDetails - path to the file the details are stored in
     * @return player object of given player
     * @throws Exception - If there is a problem reading the file an error is returned
     */
    private Player loadPlayer(String pathToUsersDetails) throws Exception {
        File fileToReadDetailsFrom;
        BufferedReader fileReader;
        //User has a saved details file, read it.
        fileToReadDetailsFrom = new File(pathToUsersDetails);
        fileReader = new BufferedReader(new FileReader(fileToReadDetailsFrom));
        String playerUsername = fileReader.readLine();
        double accuracy = Double.parseDouble(fileReader.readLine());
        int correctGuesses = Integer.parseInt(fileReader.readLine());
        int totalGuesses = Integer.parseInt(fileReader.readLine());
        int cryptogramsPlayed = Integer.parseInt(fileReader.readLine());
        int cryptogramsCompleted = Integer.parseInt(fileReader.readLine());

        //Information has been written at this point, reader can be closed.
        fileReader.close();

        //Create player object
        return new Player(
                playerUsername, accuracy, correctGuesses, totalGuesses, cryptogramsPlayed, cryptogramsCompleted
        );
    }


    /**
     *
     * @param username - players username
     * @param pathsToDetailsString - string path to the details folder
     * @param pathToDetails - path object with the details to the folder
     * @return - Path to the orders detail file
     */
    private String getPathToUsersDetails(String username, String pathsToDetailsString, Path pathToDetails) {
        //Folder exists, check if it contains files
        String pathToUsersDetails = "";
        File cryptogramDirectory = new File(pathsToDetailsString);

        String[] fileNames = cryptogramDirectory.list();

        if(fileNames == null) return null;
        if (fileNames.length == 0) return null;

        //The folder contains files, check if the user has a saved details file
        int hasDetailsSaved = 0;
        for (String file : Objects.requireNonNull(cryptogramDirectory.list())) {
            if (file.contains(username)) {
                pathToUsersDetails = pathToDetails + "\\" + file;
                hasDetailsSaved++;
            }
        }

        if (hasDetailsSaved == 0) return null;

        return pathToUsersDetails;
    }
}
