package uta.cse3310;
import java.util.Vector;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;
import java.lang.Math;

public class GridGenerator {

    private double maxRow;
    private double maxColumn;
    private String grid[][];
    private Vector<Word> placedWords = new Vector<>();
    private double density;
    private List<Integer> elements = new ArrayList<>();
    private double[] gridOrient = new double[8];
    private double[] fillerLetters = new double [26];
    private double genTime;
    private double gridDensity;

    public GridGenerator(int row, int column) {
        this.maxRow = row;
        this.maxColumn = column;
        this.grid = new String[row][column];
        //Fill grid with "-", treated as blank space, helpful for spotting words in testing
        for(int i = 0; i < 50; i++) {
            for(int j = 0; j < 50; j++) {
                this.grid[i][j] = "-";
            }
        }
    }
    
    public Vector<Word> generateGrid(Vector<String> wordList) {
        Random r = new Random();
        double timeStart = System.nanoTime();
        int result;
        int orientation = 0;
        Arrays.fill(gridOrient, 0);
        Arrays.fill(fillerLetters, 0);

        while(this.getDensity() < 0.67) {
            result = -1;
            int rand = r.nextInt(wordList.size());                     // create a random word 

            for(int i = 0; i < this.elements.size(); i++) {
                if(this.elements.contains(rand)) {                        // if an element already exists
                    rand = r.nextInt(wordList.size());                   // run the random generator again
                    i = 0;                                        // set the increment back to 0 and run the for loop again
                }
            }

            this.elements.add(rand);
            Word word = new Word(wordList.elementAt(rand));

            orientation++;
            if(orientation > 8) {
                orientation = 1;
            }
            
            if(orientation == 1) {
                result = horizontal(word, r, placedWords);
            } else if(orientation == 2) {
                result = bHorizontal(word, r, placedWords);
            } else if(orientation == 3) {
                result = vertical(word, r, placedWords);
            } else if(orientation == 4) {
                result = bVertical(word, r, placedWords);
            } else if(orientation == 5) {
                result = topLeftBottomRight(word, r);
            } else if(orientation == 6) {
                result = topRightBottomLeft(word, r);
            } else if(orientation == 7) {
                result = bottomLeftTopRight(word, r);
            } else if(orientation == 8) {
                result = bottomRightTopLeft(word, r);
            }
            if(result == 1) {
                this.placedWords.add(word);
                this.gridOrient[orientation - 1]++;
            }
        }

        this.gridDensity = this.getDensity();

        int rand = 0;
        for(int i = 0; i < 50; i++) {
            for(int j = 0; j < 50; j++) {
                if(this.grid[i][j].equals("-")) {
                    boolean loop = false;
                    int failsafe = 0;
                    do {
                        rand = r.nextInt(26) + 97;
                        if(this.fillerLetters[rand - 97] >= arrMax(this.fillerLetters)) {
                            loop = true;
                            failsafe++;
                        }
                        if(failsafe > 25) {
                            loop = false;
                            failsafe = 0;
                        }
                    }
                    while(loop);
                    this.grid[i][j] = Character.toString(rand);
                    double temp = this.fillerLetters[rand - 97];
                    this.fillerLetters[rand - 97] = temp + 1;
                }
            }
        }

        double timeEnd = System.nanoTime();
        this.genTime = (timeEnd - timeStart) / 1000000000;
        return this.placedWords;
    }

    public double getGridDensity() {
        return this.gridDensity;
    }

    public double getGenTime() {
        return this.genTime;
    }

    public double arrMax(double[] input) {
        double max = 0;
        double failsafe = 0;
        for(int i = 0; i < input.length; i++) {
            if(input[i] > max) {
                max = input[i];
                failsafe++;
            }
        }
        if(failsafe >= 25) {
            return 100000;
        }
        return max;
    }

    public double standardDeviation(double mean) {
        double sum = 0;
        for(int i = 0; i < 26; i++) {
            sum += ((this.fillerLetters[i] - mean) * (this.fillerLetters[i] - mean));
        }
        return Math.sqrt(sum / 26);
    }

