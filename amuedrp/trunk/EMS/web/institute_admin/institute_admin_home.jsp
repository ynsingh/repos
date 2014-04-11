
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page contentType="text/html"%>

<%@page pageEncoding="UTF-8"%>
<%
if(session.isNew()){
%>
<script>parent.location="<%=request.getContextPath()%>/login.jsp";</script>
<%}%>
<%@page import="com.myapp.struts.hbm.*,java.util.*" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%
String role=(String)session.getAttribute("login_role");

if(role.equalsIgnoreCase("insti-admin")|| role.equalsIgnoreCase("insti-admin,voter"))
   {
%>
<jsp:include page="/institute_admin/adminheader.jsp"/>
<%}else{
    %>
    <script>parent.location="<%=request.getContextPath()%>/login.jsp";</script>
<%
}%>
<%--<jsp:include page="adminheader.jsp"></jsp:include>--%>
 <%
String msg=(String)request.getAttribute("msg");
if(msg!=null){

out.println(msg);
}%>
<IFRAME  name="f3" src="/EMS/view_managers.do" frameborder=1 scrolling="yes" width="100%" height="700px" style="color:deepskyblue;visibility:true;" id="f3" ></IFRAME>
