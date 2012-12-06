<%-- 
    Document   : EventShow
    Created on : Dec 12, 2011, 12:32:07 PM
    Author     : IGNOU Team
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title>Events</title>
        <link href="<s:url value="/css/master.css"/>" rel="stylesheet" type="text/css" />
        <link href="<s:url value="/css/collapse.css"/>" rel="stylesheet" type="text/css" />
        <link href="<s:url value="/css/skin.css"/>" rel="stylesheet" type="text/css" />
        <script type="text/javascript" src="<s:url value="/js/jquery-1.6.4.min.js"/>"></script>
        <sj:head/>
        <script type="text/javascript" src="<s:url value="/js/expand.js"/>"></script>
        <script type="text/javascript" src="<s:url value="/js/global.js"/>"></script>
         <script>
            $(function() {
                $( "#accordion" ).accordion();
            });
        </script>
    </head>
    <body>
        <%
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
                        <s:include  value="/Left-Nevigation.jsp"/> 
                        <!--Left box Ends Here-->
                        <!--Right box Starts Here-->
                        <div class="right_box">
                            <div class="my_account_bg">Events</div>
                            <div class="w100 fl-l mart10">
                                <div class="w98 mar0a">
                                    <div class="bradcum">
                                        <s:a action="ShowEventInfo">Upcoming Events</s:a>
                                            |
                                        <s:a action="ArchivedEventList">Archived Events</s:a>
                                            |
                                        <s:a action="postponedEventList">Postponed Events</s:a>
                                            | </div>
                                        <div class="w100 fl-l"><a href="<s:url value="/Events/AddNewEvent.jsp"/>"> <img src="<s:url value="/icons/add.gif"/>" align="right" title="Add New Event"/> </a></div>
                                    <div class="w100 fl-l tc fbld fcgreen">
                                        <s:property value="msg"/>
                                    </div>
                                    <div class="w100 fl-l mart10">
                                        <table width="100%" class="fl-l mar0 pad0" cellpadding="0" cellspacing="0" border="1">
                                            <tr>
                                                <td align="center" colspan="7">
                                                    <h3>
                                                        <s:property value="EventType"/>
                                                    </h3>
                                                </td>
                                            </tr>
                                            <tr>
                                                <th align="center">S.No.</th>
                                                <th align="center">Event Title</th>
                                                <th align="center">Event Start Date</th>
                                                <th align="center">Event End Date</th>
                                                <th align="center">Venue</th>
                                                <s:if test="EventType=='Upcoming Events'">
                                                    <th>Edit</th>
                                                    <th>Delete</th>
                                                </s:if>
                                                <s:elseif test="EventType=='Archived Events'"> &nbsp; </s:elseif>
                                                <s:elseif test="EventType=='Postponed Events'">
                                                    <th>Edit</th>
                                                </s:elseif>
                                            </tr>
                                            <s:iterator value="eventList" status="stat">
                                                <tr>
                                                    <td align="center"><s:property value="#stat.count"/></td>
                                                    <td><s:property value="eventTitle"/></td>
                                                    <td align="center"><s:property value="eventDateFrom"/></td>
                                                    <td align="center"><s:property value="eventDateTo"/></td>
                                                    <td><s:property value="venue"/></td>
                                                    <s:if test="EventType=='Upcoming Events'">
                                                        <td align="center"><a href="EditEvent?eventsId=<s:property value="eventsId"/>"> <img src="<s:url value="/icons/edit.gif"/>" title="Edit Event"/> </a> </td>
                                                        <td align="center"><a href="DeleteEvent?eventsId=<s:property value="eventsId"/>" onclick="return confirm('Are you sure you want to delete this record ?')"> <img src="<s:url value="/icons/delete.gif"/>" title="Delete Event"/> </a> </td>
                                                    </s:if>
                                                    <s:elseif test="EventType=='Archived Events'"> &nbsp; </s:elseif>
                                                    <s:elseif test="EventType=='Postponed Events'">
                                                        <td align="center"><a href="EditEvent?eventsId=<s:property value="eventsId"/>"> <img src="<s:url value="/icons/edit.gif"/>" title="Edit Event"/> </a> </td>
                                                    </s:elseif>
                                                </tr>
                                            </s:iterator>
                                        </table>
                                    </div>
                                    <!--Right box Starts Here-->
                                </div>
                            </div>
                        </div>
                    </div>
                    <!--Middle Section Ends Here-->
                </div>
            </div>
        </div>
        <s:include value="/Footer.jsp"/>
    </body>
</html>
