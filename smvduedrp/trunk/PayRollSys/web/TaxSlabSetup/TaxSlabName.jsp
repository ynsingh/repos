<%--
    Document   : TaxSlabName
    Created on : May 1, 2012, 4:29:00 PM
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
            <title>Slab Name</title>
            <link type="text/css" rel="stylesheet" href="../bankDetails.css"/>
        </head>
        <body>
            <rich:panel header="Add Slab Name">
                <h:form>
                    <rich:messages  >
                        <f:facet name="infoMarker">
                            <h:graphicImage url="/img/success.png"/>
                        </f:facet>
                        <f:facet name="errorMarker">
                            <h:graphicImage url="/img/err.png"/>
                        </f:facet>
                    </rich:messages>
                    <h:panelGrid columns="1">
                        <rich:panel>
                            <a4j:commandButton value="Add Slab Name" onclick="Richfaces.showModalPanel('tsn');"/>
                        </rich:panel>
                        <h:outputText value=""/>
                        <rich:separator/>
                        <h:outputText value=""/>
                        <rich:panel>
                            <h:dataTable id="sl" headerClass="headerStyle" rowClasses="rowStyle" value="#{taxSlabHeadBean.taxHeadValue}" var="sld">
                                <h:column>
                                    <f:facet name="header">
                                        <h:outputText value="Seq. No."/>
                                    </f:facet>
                                    <h:outputText value="#{sld.slabHeadCode}"/>
                                </h:column>
                                <h:column>
                                    <f:facet name="header">
                                        <h:outputText value="Slab Name"/>
                                    </f:facet>
                                    <h:outputText value="#{sld.slabName}"/>
                                </h:column>
                                <h:column>
                                    <f:facet name="header">
                                        <h:outputText value="Slab Start Value"/>
                                    </f:facet>
                                    <h:outputText value="#{sld.startSlabValue}"/>
                                </h:column>
                                <h:column>
                                    <f:facet name="header">
                                        <h:outputText value="Slab End Value"/>
                                    </f:facet>
                                    <h:outputText value="#{sld.endSlabValue}"/>
                                </h:column>
                                <h:column>
                                    <f:facet name="header">
                                        <h:outputText value="Slab Percent"/>
                                    </f:facet>
                                    <h:outputText value="#{sld.percent}"/>
                                </h:column>
                            </h:dataTable>
                        </rich:panel>
                    </h:panelGrid>
                    <rich:panel>
                        <%--<a4j:commandButton reRender="sl" value="Update" action="#{slabConttroler.update}"/>--%>
                    </rich:panel>
                </h:form>
            </rich:panel>
            <rich:modalPanel label="Add Slab Name" id="tsn" moveable="true">
                <h:form>
                    <rich:panel>
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
                        <h:panelGrid columns="2">
                            <h:outputText value="Slab Name"/>
                            <h:inputText id="sln" value="#{taxSlabHeadBean.slabName}"/>
                            <h:outputText value="Slab Start Value"/>
                            <h:inputText id="st" value="#{taxSlabHeadBean.startSlabValue}"/>
                            <h:outputText value="Slab End Value"/>
                            <h:inputText id="se" value="#{taxSlabHeadBean.endSlabValue}"/>
                            <h:outputText value="Slab Percent"/>
                            <h:inputText id="sp" value="#{taxSlabHeadBean.percent}"/>
                        </h:panelGrid>
                    </rich:panel>
                    <rich:toolTip for="sln" value="Only Alpha Numeric Will Be Valid No Other Special Charecter Will Be Accepted(e.g @#$%^&*...)"/>
                    <rich:separator/>
                    <rich:panel>
                        <a4j:commandButton value="Save" action="#{taxSlabHeadBean.saveSlabHead}" reRender="sl"/>
                        <a4j:commandButton value="Close" onclick="Richfaces.hideModalPanel('tsn');"/>
                    </rich:panel>
                </h:form>
            </rich:modalPanel>
        </body>
    </html>
</f:view>