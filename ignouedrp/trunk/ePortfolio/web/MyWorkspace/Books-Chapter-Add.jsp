<%--
    Document   : Books-Chapter-Add
    Created on : Dec 5, 2011, 9:06:38 AM
    Author     : IGNOU Team
    Version    : 1
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
        <title>Add Book / Chapter</title>
        <link href="<s:url value="/css/master.css"/>" rel="stylesheet" type="text/css" />
        <link href="<s:url value="/css/collapse.css"/>" rel="stylesheet" type="text/css" />
        <link href="<s:url value="/css/skin.css"/>" rel="stylesheet" type="text/css" />
        <sj:head/>
        <script type="text/javascript" src="<s:url value="/js/expand.js"/>"></script>
        <script>
            $(function() {
                $("#accordion").accordion();
            });
        </script>
        <script type="text/javascript">
            if (window.history.forward(1) != null)
                window.history.forward(1);
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
                            <div class="my_account_bg">Add Book / Chapter</div>
                            <div class="w100 fl-l mart10">
                                <div class="w98 mar0a">
                                    <div class="bradcum"> <a href="<s:url value="/Welcome-Index.jsp"/>">Home</a>&nbsp;>&nbsp;<a href="<s:url value="/MyEdudation-Workspace.jsp"/>">My Education and Work</a>&nbsp;> <a href="<s:url value="/MyWorkspace/MyWorkspace.jsp"/>">My Workspace</a> &nbsp;> <a href="<s:url value="/MyWorkspace/MyPublications.jsp"/>">My Publication</a> &nbsp;> <a href="ShowBookChapter">Books Chapter</a> &nbsp;> Add Books Chapter </div>
                                    <div class="w100 fl-l mart10">
                                        <fieldset class="w450p mar0a">
                                            <legend><strong>Add Book / Chapter</strong></legend>
                                            <s:form action="AddBookChapter" method="post" namespace="/MyWorkspace" name="">
                                                <table width="90%" border="0" class="mar0a" cellpadding="0" cellspacing="2">
                                                    <s:radio name="bcType" label="Book/Chapter" list="{'Book','Chapter'}"/>
                                                    <s:select cssClass="width255" name="role" label="Role"  headerKey="1" headerValue="-- Please Select Your Role--" list="{'Author','Co-Author','Editor','Reviewer','Others'}"/>
                                                    <s:textfield name="title" label="Title"/>
                                                    <s:select id="mymenu" label="No of Author" name="noCoauthor" nchange="updatefields()" list="{'1','2','3','4','5','6','7','8','9','10'}" headerKey="0" headerValue="Select No. of Author"/>
                                                    <script type="text/javascript">
                                                        var selectmenu = document.getElementById("mymenu")
                                                        var i;
                                                        var fieldcont = "";
                                                        selectmenu.onchange = function() { //run some code when "onchange" event fires
                                                            var chosenoption = this.options[this.selectedIndex] //this refers to "selectmenu"
                                                            for (i = 0; i < chosenoption.value; i++) {
                                                                fieldcont += '<table border="0"><tr><td valign="top" width="120">Author ' + (i + 1) + ' First Name </td><td><input type="text" name="fname[' + i + ']"/></td></tr>';
                                                                fieldcont += '<tr><td valign="top">Author ' + (i + 1) + ' Last Name</td><td><input type="text" name="lname[' + i + ']"/></td></tr><tr><td colspan="2">&nbsp;</td></tr></table>';
                                                            }
                                                            if (fieldcont) {
                                                                document.getElementById("formfields").innerHTML = fieldcont;
                                                                fieldcont = "";
                                                            }
                                                        }
                                                    </script>
                                                    <tr>
                                                        <td colspan="2"><s:div id="formfields"/></td>
                                                    </tr>
                                                    <s:textfield name="publisher" label="Publisher"/>
                                                    <s:textfield name="isbn" label="ISBN"/>
                                                    <sj:datepicker readonly="true"  id="date0" label="Published on" value="%{publishedOn}" name="publishedOn" changeMonth="true" changeYear="true"/>
                                                    <tr>
                                                        <td width="29%">Pages</td>
                                                        <td width="61%">From&nbsp;&nbsp;&nbsp;
                                                            <s:textfield name="PFrom" cssStyle="width:35px;" theme="simple"/>
                                                            &nbsp;&nbsp;&nbsp;&nbsp;To&nbsp;&nbsp;&nbsp;
                                                            <s:textfield name="PTo" cssStyle="width:35px;" theme="simple"/>
                                                        </td>
                                                    </tr>
                                                    <s:textfield name="language" label="Language"/>
                                                    <s:textfield name="affiliation" label="Affiliation"/>
                                                    <s:textfield name="url" label="URL, if any"/>
                                                    <s:textarea name="summary" label="Summary"/>
                                                    <tr>
                                                        <td>&nbsp;</td>
                                                        <td><s:submit theme="simple" value="Save" />
                                                            <s:reset theme="simple" value="Cancel" onClick="history.go(-1);" />
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
