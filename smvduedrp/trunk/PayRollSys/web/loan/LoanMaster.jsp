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
        <title>JSP Page</title>
    </head>
    <body>
        <f:view>
            <rich:panel header="Existing Loan Types">
                <h:panelGrid columns="2">
                 <h:commandButton onclick="Richfaces.showModalPanel('pnl');" value="Add New"/>
                 <h:outputText style="font-size:1em;background-color:red;" value="Total : #{LoanController.total}"/>
                  <rich:messages  >
                        <f:facet name="infoMarker">
                            <h:graphicImage url="/img/success.png"/>
                        </f:facet>
                        <f:facet name="errorMarker">
                            <h:graphicImage url="/img/err.png"/>
                        </f:facet>
                    </rich:messages>
                 </h:panelGrid>
                <h:form> 
                        
                    <rich:dataTable id="tbl" binding="#{LoanController.dataGrid}"
                                    value="#{LoanController.loans}" var="dept">
                        <h:column >
                            <f:facet name="header">
                                <h:outputText value=" Code"/>
                            </f:facet>
                            <h:outputText value="#{dept.code}" />
                        </h:column>
                        <h:column >
                            <f:facet name="header">
                                <h:outputText value="Type Name"/>
                            </f:facet>
                            <rich:inplaceInput value="#{dept.name}" />
                        </h:column>
                        <h:column >
                            <f:facet name="header">
                                <h:outputText value="Maximum Amount"/>
                            </f:facet>
                            <rich:inplaceInput value="#{dept.maxAmount}" />
                        </h:column>
                        <h:column >
                            <f:facet name="header">
                                <h:outputText value="% Interest"/>
                            </f:facet>
                            <rich:inplaceInput value="#{dept.interest}" />
                        </h:column>
                    </rich:dataTable>
                    <h:panelGrid columns="2">
                        <h:commandButton value="Update" action="#{DepartmentControllerBean.update}"/>                    
                    </h:panelGrid>
                </h:form>
            </rich:panel>
            <rich:modalPanel id="pnl">
                    <rich:panel header="Add New Loan Type">
                    <h:form>
                        <h:panelGrid columns="3">
                            <h:outputText value="Type Name"/>
                            <h:inputText id="deptName" required="true" requiredMessage="Please Enter Type Name" value="#{Loan.name}"/>
                            <h:message styleClass="error" for="deptName" tooltip="Employee Type"/>
                            <h:outputText value="Maximum Amount"/>
                            <h:inputText  required="true"  value="#{Loan.maxAmount}"/>
                            <h:message styleClass="error" for="deptName" tooltip="Employee Type"/>
                            <h:outputText value="Interest %"/>
                            <h:inputText  required="true"  value="#{Loan.interest}"/>
                            <h:message styleClass="error" for="deptName" tooltip="Employee Type"/>
                    <a4j:commandButton value="Save" reRender="tbl" action="#{Loan.save}"  />
                    <h:commandButton value="Close" onclick="Richfaces.hideModalPanel('pnl');" />
                        </h:panelGrid>
                </h:form>
                </rich:panel>
                </rich:modalPanel>
        </f:view>
    </body>
</html>
