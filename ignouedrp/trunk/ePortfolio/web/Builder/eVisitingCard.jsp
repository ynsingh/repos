<%-- 
    Document   : eVisitingCard
    Created on : Feb 7, 2012, 3:18:27 PM
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
        <title>Profile</title>
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
    <body><%
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
                        <s:include value="/Left-Nevigation.jsp"/> 
                        <!--Left box Ends Here-->

                        <!--Right box Starts Here-->
                        <div class="right_box">
                            <div class="my_account_bg">E-Visiting Card</div>
                            <div class="v_gallery">
                                <div class="w100 fl-l mart10">
                                    <div class="bradcum">
                                        <a href="<s:url value="/Welcome-Index.jsp"/>">Home</a>&nbsp;>&nbsp;<a href="<s:url value="/MyBuilder.jsp"/>">My Builder</a>&nbsp;> My eVisiting card
                                            <br/>
                                           
                                    </div>
                                            <div align="right" class="marr15">
                                                <a href="EditVisiCard?visitcardId=<s:property value="visitcardId"/>">Edit Visiting Card Info</a>
                                            </div>
                                <div class="w100 fl-l">
                                    <table width="70%" border="0" class="mar0a" cellpadding="0" cellspacing="10">
                                    <div class="w300p mar0a mart15">Click Any of the below Image to generate.</div>
                                        <div id="gallerywrap">
                                            <div id="gallery">
                                                <a href="ShowEvCard?fid=b1.jpg" target="previewarea"><img src="<s:url value="/images/card1.jpg"/>"/></a>
                                                <a href="ShowEvCard?fid=b2.jpg" target="previewarea"><img src="<s:url value="/images/card2.jpg"/>" /></a>
                                                <a href="ShowEvCard?fid=b3.jpg" target="previewarea"><img src="<s:url value="/images/card3.jpg"/>" /></a>
                                                <a href="ShowEvCard?fid=b4.jpg" target="previewarea"><img src="<s:url value="/images/card4.jpg"/>" /></a>
                                                <a href="ShowEvCard?fid=b5.jpg" target="previewarea"><img src="<s:url value="/images/card5.jpg"/>" /></a>
                                            </div>
                                            <div id="preview">
                                                <iframe name="previewarea" scrolling="no" frameborder="0" width="500" height="350"></iframe>
                                            </div><div id="links">                                                    
                                            </div>
                                        </div>
                                    </table>
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
