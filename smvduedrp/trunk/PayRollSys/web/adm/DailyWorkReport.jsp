<%-- 
    Document   : DailyWorkReport
    Created on : May 25, 2011, 12:59:28 AM
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
    <rich:panel header="Today's Work Reports">
         <rich:messages  >
        <f:facet name="infoMarker">
            <h:graphicImage url="/img/success.png"/>
        </f:facet>
        <f:facet name="errorMarker">
            <h:graphicImage url="/img/err.png"/>
        </f:facet>
    </rich:messages>
        <h:form>
            <a4j:commandButton value="Fetch From Online Sheet"
                               action="#{DailyWorkController.refreshAuto}" reRender="tbl"/>
            <rich:dataTable id="tbl" value="#{DailyWorkController.allWorks}" binding="#{DailyWorkController.dataGrid}" var="work">
                <rich:column sortable="true" sortBy="#{work.empName}">
                <f:facet name="header">
                    <h:outputText value="Employee Name"/>
                </f:facet>
                <h:outputText value="#{work.empName}"/>
            </rich:column>
            <rich:column sortable="true" sortBy="#{work.prjName}">
                <f:facet name="header">
                    <h:outputText value="Project"/>
                </f:facet>
                <h:outputText value="#{work.prjName}"/>
            </rich:column>
            <rich:column sortable="true" sortBy="#{work.timeString}">
                <f:facet name="header">
                    <h:outputText value="Time"/>
                </f:facet>
                <h:outputText value="#{work.timeString}"/>
            </rich:column>
            <rich:column sortable="true" sortBy="#{work.remark}">
                <f:facet name="header">
                    <h:outputText value="Work Details"/>
                </f:facet>
                <h:outputText value="#{work.remark}"/>
            </rich:column>
                <rich:column >
                <f:facet name="header">
                    <h:outputText value="Select"/>
                </f:facet>
                    <h:selectBooleanCheckbox value="#{work.selected}"/>
            </rich:column>
                 <rich:column sortable="true" sortBy="#{work.remark}">
                <f:facet name="header">
                    <h:outputText value="Comments / Remarks"/>
                </f:facet>
                     <rich:inplaceInput showControls="true" inputWidth="200" cancelControlIcon="true" value="#{work.adminComments}"/>
            </rich:column>
        </rich:dataTable>
            <a4j:commandButton value="Accept Selected" action="#{DailyWorkController.approve}"/>
        </h:form>
    </rich:panel>
</f:view>
