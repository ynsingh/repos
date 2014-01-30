<%-- 
    Document   : InvitedTalksInfo
    Created on : Jan 18, 2012, 12:49:55 PM
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
        <title>Invited Talks / Lectures</title>
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
                            <div class="my_account_bg">Invited Talks / Lectures</div>
                            <div class="w100 fl-l mart10">
                                <div class="w98 mar0a">
                                    <div class="bradcum"> <a href="<s:url value="/Welcome-Index.jsp"/>">Home</a>&nbsp;>&nbsp;<a href="<s:url value="/MyEdudation-Workspace.jsp"/>">My Education and Work</a>&nbsp;> <a href="<s:url value="/MyWorkspace/MyWorkspace.jsp"/>">My Workspace</a> &nbsp;> <a href="<s:url value="/MyWorkspace/MyPublications.jsp"/>">My Publication</a> &nbsp;> Invited Talk/Guest Lectures </div>
                                    <div class="w100 fl-l">
                                        <s:a href="Invited-Talks-Add.jsp" namespace="/MyWorkspace"> <img src="<s:url value="/icons/add.gif"/>" align="right" title="Add Invited Talks"/> </s:a>
                                        </div>
                                        <div class="w100 fl-l tc fbld fcgreen">
                                        <s:property value="msg"/>
                                    </div>
                                    <div class="w100 fl-l mart10 richtxt_count">
                                        <table width="100%" class="fl-l" cellpadding="0" cellspacing="0" border="1">
                                            <s:bean name="org.IGNOU.ePortfolio.MyWorkspace.ConferenceAuthorComparator" var="IdComparator"/>
                                            <s:iterator value="TLList" status="groupStatus">
                                                <tr>
                                                    <td width="5%" align="center" valign="top"><s:property value="%{#groupStatus.count}"/></td>
                                                    <td width="85%"><strong>Event Type: </strong>
                                                        <s:property value="eventType"/>
                                                        <br/>
                                                        <strong>Name of the University: </strong>
                                                        <s:property value="nameUniversity"/>
                                                        <br/>
                                                        <strong> Address: </strong>
                                                        <s:property value="address"/>
                                                        <br/>
                                                        <strong>Name of the Event: </strong>
                                                        <s:property value="nameEvent"/>
                                                        <br/>
                                                        <strong> Lecture Topic: </strong>
                                                        <s:property value="lectureTopic"/>
                                                        <br/>
                                                        <strong> Delevered on: </strong>
                                                        <s:property value="deleveredOn"/>
                                                        <br/>
                                                        <strong> Time: </strong>
                                                        <s:property value="timeFrom"/>
                                                        to
                                                        <s:property value="timeTo"/>
                                                        <br/>
                                                        <strong> Participant Level </strong>
                                                        <s:property value="level"/>
                                                        <br/>
                                                        <strong> URL: </strong>
                                                        <s:property value="url"/>
                                                        <br/>
                                                        <strong> Description: </strong>
                                                        <s:property value="description" escape="false"/>
                                                        <br/></td>
                                                    <td width="5%" align="center" valign="top"><a href="EditTL?talkLectureId=<s:property value="talkLectureId"/>"> <img src="<s:url value="/icons/edit.gif"/>" title="Edit Record"/> </a></td>
                                                    <td width="5%" align="center" valign="top"><a href="DeleteTL?talkLectureId=<s:property value="talkLectureId"/>" onclick="return confirm('Are you sure you want to delete this record ?')"> <img src="<s:url value="/icons/delete.gif"/>" title="Delete Record"/> </a></td>
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
        </div>
        <s:include value="/Footer.jsp"/>
    </body>
</html>
