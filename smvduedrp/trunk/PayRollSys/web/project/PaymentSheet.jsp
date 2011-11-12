<%-- 
    Document   : PaymentSheet
    Created on : May 28, 2011, 11:09:16 AM
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
    <rich:panel header="Project Payment Sheet">
        <h:commandButton value="Add New" onclick="Richfaces.showModalPanel('pnl');"/>
        <h:form>
        <rich:dataTable id="tbl" var="p" value="#{PaymentController.payments}" binding="#{PaymentController.dataGrid}">
            <rich:column sortable="true" sortBy="#{p.prjName}">
                <f:facet name="header">
                    <h:outputText value="Project Name"/>
                </f:facet>
                <h:outputText value="#{p.prjName}"/>
            </rich:column>
            <rich:column sortable="true" sortBy="#{p.date}">
             
                <f:facet name="header">
                    <h:outputText value="Due Date"/>
                </f:facet>
                <h:outputText value="#{p.date}"/>
            </rich:column>
            <rich:column sortable="true" sortBy="#{p.amount}">
                <f:facet name="header">
                    <h:outputText value="Due Amount"/>
                </f:facet>
                <rich:inplaceInput value="#{p.amount}"/>
            </rich:column>
        </rich:dataTable>
                <a4j:commandButton reRender="tbl" value="Update"/>
        </h:form>
    </rich:panel>

    <rich:modalPanel id="pnl">
        <h:form>
            <h:panelGrid columns="2">
                <h:outputText value="Select project"/>
                <h:selectOneMenu value="#{Payment.prjId}">
                    <f:selectItems value="#{ProjectController.asItem}"/>
                </h:selectOneMenu>
                <h:outputText value="Amount Receivable"/>
                <h:inputText value="#{Payment.amount}"/>
                <h:outputText value="Date"/>
                <rich:calendar id="sdate" converter="dateConverter" showWeekDaysBar="false"
                               showFooter="false" styleClass="special"
                               datePattern="yyyy-MM-dd" popup="true"
                               required="true" value="#{Payment.date}">

                </rich:calendar>
                <a4j:commandButton reRender="tbl" value="Save" action="#{Payment.save}">
                </a4j:commandButton>
                <h:commandButton value="Close" onclick="Richfaces.hideModalPanel('pnl');" />
            </h:panelGrid>
        </h:form>
    </rich:modalPanel>
</f:view>


