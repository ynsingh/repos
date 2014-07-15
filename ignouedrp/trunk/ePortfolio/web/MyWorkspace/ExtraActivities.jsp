<%-- 
    Document   : ExtraActivities
    Created on : Feb 29, 2012, 5:19:17 PM
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
        <title>My Extra Activities</title>
        <link href="<s:url value="/css/master.css"/>" rel="stylesheet" type="text/css" />         <link href="<s:url value="/css/main.css"/>" rel="stylesheet" type="text/css" />
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
                            <div class="my_account_bg">Extra Activities</div>
                            <div class="v_gallery">
                                <div class="w98 mar0a mart10">
                                    <div class="bradcum"> <a href="<s:url value="/Welcome-Index.jsp"/>">Home</a>&nbsp;>&nbsp;<a href="<s:url value="/MyEdudation-Workspace.jsp"/>">My Education and Work</a>&nbsp;> <a href="<s:url value="/MyWorkspace/MyWorkspace.jsp"/>">My Workspace</a> &nbsp;> Extra Activities </div>
                                    <div class="w100 fl-l"><s:a href="ExtraActivities-Add.jsp" namespace="/MyWorkspace"> <img src="<s:url value="/icons/add.gif"/>" align="right" title="Add Extra Activity"/> </s:a></div>
                                    <div class="w100 fl-l tc fbld fcgreen">
                                        <s:property value="msg"/>
                                    </div>
                                    <div class="w100 fl-l mart10">
                                        <table width="100%" border="1" class="fl-l" cellpadding="0" cellspacing="0">
                                            <tr>
                                                <th width="5%" align="center">S. No</th>
                                                <th width="21%" align="center">Organization</th>
                                                <th width="13%" align="center">Role</th>
                                                <th width="15%" align="center">Cause</th>
                                                <th width="15%" align="center">Time Period</th>
                                                <th width="18%" align="center">Description</th>
                                                <th width="5%" align="center">Edit</th>
                                                <th width="8%" align="center">Delete</th>
                                            </tr>
                                            <s:iterator value="EXTList" status="stat">
                                                <tr>
                                                    <td valign="top" align="center"><s:property value="%{#stat.count}"/></td>
                                                    <td valign="top"><s:property value="organizationName"/></td>
                                                    <td valign="top"><s:property value="role"/></td>
                                                    <td valign="top"><s:property value="cause"/></td>
                                                    <td valign="top"><s:property value="tfrom"/>
                                                        &nbsp;to&nbsp;
                                                        <s:property value="tto"/></td>
                                                    <td align="center"><s:property value="description" escape="false"/></td>
                                                    <td valign="top" align="center"><a href="EditExt?activitiesId=<s:property value="activitiesId"/>"> <img src="<s:url value="/icons/edit.gif"/>" class="mart5" title="Edit Record"/> </a> </td>
                                                    <td valign="top" align="center"><a href="DeleteExt?activitiesId=<s:property value="activitiesId"/>" onclick="return confirm('Are you sure you want to delete this record ?')"> <img src="<s:url value="/icons/delete.gif"/>" class="mart5" title="Delete Record"/> </a> </td>
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
