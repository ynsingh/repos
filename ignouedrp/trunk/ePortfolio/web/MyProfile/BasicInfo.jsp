<%-- 
    Document   : BasicInfo
    Created on : Sep 1, 2011, 5:57:59 PM
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
        
        <link rel="stylesheet" type="text/css" media="screen" href="http://ajax.googleapis.com/ajax/libs/jqueryui/1.7.2/themes/base/jquery-ui.css"/>

        
    </head>
    <body><%        
           if (session.getAttribute("user_id") == null) {
                pageContext.forward("../login.jsp");
            }
                   
        %>
        <jsp:include page="../Header.jsp"/>
        <div id="container">
            <div class="wrapper">
                <s:url id="SFID" action="show_files" namespace="/MyResources"/>
                <s:url id="MyPlanID" action="fetch" namespace="/MyPlans"/>

                <jsp:include page="../Left-Nevigation.jsp"/>
                <div id="col2">
                    <h3>Basic Information</h3>

                    <br/>
                    <s:url id="PBID" action="EditBasicInfo" namespace="/MyProfile"/>
                    <s:a href="%{PBID}"><img src="../icons/edit.gif" align="right" title="Edit Information"/></s:a>
                    <table align="center" width="70%" cellpadding="4" border="0" cellspacing="0">

                        <s:iterator value="basicListList" var="ProfileBasic">
                
                            <tr><td>Name: </td><td>
                                    <s:property value="fname"/>&nbsp;<s:property value="mname"/>&nbsp;<s:property value="lname"/>
                                </td></tr>
                            
                            <tr><td >
                                    <s:label name="gender" label="Gender"/>

                                </td></tr>
                            <tr><td >
                                    <s:label name="dateOfBirth" label="Date of Birth"/>
                                </td></tr>
                            <tr><td >
                                    <s:label name="pbirth" label="Place of Birth"/>

                                </td></tr>        
                            <tr><td >
                                    <s:label name="mstatus" label="Marital Status"/>
                                   
                                </td></tr>
                            <tr><td >
                                    <s:label name="aboutMe" label="About Me" cols="30" rows="5"/>

                                </td></tr>                                
                            </s:iterator>
                    </table>
                    <br/>
                    <br/><br/>
                </div>
                    <jsp:include page="../Right-Nevigation.jsp"/>
                <div class="clear"></div>
            </div>
        </div>
                    <jsp:include page="../Footer.jsp"/>
    </body>
</html>
