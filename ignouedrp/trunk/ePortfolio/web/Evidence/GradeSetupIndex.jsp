<%-- 
    Document   : GradeSetupIndex
    Created on : Jun 08, 2012, 11:57:51 AM
    Author     : IGNOU Team
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title>Existing Grade Setups</title>
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
                            <div class="my_account_bg">Existing Grade Setups</div>
                            <div class="v_gallery">
                                <div class="w98 mar0a mart10">
                                    <div class="bradcum">
                                        <a href="<s:url value="/Welcome-Index.jsp"/>">Home</a>&nbsp;>&nbsp;<a href="<s:url value="/MyEdudation-Workspace.jsp"/>">My Education and Work</a>&nbsp;> <a href="FacultyTaskShow">Task / Activities</a> > Existing Grade Setup
                                    </div>
                                    <div align="right">
                                        <s:a href="AddEvidence.jsp">Create Activity</s:a>
                                        <s:a action="EviDraftList" cssClass="marl5">Draft</s:a>
                                        <s:a href="GradeSetup.jsp" cssClass="marl5">Setup</s:a>
                                    </div>
                                    <div class="w100 fl-l tc fbld fcred mart10"><s:property value="msg"/></div>
                                    <table width="100%" class="mar0a mart5" cellpadding="4" border="1" cellspacing="0">
                                        <tr>
                                            <th align="center">ID</th>
                                            <th align="center">Course</th>
                                            <th align="center">Grade Type</th>
                                            <th align="center">Date of Setup</th>
                                        </tr>
                                        <s:iterator value="GradeSetupList" status="stat">
                                            <tr>
                                                <td align="center"><s:property value="gradeValId"/></td>

                                                <td>
                                                    <s:property value="course.courseCode"/>
                                                    <br/>
                                                    <a href="EditGradeSetup?gradeValId=<s:property value="gradeValId"/>&amp;Course=<s:property value="course.courseCode"/>&amp;GradeType=<s:property value="gradeTypeDetailsMaster.gradeTypeMaster.title"/>">Edit</a>
                                                </td>
                                                <td><s:property value="gradeTypeDetailsMaster.gradeTypeMaster.title"/></td>
                                                <td><s:date name="gradeDate" format="MMM dd, yyyy"/></td>
                                            </tr>
                                        </s:iterator>
                                    </table> 
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