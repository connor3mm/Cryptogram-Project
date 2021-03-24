package cryptogram;

import javax.management.MalformedObjectNameException;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.io.IOException;
import java.io.PrintWriter;


public class Players extends Player{

    private List<Player> allPlayers = new ArrayList<>();
    private File playersFile;


    public List<Player> getAllPlayers() {
        return this.allPlayers;
    }

    /**
     * Constructor
     * @param p
     */
    public void addPlayer(Player p) {
        allPlayers.add(p);
    }


    /**
     *
     */
    public void savePlayers() {
        try {
            PrintWriter out = new PrintWriter("playersFile.txt");
            out.println(allPlayers);
        } catch (IOException e) {
            System.out.println("An error has occurred when trying to save players details to a file.");
            e.printStackTrace();
        }
    }


    /**
     * Finds a player within the players list array
     * @param p
     * @return true if player exists, false if not
     */
    public boolean findPlayer(Player p) {
        for (Player allPlayer : allPlayers) {
            if (allPlayer.getUsername().equals(p.getUsername())) {
                return true;
            }
        }
        return false;
    }


    public void loadPlayer() {

    }


    public void getAllPlayersAccuracies() {

    }

    public void getAllPlayersCryptogramsPlayed() {

    }

    public void getAllPlayersCompletedCryptos() {
    }

    public void loadAllPlayers(){
        String pathsToDetailsString = Paths.get("").toAbsolutePath().toString() + "\\PlayerDetails";
        Path pathToDetails = Paths.get(pathsToDetailsString);
        if (!detailsFolderExists(pathToDetails)) {
            System.out.println("Players folder does not exist. Cannot load any players.");
            return;
        }

        List<String> usernames = getUsernames();
        if(usernames.size() == 0) return;

        int playersSuccessfullyLoaded = 0;
        for (String username : usernames) {
            try {
                allPlayers.add(loadPlayersDetails(username));
                playersSuccessfullyLoaded++;
            } catch (Exception ex) {
                System.out.printf("An error occurred when trying to load %s (Possibly corrupt)\n", username);
            }
        }
        System.out.printf("%d player(s) loaded successfully.%n%n", playersSuccessfullyLoaded);
    }

    private List<String> getUsernames() {
        String pathsToDetailsString = Paths.get("").toAbsolutePath().toString() + "\\PlayerDetails";
        File playersDirectory = new File(pathsToDetailsString);
        List<String> usernames = new ArrayList<>();

        Collections.addAll(usernames, Objects.requireNonNull(playersDirectory.list()));

        return usernames;
    }

    public List<Player> getTopTenScores() {
        this.allPlayers.sort(new SortByScore());

        if(allPlayers.size() < 10)
            return allPlayers.subList(0, this.allPlayers.size());

        return this.allPlayers.subList(this.allPlayers.size() - 10, this.allPlayers.size());
    }

    public static class SortByScore implements Comparator<Player> {
        public int compare(Player one, Player two) {
            Integer scoreOne = one.getCryptogramsCompleted();
            Integer scoreTwo = two.getCryptogramsCompleted();

            return scoreOne.compareTo(scoreTwo);
        }
    }
}