    public void printGrid() {
        for(int i = 0; i < this.maxRow; i++) {
            for(int j = 0; j < this.maxColumn; j++) {
                System.out.print(this.grid[i][j] + " ");
            }
            System.out.println("");
        }
    }

    public double fillerMean() {
        double sum = 0;
        for(int i = 0; i < 26; i++) {
            sum += this.fillerLetters[i];
        }
        return sum / 26;
    }

    public double[] getOrientation() {
        double[] result = new double[8];
        result[0] = this.gridOrient[0] / this.placedWords.size();
        result[1] = this.gridOrient[1] / this.placedWords.size();
        result[2] = this.gridOrient[2] / this.placedWords.size();
        result[3] = this.gridOrient[3] / this.placedWords.size();
        result[4] = this.gridOrient[4] / this.placedWords.size();
        result[5] = this.gridOrient[5] / this.placedWords.size();
        result[6] = this.gridOrient[6] / this.placedWords.size();
        result[7] = this.gridOrient[7] / this.placedWords.size();
        return result;
    }

    private double getDensity() {
        double letters = 0;
        for(int i = 0; i < this.maxRow; i++) {
            for(int j = 0; j < this.maxColumn; j++) {
                if(!(this.grid[i][j].equals("-"))) {
                    letters++;
                }
            }
        }
        return (letters / 2500);
    }

    private int[] findCoords(Word word, int letter) {
        int arr[] = new int[2];
        if(word.getWordType() == WordType.horizontal) {
            int temp[] = new int[2];
            temp = word.getCoord1();
            arr[0] = temp[0];
            arr[1] = temp[1] + letter;
        } else if(word.getWordType() == WordType.bHorizontal) {
            int temp[] = new int[2];
            temp = word.getCoord1();
            arr[0] = temp[0];
            arr[1] = temp[1] - letter;
        } else if(word.getWordType() == WordType.vertical) {
            int temp[] = new int[2];
            temp = word.getCoord1();
            arr[0] = temp[0] + letter;
            arr[1] = temp[1];
        } else if(word.getWordType() == WordType.bVertical) {
            int temp[] = new int[2];
            temp = word.getCoord1();
            arr[0] = temp[0] - letter;
            arr[1] = temp[1];
        } else if(word.getWordType() == WordType.topLeftBottomRight) {
            int temp[] = new int[2];
            temp = word.getCoord1();
            arr[0] = temp[0] + letter;
            arr[1] = temp[1] + letter;
        } else if(word.getWordType() == WordType.topRightBottomLeft) {
            int temp[] = new int[2];
            temp = word.getCoord1();
            arr[0] = temp[0] + letter;
            arr[1] = temp[1] - letter;
        } else if(word.getWordType() == WordType.bottomLeftTopRight) {
            int temp[] = new int[2];
            temp = word.getCoord1();
            arr[0] = temp[0] - letter;
            arr[1] = temp[1] + letter;
        } else if(word.getWordType() == WordType.bottomRightTopLeft) {
            int temp[] = new int[2];
            temp = word.getCoord1();
            arr[0] = temp[0] - letter;
            arr[1] = temp[1] - letter;
        }
        return arr;
    }

