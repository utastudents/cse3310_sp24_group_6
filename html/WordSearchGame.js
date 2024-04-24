var Button_ = -1;
var PlayerNick_ = -1;
var Pin_ = -1;
var Invoke_ = -1;
var Status_ = -1;
var Type_ = 2;
var GameState_ = -1;
var idx = 1;
var playID = -1;
var start = 0;
var word = "";
var wordCount = 0;

var startCoordinate = -1;
var endCoordinate = -1;

var direction = -1;

const squareGrid = new Array(2500);

const PlayerToColor = new Map([[0,"royalblue"],[1,"blue"],[2,"red"],[3,"yellow"],[4,"green"],[5,"brown"]]);
const NumberToGameType = new Map([[0,"unknown"],[1,"Game1"],[2,"Game2"],[3,"Game3"],[4,"Game4"]]);
const DirToWordType = new Map([[1,"horizontal"],[2,"bHorizontal"],[3,"vertical"],[4,"bVertical"],
                                   [5,"bottomLeftTopRight"],[6,"topRightBottomLeft"],[7,"bottomRightTopLeft"],[8,"topLeftBottomRight"]]);

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
    
    GameState_ = -1;
    idx = -1;
    playID = -1;
    start = 0;
    word = "";
}

var connection = null;

serverUrl = "ws://" + window.location.hostname +":9106";
//9880 for locoal
//9106 for website
// Create the connection with the server
connection = new WebSocket(serverUrl);

connection.onopen = function (evt) {
    console.log("open");
}

connection.onclose = function (evt) {
    console.log("close");
    document.getElementById("topMessage").innerHTML = "Server Offline";
}

connection.onmessage = function (evt) {
    var msg;
    msg = evt.data;
    
    console.log("Message received: " + msg);
    const obj = JSON.parse(msg);

    Invoke_ = -1;

    if ('state' in obj) {
        if (obj.state == 1) {   // Setup Game and Start
            if(obj.pt == "Player1")
            {
                idx = 1;
            }
            else if(obj.pt == "Player2")
            {
                idx = 2;
            }
            else if(obj.pt == "Player3")
            {
                idx = 3;
            }
            else if(obj.pt == "Player4")
            {
                idx = 4;
            }
            var count = 0;
            for (let i = 0; i < 50; i++)
            {
                for (let j = 0; j < 50; j++)
                {
                    squareGrid[count] = obj.g.grid[i][j];

                    const button = document.createElement("button");

                    button.setAttribute("id",count);
                    button.setAttribute("onclick","change_color("+count+");");

                    button.innerHTML = squareGrid[count];

                    board.appendChild(button);
                    count++;
                }
                linebreak = document.createElement("br");
                board.appendChild(linebreak);
            }
            obj.g.placedWords.forEach(wordObj => {
              addWordBank("p5table4",wordObj.word);
              wordCount++;
            })
            GameRoom();
            sendUpdate();
        }
        if (obj.state == 2)    // Different Function
        {
        }    
    }
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
    document.getElementById("p5p").innerHTML="You are: "+PlayerNick_;
}

function FindGame() {   // Button Function
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

function StartGame() {
    // SETUP THE WORD GRID

    var coujnt = 0;
    for (let i = 0; i < 50; i++)
    {
        for (let j = 0; j < 50; j++)
        {
            squareGrid[count] = grid[i][j];

            const button = document.createElement("button");

            button.setAttribute("id",index);
            button.setAttribute("onclick","change_color("+index+");");

            button.innerHTML = squareGrid[index];

            if(index % 50 == 0) {
                linebreak = document.createElement("br");
                board.appendChild(linebreak);
            }

            board.appendChild(button);
            count++;
        }
    }

    /*
    for (let i = 0; i < sizeofwordarray; i++)
    {
        addWordBank("p5table4",wordarry[i]);
    }
    */
    //some how start timer

    //set up leaderboard
    
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
        if(direction==1) {
          for(let i=startCoordinate;i<=endCoordinate;i++) {
            document.getElementById(i).style.backgroundColor = PlayerToColor.get(idx);
            word += document.getElementById(i).innerHTML;
          }
        }    
        else if(direction==2) {      
          for(let i=startCoordinate;i>=endCoordinate;i--) {
            document.getElementById(i).style.backgroundColor = PlayerToColor.get(idx);
            word += document.getElementById(i).innerHTML;
            }
        }
        else if(direction==3) {    
          for(let i=startCoordinate;i>=endCoordinate;i -=50) {
            document.getElementById(i).style.backgroundColor = PlayerToColor.get(idx);
            word += document.getElementById(i).innerHTML;
            }
        }
        else if(direction==4) {     
          for(let i=startCoordinate;i<=endCoordinate;i +=50) {
            document.getElementById(i).style.backgroundColor = PlayerToColor.get(idx);
            word += document.getElementById(i).innerHTML;
            }
        }
        else if(direction==5) {     
          for(let i=startCoordinate;i>=endCoordinate;i -=49) {
            document.getElementById(i).style.backgroundColor = PlayerToColor.get(idx);
            word += document.getElementById(i).innerHTML;
            }
        }
        else if(direction==6) {     
          for(let i=startCoordinate;i>=endCoordinate;i -=51) {
            document.getElementById(i).style.backgroundColor = PlayerToColor.get(idx);
            word += document.getElementById(i).innerHTML;
            }
        }
        else if(direction==7) {     
          for(let i=startCoordinate;i<=endCoordinate;i +=51) {
            document.getElementById(i).style.backgroundColor = PlayerToColor.get(idx);
            word += document.getElementById(i).innerHTML;
            }
        }
        else if(direction==8) {     
          for(let i=startCoordinate;i<=endCoordinate;i +=49) {
            document.getElementById(i).style.backgroundColor = PlayerToColor.get(idx);
            word += document.getElementById(i).innerHTML;
            }
        }
        StrikethroughWord(word);
        word="";
     }
     function StrikethroughWord(word) {
         var table = document.getElementById("p5table4");
         for(let i = 0; i < table.rows.length;i++) {
           let cell0 = table.rows[i].cells[0];
           if (cell0.innerHTML == word) cell0.innerHTML = "<del>"+word+"</del>";
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

     function addWordBank(tableID,item1) {
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

     function sendUpdate() {
        U = new UserEvent();
        U.Button = -1;
        U.Invoke = Invoke_;
        /*
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
        U.PlayerNick = nick;
        U.gameType = NumberToGameType.get(gameType);
        U.Pin = pin;
        U.StartCoordinate = startCoordinate;
        U.EndCoordinate = endCoordinate;
        U.wordType = DirToWordType.get(direction);
        */
        console.log(U);
        if (connection.readyState === WebSocket.OPEN) {
          connection.send(JSON.stringify(U));
          console.log(JSON.stringify(U))
        }
        else {
          console.log("Connection not open. Unable to send update.");
        }
    }
