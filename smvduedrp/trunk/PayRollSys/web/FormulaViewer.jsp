<%-- 
    Document   : Designation
    Created on : Sept 29, 2010
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
        <title></title>
        <link rel="stylesheet" type="text/css" href="css/reset.css"/>
        <link rel="stylesheet" type="text/css" href="css/subpage.css"/>
    </head>
    <body class="subpage" id="">
        <div class="container_form">
        <f:view>
            <h:form id="departments">
                <h:dataTable  id="books" value="#{SalaryFormulaBean.formulas}" var="formula" border="1">
		  <h:column>
		    <f:facet name="header">
                        <h:outputText value="Salary Head"/>
		    </f:facet>
		    <h:outputText value="#{formula.name}" />
		  </h:column>
		  <h:column>
		    <f:facet name="header">
		      <h:outputText  value="Formula Expression"/>
		    </f:facet>
		    <h:outputText value="#{formula.formula}" />
		  </h:column>		  
		  
		</h:dataTable>		
		</h:form>

        
        </f:view>
       </div>
    </body>
</html>
