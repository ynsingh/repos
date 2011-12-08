<%-- 
    Document   : index
    Created on : Aug 9, 2011, 12:43:49 PM
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
        <link href="theme1/style.css" rel="stylesheet" type="text/css" />
    </head>
    <body>
        <%        
           if (session.getAttribute("user_id") == null) {
                pageContext.forward("login.jsp");
            }
                   
        %>
        <jsp:include page="Header.jsp"/>
        <div id="container">
            <div class="wrapper">
                <jsp:include page="Left-Nevigation.jsp"/>                
                <div id="col2">
                    <s:url id="RefID" action="ShowReference" namespace="/MyProfile"/>
                    <h3>My Account</h3>
                    <h4 class="heading"><span>My Portfolio</span></h4>
                    <table border="0" class="default" cellpadding="0" cellspacing="0">
                        <tr><td width="33%"><a href="<s:url value="/MyProfile/MyProfile.jsp"/>"><img src="icons/my-profile.gif" width="60" height="60" /><span>My Profile</span></a></td>
                            <td width="33%"><a href="<s:url value="/PageUnderConstruction.jsp"/>"><img src="icons/testmonials.gif" width="60" height="60" /><span>Testimonials</span></a></td>
                            <td width="33%"><s:a href="%{RefID}"><img src="icons/references.gif" width="60" height="60"/><span>References</span></s:a></td>
                            </tr>

                        </table>
                        <div class="more"><a href="">more...</a></div>  

                        <h4 class="heading"><span>My Education and Work</span></h4>
                        <table border="0" class="default" cellpadding="0" cellspacing="0">
                            <tr><td width="33%"><a href="<s:url value="/PageUnderConstruction.jsp"/>"><img src="icons/institute.gif" width="60" height="60" /><span>Institute</span></a></td>
                            <td width="33%"><a href="<s:url value="/PageUnderConstruction.jsp"/>"><img src="icons/course.gif" width="60" height="60" /><span>Course</span></a></td>
                            <td width="33%"><a href="<s:url value="/PageUnderConstruction.jsp"/>"><img src="icons/exam-eval.gif" width="60" height="60" /> <span>Examination &amp; Evaluation</span></a></td>  
                        </tr>
                        <tr>
                            <td width="33%"><a href="MyWorkspace/MyWorkspace.jsp"><img src="icons/my-workspace.gif" width="60" height="60" /> <span>My Workspace</span></a></td>
                        </tr>
                    </table>
                    <div class="more"><a href="">more...</a></div>

                    <h4 class="heading"><span>My Connections</span></h4>
                    <s:url id="PSID" action="ShowSocialInfo" namespace="/MyConnection"/>
                    <table border="0" class="default" cellpadding="0" cellspacing="0">
                        <tr><td width="33%"><s:a href="%{PSID}"><img src="<s:url value="/icons/social-network.gif"/>" width="60" height="60" /><span>Social Networking</span></s:a></td>
                            <td width="33%"><a href="<s:url value="/PageUnderConstruction.jsp"/>"><img src="icons/chat.gif" width="60" height="60" /><span>Chat</span></a></td>
                            <td width="33%"><a href="<s:url value="/PageUnderConstruction.jsp"/>"><img src="icons/blog.gif" width="60" height="60" /><span>Blog</span></a></td>
                        </tr>
                        <tr><td width="33%"><a href="<s:url value="/PageUnderConstruction.jsp"/>"><img src="icons/wiki.gif" width="60" height="60" /><span>Wiki</span></a></td>
                            <td width="33%"><a href="<s:url value="/PageUnderConstruction.jsp"/>"><img src="icons/communication.gif" width="60" height="60" /><span>Communication</span></a></td>
                            <td width="33%"><a href="<s:url value="/PageUnderConstruction.jsp"/>"><img src="icons/forum.gif" width="60" height="60" /><span>Discussion Forum</span></a></td>
                        </tr>
                    </table>
                    <div class="more"><a href="">more...</a></div>

                    <h4 class="heading"><span>My Builder</span></h4>
                    <table border="0" class="default" cellpadding="0" cellspacing="0">
                        <tr><td width="33%"><a href="<s:url value="/MyResume/indexResume.jsp"/>"><img src="<s:url value="icons/resume-builder.gif"/>" width="60" height="60" /><span>Resume Builder</span></a></td>
                            <td width="33%"><a href="<s:url value="/PageUnderConstruction.jsp"/>"><img src="<s:url value="/icons/e-visiting-card.gif"/>" width="60" height="60" /><span>Visiting Card Builder</span></a></td>
                            <td width="33%"><a href="<s:url value="/PageUnderConstruction.jsp"/>"><img src="<s:url value="/icons/portfolio-view.gif"/>" width="60" height="60" /><span>My ePortfolio View</span></a></td>
                        </tr>
                    </table>
                    <div class="more"><a href="">more...</a></div>

                </div>
                <jsp:include page="Right-Nevigation.jsp"/>
                <div class="clear"></div>
            </div>
        </div>
        <jsp:include page="Footer.jsp"/>  
    </body>
</html>
