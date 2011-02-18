<%--
    Document        : AddUser.jsp
    Created on      : 3:02 AM Friday, October 01, 2010
    Last Modified   : 4:03 AM Saturday, October 02, 2010
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
        <title>Edit Employee</title>
        <link rel="stylesheet" type="text/css" href="css/reset.css"/>
        <link rel="stylesheet" type="text/css" href="css/subpage.css"/>
        <link rel="stylesheet" type="text/css" href="css/table.css"/>
        <script type="text/javascript">

            function activate()
            {
                var x = document.getElementById("editForm:empCode").value;
                if(x!="")
                {
                    document.getElementById("editForm:ldbtn").disabled=false;
                    document.getElementById("editForm:btnSave").disabled=false;
                    document.getElementById("editForm:btnReset").disabled=false;
                }
                else
                {
                    document.getElementById("editForm:ldbtn").disabled=true;
                    document.getElementById("editForm:btnSave").disabled=false;
                    document.getElementById("editForm:btnReset").disabled=false;
                }
            }

        </script>



    </head>
    <body class="subpage"  id="">
        <div class="container_form">
            <f:view>

                <hr class="line" />
                <h:form>
                    <rich:panel style="width:600px;" header="Edit Employee Profile" id="pnl">
                        <h:panelGrid columns="2">
                            <rich:messages>
                                        <f:facet name="infoMarker">
                                            <h:graphicImage url="/img/success.png"/>
                                        </f:facet>
                                    </rich:messages>
                        </h:panelGrid>

                            <rich:panel>
                                <h:panelGrid columns="4">
                                <h:outputText  styleClass="Label" value="Employee Code"/>                                
                                <h:inputText onblur="activate()"  id="empCode"value="#{EmployeeBean.code}" styleClass="fields" required="true" requiredMessage="Enter Employee Code" immediate="true"/>
                                <rich:suggestionbox for="empCode" var="abc" fetchValue="#{abc.code}"  suggestionAction="#{SearchBean.getSuggestion}">
                                <h:column>
                                <h:outputText value="#{abc.name}"/>
                                </h:column>
                                <h:column>
                                <h:outputText value="#{abc.code}"/>
                                </h:column>
                                </rich:suggestionbox>
                                <a4j:commandButton id="ldbtn" styleClass="panel" action="#{EmployeeBean.loadProfile}" value="Load Profile"/>
                                </h:panelGrid>
                            </rich:panel>
                                <h:panelGrid columns="3">
                                <h:outputText styleClass="Label" value="Employee Name"/>
                                <h:inputText id="empName" styleClass="fields" value="#{EmployeeBean.name}"/>
                                <h:message for="empName" styleClass="error"/>
                                <h:outputText styleClass="Label" value="Employee Type"/>
                                <h:selectOneMenu styleClass="combo" id="empType" value="#{EmployeeBean.type}">
                                    <f:selectItems value="#{EmployeeTypeBean.items}"/>
                                </h:selectOneMenu>
                                <h:message for="empType"/>
                                <h:outputText styleClass="Label" value="Department"/>
                                <h:selectOneMenu  id="empDept" value="#{EmployeeBean.dept}">
                                    <f:selectItems value="#{DepartmentBean.arrayAsItem}"/>
                                </h:selectOneMenu>
                                <h:message for="empDept"/>
                                <h:outputText styleClass="Label" value="Designation"/>
                                <h:selectOneMenu id="empDesig" value="#{EmployeeBean.desig}">
                                    <f:selectItems value="#{DesignationBean.arrayAsItem}"/>
                                </h:selectOneMenu>
                                <h:message for="empDesig"/>
                                <h:outputText styleClass="Label" value="Pay Band"/>
                                <h:selectOneMenu styleClass="combo" value="#{EmployeeBean.grade}">
                                    <f:selectItems value="#{SalaryGradeBean.grades}"/>
                                </h:selectOneMenu>
                                <h:message for="empDesig"/>
                                <h:outputText styleClass="Label" value="Date of Birth"/>
                                <rich:calendar converter="dateConverter" datePattern="yyyy-MM-dd" styleClass="fields" id="empDob" value="#{EmployeeBean.dob}"/>
                                <h:message for="empDob" styleClass="error"/>
                                <h:outputText  styleClass="Label" value="Date of Joining"/>
                                <rich:calendar converter="dateConverter" datePattern="yyyy-MM-dd" styleClass="fields" id="empDoj"  value="#{EmployeeBean.doj}"/>
                                <h:message for="empDob" styleClass="error"/>
                                <h:outputText styleClass="Label" value="Phone/Mobile"/>
                                <h:inputText styleClass="fields" value="#{EmployeeBean.phone}"/>
                                <h:message for="empDesig"/>
                                <h:outputText styleClass="Label" value="E-Mail ID"/>
                                <h:inputText styleClass="fields" value="#{EmployeeBean.email}"/>
                                <h:message for="empDesig"/>
                                <h:outputText styleClass="Label" value="Bank Account No."/>
                                <h:inputText styleClass="fields" value="#{EmployeeBean.bankAccNo}"/>
                                <h:message for="empDesig"/>
                                <h:outputText styleClass="Label" value="PF. Account No."/>
                                <h:inputText styleClass="fields" value="#{EmployeeBean.pfAccNo}"/>
                                <h:message for="empDesig"/>
                                <h:outputText styleClass="Label" value="PAN No."/>
                                <h:inputText styleClass="fields" value="#{EmployeeBean.panNo}"/>
                                <h:message for="empDesig"/>
                                <h:panelGroup >
                                    <a4j:commandButton id="btnSave" styleClass="panel" value="Update" action="#{EmployeeBean.updateProfile}"/>
                                    <h:commandButton id="btnReset" styleClass="panel" value="Reset" onclick="this.form.reset()"/>
                                    
                                </h:panelGroup>
                            </h:panelGrid>
                    </rich:panel>
                </h:form>
            </f:view>

        </div>
    </body>
</html>
