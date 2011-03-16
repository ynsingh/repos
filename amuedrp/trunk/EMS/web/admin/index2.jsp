<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>


<%
try{
if(session.getAttribute("institute_id")!=null){
System.out.println("institute_id"+session.getAttribute("institute_id"));
}
else{
    request.setAttribute("msg", "Your Session Expired: Please Login Again");
    %><script>parent.location = "<%=request.getContextPath()%>"+"/login.jsp?session=\"expired\"";</script><%
    }
}catch(Exception e){
    request.setAttribute("msg", "Your Session Expired: Please Login Again");
    %>sessionout();<%
    }

%>

<%
String id=request.getParameter("id");
String institute_id=(String)session.getAttribute("institute_id");
if(institute_id!=null){

%>
<jsp:forward page="view2.do">
    <jsp:param name="staff_id" value="<%=id%>"/>
</jsp:forward>

<%}else{
request.setAttribute("msg", "Your Session Expired: Please Login Again");
    %><script>parent.location = "<%=request.getContextPath()%>"+"/login.jsp?session=\"expired\"";</script><%
}%>