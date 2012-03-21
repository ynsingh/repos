<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">
<HTML lang=en-US dir=ltr xmlns="http://www.w3.org/11001/xhtml"><HEAD 
profile=http://gmpg.org/xfn/11><TITLE>NFES: Error Page</TITLE>
<META http-equiv=Content-Type content="text/html; charset=UTF-8">
<META content="MSHTML 6.00.2900.5694" name=GENERATOR>
<LINK media=screen href="./css/oiostyles.css" type=text/css rel=stylesheet>

<style type="text/css" media="screen"> 
#footer {position: absolute; bottom: 0; left: 0px; right:0px; width: 100%; } 
</style>
</HEAD>

<body class="bodystyle">
<div style="background-color: #FFFFFF; margin: 10px -6px;">
<table width=100% style="background-color: #FFFFFF; border=1; margin: 0px;">
<tr>
<td width=5% rowspan="2"><img src="./images/loginheader_logo.PNG" height="80px"  ></td>
</tr>	

<tr>
<td colspan=2 width=95%  align="right" valign="bottom"><img src="./images/loginheader_NFES.PNG" ></td>	
<td width=10%></td>
</tr>
</table>
<div style="background-image: url('./images/innerpageheaderhr.jpg');repeat-x;scroll 0 0 #ef9e00;">&nbsp;</div>
<div class="loginLink_header">
<span><font color="#174664">&nbsp;&nbsp;<img alt="Help" title="Help" onclick="window.open('./UserGuides/UserGuides.html','mywindow','width=1000,height=700,left=0,top=100,screenX=0,screenY=100')" src="/nfes/images/help.gif"><b>&nbsp;&nbsp;|</b></font></span>
<span><font color="#174664">&nbsp;&nbsp;<img alt="About Us" title="About Us" onclick="window.open('./images/AboutUs_NFES.html','mywindow','width=600,height=300,left=0,top=100,screenX=0,screenY=100')" src="/nfes/images/aboutUs.jpg"><b>&nbsp;&nbsp;|</b></font></span>
<span class="classLink">&nbsp;<a href='./login.jsp' target="body" title="Home"><font color="#174664"><b>Login</a>&nbsp;|</b></font></span>
</div>
</div>
</div>
<body class="bodystyle">
   <%
   String errmsg=request.getParameter("errmsg");
   %>
   
  <form  method="post" >
  	
  	<div  class="listdiv" style="margin:200px 0px 0px 0px;">
  	<h1></h1>
  	<div style= "margin:10px;background-color:#386890;width:98%;height:25px">&nbsp;<br><br><br>		
  	</div>
	
	<br><br>
  	
  	<table width=98% border="0"  align="center" cellpadding="0" cellspacing="0">
    	<tr>	
	<td >
	<h2  style="color:#000000"> <%=errmsg%></h2>   
	<br>
      	<h4>Contact System Administrator </h4>
	<br><br><br><br>
        </td>
    	</tr>
  	</table>   	
  	</div>
      </form>      

</BODY>
<div id="footer">
<center>
<div class="footerdiv" > 
Developed by Amrita University under ERP, NME ICT, MHRD
</div>
</center>
</div> 
</form>
</HTML>
