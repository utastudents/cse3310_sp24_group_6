package uta.cse3310;
import java.util.*;

public class GameLobby 
{
    private int GameId = 0;
    int gameNum = 0; // Number of players needed for game to start
    int playersInGame = 0; // Number of players currently in a game

    private Vector<Player> LobbyList = new Vector<Player>();
    // PlayerList is all players that have logged into the game
    // LobbyList is all players who are waiting for their game to start, or who are currently in a game

    public GameLobby() 
    {
    
    }

    public Game matchMaking(Vector<Game> ActiveGames, Vector<Player> PlayerList) 
    {
        Game G = null;

        for (Player player : PlayerList) // For each player in PlayerList
        {
            if (!LobbyList.contains(player)) // If this player is not already waiting to/in a game
            {              
                for (Game i : ActiveGames) // For each game in ActiveGames
                {
                    gameNum = 2+(G.GameType.ordinal()); // Add 2 so GameNum will equal int value of Game type (Game2 = int 2)
                    // Null pointer exception on line above
                    playersInGame = G.Players.ordinal();

                    // If the game is of the same size as what the player requested
                    // AND if the game still needs more players
                    if ((player.PlayerNum == gameNum) && (gameNum > playersInGame))
                    {
                        G = i;
                        System.out.println("Found a match!");
                    }
                }

                // Make sure there is less than 5 games in progress to proceed
                // If more than 5 games matchMaking will return null
                if(ActiveGames.size() < 5)
                {
                    // No matches ? Create a new Game.
                    if (G == null) 
                    {
                        if (player.PlayerNum == 2)
                        {
                            G = new Game(GameType.Game2, PlayerType.Player1); // Game created and 1st player added
                        }
                        else if(player.PlayerNum == 3)
                        {
                            G = new Game(GameType.Game3, PlayerType.Player1);
                        }
                        else if(player.PlayerNum == 4)
                        {
                            G = new Game(GameType.Game4, PlayerType.Player1);
                        }

                        G.GameId = GameId;
                        GameId++;
                        ActiveGames.add(G);
                        System.out.println(" creating a new Game");
                    } 
                    else 
                    {
                        // join an existing game
                        System.out.println(" not a new game");

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
                            // Player 4 is not included since it is the max player amount
                        }
                    }
                }
            }
        }

        if(G == null) // If no game was made or selected that means that there are already 5 in progress
        {
            System.out.println("Sorry, maximum amount of games is in progress. Please try again later.");
        }
        else
        {
            playersInGame = G.Players.ordinal();
            gameNum = 2+(G.GameType.ordinal());
            if (gameNum == playersInGame) // If there are enough players, start the game
            {
                System.out.println("All players now present:"+playersInGame+"/"+gameNum+"\nGame starts now.");
                G.StartGame();
            }
        }

        return G;
    }
}