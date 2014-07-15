<%-- 
    Document   : PersonalInformationEdit
    Created on : Feb 6, 2013, 2:38:43 PM
    Author     : Amit
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
        <link href="<s:url value="/css/master.css"/>" rel="stylesheet" type="text/css" />         <link href="<s:url value="/css/main.css"/>" rel="stylesheet" type="text/css" />
        <link href="<s:url value="/css/collapse.css"/>" rel="stylesheet" type="text/css" />
        <link href="<s:url value="/css/skin.css"/>" rel="stylesheet" type="text/css" />
        <script type="text/javascript" src="<s:url value="/js/jquery-1.6.4.min.js"/>"></script>
        <sj:head/>
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
                                    <div class="tab_btn_2"><a onclick="history.go(-1);"><img src="<s:url value="/icons/back-arrow.png"/>" class="w25p" /></a></div>
                                    <div class="bradcum"> <a href="<s:url value="/Welcome-Index.jsp"/>">Home</a>&nbsp;>&nbsp;<a href="<s:url value="/MyPortfolio.jsp"/>">My Portfolio</a>&nbsp;>&nbsp;<a href="<s:url value="/MyProfile/MyProfile.jsp"/>">My Profile</a> > Personal Information </div>
                                    <div class="w100 fl-l mart10">
                                        <div class="w100 fl-l tc fbld fcgreen">
                                            <s:property value="msg"/>
                                        </div>
                                        <div class="w100 fl-l">
                                            <table width="90%" class="mar0a" cellpadding="4" border="0" cellspacing="0">
                                                <s:form action="userUpdate" method="post" theme="simple">
                                                    <s:iterator value="userlist">
                                                        <s:hidden name="registrationId"/>
                                                        <tr>
                                                            <th align="left">Name:</th>
                                                            <td><s:textfield name="fname" />
                                                                &nbsp;
                                                                <s:textfield name="mname"/>
                                                                &nbsp;
                                                                <s:textfield name="lname"/>
                                                            </td>
                                                        </tr>
                                                        <tr>
                                                            <th align="left">Father's Name :</th>
                                                            <td ><s:textfield name="fatherName"/>
                                                            </td>
                                                            <td></td>
                                                        </tr>
                                                        <tr>
                                                            <th align="left">Mother's Name :</th>
                                                            <td ><s:textfield name="motherName"/>
                                                            </td>
                                                            <td></td>
                                                        </tr>
                                                        <tr>
                                                            <th align="left">Other Guardian's Name :</th>
                                                            <td ><s:textfield name="otherGardian"/>
                                                            </td>
                                                            <td></td>
                                                        </tr>
                                                        <tr>
                                                            <th align="left">Date of Birth :</th>
                                                            <td>  <sj:datepicker readonly="true"  id="date0" maxDate="-1d"  name="dateOfBirth" changeMonth="true" changeYear="true"/>

                                                            </td>
                                                            <td></td>
                                                        </tr>
                                                        <tr>
                                                            <th align="left">Place of Birth :</th>
                                                            <td ><s:textfield name="pbirth"/>
                                                            </td>
                                                            <td></td>
                                                        </tr>
                                                        <tr>
                                                            <th align="left">Marital Status :</th>
                                                            <td ><s:textfield name="mstatus"/>
                                                            </td>
                                                            <td></td>
                                                        </tr>
                                                        <tr>
                                                            <th align="left">Religion :</th>
                                                            <td ><s:textfield name="religion"/>
                                                            </td>
                                                            <td></td>
                                                        </tr>
                                                        <tr>
                                                            <th align="left">Nationality :</th>
                                                            <td ><s:textfield name="nationality"/>
                                                            </td>
                                                            <td></td>
                                                        </tr>
                                                        <tr>
                                                            <th align="left">Aadhaar No. :</th>
                                                            <td ><s:textfield name="aadhaarNo"/>
                                                            </td>
                                                            <td></td>
                                                        </tr>
                                                        <tr>
                                                            <th align="left">Passport No. :</th>
                                                            <td ><s:textfield name="passportNo"/>
                                                            </td>
                                                            <td></td>
                                                        </tr>
                                                        <tr>
                                                            <th align="left">PAN :</th>
                                                            <td ><s:textfield name="panNo "/>
                                                            </td>
                                                            <td></td>
                                                        </tr>
                                                        <tr>
                                                            <th align="left">Language Known :</th>
                                                            <td ><s:textfield name="languageKnown "/>
                                                            </td>
                                                            <td></td>
                                                        </tr>
                                                        <tr>
                                                            <td colspan="2" align="center"><s:submit value="Save Changes" />
                                                                <s:reset value="Cancel" onClick="history.go(-1);" /></td>
                                                        </tr>
                                                    </s:iterator>
                                                </s:form>
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
