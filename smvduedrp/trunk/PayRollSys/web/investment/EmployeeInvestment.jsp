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
                <rich:panel header="Investment Form">                    
                    <h:form id="investmentheads">
			 <rich:messages>
                        <f:facet name="infoMarker">
                            <h:graphicImage url="/img/success.png"/>
                        </f:facet>
                        <f:facet name="errorMarker">
                            <h:graphicImage url="/img/err.png"/>
                        </f:facet>
                        </rich:messages>
                        <h:panelGrid columns="4">
                            <h:outputText value="Employee Code"/>
                            <h:inputText id="empcCode" value="#{EmployeeInvestmentController.empCode}"/>
                            <rich:suggestionbox for="empcCode" var="abc" fetchValue="#{abc.code}"  
                                                suggestionAction="#{SearchBean.getSuggestion}">
                                <h:column>
                                    <h:outputText value="#{abc.name}"/>
                                </h:column>
                                <h:column>
                                    <h:outputText value="#{abc.code}"/>
                                </h:column>
                            </rich:suggestionbox>
                            <a4j:commandButton value="Submit" action="#{EmployeeInvestmentController.loadData}" reRender="invTable"/>               
                        </h:panelGrid>                   
                        <h:panelGrid width="80%"  id="invTable" columns="3">
                            <rich:dataTable   style="width:90%;" 
                                              binding="#{EmployeeInvestmentController.dataGrid}" 
                                              value="#{EmployeeInvestmentController.investments}" var="dept">

                               <%-- <rich:column width="40%" >
                                    <f:facet name="header">
                                        <h:outputText  value="Details Of saving"/>
                                    </f:facet>
                                    <h:outputText value="#{dept.name}" />
                                </rich:column>
                                <rich:column width="40%" >
                                    <f:facet name="header">
                                        <h:outputText  value="Section"/>
                                    </f:facet>
                                    <h:outputText value="#{dept.under}" />
                                </rich:column>
                                <rich:column width="20%" >
                                    <f:facet name="header">
                                        <h:outputText  value="Amount Paid "/>
                                    </f:facet>
                                    <h:inputText value="#{dept.amount}" />
                                </rich:column> --%>
				<rich:column width="50%" >
                                    <f:facet name="header">
                                        <h:outputText  value="Details Of saving"/>
                                    </f:facet>
                                    <h:outputText value="#{dept.investmentHead}" />
                                </rich:column>
                                <rich:column width="20%" >
                                    <f:facet name="header">
                                        <h:outputText  value="Section"/>
                                    </f:facet>
                                    <h:outputText value="#{dept.investmentUnder}" />
                                </rich:column>
                                <rich:column width="20%" >
                                    <f:facet name="header">
                                        <h:outputText  value="Amount Paid "/>
                                    </f:facet>
                                    <h:inputText value="#{dept.actualInvestment}" />
                                </rich:column>
                                 <rich:column width="20%" >
                                    <f:facet name="header">
                                        <h:outputText  value="Max Limit "/>
                                    </f:facet>
                                    <h:outputText value="#{dept.maxLimitAmount}" />
                                </rich:column>
                                 <rich:column width="20%" >
                                    <f:facet name="header">
                                        <h:outputText  value="Net Savings "/>
                                    </f:facet>
                                    <h:outputText value="#{dept.netSavings}" />
                               </rich:column>
                            </rich:dataTable>
                        </h:panelGrid>
                        <h:commandButton value="Update" action="#{EmployeeInvestmentController.update}"/>
                    </h:form>
                </rich:panel>
                <br />
                <hr/>

            </f:view>
        </div>
    </body>
</html>
