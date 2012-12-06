<%-- 
    Document   : login
    Created on : Sep 3, 2011, 4:21:46 PM 
    Author     : IGNOU Team
    Version    : 1
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="sx" uri="/struts-dojo-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title>Welcome to ePortfolio</title>
        <link href="<s:url value="/css/master.css"/>" rel="stylesheet" type="text/css" />
        <link href="<s:url value="/css/collapse.css"/>" rel="stylesheet" type="text/css" />
        <link href="<s:url value="/css/skin.css"/>" rel="stylesheet" type="text/css" />
        <script type="text/javascript" src="<s:url value="/js/jquery-1.6.4.min.js"/>"></script>
        <sx:head/>
        <script type="text/javascript" src="<s:url value="/js/expand.js"/>"></script>
        <script>
            $(function() {
                $( "#accordion" ).accordion();
            });
        </script>
        
    </head>
    <body>
        <div class="w100 fl-l">
            <div class="w990p mar0a">
                <!--Header Starts Here-->
                <div class="w100 fl-l">
                    <div class="header">
                        <div class="w100 fl-l"><img src="<s:url value="/images/header.png"/>" alt="" width="980" height="100" /></div>
                    </div>
                    <div class="menu_bg">
                        <div class="wau fl-l"><img src="images/blank.gif" alt="" width="20" height="10" /></div>
                        <div class="eportfolio_txt">ePORTFOLIO</div>
                        <div class="menu">&nbsp;</div>
                        <div class="menu_arrow_img">&nbsp;</div>
                        <div class="img_panel">
                            <div class="my_profile">&nbsp;</div>
                            <div class="profile_img">&nbsp;</div>
                        </div>
                    </div>
                    <!--Header Ends Here-->
                </div>
                <!--Middle Section Starts Here-->
                <div class="w100 fl-l">
                    <div class="middle_bg">
                        <div class="login_cont">
                            <div class="w50 fl-l mart70">
                                <table width="100%" class="fl-l" border="0" cellspacing="0" cellpadding="2">
                                    
                                    <s:form action="Login" method="post" theme="simple">
                                        <tr><td colspan="4" align="center"><s:property value="msg"/></td></tr>
                                         <tr>
                                            <td width="10%">&nbsp;</td>
                                            <td>Username</td>
                                            <td><s:textfield name="email_id" /></td>
                                            <td width="5%">&nbsp;</td>
                                        </tr>
                                        <tr>
                                            <td width="10%">&nbsp;</td>
                                            <td>Password</td>
                                            <td><s:password name="password" /></td>
                                            <td width="5%">&nbsp;</td>
                                        </tr>
                                        <tr>
                                            <td width="10%">&nbsp;</td>
                                            <td>&nbsp;</td>
                                            <td><s:submit type="image" src="images/login_btn.gif" /></td>
                                            <td width="5%">&nbsp;</td>
                                        </tr>
                                            <s:token/>
                                    </s:form>

                                    <tr>
                                        <td width="10%">&nbsp;</td>
                                        <td>&nbsp;</td>
                                        <td><input name="" type="checkbox" value="" />
                                            Remember me</td>
                                        <td width="5%">&nbsp;</td>
                                    </tr>
                                    <tr>
                                        <td width="10%">&nbsp;</td>
                                        <td>&nbsp;</td>
                                        <td><a href="<s:url value="/ForgotPassword.jsp"/>">Forgot Password</a><br />
                                            <a href="http://eportfolio.edrp.ac.in:8080/ePortfolio/remote_Login.jsp">Login with Brihaspati</a></td>
                                        <td width="5%">&nbsp;</td>
                                    </tr>
                                </table>
                            </div>
                            <div class="w50 fl-l mart50">
                                <table width="100%" class="fl-l" border="0" cellspacing="3" cellpadding="0">
                                    <tr>
                                        <td width="15#">&nbsp;</td>
                                        <td width="75%" class="fs12 fcred fbld"><a href="#">&nbsp;</a></td>
                                        <td width="10%">&nbsp;</td>
                                    </tr>
                                    <tr>
                                        <td>&nbsp;</td>
                                        <td><a href="<s:url value="/Registration/StudentRegistration.jsp"/>"><img src="images/student_btn.gif" alt="" width="69" height="28" /></a>
                                            <a href="<s:url value="/Registration/FacultyRegistration.jsp"/>"><img src="images/faculty_btn.gif" alt="" width="69" height="28" class="marl15" /></a></td>
                                        <td>&nbsp;</td>
                                    </tr>
                                </table>
                            </div>
                        </div>
                    </div>
                    <!--Middle Section Ends Here-->
                </div>
            </div>
        </div>
        <!--Footer Section Starts Here-->
        <div class="footer">
            <div class="f_menu">
                <%
                    session.putValue("requri", request.getRequestURI());
                    //  out.println(request.getRequestURI());
%>
                <a href="<s:url value="/About.jsp"/>" target="_Blank">About</a> | <a href="<s:url value="/Feedback.jsp"/>" target="_Blank">Feedback</a> | <a href="<s:url value="/Help.jsp"/>" target="_Blank">Help</a> | <a href="#">Sitemap</a> | <a href="<s:url value="/Contact.jsp"/>" target="_Blank">Contact Us</a>
            </div>
        </div>
        <div class="footer_panel">
            <div class="footer_txt">
                <div class="wau fl-l tl">&COPY; 2011-12, MHRD. All Rights are Reserved</div>
                <div class="wau fl-r tr">Designed and Developed by eGyanKosh,Indira Gandhi National Open University</div>
            </div>
            <!--Footer Section Ends Here-->
        </div>
    </body>
</html>
