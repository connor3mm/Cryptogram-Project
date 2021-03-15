package cryptogram;

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

    public int getNumCryptogramsCompleted() {
        return cryptogramsCompleted;
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
     * Updates player stats
     * @param check
     */
    public void updateAccuracy(boolean check) {
        if (check) {
            correctGuesses++;
        }
        totalGuesses++;
        accuracy = Math.round(((double)correctGuesses/(double)totalGuesses) * 100);

    }

    /**
     * Increments cryptos complete
     */
    public void incrementCryptogramCompleted() {
        cryptogramsCompleted = cryptogramsCompleted+1;
    }


    /**
     * Increments cryptosplayed
     */
    public void incrementCryptogramPlayed() {
        cryptogramsPlayed = cryptogramsPlayed+1;
    }


}
