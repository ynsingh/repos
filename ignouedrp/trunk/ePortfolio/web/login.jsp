<%-- 
    Document   : login
    Created on : Sep 3, 2011, 4:21:46 PM 
Author     : Amit
Version    : 1
--%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title>Welcome to ePortfolio</title>
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
        <script type="text/javascript">
            $(document).ready(function(){
                $("#registerlink").hover(function(){
                    $('#registeras').show();
                }); 
                $("#header, #container").hover(function(){
                    $('#registeras').hide();
                }); 
            });
        </script>
    </head>
    <body>
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
                        <ul id="registeras">
                            <li><a href="<s:url value="/Registration/StudentRegistration.jsp"/>">Student</a></li>
                            <li><a href="<s:url value="/Registration/FacultyRegistration.jsp"/>">Faculty</a></li>
                            <li><a href="<s:url value="/Registration/InstituteRegistration.jsp"/>">Institute</a></li>
                        </ul>
                        <a id="registerlink" href="#">Registration</a>
                    </div>
                </div>
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

                    <h3>Login</h3>
                    <br/><br/><br/>  <div><s:property value="feedbackmsg"/></div><br/>
                    <s:form action="Login" method="post">
                        <table cellpadding="2" cellspacing="2" border="0" align="center">
                            <tr><td><s:property value="msg"/></td></tr>
                            <tr><td>
                                    <s:textfield name="email_id" label="Username" />
                                </td></tr>
                            <tr><td>
                                    <s:password name="password" label="Password" />
                                </td></tr>
                            <tr><td>Role</td><td>
                                    <select name="role" style="width: 160px;">
                                        <option value="#">Select Your Role</option>
                                        <option value="admin">Administrator</option>
                                        <option value="student">Student</option>
                                        <option value="faculty">Faculty</option>
                                        <option value="institute">Institute</option>
                                        <option value="guest" action="">Guest</option>


                                    </select>
                                </td></tr>   
                        </table>
                        <s:submit cssClass="floatL buttonsMiddle" value="Login"/>
                        <s:reset value="Reset" align="left"/>
                    </s:form>
                    <br/>
                    <table align="center">
                        <tr><td>
                                <a href="ForgotPassword.jsp" align="center">Forgot Password</a>
                            </td></tr>
                    </table>
                    <br/><br/>

                </div>
                <div id="col3"> 
                    <jsp:include page="Calender.jsp"/>
                    <h3>Upcoming Events</h3>
                    <jsp:include page="Event.jsp"/>
                </div>
                <div class="clear"></div>
            </div>
        </div>
        <div id="footerlinks">
            <a href="about.jsp" target="_blank">About Us</a> | <a href="contact.jsp" target="_blank">Contact Us</a> | <a href="help.jsp" target="_blank">Help</a> |<a href="feedback.jsp" target="_blank">Feedback</a>
        </div>
        <div id="footer">
            <div class="wrapper">Designed &amp; Developed by: eGyanKosh, IGNOU- 2011 - Best Viewed with 1024 X 768</div>

        </div>
    </body>
</html>