<%--  Design by Iqubal Ahmad
      Modified on 2011-02-02
     This jsp page is meant for upload image(ie view image) from file to jsp when view Member Detail activity is performed.
     
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
 <%@page contentType="text/html" import="java.util.*,java.io.*,java.sql.*,com.myapp.struts.hbm.*"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<%
CirMemberDetail cirmemdetail  =(CirMemberDetail)session.getAttribute("cirmemdetail");
response.reset();
response.setContentType("image/jpeg");
response.getOutputStream().write(cirmemdetail.getImage());

System.out.println("Image Length1="+cirmemdetail.getImage().length);

%>

