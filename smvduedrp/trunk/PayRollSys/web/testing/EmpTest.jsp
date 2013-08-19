<%-- 
    Document   : EmpTest
    Created on : Dec 25, 2010, 12:28:56 PM
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
      <title></title>
      <script type="text/javascript"  LANGUAGE="JavaScript">
        function disableEnterKey(){
        alert(event.keyCode);
        if(event.keyCode == 13)
        {
        event.keyCode=9; //return the tab key
        event.cancelBubble = true;
        }
        }
</script>
    </head>
    <body>
        <f:view>

            <h:panelGrid columns="2">
                <rich:panel header="Monthly Salary Processing" id="mypnl">
                    <h:form>
                        <a4j:status id="mystat" onstart="Updating ..." onstop="Done"/>
                        <h:panelGrid columns="2">
                            <rich:panel id="inpnl" style="width:200px;height:400px;">
                                <rich:dataTable  width="180px;" binding="#{SalaryProcessingBean.incomeGrid}"
                                                 value="#{SalaryProcessingBean.incomeHeads}" var="income">
                                    <h:column>
                                        <f:facet name="header">
                                            <h:outputText value="Income Head"/>
                                        </f:facet>
                                        <h:outputText value="#{income.name}"/>
                                    </h:column>
                                    <h:column>
                                        <f:facet name="header">
                                            <h:outputText value="Amount"/>
                                        </f:facet>
                                        <h:inputText onkeydown="disableEnterKey();" style="width:60px;" value="#{income.defaultValue}"/>
                                    </h:column>
                                     <f:facet name="footer">
                                         <h:outputText value="Total : #{SalaryProcessingBean.totalIncome}"/>
                                    </f:facet>
                                </rich:dataTable>

                            </rich:panel>
                            <rich:panel id="dpnl" style="width:200px;height:400px;">
                                <rich:dataTable width="180px;" binding="#{SalaryProcessingBean.deductGrid}"
                                                value="#{SalaryProcessingBean.deductHeads}" var="deduct">
                                    <h:column>
                                        <f:facet name="header">
                                            <h:outputText value="Deduction Head"/>
                                        </f:facet>
                                        <h:outputText value="#{deduct.name}"/>
                                    </h:column>
                                    <h:column>
                                        <f:facet name="header">
                                            <h:outputText value="Amount"/>
                                        </f:facet>
                                        <h:inputText style="width:60px;" value="#{deduct.defaultValue}"/>
                                    </h:column>
                                    
                                    <f:facet name="footer">
                                        <h:outputText value="Total : #{SalaryProcessingBean.totalDeduct}"/>
                                    </f:facet>
                                </rich:dataTable>
                            </rich:panel>
                            </h:panelGrid>                                   
                        
                        <a4j:commandButton status="mystat" reRender="inpnl,dpnl,salpnl"
                                           value="Update Salary" action="#{SalaryProcessingBean.updateData}"/>
                        <h:commandButton value="Reset"/>
                        <h:inputHidden value="#{SalaryProcessingBean.empCode}"/>
                        <h:inputHidden value="#{SalaryProcessingBean.typeCode}"/>
                    </h:form>
                </rich:panel>
                <rich:panel style="width:300px;height:465px;" header="Salary Tools">

                    <h:form>
                        <rich:panel style="width:280px;border:1;">
                            <h:panelGrid columns="4">
                            <h:outputText value="Employee Code"/>
                            <h:inputText id="empcCode" value="#{SalaryProcessingBean.empCode}" />
                            <rich:suggestionbox for="empcCode" var="abc" fetchValue="#{abc.code}"  suggestionAction="#{SearchBean.getSuggestion}">
                            <h:column>
                            <h:outputText value="#{abc.name}"/>
                            </h:column>
                            <h:column>
                            <h:outputText value="#{abc.code}"/>
                            </h:column>
                            </rich:suggestionbox>
                            <a4j:commandButton status="topstat" action="#{SalaryProcessingBean.loadProfile}" reRender="empDetail,mypnl,salpnl" value="Load"/>
                            <h:outputText value="For month"/>
                            <h:outputText style="font-size:12px;" value="#{UserBean.currentMonthName}"/>
                            </h:panelGrid>
                        </rich:panel>
                    </h:form>
                     <rich:panel style="width:280px;border:1;">
                        <h:panelGrid  columns="4">
                            <h:outputText value="Days Present"/>
                            <h:inputText style="width:20px;" value="#{SalaryProcessingBean.numberOfdays}"/>
                            <a4j:commandButton value="Refresh"/>
                            </h:panelGrid>
                    </rich:panel>
                            <rich:panel header="Employee Details">
                    <h:panelGrid id="empDetail" columns="2">
                            <h:outputText value="Code"/>
                            <h:inputText readonly="true"  value="#{SalaryProcessingBean.employee.code}"/>
                            <h:outputText value="Name"/>
                            <h:inputText readonly="true" value="#{SalaryProcessingBean.employee.name}"/>
                            <h:outputText value="Dept"/>
                            <h:inputText readonly="true" value="#{SalaryProcessingBean.employee.deptName}"/>
                            <h:outputText value="Desig."/>
                            <h:inputText readonly="true" value="#{SalaryProcessingBean.employee.desigName}"/>
                            <h:outputText value="Bank AC No"/>
                            <h:inputText readonly="true"  value="#{SalaryProcessingBean.employee.bankAccNo}"/>
                            <h:outputText value="Salary Band"/>
                            <h:inputText readonly="true" value="#{SalaryProcessingBean.employee.grade}"/>
                        </h:panelGrid>
                     </rich:panel>
                    
                                <rich:messages layout="table" style="border:1;" >
                                <f:facet name="infoMarker">
                                    <h:graphicImage url="/img/success.png"/>
                                </f:facet>
                                <f:facet name="errorMarker">
                                    <h:graphicImage url="/img/err.png"/>
                                </f:facet>
                            </rich:messages>
                       
                            <rich:panel id="salpnl" header="Salary Summery">
                                    <h:panelGrid columns="2">
                                    <h:outputText value="Total Income"/>
                                    <h:outputText style="font-size:14px;align:right;" value="#{SalaryProcessingBean.totalIncome}"/>
                                    <h:outputText value="Total Deduction"/>
                                    <h:outputText style="font-size:14px;align:right;" value="#{SalaryProcessingBean.totalDeduct}"/>
                                    <h:outputText value="Net Salary"/>
                                    <h:outputText style="font-size:16px;align:right;" value="#{SalaryProcessingBean.gorssTotal}"/>
                                    </h:panelGrid>
                                    </rich:panel>
                </rich:panel>
                
            </h:panelGrid>
        </f:view>
    </body>
</html>
