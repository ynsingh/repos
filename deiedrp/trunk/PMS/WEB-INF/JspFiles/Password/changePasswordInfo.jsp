<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%> 
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="style/style.css" type="text/css"></link>
<title>Successful changed Password Page</title>
</head>
<body>
	<div style="font: normal;color: blue;font-size: medium;padding-top: 6%;padding-left: 5%;">
	<bean:message key="label.dear"/> '<%=(String)session.getAttribute("uid")%>' <%=request.getAttribute("passinfo") %>
		</div>	<br><br>	
		<input type="button" value='<bean:message key="label.back.button" />' class="butStnd" onclick="history.back();" />					  		
</body>
</html>