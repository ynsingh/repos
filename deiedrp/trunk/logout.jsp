<%@ page import="java.sql.*" %>
<html>
<head>

</head>
<body>
<% 
session.invalidate();
response.sendRedirect("login.jsp"); 

%>
</body>
</html>
