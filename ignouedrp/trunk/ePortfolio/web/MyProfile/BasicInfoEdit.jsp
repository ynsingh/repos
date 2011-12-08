<%-- 
    Document   : BasicInfoEdit
    Created on : Sep 15, 2011, 4:40:19 PM
Author     : Vinay
Version      : 1
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title>Home</title>
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
        <script type="text/javascript" src="../JS/jquery-ui.min.js"></script>
        <link rel="stylesheet" type="text/css" media="screen" href="../JS/jquery-ui.css"/>

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
    <body><%        
           if (session.getAttribute("user_id") == null) {
                pageContext.forward("../login.jsp");
            }
                   
        %>
        <jsp:include page="../Header.jsp"/>

        <div id="container">
            <div class="wrapper">
                <jsp:include page="../Left-Nevigation.jsp"/>
                <div id="col2">
                    <h3>Basic Information</h3>

                    <br/>
                    <s:form action="updateBasic" method="post">
                        <table align="center" width="75%" cellpadding="4" border="0" cellspacing="0">


                            <s:iterator value="basicListList" var="ProfileBasic">
                                <tr><td><s:hidden name="basicInfoId"/>
                                        <s:label name="userId" label="User ID"/>

                                    </td></tr>
                                <tr><td>
                                        <s:textfield name="fname" label="First Name"/>
                                    </td></tr>
                                <tr><td >
                                        <s:textfield name="mname" label="Middle Name"/>
                                    </td></tr>
                                <tr><td >
                                        <s:textfield name="lname" label="Last Name"/>
                                    </td></tr>
                                <tr><td >
                                        <s:textfield name="gender" label="Gender"/>

                                    </td></tr>
                                <tr><td >
                                        <s:textfield cssClass="width250 date-picker" name="dateOfBirth" label="Date of Birth"/>
                                    </td></tr>
                                <tr><td >
                                        <s:textfield name="pbirth" label="Place of Birth">
                                        </s:textfield>
                                    </td></tr>        
                                <tr><td >
                                        <s:textfield name="mstatus" label="Marital Status">
                                        </s:textfield>
                                    </td></tr>
                                <tr><td >
                                        <s:textarea name="aboutMe" label="About Me" cols="30" rows="5">
                                        </s:textarea>
                                    </td></tr>                                
                                </s:iterator>
                        </table>
                        <br/>
                        <s:submit cssClass="floatL buttonsMiddle" value="Save" />
                        <s:reset cssClass="floatL" value="Cancel" onClick="history.go(-1);" />

                    </s:form>

                    <br/><br/>




                </div>
                <jsp:include page="../Right-Nevigation.jsp"/>
                <div class="clear"></div>
            </div>
        </div>
                <jsp:include page="../Footer.jsp"/>
    </body>
</html>
