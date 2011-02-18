<%-- 
    Document        : SalaryGrade
    Created on      : Jul 2, 2010, 4:26:10 AM
    Last Modified   : 4:03 AM Saturday, October 02, 2010
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
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="css/reset.css"/>
        <link rel="stylesheet" type="text/css" href="css/subpage.css"/>
        <link rel="stylesheet" type="text/css" href="css/table.css"/>
    </head>
    <body class="subpage" id="">

        <f:view>
            <rich:panel header="Existing Salary Grades">
                <h:panelGrid columns="2">
                    <h:commandButton onclick="Richfaces.showModalPanel('pnl');" value="Add New"/>
                    <rich:messages>
                        <f:facet name="infoMarker">
                            <h:graphicImage url="/img/success.png"/>
                        </f:facet>
                    </rich:messages>
                </h:panelGrid>
                <h:form>  
                    <rich:dataTable binding="#{SalaryGradeControllerBean.dataGrid}" value="#{SalaryGradeControllerBean.grades}" var="grades" border="1">
                        <h:column>
                            <f:facet name="header">
                                <h:outputText value="Grade Name"/>
                            </f:facet>
                            <rich:inplaceInput value="#{grades.name}" />
                        </h:column>
                        <h:column>
                            <f:facet name="header">
                                <h:outputText  value="Max Amount"/>
                            </f:facet>
                            <rich:inplaceInput value="#{grades.maxValue}" />
                        </h:column>
                        <h:column>
                            <f:facet name="header">
                                <h:outputText  value="Min Amount"/>
                            </f:facet>
                            <rich:inplaceInput  value="#{grades.minValue}" />
                        </h:column>
                    </rich:dataTable>

                    <h:panelGrid columns="2">
                        <h:commandButton value="Update" action="#{SalaryGradeControllerBean.save}"/>
                    </h:panelGrid>
                </h:form>
            </rich:panel>

            <rich:modalPanel id="pnl">
                <rich:panel header="Add New Grade">
                    <h:form>
                        <h:panelGrid columns="2">
                            <h:outputText value="Grade Name"/>
                            <h:inputText id="grdName" required="true" requiredMessage="Enter New Grade Name" value="#{SalaryGradeBean.name}" />
                            <h:message for="grdName" styleClass="error"/>
                        </h:panelGrid>
                        <h:panelGrid columns="2">
                            <h:outputText value="Maximum"/>
                            <h:inputText required="true" requiredMessage="Enter Maximum Value in Number" id="salMax" value="#{SalaryGradeBean.maxValue}" />
                            <h:message for="salMax" styleClass="error"/>
                        </h:panelGrid>
                        <h:panelGrid columns="2">
                            <h:outputText value="Minimum"/>
                            <h:inputText id="salMin" required="true" requiredMessage="Enter Maximum Value in Number"  value="#{SalaryGradeBean.minValue}" />
                            <h:message for="salMax" styleClass="error"/>
                        </h:panelGrid>
                        <h:panelGrid columns="2">
                            <h:commandButton value="Save" action="#{SalaryGradeBean.save}" styleClass="submit"/>
                            <h:commandButton value="Close" onclick="Richfaces.hideModalPanel('pnl');" />
                        </h:panelGrid>
                    </h:form>
                </rich:panel>
            </rich:modalPanel>

        </f:view>


    </tbody>

</body>
</html>
