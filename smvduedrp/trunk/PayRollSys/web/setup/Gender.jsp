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
            <title>JSP Page</title>
            <link type="text/css" rel="stylesheet" href="../bankDetails.css"/>
        </head>
        <body>
            <rich:panel header="Add Gender">
                <h:form>
                    <h:panelGrid columns="1">
                        <rich:panel>
                            <a4j:commandButton value="Add New Gender" onclick="Richfaces.showModalPanel('pnl');"/>
                            <rich:messages  >
                                <f:facet name="infoMarker">
                                    <h:graphicImage url="/img/success.png"/>
                                </f:facet>
                                <f:facet name="errorMarker">
                                    <h:graphicImage url="/img/err.png"/>
                                </f:facet>
                            </rich:messages>
                        </rich:panel>
                        <rich:panel>
                            <h:dataTable id="gent" headerClass="headerStyle" rowClasses="rowStyle" value="#{genderConttroler.gender}" binding="#{genderConttroler.dataGridValue}" var="gendert">
                                <h:column>
                                    <f:facet name="header">
                                        <h:outputText value="Gender Seq."/>
                                    </f:facet>
                                    <h:outputText value="#{gendert.code}"/>
                                </h:column>
                                <h:column>
                                    <f:facet name="header">
                                        <h:outputText value="Gender"/>
                                    </f:facet>
                                    <h:outputText value="#{gendert.gender}"/>
                                </h:column>
                            </h:dataTable>
                        </rich:panel>
                        <rich:separator/>
                        <%--<rich:panel>
                            <a4j:commandButton value="Update" reRender="gent" action="#{genderConttroler.update}"/>
                        </rich:panel>--%>
                    </h:panelGrid>
                </h:form>
            </rich:panel>
            <rich:modalPanel id="pnl">
                <h:form>
                    <h:panelGrid columns="2">
                        <h:outputText value="Gender Name"/>
                        <h:inputText value="#{newGender.gender}" required="true" id="gen"/>
                        <rich:separator/>
                        <rich:separator/>
                        <a4j:commandButton value="Save" action="#{newGender.save}"/>
                        <a4j:commandButton value="Close" onclick="Richfaces.hideModalPanel('pnl');"/>
                    </h:panelGrid>
                </h:form>
            </rich:modalPanel>
        </body>
    </html>
</f:view>