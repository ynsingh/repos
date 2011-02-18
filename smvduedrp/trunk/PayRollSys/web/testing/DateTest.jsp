<%-- 
    Document   : EmpTest
    Created on : Dec 25, 2010, 12:28:56 PM
    Author     : Algox
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsf/core" %>
<%@ taglib prefix="h" uri="http://java.sun.com/jsf/html" %>
<%@ taglib uri="http://richfaces.org/a4j" prefix="a4j"%>
<%@ taglib uri="http://richfaces.org/rich" prefix="rich"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
       

    </head>
    <body>
        <f:view>
            <h:form>
        <rich:panel>
            <h:panelGrid columns="3">
                <h:outputText value="Emp Code"/>
                <h:inputText id="inp" value="#{EmployeeBean.code}"/>
                <rich:suggestionbox for="inp" var="abc" fetchValue="#{abc.code}"  suggestionAction="#{SearchBean.getSuggestion}">
                        <h:column>
                            <h:outputText value="#{abc.name}"/>
                        </h:column>                  
                </rich:suggestionbox>
                
                </h:panelGrid>
        </rich:panel>
        </h:form>
        </f:view>
    </body>
</html>
