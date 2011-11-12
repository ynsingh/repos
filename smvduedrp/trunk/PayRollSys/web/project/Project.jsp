<%-- 
    Document   : Project
    Created on : Apr 22, 2011, 4:17:13 PM
    Author     :  *  Copyright (c) 2010 - 2011 SMVDU, Katra.
*  All Rights Reserved.
**  Redistribution and use in source and binary forms, with or 
*  without modification, are permitted provided that the following 
*  conditions are met: 
**  Redistributions of source code must retain the above copyright 
*  notice, this  list of conditions and the following disclaimer. 
* 
*  Redistribution in binary form must reproduce the above copyright
*  notice, this list of conditions and the following disclaimer in 
*  the documentation and/or other materials provided with the 
*  distribution. 
* 
* 
*  THIS SOFTWARE IS PROVIDED ``AS IS'' AND ANY EXPRESSED OR IMPLIED 
*  WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES 
*  OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE 
*  DISCLAIMED.  IN NO EVENT SHALL ETRG OR ITS CONTRIBUTORS BE LIABLE 
*  FOR ANY DIRECT, INDIRECT, INCIDENTAL,SPECIAL, EXEMPLARY, OR 
*  CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT 
*  OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR 
*  BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY,
*  WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE 
*  OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, 
*  EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE. 
* 
* 
*  Contributors: Members of ERP Team @ SMVDU, Katra
*
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsf/core" %>
<%@ taglib prefix="h" uri="http://java.sun.com/jsf/html" %>
<%@ taglib uri="http://richfaces.org/a4j" prefix="a4j"%>
<%@ taglib uri="http://richfaces.org/rich" prefix="rich"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<f:view>
    <rich:panel header="Existing Projects" style="width:100%;">
        <h:commandButton onclick="Richfaces.showModalPanel('search');" value="Filter"/>
    <h:commandButton onclick="Richfaces.showModalPanel('pnl');" value="Add New"/>
    <rich:messages  >
            <f:facet name="infoMarker">
                <h:graphicImage url="/img/success.png"/>
            </f:facet>
            <f:facet name="errorMarker">
                <h:graphicImage url="/img/err.png"/>
            </f:facet>
        </rich:messages>
    <h:form>
    <rich:dataTable id="tbl" binding="#{ProjectController.dataGrid}"
                    value="#{ProjectController.projects}" var="prj">

         <rich:column >
            <f:facet name="header">
                <h:outputText value="Select"/>
            </f:facet>
             <h:selectBooleanCheckbox value="#{prj.selected}"/>
        </rich:column>
       
        <rich:column sortable="true" sortBy="#{prj.name}">
            <f:facet name="header">
                <h:outputText value="Project Name"/>
            </f:facet>
            <h:outputText value="#{prj.name}"/>
        </rich:column>
        <rich:column sortable="true" sortBy="#{prj.partnerName}">
            <f:facet name="header">
                <h:outputText value="Partner"/>
            </f:facet>
            <h:outputText value="#{prj.partnerName}"/>
        </rich:column>
         <rich:column sortable="true" sortBy="#{prj.associateName}">
            <f:facet name="header">
                <h:outputText value="Associate"/>
            </f:facet>
            <h:outputText value="#{prj.associateName}"/>
        </rich:column>
         <rich:column sortable="true" sortBy="#{prj.archName}">
            <f:facet name="header">
                <h:outputText value="Architect"/>
            </f:facet>
            <h:outputText value="#{prj.archName}"/>
        </rich:column>
         <rich:column sortable="true" sortBy="#{prj.tlName}">
            <f:facet name="header">
                <h:outputText value="Team Leader"/>
            </f:facet>
            <h:outputText value="#{prj.tlName}"/>
        </rich:column>
        
         <rich:column sortable="true" sortBy="#{prj.budget}">
            <f:facet name="header">
                <h:outputText value="Budget"/>
            </f:facet>
            <h:outputText value="#{prj.budget}"/>
        </rich:column>
         <rich:column sortable="true" sortBy="#{prj.startDate}">
            <f:facet name="header">
                <h:outputText value="Start Date"/>
            </f:facet>
            <h:outputText value="#{prj.startDate}"/>
        </rich:column>
         <rich:column sortable="true" sortBy="#{prj.endDate}">
            <f:facet name="header">
                <h:outputText value="Comp.Date"/>
            </f:facet>
            <h:outputText value="#{prj.endDate}"/>
        </rich:column>
        <rich:column sortable="true" sortBy="#{prj.duration}">
            <f:facet name="header">
                <h:outputText value="Time(Months) "/>
            </f:facet>
            <h:outputText value="#{prj.duration}"/>
        </rich:column>
        <rich:column sortable="true" sortBy="#{prj.installment}">
            <f:facet name="header">
                <h:outputText value="Installment"/>
            </f:facet>
            <h:outputText value="#{prj.installment}"/>
        </rich:column>
        <rich:column sortable="true" sortBy="#{prj.statusName}">
            <f:facet name="header">
                <h:outputText value="Active"/>
            </f:facet>
            <h:outputText value="#{prj.statusName}"/>
        </rich:column>
        <rich:column >
            <f:facet name="header">
                <h:outputText value="Edit"/>
            </f:facet>
            <a4j:commandButton value="Edit" action="#{Project.edit}">
                <f:param name="prjId" value="#{prj.id}"/>
            </a4j:commandButton>
        </rich:column>
    </rich:dataTable>
    
    <a4j:commandButton action="#{ProjectController.delete}" reRender="tbl" value="Delete"/>
    </h:form>
    </rich:panel>

    <rich:modalPanel id="search">
        <h:form>
            <rich:panel header="Filter Projects">
                <h:panelGrid columns="3">
                    <h:outputText value="Partner Name"/>
                    <h:selectOneMenu value="#{ProjectController.rfpartner}">
                        <f:selectItem itemLabel="All" itemValue="0"/>
                        <f:selectItems value="#{ProjectController.partners}"/>
                    </h:selectOneMenu>
                    <h:outputText value=""/>
                    <h:outputText value="Associate Name"/>
                    <h:inputText id="ftl"value="#{ProjectController.rfAssoc}" styleClass="fields" required="true" requiredMessage="Enter Employee Code" immediate="true"/>
                                <rich:suggestionbox for="ftl" var="abc" fetchValue="#{abc.empId}"  suggestionAction="#{SearchBean.getSuggestion}">
                                    <h:column>
                                        <h:outputText value="#{abc.name}"/>
                                    </h:column>
                                    <h:column>
                                        <h:outputText value="#{abc.empId}"/>
                                    </h:column>
                                </rich:suggestionbox>
                    <h:outputText value="Architect"/>
                    <h:inputText id="ftl2"value="#{ProjectController.rfArch}" styleClass="fields" required="true" requiredMessage="Enter Employee Code" immediate="true"/>
                                <rich:suggestionbox for="ftl2" var="abc" fetchValue="#{abc.empId}"  suggestionAction="#{SearchBean.getSuggestion}">
                                    <h:column>
                                        <h:outputText value="#{abc.name}"/>
                                    </h:column>
                                    <h:column>
                                        <h:outputText value="#{abc.empId}"/>
                                    </h:column>
                                </rich:suggestionbox>
                    <h:outputText value="Team eader"/>
                    <h:inputText id="ftl3"value="#{ProjectController.rfTl}" styleClass="fields" required="true" requiredMessage="Enter Employee Code" immediate="true"/>
                                <rich:suggestionbox for="ftl3" var="abc" fetchValue="#{abc.empId}"  suggestionAction="#{SearchBean.getSuggestion}">
                                    <h:column>
                                        <h:outputText value="#{abc.name}"/>
                                    </h:column>
                                    <h:column>
                                        <h:outputText value="#{abc.empId}"/>
                                    </h:column>
                                </rich:suggestionbox>
                    <a4j:commandButton value="Filter" action="#{ProjectController.reLoad}" reRender="tbl"/>
                    <h:commandButton value="Close" onclick="Richfaces.hideModalPanel('search');" />

                </h:panelGrid>
            </rich:panel>
        </h:form>
    </rich:modalPanel>



</f:view>
