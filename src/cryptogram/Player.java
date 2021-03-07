package cryptogram;

public class Player {

    private String username;
    private int accuracy;
    private int totalGuesses;
    private int cryptogramsPlayed;
    private int cryptogramsCompleted;

    public void setAccuracy(int accuracy) {
        this.accuracy = accuracy;
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

    public int getTotalGuesses() {
        return totalGuesses;
    }

    public int getCryptogramsPlayed() {
        return cryptogramsPlayed;
    }

    public int getCryptogramsCompleted() {
        return cryptogramsCompleted;
    }

    public Player(String name){
        username = name;
        accuracy = 0;
        totalGuesses = 0;
        cryptogramsPlayed = 0;
        cryptogramsCompleted = 0;
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }


    public void updateAccuracy(boolean check){
        totalGuesses++;
        if(check){
            accuracy++;
        }
    }


    public void incrementCryptogramCompleted(){
        cryptogramsCompleted = ++cryptogramsCompleted;
    }


    public void incrementCryptogramPlayed(){
        cryptogramsPlayed = ++cryptogramsPlayed;
    }

    public int getAccuracy() {
        return accuracy;
    }

    public int getNumCryptogramsPlayed() {
        return cryptogramsPlayed;
    }


    public int getNumCryptogramsCompleted() {
        return cryptogramsCompleted;
    }









}
