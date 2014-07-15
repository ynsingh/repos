<%-- 
    Document   : MyPublications
    Created on : Dec 2, 2011, 2:23:01 PM
    Author     : IGNOU Team
--%>

<%@page import="java.io.Serializable"%>
<%@page import="java.util.Date"%>
<%@page import="org.apache.log4j.Logger"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title>My Publication Index</title>
        <link href="<s:url value="/css/master.css"/>" rel="stylesheet" type="text/css" />         <link href="<s:url value="/css/main.css"/>" rel="stylesheet" type="text/css" />
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
            final Logger logger = Logger.getLogger(this.getClass());
            String ipAddress = request.getRemoteAddr();
             logger.warn(session.getAttribute("user_id") + " Accessed from: " + ipAddress + " at: " + new Date());
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
                            <div class="my_account_bg">Publications</div>
                            <div class="v_gallery">
                                <div class="w100 fl-l mart10">
                                    <div class="bradcum">
                                        <a href="<s:url value="/Welcome-Index.jsp"/>">Home</a>&nbsp;>&nbsp;<a href="<s:url value="/MyEdudation-Workspace.jsp"/>">My Education and Work</a>&nbsp;> <a href="<s:url value="/MyWorkspace/MyWorkspace.jsp"/>">My Workspace</a> &nbsp;> My Publication
                                    </div>
                                    <table border="0" class="mar0a" cellpadding="0" cellspacing="10">
                                        <ul class="jcarousel-skin-tango">
                                            <s:url id="ConfID" action="ShowConference" namespace="/MyWorkspace"/>
                                            <s:url id="SWID" action="ShowSW" namespace="/MyWorkspace"/>
                                            <s:url id="SJID" action="ShowJournal" namespace="/MyWorkspace"/>
                                            <s:url id="BCID" action="ShowBookChapter" namespace="/MyWorkspace"/>
                                            <s:url id="MPubID" action="showMP" namespace="/MyWorkspace"/>
                                            <s:url id="ITID" action="showTL" namespace="/MyWorkspace"/> 
                                            <s:url id="PatID" action="ShowPatent" namespace="/MyWorkspace"/>
                                            <s:url id="TDID" action="showTD" namespace="/MyWorkspace"/>
                                            <s:url id="SEPID" action="ShowExchangePro" namespace="/MyWorkspace"/>
                                            <s:url id="GovID" action="ShowGovernance" namespace="/MyWorkspace"/>
                                            <s:url id="ConID" action="ShowConsultancy" namespace="/MyWorkspace"/>
                                            <s:url id="RCID" action="showRCInfo" namespace="/MyWorkspace"/>
                                            <li><s:a href="%{ConfID}"><img src="<s:url value="/icons/conferences.gif"/>" width="60" height="60" /><span>Conferences</span></s:a></li>
                                            <li><s:a href="%{SWID}"><img src="<s:url value="/icons/seminars.gif"/>" width="60" height="60" /><span>Seminars / Workshops</span></s:a></li>
                                            <li><s:a href="%{SJID}"><img src="<s:url value="/icons/journals.gif"/>" width="60" height="60" /><span>Journals</span></s:a></li>
                                            <li><s:a href="%{BCID}"><img src="<s:url value="/icons/books-chapters.gif"/>" width="60" height="60" /><span>Books / Chapters</span></s:a></li>
                                            <li><s:a href="%{MPubID}"><img src="<s:url value="/icons/media-publications.gif"/>" width="60" height="60" /><span>Media Publications</span></s:a></li>
                                            <li><s:a href="%{#ITID}"><img src="<s:url value="/icons/invited-talks.gif"/>" width="60" height="60" /><span>Invited Talks / Guest Lectures</span></s:a></li>
                                            <li><s:a href="%{SEPID}"><img src="<s:url value="/icons/student-exchange.gif"/>" width="60" height="60" /><span style="width:105px !important;">Student Exchange Programme</span></s:a></li>
                                            <li><s:a href="%{TDID}"><img src="<s:url value="/icons/thesis.gif"/>" width="60" height="60" /><span>Thesis / Dissertation</span></s:a></li>
                                            <li><s:a href="%{PatID}"><img src="<s:url value="/icons/patents.gif"/>" width="60" height="60" /><span>Patents</span></s:a></li>
                                            <% if (role.contains("faculty")) {%>
                                            <li><s:a href="%{ConID}"><img src="<s:url value="/icons/consultancy.gif"/>" width="60" height="60" /><span>Consultancy</span></s:a></li>
                                            <li><s:a href="%{GovID}"><img src="<s:url value="/icons/governance.gif"/>" width="60" height="60" /><span>Corporate Life</span></s:a></li>
                                            <li><s:a href="%{RCID}"><img src="<s:url value="/icons/review-committies.gif"/>" width="60" height="60" /><span>Academic Responsibilities</span></s:a></li>
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