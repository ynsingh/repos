<%--
    Document        : AddUser.jsp
    Created on      : 3:02 AM Friday, October 01, 2010
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
    <body class="subpage" onload="activate()" id="">
        <div class="container_form">
            <f:view>
                <div class="xerror"> Edit Employee Profile </div>
                <hr class="line" />
                <h:form id="editForm">
                    <h:panelGroup layout="table" >
                        <h:panelGrid
                            columns="3"
                            styleClass="myTable"
                            columnClasses="label,field,label,field">  
                            <h:outputText styleClass="Label" value="Employee Code"/>
                            <h:inputText onblur="activate()"  id="empCode"value="#{EmployeeBean.code}" styleClass="fields" required="true" requiredMessage="Enter Employee Code" immediate="true"/>
                            <h:commandButton id="ldbtn" styleClass="panel" action="#{EmployeeBean.loadProfile}" value="Load Profile"/>
                            
                            <h:outputText styleClass="Label" value="Employee Name"/>
                            <h:inputText id="empName" styleClass="fields" value="#{EmployeeBean.name}"/>
                            <h:message for="empName" styleClass="error"/>
                            <h:outputText styleClass="Label" value="Employee Type"/>
                            <h:selectOneMenu styleClass="combo" id="empType" value="#{EmployeeBean.type}">
                                <f:selectItems value="#{EmployeeTypeBean.items}"/>
                            </h:selectOneMenu>
                            <h:message for="empType"/>
                            <h:outputText styleClass="Label" value="Department"/>
                            <h:selectOneMenu styleClass="combo" id="empDept" value="#{EmployeeBean.dept}">
                                <f:selectItems value="#{DepartmentBean.allDepts}"/>
                            </h:selectOneMenu>
                            <h:message for="empDept"/>
                            <h:outputText styleClass="Label" value="Designation"/>
                            <h:selectOneMenu styleClass="combo" id="empDesig" value="#{EmployeeBean.desig}">
                                <f:selectItems value="#{DesignationBean.desigs}"/>
                            </h:selectOneMenu>
                            <h:message for="empDesig"/>
                            <h:outputText styleClass="Label" value="Pay Band"/>
                            <h:selectOneMenu styleClass="combo" value="#{EmployeeBean.grade}">
                                <f:selectItems value="#{SalaryGradeBean.grades}"/>
                            </h:selectOneMenu>
                            <h:message for="empDesig"/>
                            <h:outputText styleClass="Label" value="Date of Birth"/>
                            <h:inputText styleClass="fields" id="empDob" value="#{EmployeeBean.dob}"/>
                            <h:message for="empDob" styleClass="error"/>
                            <h:outputText styleClass="Label" value="Date of Joining"/>
                            <h:inputText styleClass="fields" id="empDoj"  value="#{EmployeeBean.doj}"/>
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
                            <h:panelGroup>
                                <h:outputText value=""/>
                                <h:outputText value=""/>
                                <h:commandButton id="btnSave" styleClass="panel" value="Update" action="#{EmployeeBean.updateProfile}"/>
                                <h:commandButton id="btnReset" styleClass="panel" value="Reset" onclick="this.form.reset()"/>
                            </h:panelGroup>
                        </h:panelGrid>
                        
                            
                    </h:panelGroup>
                </h:form>
            </f:view>

        </div>
    </body>
</html>
