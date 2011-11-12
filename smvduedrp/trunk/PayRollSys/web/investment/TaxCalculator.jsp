<%-- 
    Document   : TaxCalculator
    Created on : Feb 10, 2011, 10:57:54 AM
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

<%@taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@taglib prefix="h" uri="http://java.sun.com/jsf/html"%>

<%@ taglib uri="http://richfaces.org/a4j" prefix="a4j"%>
<%@ taglib uri="http://richfaces.org/rich" prefix="rich"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<f:view>
    <rich:panel header="Annual Tax Calculator">
        <h:form>
             <rich:messages>
                       <f:facet name="infoMarker">
                            <h:graphicImage url="/img/success.png"/>
                       </f:facet>
                    </rich:messages>
            <h:panelGrid columns="3">
                <h:outputText value="Employee Code"/>
                <h:inputText id="empcCode" value="#{TaxControllerBean.empId}"/>
                 <rich:suggestionbox for="empcCode" var="abc" fetchValue="#{abc.code}"  suggestionAction="#{SearchBean.getSuggestion}">
                                    <h:column>
                                        <h:outputText value="#{abc.name}"/>
                                    </h:column>
                                    <h:column>
                                        <h:outputText value="#{abc.code}"/>
                                    </h:column>
                                </rich:suggestionbox>
                <a4j:commandButton rendered="sume" value="Submit" onclick="submit();" reRender="dtable"/>
            </h:panelGrid>
        </h:form>
        <h:panelGrid columns="2">
        <rich:dataTable id="dTable" value="#{TaxControllerBean.taxBeans}" binding="#{TaxControllerBean.datagrid}" var="tax">
           
             <h:column>
                <f:facet name="header">
                    <h:outputText value="Particular"/>
                </f:facet>
                <h:outputText value="#{tax.investmentHead}"/>
            </h:column>
            <h:column>
                <f:facet name="header">
                    <h:outputText value="Actual Investment"/>
                </f:facet>
                <h:inputText value="#{tax.actualAmount}"/>
            </h:column>
            <h:column>
                <f:facet name="header">
                    <h:outputText value="Effective Investment"/>
                </f:facet>
                <h:inputText value="#{tax.effectiveAmount}"/>
            </h:column>
            <h:column>
                <f:facet name="header">
                    <h:outputText value="Effective Saving"/>
                </f:facet>
                <h:inputText value="#{tax.percentDeduction}"/>
            </h:column>
        </rich:dataTable>
            <rich:panel id="sume" header="Tax Summery">
                <h:form>
                    <h:inputHidden value="#{TaxControllerBean.empId}"/>
                    <h:panelGrid columns="2">
                    <h:outputText value="Net Income"/>
                    <h:inputText value="#{TaxControllerBean.netIncome}" readonly="true"/>
                    <h:outputText value="Net Savings"/>
                    <h:inputText value="#{TaxControllerBean.netSaving}" readonly="true"/>
                    <h:outputText value="Total Taxable Amount"/>
                    <h:inputText value="#{TaxControllerBean.netIncome-TaxControllerBean.netSaving}" readonly="true"/>
                    <h:outputText value="Tax Percent"/>
                    <h:inputText value="#{TaxControllerBean.taxPercent}" readonly="true"/>
                    <h:outputText value="Total Tax"/>
                    <h:inputText value="#{TaxControllerBean.netTax}" />
                    <a4j:commandButton value="Save" action="#{TaxControllerBean.save}" />
                    </h:panelGrid>
                </h:form>
            </rich:panel>
             <h:outputText value="* Total Investment exceeds total eraning"  style="foreground-color:red;"
                           rendered="#{TaxControllerBean.netIncome<TaxBean.totalInvestment}"/>


        </h:panelGrid>
    </rich:panel>
</f:view>
