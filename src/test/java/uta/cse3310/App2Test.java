package uta.cse3310;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Unit test for simple App.
 */
public class App2Test
        extends TestCase {
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public App2Test(String testName) {
        super(testName);
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite() {
        return new TestSuite(App2Test.class);
    }

    /**
     * Rigourous Test :-)
     */
    public void testApp() {
        // this tests just shows one move...
        // remember, this is "code", you can use functions
        // and loops in the test code

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