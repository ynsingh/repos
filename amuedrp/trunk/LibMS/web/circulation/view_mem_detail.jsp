 <%@page  import="java.util.*,java.io.*,java.sql.*,com.myapp.struts.hbm.*"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<%
CirMemberDetail cirmemdetail  =(CirMemberDetail)session.getAttribute("cirmemberdetail");

byte[] bytes=null;
if(cirmemdetail.getImage()!=null)
{
bytes = cirmemdetail.getImage();
System.out.println("Cir Image Length="+cirmemdetail.getImage());
}

ServletOutputStream servletOutputStream = response.getOutputStream();
servletOutputStream.write(bytes);
servletOutputStream.flush();


%>

