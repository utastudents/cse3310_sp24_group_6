package uta.cse3310;
import java.util.Vector;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.Arrays;

public class GridGenerator {

    private float maxRow;
    private float maxColumn;
    private String grid[][];
    private Vector<Word> placedWords = new Vector<>();

    public GridGenerator() {
        this.maxRow = 50;
        this.maxColumn = 50;
        this.grid = new String[50][50];
        //Fill grid with "-", treated as blank space, helpful for spotting words in testing
        for(int i = 0; i < 50; i++) {
            for(int j = 0; j < 50; j++) {
                this.grid[i][j] = "-";
            }
        }
    }
    
    public void generateGrid(Vector<Word> wordList) {
        Random r = new Random();
        long start = System.nanoTime();

        for(Word word: wordList) {
            int orientation = r.nextInt(2) + 1;
            
            if(orientation == 1) {
                horizontal(word, r, placedWords);
            } else if(orientation == 3) {
                bHorizontal(word, r, placedWords);
            } else if(orientation == 2) {
                vertical(word, r, placedWords);
            } else if(orientation == 4) {
                bVertical(word, r, placedWords);
            } else if(orientation == 5) {
                topLeftBottomRight(word, r);
            } else if(orientation == 6) {
                topRightBottomLeft(word, r);
            } else if(orientation == 7) {
                bottomLeftTopRight(word, r);
            } else if(orientation == 8) {
                bottomRightTopLeft(word, r);
            }
            placedWords.add(word);
        }

        //Fills all blank spaces with random letters, commented out so test grids are easier to read

        /*for(int i = 0; i < 50; i++) {
            for(int j = 0; j < 50; j++) {
                if(this.grid[i][j] == "0") {
                    this.grid[i][j] = Character.toString(r.nextInt(26) + 65);
                }
            }
        }*/
        long end = System.nanoTime();
        long time = end - start;
        this.printGrid(start, end);
    }

