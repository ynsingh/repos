<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%> 
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Successful changed Password Page</title>
</head>
<body>
	<div style="font: normal;color: blue;font-size: medium;padding-top: 6%;padding-left: 5%;">
	Dear, '<%=(String)session.getAttribute("uid")%>' <%=request.getAttribute("passinfo") %>
		</div>	<br><br>	
		<html:button property="back" value="Back to Previous Page" onclick="history.back();" />					  		
</body>
</html>