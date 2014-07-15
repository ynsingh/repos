<%-- 
    Document   : ActivityEdit
    Created on : May 28, 2012, 4:51:03 PM
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
        <title>Edit Task / Activities</title>
        <link href="<s:url value="/css/master.css"/>" rel="stylesheet" type="text/css" />         <link href="<s:url value="/css/main.css"/>" rel="stylesheet" type="text/css" />
        <link href="<s:url value="/css/collapse.css"/>" rel="stylesheet" type="text/css" />
        <link href="<s:url value="/css/skin.css"/>" rel="stylesheet" type="text/css" />
        <script type="text/javascript" src="<s:url value="/js/jquery-1.6.4.min.js"/>"></script>
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
                            <div class="my_account_bg">Edit Task / Activities</div>
                            <div class="v_gallery">
                                <div class="w100 fl-l mart10">
                                    <div class="bradcum">
                                        <a href="<s:url value="/Welcome-Index.jsp"/>">Home</a>&nbsp;>&nbsp;<a href="<s:url value="/MyEdudation-Workspace.jsp"/>">My Education and Work</a>&nbsp;> <a href="FacultyTaskShow">Task / Activities</a>  > <a href="EviDraftList">Draft</a> > Edit Task / Activities
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
                                        <div class="w100 fl-l tc fbld fcgreen"><s:property value="msg"/></div>
                                    <div class="w100 fl-l mart20">
                                        <fieldset class="w550p mar0a">
                                            <legend class="fbld">Edit Task/Activity</legend>
                                            <table class="tablepaging" id="tablepaging" width="100%" cellspacing="0" cellpadding="5" border="1">
                                                <s:form id="FormId" action="UpdateEvidInfo" name="myform" method="post"  theme="simple" namespace="/Activity" enctype="multipart/form-data">
                                                    <s:iterator value="EvidenceList">
                                                        <tr>
                                                            <td align="left">Course</td>
                                                            <td>
                                                                <s:property value="course.courseName"/>&nbsp;(<s:property value="course.courseCode"/>)
                                                            </td>
                                                        </tr>
                                                        <tr>
                                                            <td align="left">Grade Type</td>
                                                            <td><s:property value="gradeValue.gradeTypeDetailsMaster.gradeTypeMaster.title"/>

                                                            </td>
                                                        </tr>
                                                        <tr>
                                                            <td align="left">Title</td>
                                                            <td><s:hidden name="evidenceId"/>
                                                                <s:textfield name="evTitle"/></td>
                                                        </tr>
                                                        <tr>
                                                            <td>Start Date (MM/DD/YY)</td>
                                                            <td>
                                                                <sj:datepicker readonly="true"  id="date0" name="openDate" value="%{openDate}" changeMonth="true" changeYear="true"/>
                                                            </td>
                                                        </tr>
                                                        <tr>
                                                            <td>Closing Date (MM/DD/YY)</td>
                                                            <td> 
                                                                <sj:datepicker readonly="true"  id="date1" name="closeDate" value="%{closeDate}" changeMonth="true" changeYear="true"/>
                                                            </td>
                                                        </tr>
                                                        <tr>
                                                            <td>Accept Until</td>
                                                            <td> 
                                                                <sj:datepicker readonly="true"  id="date2" name="lastAcceptDate" value="%{lastAcceptDate}" changeMonth="true" changeYear="true"/>
                                                            </td>
                                                        </tr>
                                                        <tr>
                                                            <td align="left">Instructions</td>
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
                                                            <td></td>
                                                            <td><s:checkbox name="addDateSchedule"/>
                                                                Add Closing Date to Schedule</td>
                                                        </tr>
                                                        <tr>
                                                            <td></td>
                                                            <td><s:checkbox  name="addAnnoOdate"/>
                                                                Add an announcement about the Start Date to Announcements Grading</td>
                                                        </tr>
                                                        <tr>
                                                            <td></td>
                                                            <td><s:checkbox name="addtoGradebook"/>
                                                                Add Assignment to Gradebook</td>
                                                        </tr>
                                                        <tr>
                                                            <td align="left">Attachment</td>
                                                            <td>
                                                                <s:if test="assAttach!='null'">
                                                                    <a href="downnloadAttach?facultyId=<s:property value="facultyId"/>&amp;assAttach=<s:property value="assAttach"/>" target="_blank">
                                                                        <s:property value="assAttach"/>
                                                                    </a>
                                                                    <br/>

                                                                </s:if>
                                                                <s:elseif test="assAttach=='null'">

                                                                </s:elseif>
                                                                <s:file name="facData"/>
                                                            </td>

                                                        </tr>
                                                        <s:hidden name="saveEvid" value="0"/>
                                                        <tr>
                                                            <td align="left">Do you want to Cancel?</td>
                                                            <td>
                                                                <s:checkbox name="cancel"/>Cancel
                                                            </td>
                                                        </tr>
                                                        <tr>
                                                            <td align="left">Reason for Cancel</td>
                                                            <td>
                                                                <sjr:tinymce
                                                                    id="richtextTinymceAdvancedEditor1"
                                                                    name="cancelReason"
                                                                    rows="10"
                                                                    cols="10"
                                                                    value="%{cancelReason}"
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
                                                    </s:iterator>
                                                    <tr>
                                                        <td colspan="2" align="center">
                                                            <s:submit value="Save Changes"/>&nbsp;
                                                            <s:reset value="Cancel" onClick="history.go(-1);" />
                                                        </td>
                                                    </tr>
                                                </s:form>
                                            </table>
                                        </fieldset>
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
        <script type="text/javascript">
            var frmvalidator = new Validator("myform");
            frmvalidator.addValidation("gradeScale", "req");
            frmvalidator.addValidation("gradeScale", "dontselect=000");
        </script>
    </body>
</html>