<%--
Document : ActivityIndex
Created on : May 11, 2012, 1:18:10 PM
Author : IGNOU Team
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
        <title>Task / Activities</title>
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
                            <div class="my_account_bg">Task / Activities</div>
                            <div class="v_gallery">
                                <div class="w98 mar0a mart10">
                                    <% if (role.contains("faculty")) {%>
                                    <div class="bradcum">
                                        <a href="<s:url value="/Welcome-Index.jsp"/>">Home</a>&nbsp;>&nbsp;<a href="<s:url value="/MyEdudation-Workspace.jsp"/>">My Education and Work</a>&nbsp;> Task / Activities
                                    </div>
                                    <div align="right" class="tab_btn">
                                        <div class="tab_btn_1"><a onclick="history.go(-1);"><img src="<s:url value="/icons/back-arrow.png"/>" class="w25p" /></a></div>
                                        <div class="fl-r">
                                            <s:a href="ActivityAnnounce.jsp" cssClass="marl5">Create Activity</s:a>
                                            <s:a href="FacultyTaskShow" cssClass="marl5">Task/Activities</s:a>
                                            <s:a action="EviDraftList" cssClass="marl5">Draft</s:a>
                                            <s:a action="GetGradeSetupList" cssClass="marl5">Grade Setup</s:a>
                                            </div>                                    
                                        </div>
                                        <div class="w100 fl-l tc fbld fcred"><s:property value="msg"/></div>
                                    <table class="w100 mar0a fl-l mart10 tablepaging" id="tablepaging" cellpadding="4" border="1" cellspacing="0">
                                        <thead>
                                            <tr>
                                                <td>S No.</td>
                                                <td>Course</td>
                                                <td>Title</td>
                                                <td>Start Date</td>
                                                <td>Closing Date</td>
                                            </tr>
                                        </thead>
                                        <s:iterator value="StdevList" status="stat">
                                            <tr> 
                                                <td><s:property value="#stat.count" /></td>
                                                <td><s:property value="course.courseCode"/>
                                                </td>
                                                <td class="lh16"><s:property value="evTitle"/><br/>
                                                    <s:if test="oarList[#stat.index]==1">
                                                        <a href="ActivSubList?evidenceId=<s:property value="evidenceId"/>">Grade</a>
                                                    </s:if>
                                                    <s:elseif test="oarList[#stat.index]==0">
                                                        <a href="EviReview?evidenceId=<s:property value="evidenceId"/>">Review</a>
                                                    </s:elseif>
                                                </td>
                                                <td><s:date name="openDate" format="MMM dd, yyyy"/></td>
                                                <td><s:date name="closeDate" format="MMM dd, yyyy"/></td>
                                            </tr>
                                        </s:iterator>
                                    </table>
                                </div>
                                <% } else if (role.contains("student")) {%>
                                <div class="bradcum">
                                    <a href="<s:url value="/Welcome-Index.jsp"/>">Home</a>&nbsp;>&nbsp;<a href="<s:url value="/MyEdudation-Workspace.jsp"/>">My Education and Work</a>&nbsp;> Task / Activities
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
                                    <div class="w100 fl-l tc fbld fcred"><s:property value="msg"/></div>
                                <table  class="w100 mar0a fl-l mart10 tablepaging" id="tablepaging" cellpadding="4" border="1" cellspacing="0">
                                    <thead>
                                        <tr>
                                            <td>S No.</td>
                                            <td>Course</td>
                                            <td>Title</td>
                                            <td>Start Date</td>
                                            <td>Closing Date</td>
                                        </tr>
                                    </thead>
                                    <s:iterator value="StdevList" status="stat">
                                        <s:if test="oarList[#stat.index]==1">
                                            <tr>
                                                <td align="center"><s:property value="#stat.count" /></td>
                                                <td><s:property value="course.courseCode"/></td>
                                                <td class="lh16"> <s:property value="evTitle"/><br/>
                                                    <a href="Activ_SubList?evidenceId=<s:property value="evidenceId"/>">Submit</a>
                                                </td> 
                                                <td><s:date name="openDate" format="MMM dd, yyyy"/></td>
                                                <td><s:date name="closeDate" format="MMM dd, yyyy"/></td>
                                            </tr>
                                        </s:if>
                                        <s:if test="oarList[#stat.index]==0">

                                        </s:if>
                                    </s:iterator>
                                </table>
                                <% }%>

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