<%@ page language="java" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%> 
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
 
<html> 
	<head>
		<title>JSP for PassWordForm form</title>
		<link rel="stylesheet" href="style/main.css" type="text/css"></link>
	<link rel="stylesheet" href="style/style.css" type="text/css"></link>
	
	<script language="java" type="text/javascript">
	
	function sure()
	{
		var exp = /^[a-zA-Z][a-zA-Z\d]*$/;
		
		if(exp.test(document.getElementById("oldpassword").value)==false)
		{
			if(document.getElementById("oldpassword").value.length==0)
			{
				alert("Old Password can't be blank!");
			}
			else
			{
				alert('Old Password should be starts with alphabets & contains only alphabets and numbers.');
			}
			document.getElementById("oldpassword").focus();
			return false;
		}					
		else if(exp.test(document.getElementById("newpassword").value)==false)
		{
			if(document.getElementById("newpassword").value.length==0)
			{
				alert("New Password can't be blank!");
			}
			else
			{
				alert('New Password should be starts with alphabets & contains only alphabets and numbers.');
			}
			document.getElementById("newpassword").focus();
			return false;					
		}
		else if(exp.test(document.getElementById("repassword").value)==false)
		{
			if(document.getElementById("repassword").value.length==0)
			{
				alert("Re-Password can't be blank!");
			}
			else
			{
				alert('Re-Password should be starts with alphabets & contains only alphabets and numbers.');
			}
			document.getElementById("repassword").focus();
			return false;					
		}
	}
	</script>
	</head>
	<body>
	<%
	String mysession=(String)session.getAttribute("mysession");
	if(mysession==null)
	{
		response.sendRedirect("login.jsp");  
	}
	%>
	<html:javascript formName="passwordform" dynamicJavascript="true" staticJavascript="true" />
	<html:form action="/changepass" onsubmit="return validatePasswordform(this);">
	
	<div id="main_title">
		 <font color="#0044ff"> Change Password:</font>
	  </div><br>
		<table style="padding-left: 25%;padding-top: 3%" cellpadding="2" cellspacing="4" border="0">
		<tr class="form-element">
			<td class="form-label">Old password :</td>
			<td class="form-widget">
			<input type="password" name="oldpassword"  id="oldpassword"  value=""  size="30"/><font color="red" size="2">*</font></td>
			<td style="position: fixed;"><html:errors property="oldpassword"/></td>
			
			</tr>
			
			<tr class="form-element">
			<td class="form-label">New Password :</td>
			<td class="form-widget">
			<input type="password" name="newpassword"  id="newpassword" value="" size="30"/><font color="red" size="2">*</font></td>
			<td style="position: fixed;">
			<html:errors property="newpassword"/></td>
			
			</tr>
			
			<tr class="form-element">
			<td class="form-label">Confirm New Password :</td>
			<td class="form-widget">
			<input type="password" name="repassword"  id="repassword" value="" size="30"/><font color="red" size="2">*</font></td>
			<td style="position: fixed;"><html:errors property="repassword"/></td>
			
			</tr>
			</table>
			<table style="padding-left: 30%;padding-top: 2%">
			<tr>
			<td><html:submit value="Change" onclick="return sure();"  /></td><td><html:reset value="Reset"/></td>
			<td>
			<html:button property="back" value="Cancel" onclick="history.back();" />
			</td>
			</tr>
			</table>		
		</html:form>
	</body>
</html>

