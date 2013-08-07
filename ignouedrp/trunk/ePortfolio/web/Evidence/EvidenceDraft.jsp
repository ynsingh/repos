<%-- 
    Document   : EvidenceDraft
    Created on : May 28, 2012, 3:07:22 PM
    Author     : IGNOU Team
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title>Task / Activities</title>
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
                            <div class="my_account_bg">Draft Task / Activities</div>
                            <div class="v_gallery">
                                <div class="w98 mar0a mart10">
                                    <div class="bradcum">
                                        <a href="<s:url value="/Welcome-Index.jsp"/>">Home</a>&nbsp;>&nbsp;<a href="<s:url value="/MyEdudation-Workspace.jsp"/>">My Education and Work</a>&nbsp;> <a href="FacultyTaskShow">Task / Activities</a> > Draft Task / Activities
                                    </div>
                                    <div align="right">
                                        <s:a href="AddEvidence.jsp" cssClass="marl5">Create Activity</s:a>
                                        <s:a href="FacultyTaskShow" cssClass="marl5">Task/Activities</s:a>
                                        <s:a action="EviDraftList" cssClass="marl5">Draft</s:a>
                                        <s:a action="GetGradeSetupList" cssClass="marl5">Grade Setup</s:a>
                                    </div>
                                    <div class="w100 fl-l tc fbld fcgreen"><s:property value="msg"/></div>
                                    <table width="100%" class="mar0a mart5" cellpadding="4" border="1" cellspacing="0">
                                        <tr><th>S No.</th><th>Course</th><th>Title</th><th>Start Date(MM/DD/YY)</th><th>Closing Date(MM/DD/YY)</th></tr>
                                        <s:iterator value="StdevList" status="stat">
                                            <tr>
                                                <td align="center"><s:property value="#stat.count" /></td>
                                                <td><s:property value="course.courseCode"/></td>
                                                <td><s:property value="evTitle"/><br/>
                                                    <a href="EditEvidence?evidenceId=<s:property value="evidenceId"/>">Edit</a>
                                                </td>
                                                <td><s:property value="openDate"/></td>
                                                <td><s:property value="closeDate"/></td>
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
