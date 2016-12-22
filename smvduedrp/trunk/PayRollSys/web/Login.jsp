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
*  Modified Date: Feb 2016, Om Prakash omprakashkgp@gmail.com  IITK
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
        <link rel="stylesheet" type="text/css" href="css/LoginPage.css" /> 
        <script src="http://code.jquery.com/jquery-latest.min.js" type="text/javascript"></script>
        <script src="script.js"></script>
        
    </head>
    <f:view>
        <div class="container">
            
            <div class="wrapper">
            
                <div class="header">

                    <h:graphicImage alt="payroll" url="/img/payrollheader.png" style="width:100%;"/>

                </div>
                    
                    
                <div class="pageBody">
            
                    <div class="menu-wrapper">

                        <div id='cssmenu' class="align-center">
                            <ul>
                                <li><a href='http://brihaspati.nmeict.in/brihaspati/servlet/brihaspati' target="_blank">Brihaspati</a></li>
                                <li><a href='http://brihaspati.nmeict.in/~brihaspati/BGAS/index.php/user/login' target="_blank">BGAS</a></li>
                              <%-- <li><a href='#'>PICO</a></li>
                               <li><a href='#'>Student Fee Management</a></li>--%>
                            </ul>
                        </div>
                    </div>     
               
                
                <!--        <div class="info">

                    <div class="item">
                        <h4> Brihaspati </h4>
                    </div>

                    <div class="item">
                        <h4> BGAS </h4>
                    </div>

                    <div class="item">
                        <h4> PICO </h4>
                    </div>  

                    <div class="item">
                        <h4> Student Fee Management </h4>
                    </div>

                </div><!-- end info-->
                
                
                    <div class="loginBody">  

                        <div class="left-column">
                            <h:form>     
                                <div class="errMessage">
                                    <rich:messages>
                                        <f:facet name="errorMarker">
                                             <h:graphicImage url="/img/err.png"/>
                                         </f:facet>
                                    </rich:messages>
                                </div> 
 
                                <label>
                                    <span>Email :</span>
                                    <h:inputText label="User Name" value="#{UserBean.userName}" />
                                </label>

                                <label>
                                    <span>Password :</span>
                                    <h:inputSecret label="Password" value="#{UserBean.password}"/>
                                </label>

                                <input type="hidden" name="userrole" value="user" />

                                <label>
                                    <span>&nbsp;</span>

                                    <h:commandButton  action="#{UserBean.validate}" value="Login" styleClass="button"/><br/>

                                </label>           
                            </h:form>

                        </div> <!-- end Left Column -->

                        <div class="right-column">
                            <div class="question">
                                <h4> Don't have an account?</h4>
                                <a  href="adminLogin/OrgMain.jsf" style="font-weight:bold">Institute Admin Registration</a><br/>
                                <h:outputText value="For user registration please contact to Institute Admin" style="font-size:12px"/>
                                <br/>
                               <%--<a  href="adminLogin/EmployeeProfile.jsf" >Employee Registration</a>--%>
                            </div>

                               <%--<div class="question">
                                <h4> Forgot Password?</h4>
                                <a  href="" >Reset it here</a>
                            </div>--%>
                            <div id='links'>
                                <ul>
                                    <li><a href="adminLogin/ForgotPassword.jsf " > Forgot Password? </a></li>
                                   <li><a href="http://202.141.40.215/WebApp/wiki/index.php/PayRoll_System" target="_blank">Help Document</a></li>
                                   <li><a href='adminLogin/brihaspatiMainLogin.jsf' target="_blank">Brihaspati Server Authentication</a></li>
                                   <li><a href='http://202.141.40.215/WebApp/wiki/index.php/Release_Notes' target="_blank">Release Notes</a></li>
                                   <li><a href="usermanual/payAdmin/adminInterface.html">User Manual of Admin Interface</a></li>
                                   <li><a href="usermanual/instAdmin/instAdminInterface.html">User Manual of Institute Admin Interface</a></li>
                                   <li><a href="usermanual/payrollEmp/employeeInterface.html">User Manual of Employee Interface</a></li>
                                   <%--<li><a href="http://www.sakshat.ac.in">NMEICT home page</a></li>--%>
                                   
                                </ul>
                            </div>

                        </div>  <!-- end Right Column -->
                    </div> <!-- end of login body -->
               
            
                </div> <!--end page Body -->
        
                <div class="footer">
                    <div class="footer-content">
                
                        <p> Opensource component developed by IIT Kanpur , Intially was supported by MHRD.</p>
                        <p> For problems at this site send email to ETRG, IIT Kanpur
                            For reporting bugs, suggestion, feature request, and maintainence support
                            post at brihaspati_ERP_mission@yahoogroups.com</p>
                 <!--       
                        <div class="secondary-links">
                            <a href="#" >Brihaspati</a>
                            <a href="#" >BGAS</a>
                            <a href="#" >PICO</a>
                            <a href="#" >Student Fee Management</a>
                        </div>      -->
                        <p class="copyright"> © 2015 PayrollSys IITK.</p>
                   </div>   
                </div> <!-- end footer-->    
                   
        
          <%--     <div class="header" syle="height:20%;">
                <h:graphicImage alt="payroll" url="/img/payrollheader.png" style="width:100%;"/>
                <rich:panel style="background-color:#425C83; Color:white">
               <a href="http://172.26.81.189:8080/PayrollSys/" style="color:white;font-family:arial;font-weight:bold;text-decoration:none" onclick="return false;">PAYROLL SYSTEM</a></font>   &nbsp;   
               <a href="http://202.141.40.215:8080/brihaspati/servlet/brihaspati" style="color:white;font-family:arial;font-weight:bold;text-decoration:none">BRIHASPATI</a> &nbsp;
               <a href="http://202.141.40.215/~brihaspati/BGAS/index.php/user/login" style="color:white;font-family:arial;font-weight:bold;text-decoration:none">BGAS</a> &nbsp;
               <a href="http://202.141.40.218:8080/pico/Administration/Index.action" style="color:white;font-family:arial;font-weight:bold;text-decoration:none">PICO</a>  &nbsp;
               <a href="http://www.ignouonline.ac.in/sakshatproposal/default.aspx" style="color:white;font-family:arial;font-weight:bold;text-decoration:none" onclick="return false;">STUDENT FEES MANAGEMENT SYSTEM</a>
               </rich:panel>   
               </div>   --%>
            
            
    <%--     
            <div class="sidebar" style="float:left; width:20%;">
                
                  <rich:panel header="Important Link" style="width:100%;float:left;text-align:left; height:720px ">
                   <br/>
                   <%--<h:outputText value=" Important Link" style="font-weight:bold"/>--%>
    <%--                <a href="adminLogin/OrgMain.jsf" style="font-weight:bold;">Register New Institute</a><br/> 
                   <a href="http://www.ignouonline.ac.in/sakshatproposal/default.aspx" style="font-weight:bold;">NMEICT home page</a><br/>
                   <a href="adminLogin/index.html" style="font-weight:bold;" onclick="return false;">Release Notes</a><br/>
                   <a href="docs/indexnew.html" style="font-weight:bold;" onclick="return false;">User Manual</a><br/>
                   <a href="docs/ModuleHelp/index.jsp" style="font-weight:bold;" onclick="return false;">HTML Help</a><br/>
                   <a href="adminLogin/brihaspatiMainLogin.jsf" style="font-weight:bold;">Aunthenticate From Brihaspati Server  </a> 
                                    
                  </rich:panel>
            </div>  --%>
            
       <%--     <div class="main" style="float:left; width:80%;"> 
             <h:form>    
                <rich:panel header="Please Login" style="width:100%;float:left;text-align:left;height:720px"> 
                   <%--<h:outputText value=" Please Login"  style=" float:right;text-align:left;font-size:20px;"/>--%>
        <%--            <br/>
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
       <%--             </h:panelGrid>
                    <input type="hidden" name="userrole" value="user" />
                                     
               </rich:panel>
           </h:form>
            </div>
        </div>      --%>
            
                
                
                
           
           
           
   <%--    
       
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

       </div> <!-- end Wrapper -->                
      </div> <!-- end Container -->
    </f:view>
</html>
