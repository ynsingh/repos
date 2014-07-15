<%-- 
    Document   : Invited-Talks-Add
    Created on : Dec 8, 2011, 8:35:43 AM
    Author     : IGNOU Team
    Version    : 1
--%>
<%@page import="java.io.Serializable"%>
<%@page import="java.util.Date"%>
<%@page import="org.apache.log4j.Logger"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<%@taglib prefix="sjr" uri="/struts-jquery-richtext-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title>Add Invited Talks / Guest Lectures</title>
        <link href="<s:url value="/css/master.css"/>" rel="stylesheet" type="text/css" />         <link href="<s:url value="/css/main.css"/>" rel="stylesheet" type="text/css" />
        <link href="<s:url value="/css/collapse.css"/>" rel="stylesheet" type="text/css" />
        <link href="<s:url value="/css/skin.css"/>" rel="stylesheet" type="text/css" />
        <script type="text/javascript" src="<s:url value="/js/jquery-1.6.4.min.js"/>"></script>
        <sj:head />
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
                            <div class="my_account_bg">Add Invited Talk / Lecture</div>
                            <div class="w100 fl-l mart10">
                                <div class="w98 mar0a">
                                    <div class="bradcum"> <a href="<s:url value="/Welcome-Index.jsp"/>">Home</a>&nbsp;>&nbsp;<a href="<s:url value="/MyEdudation-Workspace.jsp"/>">My Education and Work</a>&nbsp;> <a href="<s:url value="/MyWorkspace/MyWorkspace.jsp"/>">My Workspace</a> &nbsp;> <a href="<s:url value="/MyWorkspace/MyPublications.jsp"/>">My Publication</a> &nbsp;> <a href="showTL">Invited Talk/Guest Lectures</a> &nbsp;> Invited Talk/Guest Lectures </div>
                                    <div class="w100 fl-l mart10">
                                        <fieldset class="w650p mar0a">
                                            <legend><strong>Invited Talk/Guest Lectures</strong></legend>
                                            <s:form action="AddTL" method="post" namespace="/MyWorkspace" name="" theme="simple">
                                                <table width="95%" border="0" class="mar0a" cellpadding="0" cellspacing="2">
                                                    <tr>
                                                        <td width="40%">Event Type:</td>
                                                        <td width="55%"><s:radio name="eventType" list="{'National','International'}"/></td>
                                                    </tr>
                                                    <tr>
                                                        <td>Name of the University / Institute:</td>
                                                        <td><s:textfield name="nameUniversity"/></td>
                                                    </tr>
                                                    <tr>
                                                        <td>Address:</td>
                                                        <td><s:textarea name="address"/></td>
                                                    </tr>
                                                    <tr>
                                                        <td>Name of the Event:</td>
                                                        <td><s:textfield name="nameEvent"/></td>
                                                    </tr>
                                                    <tr>
                                                        <td>Lecture Topic:</td>
                                                        <td><s:textfield name="lectureTopic"/></td>
                                                    </tr>
                                                    <tr>
                                                        <td>Delivered on:</td>
                                                        <td><sj:datepicker readonly="true"  id="date0" name="deleveredOn" changeMonth="true" changeYear="true"/></td>
                                                    </tr>
                                                    <tr>
                                                        <td>Time:</td>
                                                        <td><sj:datepicker readonly="true"  id="time0" name="timeFrom" timepicker="true" timepickerShowSecond="true" timepickerFormat="hh:mm:ss" timepickerOnly="true" cssClass="w55p" showOn="focus"/>
                                                            &nbsp;&nbsp;&nbsp;&nbsp;To&nbsp;&nbsp;&nbsp;
                                                            <sj:datepicker readonly="true"  id="time1" name="timeTo" timepicker="true" timepickerShowSecond="true" timepickerFormat="hh:mm:ss" timepickerOnly="true" cssClass="w55p" showOn="focus"/>
                                                        </td>
                                                    </tr>
                                                    <tr>
                                                        <td>Participant's Level:</td>
                                                        <td><s:select name="level" headerValue="-- Select Participant Level--" headerKey="1" list="{'Peer Group','Industry Employee','UG/PG Student','Others'}" cssClass="width255"/></td>
                                                    </tr>
                                                    <tr>
                                                        <td>URL, if any:</td>
                                                        <td><s:textfield name="url"/></td>
                                                    </tr>
                                                    <tr>
                                                        <td>Description:</td>
                                                        <td><sjr:tinymce
                                                                id="richtextTinymceAdvancedEditor"
                                                                name="description"
                                                                rows="10"
                                                                cols="10"
                                                                value="%{tdescription}"
                                                                editorLocal="en"
                                                                editorTheme="advanced"
                                                                editorSkin="o2k7"
                                                                toolbarAlign="left"
                                                                toolbarLocation="top"
                                                                toolbarButtonsRow1="bold,italic,underline,strikethrough,|,justifyleft,justifycenter,justifyright,justifyfull,|,link,unlink,anchor,image,|,formatselect,|,sub,sup"
                                                                toolbarButtonsRow2="bullist,numlist,|,outdent,indent,blockquote,|,undo,redo,|,insertdate,inserttime,preview,|,forecolor,backcolor,|,fontselect,fontsizeselect"
                                                                toolbarButtonsRow3=" "
                                                                /></td>
                                                    </tr>
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
