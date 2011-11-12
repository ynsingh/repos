<%-- 
    Document   : LeaveManager
    Created on : Jan 4, 2011, 2:09:17 AM
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
            <rich:panel style="width:720px;" header="Employee's Loan Details">
            <h:panelGrid columns="2">
                <h:commandButton onclick="Richfaces.showModalPanel('pnl');" value="Add New"/>
            <rich:messages>
                       <f:facet name="infoMarker">
                            <h:graphicImage url="/img/success.png"/>
                       </f:facet>
                    </rich:messages>
            </h:panelGrid>
            
                <rich:dataTable style="width:700px;" value="#{LoanRequestController.loans}" var="leave">
                <h:column>
                    <f:facet name="header">
                        <h:outputText value="Loan AMount"/>
                    </f:facet>
                    <h:outputText value="#{leave.reqAmount}"/>
                </h:column>
                <h:column>
                    <f:facet name="header">
                        <h:outputText value="Requested On"/>
                    </f:facet>
                    <h:outputText value="#{leave.reqDate}"/>
                </h:column>
                <h:column>
                    <f:facet name="header">
                        <h:outputText value="Duration(Year)"/>
                    </f:facet>
                    <h:outputText value="#{leave.duration}"/>
                </h:column>
                <h:column>
                    <f:facet name="header">
                        <h:outputText value="Installment"/>
                    </f:facet>
                    <h:outputText value="#{leave.installment}"/>
                </h:column>
                 <h:column>
                    <f:facet name="header">
                        <h:outputText value="Approved"/>
                    </f:facet>
                     <h:selectBooleanCheckbox style="font-color:red;" label="NO" disabled="true" value="#{leave.approve}"/>
                </h:column>
            </rich:dataTable>
            </rich:panel>

            <rich:modalPanel id="pnl">
                <h:form>
                    <rich:panel header="New Loan Request">
                        <h:panelGrid columns="2">
                            <h:outputText value="Employee ID"/>
                            <h:outputText value="#{LoanRequest.employee.empId}"/>
                            <h:outputText value="Loan Amount" />
                            <h:inputText value="#{LoanRequest.reqAmount}"/>
                            <h:outputText value="Installment Amount" />
                            <h:inputText value="#{LoanRequest.installment}"/>
                            <h:outputText value="Duration(Year)" />
                            <h:inputText value="#{LoanRequest.duration}"/>
                            <h:outputText value="Loan Type"/>
                            <h:selectOneMenu  id="leaveType" value="#{LoanRequest.loanType}">
                                <f:selectItems value="#{LoanController.asItem}"/>
                            </h:selectOneMenu>
                            
                            <h:commandButton action="#{LoanRequest.save}" value="Save"/>
                            <h:commandButton value="Close" onclick="Richfaces.hideModalPanel('pnl');" />
                        </h:panelGrid>
                    </rich:panel>
                </h:form>

            </rich:modalPanel>
        </f:view>
    </body>
</html>
