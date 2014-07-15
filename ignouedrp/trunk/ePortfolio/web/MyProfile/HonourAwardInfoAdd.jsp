<%-- 
    Document   : HonourAwardInfoAdd
    Created on : Oct 13, 2011, 12:55:56 PM
    Version    : 1
Author     : IGNOU Team
Version      : 1
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
        <title>Add Honors &amp; Awards</title>
        <link href="<s:url value="/css/master.css"/>" rel="stylesheet" type="text/css" />         <link href="<s:url value="/css/main.css"/>" rel="stylesheet" type="text/css" />
        <link href="<s:url value="/css/collapse.css"/>" rel="stylesheet" type="text/css" />
        <link href="<s:url value="/css/skin.css"/>" rel="stylesheet" type="text/css" />
        <script type="text/javascript" src="<s:url value="/js/jquery-1.6.4.min.js"/>"></script>
        <sj:head />   
        <link href="<s:url value="/css/MonthYearPicker.css"/>" rel="stylesheet" type="text/css"/>
        <script type="text/javascript" src="<s:url value="/js/expand.js"/>"></script>
        <script type="text/javascript" src="<s:url value="/js/gen_validatorv4.js"/>"></script>
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
                            <div class="my_account_bg">Add Honors &amp; Awards</div>
                            <div class="v_gallery">
                                <div class="bradcum"> <a href="<s:url value="/Welcome-Index.jsp"/>">Home</a>&nbsp;>&nbsp;<a href="<s:url value="/MyPortfolio.jsp"/>">My Portfolio</a>&nbsp;>&nbsp;<a href="<s:url value="/MyProfile/MyProfile.jsp"/>">My Profile</a> &nbsp;> <s:a action="ShowHonor">Honors / Awards</s:a>&nbsp;>&nbsp;Add Honors / Award </div>
                                <div class="w100 fl-l"><div class="tab_btn_2"><a onclick="history.go(-1);"><img src="<s:url value="/icons/back-arrow.png"/>" class="w25p" /></a></div></div>
                                <div class="w100 fl-l"> 
                                    <div class="w100 fl-l tc fbld fcgreen">
                                        <s:property value="msg"/>
                                    </div>
                                    <div class="w100 fl-l">
                                        <fieldset class="w400p mar0a">
                                            <legend class="fbld">Add Honors &amp; Awards</legend>
                                            <s:form action="AddHonor" method="post" namespace="/MyProfile" name="myform">
                                                <table width="80%" class="mar0a" cellpadding="4" border="0" cellspacing="0">
                                                    <s:textfield name="haTitle" label="Title"/>
                                                    <s:textfield name="issuer" label="Issuer"/>
                                                    <sj:datepicker readonly="true"  id="date0" label="Issue Date" 
                                                                   name="haDate" value="today" 
                                                                   displayFormat="MM, yy"                                                            
                                                                   changeMonth="true" changeYear="true"
                                                                   onChangeMonthYearTopics="true" timepicker="true" timepickerFormat=" "
                                                                   />
                                                    <s:textarea name="haDescription" label="Description"/>
                                                    <tr>
                                                        <td>&nbsp;</td>
                                                        <td><s:submit value="Save" theme="simple" />
                                                            <s:reset value="Cancel" theme="simple" onClick="history.go(-1);" /></td>
                                                    </tr>
                                                </table>
                                                <br/>
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
        <s:include value="/Footer.jsp"/>
        <script type="text/javascript">
            var frmvalidator = new Validator("myform");
            frmvalidator.addValidation("haTitle", "req", "Please enter Title");
            frmvalidator.addValidation("haTitle", "maxlen=20", "Max length is 20");
            frmvalidator.addValidation("haTitle", "alpha_s", "Alphabetic chars only");

            frmvalidator.addValidation("issuer", "req", "Please enter Issuer");
            frmvalidator.addValidation("issuer", "maxlen=20", "Max length is 20");
            frmvalidator.addValidation("issuer", "alpha_s", "Alphabetic chars only");

            frmvalidator.addValidation("haDate", "req", "Please enter Issue Date");
            frmvalidator.addValidation("issuer", "alphanumeric_space", "alphaNumeric chars only");

            frmvalidator.addValidation("haDescription", "req", "Please enter Description");
            frmvalidator.addValidation("haDescription", "maxlen=200", "Max length is 200");
            frmvalidator.addValidation("haDescription", "alphanumeric_space", "Alphanumeric chars only");
        </script>
    </body>
</html>
