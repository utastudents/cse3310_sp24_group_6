package uta.cse3310;

import java.util.Timer;
import java.util.TimerTask;
import java.util.Vector;
import java.util.Iterator;
import java.util.Arrays;
import java.time.LocalTime;

public class Game {

    Vector<Player> player = new Vector<>();
    GameType gameType;                          //referenced from <<GameType>> enum
    WordBank wbank = new WordBank();
    GridGenerator g = new GridGenerator(50,50);
    Vector<Word> totalwords = new Vector<>();
    PlayerType Players;
    static LocalTime start = LocalTime.now();

    static long startTime;
    public int GameId;
    private int time;
    public PlayerType CurrentTurn;
    
    
    // Constructor for the different game types
    public Game(Player player1, Player player2) {
        this.player.add(player1);
        this.player.add(player2);
        this.gameType = GameType.Game2;
        totalwords = g.generateGrid(wbank.getTotalList(), -1);
        
        player1.setPlayerType(PlayerType.Player1);
        player2.setPlayerType(PlayerType.Player2);
        
    }
    public Game(Player player1, Player player2, Player player3) {
        this.player.add(player1);
        this.player.add(player2);
        this.player.add(player3);
        this.gameType = GameType.Game3;
        totalwords = g.generateGrid(wbank.getTotalList(), -1);
         
        player1.setPlayerType(PlayerType.Player1);
        player2.setPlayerType(PlayerType.Player2);
        player3.setPlayerType(PlayerType.Player3);
        
    }
    public Game(Player player1, Player player2, Player player3, Player player4) {
        this.player.add(player1);
        this.player.add(player2);
        this.player.add(player3);
        this.player.add(player4);
        this.gameType = GameType.Game4;
        totalwords = g.generateGrid(wbank.getTotalList(), -1);
        
        player1.setPlayerType(PlayerType.Player1);
        player2.setPlayerType(PlayerType.Player2);
        player3.setPlayerType(PlayerType.Player3);
        player4.setPlayerType(PlayerType.Player4);
        
    }

    public void StartGame() {
        CurrentTurn = PlayerType.Player1;
        //Msg[0] = "Game has started! It's Player 1's turn.";
        tick();  // Start the timer
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
        Timer timer = new Timer();
        long startTime = System.currentTimeMillis();
        timer.scheduleAtFixedRate(new TimerTask() {
            int count = 300;

            @Override
            public void run() {
                System.out.println("Time left: " + count + " seconds");

                if (count <= 0) {
                    timer.cancel();
                    System.out.println("Time's up!");
                    endGame("Time`s up!"); //call to the game
                }
                count--;
            }
        }, 0, 1000);
    }

    public boolean verifyWord(String wordString, int coord1[], int coord2[], Word wordWord) {  
        boolean result = false;

        for(Word w : totalwords) {
            if(Arrays.equals(coord1, w.getCoord1()) && Arrays.equals(coord2, w.getCoord2())) {
                result = true;
                break;
            }
        }

        return result;
    }

    public static int calPoints(Word word) {
        //SREQ021
        LocalTime currentTime = LocalTime.now();
        int elapsedMin = Math.abs(currentTime.getMinute() - start.getMinute());
        int elapsedSec = Math.abs(currentTime.getSecond() - start.getSecond());
        int time = (elapsedMin*60) + elapsedSec;

        int points = (word.length())*time;
        System.out.println("POINTS: " + points);
        return points;
    }

    public void endGame(String reason) {
        System.out.println("Game Over: " + reason);
    }


    public boolean end() {      // I dont know if there is any more implementation -RUDY
        return true;
    }

    public void hint() {

    }
}