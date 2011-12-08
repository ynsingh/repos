<%-- 
    Document   : ProjectInfoEdit
    Created on : Oct 19, 2011, 3:26:21 PM
Author     : Vinay
Version      : 1
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title>My Project</title>
        <script type="text/javascript" src="<s:url value="/JS/jquery-latest.js"/>"></script>
        <script type="text/javascript">
            $(document).ready(function(){
                $("#accordion > li > div").click(function(){
 
                    if(false == $(this).next().is(':visible')) {
                        $('#accordion ul').slideUp(300);
                    }
                    $(this).next().slideToggle(300);
                });
 
                $('#accordion ul:eq(0)').show();

            });
        </script>
        <script type="text/javascript">
            $(document).ready(function() {
                $('fieldset.jcalendar').jcalendar();
            });
        </script>
        <script src="<s:url value="/JS/jquery-1.6.4.min.js"/>" type="text/javascript"></script>
        <script src="<s:url value="/JS/jcalendar-source.js"/>" type="text/javascript"></script>
        <link href="<s:url value="/JS/jcalendar.css"/>" rel="stylesheet" type="text/css" />
        <link href="<s:url value="/theme1/style.css"/>" rel="stylesheet" type="text/css" />

        <%
            if (session.getAttribute("user_id") == null) {
                pageContext.forward("../login.jsp");
            }

        %>

        <script type="text/javascript" src="<s:url value="/JS/jquery-ui.min.js"/>"></script>
        <link rel="stylesheet" type="text/css" media="screen" href="<s:url value="/JS/jquery-ui.css"/>"/>
        <style type="text/css">
            .ui-datepicker-calendar {
                display: none;
            }
        </style>
        <script type="text/javascript">
            $(function() {
                $('.date-picker').datepicker( {
                    changeMonth: true,
                    changeYear: true,
                    yearRange: '1950:2011',
                    showButtonPanel: true,
                    dateFormat: 'MM yy',
                    onClose: function(dateText, inst) { 
                        var month = $("#ui-datepicker-div .ui-datepicker-month :selected").val();
                        var year = $("#ui-datepicker-div .ui-datepicker-year :selected").val();
                        $(this).datepicker('setDate', new Date(year, month, 1));
                    }
                });
            });
        </script>
    </head>
    <body>

        <s:include value="../Header.jsp"/>
        <div id="container">
            <div class="wrapper">
                <s:include value="/Left-Nevigation.jsp"/>
                <div id="col2">
                    <h3>Edit Project</h3>
                    <br/>
                    <br/>
                    <s:form action="UpdateProjInfo" method="post" namespace="/MyProfile" name="AcademicForm" onsubmit="return validatePlanForm()">
                        <table align="center" width="80%" cellpadding="4" border="0" cellspacing="0">
                            <s:iterator value="ProList" var="pro">
                                <s:hidden name="projectId"/><s:hidden name="userId"/>
                                <s:textfield cssClass="width250" name="proName" label="Project Name"/>
                                <s:textfield cssClass="width250" name="teamSize" label="Team Size"/>
                                <s:textfield cssClass="width250" name="role" label="Role"/>
                                <s:textfield cssClass="width250" name="agency" label="Funding Agency (If Any)"/>
                                <s:textfield cssClass="width250" name="budget" label="Budget (If Any)"/>
                                <s:textfield cssClass="width250" name="proUrl" label="Project URL"/>
                                <s:textfield cssClass="width250 date-picker" name="startDate" label="Duration (From)"/>
                                <s:textfield cssClass="width250 date-picker" name="endDate" label="Upto"/>
                                <s:textarea cssClass="width250" name="description" label="Discription"/>
                            </s:iterator>
                        </table>
                        <br/>
                        <s:submit cssClass="floatL buttonsMiddle" value="Save" />
                        <s:reset cssClass="floatL" value="Cancel" onClick="history.go(-1);" />
                    </s:form>
                </div>
                <s:include value="/Right-Nevigation.jsp"/>
                <div class="clear"></div>
            </div>
        </div>
        <s:include value="/Footer.jsp"/>
    </body>
</html>
