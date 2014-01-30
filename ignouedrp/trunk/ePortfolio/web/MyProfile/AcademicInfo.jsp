<%--
Document : EducationInfo
Created on : Sep 1, 2011, 5:58:53 PM
Author : IGNOU Team
Version : 1
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
        <title>Academic Information</title>
        <link href="<s:url value="/css/master.css"/>" rel="stylesheet" type="text/css" />
        <link href="<s:url value="/css/collapse.css"/>" rel="stylesheet" type="text/css" />
        <link href="<s:url value="/css/skin.css"/>" rel="stylesheet" type="text/css" />
        <sj:head/>
        <script type="text/javascript" src="<s:url value="/js/expand.js"/>"></script>
        <script type="text/javascript" src="<s:url value="/js/global.js"/>"></script>
        <script type="text/javascript">
            window.onload = setallonload;
        </script>
        <script>
            $(function() {
                $("#accordion").accordion();
            });
            $(document).ready(function() {
                $(".add_qualification a#add").click(function() {
                    $("#add_qualification_form").toggle();
                });
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
                            <div class="my_account_bg">Academic Information</div>
                            <div class="v_gallery">
                                <div class="bradcum"> <a href="<s:url value="/Welcome-Index.jsp"/>">Home</a>&nbsp;>&nbsp;<a href="<s:url value="/MyPortfolio.jsp"/>">My Portfolio</a> > <a href="<s:url value="/MyProfile/MyProfile.jsp"/>">My Profile</a> > Academic Information </div>
                                <div class="w100 fl-l">
                                    <div class="tab_btn_2"><a onclick="history.go(-1);"><img src="<s:url value="/icons/back-arrow.png"/>" class="w25p" /></a></div>
                                    <div class="wau fl-r mart10">

                                        <div class="add_qualification">
                                            <a href="editAcademic">
                                                <img class="marr5" src="<s:url value="/icons/edit.gif"/>" title="Edit Record"/>
                                            </a>
                                            <a href="#" id="add" onclick="show_from()">
                                                <img src=" <s:url value="/icons/add.gif"/>" title="Add Qualification"/>
                                            </a>
                                        </div>
                                    </div>
                                </div>
                                <div id="add_qualification_form" style="display:none;" class="fl-l w100 mart20">
                                    <ul style="display:none;" id="tablerootvalue" class="formfield">
                                        <li class="field1">
                                            <input type="text" name="degree" value=""/>
                                        </li>
                                        <li class="field2">
                                            <input type="text" name="fstudy" value=""/>
                                        </li>
                                        <li class="field3">
                                            <input type="text" name="university" value=""/>
                                        </li>
                                        <li class="field4">
                                            <select class="year" name="pyear">
                                            </select>
                                        </li>
                                        <li class="field5">
                                            <input type="text" name="percent" value=""/>
                                        </li>
                                        <li class="field6">
                                            <input type="text" name="location" value=""/>
                                        </li>
                                        <li class="field7 last">
                                            <input type="text" name="division" value=""/>
                                        </li>
                                    </ul>
                                    <s:form action="AddAcademicInfo" theme="simple" method="post" namespace="/MyProfile" validate="true">
                                        <div class="w100 fl-l">
                                            <ul class="heading">
                                                <li class="field1">Degree/Programme</li>
                                                <li class="field2">Specialization</li>
                                                <li class="field3">Board/University/Institute</li>
                                                <li class="field4">Passing Year</li>
                                                <li class="field5">Percentage</li>
                                                <li class="field6">Grade</li>
                                                <li class="field7 last">Division</li>
                                            </ul>
                                            <span id="qualificationtable"></span> </div>
                                        <div class="w100 fl-l tr"><a href="javascript:addmorefield();">Add More</a></div>
                                        <div class="w100 fl-l">
                                            <table width="30%" class="mar0a" cellpadding="0" cellspacing="0">
                                                <tr>
                                                    <td>&nbsp;</td>
                                                    <td><s:submit value="Save" theme="simple" />
                                                        <s:reset value="Reset" theme="simple"/>
                                                    </td>
                                                </tr>
                                            </table>
                                        </div>
                                    </s:form>
                                </div>
                                <div class="w100 fl-l">
                                    <div class="w100 fl-l tc fbld fcgreen"><s:property value="msg"/></div>
                                    <div class="w100 fl-l mart10">
                                        <table class="tablepaging" id="tablepaging" width="100%" cellspacing="0" cellpadding="5" border="1">
                                            <tr>
                                                <th>S.No</th>
                                                <th>Degree/Programme</th>
                                                <th>Specialization</th>
                                                <th>Board/University/Institute</th>
                                                <th>Passing Year</th>
                                                <th>Percentage</th>
                                                <th>Grade</th>
                                                <th>Division</th>
                                                <th>Delete</th>
                                            </tr>
                                            <s:iterator value="academicListList" var="ProfileAcademic" status="groupStatus">
                                                <tr>
                                                    <td align="center" valign="top"><s:property value="%{#groupStatus.count}"/></td>
                                                    <td valign="top"><s:property value="degree"/></td>
                                                    <td valign="top"><s:property value="fstudy"/></td>
                                                    <td valign="top"><s:property value="university"/></td>
                                                    <td align="center" valign="top"><s:property value="pyear"/></td>
                                                    <td align="center" valign="top"><s:property value="percent"/></td>
                                                    <td valign="top"><s:property value="location"/></td>
                                                    <td align="center" valign="top"><s:property value="division"/></td>
                                                    <td align="center" valign="top"><a href="deleteAcademic?academicInfoId=<s:property value="academicInfoId"/>" onclick="return confirm('Are you sure you want to delete this record ?')"> <img src="<s:url value="/icons/delete.gif"/>" title="Delete Record"/> </a></td>
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
