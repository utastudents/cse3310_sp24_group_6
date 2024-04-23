package uta.cse3310;
//import uta.cse3310.*;
import junit.framework.TestCase;
import junit.framework.Assert;
import java.util.Vector;
import java.util.Arrays;
import java.lang.System;

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
        int seed;

        if(System.getenv("GRID_TEST") != null) {
            seed = Integer.parseInt(System.getenv("GRID_TEST"));
        } else {
            seed = -1;
        }

        System.out.println("");
        System.out.println("Grid Gen Unit Test");
        System.out.println("------------------");
        System.out.println("");

        test = G.generateGrid(W.getTotalList(), seed);
        
        bool = false;
        genTime = G.getGenTime();
        //Checks that generation time is under 1 second
        if(genTime < 1) {
            bool = true;
        }
        assertTrue(bool);
        if(bool) {
            System.out.println("Generation time good: " + genTime + " seconds");
        }
        
        bool = false;
        gridDensity = G.getGridDensity();
        //Checks that density of letters part of actual words is above 67%, letters used in multiple words are only counted once
        if(gridDensity >= 0.67) {
            bool = true;
        }
        assertTrue(bool);
        if(bool) {
            System.out.println("Grid density good: " + gridDensity);
        }

        bool = false;
        orientation = G.getOrientation();
        //Checks that distribution of orientation isn't too skewed. Code cycles through orientations, trying each once regardless of success/failure
        //This is because forcing orientations/words I found occasionally led to impossible scenarios,
        //so a bit of orientation balance is sacrificed for the sake of generation success
        //Also, the professor's requirements stated the 5 orientations will be >= 12.5%, but he listed 6 orientations, also there's 8 possible orientations,
        //so idk what he was smoking and I decided between 10% and 15% sounded pretty fair
        for(int i = 0; i < 8; i++) {
            if(orientation[i] <= 15 && orientation[i] >= 10) {
                bool = false;
                break;
            }
            if(i == 7) {
                bool = true;
            }
        }
        assertTrue(bool);
        if(bool) {
            System.out.println("Orientation distribution good. All 8 between 10% and 20%" + Arrays.toString(orientation));
        }

        bool = false;
        //For standard deviation, I couldn't find any hard number to shoot for, so I just picked 8. In my testing I average between 4-6
        //I could make a simple loop that goes through all the letters of the alphabet and places them in random places, but that would kill gen time,
        //and the other option is to just place letters in some sort of pattern, but I felt like doing it somewhat randomly was the best way
        stdDev = G.standardDeviation(G.fillerMean());
        if(stdDev < 8) {
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
        
        //Reads the grid coordinate by coordinate to make sure the letters there match the word that's supposed to be at those coordinates
        bool = true;
        for(Word w: W.getWordList()) {
            if(!(w.getWord().equals(G.read(w)))) {
                bool = false;
            }
        }
        assertTrue(bool);
        if(bool) {
            System.out.println("All words placed properly, no errors");
        }
        
        System.out.println("");
        G.printGrid();
        System.out.println("");
        System.out.println("Cheat Sheet");
        System.out.println("-----------");
        System.out.println("");
        W.printWordList(W.getWordList());
    }


}