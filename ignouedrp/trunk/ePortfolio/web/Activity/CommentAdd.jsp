<%-- 
   Document   : CommentAdd
   Created on : May 19, 2012, 10:59:07 AM
   Author     : IGNOU Team
   Last Modified on 03-04-2012 by IGNOU Team
--%>

<%@page import="java.io.Serializable"%>
<%@page import="java.util.Date"%>
<%@page import="org.apache.log4j.Logger"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="sd" uri="/struts-dojo-tags" %>
<%@taglib prefix="sj" uri="/struts-jquery-tags"%>
<%@taglib prefix="sjr" uri="/struts-jquery-richtext-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>

        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title>Comment on Peer Group</title>
        <link href="<s:url value="/css/master.css"/>" rel="stylesheet" type="text/css" />
        <link href="<s:url value="/css/collapse.css"/>" rel="stylesheet" type="text/css" />
        <link href="<s:url value="/css/skin.css"/>" rel="stylesheet" type="text/css" />
        <sj:head/>
        <script type="text/javascript" src="<s:url value="/js/expand.js"/>"></script>
        <script src="<s:url value="/js/jquery.raty.min.js"/>" type="text/javascript"></script>
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
                            <div class="my_account_bg">Comment Form</div>
                            <div class="v_gallery">
                                <div class="w98 mar0a mart10">
                                    <% if (role.contains("faculty")) {%>
                                    <% } else if (role.contains("student")) {%>
                                    <div class="bradcum">
                                        <a href="<s:url value="/Welcome-Index.jsp"/>">Home</a>&nbsp;>&nbsp;<a href="<s:url value="/MyEdudation-Workspace.jsp"/>">My Education and Work</a>&nbsp;> <a href="StudentTaskList">Task / Activities</a> > Review Task / Activities
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
                                    <% }%> 
                                    <div class="w100 fl-l">
                                        <fieldset class="w650p mar0a mart10">
                                            <legend><strong>Task / Activities</strong></legend>
                                            <table width="100%" class="mar0a tablepaging" id="tablepaging" cellpadding="4" border="1" cellspacing="0">
                                                <s:iterator value="evcommlist">    
                                                    <tr>
                                                        <td width="25%">Task / Activities</td>
                                                        <td width="75%">
                                                            <s:property value="evidence.evTitle"/>
                                                        </td>
                                                    </tr>
                                                    <tr>
                                                        <td>Created By</th>
                                                            <td><s:property value="evidence.user.lname"/>,&nbsp;<s:property value="evidence.user.fname"/></td>
                                                    </tr>
                                                    <tr>
                                                        <td valign="top">Instruction</td>
                                                        <td valign="top"><s:property value="evidence.instruction" escape="false"/></td>
                                                    </tr>
                                                    <s:if test="evidence.assAttach!='null'">
                                                        <tr>
                                                            <td>Supporting File</td>
                                                            <td>   
                                                                <a href="downnloadAttach?facultyId=<s:property value="evidence.user.emailId"/>&amp;assAttach=<s:property value="evidence.assAttach"/>" target="_blank"><s:property value="evidence.assAttach"/></a>
                                                            </td>
                                                        </tr>
                                                    </s:if>
                                                    <s:elseif test="evidence.assAttach=='null'">

                                                    </s:elseif>
                                                    <tr>
                                                        <td align="left">Submitted By</td>
                                                        <td><s:property value="user.lname"/>,&nbsp;<s:property value="user.fname"/></td>
                                                    </tr>
                                                    <s:if test="attachment!='null'">
                                                        <tr>
                                                            <td>Submitted File</td>
                                                            <td>   
                                                                <a href="downloadStuAtt?userId=<s:property value="userId"/>&amp;attachment=<s:property value="attachment"/>" target="_blank">  <s:property value="attachment"/></a>
                                                            </td>
                                                        </tr>   
                                                    </s:if>
                                                    <s:elseif test="attachment=='null'">

                                                    </s:elseif>
                                                    <tr>
                                                        <td>Comments/Reply</td>
                                                        <td><s:property value="instructions" escape="false"/></td>
                                                    </tr>
                                                    <tr>
                                                        <td colspan="2">
                                                            <s:url id="likeId" value="countLike.action"><s:param name="evidenceSubId" value="submissionId"/><s:param name="commentId" value="commentId"/>
                                                            </s:url>
                                                            <sd:div href="%{#likeId}" updateFreq="1000">

                                                            </sd:div>
                                                        </td>
                                                    </tr>

                                                </table>
                                            </fieldset>
                                            <div class="w100 fl-l tc fbld fcred mart10"><s:property value="msg"/></div>
                                            <div class="w100 fl-l">
                                                <s:if test="allper!='null'">
                                                    <s:form action="AddComment" namespace="/Activity" theme="simple" method="post" enctype="multipart/form-data">
                                                        <fieldset class="w650p mar0a mart10">
                                                            <legend><strong>Add Comment</strong></legend>
                                                            <table width="100%" class="mar0a" cellpadding="1" cellspacing="1" border="0">

                                                                <s:hidden name="userId" value="%{user.emailId}"/>
                                                                <s:hidden name="evidenceId"/>
                                                                <s:hidden name="submissionId"/>

                                                                <tr>
                                                                    <th width="25%" valign="top" align="left">Comment</th>
                                                                    <td width="75%">
                                                                        <sjr:tinymce
                                                                            id="richtextTinymceAdvancedEditor"
                                                                            name="comment"
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
                                                                <tr>
                                                                    <th align="left">File</th>
                                                                    <td>
                                                                        <s:file name="commentData"/>
                                                                    </td>
                                                                </tr>
                                                                <tr>
                                                                    <th align="left">Rating</th>
                                                                    <td id="halfd">
                                                                    </td>
                                                                </tr>
                                                                <tr>
                                                                    <th colspan="2" align="center">
                                                                        <s:submit value="Submit"/>&nbsp;
                                                                        <s:reset value="Reset"/>&nbsp;
                                                                        <s:reset value="Back" onClick="history.go(-1);" />
                                                                    </th>
                                                                </tr>
                                                            </table>
                                                        </fieldset>
                                                    </s:form>
                                                </s:if>
                                                <s:elseif test="allper=='null'">
                                                    <div class="w100 fl-l tc fbld fcred mart10">You Don't Have Permission to Comment</div>
                                                </s:elseif>
                                            </div>
                                        </s:iterator>  
                                        <fieldset class="w650p mar0a mart10">
                                            <legend><strong>Comments</strong></legend>
                                            <table width="100%" class="fl-l mart10" cellpadding="4" border="0" cellspacing="0">
                                                <tr>
                                                    <th>Average Rating: </th>
                                                    <td align="center"><div class="score" rating="<s:property value="avgStarRating"/>">&nbsp;</div></td>
                                                </tr>
                                                <s:iterator value="ComList" status="stat">
                                                    <tr>
                                                        <td >
                                                            <div align="justify">
                                                                <strong>
                                                                    <s:property value="userByCommentorId.lname"/>&nbsp;<s:property value="userByCommentorId.fname"/>
                                                                </strong>
                                                                <s:property value="comment" escape="false"/>
                                                                <s:if test="rating==0">
                                                                </s:if>
                                                                <s:else>
                                                                    <div class="score" rating="<s:property value="rating"/>">&nbsp;</div>
                                                                </s:else>

                                                            </div>
                                                            <div align="right">
                                                                <s:if test="commentorFilePath!='null'">
                                                                    <a href="downloadCmtAtth?commentorId=<s:property value="userByCommentorId.emailId"/>&amp;commentorFilePath=<s:property value="commentorFilePath"/>" target="_blank">  <s:property value="commentorFilePath"/></a>
                                                                </s:if>
                                                                <s:elseif test="commentorFilePath=='null'">
                                                                    &nbsp;
                                                                </s:elseif>
                                                            </div>
                                                        </td>
                                                        <td >
                                                            <s:url id="likeId" value="countLike.action"><s:param name="evidenceSubId" value="evidenceSubmission.submissionId"/><s:param name="commentId" value="commentId"/>
                                                            </s:url>
                                                            <sd:div href="%{#likeId}" updateFreq="1000" delay="0">

                                                            </sd:div>
                                                        </td>
                                                    </tr>
                                                </s:iterator>
                                            </table>
                                        </fieldset>

                                    </div>
                                </div>
                            </div>
                            <!--Right box End Here-->
                        </div>
                    </div>
                </div>
                <!--Middle Section Ends Here-->
            </div>
        </div>
        <s:include value="/Footer.jsp"/>
        <script type="text/javascript">
            $(function() {

                $('#halfd').raty({
                    half: true,
                    score: 0,
                    path: '../icons/',
                    scoreName: 'rating'
                });

                $('.score').raty({
                    readOnly: true,
                    path: '../icons/',
                    score: function() {
                        return $(this).attr('rating');
                    }
                });
            });
        </script>
    </body>
</html>
