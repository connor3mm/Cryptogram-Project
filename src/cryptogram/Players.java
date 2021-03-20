package cryptogram;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.io.IOException;
import java.io.PrintWriter;


public class Players {

    private List<Player> allPlayers = new ArrayList<>();
    private File playersFile;


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
        for (int i = 0; i < allPlayers.size(); i++) {
            if (allPlayers.contains(p)) {
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

}
