<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>English Language Lab</title> 
<link rel="shortcut icon" type="image/x-icon" href="${hostname}/images/favicon.ico">
<meta charset=utf-8 />
<meta name="layout" content="contentUser" />

<script type="text/javascript" src="${createLinkTo(dir:'js',file:'jquery-ui-1.8.7.custom.min.js')}"></script>
<script type="text/javascript" src="${createLinkTo(dir:'js',file:'quizLib.js')}"></script>
<script type="text/javascript" src="${createLinkTo(dir:'js',file:'jquery.shuffle.js')}"></script>
<script type="text/javascript" src="${createLinkTo(dir:'js',file:'jquery.jplayer.min.js')}"></script>
<link rel="stylesheet" type="text/css" href="${resource(dir:'css',file:'dragStyle.css')}" />
<link rel="stylesheet" type="text/css" href="${resource(dir:'css',file:'custom2.css')}" />
<link rel="stylesheet" type="text/css" href="${resource(dir:'css',file:'quiz.css')}" />
<link rel="stylesheet" type="text/css" href="${resource(dir:'css',file:'jplayer.blue.monday.css')}" />


<script type="text/javascript"> 

//script to change title dynamically
window.onload = function() {
  changePageTitleDynamically();
  var contentId=getUrlPara('cnt');
  var experimentId=getUrlPara('exp');
  if(contentId==false && experimentId==false)
      displayFirstTab(contentId,experimentId)
}  

function addExtension(me,filename){
   var root_url=getRootUrl();
  me.src=root_url+"images/tab_icon_image/"+filename+".png";
}

function quizAnswer()
{
   var data=$("#mchForm").serialize();
   $("#mContent").load("/aell/home/quizAction",data,function(response,status){
         if (status=="success"){
          $("#mContent").show();
          $("#mchsubmit").hide();
          ($("#headerStatus").val() == "1")? ($("#mChoiceHeadStd").show()):($("#mChoiceHeadStd").hide());
         }
         if(status == "error"){
           $("#mContent").html("<b style='color:red;'>Service could not be established at this moment. Please Try again after some time</b><br><br>");
         }
     });
}

$(document).ready(function() {
  $("#fibQuest").show();
  $("#showDnD").show();
  $("#dndTQuest").show();
});

$(document).ready(function() {
    //jQuery.noConflict(); Un-comment this statement if JQuery conflicts
    $('#contentTabs').tabs();
});
$(document).ready(function() {
   $("#tab-ul a").get(0).onclick();
});

function showCont(subj,exp,contType){
  var contUrl=changeLocation(contType);
  var baseURL = window.document.location.host;
  var playerPath = "http://" + baseURL + "/aell/js/" ;
  $("#tabContents").load("/aell/home/contabs",{'subjectId': subj,'exp':exp,'cnt': contType},function(response,status){
      if (status=="success"){
       $("#mContent").show();
       $("#mchsubmit").show();
       $('#fibQuest').show();
       $("#dndTQuest").show();
       $('#showDnD').show();  
       initDND("assessments");
       /* Create the Draggables and Droppables for DND text Questions */
       $(".dndch").droppable({
		   drop: function(event, ui) {
		   $(this).val(ui.draggable.text());
		   var txt = ui.draggable.text();
		   $(this).css({'width': txt.length+ 'em'})  
		   }
		 });
	   
       $(".dndtGroup").each(function(index,element){
    	   $(element).find(".dndTOptions").draggable({
    		   cursor : 'pointer',
    		   containment : element,
    		   helper : 'clone'
    	   });
       });
       

       ($("#headerStatus").val() == "1")? ($("#mChoiceHeadStd").show()):($("#mChoiceHeadStd").hide());
       /* Initialize the player only when audiofiles exist in the content */
       if($("#playButton").html() != null){
           $("#jquery_jplayer_1").jPlayer({
				swfPath: playerPath,
				solution: "flash, html",
				supplied: "mp3",
				wmode: "window"
			});

		   /* Load the jPlayer with media files. Play the media. Show the player and hide it after 10 seconds  */   	
           $(document).ready(function(){
       		$(".lessonAudio").click(function(e){
       		    var audioSrc = $(this).find("a").attr('href');
       			$("#jquery_jplayer_1").jPlayer("clearMedia");
       		 	$("#jquery_jplayer_1").jPlayer("setMedia", {
						mp3:""+audioSrc+""
				});
       		 	$("#jquery_jplayer_1").jPlayer("load");
       			$("#jp_container_1").animate({"left": "0%"}, "slow").delay(10*1000).hide("slow");
       			$("#jquery_jplayer_1").jPlayer("play");
				/* Prevent href from performing its default action*/
       			e.preventDefault();
       		});
       		
       	});
          
       }
      }
  });
}

