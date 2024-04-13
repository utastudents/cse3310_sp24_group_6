package uta.cse3310;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;
import java.lang.String;
import java.util.*;

public class WordBank {
    private Vector<String> totalList = new Vector<>();
    private Vector<Word> WordList = new Vector<>();
    private Vector<Word> foundWords = new Vector<>();
    private List<Integer> elements = new ArrayList<>();
    private Random rng = new Random();

    public WordBank() {
        try {
            File file = new File("words.txt");
            Scanner scan = new Scanner(file);
            while(scan.hasNextLine()) {
                String s = scan.nextLine();
                if(s.length() > 4) {  // Filtered to include the words greater than 4 letters
                    totalList.add(s);
                }
            }
            scan.close();
        } catch (Exception e) {
            e.printStackTrace();  // Will print the stack trace if there's an exception
        }
    }

    public void add(Word newWord) {
        foundWords.add(newWord);  // Adds new word to the found words
    }

    public Vector<Word> getWordList() {
        return WordList;  // Returns the word list
    }

    public Vector<Word> getFoundWords() {
        return foundWords;  // Returns the found words list
    }

    public void printWordList() {
        for(Word w : WordList) {
            System.out.println(w);
        }
    }

    public void generateWords() {
        Collections.shuffle(totalList);  // Shuffled to ensure random selection
        elements.clear();  // Clear previous indices to start fresh

        // Ensures that no more than 100 words or the total number of words, whichever is smaller
        int wordsToGenerate = Math.min(100, totalList.size());

        for (int i = 0; i < wordsToGenerate; i++) {
            int index = rng.nextInt(totalList.size());

            if (!elements.contains(index)) {
                elements.add(index);  // Mark this index as used
                String selectedWord = totalList.get(index);
                Word newWord = new Word(selectedWord);

                WordType type = WordType.values()[rng.nextInt(WordType.values().length)];
                newWord.setWordType(type);

                // Random starting coordinates within a presumed grid size
                int startX = rng.nextInt(50);  // Assuming grid size of 10x10 for example
                int startY = rng.nextInt(50);

                // Calculate ending coordinates based on word type and length
                int[] endCoords = calculateEndCoordinates(startX, startY, type, selectedWord.length());
                if (isValidPosition(endCoords[0], endCoords[1])) {
                    newWord.setCoord1(startX, startY);
                    newWord.setCoord2(endCoords[0], endCoords[1]);
                    WordList.add(newWord);
                } else {
                    // If the word doesn't fit, remove the index and decrement i to retry
                    elements.remove(elements.size() - 1);
                    i--;
                }
            } else {
                //If the index was already used, retry the iteration
                i--;
            }
        }
    }

    private int[] calculateEndCoordinates(int startX, int startY, WordType type, int length) {
        int endX = startX, endY = startY;
        switch (type) {
            case horizontal:
            case bHorizontal:
                endX += (type == WordType.horizontal ? length - 1 : -(length - 1));
                break;
            case vertical:
            case bVertical:
                endY += (type == WordType.vertical ? length - 1 : -(length - 1));
                break;
            case topLeftBottomRight:
            case bottomRightTopLeft:
                endX += (type == WordType.topLeftBottomRight ? length - 1 : -(length - 1));
                endY += (type == WordType.topLeftBottomRight ? length - 1 : -(length - 1));
                break;
            case topRightBottomLeft:
            case bottomLeftTopRight:
                endX += (type == WordType.topRightBottomLeft ? -(length - 1) : length - 1);
                endY += (type == WordType.topRightBottomLeft ? length - 1 : -(length - 1));
                break;
        }
        return new int[]{endX, endY};
    }

    private boolean isValidPosition(int x, int y) {
        // Assuming grid size is 50x50
        return x >= 0 && x < 50 && y >= 0 && y < 50;
    }
}


/*
 * things to be done for generator:
 * - Create a random coordinate assignment 
 * - coorisponding type assignment
 * - optimizations to the code.
 */
