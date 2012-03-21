<%@ page contentType="text/html; charset=utf-8" pageEncoding="UTF-8" language="java" import="java.lang.*,java.io.*,java.sql.*,java.util.*" errorPage="" %>

<jsp:useBean id="db" class="com.erp.nfes.ConnectDB" scope="session"/> 
<jsp:useBean id="ml" class="com.erp.nfes.MultiLanguageString" scope="session"/> 



<%
String lc="";
try{
     lc=request.getParameter("language");
     if(lc==null ||lc.equals("")){
          lc=(String) session.getAttribute("language");
          if(lc==null ||lc.equals("")){lc="en";}
     }
     session.setAttribute("language",lc);
     request.setCharacterEncoding("UTF-8");
     response.setContentType("text/html; charset=utf-8");
     Locale locale=new Locale(lc,"");     
      ml.init(lc);        
}catch(Exception e){
     e.printStackTrace();
}
	
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html>
<head>
<link href="css/oiostyles.css" type="text/css" rel="stylesheet">
<link rel="openid2.provider" href="http://openid.com/server/endpoint/url"/>
<link rel="openid2.local_id" href="http://openid.com/server/endpoint/url"/>
<style type="text/css" media="screen"> 
#footer {position: absolute; bottom: 0; left: 0px; right:0px; width: 100%; } 
</style>
</head>
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
<span class="classLink">&nbsp;<a href='./login.jsp' target="body" title="Home"><font color="#174664"><b><%=ml.getValue("home")%></a>&nbsp;|</b></font></span>
</div>
</div>
</div>

<!--https://www.google.com/accounts/o8/id-->

<!-- <form action="./OpenIDLoginServlet" method="post"> <input class="google openid_large_btn" style="background: #fff url(/resources/images/login/openid-logos.png?v=3); background-position: -1px -1px;" type="image" value=" " />-->
<form action="j_spring_openid_security_check" method="post">
	<div  class="listdiv" style="margin:200px 0px 0px 0px;">
	<h1></h1>	
	<div style= "margin:10px;background-color:#386890;color:white;width:98%;height:25px;font-weight:bold;line-height:25px;">
		<%		
		if ((request.getParameter("error_msg") != "")&&(request.getParameter("error_msg") != null)) {%>			
			&nbsp;<%=ml.getValue("openId_authentication_failed")%>!!!				
		<%} 		 
	%>
	</div>     
     
    <div style= "margin:10px">
	<h2 style="color: #d5e5ed;"></h2>
	<h4> <%=ml.getValue("please_enter_your_openID")%> </h4>
	</div>	
	 
      <table style="background-color: #d5e5ed;" align="center" width="98%"; > 
           	
        <tr>
          <td width="15%"><%=ml.getValue("openId")%></td>          
          <td width="25%">            
            <input type="text" id="openid_identifier" size="50" name="openid_identifier" vALUE=""/>
          </td>
          <td width="5%"><input type="submit" value='<%=ml.getValue("login")%>'/></td>
          <td width="55%"></td>         
        </tr>
      </table>	
       <br>       
	</div>
</form>

<div id="footer">
<center>
<div class="footerdiv" > 
Developed by Amrita University under ERP, NME ICT, MHRD
</div>
</center>
</div> 
</body
</html>

