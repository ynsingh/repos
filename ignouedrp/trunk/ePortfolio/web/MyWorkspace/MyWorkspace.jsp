<%-- 
    Document   : MyWorkspace
    Created on : Sep 22, 2011, 3:00:59 PM
Author     : IGNOU Team
Version      : 1
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title>Profile</title>
        <link href="<s:url value="/css/master.css"/>" rel="stylesheet" type="text/css" />
        <link href="<s:url value="/css/collapse.css"/>" rel="stylesheet" type="text/css" />
        <link href="<s:url value="/css/skin.css"/>" rel="stylesheet" type="text/css" />
        <script type="text/javascript" src="<s:url value="/js/jquery-1.6.4.min.js"/>"></script>

        <script type="text/javascript" src="<s:url value="/js/expand.js"/>"></script>
         <script>
            $(function() {
                $( "#accordion" ).accordion();
            });
        </script>
    </head>
    <body>
        <%
            String role = session.getAttribute("role").toString();
            if (session.getAttribute("user_id") == null) {
                response.sendRedirect("../Login.jsp");
            }
        %>
        <div class="w100 fl-l">
            <div class="w990p mar0a">
                <!--Header Starts Here-->
                <s:include  value="/Header.jsp"/>
                <!--Header Ends Here-->

                <!--Middle Section Starts Here-->
                <div class="w100 fl-l">
                    <div class="middle_bg">
                        <!--Left box Starts Here-->
                        <s:include value="/Left-Nevigation.jsp"/> 
                        <!--Left box Ends Here-->

                        <!--Right box Starts Here-->
                        <div class="right_box">
                            <div class="my_account_bg">My Profile</div>
                            <div class="v_gallery">
                                <div class="w100 fl-l mart10">
                                    <div class="bradcum">
                                        <a href="<s:url value="/Welcome-Index.jsp"/>">Home</a>&nbsp;>&nbsp;<a href="<s:url value="/MyEdudation-Workspace.jsp"/>">My Education and Work</a>&nbsp;> My Workspace
                                    </div>
                                    <s:url id="PPUID" action="ShowPublicationInfo" namespace="/MyWorkspace"/>
                                    <s:url id="MyPlanID" action="fetch" namespace="/MyPlans"/>
                                    <s:url id="SFID" action="show_files" namespace="/MyResources"/>
                                    <s:url id="MProjectID" action="ProjectInfo" namespace="/MyWorkspace"/>
                                    <s:url id="MNID" action="ShowNotesInfo" namespace="/MyWorkspace"/>
                                    <s:url id="EXTID" action="ShowExt" namespace="/MyWorkspace"/>
                                    <s:url id="EventID" action="ShowEventInfo" namespace="/Events"/>
                                    <table border="0" class="mar0a" cellpadding="0" cellspacing="0">
                                        <ul class="jcarousel-skin-tango">
                                            <li><a href="<s:url value="MyPublications.jsp"/>"><img src="<s:url value="/icons/publications.gif"/>" width="60" height="60" /><span>My Publications</span></a></li>
                                            <li><s:a href="%{SFID}"><img src="<s:url value="/icons/my-files.gif"/>" width="60" height="60" /><span>My Resources</span></s:a></li>
                                            <li><s:a href="%{MyPlanID}"><img src="<s:url value="/icons/my-plan.gif"/>" width="60" height="60" /><span>My Plans</span></s:a></li>
                                            <li><s:a href="%{MNID}"><img src="<s:url value="/icons/my-notes.gif"/>" width="60" height="60" /><span>My Notes</span></s:a></li>
                                            <li><s:a href="%{MProjectID}"><img src="<s:url value="/icons/my-projects.gif"/>" width="60" height="60" /><span>My Projects</span></s:a></li>
                                            <li><s:a href="%{EXTID}"><img src="<s:url value="/icons/extra-activities.gif"/>" width="60" height="60" /><span>My Extra Activities</span></s:a></li>
                                            <% if (role.contains("faculty")) {%>
                                            <li><s:a href="%{EventID}"><img src="<s:url value="/icons/event.gif"/>" width="60" height="60" /><span>Events</span></s:a></li>
                                            <% } else if (role.contains("student")) {%>
                                            <% }%> 
                                        </ul>
                                    </table>
                                </div>
                            </div>
                            <!--Right box End Here-->
                        </div>

                    </div>
                    <!--Middle Section Ends Here-->
                </div>
            </div>
        </div>
        <s:include value="/Footer.jsp"/>  
    </body>
</html>