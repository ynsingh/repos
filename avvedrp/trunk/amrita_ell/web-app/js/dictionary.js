 // JavaScript Document
document.write("<div class=\"popup_msg\" id=\"popuup_div\"><img align=\"right\" src=\"../images/skin/close.jpg\" width=\"13\" height=\"13\" onclick=\"displaymessage()\" /></br> <label id=\"myLabel\"></label> </div>");
jQuery(document).dblclick(function(e) { 
document.getElementById("myLabel").innerHTML ="";
var text = getSelectedText();    
var t= text.split(' ').join('');
jQuery.ajax({
type:'POST',
data: {t:t },
dataType: "json",
url:'/AELL/bvDictList/dictSearch',
success:function(data,textStatus){
if(data.type.length!=0){
document.getElementById("myLabel").innerHTML ="<font size=\"3\" color=\"green\"><b>"+t+"</b>"
if(data.image){
document.getElementById("myLabel").innerHTML +="&nbsp;&nbsp;&nbsp;<a href=\"#\"  onClick=\"displayImage('"+t+"');\"><img  width=\"20\" height=\"20\" border=\"0\" src=\"../images/skin/imag.png\" /></a>";}
if(data.voice){
	document.getElementById("myLabel").innerHTML +="&nbsp;&nbsp;&nbsp;<a href=\"#\" onClick=\"displayImage('"+t+"');\"><a href=\"#\" onClick=\"playVoice('"+t+"')\"><img width=\"20\" height=\"20\" border=\"0\" src=\"../images/skin/audio.png\" /></a>";
}
for(var i=0;i<data.type.length;i++){	
document.getElementById("myLabel").innerHTML +="</br><b>"+data.type[i]+"</b>"+data.textMeaning[i]
}

}
else
{
	
document.getElementById("myLabel").innerHTML ="<font size=\"3\" type=\"verdana\" color=\"green\"><b>"+t+"</b>"+"</font></br>"+data.type+data.textMeaning;
}

   leftVal=e.pageX-25;	  
   topVal=e.pageY-($('#popuup_div').height()+25); 
   $('#popuup_div').css({left:leftVal,top:topVal}).show();

},
error:function(XMLHttpRequest,textStatus,errorThrown){alert("Error in ajax call");}
    });  
	
	}); 

  
function getSelectedText() {    
      if (window.getSelection) return window.getSelection().toString();
      else if (document.getSelection) return document.getSelection();
      else if (document.selection) return document.selection.createRange().text;    
    }  
    function displaymessage()
     {
   $('#popuup_div').hide();  
    $.fn.soundPlay({playerId: 'embed_player', command: 'stop'});
	
   }  
   
  
   
   function displayImage(t){
	   $('#popuup_div').hide();
	   jQuery.ajax({
type:'POST',
data: {t:t },
dataType: "json",
url:'/AELL/bvDictList/dictImageSearch',
success:function(data,textStatus){
document.getElementById("myLabel").innerHTML ="<font size=\"3\" color=\"green\"><b>"+t+"</b></br>"+data.ImageLink;
 var height = $('#popuup_div').height();
       var width = $('#popuup_div').width();
       //calculating offset for displaying popup message
       leftVal=this.pageX;
       topVal=this.pageY; 
       //show the popup message and hide with fading effect
       $('#popuup_div').css({left:leftVal,top:topVal}).show(); 
	 },
error:function(XMLHttpRequest,textStatus,errorThrown){alert("Error in ajax call");}
    }); 
	 
   }
   
   
    function playVoice(t){
		
	
	
	 jQuery.ajax({
type:'POST',
data: {t:t },
dataType: "json",
url:'/AELL/bvDictList/dictVoiceSearch',
success:function(data,textStatus){
 $(document).ready(function() {
            $.fn.soundPlay({url: '../music/'+data.VoiceLink, playerId: 'embed_player', command: 'play'});
           
      
    });
	 },
error:function(XMLHttpRequest,textStatus,errorThrown){alert("Error in ajax call");}
    });    
	}
	
	document.write("<div id=\"container\"><div id=\"player-ui\"><div class=\"clear player-commands\"><label id=\"myLabel\"></label> </div></div></div>")
   