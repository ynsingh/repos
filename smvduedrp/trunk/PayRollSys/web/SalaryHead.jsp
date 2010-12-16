<%--
    Document        : SalaryHead.jsp
    Created on      : 3:02 AM Saturday, October 02, 2010
    Last Modified   : 3:02 AM Saturday, October 02, 2010
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
        <link rel="stylesheet" type="text/css" href="css/reset.css"/>
        <link rel="stylesheet" type="text/css" href="css/subpage.css"/>
        <link rel="stylesheet" type="text/css" href="css/table.css"/>

        <title>Add Salary Heads</title>
        <style>

            .oddRow{
    background-color:#EAEBD8;
    font-family:sans-serif;
    font-size: 10px;
}

.evenRow{
    background-color:#ffffff;
    font-family:sans-serif;
    font-size: 10px;
}

            
        </style>
    </head>

    <body class="subpage" id="">
        <h1>Add Salary Heads</h1>
        <div class="container_form">

            <f:view>
                <h:form>
                    <h:dataTable rowClasses="oddRow,evenRow" styleClass="myTable" value="#{SalaryHeadBean.heads}" var="heads" >
                        <h:column>
                            <f:facet name="header">
                                <h:outputText value="Head Name"/>
                            </f:facet>
                            <h:outputText value="#{heads.name}" />
                        </h:column>
                        <h:column>
                            <f:facet name="header">
                                <h:outputText style="width:100px;" value="Under"/>
                            </f:facet>
                            <h:outputText value="#{heads.underString}" />
                        </h:column>
                        <h:column>
                            <f:facet name="header">
                                <h:outputText value="Salary Type"/>
                            </f:facet>
                            <h:outputText value="#{heads.calculationString}" />
                        </h:column>

                    </h:dataTable>
                </h:form>
                <h:form>
                    <table border="1" Class="" id="data_entry_form">
                        <tbody>
                            <tr>
                                <td>New Salary Head</td>
                                <td><h:inputText value="#{SalaryHeadBean.name}" /></td>
                            </tr>
                            <tr>
                                <td>Short Name</td>
                                <td><h:inputText value="#{SalaryHeadBean.alias}" /></td>
                            </tr>
                            <tr>
                                <td>Under</td>
                                <td><h:selectOneMenu value="#{SalaryHeadBean.under}">
                                        <f:selectItem itemValue="true" itemLabel="Income"/>
                                        <f:selectItem itemValue="false" itemLabel="Deduction"/>
                                    </h:selectOneMenu>
                                </td>
                            </tr>
                            <tr>
                                <td>Calculation Type</td>
                                <td><input type="radio" name="radio" id="radio" value="1" />
                                    <label for="radio"> Consolidated</label>
                                    <input type="radio" name="radio" id="radio" value="0" />
                                    <label for="radio"> Formula</label>
                                </td>
                            </tr>
                            <tr>
                                <td><h:selectBooleanCheckbox value="true">
                                        <h:outputText value="Save and go to Create Formula"/>
                                    </h:selectBooleanCheckbox>   </td>
                                <td><h:commandButton value="Save" styleClass="submit" action="#{SalaryHeadBean.save}" /></td>
                            </tr>
                        </tbody>
                    </table>
                </h:form>
            </f:view>
        </div>
    </body>
</html>
