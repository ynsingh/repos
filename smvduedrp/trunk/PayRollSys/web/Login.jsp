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
*  Contributors: Members of ERP Team @ SMVDU, Katra, IITKanpur
*  Modified Date: 02 Dec 2013, IITK (palseema@rediffmail.com, kshuklak@rediffmail.com)
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
    <%--    <link rel="stylesheet" type="text/css" href="loginpage.css"/>  --%>
        <title>Payroll System | Login</title>
    </head>
    <f:view>
        <div class="container">
        
                <div class="header" syle="height:20%;">
                <h:graphicImage alt="payroll" url="/img/payrollheader.png" style="width:100%;"/>
                <rich:panel style="background-color:#425C83; Color:white">
               <a href="http://172.26.81.189:8080/PayrollSys/" style="color:white;font-family:arial;font-weight:bold;text-decoration:none" onclick="return false;">PAYROLL SYSTEM</a></font>   &nbsp;   
               <a href="http://202.141.40.215:8080/brihaspati/servlet/brihaspati" style="color:white;font-family:arial;font-weight:bold;text-decoration:none">BRIHASPATI</a> &nbsp;
               <a href="http://202.141.40.215/~brihaspati/BGAS/index.php/user/login" style="color:white;font-family:arial;font-weight:bold;text-decoration:none">BGAS</a> &nbsp;
               <a href="http://202.141.40.218:8080/pico/Administration/Index.action" style="color:white;font-family:arial;font-weight:bold;text-decoration:none">PICO</a>  &nbsp;
               <a href="http://www.ignouonline.ac.in/sakshatproposal/default.aspx" style="color:white;font-family:arial;font-weight:bold;text-decoration:none" onclick="return false;">STUDENT FEES MANAGEMENT SYSTEM</a>
               </rich:panel>   
               </div> 
             
        <div class="main-content" style="width:100%; background-color: red;">
            <div>
            <rich:messages>
                    <f:facet name="errorMarker">
                         <h:graphicImage url="/img/err.png"/>
                     </f:facet>
            </rich:messages>
            </div>
            
            <div class="sidebar" style="float:left; width:20%;">
                
                  <rich:panel header="Important Link" style="width:100%;float:left;text-align:left; height:720px ">
                   <br/>
                   <%--<h:outputText value=" Important Link" style="font-weight:bold"/>--%>
                   <a href="adminLogin/OrgMain.jsf" style="font-weight:bold;">Register New Institute</a><br/> 
                   <a href="http://www.ignouonline.ac.in/sakshatproposal/default.aspx" style="font-weight:bold;">NMEICT home page</a><br/>
                   <a href="adminLogin/index.html" style="font-weight:bold;" onclick="return false;">Release Notes</a><br/>
                   <a href="docs/indexnew.html" style="font-weight:bold;" onclick="return false;">User Manual</a><br/>
                   <a href="docs/ModuleHelp/index.jsp" style="font-weight:bold;" onclick="return false;">HTML Help</a><br/>
                   <a href="adminLogin/brihaspatiMainLogin.jsf" style="font-weight:bold;">Aunthenticate From Brihaspati Server  </a> 
                                    
                  </rich:panel>
            </div>
            
            <div class="main" style="float:left; width:80%;"> 
             <h:form>    
                <rich:panel header="Please Login" style="width:100%;float:left;text-align:left;height:720px"> 
                   <%--<h:outputText value=" Please Login"  style=" float:right;text-align:left;font-size:20px;"/>--%>
                    <br/>
                    <br/>
                    <h:panelGrid columns="2" style="margin-left:50px; width:30%;">
                     <h:outputText value="Organization Name"/>
                    <h:selectOneMenu id="user" value="#{UserBean.userOrgCode}" >
                    <f:selectItem itemLabel="Administrator" />
                    <f:selectItems id="user1" value="#{OrgController.items}"/>
                    </h:selectOneMenu>
                    <h:outputText value="Username" />
                    <h:inputText label="User Name" value="#{UserBean.userName}" />
                    <h:outputText value=" " />
                    <h:outputText value="Example: brihaspati@iitk.ac.in" style="color:blue;font-weight:bold"/>
                    <h:outputText value=" Password:"/>
                    <h:inputSecret label="Password" value="#{UserBean.password}"/>
                    <h:outputText value=" " />
                    <h:commandButton  action="#{UserBean.validate}" value="Login" /><br/>
                    <h:outputText value=" " />
                    <h:outputText value=" " />
                    <a href="#" style="text-decoration:none;alignment-adjust:auto;font-weight:bold;" onclick="return false;">Forgot Password</a><br/>
                    <%--<rich:separator style="align:left;width:800px" />--%>
                    </h:panelGrid>
                    <input type="hidden" name="userrole" value="user" />
                                     
               </rich:panel>
           </h:form>
            </div>
        </div>
          <h:panelGrid style="width:100%;">
           <rich:panel style="background-color:#425C83;">
            <h:outputText value="Developed by SMVD University and IIT Kanpur." style="color:white;"/>
            </rich:panel>
           </h:panelGrid>  
        
        <%--            
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
                        <rich:separator/>--%>
                        <%-- <h:outputText value="Registration Code "/>
                        <h:inputText  id="reg" value="#{userOrgRegBeans.regCode}" required="true">
                            <a4j:support event="onchange" action="#{userOrgRegBeans.registartionCode}" reRender="mes,suc"/>
                        </h:inputText>--%>
                       <%--     <h:panelGroup>
                                <a4j:commandButton id="suc" value="Save" action="#{userOrgRegBeans.save}" disabled="#{userOrgRegBeans.enable}"/>
                                <h:commandButton styleClass="panel" value="Reset" onclick="this.form.reset()"/>
                            </h:panelGroup>
                    </h:panelGrid>
             </h:form>
            </rich:panel>
            </h:panelGrid>
        </rich:modalPanel>  --%> 
       </div>
    </f:view>
</html>
