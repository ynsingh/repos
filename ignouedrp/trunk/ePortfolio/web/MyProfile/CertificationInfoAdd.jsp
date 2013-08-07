<%-- 
    Document   : CertificationInfoAdd
    Created on : Oct 4, 2011, 4:31:41 PM
    Author     : IGNOU Team
    Version      : 1
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title>Add Certification</title>
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
    <body>
        <%
            if (session.getAttribute("user_id") == null) {
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
                            <div class="my_account_bg">Add Certification</div>
                            <div class="v_gallery">
                                <div class="bradcum"> <a href="<s:url value="/Welcome-Index.jsp"/>">Home</a>&nbsp;>&nbsp;<a href="<s:url value="/MyPortfolio.jsp"/>">My Portfolio</a>&nbsp;>&nbsp;<a href="<s:url value="/MyProfile/MyProfile.jsp"/>">My Profile</a> > <s:a action="ShowCertificateInfo">Certifications</s:a>&nbsp;>&nbsp;Add Certification </div>
                                <div class="w100 fl-l"><div class="tab_btn_2"><a onclick="history.go(-1);"><img src="<s:url value="/icons/back-arrow.png"/>" class="w25p" /></a></div></div>
                                <div class="w100 fl-l">
                                    <div class="w100 fl-l tc fbld fcgreen">
                                        <s:property value="msg"/>
                                    </div>
                                    <div class="w100 fl-l mart10">
                                        <fieldset class="w450p mar0a">
                                            <legend class="fbld">Add Certification</legend>
                                            <s:form action="AddCertificateInfo" method="post" name="myform" namespace="/MyProfile">
                                                <table width="80%" class="mar0a" cellpadding="4" border="0" cellspacing="0">
                                                    <s:textfield name="certificationName" label="Certification Name"/>
                                                    <s:textfield name="certificationAuthority" label="Certification Authority"/>
                                                    <s:textfield name="license" label="License Number"/>
                                                    <sj:datepicker id="date0" label="Valid From" name="certificationDate"
                                                                   value="today" displayFormat="MM, yy"                                                            
                                                                   changeMonth="true" changeYear="true"
                                                                   onChangeMonthYearTopics="true" timepicker="true" timepickerFormat=" "
                                                                   />
                                                    <sj:datepicker id="date1" label="Valid Upto" name="validDate"
                                                                   value="today" displayFormat="MM, yy"                                                            
                                                                   changeMonth="true" changeYear="true"
                                                                   onChangeMonthYearTopics="true" timepicker="true" timepickerFormat=" "
                                                                   />
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
