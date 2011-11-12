<%-- 
    Document   : TimeManager
    Created on : Apr 22, 2011, 6:31:21 PM
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

    <rich:panel id="workpnl" >
        <h:form>
            <rich:messages>
                <f:facet name="errorMarker">
                    <h:graphicImage url="/img/err.png"/>
                </f:facet>
            </rich:messages>
            <h:selectOneMenu disabled="#{WorkController.active}" value="#{WorkController.prjId}">
                <f:selectItems value="#{ProjectController.asItem}"/>
            </h:selectOneMenu>
            <h:graphicImage url="../img/waiter.gif" rendered="#{WorkController.active}"/>
            <a4j:commandButton value="Start" action="#{WorkController.save}"
                               reRender="workpnl"   rendered="#{!WorkController.active}"/>
            <a4j:commandButton reRender="workpnl" value="Stop" action="#{WorkController.stop}" rendered="#{WorkController.active}"/>
            <h:outputText value="Select Project and click Start to Start Monitoring your Current Work" rendered="#{!WorkController.active}"/>
            <rich:panel rendered="#{WorkController.active}">
                <h:panelGrid columns="3">
                    <h:outputText value="Work Details"/>
                    <h:inputTextarea value="#{WorkController.remarks}"/>
                    <a4j:commandButton action="#{WorkController.saveRemarks}" value="Update"/>
                </h:panelGrid>
            </rich:panel>
        </h:form>

        <rich:panel>
            <h:commandButton onclick="Richfaces.showModalPanel('pnl');" value="Filter Options"/>
            <rich:extendedDataTable groupingColumn="det"  value="#{WorkController.recentWorks}" var="work">
                <rich:column id="det" sortable="true" sortBy="#{work.date}">
                    <f:facet name="header">
                        <h:outputText value="Date"/>
                    </f:facet>
                    <h:outputText  value="#{work.date}"/>
                </rich:column>
                <rich:column>
                    <f:facet name="header">
                        <h:outputText value="Project"/>
                    </f:facet>
                    <h:outputText value="#{work.project.name}"/>
                </rich:column>
                <rich:column>
                    <f:facet name="header">
                        <h:outputText value="Start Time"/>
                    </f:facet>
                    <h:outputText value="#{work.startTime}"/>
                </rich:column>
                <rich:column>
                    <f:facet name="header">
                        <h:outputText value="Stop Time"/>
                    </f:facet>
                    <h:outputText value="#{work.endTime}"/>
                </rich:column>
                <rich:column>
                    <f:facet name="header">
                        <h:outputText value="Total Time"/>
                    </f:facet>
                    <h:outputText value="#{work.totalTime}"/>
                </rich:column>
                <rich:column width="60%">
                    <f:facet name="header">
                        <h:outputText value="Work Details"/>
                    </f:facet>
                    <rich:dataTable value="#{work.remarksList}" var="rems">
                        <rich:column>

                            <h:outputText value="#{rems}"/>
                        </rich:column>
                    </rich:dataTable>
                </rich:column>
            </rich:extendedDataTable>
        </rich:panel>
    </rich:panel>
</f:view>
