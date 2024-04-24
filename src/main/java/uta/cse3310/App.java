
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

    private String version = null;

    WordBank W = new WordBank();
    
    GameLobby GL = new GameLobby();

    public WordBank getWordBankW() 
    {
        return W;
    }

    public App(int port) {
        super(new InetSocketAddress(port));
    }

    public App(InetSocketAddress address) {
        super(address);
    }

    public App(int port, Draft_6455 draft) {
        super(new InetSocketAddress(port), Collections.<Draft>singletonList(draft));
    }

    @Override
    public void onOpen(WebSocket conn, ClientHandshake handshake) {
        connectionId++;
        System.out.println(conn.getRemoteSocketAddress().getAddress().getHostAddress() + " connected");
        ServerEvent E = new ServerEvent();

        Gson gson = new Gson();

        // Note only send to the single connection
        String jsonString = gson.toJson(E);
        conn.send(jsonString);
        System.out.println("> " + Duration.between(startTime, Instant.now()).toMillis() + " " + connectionId + " " + escape(jsonString));

        // Update the running time and github hash
        stats.setRunningTime(Duration.between(startTime, Instant.now()).toSeconds());

        // The state of the game has changed, so lets send it to everyone
        //jsonString = gson.toJson(G);
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

        ServerEvent E = gson.fromJson(message, ServerEvent.class);
        UserEvent U = gson.fromJson(message, UserEvent.class);

        System.out.println("On message: " + message);

        // Update the running time
        stats.setRunningTime(Duration.between(startTime, Instant.now()).toSeconds());

        Player P = null;
        Game G = null;

        if(U.Invoke == 1)   // FindGame() and New Player
        {
            // Create a new default blank Player object
            P = new Player();
            P.setPlayerNick(U.PlayerNick);
            P.setSavedPin(U.Pin);
            
            if(U.Type == 2)
            {
                P.PlayerNum = 2;
            }
            else if(U.Type == 3)
            {
                P.PlayerNum = 3;
            }
            else if(U.Type == 4)
            {
                P.PlayerNum = 4;
            }

            System.out.println("\n\nTest\n\n");

            System.out.println("\n\nPlayerType " + P.getPlayerType());

            PlayerList.add(P);

            G = GL.matchMaking(PlayerList, ActiveGames);
            U.Invoke = -1;

            if(G != null)
            {
                G.state = 1;
                G.GameId = GameId;
                GameId++;

                ActiveGames.add(G);
                G.Update(U);
                System.out.println("\n\nGame Has Been Created\n\n");

                // Call all clients to switch to the game screen
                //conn.send(G);
            }

            if(G != null)
            {
                E.YouAre = G.Players;
                E.GameId = G.GameId;
            }

            // allows the websocket to give us the Game when a message arrives
            conn.setAttachment(G);

            gson = new Gson();
            // Note only send to the single connection
            conn.send(gson.toJson(E));
            System.out.println(gson.toJson(E));

            // send out the game state every time
            // to everyone
            String jsonString;
            jsonString = gson.toJson(G);
            System.out.println("> " + Duration.between(startTime, Instant.now()).toMillis() + " " + "*" + " " + escape(jsonString));
            broadcast(jsonString);
        }
        
        
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
        //9080 or 9006 for HttpPort
        //9880 or 9106 for Websocket

        String HttpPort = System.getenv("HTTP_PORT");
        int port = 9080;
        if (HttpPort!=null) {
            port = Integer.valueOf(HttpPort);
        }

        HttpServer H = new HttpServer(port, "./html");
        H.start();
        System.out.println("http Server started on port: " + port);

        port = 9880;
        String WSPort = System.getenv("WEBSOCKET_PORT");
        if (WSPort!=null) {
            port = Integer.valueOf(WSPort);
        }

        App A = new App(port);
        A.setReuseAddr(true);
        A.start();
        System.out.println("websocket Server started on port: " + port);

        A.version = System.getenv("VERSION");
        System.out.println("Current github hash : " + A.version); // Will work once it is connected to the web site

    }
}
