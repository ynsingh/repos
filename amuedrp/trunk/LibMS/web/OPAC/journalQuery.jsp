
  
<%--
    Document   : JournalQuery.jsp
    Created on : Jun 22, 2010, 1:15:37 PM
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
<title>Journals...</title>
<style type="text/css">
body
{
   background-color: #FFFFFF;
   color: #000000;
}
</style>
</head>

<%!
   String phrase,title,author,accno,publ,place,loc,callno;
   String db,sort,field,query="select * from document where category='journal'";
   Connection conn= null;
   PreparedStatement stmt=null;
   ResultSet rs=null;
%>
   <jsp:forward page="journal.jsp" >
          <jsp:param name="QUERY" value="<%=query%>"/>
   </jsp:forward>
</body>
</html>