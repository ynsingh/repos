<%-- 
    Document   : MonthlyIndividualGrossSalary
    Created on : Feb 10, 2011, 12:28:54 PM
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
   Modification date 15 July 2016,  Manorama Pal<palseema30@gmail.com>, IITK  
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
    <rich:panel header="Individual Annual Gross Salary">
        <h:form>
            <h:panelGrid columns="5">
                <h:outputText value="Employee Code"/>
                <h:inputText id="empcCode" value="#{GrossSalaryController.empCode}"/>
                <rich:suggestionbox for="empcCode" var="abc" fetchValue="#{abc.code}"  suggestionAction="#{SearchBean.getSuggestion}">
                    <h:column>
                    <h:outputText value="#{abc.name}"/>
                    </h:column>
                    <h:column>
                    <h:outputText value="#{abc.code}"/>
                    </h:column>
                </rich:suggestionbox>
                <a4j:commandButton value="Load" onclick="submit();" reRender="dataT"/>
            </h:panelGrid>
        </h:form>

        <rich:panel>
            <h:panelGrid columns="8">
                <h:outputText value="Name"/>
                <h:inputText readonly="true" value="#{GrossSalaryController.employee.name}"/>
                <h:outputText value="Department"/>
                <h:inputText readonly="true" value="#{GrossSalaryController.employee.deptName}"/>
                <h:outputText value="Designation"/>
                <h:inputText readonly="true" value="#{GrossSalaryController.employee.desigName}"/>
                <h:outputText value="PF Acc No"/>
                <h:inputText readonly="true" value="#{GrossSalaryController.employee.pfAccNo}"/>
                <h:outputText value="Bank Ac No"/>
                <h:inputText readonly="true" value="#{GrossSalaryController.employee.bankAccNo}"/>
                <h:outputText value="Employee Type"/>
                <h:inputText readonly="true" value="#{GrossSalaryController.employee.typeName}"/>
                 <h:outputText value="Gender"/>
                <h:inputText readonly="true" value="#{GrossSalaryController.employee.genderName}"/>
            </h:panelGrid>
        </rich:panel>


        

        <rich:dataTable id="dataT" width="100%" value="#{GrossSalaryController.salaryMatrix}" var="matrix">
            <h:column >
                <f:facet name="header">
                    <h:outputText value="Salary Head"/>
                </f:facet>
                <h:outputText style="#{matrix.style}" value="#{matrix.salaryHead}"/>
            </h:column>

             <h:column>
                <f:facet name="header">
                    <h:outputText value="April"/>
                </f:facet>
                 <h:outputText style="#{matrix.style}" value="#{matrix.salaryData[0]}" >
                </h:outputText>
            </h:column>
            <h:column>
                <f:facet name="header">
                    <h:outputText value="May"/>
                </f:facet>
                <h:outputText style="#{matrix.style}" value="#{matrix.salaryData[1]}" >
                </h:outputText>
            </h:column>
            <h:column>
                <f:facet name="header">
                    <h:outputText value="June"/>
                </f:facet>
                <h:outputText style="#{matrix.style}" value="#{matrix.salaryData[2]}" >
                </h:outputText>
            </h:column>
            <h:column>
                <f:facet name="header">
                    <h:outputText value="July"/>
                </f:facet>
                <h:outputText style="#{matrix.style}" value="#{matrix.salaryData[3]}" >
                </h:outputText>
            </h:column>
            <h:column>
                <f:facet name="header">
                    <h:outputText value="August"/>
                </f:facet>
                <h:outputText style="#{matrix.style}" value="#{matrix.salaryData[4]}" >
                </h:outputText>
            </h:column>
            <h:column>
                <f:facet name="header">
                    <h:outputText value="September"/>
                </f:facet>
                <h:outputText style="#{matrix.style}" value="#{matrix.salaryData[5]}" >
                </h:outputText>
            </h:column>
            <h:column>
                <f:facet name="header">
                    <h:outputText value="October"/>
                </f:facet>
                <h:outputText style="#{matrix.style}" value="#{matrix.salaryData[6]}" >
                </h:outputText>
            </h:column>
            <h:column>
                <f:facet name="header">
                    <h:outputText value="November"/>
                </f:facet>
                <h:outputText style="#{matrix.style}" value="#{matrix.salaryData[7]}" >
                </h:outputText>
            </h:column>
            <h:column>
                <f:facet name="header">
                    <h:outputText value="December"/>
                </f:facet>
                <h:outputText style="#{matrix.style}" value="#{matrix.salaryData[8]}" >
                </h:outputText>
            </h:column>
            <h:column>
                <f:facet name="header">
                    <h:outputText value="January"/>
                </f:facet>
                <h:outputText style="#{matrix.style}" value="#{matrix.salaryData[9]}" >
                </h:outputText>
            </h:column>
            <h:column>
                <f:facet name="header">
                    <h:outputText value="February"/>
                </f:facet>
                <h:outputText style="#{matrix.style}" value="#{matrix.salaryData[10]}" >
                </h:outputText>
            </h:column>
            <h:column>
                <f:facet name="header">
                    <h:outputText value="March"/>
                </f:facet>
                <h:outputText style="#{matrix.style}" value="#{matrix.salaryData[11]}" >
                </h:outputText>
            </h:column>
            <h:column>
                <f:facet name="header">
                    <h:outputText value="Total"/>
                </f:facet>
                <h:outputText style="font-weight:bold" value="#{matrix.salaryData[12]}" >
                </h:outputText>
            </h:column>
            
        </rich:dataTable>
    </rich:panel>
</f:view>
