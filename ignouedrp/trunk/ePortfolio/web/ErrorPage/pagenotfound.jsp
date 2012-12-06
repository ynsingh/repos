<%-- 
    Document   : pagenotfound
    Created on : Aug 26, 2011, 3:05:29 PM
Author     : IGNOU Team
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
                response.sendRedirect("../Login.jsp");
            }
                   
        %>
        <h1 align="center">Page not Found Exception.</h1>
    </body>
</html>
