<%-- 
    Document   : EventEdit
    Created on : Dec 12, 2011, 3:23:34 PM
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
        <sj:head />
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
                            <div class="my_account_bg">Edit Event Information</div>
                            <div class="w100 fl-l mart10">
                                <div class="w98 mar0a">
                                    <div class="w100 fl-l">
                                         <fieldset class="w500p mar0a">
                                            <legend class="fbld">Edit Event Detail</legend>
                                         <s:form action="UpdateEvent" method="post">
                                            <table width="85%" class="mar0a" cellpadding="4" border="0" cellspacing="0">
                                                <s:iterator value="eventList">
                                                    <s:hidden name="eventsId"/>
                                                    <s:textfield name="eventTitle" label="Event Title"/>
                                                    <sj:datepicker id="date0" minDate="0d" name="eventDateFrom" label="Start Date" changeMonth="true" changeYear="true"/>
                                                    <sj:datepicker id="date1" minDate="0d" name="eventDateTo" label="End Date" changeMonth="true" changeYear="true"/>
                                                    <sj:datepicker id="date2" minDate="0d" name="eventDisplayDate" label="Announce Date" changeMonth="true" changeYear="true"/>
                                                    <s:textfield name="venue" label="Venue name"/>
                                                    <s:textfield name="country" label="Country"/>
                                                    <s:textfield name="state" label="State/District"/>
                                                    <s:textfield name="address" label="Address"/>
                                                    <s:textfield name="city" label="City"/>
                                                    <s:textfield name="pincode" label="Postal Code"/>
                                                    <s:textfield name="phone" label="Phone No."/>
                                                    <s:textfield name="emailId" label="Email Id"/>
                                                    <s:textfield name="website" label="Website"/>
                                                    <s:textarea  name="description" label="Description" cols="20" rows="5" />
                                                    <tr>
                                                        <td>Postponed the Event</td>
                                                        <td width="50%"><s:checkbox label="Postponed" name="postponed" theme="simple"/>
                                                            Postponed</td>
                                                    </tr>
                                                    <sj:textarea name="postponedReason" label="Postponed Reason" cols="20" rows="5"/>
                                                    <tr>
                                                        <td>&nbsp;</td>
                                                        <td><s:submit theme="simple" value="Save Changes" />
                                                            <s:reset theme="simple" value="Cancel" onClick="history.go(-1);" />
                                                        </td>
                                                    </tr>
                                                </table>
                                            </s:iterator>
                                        </s:form>
                                         </fieldset>
                                        
                                    </div>
                                </div>
                            </div>
                            <!--Right box Starts Here-->
                        </div>
                    </div>
                    <!--Middle Section Ends Here-->
                </div>
            </div>
        </div>
        <s:include value="/Footer.jsp"/>
    </body>
</html>
