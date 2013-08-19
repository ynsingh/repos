<%-- 
    Document   : EmployeeSlabValue
    Created on : May 13, 2012, 12:09:02 PM
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
            <rich:panel header="Add Slab Name According To Gender">
                <h:form>
                    <h:panelGrid columns="1">
                        <h:form>
                            <rich:panel>
                                <h:panelGrid columns="1">
                                    <h:panelGrid columns="3">
                                        <h:outputText value="Select Gender"/>
                                        <h:selectOneMenu value="#{employeeSlabHeadConttroler.genderCode}">
                                            <f:selectItems value="#{employeeSlabDetail.items}"/>
                                        </h:selectOneMenu>
                                        <rich:messages>
                                            <f:facet name="infoMarker">
                                                <h:graphicImage url="/img/success.png"/>
                                            </f:facet>
                                        </rich:messages>
                                        <%--        <a4j:commandButton reRender="sllist" value="Load" action="#{employeeSlabHeadConttroler.loadGenderSlab}"/>  --%>
                                    </h:panelGrid>
                                    <rich:separator/>
                                    <rich:panel>
                                        <rich:dataTable id="sllist" value="#{employeeSlabHeadConttroler.loadGenderSlabValue}"
                                                        binding="#{employeeSlabHeadConttroler.dataGrid}" var="gensl" headerClass="headerStyle" rowClasses="rowStyle">
                                            <h:column>
                                                <f:facet name="header">
                                                    <h:outputText value="Slab Name"/>
                                                </f:facet>
                                                <h:outputText value="#{gensl.slabName}"/>
                                            </h:column>
                                            <h:column>
                                                <f:facet name="header">
                                                    <h:outputText value="Select Chekbox"/>
                                                </f:facet>
                                                <h:selectBooleanCheckbox value="#{gensl.slabSelected}"/>
                                            </h:column>
                                        </rich:dataTable>
                                    </rich:panel>
                                    <rich:separator/>
                                    <rich:panel>

                                        <a4j:commandButton value="Update" action="#{employeeSlabHeadConttroler.saveGenSlabDetail}"/>

                                    </rich:panel>
                                </h:panelGrid>
                            </rich:panel>
                        </h:form>

                    </h:panelGrid>
                </h:form>
            </rich:panel>
        </body>
    </html>
</f:view>