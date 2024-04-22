package uta.cse3310;

import java.util.Scanner;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;
import java.util.*;
import java.util.Vector;
import junit.framework.TestCase;
import uta.cse3310.Word;
import uta.cse3310.WordBank;
import junit.framework.Assert;

public class WordBankTest extends TestCase {
    

    public void testWordBank() {
        Vector<Word> WordListex = new Vector<>();
        Vector<Word> WordListSort = new Vector<>();

        System.out.println("\nWordBank Unit Test");
        System.out.println("----------------\n");

        Word wa = new Word("aaaaa");
        Word wb = new Word("bbbbb");
        Word wc = new Word("ccccc");
        Word wd = new Word("ddddd");
        Word we = new Word("eeeee");


        // Check for sort
        WordListex.add(we);
        WordListex.add(wd);
        WordListex.add(wa);
        WordListex.add(wc);
        WordListex.add(wb);

        WordListSort.add(wa);
        WordListSort.add(wb);
        WordListSort.add(wc);
        WordListSort.add(wd);
        WordListSort.add(we);

        WordBank wordbank = new WordBank();
        wordbank.wordSort(WordListex);

        boolean bool = false;

        for(int i = 0; i < 5; i ++)
        {
            if(WordListex.get(i).getWord().equals(WordListSort.get(i).getWord()))
            {
                bool = true;
            }
            else
            {
                bool = false;
            }
        }
        assertTrue(bool);
        if(bool == true) {
            System.out.println("wordSort success\n");
        }

    }
}
