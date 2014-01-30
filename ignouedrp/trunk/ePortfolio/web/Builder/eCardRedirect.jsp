<%-- 
    Document   : eCardRedirect
    Created on : Feb 16, 2012, 2:40:31 PM
    Author     : IGNOU Team
--%>

<%@page import="java.io.Serializable"%>
<%@page import="java.util.Date"%>
<%@page import="org.apache.log4j.Logger"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%><%
            final Logger logger = Logger.getLogger(this.getClass());
            String ipAddress = request.getRemoteAddr();
            logger.warn(session.getAttribute("user_id") + " Accessed from: " + ipAddress + " at: " + new Date());
            String role = session.getAttribute("role").toString();
            if (session.getAttribute("user_id") == null) {
                session.invalidate();
                response.sendRedirect("../Login.jsp");
            }
        %>
<table border="0" class="mar0a" cellpadding="0" cellspacing="10">
    <td>
    <center><font face="Arial" size="+1">Please fill your  Personal , Contact  and 
        Employment Information in My profile section to generate E-visiting card.</font></center>
</td>
</table>
