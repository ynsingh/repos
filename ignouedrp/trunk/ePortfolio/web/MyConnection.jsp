<%-- 
    Document   : MyConnection
    Created on : Apr 17, 2012, 1:18:46 PM
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
        <title>My Connection</title>
        <link href="<s:url value="/css/master.css"/>" rel="stylesheet" type="text/css" />
        <link href="<s:url value="/css/collapse.css"/>" rel="stylesheet" type="text/css" />
        <link href="<s:url value="/css/skin.css"/>" rel="stylesheet" type="text/css" />
        <script type="text/javascript" src="<s:url value="/js/jquery-1.6.4.min.js"/>"></script>

        <script type="text/javascript" src="<s:url value="/js/expand.js"/>"></script>
        <script>
            $(function() {
                $("#accordion").accordion();
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
                            <div class="my_account_bg">My Connection</div>
                            <div class="v_gallery">
                                <div class="w100 fl-l mart10">
                                    <div class="bradcum">
                                        <a href="<s:url value="/Welcome-Index.jsp"/>">Home</a>&nbsp;>&nbsp;My Connection
                                    </div>
                                    <s:url id="PSID" action="ShowSocialInfo" namespace="/MyConnection"/>
                                    <div class="gallery">
                                        <ul id="myeducationicon" class="jcarousel-skin-tango">
                                            <li><s:a href="%{PSID}"><img src="<s:url value="/icons/social-network.gif"/>" alt="Social Networking" /><span>Social Networking</span></s:a></li>
                                            <li><a href="<s:url value="/PageUnderConstruction.jsp"/>"><img src="<s:url value="/icons/chat.gif"/>" alt="Chat" /><span>Chat</span></a></li>
                                            <li><a href="<s:url value="/PageUnderConstruction.jsp"/>"><img src="<s:url value="/icons/blog.gif"/>" alt="Blog" /><span>Blog</span></a></li>
                                            <li><a href="<s:url value="http://14.139.40.226:8080/jamwiki-1.2.3/en/StartingPoints"/>" target="_blank"><img src="<s:url value="/icons/wiki.gif"/>" alt="Wiki" /><span>Wiki</span></a></li>
                                            <li><a href="<s:url value="/PageUnderConstruction.jsp"/>"><img src="<s:url value="/icons/communication.gif"/>" alt="Communication" /><span>Communication</span></a></li>
                                            <li><a href="<s:url value="http://14.139.40.226:8080/ePortfolioJforum/forums/list.page"/>" target="_blank"><img src="<s:url value="/icons/forum.gif"/>" alt="Discussion" /><span>Discussion</span></a></li>
                                        </ul>
                                    </div>
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