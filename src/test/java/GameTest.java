
package uta.cse3310;
//import uta.cse3310.*;

import junit.framework.TestCase;
import junit.framework.Assert;
import java.util.Vector;
import java.util.Random;
import java.util.Arrays;
/*
public class GameTest extends TestCase {
    Player P1 = new Player("user1", 1);
    Player P2 = new Player("user2", 2);
    Game G = new Game(P1, P2);
    Random R = new Random();
    Word W = new Word("test");
    WordBank wbank = new WordBank();
    GridGenerator GG = new GridGenerator(50, 50);
    Vector<Word> totalwords = new Vector<>();

    private int[] Coord1 = new int[2];
    private int[] Coord2 = new int[2];

    //method test
    public void testVerifyWord() {
        System.out.println("");
        System.out.println("Game Test");
        System.out.println("--------------");
        System.out.println("");

        //generates a grid
        totalwords = GG.generateGrid(wbank.getTotalList(), -1);
        
        //select a random word from the grid
        int index = R.nextInt(totalwords.size());
        Word element = totalwords.get(index);
        System.out.println("\nrandom word selected: " + element);

        //get the coordinates from that word
        Coord1 = element.getCoord1();
        Coord2 = element.getCoord2();
        System.out.println("\nCoord1: " + Arrays.toString(Coord1) + "\nCoord2: " + Arrays.toString(Coord2));

        //see if verifyWord can identify the random word and by its coords
        boolean b = G.verifyWord(Arrays.toString(Coord1), Arrays.toString(Coord2), totalwords);
        System.out.println("Result of verifyWord: " + b);
        assertTrue(b);
    }
}
*/