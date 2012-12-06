<%-- 
    Document   : ShowFeedback
    Created on : Jan 2, 2012, 10:44:02 AM
    Author     : Amit
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title>Feedback List</title>
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
    <body>
        <%
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
                            <div class="my_account_bg">FeedBack</div>
                            <div class="w100 fl-l mart10">
                                <div class="w98 mar0a">
                                    <div class="w100 fl-l">
                                        <s:form action="" method="post" theme="simple">
                                            <table width="90%" class="mar0a mart10" cellpadding="0" cellspacing="0">
                                                <tr>
                                                    <td align="left"><s:submit action="ShowArchiveFBInfo" value="Archived"/></td>
                                                </tr>
                                            </table>
                                            <table width="90%" class="mar0a mart10" cellpadding="0" cellspacing="0" border="1">
                                                <th width="10%" align="center">S. No</th>
                                                <th width="10%" >Archive</th>
                                                <th width="20%" >From</th>
                                                <th width="50%">Subject</th>
                                                <s:iterator value="fbList" status="stat">
                                                    <tr>
                                                        <td align="center"> <s:property value="%{#stat.count}"/></td>
                                                        <td align="center"><a href="ArchiveFeedBackInfo?feedbackId=<s:property value="feedbackId"/>">Move</a></td>
                                                        <td><a href="DetailFback?feedbackId=<s:property value="feedbackId"/>">
                                                                <s:property value="emailId"/>
                                                            </a> </td>
                                                        <td valign="top"><a href="DetailFback?feedbackId=<s:property value="feedbackId"/>">
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
