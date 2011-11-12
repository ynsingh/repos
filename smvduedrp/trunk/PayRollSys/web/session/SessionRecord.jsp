<%-- 
    Document   : EmpOrder
    Created on : Apr 26, 2011, 4:59:14 PM
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
<!DOCTYPE html>
<%@ taglib prefix="f" uri="http://java.sun.com/jsf/core" %>
<%@ taglib prefix="h" uri="http://java.sun.com/jsf/html" %>
<%@ taglib uri="http://richfaces.org/a4j" prefix="a4j"%>
<%@ taglib uri="http://richfaces.org/rich" prefix="rich"%>


<f:view>
    <rich:panel header="Employee Hirarchy setup">
        <h:form>
            <rich:panel>
                    <h:panelGrid columns="4">
                    <h:outputText  styleClass="Label" value="Session"/>
                    <h:selectOneMenu value="">
                        
                    </h:selectOneMenu>
                    <a4j:commandButton id="ldbtn" reRender="tbl" action="#{OrderController.load}" styleClass="panel" value="Load Hirarchy"/>
                    </h:panelGrid>
                <rich:panel  id="data">
                <h:outputText value="Under "/>
                <rich:dataTable id="tbl" binding="#{OrderController.dataGrid}"  var="emp" value="#{OrderController.children}">
                     <h:column>
		    <f:facet name="header" >
                        <h:outputText value="Sr.No"/>
		    </f:facet>
		    <h:outputText value="#{emp.srNo}" />
		  </h:column>
                  <h:column>
		    <f:facet name="header">
                        <h:outputText value="Code"/>
		    </f:facet>
		    <h:outputText value="#{emp.code}" />
		  </h:column>
                  <h:column>
		    <f:facet name="header">
                        <h:outputText value="Name"/>
		    </f:facet>
		    <h:outputText value="#{emp.name}" />
		  </h:column>
                     <h:column>
		    <f:facet name="header">
                        <h:outputText value="Department"/>
		    </f:facet>
		    <h:outputText value="#{emp.deptName}" />
		  </h:column>
                  <h:column>
		    <f:facet name="header">
                        <h:outputText value="Designation "/>
		    </f:facet>
		    <h:outputText value="#{emp.desigName}" />
		  </h:column>
                     <h:column>
		    <f:facet name="header">
                        <h:outputText value="Select"/>
		    </f:facet>
                         <h:selectBooleanCheckbox value="#{emp.selected}" />
		  </h:column>
                </rich:dataTable>
                <a4j:commandButton value="Remove Selected"/>
                </rich:panel>
                </rich:panel>
            <rich:panel header="Add New ">
                <rich:dataTable id="tblmain" binding="#{OrderController.masterGrid}"  var="emp" 
                            value="#{SearchBean.emps}">
                     <h:column>
		    <f:facet name="header" >
                        <h:outputText value="Sr.No"/>
		    </f:facet>
		    <h:outputText value="#{emp.srNo}" />
		  </h:column>
                  <h:column>
		    <f:facet name="header">
                        <h:outputText value="Code"/>
		    </f:facet>
		    <h:outputText value="#{emp.code}" />
		  </h:column>
                  <h:column>
		    <f:facet name="header">
                        <h:outputText value="Name"/>
		    </f:facet>
		    <h:outputText value="#{emp.name}" />
		  </h:column>
                     <h:column>
		    <f:facet name="header">
                        <h:outputText value="Department"/>
		    </f:facet>
		    <h:outputText value="#{emp.deptName}" />
		  </h:column>
                  <h:column>
		    <f:facet name="header">
                        <h:outputText value="Designation "/>
		    </f:facet>
		    <h:outputText value="#{emp.desigName}" />
		  </h:column>
                     <h:column>
		    <f:facet name="header">
                        <h:outputText value="Select"/>
		    </f:facet>
                         <h:selectBooleanCheckbox value="#{emp.selected}" />
		  </h:column>
                </rich:dataTable>
                <a4j:commandButton action="#{OrderController.addMember}" value="Add Selected"/>
            </rich:panel>
        </h:form>
    </rich:panel>
</f:view>
