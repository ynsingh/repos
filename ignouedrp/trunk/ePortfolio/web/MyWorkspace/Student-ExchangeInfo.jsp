<%-- 
    Document   : Student-ExchangeInfo
    Created on : Dec 27, 2011, 10:37:17 AM
    Author     : IGNOU Team
    Version    : 1
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
        <title>Student Exchange Programme</title>
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
                            <div class="my_account_bg">Student Exchange Programme</div>
                            <div class="w100 fl-l mart10">
                                <div class="w98 mar0a">
                                    <div class="bradcum"> <a href="<s:url value="/Welcome-Index.jsp"/>">Home</a>&nbsp;>&nbsp;<a href="<s:url value="/MyEdudation-Workspace.jsp"/>">My Education and Work</a>&nbsp;> <a href="<s:url value="/MyWorkspace/MyWorkspace.jsp"/>">My Workspace</a> &nbsp;> <a href="<s:url value="/MyWorkspace/MyPublications.jsp"/>">My Publication</a> &nbsp;> Student Exchange Programme </div>
                                    <div class="w100 fl-l"><a href="<s:url value="/MyWorkspace/Student-Exchange-Add.jsp"/>"> <img src="<s:url value="/icons/add.gif"/>" align="right" title="Add Employment Information"/> </a></div>
                                    <div class="w100 fl-l tc fbld fcgreen">
                                        <s:property value="msg"/>
                                    </div>
                                    <div class="w100 fl-l mart10">
                                        <table width="100%" border="1" class="fl-l" cellpadding="0" cellspacing="0">
                                            <tr>
                                                <th width="7%">S. No</th>
                                                <th width="16%">Name of the University</th>
                                                <th width="4%">Role</th>
                                                <th width="14%">Programme Theme</th>
                                                <th width="6%">Venue</th>
                                                <th width="6%">Duration</th>
                                                <th width="12%">Degree Level</th>
                                                <th width="16%">Research Collaboration</th>
                                                <th width="11%">Description</th>
                                                <th width="8%">Edit</th>
                                                <th width="9%">Delete</th>
                                            </tr>
                                            <s:iterator value="ExchangeProgrammeList" var="ExchangeModel" status="groupStatus">
                                                <tr>
                                                    <td align="center" valign="top"><s:property value="%{#groupStatus.count}"/></td>
                                                    <td align="center" valign="top"><a href="<s:property value="url"/>" target="_blank">
                                                            <s:property value="nameUniversity"/>
                                                        </a></td>
                                                    <td valign="top"><s:property value="role"/>
                                                        <s:property value="ifOther"/></td>
                                                    <td valign="top"><s:property value="programmeTheme"/></td>
                                                    <td valign="top"><s:property value="venue"/>
                                                        <s:property value="state"/>
                                                        <s:property value="country"/></td>
                                                    <td valign="top"><s:property value="durationFrom"/>
                                                        &nbsp;-&nbsp;
                                                        <s:property value="durationTo"/></td>
                                                    <td valign="top"><s:property value="degreeLevel"/>
                                                        &nbsp;(
                                                        <s:property value="degraeeName"/></td>
                                                    <td valign="top"><s:property value="researchColl"/></td>
                                                    <td valign="top"><s:property value="description"/></td>
                                                    <td align="center" valign="top"><a href="editExchangeInfo?studentExchangeId=<s:property value="studentExchangeId"/>"> <img src="<s:url value="/icons/edit.gif"/>" title="Edit Record"/> </a></td>
                                                    <td align="center" valign="top"><a href="deleteExchangeInfo?studentExchangeId=<s:property value="studentExchangeId"/>" onclick="return confirm('Are you sure you want to delete this record ?')"> <img src="<s:url value="/icons/delete.gif"/>" title="Delete Record"/> </a> </td>
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
