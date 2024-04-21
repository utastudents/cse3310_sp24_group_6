
// This is example code provided to CSE3310 Fall 2022
// You are free to use as is, or changed, any of the code provided

// Please comply with the licensing requirements for the
// open source packages being used.

// This code is based upon, and derived from the this repository
//            https:/thub.com/TooTallNate/Java-WebSocket/tree/master/src/main/example

// http server include is a GPL licensed package from
//            http://www.freeutils.net/source/jlhttp/

/*Copyright (c) 2010-2020 Nathan Rajlich

Permission is hereby granted, free of charge, to any person
obtaining a copy of this software and associated documentation
files (the "Software"), to deal in the Software without
restriction, including without limitation the rights to use,
copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the
Software is furnished to do so, subject to the following
conditions:

The above copyright notice and this permission notice shall be
included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES
OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT
HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY,
WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING
FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR
OTHER DEALINGS IN THE SOFTWARE.*/

package uta.cse3310;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;
import java.util.Collections;

import org.java_websocket.WebSocket;
import org.java_websocket.drafts.Draft;
import org.java_websocket.drafts.Draft_6455;
import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.server.WebSocketServer;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Vector;
import java.time.Instant;
import java.time.Duration;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class App extends WebSocketServer {

    // All games currently underway on this server are stored in
    // the vector ActiveGames

    private Vector<Player> PlayerList = new Vector<Player>();

    private Vector<Word> WordList = new Vector<Word>();

    private Vector<Game> ActiveGames = new Vector<Game>();

    private int GameId = 1;

    private int connectionId = 0;

    private Instant startTime;

    private Statistics stats;

    public App(int port) {
        super(new InetSocketAddress(port));
    }

    public App(InetSocketAddress address) {
        super(address);
    }

    public App(int port, Draft_6455 draft) {
        super(new InetSocketAddress(port), Collections.<Draft>singletonList(draft));
    }

    private static String getCurrHash()
    {
        try {
            // Execute Git command to retrieve the latest commit hash
            Process process = Runtime.getRuntime().exec("git rev-parse HEAD");
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));

            // Read the output of the command and add it to a string, this should be the hash
            String line; 
            StringBuilder hash = new StringBuilder();
            while ((line = reader.readLine()) != null) 
            {
                    hash.append(line); 
            }

            // Wait for the command to finish and get the exit code
            int exitCode = process.waitFor();

            // If the command was successful, return the commit hash
            if (exitCode == 0) // Git returns 0 if everything went well
            { 
                System.out.println("Current git hash: "+ hash);
                return hash.toString().trim(); // Success
            } else {
                System.err.println("Error: Git command failed with exit code " + exitCode);
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return "unknown"; // Defaut if there is an issue getting the hash
    }

    @Override
    public void onOpen(WebSocket conn, ClientHandshake handshake) {
        connectionId++;
        System.out.println(conn.getRemoteSocketAddress().getAddress().getHostAddress() + " connected");
        ServerEvent E = new ServerEvent();
        GameLobby GL = new GameLobby();

        Player player = null;

        //TODO - Retrieve playernick and num from ui 
        String playerNick = "TEST";
        int playerNum = 2; // Gametype the player is seeking

        // If PlayerList is not empty,
        // look through player list to check if this player is a returning player
        int foundPlayer = 0;

        if(PlayerList.size() != 0)
        {
            for(int i = 0; i < PlayerList.size(); i++)
            {
                Player P = PlayerList.get(i);
                if(playerNick == P.getPlayerNick())
                {
                    // This is a returning player, don't make a new player obj
                    player = P;
                    foundPlayer = 1;
                }
            }
        }
        if (foundPlayer == 0)
        {
            // Not found in PlayerList or PlayerList was empty, this is a new player
            player = new Player(playerNick, playerNum);
            PlayerList.add(player);
        }
        
        Game G = null;
        while(G == null) // Keep trying until matched with game 
        {
            if(ActiveGames.size() < 5)
            {
                G = GL.matchMaking(ActiveGames, player);
            }
        }
        
        // create an event to go to only the new player
        E.YouAre = G.Players;
        E.GameId = G.GameId;

        // allows the websocket to give us the Game when a message arrives..
        // it stores a pointer to G, and will give that pointer back to us
        // when we ask for it
        conn.setAttachment(G);

        Gson gson = new Gson();

        // Note only send to the single connection
        String jsonString = gson.toJson(E);
        conn.send(jsonString);
        System.out.println("> " + Duration.between(startTime, Instant.now()).toMillis() + " " + connectionId + " " + escape(jsonString));

        // Update the running time and github hash
        stats.setRunningTime(Duration.between(startTime, Instant.now()).toSeconds());

        // The state of the game has changed, so lets send it to everyone
        jsonString = gson.toJson(G);
        System.out.println("< " + Duration.between(startTime, Instant.now()).toMillis() + " " + "*" + " " + escape(jsonString));
        broadcast(jsonString);
    }

    @Override
    public void onClose(WebSocket conn, int code, String reason, boolean remote) {
        System.out.println(conn + " has closed");
        
        // Retrieve the game tied to the websocket connection
        Game G = conn.getAttachment();
        G = null;
    }

    @Override
    public void onMessage(WebSocket conn, String message) {
        System.out.println("< " + Duration.between(startTime, Instant.now()).toMillis() + " " + "-" + " " + escape(message));

        // Bring in the data from the webpage
        // A UserEvent is all that is allowed at this point
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        UserEvent U = gson.fromJson(message, UserEvent.class);

        // Update the running time
        stats.setRunningTime(Duration.between(startTime, Instant.now()).toSeconds());

        // Get our Game Object
        Game G = conn.getAttachment();
        G.Update(U);

        // Save game stats here?

        // send out the game state every time
        // to everyone
        String jsonString;
        jsonString = gson.toJson(G);

        System.out.println("> " + Duration.between(startTime, Instant.now()).toMillis() + " " + "*" + " " + escape(jsonString));
        broadcast(jsonString);
    }

    @Override
    public void onMessage(WebSocket conn, ByteBuffer message) {
        System.out.println(conn + ": " + message);
    }

    @Override
    public void onError(WebSocket conn, Exception ex) {
        ex.printStackTrace();
        if (conn != null) {
        // some errors like port binding failed may not be assignable to a specific
        // websocket
        }
    }

    @Override
    public void onStart() {
        setConnectionLostTimeout(0);
        stats = new Statistics();
        startTime = Instant.now();
    }

    private String escape(String S) {
        // turns " into \"
        String retval = new String();
        // this routine is very slow.
        // but it is not called very often
        for (int i = 0; i < S.length(); i++) {
        Character ch = S.charAt(i);
        if (ch == '\"') {
        retval = retval + '\\';
        }
        retval = retval + ch;
        }
        return retval;
    }

    public static void main(String[] args) {

        // Set up the http server
        int port = 9006;
        HttpServer H = new HttpServer(port, "./html");
        H.start();
        System.out.println("http Server started on port: " + port);

        // create and start the websocket server

        port = 9106;
        App A = new App(port);
        A.setReuseAddr(true);
        A.start();
        System.out.println("websocket Server started on port: " + port);

    }
}
