<%-- 
    Document   : MonthlyIndividualGrossSalary
    Created on : Feb 10, 2011, 12:28:54 PM
    Author     : Algox
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



        <rich:dataTable id="dataT" width="100%" value="#{GrossSalaryController.salaryMatrix}" var="matrix">
            <h:column >
                <f:facet name="header">
                    <h:outputText value="Salary Head"/>
                </f:facet>
                <h:outputText value="#{matrix.salaryHead}"/>
            </h:column>

             <h:column>
                <f:facet name="header">
                    <h:outputText value="April"/>
                </f:facet>
                <h:outputText value="#{matrix.salaryData[0]}" >
                </h:outputText>
            </h:column>
            <h:column>
                <f:facet name="header">
                    <h:outputText value="May"/>
                </f:facet>
                <h:outputText value="#{matrix.salaryData[1]}" >
                </h:outputText>
            </h:column>
            <h:column>
                <f:facet name="header">
                    <h:outputText value="June"/>
                </f:facet>
                <h:outputText value="#{matrix.salaryData[2]}" >
                </h:outputText>
            </h:column>
            <h:column>
                <f:facet name="header">
                    <h:outputText value="July"/>
                </f:facet>
                <h:outputText value="#{matrix.salaryData[3]}" >
                </h:outputText>
            </h:column>
            <h:column>
                <f:facet name="header">
                    <h:outputText value="August"/>
                </f:facet>
                <h:outputText value="#{matrix.salaryData[4]}" >
                </h:outputText>
            </h:column>
            <h:column>
                <f:facet name="header">
                    <h:outputText value="September"/>
                </f:facet>
                <h:outputText value="#{matrix.salaryData[5]}" >
                </h:outputText>
            </h:column>
            <h:column>
                <f:facet name="header">
                    <h:outputText value="October"/>
                </f:facet>
                <h:outputText value="#{matrix.salaryData[6]}" >
                </h:outputText>
            </h:column>
            <h:column>
                <f:facet name="header">
                    <h:outputText value="November"/>
                </f:facet>
                <h:outputText value="#{matrix.salaryData[7]}" >
                </h:outputText>
            </h:column>
            <h:column>
                <f:facet name="header">
                    <h:outputText value="December"/>
                </f:facet>
                <h:outputText value="#{matrix.salaryData[8]}" >
                </h:outputText>
            </h:column>
            <h:column>
                <f:facet name="header">
                    <h:outputText value="January"/>
                </f:facet>
                <h:outputText value="#{matrix.salaryData[9]}" >
                </h:outputText>
            </h:column>
            <h:column>
                <f:facet name="header">
                    <h:outputText value="February"/>
                </f:facet>
                <h:outputText value="#{matrix.salaryData[10]}" >
                </h:outputText>
            </h:column>
            <h:column>
                <f:facet name="header">
                    <h:outputText value="March"/>
                </f:facet>
                <h:outputText value="#{matrix.salaryData[11]}" >
                </h:outputText>
            </h:column>
            <h:column>
                <f:facet name="header">
                    <h:outputText value="Total"/>
                </f:facet>
                <h:outputText style="bold;" value="#{matrix.salaryData[12]}" >
                </h:outputText>
            </h:column>
            
        </rich:dataTable>
    </rich:panel>
</f:view>
