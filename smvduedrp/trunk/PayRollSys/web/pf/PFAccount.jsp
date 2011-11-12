<%-- 
    Document   : PFConfig
    Created on : Apr 15, 2011, 11:09:59 AM
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
    <rich:panel header="Annual PF Statement">

        <rich:messages  >
            <f:facet name="infoMarker">
                <h:graphicImage url="/img/success.png"/>
            </f:facet>
            <f:facet name="errorMarker">
                <h:graphicImage url="/img/err.png"/>
            </f:facet>
        </rich:messages>
        <h:form>
            <rich:panel>
                <h:panelGrid columns="4">
                    <h:outputText  styleClass="Label" value="Employee Code"/>
                    <h:inputText   id="empCode"value="#{PFAccountController.empCode}"  requiredMessage="Enter Employee Code" immediate="true"/>
                    <rich:suggestionbox for="empCode" var="abc" fetchValue="#{abc.code}"  suggestionAction="#{SearchBean.getSuggestion}">
                        <h:column>
                            <h:outputText value="#{abc.name}"/>
                        </h:column>
                        <h:column>
                            <h:outputText value="#{abc.code}"/>
                        </h:column>
                    </rich:suggestionbox>                    
                    <a4j:commandButton reRender="tbl,sumry" value="Load PF Details" action="#{PFAccountController.loadData}"/>
                </h:panelGrid>
            </rich:panel>
            <h:panelGrid  columns="2">
                <f:facet name="caption">
                    <h:outputText value="PF Statement"/>
                </f:facet>
                <rich:dataTable width="80%" id="tbl" value="#{PFAccountController.openingAccount.pfData}" var="pft">
                <rich:column width="10%">
                    <f:facet name="header">
                        <h:outputText value="Month"/>
                    </f:facet>
                    <h:outputText value="#{pft.month}"/>
                </rich:column>

                    <%-- Editing Of columns withdrawal & recovery adjust in last in the PF A/c table  --%>

                <rich:column width="10%">
                    <f:facet name="header">
                        <h:outputText value="Employee's Cont."/>
                    </f:facet>
                    <h:outputText value="#{pft.amount}"/>
                </rich:column>
                
                <rich:column width="10%">
                    <f:facet name="header">
                        <h:outputText value="Employer's Cont."/>
                    </f:facet>
                    <h:outputText value="#{pft.amount}"/>
                </rich:column>

                    <rich:column width="10%">
                    <f:facet name="header">
                        <h:outputText value="Employees Cum. Amt"/>
                    </f:facet>
                    <h:outputText value="#{pft.cumulative}"/>
                </rich:column>
                
                 
                    <rich:column width="10%">
                    <f:facet name="header">
                        <h:outputText value="Employer's Cum. Amt"/>
                    </f:facet>
                    <h:outputText value="#{pft.emplCum}"/>
                </rich:column>

                <rich:column width="10%">
                    <f:facet name="header">
                        <h:outputText value="Withdrawal"/>
                    </f:facet>
                    <h:outputText value="#{pft.withdrawal}"/>
                </rich:column>

                    <rich:column width="10%">
                    <f:facet name="header">
                        <h:outputText value="Recovery"/>
                    </f:facet>
                    <h:outputText value="#{pft.recovery}"/>
                </rich:column>

            </rich:dataTable>
            <rich:panel id="sumry" header="PF Report Summery" style="height:320px;" >
                <h:panelGrid columns="2">

                <h:outputText value="Employee Name"/>
                <h:outputText value="#{PFAccountController.openingAccount.empName}"/>
                <rich:separator/><rich:separator/>
                <h:outputText value="Employee's Opening Balance"/>
                <h:outputText value="#{PFAccountController.openingAccount.opBal}"/>
                <%--    <h:outputText value="Employer's Opening Balance"/>
                <h:outputText value="#{PFAccountController.openingAccount.employerBalance}"/>  --%>
                <h:outputText value="Total withdrawal"/>
                <h:outputText value="#{PFAccountController.openingAccount.totalWithDrawal}"/>
                <h:outputText value="Interest on Opening Balance"/>
                <h:outputText value="#{PFAccountController.openingAccount.interestOnOpbal}"/>
                <%--   <h:outputText value="Interest on Employer's Opening Balance"/>
                <h:outputText value="#{PFAccountController.openingAccount.interestOnEmplBal}"/>   --%>
                <h:outputText value="Interest on annual Deposite"/>
                <h:outputText value="#{PFAccountController.openingAccount.interestOnDeposite}"/>
                <h:outputText value="Total Interest"/>
                <h:outputText value="#{PFAccountController.openingAccount.totalInterest}"/>
                <h:outputText value="Total Recovery"/>
                <h:outputText value="#{PFAccountController.openingAccount.totalPfRecovery}"/>
                <h:outputText value="Closing Balance"/>
                <h:outputText value="#{PFAccountController.openingAccount.closingBalance}"/>
                </h:panelGrid>
            </rich:panel>
            </h:panelGrid>
        </h:form>

    </rich:panel>
</f:view>
