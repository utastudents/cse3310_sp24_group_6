package uta.cse3310;

public class GridGenerator {

    private float maxRow;
    private float maxColumn;
    private int currentCoord[];
    private float fillerChar[];
    private float gridDensity;  // # of char that are part of valid words / # of char in grid total
    private float validCharNum; // # of char in the grid total
    private int generationTime; // time in milliseconds taken to generate the grid

    public GridGenerator() {}
    
    public void generateGrid(int maxRow, int maxColumn) {}

    public float calGridDensity(int charNum, int validChar) {
        //validChar divided by charNum
        //should achieve > 0.67
        return 0;
    }

    public float calGridOrientation(float wordsNum, float direction) {
        //direction divided by wordsNum
        //should achieve at least 20% (0.20) for each direction
        //direction = number of words in certain direction
        //wordsNum = number of all words generated
        return 0;
    }

    public float calMean(float fillerChar[]) {
        //fillerChar[] holds the num of repeated letter in alphabet:
        /*example:
         * fillerChar[0] = # of A's
         * fillerChar[1] = # of B's
         */

        return 0;
    }

    public float calStandardDeviation(float fillerChar[], float fillerCharNum) {
        /*steps of standard deviation:
         * 1. Find the mean
         * 2. For each data point, find its distance from the mean and square it
         * 3. Sum the values found in step 2
         * 4. Divide that sum by total num of datapoints
         * 5. Take the square root
         */

        //fillerCharNum is the total num of datapoints (length of array)
        return 0;
    }

    public boolean checkWordOverlap() {
        return true;
    }
}