    private int horizontal(Word word, Random r, Vector<Word> placedWords) {
        int row = -1;
        int column = -1;
        //controls do while
        boolean loop = false;
        //for indexing placedWords
        int p = 0;
        //50/50 to force intersection
        int intersect = r.nextInt(2) + 1;
        int failsafe = 0;
        
        //Do while forces one loop
        do {
            loop = false;
            //forced intersection logic
            if(intersect == 1) {
                if(p == placedWords.size()) {
                    //if no matches in placedWords, get random coords
                    intersect = 2;
                } else {
                    //find common letter, letter[0] is letter in word, letter[1] is letter in placedWords[p]
                    p++;
                    if(placedWords.elementAt(p - 1).getWordType() != WordType.horizontal) {
                        int letter[] = new int[2];
                        letter = word.commonLetter(placedWords.elementAt(p - 1));
                        if(letter[0] != -1 && letter[1] != -1) {
                            int coords[] = new int[2];
                            coords = findCoords(placedWords.elementAt(p - 1), letter[1]);
                            row = coords[0];
                            column = coords[1] - letter[0];
                            if(column < 0 || column > (49 - word.length())) {
                                row = -1;
                                column = -1;
                            }
                        }
                    }
                }
            }
            //Random coords logic
            //Get random coords with respect to length of words so it doesn't go out of bounds
            if(intersect == 2) {
                if(failsafe > 50) {
                    return -1;
                }
                failsafe++;
                row = r.nextInt(50);
                column = r.nextInt(50 - word.length());
            }

            //Check path of word, if a space isn't blank/the correct letter, make new coords and try again
            if(row != -1 && column != -1) {
                for(int i = 0; i < word.length(); i++) {
                    if(!(this.grid[row][column + i].equals("-")) && !(this.grid[row][column + i].equals(String.valueOf(word.getLetter(i))))) {
                        //System.out.println(this.grid[row][column + i] + " " + word.getLetter(i));
                        loop = true;
                        break;
                    }
                }
            } else {
                loop = true;
            }
        } while(loop);
        
        //After path is verified, fill it
        for(int i = 0; i < word.length(); i++) {
            this.grid[row][column + i] = word.getLetter(i);
        }
        word.setCoord1(row, column);
        word.setCoord2(row, column + word.length());
        word.setWordType(WordType.horizontal);
        return 1;
    }

    private int bHorizontal(Word word, Random r, Vector<Word> placedWords) {
        int row = -1;
        int column = -1;
        boolean loop = false;
        int p = 0;
        int intersect = r.nextInt(2) + 1;
        int failsafe = 0;
        
        do {
            loop = false;
            if(intersect == 1) {
                if(p == placedWords.size()) {
                    //if no matches in placedWords, get random coords
                    intersect = 2;
                } else {
                    //find common letter, letter[0] is letter in word, letter[1] is letter in placedWords[p]
                    p++;
                    if(placedWords.elementAt(p - 1).getWordType() != WordType.bHorizontal) {
                        int letter[] = new int[2];
                        letter = word.commonLetter(placedWords.elementAt(p - 1));
                        if(letter[0] != -1 && letter[1] != -1) {
                            int coords[] = new int[2];
                            coords = findCoords(placedWords.elementAt(p - 1), letter[1]);
                            row = coords[0];
                            column = coords[1] + letter[0];
                            if(column < (0 + word.length()) || column > 49) {
                                row = -1;
                                column = -1;
                            }
                        }
                    }
                }
            }

            if(intersect == 2) {
                if(failsafe > 50) {
                    return -1;
                }
                failsafe++;
                row = r.nextInt(50);
                column = r.nextInt(50 - word.length()) + word.length();
            }
            
            if(row != -1 && column != -1) {
                for(int i = 0; i < word.length(); i++) {
                    if(!(this.grid[row][column - i].equals("-")) && !(this.grid[row][column - i].equals(String.valueOf(word.getLetter(i))))) {
                        loop = true;
                        break;
                    }
                }
            } else {
                loop = true;
            }
        } while(loop);

        for(int i = 0; i < word.length(); i++) {
            this.grid[row][column - i] = word.getLetter(i);
        }
        word.setCoord1(row, column);
        word.setCoord2(row, column - word.length());
        word.setWordType(WordType.bHorizontal);
        return 1;
    }

