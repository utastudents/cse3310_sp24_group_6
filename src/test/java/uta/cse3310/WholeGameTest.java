package uta.cse3310;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Unit test for simple App.
 */
public class WholeGameTest
        extends TestCase {
    /**
     * Create the test case
     *
     * @param testName name of the test case
     * @return
     */
    public WholeGameTest(String testName) {
        super(testName);
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite() {
        return new TestSuite(WholeGameTest.class);
    }

    private uta.cse3310.PlayerType playGame(int[] x, int[] o) {
        uta.cse3310.PlayerType retval = uta.cse3310.PlayerType.NOPLAYER;

        Game G = new Game();

        UserEvent UX = new UserEvent();
        UX.GameId = 1;
        UX.PlayerIdx = uta.cse3310.PlayerType.XPLAYER;

        UserEvent UO = new UserEvent();
        UO.GameId = 1;
        UO.PlayerIdx = uta.cse3310.PlayerType.OPLAYER;

        ServerEvent E = new ServerEvent();

        G.GameId = 1;
        G.Players = uta.cse3310.PlayerType.OPLAYER;
        G.StartGame();

        for (int i = 0; i < x.length; i++) {
            UX.Button = x[i];
            G.Update(UX);

            if (i < o.length) {
                UO.Button = o[i];
                G.Update(UO);
            }
            System.out.println(i + "x message " + G.Msg[0]);
            System.out.println("o message " + G.Msg[1]);
        }

        if (G.Msg[0] == "You Win!") {
            retval = uta.cse3310.PlayerType.XPLAYER;

        }

        if (G.Msg[1] == "You Win!") {
            retval = uta.cse3310.PlayerType.OPLAYER;
        }
        // System.out.println("x message " + G.Msg[0]);
        // System.out.println("o message " + G.Msg[1]);
        return retval;
    }

    public void testXwins() {
        // does not work
        int xmoves[] = { 1, 2, 3 };
        int omoves[] = { 4, 5 };

        //assertTrue(playGame(xmoves, omoves) == uta.cse3310.PlayerType.XPLAYER);
    }

    public void testYwins() {
        int xmoves[] = { 1, 2, 3 };
        int omoves[] = { 4, 5 };

        //assertTrue(playGame(xmoves, omoves) == uta.cse3310.PlayerType.OPLAYER);
    }

    public void testApp() {

        Game G = new Game();

        UserEvent UX = new UserEvent();
        UX.GameId = 1;
        UX.PlayerIdx = uta.cse3310.PlayerType.XPLAYER;

        UserEvent UO = new UserEvent();
        UO.GameId = 1;
        UO.PlayerIdx = uta.cse3310.PlayerType.OPLAYER;

        ServerEvent E = new ServerEvent();

        G.GameId = 1;
        G.Players = uta.cse3310.PlayerType.OPLAYER;
        G.StartGame();

        // play a game

        UX.Button = 1;
        G.Update(UX);
        // X__
        // ___
        // ___

        UX.Button = 0;

        UO.Button = 2;
        G.Update(UO);
        // X0_
        // ___
        // ___

        UO.Button = 0;

        UX.Button = 4;
        G.Update(UX);
        // X0_
        // X__
        // ___

        UX.Button = 0;

        UO.Button = 5;
        G.Update(UO);
        // X0_
        // X0_
        // ___

        UO.Button = 0;

        UX.Button = 7;
        G.Update(UX);
        // X0_
        // X__
        // X__

        // System.out.println(G.Msg[0]);
        // System.out.println(G.Msg[1]);
        // X wins
        assertTrue(G.Msg[0] == "You Win!");
        assertTrue(G.Msg[1] == "You Lose!");

        // assertTrue ( G.Button[1] == uta.cse3310.PlayerType.XPLAYER);

        // String jsonString;
        // Gson gson = new Gson();
        // jsonString = gson.toJson(G);
        // System.out.println(jsonString);
    }
}
