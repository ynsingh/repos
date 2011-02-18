<%--
    Document   : login
    Created on : Aug 11, 2009, 4:26:31 PM
    Author     : Sushant
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
        <title>JSP Page</title>
        <link rel="stylesheet" type="text/css" href="css/reset.css"/>
        <link rel="stylesheet" type="text/css" href="css/subpage.css"/>
        <link rel="stylesheet" type="text/css" href="css/table.css"/>
    </head>
    <body class="subpage" id="">
        <div class="container_form">

            <f:view>
                <rich:panel header="Existing Profiles">
                    <h:commandButton onclick="Richfaces.showModalPanel('pnl');" value="Add New"/>
                    <h:form>
                        <rich:messages>
                       <f:facet name="infoMarker">
                            <h:graphicImage url="/img/success.png"/>
                       </f:facet>
                    </rich:messages>
                    <h:panelGrid  columns="2">

                        <rich:dataTable  binding="#{EmployeeTypeControllerBean.dataGrid}" id="designation" value="#{EmployeeTypeControllerBean.employeeTypes}" var="desig" border="1">
                            <h:column>
                                <f:facet name="header">
                                    <h:outputText value="Code"/>
                                </f:facet>
                                <h:outputText value="#{desig.code}" />
                            </h:column>
                            <h:column>
                                <f:facet name="header">
                                    <h:outputText  value="Name"/>
                                </f:facet>
                                <rich:inplaceInput value="#{desig.name}" />
                            </h:column>
                        </rich:dataTable>                        
                    </h:panelGrid>
                        <h:commandButton value="Update" action="#{EmployeeTypeControllerBean.update}"/>
                    </h:form>
                </rich:panel>
                <rich:modalPanel id="pnl">
                    <rich:panel header="Add New Profile">
                        <h:form >
                            <h:outputText styleClass="Label" value="New Salary Profile"></h:outputText>
                            <h:inputText styleClass="field" required="true" value="#{SalaryProfileBean.name}" />
                            <h:commandButton styleClass="panel" value="Save" action="#{SalaryProfileBean.save}" />
                            <h:commandButton value="Close" onclick="Richfaces.hideModalPanel('pnl');" />
                        </h:form>
                    </rich:panel>
                </rich:modalPanel>
            </f:view>
        </div>
    </body>
</html>
