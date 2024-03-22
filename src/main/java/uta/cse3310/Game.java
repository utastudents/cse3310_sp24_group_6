//Kaitlin Martin
//Group 6

/*notes:
 * created this new class to eventually replace Game.
 * I'm keeping them separate for now for organization purposes,
 * and so that I can reference the Game class while building
 */

package uta.cse3310;

public class Game {

<<<<<<< HEAD
    PlayerType Players;             //referenced from <<PlayerType>> enum
    GameType GameType;              //referenced from <<GameType>> enum
    public PlayerType[] Button;
    public int GameId;

    public void Game() {
=======
    private PlayerType Players;             //referenced from <<PlayerType>> enum
    private GameType GameType;              //referenced from <<GameType>> enum
    private PlayerType Button[];
    private int GameId;
    private int time;

    public Game() {
>>>>>>> David_Branch
        //initializes Players, Button, and GameType
        //sets up the game visible to players
    }

    public void StartGame() {
        //timer starts
        //chat starts
<<<<<<< HEAD
    }

    public int PlayerToIdx(PlayerType P) {
        //NOTE: this method may not be necessary since PlayerType enums
        //are already indexed in this case (not X or O)
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

=======
    }

    public int PlayerToIdx(PlayerType P) {
        //NOTE: this method may not be necessary since PlayerType enums
        //are already indexed in this case (not X or O)
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
>>>>>>> David_Branch
}