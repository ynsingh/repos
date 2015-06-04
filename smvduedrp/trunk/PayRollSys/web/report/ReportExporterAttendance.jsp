<%-- 
    Document   : ReportExporterAttendance
    Created on : Dec 30, 2014, 4:25:29 PM
    
--%>
<%--
/**
 *
 *  *  Copyright (c) 2010 - 2011 SMVDU, Katra.
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
*  DISCLAIMED.  IN NO EVENT SHALL SMVDU OR ITS CONTRIBUTORS BE LIABLE 
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
*  Author     : Om Prakash <omprakashkgp@gmail.com>, IITK, Manorama Pal (palseema30@gmail.com)
**/--%>

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
    </head>
    <body>
        <f:view>



            <%

                        String forward = request.getParameter("fwdLink");
                        String path = application.getRealPath("/");

            %>
            <rich:panel header="Report Exporter" style="height:70px;">
 
                <%-- <rich:panel style="width:230px;float:left;text-align:center">
                            <form action="<%= forward%>" method="post">
                            <a4j:commandButton id="exbutton" reRender="pdf" value="ExportAsHTML" onclick="submit();" disabled="false"/>-->
                            </form>
                        </rich:panel>--%>
                   <h:form>
                          <h:outputText value="View Monthly Attendance Report  " style="font-weight:bold" />
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
                          </h:selectOneMenu>&nbsp;
                          <h:selectOneMenu value="#{AttendanceControllerBean.year}" >
                             <f:selectItems value="#{AttendanceControllerBean.itemsYear}"/>
                          </h:selectOneMenu>&nbsp;
                          <h:commandButton value="ExportAsPDF" action="#{AttendanceControllerBean.attendanceReportPDF}" disabled="false"/>&nbsp;
                          <h:commandButton value="ExportAsHTML" action="#{AttendanceControllerBean.attendanceReportHTML}" disabled="false"/>
                       </h:form>
                    </rich:panel>
              </f:view>
    </body>
</html>

