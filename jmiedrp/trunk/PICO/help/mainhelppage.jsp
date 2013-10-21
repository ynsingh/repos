<html>
<head>
<title>Jsp Frameset</title>
</head>
<frameset rows="10%,*">
<frame src="frame1.jsp" name="frame1"scrolling="no">
<frameset cols="20%,*">

 <frameset id="frame2" rows="3%,50%"  name="frame2" id="name="frame2"">
      
<frame name="headpageinsideframe2" src="headpageinsideframe2.jsp" />
<frame name="bodypageinsideframe2forcontents" src="bodypageinsideframe2forcontents.jsp" />

   </frameset>
<frame src="frame3.jsp" name="frame3">
</frameset>
</frameset>
</html>