<%-- 
    Document   : FineDetails.jsp
    Created on : july 21, 2010, 8:07:42 AM
    Author     : 
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.sql.*"%>
<%@ page import="java.util.*, java.lang.*"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>FineDetails.jsp</title>
    </head> 
    <body>
        <h1>Fine Details</h1>

<%!
String ID,accno,date,title,callno,author,publ,place,fine,name;
ResultSet rs=null;
%>

<%try
{
  
  
  %>
<br><br>
<table border="1" align="center" width="75%" cellpadding="0" cellspacing="0" bordercolorlight="#c0003b" bordercolordark="#FFFFFF">
  <tr bgcolor="#c0003b">
    <td><font color="#FFFFFF"><b><font size="2" face="Arial, Helvetica,sans-serif">Title</font></b></font></td>
    <td><font color="#FFFFFF"><b><font size="2" face="Arial, Helvetica,sans-serif">Author</font></b></font></td>
    <td><font color="#FFFFFF"><b><font size="2" face="Arial, Helvetica,sans-serif">Call&nbsp;No.</font></b></font></td>
    <td><font color="#FFFFFF"><b><font size="2" face="Arial, Helvetica,sans-serif">Publisher</font></b></font></td>
    <td><font color="#FFFFFF"><b><font size="2" face="Arial, Helvetica,sans-serif">Fine(Rs.)</font></b></font></td>
    <td><font color="#FFFFFF"><b><font size="2" face="Arial, Helvetica,sans-serif">Due&nbsp;Date</font></b></font></td>
 </tr>

 <%
  
       rs= (ResultSet)session.getAttribute("finedetails_resultset");
       int i=0;
    while(rs.next())
      {
        title=rs.getString(3);
        author=rs.getString(4);
        callno=rs.getString(5);
        publ=rs.getString(6);
        date=rs.getString(2);        
        fine=rs.getString(1);
 %>
  <tr>
    <td><font size="2" face="Arial, Helvetica, sans-serif"><%=title%></font></td>
    <td><font size="2" face="Arial, Helvetica, sans-serif"><%=author%></font></td>
    <td><font size="2" face="Arial, Helvetica, sans-serif"><%=callno%></font></td>
    <td><font size="2" face="Arial, Helvetica, sans-serif"><%=publ%>,&nbsp;<%=place%></font></td>
    <td><font size="2" face="Arial, Helvetica, sans-serif"><%=fine%></font></td>
    <td><font size="2" face="Arial, Helvetica, sans-serif"><%=date%></font></td>
  </tr>
  <font size="3" face="Arial, Helvetica, sans-serif" color="teal">
  <%
     i++; }
       if(i<=0){
              out.println("<align=center>No Records Found. <br>");
               }
       else { out.println("<align=center>"+i+" Records Found. <br>");}
           %>
  </font>
<%
}catch(Exception ex){
%>
</table>

      <%        out.println("Exception "+ex);
                    }

      %>
</body>
</html>
