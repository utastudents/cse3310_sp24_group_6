package uta.cse3310;
import java.util.*;
import junit.framework.TestCase;

public class GameLobbyTest extends TestCase {

    private Vector<Game> ActiveGames = new Vector<Game>();
    private Vector<Player> PlayerList = new Vector<Player>();

    public void testGameLobby()
    {
        // TEST 1
        System.out.println("");
        System.out.println("Not Enough Players For New Game Test");
        System.out.println("------------------------------------");
        System.out.println("");

        String playerNick = "PLAYER1";
        int playerNum = 2; // Gametype the player is seeking
        Player player1 = new Player(playerNick, playerNum);
        PlayerList.add(player1);

        playerNick = "PLAYER2";
        playerNum = 3;
        Player player2 = new Player(playerNick, playerNum);
        PlayerList.add(player2);

        playerNick = "PLAYER3";
        playerNum = 4;
        Player player3 = new Player(playerNick, playerNum);
        PlayerList.add(player3);

        GameLobby GL = new GameLobby();
        Game G = GL.matchMaking(PlayerList, ActiveGames);
        System.out.println("Count ActiveGames = "+ActiveGames.size()); // There should be no games
        if(ActiveGames.size() == 0)
        {
            System.out.println("Not enough players for a new game test success");
        }
        
        PlayerList.clear(); // Clear out the player list

        // TEST 2
        System.out.println("");
        System.out.println("Two Player Game Test");
        System.out.println("--------------------");
        System.out.println("");

        // Create 2 players that want a 2 player game

        playerNick = "PLAYER1";
        playerNum = 4;
        Player player4 = new Player(playerNick, playerNum); // Extra player that does not want a 2 player game
        PlayerList.add(player4);

        playerNick = "PLAYER2";
        playerNum = 2;
        Player player5 = new Player(playerNick, playerNum);
        PlayerList.add(player5);

        playerNick = "PLAYER3";
        playerNum = 2;
        Player player6 = new Player(playerNick, playerNum);
        PlayerList.add(player6);

        G = GL.matchMaking(PlayerList, ActiveGames);
        System.out.println("Count ActiveGames = "+ActiveGames.size()); // There should be 1 game that is a 2 player game

        if(ActiveGames.size() == 0)
        {
            System.out.println("Active Games is empty, two player game test failed");
        }

        int gameNum = 0;

        if(ActiveGames.size() == 1)
        {
            gameNum = 2+(G.getGameType().ordinal()); // Gametype of G as an int
            if(gameNum == 2)
            {
                System.out.println("Two player game test success");
            }
        }

        PlayerList.clear(); // Clear out the player list
        ActiveGames.clear(); // Clear out the active games

        // TEST 3
        System.out.println("");
        System.out.println("Three Player Game Test");
        System.out.println("----------------------");
        System.out.println("");

        // Create 3 players that want a 3 player game

        playerNick = "PLAYER1";
        playerNum = 4;
        Player player7 = new Player(playerNick, playerNum); // Extra player that does not want a 3 player game
        PlayerList.add(player7);

        playerNick = "PLAYER2";
        playerNum = 2;
        Player player8 = new Player(playerNick, playerNum); // Extra player that does not want a 3 player game
        PlayerList.add(player8);

        playerNick = "PLAYER3";
        playerNum = 3;
        Player player9 = new Player(playerNick, playerNum); 
        PlayerList.add(player9);

        playerNick = "PLAYER4";
        playerNum = 3;
        Player player10 = new Player(playerNick, playerNum);
        PlayerList.add(player10);

        playerNick = "PLAYER5";
        playerNum = 3;
        Player player11 = new Player(playerNick, playerNum);
        PlayerList.add(player11);

        G = GL.matchMaking(PlayerList, ActiveGames);
        System.out.println("Count ActiveGames = "+ActiveGames.size()); // There should be 1 game that is a 3 player game

        if(ActiveGames.size() == 0)
        {
            System.out.println("Active Games is empty, three player game test failed");
        }

        gameNum = 0;

        if(ActiveGames.size() == 1)
        {
            gameNum = 2+(G.getGameType().ordinal()); // Gametype of G as an int
            if(gameNum == 3)
            {
                System.out.println("Three player game test success");
            }
        }

        PlayerList.clear(); // Clear out the player list
        ActiveGames.clear(); // Clear out the active games

        // TEST 4
        System.out.println("");
        System.out.println("Four Player Game Test");
        System.out.println("---------------------");
        System.out.println("");

        
        // Create 4 players that want a 4 player game

        playerNick = "PLAYER1";
        playerNum = 2;
        Player player12 = new Player(playerNick, playerNum); // Extra player that does not want a 4 player game
        PlayerList.add(player12);

        playerNick = "PLAYER2";
        playerNum = 3;
        Player player13 = new Player(playerNick, playerNum); // Extra player that does not want a 4 player game
        PlayerList.add(player13);

        playerNick = "PLAYER3";
        playerNum = 4;
        Player player14 = new Player(playerNick, playerNum); 
        PlayerList.add(player14);

        playerNick = "PLAYER4";
        playerNum = 4;
        Player player15 = new Player(playerNick, playerNum); 
        PlayerList.add(player15);

        playerNick = "PLAYER5";
        playerNum = 4;
        Player player16 = new Player(playerNick, playerNum);
        PlayerList.add(player16);

        playerNick = "PLAYER6";
        playerNum = 4;
        Player player17 = new Player(playerNick, playerNum);
        PlayerList.add(player17);

        G = GL.matchMaking(PlayerList, ActiveGames);
        System.out.println("Count ActiveGames = "+ActiveGames.size()); // There should be 1 game that is a 3 player game

        if(ActiveGames.size() == 0)
        {
            System.out.println("Active Games is empty, four player game test failed");
        }

        gameNum = 0;

        if(ActiveGames.size() == 1)
        {
            gameNum = 2+(G.getGameType().ordinal()); // Gametype of G as an int
            if(gameNum == 4)
            {
                System.out.println("Four player game test success");
            }
        }

        PlayerList.clear(); // Clear out the player list
        ActiveGames.clear(); // Clear out the active games
    }
}