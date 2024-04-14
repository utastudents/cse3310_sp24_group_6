    const WIDTH = 50;
    const HEIGHT = 50;
    var selected_letters = "";
    var players = 2;
    var nick = "unknown";
    var pin = "XXXX";
//let idx =  Math.round(0 + Math.random() * 4); 
    var idx = 5;
    const PlayerToColor = new Map([[0,"red"],[1,"orange"],[2,"green"],[3,"black"],[4,"royalblue"],[5,"DarkViolet"]]);
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
    function FindGame()
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
      }
      else {
        document.getElementById("p2errMsg2").innerHTML="Please enter your name and create a new pin.";
      }       
    }
    function FindGame2()
    { 
      let nick=document.getElementById("name2").value;
      let pin=document.getElementById("pin2").value;
      if(nick != "" && pin != "") {
      document.getElementById("p4p").innerHTML="You are: "+nick;
      document.getElementById("p4p2").innerHTML="Game Type: "+players+"-Player";
      document.getElementById("page2").style.display="none"; 
      document.getElementById("page3").style.display="none"; 
      document.getElementById("page4").style.display="block";
      }
      else {
        document.getElementById("p3errMsg2").innerHTML="Please enter your name and pin number";
      }       
    }
    function change_color(id) {
       let x = id % WIDTH;
       let y = Math.floor(id / HEIGHT);   
       const letter = document.getElementById(id).innerHTML;
       selected_letters += letter 
       document.getElementById("p5ta").value="selected "+letter+" at coordinate ("+x+","+y+")\nselected letters="+selected_letters;
       let bcolor = document.getElementById(id).style.backgroundColor;
       if(bcolor == "royalblue")
          document.getElementById(id).style.backgroundColor = "PaleTurquoise";
      else
          document.getElementById(id).style.backgroundColor = "royalblue";
    }
    const Buttons = new Array(WIDTH*HEIGHT);
    for (let index=0;index<Buttons.length;index++) {
        let charCode = Math.round(65 + Math.random() * 25);
        Buttons[index]=String.fromCharCode(charCode);
        const button = document.createElement("button");
        button.setAttribute("id",index);
        button.setAttribute("onclick","change_color("+index+");");
        button.innerHTML = Buttons[index];
        if(index % 50 == 0) {
           linebreak = document.createElement("br");
           board.appendChild(linebreak);
        }
        board.appendChild(button);
     }
    function ResetBoard() {
      for (let i=0;i<Buttons.length;i++) {
        let charCode = Math.round(65 + Math.random() * 25);
        document.getElementById(i).innerHTML=String.fromCharCode(charCode);
        let bcolor = document.getElementById(i).style.backgroundColor;
        if(bcolor == "royalblue")
          document.getElementById(i).style.backgroundColor = "PaleTurquoise";
      }
      document.getElementById("p5ta").value=" ";
    }

var countDownDate = new Date("April 13, 2024 22:00:00").getTime();

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

    function buttonclick(i) {
        U = new UserEvent();
        U.Button = i;
        if(idx == 0)
            U.PlayerIdx = "PLAYER0";
        else if(idx == 1)
            U.PlayerIdx = "PLAYER1";
         else if(idx == 2)
            U.PlayerIdx = "PLAYER2";
        else if(idx == 3)
            U.PlayerIdx = "PLAYER3";
        U.GameId = gameid;
        U.Name = nick;
        U.Players = players;
        U.Pin = pin;
        connection.send(JSON.stringify(U));
        console.log(JSON.stringify(U))
    }