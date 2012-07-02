<%-- 
    Document   : upload
    Created on : Feb 2, 2011, 4:49:46 PM
    Author     : faraz
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="org.apache.struts.upload.FormFile" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<%
byte[] image = (byte[])session.getAttribute("image");
System.out.println("Image Length=");
response.reset();
response.setContentType("image/jpeg");
if (image!=null){
ServletOutputStream servletOutputStream = response.getOutputStream();
servletOutputStream.write(image);
servletOutputStream.flush();
}
%>