<%--  Design by Iqubal Ahmad
      Modified on 2011-02-02
     This jsp page is meant for upload image(ie view image) from file to jsp when view Member Detail activity is performed.

--%>

 <%@page contentType="text/html" import="java.util.*,java.io.*,java.sql.*,com.myapp.struts.hbm.*,com.myapp.struts.utility.*"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<%
if(session.isNew()){
%>
<script>parent.location="<%=request.getContextPath()%>/login.jsp";</script>
<%}%>
<%
VoterRegistration voter  =(VoterRegistration)session.getAttribute("voter");
System.out.println(voter.getImage()+"  ......................Callit");
byte[] bytes=null;
if(voter.getImage()!=null)
{
    bytes=UserLog.getBytesFromFile(AppPath.getProjectPropertiesImagePath()+voter.getImage());
    /* try{
        File file=new File(application.getRealPath("images")+"/"+voter.getImage());
        FileInputStream fis=new FileInputStream(file);
        int filesize=fis.available();
        bytes=new byte[filesize];
        fis.read(bytes);
       }catch(Exception e){
        e.printStackTrace();
       }*/
    if(bytes!=null){
response.setContentType("image/jpeg");
response.getOutputStream().write(bytes);
response.getOutputStream().flush();
session.removeAttribute("voter");
}
    }
else
{
     bytes=UserLog.getBytesFromFile(AppPath.getProjectImagePath()+"no-image.jpg");

   /* try{
         FileInputStream fis = new FileInputStream(file);
         BufferedInputStream bis = new BufferedInputStream(fis);
         bytes = new byte[bis.available()];
         bis.read(bytes);


    }catch(IOException e){
            System.out.println("image view Error:"+e);
      }*/
}
  session.removeAttribute("voter");
response.setContentType("image/jpeg");
ServletOutputStream servletOutputStream =response.getOutputStream();
servletOutputStream.write(bytes);
servletOutputStream.flush();
%>

