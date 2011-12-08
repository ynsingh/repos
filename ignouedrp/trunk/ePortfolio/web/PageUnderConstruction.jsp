<%-- 
    Document   : PageUnderConstruction
    Created on : Oct 14, 2011, 10:28:48 AM
Author     : Vinay
Version      : 1
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title>Page Under Construction</title>
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
        <link href="theme1/style.css" rel="stylesheet" type="text/css" />
    </head>
    <body>
        <%        
           if (session.getAttribute("user_id") == null) {
                pageContext.forward("login.jsp");
            }
                   
        %>
        <jsp:include page="Header.jsp"/>

        <div id="container">
            <div class="wrapper">
                <jsp:include page="Left-Nevigation.jsp"/>                
                <div id="col2">
                     <s:url id="RefID" action="ShowReference" namespace="/MyProfile"/>
                    <h3>Page Under Construction</h3>
                    <div class="ui-icon-power"><img align="middle" src="<s:url value="/icons/website_under_construction.jpg"/>"/>
                    </div>
                </div>
                <jsp:include page="Right-Nevigation.jsp"/>
                <div class="clear"></div>
            </div>
        </div>
        <jsp:include page="Footer.jsp"/>  
    </body>
</html>