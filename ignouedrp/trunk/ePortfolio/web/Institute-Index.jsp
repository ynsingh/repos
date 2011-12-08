<%-- 
    Document   : Institute-Index
    Created on : Oct 3, 2011, 10:48:39 AM
Author     : Vinay
Version      : 1
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Welcome to Institute Interface</title>
    </head>
    <body>
        <%        
           if (session.getAttribute("user_id") == null) {
                pageContext.forward("login.jsp");
            }
                   
        %>
        <h1>Institute Interface</h1>
    </body>
</html>
