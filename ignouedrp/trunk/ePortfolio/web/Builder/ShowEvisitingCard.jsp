<%-- 
    Document   : ShowEvisitingCard
    Created on : Feb 15, 2012, 2:57:49 PM
    Author     : IGNOU Team
--%>

<%@page import="java.io.Serializable"%>
<%@page import="java.util.Date"%>
<%@page import="org.apache.log4j.Logger"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %><%
            final Logger logger = Logger.getLogger(this.getClass());
            String ipAddress = request.getRemoteAddr();
            logger.warn(session.getAttribute("user_id") + " Accessed from: " + ipAddress + " at: " + new Date());
            String role = session.getAttribute("role").toString();
            if (session.getAttribute("user_id") == null) {
                session.invalidate();
                response.sendRedirect("../Login.jsp");
            }
        %>
<%
    String ecardpath = session.getAttribute("Ecard").toString();
%>
<table border="0" class="mar0a" cellpadding="0" cellspacing="10">
    <tr>
        <td width="500" height="261" valign="top">
            <img src="../images/<%=ecardpath%>"/>
        </td>
    </tr>
    <tr>
        <td><a href="../images/<%=ecardpath%>" target="_blank">Download E-visiting Card</a></td>
    </tr>
</table>