    private int vertical(Word word, Random r, Vector<Word> placedWords) {
        int row = -1;
        int column = -1;
        boolean loop = false;
        int p = 0;
        int intersect = r.nextInt(2) + 1;
        int failsafe = 0;
        
        do {
            loop = false;
            if(intersect == 1) {
                if(p == placedWords.size()) {
                    //if no matches in placedWords, get random coords
                    intersect = 2;
                } else {
                    //find common letter, letter[0] is letter in word, letter[1] is letter in placedWords[p]
                    p++;
                    if(placedWords.elementAt(p - 1).getWordType() != WordType.vertical) {
                        int letter[] = new int[2];
                        letter = word.commonLetter(placedWords.elementAt(p - 1));
                        if(letter[0] != -1 && letter[1] != -1) {
                            int coords[] = new int[2];
                            coords = findCoords(placedWords.elementAt(p - 1), letter[1]);
                            row = coords[0] - letter[0];
                            column = coords[1];
                            if(row < 0 || row > (49 - word.length())) {
                                row = -1;
                                column = -1;
                            }
                        }
                    }
                }
            }

            if(intersect == 2) {
                if(failsafe > 50) {
                    return -1;
                }
                failsafe++;
                row = r.nextInt(50 - word.length());
                column = r.nextInt(50);
            }

            if(row != -1 && column != -1) {
                for(int i = 0; i < word.length(); i++) {
                    if(!(this.grid[row + i][column].equals("-")) && !(this.grid[row + i][column].equals(String.valueOf(word.getLetter(i))))) {
                        loop = true;
                        break;
                    }
                }
            } else {
                loop = true;
            }
        } while(loop);

        for(int i = 0; i < word.length(); i++) {
            this.grid[row + i][column] = word.getLetter(i);
        }
        word.setCoord1(row, column);
        word.setCoord2(row + word.length(), column);
        word.setWordType(WordType.vertical);
        return 1;
    }

    private int bVertical(Word word, Random r, Vector<Word> placedWords) {
        int row = -1;
        int column = -1;
        boolean loop = false;
        int p = 0;
        int intersect = r.nextInt(2) + 1;
        int failsafe = 0;
        
        do {
            loop = false;
            if(intersect == 1) {
                if(p == placedWords.size()) {
                    //if no matches in placedWords, get random coords
                    intersect = 2;
                } else {
                    //find common letter, letter[0] is letter in word, letter[1] is letter in placedWords[p]
                    p++;
                    if(placedWords.elementAt(p - 1).getWordType() != WordType.vertical) {
                        int letter[] = new int[2];
                        letter = word.commonLetter(placedWords.elementAt(p - 1));
                        if(letter[0] != -1 && letter[1] != -1) {
                            int coords[] = new int[2];
                            coords = findCoords(placedWords.elementAt(p - 1), letter[1]);
                            //change
                            row = coords[0] + letter[0];
                            column = coords[1];
                            //change
                            if(row < (0 + word.length()) || row > 49) {
                                row = -1;
                                column = -1;
                            }
                        }
                    }
                }
            }
            
            if(intersect == 2) {
                if(failsafe > 50) {
                    return -1;
                }
                failsafe++;
                row = r.nextInt(50 - word.length()) + word.length();
                column = r.nextInt(50);
            }

            if(row != -1 && column != -1) {
                for(int i = 0; i < word.length(); i++) {
                    if(!(this.grid[row - i][column].equals("-")) && !(this.grid[row - i][column].equals(String.valueOf(word.getLetter(i))))) {
                        loop = true;
                        break;
                    }
                }
            } else {
                loop = true;
            }
        } while(loop);
        
        for(int i = 0; i < word.length(); i++) {
            this.grid[row - i][column] = word.getLetter(i);
        }
        word.setCoord1(row, column);
        word.setCoord2(row - word.length(), column);
        word.setWordType(WordType.bVertical);
        return 1;
    }

