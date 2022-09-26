package uta.cse3310;

public class Game {
    public int Players;
    public int CurrentTurn;
    public int[] Button;
    public String[] Msg;
    public int GameId;

    Game() {
        Button = new int[9];
        Msg = new String[2];
        Players = 1;
        Msg[0] = "Waiting for other player to join";
        Msg[1] = "";
    }

    public void StartGame() {
        Msg[0] = "You are O. Your turn";
        Msg[1] = "You are X. Other players turn";
        CurrentTurn = 0;
    }

    private boolean CheckLine(int i, int j, int k, int player) {
        return player == Button[i] && player == Button[j] && player == Button[k];
    }

    private boolean CheckHorizontal(int player) {
        return CheckLine(0, 1, 2, player) | CheckLine(3, 4, 5, player) | CheckLine(6, 7, 8, player);
    }

    private boolean CheckVertical(int player) {
        return CheckLine(0, 3, 6, player) | CheckLine(1, 4, 7, player) | CheckLine(2, 5, 8, player);
    }

    private boolean CheckDiagonal(int player) {
        return CheckLine(0, 4, 8, player) | CheckLine(2, 4, 6, player);
    }

    private boolean CheckBoard(int player) {
        return CheckHorizontal(player) | CheckVertical(player) | CheckDiagonal(player);
    }

    private boolean CheckDraw(int player) {
	    // how to check for a draw?
	    
        return false;
    }

    public void Update(UserEvent U) {
        if (Players == 2 && U.PlayerIdx == CurrentTurn && (CurrentTurn == 0 || CurrentTurn == 1)) {
            if (Button[U.Button] == 0) {
                System.out.println("the button was 0, setting it to" + U.PlayerIdx);
                Button[U.Button] = U.PlayerIdx + 1;
                if (CurrentTurn == 0) {
                    CurrentTurn = 1;
                    Msg[0] = "Other Players Move.";
                    Msg[1] = "Your Move.";
                } else {
                    CurrentTurn = 0;
                    Msg[1] = "Other Players Move.";
                    Msg[0] = "Your Move.";
                }
            } else {
                Msg[U.PlayerIdx] = "Not a legal move.";
            }
	    if (CheckDraw(U.PlayerIdx)) {
                Msg[0] = "Draw";
                Msg[1] = "Draw";
                CurrentTurn = 99;
	    }
	    if (CheckBoard(1)) {
                Msg[0] = "You Win!";
                Msg[1] = "You Lose!";
                CurrentTurn = 99;
            } else if (CheckBoard(2)) {
                Msg[1] = "You Win!";
                Msg[0] = "You Lose!";
                CurrentTurn = 99;
            }
        }
    }

    public void Tick() {

    }
}
// In windows, shift-alt-F formats the source code
