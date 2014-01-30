<%-- 
    Document   : ContactInfo
    Created on : Sep 1, 2011, 5:58:26 PM
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
        <title>Contact Information</title>
        <link href="<s:url value="/css/master.css"/>" rel="stylesheet" type="text/css" />
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
                            <div class="my_account_bg">Contact Information</div>
                            <div class="v_gallery">
                                <div class="bradcum"> <a href="<s:url value="/Welcome-Index.jsp"/>">Home</a>&nbsp;>&nbsp;<a href="<s:url value="/MyPortfolio.jsp"/>">My Portfolio</a>&nbsp;>&nbsp;<a href="<s:url value="/MyProfile/MyProfile.jsp"/>">My Profile</a> > Contact Information </div>
                                <div class="w100 fl-l"><div class="tab_btn_2"><a onclick="history.go(-1);"><img src="<s:url value="/icons/back-arrow.png"/>" class="w25p" /></a></div></div>
                                <div class="w100 fl-l">
                                    <s:url id="PCID" action="EditContactInfo" namespace="/MyProfile"/>
                                    <div class="w100 fl-l tc fbld fcgreen">
                                        <s:property value="msg"/>
                                    </div>
                                    <div class="w100 fl-l mart10">
                                        <s:if test="%{PAaddress!=null}">
                                            <table width="90%" class="mar0a" cellpadding="4" border="0" cellspacing="0">
                                                <tr><th>Permanent Address</th><th>Correspondence Address</th>
                                                    <td>
                                                        <a href="editDEIContactInfo">
                                                            <img src="<s:url value="/icons/edit.gif"/>" title="Edit Contact Information"/>
                                                        </a>
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td>
                                                        <table class="mar0a" cellpadding="4" border="0" cellspacing="0" align="center">
                                                            <tr>
                                                                <th align="left">Address:</th>
                                                                <td> <s:property value="PAaddress"/> 
                                                            </tr>
                                                            <tr>
                                                                <th align="left">City:</th>
                                                                <td><s:property value="PAcity"/>
                                                                </td>
                                                            </tr>
                                                            <tr>
                                                                <th align="left">State:</th>
                                                                <td><s:property value="PAstate"/>
                                                                </td>
                                                            </tr>
                                                            <tr>
                                                                <th align="left">Country:</th>
                                                                <td><s:property value="PAcountry"/>
                                                                </td>
                                                            </tr>
                                                            <tr>
                                                                <th align="left">Pin/Zip Code:</th>
                                                                <td > <s:property value="PApinCode"/>
                                                                </td>
                                                            </tr>
                                                            <tr>
                                                                <th align="left">Phone No.(R.) :</th>
                                                                <td><s:property value="PAhomePhone"/>
                                                                </td>
                                                            </tr>
                                                            <tr>
                                                                <th align="left">Phone No.(O.) :</th>
                                                                <td ><s:property value="PAofficePhone"/>
                                                                </td>
                                                            </tr>
                                                            <tr>
                                                                <th align="left">FAX No. :</th>
                                                                <td ><s:property value="PAfax"/>
                                                                </td>
                                                            </tr>
                                                        </table>
                                                    </td>
                                                    <td>
                                                        <table class="mar0a" cellpadding="4" border="0" cellspacing="0" align="center">
                                                            <tr>
                                                                <th align="left">Address:</th>
                                                                <td> <s:property value="CAaddress"/> 
                                                                </td>
                                                            </tr>
                                                            <tr>
                                                                <th align="left">City:</th>
                                                                <td><s:property value="CAcity"/>
                                                                </td>
                                                            </tr>
                                                            <tr>
                                                                <th align="left">State:</th>
                                                                <td><s:property value="CAstate"/>
                                                                </td>
                                                            </tr>
                                                            <tr>
                                                                <th align="left">Country:</th>
                                                                <td><s:property value="CAcountry"/>
                                                                </td>
                                                            </tr>
                                                            <tr>
                                                                <th align="left">Pin/Zip Code:</th>
                                                                <td > <s:property value="CApinCode"/>
                                                                </td>
                                                            </tr>
                                                            <tr>
                                                                <th align="left">Phone No.(R.) :</th>
                                                                <td><s:property value="CAhomePhone"/>
                                                                </td>
                                                            </tr>
                                                            <tr>
                                                                <th align="left">Phone No.(O.) :</th>
                                                                <td ><s:property value="CAofficePhone"/>
                                                                </td>
                                                            </tr>
                                                            <tr>
                                                                <th align="left">FAX No. :</th>
                                                                <td ><s:property value="CAfax"/>
                                                                </td>
                                                            </tr>
                                                        </table>
                                                    </td>   
                                                    <td>
                                                        &nbsp;
                                                    </td>
                                                </tr>
                                            </table>
                                        </s:if>
                                        <s:else>
                                            <table width="70%" class="mar0a" cellpadding="4" border="0" cellspacing="0">
                                                <s:iterator value="contactListList" var="ProfileContact">
                                                    <tr>
                                                        <th valign="top" align="left" width="30%">Mailing Address: </th>
                                                        <td><s:property value="address1"/>
                                                            &nbsp;
                                                            <s:property value="address2"/>
                                                            &nbsp;
                                                            <s:property value="city"/>
                                                            &nbsp;
                                                            <s:property value="state"/>
                                                            &nbsp;
                                                            <s:property value="country"/>
                                                            &nbsp;
                                                            <s:property value="pin"/><s:property value="hashCode"/>
                                                        </td>
                                                        <td><s:a href="%{PCID}"><img src="<s:url value="/icons/edit.gif"/>" align="right" title="Edit Information"/></s:a></td>
                                                        </tr>
                                                        <tr>
                                                            <th align="left">Telephone&nbsp;(R.) No.: </th>
                                                            <td ><s:property value="HTelephone"/>
                                                        </td>
                                                        <td></td>
                                                    </tr>
                                                    <tr>
                                                        <th align="left">Telephone&nbsp;(O.) No.: </th>
                                                        <td ><s:property value="OTelephone"/>
                                                        </td>
                                                        <td></td>
                                                    </tr>
                                                    <tr>
                                                        <th align="left">Mobile No.: </th>
                                                        <td ><s:property value="mobileNo"/>
                                                        </td>
                                                        <td></td>
                                                    </tr>
                                                    <tr>
                                                        <th align="left">Fax No.:</th>
                                                        <td ><s:property value="faxNo"/>
                                                        </td>
                                                        <td></td>
                                                    </tr>
                                                    <tr>
                                                        <th align="left">Email(s) :</th>
                                                        <td ><s:property value="email1"/>
                                                            &nbsp;
                                                            <s:property value="email2"/>
                                                            &nbsp;
                                                            <s:property value="email3" />
                                                        </td>
                                                        <td></td>
                                                    </tr>
                                                    <tr>
                                                        <th align="left">Website&nbsp;(Off.)</th>
                                                        <td ><s:property value="owebsite"/>
                                                        </td>
                                                        <td></td>
                                                    </tr>
                                                    <tr>
                                                        <th align="left">Website&nbsp;(Per.)</th>
                                                        <td ><s:property value="pwebsite"/>
                                                        </td>
                                                        <td></td>
                                                    </tr>
                                                </s:iterator>
                                            </s:else>
                                        </table>
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
