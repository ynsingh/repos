<%--
   Document   : Grading
   Created on : May 15, 2012, 5:22:46 PM
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
        <title>Student's Activity Submitted List</title>
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
        <style type="text/css" >
            #tablepaging th a{ color: #fff!important;}
        </style>
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
                            <div class="my_account_bg">Student's Activity Submitted List</div>
                            <div class="v_gallery">
                                <div class="w98 mar0a mart10">
                                    <div class="bradcum">
                                        <a href="<s:url value="/Welcome-Index.jsp"/>">Home</a>&nbsp;>&nbsp;<a href="<s:url value="/MyEdudation-Workspace.jsp"/>">My Education and Work</a>&nbsp;> <a href="FacultyTaskShow">Task / Activities</a> >  Task / Activities Grade
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
                                        <div class="w100 fl-l">
                                            <table class="w100 mar0a fl-l mart10 tablepaging" id="tablepaging" cellpadding="4" border="1" cellspacing="0">
                                                <head>
                                                    <tr>
                                                        <th colspan="2" class="tc">
                                                            <a href="#" onclick='$(".dta").toggle();'>Activity Details</a>
                                                        </th>
                                                    </tr>
                                                </head>
                                                <tr class="dta" style='display:none;'v>
                                                    <td class="w100p">Title</td>
                                                    <td><s:property value="evTitle"/></td></tr>
                                            <tr class="dta" style='display:none;'>
                                                <td class="w100p">Created By</td>
                                                <td><s:property value="facultyName"/></td>
                                            </tr>
                                            <tr class="dta" style='display:none;'>
                                                <td class="w100p">Grade Type</td>
                                                <td><s:property value="title"/></td></tr>
                                            <tr class="dta" style='display:none;'>
                                                <td class="w100p">Start Date</td>
                                                <td><s:date name="openDate" format="MMM dd, yyyy"/></td></tr>
                                            <tr class="dta" style='display:none;'>
                                                <td align="left">Closing Date</td>
                                                <td><s:date name="closeDate" format="MMM dd, yyyy"/></td></tr>
                                            <tr class="dta" style='display:none;'>
                                                <td class="w100p">File</td>
                                                <td>
                                                    <s:if test="assAttach!='null'">
                                                        <a href="downnloadAttach?facultyId=<s:property value="facultyId"/>&amp;assAttach=<s:property value="assAttach"/>" target="_blank"><s:property value="evidence.assAttach"/></a>
                                                    </s:if>
                                                    <s:elseif test="assAttach=='null'">

                                                    </s:elseif>
                                                </td>
                                            </tr>
                                            <tr class="dta" style='display:none;'>
                                                <td class="w100p">Instruction</td>
                                                <td><s:property value="instruction" escape="false"/></td>
                                            </tr>
                                        </table>
                                        <div class="w100 fl-l tc fbld fcred mart10"><s:property value="msg"/></div>
                                        <table class="w100 mar0a fl-l mart10 tablepaging" id="tablepaging" cellpadding="4" border="1" cellspacing="0">
                                            <tr>
                                                <th>S No.</th>
                                                <th>Student</th>
                                                <th>Submitted On</th>
                                                <th>Submitted</th>
                                                <th>Grade Obtained</th>
                                            </tr>
                                            <s:iterator value="ActivitiesSubmitedList" status="stat">
                                                <tr>
                                                    <td align="center"><s:property value="#stat.count"/></td>
                                                    <td><s:property value="user.lname"/>&nbsp;<s:property value="user.fname"/><br/>
                                                        <s:if test="submitted==1">
                                                            <form name="/Activity">
                                                                <s:if test="gradesObtained!=null">
                                                                </s:if>
                                                                <s:elseif test="gradesObtained==null">
                                                                    <a class="marl5" href="ActivityGrade?submissionId=<s:property value="submissionId"/>&amp;evidenceId=<s:property value="evidenceId"/>&amp;userId=<s:property value="user.emailId"/>">Grade</a>
                                                                </s:elseif>
                                                                <a class="marl5" href="StudentPop?evidenceId=<s:property value="evidenceId"/>&amp;submissionId=<s:property value="submissionId"/>&amp;userId=<s:property value="user.emailId"/>&amp;programmeId=<s:property value="evidence.course.programme.programmeId"/>">Allow-Comment</a>
                                                                <a class="marl5" href="ViewCommentsGrade?submissionId=<s:property value="submissionId"/>&amp;evidenceId=<s:property value="evidenceId"/>&amp;userId=<s:property value="user.emailId"/>">View Comments</a>
                                                            </form>
                                                        </s:if>
                                                        <s:elseif test="submitted==0">
                                                            &nbsp;
                                                        </s:elseif>
                                                    </td>
                                                    <td align="center">
                                                        <s:if test="submitted==1">
                                                            <s:date name="subDate" format="MMM dd, yyyy"/>
                                                        </s:if>
                                                        <s:elseif test="submitted==0">
                                                            &nbsp;
                                                        </s:elseif>
                                                    </td>
                                                    <td align="center"><s:if test="submitted==1">
                                                            Yes
                                                        </s:if>
                                                        <s:elseif test="submitted==0">
                                                            No
                                                        </s:elseif></td>
                                                    <td align="center">
                                                        <s:if test="gradesObtained==null">

                                                        </s:if>
                                                        <s:elseif test="gradesObtained!=null">
                                                            <s:property value="gradesObtained"/>
                                                        </s:elseif>
                                                    </td>
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