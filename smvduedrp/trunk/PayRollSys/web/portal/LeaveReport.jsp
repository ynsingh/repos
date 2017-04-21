<%-- 
    Document   : LeaveReport
    Created on : Jan 4, 2011, 2:09:17 AM
    Author     :  
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
*  Contributors: Members of ERP Team @ SMVDU, Katra
*
*  Modification : April 2017, Om Prakash (omprakashkgp@gmail.com), IITK

--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsf/core" %>
<%@ taglib prefix="h" uri="http://java.sun.com/jsf/html" %>
<%@ taglib uri="http://richfaces.org/a4j" prefix="a4j"%>
<%@ taglib uri="http://richfaces.org/rich" prefix="rich"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" type="text/css" href="portal/EmployeeLeave.css" />  
    </head>
    <body>
        <f:view>
            <%--  <rich:panel style="width:720px;" header="Employee's Leave Details">
            <h:panelGrid columns="2">
                <h:commandButton onclick="Richfaces.showModalPanel('pnl');" value="Add New"/>
            <rich:messages>
                       <f:facet name="infoMarker">
                            <h:graphicImage url="/img/success.png"/>
                       </f:facet>
                    </rich:messages>
            </h:panelGrid>
            
                <rich:dataTable style="width:700px;" value="#{EmployeeLeaveBean.singleLeaveData}" var="leave">
                <h:column>
                    <f:facet name="header">
                        <h:outputText value="Leave From"/>
                    </f:facet>
                    <h:outputText value="#{leave.dateFrom}"/>
                </h:column>
                <h:column>
                    <f:facet name="header">
                        <h:outputText value="Leave To"/>
                    </f:facet>
                    <h:outputText value="#{leave.dateTo}"/>
                </h:column>
                <h:column>--%>
            <%--    <f:facet name="header">
                        <h:outputText value="Total"/>
                    </f:facet>
                    <h:outputText value="#{leave.count}"/>
                </h:column>
            </rich:dataTable>
            </rich:panel>

            <rich:modalPanel id="pnl"> --%> 
        <rich:tabPanel style="border:none;width:100%">
            <rich:tab label="Apply for Leave">
              <h:form>
                  <div>
                <rich:messages>
                    <f:facet name="infoMarker">
                        <h:graphicImage url="/img/success.png"/>
                    </f:facet>
                </rich:messages>
                <h:panelGrid columns="3" columnClasses="label,field">
                    <h:outputText value="Employee Code"/>
                    <h:outputText value="#{EmployeeLeaveBean.empc}"/>
                    <br />
                      
                    <h:column><h:outputText  value="Employee Name "/>
                     
                    <h:outputText value="*" style="color:red;"/></h:column>
                    <h:inputText id="empName" styleClass="fields" readonly="true" value="#{LoggedEmployee.profile.name}">
                         <h:message for="savebutton"/>
                         <f:validateLength  minimum="2" maximum="10"/>
                    </h:inputText>
                    <br />
                    <h:column><h:outputText value="Reason for Leave "/>
                    <h:outputText value="*" style="color:red;"/></h:column>
                    <h:inputText id="rsn" styleClass="fields" required="true" requiredMessage="Please enter reason for leave."  value="#{EmployeeLeaveBean.reasonfleave}">
                      <f:validateLength  minimum="2" maximum="25"/>
                    </h:inputText>
                    <br />
                    <h:column><h:outputText value="Contact No. during Leave "/>
                    <h:outputText value="*" style="color:red;"/></h:column>
                    <h:inputText id="cntNo" styleClass="fields" required="true" requiredMessage="Please enter your contact no. during leave."  value="#{EmployeeLeaveBean.contractno}">
                        <f:validateLength  minimum="10" maximum="12"/>
                        <rich:toolTip> </rich:toolTip>
                    </h:inputText>
                    <br />
                    <h:column><h:outputText value="Leave Application Date "/>
                    <h:outputText value="*" style="color:red;"/></h:column>
                    <rich:calendar converter="dateConverter" showWeekDaysBar="false"
                              showFooter="false" styleClass="special"
                              datePattern="yyyy-MM-dd" id="empApp" popup="true"
                              required="true" requiredMessage="Please enter applied date." value="#{EmployeeLeaveBean.appliedDate}">
                    </rich:calendar>
                    <br />    
                    <h:column><h:outputText value="Leave From" />
                    <h:outputText value="*" style="color:red;"/></h:column>
                    <rich:calendar converter="dateConverter" showWeekDaysBar="false"
                              showFooter="false" styleClass="special"
                              datePattern="yyyy-MM-dd" id="empDob" popup="true"
                              required="true" requiredMessage="Please enter leave date from." value="#{EmployeeLeaveBean.dateFrom}">
                    </rich:calendar>
                    <br />
                    <h:column><h:outputText value="Leave To" />
                    <h:outputText value="*" style="color:red;"/></h:column>
                    <rich:calendar converter="dateConverter" showWeekDaysBar="false"
                              showFooter="false" styleClass="special"
                              datePattern="yyyy-MM-dd" id="empDoj" popup="true"
                              required="true" requiredMessage="Please enter leave date to." value="#{EmployeeLeaveBean.dateTo}">
                    </rich:calendar>
                    <br/>
                    <h:column>
                    <h:outputText value="Leave Type"/></h:column>
                    <h:selectOneMenu  id="leaveType" value="#{EmployeeLeaveBean.leaveTypeCode}" onchange="GetSelectedTextValue(this)">
                            <f:selectItems value="#{LeaveTypeBean.items}"/>
                    </h:selectOneMenu>
                    <br /> 
                    <h:column><h:outputText value="Reporting Officer "/>  
                        <h:outputText value="*" style="color:red;"/></h:column>
                        <h:inputText id="rptrgOffcr" styleClass="fields" required="true" requiredMessage="Please enter the name of reporting officer."  value="#{EmployeeLeaveBean.reportingoff}">
                        <f:validateLength  minimum="2" maximum="25"/>
                    </h:inputText> 
                      <br />
		    <h:column>
                         <h:outputText value="Covering Officer "/>
                    <h:outputText value="*" style="color:red;" /></h:column>
                    <h:inputText id="cvrngOffcr" styleClass="fields" required="true" requiredMessage="Please enter the name of covering officer."  value="#{EmployeeLeaveBean.coveringoff}">
                        <f:validateLength  minimum="2" maximum="25"/>
                    </h:inputText>
                    <br/> <br />
                  <h:panelGroup> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                  <a4j:commandButton id="afl" styleClass="panel" action="#{EmployeeLeaveBean.save}"  value="Save"/>
                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                    <h:commandButton styleClass="panel" value="Reset" onclick="this.form.reset()"/>
                  </h:panelGroup>
                </h:panelGrid>
                  </div>
               </h:form>
            </rich:tab>

            <rich:tab label="Leave Transaction Details">
               <h:form>
                  <h:panelGrid columns="3" columnClasses="label,field">
                     <h:column><h:outputText  value="Employee Name :   "/></h:column>
                     <h:outputText value="#{LoggedEmployee.profile.name}"/>
                     <br />
                     <h:outputText value="Leave Type"/>
                     <h:selectOneMenu  id="leaveType" value="#{EmployeeLeaveBean.leaveTypeCode}">
                        <f:selectItems value="#{LeaveTypeBean.items}"/>
                     </h:selectOneMenu>
               
                     <br />
                     <h:column><h:outputText value="Leave Balance as on today"/></h:column>
                     <h:outputText value="#{EmployeeLeaveBean.leaveValue}"></h:outputText>
                     <br />
                  </h:panelGrid>
               </h:form>
            </rich:tab>
            <rich:tab label="Leave Details"> 
              <rich:dataTable id="comdetails" value="#{EmployeeLeaveBean.leavefData}" binding="#{EmployeeLeaveBean.dataGrid3}" var="em" rows="10" style="width:100%;" >  
                    <h:column>
                        <f:facet name="header" >
                            <h:outputText value="Sr.No"/>
                        </f:facet>
                            <h:outputText value="#{em.srNo}" />
                        </h:column>  
                    <h:column>
                        <f:facet name="header" >
                            <h:outputText value="Employee Code"/>
                        </f:facet>
                            <h:outputText value="#{em.empc}" />
                    </h:column>
                    <h:column>
                        <f:facet name="header" >
                            <h:outputText value="Date From"/>
                        </f:facet>
                            <h:outputText value="#{em.dateFrom}"/>
                    </h:column>
                    <h:column>
                        <f:facet name="header" >
                            <h:outputText value="Date To"/>
                        </f:facet>
                            <h:outputText value="#{em.dateTo}" />
                    </h:column>
                    <h:column>
                        <f:facet name="header" >
                            <h:outputText value="Count"/>
                        </f:facet>
                            <h:outputText value="#{em.count}" />
                    </h:column>
                    <h:column>
                        <f:facet name="header" >
                            <h:outputText value=" Leave Quota"/>
                        </f:facet>
                            <h:outputText value="#{em.leaveTypeName}" />
                    </h:column>
                    <h:column>
                        <f:facet name="header" >
                            <h:outputText value="Applied Date"/>
                        </f:facet>
                            <h:outputText value="#{em.appliedDate}" />
                    </h:column>
                    <h:column>
                        <f:facet name="header" >
                            <h:outputText value="Approval Date"/>
                        </f:facet>
                            <h:outputText value="#{em.approvaldate}" />
                    </h:column>
                    <h:column>
                        <f:facet name="header" >
                            <h:outputText value="Approval Status"/>
                        </f:facet>
                            <h:outputText style="color:green" value="#{em.activeStatus}"/>
                    </h:column>
                    <h:column>
                        <f:facet name="header" >
                            <h:outputText value="Reason For Leave"/>
                        </f:facet>
                            <h:outputText value="#{em.reasonfleave}" />
                    </h:column>
                    <h:column>
                        <f:facet name="header" >
                            <h:outputText value="Contact No"/>
                        </f:facet>
                            <h:outputText value="#{em.contractno}" />
                    </h:column>
                    <h:column>
                        <f:facet name="header" >
                            <h:outputText value="Reporting Officer"/>
                        </f:facet>
                            <h:outputText value="#{em.reportingoff}" />
                    </h:column>
                    <h:column>
                        <f:facet name="header" >
                            <h:outputText value="Covering Officer"/>
                        </f:facet>
                            <h:outputText value="#{em.coveringoff}" />
                    </h:column>
                    </rich:dataTable>
           </rich:tab>
           <rich:tab label="Leave Status">
              <rich:tabPanel style="border:none;width:100%">
                <rich:tab label="Approved Leaves">
                <rich:dataTable id="result" value="#{EmployeeLeaveBean.leaveAData}" binding="#{EmployeeLeaveBean.dataGrid4}" var="emp" rows="10" style="width:100%;" >
                <a4j:keepAlive beanName="EmployeeLeaveBean" ajaxOnly="true"/>
                    <h:column>
                        <f:facet name="header" >
                            <h:outputText value="Sr.No"/>
                        </f:facet>
                            <h:outputText value="#{emp.srNo}" />
                    </h:column>
                    <h:column>
                        <f:facet name="header" >
                            <h:outputText value="Employee Code"/>
                        </f:facet>
                            <h:outputText value="#{emp.empc}" />
                    </h:column>
                    <h:column>
                        <f:facet name="header" >
                            <h:outputText value="Date From"/>
                        </f:facet>
                            <h:outputText value="#{emp.dateFrom}"/>
                    </h:column>
                    <h:column>
                        <f:facet name="header" >
                            <h:outputText value="Date To"/>
                        </f:facet>
                            <h:outputText value="#{emp.dateTo}" />
                    </h:column>
                    <h:column>
                        <f:facet name="header" >
                            <h:outputText value="Count"/>
                        </f:facet>
                            <h:outputText value="#{emp.count}" />
                    </h:column>
                    <h:column>
                        <f:facet name="header" >
                            <h:outputText value=" Leave Type "/>
                        </f:facet>
                            <h:outputText value="#{emp.leaveTypeName}" />
                    </h:column>
                    <h:column>
                        <f:facet name="header" >
                            <h:outputText value="Applied Date"/>
                        </f:facet>
                            <h:outputText value="#{emp.appliedDate}" />
                    </h:column>
                    <h:column>
                        <f:facet name="header" >
                            <h:outputText value="Approval Date"/>
                        </f:facet>
                            <h:outputText value="#{emp.approvaldate}" />
                    </h:column>
                    <h:column>
                        <f:facet name="header" >
                            <h:outputText value="Approval Status"/>
                        </f:facet>
                            <h:outputText style="color : #{emp.activeStatus gt Approved ? 'red;' : 'green;'};"  value="#{emp.activeStatus}"/>
                    </h:column>
                    <h:column>
                        <f:facet name="header" >
                            <h:outputText value="Reason For Leave"/>
                        </f:facet>
                            <h:outputText value="#{emp.reasonfleave}" />
                    </h:column>
                    <h:column>
                        <f:facet name="header" >
                            <h:outputText value="Contact No."/>
                        </f:facet>
                            <h:outputText value="#{emp.contractno}" />
                    </h:column>
                    <h:column>
                        <f:facet name="header" >
                            <h:outputText value="Reporting Officer"/>
                        </f:facet>
                            <h:outputText value="#{emp.reportingoff}" />
                    </h:column>
                    <h:column>
                        <f:facet name="header" >
                            <h:outputText value="Covering Officer"/>
                        </f:facet>
                            <h:outputText value="#{emp.coveringoff}" />
                    </h:column>
                </rich:dataTable>
              </rich:tab>
                <rich:tab label="Pending Leaves">
                    <rich:dataTable id="pending" value="#{EmployeeLeaveBean.leavepenData}" binding="#{EmployeeLeaveBean.dataGrid1}" var="emp" rows="10" style="width:100%;" >
                        <a4j:keepAlive beanName="EmployeeLeaveBean" ajaxOnly="true"/>
                        <h:column>
                            <f:facet name="header" >
                                <h:outputText value="Sr.No"/>
                            </f:facet>
                                <h:outputText value="#{emp.srNo}" />
                        </h:column>  
                        <h:column>
                            <f:facet name="header" >
                                <h:outputText value="Employee Code"/>
                            </f:facet>
                               <h:outputText value="#{emp.empc}" />
                        </h:column>
                        <h:column>
                            <f:facet name="header" >
                                <h:outputText value="Date From"/>
                            </f:facet>
                             <h:outputText value="#{emp.dateFrom}"/>
                        </h:column>
                        <h:column>
                            <f:facet name="header" >
                                <h:outputText value="Date To"/>
                            </f:facet>
                             <h:outputText value="#{emp.dateTo}" />
                        </h:column>
                        <h:column>
                            <f:facet name="header" >
                                <h:outputText value="Count"/>
                            </f:facet>
                             <h:outputText value="#{emp.count}" />
                        </h:column>
                        <h:column>
                            <f:facet name="header" >
                                <h:outputText value=" Leave Quota"/>
                            </f:facet>
                             <h:outputText value="#{emp.leaveTypeName}" />
                        </h:column>
                        <h:column>
                            <f:facet name="header" >
                                <h:outputText value="Applied Date"/>
                            </f:facet>
                             <h:outputText value="#{emp.appliedDate}" />
                        </h:column>
                        <h:column>
                            <f:facet name="header" >
                                <h:outputText value="Approval Date"/>
                            </f:facet>
                             <h:outputText value="#{emp.approvaldate}" />
                        </h:column>
                        <h:column>
                            <f:facet name="header" >
                                <h:outputText value="Leave Status"/>
                            </f:facet>
                            <h:outputText style="color:red" value="#{emp.activeStatus}" />
                        </h:column>
                        <h:column>
                            <f:facet name="header" >
                                <h:outputText value="Reason For Leave"/>
                            </f:facet>
                             <h:outputText value="#{emp.reasonfleave}" />
                        </h:column>
                        <h:column>
                            <f:facet name="header" >
                                <h:outputText value="Contact No"/>
                            </f:facet>
                             <h:outputText value="#{emp.contractno}" />
                        </h:column>
                        <h:column>
                            <f:facet name="header" >
                                <h:outputText value="Reporting Officer"/>
                            </f:facet>
                             <h:outputText value="#{emp.reportingoff}" />
                        </h:column>
                        <h:column>
                            <f:facet name="header" >
                                <h:outputText value="Covering Officer"/>
                            </f:facet>
                             <h:outputText value="#{emp.coveringoff}" />
                        </h:column>
                     </rich:dataTable>
                 </rich:tab>
                 <rich:tab label="Rejected Leaves">
                     <rich:dataTable id="Rejected" value="#{EmployeeLeaveBean.leaveRejecData}" binding="#{EmployeeLeaveBean.dataGrid2}" var="emp" rows="10" style="width:100%;" >
                         <h:column>
                            <f:facet name="header" >
                                <h:outputText value="Sr.No"/>
                            </f:facet>
                             <h:outputText value="#{emp.srNo}" />
                        </h:column>  
                           <h:column>
                            <f:facet name="header" >
                                <h:outputText value="Employee Code"/>
                            </f:facet>
                              <h:outputText value="#{emp.empc}" />
                        </h:column>
                         <h:column>
                            <f:facet name="header" >
                                <h:outputText value="Date From"/>
                            </f:facet>
                           <h:outputText value="#{emp.dateFrom}"/>
                        </h:column>
                         <h:column>
                            <f:facet name="header" >
                                <h:outputText value="Date To"/>
                            </f:facet>
                             <h:outputText value="#{emp.dateTo}" />
                        </h:column>
                         <h:column>
                            <f:facet name="header" >
                                <h:outputText value="Count"/>
                            </f:facet>
                             <h:outputText value="#{emp.count}" />
                        </h:column>
                         <h:column>
                            <f:facet name="header" >
                                <h:outputText value=" Leave Quota"/>
                            </f:facet>
                             <h:outputText value="#{emp.leaveTypeName}" />
                        </h:column>
                         <h:column>
                            <f:facet name="header" >
                                <h:outputText value="Applied Date"/>
                            </f:facet>
                             <h:outputText value="#{emp.appliedDate}" />
                        </h:column>
                         <h:column>
                            <f:facet name="header" >
                                <h:outputText value="Approval Date"/>
                            </f:facet>
                             <h:outputText value="#{emp.approvaldate}" />
                        </h:column>
                         <h:column>
                            <f:facet name="header" >
                                <h:outputText value="Approval Status"/>
                            </f:facet>
                             <h:outputText style="color:Red" value="#{emp.activeStatus}" />
                        </h:column>
                         <h:column>
                            <f:facet name="header" >
                                <h:outputText value="Reason For Leave"/>
                            </f:facet>
                             <h:outputText value="#{emp.reasonfleave}" />
                        </h:column>
                         <h:column>
                            <f:facet name="header" >
                                <h:outputText value="Contact No"/>
                            </f:facet>
                             <h:outputText value="#{emp.contractno}" />
                        </h:column>
                         <h:column>
                            <f:facet name="header" >
                                <h:outputText value="Reporting Officer"/>
                            </f:facet>
                             <h:outputText value="#{emp.reportingoff}" />
                        </h:column>
                         <h:column>
                            <f:facet name="header" >
                                <h:outputText value="Covering Officer"/>
                            </f:facet>
                             <h:outputText value="#{emp.coveringoff}" />
                        </h:column>
                     </rich:dataTable>
                 </rich:tab>
              </rich:tabPanel>
           </rich:tab>
        </rich:tabPanel>
      </f:view>
      <%--          <h:form>
                    <rich:panel header="New Leave Request">
                        <h:panelGrid columns="2">
                            <h:outputText value="Employee ID"/>
                            <h:outputText value="#{EmployeeLeaveBean.empId}"/>
                            <h:outputText value="From Date" />
                            <rich:calendar converter="dateConverter" showWeekDaysBar="false"
                                               showFooter="false" styleClass="special"
                                               datePattern="yyyy-MM-dd" id="empDob" popup="true"
                                               required="true" value="#{EmployeeLeaveBean.dateFrom}">
                                
                            </rich:calendar>
                            <h:outputText value="To Date" />
                            <rich:calendar converter="dateConverter" showWeekDaysBar="false"
                                               showFooter="false" styleClass="special"
                                               datePattern="yyyy-MM-dd" id="empDoj" popup="true"
                                               required="true" value="#{EmployeeLeaveBean.dateTo}">

                            </rich:calendar>
                            <h:outputText value="Leave Type"/>
                            <h:selectOneMenu  id="leaveType" value="#{EmployeeLeaveBean.leaveTypeCode}">
                                <f:selectItems value="#{LeaveTypeBean.items}"/>
                         </h:selectOneMenu>
                            <h:commandButton action="#{EmployeeLeaveBean.save}" value="Save"/>
                            <h:commandButton value="Close" onclick="Richfaces.hideModalPanel('pnl');" />
                        </h:panelGrid>
                    </rich:panel>
                </h:form>
            </rich:modalPanel>
        </f:view> --%>
    </body>
</html>
