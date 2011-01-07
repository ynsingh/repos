<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%
String id=request.getParameter("id");

out.println(id);
%>
<jsp:forward page="view3.do">
    <jsp:param name="id" value="<%=id%>"/>
</jsp:forward>