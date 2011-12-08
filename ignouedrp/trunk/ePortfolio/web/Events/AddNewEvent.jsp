<%-- 
    Document   : AddNewEvent
    Created on : Nov 3, 2011, 12:03:49 PM 
Author     : Amit
Version    : 1
--%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title>Add Event Home</title>
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
       <script type="text/javascript">
            $(document).ready(function() {
            $('fieldset.jcalendar').jcalendar();
            });
        </script>
        <script type="text/javascript">
            $(function() {
                $('.date-picker').datepicker( {
                    changeMonth: true,
                    changeYear: true,
                    yearRange: '1950:2011',
                    showButtonPanel: true,
                    dateFormat: 'yy-MM-dd',
                    onClose: function(dateText, inst) { 
                        var month = $("#ui-datepicker-div .ui-datepicker-month :selected").val();
                        var year = $("#ui-datepicker-div .ui-datepicker-year :selected").val();
                        $(this).datepicker('setDate', new Date(year, month, day));
                    }
                });
            });
        </script>
    </head>
    <s:include value="/HeaderAdmin.jsp"/>
        <div id="container">
            <div class="wrapper">
                <s:include value="/Left-NavigationAdmin.jsp"/>
                <div id="col2">
                    <h3>Add Event</h3>
                    <br/>
                    <br/>
                    <s:form action="AddEvent" method="post" namespace="/Events" name="EventForm" onsubmit="return validateEventForm()">
                        <table align="center" width="85%" cellpadding="4" border="0" cellspacing="0">
                            <s:textfield cssClass="width250" name="" label="Event Title"/>
                             <s:textfield cssClass="width250 date-picker" name="EventStartDate" label="Start Date"/>
                             <s:textfield cssClass="width250 date-picker" name="EventEndDate" label="End Date"/>
                            <s:textfield cssClass="width250" name="" label="Venue name"/>
                            <s:textfield cssClass="width250" name="" label="Address"/>
                            <s:textfield cssClass="width250" name="" label="City"/>
                            <s:textfield cssClass="width250" name="" label="State/Region"/>
                            <s:textfield cssClass="width250" name="" label="Country"/>
                            <s:textfield cssClass="width250" name="" label="Postal Code"/>
                            <s:textfield cssClass="width250" name="" label="Website" value="http://"/>
                            <s:textarea cssClass="width250" name="" label="Description"/>
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
