<%-- 
    Document   : CourseAdd
    Created on : Nov 8, 2012, 12:23:56 PM
    Author     : vinay
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<%@taglib prefix="sjr" uri="/struts-jquery-richtext-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title>Course/Subject/Paper Registration</title>
        <link href="<s:url value="/css/master.css"/>" rel="stylesheet" type="text/css" />
        <link href="<s:url value="/css/collapse.css"/>" rel="stylesheet" type="text/css" />
        <link href="<s:url value="/css/skin.css"/>" rel="stylesheet" type="text/css" />
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
                            <div class="my_account_bg">Course/Subject/Paper Registration</div>
                            <div class="w100 fl-l mart10">
                                <div class="w98 mar0a">
                                    <div class="w100 fl-l">
                                        <s:url id="Univer" action="UniversityAct" namespace="/Dropdown"/> 
                                        <s:url id="dept" action="DeptAct" namespace="/Dropdown"/> 
                                        <s:url id="deptprogram" action="DeptProgramAct" namespace="/Dropdown"/> 
                                        <s:form method="post" action="AddProCourse" id="FormId" theme="simple" namespace="/Administrator">
                                            <fieldset class="w500p mar0a">
                                                <legend class="fbld">Add Course/Subject/Paper</legend>
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
                                            </fieldset>
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
