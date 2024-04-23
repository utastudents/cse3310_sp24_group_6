var connection = null;
var serverUrl;
var gameid = -1;
var array2D;

var Name;
var Pin;
var Players;
var Button;
var PlayerIdx;
var GameId;
var StartCoordinate;
var EndCoordinate;
var Direction;
var points;
var version;

const squareGrid = new Array(2500);

class UserEvent {
    Name = "unknown";
    Pin = "0000";
    Players = 0;
    Button = -1;
    PlayerIdx = -1;
    GameId = 0;
    StartCoordinate = -1;
    EndCoordinate = -1;
    Direction = 1;
    points = 0;
    version = "uknown";
}

serverUrl = "ws://" + window.location.hostname +":"+ (parseInt(location.port) + 100);
// Create the connection with the server
connection = new WebSocket(serverUrl);

connection.onopen = function (evt) {
    console.log("open");
}
connection.onclose = function (evt) {
    console.log("close");
    document.getElementById("topMessage").innerHTML = "Server Offline"
}
connection.onmessage = function (evt) {
    var msg;
    msg = evt.data;

    console.log("Message received: " + msg);
    const obj = JSON.parse(msg);

    if ('YouAre' in obj) {
        if (obj.YouAre == "player0") {
            idx = 0;
        }
        else if(obj.YouAre == "player1"){
            idx = 1;
        }
        else if(obj.YouAre == "player2"){
            idx = 2;
        }
        else if(obj.YouAre == "player3"){
            idx = 3;
        }
        else if(obj.YouAre == "player4"){
            idx = 4;
        }
        
        gameid = obj.GameId;
    }
    else if ('CurrentTurn' in obj) {
        // show statistics to everyone
        var t = obj.Stats;
        if (t) {
        /*
            document.getElementById("timeMsg").innerHTML = "elapsed time " + t.RunningTime;
            document.getElementById("statMsg").innerHTML =
                " in progress " + t.GamesInProgress + " XWin " + t.XWins + " OWin " + t.OWins +
                " Draw " + t.Draws + " Total " + t.TotalGames;
        */
        }

        // only pay attention to this game
        if (gameid == obj.GameId) {
        /*
            // button state to display values
            document.getElementById("b1").value = ButtonStateToDisplay.get(obj.Button[0]);
            document.getElementById("b2").value = ButtonStateToDisplay.get(obj.Button[1]);
            document.getElementById("b3").value = ButtonStateToDisplay.get(obj.Button[2]);
            document.getElementById("b4").value = ButtonStateToDisplay.get(obj.Button[3]);
            document.getElementById("b5").value = ButtonStateToDisplay.get(obj.Button[4]);
            document.getElementById("b6").value = ButtonStateToDisplay.get(obj.Button[5]);
            document.getElementById("b7").value = ButtonStateToDisplay.get(obj.Button[6]);
            document.getElementById("b8").value = ButtonStateToDisplay.get(obj.Button[7]);
            document.getElementById("b9").value = ButtonStateToDisplay.get(obj.Button[8]);
            // the message line
            document.getElementById("topMessage").innerHTML = obj.Msg[idx];
        */
        }
    }
}
        /*
        Send info about player info and game status (UserEvent class) using json to the app server
        */


//Get info for when the game starts (Get the 2D array & Game Class)
var jsontringarray = '$(jsonArray2d)';
var jsonarray2d = JSON.parse(jsontringarray);

var jsonstringgame = '$(jsongame)';
var jsongame = JSON.parse(jsonstringgame)

var count = 0;

// Make conversion from 2D to 1D array
for (let i = 0; i < 50; i++) {
    for (let j = 0; j < 50; j++) {
        squareGrid[count] = array2D[i][j];
        count++;
    }
}

// Add words from list to the html wordbank
let A = Array.from(listofwords);
let l = A.length;
for(let i = 0; i < l; i++)
{
    addWordBank("p5table4",A[i]);
}

// Add button functionality to the grid
for (let index=0;index<squareGrid.length;index++) {
    const button = document.createElement("button");
    button.setAttribute("id",index);
    button.setAttribute("onclick","change_color("+index+");");
    button.innerHTML = squareGrid[index];
    if(index % 50 == 0) {
        linebreak = document.createElement("br");
        board.appendChild(linebreak);
    }
    board.appendChild(button);
}

/* ========================================================================================================================== */

