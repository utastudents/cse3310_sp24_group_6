package uta.cse3310;

public class GridGenerator {

    float maxRow;
    float maxColumn;
    int currentCoord[];
    float gridDensity;  // # of char that are part of valid words / # of char in grid total
    float validCharNum; // # of char in the grid total
    int generationTime; // time in milliseconds taken to generate the grid

    public GridGenerator() {}
    
    public void generateGrid(int row, int column) {}

    public float calGridDensity(int charNum, int validChar) {
        return 0;
    }

    public boolean checkWordOverlap() {
        return true;
    }
}