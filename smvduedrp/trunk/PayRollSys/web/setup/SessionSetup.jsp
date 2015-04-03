<%--
    Document   : DefaultSalaryData
    Created on : Dec 24, 2010, 11:37:17 AM
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

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Financial Years</title>
    </head>
    <body>
        <f:view>
            <rich:panel header="Existing Financial years">
                <div align="right">
                    <a4j:commandLink   onclick="javascript:window.print();" style="margin-right:10px;">
                    <h:graphicImage value="/img/Printer-icon.png" alt="Print"  /> 
                    </a4j:commandLink>
                </div>
                <h:panelGrid columns="3">
                    <h:commandButton onclick="Richfaces.showModalPanel('pnl');" value="Add New"/>
                    <rich:messages>
                        <f:facet name="infoMarker">
                            <h:graphicImage url="/img/success.png"/>
                        </f:facet>
                        <f:facet name="errorMarker">
                            <h:graphicImage url="/img/err.png"/>
                        </f:facet>
                    </rich:messages>
                </h:panelGrid>
                <h:form>
                    <rich:dataTable id="tbl"  binding="#{SessionController.dataGrid}"  value="#{SessionController.sessions}" var="sess">
                        <h:column >
                            <f:facet name="header">
                                <h:outputText value="Session Code"/>
                            </f:facet>
                            <h:outputText value="#{sess.code}" />
                        </h:column>
                        <h:column >
                            <f:facet name="header">
                                <h:outputText value="Session Name"/>
                            </f:facet>
                            <rich:inplaceInput value="#{sess.name}" />
                        </h:column>
                        <h:column >
                            <f:facet name="header">
                                <h:outputText value="Start Date"/>
                            </f:facet>
                            <h:outputText value="#{sess.startDate}" />
                        </h:column>
                        <h:column >
                            <f:facet name="header">
                                <h:outputText value="End Date"/>
                            </f:facet>
                            <h:outputText value="#{sess.endDate}" />
                        </h:column>
                        <h:column >
                            <f:facet name="header">
                                <h:outputText value="Current Session"/>
                            </f:facet>
                            <h:selectBooleanCheckbox value="#{sess.current}" />
                        </h:column>
                    </rich:dataTable>
                    <h:panelGrid columns="2">
                        <h:commandButton value="Update" action="#{SessionController.update}"/>
                    </h:panelGrid>
                </h:form>
            </rich:panel>
            <rich:modalPanel id="pnl" width="500" height="300">
                <rich:panel header="Add New Session">
                    <h:form>
                        <h:panelGrid columns="3">
                            <h:outputText value="Session Name"/>
                            <h:inputText id="deptName" required="true" requiredMessage="Please Enter Session Name (e.g 2011-12)"
                                         value="#{SessionBean.name}"/>
                            <h:message styleClass="error" for="deptName" tooltip="Employee Type"/>
                            <h:outputText value="Session Start Date"/>
                            <rich:calendar converter="dateConverter" showWeekDaysBar="false"
                                           showFooter="false" styleClass="special"
                                           datePattern="yyyy-MM-dd" id="startDate" popup="true"
                                           required="true" requiredMessage="Select Session Start date"
                                           value="#{SessionBean.startDate}">
                            </rich:calendar>
                            <h:message styleClass="error" for="deptName" tooltip="Employee Type"/>
                            <h:outputText value="Session End Date"/>
                            <rich:calendar converter="dateConverter" showWeekDaysBar="false"
                                           showFooter="false" styleClass="special"
                                           datePattern="yyyy-MM-dd" id="endDate" popup="true"
                                           required="true" requiredMessage="Select Session end date"
                                           value="#{SessionBean.endDate}">
                            </rich:calendar>
                            <h:message styleClass="error" for="deptName" tooltip="Employee Type"/>
                        </h:panelGrid>
                        <a4j:commandButton value="Save" action="#{SessionBean.save}" reRender="tbl" oncomplete="#{rich:component('pnl')}.hide()"/>
                        <h:commandButton value="Close" onclick="Richfaces.hideModalPanel('pnl');" />
                    </h:form>
                </rich:panel>
            </rich:modalPanel>
        </f:view>
    </body>
</html>
