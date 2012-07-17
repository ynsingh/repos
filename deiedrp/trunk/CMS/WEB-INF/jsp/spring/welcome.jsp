<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Welcome </title>
</head>
<body>
	<spring:message code="user.welcome"/> <c:out value="${loginBean.userName}"></c:out>  <br/>
	
	<hr>

	<spring:message code="user.name"/> <c:out value="${loginBean.userName}"></c:out> <br/>
	<spring:message code="user.password"/>	: <c:out value="${loginBean.password}"></c:out> <br/>
	
	<hr>

<table>
<tr><td><a href="spring/userList.htm">List Of User</a></td></tr>

<tr><td><a href="activityMaster/activity.htm">Click here to activity master</a></td></tr>
   
<tr><td> <a href="registration/registrationForm.htm">Click here to Run Process1</a></td></tr>
</table>

</body>

</html>