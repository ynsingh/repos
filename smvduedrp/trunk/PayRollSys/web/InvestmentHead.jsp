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
               <style>

            .oddRow{
    background-color:#EAEBD8;
    font-family:sans-serif;
    font-size: 10px;
}

.evenRow{
    background-color: #CCCCFF;
    font-family:sans-serif;
    font-size: 10px;
}

            
        </style>
    </head>
    <body class="subpage" id="">
        <p class="Caption">Investment Heads</p>
        <div class="container_form">
            <f:view>
                <h:form id="investmentheads">
                    <h:panelGrid columns="5">
                        <h:dataTable styleClass="myTable" rowClasses="oddRow,evenRow" value="#{InvestmentHeadBean.allHeads}" var="dept">
                            <h:column>
                                <f:facet name="header">
                                    <h:outputText value="Code"/>
                                </f:facet>
                                <h:outputText value="#{dept.code}" />
                            </h:column>
                            <h:column>
                                <f:facet name="header">
                                    <h:outputText  value="Name"/>
                                </f:facet>
                                <h:outputText value="#{dept.name}" />
                            </h:column>
                             <h:column>
                                <f:facet name="header">
                                    <h:outputText  value="Details"/>
                                </f:facet>
                                <h:outputText value="#{dept.details}" />
                            </h:column>

                        </h:dataTable>
                    </h:panelGrid>
                </h:form>
                <br />
                <hr/>
                <h:form>
                    <h:panelGroup styleClass="myTable"  layout="table">
                        <h:panelGrid
                            columns="3"
                            styleClass="data_entry_form"
                            columnClasses="label,field">
                            <f:facet  name="caption">
                                <h:outputText styleClass="Caption" value="Add New Investment Head"/>
                            </f:facet>
                            <h:outputText styleClass="Label" value="New Head"/>
                            <h:inputText id="headName" required="true" requiredMessage="Enter New Investment Head Name" value="#{InvestmentHeadBean.name}"/>
                            <h:message for="headName" styleClass="error"/>
                    <h:outputText styleClass="Label"  value="Tax Benefit"/>
                    <h:selectBooleanCheckbox id="txtBenefit" value="#{InvestmentHeadBean.benefit}">
                    </h:selectBooleanCheckbox>
                    <h:message for="txtBenefit" styleClass="error"/>
                    <h:outputText styleClass="Label"  value="Details"/>
                    <h:inputTextarea id="detail" cols="20" rows="3" value="#{InvestmentHeadBean.details}"/>
                    <h:message for="detail" styleClass="error"/>
                    <h:commandButton value="Save" action="#{InvestmentHeadBean.save}" />
                        </h:panelGrid>
                    </h:panelGroup>
                </h:form>
            </f:view>
        </div>
    </body>
</html>
