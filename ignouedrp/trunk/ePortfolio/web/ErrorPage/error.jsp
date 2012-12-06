<%-- 
    Document   : error
    Created on : Aug 26, 2011, 3:05:47 PM
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
        <h1 align="center">Error Occurred.</h1>
        <div class="w100 fl-l tc fbld fcgreen">
            <s:property value="msg"/>
        </div>
        <FORM><INPUT TYPE="button" VALUE="Back" onClick="history.go(-1);return true;"></FORM>
    </body>
</html>