function sendUpdate() {
    U = new UserEvent();
    U.Button = -1;
    if(idx == 0)
        U.PlayerIdx = "Player0";
    else if(idx == 1)
        U.PlayerIdx = "Player1";
    else if(idx == 2)
        U.PlayerIdx = "Player2";
     else if(idx == 3)
        U.PlayerIdx = "Player3";
    else if(idx == 4)
        U.PlayerIdx = "Player4";
    U.GameId = gameid;
    U.Name = nick;
    U.Players = players;
    U.Pin = pin;
    U.StartCoordinate = startCoordinate;
    U.EndCoordinate = endCoordinate;
    U.Direction = direction;
    U.version = version;
    U.newPlayer = newPlayer;
    connection.send(JSON.stringify(U));
    console.log(JSON.stringify(U))
}

function GameRoom()
{
    document.getElementById("page4").style.display="none"; 
    document.getElementById("page5").style.display="block";
    document.getElementById("p5p").innerHTML="You are: "+nick;
}

function Winner()
{
    document.getElementById("page5").style.display="none"; 
    document.getElementById("page6").style.display="block";
}

function Loser()
{
    document.getElementById("page5").style.display="none"; 
    document.getElementById("page7").style.display="block";
}

function NewPlayer()
{
    document.getElementById("page1").style.display="none"; 
    document.getElementById("page3").style.display="none"; 
    document.getElementById("page2").style.display="block";        
}

function ReturnPlayer()
{
    document.getElementById("page1").style.display="none"; 
    document.getElementById("page2").style.display="none"; 
    document.getElementById("page3").style.display="block";        
}

function SelectPlayer(id)
    {
      if(id=="p2bt3") {
        players = 2;
        document.getElementById(id).className="button"; 
        document.getElementById("p2bt4").className="button button4"; 
        document.getElementById("p2bt5").className="button button4";
      }        
      else if(id=="p2bt4") {
        players = 3;
        document.getElementById(id).className="button"; 
        document.getElementById("p2bt3").className="button button4"; 
        document.getElementById("p2bt5").className="button button4";
      }        
      else if(id=="p2bt5") {
        players = 4;
        document.getElementById(id).className="button"; 
        document.getElementById("p2bt4").className="button button4"; 
        document.getElementById("p2bt3").className="button button4";
      }        
      else if(id=="p3bt3") {
        players = 2;
        document.getElementById(id).className="button"; 
        document.getElementById("p3bt4").className="button button4"; 
        document.getElementById("p3bt5").className="button button4";
      }        
      else if(id=="p3bt4") {
        players = 3;
        document.getElementById(id).className="button"; 
        document.getElementById("p3bt3").className="button button4"; 
        document.getElementById("p3bt5").className="button button4";
      }        
      else if(id=="p3bt5") {
        players = 4;
        document.getElementById(id).className="button"; 
        document.getElementById("p3bt4").className="button button4"; 
        document.getElementById("p3bt3").className="button button4";
      }  
      else if(id=="p6bt3") {
        players = 2;
        document.getElementById(id).className="button"; 
        document.getElementById("p6bt4").className="button button4"; 
        document.getElementById("p6bt5").className="button button4";
      }        
      else if(id=="p6bt4") {
        players = 3;
        document.getElementById(id).className="button"; 
        document.getElementById("p6bt3").className="button button4"; 
        document.getElementById("p6bt5").className="button button4";
      }        
      else if(id=="p6bt5") {
        players = 4;
        document.getElementById(id).className="button"; 
        document.getElementById("p6bt4").className="button button4"; 
        document.getElementById("p6bt3").className="button button4";
      }  
      else if(id=="p7bt3") {
        players = 2;
        document.getElementById(id).className="button"; 
        document.getElementById("p7bt4").className="button button4"; 
        document.getElementById("p7bt5").className="button button4";
      }        
      else if(id=="p7bt4") {
        players = 3;
        document.getElementById(id).className="button"; 
        document.getElementById("p7bt3").className="button button4"; 
        document.getElementById("p7bt5").className="button button4";
      }        
      else if(id=="p7bt5") {
        players = 4;
        document.getElementById(id).className="button"; 
        document.getElementById("p7bt4").className="button button4"; 
        document.getElementById("p7bt3").className="button button4";
      }  
    }

function FindGame(i)
    { 
      nick=document.getElementById("name").value;
      pin=document.getElementById("pin").value;
      newPlayer = true; 
      sendUpdate();
      if(nick != "" && pin != "") {
      document.getElementById("p4p").innerHTML="You are: "+nick;
      document.getElementById("p4p2").innerHTML="Game Type: "+players+"-Player";
      document.getElementById("page2").style.display="none"; 
      document.getElementById("page3").style.display="none"; 
      document.getElementById("page4").style.display="block";
      document.getElementById("page6").style.display="none";
      document.getElementById("page7").style.display="none";
      if(i==1) 
        addRow("p4table",nick,players+"-Player")
      else {
        deleteRow("p4table")
        addRow("p4table",nick,players+"-Player");
       }
      }
      else {
        document.getElementById("p2errMsg2").innerHTML="Please enter your name and create a new pin.";
      }       
    }

