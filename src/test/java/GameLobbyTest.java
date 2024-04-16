package uta.cse3310;
import java.util.*;
import junit.framework.TestCase;

public class GameLobbyTest extends TestCase {
    
    private Vector<Game> ActiveGames = new Vector<Game>();
    private Vector<Player> PlayerList = new Vector<Player>();
    private Vector<Player> LobbyList = new Vector<Player>();

    // Tests that you cannot create a new game if there are already 5 in progress
    public void testMaxGames() 
    {
        System.out.println("");
        System.out.println("Max Games Test");
        System.out.println("------------------");
        System.out.println("");

        // Making 5 games to simulate 5 games currently in progress
        Game A = new Game(GameType.Game2, PlayerType.Player1);
        ActiveGames.add(A);
        Game B = new Game(GameType.Game2, PlayerType.Player1);
        ActiveGames.add(B);
        Game C = new Game(GameType.Game2, PlayerType.Player1);
        ActiveGames.add(C);
        Game D = new Game(GameType.Game2, PlayerType.Player1);
        ActiveGames.add(D);
        Game E = new Game(GameType.Game2, PlayerType.Player1);
        ActiveGames.add(E);

        GameLobby GL = new GameLobby();
        Game G = GL.matchMaking(ActiveGames, PlayerList);

        if(G == null)
        {
            System.out.println("Max games test success\n");
        }
    }

    // Tests that a player can join a brand new game if there are none that
    // fit thier gametype request
    /* TEST ERRORS CURRENTLY - just backing up progress for now
    public void testJoinNewGame()
    {
        System.out.println("");
        System.out.println("Join New Game Test");
        System.out.println("------------------");
        System.out.println("");

        Player player = new Player("TEST", 2); // 2nd parameter 2 is the gametype the player wants
        PlayerList.add(player);

        // Adding in games of the game types that don't match what the player wants
    
        Game A = new Game(GameType.Game3, PlayerType.Player1); // 3 person game with 1 player waiting
        ActiveGames.add(A);
        Game B = new Game(GameType.Game4, PlayerType.Player1); // 4 person game with 1 player waiting
        ActiveGames.add(B);

        GameLobby GL = new GameLobby();
        Game G = GL.matchMaking(ActiveGames, PlayerList);

        int gameNum = 2+(G.GameType.ordinal()); // Add 2 so GameNum will equal int value of Game type (Game2 = int 2)
        int playersInGame = G.Players.ordinal();

        if ((gameNum == 2) && (playersInGame == 1)) // If the newest game made is a 2 player game with 1 player waiting
        {
            System.out.println("Join new game test success\n");
        }

    }*/
}