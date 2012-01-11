<%--  Design by Iqubal Ahmad
      Modified on 2011-02-02
     This jsp page is meant for UPloading Image From File to Particular JSP page.
     This jsp page is Second page in Process of Image UPload .
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="org.apache.struts.upload.FormFile,javax.imageio.ImageIO,java.io.*,javax.imageio.ImageReader" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<%
byte[] image = (byte[])session.getAttribute("image1");
session.removeAttribute("image1");
response.reset();
response.setContentType("image/jpeg");
if (image!=null){
response.getOutputStream().write(image);

System.out.println("Image Length="+image.length);
}
/*else{
    File file = new File(application.getRealPath("images")+"/no-image.jpg");

      if(!file.exists()){

      }
    try{
         FileInputStream fis = new FileInputStream(file);
         BufferedInputStream bis = new BufferedInputStream(fis);
         byte[] bytes = new byte[bis.available()];
         response.setContentType("image/jpeg");
          bis.read(bytes);
    response.getOutputStream().write(bytes);
    }catch(IOException e){

      }
    }*/
%>