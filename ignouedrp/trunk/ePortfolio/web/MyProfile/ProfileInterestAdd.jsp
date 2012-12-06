<%-- 
    Document   : ProfileInterest
    Created on : Nov 30, 2011, 3:20:43 PM
    Author     : IGNOU Team
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title>Add Interest</title>
        <link href="<s:url value="/css/master.css"/>" rel="stylesheet" type="text/css" />
        <link href="<s:url value="/css/collapse.css"/>" rel="stylesheet" type="text/css" />
        <link href="<s:url value="/css/skin.css"/>" rel="stylesheet" type="text/css" />
        <script type="text/javascript" src="<s:url value="/js/jquery-1.6.4.min.js"/>"></script>

        <script type="text/javascript" src="<s:url value="/js/expand.js"/>"></script>
        <script type="text/javascript" src="<s:url value="/js/gen_validatorv4.js"/>"></script>
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
                            <div class="my_account_bg">Add Interests</div>
                            <div class="v_gallery">
                                <div class="w100 fl-l mart10">
                                    <div class="bradcum"> <a href="<s:url value="/Welcome-Index.jsp"/>">Home</a>&nbsp;>&nbsp;<a href="<s:url value="/MyPortfolio.jsp"/>">My Portfolio</a>&nbsp;>&nbsp;<a href="<s:url value="/MyProfile/MyProfile.jsp"/>">My Profile</a> > <s:a action="ShowInterestInfo">Interest</s:a>&nbsp;>&nbsp; Add Profile Interest </div>
                                </div>
                                <div class="w100 fl-l mart10">
                                    <div class="w100 fl-l tc fbld fcgreen">
                                        <s:property value="msg"/>
                                    </div>
                                    <div class="w100 fl-l mart5">
                                        <fieldset class="w400p mar0a">
                                            <legend class="fbld">Add Certification</legend>
                                            <s:form action="AddInterest" method="post" name="myform">
                                                <table width="80%" class="mar0a" cellpadding="4" border="0" cellspacing="0">
                                                    <tr>
                                                        <td><s:textarea cssClass="width275"  name="acadInterest" label="Academic Interest  " required="true"/>
                                                        </td>
                                                    </tr>
                                                    <tr>
                                                        <td>&nbsp;</td>
                                                        <td><b>Ex.</b></td>
                                                    </tr>
                                                    <tr>
                                                        <td><s:textarea cssClass="width275" name="persInterest" label="Personal Interest"/>
                                                        </td>
                                                    </tr>
                                                    <tr>
                                                        <td>&nbsp;</td>
                                                        <td><b>Ex.</b></td>
                                                    </tr>
                                                    <tr>
                                                        <td><s:textarea cssClass="width275" name="techInterest" label="Technical Interest"/>
                                                        </td>
                                                    </tr>
                                                    <tr>
                                                        <td>&nbsp;</td>
                                                        <td><b>Ex.</b></td>
                                                    </tr>
                                                    <tr>
                                                        <td><s:textarea cssClass="width275" name="reserInterst" label="Research Interest"/>
                                                        </td>
                                                    </tr>
                                                    <tr>
                                                        <td>&nbsp;</td>
                                                        <td><b>Ex.</b></td>
                                                    </tr>
                                                    <tr>
                                                        <td><s:textarea cssClass="width275" name="myHobbies" label="My Hobbies  " required="true"/>
                                                        </td>
                                                    </tr>
                                                    <tr>
                                                        <td>&nbsp;</td>
                                                        <td><b>Ex.</b></td>
                                                    </tr>
                                                    <tr>
                                                        <td>&nbsp;
                                                        </td>
                                                        <td><s:submit value="Save" theme="simple" /><s:reset value="Cancel" onClick="history.go(-1);" theme="simple" /></td>
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
        <s:include value="/Footer.jsp"/>
        <script type="text/javascript">
            var frmvalidator  = new Validator("myform");
            frmvalidator.addValidation("acadInterest","req","Please enter your Academic Interest");
            frmvalidator.addValidation("acadInterest","maxlen=200","Max length is 200");
            frmvalidator.addValidation("acadInterest","alnum_s","decimal","AlphaNumeric chars only");
    
            frmvalidator.addValidation("persInterest","req","Please enter your Personal Interest");
            frmvalidator.addValidation("persInterest","maxlen=200", "Max length is 200");
            frmvalidator.addValidation("persInterest","alnum_s","AlphaNumeric chars only");
  
            frmvalidator.addValidation("techInterest","req","Please enter your Technical Interest");
            frmvalidator.addValidation("techInterest","maxlen=200","Max length is 200");
            frmvalidator.addValidation("techInterest","alnum_s","AlphaNumeric chars only");

            frmvalidator.addValidation("reserInterst","req","Please enter your Research Interest");
            frmvalidator.addValidation("reserInterst","maxlen=200","Max length is 200");
            frmvalidator.addValidation("reserInterst","alnum_s","AlphaNumeric chars only");
  
            frmvalidator.addValidation("myHobbies","req","Please enter your Research Interest");
            frmvalidator.addValidation("myHobbies","maxlen=200","Max length is 200");
            frmvalidator.addValidation("myHobbies","alpha_s","Alphabetic chars only");
         
        </script>
    </body>
</html>
