<%-- 
    Document   : ShowArchivedFeedback
    Created on : Jul 27, 2012, 10:34:05 AM
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
        <title>Feedback List</title>
        <link href="<s:url value="/css/master.css"/>" rel="stylesheet" type="text/css" />         <link href="<s:url value="/css/main.css"/>" rel="stylesheet" type="text/css" />
        <link href="<s:url value="/css/collapse.css"/>" rel="stylesheet" type="text/css" />
        <link href="<s:url value="/css/skin.css"/>" rel="stylesheet" type="text/css" />
        <script type="text/javascript" src="<s:url value="/js/jquery-1.6.4.min.js"/>"></script>
        <script type="text/javascript" src="<s:url value="/js/expand.js"/>"></script>
        <script type="text/javascript" src="<s:url value="/js/global.js"/>"></script>
         <script>
            $(function() {
                $( "#accordion" ).accordion();
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
                <s:include value="/Header.jsp"/>
                <!--Header Ends Here-->
                <!--Middle Section Starts Here-->
                <div class="w100 fl-l">
                    <div class="middle_bg">
                        <!--Left box Starts Here-->
                        <s:include  value="/Left-Nevigation.jsp"/> 
                        <!--Left box Ends Here-->
                        <!--Right box Starts Here-->
                        <div class="right_box">
                            <div class="my_account_bg">FeedBack List</div>
                            <div class="w100 fl-l mart10">
                                <div class="w98 mar0a">
                                    <div class="w100 fl-l">
                                        <s:form action="" method="post" theme="simple">
                                            <table width="100%" class="fl-l mart10" cellpadding="0" cellspacing="0">
                                                <tr>
                                                    <td>
                                                        <s:submit action="ShowFeedBackInfo" value="FeedBack List"/>
                                                    </td>
                                                </tr>
                                            </table>
                                            <table width="100%" class="fl-l mart10" cellpadding="0" cellspacing="0" border="1">
                                                <th align="center">S. No</th>
                                                <th align="center">From</th>
                                                <th align="center">Subject</th>
                                                <s:iterator value="fbList" status="stat">
                                                    <tr>
                                                        <td align="center"> <s:property value="%{#stat.count}"/></td>
                                                        <td ><a href="DetailFback?feedbackId=<s:property value="feedbackId"/>" style="text-decoration: none;">
                                                                <s:property value="emailId"/>
                                                            </a> </td>
                                                        <td ><a href="DetailFback?feedbackId=<s:property value="feedbackId"/>" style="text-decoration: none;">
                                                                <s:property value="FSubject"/>
                                                            </a> </td>
                                                    </tr>
                                                </s:iterator>

                                            </table>
                                        </s:form>
                                    </div>
                                </div>
                            </div>
                            <!--Right box Starts Here-->
                        </div>
                    </div>
                    <!--Middle Section Ends Here-->
                </div>
            </div>
        </div>
        <s:include value="/Footer.jsp"/>
    </body>
</html>
