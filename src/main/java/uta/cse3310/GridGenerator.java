package uta.cse3310;
import java.util.Vector;
import java.util.Random;
import java.util.concurrent.TimeUnit;

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
            int orientation = r.nextInt(8) + 1;
            
            if(orientation == 1) {
                horizontal(word, r, placedWords);
            } else if(orientation == 2) {
                bHorizontal(word, r, placedWords);
            } else if(orientation == 3) {
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

    private void horizontal(Word word, Random r, Vector<Word> placedWords) {
        int row;
        int column;
        boolean loop = false;

        //67% chance to force collision
        /*if(r.nextInt(3) + 1 < 3) {
            if(placedWords.size() == 0) {
                break;
            }
            for(int i = 0; i < placedWords.size(); i++) {
                if(placedWords[i].getWordType() != WordType.horizontal) {
                    int result[2] = word.commonLetter(placedWords[i]);
                    if(result[0] != -1 && result[1] != -1) {
                        
                    }
                }
            }
        }*/
        
        //Do while forces one loop
        do {
            //Get random coords with respect to length of words so it doesn't go out of bounds
            row = r.nextInt(50);
            column = r.nextInt(50 - word.length());
            loop = false;

            //Check path of word, if a space isn't blank/the correct letter, make new coords and try again
            for(int i =0; i < word.length(); i++) {
                if(this.grid[row][column + i] != "-" && this.grid[row][column + i] != word.getLetter(i)) {
                    loop = true;
                    break;
                }
            }
        } while(loop);
        
        //After path is verified, fill it
        for(int i = 0; i < word.length(); i++) {
            this.grid[row][column + i] = word.getLetter(i);
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

    public boolean checkWordOverlap() {
        return true;
    }
}