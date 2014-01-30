<%--
    Document   : ProfileInterestShow
    Created on : Dec 9, 2011, 2:19:51 PM
    Author     : IGNOU Team
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
        <title>Interest</title>
        <link href="<s:url value="/css/master.css"/>" rel="stylesheet" type="text/css" />
        <link href="<s:url value="/css/collapse.css"/>" rel="stylesheet" type="text/css" />
        <link href="<s:url value="/css/skin.css"/>" rel="stylesheet" type="text/css" />
        <script type="text/javascript" src="<s:url value="/js/jquery-1.6.4.min.js"/>"></script>

        <script type="text/javascript" src="<s:url value="/js/expand.js"/>"></script>
        <script>
            $(function() {
                $("#accordion").accordion();
            });
            $(document).ready(function() {
                $(".add_course a").click(function() {
                    $("#add_course_form").toggle();
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
                            <div class="my_account_bg">Interest Information</div>
                            <div class="v_gallery">
                                <div class="bradcum"> <a href="<s:url value="/Welcome-Index.jsp"/>">Home</a>&nbsp;>&nbsp;<a href="<s:url value="/MyPortfolio.jsp"/>">My Portfolio</a>&nbsp;>&nbsp;<a href="<s:url value="/MyProfile/MyProfile.jsp"/>">My Profile</a> >Interest</div>
                                <div class="w100 fl-l"><div class="tab_btn_2"><a onclick="history.go(-1);"><img src="<s:url value="/icons/back-arrow.png"/>" class="w25p" /></a></div></div>
                                <div class="add_course fl-r"><a href="#" onclick="show_from()"><img src="<s:url value="/icons/add.gif"/>" align="right" title="Add Reference"/></a></div>
                                <div id="add_course_form" style="display:none;" class="fl-l w100">
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
                                <div class="w100 fl-l">
                                    <div class="w100 fl-l tc fbld fcgreen">
                                        <s:property value="msg"/>
                                    </div>
                                    <div class="w100 fl-l">
                                        <table class="tablepaging" id="tablepaging" width="100%" cellspacing="0" cellpadding="5" border="1">
                                            <tr>
                                                <th>S. No</th>
                                                <th>Academic Interest</th>
                                                <th>Personal Interest</th>
                                                <th>Technical Interest</th>
                                                <th>Research Interest</th>
                                                <th>My Hobbies</th>
                                                <th>Edit</th>
                                                <th>Delete</th>
                                            </tr>
                                            <s:iterator value="IntListList" var="IntList" status="stat">
                                                <tr>
                                                    <td align="center"><s:property value="%{#stat.count}"/></td>
                                                    <td><s:property value="acadInterest"/></td>
                                                    <td><s:property value="persInterest"/></td>
                                                    <td><s:property value="techInterest"/></td>
                                                    <td><s:property value="reserInterst"/></td>
                                                    <td><s:property value="myHobbies"/></td>
                                                    <td align="center"><a href="EditInterestInfo?interestId=<s:property value="interestId"/>"> <img src="<s:url value="/icons/edit.gif"/>" title="Edit Interest"/> </a> </td>
                                                    <td align="center"><a href="DeleteInterestInfo?interestId=<s:property value="interestId"/>" onclick="return confirm('Are you sure you want to delete this record ?')"> <img src="<s:url value="/icons/delete.gif"/>" title="Delete Interest"/> </a> </td>
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
