<%-- 
    Document   : TaskManager
    Created on : Apr 28, 2011, 11:12:22 PM
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
        <link rel="stylesheet" type="text/css" href="task.css"/>

    </head>
    <f:view>
        <rich:panel header="Task Management">
            <h:commandButton  onclick="Richfaces.showModalPanel('pnl');" value="Add New"/>
            <h:commandButton onclick="Richfaces.showModalPanel('filter');" value="Advance Filter"/>
            <div style="float:right;">
                <h:form>

                    <h:outputText style="foreground-color:red;" value="Quick Filters > "/>
                    <a4j:commandButton  action="#{TaskController.simpleFilter}" reRender="tbl"  value="In Progress">
                        <f:param name="sql" value=" ep_current_status=1 "/>
                    </a4j:commandButton>
                    <a4j:commandButton  action="#{TaskController.simpleFilter}" reRender="tbl"  value="Completed">
                        <f:param name="sql" value=" ts_complete=1 "/>
                    </a4j:commandButton>
                    <a4j:commandButton  action="#{TaskController.simpleFilter}" reRender="tbl"  value="Created By ME">
                        <f:param name="sql" value=" ts_creator=#{ActiveProfile.profile.empId}"/>
                    </a4j:commandButton>
                    <a4j:commandButton  action="#{TaskController.simpleFilter}" reRender="tbl"  value="Assigned To ME">
                        <f:param name="sql" value=" ts_owner='#{ActiveProfile.profile.email}'"/>
                    </a4j:commandButton>
                    <a4j:commandButton  action="#{TaskController.simpleFilter}" reRender="tbl"  value="All">
                        <f:param name="sql" value=""/>
                    </a4j:commandButton>
                </h:form>
            </div>

            <rich:messages  >
                <f:facet name="infoMarker">
                    <h:graphicImage url="/img/success.png"/>
                </f:facet>
                <f:facet name="errorMarker">
                    <h:graphicImage url="/img/err.png"/>
                </f:facet>
            </rich:messages>
            <h:form>
               
                <rich:dataTable rowClasses="even-row,odd-row" binding="#{TaskController.dataGrid}" id="tbl" width="100%" value="#{TaskController.tasks}" var="emp">
                    <rich:column width="5%">
                        <f:facet  name="header" >
                            <h:outputText value="Select"/>
                        </f:facet>
                        <h:selectBooleanCheckbox value="#{emp.selected}" />
                    </rich:column>
                    <rich:column width="20%">
                        <f:facet  name="header" >
                            <h:outputText value="Current Status"/>
                        </f:facet>
                        <h:graphicImage rendered="#{emp.complete}" url="/img/complete.png" />
                        <h:graphicImage rendered="#{emp.executing}"  url="/img/idle.png" />
                        <h:graphicImage rendered="#{!emp.complete&&!emp.executing}"  url="/img/pending.png" />
                        <h:outputText rendered="#{emp.executing}" value="In progress, #{emp.startDateTime}  "/>
                        <h:outputText rendered="#{emp.executing}" value=" #{emp.worker}  "/>
                        <h:outputText rendered="#{emp.complete}" value="Complete "/>
                        <h:outputText rendered="#{!emp.complete&&!emp.executing}" value="Idle "/>
                        <a4j:commandButton id="exe" action="#{Task.terminateTask}" reRender="tbl" rendered="#{emp.executing}" image="/img/stop.jpg">
                            <f:param name="termId" value="#{emp.id}"/>
                        </a4j:commandButton>
                        <rich:toolTip value="Task is currently in Progress. Click to Stop Executing Task." for="exe"/>

                    </rich:column>
                    <rich:column sortable="true" sortBy="#{emp.name}" width="20%">
                        <f:facet  name="header" >
                            <h:outputText value="Subject"/>
                        </f:facet>
                        <h:outputLabel style="fgcolor:red" value="#{emp.name}" />
                    </rich:column>
                    <rich:column sortable="true" sortBy="#{emp.creationDate}" width="10%">
                        <f:facet  name="header" >
                            <h:outputText value="Created On"/>
                        </f:facet>
                        <h:outputText value="#{emp.creationDate}" />
                    </rich:column>
                    <rich:column sortable="true" sortBy="#{emp.priorityS}" width="10%">
                        <f:facet  name="header" >
                            <h:outputText value="Priority"/>
                        </f:facet>
                        <h:outputText value="#{emp.priorityS}" />
                    </rich:column>
                    <rich:column sortable="true" sortBy="#{emp.creator}" width="10%">
                        <f:facet  name="header" >
                            <h:outputText value="Creator"/>
                        </f:facet>
                        <h:outputText value="#{emp.creator}" />
                    </rich:column>

                    <rich:column sortable="true" sortBy="#{emp.date}" width="10%">
                        <f:facet name="header" >
                            <h:outputText value="Due Date"/>
                        </f:facet>
                        <h:outputText value="#{emp.date}" />
                    </rich:column>
                    <rich:column sortable="true" sortBy="#{emp.owner}" width="7%">
                        <f:facet name="header" >
                            <h:outputText value="Assigned To"/>
                        </f:facet>
                        <h:outputText value="#{emp.owner}" />
                    </rich:column>
                    <rich:column sortable="true" sortBy="#{emp.projectName}">
                        <f:facet name="header" >
                            <h:outputText value="Project"/>
                        </f:facet>
                        <h:outputText value="#{emp.projectName}" />
                    </rich:column>

                    <rich:column>

                        <f:facet name="header" >
                            <h:outputText value="Respond"/>
                        </f:facet>
                        <h:commandButton action="#{UserBean.taskPage}" rendered="#{emp.edit}"  value="Respond" >
                            <f:param name="taskId" value="#{emp.id}"/>
                        </h:commandButton>
                    </rich:column>
                </rich:dataTable>
                <a4j:commandButton action="#{TaskController.delete}" reRender="tbl" value="Delete Selected"/>
                <a4j:commandButton action="#{TaskController.email}" value="E mail Selected"/>
                <h:outputText value="Mail to"/>
                <h:inputText value="#{TaskController.mailTo}"/>
            </h:form>
        </rich:panel>






        <rich:modalPanel id="filter" width="300" autosized="true">
            <rich:panel header="Filter Tasks">
                <h:form>
                    <h:panelGrid columns="2">
                        <h:outputText value="Context"/>
                        <h:selectOneMenu value="#{TaskController.context}">
                            <f:selectItems value="#{TContextController.asItem}"/>
                        </h:selectOneMenu>
                        <h:outputText value="Project"/>
                        <h:selectOneMenu value="#{TaskController.projectId}">
                            <f:selectItem itemLabel="All" itemValue="0"/>
                            <f:selectItems value="#{ProjectController.asItem}"/>
                        </h:selectOneMenu>
                        <h:outputText value="Priority"/>
                        <h:selectOneMenu value="#{TaskController.priority}">
                            <f:selectItem itemLabel="All" itemValue="0"/>
                            <f:selectItems value="#{TaskController.priorities}"/>
                        </h:selectOneMenu>
                        <h:outputText value="Status"/>
                        <h:selectOneMenu value="#{TaskController.status}">
                            <f:selectItem itemLabel="All" itemValue="0"/>
                            <f:selectItems value="#{TaskController.responses}"/>
                        </h:selectOneMenu>
                        <h:outputText value="Date From"/>
                        <rich:calendar id="sdate" converter="dateConverter"
                                       showFooter="false" styleClass="special"
                                       datePattern="yyyy-MM-dd" popup="true"
                                       value="#{TaskController.dateFrom}">

                        </rich:calendar>
                        <h:outputText value="Date to"/>
                        <rich:calendar id="s2date" converter="dateConverter"
                                       showFooter="false" styleClass="special"
                                       datePattern="yyyy-MM-dd" popup="true"
                                       value="#{TaskController.dateTo}">

                        </rich:calendar>
                        <h:outputText value="Show Records"/>
                        <h:inputText value="#{TaskController.recordCount}"/>
                    </h:panelGrid>
                    <a4j:commandButton value="Search" action="#{TaskController.reLoad}" reRender="tbl"/>
                    <h:commandButton value="Close" onclick="Richfaces.hideModalPanel('filter');" />
                </h:form>
            </rich:panel>
        </rich:modalPanel>

        <rich:modalPanel width="400" height="400"  moveable="true" id="pnl">
            <h:form>
                <rich:panel header="Add New Task">
                    <h:panelGrid columns="3">
                        <h:outputText value="Project"/>
                        <h:selectOneMenu value="#{Task.folderId}">
                            <f:selectItems value="#{ProjectController.asItem}"/>
                        </h:selectOneMenu>
                        <h:outputText value="*"/>
                        <h:outputText value="Priority"/>
                        <h:selectOneMenu value="#{Task.priority}">
                            <f:selectItems value="#{TaskController.priorities}"/>
                        </h:selectOneMenu>
                        <h:outputText value="*"/>
                        <h:outputText value="Subject"/>
                        <h:inputTextarea value="#{Task.name}"/>
                        <h:outputText value="*"/>
                        <h:outputText value="Due Date" />
                        <rich:calendar converter="dateConverter" showWeekDaysBar="false"
                                       showFooter="false" styleClass="special"
                                       datePattern="yyyy-MM-dd" id="empDoj" popup="true"
                                       value="#{Task.date}">

                        </rich:calendar>
                        <h:outputText value="*"/>
                        <h:outputText value="Due Time"/>
                        <h:inputText value="#{Task.time}"/>
                        <h:outputText value=""/>
                        <h:outputText value="Context"/>
                        <h:selectOneMenu value="#{Task.ctxId}">
                            <f:selectItems value="#{TContextController.asItem}"/>
                        </h:selectOneMenu>
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
                        <h:outputText value="Notes"/>
                        <h:inputTextarea rows="5" cols="40" value="#{Task.notes}"/>
                        <h:outputText value=""/>
                        <a4j:commandButton action="#{Task.save}" reRender="tbl" value="Save"/>
                        <h:commandButton value="Close" onclick="Richfaces.hideModalPanel('pnl');" />
                    </h:panelGrid>
                </rich:panel>
            </h:form>

        </rich:modalPanel>






    </f:view>
</html>
