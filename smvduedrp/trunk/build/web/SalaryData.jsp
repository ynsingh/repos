<%-- 
    Document   : SalaryData
    Created on : Jul 10, 2010, 12:21:33 PM
    Author     : Algox
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
    </head>
    <body>
        <f:view>
            
            <h:form>
                <h:panelGrid binding="#{SalaryDataBean.grid}">
                <h:dataTable bgcolor="yellow" border="1" value="#{SalaryDataBean.allData}" var="data">
                    <h:column>
		    <f:facet name="header">
                        <h:outputText value="Salary Head"/>
		    </f:facet>
		    <h:outputText value="#{data.headName}" />
		  </h:column>
                    <h:column>
		    <f:facet name="header">
                        <h:outputText value="Amount(Rs.)"/>
		    </f:facet>
                        <h:inputText required="true" value="#{data.headValue}" >
                            <f:valueChangeListener type="#{SalaryDataBean}"/>
                        </h:inputText>
		  </h:column>
                </h:dataTable>
                <h:commandButton value="Update Data" action="#{SalaryDataBean.print}"/>
                </h:panelGrid>
            </h:form>
        </f:view>

    </body>
</html>
