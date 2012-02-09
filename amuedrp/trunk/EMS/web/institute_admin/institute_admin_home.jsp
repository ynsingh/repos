
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page contentType="text/html"%>

<%@page pageEncoding="UTF-8"%>
<%@page import="com.myapp.struts.hbm.*,java.util.*" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>

<jsp:include page="adminheader.jsp"></jsp:include>
 <%
String msg=(String)request.getAttribute("msg");
if(msg!=null){

out.println(msg);
}%>
                      <IFRAME  name="f3" src="/EMS/view_managers.do" frameborder=0 scrolling="no" width="100%" style="color:deepskyblue;height:100%;visibility:true;" id="f3" ></IFRAME>
