

 <%@page contentType="text/html" import="com.myapp.struts.utility.*" trimDirectiveWhitespaces="true" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<%
byte[] file=UserLog.getBytesFromFile(AppPath.getPropertiesFilePath()+"UserManual.pdf");
response.setContentType("application/pdf");
response.setContentLength(file.length);
response.getOutputStream().write(file, 0, file.length);
%>

