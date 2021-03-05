package cryptogram;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Random;

public class Game {

    //variables could be wrong
    private Player currentPlayer;
    private Cryptogram currentGame;
    private int cryptType;


    public void Game(Player p, int cryptType){
        this.currentPlayer = p;
        this.cryptType = cryptType;
    }

    public void Game(Player p){
        this.currentPlayer = p;

        Random random = new Random();
        int rand = random.nextInt(2);

        this.cryptType = rand;
    }


    public void getHint(){

    }

    public void loadPlayer(){

    }

    public void playGame(){

    }

    public void generateCryptogram(){
        currentGame = new Cryptogram();
        currentGame.Cryptogram(cryptType);
    }

    public void enterLetter(){


    }

    public void undoLetter(){

    }


    public void viewFrequencies(){

    }
    public void saveGame(){

    }

    public void loadGame(){

    }

    public void showSolution(){

    }


}
