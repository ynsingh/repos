<%-- 
    Document   : Footer
    Created on : Oct 10, 2011, 11:30:05 AM
Author     : IGNOU Team
Version      : 1
--%>

<%@page import="org.eclipse.persistence.internal.xr.Invocation"%>
<%@page import="com.opensymphony.xwork2.ActionContext"%>
<%@page import="org.apache.struts2.ServletActionContext"%>
<%@page import="java.util.StringTokenizer"%>
<%@taglib prefix="s" uri="/struts-tags" %>


<!--Footer Section Starts Here-->
<div class="footer">
    <div class="f_menu"> 

        <%
            session.putValue("requri", request.getRequestURI());
            out.println(request.getRequestURI());
        %>
        <a href="<s:url value="/About.jsp"/>" target="_Blank">About</a> | <a href="<s:url value="/Feedback.jsp"/>" target="_Blank">Feedback</a> | <a href="<s:url value="/Help.jsp"/>" target="_Blank">Help</a> | <a href="#">Sitemap</a> | <a href="<s:url value="/Contact.jsp"/>" target="_Blank">Contact Us</a>

    </div>
</div>
<div class="footer_panel">
    <div class="footer_txt">
        <div class="wau fl-l tl">&COPY; 2011-12, MHRD. All Rights are Reserved</div>
        <div class="wau fl-r tr">Designed and Developed by eGyanKosh,Indira Gandhi National Open University</div>
    </div>
    <!--Footer Section Ends Here-->
</div>