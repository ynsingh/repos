<%--
    Document   : TestScoreInfo
    Created on : Oct 12, 2011, 12:42:35 PM
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
<%@taglib prefix="sjr" uri="/struts-jquery-richtext-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title>Test Score</title>
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
                $(".add_test a").click(function() {
                    $("#add_test_form").toggle();
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
                            <div class="my_account_bg">Test Score</div>
                            <div class="v_gallery">
                                <div class="bradcum"> <a href="<s:url value="/Welcome-Index.jsp"/>">Home</a>&nbsp;>&nbsp;<a href="<s:url value="/MyPortfolio.jsp"/>">My Portfolio</a> > <a href="<s:url value="/MyProfile/MyProfile.jsp"/>">My Profile</a> > Test Score </div>
                                <div class="w100 fl-l mart10">
                                    <div class="tab_btn_2"><a onclick="history.go(-1);"><img src="<s:url value="/icons/back-arrow.png"/>" class="w25p" /></a></div>
                                    <div class="add_test fl-r"><a href="#" onclick="show_from()"><img src=" <s:url value="/icons/add.gif"/>" title="Add Test Score"/> </a> </div>
                                </div>
                                <div class="w100 fl-l">
                                    <div id="add_test_form" style="display:none;" class="fl-l w100">
                                        <fieldset class="w600p mar0a">
                                            <legend class="fbld">Add Test Score</legend>
                                            <s:form action="AddTestInfo" method="post" namespace="/MyProfile" name="myform">
                                                <table width="80%" class="mar0a" cellpadding="4" border="0" cellspacing="0">
                                                    <s:textfield name="tname" label="Test Name"/>
                                                    <s:textfield name="score" label="Score"/>
                                                    <sj:datepicker readonly="true"  id="date0" label="Date" maxDate="-1d" name="tdate" changeMonth="true" changeYear="true"/>
                                                    <sjr:tinymce
                                                        id="richtextTinymceAdvancedEditor"
                                                        name="tdescription"
                                                        label="Description"
                                                        rows="10"
                                                        cols="10"
                                                        value="%{tdescription}"
                                                        editorLocal="en"
                                                        editorTheme="advanced"
                                                        editorSkin="o2k7"
                                                        toolbarAlign="left"
                                                        toolbarLocation="top"
                                                        toolbarButtonsRow1="bold,italic,underline,strikethrough,|,justifyleft,justifycenter,justifyright,justifyfull,|,link,unlink,anchor,image,|,formatselect,|,sub,sup"
                                                        toolbarButtonsRow2="bullist,numlist,|,outdent,indent,blockquote,|,undo,redo,|,insertdate,inserttime,preview,|,forecolor,backcolor,|,fontselect,fontsizeselect"
                                                        toolbarButtonsRow3=" "
                                                        />
                                                    <tr>
                                                        <td>&nbsp;</td>
                                                        <td><s:submit value="Save" theme="simple" />
                                                            <s:reset value="Cancel" theme="simple" onClick="history.go(-1);" /></td>
                                                    </tr>
                                                </table>
                                            </s:form>
                                        </fieldset>
                                    </div>
                                    <div class="w100 fl-l mart10">
                                        <div class="w100 fl-l tc fbld fcgreen">
                                            <s:property value="msg"/>
                                        </div>
                                        <table class="tablepaging" id="tablepaging" width="100%" cellspacing="0" cellpadding="5" border="1">
                                            <tr>
                                                <th>S. No</th>
                                                <th width="150">Name of Test</th>
                                                <th>Score</th>
                                                <th>Date</th>
                                                <th  width="200">Description</th>
                                                <th width="65">Edit</th>
                                                <th width="65">Delete</th>
                                            </tr>
                                            <s:iterator value="ScoreList" var="Score" status="stat">
                                                <tr>
                                                    <td align="center"><s:property value="%{#stat.count}"/></td>
                                                    <td><s:property value="tname"/>
                                                    </td>
                                                    <td align="center"><s:property value="score"/>
                                                    </td>
                                                    <td><s:property value="tdate"/>
                                                    </td>
                                                    <td><s:property value="tdescription" escape="false"/>
                                                    </td>
                                                    <td valign="middle" style="vertical-align:middle;" align="center"><a href="editTest?testId=<s:property value="testId"/>"> <img src="<s:url value="/icons/edit.gif"/>" title="Edit Record"/> </a> </td>


                                                    <td align="center"><a href="deleteTest?testId=<s:property value="testId"/>" onclick="return confirm('Are you sure you want to delete this record ?')"> <img src="<s:url value="/icons/delete.gif"/>" title="Delete Record"/> </a> </td>

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
