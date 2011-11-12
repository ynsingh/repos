<%-- 
    Document   : Income
    Created on : May 26, 2011, 6:55:15 PM
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
    <rich:panel header="Expenses">
         <rich:messages  >
            <f:facet name="infoMarker">
                <h:graphicImage url="/img/success.png"/>
            </f:facet>
            <f:facet name="errorMarker">
                <h:graphicImage url="/img/err.png"/>
            </f:facet>
        </rich:messages>
        <h:panelGrid columns="2">
            
                
                    <h:form>
                        <h:panelGrid border="1" columns="2">
                    <h:outputText value="Project"/>
                    <h:selectOneMenu value="#{Expense.prjId}">
                        <f:selectItems value="#{ProjectController.asItem}"/>
                    </h:selectOneMenu>
                    <h:outputText value="Ledger"/>
                    <h:selectOneMenu value="#{Expense.ledgerId}">
                        <f:selectItems value="#{LedgerController.asItem}"/>
                    </h:selectOneMenu>
                     <h:outputText value="Employee"/>
                     <h:selectOneMenu value="#{Expense.employeeId}">
                         <f:selectItems value="#{SearchBean.asItem}"/>
                    </h:selectOneMenu>
                    <h:outputText value="Amount"/>
                    <h:inputText value="#{Expense.amount}"/>
                    <h:outputText value="Date"/>
                    <rich:calendar id="sdate" converter="dateConverter" 
                                   showFooter="false" styleClass="special"
                                   datePattern="yyyy-MM-dd" popup="true"
                                   required="true" value="#{Expense.date}">

                    </rich:calendar>
                    <h:outputText value="Description"/>
                    <h:inputTextarea value="#{Expense.remark}"/>
                    <a4j:commandButton reRender="tbl" value="Save" action="#{Expense.save}"/>
                   
                </h:panelGrid>
                         </h:form>
                <rich:panel style="height:400px;">
                    <h:commandButton onclick="Richfaces.showModalPanel('pnl');" value="Filter Options"/>
                    <h:form>
                <rich:dataTable id="tbl" value="#{ExpController.expenses}" var="exp">
                    <f:facet name="caption">
                                <h:outputText value="Recent Expenses"/>
                            </f:facet>
                        <rich:column >
                            <f:facet name="header">
                                <h:outputText value="Project"/>
                            </f:facet>
                            <h:outputText value="#{exp.projectName}"/>
                        </rich:column>
                        <rich:column rendered="#{!ExpController.group}" sortable="true" sortBy="#{exp.ledgerName}">
                            <f:facet name="header">
                                <h:outputText value="Ledger"/>
                            </f:facet>
                            <h:outputText value="#{exp.ledgerName}"/>
                        </rich:column>
                    <rich:column rendered="#{!ExpController.group}" sortable="true" sortBy="#{exp.employeeName}">
                            <f:facet name="header">
                                <h:outputText value="Employee"/>
                            </f:facet>
                            <h:outputText value="#{exp.employeeName}"/>
                        </rich:column>
                         <rich:column sortable="true" sortBy="#{exp.amount}">
                            <f:facet name="header">
                                <h:outputText value="Amount"/>
                            </f:facet>
                            <h:outputText value="#{exp.amount}"/>
                        </rich:column>
                         <rich:column sortable="true" sortBy="#{exp.date}">
                            <f:facet name="header">
                                <h:outputText value="Date"/>
                            </f:facet>
                            <h:outputText value="#{exp.date}"/>
                        </rich:column>
                        <rich:column sortable="true" sortBy="#{exp.remark}">
                            <f:facet name="header">
                                <h:outputText value="Remark"/>
                            </f:facet>
                            <h:outputText value="#{exp.remark}"/>
                        </rich:column>
                    <rich:column >
                            <f:facet name="header">
                                <h:outputText value="Remark"/>
                            </f:facet>
                        <a4j:commandButton value="Delete" image="/img/cut.jpg" action="#{Expense.delete}">
                             <f:param name="delid" value="#{exp.id}"/>
                         </a4j:commandButton>
                        </rich:column>
                    </rich:dataTable>
                </h:form>
            </rich:panel>
        </h:panelGrid>
    </rich:panel>


    <rich:modalPanel id="pnl">
        <h:form>
            <h:panelGrid columns="2">
            <h:outputText value="Project"/>
            <h:selectOneMenu value="#{ExpController.project}">
                <f:selectItem itemLabel="All" itemValue="0"/>
                <f:selectItems value="#{ProjectController.asItem}"/>
            </h:selectOneMenu>
            <h:outputText value="Ledger"/>
            <h:selectOneMenu value="#{ExpController.ledger}">
                <f:selectItem itemLabel="All" itemValue="0"/>
                <f:selectItems value="#{LedgerController.asItem}"/>
            </h:selectOneMenu>
            <h:outputText value="Employee"/>
            <h:selectOneMenu value="#{ExpController.employee}">
                <f:selectItem itemLabel="All" itemValue="0"/>
                <f:selectItems value="#{SearchBean.asItem}"/>
            </h:selectOneMenu>
            <h:outputText value="Date From"/>
            <rich:calendar id="sdate" converter="dateConverter"
                           showFooter="false" styleClass="special"
                           datePattern="yyyy-MM-dd" popup="true"
                           value="#{ExpController.dateFrom}">

            </rich:calendar>
            <h:outputText value="Date to"/>
            <rich:calendar id="s2date" converter="dateConverter"
                           showFooter="false" styleClass="special"
                           datePattern="yyyy-MM-dd" popup="true"
                           value="#{ExpController.dateTo}">

            </rich:calendar>
            <h:outputText value="Show Records"/>
            <h:inputText value="#{ExpController.recordCount}"/>
            <h:outputText value="Group Results"/>
            <h:selectBooleanCheckbox value="#{ExpController.group}"/>
            <a4j:commandButton reRender="tbl" action="#{ExpController.reLoad}" value="Filter Report"/>
            <h:commandButton value="Close" onclick="Richfaces.hideModalPanel('pnl');" />
            </h:panelGrid>
        </h:form>

    </rich:modalPanel>

</f:view>
