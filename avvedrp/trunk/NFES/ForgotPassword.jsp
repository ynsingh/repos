<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">
<HTML lang=en-US dir=ltr xmlns="http://www.w3.org/11001/xhtml"><HEAD 
profile=http://gmpg.org/xfn/11><TITLE>Forgot Password</TITLE>
<META http-equiv=Content-Type content="text/html; charset=UTF-8">
<LINK media=screen href="css/style1.css" type=text/css 
rel=stylesheet>
<META content="MSHTML 6.00.2900.5694" name=GENERATOR>
<%@ taglib prefix='c' uri='http://java.sun.com/jstl/core_rt' %>

<script>

function updateAccount(){
	if( checkValues() ){
		document.forms['usrForm'].submit();
	}	
}

function checkValues(){
	var frmObj = document.forms['usrForm'];
	var user = frmObj.userName.value;	
	if( user == ""  ){
		alert("Either User Name  is required !!!");
		return false;
	}	
	return true;
}

</script>
<link href="css/forms000.css" rel="stylesheet" type="text/css">
</HEAD>
<BODY>
<form id="usrForm" action="./ForgotPasswordController" method="POST">
<script language="JavaScript" type="text/javascript" ></script>
		<%
		 if ((request.getParameter("invalidUser") != "")&&(request.getParameter("invalidUser") != null)) {%>
			<div class="errorlogin">			
			User name you entered is not correct..
			</div>		             
		<%  } 		 
		%>
  <table width="50%" border="0"  align="center" cellpadding="0" cellspacing="0" >
			 <TR>  
			 <td colspan="2"><h5 style="color:#000000"> Please enter your username .Your account details will be showed to you.</h5></td> 
			 </TR>
			 
			 <tr><td>&nbsp;</td></tr>
             
			 <TR> 
			  <TD><label class="desc">UserName</label> 
			  </TD><td><input class="text medium" id="userName" name="userName" > </td>
              </TR>	
			   	             
 	          <tr><td>&nbsp;</td>	
			  <td><input type="button" class="buttonforgotpassword" id="btnUpdateAccount" onClick="updateAccount()" value="Send"/>          </td></tr>         	                        
      	<tr>
      	<td><img src="images/arrowlback.gif"> <a href="index.jsp"> Back</a></img> </td>
      	</tr>     
  </table>
  


</form>

</BODY></HTML>
