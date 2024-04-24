/*
package uta.cse3310;
//import uta.cse3310.*;

import junit.framework.TestCase;
import junit.framework.Assert;
import java.util.Vector;
import java.util.Random;
import java.util.Arrays;

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

        //pick a random word from word vector
        //check if verifyWord can identify correct placement
        totalwords = GG.generateGrid(wbank.getTotalList(), -1);

        ///////////////////// FAILED ATTEMPT
        int rand_row1 = R.nextInt(51);          
        int rand_column1 = R.nextInt(51);
        System.out.println("random int row: " + rand_row1 + "\nrandom int column1: " + rand_column1);
        int row2 = rand_row1;
        int column2 = rand_column1 + (W.length() - 1);
        System.out.println("\nrow2: (should be equal to row1)" + row2 + "\ncolumn2: (should be column1 + 3)" + column2);
        
        W.setCoord1(rand_row1, rand_column1);
        W.setCoord2(row2, column2);
        System.out.println("\nCoord1: " + Coord1 + "\nCoord2: " + Coord2);
        ////////////////////////

        int index = R.nextInt(totalwords.size());
        Word element = totalwords.get(index);
        System.out.println("\nelement = " + element);
        //System.out.println("\nword list: " + totalwords);
        Coord1 = element.getCoord1();
        Coord2 = element.getCoord2();
        System.out.println("\nCoord1: " + Arrays.toString(Coord1) + "\nCoord2: " + Arrays.toString(Coord2));

        boolean b = G.verifyWord(Arrays.toString(Coord1), Arrays.toString(Coord2));
        System.out.println("Result of verifyWord: " + b);
        //test verifyWord method for correctness
        assertTrue(G.verifyWord(Arrays.toString(Coord1), Arrays.toString(Coord2)));
    }
}
*/