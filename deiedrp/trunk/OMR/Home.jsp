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
    
    <title>Online OMR Evaluation System</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	

  </head>
  
  <body>
    <div>
    <center>
    <img src="img/Inst_Admin.PNG" align="right" width="15%" height="18%"/>
	<h2><font color = "blue"><center> <bean:message key="label.onOmrEvaSys"/></center> </font></h2>
	        <h3><center><bean:message key="label.openSrcInitiative"/></center> </h3>
            <font size="4" face = "TimesNewRoman"><center> <bean:message key="label.devUnder"/></center> </font></center><br/>
	<hr width="100%"/> 
	</div>
	<jsp:include page="Menu.jsp"></jsp:include>
	<br/><br/>
	
	<%--<a href="uploadCorrectAnsSelect.jsp">correct</a>
	
  --%></body>
</html>
