<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'ProcessLog.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
    
	<jsp:include page="header.jsp"></jsp:include><br/>
	<center> <b> <bean:message key="link.proLog"/>: </b></center>
	<table align = "center" width = "90%" border = "1">
	<TR> 
	   <td><bean:message key="label.rollno"/>  </td><td><bean:message key="label.filename"/> </td><td><bean:message key="label.proStatus"/> </td><td><bean:message key="label.proBy"/> </td><Td> <bean:message key="label.proDate"/> </Td></TR>
	   </table>
  </body>
</html>
