var Button_ = -1;
var PlayerNick_ = -1;
var Pin_ = -1;
var Invoke_ = -1;
var Status_ = -1;

class UserEvent {
    Button = -1;
    Playernick = -1;
    Pin = -1;
    Invoke = 0;
    Status = -1;   
}

var connection = null;

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
}

function NewPlayer() {
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

function FindGame() {
    U = new UserEvent();

    PlayerNick_=document.getElementById("name").value;
    Pin_=document.getElementById("pin").value;
    U.Invoke= 1;

    U.Playernick = PlayerNick_;
    U.Pin = Pin_;

    //connection.send(JSON.stringify(U));
    console.log(JSON.stringify(U));

    document.getElementById("page2").style.display="none"; 
    document.getElementById("page3").style.display="none"; 
    document.getElementById("page4").style.display="block";
    document.getElementById("page6").style.display="none";
    document.getElementById("page7").style.display="none";
}