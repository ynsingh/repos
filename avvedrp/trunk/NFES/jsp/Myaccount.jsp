<%@ page contentType="text/html; charset=utf-8" pageEncoding="UTF-8" language="java" import="javax.sql.DataSource,javax.naming.Context,javax.naming.InitialContext,java.sql.*,java.util.*,java.io.FileInputStream" errorPage="" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">

<jsp:useBean id="getUserDetails" class="com.erp.nfes.GetRecordValue" scope="session"/> 
<jsp:useBean id="db" class="com.erp.nfes.ConnectDB" scope="session"/> 
<jsp:useBean id="ml" class="com.erp.nfes.MultiLanguageString" scope="session"/> 

<HTML lang=en-US dir=ltr xmlns="http://www.w3.org/11001/xhtml"><HEAD
profile=http://gmpg.org/xfn/11><TITLE>Change Password</TITLE>
<META http-equiv=Content-Type content="text/html; charset=UTF-8">

<LINK media=screen href="../css/oiostyles.css" type=text/css rel=stylesheet>
<META content="MSHTML 6.00.2900.5694" name=GENERATOR>

</HEAD>
<BODY class="bodystyle">

<%
String usertop=null;
try
{
	usertop=request.getUserPrincipal().getName();
}catch(Exception e)
{
}
%>

<script language="JavaScript" type="text/javascript" ></script>
<script type="text/javascript">
var ps="";
function clearall()
{
	if(document.forms['user'].oldpass){
		document.forms['user'].oldpass.value="";
	}	
	document.forms['user'].newpass.value="";
	document.forms['user'].cnewpass.value="";
	
}
function passwordChanged()
{	
	
	var strength = document.getElementById("strength");
	var strongRegex = new RegExp("^(?=.{8,})(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])(?=.*\\W).*$", "g");
	var mediumRegex = new RegExp("^(?=.{7,})(((?=.*[A-Z])(?=.*[a-z]))|((?=.*[A-Z])(?=.*[0-9]))|((?=.*[a-z])(?=.*[0-9]))).*$","g");
	var enoughRegex = new RegExp("(?=.{6,}).*", "g");
	var pwd = document.getElementById("newpass");
	if (pwd.value.length==0) {
		strength.innerHTML ='<%=ml.getValue("type_password")%>';ps="";
	} else if (false == enoughRegex.test(pwd.value)) {
		strength.innerHTML = '<%=ml.getValue("more_characters")%>';ps="MC";
	} else if (strongRegex.test(pwd.value)) {
		strength.innerHTML = '<span style="color:green"><%=ml.getValue("strong")%></span>';ps="S";
	} else if (mediumRegex.test(pwd.value)) {
		strength.innerHTML = '<span style="color:orange"><%=ml.getValue("medium")%></span>';ps="M";
	} else { 
		strength.innerHTML = '<span style="color:red"><%=ml.getValue("weak")%></span>';ps="W";
	}
}
function checkval(){	
	var a = document.forms['user'].newpass.value;	
	var b = document.forms['user'].cnewpass.value;
	var c=document.forms['user'].oldpass.value;	
	if(a=="" || b=="" || c=="" ){
		alert("Please enter mandatory fields.");
		return false;
	}
	
	if(ps=="MC" || ps=="W"){
		alert( "Weak Password enter new one");
		return false;
	}	
	if( a != b ){
		alert("Typed in passwords do not match");
		return false;
	}

	else
	{
		var answer = confirm ("Are sure you want to change your password?");
		if (answer){
			return true;
		}else{
			return false;
		}
	}
	
}
</script>

<form id="user" action="../ChangePasswordServlet" method="post" name="user" onSubmit="return checkval()">
<div class="listdiv">
<h4>&nbsp;&nbsp;&nbsp;<%=ml.getValue("change_password")%> </h4> 
<table style="background: none repeat scroll 0 0 #CCE6F3;" align="center" width="98%">  	

