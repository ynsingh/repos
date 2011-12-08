<%-- 
    Document   : MyWorkspace
    Created on : Sep 22, 2011, 3:00:59 PM
Author     : Vinay
Version      : 1
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
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
        <s:include value="../Header.jsp"/>

        <div id="container">
            <div class="wrapper">
                <s:include value="/Left-Nevigation.jsp"/>

                <div id="col2">
                    <s:url id="PPUID" action="ShowPublicationInfo" namespace="/MyProfile"/>
                    <s:url id="MyPlanID" action="fetch" namespace="/MyPlans"/>
                    <s:url id="SFID" action="show_files" namespace="/MyResources"/>
                    <s:url id="MProjectID" action="ProjectInfo" namespace="/MyWorkspace"/>
                    <h3>My Workspace</h3>
                    <br/><br/>
                    <table border="0" class="default" cellpadding="0" cellspacing="0">
                        <tr>
                            <td width="33%"><s:a href="%{PPUID}"><img src="<s:url value="/icons/publications.gif"/>" width="60" height="60" /><span>My Publications</span></s:a></td>
                            <td width="33%"><s:a href="%{SFID}"><img src="<s:url value="/icons/my-files.gif"/>" width="60" height="60" /><span>My Resources</span></s:a></td>
                            <td width="33%"><s:a href="%{MyPlanID}"><img src="<s:url value="/icons/my-plan.gif"/>" width="60" height="60" /><span>My Plans</span></s:a></td>
                        </tr>
                        <tr>
                            <td width="33%"><s:a href="%{}"><img src="<s:url value="/icons/my-notes.gif"/>" width="60" height="60" /><span>My Notes</span></s:a></td>
                            <td width="33%"><s:a href="%{MProjectID}"><img src="<s:url value="/icons/my-projects.gif"/>" width="60" height="60" /><span>My Projects</span></s:a></td>
                            <td width="33%"><s:a href="%{}"><img src="<s:url value="/icons/my-assignments.gif"/>" width="60" height="60" /><span>My Assignments</span></s:a></td>
                        </tr>
                        <tr>
                            <td width="33%"><s:a href="%{}"><img src="<s:url value="/icons/extra-activities.gif"/>" width="60" height="60" /><span>My Extra Activities</span></s:a></td>

                        </tr>
                    </table>

                </div>
                <s:include value="/Right-Nevigation.jsp"/>
                <div class="clear"></div>
            </div>
        </div>
        <jsp:include page="../Footer.jsp"/> 
    </body>
</html>
