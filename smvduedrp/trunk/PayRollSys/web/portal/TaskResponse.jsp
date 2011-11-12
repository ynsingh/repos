<%-- 
    Document   : TaskResponse
    Created on : Apr 29, 2011, 10:37:27 AM
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
<!DOCTYPE html>
<f:view>
    <h:form>
        <rich:panel id="tp" header="Respond to Task">
             <rich:messages  >
            <f:facet name="infoMarker">
                <h:graphicImage url="/img/success.png"/>
            </f:facet>
            <f:facet name="errorMarker">
                <h:graphicImage url="/img/err.png"/>
            </f:facet>
        </rich:messages>
            <h:panelGrid columns="3">
                <rich:panel>
            <h:panelGrid columns="3">
                <f:facet name="caption">
                    <h:outputText style="font-size:12px;" value="Task Details"/>
                    <rich:separator/>
                </f:facet>
                <h:outputText value="Task ID"/>
                <h:outputText value="#{Task.editId}"/>
                <h:outputText value="*"/>
                <h:outputText value="priority"/>
                <h:outputText style="font-size:12px;" value="#{Task.priorityS}"/>
                <h:outputText value="*"/>
                <h:outputText value="Created On"/>
                <h:outputText value="#{Task.createDate}"/>
                <h:outputText value="*"/>
                <h:outputText value="Created By"/>
                <h:outputText value="#{Task.creator}"/>
                <h:outputText value="*"/>
                <h:outputText value="Project"/>
                <h:outputText value="#{Task.projectName}"/>
                <h:outputText value="*"/>
                <h:outputText value="Response"/>
                <h:selectOneMenu  value="#{Task.statusId}">
                    <f:selectItems value="#{TaskController.responses}"/>
                </h:selectOneMenu>
                <h:outputText value="*"/>
                <h:outputText value="Subject"/>
                <h:inputText readonly="true" value="#{Task.name}"/>
                <h:outputText value="*"/>
                <h:outputText value="Task Date" />
                <h:outputText value="#{Task.date}"/>
                <h:outputText value="*"/>
                <h:outputText value="Assign To"/>
                <h:inputText  id="empCode"value="#{Task.owner}" styleClass="fields" required="true" requiredMessage="Enter Employee Code" immediate="true"/>
                <rich:suggestionbox for="empCode" var="abc" fetchValue="#{abc.email}"  
                                    suggestionAction="#{SearchBean.getSuggestion}">
                    <h:column>
                        <h:outputText value="#{abc.name}"/>
                    </h:column>
                    <h:column>
                        <h:outputText value="#{abc.email}"/>
                    </h:column>
                </rich:suggestionbox>
                <h:commandButton action="#{Task.update}" value="Update">
                    <f:param name="taskId" value="#{Task.editId}"/>
                </h:commandButton>

            </h:panelGrid>
            </rich:panel>
                <rich:panel >
                    <h:panelGrid columns="2">
                        <h:outputText value="Remarks"/>
                        <h:inputTextarea value="#{TaskRemark.remark}" />
                    </h:panelGrid>
                    <a4j:commandButton reRender="tbl" value="Add Remark" action="#{TaskRemark.save}">
                        <f:param name="taskId" value="#{Task.editId}"/>
                    </a4j:commandButton>
                </rich:panel>
                <rich:panel header="Remark History">
                    <rich:dataTable id="tbl" value="#{Task.remarks}" var="rem">
                        <h:column>
                            <f:facet name="header">
                                <h:outputText value="Remark"/>
                            </f:facet>
                            <h:outputText value="#{rem.remark}"/>
                        </h:column>
                        <h:column>
                            <f:facet name="header">
                                <h:outputText value="Date"/>
                            </f:facet>
                            <h:outputText value="#{rem.date}"/>
                        </h:column>
                        <h:column>
                            <f:facet name="header">
                                <h:outputText value="User"/>
                            </f:facet>
                            <h:outputText value="#{rem.userName}"/>
                        </h:column>
                        
                    </rich:dataTable>
                </rich:panel>
            
            </h:panelGrid>

            <rich:panel id="ctrl" rendered="#{!Task.complete}" header="Task Controls">
                <a4j:commandButton rendered="#{Task.executing}" reRender="ctrl" action="#{Task.terminateTask}" value="Stop Task">
                    <f:param name="taskId" value="#{Task.editId}"/>
                </a4j:commandButton>
                <a4j:commandButton rendered="#{!Task.executing}" reRender="ctrl" action="#{Task.start}" value="Start">
                    <f:param name="taskId" value="#{Task.editId}"/>
                </a4j:commandButton>
                
                <a4j:commandButton action="#{Task.markAsComplete}" reRender="ctrl" value="Mark As Complete">
                    <f:param name="taskId" value="#{Task.editId}"/>
                </a4j:commandButton>
            </rich:panel>
        </rich:panel>
    </h:form>
</f:view>
