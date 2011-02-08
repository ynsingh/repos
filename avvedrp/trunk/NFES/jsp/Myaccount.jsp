<%@ page contentType="text/html; charset=iso-8859-1" language="java" import="javax.sql.DataSource,javax.naming.Context,javax.naming.InitialContext,java.sql.*" errorPage="" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">

<HTML lang=en-US dir=ltr xmlns="http://www.w3.org/11001/xhtml"><HEAD
profile=http://gmpg.org/xfn/11><TITLE>Change Password</TITLE>
<META http-equiv=Content-Type content="text/html; charset=UTF-8"><LINK
media=screen href="../css/style1.css" type=text/css rel=stylesheet>
<META content="MSHTML 6.00.2900.5694" name=GENERATOR>


<style type="text/css">
<!--
.style2 {color: #0000FF}
.style5 {color: #000099}
.style6 {
	color: #000000;	
}

-->
</style>
</HEAD>
<BODY>

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
function clearall()
{
	document.forms['user'].oldpass.value="";
	document.forms['user'].newpass.value="";
	document.forms['user'].cnewpass.value="";
	
}
function checkval(){
	var a = document.forms['user'].newpass.value;	
	var b = document.forms['user'].cnewpass.value;
	
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



<table  width=100%>
<tr>
<td width=30%>

<FORM id="searchform"   action="../j_spring_security_logout" method="get"  ">
<p class="style2" > Welcome &nbsp; <%=usertop%></p>
<a href="../j_spring_security_logout"><image src="../images/logout.jpg"></img> </a> 
</FORM></td>

<td width=70%>

<form id="user" action="../ChangePasswordServlet" method="post" name="user" onSubmit="return checkval()">

<table>  	
<tr>
<td colspan="2"><image src="../images/changepassword.gif" ></img></td>
</tr>
<tr>
<td colspan=2> &nbsp;
<%    		
String s = request.getParameter("value");
if( s != null ){
if(s.equals("0")){
	%>
	<div class="success">			
		Successfully changed your password.
	</div>	
	<%
} else if(s.equals("1")){
	%>
	<div class="errorlogin">			
		Current password is not correct.
	</div>	
	<%
} else if(s.equals("2")){
	%>
	<div class="errorlogin">			
		Current password and new password should not be same.
	</div>	
	<%
}
}
%>
</td>

</tr>
<tr>	 
 <td colspan=2> <p class="style6">To reset your password, provide your current password  </p>
   <p class="style5">&nbsp;</p></td>
</tr>
<tr>
  <td width="198"><label  class="desc">Current Password </label></td>
  <td width="265"><input  name="oldpass" class="text medium" type="password" value="" maxlength="50"/></td>
</tr>  
<tr>
  <td><label  class="desc">New Password </label></td>
  <td> <input  name="newpass" class="text medium" type="password" value="" maxlength="50"/></td>
</tr>  
<tr>
  <td><label  class="desc">Confirm New Password </label>  </td>
  <td><input  name="cnewpass" class="text medium" type="password" value="" maxlength="50"/></td>
</tr>
<tr>
      <td><input type="button"  onClick="clearall()"  value="Clear"/></td>
      <td><input type="submit"  name="Save"  value="Change Password"/>    </td>
</tr>  
</table>
</form>
</td>
</tr>

<tr>
<td><img src="../images/arrowlback.gif" width="22" height="22"> <a href="index.jsp"> Back</a></img> </td>
</tr>     


</table>	

</BODY></HTML>