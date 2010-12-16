<%-- 
    Document   : SalaryFormula
    Created on : Sept 28, 2010
    Author     : Saurabh Kumar
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
        <link rel="stylesheet" type="text/css" href="css/table.css"/>
        <link rel="stylesheet" type="text/css" href="css/subpage.css"/>
        <title>Formula Builder</title>
        

    </head>
    <body class="subpage">
        <div class="container_form">
                    <f:view>
                        <h:form id="salaryformula">
                            <div class="xerror" >Formula Builder</div>
                            <h:outputText styleClass="Label" value="Select Salary Head "> </h:outputText>
                            <h:selectOneMenu id="code" value="#{SalaryFormulaBean.salCode}">
                                            <f:selectItems value="#{SalaryHeadBean.items}"/>
                            </h:selectOneMenu>
                           
                            <h:outputText styleClass="Label" value="Formula"></h:outputText>
                            <h:inputText id="txtFormula" value="#{SalaryFormulaBean.formula}"/>
                            <h:commandButton value="Save" action="#{SalaryFormulaBean.save}" />
                            </h:form>
                        </f:view>          
        </div>
    </body>
</html>
