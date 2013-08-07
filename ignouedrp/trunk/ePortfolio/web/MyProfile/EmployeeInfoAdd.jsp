<%-- 
    Document   : EmployeeInfoAdd
    Created on : Sep 13, 2011, 12:09:11 PM
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
        <title>Add Employment Information</title>
        <link href="<s:url value="/css/master.css"/>" rel="stylesheet" type="text/css" />
        <link href="<s:url value="/css/collapse.css"/>" rel="stylesheet" type="text/css" />
        <link href="<s:url value="/css/skin.css"/>" rel="stylesheet" type="text/css" />
        <script type="text/javascript" src="<s:url value="/js/jquery-1.6.4.min.js"/>"></script>
        <sj:head />
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
                            <div class="my_account_bg">Add Employment Information</div>
                            <div class="v_gallery">
                                <div class="bradcum"><a href="<s:url value="/Welcome-Index.jsp"/>">Home</a>&nbsp;>&nbsp;<a href="<s:url value="/MyPortfolio.jsp"/>">My Portfolio</a>&nbsp;>&nbsp;<a href="<s:url value="/MyProfile/MyProfile.jsp"/>">My Profile</a> > <s:a action="ShowEmployeeInfo">Employment Information</s:a>&nbsp;>&nbsp;Add Employment </div>
                                <div class="w100 fl-l"><div class="tab_btn_2"><a onclick="history.go(-1);"><img src="<s:url value="/icons/back-arrow.png"/>" class="w25p" /></a></div></div>
                                <div class="w100 fl-l">
                                    <div class="w100 fl-l tc fbld fcgreen">
                                        <s:property value="msg"/>
                                    </div>
                                    <div class="w100 fl-l mart10">
                                        <s:form action="AddEmploment" method="post" namespace="/MyProfile" name="myform" theme="simple">
                                            <fieldset class="w450p mar0a">
                                                <legend class="fbld">Add Employment Information</legend>
                                                <table width="70%" class="mar0a" cellpadding="4" border="0" cellspacing="0">
                                                    <tr>
                                                        <td width="25%">Job Title</td>
                                                        <td width="45%"><s:textfield name="jtitle" /></td>
                                                    </tr>
                                                    <tr>
                                                        <td>Organization</td>
                                                        <td><s:textfield name="orgName" /></td>
                                                    </tr>
                                                    <tr>
                                                        <td>Address</td>
                                                        <td><s:textfield name="oaddress" /></td>
                                                    </tr>
                                                    <tr>
                                                        <td>City</td>
                                                        <td><s:textfield name="ocity" /></td>
                                                    </tr>
                                                    <tr>
                                                        <td>State</td>
                                                        <td><s:textfield name="ostate" /></td>
                                                    </tr>


                                                    <tr>
                                                        <td>Country</td>
                                                        <td><s:textfield name="ocountry" /></td>
                                                    </tr>
                                                    <tr>
                                                        <td>Joining Date</td>
                                                        <td><sj:datepicker id="date0" name="jdate" changeMonth="true" changeYear="true"/></td>
                                                    </tr>
                                                    <tr>
                                                        <td>Leaving Date</td>
                                                        <td><sj:datepicker id="date1" maxDate="-1d" name="ldate" changeMonth="true" changeYear="true"/></td>
                                                    </tr>
                                                    <tr>
                                                        <td>Description</td>
                                                        <td><s:textarea name="description"  cols="30" rows="5"/></td>
                                                    </tr>
                                                    <tr>
                                                        <td>&nbsp;</td>
                                                        <td><s:submit cssClass="fl-l" value="Save" />
                                                            <s:reset cssClass="fl-l" value="Cancel" onClick="history.go(-1);" />
                                                        </td>
                                                    </tr>
                                                </table>
                                            </fieldset>
                                        </s:form>
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

            frmvalidator.addValidation("jtitle", "req", "Please enter Job Title");
            frmvalidator.addValidation("jtitle", "maxlen=20", "Max length is 20");
            frmvalidator.addValidation("jtitle", "alpha_s", "Alphabetic chars only");

            frmvalidator.addValidation("orgName", "req", "Please enter Organization");
            frmvalidator.addValidation("orgName", "maxlen=20", "Max length is 20");
            frmvalidator.addValidation("orgName", "alpha_s", "Alphabetic chars only");

            frmvalidator.addValidation("oaddress", "req", "Please enter Address");
            frmvalidator.addValidation("oaddress", "maxlen=50", "Max length is 50");
            frmvalidator.addValidation("oaddress", "alphanumeric_space", "Address should Alphanumeric only");

            frmvalidator.addValidation("ocity", "req", "Please enter City");
            frmvalidator.addValidation("ocity", "maxlen=20", "Max length is 20");
            frmvalidator.addValidation("ocity", "alpha_s", "Alphabetic chars only");

            frmvalidator.addValidation("ostate", "req", "Please enter State");
            frmvalidator.addValidation("ostate", "maxlen=20", "Max length is 20");
            frmvalidator.addValidation("ostate", "alpha_s", "Alphabetic chars only");

            frmvalidator.addValidation("ocountry", "req", "Please enter Country");
            frmvalidator.addValidation("ocountry", "maxlen=20", "Max length is 20");
            frmvalidator.addValidation("ocountry", "alpha_s", "Alphabetic chars only");

            frmvalidator.addValidation("jdate", "Please enter Joining Date");

            // frmvalidator.addValidation("ldate","req","Please enter Leaving Date");

            frmvalidator.addValidation("description", "req", "Please enter Description");
            frmvalidator.addValidation("description", "maxlen=200", "Max length is 200");
            frmvalidator.addValidation("description", "alpha_s", "Alphabetic chars only");

        </script>
    </body>
</html>
