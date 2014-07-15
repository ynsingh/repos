<%-- 
    Document   : ChangePersonalId
    Created on : Sep 13, 2012, 3:18:36 PM
    Author     : Vinay
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
        <title>Change Personal Information</title>
        <link href="<s:url value="/css/master.css"/>" rel="stylesheet" type="text/css" />         <link href="<s:url value="/css/main.css"/>" rel="stylesheet" type="text/css" />
        <link href="<s:url value="/css/collapse.css"/>" rel="stylesheet" type="text/css" />
        <link href="<s:url value="/css/skin.css"/>" rel="stylesheet" type="text/css" />
        <script type="text/javascript" src="<s:url value="/js/jquery-1.6.4.min.js"/>"></script>

        <script type="text/javascript" src="<s:url value="/js/expand.js"/>"></script>
        <script>
            $(function() {
                $( "#accordion" ).accordion();
            });
        </script>
    </head>
    <body>
        <%
            final Logger logger = Logger.getLogger(this.getClass());
            String ipAddress = request.getRemoteAddr();
             logger.warn(session.getAttribute("user_id") + " Accessed from: " + ipAddress + " at: " + new Date());
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
                            <div class="my_account_bg">Change Personal Information</div>
                            <div class="v_gallery">
                                <div class="w100 fl-l mart15">
                                    <div class="bradcum">
                                        <a href="<s:url value="/Welcome-Index.jsp"/>">Home</a>&nbsp;>&nbsp;<a href="RequestIndex.jsp">Requests</a> >&nbsp;Change PAN/Passport/Aadhaar No.
                                    </div>
                                    <div class="w100 fl-l">
                                        <fieldset class="w500p mar0a">
                                            <legend class="fbld">Change Personal Request</legend>

                                        <s:form method="post" action="reqtoChangePIDs" namespace="/Requests" theme="simple" enctype="multipart/form-data">
                                            <table width="50%" class="mar0a" cellpadding="4" border="0" cellspacing="0">
                                                <tr>
                                                    <td>Request Type</td>
                                                    <td>
                                                        <select name="requestType">
                                                            <option value="" selected="true">----Select Request Type----</option>
                                                            <option value="aadhaarNo">Change the Aadhaar No.</option>
                                                            <option value="panNo">Change the PAN</option>
                                                            <option value="passportNo">Change the Passport No.</option>
                                                            <option value="dateOfBirth">Change Date of Birth</option>
                                                            <option value="others">Others</option>
                                                        </select>
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td valign="top">Reason</td>
                                                    <td><s:textarea name="reason" cssStyle="margin: 2px 0px; height: 121px; width: 233px"/></td>
                                                </tr>
                                                <tr>
                                                    <td valign="top">New Record</td>
                                                    <td><s:textarea name="newRecord" cssStyle="margin: 2px 0px; height: 121px; width: 233px"/></td>
                                                </tr>
                                                <tr>
                                                    <td>Upload Supporting Document</td>
                                                    <td><s:file name="uploadProof"/></td>
                                                </tr>
                                                <tr>
                                                    <td colspan="2" align="center">
                                                        <s:submit value="Request"/>
                                                    </td>
                                                </tr>
                                            </table>
                                        </s:form>
                                        </fieldset>
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