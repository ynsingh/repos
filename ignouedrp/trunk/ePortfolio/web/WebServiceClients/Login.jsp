<%-- 
    Document   : newjsp
    Created on : Jul 17, 2013, 5:37:33 PM
    Author     : vinay
--%>

<%@page import="java.io.Serializable"%>
<%@page import="java.util.Date"%>
<%@page import="org.apache.log4j.Logger"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World!</h1>
        <s:form action="WSLogin" namespace="/">
            <s:textfield name="userId"/>
            <s:textfield name="password"/>
            <s:submit/>
        </s:form>

    </body>
</html>
