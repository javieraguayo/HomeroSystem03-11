/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 * @Ren
 */


var current_state = 0;

function g(){

var num;
  
  
  $("#incident").load("http://localhost:8080/HomeroSystem03-11/faces/incidente/Latest.xhtml");


  setTimeout(function(){
    if(current_state == 0 && $("#latest").val() != undefined)
      current_state = $("#latest").val();
    
    else if(current_state > 0 && $("#latest").val() != undefined && $("#latest").val() > current_state){
      current_state = $("#latest").val();
      console.log("new incident");
      $("#notify").delay(300).addClass("in").delay(5000).fadeOut(3000);
    }
    
  }, 5000);

  
  
}


var tid = setInterval(g, 12000);