function FindGame2(i)
    { 
      nick=document.getElementById("name2").value;
      pin=document.getElementById("pin2").value;
      if(nick != "" && pin != "") {
      document.getElementById("p4p").innerHTML="You are: "+nick;
      document.getElementById("p4p2").innerHTML="Game Type: "+players+"-Player";
      document.getElementById("page2").style.display="none"; 
      document.getElementById("page3").style.display="none"; 
      document.getElementById("page4").style.display="block";
      if(i==1) 
        addRow("p4table",nick,players+"-Player")
      else {
        deleteRow("p4table")
        addRow("p4table",nick,players+"-Player");
        }
      }
      else {
        document.getElementById("p3errMsg2").innerHTML="Please enter your name and pin number";
      }       
      sendUpdate();
    }

function change_color(id) {
        let x = id % 50;
        let y = Math.floor(id / 50);   
        const letter = document.getElementById(id).innerHTML;
        let bcolor = document.getElementById(id).style.backgroundColor;
        document.getElementById(id).style.backgroundColor = PlayerToColor.get(idx);
       if(start==0) {
          startCoordinate = id;
          start = 1;
       }
       else {
         endCoordinate = id;
         start = 0;
       }         
       if(startCoordinate >= 0 && endCoordinate >= 0) {
     highlightWord();
         sendUpdate();
         startCoordinate=endCoordinate=-1;
         start=0;
       }
     }

function WordBank(tableID,item1) {
        var table = document.getElementById(tableID);
        var row = table.insertRow(table.rows.length);
        var cell1 = row.insertCell(0);
        cell1.innerHTML = item1;
      }

function ResetBoard() {
        start = 0;
        startCoordinate=endCoordinate=-1;
        for (let i=0;i<squareGrid.length;i++)
          document.getElementById(i).style.backgroundColor = "PaleTurquoise";
      }

function highlightWord() {
        direction = getDirection(startCoordinate,endCoordinate);
        if(direction==1) 
          for(let i=startCoordinate;i<endCoordinate;i++)
            document.getElementById(i).style.backgroundColor = PlayerToColor.get(idx);
        else if(direction==2)      
          for(let i=startCoordinate;i>endCoordinate;i--)
            document.getElementById(i).style.backgroundColor = PlayerToColor.get(idx);
        else if(direction==3)      
          for(let i=startCoordinate-50;i>=endCoordinate;i -=50)
            document.getElementById(i).style.backgroundColor = PlayerToColor.get(idx);
        else if(direction==4)      
          for(let i=startCoordinate+50;i<=endCoordinate;i +=50)
            document.getElementById(i).style.backgroundColor = PlayerToColor.get(idx);
        else if(direction==5)      
          for(let i=startCoordinate-50+1;i>=endCoordinate;i -=49)
            document.getElementById(i).style.backgroundColor = PlayerToColor.get(idx);
        else if(direction==6)      
          for(let i=startCoordinate-50-1;i>=endCoordinate;i -=51)
            document.getElementById(i).style.backgroundColor = PlayerToColor.get(idx);
        else if(direction==7)      
          for(let i=startCoordinate+50+1;i<=endCoordinate;i +=51)
            document.getElementById(i).style.backgroundColor = PlayerToColor.get(idx);
        else if(direction==8)      
          for(let i=startCoordinate+50-1;i<=endCoordinate;i +=49)
            document.getElementById(i).style.backgroundColor = PlayerToColor.get(idx);
     }

addWordToGrid(array2D)
     {
       var sgindx = 0;  
       for(let i = 0; i < 50; i++)
       {
         for(let j = 0; j < 50; j++)
         {
           squareGrid[sgindx] = array2D[i][j];
         }
       }
     }

function addRow(tableID,item1,item2) {
        var table = document.getElementById(tableID);
        var row = table.insertRow(table.rows.length);  
        var cell1 = row.insertCell(0);
        var cell2 = row.insertCell(1);
        cell1.innerHTML = item1;
        cell2.innerHTML = item2;
      }

function deleteRow(tableID) {
      var table = document.getElementById(tableID);
      table.deleteRow(table.rows.length-1);
    }

var countDownDate = new Date("May 1, 2024 22:00:00").getTime();
    // Update the count down every 1 second
        var x = setInterval(function() {
        var now = new Date().getTime();
        var distance = countDownDate - now;
        var minutes = Math.floor((distance % (1000 * 60 * 60)) / (1000 * 60));
        var seconds = Math.floor((distance % (1000 * 60)) / 1000);
        document.getElementById("p5h1").innerHTML = minutes + ":" + seconds;
        if (distance < 0) {
          clearInterval(x);
          document.getElementById("p5h1").innerHTML = "GAME OVER";
        }
    }, 1000);

