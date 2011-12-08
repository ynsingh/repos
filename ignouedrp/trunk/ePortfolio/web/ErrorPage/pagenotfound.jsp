<%-- 
    Document   : pagenotfound
    Created on : Aug 26, 2011, 3:05:29 PM
Author     : Vinay
Version      : 1
    Version: 1
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%        
           if (session.getAttribute("user_id") == null) {
                pageContext.forward("../login.jsp");
            }
                   
        %>
        <h1 align="center">Page not Found Exception.</h1>
    </body>
</html>
