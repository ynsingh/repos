<%--  Design by Iqubal Ahmad
      Modified on 2011-02-02
     This jsp page is meant for UPloading Image From File to Particular JSP page.
     This jsp page is Second page in Process of Image UPload .
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="org.apache.struts.upload.FormFile,javax.imageio.ImageIO,java.io.*,javax.imageio.ImageReader" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<%
byte[] image = (byte[])session.getAttribute("image");
session.removeAttribute("image");
System.out.println("Image Length=");
response.reset();
response.setContentType("image/jpeg");
if (image!=null){
ServletOutputStream servletOutputStream = response.getOutputStream();
servletOutputStream.write(image);
servletOutputStream.flush();

}
%>