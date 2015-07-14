<%-- 
    Document   : Home
    Created on : Mar 24, 2011, 9:27:20 PM
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
<!DOCTYPE html>
<f:view>
<html>
    <head>

        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <%--<title>erp-360 | Welcome</title>--%>
            <title>PayRollSystem | Welcome</title>
            <%--<h:graphicImage url="/img/payrollheader.png" width="1246" height="140" style="0" />--%>
        <link rel="stylesheet" type="text/css" href="../css/reset.css"/>
        <link rel="stylesheet" type="text/css" href="../css/table.css"/>
        <link rel="stylesheet" type="text/css" href="../css/layout.css"/>
        <link rel="stylesheet" type="text/css" href="../css/mainpage.css"/>
        <script type="text/javascript">

            function loadIframe(iframeName, url) {
                if ( window.frames[iframeName] ) {

                    window.frames[iframeName].location = url;

                    return false;
                }
                return true;
            }
            // assign new url to iframe element's src property
            function changeIframeSrc(id, url) {
                if (!document.getElementById) return;
                var el = document.getElementById(id);
                if (el && el.src) {
                    el.src = url;
                    return false;
                }
                return true;
            }

        </script>
    </head>
    <body id="" class="mainpage">
         <div style=" margin-left: 10%; margin-right: 10%; margin-top: 1%">
                <h:graphicImage url="/img/payrollheader.png"   width="100%" height="100%"/>         
                <rich:panel style="background-color:#f5dbdb;">
                    <h:panelGrid width="90%" columns="2">
                        <h:panelGrid width="90%" columns="3">
                            <h:outputText style="font-size:1.2em;font-color:blue;font-weight:bold;" value="Welcome : #{LoggedEmployee.profile.name}  "/>
                            <h:outputText style="font-size:1.2em;font-color:blue;font-weight:bold;" value="Department : #{LoggedEmployee.profile.deptName}"/>
                            <h:outputText style="font-size:1.2em;font-color:blue;font-weight:bold;" value="Designation : #{LoggedEmployee.profile.desigName}"/>
                            <h:outputText  value="Today :  #{LoggedEmployee.currentDay  },#{LoggedEmployee.currentMonthName}, Attendance Marked"/>
                           
                        </h:panelGrid>
                        <h:panelGrid >
                            <h:form>
                                <%--<h:commandButton id="lout" action="#{LoggedEmployee.logout}"image="/img/lout.gif" value="Logout"/>--%>
                                <h:commandButton id="lout" action="#{LoggedEmployee.logout}" value="Logout"/>
                                <%--<rich:toolTip value="Click to Logout" for="lout"/>--%>

                            </h:form>
                            <h:commandButton onclick="Richfaces.showModalPanel('cp');" value="Change Password"/>
                            
                        </h:panelGrid>
                    </h:panelGrid>
                </rich:panel>

               
               <div class="sidebar"> 
                     <rich:panel style="height:100px;background-color:#CFDEFF:">
                            <h:form>
                                <a4j:mediaOutput id="logoimg" element="img" mimeType="#{fileUploadBean.file.mime}"
                                             createContent="#{fileUploadBean.paint}" value="#{row}"
                                             style="width:80px; height:80px;" cacheable="false">
                                     </a4j:mediaOutput>
                            </h:form>
                            </rich:panel>
                            <rich:panel>
                            <br/><h:panelGrid columns="2">
                                <h:outputText value=""/>
                                <h:outputText style="font-size:16px;align:center;" value="#{OrgController.currentOrg.name}"/>
                            </h:panelGrid>                   
                       </rich:panel>
                       <rich:panel style="height:450px;background-color:#CFDEFF:" header="My Options">
                        <h:form>
                            <h:panelGrid  columns="1">
                                <h:commandButton onclick="return loadIframe('ifrm', 'UpdateProfile.jsf')" value="Profile"/>
                                <h:commandButton onclick="return loadIframe('ifrm', '../report/ReportExporter.jsf?fwdLink=SingleMonthlyPayroll.jsf')" value="Monthly Salary Slip"/>
                                <h:commandButton onclick="return loadIframe('ifrm', 'AttendanceReport.jsf')" value="Monthly Attendance Report"/>
                                <h:commandButton onclick="return loadIframe('ifrm', 'TimeManager.jsf')" value="My TaskPad"  disabled="true"/>
                                <h:commandButton onclick="return loadIframe('ifrm', 'DailyWorkReport.jsf')" value="My daily Worksheet" disabled="true"/>
                                <h:commandButton onclick="return loadIframe('ifrm', 'IndividualAttendanceReport.jsf')" value="AttendanceReport" disabled="true"/>
                                <h:commandButton onclick="return loadIframe('ifrm', 'TaxPlanner.jsf')" value="Plan Tax Payback" disabled="true"/>
                                <h:commandButton onclick="return loadIframe('ifrm', 'AnnualSalaryReport.jsf')" value="Annual salary Report" disabled="true"/>
                                <h:commandButton onclick="return loadIframe('ifrm', 'TDSReport.jsf')" value="Annual TDS Report" disabled="true"/>
                                <h:commandButton onclick="return loadIframe('ifrm', 'PFReport.jsf')" value="Annual PF Report" disabled="true"/>
                                <h:commandButton onclick="return loadIframe('ifrm', 'LeaveReport.jsf')" value="Leave Account Status" disabled="true"/>
                                <h:commandButton onclick="return loadIframe('ifrm', 'LoanRequest.jsf')" value="Loans" disabled="true"/>
                                <h:commandButton onclick="return loadIframe('ifrm', 'TaskManager.jsf')" value="My Tasks" disabled="true"/>
                                <h:commandButton onclick="return loadIframe('ifrm', 'EmpOrder.jsf')" value="My Team" disabled="true"/>
                                
                                                               
                            </h:panelGrid>
                        </h:form>
                        <%--<rich:panel id="setdate" header="User Details">
                            <h:form>
                                <h:outputText value="Current Date | "/>
                                <h:outputText value="#{LoggedEmpoyee.currentMonthName} "/>
                                <rich:calendar inputSize="10" value="#{LoggedEmpoyee.currentDate}"
                                               datePattern="yyyy-MM-dd" converter="dateConverter"/>
                                <a4j:commandButton reRender="setdate" action="#{LoggedEmpoyee.set}" value="Set" />
                            </h:form>

                            <br/> <br/>
                            <h:panelGrid columns="3">
                                <h:commandButton onclick="Richfaces.showModalPanel('themepanel');" value="Theme"/>
                                
                            </h:panelGrid>
                        </rich:panel>--%>

                    </rich:panel>
                            <rich:modalPanel id="cp">
                <h:form>
                    
                        <rich:panel header="Change Password">
                            <h:panelGrid columns="2">
                        <h:outputText value="New Password"/>
                        <h:inputSecret value="#{LoggedEmployee.passOne}"/>
                        <h:outputText value="Confirm"/>
                        <h:inputSecret value="#{LoggedEmployee.passTwo}"/>
                        
                        </h:panelGrid>
                         
                        </rich:panel>
                    <a4j:commandButton value="Change Password" action="#{LoggedEmployee.changePassword}"/>
                        <h:commandButton onclick="Richfaces.hideModalPanel('cp');" value="Close"/>
                    
                </h:form>
            </rich:modalPanel>

                    </div>
                <div class="body">
                    <div class="content-area" >
                       <%-- <iframe name="ifrm" id="ifrm" src="AttendanceReport.jsf" style="background-color:#CFDEFF" --%>
                       <iframe name="ifrm" id="ifrm" src="AttendanceReport.jsf" style="background-color:#CFDEFF"
                        width="100%" height="600px">Your browser doesn't support iframes.</iframe>
                   </div>
                      
                </div>
                       
           <rich:toolBar width="100%;" height="25px" > Developed by : SMVDU Team, and IIT KANPUR Team </rich:toolBar>
       </body>
</html>
</f:view>
