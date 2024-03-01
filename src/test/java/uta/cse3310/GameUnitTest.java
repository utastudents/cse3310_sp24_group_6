package uta.cse3310;

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

    public void testApp() { // first create a game object
        Game G = new Game();

        // set it up for a draw game

        // XOX
        // OOX
        // XXO
        G.SetBoard(uta.cse3310.PlayerType.XPLAYER, new int[] { 0, 2, 5, 6, 7 });
        G.SetBoard(uta.cse3310.PlayerType.OPLAYER, new int[] { 1, 3, 4, 8 });

        // then CheckDraw()

        PlayerType p = uta.cse3310.PlayerType.OPLAYER;
 
        assertTrue(G.CheckDraw(p));
        assertFalse(G.CheckBoard(uta.cse3310.PlayerType.XPLAYER));
        assertFalse(G.CheckBoard(uta.cse3310.PlayerType.OPLAYER));

        // This is not a draw game.
        // X Wins

        // XOX
        // OOX
        // OXX
        G.ResetBoard();

        G.SetBoard(uta.cse3310.PlayerType.XPLAYER, new int[] { 0, 2, 5, 7, 8 });
        G.SetBoard(uta.cse3310.PlayerType.OPLAYER, new int[] { 1, 3, 4, 6 });

        // G.PrintGame();
        // then CheckDraw()
        assertFalse(G.CheckDraw(p));
        assertTrue(G.CheckBoard(uta.cse3310.PlayerType.XPLAYER));
        assertFalse(G.CheckBoard(uta.cse3310.PlayerType.OPLAYER));

        // This is not a draw game.
        // O Wins

        // XOX
        // XOX
        // _O_
        G.ResetBoard();

        G.SetBoard(uta.cse3310.PlayerType.XPLAYER, new int[] { 0, 2, 3, 5 });
        G.SetBoard(uta.cse3310.PlayerType.OPLAYER, new int[] { 1, 4, 7 });

        // G.PrintGame();
        // then CheckDraw()
        assertFalse(G.CheckDraw(p));
        assertTrue(G.CheckBoard(uta.cse3310.PlayerType.OPLAYER));
        assertFalse(G.CheckBoard(uta.cse3310.PlayerType.XPLAYER));

        // This is not a draw game.
        // O Wins

        // OXX
        // XOX
        // __O
        G.ResetBoard();

        G.SetBoard(uta.cse3310.PlayerType.XPLAYER, new int[] { 1, 2, 3, 5 });
        G.SetBoard(uta.cse3310.PlayerType.OPLAYER, new int[] { 0, 4, 8 });

        // G.PrintGame();
        // then CheckDraw()
        assertFalse(G.CheckDraw(p));
        assertTrue(G.CheckBoard(uta.cse3310.PlayerType.OPLAYER));
        assertFalse(G.CheckBoard(uta.cse3310.PlayerType.XPLAYER));
    }

}
// mvn -Dtest=WholeGameTest test