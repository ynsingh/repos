<%-- 
    Document   : EventIndex
    Created on : Oct 19, 2011, 11:49:42 AM
    Author     : IGNOU Team
    Version      : 1
--%>

<%@page import="java.io.Serializable"%>
<%@page import="java.util.Date"%>
<%@page import="org.apache.log4j.Logger"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title>Events</title>
        <link href="<s:url value="/css/master.css"/>" rel="stylesheet" type="text/css" />
        <link href="<s:url value="/css/collapse.css"/>" rel="stylesheet" type="text/css" />
        <link href="<s:url value="/css/skin.css"/>" rel="stylesheet" type="text/css" />
        <script type="text/javascript" src="<s:url value="/js/jquery-1.6.4.min.js"/>"></script>
        <sj:head/>
        <script type="text/javascript" src="<s:url value="/js/expand.js"/>"></script>
        <script type="text/javascript" src="<s:url value="/js/global.js"/>"></script>
        <script>
            $(function() {
                $( "#accordion" ).accordion();
            });
        </script>
    </head>
    <body>
        <%            //  final Logger logger = Logger.getLogger(this.getClass());
            String ipAddress = request.getRemoteAddr();
             logger.warn(session.getAttribute("user_id") + " Accessed from: " + ipAddress + " at: " + new Date());
            String role = session.getAttribute("role").toString();
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
                            <div class="my_account_bg">Latest Events</div>
                            <div class="w100 fl-l mart10">
                                <div class="w98 mar0a">
                                    <div class="w100 fl-l tc fbld fcgreen">
                                        <s:property value="msg"/>
                                    </div>
                                    <div class="w100 fl-l mart10">
                                        <table width="100%" class="fl-l mar0 pad0" cellpadding="0" cellspacing="0" border="1">
                                            <tr>
                                                <th align="center">S.No.</th>
                                                <th align="center">Event Title</th>
                                                <th align="center">Event Start Date</th>
                                                <th align="center">Event End Date</th>
                                                <th align="center">Venue</th>
                                                <th align="center">Description</th>
                                            </tr>
                                            <s:iterator value="eventList" status="stat">
                                                <tr>
                                                    <td align="center" width="2%"><s:property value="#stat.count"/></td>
                                                    <td width="30%"><s:property value="eventTitle"/></td>
                                                    <td align="center" width="10%"><s:property value="eventDateFrom"/></td>
                                                    <td align="center" width="10%"><s:property value="eventDateTo"/></td>
                                                    <td width="25%"><s:property value="venue"/>,&nbsp;
                                                        <s:property value="address"/>,&nbsp;
                                                        <s:property value="city"/>,&nbsp;
                                                        <s:property value="state"/>,&nbsp;
                                                        <s:property value="country"/>,&nbsp;
                                                        <s:property value="pincode"/>,&nbsp;
                                                        P.H. <s:property value="phone"/>,&nbsp;
                                                        Email Id: <s:property value="emailId"/><br/>
                                                        <s:property value="website"/>
                                                    </td>
                                                    <td width="23%"><s:property value="description"/></td>
                                                </tr>

                                            </s:iterator>
                                        </table>
                                    </div>
                                    <!--Right box Starts Here-->
                                </div>
                            </div>
                        </div>
                    </div>
                    <!--Middle Section Ends Here-->
                </div>
            </div>
        </div>
        <s:include value="/Footer.jsp"/>
    </body>
</html>
