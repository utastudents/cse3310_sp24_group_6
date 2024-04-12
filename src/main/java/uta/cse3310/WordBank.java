package uta.cse3310;

import java.util.Scanner;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;
import java.util.*;

public class WordBank
{
   List<Integer> elements = new ArrayList<>();
   Vector<String> totalList = new Vector<>();
   Vector<Word> WordList = new Vector<>();
   Vector<Word> foundWords = new Vector<>();
   int numwords;

   public WordBank()
   {
      // Checking the amount of lines in a file
       // Amount of words 
      try { 
         File file = new File("words.txt");
         Scanner scan = new Scanner(file);

         while(scan.hasNextLine()) {
            totalList.add(scan.nextLine());
            numwords++;
         }
         scan.close();
      }
      catch (Exception e) {
         e.getStackTrace();
      }
   } 
   
   public void add(Word newWord)
   {
      this.foundWords.add(newWord);   // adds new word to the foundwords
   }

   public Vector<Word> getWordList()
   {
      return WordList;  // returns the word list
   }
   
   public Vector<Word> getFoundWords()
   {
      return foundWords;   // returns the foundwords list
   }
   
   public void generateWords(Vector<Word> wordList)
   {
      Random rng = new Random();

      // Generate 20 words from the total list
      for(int i = 0; i < 20; i++) {
         int rand = rng.nextInt(numwords);

         for(int j = 0; j < elements.size(); j++) {
            if(elements.contains(rand))
            {
               rand = rng.nextInt(numwords);
               j = 0;
            }
         }

         wordList.add(new Word(0, 0, totalList.get(rand)));
      }
   }
}
