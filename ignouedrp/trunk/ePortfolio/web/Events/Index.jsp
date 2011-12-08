<%-- 
    Document   : Index
    Created on : Oct 19, 2011, 11:49:42 AM
Author     : Vinay
Version      : 1
--%>

<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title>Events</title>
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

        <link rel="stylesheet" type="text/css" media="screen" href="http://ajax.googleapis.com/ajax/libs/jqueryui/1.7.2/themes/base/jquery-ui.css"/>

    </head>
    <body>
        <%        
           if (session.getAttribute("user_id") == null) {
                pageContext.forward("../login.jsp");
            }
                   
        %>
        <div id="header">
            <div class="wrapper">
                <div id="logo">
                    <a href="<s:url value="/Index.jsp"/>">
                        <img src="<s:url value="/theme1/images/logo.png"/>"/>
                    </a>
                </div>
                <div id="accountinfo">
                    <img src="<s:url value="/theme1/images/ignou-logo.jpg"/>"/>
                    <div id="profilelinks">
                    <br/>
                    </div></div>
                <div id="changecolors">
                    <a href="#">
                        <img src="<s:url value="/theme1/images/theme-red.gif"/>"  alt="Red"/></a>
                    <a href="#">
                        <img src="<s:url value="/theme1/images/theme-green.gif"/>" alt="Green" />
                    </a><a href="#">
                        <img src="<s:url value="/theme1/images/theme-yellow.gif"/>" alt="Yellow" />
                    </a><a href="#">
                        <img src="<s:url value="/theme1/images/theme-blue.gif"/>" alt="Blue" />
                    </a>
                </div>
            </div>
        </div>
        <div id="container">
            <div class="wrapper">

                <div id="col1">
                    <ul id="accordion">
                        <li style="background:none;"><div><a style="color:#000; font-size:16px;" href="#"></a></div></li>
                    </ul>
                </div>
                <div id="col2">

                    <h3>News &amp; Events</h3>
                    <br/><br/>
                    <div align="justify">
                        <strong>Workshop on ERP mission on 10-11 December 2011 in AMU, Aligarh.</strong><br/>
                        For details send email to nesar.ahmad@gmail.com (Dr. Nesar Ahmad) or call 09411878942.
                        <br/><strong><br/><br/>
                            Workshop on ERP mission on 7-8 January 2012 in IGNOU, Delhi.</strong><br/>
                        For details send email to ukanjilal@ignou.ac.in (Dr. Uma Kanjilal) or call 09810488895.
                    </div>

                    <br/><br/>

                </div>
                <div id="col3"> 
                    <jsp:include page="../Calender.jsp"/>
                </div>
                <div class="clear"></div>
            </div>
        </div>
        <div id="footerlinks">
            <a href="about.jsp" target="_blank">About Us</a> | <a href="contact.jsp" target="_blank">Contact Us</a> | <a href="../help.jsp" target="_blank">Help</a> |<a href="feedback.jsp" target="_blank">Feedback</a>
        </div>
        <div id="footer">
            <div class="wrapper">Designed &amp; Developed by: eGyanKosh, IGNOU- 2011 - Best Viewed with 1024 X 768</div>
        </div>
    </body>
</html>