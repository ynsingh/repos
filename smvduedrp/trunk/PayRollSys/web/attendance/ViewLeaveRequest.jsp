<%-- 
    Document   : ViewLeaveRequest
    Created on : Feb 16, 2017, 11:09:11 AM
    Author     : Om Prakash (omprakashkgp@gmail.com)
*  Copyright (c) 2010 - 2011 SMVDU, Katra.
*  Copyright (c) 2014 - 2017 ETRG, IITK.
*  All Rights Reserved.
*  Redistribution and use in source and binary forms, with or 
*  without modification, are permitted provided that the following 
*  conditions are met: 
*  Redistributions of source code must retain the above copyright 
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

--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsf/core" %>
<%@ taglib prefix="h" uri="http://java.sun.com/jsf/html" %>
<%@ taglib uri="http://richfaces.org/a4j" prefix="a4j"%>
<%@ taglib uri="http://richfaces.org/rich" prefix="rich"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title> Leave Accept form </title>
    </head>
    <body>
        <f:view>
         <rich:panel id="addemplleave" header="Leave Approval Details" styleClass="form" style="width:auto;" >
            <h:form id="leaveForm">
                <h:panelGrid columns="1" id="leavetypeDetail">
                <rich:messages>
                      <f:facet name="infoMarker">
                          <h:graphicImage url="/img/success.png"/>
                      </f:facet>
                </rich:messages>
                <rich:dataTable  id="llist" value="#{EmployeeLeaveBean.allLeaveDetails}" binding="#{EmployeeLeaveBean.dataGrid}" var="leave" rowKeyVar="row" rows="10" style="width:100%;">
                    <rich:column>
                        <f:facet name="header">
                        <h:outputText value="S. No"/>
                        </f:facet>
                        <h:outputText value="#{leave.srNo}"/>
                    </rich:column>
                    <rich:column>
                    <f:facet name="header">
                        <h:outputText value="Name" style="height:16;" />
                    </f:facet>
                    <h:outputText value="#{leave.employee.name}"/>
                </rich:column>
                <rich:column>
                    <f:facet name="header">
                        <h:outputText value="Leave Type"/>
                    </f:facet>
                    <h:outputText value="#{leave.leaveTypeName}"/>
                </rich:column>
                <rich:column>
                    <f:facet name="header">
                        <h:outputText value="Leave Days"/>
                    </f:facet>
                    <h:outputText value="#{leave.count}"/>
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
                    <h:outputText value="Charge given To"/>
                    </f:facet>
                    <h:outputText value="#{leave.coveringoff}"/>
                 </rich:column>
                 <rich:column>
                    <f:facet name="header">
                    <h:outputText value="Applied Date"/>
                    </f:facet>
                    <h:outputText value="#{leave.appliedDate}"/>
                  </rich:column>
                  <rich:column>
                    <f:facet name="header">
                    <h:outputText value="Approval Date"/>
                    </f:facet>
                    <h:outputText value="#{leave.approvaldate}"/>
                   </rich:column>
                   <rich:column>
                    <f:facet name="header">
                    <h:outputText value="Status"/>
                    </f:facet> 
                        <h:outputText style="color : #{leave.activeStatus gt Approved ? 'red;' : 'green;'};"  value="#{leave.activeStatus}"/>
                    </rich:column>           
                   <rich:column>
                   <f:facet name="header">
                     <h:outputText value="Comments"/>
                   </f:facet>
                     <h:inputTextarea id="textarea" value="#{leave.comments}" />
                   </rich:column>
                   <rich:column>
                    <f:facet name="header">
                        <h:outputText value="Available Leave"/>
                    </f:facet>
                    <h:outputText value="#{leave.leaveValue}"/>
                   </rich:column>    
                   <rich:column>
                        <f:facet name="header">
                        <h:outputText value="Select"/>
                        </f:facet>
                        <h:selectBooleanCheckbox value="#{leave.selected}"/>
                    </rich:column>
                    <rich:column width="125px;" >
                        <f:facet name="header" >
                            <h:outputText value="Actions" />   
                        </f:facet>
                            <a4j:commandButton id="act" value="Accept" action="#{EmployeeLeaveBean.updateLeaveReq }" reRender="#{EmployeeLeaveBean.allLeaveDetails}, llist" ></a4j:commandButton>
                            <a4j:commandButton id="rej" value="Reject" action="#{EmployeeLeaveBean.rejectLeaveReq}" reRender="#{EmployeeLeaveBean.allLeaveDetails}, llist" ></a4j:commandButton>
                      </rich:column> 
                  <f:facet name="footer">
                <rich:datascroller for="llist" page="5" />  
                </f:facet>
                </rich:dataTable>
               </h:panelGrid>
            </h:form> 
        </rich:panel> 
        </f:view>
    </body>
</html>
