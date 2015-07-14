<%-- 
    Document   : WelcomePage
    Created on : May 25, 2015, 3:46:06 PM
    Author     : yuvraj
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@taglib prefix="h" uri="http://java.sun.com/jsf/html"%>
<%@ taglib uri="http://richfaces.org/a4j" prefix="a4j"%>
<%@ taglib uri="http://richfaces.org/rich" prefix="rich"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<f:view>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
            <title>Welcome Page</title>
            <title>Payroll System | Login</title>
            <link rel="stylesheet" type="text/css" href="css/LoginPage.css" /> 
          
            <style>
                *{
                    margin: 0px;
                    padding: 0px;
                }            
                html, body{
                    width: 100%;
                    height: 100%;
                    padding: 0px;
                    margin: 0px;
                }

                body{
                    background: #fff;
                }

                .container{
                    background-image: none !important;
                    margin: 0 auto;
                    height: 100%;
                    max-width: 1366px;
                }

                .wrapper{
                    margin:0 auto;
                    height: 95%;
                    padding-top: 50px;
                }

                .header{
                    height: 15%;
                    width: 100%;
                }

                .header img{
                    width: 100%;
                    height:100%;
                }

                /* ###### pageBody #########*/
                .pageBody {
                    background: #fff;
                    padding: 0px 30px 0px 30px;
                    box-shadow: rgba(187, 187, 187, 1) 0 0px 20px -1px;
                    -webkit-box-shadow: rgba(187, 187, 187, 1) 0 0px 20px -1px;
                    font: 18px Arial, Helvetica, sans-serif;
                    color: #333;
                    border-radius: 0px;
                    -webkit-border-radius: 0px;
                    height: 70%;
                }
                
                .institutes{
                    margin: 0 auto;
                    display: block;
                    position: relative;
                    padding-top: 0%;
                    padding-left: 0%;
                    height: 80%;
                    width: 90%;
                }
                
                .pageBody label {
                    display: block;
                    margin: 0px 0px 5px;
                    text-align: left;
                }
                .pageBody label>span {
                    float: left;
                    width: 25%;
                    text-align: left;
                    padding-right: 10px;
                    margin-top: 10px;
                    color: #969696;
                }
                .pageBody input[type="text"], .pageBody input[type="email"], .pageBody input[type="password"], .pageBody textarea,.pageBody select{
                    color: #555;
                    width: 50%;
                    padding: 3px 0px 3px 5px;
                    margin-top: 2px;
                    margin-right: 6px;
                    margin-bottom: 12px;
                    border: 1px solid #e5e5e5;
                    background: #fbfbfb;
                    height: 20px;
                    line-height:10px;
                    outline: 0;
                    -webkit-box-shadow: inset 1px 1px 2px rgba(200,200,200,0.2);
                    box-shadow: inset 1px 1px 2px rgba(200,200,200,0.2);
                    font-size: 14px;
                }
                .pageBody .button {
                    -moz-box-shadow:inset 0px 1px 0px 0px #3399FF;
                    -webkit-box-shadow:inset 0px 1px 0px 0px #3399FF;
                    box-shadow:inset 0px 1px 0px 0px #6699FF;
                    background:-webkit-gradient( linear, left top, left bottom, color-stop(0.05, #0099FF), color-stop(1, #6699FF) );
                    background:-moz-linear-gradient( center top, #0099FF 5%, #ef027d 100% );
                    filter:progid:DXImageTransform.Microsoft.gradient(startColorstr='#0099FF', endColorstr='#6699FF');
                    background-color:#0099FF;
                    border-radius:9px;
                    -webkit-border-radius:9px;
                    -moz-border-border-radius:9px;
                    border:1px solid #6699FF;
                    display:inline-block;
                    color:#ffffff;
                    font-family:Arial;
                    font-size:15px;
                    font-weight:bold;
                    font-style:normal;
                    height: 40px;
                    line-height: 30px;
                    width:100px;
                    text-decoration:none;
                    text-align:center;
                    text-shadow:1px 1px 0px #0000FF;
                    background-image: none;
                }
                .pageBody .button:hover {
                    background:-webkit-gradient( linear, left top, left bottom, color-stop(0.05,#6699FF), color-stop(1, #3399FF) );
                    background:-moz-linear-gradient( center top, #6699FF 5%, #3399FF 100% );
                    filter:progid:DXImageTransform.Microsoft.gradient(startColorstr='#6699FF', endColorstr='#3399FF');
                    background-color:#6699FF;
                    background-image: none;
                }

                .pageBody .button:active {
                    position:relative;
                    top:1px;
                }

                .pageBody select {
                    background: url('down-arrow.png') no-repeat right, -moz-linear-gradient(top, #FBFBFB 0%, #E9E9E9 100%);
                    background: url('down-arrow.png') no-repeat right, -webkit-gradient(linear, left top, left bottom, color-stop(0%,#FBFBFB), color-stop(100%,#E9E9E9));
                   appearance:none;
                    -webkit-appearance:none;
                   -moz-appearance: none;
                   padding: 3px 0px 3px 5px;
                    text-indent: 0.01px;
                    text-overflow: '';
                    width: 50%;
                    line-height: 15px;
                    height: 30px;
                } 
                
                .pageBody th {
                    text-align: left;
                }

                .footer{
                    height: 10%;
                    background-color: #fff;
                    margin: 10px  auto;
                }


                .footer-content{
                    position: relative;
                    color: #a6a6a6;
                    clear: both;
                    font-family: "Helvetica Neue", Helvetica, Arial, sans-serif;
                    line-height: 20px;
                    padding: 20px 0px 0px 0px;
                    text-align: center;
                    font-size: 12px;
                }

                .secondary-links a{
                    border: none;
                    font: 200 14px "Helvetica Neue", Helvetica, Arial, sans-serif;
                    color: #666;
                    padding: 0px 12px;
                    text-decoration: none;
                }
                
                .secondary-links a{
                    border: none;
                    font: 200 14px "Helvetica Neue", Helvetica, Arial, sans-serif;
                    color: #666;
                    padding: 0px 12px;
                    text-decoration: none;
                }

                @media screen and (max-width: 1366px) {
                    .wrapper{
                    margin:0 auto;
                    height: 95%;
                    padding-top: 0px;
                }
                }
                
                #logout{
                    padding-top: 50px;
                    float: right;
                    padding-right: 100px;
                }

                
                
                
                
            </style>
                        
            
        </head>
        <body>   
           <a4j:keepAlive beanName="UserBean" ajaxOnly="true"/>
            
            <div class="container">
            
                <div class="wrapper">
            
                    <div class="header">

                        <h:graphicImage alt="payroll" url="/img/payrollheader.png" style="width:100%;"/>

                    </div>
                      
                    <%--<div class="pageBody">--%>
            
                   
                       <%-- <div style="background-color:#f5dbdb;width:100%;">
                            <br/>
                           &nbsp;&nbsp;<h:outputText style="font-size:0.9em;font-color:blue;font-weight:bold;" value="Welcome : #{UserBean.userName}  "/>
                            <div align="right" style="padding-right:20px;">
                             <h:form>
                                 <h:commandButton id="lout"  value="Logout" action="#{UserBean.logout}" />&nbsp;
                                
                                
                            </h:form>
                             </div>   
                            
                       </div> --%>
                          <%--<h:commandButton onclick="Richfaces.showModalPanel('cp');" value="Change Password"/>--%>             
                    <div class="pageBody">
                        
                        
                         <div class="menu-wrapper">

                        <div id='cssmenu' class="align-center">
                            <ul>
                                <li><a href='http://202.141.40.215:8080/brihaspati/servlet/brihaspati' target="_blank">Brihaspati</a></li>
                                <li><a href='http://202.141.40.215/~brihaspati/BGAS/index.php/user/login' target="_blank">BGAS</a></li>
                               <li><a href='#'>PICO</a></li>
                              <%-- <li><a href='#'>Student Fee Management</a></li>--%>
                            </ul>
                        </div>
                        
                    </div> 
                        
                        <div class="institutes">
                          <rich:panel style="background-color:#B0C4DE;">
                            <div style="width:100%;">
                           <h:outputText style="font-size:1.4em;font-color:blue;font-weight:bold;" value="Welcome : #{UserBean.userName}  "/>
                                                       
                       </div>  
                           <div align="right" style="padding-right:20px;">
                             <h:form>
                                 <h:commandButton id="lout"  value=" Logout " action="#{UserBean.logout}" />&nbsp;
                                
                                
                            </h:form>
                             </div>  
                          </rich:panel>  
                        <h:form>
                                              
                            <rich:dataTable value="#{UserBean.collegeList}" binding="#{UserBean.dataGrid}"  var="ins" rowKeyVar="row" width="100%"> 
                               <%-- <a4j:keepAlive beanName="UserBean" ajaxOnly="true"/>--%>
                                <%--<a4j:keepAlive beanName="LoggedEmployee" ajaxOnly="true"/>--%>
                               <%-- <rich:column>
                                    <f:facet name="header" >
                                        <h:outputText value=""/>
                                    </f:facet>
                                    <h:outputText value="#{ins.userOrgCode}"/>
                                </rich:column>--%>
                                <rich:column>
                                    <f:facet name="header" >
                                        <h:outputText value="Institute Name"/>
                                    </f:facet>
                                    <h:outputText value="#{ins.orgName}" />
                                </rich:column>
                                                                
                                <rich:column>
                                    <f:facet name="header">
                                     <h:outputText value="Login As Institute Admin"/>
                                    </f:facet>
                                  <%--  <a4j:commandLink  styleClass="no-decor" ajaxSingle="true" immediate="true" action="#{UserBean.login}" value="Click Here" 
                                                      disabled="#{UserBean.linkActive}" style="color: #{UserBean.linkActive == 'true' ? 'grey':'' }" > --%>
                                  <a4j:commandLink  styleClass="no-decor" ajaxSingle="true" immediate="true" action="#{UserBean.login}" value="Click Here" 
                                                     disabled="#{ins.linkActive}"  style="color: #{ins.linkActive == 'true' ? 'grey':'' }" >
                                  
                                    <%--onclick="this.disabled=true; #{rich:component('busy')}.show()" oncomplete="this.disabled=false; #{rich:component('busy')}.hide()" execute="@form"> --%>
                                    <a4j:actionparam name="insid" value="#{ins.userOrgCode}" />
                                    <f:setPropertyActionListener value="#{ins}" target="#{UserBean.editedRecord}" />
                                    </a4j:commandLink>
                                   
                            
                                   
                                </rich:column>
                                <%-- Add by seema ============================ --%>
                                <rich:column>
                                    <f:facet name="header">
                                     <h:outputText value="Login As Employee"/>
                                    </f:facet>
                                   
                                     <a4j:commandLink  styleClass="no-decor" ajaxSingle="true" immediate="true" action="#{UserBean.EmployeeLogin}" value="Click Here" 
                                     oncomplete="#{UserBean.profile == null}; #{rich:component('confirmPane')}.show()"> 
                                                       
                                     <a4j:actionparam name="insid" value="#{ins.userOrgCode}" />
                                    <f:setPropertyActionListener value="#{ins}" target="#{UserBean.editedRecord}" />
                                    </a4j:commandLink>
                                    
                                </rich:column>
                                <%-- ========================= --%>
                                              
                            </rich:dataTable>
                            
                                 <rich:modalPanel id="confirmPane" autosized="true"  width="400" height="80">
                                your profile as an employee does not exist. proceed as an Institute Admin and create an employee profile for yourself. 
                                <br/><br/><a4j:commandButton value="    OK      " onclick="#{rich:component('confirmPane')}.hide(); return false;" style="padding:center;"/>
                                                          
                                </rich:modalPanel>
                            
                             </h:form>

                            <rich:panel style="background-color:#B0C4DE;" >   </rich:panel>
                        </div>     
                    </div> <%-- end of pageBody --%>
                    
                     <div class="footer">
                    <div class="footer-content">
                
                        <p> Opensource component developed by IIT Kanpur, Initially was supported by MHRD.</p>
                        <p> For problems at this site send email to ETRG, IIT Kanpur
                            For reporting bugs, suggestion, feature request, and maintainence support
                            post at brihaspati_ERP_mission@yahoogroups.com</p>
                       
                        <p class="copyright"> © 2015 PayrollSys IITK.</p>
                   </div>   
                </div> <!-- end footer-->    
                    
                    
              <%--  </div> --%>
            </div>
        </body>
    </html>
</f:view>
