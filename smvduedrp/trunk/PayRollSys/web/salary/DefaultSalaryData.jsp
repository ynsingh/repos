<%-- 
    Document   : DefaultSalaryData
    Created on : Dec 24, 2010, 11:37:17 AM
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
            <rich:panel header="Default Salary Data">
                <h:form>
                    <h:panelGrid columns="3">
                        <h:outputText value="Salary Grade"/>
                        <h:selectOneMenu value="#{DefaultSalaryDataController.typecode}">
                            <f:selectItems value="#{SalaryGradeBean.grades}"/>
                        </h:selectOneMenu>
                        <a4j:commandButton reRender="pnl" value="Load Defaults" action="#{DefaultSalaryDataController.populate}"/>
                    </h:panelGrid>
                </h:form>
                <h:form>
                    <rich:panel id="pnl">
                        <h:inputHidden value="#{DefaultSalaryDataController.typecode}"/>
                        <rich:messages>
                       <f:facet name="infoMarker">
                            <h:graphicImage url="/img/success.png"/>
                       </f:facet>
                    </rich:messages>
                        <rich:dataTable binding="#{DefaultSalaryDataController.grid}" value="#{DefaultSalaryDataController.heads}" var="sal">
                        <h:column >
                            <f:facet name="header">
                                <h:outputText value="Salary Head"/>
                            </f:facet>
                            <h:outputText value="#{sal.name}" />
                        </h:column>
                        <h:column >
                            <f:facet name="header">
                                <h:outputText value="Under"/>
                            </f:facet>
                            <h:outputText value="#{sal.underString}" />
                        </h:column>
                        <h:column >
                            <f:facet name="header">
                                <h:outputText value="Default Value"/>
                            </f:facet>
                            <rich:inplaceInput value="#{sal.defaultValue}" />
                        </h:column>
                    </rich:dataTable>
                    </rich:panel>
                    <h:panelGrid columns="2">
                        <h:commandButton value="Update" action="#{DefaultSalaryDataController.update}"/>
                    
                    </h:panelGrid>
                </h:form>
            </rich:panel>
        </f:view>
    </body>
</html>
