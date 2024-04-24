var Button_ = -1;
var PlayerNick_ = -1;
var Pin_ = -1;
var Invoke_ = -1;
var Status_ = -1;
var Type_ = 2;
var GameState_ = -1;

class ServerEvent {
    GameState = -1
}

class UserEvent {
    Button = -1;
    Playernick = -1;
    Pin = -1;
    Invoke = 0;
    Status = -1;
    Type = -1   
}

var connection = null;

serverUrl = "ws://" + window.location.hostname +":9880";
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
}

function NewPlayer(){
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

function GameRoom()
{
    document.getElementById("page4").style.display="none"; 
    document.getElementById("page5").style.display="block";
    document.getElementById("p5p").innerHTML="You are: "+nick;
}

function FindGame() {
    U = new UserEvent();

    PlayerNick_=document.getElementById("name").value;
    Pin_=document.getElementById("pin").value;
    
    U.Invoke= 1;

    U.Playernick = PlayerNick_;
    U.Pin = Pin_;
    U.Type = Type_;

    connection.send(JSON.stringify(U));
    console.log(JSON.stringify(U));

    document.getElementById("page2").style.display="none"; 
    document.getElementById("page3").style.display="none"; 
    document.getElementById("page4").style.display="block";
    document.getElementById("page6").style.display="none";
    document.getElementById("page7").style.display="none";
}

function SelectPlayer(id)
    {
      if(id=="p2bt3") {
        Type_ = 2;
        document.getElementById(id).className="button"; 
        document.getElementById("p2bt4").className="button button4"; 
        document.getElementById("p2bt5").className="button button4";
      }        
      else if(id=="p2bt4") {
        Type_ = 3;
        document.getElementById(id).className="button"; 
        document.getElementById("p2bt3").className="button button4"; 
        document.getElementById("p2bt5").className="button button4";
      }        
      else if(id=="p2bt5") {
        Type_ = 4;
        document.getElementById(id).className="button"; 
        document.getElementById("p2bt4").className="button button4"; 
        document.getElementById("p2bt3").className="button button4";
      }        
      else if(id=="p3bt3") {
        Type_ = 2;
        document.getElementById(id).className="button"; 
        document.getElementById("p3bt4").className="button button4"; 
        document.getElementById("p3bt5").className="button button4";
      }        
      else if(id=="p3bt4") {
        Type_ = 3;
        document.getElementById(id).className="button"; 
        document.getElementById("p3bt3").className="button button4"; 
        document.getElementById("p3bt5").className="button button4";
      }        
      else if(id=="p3bt5") {
        Type_ = 4;
        document.getElementById(id).className="button"; 
        document.getElementById("p3bt4").className="button button4"; 
        document.getElementById("p3bt3").className="button button4";
      }  
      else if(id=="p6bt3") {
        Type_ = 2;
        document.getElementById(id).className="button"; 
        document.getElementById("p6bt4").className="button button4"; 
        document.getElementById("p6bt5").className="button button4";
      }        
      else if(id=="p6bt4") {
        Type_ = 3;
        document.getElementById(id).className="button"; 
        document.getElementById("p6bt3").className="button button4"; 
        document.getElementById("p6bt5").className="button button4";
      }        
      else if(id=="p6bt5") {
        Type_ = 4;
        document.getElementById(id).className="button"; 
        document.getElementById("p6bt4").className="button button4"; 
        document.getElementById("p6bt3").className="button button4";
      }  
      else if(id=="p7bt3") {
        Type_ = 2;
        document.getElementById(id).className="button"; 
        document.getElementById("p7bt4").className="button button4"; 
        document.getElementById("p7bt5").className="button button4";
      }        
      else if(id=="p7bt4") {
        Type_ = 3;
        document.getElementById(id).className="button"; 
        document.getElementById("p7bt3").className="button button4"; 
        document.getElementById("p7bt5").className="button button4";
      }        
      else if(id=="p7bt5") {
        Type_ = 4;
        document.getElementById(id).className="button"; 
        document.getElementById("p7bt4").className="button button4"; 
        document.getElementById("p7bt3").className="button button4";
      }  
    }

