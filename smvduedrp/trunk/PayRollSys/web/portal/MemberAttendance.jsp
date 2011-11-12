<%-- 
    Document   : MemberAttendance
    Created on : Apr 28, 2011, 6:14:06 PM
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
*  Contributors: Members of ERP Team @ SMVDU, Katra
*
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsf/core" %>
<%@ taglib prefix="h" uri="http://java.sun.com/jsf/html" %>
<%@ taglib uri="http://richfaces.org/a4j" prefix="a4j"%>
<%@ taglib uri="http://richfaces.org/rich" prefix="rich"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<f:view>

    <rich:panel header="Update Team Attendance">
        <h:form>
            <h:outputText value="Member ID"/>
            <h:selectOneMenu value="#{AttendanceReport.memberId}">
                <f:selectItems value="#{OrderController.asItem}"/>
            </h:selectOneMenu>
            <a4j:commandButton value="Show Attendances" action="#{AttendanceReport.loadAttReport}" reRender="tbl"/>
            <rich:dataTable value="#{AttendanceReport.attendances}" id="tbl" var="att">
                <rich:column width="40%" >
                    <f:facet name="header">
                        <h:outputText  value="Date"/>
                    </f:facet>
                    <h:outputText value="#{att.date}" />
                </rich:column>
                <rich:column  >
                    <f:facet name="header">
                        <h:outputText  value="Login Time"/>
                    </f:facet>
                    <h:inputText value="#{att.oginTime}" />
                </rich:column>
                <rich:column  >
                    <f:facet name="header">
                        <h:outputText  value="Logout Time"/>
                    </f:facet>
                    <h:inputText value="#{att.ogoutTime}" />
                </rich:column>
            </rich:dataTable>

        </h:form>

    </rich:panel>

</f:view>

