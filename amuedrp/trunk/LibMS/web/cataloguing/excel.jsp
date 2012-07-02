

 <%@page contentType="text/html" import="com.myapp.struts.utility.*" trimDirectiveWhitespaces="true" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<%

String file1=(String)session.getAttribute("filename");
String type=(String)session.getAttribute("type");
try{
if(file1!=null){
byte[] file=UserLog.getBytesFromFile(AppPath.getPropertiesFilePath()+file1);
if(type!=null)
    response.setContentType("application/text");
else
response.setContentType("application/excel");
response.setContentLength(file.length);
response.getOutputStream().write(file, 0, file.length);
}
}catch(Exception e)
{
}
session.removeAttribute("filename");
session.removeAttribute("type");
%>

