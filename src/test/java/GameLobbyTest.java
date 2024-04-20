package uta.cse3310;
import java.util.*;
import junit.framework.TestCase;

public class GameLobbyTest extends TestCase {

    private Vector<Game> ActiveGames = new Vector<Game>();
    private Vector<Player> PlayerList = new Vector<Player>();
    private Vector<Player> LobbyList = new Vector<Player>();

    public void testGameLobby()
    {

        // ------------ TEST 1 ------------
        // Tests that you cannot create a new game if there are already 5 in progress
        System.out.println("");
        System.out.println("Max Games Test");
        System.out.println("------------------");
        System.out.println("");

        String playerNick = "TEST";
        int playerNum = 4; // Gametype the player is seeking
        Player player = new Player(playerNick, playerNum);

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
        Game G = GL.matchMaking(ActiveGames, player);
        System.out.println("Count ActiveGames = "+ActiveGames.size());

        if(G == null)
        {
            System.out.println("Max games test success\n");
        }

        // Reset the ActiveGames vector
        ActiveGames.clear();
        System.out.println("Count ActiveGames = "+ActiveGames.size());


        // ------------ TEST 2 ------------
        // Tests that a player can join a brand new game if there are none that fit thier gametype request
        System.out.println("");
        System.out.println("Create & Join New Game Test");
        System.out.println("---------------------------");
        System.out.println("");

        // Adding in games of the game types that don't match what the player wants
    
        ActiveGames.add(A);
        ActiveGames.add(B);

        int bCount = ActiveGames.size(); // Should be 2 right now
        System.out.println("Num of Active Games before matching = "+bCount);

        G = GL.matchMaking(ActiveGames, player);
        int aCount = ActiveGames.size(); // Should be 3 right now
        System.out.println("Num of Active Games after matching = "+aCount);

        int gameNum = 2+(G.GameType.ordinal()); // Add 2 so GameNum will equal int value of Game type (Game2 = int 2)
        int playersInGame = G.Players.ordinal();

        if ((gameNum == 4) && (playersInGame == 1)) // If the newest game made is a 4 player game with 1 player waiting
        {
            System.out.println("Joining lobby for new game test success\n");
        }

        // ------------ TEST 3 ------------ TODO
        // Tests that a player can join a active game if it matches the game type they wanted and has space for them
    }
}