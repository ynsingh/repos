 // JavaScript Document
 //to get server url
 var root_url=getRootUrl();
 var close_img=root_url+"images/skin/close.jpg";
 var pic_imag=root_url+"images/skin/imag.png";
 var audio_image=root_url+"images/skin/audio.png";
document.write("<div class=\"popup_msg\" id=\"popuup_div\"><img  class=\"close_img\" align=\"right\" src='"+close_img+"' onclick=\"closeBox()\" /><br/> <label id=\"myLabel\"></label> </div>");
//a double click event in the index page is handled here
 jQuery(document).dblclick(function(e) { 
 var url_param_pg =getUrlPara('pg');
 if(url_param_pg=='main')//then it will be the index page
 {
    popUpBox(e);//to popup the message box
  } 
});   
   
//a double click event in the content page  is handled here
$(document).ready(function(){
//double click event within the div tag of content page
$("#innerData").dblclick(function (e) { 
      popUpBox(e);//to popup the message box
}); 
});//document.ready
  

  
//function which display the popup box

function popUpBox (e)
{
document.getElementById("myLabel").innerHTML ="";
var text = getSelectedText();    
var textt= text.split(' ').join('');
var t=textt.replace(/['"]/g,'');
//ajax call to retrieve word meaning from the database
jQuery.ajax({
type:'POST',
data: {t:t },
dataType: "json",
url:root_url+"aellDictList/dictSearch",
success:function(data,textStatus){
if(data.type.length!=0){
document.getElementById("myLabel").innerHTML ="<font size=\"3\" color=\"green\"><b>"+t+"</b>"
if(data.image){
document.getElementById("myLabel").innerHTML +="&nbsp;&nbsp;&nbsp;<a href=\"#\"  onClick=\"displayImage('"+t+"','"+e.pageY+"');\"><img  width=\"20\" height=\"20\" src='"+pic_imag+"' border=\"0\"/></a>";}
if(data.voice){
	document.getElementById("myLabel").innerHTML +="&nbsp;&nbsp;&nbsp;<a href=\"#\" onClick=\"displayImage('"+t+"');\"><a href=\"#\" onClick=\"playVoice('"+t+"','"+e.pageY+"')\"><label id=\"audioImage\" border=\"0\"><img border=\"0\" width=\"20\" height=\"20\" src='"+audio_image+"' /></label></a>";
}
for(var i=0;i<data.type.length;i++){	
document.getElementById("myLabel").innerHTML +="<br/><b>"+data.type[i]+"</b>"+data.textMeaning[i]
}
}
else
{
document.getElementById("myLabel").innerHTML ="<font size=\"3\" type=\"verdana\" color=\"green\"><b>"+t+"</b>"+"</font><br/>"+data.type+data.textMeaning;
}
leftVal=e.pageX-25;	  
topVal=e.pageY-($('#popuup_div').height()+25); 
//call the css file for displaying pop up message
$('#popuup_div').css({left:leftVal,top:topVal}).show();
},
error:function(XMLHttpRequest,textStatus,errorThrown){}
});  
jQuery(document).click(function(e) {  $('#popuup_div').hide();});
}


//function to get the full selected text

function getSelectedText() {    
if (window.getSelection) return window.getSelection().toString();
    else if (document.getSelection) return document.getSelection();
    else if (document.selection) return document.selection.createRange().text;    
}  

//function to close the pop up box

function closeBox()
{
$('#popuup_div').hide();  
}  
   
  
//function which displays the image of the selected word means

function displayImage(t,e){
$('#popuup_div').hide();
//ajax call to retrieve image source from the database
jQuery.ajax({
 type:'POST',
 data: {t:t },
 dataType: "json",
 url:root_url+"aellDictList/dictImageSearch",
 success:function(data,textStatus){
 var inner_image_url=root_url+"images/DictImages/"+data.ImageLink;
 document.getElementById("myLabel").innerHTML ="<img height=\"210\" width=\"172\" src='"+inner_image_url+"' />";
 var height = $('#popuup_div').height();
 var width = $('#popuup_div').width();
//calculating offset for displaying popup message
//leftVal=this.pageX;
topVal=e-($('#popuup_div').height()+25); 
//show the popup message and hide with fading effect
$('#popuup_div').css({top:topVal}).show(); 	   
},
error:function(XMLHttpRequest,textStatus,errorThrown){}
}); 
}
   
//function which plays the audio corresponding to the selected text
   
function playVoice(t,e){		
document.getElementById("audioImage").style.display='none';	
//ajax call to retrieve voice source from the database
jQuery.ajax({
type:'POST',
data: {t:t },
dataType: "json",
url:root_url+"aellDictList/dictVoiceSearch",
success:function(data,textStatus){
$(document).ready(function() {	 
//	topVal=($('#popuup_div').height()-50); 					
 filename=root_url+'uploads/audio/'+data.VoiceLink;
 document.getElementById("myLabel").innerHTML+='<object id="mediaPlayer" width="290" height="50" '
+'classid="CLSID:22d6f312-b0f6-11d0-94ab-0080c74c7e95" '
+'codebase="http://activex.microsoft.com/activex/controls/mplayer/en/nsmp2inf.cab#Version=5,1,52,701" '
+'standby="Loading Microsoft Windows Media Player components..." type="application/x-oleobject">'
+'<param name="fileName" value="'+filename+'">'
+'<param name="animationatStart" value="true">'
+'<param name="transparentatStart" value="true">'
+'<param name="autoStart" value="true">'
+'<param name="showControls" value="true">'
+'<param name="loop" value="false">'
+'<embed type="application/x-mplayer2" '
+'pluginspage="http://microsoft.com/windows/mediaplayer/en/download/" '
+'bgcolor="101010" showcontrols="true" width="172" height="50" '
+'src="'+filename+'" autostart="true" designtimesp="5311" loop="false">'
+'</embed>'
+'</object>'     
});
//leftVal=e.pageX-25;	  
topVal=e-($('#popuup_div').height()+25); 
$('#popuup_div').css({top:topVal}).show();
},
error:function(XMLHttpRequest,textStatus,errorThrown){}
});    
}	
  