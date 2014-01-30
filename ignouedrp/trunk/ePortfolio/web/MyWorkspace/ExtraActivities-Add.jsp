<%-- 
    Document   : ExtraActivities-Add
    Created on : Feb 29, 2012, 12:37:07 PM
    Author     : IGNOU Team
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
        <title>My Extra Activities</title>
        <link href="<s:url value="/css/master.css"/>" rel="stylesheet" type="text/css" />
        <link href="<s:url value="/css/collapse.css"/>" rel="stylesheet" type="text/css" />
        <link href="<s:url value="/css/skin.css"/>" rel="stylesheet" type="text/css" />
        <script type="text/javascript" src="<s:url value="/js/jquery-1.6.4.min.js"/>"></script>
        <sj:head/>
        <script type="text/javascript" src="<s:url value="/js/expand.js"/>"></script>
        <script>
            $(function() {
                $("#accordion").accordion();
            });
        </script>
        <script type="text/javascript">
            if (window.history.forward(1) != null)
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
                            <div class="my_account_bg">My Profile</div>
                            <div class="v_gallery">
                                <div class="w100 fl-l mart10">
                                    <div class="w98 mar0a">
                                        <div class="bradcum"> <a href="<s:url value="/Welcome-Index.jsp"/>">Home</a>&nbsp;>&nbsp;<a href="<s:url value="/MyEdudation-Workspace.jsp"/>">My Education and Work</a>&nbsp;> <a href="<s:url value="/MyWorkspace/MyWorkspace.jsp"/>">My Workspace</a> &nbsp;> <a href="ShowExt">Extra Activities</a> &nbsp;> Add Extra Activities </div>
                                        <div class="w100 fl-l mart10">
                                            <fieldset class="w600p mar0a">
                                                <legend><strong>Add Extra Activities</strong></legend>
                                                <s:form action="AddExt" method="post" namespace="/MyWorkspace" name="myform" theme="simple">
                                                    <table width="95%" border="0" class="mar0a" cellpadding="5" cellspacing="0">
                                                        <tr>
                                                            <td width="25%">Which Organization do you support: </td>
                                                            <td width="70%"><s:textfield name="organizationName"/></td>
                                                        </tr>
                                                        <tr>
                                                            <td>Role: </td>
                                                            <td><s:textfield name="role"/></td>
                                                        </tr>
                                                        <tr>
                                                            <td>Cause: </td>
                                                            <td><s:select name="cause" list="{'Animal Welfare','Art and Culture','Children Welfare','Civil Right','Disaster and Humanitarim Relief','Economic Empowerment','Education','Environment','Health','Human Rights','Politics','Science and Technology','Social Services','Women Empowerment','Others'}" headerKey="-1" headerValue="-- Select Cause do you care about --"/></td>
                                                        </tr>
                                                        <tr>
                                                            <td>Time Period:</td>
                                                            <td>From&nbsp;&nbsp;&nbsp;
                                                                <sj:datepicker readonly="true"  id="date0" value="%{tfrom}" name="tfrom" changeMonth="true" changeYear="true" cssClass="w80p"/>
                                                                &nbsp;&nbsp;&nbsp;To&nbsp;&nbsp;&nbsp;
                                                                <sj:datepicker readonly="true"  id="date1" value="%{tto}" name="tto" changeMonth="true" changeYear="true" cssClass="w80p"/>
                                                            </td>
                                                        </tr>
                                                        <tr>
                                                            <td valign="top">Description: </td>
                                                            <td><sjr:tinymce
                                                                    id="richtextTinymceAdvancedEditor"
                                                                    name="description"
                                                                    label="Description"
                                                                    rows="10"
                                                                    cols="10"
                                                                    value="%{description}"
                                                                    editorLocal="en"
                                                                    editorTheme="advanced"
                                                                    editorSkin="o2k7"
                                                                    toolbarAlign="left"
                                                                    toolbarLocation="top"
                                                                    toolbarButtonsRow1="bold,italic,underline,strikethrough,|,justifyleft,justifycenter,justifyright,justifyfull,|,link,unlink,anchor,image,|,formatselect,|,sub,sup"
                                                                    toolbarButtonsRow2="bullist,numlist,|,outdent,indent,blockquote,|,undo,redo,|,insertdate,inserttime,preview,|,forecolor,backcolor,|,fontselect,fontsizeselect"
                                                                    toolbarButtonsRow3=" "
                                                                    />
                                                            </td>
                                                        </tr>
                                                        <tr>
                                                            <td>&nbsp;</td>
                                                            <td><s:submit value="Save" />
                                                                <s:reset value="Cancel" onClick="history.go(-1);" /></td>
                                                        </tr>
                                                    </table>
                                                </s:form>
                                            </fieldset>
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
        </div>
        <s:include value="/Footer.jsp"/>
        <script type="text/javascript">
            var frmvalidator = new Validator("myform");
            frmvalidator.addValidation("organizationName", "req", "Which Organization do you support");
            frmvalidator.addValidation("organizationName", "maxlen=20", "Max length is 20");
            frmvalidator.addValidation("organizationName", "alpha_s", "Alphabetic chars only");

            frmvalidator.addValidation("role", "req", "Please enter Role");
            frmvalidator.addValidation("role", "maxlen=20", "Max length is 20");
            frmvalidator.addValidation("role", "alpha_s", "Alphabetic chars only");

            frmvalidator.addValidation("cause", "dontselect=-1", "Please enter Cause");

            frmvalidator.addValidation("tfrom", "req", "Please enter Time Period");

            frmvalidator.addValidation("tto", "req", "Please enter Time Period");

            frmvalidator.addValidation("description", "req", "Please enter Description");
            frmvalidator.addValidation("description", "maxlen=200", "Max length is 200");
            frmvalidator.addValidation("description", "alpha_s", "Alphabetic chars only");

        </script>
    </body>
</html>
