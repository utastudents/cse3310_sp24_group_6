//Kaitlin Martin
//Group 6

package uta.cse3310;

public class Game {

    PlayerType Players;             //referenced from <<PlayerType>> enum
    GameType GameType;              //referenced from <<GameType>> enum
    private PlayerType[] Button;
    private int GameId;
    private int time;

    public Game() {
        //initializes Players, Button, and GameType
        //sets up the game visible to players
    }

    public void StartGame() {
        //timer starts
        //chat starts
    }

    public int PlayerToIdx(PlayerType P) {
        //returns an index for each player
        //used to identify winner and assign color
        int idx = 0;
        return idx;
    }

    public void Update(UserEvent U) {
        //reacts to user input
        //no turns, real time reaction for letter selections
        //points update when word is won
    }

    public void tick() {
        //timer function
    }

    public boolean verifyWord(String wordString, int coord1[], int coord2[], Word wordWord) {
        //validates a match was made by player
        return true;
    }

    public int calpoints(Word word) {
        return 0;
    }

    public int calWinner() {
        return 0;
    }

    public boolean end() {
        return true;
    }

    public void hint() {}
}