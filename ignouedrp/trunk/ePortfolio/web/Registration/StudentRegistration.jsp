<%-- 
    Document   : StudentRegistration
    Created on : Oct 3, 2011, 11:59:57 AM
Author     : Vinay
Version      : 1
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
        <link href="../theme1/style.css" rel="stylesheet" type="text/css" />
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
                    &nbsp;<br/>
                    <img src="<s:url value="/theme1/images/ignou-logo.jpg"/>"/>
                    <div id="profilelinks">

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
                    <h3>Student Registration</h3>
                    <br/><br/>
                    <s:form action="StudentReg" method="post">
                        <table cellpadding="2" cellspacing="2" border="0" align="center">
                            <tr><td>
                                    <s:textfield cssClass="width250" name="emailId" label="Email Id"/>
                                </td></tr>
                            <tr><td>
                                    <s:password cssClass="width250" name="password" label="Password"/>
                                </td></tr>
                            <tr><td>
                                    <s:password cssClass="width250" name="rePassword" label="Re-Password" />
                                </td></tr>
                            <tr><td>
                                    <s:textfield cssClass="width250" name="role" label="Role" />
                                </td></tr> 
                            <tr><td>
                                    <s:textfield cssClass="width250" name="fname" label="First Name"/>
                                </td></tr>
                            <tr><td>
                                    <s:textfield cssClass="width250" name="mname" label="Middle Name"/>
                                </td></tr>
                            <tr><td>
                                    <s:textfield cssClass="width250" name="lname" label="Last Name" />
                                </td></tr> 
                            <tr><td>
                                    <s:textfield cssClass="width250" name="occupation" label="Occupation"/>
                                </td></tr>
                            <tr><td>
                                    <s:textfield cssClass="width250" name="univName" label="University, Institute"/>
                                </td></tr>
                            <tr><td>
                                    <s:textfield cssClass="width250" name="univRegNo" label="Registration No." />
                                </td></tr> 
                            <tr><td>
                                    <s:textfield cssClass="width250" name="programe" label="Programme"/>
                                </td></tr>
                        </table>
                        <br/>
                        <s:submit cssClass="floatL buttonsMiddle" value="Submit"/>
                        <s:reset value="Reset" align="left"/>
                    </s:form>
                    <br/>
                </div>
                <div id="col3">  
                        <h3>Upcoming Events</h3>
                        <s:include value="/Event.jsp"/>
                    <div class="more"><a href="<s:url value="/Events/"/>" target="_blank">more...</a></div>
                                        
                </div>
                <div class="clear"></div>
            </div>
        </div>
        <jsp:include page="../Footer.jsp"/>
    </body>
</html>