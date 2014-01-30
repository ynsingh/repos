<%--
    Document   : CourseInfo
    Created on : Dec 12, 2012, 3:49:48 PM
    Author     : vinay
--%>

<%@page import="java.io.Serializable"%>
<%@page import="java.util.Date"%>
<%@page import="org.apache.log4j.Logger"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<%@taglib prefix="sjr" uri="/struts-jquery-richtext-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title>Course Configuration</title>
        <link href="<s:url value="/css/master.css"/>" rel="stylesheet" type="text/css" />
        <link href="<s:url value="/css/collapse.css"/>" rel="stylesheet" type="text/css" />
        <link href="<s:url value="/css/skin.css"/>" rel="stylesheet" type="text/css" />
        <sj:head/>
        <script type="text/javascript" src="<s:url value="/js/expand.js"/>"></script>
        <script type="text/javascript" src="<s:url value="/js/global.js"/>"></script>
        <script>
            $(function() {
                $("#accordion").accordion();
            });
        </script>
        <script>
            $(document).ready(function() {
                $(".add_course a").click(function() {
                    $("#add_course_form").toggle();
                });
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
                            <div class="my_account_bg">My Course</div>
                            <div class="w100 fl-l mart10">
                                <div class="w98 mar0a">
                                    <!--   <div class="fl-r">
                                    <s:a href="../Administrator/CourseAdd.jsp" cssClass="marl5">New Course</s:a>
                                    </div>
                                        -->
                                        <div class="w100 fl-l">
                                        <s:url id="Univer" action="UniversityAct" namespace="/Dropdown"/>
                                        <s:url id="dept" action="DeptAct" namespace="/Dropdown"/>
                                        <s:url id="deptprogram" action="DeptProgramAct" namespace="/Dropdown"/>
                                        <div class="tab_btn_1"><a onclick="history.go(-1);"><img src="<s:url value="/icons/back-arrow.png"/>" class="w25p" /></a></div>
                                            <%
                                                if (role.equals("student")) {
                                                } else {
                                            %>
                                        <div class="add_course fl-r"><a href="#" onclick="show_from()">Add Course</a></div>
                                        <div id="add_course_form" style="display:none;" class="fl-l w100">
                                            <fieldset class="w450p mar0a">
                                                <legend class="fbld">Add Course/Subject/Paper</legend>
                                                <s:form method="post" action="AddProCourse" id="FormId" theme="simple" namespace="/Administrator">
                                                    <table align="center">
                                                        <tr><th align="left">University/Institute</th>
                                                            <td>
                                                                <sj:select
                                                                    href="%{Univer}"
                                                                    id="univCode"
                                                                    onChangeTopics="reloaddepartmentlist"
                                                                    name="instituteId"
                                                                    list="univList"
                                                                    emptyOption="false"
                                                                    headerKey="-1"
                                                                    headerValue="Please Select University/Institute"
                                                                    label="Select University/Institute"
                                                                    sortable="false"
                                                                    />
                                                            </td>
                                                        </tr>
                                                        <tr>
                                                            <th align="left">Department/School</th>
                                                            <td>
                                                                <sj:select
                                                                    href="%{dept}"
                                                                    id="department"
                                                                    formIds="FormId"
                                                                    reloadTopics="reloaddepartmentlist"
                                                                    name="departmentId"
                                                                    list="departmentL"
                                                                    emptyOption="false"
                                                                    headerKey="-1"
                                                                    headerValue="Please Select a Department/School/Collage"
                                                                    label="Programme"
                                                                    onChangeTopics="reloadprogrammelist"
                                                                    />
                                                            </td>
                                                        </tr>
                                                        <tr>
                                                            <th align="left">Programme</th>
                                                            <td>
                                                                <sj:select
                                                                    href="%{deptprogram}"
                                                                    id="programe"
                                                                    formIds="FormId"
                                                                    reloadTopics="reloadprogrammelist"
                                                                    name="programmeId"
                                                                    list="programmeL"
                                                                    emptyOption="false"
                                                                    headerKey="-1"
                                                                    headerValue="Please Select Programme/Degree"
                                                                    label="Programme"
                                                                    />
                                                            </td>
                                                        </tr>
                                                        <tr>
                                                            <th align="left">Name</th>
                                                            <td align="left"><s:textfield name="courseName"/></td>
                                                        </tr>
                                                        <tr>
                                                            <th align="left">Code</th>
                                                            <td align="left"><s:textfield name="courseCode"/></td>
                                                        </tr>
                                                        <tr>
                                                            <th colspan="2" align="center">
                                                                <s:submit value="Save"/>&nbsp;&nbsp;<s:reset value="Reset"/>&nbsp;&nbsp;<s:reset value="Back" onClick="history.go(-1);" />
                                                            </th>
                                                        </tr>
                                                    </table>
                                                </s:form>
                                            </fieldset>
                                        </div>
                                        <%}%>
                                        <div class="w700p mar0a mart30">
                                            <table class="tablepaging" id="tablepaging" width="100%" cellspacing="0" cellpadding="5" border="1">
                                                <tr>
                                                    <th>S.No.</th>
                                                    <th>Course Code</th>
                                                    <th>Course Name</th>
                                                    <th>Programme (Institute)</th>
                                                    <th>Date of Creation</th>
                                                </tr>
                                                <s:iterator value="CourseList" status="stat">
                                                    <tr>
                                                        <td><s:property value="#stat.count"/></td>
                                                        <td><s:property value="courseCode"/></td>
                                                        <td><s:property value="courseName"/></td>
                                                        <td><s:property value="programme.programmeName"/>&nbsp;(<s:property value="programme.institute.shortName"/>)</td>
                                                        <td><s:date name="courceCreatorDate" format="MMM dd, yyyy"/></td>
                                                    </tr>
                                                </s:iterator>
                                            </table>
                                        </div> 
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
