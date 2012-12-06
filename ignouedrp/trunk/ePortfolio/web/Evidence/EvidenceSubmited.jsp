<%-- 
    Document   : EvidenceSubmited
    Created on : May 31, 2012, 4:40:04 PM
    Author     : IGNOU Team
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title>Submitted Task / Activities</title>
        <link href="<s:url value="/css/master.css"/>" rel="stylesheet" type="text/css" />
        <link href="<s:url value="/css/collapse.css"/>" rel="stylesheet" type="text/css" />
        <link href="<s:url value="/css/skin.css"/>" rel="stylesheet" type="text/css" />
        <script type="text/javascript" src="<s:url value="/js/jquery-1.6.4.min.js"/>"></script>

        <script type="text/javascript" src="<s:url value="/js/expand.js"/>"></script>
        <script>
            $(function() {
                $( "#accordion" ).accordion();
            });
        </script>
    </head>
    <body>
        <%
            String role = session.getAttribute("role").toString();
            if (session.getAttribute("user_id") == null) {
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
                            <div class="my_account_bg">Submitted Task / Activities</div>
                            <div class="v_gallery">
                                <div class="w98 mar0a mart10">
                                    <div class="bradcum">
                                        <a href="<s:url value="/Welcome-Index.jsp"/>">Home</a>&nbsp;>&nbsp;<a href="<s:url value="/MyEdudation-Workspace.jsp"/>">My Education and Work</a>&nbsp;><a href="<s:url value="/Evidence/StudentTaskList"/>"> Task /Activity</a>&nbsp;>&nbsp;Submitted Task / Activities
                                    </div>
                                    <div class="w100 fl-l tc fbld fcred"><s:property value="msg"/></div>
                                    <s:url id="GSSID" action="getScore" namespace="/ExamEvaluation"/>
                                    <div align="right"> <s:a href="%{GSSID}">Task/Activity Score Card</s:a></div>
                                    <table width="60%" class="mar0a" cellpadding="4" border="1" cellspacing="0">
                                        <tr>
                                            <th align="center">S No.</th>
                                            <th align="center">Course</th>
                                            <th align="center">Title</th>
                                        </tr>
                                        <s:iterator value="StdevList" status="stat">
                                            <tr>
                                                <td align="center">
                                                    <s:property value="#stat.count" />
                                                </td>
                                                <td align="center"><s:property value=" CourseList[#stat.index]"/></td>
                                                <td><s:property value="evTitle"/><br/>
                                                    <a href="ViewComments?evidenceId=<s:property value="evidenceId"/>">View Comments</a>
                                                    <a class="marl5" href="TaskSubmited?evidenceId=<s:property value="evidenceId"/>">Peer Group</a>
                                                </td>
                                            </tr>
                                        </s:iterator>
                                    </table> 
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