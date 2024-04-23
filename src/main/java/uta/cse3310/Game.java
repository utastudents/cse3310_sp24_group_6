package uta.cse3310;

import java.util.Timer;
import java.util.TimerTask;
import java.util.Vector;
import java.util.Iterator;
import java.util.Arrays;
import java.time.LocalTime;

public class Game {

    // All Information that will be sent to JSON
    Vector<Player> player = new Vector<>();                       //referenced from <<GameType>> enum
    WordBank wbank = new WordBank();
    GridGenerator g = new GridGenerator(50,50);
    Vector<Word> totalwords = new Vector<>();
    static LocalTime start = LocalTime.now();
    PlayerType Players;
    GameType gameType;  
    public int GameId; 
    public String[] Msg;

    static long startTime;
    private int time;
    
    
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
        //CurrentTurn = PlayerType.Player1;
        //Msg[0] = "Game has started! It's Player 1's turn.";
        //tick();  // Start the timer
        //chat starts

        Msg[0] = "Good luck, game has been started";

        // Send all players to the current game
        // Somehow initiate the game when this gets called.
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
        System.out.println("The user event is " + U.PlayerIdx + "  " + U.Button);
        // Check if a chosen word is legitimate and is correct
        int[] coord1 = new int[2];
        int[] coord2 = new int[2];

        coord1[0] = U.StartCoordinate/50;
        coord1[1] = U.EndCoordinate % 50;
        coord2[0] = U.StartCoordinate/50;
        coord2[1] = U.EndCoordinate % 50;
        if(verifyWord(coord1, coord2))
        {
            U.points++;
        }

        // Check if the time is up, the game is over and choose who won
            //if player won, send to win screen
            //if player lost, send to lose screen

        

        // Check if a message has been sent to the chat box
            //if a message has been sent, send to the clients
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

    public boolean verifyWord(int coord1[], int coord2[]) {  
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