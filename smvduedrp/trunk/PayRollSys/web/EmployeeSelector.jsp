<%--
    Document        : Login.jsp
    Created on      : 3:02 AM Saturday, October 02, 2010
    Last Modified   : 3:21 AM Saturday, October 02, 2010
    Author          : Saurabh Kumar
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
        <link rel="stylesheet" type="text/css" href="css/layout.css"/>
        <link rel="stylesheet" type="text/css" href="css/subpage.css"/>
    </head>
    <body class="subpage" id="">
        <h1>Departments</h1>
        <div class="container_form">
            <f:view>

                <h:outputText value="#{EmployeeBean.emp.code}"/>

                <h:form id="getemployee">
                    <h:selectOneMenu value="#{EmployeeBean.emp}">
                        <f:selectItems value="#{EmployeeBean.codedEmp}" />
                    </h:selectOneMenu>
                    <h:commandButton value="Edit"/>
                </h:form>
            </f:view>
        </div>
    </body>
</html>
