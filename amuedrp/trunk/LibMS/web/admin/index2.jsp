<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%
String id=(String)request.getParameter("id");


%>
<jsp:forward page="view2.do">
    <jsp:param name="id" value="<%=id%>"/>
</jsp:forward>