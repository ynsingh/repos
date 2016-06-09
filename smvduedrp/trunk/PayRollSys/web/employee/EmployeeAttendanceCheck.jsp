<%-- 
    Document   : EmployeeAttendanceCheck
    Created on : Dec 09, 2014, 5:22:17 PM
    Author     :
*  Copyright (c) 2010 - 2011 SMVDU, Katra.
* Copyright (c) 2014 - 2016 ETRG, IITK.
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
   guest
   @Author : Om Prakash(omprakashkgp@gmail.com), Manorama Pal (palseema30@gmail.com) IITK
--%>

<%@page import="org.smvdu.payroll.beans.upload.FileUploadBean" %>
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
        <style>
.top {
    vertical-align: top;
    
}
.info {
    height: 202px;
    overflow: auto;
}
</style>
        
    </head>
    <body>
            
        <f:view>
            <rich:panel>
               <h:form>
                  <h:panelGrid columns="5">
                         <h:outputText value="List of Attendance is not uploaded "/>
                           <h:selectOneMenu value="#{AttendanceControllerBean.month}" >
                                        <f:selectItem itemValue="0" itemLabel="January"/>
                                        <f:selectItem itemValue="1" itemLabel="February"/>
                                        <f:selectItem itemValue="2" itemLabel="March"/>
                                        <f:selectItem itemValue="3" itemLabel="April"/>
                                        <f:selectItem itemValue="4" itemLabel="May"/>
                                        <f:selectItem itemValue="5" itemLabel="June"/>
                                        <f:selectItem itemValue="6" itemLabel="July"/>
                                        <f:selectItem itemValue="7" itemLabel="August"/>
                                        <f:selectItem itemValue="8" itemLabel="September"/>
                                        <f:selectItem itemValue="9" itemLabel="October"/>
                                        <f:selectItem itemValue="10" itemLabel="November"/>
                                        <f:selectItem itemValue="11" itemLabel="December"/>
                         </h:selectOneMenu>
                         <h:selectOneMenu value="#{AttendanceControllerBean.year}" >
                             <%--<f:selectItem value="#{AttendanceControllerBean.cYear}"/>--%>
                             <f:selectItems value="#{AttendanceControllerBean.itemsYear}"/>
                         </h:selectOneMenu>
                         <a4j:commandButton reRender="tbl" action="#{AttendanceControllerBean.checkAtts}" value="Check Attendance"/>
                    </h:panelGrid>
                  </h:form>
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
                <h:form id="attForm"> 
                    
                  <%--  <rich:panel>--%>
                    <h:panelGrid id="attlist" style="width:100%;">
                                  
                        <rich:dataTable id="tbl" value="#{AttendanceControllerBean.allCheckAttendanceData}"  binding="#{AttendanceControllerBean.dataGrid}" var="emp" rowKeyVar="row"  rows="15" style="width:100%;">
                      
                        <h:column>
                            <f:facet name="header">
                                <h:outputText value="Check Box"/>
                            </f:facet>
                            <h:selectBooleanCheckbox value="#{emp.remember}" />
                        </h:column>
                        
                        <h:column >
                            <f:facet name="header">
                                <h:outputText value="Employee Code"/>
                            </f:facet>
                            <h:outputText value="#{emp.code}" id="code" />
                        </h:column>  
                         <%--<h:column >
                            <f:facet name="header">
                                <h:outputText value=" Employee Name "/>
                            </f:facet>
                            <h:outputText value="#{emp.name}" />
                         </h:column>--%>                       
                         <h:column >
                            <f:facet name="header">
                                <h:outputText value=" Present "/>
                            </f:facet>
                            <rich:inplaceInput value="#{emp.present}" />
                        </h:column> 
                         <h:column>
                             <f:facet name="header">
                                 <h:outputText value=" Absent" />
                             </f:facet>
                             <rich:inplaceInput value="#{emp.absent}" />
                         </h:column>
                         <h:column >
                            <f:facet name="header">
                                <h:outputText value=" Leave "/>
                            </f:facet>
                            <rich:inplaceInput value="#{emp.leave}" />
                         </h:column> 
                        <h:column>
                           <f:facet name="header">
                               <h:outputText value="Month"/>
                           </f:facet>
                           <rich:inplaceInput value="#{emp.month}"/>
                       </h:column>
                       <h:column>
                           <f:facet name="header">
                               <h:outputText value="Year"/>
                           </f:facet>
                           <rich:inplaceInput value="#{emp.year}"/>
                        </h:column> 
                         
                                <f:facet name="footer">
                                <rich:datascroller for="tbl" page="15"/>  
                            </f:facet>
                    </rich:dataTable>
                    </h:panelGrid>
                    <h:panelGrid columns="2">
                        <a4j:commandButton value="Save" reRender="tbl" action="#{AttendanceControllerBean.saveCheckAt}"/> 
                        <h:commandButton action="#{AttendanceBean.backPage }" value=" Back "/>
                    </h:panelGrid>
                   <%-- </rich:panel>--%>
                </h:form>
                
            </rich:panel>
          </f:view>
    </body>
</html>


