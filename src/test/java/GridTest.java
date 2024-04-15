package uta.cse3310;
import junit.framework.TestCase;
import junit.framework.Assert;
import java.util.Vector;

public class GridTest extends TestCase {
    
    public void testGridGen() {
        WordBank W = new WordBank();
        GridGenerator G = new GridGenerator(50, 50);
        Vector<Word> test = new Vector<>();
        double genTime;
        double gridDensity;
        double[] orientation = new double[8];
        double stdDev;
        boolean bool;

        System.out.println("");
        System.out.println("Generation Stats");
        System.out.println("----------------");
        System.out.println("");

        test = G.generateGrid(W.getTotalList());
        
        bool = false;
        genTime = G.getGenTime();
        if(genTime < 1) {
            bool = true;
        }
        assertTrue(bool);
        if(bool) {
            System.out.println("Generation time good: " + genTime + " seconds");
        }
        
        bool = false;
        gridDensity = G.getGridDensity();
        if(gridDensity >= 0.67) {
            bool = true;
        }
        assertTrue(bool);
        if(bool) {
            System.out.println("Grid density good: " + gridDensity);
        }

        bool = false;
        orientation = G.getOrientation();
        for(int i = 0; i < 8; i++) {
            if(orientation[i] <= 20 && orientation[i] >= 10) {
                bool = false;
                break;
            }
            if(i == 7) {
                bool = true;
            }
        }
        assertTrue(bool);
        if(bool) {
            System.out.println("Orientation distribution good. All 8 between 10% and 20%");
        }

        bool = false;
        stdDev = G.standardDeviation(G.fillerMean());
        if(stdDev < 10) {
            bool = true;
        }
        assertTrue(bool);
        if(bool) {
            System.out.println("Standard Deviation good: " + stdDev);
        }


        for(int i = 0; i < test.size(); i++) {
            W.addList(test.elementAt(i));
        }
        W.generateWords();
        G.printGrid();
        System.out.println("");
        System.out.println("Cheat Sheet");
        System.out.println("-----------");
        System.out.println("");
        W.printWordList(W.getWordList());
    }


}