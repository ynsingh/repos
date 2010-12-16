<%--
    Document   : login
    Created on : Aug 11, 2009, 4:26:31 PM
    Author     : Sushant
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
        <link rel="stylesheet" type="text/css" href="css/reset.css"/>
        <link rel="stylesheet" type="text/css" href="css/subpage.css"/>
        <link rel="stylesheet" type="text/css" href="css/table.css"/>
    </head>
    <body class="subpage" id="">
        <div class="container_form">
            
            <f:view>
                <div class="xerror">Existing Types</div>
                    <h:panelGrid styleClass="myTable" columns="5">
                        <h:dataTable  id="designation" value="#{EmployeeTypeBean.items}" var="desig" border="1">
                        <h:column>
                            <f:facet name="header">
                                <h:outputText value="Code"/>
                            </f:facet>
                            <h:outputText value="#{desig.value}" />
                        </h:column>
                        <h:column>
                            <f:facet name="header">
                                <h:outputText  value="Name"/>
                            </f:facet>
                            <h:outputText value="#{desig.label}" />
                        </h:column>

                    </h:dataTable>
                    </h:panelGrid>
                <div class="xerror"> Add New Type </div>
                    <h:form >
                        <h:outputText styleClass="Label" value="New Salary Profile"></h:outputText>
                        <h:inputText styleClass="field" required="true" value="#{SalaryProfileBean.name}" />
                        <h:commandButton styleClass="panel" value="Save" action="#{SalaryProfileBean.save}" />
                </h:form>
            </f:view>
        </div>
    </body>
</html>
