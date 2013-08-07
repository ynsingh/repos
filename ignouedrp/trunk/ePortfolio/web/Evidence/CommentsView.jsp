<%--
Document : CommentsView
Created on : May 19, 2012, 11:02:01 AM
Author : IGNOU Team
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="sd" uri="/struts-dojo-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title>View Comments</title>
        <link href="<s:url value="/css/master.css"/>" rel="stylesheet" type="text/css" />
        <link href="<s:url value="/css/collapse.css"/>" rel="stylesheet" type="text/css" />
        <link href="<s:url value="/css/skin.css"/>" rel="stylesheet" type="text/css" />
        <script type="text/javascript" src="<s:url value="/js/jquery-1.6.4.min.js"/>"></script>

        <script type="text/javascript" src="<s:url value="/js/expand.js"/>"></script>
        <script src="<s:url value="/js/jquery.raty.min.js"/>" type="text/javascript"></script>
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
                <s:include value="/Header.jsp"/>
                <!--Header Ends Here-->

                <!--Middle Section Starts Here-->
                <div class="w100 fl-l">
                    <div class="middle_bg">
                        <!--Left box Starts Here-->
                        <s:include value="/Left-Nevigation.jsp"/>
                        <!--Left box Ends Here-->

                        <!--Right box Starts Here-->
                        <div class="right_box">
                            <div class="my_account_bg">View Comments on my Submission</div>
                            <div class="v_gallery">
                                <div class="w100 fl-l mart10">
                                    <div class="bradcum">
                                        <% if (role.contains("faculty")) {%>
                                        <a href="<s:url value="/Welcome-Index.jsp"/>">Home</a>&nbsp;>&nbsp;<a href="<s:url value="/MyEdudation-Workspace.jsp"/>">My Education and Work</a>&nbsp;> <a href="FacultyTaskShow">Task / Activities</a> &nbsp; > <a href="ActivSubList?evidenceId=<s:property value="evidenceId"/>">Task/Activity Details</a> &nbsp;> View Comments
                                        <% } else if (role.contains("student")) {%>
                                        <a href="<s:url value="/Welcome-Index.jsp"/>">Home</a>&nbsp;>&nbsp;<a href="<s:url value="/MyEdudation-Workspace.jsp"/>">My Education and Work</a>&nbsp;><a href="<s:url value="/Evidence/StudentTaskList"/>"> Task /Activity</a>&nbsp;> <a href="<s:url value="/Evidence/SubmitedEvi"/>"> Submitted Task / Activities</a>&nbsp;> View Comments
                                        <% }%>
                                    </div>
                                    <div class="w100 fl-l">
                                        <fieldset class="w550p mar0a mart10">
                                            <legend><strong>Task / Activities Details</strong></legend>
                                            <table width="100%" class="mar0a" cellpadding="4" border="0" cellspacing="0">
                                                <s:iterator value="EvidenceInfo">
                                                <tr>
                                                    <th align="left">Task / Activities</th>
                                                    <td>
                                                        <s:property value="evidence.evTitle"/>
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <th align="left">Created By</th>
                                                    <td><s:property value="evidence.user.fname"/>&nbsp;<s:property value="evidence.user.lname"/></td>

                                                </tr>
                                             <!--   <tr>
                                                    <td colspan="2">
                                                        <s:url id="likeId" value="countLike.action"><s:param name="evidenceSubId" value="submissionId"/><s:param name="commentId" value="commentId"/>
                                                        </s:url>
                                                        <sd:div href="%{#likeId}" updateFreq="1000">
                                                        </sd:div></td>
                                                </tr>-->
                                                </s:iterator>
                                            </table>
                                        </fieldset>
                                        <fieldset class="w550p mar0a mart10">
                                            <legend><strong>Comments</strong></legend>
                                            <div class="w100 fl-l tc fbld fcred mart10"><s:property value="msg"/></div>
                                            <div class="w200p mar0a">
                                                <span class="fbld wau fl-l">Average Rating:&nbsp;&nbsp;</span> 
                                                <div class="score wau fl-l" rating="<s:property value="avgStarRating"/>"></div>
                                            </div>
                                            <table width="100%" class="mar0a" cellpadding="4" border="0" cellspacing="0">
                                                <s:iterator value="CommentList" status="stat">
                                                    <tr>
                                                        <td valign="top" align="left" class="w120p">
                                                            <strong>
                                                                <s:property value="userByCommentorId.lname"/>&nbsp;<s:property value="userByCommentorId.fname"/>
                                                            </strong>
                                                        </td>
                                                        <td align="left">
                                                            <div class="fl-l mar0a">
                                                                <s:property value="comment" escape="false"/>
                                                                <s:if test="rating==0">
                                                                </s:if>
                                                                <s:else>
                                                                    <div class="score" rating="<s:property value="rating"/>"> &nbsp;</div>
                                                                </s:else>
                                                                <div class="mar0a fl-l">
                                                                    <s:if test="commentorFilePath!='null'">
                                                                        <a href="downloadCmtAtth?commentorId=<s:property value="userByCommentorId.emailId"/>&amp;commentorFilePath=<s:property value="commentorFilePath"/>" target="_blank">  <s:property value="commentorFilePath"/></a>
                                                                    </s:if>
                                                                    <s:elseif test="commentorFilePath=='null'">

                                                                    </s:elseif>
                                                                </div>
                                                            </div>

                                                            <% if (role.contains("faculty")) {%>

                                                            <% } else if (role.contains("student")) {%>
                                                            <div align="right">

                                                                <a href="ReplyComment?evidenceId=<s:property value="evidenceId"/>&amp;userId=<s:property value="userByCommentorId.emailId"/>">Comment</a>
                                                            </div>
                                                            <% }%>
                                                        </td>
                                                    </tr>
                                                    <tr>
                                                        <td colspan="2">
                                                            <s:url id="likeId" value="countLike.action"><s:param name="evidenceSubId" value="evidenceSubmission.submissionId"/><s:param name="commentId" value="commentId"/>
                                                            </s:url>
                                                            <sd:div href="%{#likeId}" updateFreq="5000" delay="0">

                                                            </sd:div>
                                                        </td>
                                                    </tr>
                                                </s:iterator>
                                            </table>
                                        </fieldset>
                                    </div>
                                </div>
                                <!--Right box End Here-->
                            </div>
                        </div>
                    </div>
                    <!--Middle Section Ends Here-->
                </div>
            </div>
        </div>
        <s:include value="/Footer.jsp"/>
        <script type="text/javascript">
            $(function() {

                $('#half').raty({
                    half : true,
                    score : 0,
                    path : '../icons/',
                    scoreName: 'rating'
                });

                $('.score').raty({
                    readOnly : true,
                    path : '../icons/',
                    score: function() {
                        return $(this).attr('rating');
                    }
                });

            });
        </script>
    </body>
</html>

