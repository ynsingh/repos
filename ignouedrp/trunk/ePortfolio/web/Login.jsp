<%-- 
    Document   : login
    Created on : Sep 3, 2011, 4:21:46 PM 
    Author     : IGNOU Team
    Version    : 1
--%>
<%@page import="java.io.Serializable"%>
<%@page import="java.util.Date"%>
<%@page import="org.apache.log4j.Logger"%>
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
                $("#accordion").accordion();
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
                        <div class="mar0a tc fbld fcred">
                            <s:property value="msg"/>
                            <%
                                String ipAddress = request.getRemoteAddr();
                                // out.println(ipAddress);
                            %>
                        </div>
                        <div class="login_cont">
                            <div class="w68 fl-l mart70">
                                <table width="100%" class="fl-l" border="0" cellspacing="0" cellpadding="2">
                                    <s:form action="Login" method="post" theme="simple" namespace="/">
                                        <s:token/>
                                        <input type="hidden" name="clientIP" value="<% out.println(ipAddress);%>"/><tr>
                                            <td width="10%">&nbsp;</td>
                                            <td width="80%"><s:textfield cssClass="txt_field" placeholder="Username" name="email_id" /></td>
                                            <td width="10%">&nbsp;</td> 
                                        </tr>
                                        <tr>
                                            <td width="10%">&nbsp;</td>
                                            <td><s:password cssClass="txt_field mart5" placeholder="Password"  name="password" /></td>
                                            <td width="5%">&nbsp;</td>
                                        </tr>
                                        <tr>
                                            <td width="10%">&nbsp;</td>
                                            <td>
                                                <p><input name="" type="checkbox" value="" />
                                                    Remember me</p><p class="wau fl-l mart5"><a href="<s:url value="/ForgotPassword.jsp"/>">Forgot Password</a></p>
                                                <p class="wau fl-r mart5"><s:submit type="image" src="images/login_btn.gif" /></p>
                                                <p class="wau fl-l mart5"><a href="http://14.139.40.232:8080/ePortfolio/remote_Login.jsp">Login with Brihaspati</a></p>

                                            </td>
                                            <td width="5%">&nbsp;</td>
                                        </tr>
                                    </s:form>
                                </table>
                            </div>
                            <div class="w32 fl-l mart50">
                                <table width="100%" class="fl-l" border="0" cellspacing="3" cellpadding="0">
                                    <tr>
                                        <td width="15#">&nbsp;</td>
                                        <td width="75%" class="fs12 fcred fbld"><a href="#">&nbsp;</a></td>
                                        <td width="10%">&nbsp;</td>
                                    </tr>
                                    <tr>
                                        <td>&nbsp;</td>
                                        <td>
                                            <a href="<s:url value="/RegistrationForm.jsp"/>"><img src="images/sign_up_btn.gif" alt="" width="69" height="28" class="mart90 " /></a></td>
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
                <s:a action="ShowAboutUs" namespace="/Administrator">About Us</s:a> | <a href="<s:url value="/Feedback.jsp"/>" target="_Blank">Feedback</a> | <a href="<s:url value="/Help.jsp"/>" target="_Blank">Help</a> | <a href="#">Sitemap</a> | <s:a action="ShowContactUs" namespace="/Administrator">Contact Us</s:a>
            </div>
        </div>
        <div class="footer_panel">
            <div class="footer_txt">
                <div class="wau fl-l tl">ePortfolio &COPY; 2011-13, MHRD. All Rights Reserved</div>
                <div class="wau fl-r tr">Designed and Developed by eGyanKosh,Indira Gandhi National Open University</div>
            </div>
            <!--Footer Section Ends Here-->
        </div>
    </body>
</html>
