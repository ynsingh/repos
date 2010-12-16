<%-- 
    Document        : SalaryGrade
    Created on      : Jul 2, 2010, 4:26:10 AM
    Last Modified   : 4:03 AM Saturday, October 02, 2010
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
        <link rel="stylesheet" type="text/css" href="css/reset.css"/>
        <link rel="stylesheet" type="text/css" href="css/subpage.css"/>
        <link rel="stylesheet" type="text/css" href="css/table.css"/>
    </head>
    <body class="subpage" id="">
        <h1>Manage Grades</h1>
        <div class="container_form">
            <html:form action="/salarygrade">
                
                        <f:view>
                                    <div class="xerror"> Existing Grades </div>
                                    <h:dataTable styleClass="myTable" value="#{SalaryGradeBean.allGrades}" var="grades" border="1">
                                            <h:column>
                                                <f:facet name="header">
                                                    <h:outputText value="Grade Name"/>
                                                </f:facet>
                                                <h:outputText value="#{grades.name}" />
                                            </h:column>
                                            <h:column>
                                                <f:facet name="header">
                                                    <h:outputText  value="Max Amount"/>
                                                </f:facet>
                                                <h:outputText value="#{grades.maxValue}" />
                                            </h:column>
                                            <h:column>
                                                <f:facet name="header">
                                                    <h:outputText  value="Min Amount"/>
                                                </f:facet>
                                                <h:outputText value="#{grades.minValue}" />
                                            </h:column>

                                        </h:dataTable>
                                    
                        <h:form>
                        <table  class="myTable">
                            <tr>
                                <td>Grade Name</td>
                                <td width="72%"><h:inputText id="grdName" required="true" requiredMessage="Enter New Grade Name" value="#{SalaryGradeBean.name}" /></td>
                                <td><h:message for="grdName" styleClass="error"/></td>
                            </tr>
                            <tr>
                                <td>Maximum</td>
                                <td width="72%"><h:inputText required="true" requiredMessage="Enter Maximum Value in Number" id="salMax" value="#{SalaryGradeBean.maxValue}" /></td>
                                <td><h:message for="salMax" styleClass="error"/></td>
                            </tr>
                            <tr>
                                <td>Minimum</td>
                                <td width="72%"><h:inputText id="salMin" required="true" requiredMessage="Enter Maximum Value in Number"  value="#{SalaryGradeBean.minValue}" /></td>
                                <td><h:message for="salMax" styleClass="error"/></td>
                            </tr>

                            <tr>
                                <td></td>
                                <td><h:commandButton value="Save" action="#{SalaryGradeBean.save}" styleClass="submit"/></td>
                            </tr>
                            </table>
                        </h:form>

                    </f:view>


                    </tbody>
                </table>
            </html:form>
    </body>
</html>
