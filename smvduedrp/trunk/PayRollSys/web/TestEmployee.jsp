<%--
    Document        : AddUser.jsp
    Created on      : 3:02 AM Friday, October 01, 2010
    Last Modified   : 4:03 AM Saturday, October 02, 2010
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
        <title>Edit Employee</title>
        <link rel="stylesheet" type="text/css" href="css/reset.css"/>
        <link rel="stylesheet" type="text/css" href="css/subpage.css"/>
    </head>
    <body class="subpage" id="">
        <div class="container_form">
            <f:view>
                <h:form>
                    
                            <h:outputText value="Employee Code"/>
                            <h:inputText value="#{TestEmployee.code}"/>
                            <h:outputText value="Employee Name"/>
                            <h:inputText value="#{TestEmployee.name}"/>
                            <h:outputText value="Department"/>
                            <h:selectOneMenu value="#{TestEmployee.department}">
                                <f:selectItems value="#{DepartmentBean.departments}"/>
                            </h:selectOneMenu>                        
                        <h:commandButton value="Print" action="#{TestEmployee.processXYZ}"/>
                </h:form>
            </f:view>

        </div>
    </body>
</html>
