<%-- 
    Document   : MyTestPage
    Created on : Feb 5, 2011, 11:38:06 AM
    Author     : Algox
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@ taglib uri="http://richfaces.org/a4j" prefix="a4j"%>
<%@taglib prefix="h" uri="http://java.sun.com/jsf/html"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<f:view>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
            <title>JSP Page</title>
        </head>
        <body>
            <h1><h:outputText value="Hello World!"/></h1>

            <h:form>
                <h:selectOneMenu  binding="#{TestEmployee.departmentCombo}">
                    <f:selectItems value="#{DepartmentControllerBean.departments}"/>
                </h:selectOneMenu>
                <a4j:commandButton value="Print" action="#{TestEmployee.print}"/>
            </h:form>


        </body>
    </html>
</f:view>
