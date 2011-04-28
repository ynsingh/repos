<%--
      
     This jsp page is meant for UPloading Image From File to Particular JSP page.
     This jsp page is Second page in Process of Image UPload .
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="org.apache.struts.upload.FormFile" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<%
byte[] image = (byte[])session.getAttribute("image1");
session.removeAttribute("image1");
response.reset();
response.setContentType("image/jpeg");
if (image!=null){
response.getOutputStream().write(image);

System.out.println("Image Length="+image.length);}
%>