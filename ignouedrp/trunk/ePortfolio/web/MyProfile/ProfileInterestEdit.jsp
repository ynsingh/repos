<%-- 
    Document   : ProfileInterestEdit
    Created on : Dec 9, 2011, 3:43:21 PM
    Author     : IGNOU Team
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title>Edit Interest</title>
        <link href="<s:url value="/css/master.css"/>" rel="stylesheet" type="text/css" />
        <link href="<s:url value="/css/collapse.css"/>" rel="stylesheet" type="text/css" />
        <link href="<s:url value="/css/skin.css"/>" rel="stylesheet" type="text/css" />
        <script type="text/javascript" src="<s:url value="/js/jquery-1.6.4.min.js"/>"></script>

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
                            <div class="my_account_bg">Edit Interests</div>
                            <div class="v_gallery">
                                <div class="bradcum"> <a href="<s:url value="/Welcome-Index.jsp"/>">Home</a>&nbsp;>&nbsp;<a href="<s:url value="/MyPortfolio.jsp"/>">My Portfolio</a>&nbsp;>&nbsp;<a href="<s:url value="/MyProfile/MyProfile.jsp"/>">My Profile</a> > <s:a action="ShowInterestInfo">Interest</s:a>&nbsp;>&nbsp; Edit Interest </div>
                                <div class="w100 fl-l"><div class="tab_btn_2"><a onclick="history.go(-1);"><img src="<s:url value="/icons/back-arrow.png"/>" class="w25p" /></a></div></div>
                                <div class="w100 fl-l">
                                    <div class="w100 fl-l tc fbld fcgreen">
                                        <s:property value="msg"/>
                                    </div>
                                    <div class="w100 fl-l">
                                        <fieldset class="w400p mar0a">
                                            <legend class="fbld">Edit Interests</legend>
                                            <s:form action="UpdateInt" method="post" name="myform">
                                                <table align="center" width="80%" cellpadding="4" border="0" cellspacing="0">
                                                    <s:iterator value="editIntList" var="IntList">

                                                        <s:hidden name="interestId"/>
                                                        <s:textarea name="acadInterest" cssClass="width275" label="Academic Interest"/>
                                                        <s:textarea name="persInterest" cssClass="width275" label="Personal Interest"/>
                                                        <s:textarea name="techInterest" cssClass="width275" label="Technical Interest"/>
                                                        <s:textarea name="reserInterst" cssClass="width275" label="Research Interest"/>
                                                        <s:textarea name="myHobbies" cssClass="width275" label="My Hobbies"/>
                                                        <tr>
                                                            <td>&nbsp;</td>
                                                            <td><s:submit value="Save Changes" theme="simple" />
                                                                <s:reset value="Cancel" onClick="history.go(-1);" theme="simple" />
                                                            </td>
                                                        </tr>
                                                    </table>
                                                    <br/>
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
            frmvalidator.addValidation("acadInterest", "req", "Please enter your Academic Interest");
            frmvalidator.addValidation("acadInterest", "maxlen=200", "Max length is 200");
            frmvalidator.addValidation("acadInterest", "alphabetic_space", "Academic Interest should be Alphabetic with comma seperated only.");

            frmvalidator.addValidation("persInterest", "req", "Please enter your Personal Interest");
            frmvalidator.addValidation("persInterest", "maxlen=200", "Max length is 200");
            frmvalidator.addValidation("persInterest", "alphabetic_space", "Personal Interest should be Alphabetic with comma seperated only");

            frmvalidator.addValidation("techInterest", "req", "Please enter your Technical Interest");
            frmvalidator.addValidation("techInterest", "maxlen=200", "Max length is 200");
            frmvalidator.addValidation("techInterest", "alphabetic_space", "Technical Interest should be Alphabetic with comma seperated only");

            frmvalidator.addValidation("reserInterst", "req", "Please enter your Research Interest");
            frmvalidator.addValidation("reserInterst", "maxlen=200", "Max length is 200");
            frmvalidator.addValidation("reserInterst", "alphabetic_space", "Research Interest should be Alphabetic with comma seperated only");

            frmvalidator.addValidation("myHobbies", "req", "Please enter your Research Interest");
            frmvalidator.addValidation("myHobbies", "maxlen=200", "Max length is 200");
            frmvalidator.addValidation("myHobbies", "alphabetic_space", "Research Interest should be Alphabetic with comma seperated only");

        </script>
    </body>
</html>
