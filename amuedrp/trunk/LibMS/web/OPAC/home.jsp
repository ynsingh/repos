<%-- 
    Document   : home.jsp
    Created on : Jul 27, 2010, 3:06:50 PM
    Author     : Mayank Saxena
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="Mayank Saxena" content="MCA,AMU">
        <title>Home Page...</title>
    </head>
    <body>
        

    <a href="/LibMS-Struts/OPAC/opachome.jsp">Go to Home</a>
<%
    String message=(String)request.getAttribute("msg");
    if(message!=null){
       %> <br>
	           <TABLE style="background-color: #E3E4FA;"
                   WIDTH="30%" border="1" align="center">
		      <tr><th><%=message%></th></tr>
		   </TABLE>
   <% }else
        message="";
    %>
    </body>
</html>
