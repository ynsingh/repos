<%-- 
    Document   : eCardRedirect
    Created on : Feb 16, 2012, 2:40:31 PM
    Author     : IGNOU Team
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    if (session.getAttribute("user_id") == null) {
        response.sendRedirect("../Login.jsp");
    }

%>
<table border="0" class="mar0a" cellpadding="0" cellspacing="10">
    <td>
    <center><font face="Arial" size="+1">Please fill your  Personal , Contact  and 
        Employment Information in My profile section to generate E-visiting card.</font></center>
</td>
</table>
