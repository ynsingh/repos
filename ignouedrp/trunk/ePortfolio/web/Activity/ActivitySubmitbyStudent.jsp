<%-- 
    Document   : ActivitySubmitbyStudent
    Created on : May 11, 2012, 3:55:01 PM
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
        <title>Task / Activity Submission</title>
        <link href="<s:url value="/css/master.css"/>" rel="stylesheet" type="text/css" />
        <link href="<s:url value="/css/collapse.css"/>" rel="stylesheet" type="text/css" />
        <link href="<s:url value="/css/skin.css"/>" rel="stylesheet" type="text/css" />
        <script type="text/javascript" src="<s:url value="/js/jquery-1.6.4.min.js"/>"></script>
        <sj:head/>
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
                            <div class="my_account_bg">Task / Activity Submission</div>
                            <div class="v_gallery">
                                <div class="w98 mar0a">
                                    <div class="w100 fl-l mart10">
                                        <div class="bradcum">
                                            <a href="<s:url value="/Welcome-Index.jsp"/>">Home</a>&nbsp;>&nbsp;<a href="<s:url value="/MyEdudation-Workspace.jsp"/>">My Education and Work</a>&nbsp;><a href="<s:url value="/Activity/StudentTaskList"/>"> Task /Activity</a>&nbsp;>&nbsp;Submit Task / Activities
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
                                            <div class="w100 fl-l">
                                                <fieldset class="w650p mar0a mart10">
                                                    <legend><strong>Task / Activity</strong></legend>
                                                    <table width="100%" class="fl-l mart10" cellpadding="4" border="0" cellspacing="0">
                                                    <s:iterator value="EviList">
                                                        <tr><th align="left">Title</th>
                                                            <td><s:property value="evTitle"/></td></tr>                           
                                                        <tr>
                                                            <th align="left">Created By</th>
                                                            <td><s:property value="user.lname"/>,&nbsp;<s:property value="user.fname"/></td>
                                                        </tr>

                                                        <tr><th align="left">Start Date</th>
                                                            <td><s:date name="openDate" format="MMM dd, yyyy"/></td></tr>
                                                        <tr><th align="left">Closing Date)</th>
                                                            <td><s:date name="closeDate" format="MMM dd, yyyy"/></td></tr>
                                                        <tr>
                                                            <th align="left" valign="top">Instruction</th>
                                                            <td valign="top"><s:property value="instruction" escape="false"/></td>
                                                        </tr>
                                                        <s:if test="assAttach!='null'">
                                                            <tr>
                                                                <th align="left">File</th>
                                                                <td>  
                                                                    <a href="downnloadAttach?facultyId=<s:property value="user.emailId"/>&amp;assAttach=<s:property value="assAttach"/>" target="_blank"><s:property value="assAttach"/></a>
                                                                </td>
                                                            </tr>  
                                                        </s:if>
                                                        <s:elseif test="assAttach=='null'">

                                                        </s:elseif>
                                                    </s:iterator>
                                                </table>
                                            </fieldset>
                                        </div>
                                    </div>
                                    <div class="w100 fl-l mart10">
                                        <fieldset class="w650p mar0a">
                                            <legend><strong><h3>Task / Activity Submission</h3></strong></legend>
                                            <s:form method="post" name="myform" action="EvSubmision" namespace="/Activity" theme="simple" enctype="multipart/form-data">
                                                <table width="100%" class="mar0a" cellpadding="4" border="0" cellspacing="0">

                                                    <s:hidden name="evidenceId"/>
                                                    <tr>
                                                        <td width="25%">Instructions:</td>
                                                        <td width="75%">&nbsp;</td>
                                                    </tr>
                                                    <tr>
                                                        <td colspan="2">This Section allows submissions of Task / Activity. You can provide your comments in the box given below and can upload the supported documents.</td>
                                                    </tr>
                                                    <tr>
                                                        <td valign="top">Comment</td>
                                                        <td>
                                                            <sjr:tinymce
                                                                id="richtextTinymceAdvancedEditor"
                                                                name="instructions"
                                                                rows="10"
                                                                cols="10"
                                                                value="%{instructions}"
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
                                                        <td>Attachment(s)</td>
                                                        <td><s:file name="stuData"/></td>
                                                    </tr>
                                                    <tr>
                                                        <td colspan="2"><s:checkbox  name="saveDraft" value="false"/>Save as  Draft</td>
                                                        <!--<td>Save as  Draft</td>-->
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
            frmvalidator.addValidation("instructions", "req", "Please enter your Comment");
            frmvalidator.addValidation("instructions", "maxlen=200", "Max length is 200");
            frmvalidator.addValidation("instructions", "alpha_s", "Alphabetic chars only");

        </script>
    </body>
</html>
