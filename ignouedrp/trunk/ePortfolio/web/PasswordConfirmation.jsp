<%-- 
    Document   : PasswordConfirmation
    Created on : Dec 27, 2011, 11:02:34 AM
    Author     : IGNOU Team
--%>

<%@page import="java.io.Serializable"%>
<%@page import="java.util.Date"%>
<%@page import="org.apache.log4j.Logger"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib  prefix="sx"  uri="/struts-dojo-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title>Welcome to ePortfolio</title>
        <%
            String theme = request.getParameter("theme");
            if (theme == null) {
        %>
        <%
            theme = "/theme4";

        } else {%>
        <%
                theme = request.getParameter("theme");
            }
            String PageTheme = "./style/" + theme + "/style.css";
            String calTheme = "./style/" + theme + "/date_cal.css";
            String login = "./Login.jsp" + "?theme=" + theme;
            String Registration = "./Registration/RegistrationForm.jsp" + "?theme=" + theme;
        %>

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
                                <a href="<%=login%>">Login</a>|<a id="registerlink" href="#">New User</a>
                            </div>
                            <div class="profile_img">&nbsp;</div>
                        </div>
                    </div>
                    <!--Header Ends Here-->
                </div>
                <!--Middle Section Starts Here-->
                <div class="w100 fl-l">
                    <div class="middle_bg">
                        <!--Left box Starts Here-->
                        <div class="left_box">
                            <div class="u_name">

                            </div>
                            <div class="u_events">Upcoming Events</div>
                            <s:url id="eventI" value="/Events/eShowEventInfo.action" />
                            <!-- Calling Actions -->
                            <sx:div href="%{#eventI}" >

                            </sx:div>
                            <!--Left box Ends Here-->
                        </div>
                        <!--Right box Starts Here-->
                        <div class="right_box">
                            <div class="my_account_bg">Password Confirmation</div>
                            <div class="v_gallery">
                                <div class="w100 fl-l tc fbld fcgreen"><s:property value="feedbackmsg"/></div>
                                <table cellpadding="2" cellspacing="2" border="0" align="center">
                                    <tr><td>
                                            <center>We have Mailed Your Password To your E-Mail Id,Please check to Login Again.<br/><br/>
                                            </center>
                                            <a href="<s:url value="/Login.jsp"/>"><center><b><font color="blue">Click Here to Login</font></b></center></a>
                                        </td></tr>
                                </table>
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
                <a href="<s:url value="/About.jsp"/>" target="_Blank">About Us</a> | <a href="#">Sitemap</a> |  <a href="<s:url value="/Feedback.jsp"/>" target="_Blank">Feedback</a> | <a href="<s:url value="/Help.jsp"/>" target="_Blank">Help</a> | <s:a action="ShowContactUs" namespace="/Administrator">Contact Us</s:a>
            </div>
        </div>
        <div class="footer_panel">
            <div class="footer_txt">
                <div class="wau fl-l tl">Copyright 2008 IGNOU, MHRD. All right reserved</div>
                <div class="wau fl-r tr">Developed &amp; Maintained By: eGyanKosh Team, IGNOU</div>
            </div>
            <!--Footer Section Ends Here-->
        </div>
    </body>
</html>