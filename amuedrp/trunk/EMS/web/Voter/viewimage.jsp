<%--  Design by Iqubal Ahmad
      Modified on 2011-02-02
     This jsp page is meant for upload image(ie view image) from file to jsp when view Member Detail activity is performed.
     
--%>

 <%@page contentType="text/html" import="java.util.*,java.io.*,java.sql.*,com.myapp.struts.hbm.*"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<%
VoterRegistration voter  =(VoterRegistration)session.getAttribute("voter");
System.out.println(voter.getImage()+"  ......................");
byte[] bytes=null;
if(voter.getImage()!=null)
{
bytes = voter.getImage();
System.out.println("Image Length1="+voter.getImage().length);
}
else
{
    File file = new File(application.getRealPath("image")+"/no-image.jpg");

      
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
ServletOutputStream servletOutputStream=null;
response.flushBuffer();
{
servletOutputStream =response.getOutputStream();
servletOutputStream.write(bytes);
servletOutputStream.flush();
}

%>

