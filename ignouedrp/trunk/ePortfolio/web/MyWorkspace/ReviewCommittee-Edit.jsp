<%-- 
    Document   : ReviewCommittee-Edit
    Created on : Feb 17, 2012, 12:41:40 PM
    Author     : IGNOU Team
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
        <title>Edit Academic Responsibilities</title>
        <link href="<s:url value="/css/master.css"/>" rel="stylesheet" type="text/css" />         <link href="<s:url value="/css/main.css"/>" rel="stylesheet" type="text/css" />
        <link href="<s:url value="/css/collapse.css"/>" rel="stylesheet" type="text/css" />
        <link href="<s:url value="/css/skin.css"/>" rel="stylesheet" type="text/css" />
        <script type="text/javascript" src="<s:url value="/js/jquery-1.6.4.min.js"/>"></script>
        <sj:head />   
        <link href="<s:url value="/css/MonthYearPicker.css"/>" rel="stylesheet" type="text/css"/>
        <script type="text/javascript" src="<s:url value="/js/expand.js"/>"></script>
        <script>
            $(function() {
                $( "#accordion" ).accordion();
            });
        </script>
        <script type="text/javascript">
            if(window.history.forward(1) != null)
                window.history.forward(1);
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
                            <div class="my_account_bg">Edit Academic Responsibilities</div>
                            <div class="v_gallery">
                                <div class="w98 mar0a">
                                    <div class="w100 fl-l mart10">
                                        <div class="bradcum">
                                            <a href="<s:url value="/Welcome-Index.jsp"/>">Home</a>&nbsp;>&nbsp;<a href="<s:url value="/MyEdudation-Workspace.jsp"/>">My Education and Work</a>&nbsp;> <a href="<s:url value="/MyWorkspace/MyWorkspace.jsp"/>">My Workspace</a> &nbsp;> <a href="<s:url value="/MyWorkspace/MyPublications.jsp"/>">My Publication</a> &nbsp;> <a href="showRCInfo">Academic Responsibilities</a> &nbsp;> Edit Academic Responsibility
                                        </div>
                                        <div class="w100 fl-l mart10">
                                            <fieldset class="w500p mar0a">
                                                <legend class="fbld">Edit Academic Responsible</legend>
                                                <s:form action="UpdateRCInfo" method="post" namespace="/MyWorkspace" name="">
                                                    <table border="0" class="mar0a" cellpadding="2" cellspacing="0" width="90%">
                                                        <s:iterator value="RCList">
                                                            <s:hidden name="reviewCommitteeId"/>  <s:hidden name="userId"/>
                                                            <s:select label="Committee Type" name="committeeType"  cssClass="width255" headerKey="1" headerValue="Please Select Committee Type" list="{'Conference','Journal','Project','Others'}" />
                                                            <s:select label="Role" name="role"  cssClass="width255" headerKey="1" headerValue="Please Select Role in Committee" list="{'Convener','Chairman','Editor','Reviewer','Member'}" />
                                                            <s:textfield name="committeeName" label="Name of the Committee"/>
                                                            <sj:datepicker readonly="true"  id="date0" label="Appointed Month and Year" 
                                                                           name="Date" value="%{Date}" 
                                                                           displayFormat="MM, yy"                                                            
                                                                           changeMonth="true" changeYear="true"
                                                                           onChangeMonthYearTopics="true" timepicker="true" timepickerFormat=" "
                                                                           />
                                                            <!--
                                                            <s:select label="Friquency of the meeting" name="frequency"  cssClass="width255" headerKey="1" headerValue="-- Please Select Friquency of th Meeting --" list="{'Daily','Weekly','Bimonthly','Monthly', 'Quartarly','Half Yearly','Yearly','Others'}" />
                                                            <s:textfield name="url" label="URL"/>
                                                            <s:file label="Upload the Minutes/Report" name="minutesFile" disabled="true"/>-->
                                                            <s:textarea name="review" label="Summary/Review"/>

                                                        </s:iterator> 
                                                        <tr>
                                                            <td>&nbsp;</td>
                                                            <td>
                                                                <s:submit theme="simple" value="Save Changes" />
                                                                <s:reset theme="simple" value="Cancel" onClick="history.go(-1);" />
                                                            </td>
                                                        </tr>
                                                    </table>
                                                </s:form>
                                            </fieldset>
                                        </div>
                                    </div>
                                    <!--Right box End Here-->
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