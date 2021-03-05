package cryptogram;

public class Player {

    private String username;
    private int accuracy;
    private int totalGuesses;
    private int cryptogramsPlayed;
    private int cryptogramsCompleted;

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


    public void updateAccuracy(){

    }


    public void incrementCryptogramCompleted(){

    }


    public void incrementCryptogramPlayed(){

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
