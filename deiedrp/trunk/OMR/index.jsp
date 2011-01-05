<%@ page language="java" import="java.util.*, in.ac.dei.mhrd.omr.img.*" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'index.jsp' starting page</title>
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
  
  <jsp:include page="Menu.jsp"></jsp:include><br/>
  <strong><font face="Arial" color="#000040">
  <%
	if(!((String)request.getAttribute("ProcessSheetMsg")==" "))
	{
	%>
	<%=(String)request.getAttribute("ProcessSheetMsg")%>
	<%} else{%>

  <BR/><bean:message key="label.TotalSheets"/>   <%= (Integer)request.getAttribute("TotalSheets") %><br/>
  <bean:message key="label.Processed"/>  <%=(Integer)request.getAttribute("ProcessedSheets") %><br/>
  <bean:message key="label.RejectedSheets"/> <%=(Integer)request.getAttribute("RejectedSheets")%>
  </font>
  <%} %>
  </body>
</html>
