<head>
<meta http-equiv="Content-Type"
content="text/html; charset=iso-8859-1">
<title>Untitled Document</title>
<style type="text/css">
<!--
body {
text-align:center;
}
form {
margin:0;
padding:0;
}
-->
</style>
<script type="text/javascript">
<!--
function PlayIt(filename){
//filename="http://localhost:8080/AELL/Agar Tum Mil Jao - 1.mp3";
/*document.getElementById("music1").innerHTML='<EMBED height="20" SRC="clockbell.mp3" VOLUME="50" loop="true" controls="console" AUTOSTART="FALSE" width="128">';*/
document.getElementById("music1").innerHTML='<object id="mediaPlayer" width="400" height="400" '
+'classid="CLSID:22d6f312-b0f6-11d0-94ab-0080c74c7e95" '
+'codebase="http://activex.microsoft.com/activex/controls/mplayer/en/nsmp2inf.cab#Version=5,1,52,701" '
+'standby="Loading Microsoft Windows Media Player components..." type="application/x-oleobject">'
+'<param name="fileName" value="'+filename+'">'
+'<param name="animationatStart" value="true">'
+'<param name="transparentatStart" value="true">'
+'<param name="autoStart" value="true">'
+'<param name="showControls" value="false">'
+'<param name="loop" value="true">'
+'<embed type="application/x-mplayer2" '
+'pluginspage="http://microsoft.com/windows/mediaplayer/en/download/" '
+'bgcolor="101010" showcontrols="false" width="400" height="400" '
+'src="'+filename+'" autostart="true" designtimesp="5311" loop="true">'
+'</embed>'
+'</object>'
}


//-->
</script>
</head>
<body  onLoad="PlayIt('../video/Face to face intrvw.mpg')">

<span id="music1">
Your system does cannot find the windows media player that was specified.
</object>
</span>
</body>