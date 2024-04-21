package uta.cse3310;
import java.util.*;

public class GameLobby 
{
    private int gameNum = 0;        // Number of players needed for game to start
    private int playersInGame = 0;  // Number of players currently in a game
    private int gtWanted = 0;       // Game type the player is seeking
    private Game G = null;          // Game that will be returned 
    private int matchFound = 0;

    private Vector<Player> LobbyList = new Vector<Player>();
    // PlayerList is all players that have logged into the game in this instance of the program running
    // LobbyList is all players who are waiting for their game to start, or who are currently in a game

    public GameLobby() 
    {
    
    }

    // Passed in the current active games and the individual player seeking a game.
    // Purpose is to match the player with an active game or to create a new game
    // if none of the active games match the gametype the player is seeking.
    // Max number of active games is 5 so if 5 are already running and the player wants
    // a game of a type that does not match any of them, they will be asked to try 
    // again later.
    public Game matchMaking(Vector<Game> ActiveGames, Player player) 
    {
        gtWanted = player.PlayerNum;

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
        
        return G;
    }

}