<tr>
<td colspan=2> &nbsp;
<%    		
String s = request.getParameter("value");
if( s != null ){
if(s.equals("0")){
	%>
	<div class="message">			
		Successfully changed password.
	</div>	
	<%
} else if(s.equals("1")){
	%>
	<div class="message">			
		Current password is not correct.
	</div>	
	<%
} else if(s.equals("2")){
	%>
	<div class="message">			
		Current password and new password should not be same.
	</div>	
	<%
}
}
%>
</td>
</tr>

<tr>	 
 <td colspan=2>  </td>
</tr>
<%

Connection conn=null;
Statement theStatement=null;
ResultSet theResult=null;

if(getUserDetails.getRole(request.getUserPrincipal().getName()).equals("ROLE_PROFILE_CREATION")){%>		
<tr>
  <td width="20%"><input  name="user" class="textmedium" type="HIDDEN" value="<%=request.getUserPrincipal().getName()%>" maxlength="50"/></td>  
 </tr> 
<%}
else{
try{
	 String university_name=getUserDetails.getUniversity(request.getUserPrincipal().getName());
	 String username=request.getUserPrincipal().getName();
     conn = db.getMysqlConnection();
     theStatement=conn.createStatement();
     if(getUserDetails.getRole(request.getUserPrincipal().getName()).equals("ROLE_ADMIN")){
     	theResult=theStatement.executeQuery("SELECT username FROM authorities WHERE username<>'"+username +"' order by username");
     }else{
     	theResult=theStatement.executeQuery("SELECT username FROM `staff_profile_masterdetails_v0_values` WHERE username<>'"+username +"' and university='" + university_name + "' order by username");     
     }
%>
<tr>
  <td width="20%"><label  class="labeltext"><%=ml.getValue("username")%></label><label class="mandatory">*</label></td>
   <td width="78%"> <!--<input  name="user" class="textmedium" type="TEXT" value="<%=request.getUserPrincipal().getName()%>" maxlength="50"/>-->
  <SELECT name="user" style="width:204px">
 	<OPTION value=<%=username%>><%=username%></OPTION>
  <%while(theResult.next()){%>
  	<OPTION value=<%=theResult.getString("username")%>><%=theResult.getString("username")%></OPTION>
  <%}%>  
  </SELECT>
  </td>  
</tr>  
<%}
catch(Exception e)	{e.printStackTrace();	}
conn.close();
}

 if(getUserDetails.getRole(request.getUserPrincipal().getName()).equals("ROLE_PROFILE_CREATION")){
%>
<tr>
  <td width="20%"><label  class="labeltext"><%=ml.getValue("current_password")%> </label><label class="mandatory">*</label></td>
  <td width="78%"><input  name="oldpass" class="textmedium" type="password" value="" maxlength="50" onCopy="return false"  onPaste="return false"/></td>
</tr>  
<%}%>
<tr>
  <td><label  class="labeltext"><%=ml.getValue("new_Password")%> </label><label class="mandatory">*</label></td>
  <td> <input  name="newpass" id="newpass" class="textmedium" type="password" value="" maxlength="50" onkeyup="return passwordChanged();" onCopy="return false"  onPaste="return false"/>
  <span id="strength" class="labeltext"><%=ml.getValue("type_password")%></span>
  </td>
</tr>  
<tr>
  <td><label  class="labeltext"><%=ml.getValue("confirm_new_password")%> </label><label class="mandatory">*</label>  </td>
  <td><input  name="cnewpass" class="textmedium" type="password" value="" maxlength="50" onCopy="return false"  onPaste="return false"/></td>
</tr>
<tr>
      <td></td>
      <td><input type="submit"  name="Save"  value='<%=ml.getValue("change_password")%>'/><input type="button"  onClick="clearall()"  value='<%=ml.getValue("clear")%>'/>    </td>
</tr> 
</table>
<br>
</div>
</form>
</BODY></HTML>