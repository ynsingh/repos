<%-- 
    Document   : remote_login
    Created on : May 22, 2012, 3:04:56 PM
    Author     : IGNOU Team
--%>

<%@page import="java.io.Serializable"%>
<%@page import="java.util.Date"%>
<%@page import="org.apache.log4j.Logger"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title>Welcome to ePortfolio: Remote Login</title>
        <link href="<s:url value="/css/master.css"/>" rel="stylesheet" type="text/css" />
        <link href="<s:url value="/css/collapse.css"/>" rel="stylesheet" type="text/css" />
        <link href="<s:url value="/css/skin.css"/>" rel="stylesheet" type="text/css" />
        <script type="text/javascript" src="<s:url value="/js/jquery-1.6.4.min.js"/>"></script>

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
                        <div class="menu">
                            <a href="<s:url value="/Login.jsp"/>">Home</a>
                        </div>
                        <div class="menu_arrow_img">&nbsp;</div>
                        <div class="img_panel">
                            <div class="my_profile"> 
                                &nbsp;
                            </div>
                            <div class="profile_img">&nbsp;</div>
                        </div>
                    </div>
                    <!--Header Ends Here-->
                </div>
                <!--Middle Section Starts Here-->
                <div class="w100 fl-l">
                    <div class="middle_bg">
                        <!--Right box Starts Here-->
                        <div class="middle_cont">
                            <div class="my_account_bg">Remote Login</div>
                            <div class="v_gallery">
                                <div class="w100 fl-l"><img src="images/blank.gif" alt="" width="600" height="100" /></div>
                                <div class="w100 fl-l tc fbld fcgreen"><s:property value="msg"/></div>
                                <s:form method="post" action="remoteLogin" namespace="/">
                                    <table width="50%" class="mar0a" cellpadding="0" border="0" cellspacing="0">
                                        <s:textfield name="email" label="Email Id:"/>
                                    </table>
                                    <div class="w20 mar0a">
                                        <s:submit value="Login" align="center"/>
                                    </div>
                                </s:form>
                            </div>
                            <!--Right box End Here-->
                        </div>
                        <div class="w100 fl-l"><img src="images/blank.gif" alt="" width="600" height="30" /></div>
                    </div>
                    <!--Middle Section Ends Here-->
                </div>
            </div>
        </div>
        <!--Footer Section Starts Here-->
        <div class="footer">
            <div class="f_menu"> 
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
