package uta.cse3310;

import java.util.Vector;

public class Player {
    // Attributes
    private String PlayNick;
    public int PlayerNum;
    private int Wins;
    private int Points;
    private int SavedPin;
    private int HighScore;
    private int Coord1[];
    private int Coord2[];

    // Constructor
    public Player(String PlayNick, int PlayerNum) {
        this.PlayNick = PlayNick;
        this.PlayerNum = PlayerNum;
        this.Wins = 0;
        this.Points = 0;
        this.SavedPin = 0;
        this.HighScore = 0;
        //this.Coord1 = new int[MAX_GRID_SIZE-1]; // Assuming default size
        //this.Coord2 = new int[MAX_GRID_SIZE-1]; 
    }
    
    public boolean checkUnique(Vector<Player> PlayerList) {
        for(int i = 0; i < PlayerList.size(); i++) {            //  While going through the entire vector
            if(this.PlayNick == PlayerList.get(i).PlayNick) {   //  If there is a copy of a name
            }
        }
        return true;                                            //  Otherwise it is true                
    }

    // Method to save or update the SavedPin
    public boolean savePin(int newPin) {
        String pinStr = Integer.toString(newPin);
        if (pinStr.length() == 4 && pinStr.matches("\\d{4}")) {
            this.SavedPin = newPin; // Set the requested pin to the saved pin if valid
            return true;
        } else {
            System.out.println("Invalid PIN. PIN must be a four-digit number.");
            return false;
        }
    }

    public int updateScore() {
        // Question: Update Highschore or score in game?
        return this.Points;
    }
}
