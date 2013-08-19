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
        <link rel="stylesheet" type="text/css"  href="css/PanelWidth.css"/>
        <link rel="stylesheet" type="text/css" href="../css/richpanel.css"/>
        <link type="text/css" rel="stylesheet" href="../bankDetails.css"/>
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
                <rich:panel style="width:auto;float:left;" header="Edit Employee Profile" id="pnl">
                    <h:panelGrid columns="1">
                        <h:column>
                            <h:form>
                                <rich:toolTip for="empName" value="Standard Charecter(e.g @,#,$,...etc) Will Not Be Accepted, Length Should Be Three Charecter"/>
                                <rich:toolTip for="seci" value="Check , If Employee Is Senior Citizen Otherwise Uncheck"/>
                                <h:panelGrid columns="2">
                                    <rich:messages  >
                                        <f:facet name="infoMarker">
                                            <h:graphicImage url="/img/success.png"/>
                                        </f:facet>
                                        <f:facet name="errorMarker">
                                            <h:graphicImage url="/img/err.png"/>
                                        </f:facet>
                                    </rich:messages>
                                </h:panelGrid>
                                <h:panelGroup>
                                    <rich:panel>
                                        <h:panelGrid columns="2">
                                            <h:column>
                                                <h:panelGrid columns="8">
                                                    <h:outputText  styleClass="Label" value="Employee Code"/>
                                                    <h:inputText onblur="activate()"  id="empCode" value="#{EmployeeBean.code}" styleClass="fields" required="true" requiredMessage="Enter Employee Code" immediate="true"/>
                                                    <rich:suggestionbox for="empCode" var="abc" fetchValue="#{abc.code}"  suggestionAction="#{SearchBean.getSuggestion}">
                                                        <h:column>
                                                            <h:outputText value="#{abc.name}"/>
                                                        </h:column>
                                                        <h:column>
                                                            <h:outputText value="#{abc.code}"/>
                                                        </h:column>
                                                    </rich:suggestionbox>
                                                    <rich:toolTip value="Enter few characters of name and choose from List" for="empCode"/>
                                                    <a4j:commandButton id="ldbtn" styleClass="panel" action="#{EmployeeBean.loadProfile}" value="Load Profile" reRender="si"/>
                                                    <h:graphicImage value="#{EmployeeBean.statusI}"></h:graphicImage>
                                                    <h:outputText value="#{EmployeeBean.genDetails}"/>
                                                </h:panelGrid>
                                            </h:column>
                                        </h:panelGrid>
                                    </rich:panel>
                                </h:panelGroup>
                                <h:panelGrid columns="2">
                                    <h:panelGrid columns="3">
                                        <h:outputText value="Title"/>
                                        <h:selectOneMenu value="#{EmployeeBean.title}">
                                            <f:selectItem itemLabel="Prof." itemValue="Prof."/>
                                            <f:selectItem itemLabel="Doctor" itemValue="Doctor"/>
                                            <f:selectItem itemLabel="Mr." itemValue="Mr."/>
                                            <f:selectItem itemLabel="Mrs." itemValue="Mrs."/>
                                        </h:selectOneMenu>
                                        <h:message styleClass="error" for="empCode" tooltip="true"/>
                                        <h:outputText styleClass="Label" value="Employee Name"/>
                                        <h:inputText id="empName" styleClass="fields" value="#{EmployeeBean.name}">
                                            <h:message for="empName" styleClass="error"/>
                                        </h:inputText>
                                        <h:outputText value=""/>
                                        <h:outputText styleClass="Label" value="Father's Name"/>
                                        <h:inputText id="empfName"
                                                     styleClass="fields" value="#{EmployeeBean.fatherName}">
                                            <f:validateLength  maximum="70"/>
                                            <h:message styleClass="error" for="empfName" tooltip="Enter Employee's Name."/>
                                        </h:inputText>
                                        <h:outputText value=""/>
                                        <h:outputText styleClass="Label" value="Gender"/>
                                        <h:selectOneMenu  styleClass="combo" id="empGender" value="#{EmployeeBean.male}">
                                            <f:selectItem itemValue="#{EmployeeBean.gender}" itemLabel="Male"/>
                                            <f:selectItem itemValue="#{EmployeeBean.gender}" itemLabel="Female"/>
                                        </h:selectOneMenu>
                                        <h:message for="empGender" tooltip="Employee's Gender."/>
                                       
                                        
                                        <h:outputText styleClass="label" value="Gender Details"/>
                                        <h:selectOneMenu value="#{EmployeeBean.genDetailCode}" id = "seci">
                                            <f:selectItem itemLabel="<--Select One-->"/>
                                            <f:selectItems value="#{employeeSlabDetail.items}"/>
                                        </h:selectOneMenu>
                                        <h:outputText value=""/>
                                     
                                        <%--                                      <h:outputText styleClass="Label" value="Senior Citizen"/>
                                        <h:selectBooleanCheckbox value="#{EmployeeBean.seniorCitizen}" id="seci"/>
                                        <h:outputText value=""/>--%>
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
                                        <h:outputText value="Current Basic"/>
                                        <h:inputText id="empBasic" requiredMessage="Basic Is must.Input Numbers without decimal" validatorMessage="Input Numbers without decimal " value="#{EmployeeBean.currentBasic}"/>
                                        <h:message for="empBasic"  tooltip="*"/>
                                        <h:outputText value="Qualification"/>
                                        <h:inputText id="qual"  value="#{EmployeeBean.qualification}"/>
                                        <h:message for="qual"/>
                                        <h:outputText value="Experience"/>
                                        <h:inputText id="exp" value="#{EmployeeBean.experience}" />
                                        <h:message for="exp"/>
                                        <h:outputText styleClass="Label" value="PF. Account No."/>
                                        <h:inputText id="empPf" styleClass="fields" value="#{EmployeeBean.pfAccNo}"/>
                                        <h:message for="empPf" tooltip="*"/>
                                    </h:panelGrid>
                                    <h:panelGrid columns="3" >
                                        <h:outputText id="st" value="Employee Status"></h:outputText>
                                        <h:selectBooleanCheckbox id="sta" value="#{EmployeeBean.ststus}"></h:selectBooleanCheckbox>
                                        <rich:toolTip value="Click On Here To Activate/Deactivate An Employee" for="sta"/>
                                        <h:outputText styleClass="Label" value="Date of Birth"/>
                                        <rich:calendar enableManualInput="false" converter="dateConverter" datePattern="yyyy-MM-dd" styleClass="fields" id="empDob" value="#{EmployeeBean.dob}"/>
                                        <h:message for="empDob" styleClass="error"/>
                                        <h:outputText  styleClass="Label" value="Date of Joining"/>
                                        <rich:calendar enableManualInput="false" converter="dateConverter" datePattern="yyyy-MM-dd" styleClass="fields" id="empDoj"  value="#{EmployeeBean.doj}"/>
                                        <h:message for="empDob" styleClass="error"/>
                                        <h:outputText value="Year of Passing"/>
                                        <h:inputText id="yop" value="#{EmployeeBean.yearOfPassing}" />
                                        <h:message for="qual"/>
                                        <h:outputText value="Previous Employer"/>
                                        <h:inputText id="prev" value="#{EmployeeBean.previousEmployer}" />
                                        <h:message for="prev"/>
                                        <h:outputText styleClass="Label" value="Phone/Mobile"/>
                                        <h:inputText id="empPhone"  styleClass="fields" value="#{EmployeeBean.phone}"/>
                                        <h:message for="empPhone" tooltip="*"/>
                                        <h:outputText styleClass="Label" value="E-Mail ID"/>
                                        <h:inputText id="empEmail" styleClass="fields" value="#{EmployeeBean.email}"/>
                                        <rich:message>
                                            <f:facet name="errorMarker">
                                                <h:graphicImage url="/img/err.png"/>
                                            </f:facet>
                                        </rich:message>
                                        <h:outputText styleClass="Label" value="PAN No."/>
                                        <h:inputText id="empPan" styleClass="fields" value="#{EmployeeBean.panNo}"/>
                                        <h:message for="empPan" tooltip="*"/>
                                        <h:outputText styleClass="Label" value="Address"/>
                                        <h:inputTextarea id="address" styleClass="fields" value="#{EmployeeBean.address}"/>
                                        <h:message for="empPan" tooltip="*"/>
                                        <rich:separator />
                                        <rich:separator />
                                        <rich:separator />
                                        <h:outputText styleClass="Label" value="Bank Acc. No."/>
                                        <h:inputText id="ban" styleClass="fields" value="#{EmployeeBean.bankAccNo}"/>
                                        <h:message for="ban" tooltip="*"/>
                                        <h:outputText styleClass="Label" value="Bank Name "/>
                                        <h:inputText onclick="active"  value="#{EmployeeBean.bankIFSCcode}" styleClass="fields" id="bankInfo"/>
                                        <rich:suggestionbox  for="bankInfo" var="bif" fetchValue="#{bif.bankIFSCCode}" suggestionAction="#{EmployeeBean.getBankSuggestion}">
                                            <h:column>
                                                <f:facet name="header" >
                                                    <h:outputText value="Bank Name" style="font-size:14;"/>
                                                </f:facet>
                                                <h:outputText value="#{bif.bankName}"/>
                                            </h:column>
                                            <h:column>
                                                <f:facet name="header" >
                                                    <h:outputText value="Branch Name" style="font-size:14;"/>
                                                </f:facet>
                                                <h:outputText value="#{bif.bankBranch}"/>
                                            </h:column>
                                            <h:column></h:column>
                                            <h:column>
                                                <f:facet name="header" >
                                                    <h:outputText value="Bank IFSC Code" style="font-size:14;"/>
                                                </f:facet>
                                                <h:outputText value="#{bif.bankIFSCCode}"/>
                                            </h:column>
                                        </rich:suggestionbox>
                                    </h:panelGrid>
                                </h:panelGrid>
                                <h:panelGroup >
                                    <rich:separator/>
                                </h:panelGroup>
                                <h:panelGroup>
                                    <rich:panel>
                                        <h:panelGrid columns="3">
                                            <h:outputText  styleClass="Label" value="Date of Rsignation"/>
                                            <rich:calendar enableManualInput="false" converter="dateConverter" datePattern="yyyy-MM-dd" styleClass="fields" id="empReg"  value="#{EmployeeBean.dateOfResig}"/>
                                            <h:message for="empReg" styleClass="error"/>
                                            <h:outputText value="Notification Day"/>
                                            <h:inputText value="#{EmployeeBean.empNotDay}" id="empNot" />
                                            <h:message for="empNot" styleClass="error"/>
                                            <h:outputText value="Date Of Leaving" styleClass="Label"/>
                                            <rich:calendar enableManualInput="false" converter="dateConverter" datePattern="yyyy-MM-dd" styleClass="fields" id="empLea"  value="#{EmployeeBean.empLeaDate}"/>
                                            <h:message for="empLea" styleClass="error"/>
                                            <rich:toolTip for="empNot" value="Enter Number Of Notification Day ."/>
                                        </h:panelGrid>
                                    </rich:panel>
                                </h:panelGroup>
                                <h:panelGroup>
                                    <h:commandButton id="btnSave"  styleClass="panel" value="Update" action="#{EmployeeBean.updateProfile}"/>
                                    <h:commandButton id="btnReset" styleClass="panel" value="Reset" onclick="this.form.reset()"/>
                                </h:panelGroup>
                            </h:form>
                        </h:column>
                        <h:column>
                            <rich:panel styleClass="bodyBack"  header="Employee Notification">
                                <h:outputText style="color:red;font-size: 17px;font-weight: bold;" value="#{EmployeeBean.notification}"/>
                            </rich:panel>
                        </h:column>
                    </h:panelGrid>

                </rich:panel>
                <rich:panel style="width:auto;float:left;" header="Employee's Pending Bank Details">
                    <h:form>
                        <rich:panel>
                            <h:panelGrid columns="5">
                                <h:column>
                                    <h:outputText value="Bank Name "/>
                                    <h:inputText value="#{SearchBean.bfsc}" styleClass="fields" id="bankInfoup"/>
                                    <rich:suggestionbox id="kl"  for="bankInfoup" var="binf" fetchValue="#{binf.bankIFSCCode}" suggestionAction="#{EmployeeBean.getBankSuggestion}">
                                        <h:column>
                                            <f:facet name="header" >
                                                <h:outputText value="Bank Name" style="font-size:14;"/>
                                            </f:facet>
                                            <h:outputText value="#{binf.bankName}"/>
                                        </h:column>
                                        <h:column>
                                            <f:facet name="header" >
                                                <h:outputText value="Branch Name" style="font-size:14;"/>
                                            </f:facet>
                                            <h:outputText value="#{binf.bankBranch}"/>
                                        </h:column>
                                        <h:column></h:column>
                                        <h:column>
                                            <f:facet name="header" >
                                                <h:outputText value="Bank IFSC Code" style="font-size:14;"/>
                                            </f:facet>
                                            <h:outputText value="#{binf.bankIFSCCode}"/>
                                        </h:column>
                                    </rich:suggestionbox>
                                </h:column>
                                <h:column>
                                    <a4j:commandButton value="Activate" id="acti" reRender="result" action="#{SearchBean.updateBankedEmployee}"/>
                                </h:column>
                                <rich:toolTip value="Enter Bank Name In Which Employee's Wants To Activate/Deactivate His/Her Account" for="bankInfo"/>
                            </h:panelGrid>
                        </rich:panel>
                        <rich:spacer height="10"/>
                        <rich:panel id="result">
                            <rich:datascroller for="empSt" maxPages="20"/>
                            <h:dataTable headerClass="headerStyle" id="empSt" rows="20" rowClasses="rowStyle"  value="#{SearchBean.nonBankedDetails}" binding="#{SearchBean.dataGrid}"  var="nonbankd">
                                <h:column>
                                    <f:facet name="header" >
                                        <h:outputText value="Employee Code"/>
                                    </f:facet>
                                    <h:outputText value="#{nonbankd.code}"/>
                                </h:column>
                                <h:column>
                                    <f:facet name="header" >
                                        <h:outputText value="Employee Name"/>
                                    </f:facet>
                                    <h:outputText value="#{nonbankd.name}"/>
                                </h:column>
                                <h:column>
                                    <f:facet name="header" >
                                        <h:outputText value="Select Check Box"/>
                                    </f:facet>
                                    <h:selectBooleanCheckbox value="#{nonbankd.bankStatus}"/>
                                </h:column>
                            </h:dataTable>
                        </rich:panel>
                    </h:form>
                </rich:panel>
            </f:view>
        </div>
    </body>
</html>
