<%-- 
    Document   : TokenValidationForm
    Created on : Apr 5, 2013, 3:38:50 PM
    Author     : Vinay
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
        <s:form action="EmailVarification" method="port" namespace="/Registration">
            <s:textfield name="emailId" value="icbc.vinay@gmail.com"/>
            <s:textfield name="registrationId" value="10"/>
            <s:textfield name="uuid" value="&%OGEAJEY1"/>
            <s:submit/>
        </s:form>
    </body>
</html>
