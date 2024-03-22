package uta.cse3310;

public class GridGenerator {

<<<<<<< HEAD
    int row;
    int column;
    float gridDensity;  // # of char that are part of valid words / # of char in grid total
    int charNum;        // # of char in the grid total
    int generationTime; // time in milliseconds taken to generate the grid

    public void generateGrid(int row, int column) {}

    public float calGridDensity(int charNum, int validChar) {
        return gridDensity;
    }

    public void checkWordOverlap() {}
=======
    int maxRow;
    int maxColumn;
    int[] currentCoord;
    float gridDensity;  // # of char that are part of valid words / # of char in grid total
    int charNum;        // # of char in the grid total
    int validChar;      // # of char in the grid that are part of words
    int generationTime; // time in milliseconds taken to generate the grid

    public void GridGenerator(){

    }

    public void generateGrid(int row, int column){

    }

    public float calGridDensity(float charNum, float validChar){
        return gridDensity;
    }

    public void checkWordOverlap(){

    }

    public void verifyWord(String word){

    }

    public void tick(){
        
    }
>>>>>>> ff1586ea933f89d90094c5b76017bcadeba40f43

    public void verifyWord(String word) {}
}