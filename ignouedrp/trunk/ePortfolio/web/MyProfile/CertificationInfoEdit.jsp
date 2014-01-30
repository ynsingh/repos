<%-- 
    Document   : CertificationInfoEdit
    Created on : Oct 10, 2011, 11:55:12 AM
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
        <title>Edit Certification</title>
        <link href="<s:url value="/css/master.css"/>" rel="stylesheet" type="text/css" />
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
                            <div class="my_account_bg">Edit Certification</div>
                            <div class="v_gallery">
                                <div class="bradcum"> <a href="<s:url value="/Welcome-Index.jsp"/>">Home</a>&nbsp;>&nbsp;<a href="<s:url value="/MyPortfolio.jsp"/>">My Portfolio</a>&nbsp;>&nbsp;<a href="<s:url value="/MyProfile/MyProfile.jsp"/>">My Profile</a> > <s:a action="ShowCertificateInfo">Certifications</s:a>&nbsp;>&nbsp;Edit Certification </div>
                                <div class="w100 fl-l"><div class="tab_btn_2"><a onclick="history.go(-1);"><img src="<s:url value="/icons/back-arrow.png"/>" class="w25p" /></a></div></div>
                                <div class="w100 fl-l">
                                    <div class="w100 fl-l tc fbld fcgreen">
                                        <s:property value="msg"/>
                                    </div>
                                    <div class="w100 fl-l">
                                        <fieldset class="w450p mar0a">
                                            <legend class="fbld">Edit Certification</legend>
                                            <s:form action="UpdateCertificateInfo" method="post" namespace="/MyProfile" name="myform">
                                                <s:hidden name="certificationId"/>
                                                <s:hidden name="userId"/>
                                                <s:iterator value="CertificateList" var="Certificate">
                                                    <table width="80%" class="mar0a" cellpadding="4" border="0" cellspacing="0">
                                                        <s:textfield name="certificationName" label="Certification Name"/>
                                                        <s:textfield name="certificationAuthority" label="Certification Authority"/>
                                                        <s:textfield name="license" label="License Number"/>
                                                        <sj:datepicker readonly="true"  id="date0" label="Valid From" name="certificationDate"
                                                                       value="%{certificationDate}" displayFormat="MM, yy"                                                            
                                                                       changeMonth="true" changeYear="true"
                                                                       onChangeMonthYearTopics="true" timepicker="true" timepickerFormat=" "
                                                                       />
                                                        <sj:datepicker readonly="true"  id="date1" label="Valid Upto" name="validDate"
                                                                       value="%{validDate}" displayFormat="MM, yy"                                                            
                                                                       changeMonth="true" changeYear="true"
                                                                       onChangeMonthYearTopics="true" timepicker="true" timepickerFormat=" "
                                                                       /> 
                                                        <tr>
                                                            <td>&nbsp;</td>
                                                            <td><s:submit value="Save Changes" theme="simple"/>
                                                                <s:reset  value="Cancel" theme="simple" onClick="history.go(-1);" />
                                                            </td>
                                                        </tr>
                                                    </table>
                                                </s:iterator>
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
            frmvalidator.addValidation("certificationName", "req", "Please enter Certification Name");
            frmvalidator.addValidation("certificationName", "maxlen=20", "Max length is 20");
            frmvalidator.addValidation("certificationName", "alpha_s", "Alphabetic chars only");
            frmvalidator.addValidation("certificationAuthority", "req", "Please enter Certification Authority");
            frmvalidator.addValidation("certificationAuthority", "maxlen=20", "Max length is 20");
            frmvalidator.addValidation("certificationAuthority", "alpha_s", "Alphabetic only");
            frmvalidator.addValidation("license", "req", "Please enter License Number");
            frmvalidator.addValidation("license", "maxlen=20", "Max length is 20");
            frmvalidator.addValidation("license", "alphanumeric_space", "Alphanumeric only");
            frmvalidator.addValidation("certificationDate", "req", "Please enter Valid From");
            frmvalidator.addValidation("validDate", "req", "Please enter Valid From");
        </script>
    </body>
</html>
