package uta.cse3310;

public class Word {
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

    // Might include additional methods as necessary
}
