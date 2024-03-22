package uta.cse3310;

import java.util.Vector;

public class Player {
    // Attributes
    private String PlayNick;
    private int PlayerNum;
    private int Wins;
    private int Points;
    private int SavedPin;
    private int HighScore;
    private int Coord1[];
    private int Coord2[];

    // Constructor
    public Player(String PlayNick, int PlayerNum) {
        /*this.PlayNick = PlayNick;
        this.PlayerNum = PlayerNum;
        this.Wins = 0;
        this.Points = 0;
        this.SavedPin = 0;
        this.HighScore = 0;
        this.Coord1 = new int[0]; // Assuming default size
        this.Coord2 = new int[0];*/ 
    }
    
    public boolean checkUnique(Vector<Player> PlayerList) {
        return true;
    }

    // Method to save or update the SavedPin
    public void SavePin(int newPin) {
        /*this.SavedPin = newPin;*/
    }

    public void updateScore() {}
}
