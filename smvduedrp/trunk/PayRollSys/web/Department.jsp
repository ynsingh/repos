<%--
    Document        : Login.jsp
    Created on      : 3:02 AM Saturday, October 02, 2010
    Last Modified   : 3:21 AM Saturday, October 02, 2010
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
        <title>JSP Page</title>
        <link rel="stylesheet" type="text/css" href="css/layout.css"/>
        <link rel="stylesheet" type="text/css" href="css/subpage.css"/>
        <link rel="stylesheet" type="text/css" href="css/table.css"/>
    </head>
    <body class="subpage" id="">
 
        <div class="container_form">
            <f:view>
                <rich:panel header="Existing Departments">
                    <h:commandButton onclick="Richfaces.showModalPanel('pnl');" value="Add New"/>
                <h:form id="departments">                    
                   
                       
                        <rich:dataTable binding="#{DepartmentBean.dataGrid}" value="#{DepartmentBean.departments}"
                                        var="dept">
                             <rich:panel>
                            <rich:messages>
                       <f:facet name="infoMarker">
                            <h:graphicImage url="/img/success.png"/>
                       </f:facet>
                     
                    </rich:messages>
                            <h:column >
                                <f:facet name="header">
                                    <h:outputText value="Code"/>
                                </f:facet>
                                <h:outputText value="#{dept.number}" />
                            </h:column>
                            <h:column>
                                <f:facet name="header">
                                    <h:outputText  value="Name"/>
                                </f:facet>
                                <rich:inplaceInput value="#{dept.name}" />
                            </h:column>
                        </rich:dataTable>
                        </rich:panel>                    
                    <h:panelGrid columns="2">
                    <h:commandButton value="Update" action="#{DepartmentBean.update}"/>
                    
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
        </div>
    </body>
</html>
