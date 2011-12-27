<%-- 
    Document   : addRoom
    Created on : Oct 19, 2011, 3:52:42 PM
    Author     : edrp01
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
	<title>Add new Room</title>
	<LINK rel="stylesheet" href="<%=request.getContextPath()%>/css/common.css">
 <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>

<body>

<div align="center">
<center>
 <table bgcolor="yellow" width="100%" border="1">
	<tr>
            <td  width="100%" align="center" class="headerStyle">Welcome To Online Chatting</td>
	</tr>

</table>
</center>
</div>
<br>
<%
  String t=(String)request.getParameter("x");

session.setAttribute("x", t);
%>

<div align="center">
<center>
<form action="<%=request.getContextPath()%>/listrooms1.do" method="post">

<table width="80%" cellpadding="0" cellspacing="0" border="0">

	<tr>
		<td colspan="2"><h2>Add new Room</h2></td>
	</tr>
	<tr>
		<td><b>Room Name</b></td><td><input type="text" name="rn"></td>
	</tr>
	<tr>

		<td><b>Description</b></td><td><textarea rows="5" cols="30" name="rd"></textarea></td>
	</tr>
	<tr>
		<td>&nbsp;</td><td><input type="submit" value="Submit"></td>
	</tr>
</table>
</form>
</center>
</div>
<DIV align="center">

<CENTER>
<TABLE width="100%" border="0" align="center" cellpadding="3" cellspacing="0">
	<TR>
		<TD width="100%" align="center"><a href="listrooms.jsp">Back</a></TD>
	</TR>
</TABLE>
</CENTER>
</DIV>

</body>

</html>
