<%-- 
    Document   : GradingSetupEdit
    Created on : Jun 8, 2012, 2:35:00 PM
    Author     : IGNOU Team
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title>Edit Grade Setup</title>
        <link href="<s:url value="/css/master.css"/>" rel="stylesheet" type="text/css" />
        <link href="<s:url value="/css/collapse.css"/>" rel="stylesheet" type="text/css" />
        <link href="<s:url value="/css/skin.css"/>" rel="stylesheet" type="text/css" />
        <sj:head/>
        <script type="text/javascript" src="<s:url value="/js/expand.js"/>"></script>
        <script>
            $(function() {
                $( "#accordion" ).accordion();
            });
        </script>
    </head>
    <body>
        <%
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
                            <div class="my_account_bg">Edit Grade Setup</div>
                            <div class="v_gallery">
                                <div class="w100 fl-l mart10">
                                    <div class="bradcum">
                                        <a href="<s:url value="/Welcome-Index.jsp"/>">Home</a>&nbsp;>&nbsp;<a href="<s:url value="/MyEdudation-Workspace.jsp"/>">My Education and Work</a>&nbsp;> <a href="FacultyTaskShow">Task / Activities</a> > <a href="GetGradeSetupList">Existing Grade Setup</a> > Edit Grade Setup
                                    </div>
                                    <div class="w100 fl-l mart20">
                                        <fieldset class="w500p mar0a">
                                            <legend class="fbld">Edit Grade Setup</legend>
                                            <s:url id="gradeId" action="GradeMasterAct" namespace="/Evidence"/> 
                                            <s:form action="EditsetVal" method="post" theme="simple" namespace="/Evidence">
                                                <table class="tablepaging" id="tablepaging" width="100%" cellspacing="0" cellpadding="5" border="1">
                                                    <s:hidden name="gradeValId"/>
                                                    <tr>
                                                        <td class="fbld" width="180px;">Course</td>
                                                        <td><s:property value="Course"/><s:hidden name="Course"/></td>
                                                    </tr>
                                                    <tr>
                                                        <td class="fbld" width="180px;">Previous Grade Type</td>
                                                        <td><s:property value="GradeType"/><s:hidden name="GradeType"/></td>
                                                    </tr>
                                                    <tr>
                                                        <td class="fbld" width="180px;">New Grade Type <span class="fcred">*</span></td>
                                                        <td colspan="2">
                                                            <sj:select 
                                                                href="%{gradeId}" 
                                                                id="grade" 
                                                                formIds="FormId"
                                                                reloadTopics="reloadGradelist"
                                                                name="gtId" 
                                                                list="gradeL"
                                                                emptyOption="false" 
                                                                headerKey="-1" 
                                                                headerValue="Please Select Grade Type"
                                                                label="Select Grade Type"
                                                                sortable="false"
                                                                />
                                                        </td>
                                                    </tr>
                                                    <tr><td colspan="3" align="center">
                                                            <s:submit value="Edit"/>&nbsp;
                                                            <s:reset value="Back" onClick="history.go(-1);" />
                                                        </td></tr>
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