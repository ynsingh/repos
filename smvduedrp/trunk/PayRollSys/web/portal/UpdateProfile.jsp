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
<f:view>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
            <title>Edit Employee</title>
            <link type="text/css" rel="stylesheet" href="../JQuery/themes/base/jquery-ui.css"/>
            <link rel="stylesheet" type="text/css" href="css/reset.css"/>
            <link rel="stylesheet" type="text/css" href="css/LoginPage.css" /> 
            <link rel="stylesheet" type="text/css" href="../css/layout.css"/>
        </head>
        <body>
            <div class="pageBody">
                <rich:messages>
                    <f:facet name="infoMarker">
                        <h:graphicImage url="/img/success.png"/>
                    </f:facet>
                    <f:facet name="errorMarker">
                        <h:graphicImage url="/img/err.png"/>
                    </f:facet>
                </rich:messages>   
                <rich:tabPanel style="border:none;width:100%">
                    <rich:tab label="Job Related Information">
                   <h:panelGrid  columns="9" style="margin-top:40px;width:100%;    ">
                 <h:outputText style="font-size:1.2em;font-weight:bold" styleClass="Label" value="Employee Code"/>
                 <h:outputText style="font-size:1.2em;font-weight:bold" styleClass="Label" value=":"/>
                 <h:outputText style="font-size:1.2em;margin-;margin-right:20px;" value="#{LoggedEmployee.profile.code}"/>
                 <h:outputText style="font-size:1.2em;font-weight:bold" styleClass="Label" value="Employee Name"/>
                 <h:outputText style="font-size:1.2em;font-weight:bold" styleClass="Label" value=":"/>
                 <h:outputText style="font-size:1.2em;margin-;margin-right:20px;" value="#{LoggedEmployee.profile.name}"/>
                 <h:outputText style="font-size:1.2em;font-weight:bold" styleClass="Label" value="Father's Name"/>
                 <h:outputText style="font-size:1.2em;font-weight:bold" styleClass="Label" value=":"/>
                 <h:outputText style="font-size:1.2em;margin-;margin-right:20px;" value="#{LoggedEmployee.profile.fatherName} "/>
                 <h:outputText style="font-size:1.2em;font-weight:bold" styleClass="Label" value="Basic Pay"/>
                 <h:outputText style="font-size:1.2em;font-weight:bold" styleClass="Label" value=":"/>
                 <h:outputText style="font-size:1.2em;margin-;margin-right:20px;" value="#{LoggedEmployee.profile.currentBasic}"/>
                 <h:outputText style="font-size:1.2em;font-weight:bold" styleClass="Label" value="Employee Type"/>
                 <h:outputText style="font-size:1.2em;font-weight:bold" styleClass="Label" value=":"/>
                 <h:outputText style="font-size:1.2em;margin-;margin-right:20px;" value="#{LoggedEmployee.profile.typeName}"/>
                 <h:outputText style="font-size:1.2em;font-weight:bold" styleClass="Label" value="Current Department"/>
                 <h:outputText style="font-size:1.2em;font-weight:bold" styleClass="Label" value=":"/>
                 <h:outputText style="font-size:1.2em;margin-;margin-right:20px;" value="#{LoggedEmployee.profile.deptName} "/>
                 <h:outputText style="font-size:1.2em;font-weight:bold" styleClass="Label" value=" Current Designation"/>
                 <h:outputText style="font-size:1.2em;font-weight:bold" styleClass="Label" value=":"/>
                 <h:outputText style="font-size:1.2em;margin-;margin-right:20px;" value="#{LoggedEmployee.profile.desigName}"/>
                 <h:outputText style="font-size:1.2em;font-weight:bold" styleClass="Label" value="Pay Band"/>
                 <h:outputText style="font-size:1.2em;font-weight:bold" styleClass="Label" value=":"/>
                 <h:outputText style="font-size:1.2em;margin-;margin-right:20px;" value="#{LoggedEmployee.payBand}"/>
                 <h:outputText style="font-size:1.2em;font-weight:bold" styleClass="Label" value="Salary Department"/>
                 <h:outputText style="font-size:1.2em;font-weight:bold" styleClass="Label" value=":"/>
                 <h:outputText style="font-size:1.2em;margin-;margin-right:20px;" value="#{LoggedEmployee.salDeptName}"/>
                 <h:outputText style="font-size:1.2em;font-weight:bold" styleClass="Label" value="Joined As (Designation)"/>
                 <h:outputText style="font-size:1.2em;font-weight:bold" styleClass="Label" value=":"/>
                 <h:outputText style="font-size:1.2em;margin-;margin-right:20px;" value="#{LoggedEmployee.joinDesigName} "/>
                 <h:outputText style="font-size:1.2em;font-weight:bold" styleClass="Label" value="Working Type"/>
                 <h:outputText style="font-size:1.2em;font-weight:bold" styleClass="Label" value=":"/>
                 <h:outputText style="font-size:1.2em;margin-;margin-right:20px;" value="#{LoggedEmployee.empSuptData.workingType}"/>
                 <h:outputText style="font-size:1.2em;font-weight:bold" styleClass="Label" value="Joining Department"/>
                 <h:outputText style="font-size:1.2em;font-weight:bold" styleClass="Label" value=":"/>
                 <h:outputText style="font-size:1.2em;margin-;margin-right:20px;" value="#{LoggedEmployee.joinDeptName}"/>
                 <h:outputText  style="font-size:1.2em;font-weight:bold" styleClass="Label" value="Date of Joining"/>
                 <h:outputText style="font-size:1.2em;font-weight:bold" styleClass="Label" value=":"/>
                 <h:outputText style="font-size:1.2em;margin-;margin-right:20px;" value="#{LoggedEmployee.profile.doj}"/>
                 <h:outputText style="font-size:1.2em;font-weight:bold" styleClass="Label" value="Probation Date"/>
                 <h:outputText style="font-size:1.2em;font-weight:bold" styleClass="Label" value=":"/>
                 <h:outputText style="font-size:1.2em;margin-;margin-right:20px;" value="#{LoggedEmployee.empSuptData.probationDate} "/>
                 <h:outputText style="font-size:1.2em;font-weight:bold" styleClass="Label" value="Confirmation Date"/>
                 <h:outputText style="font-size:1.2em;font-weight:bold" styleClass="Label" value=":"/>
                 <h:outputText style="font-size:1.2em;margin-;margin-right:20px;" value="#{LoggedEmployee.empSuptData.confirmationDate} "/>
                 <h:outputText style="font-size:1.2em;font-weight:bold" styleClass="Label" value="Extension Date"/>
                 <h:outputText style="font-size:1.2em;font-weight:bold" styleClass="Label" value=":"/>
                 <h:outputText style="font-size:1.2em;margin-;margin-right:20px;" value="#{LoggedEmployee.empSuptData.extentionDate} "/>
                 <h:outputText style="font-size:1.2em;font-weight:bold" styleClass="Label" value="Phone/Mobile"/>
                 <h:outputText style="font-size:1.2em;font-weight:bold" styleClass="Label" value=":"/>
                 <h:outputText style="font-size:1.2em;margin-;margin-right:20px;" value="#{LoggedEmployee.profile.phone}"/>
                 <h:outputText style="font-size:1.2em;font-weight:bold" styleClass="Label" value="E-Mail ID"/>
                 <h:outputText style="font-size:1.2em;font-weight:bold" styleClass="Label" value=":"/>
                 <h:outputText style="font-size:1.2em;margin-;margin-right:20px;" value="#{LoggedEmployee.profile.email} "/>
                 <h:outputText style="font-size:1.2em;font-weight:bold" styleClass="Label" value="Experience"/>
                 <h:outputText style="font-size:1.2em;font-weight:bold" styleClass="Label" value=":"/>
                 <h:outputText style="font-size:1.2em;margin-;margin-right:20px;" value="#{LoggedEmployee.profile.experience}"/>
                 <h:outputText style="font-size:1.2em;font-weight:bold" styleClass="Label" value="Bank Account No"/>
                 <h:outputText style="font-size:1.2em;font-weight:bold" styleClass="Label" value=":"/>
                 <h:outputText style="font-size:1.2em;margin-;margin-right:20px;" value="#{LoggedEmployee.profile.bankAccNo}"/>
                 <h:outputText style="font-size:1.2em;font-weight:bold" styleClass="Label" value="BankName"/>
                 <h:outputText style="font-size:1.2em;font-weight:bold" styleClass="Label" value=":"/>
                 <h:outputText style="font-size:1.2em;margin-;margin-right:20px;;" value="#{LoggedEmployee.profile.bankName}"/>
                 <h:outputText style="font-size:1.2em;font-weight:bold" styleClass="Label" value="PF. Account No"/>
                 <h:outputText style="font-size:1.2em;font-weight:bold" styleClass="Label" value=":"/>
                 <h:outputText style="font-size:1.2em;margin-;margin-right:20px;" value="#{LoggedEmployee.profile.pfAccNo}"/>
                 <h:outputText style="font-size:1.2em;font-weight:bold" styleClass="Label" value="PAN No"/>
                 <h:outputText style="font-size:1.2em;font-weight:bold" styleClass="Label" value=":"/>
                 <h:outputText style="font-size:1.2em;margin-;margin-right:20px;;" value="#{LoggedEmployee.profile.panNo}"/>
                 <h:outputText style="font-size:1.2em;font-weight:bold" styleClass="Label" value="Aadhaar No"/>
                 <h:outputText style="font-size:1.2em;font-weight:bold" styleClass="Label" value=":"/>
                 <h:outputText style="font-size:1.2em;margin-;margin-right:20px;;" value=""/>
                 <h:outputText style="font-size:1.2em;font-weight:bold" styleClass="Label" value="NPR No"/>
                 <h:outputText style="font-size:1.2em;font-weight:bold" styleClass="Label" value=":"/>
                 <h:outputText style="font-size:1.2em;margin-;margin-right:20px;;" value=""/>
                 <h:outputText style="font-size:1.2em;font-weight:bold" styleClass="Label" value="Date of Retirement /Term-End /Resignation"/>
                 <h:outputText style="font-size:1.2em;font-weight:bold" styleClass="Label" value=":"/>
                 <h:outputText style="font-size:1.2em;margin-;margin-right:20px;;" value="#{LoggedEmployee.profile.dateOfResig}"/>
                                            
                 </h:panelGrid>
                
             
         </rich:tab>    
         <rich:tab label="Personal Information">
             <h:panelGrid  columns="9" style="margin-top:40px;width:100%;">
                 <h:outputText style="font-size:1.2em;font-weight:bold" styleClass="Label" value="Date of Birth"/>
                 <h:outputText style="font-size:1.2em;font-weight:bold" styleClass="Label" value=":"/>
                 <h:outputText style="font-size:1.2em;margin-;margin-right:20px;" value="#{LoggedEmployee.profile.dob}"/>
                 <h:outputText style="font-size:1.2em;font-weight:bold" styleClass="Label" value="Gender"/>
                 <h:outputText style="font-size:1.2em;font-weight:bold" styleClass="Label" value=":"/>
                 <h:outputText style="font-size:1.2em;margin-;margin-right:20px;" value="#{LoggedEmployee.profile.genderName}"/>
                 <h:outputText style="font-size:1.2em;font-weight:bold" styleClass="Label" value="Highest Qualification"/>
                 <h:outputText style="font-size:1.2em;font-weight:bold" styleClass="Label" value=":"/>
                 <h:outputText style="font-size:1.2em;margin-;margin-right:20px;" value="#{LoggedEmployee.profile.qualification} "/>
                 <h:outputText style="font-size:1.2em;font-weight:bold" styleClass="Label" value="Martial Status"/>
                 <h:outputText style="font-size:1.2em;font-weight:bold" styleClass="Label" value=":"/>
                 <h:outputText style="font-size:1.2em;margin-;margin-right:20px;" value="#{LoggedEmployee.empSuptData.employeeStatus}"/>
                 <h:outputText style="font-size:1.2em;font-weight:bold" styleClass="Label" value="Category"/>
                 <h:outputText style="font-size:1.2em;font-weight:bold" styleClass="Label" value=":"/>
                 <h:outputText style="font-size:1.2em;margin-;margin-right:20px;" value="#{LoggedEmployee.empSuptData.entitledCategory}"/>
                 <h:outputText style="font-size:1.2em;font-weight:bold" styleClass="Label" value="Nationality"/>
                 <h:outputText style="font-size:1.2em;font-weight:bold" styleClass="Label" value=":"/>
                 <h:outputText style="font-size:1.2em;margin-;margin-right:20px;" value=""/>
                 <h:outputText  style="font-size:1.2em;font-weight:bold" styleClass="Label" value="Religion"/>
                 <h:outputText style="font-size:1.2em;font-weight:bold" styleClass="Label" value=":"/>
                 <h:outputText style="font-size:1.2em;margin-;margin-right:20px;" value=""/>
                 <h:outputText style="font-size:1.2em;font-weight:bold" styleClass="Label" value="Blood Group"/>
                 <h:outputText style="font-size:1.2em;font-weight:bold" styleClass="Label" value=":"/>
                 <h:outputText style="font-size:1.2em;margin-;margin-right:20px;" value=""/>
                 <h:outputText style="font-size:1.2em;font-weight:bold" styleClass="Label" value="Home Town"/>
                 <h:outputText style="font-size:1.2em;font-weight:bold" styleClass="Label" value=":"/>
                 <h:outputText style="font-size:1.2em;margin-;margin-right:20px;" value=""/>
                 </h:panelGrid>
         </rich:tab>    
         <rich:tab label="Communication Address" id="commadd">
             <a4j:commandLink styleClass="no-decor" ajaxSingle="true" value="Edit" reRender="editGrid" onclick="#{rich:component('pnl')}.show()">
             <f:setPropertyActionListener value="" target="#{LoggedEmployee.profile}" />
             </a4j:commandLink>
             <h:panelGrid  columns="9" style="margin-top:40px;width:100%;">
                 <h:outputText style="font-size:1.2em;font-weight:bold" styleClass="Label" value=" Residence Type"/>
                 <h:outputText style="font-size:1.2em;font-weight:bold" styleClass="Label" value=":"/>
                 <h:outputText style="font-size:1.2em;margin-;margin-right:20px;" value="#{LoggedEmployee.empSuptData.houseType}"/>
                 <h:outputText style="font-size:1.2em;font-weight:bold" styleClass="Label" value=" House Number"/>
                 <h:outputText style="font-size:1.2em;font-weight:bold" styleClass="Label" value=":"/>
                 <h:outputText style="font-size:1.2em;margin-;margin-right:20px;" value="#{LoggedEmployee.empSuptData.houseNo}"/>
                 <h:outputText style="font-size:1.2em;font-weight:bold" styleClass="Label" value="Mobile Number"/>
                 <h:outputText style="font-size:1.2em;font-weight:bold" styleClass="Label" value=":"/>
                 <h:outputText style="font-size:1.2em;margin-;margin-right:20px;" value="#{LoggedEmployee.profile.phone}"/>
                 <h:outputText style="font-size:1.2em;font-weight:bold" styleClass="Label" value="Current Address"/>
                 <h:outputText style="font-size:1.2em;font-weight:bold" styleClass="Label" value=":"/>
                 <h:outputText style="font-size:1.2em;margin-;margin-right:20px;" value="#{LoggedEmployee.profile.address}"/>
                 <h:outputText style="font-size:1.2em;font-weight:bold" styleClass="Label" value="Permanent Address"/>
                 <h:outputText style="font-size:1.2em;font-weight:bold" styleClass="Label" value=":"/>
                 <h:outputText style="font-size:1.2em;margin-;margin-right:20px;" value="#{LoggedEmployee.profile.address}"/>
                 <h:outputText style="font-size:1.2em;font-weight:bold" styleClass="Label" value="Email Id"/>
                 <h:outputText style="font-size:1.2em;font-weight:bold" styleClass="Label" value=":"/>
                 <h:outputText style="font-size:1.2em;margin-;margin-right:20px;" value="#{LoggedEmployee.profile.email} "/>
                                 
                 </h:panelGrid>
         </rich:tab>    
         <rich:tab label="Other Information">
             <h:panelGrid  columns="9" style="margin-top:40px;width:100%;">
                 <h:outputText style="font-size:1.2em;font-weight:bold" styleClass="Label" value="GPF Number"/>
                 <h:outputText style="font-size:1.2em;font-weight:bold" styleClass="Label" value=":"/>
                 <h:outputText style="font-size:1.2em;margin-;margin-right:20px;" value="#{LoggedEmployee.empSuptData.gpfNo}"/>
                 <h:outputText style="font-size:1.2em;font-weight:bold" styleClass="Label" value="DPS Number"/>
                 <h:outputText style="font-size:1.2em;font-weight:bold" styleClass="Label" value=":"/>
                 <h:outputText style="font-size:1.2em;margin-;margin-right:20px;" value="#{LoggedEmployee.empSuptData.dpsNo}"/>
                 <h:outputText style="font-size:1.2em;font-weight:bold" styleClass="Label" value="NPS Number"/>
                 <h:outputText style="font-size:1.2em;font-weight:bold" styleClass="Label" value=":"/>
                 <h:outputText style="font-size:1.2em;margin-;margin-right:20px;" value="#{LoggedEmployee.empSuptData.npsNo}"/>
                 <h:outputText style="font-size:1.2em;font-weight:bold" styleClass="Label" value="ECR Number"/>
                 <h:outputText style="font-size:1.2em;font-weight:bold" styleClass="Label" value=":"/>
                 <h:outputText style="font-size:1.2em;margin-;margin-right:20px;" value="#{LoggedEmployee.empSuptData.ecrNo}"/>
                 <h:outputText style="font-size:1.2em;font-weight:bold" styleClass="Label" value="Page Number"/>
                 <h:outputText style="font-size:1.2em;font-weight:bold" styleClass="Label" value=":"/>
                 <h:outputText style="font-size:1.2em;margin-;margin-right:20px;" value="#{LoggedEmployee.empSuptData.ecrPageNo} "/>
                 <h:outputText style="font-size:1.2em;font-weight:bold" styleClass="Label" value="Posting Id"/>
                 <h:outputText style="font-size:1.2em;font-weight:bold" styleClass="Label" value=":"/>
                 <h:outputText style="font-size:1.2em;margin-;margin-right:20px;" value="#{LoggedEmployee.empSuptData.postingId}"/>
                 <h:outputText style="font-size:1.2em;font-weight:bold" styleClass="Label" value="LIC Policy No"/>
                 <h:outputText style="font-size:1.2em;font-weight:bold" styleClass="Label" value=":"/>
                 <h:outputText style="font-size:1.2em;margin-;margin-right:20px;" value="#{LoggedEmployee.empSuptData.policyNo}"/>
                 <h:outputText style="font-size:1.2em;font-weight:bold" styleClass="Label" value="Date of Acceptance(LIC)"/>
                 <h:outputText style="font-size:1.2em;font-weight:bold" styleClass="Label" value=":"/>
                 <h:outputText style="font-size:1.2em;margin-;margin-right:20px;" value="#{LoggedEmployee.empSuptData.doAcceptance} "/>
                 <h:outputText style="font-size:1.2em;font-weight:bold" styleClass="Label" value="Date of Maturity (LIC)"/>
                 <h:outputText style="font-size:1.2em;font-weight:bold" styleClass="Label" value=":"/>
                 <h:outputText style="font-size:1.2em;margin-;margin-right:20px;" value="#{LoggedEmployee.empSuptData.doMaturity} "/>
                 <h:outputText style="font-size:1.2em;font-weight:bold" styleClass="Label" value="GI Policy"/>
                 <h:outputText style="font-size:1.2em;font-weight:bold" styleClass="Label" value=":"/>
                 <h:outputText style="font-size:1.2em;margin-;margin-right:20px;" value="#{LoggedEmployee.empSuptData.policyNo} "/>
                 <h:outputText style="font-size:1.2em;font-weight:bold" styleClass="Label" value="Date of Acceptance (GI)"/>
                 <h:outputText style="font-size:1.2em;font-weight:bold" styleClass="Label" value=":"/>
                 <h:outputText style="font-size:1.2em;margin-;margin-right:20px;" value="#{LoggedEmployee.empSuptData.doAcceptance} "/>
                 <h:outputText style="font-size:1.2em;font-weight:bold" styleClass="Label" value="Date of Maturity (GI)"/>
                 <h:outputText style="font-size:1.2em;font-weight:bold" styleClass="Label" value=":"/>
                 <h:outputText style="font-size:1.2em;margin-;margin-right:20px;" value="#{LoggedEmployee.empSuptData.doMaturity} "/>
                 </h:panelGrid>
         </rich:tab>   
             
        </rich:tabPanel>  
        <rich:modalPanel id="pnl"autosized="true"  domElementAttachment="parent" width="400" height="170">
            <f:facet name="header">
                <h:panelGroup>
                    <h:outputText value="Edit Communication Information"></h:outputText>
                </h:panelGroup>
            </f:facet>
            <f:facet name="controls">
                <h:panelGroup>
                    <h:graphicImage value="/img/close1.png" styleClass="hidelink" id="hidelink"/>
                    <rich:componentControl for="pnl" attachTo="hidelink" operation="hide" event="onclick"/>
                </h:panelGroup>
            </f:facet>
            <h:form id="commInfo">
                <h:panelGrid style="padding-left:10%;padding-right: 10%;">
                <h:outputText value="Current Address "/><br/>
                <h:panelGrid columns="2" >
                    <h:outputText value="Address"/>
                    <h:inputText  id="cadd" size="30"  value="#{LoggedEmployee.address}"/>
                    <h:outputText value="City"/>
                    <h:inputText  id="ccity" size="30" styleClass="fields" value="#{LoggedEmployee.city}"/>
                    <h:outputText value="State"/>
                    <h:inputText id="cstate" size="30" styleClass="fields" value="#{LoggedEmployee.state}"/>
               </h:panelGrid>
               <br/>
               <rich:separator/><br/>
               <h:panelGrid columns="2" >
                    <h:outputText value="Mobile Number"/>
                    <h:inputText size="30" styleClass="fields" value="#{LoggedEmployee.profile.phone}"/>
               </h:panelGrid>
               <br/>
               <rich:separator/>
               </h:panelGrid>
               <h:panelGrid columns="2" style="padding-left:10%;padding-right: 10%;">
                   <a4j:commandButton value="Submit" action="#{LoggedEmployee.updateEmpCommInfo}" reRender="commadd" oncomplete="#{rich:component('pnl')}.hide();"/>
               <a4j:commandButton value="Reset" type="reset"/>
               </h:panelGrid>
           </h:form>
         </rich:modalPanel>    

           </div>
        </body>
    </html>
</f:view>

