<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@page import="java.sql.*,com.myapp.struts.MyQueryResult" %>

<%
String id=request.getParameter("id");
session.setAttribute("reg", id);

%>
<jsp:forward page="view5.do">
    <jsp:param name="id" value="<%=id%>"/>
</jsp:forward>