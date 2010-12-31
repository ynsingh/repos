<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">
<HTML lang=en-US dir=ltr xmlns="http://www.w3.org/11001/xhtml">
<HEAD profile=http://gmpg.org/xfn/11>
<TITLE>Staff Registration</TITLE>
<META http-equiv=Content-Type content="text/html; charset=UTF-8">

<LINK media=screen href="css/style1.css" type=text/css rel=stylesheet> 

<META content="MSHTML 6.00.2900.5694" name=GENERATOR>
<%@ taglib prefix='c' uri='http://java.sun.com/jstl/core_rt' %>


<script>

function createAccount(){
	var frmObj = document.forms['userForm'];	
	if( checkValues() ){			
		var frmObj = document.forms['userForm'];
		var answer = confirm ("Create an account?");
		if (answer){			     
			     document.forms['userForm'].submit();			     
			   }
		else{			
			return false;			
		    }		
		}		
	}	


function checkValues(){	
	var frmObj = document.forms['userForm'];
	var user = frmObj.userName.value;
	var pwd = frmObj.userPassword.value;
	var email = frmObj.emailId.value;
	var pwd2 = frmObj.userPassword2.value;
	var email2 = frmObj.emailId2.value;
	if( user == "" || pwd == "" || email == "" ){
		alert("Required Fields can not be Left Unfilled!!!");
		return false;
	}
	if( pwd != pwd2 ){
		alert( "Passwords you entered are not matching");
		return false;
	}
	if( email != email2 ){
		alert( "Email IDs you entered are not matching");
		return false;
	}	
	return validateEmail( email );	
}


function validateEmail(email) {   
    var re = /^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
	//"
    if (! email.match(re)) {
    	alert("Please enter a valid e-mail");
        return (false);
    }
    return(true);    
}

<!-- Added by ManuSoman to prevent Special Symbol Entry in UserName Field for Bug#22921 -->
<!-- This fuction allows only alphanumerical characters,undescore,and dot in UserName field --> 
var r={'special':/[^a-zA-Z0-9_.]/g}
function valid(o,w)
{
  o.value = o.value.replace(r[w],'');
} 
<!-- End of Added by ManuSoman -->
</script>

<link href="css/forms000.css" rel="stylesheet" type="text/css"> 

</HEAD>
<BODY>

<form id="userForm" action="ProfileCreationServlet" method="post"  >
<script language="JavaScript" type="text/javascript" src="js/menu.js"></script> 
<img src="images/createaccount.gif">                          
<%
 if ((request.getParameter("userExists") != "")&&(request.getParameter("userExists") != null)) {%>
	<div class="errorlogin">			
		User already exists..
	</div>		             
<%  } 
 if ((request.getParameter("emailExists") != "")&&(request.getParameter("emailExists") != null)) {%>	             
	<div class="errorlogin">			
	The email you entered already exists..
	</div>	

<%  }
%>
<table>  	
	<tr>
	<td rowspan="7"><img src="images/StaffReg.gif"></img></td>			
	</tr>
	<tr>		   
	<td width=><label class="desc">Full Name</label></td>
	<td><input class="text medium" id="userFullName" name="userFullName" ></td>		   
	</tr>	
	
	<tr>		   
	<td width=><label class="desc">  Username</label></td>
	<td><input class="text medium" id="userName" name="userName" ></td>		   
	</tr> 
				   
	<tr> 		                 
	<td><label class="desc">  Password</label></td>
	<td><input type="password" id="userPassword"  name="userPassword" class="text medium"></td>
	</tr>
	
	<tr>
	<td><label class="desc"> Confirm Password</label></td>
	<td><input type="password" id="userPassword2"  name="userPassword2" class="text medium"></td>                
	</tr>                 
		 
	<tr>                                  
	<td><label class="desc">  Email</label></td>
	<td><input  class="text medium" id="emailId" name="emailId" ></td>
	</tr>
	
	<tr>
	<td><label class="desc"> Confirm Email</label></td>
	<td><input  class="text medium" id="emailId2" name="emailId2" ></td>                 
	</tr>		
	<tr>		  
	<td>&nbsp;</td>
	<td>&nbsp;</td>
	<td ><input type="button"  onClick="createAccount()"  value="Create Account"/>	</td>
	</tr>
		  
	<tr>
	<td>&nbsp;</td>
	<td>
	<img src="images/arrowlback.gif"> <a href="index.jsp">Back</a> </img>
	</td>
	</tr>
</table>     
 
</form>
</BODY></HTML>