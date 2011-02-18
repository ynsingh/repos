<%--
    Document        : SalaryHead.jsp
    Created on      : 3:02 AM Saturday, October 02, 2010
    Last Modified   : 3:02 AM Saturday, October 02, 2010
    Author          : Saurabh Kumar
--%>

<%@ taglib prefix="f" uri="http://java.sun.com/jsf/core" %>
<%@ taglib prefix="h" uri="http://java.sun.com/jsf/html" %>
<%@ taglib uri="http://richfaces.org/a4j" prefix="a4j"%>
<%@ taglib uri="http://richfaces.org/rich" prefix="rich"%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <f:view>
        <rich:panel header="Existing Salary Heads">
            <h:panelGrid columns="2">
                <h:commandButton onclick="Richfaces.showModalPanel('pnl');" value="Add New"/>
                <rich:messages>
                    <f:facet name="infoMarker">
                        <h:graphicImage url="/img/success.png"/>
                    </f:facet>
                </rich:messages>
            </h:panelGrid>
            <h:form>

                <rich:dataTable binding="#{SalaryHeadControllerBean.dataGrid}" style="width:70%;" value="#{SalaryHeadControllerBean.heads}" var="heads" >
                    <h:column>
                        <f:facet name="header">
                            <h:outputText value="Head Name"/>
                        </f:facet>
                        <rich:inplaceInput value="#{heads.name}" />
                    </h:column>
                    <h:column>
                        <f:facet name="header">
                            <h:outputText style="width:100px;" value="Income"/>
                        </f:facet>
                        <h:selectBooleanCheckbox value="#{heads.under}"/>
                    </h:column>
                    <h:column>
                        <f:facet name="header">
                            <h:outputText value="Formula"/>
                        </f:facet>
                        <h:selectBooleanCheckbox value="#{heads.calculationType}"/>
                    </h:column>
                </rich:dataTable>
                <h:commandButton action="#{SalaryHeadControllerBean.update}" value="Update"/>
            </h:form>
        </rich:panel>
        <rich:modalPanel id="pnl">
            <h:form>
                <rich:panel header="Add New Salary Head">
                    <h:panelGrid columns="2">
                        <h:outputText value="Salary Head"/>
                        <h:inputText value="#{SalaryHeadBean.name}" />
                   
                        <h:outputText value="Short Name"/>
                        <h:inputText value="#{SalaryHeadBean.alias}" />
                    
                        <h:outputText value="Under"/>
                        <h:selectOneMenu value="#{SalaryHeadBean.under}">
                            <f:selectItem itemValue="true" itemLabel="Income"/>
                            <f:selectItem itemValue="false" itemLabel="Deduction"/>
                        </h:selectOneMenu>
                    
                        <h:outputText value="Calculation Type"/>
                        <h:selectOneMenu value="#{SalaryHeadBean.under}">
                            <f:selectItem itemValue="false" itemLabel="Consolidated"/>
                            <f:selectItem itemValue="true" itemLabel="Formula"/>
                        </h:selectOneMenu>                    
                    <h:commandButton value="Save" styleClass="submit" action="#{SalaryHeadBean.save}" />
                    <h:commandButton value="Close" onclick="Richfaces.hideModalPanel('pnl');" />
                    </h:panelGrid>
                </rich:panel>
            </h:form>
        </rich:modalPanel>
    </f:view>
