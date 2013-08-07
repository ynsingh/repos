<%-- 
    Document   : MyEdudation-Workspace
    Created on : Apr 17, 2012, 1:18:29 PM
    Author     : IGNOU Team
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title>My Education and Work</title>
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
                            <div class="my_account_bg">My Education and Work</div>
                            <div class="v_gallery">
                                <div class="w100 fl-l mart10">
                                    <div class="bradcum">
                                        <a href="<s:url value="/Welcome-Index.jsp"/>">Home</a>&nbsp;>&nbsp;My Education and Work
                                    </div>
                                    <div class="gallery">
                                        <ul id="myconnectionicon" class="jcarousel-skin-tango">
                                            <% if (role.contains("faculty")) {%>     
                                            <s:url id="EviID" action="FacultyTaskShow" namespace="/Evidence"/>
                                            <s:url id="ActId" action="myAnnouncedActivities" namespace="/Activities"/>
                                            <% } else if (role.contains("student")) {%>
                                            <s:url id="EviID" action="StudentTaskList" namespace="/Evidence"/>
                                            <s:url id="ActId" action="announcedActivities" namespace="/Activities"/>
                                            <% }%> 
                                             <li><s:a href="%{EviID}"><img src="<s:url value="/icons/task-activities.gif"/>" width="60" height="60"/><span>Task / Activities&nbsp;(Evidence)</span></s:a></li>
                                            <li><s:a href="%{ActId}"><img src="<s:url value="/icons/task-activities.gif"/>" width="60" height="60"/><span>Activities</span></s:a></li>
                                             <li><a href="MyWorkspace/MyWorkspace.jsp"><img src="<s:url value="/icons/my-workspace.gif"/>" alt="My Workspace" /><span>My Workspace</span></a></li>
                                            <li><a href="<s:url value="/PageUnderConstruction.jsp"/>"><img src="<s:url value="/icons/institute.gif"/>" alt="Institute" /><span>Institute</span></a></li>
                                            <li><a href="<s:url value="/PageUnderConstruction.jsp"/>"><img src="<s:url value="/icons/course.gif"/>" alt="Course" /><span>Courses</span></a></li>
                                            <li><a href="<s:url value="/ExamEvaluation/ExamEvaluationIndex.jsp"/>"><img src="<s:url value="/icons/exam-eval.gif"/>" alt="Examination &amp; Evaluation" /><span>Examination &amp; Evaluation</span></a></li>
                                        </ul>
                                    </div>
                                </div>
                            </div>
                            <!--Right box End Here-->
                        </div>

                    </div>
                    <!--Middle Section Ends Here-->
                </div>
            </div>
        </div>
        <s:include value="/Footer.jsp"/>  
    </body>
</html>