package uta.cse3310;
//import uta.cse3310.*;

import junit.framework.TestCase;
import java.util.Vector;

public class MessageBoardTest extends TestCase {

    private MessageBoard board;

    public void setUp() {
        board = new MessageBoard();
    }

    public void testAddMessage() {
        System.out.println("Test Add Message");
        System.out.println("----------------");

        board.addMessage("Alice", "Hello, Bob!");
        Vector<String> messages = board.getMessages();

        // Check that the message was correctly added
        boolean messageAdded = messages.contains("Alice: Hello, Bob!");
        assertTrue(messageAdded);
        if (messageAdded) {
            System.out.println("Message added correctly: Alice: Hello, Bob!");
        } else {
            System.out.println("Error: Message not added correctly.");
        }
    }

    public void testGenerateMessage() {
        System.out.println("Test Generate System Message");
        System.out.println("---------------------------");

        board.generateMessage("Welcome to the chat!");
        Vector<String> messages = board.getMessages();

        // Check that the system message was formatted and added correctly
        boolean messageGenerated = messages.contains("System: Welcome to the chat!");
        assertTrue(messageGenerated);
        if (messageGenerated) {
            System.out.println("System message generated correctly: System: Welcome to the chat!");
        } else {
            System.out.println("Error: System message not generated correctly.");
        }
    }

    public void testReceiveInputWithEmptyMessage() {
        System.out.println("Test Receive Empty Input");
        System.out.println("-----------------------");

        board.receiveInput("Charlie", "   ");
        boolean isEmpty = board.getMessages().isEmpty();

        assertTrue(isEmpty);
        if (isEmpty) {
            System.out.println("Empty input correctly ignored.");
        } else {
            System.out.println("Error: Empty input was not ignored.");
        }
    }

    public void testMessageRetrieval() {
        System.out.println("Test Message Retrieval");
        System.out.println("----------------------");

        board.addMessage("Alice", "Hello, Bob!");
        board.generateMessage("Server restart in 5 minutes.");
        Vector<String> messages = board.getMessages();

        // Check if all messages are retrieved correctly
        boolean allRetrieved = messages.size() == 2 && messages.contains("Alice: Hello, Bob!") && messages.contains("System: Server restart in 5 minutes.");
        assertTrue(allRetrieved);
        if (allRetrieved) {
            System.out.println("All messages retrieved correctly.");
        } else {
            System.out.println("Error: Not all messages retrieved correctly.");
        }
    }
}


