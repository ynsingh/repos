<%-- 
    Document   : ChangeLoginPassword
    Created on : Aug 13, 2012, 04:23:01 PM
    Author     : Vinay Kr. Sharma
--%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title>Change Login Password</title>
        <link href="<s:url value="/css/master.css"/>" rel="stylesheet" type="text/css" />
        <link href="<s:url value="/css/collapse.css"/>" rel="stylesheet" type="text/css" />
        <link href="<s:url value="/css/skin.css"/>" rel="stylesheet" type="text/css" />
        <script type="text/javascript" src="<s:url value="/js/jquery-1.6.4.min.js"/>"></script>
        <script type="text/javascript" src="<s:url value="/js/jquery.jcarousel.min.js"/>"></script>
        <script type="text/javascript" src="<s:url value="/js/expand.js"/>"></script>
        <script>
            $(function() {
                $( "#accordion" ).accordion();
            });
        </script>
    </head>
    <body>
        <% String role = session.getAttribute("role").toString();
            if (session.getAttribute("user_id") == null) {
                response.sendRedirect("../Login.jsp");
            }
        %>
        <div class="w100 fl-l">
            <div class="w990p mar0a">
                <!--Header Starts Here-->
                <s:include  value="./Header.jsp"/>
                <!--Header Ends Here-->

                <!--Middle Section Starts Here-->
                <div class="w100 fl-l">
                    <div class="middle_bg">
                        <!--Left box Starts Here-->
                        <s:include  value="/Left-Nevigation.jsp"/> 
                        <!--Left box Ends Here-->

                        <!--Right box Starts Here-->
                        <div class="right_box">
                            <div class="my_account_bg">Change Login Password</div>
                            <div class="w100 fl-l">
                                <div class="w100 fl-l tc fbld fcgreen">
                                    <s:property value="msg"/>
                                </div>
                                <fieldset class="w450p mar0a mart15">
                                    <legend><strong>Change Login Password</strong></legend>

                                    <s:form method="post" action="getMyPassword" onsubmit="return checkPassword(this);" theme="simple">
                                        <table class="mar0a mart10">
                                            <tr>
                                                <td>Old Password</td>
                                                <td><s:password name="oldPassword"/></td>
                                            </tr>
                                            <tr>
                                                <td>New Password</td>
                                                <td><s:password name="passwordField"/></td>
                                            </tr>
                                            <tr>
                                                <td>Confirm New Password</td>
                                                <td><s:password name="passwordConfirmField"/></td>
                                            </tr>
                                            <tr>
                                                <td align="center" colspan="2"><s:submit value="Change Password"/></td>
                                            </tr>
                                        </table>
                                    </s:form>
                                </fieldset>
                            </div>
                            <!--Right box Starts Here-->
                        </div>

                    </div>
                    <!--Middle Section Ends Here-->
                </div>
            </div>
        </div>
        <s:include  value="./Footer.jsp"/>  
        <script type="text/javascript" language="JavaScript">
            
            //--------------------------------
            // This code compares two fields in a form and submit it
            // if they're the same, or not if they're different.
            //--------------------------------
            
            function checkPassword(theForm) {
                if (theForm.passwordField.value != theForm.passwordConfirmField.value)
                {
                    alert('The Confirm Password don\'t match!');
                    return false;
                } else {
                    return true;
                }
            }
            //-->
        </script>
    </body>
</html>