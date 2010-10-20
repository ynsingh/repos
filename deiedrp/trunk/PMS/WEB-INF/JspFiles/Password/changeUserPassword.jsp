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


</script>

	<script language="java" type="text/javascript">
	
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
	
	<div style="padding-left:100px;padding-right:100px;padding-top:40px;">

<div id="accordion">

	
	
	<h3><a href="#"><bean:message key="title.changeSystemUserPassword"/> -</a></h3>
	<div>
	<html:javascript formName="userpasswordform" dynamicJavascript="true" staticJavascript="true" />
	<html:form action="/changeUserpassword" onsubmit="return validateUserpasswordform(this);">
		<br>
		<table cellspacing="1" cellpadding="6" width="50%" border="0" align="center">
		<tr class="form-element">
		<td class="form-label">
			<bean:message key="label.userId"/> : </td>
			<td class="form-widget">
			<html:select property="userid" indexed="userid" value="${param.userid}" style="width: 200px;">
			<c:choose>
			<c:when test="${sessionScope.authority=='Super Admin'}">
			<html:option value="--Select--">--Select--</html:option>
			<sql:query var="validuser" dataSource="jdbc/mydb">
				select distinct u.user_id from user_in_org uo,user_role_in_org uro,
					user_info u where uo.valid_user_id=u.user_id and 
					uo.valid_key=uro.valid_key and uro.permittedby=(
					select l.login_user_id from login l where l.authority='Super Admin')
			</sql:query>
			<c:forEach var="row" items="${validuser.rows}">
			<html:option value="${row.user_id}"></html:option>
			</c:forEach></c:when>
			
			<c:otherwise>
			<html:option value="--Select--">--Select--</html:option>
			<sql:query var="validuser" dataSource="jdbc/mydb">
				SELECT uio.valid_user_id FROM user_in_org uio,login l
				where uio.valid_user_id=l.login_user_id and uio.valid_orgportal=?;
				<sql:param value="${sessionScope.validOrgInPortal}"/>
			</sql:query>
			<c:forEach var="row" items="${validuser.rows}">
			<html:option value="${row.valid_user_id}"></html:option>
			</c:forEach></c:otherwise>
			</c:choose>
			</html:select><html:errors property="userid"/><font color="red" size="2">*</font>
			</td></tr>
			
			<tr class="form-element">
			<td class="form-label"><bean:message key="label.newPassword"/> :</td>
			<td class="form-widget">
			<input type="password" name="newpassworduser"  id="newpassworduser" value="" size="31"/><font color="red" size="2">*</font></td>
			<td style="position: fixed;">
			<html:errors property="newpassworduser"/></td>
			</tr>
			<tr class="form-element">
			<td class="form-label"><bean:message key="label.confirmNewPassword"/> :</td>
			<td class="form-widget">
			<input type="password" name="repassworduser"  id="repassworduser" value="" size="31"/><font color="red" size="2">*</font></td>
			<td style="position: fixed;"><html:errors property="repassworduser"/></td>
			</tr>
			</table><br>
			<table align="center">
			<tr>
			<td><input type="submit" value='<bean:message key="label.change.button" />' class="butStnd" onclick="return sure_user();"  /></td>
			<td><input type="reset" value='<bean:message key="label.reset.button" />' class="butStnd"/></td>
			<td>
			<input type="button" value='<bean:message key="label.back.button" />' class="butStnd" onclick="history.back();" />
			</td></tr>
			</table>
		</html:form>
		</div>
	
		</div>
	</div>
</body>
</html>

