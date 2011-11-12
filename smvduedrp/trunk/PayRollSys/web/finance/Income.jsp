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
    <rich:panel header="Income">
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
                <h:panelGrid columns="2">
                    <h:outputText value="Project"/>
                    <h:selectOneMenu value="#{Income.prjId}">
                        <f:selectItems value="#{ProjectController.asItem}"/>
                    </h:selectOneMenu>
                    <h:outputText value="Amount"/>
                    <h:inputText value="#{Income.amount}"/>
                    <h:outputText value="Date"/>
                    <rich:calendar id="sdate" converter="dateConverter" 
                                   showFooter="false" styleClass="special"
                                   datePattern="yyyy-MM-dd" popup="true"
                                   required="true" value="#{Income.date}">

                    </rich:calendar>
                    <h:outputText value="Description"/>
                    <h:inputTextarea value="#{Income.remark}"/>
                    <a4j:commandButton reRender="tbl" value="Save" action="#{Income.save}"/>
                </h:panelGrid>
            </h:form>
            <rich:panel style="height:400px;">
                <h:commandButton onclick="Richfaces.showModalPanel('pnl');" value="Filter Options"/>
                <h:form>
                <rich:dataTable id="tbl" value="#{IncomeController.incomes}" var="exp">
                    <f:facet name="caption">
                                <h:outputText value="Recent Income"/>
                            </f:facet>
                    <rich:column sortable="true" sortBy="#{exp.projectName}">
                            <f:facet name="header">
                                <h:outputText value="Project"/>
                            </f:facet>
                            <h:outputText value="#{exp.projectName}"/>
                        </rich:column>                        
                         <rich:column sortable="true" sortBy="#{exp.amount}">
                            <f:facet name="header">
                                <h:outputText value="Amount"/>
                            </f:facet>
                            <h:outputText value="#{exp.amount}"/>
                        </rich:column>
                    <rich:column rendered="#{!IncomeController.group}" sortable="true" sortBy="#{exp.date}">
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
                         <a4j:commandButton value="Delete" image="/img/cut.jpg" action="#{Income.delete}">
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
            <h:selectOneMenu value="#{IncomeController.prjId}">
                <f:selectItem itemLabel="All" itemValue="0"/>
                <f:selectItems value="#{ProjectController.asItem}"/>
            </h:selectOneMenu>
            <h:outputText value="Date From"/>
            <rich:calendar id="sdate" converter="dateConverter"
                           showFooter="false" styleClass="special"
                           datePattern="yyyy-MM-dd" popup="true"
                           value="#{IncomeController.dateFrom}">

            </rich:calendar>
            <h:outputText value="Date to"/>
            <rich:calendar id="s2date" converter="dateConverter"
                           showFooter="false" styleClass="special"
                           datePattern="yyyy-MM-dd" popup="true"
                           value="#{IncomeController.dateTo}">

            </rich:calendar>
            <h:outputText value="Show Records"/>
            <h:inputText value="#{IncomeController.recordCount}"/>
            <h:outputText value="Group Results"/>
            <h:selectBooleanCheckbox value="#{IncomeController.group}"/>
            <a4j:commandButton reRender="tbl" action="#{IncomeController.reLoad}" value="Filter Report"/>
            <h:commandButton value="Close" onclick="Richfaces.hideModalPanel('pnl');" />
            </h:panelGrid>
        </h:form>
        
    </rich:modalPanel>












</f:view>
