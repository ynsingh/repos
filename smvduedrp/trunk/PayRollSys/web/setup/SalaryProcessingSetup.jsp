<%--
    Document   : SalaryProcessingSetup 
    Created on : Dec, 2013, 4:26:31 PM
    Author     : Seema, kishore
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
        <title>Salary Processing Setup</title>
    </head>
    <body class="subpage" >
        <div class="container_form">
            <f:view>
                <rich:panel header="Salary Processing Setup">
                    <h:form id="salaryprocessingsetup">
                        <rich:messages>
                            <f:facet name="infoMarker">
                            <h:graphicImage url="/img/success.png"/>
                            </f:facet>
                            <f:facet name="errorMarker">
                            <h:graphicImage url="/img/err.png"/>
                            </f:facet>
                        </rich:messages>
                        <rich:dataTable  id="tbl" value="#{SalaryProcessingSetupController.salProMode}" binding="#{SalaryProcessingSetupController.dataGrid}" var="sps">
                            <h:column>
                            <h:selectOneRadio   required="true" value="#{sps.salaryprocessmode}">   
                            <f:selectItem itemValue="Salary Processing" itemLabel="Salary Processing"/>
                            <f:selectItem itemValue="Salary Processing with Budget" itemLabel="Salary Processing with Budget" />
                        </h:selectOneRadio>
                            </h:column>
                        </rich:dataTable> 
                        <a4j:commandButton reRender="tbl" value="update" action="#{SalaryProcessingSetupController.update}" />
                        <h:commandButton value="Reset" type="reset" />
                </h:form>
            </rich:panel>
         </f:view>
        </div>
    </body>
</html>
