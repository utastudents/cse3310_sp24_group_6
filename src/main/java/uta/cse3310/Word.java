package uta.cse3310;

public class Word {
<<<<<<< HEAD
    private int Xval;
    private int Yval;
    private WordType type;

    // Constructor
    public Word(int Xval, int Yval, WordType type) {
        /*this.Xval = Xval;
        this.Yval = Yval;
        this.type = type;*/
    }

    // Getters and Setters
    public int getXval() {
        return Xval;
    }

    public void setXval(int Xval) {
        /*this.Xval = Xval;*/
    }

    public int getYval() {
        return Yval;
    }

    public void setYval(int Yval) {
        /*this.Yval = Yval;*/
    }

    public WordType getType() {
        return type;
    }

    public void setType(WordType type) {
        /*this.type = type;*/
    }

    // Might include additional methods as necessary
=======
    private int coord1[];
    private int coord2[];
    private WordType type;
    private String word;

    // Constructor
    public Word(int coord1[], int coord2[], WordType type, String word) {}

    public int[] getCoord1() {
        return coord1;
    }

    public int[] getCoord2() {
        return coord2;
    }

    public WordType getWordType() {
        return type;
    }

    public String getWord() {
        return word;
    }

    public String read() {
        return "";
    }
>>>>>>> David_Branch
}
