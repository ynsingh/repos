

<%--  Design by Iqubal Ahmad
      Modified on 2011-02-02
     This jsp page is meant for upload image(ie view image) from file to jsp when view Member Detail is performed on Grid.
     
--%>
 <%@page contentType="text/html" import="java.util.*,java.io.*,java.sql.*,com.myapp.struts.hbm.*"%>
<%@page import="org.apache.struts.upload.FormFile" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<%
CirRequestfromOpac opaclist = (CirRequestfromOpac)session.getAttribute("opacimage");




byte[] bytes=null;
//response.reset();
//response.setContentType("image/jpeg");
if (opaclist.getImage()!=null)
  {  bytes=(byte[])opaclist.getImage();
//response.getOutputStream().write(bytes);
//System.out.println("Image Length="+bytes.length);
}else{

 File file = new File(application.getRealPath("images")+"/no-image.jpg");


    try{
         FileInputStream fis = new FileInputStream(file);
         BufferedInputStream bis = new BufferedInputStream(fis);
         bytes = new byte[bis.available()];
         bis.read(bytes);


    }catch(IOException e){
            System.out.println("image view Error:"+e);
      }
}
response.setContentType("image/jpeg");
System.out.println("bytes11="+bytes.length);
ServletOutputStream servletOutputStream = response.getOutputStream();
servletOutputStream.write(bytes);
servletOutputStream.flush();


%>