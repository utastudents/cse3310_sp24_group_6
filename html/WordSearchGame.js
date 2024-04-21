    var players = 2;
    var nick = "unknown";
    var pin = "XXXX";
    var direction = 1;
    var startCoordinate = -1;
    var endCoordinate = -1;
    var start = 0;
    var idx = Math.round(1 + Math.random()*3);
    const PlayerToColor = new Map([[0,"royalblue"],[1,"blue"],[2,"red"],[3,"yellow"],[4,"green"],[5,"brown"]]);
    const NumberToDirection = new Map([[1,"HORIZONTAL"],[2,"LHORIZONTAL"],[3,"VERTICAL"],[4,"DVERTICAL"],[5,"NEDIAGONAL"],[6,"SWDIAGONAL"],[7,"NWDIAGONAL"],[8,"SEDIAGONAL"]]); 
    const DirectionToNumber = new Map(["horizontal",1],["bhorizontal",2],["vertical",3],["bvertical",4],["bottomLeftTopRight",5],["topRightBottomLeft",6],["bottomRightTopLeft",7],["topLeftBottomRight",8]]);
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
    function ReturnPage1()
    {
      document.getElementById("page2").style.display="none"; 
      document.getElementById("page3").style.display="none"; 
      document.getElementById("page1").style.display="block";        
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
      sendUpdate();
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
    const squareGrid = new Array(2500);
    for (let index=0;index<squareGrid.length;index++) {
        let charCode = Math.round(65 + Math.random() * 25);
        squareGrid[index]=String.fromCharCode(charCode);
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
      addWordToGrid("ABRACADEBRA",1,110);
      addWordToGrid("VALERIE",2,975);
      addWordToGrid("LAURA",5,300);
      addWordToGrid("MISSISSIPPI",3,422);
      addWordToGrid("ABILITIES",4,849);
      addWordToGrid("ACCESSIBILITY",1,462);
      addWordToGrid("ACCOUNTS",7,562);
      addWordToGrid("ULTIMATE",6,340);
      addWordToGrid("ABLE",8,462);

    function ResetBoard() {
      start = 0;
      startCoordinate=endCoordinate=-1;
      for (let i=0;i<squareGrid.length;i++)
        document.getElementById(i).style.backgroundColor = "PaleTurquoise";
      document.getElementById("p5ta").value=" ";
    }

    function addWordToGrid(Word,dir,startPosition) {
       let A = Array.from(Word);
       let l = A.length;
       if(dir==1)
         for(let i=startPosition;i<startPosition+l;i++)
           document.getElementById(i).innerHTML=A[i-startPosition];
       else if(dir==2)
         for(let i=0;i<l;i++)
           document.getElementById(startPosition-i).innerHTML=A[i];
       else if(dir==3)
         for(let i=0;i<l;i++)
           document.getElementById(startPosition+i*50).innerHTML=A[i];
       else if(dir==4)
         for(let i=0;i<l;i++)
           document.getElementById(startPosition-i*50).innerHTML=A[i];
       else if(dir==5)
         for(let i=0;i<l;i++)
           document.getElementById(startPosition-i*(50-1)).innerHTML=A[i];
       else if(dir==6)
         for(let i=0;i<l;i++)
           document.getElementById(startPosition+i*(50-1)).innerHTML=A[i];
       else if(dir==7)
         for(let i=0;i<l;i++)
           document.getElementById(startPosition-i*(50+1)).innerHTML=A[i];
        else if(dir==8)
         for(let i=0;i<l;i++)
           document.getElementById(startPosition+i*(50+1)).innerHTML=A[i];
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

  // Get today's date and time
  var now = new Date().getTime();
  // Find the distance between now and the count down date
  var distance = countDownDate - now;
//var distance = 10;
  // Time calculations for days, hours, minutes and seconds
  var minutes = Math.floor((distance % (1000 * 60 * 60)) / (1000 * 60));
  var seconds = Math.floor((distance % (1000 * 60)) / 1000);
  document.getElementById("p5h1").innerHTML = minutes + ":" + seconds;
  if (distance < 0) {
    clearInterval(x);
    document.getElementById("p5h1").innerHTML = "GAME OVER";
  }
}, 1000);

//    var idx = -1;
    var gameid = -1;
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
    }
    var connection = null;
    var serverUrl;
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
            if (obj.YouAre == "PLAYER0") {
                idx = 0;
            }
            else if(obj.YouAre == "PLAYER1"){
                idx = 1;
            }
            else if(obj.YouAre == "PLAYER2"){
                idx = 2;
            }
            else if(obj.YouAre == "PLAYER3"){
                idx = 3;
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

    function getDirection(v1, v2) {
       let x = v1 % 50;
       let y = Math.floor(v1 / 50);
       let x2 = v2 % 50;
       let y2 = Math.floor(v2 / 50);
       if((v1<0) || (v2<0)) return -2;
       if(y==y2)
       {
         if(x2-x>0)
           return 1;
         else
           return 2;
       }
       else if (x==x2)
       {
         if(y-y2>0)
           return 3;
         else
           return 4;
       } 
       else if(Math.abs(x2-x) == Math.abs(y2-y))
       {
         if((x2-x)>0 && (y-y2)>0)
           return 5;
         else if((x2-x)<0 && (y-y2)>0)
           return 6;
         else if((x2-x)>0 && (y-y2)<0)
           return 7;
         else if((x2-x)<0 && (y-y2)<0)
           return 8;
       }
       else
         return -1;
    }

    function sendUpdate() {
        U = new UserEvent();
        U.Button = -1;
        if(idx == 0)
            U.PlayerIdx = "Player1";
        else if(idx == 1)
            U.PlayerIdx = "Player2";
         else if(idx == 2)
            U.PlayerIdx = "Player3";
        else if(idx == 3)
            U.PlayerIdx = "Player4";
        U.GameId = gameid;
        U.Name = nick;
        U.Players = players;
        U.Pin = pin;
        U.StartCoordinate = startCoordinate;
        U.EndCoordinate = endCoordinate;
        U.Direction = direction;
//        console.log(U);
        connection.send(JSON.stringify(U));
        console.log(JSON.stringify(U))
    }