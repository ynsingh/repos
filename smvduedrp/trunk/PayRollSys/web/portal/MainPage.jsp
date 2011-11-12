<%-- 
    Document   : TableViewer
    Created on : Dec 13, 2010, 7:57:28 PM
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

<html>
    <head>
        
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title>Payroll System | Welcome</title>
        <link rel="stylesheet" type="text/css" href="css/reset.css"/>
        <link rel="stylesheet" type="text/css" href="css/table.css"/>
        <link rel="stylesheet" type="text/css" href="css/layout.css"/>
        <link rel="stylesheet" type="text/css" href="css/mainpage.css"/>
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
            <h:form>
                <rich:toolBar   height="10px">
                    <rich:hotKey key="ctrl+g" handler="#{rich:component('emp')}.expand()"/>
                    <h:graphicImage onclick="return loadIframe('ifrm', 'Home.jsf')" width="170px" height="16px" url="/img/psmini.png" alt="Image"/>
                   

                    <rich:dropDownMenu  value="Tax Management">
                        <rich:menuItem onclick="return loadIframe('ifrm', 'investment/InvestmentCategory.jsf')"  value="Investment Categories"/>
                        <rich:menuItem onclick="return loadIframe('ifrm', 'investment/InvestmentHead.jsf')"  value="Investment Heads"/>
                        <rich:menuItem onclick="return loadIframe('ifrm', 'investment/EmployeeInvestment.jsf')" value="Investment Form"/>
                        <rich:menuItem onclick="return loadIframe('ifrm', 'investment/TaxCalculator.jsf')" value="Annual Tax Calculator"/>
                        <rich:menuItem onclick="return loadIframe('ifrm', 'investment/TaxPlanner.jsf')" value="Tax payback Planer"/>
                    </rich:dropDownMenu>
                    <rich:dropDownMenu  value="Provident Fund">
                    </rich:dropDownMenu>
                    <rich:dropDownMenu  value="Leave">
                        <rich:menuItem onclick="return loadIframe('ifrm', 'attendance/LeaveManager.jsf')"  value="Leave Manager"/>
                        <rich:menuItem onclick="return loadIframe('ifrm', 'attendance/LeaveValue.jsf')"  value="Leave Values Setup"/>
                        <rich:menuItem onclick="return loadIframe('ifrm', 'attendance/LeaveQuota.jsf')"  value="Leave Quota Setup"/>
                    </rich:dropDownMenu>
                    <rich:dropDownMenu  value="Loan & Advances">
                    </rich:dropDownMenu>
                    <rich:dropDownMenu  value="Report">
                        <rich:menuItem  onclick="return loadIframe('ifrm', 'report/ReportExporter.jsf?fwdLink=BankStatement.jsf')"  value="Bank Statement"/>
                        <rich:menuItem  onclick="return loadIframe('ifrm', 'report/ReportExporter.jsf?fwdLink=MonthlySalarySingle.jsf')"  value="Monthly Salary Slip"/>
                        <rich:menuItem onclick="return loadIframe('ifrm','salary/MonthlyIndividualGrossSalary.jsf')" value="Individual Gross Salary"/>
                        <rich:menuItem onclick="return loadIframe('ifrm','report/MonthlyPayroll.jsf')" value="Monthly Salary Roll"/>
                        <rich:menuItem value="Exit"/>
                    </rich:dropDownMenu>
                    <rich:dropDownMenu  value="User">
                        <rich:menuItem onclick="return loadIframe('ifrm', 'account/ChangePass.jsf')"  value="Change Password"/>
                        <rich:menuItem action="account/Logout.jsf" value="Logout"/>
                    </rich:dropDownMenu>
                    <rich:dropDownMenu  value="Tools">
                        <rich:menuItem onclick="return loadIframe('ifrm', 'account/ChangePass.jsf')"  value="Change Password"/>
                        <rich:menuItem action="account/Logout.jsf" value="Logout"/>
                    </rich:dropDownMenu>
                    <rich:dropDownMenu  value="Help">
                        <rich:menuItem onclick="return loadIframe('ifrm', 'account/ChangePass.jsf')"  value="Change Password"/>
                        <rich:menuItem action="account/Logout.jsf" value="Logout"/>
                    </rich:dropDownMenu>
                    <h:graphicImage style="visibility:hidden" id="loaderimg" onclick="return loadIframe('ifrm', 'Home.jsf')" width="170px" height="16px" url="/img/waiter.gif" alt="Image"/>

                </rich:toolBar>
            </h:form>
            <div class="sidebar">
                <div class="logo">  </div>
                <rich:panel header="Institute Details">
                    <h:form>
                    <rich:contextMenu event="oncontextmenu" attachTo="logoimg" submitMode="ajax">
                                <rich:menuItem value="Edit Logo" onclick="Richfaces.showModalPanel('pnl');" id="zin"/>
                            </rich:contextMenu>
                    <a4j:mediaOutput id="logoimg" element="img" mimeType="#{fileUploadBean.file.mime}"
                                             createContent="#{fileUploadBean.paint}" value="#{row}"
                                             style="width:140px; height:140px;" cacheable="false">
                    </a4j:mediaOutput>
                    </h:form>
               </rich:panel>
                <rich:panel>
                    <h:panelGrid columns="2">
                        <h:outputText value=""/>
                        <h:outputText style="font-size:16px;align:center;" value="#{OrgController.currentOrg.name}"/>
                    </h:panelGrid>
                    <h:panelGrid columns="2">
                        <h:outputText value=""/>
                        <h:outputText value="#{OrgController.currentOrg.tagLine}"/>
                    </h:panelGrid>
                    <h:form>
                    <h:panelGrid columns="2">
                        <h:outputText value=""/>
                        <h:commandLink value="#{OrgController.currentOrg.web}"/>
                    </h:panelGrid>
                    </h:form>
                    <h:panelGrid columns="2">
                        <h:outputText value=""/>
                        <h:outputText value="#{OrgController.currentOrg.address1}"/>
                    </h:panelGrid>
                    <h:panelGrid columns="2">
                        <h:outputText value=""/>
                        <h:outputText value="#{OrgController.currentOrg.address2}"/>
                    </h:panelGrid>
                    <h:panelGrid columns="2">
                        <h:outputText value=""/>
                        <h:outputText value="#{OrgController.currentOrg.email}"/>
                    </h:panelGrid>
                    <h:panelGrid columns="2">
                        <h:outputText value=""/>
                        <h:outputText value="#{OrgController.currentOrg.phone}"/>
                    </h:panelGrid>
                    <h:commandButton onclick="Richfaces.showModalPanel('pnl');" value="Upload Logo" />
                    <h:commandButton onclick="Richfaces.showModalPanel('instData');" value="Update"/>
                    </rich:panel>

                <rich:panel id="setdate" header="User Details">
                        <h:form>
                        <h:outputText value="Current Date | "/>
                        <h:outputText value="#{UserBean.currentMonthName} "/>
                        <rich:calendar inputSize="10" value="#{UserBean.currentDate}"
                                       datePattern="yyyy-MM-dd" converter="dateConverter"/>
                        <a4j:commandButton reRender="setdate" action="#{UserBean.set}" value="Set" />
                        </h:form>
                        
                        <br/> <br/>
                    <h:panelGrid columns="3">
                         <h:commandButton onclick="Richfaces.showModalPanel('themepanel');" value="Theme"/>
                         <h:commandButton value="Help"/>                         
                    </h:panelGrid>
                     </rich:panel>

                
            </div>
            <div class="body">
                <div class="content-area" >
                    <iframe name="ifrm" id="ifrm" src="Home.jsf" style="background-color:#ffffff"
                            width="100%" height="600px">Your browser doesn't support iframes.</iframe>

                </div>
            </div>
             <rich:modalPanel id="pnl">
                <f:facet name="controls">
                    <h:graphicImage value="/img/cls.png" style="cursor:pointer"
                                    onclick="Richfaces.hideModalPanel('pnl')" />
                </f:facet>
                <h:form>
                    <rich:fileUpload addControlLabel="Browse Logo" fileUploadListener="#{fileUploadBean.listener}"
                                     id="uploasd"
                                     immediateUpload="false"
                                     acceptedTypes="jpg, gif, png, bmp" allowFlash="false">
                        <a4j:support event="onuploadcomplete" reRender="logoimg" />
                    </rich:fileUpload>
                </h:form>
            </rich:modalPanel>


            <rich:modalPanel id="instData">
                <f:facet name="controls">
                    <h:graphicImage value="/img/cls.png" style="cursor:pointer"
                                    onclick="Richfaces.hideModalPanel('instData')" />
                </f:facet>
                <f:facet name="header">
                    <h:outputText value="Theme Selector" />
                </f:facet>
                <h:form>
                
                    <h:panelGrid columns="2">
                        <h:outputText value="Institute Name"/>
                        <rich:inplaceInput value="#{OrgController.currentOrg.name}"/>
                    
                        <h:outputText value="Tag Line"/>
                        <rich:inplaceInput value="#{OrgController.currentOrg.tagLine}"/>
                   
                        <h:outputText value="WebSite"/>
                        <rich:inplaceInput value="#{OrgController.currentOrg.web}"/>
                   
                        <h:outputText value="Adress"/>
                        <rich:inplaceInput value="#{OrgController.currentOrg.address1}"/>
                   
                        <h:outputText value="City/State"/>
                        <rich:inplaceInput value="#{OrgController.currentOrg.address2}"/>
                    
                        <h:outputText value="E-Mail"/>
                        <rich:inplaceInput value="#{OrgController.currentOrg.email}"/>
                   
                        <h:outputText value="Phone"/>
                        <rich:inplaceInput value="#{OrgController.currentOrg.phone}"/>
                   
                        <h:commandButton action="#{OrgController.currentOrg.update}" value="Update"/>
                        <a4j:commandButton onclick="Richfaces.hideModalPanel('instData')" value="Close"/>
                    </h:panelGrid>
                </h:form>
            </rich:modalPanel>



            <rich:modalPanel id="themepanel">
                <f:facet name="controls">
                    <h:graphicImage value="/img/cls.png" style="cursor:pointer"
                                    onclick="Richfaces.hideModalPanel('themepanel')" />
                </f:facet>
                <f:facet name="header">
                    <h:outputText value="Theme Selector" />
                </f:facet>
                <h:form>
                    <h:panelGrid columns="3">
                        <h:outputText value="Select Theme"/>
                        <h:selectOneMenu onchange="submit();" value="#{ThemeBean.theme}">
                            <f:selectItems value="#{ThemeBean.themes}"/>
                        </h:selectOneMenu>
                        <h:commandButton onclick="Richfaces.hideModalPanel('themepanel');" value="Close"/>
                    </h:panelGrid>
                </h:form>

            </rich:modalPanel>
        </f:view>
    </body>
</html>
