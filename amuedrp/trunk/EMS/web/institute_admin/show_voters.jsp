<%-- 
    Document   : show_voters
    Created on : Sep 6, 2011, 5:36:58 PM
    Author     : faraz
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
if(session.isNew()){
%>
<script>parent.location="<%=request.getContextPath()%>/login.jsp";</script>
<%}%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
    <%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <table>
        <logic:iterate id="VoterRegistration" name="voterreg">
                <tr>
                    <td><bean:write name="VoterRegistration" property=""/></td>
                    <td><bean:write name="VoterRegistration" property=""/></td>
                    <td><bean:write name="VoterRegistration" property=""/></td>
                    <td><bean:write name="VoterRegistration" property=""/></td>
                </tr>
           </logic:iterate>
    </table>
        </body>
</html>
