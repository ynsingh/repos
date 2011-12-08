<%-- 
    Document   : SocialInfoAdd
    Created on : Sep 16, 2011, 10:42:37 AM
Author     : Vinay
Version      : 1
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
         <title> My Social Connections</title>
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
        <jsp:include page="../Header.jsp"/>

        <div id="container">
            <div class="wrapper">
                <jsp:include page="../Left-Nevigation.jsp"/>
                <div id="col2">
                    <h3>Social Networking Accounts</h3>
                    <br/>



                    <s:form action="AddSocialInfo" method="post">
                        <table align="center" width="75%" cellpadding="4" border="0" cellspacing="0">

                            <s:textfield cssClass="width250" name="gtalk" label="GTalk"/>
                            <s:textfield cssClass="width250" name="skype" label="Skype"/>
                            <s:textfield cssClass="width250" name="msn" label="MSN"/>
                            <s:textfield cssClass="width250" name="aim" label="AIM"/>
                            <s:textfield cssClass="width250" name="yahoo" label="Yahoo"/>
                            <s:textfield cssClass="width250" name="facebook" label="Facebook"/>
                            <s:textfield cssClass="width250" name="orkut" label="Orkut"/>
                            <s:textfield cssClass="width250" name="twitter" label="Twitter"/>
                            <s:textfield cssClass="width250" name="blog" label="Blog"/>


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
