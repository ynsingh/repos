 <%@page contentType="text/html" import="java.io.*,com.myapp.struts.AdminDAO.AdminRegistrationDAO,java.util.*,com.myapp.struts.hbm.*,com.myapp.struts.utility.*" trimDirectiveWhitespaces="true" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<%


byte[] bytes=UserLog.getBytesFromFile(AppPath.getProjectPropertiesImagePath()+request.getParameter("x"));

if(bytes==null){
    File file = new File(AppPath.getProjectImagePath()+"no-image.jpg");


    try{
         FileInputStream fis = new FileInputStream(file);
         BufferedInputStream bis = new BufferedInputStream(fis);
         bytes = new byte[bis.available()];
         bis.read(bytes);


    }catch(IOException e){
            System.out.println("image view Error:"+e);
      }


}
if(bytes!=null){
    response.setContentType( "application/octet-stream" );
 /*   String k=(String)request.getParameter("x");

    if(k.endsWith("docx"))
    {
        response.setContentType("txt/doc");
     }else if(k.endsWith("mp4")){
     
     }else{
response.setContentType("image/jpeg");
}*/
response.getOutputStream().write(bytes);
response.getOutputStream().flush();
}
%>

