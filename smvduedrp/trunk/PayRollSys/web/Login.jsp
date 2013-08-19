<%--
    Document        : Login.jsp
    Created on      : 3:02 AM Saturday, October 02, 2010
    Last Modified   : 3:21 AM Saturday, October 02, 2010
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

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <link rel="stylesheet" type="text/css" href="loginpage.css"/>
        <title>Payroll System | Login</title>
    </head>
    <f:view>
        <h:panelGrid style="margin-left:150px;margin-top:20px;" columns="2">
            <h:graphicImage alt="Payroll System" url="/img/pls.png"/>
            <rich:panel style="height:480px;" header="">
                <h:form>
                    <rich:messages>
                        <f:facet name="errorMarker">
                            <h:graphicImage url="/img/err.png"/>
                        </f:facet>
                    </rich:messages>
                    <rich:panel>
                        <h:graphicImage width="210px;" url="/icons/lg.jpg"/>
                        <h:outputText value="Login"  style="align:left;font-size:20px;"/>
                        <h:panelGrid bgcolor="grey"  columns="2">
                           <h:outputText value="Organization Name"/>
                            <h:selectOneMenu id="user" value="#{UserBean.userOrgCode}">
                                <f:selectItem itemLabel="Administrator"/>
                                <f:selectItems id="user1" value="#{OrgController.items}"/>
                            </h:selectOneMenu>
                            <h:outputText value="Username"/>
                            <h:inputText label="User Name" value="#{UserBean.userName}" />
                            <h:outputText value=" Password:"/>
                            <h:inputSecret label="Password" value="#{UserBean.password}"/>
                            <h:outputText value=" >> "/>
                            <h:commandButton  action="#{UserBean.validate}" value="Login" />
                        </h:panelGrid>
                    </rich:panel>
                    <h:outputText value=""/>
                    </h:form>
                <rich:separator/>
                <rich:separator/>
                <a href="adminLogin/OrgMain.jsf">Register New Institute</a> 
                <%--<a href="adminLogin/adminLogin.jsf">Administrator Login</a>--%>
                </rich:panel>
                </h:panelGrid>
        <rich:panel style="margin-left:150px;width:80%;background-color:red;">
            <h:panelGrid columns="4">
                <h:graphicImage width="50px;" height="50px;" />
                <h:outputText value="Developed by SMVD University"/>
            </h:panelGrid>
        </rich:panel>
            <rich:modalPanel id="newuser" height="470" width="400">
            <h:panelGrid columns="1">
                <rich:panel id="mes">
                <rich:messages>
                        <f:facet name="infoMarker">
                            <h:graphicImage url="/img/success.png"/>
                        </f:facet>
                        <f:facet name="errorMarker">
                            <h:graphicImage url="/img/error.png"/>
                        </f:facet>
                    </rich:messages>
            </rich:panel>
               <rich:panel header="User Registration">
                    <h:form>
                    <h:panelGrid id="pnl" columns="2">
                        <h:outputText value="Name"/>
                        <h:inputText id="Name" requiredMessage="Name cannot be empty" required="true" value="#{userOrgRegBeans.name}"/>
                        <h:outputText value="Father Name"/>
                        <h:inputText id="fname" requiredMessage="Name cannot be empty" required="true" value="#{userOrgRegBeans.fatherName}"/>
                       
                        <h:outputText value="Gender"/>
                        <h:inputText id="gender" requiredMessage="Gender cannot be empty" required="true" value="#{userOrgRegBeans.gender}"/>
                        
                        <h:outputText value="Organization Name"/>
                        <h:selectOneMenu value="#{userOrgRegBeans.orgCode}">
                            <f:selectItems value="#{OrgController.items}"/>
                        </h:selectOneMenu>
                        
                        <h:outputText value="Address"/>
                        <h:inputTextarea id="insta1" value="#{userOrgRegBeans.address}"/>
                        

                        <h:outputText value="Date of Birth"/>
                        <rich:calendar enableManualInput="false" converter="dateConverter" showWeekDaysBar="false"
                                                   showFooter="false" styleClass="special"
                                                   datePattern="yyyy-MM-dd" id="empDob" popup="true"
                                                   required="true"   requiredMessage="*Enter Date Of Birth as yyyy-mm-dd"
                                                   value="#{userOrgRegBeans.dateoffbirth}">
                                    </rich:calendar>
                        
                        <h:outputText value="Phone"/>
                        <h:inputText id="instph" value="#{userOrgRegBeans.phone}"/>
                        
                        <h:outputText value="E-Mail"/>
                        <h:inputText id="instmail" value="#{userOrgRegBeans.email}"/>
                        <h:selectBooleanCheckbox id="se" immediate="true" value="#{userOrgRegBeans.terms}">
                            <a4j:support action="#{userOrgRegBeans.sendingMailToNewUser}" event="onclick" reRender="mes"/>
                        </h:selectBooleanCheckbox>
                        <h:commandLink value="Accept Terms And Condition"/>
                        <rich:separator/>
                        <rich:separator/>
                        <%-- <h:outputText value="Registration Code "/>
                        <h:inputText  id="reg" value="#{userOrgRegBeans.regCode}" required="true">
                            <a4j:support event="onchange" action="#{userOrgRegBeans.registartionCode}" reRender="mes,suc"/>
                        </h:inputText>--%>
                            <h:panelGroup>
                                <a4j:commandButton id="suc" value="Save" action="#{userOrgRegBeans.save}" disabled="#{userOrgRegBeans.enable}"/>
                                <h:commandButton styleClass="panel" value="Reset" onclick="this.form.reset()"/>
                            </h:panelGroup>
                    </h:panelGrid>
             </h:form>
            </rich:panel>
            </h:panelGrid>
        </rich:modalPanel>    
    </f:view>
</html>
