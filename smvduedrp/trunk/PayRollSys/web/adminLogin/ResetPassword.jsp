<%-- 
    Document   : ResetPassword
    Created on : Aug 31, 2015, 3:17:49 PM
    Author     : Om Prakash<omprakashkgp@gmail.com> IITK
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsf/core" %>
<%@ taglib prefix="h" uri="http://java.sun.com/jsf/html" %>
<%@ taglib uri="http://richfaces.org/a4j" prefix="a4j"%>
<%@ taglib uri="http://richfaces.org/rich" prefix="rich"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    

<!DOCTYPE html>
<f:view>
<html>
    <head>
        <link rel="stylesheet" type="text/css" href="../css/RegistrationPage.css" />
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title> Reset Password </title>
    </head>
    <body>
        <c:if test="${param.rkey == null}">
            <jsp:forward page="error.jsp" />
        </c:if>
        
        <div class="container">
            
            <div class="wrapper">
            
                <div class="header">

                    <h:graphicImage alt="payroll" url="/img/payrollheader.png" style="width:100%;"/>

                </div>
                    
                    
                <div class="pageBody">
                    
                    <div class="menu-wrapper">

                        <div id='cssmenu' class="align-center">
                            <ul>
                                <li><a href="../Login.jsf"> Login Page </a></li>
                                <li><a href='http://202.141.40.215:8080/brihaspati/servlet/brihaspati' target="_blank">Brihaspati</a></li>
                                <li><a href='http://202.141.40.215/~brihaspati/BGAS/index.php/user/login' target="_blank">BGAS</a></li>
                               <li><a href='#'>PICO</a></li>
                               <%--  <li><a href='#'>Student Fee Management</a></li>--%>
                            </ul>
                        </div>
                    </div>
                    
                <div class="registrationForm">    
                
                    <h:form id="frmTest" style=" margin-top: 70px;" >

                   <div id="msg">
                        <rich:messages>
                            <f:facet name="infoMarker">
                                <h:graphicImage url="/img/success.png"/>
                                <h:outputText value="kk"/>
                            </f:facet>
                            <f:facet name="errorMarker">
                                <h:graphicImage url="/img/err.png"/>
                            </f:facet>
                        </rich:messages>
                     </div>    
                    <div class="form_lables" style="height: 400px">
                        <%--${param.rkey}--%>
                        
                        <label>
                            <input type="hidden" name="rkey" value="${param.rkey}" />
                        </label>    
    
                        <label>
                                    <span> New Password :</span>
                                    <h:inputSecret label=" newPass" requiredMessage="Password can not be empty" required="true" value="#{ForgotPasswordBean.password}" />                     
                        </label>
                       
                        <label>
                                    <span> Re Password :</span>
                                    <h:inputSecret id="rePass" requiredMessage="Password can not be empty" required="true" value="#{ForgotPasswordBean.repassword}"/>
                                    
                        </label>
                                           
                        <label>
                                <span>&nbsp;</span>
                                <a4j:commandButton value="Submit" style="width: 100px; height: 35px; " reRender="up" action="#{ForgotPasswordBean.reSetPassword}" styleClass="button"/>&nbsp;
                                <%-- <a4j:commandButton value="Reset" onclick="this.form.reset()" styleClass="button"/> --%>
                        </label>    
                                              
                    </div>   
                    </h:form>
                    
                    </div>
                </div>
                    
                    <div class="footer">
                    <div class="footer-content">
                
                        <p> Opensource component developed by IIT Kanpur, Initially was supported by MHRD</p>
                        <p> For problems at this site send email to ETRG, IIT Kanpur
                            For reporting bugs, suggestion, feature request, and maintainence support
                            post at brihaspati_ERP_mission@yahoogroups.com</p>
                         <p class="copyright"> © 2015 PayrollSys IITK.</p>
                   </div>   
                </div> <!-- end footer--> 
                
            </div> <!-- end Wrapper -->                
            </div> <!-- end Container -->

    </body>
</html>
</f:view>