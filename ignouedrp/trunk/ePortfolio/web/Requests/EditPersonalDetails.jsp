<%-- 
    Document   : EditPersonalDetails
    Created on : Sep 14, 2012, 12:01:12 PM
    Author     : Vinay
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="sj" uri="/struts-jquery-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title>Edit User Personal Information</title>
        <link href="<s:url value="/css/master.css"/>" rel="stylesheet" type="text/css" />
        <link href="<s:url value="/css/collapse.css"/>" rel="stylesheet" type="text/css" />
        <link href="<s:url value="/css/skin.css"/>" rel="stylesheet" type="text/css" />
        <script type="text/javascript" src="<s:url value="/js/jquery-1.6.4.min.js"/>"></script>
        <sj:head/>
        <script type="text/javascript" src="<s:url value="/js/expand.js"/>"></script>
        <script>
            $(function() {
                $( "#accordion" ).accordion();
            });
        </script>
    </head>
    <body>
        <%
            String role = session.getAttribute("role").toString();
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
                            <div class="my_account_bg">Edit User Personal Information</div>
                            <div class="w100 fl-l mart10">
                                <div class="bradcum">
                                    <a href="<s:url value="/Welcome-Index.jsp"/>">Home</a>&nbsp;>&nbsp;Requests&nbsp;>&nbsp;Edit User Personal Information
                                </div>
                                <div class="w100 fl-l mart10">
                                    <fieldset class="w500p mar0a">
                                            <legend class="fbld">Edit User Personal Information</legend>
                                    <s:form action="UpdateUserPerInfo" method="post" name="myform" theme="simple">
                                        <table width="60%" class="mar0a" cellpadding="4" border="0" cellspacing="0">
                                            <tr><th colspan="2">Request Details</th></tr>
                                            <s:iterator value="UsrReqList">
                                                <tr><s:hidden name="requestId"/><s:hidden name="requestorId"/>
                                                    <td>Requestor</td><td><s:property value="requestorId"/></td>
                                                </tr>
                                                <tr>
                                                    <td>Request Type</td><td><s:property value="requestType"/></td>
                                                </tr>
                                                <tr>
                                                    <td>Reason</td><td><s:property value="reason"/></td>
                                                </tr>
                                                <tr>
                                                    <td>New Record</td><td><s:property value="newRecord"/></td>
                                                </tr>
                                                <tr>
                                                    <td>Record Proof</td>
                                                    <td>
                                                        <s:if test="recordProof!='null'">
                                                            <a href="DownloadProof?requestorId=<s:property value="requestorId"/>&amp;recordProof=<s:property value="recordProof"/>" target="_blank"><s:property value="recordProof"/></a>
                                                        </s:if>
                                                        <s:elseif test="recordProof=='null'">

                                                        </s:elseif>
                                                    </td>
                                                </tr>
                                            </s:iterator>
                                            <tr><th colspan="2">Personal Information</th></tr>
                                            <s:iterator value="personalInfoList">
                                                <s:hidden name="personalInfoId"/>
                                                <s:hidden name="emailId"/>
                                                <tr>
                                                    <td>First Name:</td>
                                                    <td><s:textfield name="firstName"/></td>
                                                </tr>
                                                <tr>
                                                    <td>Last Name:</td>
                                                    <td><s:textfield name="lastName"/></td>
                                                </tr>
                                                <tr>
                                                    <td>Father's Name:</td>
                                                    <td><s:textfield name="fatherName"/></td>
                                                </tr>
                                                <tr>
                                                    <td>Mother's Name:</td>
                                                    <td><s:textfield name="motherName"/></td>
                                                </tr>
                                                <tr>
                                                    <td>Other Guardian(Husband) Name:</td>
                                                    <td><s:textfield name="otherGuardian"/></td>
                                                </tr>

                                                <tr>
                                                    <td>Gender:</td>
                                                    <td><s:radio  name="gender"  list="{'Male','Female'}"/></td>
                                                </tr>
                                                <tr>
                                                    <td>Date of Birth:</td>
                                                    <td><sj:datepicker id="date0" maxDate="-1d" value="%{dateOfBirth}" name="dateOfBirth" changeMonth="true" changeYear="true"/></td>
                                                </tr>
                                                <tr>
                                                    <td>Place of Birth:</td>
                                                    <td><s:textfield name="pbirth"/></td>
                                                </tr>
                                                <tr>
                                                    <td>Marital Status:</td>
                                                    <td><s:select name="mstatus"  list="{'Married','Single','Others'}" headerValue="--Select--" headerKey="0"/></td>
                                                </tr>
                                                <tr>
                                                    <td>Category:</td>
                                                    <td><s:select name="castCategory"  list="{'General','SC','ST','OBC-Creamy','OBC-Non Creamy','Others'}" headerValue="--Select Category--" headerKey="0"/></td>
                                                </tr>
                                                <tr>
                                                    <td>Religion:</td>
                                                    <td><s:select name="religion"  list="{'Ayyavazhi','Buddhism','Bahai Faith','Christianity','Christian','Gnosticism','Hinduism','Islam','Jainism','Judaism','Shinto','Sikhism','Slavic Neopaganism','Taoism','Wicca','Others'}" headerValue="--Select Religion--" headerKey="0"/></td>
                                                </tr>
                                                <tr>
                                                    <td>Nationality:</td>
                                                    <td><s:textfield name="nationality"/></td>
                                                </tr>
                                                <tr>
                                                    <td>Aadhaar No.:</td>
                                                    <td><s:textfield name="aadhaarNo"/></td>
                                                </tr>
                                                <tr>
                                                    <td>Passport No.:</td>
                                                    <td><s:textfield name="passportNo"/></td>
                                                </tr>
                                                <tr>
                                                    <td>PAN:</td>
                                                    <td><s:textfield name="panNo"/></td>
                                                </tr>
                                                <tr>
                                                    <td>Language Known:</td>
                                                    <td><s:textarea name="languageKnown" title="English, Hindi, Chinese" cssClass="s_none"/></td>
                                                </tr>
                                            </s:iterator>
                                            <tr>
                                                <td colspan="2" align="center"><s:submit value="Save Changes" />
                                                    <s:reset value="Cancel" onClick="history.go(-1);" /></td>
                                            </tr>
                                        </table>
                                    </s:form>
                                    </fieldset>
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