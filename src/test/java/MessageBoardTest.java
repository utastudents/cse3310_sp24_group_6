package uta.cse3310;

import org.junit.Before;
import org.junit.Test;

import junit.framework.TestCase;

import static org.junit.Assert.*;

import java.util.Vector;

public class MessageBoardTest extends TestCase {
    private messageBoard messageBoard;

    public void setUp() {
        messageBoard = new messageBoard();
    }

    public void testAddMessage() {
        String nick = "User1";
        String message = "Hello World";
        messageBoard.addMessage(nick, message);
        Vector<String> messages = messageBoard.getMessages();
        assertTrue("Message should be added to the board", messages.contains(nick + ": " + message));
    }

    public void testGenerateMessage() {
        String systemMessage = "System update";
        messageBoard.generateMessage(systemMessage);
        Vector<String> messages = messageBoard.getMessages();
        assertTrue("System message should be added to the board", messages.contains("System: " + systemMessage));
    }

    public void testReceiveInput() {
        String nick = "User2";
        String message = "Another message";
        MessageBoard.receiveInput(nick, message);
        Vector<String> messages = messageBoard.getMessages();
        assertTrue("Received message should be added to the board", messages.contains(nick + ": " + message));
    }

    public void testEmptyMessageNotAdded() {
        String nick = "User3";
        String message = "    ";  // Intentionally empty message
        messageBoard.receiveInput(nick, message);
        Vector<String> messages = messageBoard.getMessages();
        assertFalse("Empty message should not be added to the board", messages.contains(nick + ": " + message));
    }

    public void testPrintMessages() {
        messageBoard.addMessage("User4", "Test message for print");
        // Not an ideal way to test print, as it involves standard output, but here we assume it's implemented correctly.
        messageBoard.printMessages();  // Observational test to see if it prints without errors
    }
}

