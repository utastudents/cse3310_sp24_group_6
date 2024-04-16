package uta.cse3310;
import junit.framework.TestCase;
import uta.cse3310.Player;
import junit.framework.Assert;
import java.util.Vector;

public class PlayerTest extends TestCase {
    public void testPlayer() {
        boolean bool = false;
        Vector<Player> PlayerList = new Vector<>();

        Player p1 = new Player("P1", 1);
        Player p2 = new Player("P2", 2);
        Player p3 = new Player("P3", 3);
        Player p4 = new Player("P4", 4);
        PlayerList.add(p1);
        PlayerList.add(p2);
        PlayerList.add(p3);
        PlayerList.add(p4);

        System.out.println("\nPlayer Unit Test");
        System.out.println("----------------\n");

        //checkUnique tests
        Player p5 = new Player("P5", 1);
        bool = p5.checkUnique(PlayerList);
        assertTrue(bool);
        if(bool == true) {
            System.out.println("checkUnique return true success\n");
        }

        bool = false;

        Player p6 = new Player("P5", 1);
        bool = p6.checkUnique(PlayerList);
        if(bool == false) {
            System.out.println("checkUniquepin  return false success\n");
        }   

        bool = false;

        //savePin tests
        //TEST 1: Valid Pin
        bool = p1.savePin(1234);
        assertTrue(bool);
        if(bool == true) {
            System.out.println("Test 1: savePin normal success\n");
        }

        bool = false;

        //TEST 2: Invalid Length
        bool = p1.savePin(124);
        assertFalse(bool);
        if(bool == false) {
            System.out.println("Test 2a: savePin invalid length (too short) success\n");
        }

        bool = false;

        bool = p1.savePin(12345);
        assertFalse(bool);
        if(bool == false) {
            System.out.println("Test 2b: savePin invalid length (too long) success\n");
        }
    }   
}