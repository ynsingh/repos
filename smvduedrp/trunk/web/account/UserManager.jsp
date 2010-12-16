<%-- 
    Document   : AddUser
    Created on : Jul 5, 2010, 8:42:41 PM
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
        <title>Add New User</title>
    </head>
    <body>
         <f:view>

             <h2>Add New User</h2>
            <h:form id="users">
                <h:outputText value="User Name"/>
                <h:inputText value="#{UserBean.userName}"/>
                <h:outputText value="Password"/>
                <h:inputSecret value="#{UserBean.password}"/>
                <h:outputText value="Varify Password"/>
                <h:inputSecret value="#{UserBean.userName}"/>
                <h:outputText value="Administrator ?"/>
                <h:selectBooleanCheckbox value="#{UserBean.admin}"/>
                <h:commandButton value="Save" action="#{UserBean.save}"/>


            </h:form>
         </f:view>

    </body>
</html>
