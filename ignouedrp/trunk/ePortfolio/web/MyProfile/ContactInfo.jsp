<%-- 
    Document   : ContactInfo
    Created on : Sep 1, 2011, 5:58:26 PM
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
                        <h3>Contact Information</h3>
                        <br/>    
                    <s:url id="PCID" action="EditContactInfo" namespace="/MyProfile"/>
                    <s:a href="%{PCID}"><img src="../icons/edit.gif" align="right" title="Edit Information"/></s:a>

<br/>
                        <table align="center" width="90%" cellpadding="4" border="0" cellspacing="0">

                            <s:iterator value="contactListList" var="ProfileContact">
                                <tr><th align="left">Mailing Address: </th><td>
                                       <s:property value="address1"/>&nbsp;
                                       <s:property value="address2"/>&nbsp;
                                       <s:property value="city"/>&nbsp;<br/>
                                       <s:property value="state"/>&nbsp;
                                       <s:property value="country"/>&nbsp;
                                       <s:property value="pin"/>
                                    </td></tr>
                                
                                <tr><th align="left">Telephone&nbsp;(Res): </th><td >
                                        <s:property value="htelephone"/>

                                    </td></tr>
                                <tr><th align="left">Telephone&nbsp;(Off): </th><td >
                                        <s:property value="otelephone"/>
                                    </td></tr>
                                <tr><th align="left">Mobile : </th><td >
                                        <s:property value="mobile"/>
                                    </td></tr>
                                <tr><th align="left">Fax :</th><td >
                                        <s:property value="fax"/>
                                    </td></tr>
                                <tr><th align="left">Emails :</th><td >
                                        <s:property value="email1"/>&nbsp;<s:property value="email2"/>&nbsp;<s:property value="email3" />
                                    </td></tr>
                                <tr><th align="left">Website&nbsp;(Org)</th><td >
                                        <s:property value="owebsite"/>
                                    </td></tr>
                                <tr><th align="left">Website&nbsp;(Per)</th><td >
                                        <s:property value="pwebsite"/>
                                    </td></tr>
                                </s:iterator>
                        </table>                  
                    <br/><br/>
                </div>
                    <jsp:include page="../Right-Nevigation.jsp"/>
                <div class="clear"></div>
            </div>
        </div>
               <jsp:include page="../Footer.jsp"/>    </body>
</html>
