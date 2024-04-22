package uta.cse3310;
import java.util.*;

public class GameLobby 
{
    // private int gameNum = 0;        // Number of players needed for game to start
    //private int playersInGame = 0;  // Number of players currently in a game
    private int gtWanted = 0;       // Game type the player is seeking
    private Game G = null;          // Game that will be returned 
    //private int matchFound = 0;

    private Vector<Player> LobbyList = new Vector<Player>();
    // PlayerList is all players that have logged into the game in this instance of the program running
    // LobbyList is all players who are waiting for their game to start, or who are currently in a game

    public GameLobby() {}

    // MATCHMAKING REDONE - Now only makes a new game when ther is enough players that want to play it
    // instead of making a new game whenever a player requests a game type that has not already been requested
    public Game matchMaking(Player player, Vector<Player> PlayerList) 
    {
        int pForTwo = 0;
        int pForThree = 0;
        int pForFour = 0;

        // Count up how many players currently want each type of game
        for (Player player : PlayerList) 
        {
            gtWanted = player.PlayerNum;
            // player will have a PlayerNum of 0 after they are put into a game 

            if (gtWanted == 2)
            {
                ++pForTwo;
            }
            else if (gtWanted == 3)
            {
                ++pForThree;
            }
            else if (gtWanted == 4)
            {
                ++pForFour;
            }
        }

        Player player1 = null;
        Player player2 = null;
        Player player3 = null;
        Player player4 = null;
        Player currPlayer = null;
        int playersNeeded = 0;

        // If there are enough players create and start the game 
        if (pForTwo >= 2)
        {
            playersNeeded = 0;

            // Get the first 2 players that want to join a 2 player game
            while(playersNeeded < 2 )
            {
                for (int i = 0; i < PlayerList.size(); i++)
                {
                    currPlayer = PlayerList.get(i);
                    gtWanted = currPlayer.PlayerNum;

                    if (gtWanted == 2 && playersNeeded == 0)
                    {
                        player1 = currPlayer;
                        ++playersNeeded;

                    }
                    else if (gtWanted == 2 && playersNeeded == 1)
                    {
                        player2 = currPlayer;
                        ++playersNeeded;
                    }
                }
            }

            // Make new game
            G = new Game(player1, player2);
            
            return G;
        }
        else if (pForThree >= 3)
        {
            playersNeeded = 0;

            // Get the first 3 players that want to join a 3 player game
            while(playersNeeded < 3 )
            {
                for (int i = 0; i < PlayerList.size(); i++)
                {
                    currPlayer = PlayerList.get(i);
                    gtWanted = currPlayer.PlayerNum;

                    if (gtWanted == 3 && playersNeeded == 0)
                    {
                        player1 = currPlayer;
                        ++playersNeeded;

                    }
                    else if (gtWanted == 3 && playersNeeded == 2)
                    {
                        player2 = currPlayer;
                        ++playersNeeded;
                    }
                    else if (gtWanted == 3 && playersNeeded == 3)
                    {
                        player3 = currPlayer;
                        ++playersNeeded;
                    }

                }
            }

            // Make new game
            G = new Game(player1, player2, player3);
            
            return G;
        }
        else if (pForFour >= 4)
        {
            playersNeeded = 0;

            // Get the first 4 players that want to join a 4 player game
            while(playersNeeded < 4 )
            {
                for (int i = 0; i < PlayerList.size(); i++)
                {
                    currPlayer = PlayerList.get(i);
                    gtWanted = currPlayer.PlayerNum;

                    if (gtWanted == 3 && playersNeeded == 0)
                    {
                        player1 = currPlayer;
                        ++playersNeeded;
                    }
                    else if (gtWanted == 3 && playersNeeded == 2)
                    {
                        player2 = currPlayer;
                        ++playersNeeded;
                    }
                    else if (gtWanted == 3 && playersNeeded == 3)
                    {
                        player3 = currPlayer;
                        ++playersNeeded;
                    }
                    else if (gtWanted == 3 && playersNeeded == 3)
                    {
                        player4 = currPlayer;
                        ++playersNeeded;
                    }
                }
            }

            // Make new game
            G = new Game(player1, player2, player3, player4);
    
            return G;
        }

    }
        
        // OLD MATCH MAKING---------------------------------------------------------------------------------------------
        // Passed in the current active games and the individual player seeking a game.
        // Purpose is to match the player with an active game or to create a new game
        // if none of the active games match the gametype the player is seeking.
        // Max number of active games is 5 so if 5 are already running and the player wants
        // a game of a type that does not match any of them, they will be asked to try 
        // again later.

        /*gtWanted = player.PlayerNum;

        // Look through the ActiveGames to see if a game matches what the player wants 
        for (int i = 0; i < ActiveGames.size(); i++)
        {
            Game AG = ActiveGames.get(i); // Current game in loop
            
            // Get the GameType and the number of players currently in the game
            gameNum = 2+(AG.GameType.ordinal()); // Add 2 so GameNum will equal int value of Game type (Game2 = int 2)
            playersInGame = AG.Players.ordinal();

            // If the game is of the same size as what the player requested
            // AND if the game still needs more players
            if ((gtWanted == gameNum) && (gameNum > playersInGame))
            {
                G = AG;
                matchFound = 1;
                System.out.println("Found a match!");
            }
        }

        if(matchFound == 1)
        {
            // G will be passed back to App
            // Change the game's PlayerType to reflect 1 more player joining
            switch (G.Players)
            {
                case Player1:
                    G.Players = PlayerType.Player2;
                    break;
                case Player2:
                    G.Players = PlayerType.Player3;
                    break;
                case Player3: 
                    G.Players = PlayerType.Player4;
                    break;
            }

        }
        else if(ActiveGames.size() < 5 && matchFound == 0)
        {
            // Less than 5 games and no match found? create new game
            if (gtWanted == 2)
            {
                G = new Game(GameType.Game2, PlayerType.Player1);
                // Game created and 1st player added
                // PlayerType is the current amount of player in the game
            }
            else if(gtWanted == 3)
            {
                G = new Game(GameType.Game3, PlayerType.Player1);
            }
            else if(gtWanted == 4)
            {
                G = new Game(GameType.Game4, PlayerType.Player1);
            }

            ActiveGames.add(G);
            System.out.println("Creating a new Game!");
        }
        
        if(G == null) // If no game was made or selected that means that there are already 5 in progress
        {
            System.out.println("Sorry, maximum amount of games is in progress. Please try again later.");
        }
        
        return G;*/

}