package uta.cse3310;

import java.util.*;

public class WordBank
{
   private Vector<Word> wordList = new Vector<>();
   private Vector<Word> foundWords = new Vector<>();

   public WordBank() {}

   public Vector<Word> getWordList() {
      return wordList;
   }

   public Vector<Word> getFoundWords() {
      return foundWords;
   }
   
   public void generateWords(Vector<Word> wordList) {}
}
