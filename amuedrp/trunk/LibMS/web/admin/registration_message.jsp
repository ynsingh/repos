<%-- 
    Document   : registration_message
    Created on : Jun 26, 2010, 5:52:34 PM
    Author     : Dushyant
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<%
String msg2=(String)request.getAttribute("registration_id");
%>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body onload="message()" >
        <h1>Hello World!</h1>
    </body>
</html>
<script language="javascript">
    function message()
    {
        alert("<%=msg2%>");
        window.location="login.jsp";
        return false;
    }


</script>