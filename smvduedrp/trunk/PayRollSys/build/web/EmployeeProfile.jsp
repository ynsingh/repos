<%--
    Document        : EmployeeProfile.jsp
    Created on      : 3:02 AM Friday, October 01, 2010
    Last Modified   : 5:53 AM Saturday, October 02, 2010
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
        <title>EmployeeProfile.jsp</title>
        <link rel="stylesheet" type="text/css" href="css/reset.css"/>
        <link rel="stylesheet" type="text/css" href="css/subpage.css"/>
        <link rel="stylesheet" type="text/css" href="css/components.css"/>
        <link rel="stylesheet" type="text/css" href="css/table.css"/>
        <link rel="stylesheet" type="text/css" href="calendar.css"/>
        
    </head>
    <f:view>
    <body class="subpage" id="">
        
        <div class="container_form">
            
            <div class="xerror"> Add New Employee  </div>
                <hr class="line" />
                <h:form  id="profileForm">
                    
                    <h:panelGroup styleClass="myTable"  layout="table">
                        <h:panelGrid
                            columns="3"
                            styleClass="myTable"
                            columnClasses="label,field">
                                               
                            <h:outputText styleClass="Label" value="Employee Code"/>
                            <h:inputText id="empCode" styleClass="fields" required="true" requiredMessage="Please Enter Employee Code"  value="#{EmployeeBean.code}">
                                <f:validateLength  minimum="2" maximum="10"/>
                            </h:inputText>
                            <h:message styleClass="error" for="empCode" tooltip="true"/>
                            <h:outputText styleClass="Label" value="Employee Name"/>
                            <h:inputText id="empName" required="true" requiredMessage="Enter Employee's Name."  styleClass="fields" value="#{EmployeeBean.name}">
                                <f:validateLength minimum="2" maximum="70"/>
                            </h:inputText>
                            <h:message styleClass="error" for="empName" tooltip="Enter Employee's Name."/>
                            <h:outputText styleClass="Label" value="Gender"/>
                            <h:selectOneMenu  styleClass="combo" id="empGender" value="#{EmployeeBean.male}">
                                        <f:selectItem itemValue="true" itemLabel="Male"/>
                                        <f:selectItem itemValue="false" itemLabel="Female"/>
                            </h:selectOneMenu>
                            <h:message for="empGender" tooltip="Employee's Gender."/>
                            <h:outputText styleClass="Label" value="Employee Type"/>
                            <h:selectOneMenu  styleClass="combo" id="empType"  value="#{EmployeeBean.type}">
                                <f:selectItems value="#{EmployeeTypeBean.items}"/>
                            </h:selectOneMenu>
                            <h:message for="empType" tooltip="Employee Type. Select From Choices"/>
                            <h:outputText styleClass="Label" value="Pay Band"/>
                            <h:selectOneMenu styleClass="combo"  id="empGrade"  value="#{EmployeeBean.grade}">
                                <f:selectItems value="#{SalaryGradeBean.grades}"/>
                            </h:selectOneMenu>
                            <h:message for="empGrade" tooltip="Employee's Date of Birth. Format as yyyy-mm-dd"/>
                            <h:outputText styleClass="Label" value="Department"/>
                            <h:selectOneMenu styleClass="combo" id="empDept"   value="#{EmployeeBean.dept}">
                                <f:selectItems value="#{DepartmentBean.allDepts}"/>
                            </h:selectOneMenu>
                            <h:message for="empDept" tooltip="Employee Department. Select From Choices"/>
                            <h:outputText styleClass="Label" value="Designation"/>
                            <h:selectOneMenu styleClass="combo"  id="empDesig"  value="#{EmployeeBean.desig}">
                                <f:selectItems value="#{DesignationBean.desigs}"/>
                            </h:selectOneMenu>
                            <h:message for="empDesig" tooltip="Employee Designation. Select From Choices"/>
                            <h:outputText styleClass="Label" value="Date of Birth"/>
                            <h:inputText id="empDob"  styleClass="fields" required="true" requiredMessage="Employee's DOB (Format as yyyy-mm-dd)" value="#{EmployeeBean.dob}">
                                <f:validator validatorId="dateValidator" />
                            </h:inputText>
                            <h:message styleClass="error" for="empDob" tooltip="Employee's Date of Birth. Format as yyyy-mm-dd"/>
                            <h:outputText styleClass="Label" value="Date of Joining"/>
                            <h:inputText id="empDoj"  styleClass="fields" required="true" requiredMessage="Employee's DOJ (Format as yyyy-mm-dd)" value="#{EmployeeBean.doj}">
                                <f:validator validatorId="dateValidator" />
                            </h:inputText>
                            <h:message styleClass="error" for="empDoj" tooltip="Employee's Date of Joining. Format as yyyy-mm-dd"/>
                            
                            <h:outputText styleClass="Label" value="Phone/Mobile"/>
                            <h:inputText id="empPhone"  styleClass="fields" value="#{EmployeeBean.phone}"/>
                            <h:message for="empPhone" tooltip="Employee's Date of Birth. Format as yyyy-mm-dd"/>
                            <h:outputText styleClass="Label" value="E-Mail ID"/>
                            <h:inputText id="empEmail" styleClass="fields" value="#{EmployeeBean.email}"/>
                            <h:message for="empEmail" tooltip="Employee's Date of Birth. Format as yyyy-mm-dd"/>
                            <h:outputText styleClass="Label" value="Bank Account No."/>
                            <h:inputText id="empBank" styleClass="fields" value="#{EmployeeBean.bankAccNo}"/>
                            <h:message for="empBank" tooltip="Employee's Date of Birth. Format as yyyy-mm-dd"/>
                            <h:outputText styleClass="Label" value="PF. Account No."/>
                            <h:inputText id="empPf" styleClass="fields" value="#{EmployeeBean.pfAccNo}"/>
                            <h:message for="empPf" tooltip="Employee's Date of Birth. Format as yyyy-mm-dd"/>
                            <h:outputText styleClass="Label" value="PAN No."/>
                            <h:inputText id="empPan" styleClass="fields" value="#{EmployeeBean.panNo}"/>
                            <h:message for="empPan" tooltip="Employee's Date of Birth. Format as yyyy-mm-dd"/>
                            <h:outputText value="#{EmployeeBean.message}"/>
                            <h:panelGroup>
                            <h:commandButton  styleClass="panel"  value="Save" action="#{EmployeeBean.save}"/>
                            <h:commandButton styleClass="panel" value="Reset" onclick="this.form.reset()"/>
                            </h:panelGroup>
                            <h:outputText value=""/>
                        </h:panelGrid>
                        
                        
                        
                        
                    </h:panelGroup>
                </h:form>               
            </f:view>
        </div>
    </body>
</html>
