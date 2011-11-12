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
                <rich:panel header="Annual TDS Report">
                    <h:form id="investmentheads">
                        <h:panelGrid columns="4">
                            <h:outputText value="Employee Code"/>
                            <h:outputText id="empcCode"  value="#{TDSReport.empCode}"/>
                        </h:panelGrid>    
                            <rich:dataTable   style="width:90%;"
                                              value="#{TDSReport.data}" var="dt">
                                <rich:column width="40%" >
                                    <f:facet name="header">
                                        <h:outputText  value="Month,Year"/>
                                    </f:facet>
                                    <h:outputText value="#{dt.name}" />
                                </rich:column>
                                <rich:column width="40%" >
                                    <f:facet name="header">
                                        <h:outputText  value="Amount"/>
                                    </f:facet>
                                    <h:outputText value="#{dt.baseAmount}" />
                                </rich:column>
                                 <f:facet name="footer">
                                     <h:outputText  value="Net Total #{TDSReport.netTotal}"/>
                                    </f:facet>
                            </rich:dataTable>
                    </h:form>
                </rich:panel>
                <br />
                <hr/>

            </f:view>
        </div>
    </body>
</html>