$(document).ready(function(){
	$("#jp_show").click(function(){
		$("#jp_container_1").animate({"left": "0%"}, "slow").delay(10*1000).hide("slow");
	});
});
	
</script>
</head>
<body> 
 <div class="dataPosition" id="Data">
   <g:if test="${aellContentTypeMasterList}">
      <div id="contentTabs">
        <ul id="tab-ul">
           <g:each in="${aellContentTypeMasterList}" status="i" var="contentType"> 
               <li>
               <div style="display:inline;">
               <a href="#tabContents" onclick="showCont(${session.subjectId},${session.experimentId},${contentType.id},${i});">
               <div id="myBox">
               <img src="${hostname}/images/tab_icon_image/${contentType.contentTypeIcon}" width="20px" height="20px" id="tabimg" style="border:0;" onerror="javascript:addExtension(this,'${contentType.contentTypeIcon}');" />
               </div>
               <div class="tabText">${contentType.contentTypeName}</div>
				</a>
               </div>
               </li>   
            </g:each> 
        </ul>
        <div id="tabContents"></div>
        </div>
   </g:if> 
</div>
<!-- New Divs for Jquery Audio/Video PLayer - jPlayer -->
	<div id="jquery_jplayer_1" class="jp-jplayer"></div>
	<div id="jp_show" class="jpShow-audio">&#x266b;</div>
		<div id="jp_container_1" class="jp-audio">
			<div class="jp-type-single">
				<div class="jp-gui jp-interface">
					<ul class="jp-controls">
						<li><a href="javascript:;" class="jp-play" tabindex="1">play</a></li>
						<li><a href="javascript:;" class="jp-pause" tabindex="1">pause</a></li>
						<li><a href="javascript:;" class="jp-stop" tabindex="1">stop</a></li>
						<li><a href="javascript:;" class="jp-mute" tabindex="1" title="mute">mute</a></li>
						<li><a href="javascript:;" class="jp-unmute" tabindex="1" title="unmute">unmute</a></li>
						<li><a href="javascript:;" class="jp-volume-max" tabindex="1" title="max volume">max volume</a></li>
					</ul>
					<div class="jp-progress">
						<div class="jp-seek-bar">
							<div class="jp-play-bar"></div>
						</div>
					</div>
					<div class="jp-volume-bar">
						<div class="jp-volume-bar-value"></div>
					</div>
					<div class="jp-time-holder">
						<div class="jp-current-time"></div>
						<div class="jp-duration"></div>

						<ul class="jp-toggles">
							<li><a href="javascript:;" class="jp-repeat" tabindex="1" title="repeat">repeat</a></li>
							<li><a href="javascript:;" class="jp-repeat-off" tabindex="1" title="repeat off">repeat off</a></li>
						</ul>
					</div>
				</div>
				<div class="jp-title">
					<ul>
						<li>AELL</li>
					</ul>
				</div>
				<div class="jp-no-solution">
					<span>Update Required</span>
					To play the media you will need to either update your browser to a recent version or update your <a href="http://get.adobe.com/flashplayer/" target="_blank">Flash plugin</a>.
				</div>
			</div>
		</div>
<!-- End of Section for Jquery Audio/Video Player - jPlayer -->
</body>
</html>
