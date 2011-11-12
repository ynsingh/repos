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
<html>
    <head>

        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title>Payroll System | Welcome</title>
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
        <f:view>
            <rich:panel style="height:100%;" header="Payroll System | Employee Portal">
                <rich:panel style="background-color:#f5dbdb;">
                    <h:panelGrid width="80%" columns="2">
                        <h:panelGrid width="80%" columns="3">
                            <h:outputText style="font-size:1.2em;font-color:blue;" value="Welcome : #{LoggedEmployee.profile.name} ,  "/>
                            <h:outputText value="Department : #{LoggedEmployee.profile.deptName}"/>
                            <h:outputText value="Designation : #{LoggedEmployee.profile.desigName}"/>
                            <h:outputText value="Today :  #{LoggedEmployee.currentDay  },#{LoggedEmployee.currentMonthName}, Attendance Marked"/>
                            <h:graphicImage url="../img/success.png" width="20px;" height="20px;"/>
                        </h:panelGrid>
                        <h:panelGrid >
                            <h:commandButton value="Logout"/>
                            <h:commandButton value="Profile"/>
                        </h:panelGrid>
                    </h:panelGrid>
                </rich:panel>

                <div class="sidebar">

                    <rich:panel >
                        <h:form>
                            <a4j:mediaOutput id="logoimg" element="img" mimeType="#{fileUploadBean.file.mime}"
                                             createContent="#{fileUploadBean.paint}" value="#{row}"
                                             style="width:50px; height:50px;align:center;" cacheable="false">
                            </a4j:mediaOutput>
                        </h:form>
                    </rich:panel>
                    <rich:panel>
                        <h:panelGrid columns="2">
                            <h:outputText value=""/>
                            <h:outputText style="font-size:16px;align:center;" value="#{OrgController.currentOrg.name}"/>
                        </h:panelGrid>                   
                    </rich:panel>
                    <rich:panel style="width:100%;background-color:gray;"  header="My Options">
                        <h:form>
                            <h:panelGrid  columns="1">
                                <h:commandButton onclick="return loadIframe('ifrm', 'Items.jsf')" value="Items"/>
                            </h:panelGrid>
                        </h:form>
                        <rich:panel id="setdate" header="User Details">
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
                                <h:commandButton value="Help"/>
                            </h:panelGrid>
                        </rich:panel>

                    </rich:panel>
                </div>
                <div class="body">
                    <div class="content-area" >
                        <iframe name="ifrm" id="ifrm" src="TimeManager.jsf" style="background-color:#CFDEFF"
                                width="100%" height="600px">Your browser doesn't support iframes.</iframe>

                    </div>
                </div>
            </rich:panel>
            <rich:panel style="background-color:white;">
                <h:panelGrid columns="2">
                    <h:graphicImage width="30px;" height="30px;"  url="/img/algox.gif"/>
                    <h:outputText style="font-size:14px;"  value="Payroll System . Copyright 2010,  *  Copyright (c) 2010 - 2011 SMVDU, Katra.
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
* Technologies.http://www.algox.com"/>
                </h:panelGrid>
            </rich:panel>
        </f:view>
    </body>
</html>
