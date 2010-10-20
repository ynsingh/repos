<%@ page language="java" pageEncoding="UTF-8"%>
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

function gotoUserHome()
	{
		window.location.href="mainwelcome.do";
	}
function gotoSuperAdminHome()
	{
		window.location.href="welcome.do";
	}
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
		
	</script>
	</head>
	<body>
	
	<div style="padding-left:100px;padding-right:100px;padding-top:40px;">

<div id="accordion">

	<h3><a href="#"><bean:message key="title.changeOwnPassword"/> -</a></h3>
	<div>
		<html:javascript formName="passwordform" dynamicJavascript="true" staticJavascript="true" />
	<html:form action="/changeOwnLoginpassword" onsubmit="return validatePasswordform(this);">
	<br>
		<table cellspacing="1" cellpadding="6" width="50%" border="0" align="center">
		<tr class="form-element">
			<td class="form-label">
			<bean:message key="label.oldPassword"/> :</td>
			<td class="form-widget">
			<input type="password" name="oldpassword"  id="oldpassword"  value=""  size="30"/><font color="red" size="2">*</font></td>
			<td style="position: fixed;"><html:errors property="oldpassword"/></td>
			</tr>
			<tr class="form-element">
			<td class="form-label"><bean:message key="label.newPassword"/> :</td>
			<td class="form-widget">
			<input type="password" name="newpassword"  id="newpassword" value="" size="30"/><font color="red" size="2">*</font></td>
			<td style="position: fixed;">
			<html:errors property="newpassword"/></td>
			</tr>
			<tr class="form-element">
			<td class="form-label"><bean:message key="label.confirmNewPassword"/> :</td>
			<td class="form-widget">
			<input type="password" name="repassword"  id="repassword" value="" size="30"/><font color="red" size="2">*</font></td>
			<td style="position: fixed;"><html:errors property="repassword"/></td>
			</tr>
			</table><br>
			<table align="center">
			<tr>
			<td><input type="submit" value='<bean:message key="label.change.button" />' class="butStnd" onclick="return sure();"  /></td>
			<td><input type="reset" value='<bean:message key="label.reset.button" />' class="butStnd"/></td>
			<td>
			<c:if test="${sessionScope.authority=='User'}">
			<input type="button" value='<bean:message key="label.cancel.button" />' class="butStnd" onclick="gotoUserHome();" />
			</c:if>
			<c:if test="${sessionScope.authority=='Super Admin'}">
			<input type="hidden" value="a" name="rolename" id="rolename"/>
			<input type="button" value='<bean:message key="label.cancel.button" />' class="butStnd" onclick="gotoSuperAdminHome();" />
			</c:if>
			</td>
			</tr>
			</table>		
		</html:form>
	</div></div></div>
</body>
</html>

