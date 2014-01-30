<%-- 
    Document   : PersonalInfoEdit
    Created on : Sep 16, 2011, 10:42:16 AM
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
        <title>Personal Information</title>
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
                            <div class="my_account_bg">Edit Personal Information</div>
                            <div class="v_gallery">
                                <div class="w100 fl-l mart10">
                                    <div class="bradcum"> <a href="<s:url value="/Welcome-Index.jsp"/>">Home</a>&nbsp;>&nbsp;<a href="<s:url value="/MyPortfolio.jsp"/>">My Portfolio</a>&nbsp;>&nbsp;<a href="<s:url value="/MyProfile/MyProfile.jsp"/>">My Profile</a> &nbsp;> <s:a action="ShowPersonalInfo">Personal Information</s:a> &nbsp;>&nbsp;Edit Personal Information </div>
                                    <div class="tab_btn_2"><a onclick="history.go(-1);"><img src="<s:url value="/icons/back-arrow.png"/>" class="w25p" /></a></div>
                                        <div class="w100 fl-l mart10">
                                        <s:if test="%{studentName!=null}">
                                            <s:form action="UpdateDEIPersonalInfo" method="post" name="myform" theme="simple">
                                                <fieldset class="w550p mar0a">
                                                    <legend class="fbld">Edit Personal Information</legend>
                                                    <table width="70%" class="mar0a" cellpadding="4" border="0" cellspacing="0">
                                                        <tr>
                                                            <td align="left">Enrollment No.:</td>
                                                            <td><s:hidden name="enrollmentNo"/>
                                                                <s:hidden name="randomNumber"/>
                                                                <s:hidden name="hashCode"/>
                                                                <s:property value="enrollmentNo"/>
                                                            </td>
                                                        </tr>
                                                        <tr>
                                                            <td>First Name:</td>
                                                            <td>
                                                                <s:if test="studentName!=null">
                                                                    <s:if test="studentName!=' '">
                                                                        <s:textfield name="studentName"/>
                                                                    </s:if>
                                                                    <s:else>
                                                                        <s:hidden name="studentName"/>
                                                                        <s:property value="studentName"/>
                                                                    </s:else>
                                                                </s:if>
                                                                <s:else>
                                                                    <s:textfield name="studentName"/>
                                                                </s:else>
                                                            </td>
                                                        </tr>
                                                        <tr>
                                                            <td>Father's Name:</td>
                                                            <td>
                                                                <s:if test="fatherName!=null">
                                                                    <s:if test="fatherName!=' '">
                                                                        <s:textfield name="fatherName"/>
                                                                    </s:if>
                                                                    <s:else>
                                                                        <s:hidden name="fatherName"/>
                                                                        <s:property value="fatherName"/>
                                                                    </s:else>
                                                                </s:if>
                                                                <s:else>
                                                                    <s:textfield name="fatherName"/>
                                                                </s:else>
                                                            </td>
                                                        </tr>
                                                        <tr>
                                                            <td>Mother's Name:</td>
                                                            <td>
                                                                <s:if test="motherName!=null">
                                                                    <s:if test="motherName!=' '">
                                                                        <s:textfield name="motherName"/>
                                                                    </s:if>
                                                                    <s:else>
                                                                        <s:hidden name="motherName"/>
                                                                        <s:property value="motherName"/>
                                                                    </s:else>
                                                                </s:if>
                                                                <s:else>
                                                                    <s:textfield name="motherName"/>
                                                                </s:else>
                                                            </td>
                                                        </tr>
                                                        <tr>
                                                            <td>Other Guardian(Husband) Name:</td>
                                                            <td>
                                                                <s:if test="otherGuardian!=null">
                                                                    <s:if test="otherGuardian!=' '">
                                                                        <s:textfield name="otherGuardian"/>
                                                                    </s:if>                                                                    
                                                                    <s:else>
                                                                        <s:hidden name="otherGuardian"/>
                                                                        <s:property value="otherGuardian"/>
                                                                    </s:else>
                                                                </s:if>
                                                                <s:else>
                                                                    <s:textfield name="otherGuardian"/>
                                                                </s:else>
                                                            </td>
                                                        </tr>

                                                        <tr>
                                                            <td>Gender:</td>
                                                            <td>
                                                                <s:if test="gender!=null">
                                                                    <s:if test="gender!=' '">
                                                                        <s:textfield name="gender"/>
                                                                    </s:if>
                                                                    <s:else>
                                                                        <s:hidden name="gender"/>
                                                                        <s:if test="gender='F'">
                                                                            Female
                                                                        </s:if>
                                                                        <s:elseif test="gender='M'">
                                                                            Male
                                                                        </s:elseif>
                                                                    </s:else>
                                                                </s:if>
                                                                <s:else>
                                                                    <s:radio  name="gender"  list="{'Male','Female'}"/>
                                                                </s:else>
                                                            </td>
                                                        </tr>
                                                        <tr>
                                                            <td>Date of Birth:</td>
                                                            <td>
                                                                <s:if test="dob!=null">
                                                                    <s:if test="dob!=' '">
                                                                        <s:textfield name="dob"/>
                                                                    </s:if>
                                                                    <s:else>
                                                                        <s:hidden name="dob"/>
                                                                        <s:property value="dob"/>
                                                                    </s:else>
                                                                </s:if>
                                                                <s:else>
                                                                    <sj:datepicker readonly="true"  id="date0" maxDate="-1d" value="%{dob}" name="dob" changeMonth="true" changeYear="true"/>
                                                                </s:else>
                                                            </td>
                                                        </tr>
                                                        <tr>
                                                            <td>Place of Birth:</td>
                                                            <td> 
                                                                <s:if test="pbirth!=null">
                                                                    <s:if test="pbirth!=' '">
                                                                        <s:textfield name="pbirth"/>
                                                                    </s:if>
                                                                    <s:else>
                                                                        <s:hidden name="pbirth"/>
                                                                        <s:property value="pbirth"/>
                                                                    </s:else>
                                                                </s:if>
                                                                <s:else>
                                                                    <s:textfield name="pbirth"/>
                                                                </s:else>
                                                            </td>
                                                        </tr>
                                                        <tr>
                                                            <td>Marital Status:</td>
                                                            <td><s:select name="mstatus"  list="{'Married','Single','Others'}" headerValue="--Select--" headerKey="0"/></td>
                                                        </tr>
                                                        <tr>
                                                            <td>Category:</td>
                                                            <td>
                                                                <s:if test="castCategory!=null">
                                                                    <s:if test="castCategory!=' '">
                                                                        <s:textfield name="castCategory"/>
                                                                    </s:if>
                                                                    <s:else>
                                                                        <s:hidden name="castCategory"/>
                                                                        <s:property value="castCategory"/>
                                                                    </s:else>
                                                                </s:if>
                                                                <s:else>
                                                                    <s:select name="castCategory"  list="{'General','SC','ST','OBC-Creamy','OBC-Non Creamy','Others'}" headerValue="--Select Category--" headerKey="0"/>
                                                                </s:else>
                                                            </td>
                                                        </tr>
                                                        <tr>
                                                            <td>Religion:</td>
                                                            <td>
                                                                <s:if test="religion!=null">
                                                                    <s:if test="religion!=' '">
                                                                        <s:textfield name="religion"/>
                                                                    </s:if>
                                                                    <s:else>
                                                                        <s:hidden name="religion"/>
                                                                        <s:property value="religion"/>
                                                                    </s:else>
                                                                </s:if>
                                                                <s:else>
                                                                    <s:select name="religion"  list="{'Ayyavazhi','Buddhism','Bahai Faith','Christianity','Christian','Gnosticism','Hinduism','Islam','Jainism','Judaism','Shinto','Sikhism','Slavic Neopaganism','Taoism','Wicca','Others'}" headerValue="--Select Religion--" headerKey="0"/> 
                                                                </s:else>
                                                            </td>
                                                        </tr>
                                                        <tr>
                                                            <td>Nationality:</td>
                                                            <td><s:if test="nationality!=null">
                                                                    <s:if test="nationality!=' '">
                                                                        <s:textfield name="nationality"/>
                                                                    </s:if>
                                                                    <s:else>
                                                                        <s:hidden name="nationality"/>
                                                                        <s:property value="nationality"/>
                                                                    </s:else>
                                                                </s:if>
                                                                <s:else>
                                                                    <s:textfield name="nationality"/>
                                                                </s:else>
                                                            </td>
                                                        </tr>
                                                        <tr>
                                                            <td>Aadhaar No.:</td>
                                                            <td>
                                                                <s:if test="UID!=null">
                                                                    <s:if test="!UID==' '">
                                                                        <s:textfield name="UID"/>
                                                                    </s:if>
                                                                    <s:else>
                                                                        <s:hidden name="UID"/>
                                                                        <s:property value="UID"/>
                                                                    </s:else>
                                                                </s:if>
                                                                <s:else>
                                                                    <s:textfield name="UID"/>
                                                                </s:else>
                                                            </td>
                                                        </tr>
                                                        <tr>
                                                            <td>Passport No.:</td>
                                                            <td>
                                                                <s:if test="passportNo!=null">
                                                                    <s:if test="passportNo!=' '">
                                                                        <s:textfield name="passportNo"/>
                                                                    </s:if>
                                                                    <s:else> 
                                                                        <s:hidden name="passportNo"/>
                                                                        <s:property value="passportNo"/>
                                                                    </s:else>
                                                                </s:if>
                                                                <s:else>
                                                                    <s:textfield name="passportNo"/>
                                                                </s:else>
                                                            </td>
                                                        </tr>
                                                        <tr>
                                                            <td>PAN:</td>
                                                            <td><s:if test="panNo!=null">
                                                                    <s:if test="panNo!=' '">
                                                                        <s:textfield name="panNo"/>
                                                                    </s:if>
                                                                    <s:else>
                                                                        <s:hidden name="panNo"/>
                                                                        <s:property value="panNo"/>
                                                                    </s:else>
                                                                </s:if>

                                                                <s:else>
                                                                    <s:textfield name="panNo"/>
                                                                </s:else>
                                                            </td>
                                                        </tr>
                                                        <tr>
                                                            <td>Language Known:</td>
                                                            <td><s:textarea name="languageKnown" title="English, Hindi, Chinese" cssClass="s_none"/></td>
                                                        </tr>
                                                        <tr>
                                                            <td colspan="2" align="center"><s:submit value="Save Changes"/>
                                                                <s:reset value="Cancel" onClick="history.go(-1);" /></td>
                                                        </tr>
                                                    </table>
                                                </fieldset>
                                            </s:form>
                                        </s:if>
                                        <s:else>
                                            <s:form action="UpdatePerInfo" method="post" name="myform" theme="simple">
                                                <fieldset class="w500p mar0a">
                                                    <legend class="fbld">Edit Personal Information</legend>
                                                    <table width="70%" class="mar0a" cellpadding="4" border="0" cellspacing="0">
                                                        <s:iterator value="UserInfoList">
                                                            <s:hidden name="registrationId"/>
                                                            <s:hidden name="emailId"/>
                                                            <tr>
                                                                <td width=50%">First Name:</td>
                                                                <td width="20%">
                                                                    <s:if test="fname!=null">
                                                                        <s:if test="fname!=' '">
                                                                            <s:textfield name="fname"/>
                                                                        </s:if>
                                                                        <s:else>
                                                                            <s:hidden name="fname"/>
                                                                            <s:property value="fname"/>
                                                                        </s:else>
                                                                    </s:if>
                                                                    <s:else>
                                                                        <s:textfield name="fname"/>
                                                                    </s:else>
                                                                </td>
                                                            </tr>
                                                            <tr>
                                                                <td>Middle Name:</td>
                                                                <td>
                                                                    <s:if test="mname!=null">
                                                                        <s:if test="mname!=' '">
                                                                            <s:textfield name="mname"/>
                                                                        </s:if>
                                                                        <s:else>
                                                                            <s:hidden name="mname"/>
                                                                            <s:property value="mname"/>
                                                                        </s:else>
                                                                    </s:if>
                                                                    <s:else>
                                                                        <s:textfield name="mname"/>
                                                                    </s:else>
                                                                </td>
                                                            </tr>
                                                            <tr>
                                                                <td width=50%">Last Name:</td>
                                                                <td width="20%">
                                                                    <s:if test="lname!=null">
                                                                        <s:if test="lname!=' '">
                                                                            <s:textfield name="lname"/>
                                                                        </s:if>
                                                                        <s:else>
                                                                            <s:hidden name="lname"/>
                                                                            <s:property value="lname"/>
                                                                        </s:else>
                                                                    </s:if>
                                                                    <s:else>
                                                                        <s:textfield name="lname"/>
                                                                    </s:else>
                                                                </td>
                                                            </tr>
                                                            <tr>
                                                                <td>Father's Name:</td>
                                                                <td>
                                                                    <s:if test="fatherName!=null">
                                                                        <s:if test="fatherName!=' '">
                                                                            <s:textfield name="fatherName"/>
                                                                        </s:if>
                                                                        <s:else>
                                                                            <s:hidden name="fatherName"/>
                                                                            <s:property value="fatherName"/>
                                                                        </s:else>
                                                                    </s:if>
                                                                    <s:else>
                                                                        <s:textfield name="fatherName"/>
                                                                    </s:else>
                                                                </td>
                                                            </tr>
                                                            <tr>
                                                                <td>Mother's Name:</td>
                                                                <td>
                                                                    <s:if test="motherName!=null">
                                                                        <s:if test="motherName!=' '">
                                                                            <s:textfield name="motherName"/>
                                                                        </s:if>
                                                                        <s:else>
                                                                            <s:hidden name="motherName"/>
                                                                            <s:property value="motherName"/>
                                                                        </s:else>
                                                                    </s:if>
                                                                    <s:else>
                                                                        <s:textfield name="motherName"/>
                                                                    </s:else>
                                                                </td>
                                                            </tr>
                                                            <tr>
                                                                <td>Other Guardian(Husband) Name:</td>
                                                                <td>
                                                                    <s:if test="otherGardian!=null">
                                                                        <s:if test="otherGardian!=' '">
                                                                            <s:textfield name="otherGardian"/>
                                                                        </s:if>                                                                    
                                                                        <s:else>
                                                                            <s:hidden name="otherGardian"/>
                                                                            <s:property value="otherGardian"/>
                                                                        </s:else>
                                                                    </s:if>
                                                                    <s:else>
                                                                        <s:textfield name="otherGardian"/>
                                                                    </s:else>
                                                                </td>
                                                            </tr>

                                                            <tr>
                                                                <td>Gender:</td>
                                                                <td>
                                                                    <s:if test="gender!=null">
                                                                        <s:if test="gender!=' '">
                                                                            <s:textfield name="gender"/>
                                                                        </s:if>
                                                                        <s:else>
                                                                            <s:hidden name="gender"/>
                                                                            <s:property value="gender"/>
                                                                        </s:else>
                                                                    </s:if>
                                                                    <s:else>
                                                                        <s:radio  name="gender"  list="{'Male','Female'}"/>
                                                                    </s:else>
                                                                </td>
                                                            </tr>
                                                            <tr>
                                                                <td>Date of Birth:</td>
                                                                <td>
                                                                    <s:if test="dateOfBirth!=null">
                                                                        <s:if test="dateOfBirth!=' '">
                                                                            <s:textfield name="dateOfBirth"/>
                                                                        </s:if>
                                                                        <s:else>
                                                                            <s:hidden name="dateOfBirth"/>
                                                                            <s:property value="dateOfBirth"/>
                                                                        </s:else>
                                                                    </s:if>
                                                                    <s:else>
                                                                        <sj:datepicker readonly="true"  id="date0" maxDate="-1d" value="%{dateOfBirth}" name="dateOfBirth" changeMonth="true" changeYear="true"/>
                                                                    </s:else>
                                                                </td>
                                                            </tr>
                                                            <tr>
                                                                <td>Place of Birth:</td>
                                                                <td> 
                                                                    <s:if test="pbirth!=null">
                                                                        <s:if test="pbirth!=' '">
                                                                            <s:textfield name="pbirth"/>
                                                                        </s:if>
                                                                        <s:else>
                                                                            <s:hidden name="pbirth"/>
                                                                            <s:property value="pbirth"/>
                                                                        </s:else>
                                                                    </s:if>
                                                                    <s:else>
                                                                        <s:textfield name="pbirth"/>
                                                                    </s:else>
                                                                </td>
                                                            </tr>
                                                            <tr>
                                                                <td>Marital Status:</td>
                                                                <td><s:select name="mstatus"  list="{'Married','Single','Others'}" headerValue="--Select--" headerKey="0"/></td>
                                                            </tr>
                                                            <tr>
                                                                <td>Category:</td>
                                                                <td>
                                                                    <s:if test="castCategory!=null">
                                                                        <s:if test="castCategory!=' '">
                                                                            <s:textfield name="castCategory"/>
                                                                        </s:if>
                                                                        <s:else>
                                                                            <s:hidden name="castCategory"/>
                                                                            <s:property value="castCategory"/>
                                                                        </s:else>
                                                                    </s:if>
                                                                    <s:else>
                                                                        <s:select name="castCategory"  list="{'General','SC','ST','OBC-Creamy','OBC-Non Creamy','Others'}" headerValue="--Select Category--" headerKey="0"/>
                                                                    </s:else>
                                                                </td>
                                                            </tr>
                                                            <tr>
                                                                <td>Religion:</td>
                                                                <td>
                                                                    <s:if test="religion!=null">
                                                                        <s:if test="religion!=' '">
                                                                            <s:select name="religion"  list="{'Ayyavazhi','Buddhism','Bahai Faith','Christianity','Christian','Gnosticism','Hinduism','Islam','Jainism','Judaism','Shinto','Sikhism','Slavic Neopaganism','Taoism','Wicca','Others'}" headerValue="--Select Religion--" headerKey="0"/> 
                                                                        </s:if>
                                                                        <s:else>
                                                                            <s:hidden name="religion"/>
                                                                            <s:property value="religion"/>
                                                                        </s:else>
                                                                    </s:if>
                                                                    <s:else>
                                                                        <s:select name="religion"  list="{'Ayyavazhi','Buddhism','Bahai Faith','Christianity','Christian','Gnosticism','Hinduism','Islam','Jainism','Judaism','Shinto','Sikhism','Slavic Neopaganism','Taoism','Wicca','Others'}" headerValue="--Select Religion--" headerKey="0"/> 
                                                                    </s:else>
                                                                </td>
                                                            </tr>
                                                            <tr>
                                                                <td>Nationality:</td>
                                                                <td><s:if test="nationality!=null">
                                                                        <s:if test="nationality!=' '">
                                                                            <s:textfield name="nationality"/>
                                                                        </s:if>
                                                                        <s:else>
                                                                            <s:hidden name="nationality"/>
                                                                            <s:property value="nationality"/>
                                                                        </s:else>
                                                                    </s:if>
                                                                    <s:else>
                                                                        <s:textfield name="nationality"/>
                                                                    </s:else>
                                                                </td>
                                                            </tr>
                                                            <tr>
                                                                <td>Aadhaar No.:</td>
                                                                <td>
                                                                    <s:if test="aadhaarNo!=null">
                                                                        <s:if test="!aadhaarNo==' '">
                                                                            <s:textfield name="aadhaarNo"/>
                                                                        </s:if>
                                                                        <s:else>
                                                                            <s:hidden name="aadhaarNo"/>
                                                                            <s:property value="aadhaarNo"/>
                                                                        </s:else>
                                                                    </s:if>
                                                                    <s:else>
                                                                        <s:textfield name="aadhaarNo"/>
                                                                    </s:else>
                                                                </td>
                                                            </tr>
                                                            <tr>
                                                                <td>Passport No.:</td>
                                                                <td>
                                                                    <s:if test="passportNo!=null">
                                                                        <s:if test="passportNo!=' '">
                                                                            <s:textfield name="passportNo"/>
                                                                        </s:if>
                                                                        <s:else> 
                                                                            <s:hidden name="passportNo"/>
                                                                            <s:property value="passportNo"/>
                                                                        </s:else>
                                                                    </s:if>
                                                                    <s:else>
                                                                        <s:textfield name="passportNo"/>
                                                                    </s:else>
                                                                </td>
                                                            </tr>
                                                            <tr>
                                                                <td>PAN:</td>
                                                                <td><s:if test="panNo!=null">
                                                                        <s:if test="panNo!=' '">
                                                                            <s:textfield name="panNo"/>
                                                                        </s:if>
                                                                        <s:else>
                                                                            <s:hidden name="panNo"/>
                                                                            <s:property value="panNo"/>
                                                                        </s:else>
                                                                    </s:if>

                                                                    <s:else>
                                                                        <s:textfield name="panNo"/>
                                                                    </s:else>
                                                                </td>
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
                                                </fieldset>
                                            </s:form>
                                        </s:else>

                                    </div>
                                </div>
                            </div>
                            <!--Right box Starts Here-->
                        </div>
                    </div>
                    <!--Middle Section Ends Here-->
                </div>
            </div>
        </div>
        <s:include value="/Footer.jsp"/>
        <script type="text/javascript">
            var frmvalidator = new Validator("myform");
            frmvalidator.addValidation("firstName", "req", "Please enter your First Name");
            frmvalidator.addValidation("firstName", "maxlen=20", "Max length is 20");
            frmvalidator.addValidation("firstName", "alpha_s", "First Name Should be Alphabetic chars only");

            frmvalidator.addValidation("lastName", "req", "Please enter your Last Name");
            frmvalidator.addValidation("lastName", "maxlen=20", "Max length is 20");
            frmvalidator.addValidation("lastName", "alpha_s", "Last Name Should be Alphabetic chars only");

            frmvalidator.addValidation("fatherName", "req", "Please enter your Father Name");
            frmvalidator.addValidation("fatherName", "maxlen=20", "Max length is 20");
            frmvalidator.addValidation("fatherName", "alpha_s", "Alphabetic chars only");

            frmvalidator.addValidation("dateOfBirth", "req", "Please enter your Date of Birth");

            frmvalidator.addValidation("pbirth", "alpha", "Place of Birth Should be Alphabetic chars only");
            frmvalidator.addValidation("pbirth", "maxlen=50");

            frmvalidator.addValidation("passportNo", "alphanumeric", "PassportNo Should be Alphanumeric");
            frmvalidator.addValidation("passportNo", "maxlen=15");

            frmvalidator.addValidation("panNo", "req", "Please enter Pan");
            frmvalidator.addValidation("panNo", "maxlen=11", "Max length is 11");

            frmvalidator.addValidation("gender", "req", "Please select Gender");
            frmvalidator.addValidation("gender", "VWZ_IsChecked(document.forms['myform']");

            frmvalidator.addValidation("mstatus", "dontselect=0", "Please Select Marital Status");


        </script>
    </body>
</html>
