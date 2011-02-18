<%-- 
    Document   : Designation
    Created on : Jul 5, 2010, 9:59:46 AM
    Author     : Algox
--%>

<%@ taglib prefix="f" uri="http://java.sun.com/jsf/core" %>
<%@ taglib prefix="h" uri="http://java.sun.com/jsf/html" %>
<%@ taglib uri="http://richfaces.org/a4j" prefix="a4j"%>
<%@ taglib uri="http://richfaces.org/rich" prefix="rich"%>

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
            <rich:modalPanel id="pnl" showWhenRendered="true">
                <rich:panel  header="Change Your Password">
                    <h:form>
                        <h:panelGrid columns="2">
                            <h:outputText value="Current Password"/>
                            <h:inputSecret required="required" value="#{UserBean.pass3}"/>
                        </h:panelGrid>
                        <h:panelGrid columns="2">
                            <h:outputText value="New Password"/>
                            <h:inputSecret required="required" value="#{UserBean.pass1}"/>
                        </h:panelGrid>
                        <h:panelGrid columns="2">
                            <h:outputText  value="Varify Password"/>
                            <h:inputSecret required="required" value="#{UserBean.pass2}"/>
                        </h:panelGrid>
                        <h:panelGrid columns="2">
                            <h:commandButton value="Save" action="#{UserBean.editPass}"  />
                            <h:commandButton value="Close"  onclick="Richfaces.hideModalPanel('pnl');"  />
                            <h:messages style="color:red" />
                        </h:panelGrid>
                    </h:form>
                </rich:panel>
            </rich:modalPanel>
        </f:view>
    </body>
</html>
