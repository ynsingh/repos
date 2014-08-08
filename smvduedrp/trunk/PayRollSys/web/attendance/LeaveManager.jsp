<%-- 
    Document   : LeaveManager
    Created on : Jan 4, 2011, 2:09:17 AM
    Author     :  *  Copyright (c) 2010 - 2011 SMVDU, Katra.
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
*  Modified Date: 4 AUG 2014, IITK (palseema30@gmail.com, kishore.shuklak@gmail.com)
*
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
        <link rel="stylesheet" type="text/css" href="css/reset.css"/>
        <link rel="stylesheet" type="text/css" href="css/subpage.css"/>
        <link rel="stylesheet" type="text/css" href="css/components.css"/>
        <link rel="stylesheet" type="text/css" href="css/Form.css"/>
        <link rel="stylesheet" type="text/css" href="../css/richpanel.css"/>
      
    </head>
    <body>
        <f:view>
            <a4j:keepAlive beanName="EmployeeLeaveBean" ajaxOnly="true"/>
            <div class="container_form">
            <rich:panel id="addemplleave" header="Employee's Leave Details" styleClass="form" style="width:auto;" >
            <h:panelGrid columns="2">
            <h:commandButton onclick="Richfaces.showModalPanel('pnl');" value="Add New"/>
            <rich:messages>
                <f:facet name="infoMarker">
                    <h:graphicImage url="/img/success.png"/>
                </f:facet>
                <f:facet name="errorMarker">
                    <h:graphicImage url="/img/err.png"/>
                </f:facet>
                </rich:messages>
            </h:panelGrid>
            <h:form id="leaveForm">
                <h:panelGrid columns="3" id="leavetypeDetail">
                
                <rich:dataTable  id="llist" value="#{EmployeeLeaveBean.leaveData}" binding="#{EmployeeLeaveBean.dataGrid}" var="leave" rowKeyVar="row" rows="10" style="width:1200px;">
                    <rich:column>
                        <f:facet name="header">
                        <h:outputText value="Select"/>
                        </f:facet>
                        <h:selectBooleanCheckbox value="#{leave.selected}"/>
                    </rich:column>
                    <rich:column>
                    <f:facet name="header">
                        <h:outputText value="Name"/>
                    </f:facet>
                    <h:outputText value="#{leave.employee.name}"/>
                </rich:column>
                <rich:column>
                    <f:facet name="header">
                        <h:outputText value="Department"/>
                    </f:facet>
                    <h:outputText value="#{leave.employee.deptName}"/>
                </rich:column>
                <rich:column>
                    <f:facet name="header">
                        <h:outputText value="Designation"/>
                    </f:facet>
                    <h:outputText value="#{leave.employee.desigName}"/>
                </rich:column>
                <rich:column>
                    <f:facet name="header">
                    <h:outputText value="Leave Type"/>
                    </f:facet>
                    <h:outputText value="#{leave.leaveTypeName}"/>
                </rich:column>
                <rich:column>
                    <f:facet name="header">
                    <h:outputText value="Leave From"/>
                    </f:facet>
                    <h:outputText value="#{leave.dateFrom}"/>
                </rich:column>
                <rich:column>
                    <f:facet name="header">
                    <h:outputText value="Leave To"/>
                    </f:facet>
                    <h:outputText value="#{leave.dateTo}"/>
                </rich:column>
                 <rich:column>
                    <f:facet name="header">
                     <h:outputText value="Total"/>
                    </f:facet>
                    <h:outputText value="#{leave.count}"/>
                </rich:column>
                <rich:column>
                    <f:facet name="header">
                    <h:outputText value="Applied Date"/>
                    </f:facet>
                    <h:outputText value="#{leave.appliedDate}"/>
                </rich:column>
                <rich:column>
                    <f:facet name="header">
                    <h:outputText value="Status"/>
                    </f:facet>
                     <h:outputText style="color : #{leave.activeStatus gt Approved ? 'red' : 'green'};"  value="#{leave.activeStatus}"/>
                 </rich:column>
                <f:facet name="footer">
                <rich:datascroller for="llist" page="5" />  
                </f:facet>
                </rich:dataTable>
               </h:panelGrid>
               <%--<rich:separator/>--%>
               <a4j:commandButton value="Apply" action="#{EmployeeLeaveBean.acceptRequest}" reRender="llist, leavetypeDetail"/>
            </h:form>  
            <rich:modalPanel id="pnl">
                <h:form id="leaverequest">
                    <rich:panel header="New Leave Request">
                        <h:panelGrid columns="2">
                            <h:outputText value="Employee"/>
                            <h:selectOneMenu  id="empCode" value="#{EmployeeLeaveBean.empId}">
                            <f:selectItems value="#{EmployeeBean.empIdentity}"/>
                         </h:selectOneMenu>
                            <h:outputText value="From Date" />
                            <rich:calendar converter="dateConverter" showWeekDaysBar="false"
                                               showFooter="false" styleClass="special"
                                               datePattern="yyyy-MM-dd" id="Dfrom" popup="true"
                                               required="true" value="#{EmployeeLeaveBean.dateFrom}">
                                
                            </rich:calendar>
                            <h:outputText value="To Date" />
                            <rich:calendar converter="dateConverter" showWeekDaysBar="false"
                                               showFooter="false" styleClass="special"
                                               datePattern="yyyy-MM-dd" id="Dto" popup="true"
                                               required="true" value="#{EmployeeLeaveBean.dateTo}">

                            </rich:calendar>
                            <h:outputText value="Leave Type"/>
                            <h:selectOneMenu  id="leaveType" value="#{EmployeeLeaveBean.leaveTypeCode}">
                                <f:selectItems value="#{LeaveQuotaBean.itemAsArray}"/>
                          </h:selectOneMenu>
                            <h:outputText value="Applied Date"/>
                            <rich:calendar converter="dateConverter" showWeekDaysBar="false"
                                               showFooter="false" styleClass="special"
                                               datePattern="yyyy-MM-dd" id="appdate" popup="true"
                                               required="true" value="#{EmployeeLeaveBean.appliedDate}">
                             </rich:calendar>
                            <a4j:commandButton  value="Save"  action="#{EmployeeLeaveBean.save}"  reRender="llist, leavetypeDetail" oncomplete="#{rich:component('pnl')}.hide();"/>
                            <a4j:commandButton value="Close" onclick="#{rich:component('pnl')}.hide(); return false;"/>
                        </h:panelGrid>
                    </rich:panel>
                </h:form>
            </rich:modalPanel>
            </rich:panel>
        </f:view>
        </div>
    </body>
</html>
