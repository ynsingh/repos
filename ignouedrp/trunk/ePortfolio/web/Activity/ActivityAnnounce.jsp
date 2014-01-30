<%--
   Document   : ActivityAnnounce
   Created on : May 11, 2012, 1:30:50 PM
   Author     : IGNOU Team
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
        <title>Create Task / Activities</title>
        <link href="<s:url value="/css/master.css"/>" rel="stylesheet" type="text/css" />
        <link href="<s:url value="/css/collapse.css"/>" rel="stylesheet" type="text/css" />
        <link href="<s:url value="/css/skin.css"/>" rel="stylesheet" type="text/css" />
        <sj:head />
        <script type="text/javascript" src="<s:url value="/js/expand.js"/>"></script>
        <script>
            $(function() {
                $("#accordion").accordion();
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
                            <div class="my_account_bg">Create Task / Activities</div>
                            <div class="v_gallery">
                                <div class="w98 mar0a mart10">
                                    <div class="bradcum">
                                        <a href="<s:url value="/Welcome-Index.jsp"/>">Home</a>&nbsp;>&nbsp;<a href="<s:url value="/MyEdudation-Workspace.jsp"/>">My Education and Work</a>&nbsp;> <a href="FacultyTaskShow">Task / Activities</a> > Create Task / Activity
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
                                        <div class="w100 fl-l mart15">
                                            <fieldset class="w5500p mar0a">
                                                <legend class="fbld">Add Task/Activity</legend>
                                            <s:form id="FormId" action="AddEvidence" namespace="/Activity" theme="simple" method="post" enctype="multipart/form-data">
                                                <s:url id="cgsetup" action="CourseGradeSetuped" namespace="/Activity"/>
                                                <s:url id="gtype" action="GradeSetuped" namespace="/Activity"/>
                                                <table class="tablepaging" id="tablepaging" width="100%" cellspacing="0" cellpadding="5" border="1">
                                                    <tr>
                                                        <td>Course</td>
                                                        <td colspan="2">
                                                            <sj:select
                                                                href="%{cgsetup}"
                                                                id="course"
                                                                onChangeTopics="reloadGradeType"
                                                                name="coursesId"
                                                                list="courseL"
                                                                emptyOption="false"
                                                                headerKey="-1"
                                                                headerValue="Please Select Course/Subject"
                                                                label="Select Course"
                                                                sortable="false"
                                                                required="true"
                                                                />
                                                        </td>
                                                    </tr>
                                                    <tr>
                                                        <td>Grade Type</td>
                                                        <td colspan="2">
                                                            <sj:select
                                                                href="%{gtype}"
                                                                id="grade"
                                                                formIds="FormId"
                                                                reloadTopics="reloadGradeType"
                                                                name="gradeId"
                                                                list="gradeMasterL"
                                                                emptyOption="false"
                                                                headerKey="-1"
                                                                headerValue="Please Select a Grade Type"
                                                                label="Grade Type"
                                                                required="true"
                                                                />
                                                        </td>
                                                    </tr>
                                                    <tr><td>Title</td>
                                                        <td><s:textfield name="evTitle" cssClass="w500p"/></td>
                                                    </tr>
                                                    <tr>
                                                        <td>Start Date (MM/DD/YY)</td>
                                                        <td>
                                                            <sj:datepicker readonly="true"  id="date0" name="openDate" changeMonth="true" changeYear="true" minDate="0"/>
                                                        </td>
                                                    </tr>
                                                    <tr>
                                                        <td>Closing Date (MM/DD/YY)</td>
                                                        <td>
                                                            <sj:datepicker readonly="true"  id="date1" name="closeDate" changeMonth="true" changeYear="true" minDate="0"/>
                                                        </td>
                                                    </tr>
                                                    <!--      <tr>
                                                              <td>Accept Until</td>
                                                              <td>
                                                    <sj:datepicker readonly="true"  id="date2" name="lastAcceptDate" changeMonth="true" changeYear="true" minDate="0"/>
                                                </td>
                                            </tr> -->
                                                    <tr>
                                                        <td valign="top">Instructions</td>
                                                        <td>
                                                            <sjr:tinymce
                                                                id="richtextTinymceAdvancedEditor"
                                                                name="instruction"
                                                                rows="10"
                                                                cols="10"
                                                                value="%{instruction}"
                                                                editorLocal="en"
                                                                editorTheme="advanced"
                                                                editorSkin="o2k7"
                                                                toolbarAlign="left"
                                                                toolbarLocation="top"
                                                                toolbarButtonsRow1="bold,italic,underline,strikethrough,|,justifyleft,justifycenter,justifyright,justifyfull,|,link,unlink,anchor,image,|,formatselect,|,sub,sup"
                                                                toolbarButtonsRow2="bullist,numlist,|,outdent,indent,blockquote,|,undo,redo,|,insertdate,inserttime,preview,|,forecolor,backcolor,|,fontselect,fontsizeselect"
                                                                toolbarButtonsRow3=" "
                                                                />
                                                        </td>
                                                    </tr>
                                                    <tr>
                                                        <td>&nbsp;</td>
                                                        <td><s:checkbox name="addDateSchedule" value="false" />Add Closing Date to Schedule</td>
                                                    </tr>
                                                    <tr>
                                                        <td>&nbsp;</td>
                                                        <td><s:checkbox  name="addAnnoOdate" value="false"/>Add an announcement about the Start Date to Announcements</td>
                                                    </tr>
                                                    <tr>
                                                        <td>&nbsp;</td>
                                                        <td><s:checkbox name="addtoGradebook" value="false"/>Add Assignment to Gradebook</td>
                                                    </tr>
                                                    <tr>
                                                        <td>Supporting File</td>
                                                        <td><s:file name="myAttach"/></td>
                                                    </tr>
                                                    <tr>
                                                        <td>&nbsp;</td>
                                                        <td><s:checkbox  name="saveEvid" value="false"/>Save as  Draft</td>
                                                    </tr>
                                                    <tr>
                                                        <td colspan="2" align="center">
                                                            <s:submit value="Submit"/>&nbsp;
                                                            <s:reset value="Reset"/>&nbsp;
                                                            <s:reset value="Back" onClick="history.go(-1);" />
                                                        </td>
                                                    </tr>
                                                </table>
                                            </s:form>
                                        </fieldset>
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
