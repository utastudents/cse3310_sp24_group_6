package uta.cse3310;
import java.util.*;

public class GameLobby 
{
    private int GameId = 0;

    public GameLobby() 
    {
    
    }

    public void matchMaking(Vector<Game> ActiveGames, Vector<Player> PlayerList) 
    {
       while (!PlayerList.isEmpty())
        {
            // This next chunk was just from App from the original TicTacToe code
            // Needs to be modified to work with the max of 4 players
            Game G = null;
            for (Game i : ActiveGames) {
                if (i.Players == uta.cse3310.PlayerType.Player1) {
                G = i;
                System.out.println("found a match");
                }
            }

            // No matches ? Create a new Game.
            if (G == null) {
                G = new Game();
                G.GameId = GameId;
                GameId++;
                // Add the first player
                G.Players = PlayerType.Player1;
                ActiveGames.add(G);
                System.out.println(" creating a new Game");
            } else {
                // join an existing game
                System.out.println(" not a new game");
                G.Players = PlayerType.Player2;
                G.StartGame();
            }
        }
    }
}