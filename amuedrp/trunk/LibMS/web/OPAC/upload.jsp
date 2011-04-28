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
if(image==null)
     {
    FormFile filename = (FormFile)session.getAttribute("filename");
    if (filename!=null)
        {
           image = (byte[])filename.getFileData();
           //session.removeAttribute("filename");
         }
    
     }
response.reset();
response.setContentType("image/jpeg");
if (image!=null)
    response.getOutputStream().write(image);

System.out.println("Image write karna hai yaha pay upload.jspHai");
System.out.println("Image Length="+image.length);
session.removeAttribute("image");
%>