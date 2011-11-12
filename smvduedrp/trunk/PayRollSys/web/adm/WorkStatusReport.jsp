<%-- 
    Document   : WorkStatusReport
    Created on : May 20, 2011, 10:19:02 PM
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
    <rich:panel header="Current Working Status">
        
        <h:form>
            
        <rich:extendedDataTable groupingColumn="det"  value="#{WorkController.currentWorks}" var="work">
                <rich:column id="det" sortable="true" sortBy="#{work.empCode}">
                    <f:facet name="header">
                        <h:outputText value="Employee Code"/>
                    </f:facet>
                    <h:outputText  value="#{work.empCode}"/>
                </rich:column>
                 <rich:column sortable="true" sortBy="#{work.empName}">
                    <f:facet name="header">
                        <h:outputText value="Employee Name"/>
                    </f:facet>
                    <h:outputText value="#{work.empName}"/>
                </rich:column>
                 <rich:column sortable="true" sortBy="#{work.present}">
                    <f:facet name="header">
                        <h:outputText value="Present"/>
                    </f:facet>
                     <h:selectBooleanCheckbox value="#{work.present}"/>
                </rich:column>
                <rich:column sortable="true" sortBy="#{work.working}">
                    <f:facet name="header">
                        <h:outputText value="Working"/>
                    </f:facet>
                    <h:selectBooleanCheckbox value="#{work.working}"/>
                </rich:column>
            <rich:column width="20%" sortable="true" sortBy="#{work.subject}">
                    <f:facet name="header">
                        <h:outputText value="Current Work"/>
                    </f:facet>
                    <h:outputText value="#{work.subject}"/>
                </rich:column>


            <rich:column sortable="true" width="30%" sortBy="#{work.subject}">
                  <f:facet name="header">
                        <h:outputText value="Assigned Tasks"/>
                    </f:facet>
                    <rich:dataTable value="#{work.assignedTasks}" var="tsk">
                         <rich:column sortable="true" sortBy="#{tsk.priorityS}">
                            <f:facet name="header">
                                <h:outputText value="Priority"/>
                            </f:facet>
                             <h:outputText value="#{tsk.priorityS}"/>
                        </rich:column>
                          <rich:column sortable="true" sortBy="#{tsk.priorityS}">
                            <f:facet name="header">
                                <h:outputText value="Subject"/>
                            </f:facet>
                              <h:outputText value="#{tsk.name}"/>
                        </rich:column>
                    </rich:dataTable>
                </rich:column>
            </rich:extendedDataTable>
        </h:form>  
            
    </rich:panel>
</f:view>
