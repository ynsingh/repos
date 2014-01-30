<%-- 
    Document   : error
    Created on : Aug 26, 2011, 3:05:47 PM
Author     : IGNOU Team
Version      : 1
    Version: 1
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
        <title>ePortfolio Home</title>
        <link href="<s:url value="/css/master.css"/>" rel="stylesheet" type="text/css" />
        <link href="<s:url value="/css/collapse.css"/>" rel="stylesheet" type="text/css" />
        <link href="<s:url value="/css/skin.css"/>" rel="stylesheet" type="text/css" />
        <script type="text/javascript" src="<s:url value="/js/jquery-1.6.4.min.js"/>"></script>
        <script type="text/javascript" src="<s:url value="/js/jquery.jcarousel.min.js"/>"></script>
        <script type="text/javascript" src="<s:url value="/js/expand.js"/>"></script>
        <script>
            $(function() {
                $("#accordion").accordion();
            });
        </script>
        <script type="text/javascript">
            jQuery(document).ready(function() {
                jQuery('#myportfolioicon').jcarousel();
            });
            jQuery(document).ready(function() {
                jQuery('#myconnectionicon').jcarousel();
            });
            jQuery(document).ready(function() {
                jQuery('#myeducationicon').jcarousel();
            });
            jQuery(document).ready(function() {
                jQuery('#mybuildericon').jcarousel();
            });
        </script>
    </head>
    <body>
        <%
            final Logger logger = Logger.getLogger(this.getClass());
            String ipAddress = request.getRemoteAddr();
            logger.warn(session.getAttribute("user_id") + " Accessed from: " + ipAddress + " at: " + new Date());
            String role = session.getAttribute("role").toString();
            if (session.getAttribute("user_id") == null) {
                session.invalidate();
                response.sendRedirect("../Login.jsp");
            }
        %>
        <div class="w100 fl-l">
            <div class="w990p mar0a">
                <!--Header Starts Here-->
                <s:include  value="/Header.jsp"/>
                <!--Header Ends Here-->

                <!--Middle Section Starts Here-->
                <div class="w100 fl-l">
                    <div class="middle_bg">
                        <!--Left box Starts Here-->
                        <s:include  value="/Left-Nevigation.jsp"/> 
                        <!--Left box Ends Here-->

                        <!--Right box Starts Here-->
                        <div class="right_box">
                            <div class="my_account_bg">Page Under Construction</div>
                            <div class="w100 fl-l tc fbld fcgreen">
                                <s:property value="msg"/>
                            </div>
                            <div class="tab_btn_1 w110p"><a onclick="history.go(-1);"><img src="<s:url value="/icons/back-arrow.png"/>" class="w25p" /></a></div>
                                <s:if test="hasActionErrors()">
                                <div class="errors">
                                    <s:actionerror/>
                                </div>
                            </s:if>
                            <s:if test="hasActionMessages()">
                                <div class="welcome">
                                    <s:actionmessage/>
                                </div>
                            </s:if>
                            <h4 class="fcred">
                                <s:property value="msg" />
                            </h4>  
                            <!--Right box Starts Here-->
                        </div>
                    </div>
                    <!--Middle Section Ends Here-->
                </div>
            </div>
        </div>
        <s:include  value="/Footer.jsp"/>  
    </body>
</html>