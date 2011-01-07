<%-- 
    Document   : Location.jsp
    Created on : Jun 5, 2010, 6:04:16 PM
    Author     : Mayank Saxena
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.sql.*" %>
<%@ page import="java.io.*"   %> 
<%@ page import="java.util.*" %>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="Mayank Saxena" content="MCA,AMU">
<title>Locations in Library.....</title>
<style type="text/css">
body
{
   background-color: #FFFFFF;
   color: #000000;
}
</style>
<%!
    Locale locale=null;
    String locale1="en";
    String rtl="ltr";
    boolean page=true;
%>
<%
try{
locale1=(String)session.getAttribute("locale");
    if(session.getAttribute("locale")!=null)
    {
        locale1 = (String)session.getAttribute("locale");
        System.out.println("locale="+locale1);
    }
    else locale1="en";
}catch(Exception e){locale1="en";}
     locale = new Locale(locale1);
    if(!(locale1.equals("ur")||locale1.equals("ar"))){ rtl="LTR";page=true;}
    else{ rtl="RTL";page=false;}
    ResourceBundle resource = ResourceBundle.getBundle("multiLingualBundle", locale);

    %>



</head>


<%!
   
   ResultSet rs=null;
%>
     <%if(page.equals(true)){%>
<%

try
{ /*Create string of connection url within specified
    format with machine name, port number and database name.
    Here machine name id localhost and
    database name is library.*/

 
%>

<br><br>


<table border="1" align="left" width="40%" cellpadding="0" cellspacing="0" bordercolorlight="#c0003b" bordercolordark="#FFFFFF">
  <tr bgcolor="#c0003b">
    <td><font color="#FFFFFF"><b><font size="2" face="Arial, Helvetica,sans-serif">&nbsp;&nbsp;Locations in Library</font></b></font></td>
  </tr>

 <%
      String loc="";
       rs=(ResultSet)request.getAttribute("locationRs");
    rs.beforeFirst();
       while(rs.next())
      {
        loc=rs.getString(1);
 %>
  <tr>
      <td><font size="2" face="Arial, Helvetica, sans-serif">&nbsp;&nbsp;&nbsp;<%=loc%></font></td>
  </tr>
  <%
      }
}catch(Exception ex){
%>
</table>
<font size="3" color="teal">
      <%
        {
         out.println("No Records Found. <br>");
        }
                    }
      %>
</font>

<%}else{%>

<%

try
{ /*Create string of connection url within specified
    format with machine name, port number and database name.
    Here machine name id localhost and
    database name is library.*/


%>

<br><br>


<table border="1" align="right" width="40%" cellpadding="0" cellspacing="0" bordercolorlight="#c0003b" bordercolordark="#FFFFFF">
  <tr bgcolor="#c0003b">
    <td><font color="#FFFFFF"><b><font size="2" face="Arial, Helvetica,sans-serif">&nbsp;&nbsp;Locations in Library</font></b></font></td>
  </tr>

 <%
      String loc="";
       rs=(ResultSet)request.getAttribute("locationRs");
    rs.beforeFirst();
       while(rs.next())
      {
        loc=rs.getString(1);
 %>
  <tr>
      <td><font size="2" face="Arial, Helvetica, sans-serif">&nbsp;&nbsp;&nbsp;<%=loc%></font></td>
  </tr>
  <%
      }
}catch(Exception ex){
%>
</table>
<font size="3" color="teal">
      <%
        {
         out.println("No Records Found. <br>");
        }
                    }
      %>
</font>




<%}%>


</body>
</html>

