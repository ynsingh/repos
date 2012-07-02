

 <%@page contentType="text/html" import="java.util.*,java.io.*,java.sql.*,com.myapp.struts.hbm.*" trimDirectiveWhitespaces="true" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<%
ServletOutputStream servletOutputStream=null;

AdminRegistration cirmemdetail  =(AdminRegistration)session.getAttribute("AdminDetail");

byte[] bytes=null;
if(cirmemdetail.getInstiLogo()!=null)
{
bytes = cirmemdetail.getInstiLogo();

}
response.setContentType("image/jpeg");
servletOutputStream = response.getOutputStream();
servletOutputStream.write(bytes);


servletOutputStream.flush();
%>

