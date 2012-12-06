<%-- 
    Document   : ShowEvisitingCard
    Created on : Feb 15, 2012, 2:57:49 PM
    Author     : IGNOU Team
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>

<%
    if (session.getAttribute("user_id") == null) {
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
