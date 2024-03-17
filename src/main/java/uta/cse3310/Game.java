//Kaitlin Martin
//Group 6

package uta.cse3310;

public class Game {

    PlayerType Players;             //referenced from <<PlayerType>> enum
    GameType GameType;              //referenced from <<GameType>> enum
    public PlayerType[] Button;
    public int GameId;
    public int time;
    public float points;

    Game() {
        //initializes Players, Button, and GameType
        //sets up the game visible to players
    }

    public void StartGame() {
        //timer starts
        //chat starts
    }

    public int PlayerToIdx(PlayerType P) {
        //returns an index for each player
        //used to identify winner
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

    public boolean wordCheck() {
        //validates a match was made by player
        return true;
    }

    public float calPoints(Word W) {
        //calculates points earned from a word (SysReq021)
        return points;
    }

    public float calWinner() {
        //compares points earned by all players at time of method call
        int win = 0;    //temp value to hold idx of winner
        return win;
    }

}
