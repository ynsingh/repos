<%-- 
    Document   : TaxCalculationType
    Created on : 16 Jul, 2015, 3:10:44 PM
    Author     : shikhar
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
        <title>Tax Calculation Type</title>
    </head>
    <body class="subpage" >
        <div class="container_form">
            <f:view>
                <rich:panel header="Tax Calculation Type">
                    <h:form id="taxclaculationtypesetup">
                        <rich:panel  style="height:40px;center;">
                            <rich:messages>
                            <f:facet name="infoMarker">
                            <h:graphicImage url="/img/success.png"/>
                            </f:facet>
                            <f:facet name="errorMarker">
                            <h:graphicImage url="/img/err.png"/>
                            </f:facet>
                            </rich:messages>
                        </rich:panel>   
                        <br>
                        <h:panelGrid  columns="2" style="width:100%">
                           <rich:dataTable style="width:300px;" id="tbl" value="#{TaxCalculationTypeController.taxCalcType}" binding="#{TaxCalculationTypeController.dataGrid}" var="tct">
                               <h:column>
                              <%--  ${tct.calcType}    
                                 <h:selectOneRadio value="#{tct.calcType}">--%>
                                    <h:selectOneRadio required="true" value="#{TaxCalculationType.calcType}" style="width:450px; height:40px;">
                                        <f:selectItem itemValue="Yearly" itemLabel="YEARLY"/>
                                        <f:selectItem itemValue="Quaterly" itemLabel="QUATERLY" />
                                        <f:selectItem itemValue="Monthly" itemLabel="MONTHLY"/>
                                    </h:selectOneRadio>
                                </h:column>
                           </rich:dataTable>
                            <rich:panel style="width:600px;" header="Important Points related to Income Tax">
                            <h:outputText value="Income tax calculation on yearly basis, that is default setting."/><br/>
                            <h:outputText value="Update according to yours choice that is YEARLY, QUARTLY and MONTHLY."/>
                            </rich:panel>
                        </h:panelGrid>  
                        <br>
                        <h:panelGrid> 
                            <a4j:commandButton reRender="tbl" value="update" action="#{TaxCalculationTypeController.update}" />
                        </h:panelGrid>
                        
                    </h:form>
            </rich:panel>
         </f:view>
        </div>
    </body>
</html>
