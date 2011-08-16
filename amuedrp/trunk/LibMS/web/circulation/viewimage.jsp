<%--  Design by Iqubal Ahmad
      Modified on 2011-02-02
     This jsp page is meant for upload image(ie view image) from file to jsp when view Member Detail activity is performed.
     
--%>

 <%@page contentType="text/html" import="java.util.*,java.io.*,java.sql.*,com.myapp.struts.hbm.*"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<%
CirMemberDetail cirmemdetail  =(CirMemberDetail)session.getAttribute("cirmemdetail");

byte[] bytes=null;
if(cirmemdetail.getImage()!=null)
{
bytes = cirmemdetail.getImage();
//System.out.println("Image Length1="+cirmemdetail.getImage().length);
}
else
{
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

