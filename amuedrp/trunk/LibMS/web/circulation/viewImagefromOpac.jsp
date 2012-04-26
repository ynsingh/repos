 <%@page contentType="text/html" import="java.util.*,java.io.*,java.sql.*,com.myapp.struts.hbm.*,com.myapp.struts.utility.*"%>
<%@page import="org.apache.struts.upload.FormFile" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<%
CirRequestfromOpac opaclist = (CirRequestfromOpac)session.getAttribute("opacimage");




byte[] bytes=null;

//Read Image from Images Folder

if(opaclist.getImage()!=null){
bytes = UserLog.getBytesFromFile(AppPath.getProjectImagePath()+opaclist.getImage());
}
if(bytes==null)
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