    public void printGrid(long start, long end) {
        for(int i = 0; i < 50; i++) {
            for(int j = 0; j < 50; j++) {
                System.out.print(this.grid[i][j] + " ");
            }
            System.out.println("");
        }
        double time = ((double) end - (double) start) / 1000000000;
        System.out.println(time + " seconds");
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

    private void horizontal(Word word, Random r, Vector<Word> placedWords) {
        int row = -1;
        int column = -1;
        //for indexing placedWords
        int p = 0;
        //controls do while
        boolean loop = false;
        //50/50 to force intersection
        int intersect = 1;
        
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
                            if(column < 0 || column > (50 - word.length())) {
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
        if(intersect == 1) {
            System.out.println("Success!!!");
        }
        word.setCoord1(row, column);
        word.setCoord2(row, column + word.length());
        word.setWordType(WordType.horizontal);
    }

    private void bHorizontal(Word word, Random r, Vector<Word> placedWords) {
        int row;
        int column;
        boolean loop = false;
        
        do {
            row = r.nextInt(50);
            column = r.nextInt(50 - word.length()) + word.length();
            loop = false;

            for(int i = 0; i < word.length(); i++) {
                if(this.grid[row][column - i] != "-" && this.grid[row][column - i] != word.getLetter(i)) {
                    loop = true;
                    break;
                }
            }
        } while(loop);

        for(int i = 0; i < word.length(); i++) {
            this.grid[row][column - i] = word.getLetter(i);
        }
        word.setCoord1(row, column);
        word.setCoord2(row, column - word.length());
        word.setWordType(WordType.bHorizontal);
    }

    private void vertical(Word word, Random r, Vector<Word> placedWords) {
        int row;
        int column;
        boolean loop = false;
        
        do {
            row = r.nextInt(50 - word.length());
            column = r.nextInt(50);
            loop = false;
            for(int i = 0; i < word.length(); i++) {
                if(this.grid[row + i][column] != "-" && this.grid[row + i][column] != word.getLetter(i)) {
                    loop = true;
                    break;
                }
            }
        } while(loop);

        for(int i = 0; i < word.length(); i++) {
            this.grid[row + i][column] = word.getLetter(i);
        }
        word.setCoord1(row, column);
        word.setCoord2(row + word.length(), column);
        word.setWordType(WordType.vertical);
    }

    private void bVertical(Word word, Random r, Vector<Word> placedWords) {
        int row;
        int column;
        boolean loop = false;
        
        do {
            row = r.nextInt(50 - word.length()) + word.length();
            column = r.nextInt(50);
            loop = false;
            for(int i = 0; i < word.length(); i++) {
                if(this.grid[row - i][column] != "-" && this.grid[row - i][column] != word.getLetter(i)) {
                    loop = true;
                    break;
                }
            }
        } while(loop);
        
        for(int i = 0; i < word.length(); i++) {
            this.grid[row - i][column] = word.getLetter(i);
        }
        word.setCoord1(row, column);
        word.setCoord2(row - word.length(), column);
        word.setWordType(WordType.bVertical);
    }

    private void topLeftBottomRight(Word word, Random r) {
        int row; 
        int column;
        boolean loop = false;
        
        do {
            row = r.nextInt(50 - word.length());
            column = r.nextInt(50 - word.length());
            loop = false;
            for(int i = 0; i < word.length(); i++) {
                if(this.grid[row + i][column + i] != "-" && this.grid[row + i][column + i] != word.getLetter(i)) {
                    loop = true;
                    break;
                }
            }
        } while(loop);

        for(int i = 0; i < word.length(); i++) {
            this.grid[row + i][column + i] = word.getLetter(i);
        }
        word.setCoord1(row, column);
        word.setCoord2(row + word.length(), column + word.length());
        word.setWordType(WordType.topLeftBottomRight);
    }

    private void topRightBottomLeft(Word word, Random r) {
        int row;
        int column;
        boolean loop = false;
        
        do {
            row = r.nextInt(50 - word.length());
            column = r.nextInt(50 - word.length()) + word.length();
            loop = false;
            for(int i = 0; i < word.length(); i++) {
                if(this.grid[row + i][column - i] != "-" && this.grid[row + i][column - i] != word.getLetter(i)) {
                    loop = true;
                    break;
                }
            }
        } while(loop);

        for(int i = 0; i < word.length(); i++) {
            this.grid[row + i][column - i] = word.getLetter(i);
        }
        word.setCoord1(row, column);
        word.setCoord2(row + word.length(), column - word.length());
        word.setWordType(WordType.topRightBottomLeft);
    }

    private void bottomLeftTopRight(Word word, Random r) {
        int row;
        int column;
        boolean loop = false;
        
        do {
            row = r.nextInt(50 - word.length()) + word.length();
            column = r.nextInt(50 - word.length());
            loop = false;
            for(int i = 0; i < word.length(); i++) {
                if(this.grid[row - i][column + i] != "-" && this.grid[row - i][column + i] != word.getLetter(i)) {
                    loop = true;
                    break;
                }
            }
        } while(loop);

        for(int i = 0; i < word.length(); i++) {
            this.grid[row - i][column + i] = word.getLetter(i);
        }
        word.setCoord1(row, column);
        word.setCoord2(row - word.length(), column + word.length());
        word.setWordType(WordType.bottomLeftTopRight);
    }

    private void bottomRightTopLeft(Word word, Random r) {
        int row;
        int column;
        boolean loop = false;

        do {
            row = r.nextInt(50 - word.length()) + word.length();
            column = r.nextInt(50 - word.length()) + word.length();
            loop = false;
            for(int i = 0; i < word.length(); i++) {
                if(this.grid[row - i][column - i] != "-" && this.grid[row - i][column - i] != word.getLetter(i)) {
                    loop = true;
                    break;
                }
            }
        } while(loop);

        for(int i = 0; i < word.length(); i++) {
            this.grid[row - i][column - i] = word.getLetter(i);
        }
        word.setCoord1(row, column);
        word.setCoord2(row - word.length(), column - word.length());
        word.setWordType(WordType.bottomRightTopLeft);
    }

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

    private boolean checkWordOverlap() {
        return true;
    }
}