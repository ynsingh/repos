<%--
Document   : Welcome-Index
Created on : Aug 9, 2011, 12:43:49 PM
Author     : IGNOU Team
Version      : 1
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
        <title>ePortfolio Home</title>
        <link href="<s:url value="/css/master.css"/>" rel="stylesheet" type="text/css" />
        <link href="<s:url value="/css/collapse.css"/>" rel="stylesheet" type="text/css" />
        <link href="<s:url value="/css/skin.css"/>" rel="stylesheet" type="text/css" />
        <script type="text/javascript" src="<s:url value="/js/jquery-1.6.4.min.js"/>"></script>
        <script type="text/javascript" src="<s:url value="/js/jquery.jcarousel.min.js"/>"></script>
        <script type="text/javascript" src="<s:url value="/js/expand.js"/>"></script>
        <script>
            $(function() {
                $("#accordion").accordion();
            });
        </script>
        <script type="text/javascript">
            jQuery(document).ready(function() {
                jQuery('#myportfolioicon').jcarousel();
            });
            jQuery(document).ready(function() {
                jQuery('#myconnectionicon').jcarousel();
            });
            jQuery(document).ready(function() {
                jQuery('#myeducationicon').jcarousel();
            });
            jQuery(document).ready(function() {
                jQuery('#mybuildericon').jcarousel();
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
                session.invalidate();
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
                        <s:include  value="/Left-Nevigation.jsp"/>
                        <!--Left box Ends Here-->

                        <!--Right box Starts Here-->
                        <div class="right_box">
                            <% if (role.contains("admin")) {%>
                            <s:url id="LinksID" action="ShowNavLinks" namespace="/Administrator"/>
                            <s:url id="RegInsiID" action="ShowRegisteredInstitute" namespace="/Administrator"/>
                            <s:url id="AllInsiID" value="/Administrator/Programmes.jsp"/>
                            <s:url id="DepID" value="/Administrator/Departments.jsp"/>
                            <s:url id="EVID" action="ShowEventInfo" namespace="/Events"/>
                            <s:url id="FDBID" action="ShowFeedBackInfo" namespace="/Administrator"/>
                            <div class="my_account_bg">Welcome to Administrator</div>
                            <div class="w100 fl-l tc fbld fcgreen">
                                <s:property value="msg"/>
                            </div>
                            <div class="w100 fl-l mart10">
                                <div class="hdng_gallery nodisplay"><span>My Connections</span></div>
                                <s:url id="PSID" action="ShowSocialInfo" namespace="/MyConnection"/>
                                <div class="gallery">
                                    <ul id="" class="jcarousel-skin-tango">
                                        <li><s:a href="%{RegInsiID}"><img src="<s:url value="/icons/institute.gif"/>" alt="institute" /><span>Institute</span></s:a></li>
                                        <li><s:a href="%{DepID}"><img src="<s:url value="/icons/department.gif"/>" alt="SchooleDeparment" /><span>School / Department</span></s:a></li>
                                        <li><s:a href="%{AllInsiID}"><img src="<s:url value="/icons/programme.gif"/>" alt="Programme" /><span>Programme</span></s:a></li>
                                            <s:url id="cId" action="courseList" namespace="/Administrator"/>
                                        <li><s:a href="%{cId}"><img src="<s:url value="/icons/course.gif"/>" alt="Corse" /><span>Course</span></s:a></li>
                                        <li><s:a action="UserReqList" namespace="/Requests"><img src="<s:url value="/icons/communication.gif"/>" alt="StudentRequest" /><span>Student Request</span></s:a></li>
                                        <li><a href="<s:url value="/Administrator/HeaderChange.jsp"/>"><img src="<s:url value="/icons/header.gif"/>" alt="Header" /><span>Header</span></a></li>
                                        <li><s:a href="%{LinksID}"><img src="<s:url value="/icons/links.gif"/>" alt="Communication" /><span>Other Links</span></s:a></li>
                                        <li><s:a action="ShowContactUsAdmin" namespace="/Administrator"><img src="<s:url value="/icons/contact.gif"/>" alt="ConatcUs" /><span>Contact Us</span></s:a></li>
                                        <li><s:a href="%{EVID}"><img src="<s:url value="/icons/event.gif"/>" alt="Event" /><span>Event</span></s:a></li>
                                        <li><s:a href="%{FDBID}"><img src="<s:url value="/icons/communication.gif"/>" alt="Feedback" /><span>Feedback</span></s:a></li>
                                        </ul>
                                    </div>
                                </div>
                            <% } else {%>
                            <div class="my_account_bg">Home</div>
                            <s:url id="RefID" action="ShowReference" namespace="/MyProfile"></s:url>
                            <% if (role.contains("faculty")) {%>
                            <s:url id="TestiReqID" action="StdTestiReq" namespace="/MyProfile"/>

                            <% }
                                if (role.contains("student")) {%>
                            <s:url id="TestiReqID" action="TestimonialSent" namespace="/MyProfile"/>
                            <% }%>
                            <div class="v_gallery">
                                <div class="w100 fl-l mart10">
                                    <div class="hdng_gallery"><span>My Portfolio</span></div>
                                    <div class="gallery">
                                        <ul id="myportfolioicon" class="jcarousel-skin-tango">
                                            <li><a href="<s:url value="/MyProfile/MyProfile.jsp"/>"><img src="<s:url value="/icons/my-profile.gif"/>" alt="My Profile" /><span>My Profile</span></a></li>
                                            <li><s:a href="%{TestiReqID}"><img src="<s:url value="/icons/testmonials.gif"/>" alt="Testimonials" /><span>Testimonials</span></s:a></li>
                                            <li><s:a href="%{RefID}"><img src="<s:url value="/icons/references.gif"/>" alt="References" /><span>References</span></s:a></li>
                                            </ul>
                                        </div>
                                    </div>
                                    <div class="w100 fl-l mart10">
                                        <div class="hdng_gallery"><span>My Education and Work</span></div>
                                    <% if (role.contains("faculty")) {%>
                                    <s:url id="EviID" action="FacultyTaskShow" namespace="/Activity"/>
                                    <s:url id="RegInsiID" action="ShowRegisteredInstitute" namespace="/Administrator"/>
                                    <s:url id="ActId" action="myAnnouncedActivities" namespace="/Activities"/>
                                    <% } else if (role.contains("student")) {%>
                                    <s:url id="EviID" action="StudentTaskList" namespace="/Activity"/>
                                    <s:url id="ActId" action="announcedActivities" namespace="/Activities"/>
                                    <% }%>
                                    <div class="gallery">
                                        <ul id="myconnectionicon" class="jcarousel-skin-tango">
                                            <li><s:a href="%{EviID}"><img src="<s:url value="/icons/task-activities.gif"/>" width="60" height="60"/><span>Task / Activities &nbsp; (Evidence)</span></s:a></li>
                                      <!--      <li><s:a href="%{ActId}"><img src="<s:url value="/icons/task-activities.gif"/>" width="60" height="60"/><span>Activities</span></s:a></li> -->
                                            <li><a href="<s:url value="/MyWorkspace/MyWorkspace.jsp"/>"><img src="<s:url value="/icons/my-workspace.gif"/>" alt="My Workspace" /><span>My Workspace</span></a></li>
                                            <li><s:a href="%{RegInsiID}"><img src="<s:url value="/icons/institute.gif"/>" alt="Institute" /><span>Institute</span></s:a></li>
                                                <s:url id="cId" action="myCourse" namespace="/Administrator"/>
                                            <li><s:a href="%{cId}"><img src="<s:url value="/icons/course.gif"/>" alt="Course" /><span>Courses</span></s:a></li>
                                            <li><a href="<s:url value="/ExamEvaluation/ExamEvaluationIndex.jsp"/>"><img src="<s:url value="/icons/exam-eval.gif"/>" alt="Examination &amp; Evaluation" /><span>Examination &amp; Evaluation</span></a></li>
                                        </ul>
                                    </div>
                                </div>
                                <div class="w100 fl-l mart10">
                                    <div class="hdng_gallery"><span>My Connections</span></div>
                                    <s:url id="PSID" action="ShowSocialInfo" namespace="/MyConnection"/>
                                    <div class="gallery">
                                        <ul id="myeducationicon" class="jcarousel-skin-tango">
                                            <li><s:a href="%{PSID}"><img src="<s:url value="/icons/social-network.gif"/>" alt="Social Networking" /><span>Social Networking</span></s:a></li>
                                            <li><a href="<s:url value="http://wiki.edrp.ac.in/wiki/index.php/Main_Page"/>" target="_blank"><img src="<s:url value="/icons/wiki.gif"/>" alt="Wiki" /><span>Wiki</span></a></li>
                                            <li><a href="<s:url value="http://forum.edrp.ac.in/forum/"/>" target="_blank"><img src="<s:url value="/icons/forum.gif"/>" alt="Discussion" /><span>Discussion</span></a></li>
                                            <li><a href="<s:url value="/ErrorPage/PageUnderConstruction.jsp"/>"><img src="<s:url value="/icons/chat.gif"/>" alt="Chat" /><span>Chat</span></a></li>
                                            <li><a href="<s:url value="/ErrorPage/PageUnderConstruction.jsp"/>"><img src="<s:url value="/icons/blog.gif"/>" alt="Blog" /><span>Blog</span></a></li>
                                            <li><a href="<s:url value="/ErrorPage/PageUnderConstruction.jsp"/>"><img src="<s:url value="/icons/communication.gif"/>" alt="Communication" /><span>Communication</span></a></li>
                                        </ul>
                                    </div>
                                </div>
                                <div class="w100 fl-l mart10">
                                    <div class="hdng_gallery"><span>My Builder</span></div>
                                    <div class="gallery">
                                        <ul id="mybuildericon" class="jcarousel-skin-tango">
                                            <s:url id="EVCID" action="VisitingRedirect" namespace="/Builder"/>
                                            <li><a href="<s:url value="/Builder/indexResume.jsp"/>"><img src="<s:url value="/icons/resume-builder.gif"/>" alt="Resume Builder" /><span>Resume Builder</span></a></li>
                                            <li><s:a href="%{EVCID}"><img src="<s:url value="/icons/e-visiting-card.gif"/>" alt="Visiting Card Builder" /><span>Visiting Card Builder</span></s:a></li>
                                            <li><a href="<s:url value="/Builder/CompletePortfolio"/>"><img src="<s:url value="/icons/portfolio-view.gif"/>" alt="My ePortfolio View" /><span>My ePortfolio View</span></a></li>
                                        </ul>
                                    </div>
                                </div>
                            </div>
                            <% }%>
                            <!--Right box Starts Here-->
                        </div>
                    </div>
                    <!--Middle Section Ends Here-->
                </div>
            </div>
        </div>
        <s:include  value="./Footer.jsp"/>
    </body>
</html>