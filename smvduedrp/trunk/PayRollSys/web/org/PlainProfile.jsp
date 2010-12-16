<%-- 
    Document   : PlainProfile
    Created on : Nov 22, 2010, 4:00:16 AM
    Author     : Algox
--%>

<%@page import="org.smvdu.payroll.beans.Org"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <img align="center" alt="Logo Image"  src="org/uploadimg/logo.gif" width="100px" height="100px"/><br>
    <%
    Org org = new Org().getProfile();
    if(org!=null)
        {
            out.println(" <h1><i>"+org.tagLine+"</i> </h1> <br>");
            out.println("<hr align='center' width'300px'/>");

            out.println("Location : "+org.address1+" ");
            out.println(""+org.address2+" <br>");
            out.println(" Phone : "+org.phone+" <br>");
            out.println("Website :   <a href='http://www."+org.web+"' target='_new'>"+org.web+"</a> <br>");
            out.println("<b> E-Mail :  "+org.email+"</b><br>");
        }
%>

<hr align="center" width="300px"/>

<input type="button" value="Update Details" onclick="window.location='org/Profile.jsp'"/>

        
    </body>
</html>
