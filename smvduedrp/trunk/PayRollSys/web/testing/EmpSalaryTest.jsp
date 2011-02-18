<%-- 
    Document   : EmpTest
    Created on : Dec 25, 2010, 12:28:56 PM
    Author     : Algox
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

            <h:panelGrid columns="2">

                <rich:panel header="Monthly Salary Processing" id="mypnl">
                    <h:form>
                        <rich:panel style="width:410px;border:1;">
                            <h:outputText value="Employee Code"/>
                            <h:inputText value="#{SalaryProcessingBean.empCode}" onchange="submit()"/>
                            <a4j:commandButton status="topstat" action="#{SalaryProcessingBean.loadProfile}" reRender="mypnl" value="Load"/>
                            <rich:messages layout="table" style="border:1;" >
                                <f:facet name="infoMarker">
                                    <h:graphicImage url="/img/success.png"/>
                                </f:facet>
                                <f:facet name="errorMarker">
                                    <h:graphicImage url="/img/err.png"/>
                                </f:facet>
                            </rich:messages>
                        </rich:panel>
                    </h:form>



                    <rich:panel id="pnl" style="width:410px;height:120px;" header="Employee Details">
                        <h:panelGrid columns="4">
                            <h:outputText value="Code"/>
                            <h:inputText value="#{SalaryProcessingBean.employee.code}"/>
                            <h:outputText value="Name"/>
                            <h:inputText value="#{SalaryProcessingBean.employee.name}"/>
                            <h:outputText value="Dept"/>
                            <h:inputText value="#{SalaryProcessingBean.employee.deptName}"/>
                            <h:outputText value="Desig."/>
                            <h:inputText value="#{SalaryProcessingBean.employee.desigName}"/>
                            <h:outputText value="Bank AC No"/>
                            <h:inputText value="#{SalaryProcessingBean.employee.bankAccNo}"/>
                            <h:outputText value="PAN No."/>
                            <h:inputText value="#{SalaryProcessingBean.employee.panNo}"/>
                            <h:outputText value="PF Acc No"/>
                            <h:inputText value="#{SalaryProcessingBean.employee.pfAccNo}"/>
                            <h:outputText value="Salary Band"/>
                            <h:inputText value="#{SalaryProcessingBean.employee.typeName}"/>
                        </h:panelGrid>
                    </rich:panel>
                    <h:form>
                        <a4j:status id="mystat" onstart="Updating ..." onstop="Done"/>
                        <h:panelGrid columns="2">
                            <rich:panel id="inpnl" header="Income" style="width:200px;height:200px;">
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
                                        <h:inputText style="width:60px;" value="#{income.defaultValue}"/>
                                    </h:column>
                                </rich:dataTable>

                            </rich:panel>
                            <rich:panel id="dpnl" header="Deductions"  style="width:200px;height:200px;">
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
                                </rich:dataTable>
                            </rich:panel>
                        </h:panelGrid>
                        <a4j:commandButton status="mystat" reRender="inpnl,dpnl"
                                           value="Update Salary" action="#{SalaryProcessingBean.updateData}"/>
                        <h:commandButton value="Reset"/>
                        <h:inputHidden value="#{SalaryProcessingBean.empCode}"/>
                        <h:inputHidden value="#{SalaryProcessingBean.typeCode}"/>
                    </h:form>
                </rich:panel>
                <rich:panel style="width:200px;height:430px;" header="Salary Tools">
                    <h:panelGrid columns="1">
                        <h:commandButton value="Copy Previous Month Salary"/>
                        <h:commandButton value="Preview Salary Slip"/>
                        <h:commandButton value="View Salary Formula"/>
                        <h:commandButton value="Configure Salary"/>
                        <h:commandButton value="Salary Help"/>
                    </h:panelGrid>
                </rich:panel>
            </h:panelGrid>
        </f:view>
    </body>
</html>
