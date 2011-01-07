<%-- 
    Document   : ReservationDetails.jsp
    Created on : july 21, 2010, 8:08:01 AM
    Author     : Mayank Saxena
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page errorPage = "ErrorPage.jsp" language="java"%>
<%@page import="java.sql.*"%>
<%@ page import="java.util.*, java.lang.*"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>ReservationDetails.jsp</title>
    </head>
    <body>
        <h1>Reservation Details</h1>

<%!
String ID,accno,date,title,callno,author,status,vol,ed,name;
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
    <td><font color="#FFFFFF"><b><font size="2" face="Arial, Helvetica,sans-serif">Vol. Ed.</font></b></font></td>
    <td><font color="#FFFFFF"><b><font size="2" face="Arial, Helvetica,sans-serif">Request&nbsp;Date</font></b></font></td>
    <td><font color="#FFFFFF"><b><font size="2" face="Arial, Helvetica,sans-serif">Status</font></b></font></td>
 </tr>

 <%
  
       rs=(ResultSet)session.getAttribute("reservationdetails_resultset");
       int i=0;
    while(rs.next())
      {
             title=rs.getString(3);
             author=rs.getString(5);
             callno=rs.getString(7);
             date=rs.getString(12);
             vol=rs.getString(9);
             ed=rs.getString(8);
             status=rs.getString(13);
 %>
  <tr>
    <td><font size="2" face="Arial, Helvetica, sans-serif"><%=title%></font></td>
    <td><font size="2" face="Arial, Helvetica, sans-serif"><%=author%></font></td>
    <td><font size="2" face="Arial, Helvetica, sans-serif"><%=callno%></font></td>
    <td><font size="2" face="Arial, Helvetica, sans-serif"><%=vol%>,&nbsp;<%=ed%></font></td>
    <td><font size="2" face="Arial, Helvetica, sans-serif"><%=date%></font></td>
    <td><font size="2" face="Arial, Helvetica, sans-serif"><%=status%></font></td>
  </tr>
  <%
     i++; }
       if(i<=0){%>
       <font size="3" color="teal" align="center">
           <%
              out.println("No Records Found. <br>");
               }
       else { out.println(i+" Records Found. <br>");}
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

