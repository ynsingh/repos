

<%--  Design by Iqubal Ahmad
      Modified on 2011-02-02
     This jsp page is meant for upload image(ie view image) from file to jsp when view Member Detail is performed on Grid.
     
--%>
<%@page contentType="text/html" pageEncoding="UTF-8" import="java.util.*,com.myapp.struts.hbm.*"%>
<%@page import="org.apache.struts.upload.FormFile" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<%
CirRequestfromOpac opaclist = (CirRequestfromOpac)session.getAttribute("opacimage");



//List cirrequestfromopac = (List)session.getAttribute("cirrequestfromopac");

//Iterator it = cirrequestfromopac.iterator();
//int i=0;
//while(it.hasNext())
  //  {
 //CirRequestfromOpac opaclist = (CirRequestfromOpac)cirrequestfromopac.get(i);
//if (opaclist.getMemId().equalsIgnoreCase(memid))
  //  {
byte[] image = (byte[])opaclist.getImage();
response.reset();
response.setContentType("image/jpeg");
if (image!=null)
    response.getOutputStream().write(image);
System.out.println("Image Length="+image.length);
//}
 //i++;
 //it.next();
// }
%>