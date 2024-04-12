package uta.cse3310;

public class Word {
    private int coord1[];
    private int coord2[];
    private WordType type;
    private String word;

    // Constructor
    public Word(String word) {
        this.word = word;
        this.coord1 = new int[2];
        this.coord2 = new int[2];
    }

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

    public void setCoord1(int start, int end) {
        this.coord1[0] = start;
        this.coord1[1] = end;
    }
     
    public void setCoord2(int start, int end) {
        this.coord2[0] = start;
        this.coord2[1] = end;
    }

    public void setWordType(WordType type) {
        this.type = type;
    }

    public int length() {
        return this.word.length();
    }

    public String getLetter(int i) {
        char c = this.word.charAt(i);
        String s = String.valueOf(c);
        return s;
    }

    @Override

    public String toString() {
        return this.word;
    }
}
