<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%
String id=request.getParameter("id");


%>
<jsp:forward page="block_library.do">
    <jsp:param name="id" value="<%=id%>"/>
</jsp:forward>