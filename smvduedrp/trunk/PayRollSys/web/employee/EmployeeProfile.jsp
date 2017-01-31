<%--
    Document        : EmployeeProfile.jsp
    Created on      : 3:02 AM Friday, October 01, 2010
    Last Modified   : 5:53 AM Saturday, October 02, 2010, February 24, 2015 
    Author          : Saurabh Kumar

*  Copyright (c) 2010 - 2011 SMVDU, Katra.
*  Copyright (c) 2014 - 2017 ETRG, IITK.
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
*  Contributors: Members of ERP Team @ SMVDU, Katra, IITKanpur
*  Last Modified : January,2017, Om Prakash (omprakashkgp@gmail.com)
*
--%>



<%@ taglib prefix="f" uri="http://java.sun.com/jsf/core" %>
<%@ taglib prefix="h" uri="http://java.sun.com/jsf/html" %>
<%@ taglib uri="http://richfaces.org/a4j" prefix="a4j" %>
<%@ taglib uri="http://richfaces.org/rich" prefix="rich" %>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Employee help doc</title>
        <link rel="stylesheet" type="text/css" href="css/reset.css"/>
        <link rel="stylesheet" type="text/css" href="css/subpage.css"/>
        <link rel="stylesheet" type="text/css" href="css/components.css"/>
        <link rel="stylesheet" type="text/css" href="css/Form.css"/>

    </head>
    <f:view>
        <body class="subpage" id="">
            <div class="container_form">
                <rich:panel id="addempl" header="Add New Employee Details" styleClass="form" style="width:auto;">
                  <div align="right" >
                    <h2><font color="blue"> Before Registration you have to update SMTP Configuration.</font></h2> 
                  <h:form>
                  <a4j:commandLink id="updfile" ajaxSingle="true"  onclick="Richfaces.showModalPanel('dnl');">
                  <rich:toolTip value="Click for upload csv file" for="updfile"/>  
                  <h:graphicImage value="/img/uploadcsv.jpg" alt="Upload Employee List" /> 
                  </a4j:commandLink> &nbsp;
                  <%--
                  <a4j:commandLink id="viewfile" ajaxSingle="true"  action="#{EmployeeBean.callpage}">
                  <h:graphicImage value="/img/document.png" alt="Uploaded Employee List" /> 
                  <rich:toolTip value="Click for view uploaded file" for="viewfile"/>
                  </a4j:commandLink>&nbsp;--%> 
                  <a4j:commandLink id="hlp"ajaxSingle="true"  onclick="Richfaces.showModalPanel('hnl');">
                  <h:graphicImage value="/img/help-icon.png" alt="Help" /> 
                  <rich:toolTip value="Click for help" for="hlp"/>
                  </a4j:commandLink>
                  </h:form>
                 </div>
                
               
                 <rich:separator  style="width:100%;" /><br/>
                    <rich:messages  >
                        <f:facet name="infoMarker">
                            <h:graphicImage url="/img/success.png"/>
                        </f:facet>
                        <f:facet name="errorMarker">
                            <h:graphicImage url="/img/err.png"/>
                        </f:facet>
                    </rich:messages>
                    <h:form  id="profileForm">
                        <a4j:region id="empForm">
                            <h:panelGrid columns="3" columnClasses="label,field">
                                <h:panelGrid columns="3" columnClasses="label,field">
                                    <h:outputText value=""/>
                                    <h:column><h:outputText styleClass="Label" value="Employee Code "/>
                                    <h:outputText value="*" style="color:red;"/></h:column>
                                    <h:inputText id="empCode" styleClass="fields" required="true"
                                                 requiredMessage="Please Enter Employee Code"  value="#{EmployeeBean.code}"> 
                                        <f:validateLength  minimum="2" maximum="10"/>
                                    </h:inputText>
                                    <h:message styleClass="error" for="empCode" tooltip="true"/>
                                    
                                      <h:column><h:outputText value="Title " />
                                    <h:outputText value="*" style="color:red;"/></h:column>
                                    <h:selectOneMenu value="#{EmployeeBean.title}">
                                        <f:selectItem itemLabel="Prof." itemValue="Prof."/>
                                        <f:selectItem itemLabel="Doctor" itemValue="Doctor"/>
                                        <f:selectItem itemLabel="Mr." itemValue="Mr."/>
                                        <f:selectItem itemLabel="Mrs." itemValue="Mrs."/>
                                    </h:selectOneMenu>
                                    <h:message styleClass="error" for="empCode" tooltip="true"/>
                                    <h:column><h:outputText styleClass="Label" value="Employee Name " />
                                    <h:outputText value="*" style="color:red;"/></h:column>
                                    <h:inputText id="empName" required="true"
                                                 requiredMessage="Enter Employee's Name."
                                                 styleClass="fields" value="#{EmployeeBean.name}" validator="#{allValidator.validateUserName}">
                                        <rich:ajaxValidator event="onblur"></rich:ajaxValidator>
                                    </h:inputText>
                                    <h:message styleClass="error" for="empName" tooltip="Enter Employee's Name."/>
                                    <h:outputText styleClass="Label" value="Father's Name " />
                                    <h:inputText id="empfName"
                                                 styleClass="fields" value="#{EmployeeBean.fatherName}">
                                        <f:validateLength  maximum="70"/>
                                    </h:inputText>
                                    <h:message styleClass="error" for="empfName" tooltip="Enter Employee's Father Name."/>
                                    
                                    <h:column><h:outputText styleClass="Label" value="Gender " />
                                    <h:outputText value="*" style="color:red;"/></h:column>
                                    <h:selectOneMenu  styleClass="combo" id="empGender" value="#{EmployeeBean.tgender}">
                                            <f:selectItem itemValue="1" itemLabel="Female"/>
                                            <f:selectItem itemValue="2" itemLabel="Male" />
                                            <f:selectItem itemValue="3" itemLabel="Transgender" />
                                    </h:selectOneMenu>
                                    <%--  <h:selectOneMenu  styleClass="combo" id="empGender" value="#{EmployeeBean.male}">
                                        <f:selectItem itemValue="true" itemLabel="Male"/>
                                        <f:selectItem itemValue="false" itemLabel="Female"/>
                                      
                                    </h:selectOneMenu> --%>
                                    <h:message for="empGender" tooltip=" Enter Employee's Gender."/>
                                    <%-- ==================Add Employee Category-----start------- --%>
                                    
                                    <h:column><h:outputText styleClass="Label" value="Entitlement Category" />
                                    <h:outputText value="*" style="color:red;"/></h:column>
                                    <h:selectOneMenu  styleClass="combo" id="entileCat"  value="#{EmployeeBean.entitledCategory}">
                                        <f:selectItem itemValue="General" itemLabel="General"/>
                                        <f:selectItem itemValue="Handicap" itemLabel="Handicap"/>
                                    </h:selectOneMenu>
                                    <h:message for="entileCat" tooltip="Entitlement Category . Select From Choices"/>
                                        
                                    <h:column><h:outputText styleClass="Label" value="Status of Employee "/>
                                    <h:outputText value="*" style="color:red;"/></h:column>
                                    <h:selectOneMenu  styleClass="combo" id="empStat"  value="#{EmployeeBean.employeeStatus}">
                                        <f:selectItem itemValue="Married" itemLabel="Married"/>
                                        <f:selectItem itemValue="Single" itemLabel="Single"/>
                                    </h:selectOneMenu>
                                    <h:message for="empStat" tooltip="Status of Employee . Select From Choices"/>
                                    
                                    <%-- ============== Add Employee Category--end---------- --%>
                                                                        
                                    <h:column><h:outputText styleClass="Label" value="Employee Type "/>
                                    <h:outputText value="*" style="color:red;"/></h:column>
                                    <h:selectOneMenu  styleClass="combo" id="empType"  value="#{EmployeeBean.type}">
                                        <f:selectItems value="#{EmployeeTypeBean.items}"/>
                                    </h:selectOneMenu>
                                    <h:message for="empType" tooltip="Employee Type. Select From Choices"/>
                                    
                                    <h:column><h:outputText styleClass="Label" value="Pay Band "/>
                                    <h:outputText value="*" style="color:red;"/></h:column>
                                    <h:selectOneMenu styleClass="combo"  id="empGrade"  value="#{EmployeeBean.grade}">
                                        <f:selectItems value="#{SalaryGradeBean.grades}"/>
                                    </h:selectOneMenu>
                                    <h:message for="empGrade"  tooltip="*"/>
                                    <h:column><h:outputText value="Current Basic "/>
                                    <h:outputText value="*" style="color:red;"/></h:column>
                                    <h:inputText id="empBasic" requiredMessage="Basic Is must.Input Numbers without decimal" validatorMessage="Input Numbers without decimal " value="#{EmployeeBean.currentBasic}"/>
                                    <h:message for="empBasic"  tooltip="*"/>
                                    
                                    <%-- new field Add=================Salary Department Code-------start---------%>
                                    
                                    <h:column><h:outputText styleClass="Label" value=" Salary Department "/>
                                    <h:outputText value="*" style="color:red;"/></h:column>
                                    <h:selectOneMenu styleClass="combo" id="salDept"   value="#{EmployeeBean.saldept}">
                                        <f:selectItems value="#{DepartmentBean.arrayAsItem}"/>
                                    </h:selectOneMenu>
                                    <h:message for="salDept" tooltip="Salary Department Code. Select From Choices (Department Code from which salary issued)*"/>
                                   
                                    <%-- new field Add ===================Salary Department Code--============end========== --%>
                                    
                                    <h:column><h:outputText styleClass="Label" value="Current Department"/>
                                    <h:outputText value="*" style="color:red;"/></h:column>
                                    <h:selectOneMenu styleClass="combo" id="empDept"   value="#{EmployeeBean.dept}">
                                        <f:selectItems value="#{DepartmentBean.arrayAsItem}"/>
                                    </h:selectOneMenu>
                                    <h:message for="empDept" tooltip="Employee Department. Select From Choices*"/>
                                    
                                    <h:column><h:outputText styleClass="Label" value="Current Designation"/>
                                    <h:outputText value="*" style="color:red;"/></h:column>
                                    <h:selectOneMenu  styleClass="combo"  id="empDesig"  value="#{EmployeeBean.desig}">
                                        <f:selectItems value="#{DesignationBean.arrayAsItem}"/>
                                    </h:selectOneMenu>
                                    <h:message for="empDesig" tooltip="Employee Designation. Select From Choices"/>
                                    
                                    <h:column><h:outputText styleClass="Label" value="Date of Joining "/>
                                    <h:outputText value="*" style="color:red;"/></h:column>
                                    <rich:calendar enableManualInput="false" converter="dateConverter"
                                                   showWeekDaysBar="false" showFooter="false" styleClass="special"
                                                   datePattern="yyyy-MM-dd" id="empDoj" popup="true" required="true"
                                                   requiredMessage="*Enter Date Of Joining as yyyy-mm-dd" value="#{EmployeeBean.doj}"/>
                                    <h:message styleClass="error" for="empDoj" tooltip="*"/>
                                    
                                    <h:outputText styleClass="Label" value="PAN No."/>
                                    <h:inputText id="empPan" styleClass="fields" value="#{EmployeeBean.panNo}"/>
                                    <h:message for="empPan" tooltip="*"/>
                                    <h:outputText styleClass="Label" value="PF. Account No."/>
                                    <h:inputText id="empPf" styleClass="fields" value="#{EmployeeBean.pfAccNo}"/>
                                    <h:message for="empPf" tooltip="*"/>
                                    <a4j:status for="empForm" />
                                    </h:panelGrid>
                                    <h:panelGrid columns="3" columnClasses="label,field">
                                   
                                    <h:column><h:outputText styleClass="Label" value="Date of Birth"/>
                                    <h:outputText value="*" style="color:red;"/></h:column>
                                    <rich:calendar enableManualInput="false" converter="dateConverter" showWeekDaysBar="false"
                                                   showFooter="false" styleClass="special"
                                                   datePattern="yyyy-MM-dd" id="empDob" popup="true"
                                                   required="true"   requiredMessage="*Enter Date Of Birth as yyyy-mm-dd"
                                                   value="#{EmployeeBean.dob}">
                                    </rich:calendar>
                                    <h:message styleClass="error" for="empDob" tooltip="*"/>
                                    <%-- ================= New Field Added According to Nysa ==start============= --%>
                                    
                                    <h:outputText value=" GPF Number"/>
                                    <h:inputText id="empgpf" value="#{EmployeeBean.gpfNo}" />
                                    <h:message styleClass="error"  for="empgpf" tooltip="Enter Employee GPF Number( Employee Eligible if)*"/>
                                    
                                    <h:outputText value="NPS Number"/>
                                    <h:inputText id="empNps" value="#{EmployeeBean.dpsNo}" />
                                    <h:message styleClass="error"  for="empDpsNps" tooltip="Enter Employee DPS/NPS Number( Employee Eligible if)*"/>
                                    
                                    <h:outputText value=" DPS Number"/>
                                    <h:inputText id="empDps" value="#{EmployeeBean.dpsNo}" />
                                    <h:message styleClass="error"  for="empDpsNps" tooltip="Enter Employee DPS/NPS Number( Employee Eligible if)*"/>
                                                                        
                                                                                               
                                    <h:column><h:outputText value="Working Type"/>
                                    <h:outputText value="*" style="color:red;"/></h:column>
                                    <h:selectOneMenu id="worType" value="#{EmployeeBean.workingType}">
                                        <f:selectItem itemLabel="Teaching" itemValue="Teaching"/>
                                        <f:selectItem itemLabel="NonTeaching" itemValue="NonTeaching"/>
                                    </h:selectOneMenu>
                                    <h:message for="worType" tooltip="Employee Working Type. Select From Choices*"/>
                                    
                                    <h:outputText value="House Type"/>
                                    <h:inputText id="empHtype" value="#{EmployeeBean.houseType}" />
                                    <h:message styleClass="error"  for="empHtype" tooltip="Employee House Type (Type of House alloted if any)*"/>
                                    
                                    <h:outputText value=" House Number"/>
                                    <h:inputText id="emphouseNo" value="#{EmployeeBean.houseNo}" />
                                    <h:message styleClass="error"  for="emphouseNo" tooltip="Employee House Number (H.No., if House provided by university)*"/>
                                    
                                    <h:outputText value=" ECR Number"/>
                                    <h:inputText id="empEcr" value="#{EmployeeBean.ecrNo}" />
                                    <h:message styleClass="error"  for="empEcr" tooltip="Enter Employee ECR Number( ECR Number of Employee)*"/>
                                    
                                    <h:outputText value=" Page Number"/>
                                    <h:inputText id="pageNo" value="#{EmployeeBean.ecrPageNo}" />
                                    <h:message styleClass="error"  for="pageNo" tooltip="Page Number of the ECR book.)*"/>
                                                     
                                    <h:column><h:outputText styleClass="Label" value="Joining Department"/>
                                    <h:outputText value="*" style="color:red;"/></h:column>
                                    <h:selectOneMenu styleClass="combo" id="joiningDept"   value="#{EmployeeBean.joindept}">
                                        <f:selectItems value="#{DepartmentBean.arrayAsItem}"/>
                                    </h:selectOneMenu>
                                    <h:message for="joiningDept" tooltip="Department at the time of joining of Employee *"/>
                                     
                                    <h:outputText styleClass="Label" value="Posting Id"/>
                                    <h:inputText id="postingId" value="#{EmployeeBean.postingId}" />
                                    <h:message for="postingId" tooltip="Posting Department of Employee *"/>
                                                                     
                                    
                                                                        
                                    <h:outputText styleClass="Label" value="LIC Policy"/>
                                    <h:inputText id="licPNo" value="#{EmployeeBean.policyNo}" />
                                    <h:message for="licPNo" tooltip="Policy Number Of LIC *"/>
                                    
                                    <h:outputText styleClass="Label" value="Date of Acceptance (LIC Policy)"/>
                                    <rich:calendar enableManualInput="false" converter="dateConverter"
                                                   showWeekDaysBar="false" showFooter="false" styleClass="special"
                                                   datePattern="yyyy-MM-dd" id="emplicDoa" popup="true" 
                                                   requiredMessage="*Enter Date Of Acceptance as yyyy-mm-dd" value="#{EmployeeBean.doAcceptance}"/>
                                    <h:message styleClass="error" for="emplicDoa" tooltip="*"/>
                                    
                                    <h:outputText styleClass="Label" value="Date of Maturity (LIC Policy)"/>
                                    <rich:calendar enableManualInput="false" converter="dateConverter"
                                                   showWeekDaysBar="false" showFooter="false" styleClass="special"
                                                   datePattern="yyyy-MM-dd" id="emplicDom" popup="true" 
                                                   requiredMessage="*Enter Date Of Maturity as yyyy-mm-dd" value="#{EmployeeBean.doMaturity}"/>
                                    <h:message styleClass="error" for="emplicDom" tooltip="*"/>
                                    
                                    <h:outputText styleClass="Label" value="GI Policy"/>
                                    <h:inputText id="giPNo" value="#{EmployeeBean.policyNo}" />
                                    <h:message for="giPNo" tooltip="Policy Number Of GI *"/>
                                    
                                    <h:outputText styleClass="Label" value="Date of Acceptance (GI Policy)"/>
                                    <rich:calendar enableManualInput="false" converter="dateConverter"
                                                   showWeekDaysBar="false" showFooter="false" styleClass="special"
                                                   datePattern="yyyy-MM-dd" id="empgiDoa" popup="true" 
                                                   requiredMessage="*Enter Date Of Acceptance as yyyy-mm-dd" value="#{EmployeeBean.doAcceptance}"/>
                                    <h:message styleClass="error" for="empgicDoa" tooltip="*"/>
                                    
                                    <h:outputText styleClass="Label" value="Date of Maturity (GI Policy)"/>
                                    <rich:calendar enableManualInput="false" converter="dateConverter"
                                                   showWeekDaysBar="false" showFooter="false" styleClass="special"
                                                   datePattern="yyyy-MM-dd" id="empgicDom" popup="true" 
                                                   requiredMessage="*Enter Date Of Maturity as yyyy-mm-dd" value="#{EmployeeBean.doMaturity}"/>
                                    <h:message styleClass="error" for="empgicDom" tooltip="*"/>
                                   <%-- </h:panelGrid>
                                     <h:panelGrid columns="3">--%>
                                   </h:panelGrid>
                                    <h:panelGrid columns="3" columnClasses="label,field">
                                    <h:outputText styleClass="Label" value="Date of Next Increment"/>
                                    <rich:calendar enableManualInput="false" converter="dateConverter"
                                                   showWeekDaysBar="false" showFooter="false" styleClass="special"
                                                   datePattern="yyyy-MM-dd" id="empNIncDate" popup="true" 
                                                   requiredMessage="*Enter Date Of Next Increment as yyyy-mm-dd" value="#{EmployeeBean.doNextIncrement}"/>
                                    <h:message styleClass="error" for="empNIncDate" tooltip="*"/>
                                    
                                    
                                    <h:outputText styleClass="Label" value="Probation Date"/>
                                    <rich:calendar enableManualInput="false" converter="dateConverter"
                                                   showWeekDaysBar="false" showFooter="false" styleClass="special"
                                                   datePattern="yyyy-MM-dd" id="empdop" popup="true" 
                                                   requiredMessage="*Enter Date Of Probation period as yyyy-mm-dd" value="#{EmployeeBean.probationDate}"/>
                                    <h:message styleClass="error" for="empdop" tooltip="*"/>
                                    
                                    
                                    <h:outputText styleClass="Label" value="Confirmation Date"/>
                                    <rich:calendar enableManualInput="false" converter="dateConverter"
                                                   showWeekDaysBar="false" showFooter="false" styleClass="special"
                                                   datePattern="yyyy-MM-dd" id="empdoc" popup="true" 
                                                   requiredMessage="*Enter Date Of Confirmation as permanent employee as yyyy-mm-dd" value="#{EmployeeBean.confirmationDate}"/>
                                    <h:message styleClass="error" for="empdoc" tooltip="*"/>
                                    
                                    <h:outputText styleClass="Label" value="Extention Date"/>
                                    <rich:calendar enableManualInput="false" converter="dateConverter"
                                                   showWeekDaysBar="false" showFooter="false" styleClass="special"
                                                   datePattern="yyyy-MM-dd" id="empdoext" popup="true" 
                                                   requiredMessage="* Enter Date Of Extention as yyyy-mm-dd" value="#{EmployeeBean.extentionDate}"/>
                                    <h:message styleClass="error" for="empdoext" tooltip="*"/>
                                    
                                    
                                    <h:column><h:outputText value="Joined As"/>
                                    <h:outputText value="*" style="color:red;"/></h:column>
                                    <h:selectOneMenu  styleClass="combo"  id="empjoinDesig"  value="#{EmployeeBean.joindesig}">
                                        <f:selectItems value="#{DesignationBean.arrayAsItem}"/>
                                    </h:selectOneMenu>
                                    <h:message styleClass="error"  for="empjoinDesig" tooltip="Employee Designation (Designation at the time of joining)*"/>
                                     <%--</h:panelGrid>
                                     <h:panelGrid columns="3"> --%>                          
                                    <%-- ================ New Field Added According to Nysa ======end ======================--%>
                                    
                                    <h:outputText value="Year of Passing"/>
                                    <h:inputText id="yop" value="#{EmployeeBean.yearOfPassing}" />
                                    <h:message for="qual"/>
                                    <h:outputText value="Previous Employer"/>
                                    <h:inputText id="prev" value="#{EmployeeBean.previousEmployer}" />
                                    <h:message for="prev"/>
                                    <h:outputText value="Qualification"/>
                                    <h:inputText id="qual"  value="#{EmployeeBean.qualification}"/>
                                    <h:message for="qual"/>
                                    <h:outputText value="Experience"/>
                                    <h:inputText id="exp" value="#{EmployeeBean.experience}" />
                                    <h:message for="exp"/>
                                    <h:column><h:outputText styleClass="Label" value="Phone/Mobile"/>
                                    <h:outputText value="*" style="color:red;"/></h:column>
                                    <h:inputText id="empPhone" maxlength="11" required="true" requiredMessage="Mobile is Compulsory"  styleClass="fields" value="#{EmployeeBean.phone}"/>
                                    <h:message for="empPhone" tooltip="*"/>
                                    <h:column><h:outputText styleClass="Label" value="E-Mail ID"/>
                                    <h:outputText value="*" style="color:red;"/></h:column>
                                    <h:inputText id="empEmail" required="true" requiredMessage="E-mail is Compulsory" styleClass="fields" value="#{EmployeeBean.email}"/>
                                    <h:message for="empEmail" tooltip="*"/>
                                    <h:outputText styleClass="Label" value="Address"/>
                                    <h:inputTextarea id="address" styleClass="fields" value="#{EmployeeBean.address}"/>
                                    <h:message for="address" tooltip="*"/>
                                    <%--</h:panelGrid>
                                    <h:panelGrid columns="3">     
                                    <rich:separator />
                                    <rich:separator />
                                    <rich:separator />--%>
                                    <h:column><h:outputText styleClass="Label" value="Bank Acc. No."/>
                                    <h:outputText value="*" style="color:red;"/></h:column>
                                    <h:inputText id="ban" maxlength="11" required="true" requiredMessage="Account No. is Compulsory " styleClass="fields" value="#{EmployeeBean.bankAccNo}"/>
                                    <h:message for="ban" tooltip="*"/>
                                    <h:outputText styleClass="Label" value="Bank Name "/>
                                    <h:inputText value="#{EmployeeBean.bankIFSCcode}" styleClass="fields" id="bankInfo"/>
                                    <h:message for="address" tooltip="*"/>

                                    <h:column> <h:outputText styleClass="Label" value="Aadhaar No. "/>
                                    <h:outputText value="*" style="color:red;"/></h:column>
                                    <h:inputText maxlength="12" required="true" requiredMessage="Aadhaar No. is Compulsory " value="#{EmployeeBean.aadhaarNo}" styleClass="fields" id="aadharInfo">
                                            <f:convertNumber integerOnly="true" type="number" />
                                    </h:inputText> 
                                    <rich:toolTip value="Enter Aadhaar Number, character are not allowed " for="aadharInfo"/>
                                                                       
                                    <h:outputText styleClass="Lable" value="Category Type"/>
                                    <h:selectOneMenu  styleClass="categT" id="empCategT" value="#{EmployeeBean.categoryT}">
                                        <f:selectItem itemValue="00" itemLabel="Faculty"/>
                                        <f:selectItem itemValue="01" itemLabel="Student"/>
                                        <f:selectItem itemValue="02" itemLabel="Staff Clerical"/>
                                        <f:selectItem itemValue="03" itemLabel="Technical staff"/>
                                        <f:selectItem itemValue="04" itemLabel="Supplier"/>
                                        <f:selectItem itemValue="05" itemLabel="Admin staff"/>
                                        <f:selectItem itemValue="06" itemLabel="Contractor"/>
                                        <f:selectItem itemValue="07" itemLabel="Service provider"/>
                                        <f:selectItem itemValue="08" itemLabel="Alumni/Doner"/>
                                      
                                    </h:selectOneMenu>
                                   
                                    <rich:suggestionbox for="bankInfo" var="bif" fetchValue="#{bif.bankIFSCCode}" suggestionAction="#{EmployeeBean.getBankSuggestion}">
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
                                    <h:message for="bankInfo" tooltip="*"/>
                                </h:panelGrid>
                            </h:panelGrid>
                            <h:panelGroup>
                                <rich:separator/>
                            </h:panelGroup>
                            <h:panelGroup>
                                <a4j:commandButton styleClass="panel" id="success" reRender="report,addempl"  value="Save" action="#{EmployeeBean.save}"/>
                                <h:commandButton styleClass="panel" value="Reset" onclick="this.form.reset()"/>
                            </h:panelGroup> 
                        </a4j:region>
                    </h:form>
                    <rich:modalPanel  width="500" height="240" autosized="true" id="dnl">
                     <rich:messages>
                        <f:facet name="infoMarker">
                            <h:graphicImage url="/img/success.png"/>
                        </f:facet>
                        <f:facet name="errorMarker">
                            <h:graphicImage url="/img/err.png"/>
                        </f:facet>
                    </rich:messages>   
                    <%--file upload for departments---------------------- --%>
                    <f:facet name="controls">
                    <h:graphicImage value="/img/cls.png" style="cursor:pointer"
                                    onclick="Richfaces.hideModalPanel('dnl')" />
                    </f:facet>
                    <h:form>
                    <h:panelGrid columns="2" columnClasses="top,top">
                    <rich:fileUpload fileUploadListener="#{EmployeeBean.listener}"
                    maxFilesQuantity="#{FileUploadBean.uploadsAvailable}"
                    id="upload"
                    immediateUpload="#{FileUploadBean.autoUpload}"
                    acceptedTypes="csv" ontyperejected="alert('Only csv files are accepted');" allowFlash="#{FileUploadBean.useFlash}">
                   <%-- <a4j:support  event="onuploadcomplete"  reRender="info" oncomplete="#{rich:component('dnl')}.hide(); return false;"/>--%>
                   <a4j:support  event="onuploadcomplete"  reRender="info" />
                   </rich:fileUpload>
                   </h:panelGrid>
                    </h:form>
                
                <%---file upload END================================= --%>     
                </rich:modalPanel>
                 <rich:modalPanel id="hnl"  width="700" height="500" autosized="true">
                    <f:facet name="header">
                        <h:outputText value=" Employee Registration Help" />
                    </f:facet>
                    <f:facet name="controls">
                    <h:graphicImage value="/img/close1.png" style="cursor:pointer"
                                    onclick="Richfaces.hideModalPanel('hnl')" />
                    </f:facet>
                    <font size="2.0em">   
                    <p><b>Instruction for Add Employee Profile and Upload a csv file.</b></p>
                    <p><b>Add Employee Profile:</b></p> 
                    <p>In Employee menu select <b>Add profile</b> option,There is a form  which contain some fields.<br>
                       Fields which are madatory : Employee Code (Unique), Employee Name, Employee Gender,Entitlement Category, Employee Type, 
                        Pay Band, Current Basic, Salary Department, Current Department, Current Designation, Date of joining, Date of Birth,
                        Working Type, Joining Department, Joined As(designation),    E-Mail ID, Phone No.<br>
                        Now press the <b>Save Button</b> for Employee Registration.</p>
                    <p><b>Instruction for Upload a CSV File :</b></p>
                    1.Open LibreOffice Calc in ubuntu and Excel in windows.</p>
                    <p><b>Example :</b></p>
                    <p>Employee Code = 111 or 111ee<br>
                     Employee Name = Manorama Pal <br>Depatment Code = CS01<br>Designation Code = PM<br>Employee Type Code = CONT<br>
                     Phone No = 1234567890<br>Employee E-mail Id = xyz@gmail.com<br>Employee Date of Joining = 2014-12-09 <br>Employee Date of Birth = 1989-12-01
                     Salary Grade Name = PB-4<br>Current Basic = 10000<br>Entitlement Category = general or Handicap<br>Employee Status = Married or Single<br>
                     Working Type = Teaching or Non Teaching<br> Salary Department Code = AS01<br> Department Code(at the time of Joining) = CS01<br>
                     Designation Code (at the time of joining)= JT08</p>
                    <p>3. Save as csv format.</p>
                    <p>4. Now Click for upload csv file image and upload the csv file.</p>
                    <p><b> Note: Department, Designation, EmployeeType and Pay Band code  should be exist in this system.</b></p>
                    </font>
                </rich:modalPanel> 
                               
                </rich:panel>
            </f:view>
        </div>
    </body>
</html>
