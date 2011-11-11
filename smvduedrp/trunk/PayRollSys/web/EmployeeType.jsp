<%--
    Document   : Add Department
    Created on : Sept 28, 2010, 4:26:31 PM
    Author     : Saurabh
--%>


<%@ taglib prefix="f" uri="http://java.sun.com/jsf/core" %>
<%@ taglib prefix="h" uri="http://java.sun.com/jsf/html" %>
<%@ taglib uri="http://richfaces.org/a4j" prefix="a4j"%>
<%@ taglib uri="http://richfaces.org/rich" prefix="rich"%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="css/reset.css"/>
        <link rel="stylesheet" type="text/css" href="css/subpage.css"/>
        <title>Add Employee Type</title>
    </head>
    <body class="subpage" id="">
        <div class="container_form">
            <f:view>
                <rich:panel header="Employee Types">
                    <h:commandButton onclick="Richfaces.showModalPanel('pnl');" value="Add New"/>
                    <rich:messages>
                       <f:facet name="infoMarker">
                            <h:graphicImage url="/img/success.png"/>
                       </f:facet>
                    </rich:messages>
                     <h:form>
                         <rich:dataTable id="tbl" value="#{EmployeeTypeControllerBean.employeeTypes}"
                                         binding="#{EmployeeTypeControllerBean.dataGrid}" var="et">
                        
                        <h:column>
                            <f:facet name="header">
                                <h:outputText value="Name"/>
                            </f:facet>
                            <rich:inplaceInput value="#{et.name}"/>
                        </h:column>
                        <h:column>
                            <f:facet name="header">
                                <h:outputText value="PF Applies"/>
                            </f:facet>
                            <h:selectBooleanCheckbox value="#{et.pfApplies}"/>
                        </h:column>
                    </rich:dataTable>
                    <h:commandButton action="#{EmployeeTypeControllerBean.update}" value="Update"/>
                    </h:form>
                </rich:panel>
                <rich:modalPanel id="pnl">
                    <rich:panel header="Add New Employee Type">
                        <h:form>
                        <h:panelGrid columns="2">
                            <h:outputText value="Type Name*"/>
                            <h:inputText value="#{EmployeeTypeBean.name}"/>
                            <h:commandButton  value="Save" action="#{EmployeeTypeBean.save}"/>
                            <h:commandButton onclick="Richfaces.hideModalPanel('pnl');" value="Close"/>
                        </h:panelGrid>
                        </h:form>
                    </rich:panel>
                </rich:modalPanel>

            </f:view>
        </div>
    </body>
</html>
