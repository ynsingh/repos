<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%
String id=request.getParameter("id");


%>
<jsp:forward page="view2.do">
    <jsp:param name="staff_id" value="<%=id%>"/>
</jsp:forward>