    private int topLeftBottomRight(Word word, Random r) {
        int row = -1; 
        int column = -1;
        boolean loop = false;
        int p = 0;
        int intersect = r.nextInt(2) + 1;
        int failsafe = 0;
        
        do {
            loop = false;
            if(intersect == 1) {
                if(p == placedWords.size()) {
                    //if no matches in placedWords, get random coords
                    intersect = 2;
                } else {
                    //find common letter, letter[0] is letter in word, letter[1] is letter in placedWords[p]
                    p++;
                    if(placedWords.elementAt(p - 1).getWordType() != WordType.vertical) {
                        int letter[] = new int[2];
                        letter = word.commonLetter(placedWords.elementAt(p - 1));
                        if(letter[0] != -1 && letter[1] != -1) {
                            int coords[] = new int[2];
                            coords = findCoords(placedWords.elementAt(p - 1), letter[1]);
                            //change
                            row = coords[0] - letter[0];
                            column = coords[1] - letter[0];
                            //change
                            if(row < 0 || row > (49 - word.length())) {
                                row = -1;
                                column = -1;
                            }
                            if(column < 0 || column > (49 - word.length())) {
                                row = -1;
                                column = -1;
                            }
                        }
                    }
                }
            }
            if(intersect == 2) {
                if(failsafe > 50) {
                    return -1;
                }
                failsafe++;
                row = r.nextInt(50 - word.length());
                column = r.nextInt(50 - word.length());
            }

            if(row != -1 && column != -1) {
                for(int i = 0; i < word.length(); i++) {
                    if(!(this.grid[row + i][column + i].equals("-")) && !(this.grid[row + i][column + i].equals(String.valueOf(word.getLetter(i))))) {
                        loop = true;
                        break;
                    }
                }
            } else {
                loop = true;
            }
        } while(loop);

        for(int i = 0; i < word.length(); i++) {
            this.grid[row + i][column + i] = word.getLetter(i);
        }
        word.setCoord1(row, column);
        word.setCoord2(row + word.length(), column + word.length());
        word.setWordType(WordType.topLeftBottomRight);
        return 1;
    }

    private int topRightBottomLeft(Word word, Random r) {
        int row = -1; 
        int column = -1;
        boolean loop = false;
        int p = 0;
        int intersect = r.nextInt(2) + 1;
        int failsafe = 0;
        
        do {
            loop = false;
            if(intersect == 1) {
                if(p == placedWords.size()) {
                    //if no matches in placedWords, get random coords
                    intersect = 2;
                } else {
                    //find common letter, letter[0] is letter in word, letter[1] is letter in placedWords[p]
                    p++;
                    if(placedWords.elementAt(p - 1).getWordType() != WordType.vertical) {
                        int letter[] = new int[2];
                        letter = word.commonLetter(placedWords.elementAt(p - 1));
                        if(letter[0] != -1 && letter[1] != -1) {
                            int coords[] = new int[2];
                            coords = findCoords(placedWords.elementAt(p - 1), letter[1]);
                            //change
                            row = coords[0] - letter[0];
                            column = coords[1] + letter[0];
                            //change
                            if(row < 0 || row > (49 - word.length())) {
                                row = -1;
                                column = -1;
                            }
                            if(column < (0 + word.length()) || column > 49) {
                                row = -1;
                                column = -1;
                            }
                        }
                    }
                }
            }
            
            if(intersect == 2) {
                if(failsafe > 50) {
                    return -1;
                }
                failsafe++;
                row = r.nextInt(50 - word.length());
                column = r.nextInt(50 - word.length()) + word.length();
            }

            if(row != -1 && column != -1) {
                for(int i = 0; i < word.length(); i++) {
                    if(!(this.grid[row + i][column - i].equals("-")) && !(this.grid[row + i][column - i].equals(String.valueOf(word.getLetter(i))))) {
                        loop = true;
                        break;
                    }
                }
            } else {
                loop = true;
            }
        } while(loop);

        for(int i = 0; i < word.length(); i++) {
            this.grid[row + i][column - i] = word.getLetter(i);
        }
        word.setCoord1(row, column);
        word.setCoord2(row + word.length(), column - word.length());
        word.setWordType(WordType.topRightBottomLeft);
        return 1;
    }

