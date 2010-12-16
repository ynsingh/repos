<%-- 
    Document   : SearchEmployee
    Created on : Jul 8, 2010, 3:23:09 PM
    Author     : Algox
--%>

<%@ taglib prefix="f" uri="http://java.sun.com/jsf/core" %>
<%@ taglib prefix="h" uri="http://java.sun.com/jsf/html" %>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" type="text/css" href="css/table.css"/>
        <style type="text/css" >

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
    <body>

        <f:view>
            <div class="xerror"> Search Employee </div>
            <hr class="line" />
            <div >
               
                
            </div>
            <h:form id="myform">
                <h:dataTable width="100%" styleClass="myTable" rowClasses="oddRow,evenRow"  border="0"  value="#{EmployeeBean.all}" var="emp">
                    <h:column>
		    <f:facet name="header" >
                        <h:outputText value="Sr.No"/>
		    </f:facet>
		    <h:outputText value="#{emp.srNo}" />
		  </h:column>
                  <h:column>
		    <f:facet name="header">
                        <h:outputText value="Code"/>
		    </f:facet>
		    <h:outputText value="#{emp.code}" />
		  </h:column>
                  <h:column>
		    <f:facet name="header">
                        <h:outputText value="Name"/>
		    </f:facet>
		    <h:outputText value="#{emp.name}" />
		  </h:column>
                  <h:column>
		    <f:facet name="header">
                        <h:outputText value="Department"/>
		    </f:facet>
		    <h:outputText value="#{emp.deptName}" />
		  </h:column>
                  <h:column>
		    <f:facet name="header">
                        <h:outputText value="Designation "/>
		    </f:facet>
		    <h:outputText value="#{emp.desigName}" />
		  </h:column>
                  <h:column>
		    <f:facet name="header">
                        <h:outputText value="Employee Type"/>
		    </f:facet>
		    <h:outputText value="#{emp.typeName}" />
		  </h:column>
                  <h:column>
		    <f:facet name="header">
                        <h:outputText value="Salary Band"/>
		    </f:facet>
		    <h:outputText value="#{emp.bandName}" />
		  </h:column>
                </h:dataTable>
            </h:form>

        </f:view>

    </body>
</html>
