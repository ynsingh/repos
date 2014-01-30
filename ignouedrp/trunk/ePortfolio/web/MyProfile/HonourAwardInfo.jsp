<%--
    Document   : HonourAwardInfo
    Created on : Oct 13, 2011, 12:55:31 PM
    Version    : 1
Author     : IGNOU Team
Version      : 1
--%>
<%@page import="java.io.Serializable"%>
<%@page import="java.util.Date"%>
<%@page import="org.apache.log4j.Logger"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="sj" uri="/struts-jquery-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title>Honors &amp; Awards</title>
        <link href="<s:url value="/css/master.css"/>" rel="stylesheet" type="text/css" />
        <link href="<s:url value="/css/collapse.css"/>" rel="stylesheet" type="text/css" />
        <link href="<s:url value="/css/skin.css"/>" rel="stylesheet" type="text/css" />
        <sj:head/>
        <script type="text/javascript" src="<s:url value="/js/expand.js"/>"></script>
        <script>
            $(function() {
                $("#accordion").accordion();
            });
            $(document).ready(function() {
                $(".add_honour a").click(function() {
                    $("#add_honour_form").toggle();
                });
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
                            <div class="my_account_bg">Honors &amp; Awards</div>
                            <div class="v_gallery">
                                <div class="bradcum"> <a href="<s:url value="/Welcome-Index.jsp"/>">Home</a>&nbsp;>&nbsp;<a href="<s:url value="/MyPortfolio.jsp"/>">My Portfolio</a>&nbsp;>&nbsp;<a href="<s:url value="/MyProfile/MyProfile.jsp"/>">My Profile</a> > Honor / Awards </div>
                                <div class="w100 fl-l">
                                    <div class="tab_btn_2"><a onclick="history.go(-1);"><img src="<s:url value="/icons/back-arrow.png"/>" class="w25p" /></a></div>
                                    <div class="add_honour wau fl-r mart10"><a href="#" onclick="show_from()"> <img src="<s:url value="/icons/add.gif"/>" title="Add Honors/Award"/> </a> </div>
                                </div>
                                <div id="add_honour_form" style="display:none;" class="fl-l w100">
                                    <fieldset class="w400p mar0a">
                                        <legend class="fbld">Add Honors &amp; Awards</legend>
                                        <s:form action="AddHonor" method="post" namespace="/MyProfile" name="myform">
                                            <table width="80%" class="mar0a" cellpadding="4" border="0" cellspacing="0">
                                                <s:textfield name="haTitle" label="Title"/>
                                                <s:textfield name="issuer" label="Issuer"/>
                                                <sj:datepicker readonly="true"  id="date0" label="Issue Date"
                                                               name="haDate" value="today"
                                                               displayFormat="MM, yy"
                                                               changeMonth="true" changeYear="true"
                                                               onChangeMonthYearTopics="true" timepicker="true" timepickerFormat=" "
                                                               />
                                                <s:textarea name="haDescription" label="Description"/>
                                                <tr>
                                                    <td>&nbsp;</td>
                                                    <td><s:submit value="Save" theme="simple" />
                                                        <s:reset value="Cancel" theme="simple" onClick="history.go(-1);" /></td>
                                                </tr>
                                            </table>
                                            <br/>
                                        </s:form>
                                    </fieldset>
                                </div>
                                <div class="w100 fl-l">
                                    <div class="w100 fl-l tc fbld fcgreen"><s:property value="msg"/></div>
                                    <div class="w100 fl-l mart10">
                                        <table class="tablepaging" id="tablepaging" width="100%" cellspacing="0" cellpadding="5" border="1">
                                            <tr>
                                                <th>S. No</th>
                                                <th width="120">Title</th>
                                                <th>Issuer</th>
                                                <th width="75">Issue Date</th>
                                                <th  width="200">Description</th>
                                                <th width="75">Edit</th>
                                                <th width="75">Delete</th>
                                            </tr>
                                            <s:iterator value="HonorAwardList" var="ProHonor" status="stat">
                                                <tr>
                                                    <td align="center"><s:property value="%{#stat.count}"/></td>

                                                    <td><s:property value="haTitle"/>
                                                    </td>
                                                    <td><s:property value="issuer"/>
                                                    </td>
                                                    <td><s:property value="haDate"/>
                                                    </td>
                                                    <td><s:property value="haDescription"/>
                                                    </td>
                                                    <td valign="middle" style="vertical-align:middle;" align="center"><a href="editAwardInfo?honorAwardId=<s:property value="honorAwardId"/>"> <img src="<s:url value="/icons/edit.gif"/>" title="Edit Record"/> </a> </td>
                                                    <td valign="middle" style="vertical-align:middle;" align="center"><a href="deleteAwardInfo?honorAwardId=<s:property value="honorAwardId"/>" onclick="return confirm('Are you sure you want to delete this record ?')"> <img src="<s:url value="/icons/delete.gif"/>" title="Delete Record"/> </a> </td>
                                                </tr>
                                            </s:iterator>
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
