<%-- 
    Document   : EmployeeSlabValue
    Created on : May 13, 2012, April 29 2015, 12:09:02 PM
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
               <h:panelGrid columns="1">
                        <h:form>
                            <h:panelGrid columns="4">
                                <h:outputText value="Select Gender"/>
                                    <h:selectOneMenu value="#{employeeSlabHeadConttroler.genderCode}">
                                        <f:selectItems value="#{employeeSlabDetail.items}"/>
                                        </h:selectOneMenu>
                                    <a4j:commandButton reRender="sllist" value="Load" action="#{employeeSlabHeadConttroler.populate}"/>
                                    <rich:messages>
                                        <f:facet name="infoMarker">
                                            <h:graphicImage url="/img/success.png"/>
                                        </f:facet>
                                    </rich:messages>
                             </h:panelGrid> <br/>
                            <%-- <rich:separator/><br/>--%>
                            <h:panelGrid columns="2">
                                <rich:dataTable id="sllist" value="#{employeeSlabHeadConttroler.loadGenderSlabValue}" 
                                               binding="#{employeeSlabHeadConttroler.dataGrid}" var="gensl" rows="20" style="width:500px;">
                                  
                                    <rich:column width="250px">
                                        <f:facet name="header">
                                            <h:outputText value="Select Chekbox"/>
                                         </f:facet>
                                         <h:selectBooleanCheckbox value="#{gensl.slabSelected}"/>
                                    </rich:column>
                                    <rich:column width="250px">
                                        <f:facet name="header">
                                            <h:outputText value="Slab Name"/>
                                        </f:facet>
                                        <h:outputText value="#{gensl.slabeName}"/>
                                    </rich:column>
                                    <f:facet name="footer">
                                <rich:datascroller for="sllist" page="1"/>  
                                </f:facet>
                                </rich:dataTable>
                             </h:panelGrid>
                            <a4j:commandButton value="Update" action="#{employeeSlabHeadConttroler.saveGenSlabDetail}"/>
                         </h:form>
               </h:panelGrid>
            </rich:panel>
        </body>
    </html>
</f:view>