<%-- 
    Document   : TaxCalculator
    Created on : Feb 10, 2011, 10:57:54 AM
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
    <rich:panel header="Annual Tax Calculator">
        <h:form>
            <h:panelGrid columns="3">
                <h:outputText value="Employee Code"/>
                <h:inputText value="#{TaxControllerBean.empId}"/>
                <a4j:commandButton value="Submit" onclick="submit();" reRender="dtable"/>
            </h:panelGrid>
        </h:form>
        <rich:dataTable id="dTable" value="#{TaxControllerBean.taxBeans}" binding="#{TaxControllerBean.datagrid}" var="tax">
            <h:column>
                <f:facet name="header">
                    <h:outputText value="Index Code"/>
                </f:facet>
                <h:outputText value="#{tax.headCode}"/>
            </h:column>
             <h:column>
                <f:facet name="header">
                    <h:outputText value="Head Name"/>
                </f:facet>
                <h:outputText value="#{tax.investmentHead}"/>
            </h:column>
            <h:column>
                <f:facet name="header">
                    <h:outputText value="Actual Investment"/>
                </f:facet>
                <h:outputText value="#{tax.actualAmount}"/>
            </h:column>
            <h:column>
                <f:facet name="header">
                    <h:outputText value="Effective Investment"/>
                </f:facet>
                <h:outputText value="#{tax.effectiveAmount}"/>
            </h:column>
        </rich:dataTable>

    </rich:panel>
</f:view>
