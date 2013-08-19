<%-- 
    Document   : createUser
    Created on : May 14, 2012, 4:09:42 PM
    Author     : ERP
--%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsf/core" %>
<%@ taglib prefix="h" uri="http://java.sun.com/jsf/html" %>
<%@ taglib uri="http://richfaces.org/a4j" prefix="a4j"%>
<%@ taglib uri="http://richfaces.org/rich" prefix="rich"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<f:view>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
            <title>JSP Page</title>
            <link type="text/css" rel="stylesheet" href="../bankDetails.css"/>
        </head>
        <body>
            <rich:panel>
                <h:panelGrid columns="1">
                    <rich:panel>
                        <a4j:commandButton value="Add User" onclick="Richfaces.showModalPanel('us');"/>
                    </rich:panel>
                    <rich:separator/>
                    <rich:panel header="">
                        <h:dataTable headerClass="headerStyle" rowClasses="rowStyle1" id="on">
                            <h:column>
                                <f:facet name="header">
                                    <h:outputText value="User Name"/>
                                </f:facet>
                            </h:column>
                            <h:column>
                                <f:facet name="header">
                                    <h:outputText value="User ID"/>
                                </f:facet>
                            </h:column>
                            <h:column>
                                <f:facet name="header">
                                    <h:outputText value="Department Name"/>
                                </f:facet>
                            </h:column>
                            <h:column>
                                <f:facet name="header">
                                    <h:outputText value="In Time"/>
                                </f:facet>
                            </h:column>
                            <h:column>
                                <f:facet name="header">
                                    <h:outputText value="Out Time"/>
                                </f:facet>
                            </h:column>
                            <h:column>
                                <f:facet name="header">
                                    <h:outputText value="Status"/>
                                </f:facet>
                            </h:column>
                            <h:column>
                                <f:facet name="header">
                                    <h:outputText value="System Name"/>
                                </f:facet>
                            </h:column>
                            <h:column>
                                <f:facet name="header">
                                    <h:outputText value="IP Address"/>
                                </f:facet>
                            </h:column>
                        </h:dataTable>
                    </rich:panel>
                </h:panelGrid>
                <rich:modalPanel id="us" height="250" width="320" autosized="false">
                    <h:form>
                        <rich:panel>
                            <rich:messages >
                                <f:facet name="infoMarker">
                                    <h:graphicImage url="/img/success.png"/>
                                </f:facet>
                                <f:facet name="errorMarker">
                                    <h:graphicImage url="/img/err.png"/>
                                </f:facet>
                            </rich:messages>

                            <h:panelGrid columns="3">
                                <h:outputText  styleClass="Label" value="User Id"/>
                                <h:inputText id="uid" value="#{userBeans.userId}"/>
                                <rich:suggestionbox id="ui" for="uid" var="abc" fetchValue="#{abc.userId}"  suggestionAction="#{userBeans.getSuggestion}">
                                    <h:column>
                                        <h:outputText value="#{abc.userName}"/>
                                    </h:column>
                                    <h:column>
                                        <h:outputText value="#{abc.userId}"/>
                                    </h:column>
                                </rich:suggestionbox>
                                <rich:message for="ui"/>
                                <rich:separator/>
                                <rich:separator/>
                                <h:outputText value="Password"/>
                                <h:inputSecret value="#{userBeans.userPassword}" id="pass"/>
                                <rich:message for="pass"/>
                                <h:outputText value="Re. Password"/>
                                <h:inputSecret value="#{userBeans.userRePassword}" id="repass"/>
                                <rich:message for="repass"/>
                                <rich:separator/>
                                <rich:separator/>
                                <rich:separator/>
                                <h:panelGroup>
                                    <a4j:commandButton value="Save" action="#{userBeans.save}"/>
                                    <a4j:commandButton value="Reset" type="reset"/>
                                </h:panelGroup>
                            </h:panelGrid>
                            <a4j:commandButton value="Close" onclick="Richfaces.hideModalPanel('us');"/>
                        </rich:panel>
                    </h:form>
                </rich:modalPanel>
            </rich:panel>
        </body>
    </html>
</f:view>