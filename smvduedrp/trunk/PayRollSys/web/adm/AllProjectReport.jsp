<%-- 
    Document   : ProjecctReport
    Created on : May 25, 2011, 3:18:11 AM
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
    <rich:panel header="All Project Report">
        <h:commandButton onclick="Richfaces.showModalPanel('search');" value="Filter"/>
        <h:form>
        <rich:dataTable id="tbl" value="#{ProjectReport.allData}" var="exp">
            <rich:column  sortable="true" sortBy="#{exp.employee}">
                <f:facet name="header">
                    <h:outputText value="Project Name"/>
                </f:facet>
                <h:outputText value="#{exp.employee}"/>
            </rich:column>
            <rich:column  sortable="true" sortBy="#{exp.budget}">
                <f:facet name="header">
                    <h:outputText value="Total Budget"/>
                </f:facet>
                <h:outputText value="#{exp.budget}"/>
            </rich:column>
            <rich:column  sortable="true" sortBy="#{exp.total}">
                <f:facet name="header">
                    <h:outputText value="Total Man Hour Cost"/>
                </f:facet>
                <h:outputText value="#{exp.total}"/>
            </rich:column>
             <rich:column  sortable="true" sortBy="#{exp.expense}">
                <f:facet name="header">
                    <h:outputText value="Other Expenses"/>
                </f:facet>
                <h:outputText value="#{exp.expense}"/>
            </rich:column>
            <rich:column  sortable="true" sortBy="#{exp.expense}">
                <f:facet name="header">
                    <h:outputText value="Net Expenses"/>
                </f:facet>
                <h:outputText value="#{exp.netExpense}"/>
            </rich:column>
             <rich:column  sortable="true" sortBy="#{exp.actualIncome}">
                <f:facet name="header">
                    <h:outputText value="Actual Income as on Date"/>
                </f:facet>
                <h:outputText value="#{exp.actualIncome}"/>
            </rich:column>
            <rich:column  sortable="true" sortBy="#{exp.profit}">
                <f:facet name="header">
                    <h:outputText value="Profit As on Now"/>
                </f:facet>
                <h:outputText value="#{exp.profit}"/>
            </rich:column>

                <f:facet name="caption">
                    <h:outputText value="Concise Project Report"/>
                </f:facet>
            
        </rich:dataTable>
        </h:form>
    </rich:panel>

    <rich:modalPanel id="search">
        <h:form>
            <h:panelGrid columns="2">
                <h:outputText value="Select Partner"/>
                <h:selectOneMenu value="#{ProjectReport.rfPartner}">
                    <f:selectItem itemLabel="All" itemValue="0"/>
                    <f:selectItems value="#{ProjectController.partners}"/>
                </h:selectOneMenu>
                
            </h:panelGrid>
            <a4j:commandButton reRender="tbl" value="Filter" action="#{ProjectReport.load}"/>
                <h:commandButton value="Close" onclick="Richfaces.hideModalPanel('filter');" />
        </h:form>
    </rich:modalPanel>



</f:view>
