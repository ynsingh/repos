<%-- 
    Document   : DetailFeedBack
    Created on : Apr 11, 2012, 12:10:37 PM
    Author     : IGNOU Team
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title>Feedback Details</title>
        <link href="<s:url value="/css/master.css"/>" rel="stylesheet" type="text/css" />
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
        if (session.getAttribute("user_id") == null) {
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
                            <div class="my_account_bg">FeedBack Details</div>
                            <div class="">
                                <table width="80%" border="0" class="richtxt">

                                    <s:iterator value="fbList">
                                        <a href="ForwardFback?feedbackId=<s:property value="feedbackId"/>">Forward</a>&nbsp;&nbsp;&nbsp;<a href="ReplyFback?feedbackId=<s:property value="feedbackId"/>">Reply</a>
                                        <br/>
                                        <th align="left"><s:property value="FSubject"/>:<hr></hr></th>
                                        <tr><td><b><s:property value="name"/></b>--<s:property value="emailId"/></td></tr>
                                        <tr><td></td></tr>
                                        <tr><td><s:property value="comment" escape="false"/></td></tr>
                                    </s:iterator>
                                </table>
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