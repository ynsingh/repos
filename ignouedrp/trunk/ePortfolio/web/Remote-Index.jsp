<%-- 
    Document   : Remote-Index
    Created on : May 29, 2012, 2:16:46 PM
    Author     : IGNOU Team
--%>

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
        
        <script type="text/javascript" src="<s:url value="/js/expand.js"/>"></script>
         <script>
            $(function() {
                $( "#accordion" ).accordion();
            });
        </script>
    </head>
    <body>
        <% String role = session.getAttribute("role").toString();
            if (session.getAttribute("user_id") == null) {
                response.sendRedirect("../Login.jsp");
            }
        %>

        <div class="w100 fl-l">
            <div class="w990p mar0a">
                <!--Header Starts Here-->
                <jsp:include page="Header.jsp"/>
                <!--Header Ends Here-->

                <!--Middle Section Starts Here-->
                <div class="w100 fl-l">
                    <div class="middle_bg">
                        <!--Left box Starts Here-->
                        <jsp:include page="Left-Nevigation.jsp"/> 
                        <!--Left box Ends Here-->

                        <!--Right box Starts Here-->
                        <div class="right_box">
                            <div class="my_account_bg">My Account</div>
                            <s:url id="RefID" action="ShowReference" namespace="/MyProfile"></s:url>                    
                            <% if (role.contains("faculty")) {%>     
                            <s:url id="TestiID" action="StdReqTesti" namespace="/MyProfile"/>
                            <% } else if (role.contains("student")) {%>
                            <s:url id="TestiID" action="STestimonialList" namespace="/MyProfile"/>
                            <% }%> 
                            <div class="v_gallery">
                                <div class="w100 fl-l mart10">
                                    <div class="hdng_gallery"><span>My Portfolio</span></div>
                                    <div class="gallery">
                                        <ul id="myportfolioicon" class="jcarousel-skin-tango">
                                            <li><a href="<s:url value="/MyProfile/MyProfile.jsp"/>"><img src="<s:url value="/icons/my-profile.gif"/>" alt="My Profile" /><span>My Profile</span></a></li>
                                            <li><s:a href="%{TestiID}"><img src="<s:url value="/icons/testmonials.gif"/>" alt="Testimonials" /><span>Testimonials</span></s:a></li>
                                            <li><s:a href="%{RefID}"><img src="<s:url value="/icons/references.gif"/>" alt="References" /><span>References</span></s:a></li>
                                        </ul>
                                    </div>
                                </div>
                                <div class="w100 fl-l mart10">
                                    <div class="hdng_gallery"><span>My Education and Work</span></div>
                                    <% if (role.contains("faculty")) {%>     
                                    <s:url id="EviID" action="FacultyTaskShow" namespace="/Evidence"/>
                                    <% } else if (role.contains("student")) {%>
                                    <s:url id="EviID" action="StudentTaskList" namespace="/Evidence"/>
                                    <% }%> 
                                    <div class="gallery">
                                        <ul id="myconnectionicon" class="jcarousel-skin-tango">
                                            <li><s:a href="%{EviID}"><img src="<s:url value="/icons/task-activities.gif"/>" width="60" height="60"/><span>Task / Activities</span></s:a></li>
                                            <li><a href="MyWorkspace/MyWorkspace.jsp"><img src="<s:url value="/icons/my-workspace.gif"/>" alt="My Workspace" /><span>My Workspace</span></a></li>
                                            <li><a href="<s:url value="/PageUnderConstruction.jsp"/>"><img src="<s:url value="/icons/institute.gif"/>" alt="Institute" /><span>Institute</span></a></li>
                                            <li><a href="<s:url value="/PageUnderConstruction.jsp"/>"><img src="<s:url value="/icons/course.gif"/>" alt="Course" /><span>Course</span></a></li>
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
                                            <li><a href="<s:url value="/PageUnderConstruction.jsp"/>"><img src="<s:url value="/icons/chat.gif"/>" alt="Chat" /><span>Chat</span></a></li>
                                            <li><a href="<s:url value="/PageUnderConstruction.jsp"/>"><img src="<s:url value="/icons/blog.gif"/>" alt="Blog" /><span>Blog</span></a></li>
                                            <li><a href="<s:url value="/PageUnderConstruction.jsp"/>"><img src="<s:url value="/icons/wiki.gif"/>" alt="Wiki" /><span>Wiki</span></a></li>
                                            <li><a href="<s:url value="/PageUnderConstruction.jsp"/>"><img src="<s:url value="/icons/communication.gif"/>" alt="Communication" /><span>Communication</span></a></li>
                                            <li><a href="<s:url value="/PageUnderConstruction.jsp"/>"><img src="<s:url value="/icons/forum.gif"/>" alt="Discussion" /><span>Discussion</span></a></li>
                                        </ul>
                                    </div>
                                </div>
                                <div class="w100 fl-l mart10">
                                    <div class="hdng_gallery"><span>My Builder</span></div>
                                    <div class="gallery">
                                        <ul id="mybuildericon" class="jcarousel-skin-tango">
                                            <li><a href="<s:url value="/Builder/indexResume.jsp"/>"><img src="<s:url value="/icons/resume-builder.gif"/>" alt="Resume Builder" /><span>Resume Builder</span></a></li>
                                            <li><a href="<s:url value="/Builder/eVisitingCard.jsp"/>"><img src="<s:url value="/icons/e-visiting-card.gif"/>" alt="Visiting Card Builder" /><span>Visiting Card Builder</span></a></li>
                                            <li><a href="<s:url value="/Builder/viewEportfolio.jsp"/>"><img src="<s:url value="/icons/portfolio-view.gif"/>" alt="My ePortfolio View" /><span>My ePortfolio View</span></a></li>
                                        </ul>
                                    </div>
                                </div>
                            </div>
                            <!--Right box Starts Here-->
                        </div>
                        
                    </div>
                    <!--Middle Section Ends Here-->
                </div>
            </div>
        </div>
        <jsp:include page="Footer.jsp"/>  
    </body>
</html>