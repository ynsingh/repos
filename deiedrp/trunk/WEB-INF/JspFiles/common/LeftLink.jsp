<%@ page language="java" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%> 
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'index1.jsp' starting page</title>
    
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
 
Navigation page 
<br />

<table align="center" dir="rtl">
  <tr>
    <td class="link" nowrap="nowrap"><html:link action="welcome" styleClass="B"> Goto Welcome Page</html:link></td></tr>
     <tr>
    <td class="link" nowrap="nowrap"><html:link action="viewproject" styleClass="B"> View Project </html:link></td></tr>
     <tr>
    <td class="link" nowrap="nowrap"><html:link action="ganttchart" styleClass="B"> View Gantt Chart </html:link></td>
    </tr>
    
   
</table>
</body>
</html>