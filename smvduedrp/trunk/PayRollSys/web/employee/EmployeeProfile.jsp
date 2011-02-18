<%--
    Document        : EmployeeProfile.jsp
    Created on      : 3:02 AM Friday, October 01, 2010
    Last Modified   : 5:53 AM Saturday, October 02, 2010
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
        <title>EmployeeProfile.jsp</title>
        <link rel="stylesheet" type="text/css" href="css/reset.css"/>
        <link rel="stylesheet" type="text/css" href="css/subpage.css"/>
        <link rel="stylesheet" type="text/css" href="css/components.css"/>
        <link rel="stylesheet" type="text/css" href="css/table.css"/>

    </head>
    <f:view>
        <body class="subpage" id="">
            <div class="container_form">
                <rich:panel header="Add New Employee" style="width:500px;">
                    <h:form  id="profileForm">
                        <a4j:region id="empForm">                            
                            <h:panelGrid columns="2" >
                                <rich:messages layout="table" style="border:1;" >
                                        <f:facet name="infoMarker">
                                            <h:graphicImage url="/img/success.png"/>
                                        </f:facet>
                                         <f:facet name="errorMarker">
                                            <h:graphicImage url="/img/err.png"/>
                                        </f:facet>
                                    </rich:messages>

                            <h:panelGrid
                                columns="3"
                                columnClasses="label,field">
                                <h:outputText styleClass="Label" value="Employee Code"/>
                                <h:inputText id="empCode" styleClass="fields" required="true"
                                             requiredMessage="Please Enter Employee Code"  value="#{EmployeeBean.code}">
                                    <f:validateLength  minimum="2" maximum="10"/>
                                </h:inputText>
                                <h:message styleClass="error" for="empCode" tooltip="true"/>
                                <h:outputText styleClass="Label" value="Employee Name"/>
                                <h:inputText id="empName" required="true" 
                                             requiredMessage="Enter Employee's Name."
                                             styleClass="fields" value="#{EmployeeBean.name}">
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
                                <h:message for="empGrade" tooltip="*"/>
                                 <h:outputText styleClass="Label" value="Department"/>
                                <h:selectOneMenu styleClass="combo" id="empDept"   value="#{EmployeeBean.dept}">
                                    <f:selectItems value="#{DepartmentBean.arrayAsItem}"/>
                                </h:selectOneMenu>
                                <h:message for="empDept" tooltip="*"/>
                                <h:outputText styleClass="Label" value="Designation"/>
                                <h:selectOneMenu  styleClass="combo"  id="empDesig"  value="#{EmployeeBean.desig}">
                                    <f:selectItems value="#{DesignationBean.arrayAsItem}"/>
                                </h:selectOneMenu>
                                <h:message for="empDesig" tooltip="Employee Designation. Select From Choices"/>
                                <h:outputText styleClass="Label" value="Date of Birth"/>
                                <rich:calendar converter="dateConverter" showWeekDaysBar="false" 
                                               showFooter="false" styleClass="special"
                                               datePattern="yyyy-MM-dd" id="empDob" popup="true"
                                               required="true" requiredMessage="*Enter Date Of Birth"
                                               value="#{EmployeeBean.dob}">
                                </rich:calendar>
                                <h:message styleClass="error" for="empDob" tooltip="*"/>
                                <h:outputText styleClass="Label" value="Date of Joining"/>
                               <rich:calendar converter="dateConverter" 
                                              showWeekDaysBar="false" showFooter="false" styleClass="special"
                                              datePattern="yyyy-MM-dd" id="empDoj" popup="true" required="true"
                                              requiredMessage="*Enter Date Of Joining" value="#{EmployeeBean.doj}"/>
                                <h:message styleClass="error" for="empDoj" tooltip="*"/>

                                <h:outputText styleClass="Label" value="Phone/Mobile"/>
                                <h:inputText id="empPhone"  styleClass="fields" value="#{EmployeeBean.phone}"/>
                                <h:message for="empPhone" tooltip="*"/>
                                <h:outputText styleClass="Label" value="E-Mail ID"/>
                                <h:inputText id="empEmail" styleClass="fields" value="#{EmployeeBean.email}"/>
                                <h:message for="empEmail" tooltip="*"/>
                                <h:outputText styleClass="Label" value="Bank Account No."/>
                                <h:inputText id="empBank" styleClass="fields" value="#{EmployeeBean.bankAccNo}"/>
                                <h:message for="empBank" tooltip="*"/>
                                <h:outputText styleClass="Label" value="PF. Account No."/>
                                <h:inputText id="empPf" styleClass="fields" value="#{EmployeeBean.pfAccNo}"/>
                                <h:message for="empPf" tooltip="*"/>
                                <h:outputText styleClass="Label" value="PAN No."/>
                                <h:inputText id="empPan" styleClass="fields" value="#{EmployeeBean.panNo}"/>
                                <h:message for="empPan" tooltip="*"/>
                                <h:panelGroup >
                                <rich:separator/>
                                <h:outputText value="Click To Save"/>
                                </h:panelGroup>
                                <h:panelGroup>
                                    <a4j:commandButton id="success" reRender="report"  styleClass="panel"
                                                       value="Save" action="#{EmployeeBean.save}"/>
                                    <h:commandButton styleClass="panel" value="Reset" onclick="this.form.reset()"/>                                    
                                </h:panelGroup>                                
                                <a4j:status for="empForm" />
                                <h:outputText value=""/>
                            </h:panelGrid>
                            </h:panelGrid>
                        </a4j:region>
                    </h:form>
                </rich:panel>
            </f:view>
        </div>
    </body>
</html>
