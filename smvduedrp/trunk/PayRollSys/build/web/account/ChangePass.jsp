<%-- 
    Document   : Designation
    Created on : Jul 5, 2010, 9:59:46 AM
    Author     : Algox
--%>

<%@ taglib prefix="f" uri="http://java.sun.com/jsf/core" %>
<%@ taglib prefix="h" uri="http://java.sun.com/jsf/html" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <f:view>

            <h:outputLabel value="#{UserBean.message}"/>

            
            
        <h:form>
            <h:outputText value="New Password"/>
            <h:inputSecret required="required" value="#{UserBean.password}"/>
            <h:outputText  value="Varify Password"/>
            <h:inputSecret required="required" value="#{UserBean.pass2}"/>
            <h:commandButton value="Save" action="#{UserBean.editPass}"  />
        </h:form>
        </f:view>

    </body>
</html>
