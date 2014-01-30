<%--
    Document   : Institutes
    Created on : Nov 5, 2012, 1:37:31 PM
    Author     : vinay
--%>

<%@page import="java.io.Serializable"%>
<%@page import="java.util.Date"%>
<%@page import="org.apache.log4j.Logger"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<%@taglib prefix="sjr" uri="/struts-jquery-richtext-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title>Institutes</title>
        <link href="<s:url value="/css/master.css"/>" rel="stylesheet" type="text/css" />
        <link href="<s:url value="/css/collapse.css"/>" rel="stylesheet" type="text/css" />
        <link href="<s:url value="/css/skin.css"/>" rel="stylesheet" type="text/css" />
        <sj:head/>
        <script type="text/javascript" src="<s:url value="/js/expand.js"/>"></script>
        <script type="text/javascript" src="<s:url value="/js/global.js"/>"></script>
        <script>
            $(function() {
                $("#accordion").accordion();
            });

            $(document).ready(function() {
                $(".add_Institute a").click(function() {
                    $("#add_Institute_form").toggle();
                });
            });
        </script>
    </head>
    <body>
        <%
            if (session.getAttribute("user_id") == null) {
                response.sendRedirect("../Login.jsp");

            }
            final Logger logger = Logger.getLogger(this.getClass());
            String ipAddress = request.getRemoteAddr();
            logger.warn(session.getAttribute("user_id") + " Accessed from: " + ipAddress + " at: " + new Date());
            String role = session.getAttribute("role").toString();
        %>

        <div class="w100 fl-l">
            <div class="w990p mar0a">
                <!--Header Starts Here-->
                <s:include value="/Header.jsp"/>
                <!--Header Ends Here-->
                <!--Middle Section Starts Here-->
                <div class="w100 fl-l">
                    <div class="middle_bg">
                        <!--Left box Starts Here-->
                        <s:include  value="/Left-Nevigation.jsp"/>
                        <!--Left box Ends Here-->
                        <!--Right box Starts Here-->
                        <div class="right_box">
                            <div class="my_account_bg">Institutes</div>
                            <div class="w100 fl-l mart10">
                                <div class="w98 mar0a">
                                    <div class="bradcum">
                                        <a href="<s:url value="/Welcome-Index.jsp"/>">Home</a>&nbsp;>&nbsp;Registered Institutes
                                    </div>
                                    <% if (role.contains("admin")) {%>
                                    <div class="add_Institute fl-r mart10">
                                        <a href="#" onclick="show_from()">Register New Institute</a>
                                    </div>
                                    <% }%>
                                    <div class="w100 fl-l tc fbld fcred">
                                        <s:property value="msg"/>
                                    </div>
                                    <div id="add_Institute_form" style="display:none;" class="fl-l w100">
                                        <fieldset class="w600p mar0a mart30">
                                            <legend class="fbld">Add Institute</legend>
                                            <s:form method="post" action="AddInst" id="InstiRegId" theme="simple" namespace="/Administrator" enctype="multipart/form-data">
                                                <table width="100%" class="mar0a" cellpadding="4" border="0" cellspacing="0">
                                                    <tr><th align="left">Institute Name</th>
                                                        <td><s:textfield name="instituteName" cssClass="w300p"/></td></tr>
                                                    <tr><th align="left">Short Name</th>
                                                        <td><s:textfield name="shortName" cssClass="w300p"/></td></tr>
                                                        <s:url id="CountId" action="CountryAction" namespace="/Dropdown"/>
                                                        <s:url id="CityId" action="CityAction" namespace="/Dropdown"/>
                                                    <tr><th align="left">Country</th>
                                                        <td><sj:select
                                                                href="%{CountId}"
                                                                id="CountryCode"
                                                                onChangeTopics="reloadcitylist"
                                                                name="CountryCode"
                                                                list="countryMap"
                                                                emptyOption="false"
                                                                headerKey="-1"
                                                                headerValue="Please Select Country"
                                                                label="Country"
                                                                sortable="true"
                                                                required="true"
                                                                />
                                                        </td>
                                                    </tr>
                                                    <tr><th align="left">City/Place</th><td>
                                                            <sj:select
                                                                href="%{CityId}"
                                                                id="CityName"
                                                                formIds="InstiRegId"
                                                                reloadTopics="reloadcitylist"
                                                                name="city"
                                                                list="cityMap"
                                                                emptyOption="false"
                                                                headerKey="-1"
                                                                headerValue="Please Select City"
                                                                label="City"
                                                                sortable="true"
                                                                required="true"
                                                                />
                                                        </td>
                                                    </tr>
                                                    <tr><th align="left" class="w">Address 1</th><td><s:textarea name="address1" cssClass="w300p"/></td></tr>
                                                    <tr><th align="left">Address 2</th><td><s:textarea name="address2" cssClass="w300p"/></td></tr>
                                                    <tr><th align="left">Pin/Zip Code</th><td><s:textfield name="pinzip"/></td></tr>
                                                    <tr><th align="left">Phone No.</th><td><s:textfield name="phone1"/></td></tr>
                                                    <tr><th align="left">Email Id</th><td><s:textfield name="emailId"/></td></tr>
                                                    <tr><th align="left">Website URL</th><td><s:textfield name="website" cssClass="w300p"/></td></tr>
                                                    <tr><th align="left" valign="top">About us</th><td>
                                                            <sjr:tinymce
                                                                id="richtextTinymceAdvancedEditor"
                                                                name="aboutus"
                                                                rows="10"
                                                                cols="10"
                                                                value="%{aboutus}"
                                                                editorLocal="en"
                                                                editorTheme="advanced"
                                                                editorSkin="o2k7"
                                                                toolbarAlign="left"
                                                                toolbarLocation="top"
                                                                toolbarButtonsRow1="bold,italic,underline,strikethrough,|,justifyleft,justifycenter,justifyright,justifyfull,|,link,unlink,anchor,image,|,formatselect,|,sub,sup"
                                                                toolbarButtonsRow2="bullist,numlist,|,outdent,indent,blockquote,|,undo,redo,|,insertdate,inserttime,preview,|,forecolor,backcolor,|,fontselect,fontsizeselect"
                                                                toolbarButtonsRow3=" "
                                                                />
                                                        </td></tr>
                                           <!--     <tr><th align="left">Logo</th><td><s:file name="logo"/></td></tr> -->
                                                    <tr><th colspan="2" align="center">
                                                            <s:submit value="Save"/>&nbsp;&nbsp;
                                                            <s:reset value="Reset"/>&nbsp;&nbsp;
                                                            <s:reset value="Back" onClick="history.go(-1);" />
                                                        </th>
                                                    </tr>
                                                </table>
                                            </s:form>
                                        </fieldset>
                                    </div>
                                    <div class="fl-r mart10"><a onclick="history.go(-1);"><img src="<s:url value="/icons/back-arrow.png"/>" class="w25p" /></a></div>

                                    <div class="w100 fl-l ">
                                        <table class="tablepaging" id="tablepaging" width="100%" cellspacing="0" cellpadding="5" border="1">
                                            <tr>
                                                <th>S.No.</th>
                                                <th>Institute Name</th>
                                                <th>Short Name</th>
                                                <th>Location</th>
                                                <th>Contact</th>
                                            </tr>
                                            <s:iterator value="InsList" status="stat">
                                                <tr>
                                                    <td><s:property value="#stat.count"/></td>
                                                    <td><s:property value="instituteName"/></td>
                                                    <td><s:property value="shortName"/></td>
                                                    <td><s:property value="cityPlace"/>,&nbsp;<s:property value="country"/></td>
                                                    <td>
                                                        <span class="fbld">Phone No:</span> <s:property value="phone1"/>,&nbsp;<s:property value="phone2"/><br/>
                                                        <span class="fbld">Email Id:</span> <s:property value="emailId"/><br/>
                                                        <span class="fbld">URL:</span> <s:property value="website"/>
                                                    </td>
                                                </tr>
                                            </s:iterator>
                                        </table>
                                    </div>
                                </div>
                            </div>
                            <!--Right box Starts Here-->
                        </div>
                    </div>
                    <!--Middle Section Ends Here-->
                </div>
            </div>
        </div>
        <s:include value="/Footer.jsp"/>
    </body>
</html>
