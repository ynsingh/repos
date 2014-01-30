<%--
    Document   : EmployeeInfo
    Created on : Sep 13, 2011, 11:52:03 AM
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
        <title>Employment Information</title>
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
                            <div class="my_account_bg">Employment Information</div>
                            <div class="v_gallery">
                                <div class="bradcum"> <a href="<s:url value="/Welcome-Index.jsp"/>">Home</a>&nbsp;>&nbsp;<a href="<s:url value="/MyPortfolio.jsp"/>">My Portfolio</a>&nbsp;>&nbsp;<a href="<s:url value="/MyProfile/MyProfile.jsp"/>">My Profile</a> > Employment </div>
                                <div class="w100 fl-l">
                                    <div class="tab_btn_2"><a onclick="history.go(-1);"><img src="<s:url value="/icons/back-arrow.png"/>" class="w25p" /></a></div>
                                    <div class="add_course fl-r mart10 wau">
                                        <a href="#" onclick="show_from()">
                                            <img src="<s:url value="/icons/add.gif" />" title="Add Employment"/>
                                        </a>
                                    </div>
                                </div>
                                <div class="w100 fl-l">
                                    <div class="w100 fl-l tc fbld fcgreen">
                                        <s:property value="msg"/>
                                    </div>
                                    <div id="add_course_form" style="display:none;" class="fl-l w100">
                                        <s:form action="AddEmploment" method="post" namespace="/MyProfile" name="myform" theme="simple">
                                            <fieldset class="w450p mar0a">
                                                <legend class="fbld">Add Employment Information</legend>
                                                <table width="100%" class="mar0a" cellpadding="4" border="0" cellspacing="0">
                                                    <tr>
                                                        <td width="20%">Job Title</td>
                                                        <td colspan="3"><s:textfield name="jtitle" cssClass="w100"/></td>
                                                    </tr>
                                                    <tr>
                                                        <td>Organization</td>
                                                        <td colspan="3"><s:textfield name="orgName" cssClass="w100"/></td>
                                                    </tr>
                                                    <tr>
                                                        <td>Address</td>
                                                        <td colspan="3"><s:textarea name="oaddress" cssClass="w100" /></td>
                                                    </tr>
                                                    <tr>
                                                        <td>City</td>
                                                        <td><s:textfield name="ocity" /></td>
                                                        <td>State</td>
                                                        <td><s:textfield name="ostate" /></td>
                                                    </tr>
                                                    <tr>
                                                        <td>Country</td>
                                                        <td><s:textfield name="ocountry" /></td>
                                                    </tr>
                                                    <tr>
                                                        <td>Joining Date</td>
                                                        <td><sj:datepicker cssClass="w50" readonly="true"  id="date0" name="jdate" changeMonth="true" changeYear="true"/></td>
                                                        <td width="20%">Leaving Date</td>
                                                        <td><sj:datepicker cssClass="w50" readonly="true"  id="date1" maxDate="-1d" name="ldate" changeMonth="true" changeYear="true"/></td>
                                                    </tr>
                                                    <tr>
                                                        <td>Description</td>
                                                        <td colspan="3"><s:textarea cssClass="w100" name="description"  cols="30" rows="5"/></td>
                                                    </tr>
                                                    <tr>
                                                        <td>&nbsp;</td>
                                                        <td colspan="2"><s:submit cssClass="fl-l" value="Save" />
                                                            <s:reset cssClass="fl-l" value="Cancel" onClick="history.go(-1);" />
                                                        </td><td>&nbsp;</td>
                                                    </tr>
                                                </table>
                                            </fieldset>
                                        </s:form>
                                    </div>
                                    <div class="w100 fl-l mart10">
                                        <table class="tablepaging" id="tablepaging" width="100%" cellspacing="0" cellpadding="5" border="1">
                                            <tr>
                                                <th width="81">S. No</th>
                                                <th width="79">Job Title</th>
                                                <th width="134">Organization Name</th>
                                                <th width="216">Address</th>
                                                <th width="86">Joining Date</th>
                                                <th width="156">Relieved On</th>
                                                <th>Role/Description</th>
                                                <th width="53">Edit</th>
                                                <th width="76">Delete</th>
                                            </tr>
                                            <s:iterator value="employmentListList" var="ProfileEmployment" status="groupStatus">
                                                <tr>
                                                    <td align="center" valign="top"><s:property value="%{#groupStatus.count}"/></td>
                                                    <td valign="top"><s:property value="jtitle"/></td>
                                                    <td valign="top"><s:property value="orgName"/></td>
                                                    <td valign="top"><s:property value="oaddress"/>
                                                        <s:property value="ocity"/>
                                                        <s:property value="ostate"/>
                                                        <s:property value="ocountry"/>
                                                    </td>
                                                    <td valign="top"><s:property value="jdate"/></td>
                                                    <td valign="top"><s:property value="ldate"/>
                                                        &nbsp;</td>
                                                    <td valign="top" width="223"><s:property value="description"/></td>
                                                    <td align="center" valign="top"><a href="EditEmpInfo?employmentInfoId=<s:property value="employmentInfoId"/>"> <img src="<s:url value="/icons/edit.gif"/>" title="Edit Record"/> </a> </td>
                                                    <td align="center" valign="top"><a href="DeleteEmpInfo?employmentInfoId=<s:property value="employmentInfoId"/>" onclick="return confirm('Are you sure you want to delete this record ?')"> <img src="<s:url value="/icons/delete.gif"/>" title="Delete Record"/> </a> </td>
                                                </tr>
                                            </s:iterator>
                                        </table>
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
