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
        <script type="text/javascript">
            function showMyDiv()
            {
                document.getElementById("myDiv").visibility=visible;
            }
        </script>
        <style type="text/css" >


.hdn
    {
       visibility: hidden;

    }


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

.myDiv
{
 visibility: collapse;

}

            
        </style>

        


    </head>
    <body>

        <f:view>
            <h:form id="myform">

                <h:commandButton value="Add New" onclick="showMyDiv()"/>

                <div id="myDiv" class="myDiv">
                <h:form>
                    <h:outputText value="Employee name"/>
                    <h:selectOneMenu value=""/>
                    <h:outputText value="Investment Head"/>
                    <h:selectOneMenu value=""/>
                    <h:outputText value="Amount(Rs)"/>
                    <h:inputText value="0"/>

                    
                </h:form>
                </div>



                <h:outputText value="Employee's Annual Investment Plan"/>
                <h:dataTable width="100%" rowClasses="oddRow,evenRow"  border="0"  value="#{InvestmentPlanBean.allPlanes}" var="emp">

                    <h:column>
		    <f:facet name="header" >
                        <h:outputText value="Plan ID"/>
		    </f:facet>
		    <h:outputText value="#{emp.planId}" />
		  </h:column>
                  <h:column>
		    <f:facet name="header">
                        <h:outputText value="Investment Head"/>
		    </f:facet>
		    <h:outputText value="#{emp.headId.name}" />
		  </h:column>
                  <h:column>
		    <f:facet name="header">
                        <h:outputText value="Amount"/>
		    </f:facet>
		    <h:outputText value="#{emp.amount}" />
		  </h:column>
                  <h:column>
		    <f:facet name="header">
                        <h:outputText value="Employee Name"/>
		    </f:facet>
		    <h:outputText value="#{emp.empId.name}" />
		  </h:column>
                  <h:column>
		    <f:facet name="header">
                        <h:outputText value="Employee Code "/>
		    </f:facet>
		    <h:outputText value="#{emp.empId.code}" />
		  </h:column>
                    <h:column>
		    <f:facet name="header">
                        <h:outputText value="Remove "/>
		    </f:facet>
                        <h:commandButton value="Remove" />
		  </h:column>
                    <h:column>
		    <f:facet name="header">
                        <h:outputText value="Edit "/>
		    </f:facet>
                        <h:commandButton value="Edit" />
		  </h:column>
                  
                </h:dataTable>
            </h:form>

        </f:view>

    </body>
</html>
