<%--
    Document        : AddUser.jsp
    Created on      : 3:02 AM Friday, October 01, 2010
    Last Modified   : 4:03 AM Saturday, October 02, 2010
    Author          :  *  Copyright (c) 2010 - 2011 SMVDU, Katra.
*  All Rights Reserved.
**  Redistribution and use in source and binary forms, with or 
*  without modification, are permitted provided that the following 
*  conditions are met: 
**  Redistributions of source code must retain the above copyright 
*  notice, this  list of conditions and the following disclaimer. 
* 
*  Redistribution in binary form must reproduce the above copyright
*  notice, this list of conditions and the following disclaimer in 
*  the documentation and/or other materials provided with the 
*  distribution. 
* 
* 
*  THIS SOFTWARE IS PROVIDED ``AS IS'' AND ANY EXPRESSED OR IMPLIED 
*  WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES 
*  OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE 
*  DISCLAIMED.  IN NO EVENT SHALL ETRG OR ITS CONTRIBUTORS BE LIABLE 
*  FOR ANY DIRECT, INDIRECT, INCIDENTAL,SPECIAL, EXEMPLARY, OR 
*  CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT 
*  OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR 
*  BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY,
*  WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE 
*  OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, 
*  EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE. 
* 
* 
*  Contributors: Members of ERP Team @ SMVDU, Katra
*
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

                            
                                <h:panelGrid columns="3">
                                <h:outputText styleClass="Label" value="Employee Name"/>
                                <h:inputText id="empName" styleClass="fields" value="#{LoggedEmployee.profile.name}"/>
                                <h:message for="empName" styleClass="error"/>
                                
                                <h:outputText styleClass="Label" value="Father's Name"/>
                                <h:inputText id="empfName" 
                                             styleClass="fields" value="#{LoggedEmployee.profile.fatherName}">
                                    <f:validateLength  maximum="70"/>
                                </h:inputText>
                                <h:message styleClass="error" for="empName" tooltip="Enter Employee's Name."/>
                                <h:outputText styleClass="Label" value="Employee Type"/>
                                <h:selectOneMenu styleClass="combo" id="empType" value="#{LoggedEmployee.profile.type}">
                                    <f:selectItems value="#{EmployeeTypeBean.items}"/>
                                </h:selectOneMenu>
                                <h:message for="empType"/>
                                <h:outputText styleClass="Label" value="Department"/>
                                <h:selectOneMenu readonly="true"  id="empDept" value="#{LoggedEmployee.profile.dept}">
                                    <f:selectItems value="#{DepartmentBean.arrayAsItem}"/>
                                </h:selectOneMenu>
                                <h:message for="empDept"/>
                                <h:outputText styleClass="Label" value="Designation"/>
                                <h:selectOneMenu readonly="true" id="empDesig" value="#{LoggedEmployee.profile.desig}">
                                    <f:selectItems value="#{DesignationBean.arrayAsItem}"/>
                                </h:selectOneMenu>
                                <h:message for="empDesig"/>
                                <h:outputText styleClass="Label" value="Pay Band"/>
                                <h:selectOneMenu readonly="true" styleClass="combo" value="#{LoggedEmployee.profile.grade}">
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
                                    <a4j:commandButton id="btnSave" reRender="pnl" styleClass="panel" value="Update" action="#{EmployeeBean.updateProfile}"/>
                                    <h:commandButton id="btnReset" styleClass="panel" value="Reset" onclick="this.form.reset()"/>
                                    
                                </h:panelGroup>
                            </h:panelGrid>
                    </rich:panel>
                </h:form>
            </f:view>

        </div>
    </body>
</html>
