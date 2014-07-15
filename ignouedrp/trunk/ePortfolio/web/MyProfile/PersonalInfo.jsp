<%-- 
    Document   : PersonalInfo
    Created on : Sep 1, 2011, 5:58:40 PM
Author     : IGNOU Team
Version      : 1
--%>
<%@page import="java.io.Serializable"%>
<%@page import="java.util.Date"%>
<%@page import="org.apache.log4j.Logger"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title>Personal Information</title>
        <link href="<s:url value="/css/master.css"/>" rel="stylesheet" type="text/css" />         <link href="<s:url value="/css/main.css"/>" rel="stylesheet" type="text/css" />
        <link href="<s:url value="/css/collapse.css"/>" rel="stylesheet" type="text/css" />
        <link href="<s:url value="/css/skin.css"/>" rel="stylesheet" type="text/css" />
        <script type="text/javascript" src="<s:url value="/js/jquery-1.6.4.min.js"/>"></script>
        <script type="text/javascript" src="<s:url value="/js/jquery.jcarousel.min.js"/>"></script>
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
                            <div class="my_account_bg">Personal Information</div>
                            <div class="v_gallery">
                                <div class="w100 fl-l mart10">
                                    <div class="bradcum"> <a href="<s:url value="/Welcome-Index.jsp"/>">Home</a>&nbsp;>&nbsp;<a href="<s:url value="/MyPortfolio.jsp"/>">My Portfolio</a>&nbsp;>&nbsp;<a href="<s:url value="/MyProfile/MyProfile.jsp"/>">My Profile</a> > Personal Information </div>
                                    <div class="tab_btn_2"><a onclick="history.go(-1);"><img src="<s:url value="/icons/back-arrow.png"/>" class="w25p" /></a></div>
                                    <div class="w100 fl-l mart10">
                                        <div class="w100 fl-l tc fbld fcgreen">
                                            <s:property value="msg"/>
                                        </div>
                                        <div class="w100 fl-l">
                                            <table width="50%" class="mar0a" cellpadding="4" border="0" cellspacing="0">
                                                <s:if test="%{studentName!=null}">
                                                    <tr>
                                                        <th align="left">Enrollment No.:</th>
                                                        <td><s:property value="enrollmentNo"/> 
                                                        </td>
                                                        <td><a href="EditDEIPersonalInfo"><img src="<s:url value="/icons/edit.gif"/>" align="right" title="Edit Information"/></a></td>
                                                    </tr>
                                                    <tr>
                                                        <th align="left">Name:</th>
                                                        <td><s:property value="studentName"/>
                                                        </td>
                                                        <td>&nbsp;</td>
                                                    </tr>
                                                    <tr>
                                                        <th align="left">Father's Name :</th>
                                                        <td><s:property value="fatherName"/>
                                                        </td>
                                                        <td></td>
                                                    </tr>
                                                    <tr>
                                                        <th align="left">Mother's Name :</th>
                                                        <td><s:property value="motherName"/>
                                                        </td>
                                                        <td></td>
                                                    </tr>
                                                    <tr>
                                                        <th align="left">Gender :</th>
                                                        <td>
                                                            <s:if test="gender='F'">
                                                                Female
                                                            </s:if>
                                                            <s:elseif test="gender='M'">
                                                                Male
                                                            </s:elseif>
                                                        </td>
                                                        <td></td>
                                                    </tr>
                                                    <tr>
                                                        <th align="left">Date of Birth :</th>
                                                        <td><s:property value="dob"/>
                                                        </td>
                                                        <td></td>
                                                    </tr>
                                                    <tr>
                                                        <th align="left">Place of Birth :</th>
                                                        <td ><s:property value="pbirth"/>
                                                        </td>
                                                        <td></td>
                                                    </tr>
                                                    <tr>
                                                        <th align="left">Marital Status :</th>
                                                        <td ><s:property value="mstatus"/>
                                                        </td>
                                                        <td></td>
                                                    </tr>
                                                    <tr>
                                                        <th align="left">Category :</th>
                                                        <td ><s:property value="castCategory"/>
                                                        </td>
                                                        <td></td>
                                                    </tr>
                                                    <tr>
                                                        <th align="left">Religion :</th>
                                                        <td ><s:property value="religion"/>
                                                        </td>
                                                        <td></td>
                                                    </tr>
                                                    <tr>
                                                        <th align="left">Nationality :</th>
                                                        <td ><s:property value="nationality"/>
                                                        </td>
                                                        <td></td>
                                                    </tr>
                                                    <tr>
                                                        <th align="left">Aadhaar No. :</th>
                                                        <td ><s:property value="UID"/>
                                                        </td>
                                                        <td></td>
                                                    </tr>
                                                    <tr>
                                                        <th align="left">Passport No. :</th>
                                                        <td ><s:property value="passportNo"/>
                                                        </td>
                                                        <td></td>
                                                    </tr>
                                                    <tr>
                                                        <th align="left">PAN :</th>
                                                        <td ><s:property value="panNo "/>
                                                        </td>
                                                        <td></td>
                                                    </tr>
                                                    <tr>
                                                        <th align="left">Language Known :</th>
                                                        <td ><s:property value="languageKnown "/>
                                                        </td>
                                                        <td></td>
                                                    </tr>
                                                </s:if>
                                                <s:else>
                                                    <s:iterator value="UserInfoList">
                                                        <tr>
                                                            <th align="left">Name:</th>
                                                            <td><s:property value="fname"/>
                                                                <s:if test="mname!=null">
                                                                    &nbsp;<s:property value="mname"/>
                                                                </s:if>
                                                                <s:else>
                                                                </s:else>
                                                                &nbsp;
                                                                <s:property value="lname"/>
                                                            </td>
                                                            <td><a href="EditPersonalInfo?personalInfoId=<s:property value="personalInfoId"/>"><img src="<s:url value="/icons/edit.gif"/>" align="right" title="Edit Information"/></a></td>
                                                        </tr>
                                                        <tr>
                                                            <th align="left">Father's Name :</th>
                                                            <td ><s:property value="fatherName"/>
                                                            </td>
                                                            <td></td>
                                                        </tr>
                                                        <tr>
                                                            <th align="left">Mother's Name :</th>
                                                            <td ><s:property value="motherName"/>
                                                            </td>
                                                            <td></td>
                                                        </tr>
                                                        <tr>
                                                            <th align="left">Other Guardian's Name :</th>
                                                            <td ><s:property value="otherGardian"/>
                                                            </td>
                                                            <td></td>
                                                        </tr>
                                                        <tr>
                                                            <th align="left">Gender :</th>
                                                            <td ><s:property value="gender"/>
                                                            </td>
                                                            <td></td>
                                                        </tr>
                                                        <tr>
                                                            <th align="left">Date of Birth :</th>
                                                            <td><s:date name="dateOfBirth" format="MMM dd, yyyy"/>
                                                            </td>
                                                            <td></td>
                                                        </tr>
                                                        <tr>
                                                            <th align="left">Place of Birth :</th>
                                                            <td ><s:property value="pbirth"/>
                                                            </td>
                                                            <td></td>
                                                        </tr>
                                                        <tr>
                                                            <th align="left">Marital Status :</th>
                                                            <td ><s:property value="mstatus"/>
                                                            </td>
                                                            <td></td>
                                                        </tr>
                                                        <tr>
                                                            <th align="left">Category :</th>
                                                            <td ><s:property value="castCategory"/>
                                                            </td>
                                                            <td></td>
                                                        </tr>
                                                        <tr>
                                                            <th align="left">Religion :</th>
                                                            <td ><s:property value="religion"/>
                                                            </td>
                                                            <td></td>
                                                        </tr>
                                                        <tr>
                                                            <th align="left">Nationality :</th>
                                                            <td ><s:property value="nationality"/>
                                                            </td>
                                                            <td></td>
                                                        </tr>
                                                        <tr>
                                                            <th align="left">Aadhaar No. :</th>
                                                            <td ><s:property value="aadhaarNo"/>
                                                            </td>
                                                            <td></td>
                                                        </tr>
                                                        <tr>
                                                            <th align="left">Passport No. :</th>
                                                            <td ><s:property value="passportNo"/>
                                                            </td>
                                                            <td></td>
                                                        </tr>
                                                        <tr>
                                                            <th align="left">PAN :</th>
                                                            <td ><s:property value="panNo "/>
                                                            </td>
                                                            <td></td>
                                                        </tr>
                                                        <tr>
                                                            <th align="left">Language Known :</th>
                                                            <td ><s:property value="languageKnown "/>
                                                            </td>
                                                            <td></td>
                                                        </tr>
                                                    </s:iterator>
                                                </s:else>
                                            </table>
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
    </body>
</html>
