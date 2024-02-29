package uta.cse3310;

import javax.xml.crypto.dsig.XMLSignContext;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class GameUnitTest
        extends TestCase {
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public GameUnitTest(String testName) {
        super(testName);
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite() {
        return new TestSuite(GameUnitTest.class);
    }

    public void testApp() {
        // first create a game object
        Game G = new Game();

        // set it up for a draw game

        // XOX
        // OOX
        // XXO

        G.Button[0] = uta.cse3310.PlayerType.XPLAYER;
        G.Button[2] = uta.cse3310.PlayerType.XPLAYER;
        G.Button[5] = uta.cse3310.PlayerType.XPLAYER;
        G.Button[6] = uta.cse3310.PlayerType.XPLAYER;
        G.Button[7] = uta.cse3310.PlayerType.XPLAYER;

        G.Button[1] = uta.cse3310.PlayerType.OPLAYER;
        G.Button[3] = uta.cse3310.PlayerType.OPLAYER;
        G.Button[4] = uta.cse3310.PlayerType.OPLAYER;
        G.Button[8] = uta.cse3310.PlayerType.OPLAYER;

        // then CheckDraw()

        PlayerType p = uta.cse3310.PlayerType.OPLAYER;

        assertTrue(G.CheckDraw(p));

        // This is not a draw game.

        // XOX
        // OOX
        // OXX

        G.Button[0] = uta.cse3310.PlayerType.XPLAYER;
        G.Button[2] = uta.cse3310.PlayerType.XPLAYER;
        G.Button[5] = uta.cse3310.PlayerType.XPLAYER;
        G.Button[7] = uta.cse3310.PlayerType.XPLAYER;
        G.Button[8] = uta.cse3310.PlayerType.XPLAYER;

        G.Button[1] = uta.cse3310.PlayerType.OPLAYER;
        G.Button[3] = uta.cse3310.PlayerType.OPLAYER;
        G.Button[4] = uta.cse3310.PlayerType.OPLAYER;
        G.Button[6] = uta.cse3310.PlayerType.OPLAYER;

        // then CheckDraw()
        assertFalse(G.CheckDraw(p));

    }
}
