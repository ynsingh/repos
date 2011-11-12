<%-- 
    Document   : DailyWorkHourReport
    Created on : May 30, 2011, 1:03:48 AM
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
    <rich:panel header="Daily Work Report">
        
        <h:form>
            <h:outputText value="Report Date"/>
            <rich:calendar id="sdate2" converter="dateConverter" showWeekDaysBar="false"
                                   showFooter="false" styleClass="special"
                                   datePattern="yyyy-MM-dd" popup="true"
                                   required="true" value="#{ConciseWorkReport.date}"/>
            <h:outputText value="Daily Work Hours"/>
            <rich:inplaceInput value="#{ConciseWorkReport.workHours}"/>
            <a4j:commandButton reRender="tbl" value="Load Report" action="#{ConciseWorkReport.reLoad}"/>
            
            <rich:dataTable id="tbl" value="#{ConciseWorkReport.works}" var="work">
                <rich:column sortable="true" sortBy="#{work.date}">
                    <f:facet name="header">
                        <h:outputText value="Date"/>
                    </f:facet>
                    <h:outputText value="#{work.date}"/>
                </rich:column>
                <rich:column rendered="#{ConciseWorkReport.dateRendered}" sortable="true" sortBy="#{work.projectName}">
                    <f:facet name="header">
                        <h:outputText value="Project"/>
                    </f:facet>
                    <h:outputText value="#{work.projectName}"/>
                </rich:column>
                <rich:column sortable="true" sortBy="#{work.empCode}">
                    <f:facet name="header">
                        <h:outputText value="Employee Code"/>
                    </f:facet>
                    <h:outputText value="#{work.empCode}"/>
                </rich:column>
                 <rich:column sortable="true" sortBy="#{work.empName}">
                    <f:facet name="header">
                        <h:outputText value="Employee Name"/>
                    </f:facet>
                    <h:outputText value="#{work.empName}"/>
                </rich:column>
                 <rich:column sortable="true" sortBy="#{work.empCode}">
                    <f:facet name="header">
                        <h:outputText value="Total Time"/>
                    </f:facet>
                    <h:outputText value="#{work.timeString}"/>
                </rich:column>
                <rich:column sortable="true" sortBy="#{work.empCode}">
                    <f:facet name="header">
                        <h:outputText value="Idle Time"/>
                    </f:facet>
                    <h:outputText value="#{work.idleTime}"/>
                </rich:column>
            </rich:dataTable>


        </h:form>
    </rich:panel>

    <rich:modalPanel id="search">
        <rich:panel header="Filter">
            <h:form>
                <h:panelGrid columns="2">
                    <h:outputText value="Project"/>
                    <h:selectOneMenu value="#{ConciseWorkReport.prjId}">
                        <f:selectItem itemLabel="All" itemValue="0"/>
                        <f:selectItems value="#{ProjectController.asItem}"/>
                    </h:selectOneMenu>
                     <h:outputText value="Employee"/>
                     <h:selectOneMenu value="#{ConciseWorkReport.rfempCode}">
                        <f:selectItem itemLabel="All" itemValue=""/>
                        <f:selectItems value="#{SearchBean.asItem}"/>
                    </h:selectOneMenu>
                     <h:outputText value="Date From"/>
                     <rich:calendar id="sdate" converter="dateConverter" showWeekDaysBar="false"
                                   showFooter="false" styleClass="special"
                                   datePattern="yyyy-MM-dd" popup="true"
                                   required="true" value="#{ConciseWorkReport.rfDateFrom}"/>
                     <h:outputText value="Date To"/>
                     <rich:calendar id="sdate4" converter="dateConverter" showWeekDaysBar="false"
                                   showFooter="false" styleClass="special"
                                   datePattern="yyyy-MM-dd" popup="true"
                                   required="true" value="#{ConciseWorkReport.rfDateTo}"/>
                     <a4j:commandButton value="Filter" action="#{ConciseWorkReport.reLoad}" reRender="tbl"/>
                     <h:commandButton value="Close" onclick="Richfaces.hideModalPanel('search');" />
                </h:panelGrid>
            </h:form>
        </rich:panel>
    </rich:modalPanel>


</f:view>
