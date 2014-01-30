<%-- 
   Document   : AddNewEvent
   Created on : Nov 3, 2011, 12:03:49 PM 
   Author     : IGNOU Team
   Version    : 1
--%>
<%@page import="java.io.Serializable"%>
<%@page import="java.util.Date"%>
<%@page import="org.apache.log4j.Logger"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title>Add Events</title>
        <link href="<s:url value="/css/master.css"/>" rel="stylesheet" type="text/css" />
        <link href="<s:url value="/css/collapse.css"/>" rel="stylesheet" type="text/css" />
        <link href="<s:url value="/css/skin.css"/>" rel="stylesheet" type="text/css" />
        <script type="text/javascript" src="<s:url value="/js/jquery-1.6.4.min.js"/>"></script>
        <sj:head />
        <script type="text/javascript" src="<s:url value="/js/expand.js"/>"></script>
        <script type="text/javascript" src="<s:url value="/js/global.js"/>"></script>
        <script type="text/javascript" src="<s:url value="/js/countries.js"/>"></script>
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
                            <div class="my_account_bg">Add Event</div>
                            <div class="w98 mar0a">
                                <div class="w100 fl-l mart10">
                                    <div class="w100 fl-l mart10"> 
                                        <fieldset class="w500p mar0a">
                                            <legend class="fbld">Add Event Detail</legend>

                                            <s:form action="AddNewEvent" method="post" namespace="/Administrator/Events" theme="simple">
                                                <table width="80%" class="mar0a" cellpadding="4" border="0" cellspacing="0">
                                                    <s:i18n name="org.IGNOU.ePortfolio.Events.Events" >
                                                        <tr>
                                                            <td width="20%"><s:text name="title"/></td>
                                                            <td width="60%"><s:textfield name="eventTitle" label="Event Title"/></td>
                                                        </tr>
                                                        <tr>
                                                            <td><s:text name="sDate"/></td>
                                                            <td><sj:datepicker readonly="true"  id="date0" minDate="0d" name="eventDateFrom" label="Start Date" changeMonth="true" changeYear="true"/></td>
                                                        </tr>
                                                        <tr>
                                                            <td><s:text name="eDate"/></td>
                                                            <td><sj:datepicker readonly="true"  id="date1" minDate="0d" name="eventDateTo" label="End Date" changeMonth="true" changeYear="true"/></td>
                                                        </tr>
                                                        <tr>
                                                            <td><s:text name="aDate"/></td>
                                                            <td><sj:datepicker readonly="true"  id="date2" minDate="0d" name="eventDisplayDate" label="Announce Date" changeMonth="true" changeYear="true"/></td>
                                                        </tr>
                                                        <tr>
                                                            <td><s:text name="venue"/></td>
                                                            <td><s:textfield name="venue" label="Venue name"/></td>
                                                        </tr>
                                                        <tr>
                                                            <td><s:text name="country"/></td>
                                                            <td><select onchange="print_state('state',this.selectedIndex);" id="country" name ="country">
                                                                    <option value="#">Choose Your Country Name</option>
                                                                </select></td>
                                                        </tr>
                                                        <tr>
                                                            <td><s:text name="state"/></td>
                                                            <td><select id ="state" name ="state" >
                                                                </select></td>
                                                        </tr>
                                                        <script language="javascript">print_country("country");</script>
                                                        <tr>
                                                            <td><s:text name="address"/></td>
                                                            <td><s:textfield name="address" label="Address"/></td>
                                                        </tr>
                                                        <tr>
                                                            <td><s:text name="city"/></td>
                                                            <td><s:textfield name="city" label="City"/></td>
                                                        </tr>
                                                        <tr>
                                                            <td><s:text name="pCode"/></td>
                                                            <td><s:textfield name="pincode" label="Postal Code"/></td>
                                                        </tr>
                                                        <tr>
                                                            <td><s:text name="phone"/></td>
                                                            <td><s:textfield name="phone" label="Phone No."/></td>
                                                        </tr>
                                                        <tr>
                                                            <td><s:text name="email"/></td>
                                                            <td><s:textfield name="emailId" label="Email Id"/></td>
                                                        </tr>
                                                        <tr>
                                                            <td><s:text name="site"/></td>
                                                            <td><s:textfield name="website" label="Website" value="http://"/></td>
                                                        </tr>
                                                        <tr>
                                                            <td><s:text name="des"/></td>
                                                            <td><s:textarea  name="description" label="Description"/></td>
                                                        </tr>
                                                    </s:i18n>
                                                    <tr>
                                                        <td>&nbsp;</td>
                                                        <td><s:submit theme="simple" value="Save" />
                                                            <s:reset theme="simple" value="Cancel" onClick="history.go(-1);" />
                                                        </td>
                                                    </tr>
                                                </table>
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
