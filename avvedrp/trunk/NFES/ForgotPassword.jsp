<%@ page contentType="text/html; charset=utf-8" pageEncoding="UTF-8" language="java" import="java.lang.*,java.io.*,java.sql.*,java.util.*" errorPage="" %>
<jsp:useBean id="ml" class="com.erp.nfes.MultiLanguageString" scope="session"/> 

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">
<HTML lang=en-US dir=ltr xmlns="http://www.w3.org/11001/xhtml"><HEAD 
profile=http://gmpg.org/xfn/11><TITLE>Forgot Password</TITLE>
<META http-equiv=Content-Type content="text/html; charset=UTF-8">

<META content="MSHTML 6.00.2900.5694" name=GENERATOR>
<%@ taglib prefix='c' uri='http://java.sun.com/jstl/core_rt' %>
<LINK media=screen href="./css/oiostyles.css" type=text/css rel=stylesheet>

<script>

function updateAccount(){
	if( checkValues() ){
		document.forms['usrForm'].submit();
	}	
}

function checkValues(){
	var frmObj = document.forms['usrForm'];
	var userName = frmObj.userName.value;	
	if( userName == ""  ){
		alert("<%=ml.getValue("user_name_input_prompt")%>");
		return false;
	}	
	return true;
}


function showlogin()
{
location.href="login.jsp";
}
</script>
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
<span class="classLink">&nbsp;<a href='./login.jsp' target="body" title="Home"><font color="#174664"><b><%=ml.getValue("home")%></a>&nbsp;|</b></font></span>
</div>
</div>
</div>
<form id="usrForm" action="./ResetPasswordController" method="POST">
<div  class="listdiv" style="margin:200px 0px 0px 0px;">
	<h1></h1>
	<div style= "margin:10px;background-color:#386890;width:98%;height:25px">&nbsp;<br><br><br>		
	</div>
	
	<div style= "margin:10px">
		<%
					 if ((request.getParameter("invalidUser") != "")&&(request.getParameter("invalidUser") != null)) {%>
						<div class="message">			
						<%=ml.getValue("user_name_validation_message")%>
						</div>		             
					<%  } 		 
		%>
	<h2><%=ml.getValue("lost_password")%></h2>
	<h4><%= ml.getValue("forgot_password_info")%>.</h4>
	</div>		
  	      
  	     <table style="background-color: #d5e5ed;" align="center" width="98%"; >
  			
		 <TR>  
		 <td colspan="2"><h4 style="color:#000000"> <%=ml.getValue("user_name_input_prompt")%></h4></td> 
		 </TR>
			 
		
             
	       <TR> 
		  <!--<TD><label class="desc">Email</label></TD>-->
		  <td><input class="textmedium" id="userName" name="userName" > </td>
               </TR>	
			   	             
 	        <tr>
		<td><input type="button"  id="btnUpdateAccount" onClick="updateAccount()" value="<%=ml.getValue("send")%>"/>
		<input type="button"  value="<%=ml.getValue("cancel")%>"  id="btncancel" onClick="showlogin();"></td>	
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
</body>

</HTML>
