<%-- 
    Document   : Governance-Add
    Created on : Dec 19, 2011, 2:30:40 PM
    Author     : IGNOU Team
    Version    : 1
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<%@taglib prefix="sjr" uri="/struts-jquery-richtext-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title>Add Corporate Life</title>
        <link href="<s:url value="/css/master.css"/>" rel="stylesheet" type="text/css" />
        <link href="<s:url value="/css/collapse.css"/>" rel="stylesheet" type="text/css" />
        <link href="<s:url value="/css/skin.css"/>" rel="stylesheet" type="text/css" />
        <script type="text/javascript" src="<s:url value="/js/jquery-1.6.4.min.js"/>"></script>
        <sj:head/>
        <script type="text/javascript" src="<s:url value="/js/expand.js"/>"></script>
        <script>
            $(function() {
                $( "#accordion" ).accordion();
            });
        </script>
        <script type="text/javascript">
            if(window.history.forward(1) != null)
                window.history.forward(1);
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
                            <div class="my_account_bg">Add Corporate Life</div>
                            <div class="w100 fl-l mart10">
                                <div class="w98 mar0a">
                                    <div class="bradcum"> <a href="<s:url value="/Welcome-Index.jsp"/>">Home</a>&nbsp;>&nbsp;<a href="<s:url value="/MyEdudation-Workspace.jsp"/>">My Education and Work</a>&nbsp;> <a href="<s:url value="/MyWorkspace/MyWorkspace.jsp"/>">My Workspace</a> &nbsp;> <a href="<s:url value="/MyWorkspace/MyPublications.jsp"/>">My Publication</a> &nbsp;> <a href="ShowGovernance">Corporate Life</a> &nbsp;> Add Corporate Life </div>
                                    <div class="w100 fl-l mart10">
                                        <fieldset class="w500p mar0a">
                                            <legend class="fbld">Add Corporate Life</legend>
                                            <s:form action="AddGovernance" method="post" namespace="/MyWorkspace" name="" theme="simple">
                                                <table border="0" class="mar0a" cellpadding="0" cellspacing="0" width="90%">
                                                    <tr>
                                                        <td>Name of Committee/Panels</td>
                                                        <td><s:textfield name="nameCommittee"/></td>
                                                    </tr>
                                                    <tr>
                                                        <td>Name of University/Institute</td>
                                                        <td><s:textfield name="nameUniversity"/></td>
                                                    </tr>
                                                    <tr>
                                                        <td>Address</td>
                                                        <td><s:textarea name="address"/></td>
                                                    </tr>
                                                    <tr>
                                                        <td>Service Duration:</td>
                                                        <td>From&nbsp;
                                                            <sj:datepicker id="date0" name="durationFrom" cssClass="w70p" changeMonth="true" changeYear="true"/>
                                                            &nbsp;To&nbsp;
                                                            <sj:datepicker id="date1" name="durationTo" cssClass="w70p" changeMonth="true" changeYear="true"/>
                                                        </td>
                                                    </tr>
                                                    <tr>
                                                        <td>Responsibilities</td>
                                                        <td><s:textarea name="responsibility"/></td>
                                                    </tr>
                                                    <tr>
                                                        <td>URL, if any</td>
                                                        <td><s:textfield name="url"/></td>
                                                    </tr>
                                                    <tr>
                                                        <td colspan="2" align="center"><font color="#FF0000">www.example.com</font></td>
                                                    </tr>
                                                    <tr>
                                                        <td>Summary</td>
                                                        <td><sjr:tinymce
                                                                id="richtextTinymceAdvancedEditor"
                                                                name="summary"
                                                                rows="10"
                                                                cols="10"
                                                                value="%{summary}"
                                                                editorLocal="en"
                                                                editorTheme="advanced"
                                                                editorSkin="o2k7"
                                                                toolbarAlign="left"
                                                                toolbarLocation="top"
                                                                toolbarButtonsRow1="bold,italic,underline,strikethrough,|,justifyleft,justifycenter,justifyright,justifyfull,|,link,unlink,anchor,image,|,formatselect,|,sub,sup"
                                                                toolbarButtonsRow2="bullist,numlist,|,outdent,indent,blockquote,|,undo,redo,|,insertdate,inserttime,preview,|,forecolor,backcolor,|,fontselect,fontsizeselect"
                                                                toolbarButtonsRow3=" "
                                                                /></td>
                                                    </tr>
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
