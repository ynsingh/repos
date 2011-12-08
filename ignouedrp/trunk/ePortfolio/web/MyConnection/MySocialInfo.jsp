<%-- 
    Document   : MySocialInfo
    Created on : Oct 13, 2011, 11:28:03 AM
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
        <s:include value="/Header.jsp"/>
        <div id="container">
            <div class="wrapper">

                <s:include value="/Left-Nevigation.jsp"/>
                <div id="col2">
                    <h3>My Social Connections</h3>

                    <br/>
                    <s:url id="PSID" action="EditSocialInfo" namespace="/MyConnection"/>
                    <s:a href="%{PSID}"><img src="<s:url value="/icons/edit.gif"/>" align="right" title="Edit Information"/></s:a>

                    <table align="center" width="75%" cellpadding="4" border="0" cellspacing="0">

                        <s:iterator value="socialListList" var="ProfileSocial">
                            <tr><th align="left">Gtalk :</th><td>
                                    <s:property value="gtalk"/>
                                </td></tr>
                            <tr><th align="left">Skype :</th><td >
                                    <s:property value="skype"/>
                                </td></tr>
                            <tr><th align="left">MSN :</th><td >
                                    <s:property value="msn"/>
                                </td></tr>
                            <tr><th align="left">AIM :</th><td >
                                    <s:property value="aim"/>
                                </td></tr>
                            <tr><th align="left">Yahoo :</th><td >
                                    <s:property value="yahoo"/>
                                </td></tr>
                            <tr><th align="left">Facebook :</th><td >
                                    <a href="<s:property value="facebook"/>" target="_blank"><s:property value="facebook"/></a>
                                </td></tr>
                            <tr><th align="left">Orkut :</th><td >
                                    <a href="<s:property value="orkut"/>" target="_blank"><s:property value="orkut"/></a>
                                </td></tr>
                            <tr><th align="left">Twitter :</th><td >
                                    <a href="<s:property value="twitter"/>" target="_blank"><s:property value="twitter"/></a>
                                </td></tr>
                            <tr><th align="left">Blog :</th><td >
                                    <a href="<s:property value="blog"/>" target="_blank"><s:property value="blog"/></a>
                                </td></tr>
                            </s:iterator>
                    </table>
                    <br/><br/>
                </div>
                <s:include value="/Right-Nevigation.jsp"/>
                <div class="clear"></div>
            </div>
        </div>
        <s:include value="/Footer.jsp"/>
    </body>
</html>
