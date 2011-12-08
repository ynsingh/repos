<%-- 
    Document   : ProfAffiliationInfoEdit
    Created on : Oct 17, 2011, 3:14:28 PM
Author     : Vinay
Version      : 1
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title>My Professional Affiliation</title>
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

        <style>
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
        </script></head>
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
                    <h3>Edit Professional Affiliation</h3>
                    <br/><br/><br/>
                
                    <s:form action="UpdateAffiliation" method="post" namespace="/MyProfile">

                        <table align="center" width="75%" cellpadding="4" border="0" cellspacing="0">   

                            <s:iterator value="AffiliationList" var="ProfAffili">
                                <s:hidden name="proAffiliationId"/><s:hidden name="userId"/>
                                <s:textfield cssClass="width250" name="role" label="Role"/>
                                <s:textfield cssClass="width250" name="orgBody" label="Organization/Body"/>
                                <s:textfield cssClass="width250 date-picker" name="vfrom" label="Working From"/>
                                <s:textfield cssClass="width250 date-picker" name="vupto" label="Work Upto"/>
                                <s:textfield cssClass="width250" name="place" label="Place"/>
                                <s:textfield cssClass="width250" name="country" label="Country"/>
                                <s:textarea cssClass="width250" name="summary" label="Summary"/>
                            </s:iterator>
                        </table>
                        <br/>
                        <s:submit cssClass="floatL buttonsMiddle" value="Save" />
                        <s:reset cssClass="floatL" value="Cancel" onClick="history.go(-1);" />

                    </s:form>
                    <br/><br/><br/><br/>
                </div>
                <jsp:include page="../Right-Nevigation.jsp"/>
                <div class="clear"></div>
            </div>
        </div>
        <jsp:include page="../Footer.jsp"/>
    </body>
</html>