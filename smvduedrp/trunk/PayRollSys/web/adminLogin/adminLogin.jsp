<%-- 
    Document   : adminLogin
    Created on : Dec 12, 2012, 10:35:06 PM
    Author     : KESU
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsf/core" %>
<%@ taglib prefix="h" uri="http://java.sun.com/jsf/html" %>
<%@ taglib uri="http://richfaces.org/a4j" prefix="a4j"%>
<%@ taglib uri="http://richfaces.org/rich" prefix="rich"%>
<!DOCTYPE html>
<f:view>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
            <title>JSP Page</title>
        </head>
        <body>
            <h:form>
                <h:panelGrid columns="1">
                    <rich:panel style="height:170px; width:1340px;">
                        <div  align="left">
                            <h:graphicImage url="/img/pls1.png"/>
                        </div>
                        <div  align="right">
                            <h:graphicImage url="/img/3_1.PNG" style="margin-top:-100px;"/>
                        </div>
                    </rich:panel>
                    <rich:panel style="height:430px;">
                        <rich:messages>
                                <f:facet name="errorMarker">
                                    <h:graphicImage url="/img/err.png"/>
                                </f:facet>
                            </rich:messages>
                        <rich:panel header="Admin Login" style="margin-left:35%;margin-right:35%;width:30%;margin-top:100px;height:140px;">
                            <h:panelGrid columns="2">
                                <h:outputText value="Admin ID : "/>
                                <h:inputText value="#{OrgProfileBean.adUserId}"/>
                                <h:outputText value="Password"/>
                                <h:inputSecret value="#{OrgProfileBean.adPassword}"/>
                                <f:facet name="footer">
                                    <a4j:commandButton value="Login" action="#{OrgProfileBean.admin}"/>
                                </f:facet>
                            </h:panelGrid>
                        </rich:panel>
                    </rich:panel>
                </h:panelGrid>
            </h:form>
        </body>
    </html>
</f:view>