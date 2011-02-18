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
            <rich:panel header="Existing Departments">
                <h:panelGrid columns="2">
                 <h:commandButton onclick="Richfaces.showModalPanel('pnl');" value="Add New"/>
                 <rich:messages>
                       <f:facet name="infoMarker">
                            <h:graphicImage url="/img/success.png"/>
                       </f:facet>
                    </rich:messages>
                 </h:panelGrid>
                <h:form> 
                        
                        <rich:dataTable binding="#{DepartmentControllerBean.dataGrid}" value="#{DepartmentControllerBean.departments}" var="dept">
                        <h:column >
                            <f:facet name="header">
                                <h:outputText value="Dept. Code"/>
                            </f:facet>
                            <h:outputText value="#{dept.code}" />
                        </h:column>
                        <h:column >
                            <f:facet name="header">
                                <h:outputText value="Dept Name"/>
                            </f:facet>
                            <rich:inplaceInput value="#{dept.name}" />
                        </h:column>                       
                    </rich:dataTable>
                    <h:panelGrid columns="2">
                        <h:commandButton value="Update" action="#{DepartmentControllerBean.update}"/>                    
                    </h:panelGrid>
                </h:form>
            </rich:panel>
            <rich:modalPanel id="pnl">
                    <rich:panel header="Add New Department">
                    <h:form>
                    <h:inputText id="deptName" required="true" requiredMessage="Please Enter Department Name" value="#{DepartmentBean.name}"/>
                    <h:message styleClass="error" for="deptName" tooltip="Employee Type"/>
                    <h:commandButton value="Save" action="#{DepartmentBean.save}"  />
                    <h:commandButton value="Close" onclick="Richfaces.hideModalPanel('pnl');" />
                </h:form>
                </rich:panel>
                </rich:modalPanel>
        </f:view>
    </body>
</html>
