<%-- 
    Document   : CommentsonPeerGroup
    Created on : May 19, 2012, 11:04:32 AM
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
        <title>Comment on Peer Group</title>
        <link href="<s:url value="/css/master.css"/>" rel="stylesheet" type="text/css" />         <link href="<s:url value="/css/main.css"/>" rel="stylesheet" type="text/css" />
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
        <%
            final Logger logger = Logger.getLogger(this.getClass());
            String ipAddress = request.getRemoteAddr();
             logger.warn(session.getAttribute("user_id") + " Accessed from: " + ipAddress + " at: " + new Date());
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
                            <div class="my_account_bg">Comment on Peer Group</div>
                            <div class="v_gallery">
                                <div class="w98 mar0a mart10">
                                    <div class="bradcum">
                                        <a href="<s:url value="/Welcome-Index.jsp"/>">Home</a>&nbsp;>&nbsp;<a href="<s:url value="/MyEdudation-Workspace.jsp"/>">My Education and Work</a>&nbsp;><a href="<s:url value="/Activity/StudentTaskList"/>"> Task /Activity</a>&nbsp;> <a href="<s:url value="/Activity/SubmitedEvi"/>"> Submitted Task / Activities</a>&nbsp;> Comment on Peer Group 
                                    </div>
                                    <div align="right" class="tab_btn">
                                        <div class="tab_btn_1"><a onclick="history.go(-1);"><img src="<s:url value="/icons/back-arrow.png"/>" class="w25p" /></a></div>
                                        <div class="fl-r">
                                            <s:url id="GSSID" action="getScore" namespace="/ExamEvaluation"/>
                                            <s:a action="SubmitedEvi">Submitted Task / Activities</s:a>
                                            <a href="<s:url value="/Activity/StudentTaskList"/>"> Task /Activity</a>
                                            <s:a href="%{GSSID}">Activity Score</s:a>
                                            </div>
                                        </div>
                                        <div class="w100 fl-l mart20">
                                            <fieldset class="w550p mar0a">
                                                <legend class="fbld">Peer Group's Task/Activity</legend>
                                                <table width="100%" class="mar0a tablepaging" id="tablepaging" cellpadding="4" border="0" cellspacing="0">
                                                <s:iterator value="eviList">
                                                    <tr>
                                                        <th align="left">Task / Activities</th>
                                                        <td>
                                                            <s:property value="evTitle"/>
                                                        </td>
                                                    </tr>
                                                    <tr>
                                                        <th align="left">Created By</th>
                                                        <td><s:property value="user.lname"/>,&nbsp;<s:property value="user.fname"/></td>
                                                    </tr>
                                                    <tr>
                                                        <th align="left">File</th>
                                                        <td>
                                                            <s:if test="assAttach!='null'">
                                                                <a href="downnloadAttach?facultyId=<s:property value="user.emailId"/>&amp;assAttach=<s:property value="assAttach"/>" target="_blank"><s:property value="assAttach"/></a>
                                                            </s:if>
                                                            <s:elseif test="assAttach=='null'">

                                                            </s:elseif>
                                                        </td>
                                                    </tr>
                                                </s:iterator>
                                            </table>
                                        </fieldset>
                                        <table width="80%" class="mar0a mart10 tablepaging" id="tablepaging" cellpadding="4" border="1" cellspacing="0">
                                            <thead>
                                                <tr>
                                                    <td align="center">S No.</td>
                                                    <td align="center" width="200px;">Student</td>
                                                    <td align="center">Date Of Submission(MM/DD/YY)</td>
                                                    <td align="center">Comments</td>
                                                </tr>
                                            </thead>
                                            <s:iterator value="EviStdList" status="stat">
                                                <tr><td align="center"><s:property value="#stat.count"/></td>
                                                    <td><s:property value="user.lname"/>,&nbsp;<s:property value="user.fname"/></td>
                                                    <td align="center"><s:date name="subDate" format="MMM dd, yyyy"/></td>
                                                    <s:if test="canComment!=true">
                                                        <td>&nbsp;&nbsp;</td>
                                                    </s:if>                                            
                                                    <s:elseif test="canComment='true'">
                                                        <td> 
                                                            <a href="CmtDetList?evidenceId=<s:property value="evidenceId"/>&amp;userId=<s:property value="user.emailId"/>">Comment</a>
                                                        </td> 
                                                    </s:elseif>                                                                                           
                                                </tr>
                                            </s:iterator>
                                        </table>
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