
package uta.cse3310;
// User events are sent from the webpage to the server

public class UserEvent {
    int GameId; // the game ID on the server
    PlayerType PlayerIdx; // 
    int Button; // 
    String PlayerNick;
    String Pin;
    GameType gameType;
    int StartCoordinate;
    int EndCoordinate;
    WordType wordType;
    boolean newPlayer;
    
    UserEvent() {}
    
    UserEvent(int _GameId, PlayerType _PlayerIdx, int _Button, String PlayerNick) {
        GameId = _GameId;
        PlayerIdx = _PlayerIdx;
        Button = _Button;
        PlayerNick = PlayerNick;
    }
}


