<%-- 
    Document   : ShowContactUs
    Created on : Dec 27, 2012, 1:22:29 PM
    Author     : Amit
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
        <title>Contact Us</title>
        <link href="<s:url value="/css/master.css"/>" rel="stylesheet" type="text/css" />
        <link href="<s:url value="/css/collapse.css"/>" rel="stylesheet" type="text/css" />
        <link href="<s:url value="/css/skin.css"/>" rel="stylesheet" type="text/css" />
        <script type="text/javascript" src="<s:url value="/js/jquery-1.6.4.min.js"/>"></script>
        <script type="text/javascript" src="<s:url value="/js/expand.js"/>"></script>
        <script type="text/javascript" src="<s:url value="/js/global.js"/>"></script>
        <script>
            $(function() {
                $("#accordion").accordion();
            });
        </script>
    </head>
    <body><%
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
                        <s:include value="/Left-Nevigation.jsp"/>
                        <!--Left box Ends Here-->
                        <!--Right box Starts Here-->
                        <div class="right_box">
                            <div class="my_account_bg">Contact Us</div>
                            <div class="v_gallery">
                                <div class="w98 mar0a">

                                    <div class="w100 fl-l mart5">
                                        <div class="v_gallery">
                                            <table align="center">
                                                <s:iterator value="contactList">
                                                    <tr>
                                                        <td><strong><s:property value="contactName"/></strong></td>
                                                        <td align="center">
                                                            <a href="EditContactUs?contactId=<s:property value="contactId"/>">
                                                                <img src="<s:url value="/icons/edit.gif"/>" title="Edit Interest"/> 
                                                            </a> 
                                                        </td> 
                                                    </tr>
                                                    <s:generator separator="," val="%{contactAddress}">
                                                        <s:iterator >
                                                            <tr><td><s:property /></td></tr>  
                                                        </s:iterator>
                                                    </s:generator>
                                                    <tr><td><strong>Office No:</strong> <s:property value="contactOff" /></td></tr>
                                                    <tr><td><strong>Mobile No:</strong> <s:property value="contactMob" /></td></tr>
                                                    <tr><td><strong> Email-Id: </strong><s:property value="contactEmail" /></td></tr>
                                                </s:iterator>
                                            </table>

                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <!--Right box End Here-->
                    </div>
                </div>
                <!--Middle Section Ends Here-->
            </div>
        </div>
        <s:include value="/Footer.jsp"/>

    </body>
</html>
