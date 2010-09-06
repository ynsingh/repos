<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page import="in.ac.dei.edrp.pms.viewer.checkRecord"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%> 
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 
<html> 
	<head>
		<title>JSP for PassWordForm form</title>
		<link rel="stylesheet" href="style/main.css" type="text/css"></link>
	<link rel="stylesheet" href="style/style.css" type="text/css"></link>
	<link rel="stylesheet" type="text/css" href="style/jquery-ui.css" />
<script type="text/javascript" src="javascript/jquery.js"></script>
<script type="text/javascript" src="javascript/jquery-ui.min.js"></script>
<script type="text/javascript">
$(document).ready(function(){

$(function() {
		$("#accordion").accordion({ collapsible: true,
		header: 'h3',
		fillSpace: false
		});
				
	});
});
</script>

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
	
	function sure_user()
	{
		var exp = /^[a-zA-Z][a-zA-Z\d]*$/;
		
		if(exp.test(document.getElementById("newpassworduser").value)==false)
		{
			if(document.getElementById("newpassworduser").value.length==0)
			{
				alert("New Password can't be blank!");
			}
			else
			{
				alert('New Password should be starts with alphabets & contains only alphabets and numbers.');
			}
			document.getElementById("newpassworduser").focus();
			return false;					
		}
		else if(exp.test(document.getElementById("repassworduser").value)==false)
		{
			if(document.getElementById("repassworduser").value.length==0)
			{
				alert("Re-Password can't be blank!");
			}
			else
			{
				alert('Re-Password should be starts with alphabets & contains only alphabets and numbers.');
			}
			document.getElementById("repassworduser").focus();
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
	<%!
	String uid=null;
	String authority=null;
	 %>
     <%
	uid=(String)session.getAttribute("uid");
	authority=checkRecord.duplicacyChecker("Authority","login","login_user_id",uid);
	 %>
	<div style="padding-left:100px;padding-right:100px;padding-top:40px;">

<div id="accordion">

	<h3><a href="#">Change your own password -</a></h3>
	<div>
		<html:javascript formName="passwordform" dynamicJavascript="true" staticJavascript="true" />
	<html:form action="/changeadminpassword" onsubmit="return validatePasswordform(this);">
	<br>
		<table cellspacing="1" cellpadding="6" width="50%" border="0" align="center">
		<tr class="form-element">
			<td class="form-label">
			Old Password :</td>
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
			</table><br>
			<table align="center">
			<tr>
			<td><html:submit value="Change" styleClass="butStnd" onclick="return sure();"  /></td>
			<td><html:reset value="Reset" styleClass="butStnd"/></td>
			<td>
			<html:button property="back" value="Cancel" styleClass="butStnd" onclick="history.back();" />
			</td>
			</tr>
			</table>		
		</html:form>
		
	</div>
	<%
		if(authority.equalsIgnoreCase("Super Admin"))
			{ %>
	<h3><a href="#">Change the system user password -</a></h3>
	<div>
	<html:javascript formName="userpasswordform" dynamicJavascript="true" staticJavascript="true" />
	<html:form action="/changeuserpassword" onsubmit="return validateUserpasswordform(this);">
		<br>
		<table cellspacing="1" cellpadding="6" width="60%" border="0" align="center">
		<tr class="form-element">
		<td class="form-label">
			User Id : </td>
			<td class="form-widget">
			<html:select property="userid" indexed="userid" style="width: 270px;">
			<html:option value="--Select--">--Select--</html:option>
			<sql:query var="validuser" dataSource="jdbc/mydb">
				select distinct login_user_id from login where authority!='Super Admin' order by login_user_id asc
			</sql:query>
			<c:forEach var="row" items="${validuser.rows}">
			<html:option value="${row.login_user_id}"></html:option>
			</c:forEach>
			</html:select><html:errors property="userid"/><font color="red" size="2">*</font>
			</td></tr>
			
			<tr class="form-element">
			<td class="form-label">New Password :</td>
			<td class="form-widget">
			<input type="password" name="newpassworduser"  id="newpassworduser" value="" size="30"/><font color="red" size="2">*</font></td>
			<td style="position: fixed;">
			<html:errors property="newpassworduser"/></td>
			</tr>
			<tr class="form-element">
			<td class="form-label">Confirm New Password :</td>
			<td class="form-widget">
			<input type="password" name="repassworduser"  id="repassworduser" value="" size="30"/><font color="red" size="2">*</font></td>
			<td style="position: fixed;"><html:errors property="repassworduser"/></td>
			</tr>
			</table><br>
			<table align="center">
			<tr>
			<td><html:submit value="Change" styleClass="butStnd" onclick="return sure_user();"  /></td>
			<td><html:reset value="Reset" styleClass="butStnd"/></td>
			<td>
			<html:button property="back" value="Cancel" styleClass="butStnd" onclick="history.back();" />
			</td></tr>
			</table>
		</html:form>
		</div>
	<%} %>
		</div>
	</div>
</body>
</html>