    private int bottomLeftTopRight(Word word, Random r) {
        int row = -1;
        int column = -1;
        boolean loop = false;
        int p = 0;
        int intersect = r.nextInt(2) + 1;
        int failsafe = 0;
        
        do {
            loop = false;
            if(intersect == 1) {
                loop = false;
                if(p == placedWords.size()) {
                    //if no matches in placedWords, get random coords
                    intersect = 2;
                } else {
                    //find common letter, letter[0] is letter in word, letter[1] is letter in placedWords[p]
                    p++;
                    if(placedWords.elementAt(p - 1).getWordType() != WordType.vertical) {
                        int letter[] = new int[2];
                        letter = word.commonLetter(placedWords.elementAt(p - 1));
                        if(letter[0] != -1 && letter[1] != -1) {
                            int coords[] = new int[2];
                            coords = findCoords(placedWords.elementAt(p - 1), letter[1]);
                            //change
                            row = coords[0] + letter[0];
                            column = coords[1] - letter[0];
                            //change
                            if(row < (0 + word.length()) || row > 49) {
                                row = -1;
                                column = -1;
                            }
                            if(column < 0 || column > (49 - word.length())) {
                                row = -1;
                                column = -1;
                            }
                        }
                    }
                }
            }
            
            if(intersect == 2) {
                if(failsafe > 50) {
                    return -1;
                }
                failsafe++;
                row = r.nextInt(50 - word.length()) + word.length();
                column = r.nextInt(50 - word.length());
            }
            

            if(row != -1 && column != -1) {
                for(int i = 0; i < word.length(); i++) {
                    if(!(this.grid[row - i][column + i].equals("-")) && !(this.grid[row - i][column + i].equals(String.valueOf(word.getLetter(i))))) {
                        loop = true;
                        break;
                    }
                }
            } else {
                loop = true;
            }
        } while(loop);

        for(int i = 0; i < word.length(); i++) {
            this.grid[row - i][column + i] = word.getLetter(i);
        }
        word.setCoord1(row, column);
        word.setCoord2(row - word.length(), column + word.length());
        word.setWordType(WordType.bottomLeftTopRight);
        return 1;
    }

    private int bottomRightTopLeft(Word word, Random r) {
        int row = -1;
        int column = -1;
        boolean loop = false;
        int p = 0;
        int intersect = r.nextInt(2) + 1;
        int failsafe = 0;

        do {
            loop = false;
            if(intersect == 1) {
                if(p == placedWords.size()) {
                    //if no matches in placedWords, get random coords
                    intersect = 2;
                } else {
                    //find common letter, letter[0] is letter in word, letter[1] is letter in placedWords[p]
                    p++;
                    if(placedWords.elementAt(p - 1).getWordType() != WordType.vertical) {
                        int letter[] = new int[2];
                        letter = word.commonLetter(placedWords.elementAt(p - 1));
                        if(letter[0] != -1 && letter[1] != -1) {
                            int coords[] = new int[2];
                            coords = findCoords(placedWords.elementAt(p - 1), letter[1]);
                            //change
                            row = coords[0] + letter[0];
                            column = coords[1] + letter[0];
                            //change
                            if(row < (0 + word.length()) || row > 49) {
                                row = -1;
                                column = -1;
                            }
                            if(column < (0 + word.length()) || column > 49) {
                                row = -1;
                                column = -1;
                            }
                        }
                    }
                }
            }

            if(intersect == 2) {
                if(failsafe > 50) {
                    return -1;
                }
                failsafe++;
                row = r.nextInt(50 - word.length()) + word.length();
                column = r.nextInt(50 - word.length()) + word.length();
            }

            if(row != -1 && column != -1) {
                for(int i = 0; i < word.length(); i++) {
                    if(!(this.grid[row - i][column - i].equals("-")) && !(this.grid[row - i][column - i].equals(String.valueOf(word.getLetter(i))))) {
                        loop = true;
                        break;
                    }
                }
            } else {
                loop = true;
            }
        } while(loop);

        for(int i = 0; i < word.length(); i++) {
            this.grid[row - i][column - i] = word.getLetter(i);
        }
        word.setCoord1(row, column);
        word.setCoord2(row - word.length(), column - word.length());
        word.setWordType(WordType.bottomRightTopLeft);
        return 1;
    }
}