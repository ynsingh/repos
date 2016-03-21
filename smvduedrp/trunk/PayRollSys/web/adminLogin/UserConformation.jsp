<%-- 
    Document   : MainLogin
    Created on : Nov 26, 2012, 1:09:06 PM
    Author     : sumit
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
                    <h:panelGrid columns="2">
                        <h:panelGroup>
                            <h:graphicImage value="/img/#{userConformationBeans.imageUrl}"/>
                            <h:outputText value="#{userConformationBeans.message}" style="font-style:Arial;font-size:18px;font-weight:bold;"/>
                        </h:panelGroup>
                        <%-- <h:commandLink  value="#{userConformationBeans.eventPage}" action="#{userConformationBeans.redirect}"/> --%>
                        <div> <a href="../Login.jsf"><h2> Click here for Login/Registration </h2> </a> </div>
                    </h:panelGrid>
                </rich:panel>
                <rich:panel style="height:430px;">
                    <h:outputLink value="#{userConformationBeans.userEvent}"/>
                </rich:panel>
            </h:panelGrid>
        </h:form>
    </body>
</html>
</f:view>