package uta.cse3310;
// User events are sent from the webpage to the server

public class UserEvent {
    int GameId; // the game ID on the server
    int PlayerIdx; // either a 0 or a 1
    int Button; // button number from 0 to 8
}
