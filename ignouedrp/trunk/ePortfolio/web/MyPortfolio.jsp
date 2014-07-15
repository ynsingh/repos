<%-- 
    Document   : MyPortfolio
    Created on : Apr 17, 2012, 1:17:46 PM
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
        <title>My Portfolio</title>
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
                            <div class="my_account_bg">My Portfolio</div>
                            <s:url id="RefID" action="ShowReference" namespace="/MyProfile"></s:url>                    
                            <% if (role.contains("faculty")) {%>     
                            <s:url id="TestiReqID" action="StdTestiReq" namespace="/MyProfile"/>
                            <% }
                                if (role.contains("student")) {%>
                            <s:url id="TestiReqID" action="TestimonialSent" namespace="/MyProfile"/>
                            <% }%> 
                            <div class="v_gallery">
                                <div class="w100 fl-l mart10">
                                    <div class="bradcum">
                                        <a href="<s:url value="/Welcome-Index.jsp"/>">Home</a>&nbsp;>&nbsp;My Portfolio
                                    </div>
                                    <% if (role.contains("faculty")) {%>     
                                    <s:url id="TestiID" action="StdReqTesti" namespace="/MyProfile"/>
                                    <% } else if (role.contains("student")) {%>
                                    <s:url id="TestiID" action="STestimonialList" namespace="/MyProfile"/>
                                    <% }%> 
                                    <div class="gallery">
                                        <ul id="myportfolioicon" class="jcarousel-skin-tango">
                                            <li><a href="<s:url value="/MyProfile/MyProfile.jsp"/>"><img src="<s:url value="/icons/my-profile.gif"/>" alt="My Profile" /><span>My Profile</span></a></li>
                                            <li><s:a href="%{TestiReqID}"><img src="<s:url value="/icons/testmonials.gif"/>" alt="Testimonials" /><span>Testimonials</span></s:a></li>
                                            <li><s:a href="%{RefID}"><img src="<s:url value="/icons/references.gif"/>" alt="References" /><span>References</span></s:a></li>
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