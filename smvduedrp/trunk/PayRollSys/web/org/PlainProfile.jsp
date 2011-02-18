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
        <img align="center" alt="Logo Image"  src="org/uploadimg/logo.gif" width="150px" height="150px"/><br>
    <%
    Org org = new Org().getProfile();
    if(org!=null)
        {
            out.println(" <h1><i>"+org.getTagLine()+"</i> </h1> <br>");
            out.println("<hr align='center'/>");

            out.println("Location : "+org.getAddress1()+" ");
            out.println(""+org.getAddress2()+" <br>");
            out.println(" Phone : "+org.getPhone()+" <br>");
            out.println("Website :   <a href='http://www."+org.getWeb()+"' target='_new'>"+org.getWeb()+"</a> <br>");
            out.println("<b> E-Mail :  "+org.getEmail()+"</b><br>");
        }
%>

<hr align="center" />



        
    </body>
</html>
