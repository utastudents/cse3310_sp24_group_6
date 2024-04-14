//Kaitlin Martin
//Group 6

package uta.cse3310;

import java.util.Timer;
import java.util.TimerTask;

public class Game {

    PlayerType Players;             //referenced from <<PlayerType>> enum
    GameType GameType;              //referenced from <<GameType>> enum
    private PlayerType[] Button;
    public int GameId;
    private int time;

    public Game(GameType gameType, PlayerType players) {
        this.GameType = gameType;
        this.Players = players;
        //initializes Players, Button, and GameType
        //sets up the game visible to players
        Button = new PlayerType[9];
        // initialize it
        for (int i = 0; i < Button.length; i++) {
            Button[i] = PlayerType.Player0;
        }
        /*
        Msg = new String[2];
        bottomMsg = new String[5];
        Players = PlayerType.XPLAYER;
        CurrentTurn = PlayerType.NOPLAYER;
        Msg[0] = "Waiting for other player to join";
        Msg[1] = "";
        bottomMsg[0] = "Total games played: " + S.played;
        bottomMsg[1] = "Games currently in progress: " + S.concurrent;
        bottomMsg[2] = "Games won by X: " + S.xwin;
        bottomMsg[3] = "Games won by O: " + S.owin;
        bottomMsg[4] = "Games that have been draws: " + S.draw;
        */
    }

    public void StartGame() {
        //timer starts
        //chat starts
    }

    public int PlayerToIdx(PlayerType P) {
        //returns an index for each player
        //used to identify winner and assign color
        int idx = 0;
        if (P == PlayerType.Player1) {
            //blue
            idx = 1;
        }
        else if (P == PlayerType.Player2) {
            //red
            idx = 2;
        }
        else if (P == PlayerType.Player3) {
            //yellow
            idx = 3;
        }
        else if (P == PlayerType.Player4) {
            //green
            idx = 4;
        }
        else {
            //No player
            idx = 0;
        }

        return idx;
    }

    public void Update(UserEvent U) {
        //reacts to user input
        //no turns, real time reaction for letter selections
        //points update when word is won
    }

    public void tick() {
        //SREQ025
        //TODO: format display
        Timer timer = new Timer();
        long startTime = System.currentTimeMillis();
        timer.scheduleAtFixedRate(new TimerTask() {
            int count = 300;

            @Override
            public void run() {
                System.out.println(count);

                if (count <= 0) {
                    timer.cancel();
                }

                count--;
            }
        }, 0, 1000);
    }

    public boolean verifyWord(String wordString, int coord1[], int coord2[], Word wordWord) {
        //validates a match was made by player
        return true;
    }

    public static int calPoints(Word word) {
        //SREQ021
        String test = "hello";
        int points = 0;
        //int seconds_elapsed = (System.currentTimeMillis() - startTime)/1000;  
        //points = (test.length())*(1/seconds_elapsed)*50;
        //System.out.println("POINTS: " + points);
        return points;
    }

    public int calWinner() {
        return 0;
    }

    public boolean end() {
        return true;
    }

    public void hint() {}
}