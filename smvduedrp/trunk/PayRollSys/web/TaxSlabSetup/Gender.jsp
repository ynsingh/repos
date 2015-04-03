<%--
    Document   : Gender
    Created on : Apr 27, 2012, 12:58:01 PM
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
            <title>Tax Slab Gender Details</title>
            <link type="text/css" rel="stylesheet" href="../bankDetails.css"/>
        </head>
        <body>
            <rich:panel header="Gender Details">
                <h:form>
                    <h:panelGrid columns="1">

                            <a4j:commandButton value="Add New Gender" onclick="Richfaces.showModalPanel('pnl');"/>
                            <rich:messages  >
                                <f:facet name="infoMarker">
                                    <h:graphicImage url="/img/success.png"/>
                                </f:facet>
                                <f:facet name="errorMarker">
                                    <h:graphicImage url="/img/err.png"/>
                                </f:facet>
                            </rich:messages>


                            <rich:dataTable id="gent"value="#{gender.loadGender}" var="gendert">
                                <h:column>
                                    <f:facet name="header">
                                        <h:outputText value="Gender Seq."/>
                                    </f:facet>
                                    <h:outputText value="#{gendert.genderCode}"/>
                                </h:column>
                                <h:column>
                                    <f:facet name="header">
                                        <h:outputText value="Gender"/>
                                    </f:facet>
                                    <h:outputText value="#{gendert.genderName}"/>
                                </h:column>
                            </rich:dataTable>

                        <%--<rich:panel>
                            <a4j:commandButton value="Update" reRender="gent" action="#{genderConttroler.update}"/>
                        </rich:panel>--%>
                    </h:panelGrid>
                </h:form>
            </rich:panel>
            <rich:modalPanel id="pnl" height="120" width="270">
                
                <f:facet name="header">
                     <h:panelGroup>
                         <h:outputText value="Add New Gender Detail"></h:outputText>
                     </h:panelGroup>
                </f:facet>
                
                <f:facet name="controls">
                     <h:panelGroup>
                        <h:graphicImage value="/img/close1.png" styleClass="hidelink" id="hidelink"/>
                        <rich:componentControl for="pnl" attachTo="hidelink" operation="hide" event="onclick"/>
                     </h:panelGroup>
                </f:facet>
                <h:form>

                        <h:panelGrid columns="2">
                            <h:outputText value="Gender Name"/>
                            <h:inputText value="#{gender.genderName}" required="true" id="gen" requiredMessage="Please Enter Gender Name"/>
                        </h:panelGrid></br>
                            <rich:separator/></br>
                        <h:panelGrid columns="2">
                            <a4j:commandButton value="Save" action="#{gender.save}" reRender="gent" onclick="Richfaces.hideModalPanel('pnl');"/>
                            <a4j:commandButton value="Close" onclick="Richfaces.hideModalPanel('pnl');"/>
                        </h:panelGrid></br>

                </h:form>
            </rich:modalPanel>
        </body>
    </html>
</f:view>