<%-- 
    Document   : GradingValue
    Created on : May 16, 2012, 1:20:47 PM
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
        <title>Task / Activity Grading</title>
        <link href="<s:url value="/css/master.css"/>" rel="stylesheet" type="text/css" />         <link href="<s:url value="/css/main.css"/>" rel="stylesheet" type="text/css" />
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
                            <div class="my_account_bg">Task / Activity Grading</div>
                            <div class="v_gallery">
                                <div class="w98 mar0a mart10">
                                    <div class="bradcum">
                                        <a href="<s:url value="/Welcome-Index.jsp"/>">Home</a>&nbsp;>&nbsp;<a href="<s:url value="/MyEdudation-Workspace.jsp"/>">My Education and Work</a>&nbsp;> <a href="FacultyTaskShow">Task / Activities</a> &nbsp; >  <a href="ActivSubList?evidenceId=<s:property value="evidenceId"/>">Task / Activities Grade</a> &nbsp; > Task / Activity Grading 
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
                                            <fieldset class="w550p mar0a">
                                                <legend class="fbld">Grading Task/Activity</legend>
                                                <table class="w100 mar0a fl-l mart10 tablepaging" id="tablepaging" cellpadding="4" border="1" cellspacing="0">
                                                    <tr><th align="center" colspan="2">Task / Activities Details</th></tr>
                                                        <s:iterator value="ActivitiesSubmitedList"> 
                                                    <tr>
                                                        <th align="left">Title</th>
                                                        <td align="left"><s:property value="evidence.evTitle"/></td></tr>                           
                                                    <tr>
                                                        <th align="left">Created By</th>
                                                        <td align="left"><s:property value="evidence.user.lname"/>,&nbsp;<s:property value="evidence.user.fname"/></td>
                                                    </tr>
                                                    <tr>
                                                        <th align="left">Grade Type</th>
                                                        <td align="left"><s:property value="evidence.gradeValue.gradeTypeDetailsMaster.gradeTypeMaster.title"/></td></tr>
                                                    <tr>
                                                        <th align="left">Start Date</th>
                                                        <td align="left"><s:property value="evidence.openDate"/></td></tr>
                                                    <tr>
                                                        <th align="left">Closing Date</th>
                                                        <td align="left"><s:property value="evidence.closeDate"/></td></tr>
                                                    <tr>
                                                        <th align="left">Instruction</th>
                                                        <td align="left"><s:property value="evidence.instruction" escape="false"/></td>
                                                    </tr>
                                                    <tr>
                                                        <th align="left">File</th>
                                                        <td align="left">
                                                            <s:if test="evidence.assAttach!='null'">
                                                                <a href="downnloadAttach?facultyId=<s:property value="evidence.user.emailId"/>&amp;assAttach=<s:property value="evidence.assAttach"/>" target="_blank"><s:property value="evidence.assAttach"/></a>
                                                            </s:if>
                                                            <s:elseif test="evidence.assAttach=='null'">

                                                            </s:elseif>
                                                        </td>
                                                    </tr>
                                                    <tr><th colspan="2" align="center">Student Submission Details</th></tr>
                                                    <tr>
                                                        <th align="left">Submitted By</th>
                                                        <td align="left"><s:property value="user.lname"/>,&nbsp;<s:property value="user.fname"/></td>
                                                    </tr>
                                                    <tr>
                                                        <th align="left">Enrollment No.</th>
                                                        <td align="left"><s:property value="user.univRegNo"/></td>
                                                    </tr>
                                                    <tr>
                                                        <th align="left">Submitted On</th>
                                                        <td align="left"><s:date name="subDate" format="MMM dd, yyyy"/></td></tr>
                                                    <tr>
                                                        <th align="left">Student Reply</th>
                                                        <td align="left"><s:property value="instructions" escape="false" /></td></tr>
                                                    <tr>
                                                        <th align="left">Student's Attachment</th>
                                                        <td align="left"> <s:if test="attachment!='null'">
                                                                <a href="downloadStuAtt?userId=<s:property value="user.emailId"/>&amp;attachment=<s:property value="attachment"/>" target="_blank">  <s:property value="attachment"/></a>
                                                            </s:if>
                                                            <s:elseif test="attachment=='null'">

                                                            </s:elseif></td></tr>

                                                    <s:form action="UpdateMarks" method="post" theme="simple" namespace="/Activity" enctype="multipart/form-data">
                                                        <s:hidden name="submissionId"/>
                                                        <s:hidden name="instituteId"/>
                                                        <s:hidden name="evidenceId"/>
                                                        <tr><th align="center" colspan="2">Grading Details</th></tr>
                                                                <s:if test='evidence.gradeValue.gradeTypeDetailsMaster.gradeTypeMaster.title.equals("Points Grade")'>
                                                            <tr><th align="left">Maximum Points</th>
                                                                <td>      
                                                                    <s:iterator value="splitValue1" status="stat">                         
                                                                        <s:property value="GradeValueRange[#stat.index]"/>
                                                                    </s:iterator>
                                                                </td>
                                                            </tr>
                                                            <tr><th align="left">Obtained Points</th>
                                                                <td align="left">                                       
                                                                    <s:textfield name="gradesObtained"/>
                                                                </td>
                                                            </tr>
                                                        </s:if>
                                                        <s:else>
                                                            <tr><th>Select Grade</th>
                                                                <td valign="middle">
                                                                    <div style="width: auto; float: left; margin-top: 100px;" align="left">
                                                                        <select name="gradesObtained">
                                                                            <s:iterator value="splitValue1" status="stat">
                                                                                <option value="<s:property value="GradeLable[#stat.index]"/>">
                                                                                    <s:property value="GradeLable[#stat.index]"/>
                                                                                </option>
                                                                            </s:iterator>
                                                                        </select>
                                                                    </div>
                                                                    <div style="width: auto; float: left; margin-left: 30px;" align="right">
                                                                        <table border="1">
                                                                            <s:iterator value="splitValue1" status="stat">
                                                                                <tr>
                                                                                    <th><s:property value="GradeLable[#stat.index]"/></th>
                                                                                    <td><s:property value="GradeValueRange[#stat.index]"/></td>
                                                                                </tr>
                                                                            </s:iterator>
                                                                        </table>
                                                                    </div>
                                                                </td>
                                                            </tr> 
                                                        </s:else>
                                                        <tr><th>Comment</th><td>
                                                                <sjr:tinymce
                                                                    id="richtextTinymceAdvancedEditor"
                                                                    name="facultyComment"
                                                                    rows="10"
                                                                    cols="10"
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
                                                        <tr><th>Attachment</th>
                                                            <td><s:file name="facData"/></td>
                                                        </tr>
                                                        <tr>
                                                            <th>&nbsp;</th>
                                                            <td align="left">  
                                                                <s:submit value="Submit"/>&nbsp;
                                                                <s:reset value="Reset"/>&nbsp;
                                                                <s:reset value="Back" onClick="history.go(-1);" />
                                                            </td></tr>
                                                        </s:form>
                                                </table>
                                            </s:iterator>       
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
    </body>
</html>