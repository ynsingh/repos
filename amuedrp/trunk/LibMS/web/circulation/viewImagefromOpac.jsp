 <%@page contentType="text/html" import="java.util.*,java.io.*,java.sql.*,com.myapp.struts.hbm.*,com.myapp.struts.utility.*"%>
<%@page import="org.apache.struts.upload.FormFile" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<%
CirRequestfromOpac opaclist = (CirRequestfromOpac)session.getAttribute("opacimage");



byte[] image = (byte[])opaclist.getImage();
System.out.println("Image Length=1111111111");
response.reset();
response.setContentType("image/jpeg");
if (image!=null){
ServletOutputStream servletOutputStream = response.getOutputStream();
servletOutputStream.write(image);
servletOutputStream.flush();

%>