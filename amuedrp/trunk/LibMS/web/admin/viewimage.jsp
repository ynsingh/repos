

<%@page contentType="text/html" pageEncoding="UTF-8"%>
 <%@page contentType="text/html" import="java.util.*,java.io.*,java.sql.*,com.myapp.struts.hbm.*"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<%
StaffDetail staff  =(StaffDetail)session.getAttribute("updateresultset");
response.reset();
response.setContentType("image/jpeg");
response.getOutputStream().write(staff.getStaffImage());

System.out.println("Image Length1="+staff.getStaffImage().length);

%>

