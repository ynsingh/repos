<%-- 
    Document   : ChangePassword
    Created on : Dec 14, 2012, 3:40:27 PM
    Author     : KESU
--%>

<%@ taglib prefix="f" uri="http://java.sun.com/jsf/core" %>
<%@ taglib prefix="h" uri="http://java.sun.com/jsf/html" %>
<%@ taglib uri="http://richfaces.org/a4j" prefix="a4j"%>
<%@ taglib uri="http://richfaces.org/rich" prefix="rich"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <f:view>
            <rich:panel header="Change Admin Password">
                <h:form>
                    <rich:messages>
                        <f:facet name="infoMarker">
                           <h:graphicImage url="/img/success.png"/>
                        </f:facet>
                        <f:facet name="errorMarker">
                           <h:graphicImage url="/img/err.png"/>
                        </f:facet>
                    </rich:messages>
                    <h:panelGrid columns="2">
                        <h:outputText value="New Password"/>
                        <h:inputSecret size="30" value="#{OrgProfileBean.adPassword}"/>
                        <h:outputText value="Re. Password"/>
                        <h:inputSecret size="30" value="#{OrgProfileBean.adRePassword}"/>
                    </h:panelGrid>
                    <br/>
                    <rich:separator/> 
                    <br/>
                    <h:panelGrid columns="2" style="padding-left:6%;">
                        <a4j:commandButton value="Change" action="#{OrgProfileBean.changeAdPassword}"/>
                        <a4j:commandButton value="Reset" type="reset"/>
                    </h:panelGrid>
                </h:form>
            </rich:panel>
        </f:view>
    </body>
</html>