package uta.cse3310;
//import uta.cse3310.*;

import junit.framework.TestCase;
import junit.framework.Assert;
import java.util.Vector;
import java.util.Random;

public class GameTest extends TestCase {
    Player P1 = new Player("user1", 1);
    Player P2 = new Player("user2", 2);
    Game G = new Game(P1, P2);
    Random R = new Random();
    Word W = new Word("test");
    WordBank wbank = new WordBank();
    GridGenerator GG = new GridGenerator(50, 50);
    //Vector<Word> totalwords = new Vector<>();

    //method test
    public void testVerifyWord() {
        //place test word into empty grid at random coordinates
        //for now, the orientation is horizontal only. Will implement more later
        int rand_row1 = R.nextInt(51);
        int rand_column1 = R.nextInt(51);
        int row2 = rand_row1;
        int column2 = rand_column1 + (W.length() - 1);

        W.setCoord1(rand_row1, rand_column1);
        W.setCoord2(row2, column2);

        int[] Coord1 = W.getCoord1();
        int[] Coord2 = W.getCoord2();

        //test verifyWord method for correctness
        assertTrue(G.verifyWord("test", Coord1, Coord2, W));
    }
}