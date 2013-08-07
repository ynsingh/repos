<%-- 
    Document   : HonourAwardInfo
    Created on : Oct 13, 2011, 12:55:31 PM
    Version    : 1
Author     : IGNOU Team
Version      : 1
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title>Honors &amp; Awards</title>
        <link href="<s:url value="/css/master.css"/>" rel="stylesheet" type="text/css" />
        <link href="<s:url value="/css/collapse.css"/>" rel="stylesheet" type="text/css" />
        <link href="<s:url value="/css/skin.css"/>" rel="stylesheet" type="text/css" />
        <script type="text/javascript" src="<s:url value="/js/jquery-1.6.4.min.js"/>"></script>

        <script type="text/javascript" src="<s:url value="/js/expand.js"/>"></script>
        <script>
            $(function() {
                $("#accordion").accordion();
            });
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
                            <div class="my_account_bg">Honors &amp; Awards</div>
                            <div class="v_gallery">
                                <div class="bradcum"> <a href="<s:url value="/Welcome-Index.jsp"/>">Home</a>&nbsp;>&nbsp;<a href="<s:url value="/MyPortfolio.jsp"/>">My Portfolio</a>&nbsp;>&nbsp;<a href="<s:url value="/MyProfile/MyProfile.jsp"/>">My Profile</a> > Honor / Awards </div>
                                <div class="w100 fl-l">
                                    <div class="tab_btn_2"><a onclick="history.go(-1);"><img src="<s:url value="/icons/back-arrow.png"/>" class="w25p" /></a></div>
                                    <div class="wau fl-r mart10"><a href="HonourAwardInfoAdd.jsp"> <img src="<s:url value="/icons/add.gif"/>" title="Add Honors/Award"/> </a> </div>
                                </div>
                                <div class="w100 fl-l">
                                    <div class="w100 fl-l tc fbld fcgreen"><s:property value="msg"/></div>
                                    <div class="w100 fl-l mart10">
                                        <table width="100%" class="fl-l" cellpadding="4" border="1" cellspacing="0">
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
