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
		alert("Email is required !!!");
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
<div class="innnerBanner">
	<div class="loginLink">
	<span>
	<b><a href="login.jsp" STYLE="TEXT-DECORATION: NONE"><img src="./images/home.jpg">Home</a></b></font>
	</span>
	</div>
</div>

<form id="usrForm" action="./ForgotPasswordController" method="POST">
<div  class="listdiv" style="margin:2px">
	<h1></h1>
	<div style= "margin:10px;background-color:#386890;width:98%;height:25px">&nbsp;<br><br><br>		
	</div>
	
	<div style= "margin:10px">
		<%
					 if ((request.getParameter("invalidUser") != "")&&(request.getParameter("invalidUser") != null)) {%>
						<div class="message">			
						Please enter a valid User Name
						</div>		             
					<%  } 		 
		%>
	<h2>Forgot your password?</h2>
	<h4>Admin will send password to the email address associated with your account.</h4>
	</div>		
  	      
  	     <table style="background-color: #d5e5ed;" align="center" width="98%"; >
  			
		 <TR>  
		 <td colspan="2"><h4 style="color:#000000">  Please type your <b>User Name<b> below.</h4></td> 
		 </TR>
			 
		
             
	       <TR> 
		  <!--<TD><label class="desc">Email</label></TD>-->
		  <td><input class="textmedium" id="userName" name="userName" > </td>
               </TR>	
			   	             
 	        <tr>
		<td><input type="button"  id="btnUpdateAccount" onClick="updateAccount()" value="Send"/>
		<input type="button"  value="Cancel"  id="btncancel" onClick="showlogin();"></td>	
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
