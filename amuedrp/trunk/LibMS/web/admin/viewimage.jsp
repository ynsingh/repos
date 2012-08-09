 <%@page contentType="text/html" import="com.myapp.struts.AdminDAO.AdminRegistrationDAO,com.myapp.struts.hbm.*,java.io.*,com.myapp.struts.utility.*" trimDirectiveWhitespaces="true" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<%
AdminRegistration admin=AdminRegistrationDAO.searchInstitute((String)session.getAttribute("library_id"));

byte[] bytes=UserLog.getBytesFromFile(AppPath.getProjectPropertiesImagePath()+admin.getInstiLogo());
if(bytes==null)
{
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
System.out.println(admin.getInstiLogo()+" "+bytes);
if(bytes!=null){
response.setContentType("image/jpeg");
response.getOutputStream().write(bytes);
response.getOutputStream().flush();
}
%>

