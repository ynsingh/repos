<%-- 
    Document   : Logout
    Created on : Jul 2, 2010, 2:27:34 PM
    Author     : Algox
--%>

<%@page import="javax.faces.context.FacesContext"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <%
           FacesContext.getCurrentInstance().getExternalContext().getSessionMap().clear();
           response.sendRedirect("Login.faces");
        %>
    </head>
    <body>
        <h1>Hello World!</h1>
    </body>
</html>
