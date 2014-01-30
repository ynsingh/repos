<%-- 
    Document   : remote_success
    Created on : May 22, 2012, 3:05:03 PM
    Author     : IGNOU Team
--%>

<%@page import="org.hibernate.tool.hbm2x.StringUtils"%>
<%@page import="org.iitk.brihaspati.modules.utils.security.ReadNWriteInTxt"%>
<%@page import="org.iitk.brihaspati.modules.utils.security.EncrptDecrpt"%>
<%@page import="org.iitk.brihaspati.modules.utils.security.RemoteAuthProperties"%>
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
                                <a href="<s:url value="/Index.jsp"/>">Login</a>|<a id="registerlink" href="#">New User</a>
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
                            <div class="my_account_bg">Remote Success Page</div>
                            <div class="v_gallery">
                                <div class="w100 fl-l tc fbld fcgreen"><s:property value="feedbackmsg"/></div>
                                <%
                                    String encriptData = request.getParameter("encd");
                                    String keyedHash = request.getParameter("hash");
                                    String randomStr = request.getParameter("rand");
                                    out.println("Encripted Data: " + encriptData + "<br>");
                                    out.println("Randam String: " + randomStr + "<br>");
                                    out.println("Keyed Hash: " + keyedHash + "<br>");
                                %>
                                <hr/>
                                <%
                                    String sourceid = "ignou_eportfolio";
                                    String hdir = System.getProperty("user.home");
                                    out.println("Home Location: " + hdir + "<br>");
                                    String path = hdir + "/Remote_auth/brihaspati3-remote-access.properties";
                                    String filepath = hdir + "/Remote_auth/remote-user.txt";
                                    out.println("Property File Path: " + path + "<br>");
                                    String line = ReadNWriteInTxt.readLin(path, sourceid);
                                    out.println("Line: " + line + "<br>");
                                    String skey = org.apache.commons.lang.StringUtils.substringBetween(line, ";", ";");
                                    String serverUrl = org.apache.commons.lang.StringUtils.substringAfterLast(line, ";");
                                    out.println("Server Key: " + skey + "<br>");
                                    out.println("Server URL: " + serverUrl + "<br>");
                                %>
                                <hr/>
                                <%
                                    String enUrl = EncrptDecrpt.decrypt(encriptData, sourceid);
                                    out.println("Decrypted Data: " + enUrl);
                                    String EmailId = org.apache.commons.lang.StringUtils.substringBetween(enUrl, "email=", "&");
                                    out.println("<br/> Email ID: " + EmailId);
                                    String SessionStr = org.apache.commons.lang.StringUtils.substringAfter(enUrl, "sess=");
                                    out.println("<br/>Session Value: " + SessionStr);
                                    String hashcode = EncrptDecrpt.keyedHash(EmailId, randomStr, skey);
                                    out.println("<hr/><br/>Hash Code: " + hashcode);
                                    out.println("<hr/>Matching HashCode with KededHash<br/>");
                                    if (hashcode.equals(keyedHash)) {
                                        out.println("<hr/>Hash Code Matched.");
                                        boolean verified = ReadNWriteInTxt.readF(filepath, EmailId + ";" + SessionStr);
                                        out.println("<hr/>Varified: " + verified);
                                        if (verified) {
                                            // response.sendRedirect("PassAuth?email=" + EmailId + "&sess=" + SessionStr);
                                        } else {
                                        }
                                    } else {
                                        out.println("Hash Code Not Matched.");
                                    }
                                    //  response.sendRedirect("http://14.139.40.226:80/ePortfolio/remote_Login.jsp");
                                %>
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
                <a href="<s:url value="/About.jsp"/>" target="_Blank">About Us</a> | <a href="#">Sitemap</a> |  <a href="<s:url value="/Feedback.jsp"/>" target="_Blank">Feedback</a> | <a href="<s:url value="/Help.jsp"/>" target="_Blank">Help</a> | <a href="<s:url value="/Contact.jsp"/>" target="_Blank">Contact Us</a>
            </div>
        </div>
        <div class="footer_panel">
            <div class="footer_txt">
                <div class="wau fl-l tl">ePortfolio &COPY; 2011-13, MHRD. All Rights Reserved</div>
                <div class="wau fl-r tr">Developed &amp; Maintained By: eGyanKosh Team, IGNOU</div>
            </div>
            <!--Footer Section Ends Here-->
        </div>
    </body>
</html>