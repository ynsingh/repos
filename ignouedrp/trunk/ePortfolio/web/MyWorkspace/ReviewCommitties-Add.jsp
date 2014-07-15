<%-- 
    Document   : ReviewCommitties-Add
    Created on : Dec 19, 2011, 3:09:48 PM
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
        <title>Add Academic Responsibilities</title>
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
                            <div class="my_account_bg">Add Academic Responsibilities</div>
                            <div class="w100 fl-l mart10">
                                <div class="w98 mar0a">
                                    <div class="bradcum">
                                        <a href="<s:url value="/Welcome-Index.jsp"/>">Home</a>&nbsp;>&nbsp;<a href="<s:url value="/MyEdudation-Workspace.jsp"/>">My Education and Work</a>&nbsp;> <a href="<s:url value="/MyWorkspace/MyWorkspace.jsp"/>">My Workspace</a> &nbsp;> <a href="<s:url value="/MyWorkspace/MyPublications.jsp"/>">My Publication</a> &nbsp;> <a href="showRCInfo">Academic Responsibilities</a> &nbsp;> Add Academic Responsibility
                                    </div>
                                    <div class="w100 fl-l mart10">
                                        <fieldset class="w500p mar0a">
                                            <legend class="fbld">Add Academic Responsibility</legend>
                                            <s:form action="AddRCInfo" method="post" namespace="/MyWorkspace" name="">
                                                <table width="90%" border="0" class="mar0a" cellpadding="2" cellspacing="0">  
                                                    <s:select label="Committee Type" name="committeeType"  cssClass="width255" headerKey="1" headerValue="-- Please Select Committee Type --" list="{'Conference','Journal','Project','Others'}" />
                                                    <s:select label="Role" name="role"  cssClass="width255" headerKey="1" headerValue="-- Please Select Your Role in Committee --" list="{'Convener','Chairman','Editor','Reviewer','Member'}" />
                                                    <s:textfield name="committeeName" label="Appointed Month and Year"/>
                                                    <sj:datepicker readonly="true"  id="date0" label="Appointed Month and Year" 
                                                                   name="Date" value="today" 
                                                                   displayFormat="MM, yy"                                                            
                                                                   changeMonth="true" changeYear="true"
                                                                   onChangeMonthYearTopics="true" timepicker="true" timepickerFormat=" "
                                                                   />
                                                    <s:textarea name="review" label="Summary/Review"/>
                                                    <tr>
                                                        <td>&nbsp;</td>
                                                        <td>
                                                            <s:submit theme="simple" value="Save" />
                                                            <s:reset theme="simple" value="Cancel" onClick="history.go(-1);" />
                                                        </td>
                                                    </tr>
                                                </table>
                                            </s:form>
                                        </fieldset>
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