<%--
    Document        : Designation.jsp
    Created on      : 3:02 AM Friday, October 01, 2010
    Last Modified   : 4:03 AM Saturday, October 02, 2010
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
        <title>Designation</title>
        <link rel="stylesheet" type="text/css" href="css/reset.css"/>
        <link rel="stylesheet" type="text/css" href="css/subpage.css"/>
        <link rel="stylesheet" type="text/css" href="css/table.css"/>
    </head>
    <body class="subpage" id="">
        <div class="xerror">Existing Designations</div>
        <div class="container_form">
            <f:view>

                <h:form id="bookList">
                    <h:panelGrid styleClass="myTable" columns="5">
                    <h:dataTable  id="designation" value="#{DesignationBean.desigs}" var="desig" border="1">
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
                </h:form>
                <div class="xerror">Add New Designation</div>
                <h:form>
                    <h:inputText id="desigName" required="true" requiredMessage="Please Enter New Designation Name" value="#{DesignationBean.name}"/>
                    <h:message for="desigName" styleClass="error"/>
                    <h:commandButton value="Save" action="#{DesignationBean.save}"  />
                </h:form>

            </f:view>
        </div>
    </body>
</html>
