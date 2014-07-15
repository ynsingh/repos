<%-- 
    Document   : Programmes
    Created on : Nov 5, 2012, 3:56:33 PM
    Author     : vinay
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
        <title>Programmes</title>
        <link href="<s:url value="/css/master.css"/>" rel="stylesheet" type="text/css" />         <link href="<s:url value="/css/main.css"/>" rel="stylesheet" type="text/css" />
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
                            <div class="my_account_bg">Programmes</div>
                            <div class="w100 fl-l mart10">
                                <div class="w98 mar0a">
                                    <div class="bradcum"> 
                                        <a href="<s:url value="/Welcome-Index.jsp"/>">Home</a>&nbsp;>&nbsp;<s:a action="ShowRegisteredInstitute">Registered Institutes</s:a>&nbsp;>&nbsp; Programmes
                                        </div>
                                        <div class="w98 maroa tr">
                                        <s:a href="ProgrammeRegistration.jsp">Add New Programme</s:a>
                                        <s:a href="CourseAdd.jsp" cssClass="marl5">Add New Course</s:a>
                                        </div>
                                        <div class="w100 fl-l tc fbld fcred">
                                        <s:property value="msg"/>
                                    </div>
                                    <div class="w100 fl-l mart10">
                                        <s:url id="Univer" action="UniversityAct" namespace="/Dropdown"/> 
                                        <s:url id="dept" action="DeptAct" namespace="/Dropdown"/> 
                                        <s:url id="deptprogram" action="DeptProgramAct" namespace="/Dropdown"/> 
                                        <s:url id="programCourse" action="ProgramCourseAct" namespace="/Dropdown"/> 
                                        <s:form method="post" id="FormId" theme="simple" namespace="/Dropdown">
                                            <table class="tablepaging" id="tablepaging" width="80%" cellspacing="0" cellpadding="5" border="1">
                                                <thead>
                                                    <tr><td width="180px;">Select University/Institute</td>
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
                                                                required="true"
                                                                />
                                                        </td>
                                                    </tr>
                                                    <tr>
                                                        <td width="180px;">Department/School</td>
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
                                                                required="true"
                                                                />
                                                        </td>
                                                    </tr>
                                                    <tr>
                                                        <td width="180px;">Programme</td>
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
                                                                onChangeTopics="reloadProCourselist"
                                                                required="true"
                                                                />
                                                        </td>
                                                    </tr>
                                                </thead> 
                                                <tr>
                                                    <th width="180px;">Course</th>
                                                    <td>
                                                        <sj:radio
                                                            href="%{programCourse}" 
                                                            id="course" 
                                                            formIds="FormId" 
                                                            reloadTopics="reloadProCourselist" 
                                                            name="courseId" 
                                                            list="courseL" 
                                                            emptyOption="false" 
                                                            label="Programme"
                                                            />
                                                    </td>
                                                </tr> 

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
