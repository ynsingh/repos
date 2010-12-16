<%--
    Document        : Login.jsp
    Created on      : 3:02 AM Saturday, October 02, 2010
    Last Modified   : 3:21 AM Saturday, October 02, 2010
    Author          : Saurabh Kumar
--%>

<%@ taglib prefix="f" uri="http://java.sun.com/jsf/core" %>
<%@ taglib prefix="h" uri="http://java.sun.com/jsf/html" %>

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
        <div class="xerror">Existing Departments</div>
        <div class="container_form">
            <f:view>
                <h:form id="departments">
                    <h:panelGrid styleClass="myTable" columns="5">
                        <h:dataTable value="#{DepartmentBean.allDepts}" var="dept" styleClass="table_full_fixed">
                            <h:column>
                                <f:facet name="header">
                                    <h:outputText value="Code"/>
                                </f:facet>
                                <h:outputText value="#{dept.value}" />
                            </h:column>
                            <h:column>
                                <f:facet name="header">
                                    <h:outputText  value="Name"/>
                                </f:facet>
                                <h:outputText value="#{dept.label}" />
                            </h:column>

                        </h:dataTable>
                    </h:panelGrid>
                </h:form>
                <br />
                <div class="xerror">Add New Department</div>
                <h:form>
                    <h:inputText id="deptName" required="true" requiredMessage="Please Enter Department Name" value="#{DepartmentBean.name}"/>
                    <h:message styleClass="error" for="deptName" tooltip="Employee Type"/>
                    <h:commandButton value="Save" action="#{DepartmentBean.save}"  />
                </h:form>
            </f:view>
        </div>
    </body>
</html>
