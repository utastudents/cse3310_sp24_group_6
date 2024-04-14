//Kaitlin Martin
//Group 6

package uta.cse3310;
import java.util.Vector;

public class Leaderboard {
    private Vector<Player> GamesWonPlace = new Vector<>();
    private Vector<Player> PointsEarnedPlace = new Vector<>();

    public Leaderboard () {}

    private void sort() {}

    public Vector<Player> getGamesWon() {
        return GamesWonPlace;
    }

    public Vector<Player> getPointsEarned() {
        return PointsEarnedPlace;
    }
    public void setGamesWon(Player p)
    {
       GamesWonPlace.add(p);    
    }
    
    public void setPointsEarned(Player p)
    {
       PointsEarnedPlace.add(p);
    }
}
