<%--
    Document   : EvidenceReview
    Created on : May 28, 2012, 12:50:49 PM
    Author     : IGNOU Team
--%>

<%@page import="java.io.Serializable"%>
<%@page import="java.util.Date"%>
<%@page import="org.apache.log4j.Logger"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title>Review Task / Activities</title>
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
                            <div class="my_account_bg">Review Task / Activities</div>
                            <div class="v_gallery">
                                <div class="w98 mar0a mart10">
                                    <div class="bradcum">
                                        <a href="<s:url value="/Welcome-Index.jsp"/>">Home</a>&nbsp;>&nbsp;<a href="<s:url value="/MyEdudation-Workspace.jsp"/>">My Education and Work</a>&nbsp;> <a href="FacultyTaskShow">Task / Activities</a> > Review Task / Activities
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
                                        <div class="w100 fl-l mart20">
                                            <table class="tablepaging" id="tablepaging" width="75%" cellspacing="0" cellpadding="5" border="1">
                                            <s:iterator value="EvidenceReviewList">
                                                <tr><td align="left" class="fbld w200p">Title</td>
                                                    <td>
                                                        <s:property value="evTitle"/>
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td align="left" class="fbld w200p">Start Date (MM/DD/YY)</td>
                                                    <td>
                                                        <s:property value="openDate"/>
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td align="left" class="fbld w200p">Closing Date (MM/DD/YY)</td>
                                                    <td>
                                                        <s:property value="closeDate"/>
                                                    </td>
                                                </tr>
                                                <!--      <tr>
                                                          <td align="left" class="fbld w200p">Accept Until</td>
                                                          <td>
                                                <s:property value="lastAcceptDate"/>
                                            </td>
                                        </tr>
                                                -->
                                                <tr>
                                                    <td valign="top" align="left" class="fbld w200p">Instructions</td>
                                                    <td valign="top">
                                                        <s:property value="instruction" escape="false"/>
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td align="left" class="fbld w200p">Supporting Document</td>
                                                    <td><s:if test="assAttach!='null'">
                                                            <a href="downnloadAttach?facultyId=<s:property value="user.emailId"/>&amp;assAttach=<s:property value="assAttach"/>" target="_blank">
                                                                <s:property value="assAttach"/>
                                                            </a>
                                                        </s:if>
                                                        <s:elseif test="assAttach=='null'">
                                                        </s:elseif>
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td align="center" class="fbld w200p" colspan="2">
                                                        <s:if test="poststatus=='true'">
                                                            <s:reset value="Cancel" onClick="history.go(-1);" theme="simple"/>
                                                        </s:if>
                                                        <s:elseif test="poststatus=='false'">
                                                            <a href="EditEvidence?evidenceId=<s:property value="evidenceId"/>&amp;courseId=<s:property value="courseId"/>"><input type="button" value="Edit"/></a>
                                                            <s:reset value="Cancel" onClick="history.go(-1);" theme="simple"/>
                                                        </s:elseif>
                                                    </td>
                                                </tr>
                                            </s:iterator>
                                        </table>
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