package uta.cse3310;
import java.util.Arrays;

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

    public void setCoord1(int row, int column) {
        this.coord1[0] = row;
        this.coord1[1] = column;
    }
     
    public void setCoord2(int row, int column) {
        this.coord2[0] = row;
        this.coord2[1] = column;
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

    public int[] commonLetter(Word w) {
        int result[] = new int[2];
        result[0] = -1;
        result[1] = -1;
        for(int i = 0; i < this.word.length(); i++) {
            for(int j = 0; j < w.length(); j++) {
                if(this.word.charAt(i) == w.getWord().charAt(j)) {
                    result[0] = i;
                    result[1] = j;
                    return result;
                }
            }
        }
        return result;
    }

    @Override

    public String toString() {
        return this.word + Arrays.toString(coord1) + Arrays.toString(coord2) + this.type.name();
    }
}
