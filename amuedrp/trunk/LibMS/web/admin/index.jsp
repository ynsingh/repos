<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%
String id=request.getParameter("id");


%>
<jsp:forward page="view.do">
    <jsp:param name="id" value="<%=id%>"/>
</jsp:forward>