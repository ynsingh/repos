<%-- 
    Document   : feedbackData
    Created on : Jun 29, 2010, 5:43:57 PM
    Author     : Mayank Saxena
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page errorPage = "ErrorPage.jsp" language="java" import="java.sql.*"%>
<%@ page import="java.util.*"%>

<%
String name="", email="", comments="",cardno="", date="" ;
Connection conn=null;
Calendar cal = new GregorianCalendar();
    int month = cal.get(Calendar.MONTH);
    int year = cal.get(Calendar.YEAR);
    int day = cal.get(Calendar.DAY_OF_MONTH);
    date=day+ "/"+ (month+1) + "/"+year;
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
       <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="Mayank Saxena" content="MCA,AMU">
        <title>FeedBack Page</title>
    </head>
    <body>


<%
//Retrieving the values of Feedback form in variables.
cardno=request.getParameter("cardno");
name=request.getParameter("name");
email=request.getParameter("email");
comments=request.getParameter("comments");
//out.println(cardno+name+email+comments+date);
   /* Create string of connection url within specified
   format with machine name,
    port number and database name. Here machine name id
    localhost and database name is library. */
    String connectionURL = "jdbc:mysql://localhost:3307/library";
    // Load JBBC driver "com.mysql.jdbc.Driver"
     Class.forName("com.mysql.jdbc.Driver").newInstance();
PreparedStatement stmt=null;
     int updateQuery = 0;
               try {
              /* Create a connection by using getConnection()
              method that takes parameters of string type
              connection url, user name and password to connect
		to database. */
              conn = DriverManager.getConnection
              (connectionURL, "root", "");
              //out.println("connection="+conn);

              // sql query to insert values in the secified table.
       String queryString = "INSERT INTO feedback" +
        "(cardno,name,email,comments,date) VALUES (?,?,?,?,?)";

	      /* createStatement() is used for create statement
              object that is used for sending sql statements to the specified database. */
            stmt = conn.prepareStatement(queryString);
              stmt.setString(1, cardno);
	      stmt.setString(2, name);
	      stmt.setString(3, email);
              stmt.setString(4, comments);
              stmt.setString(5, date);
              updateQuery = stmt.executeUpdate();

              if (updateQuery != 0) {
                             response.sendRedirect("home.jsp?msg=Your Feedback is sent to Library..");
	                            }
            }catch (Exception ex){
                                  out.println("Unable to connect to Database."+ex);
                                 }
             finally {
                       // close all the connections.
                          stmt.close();
                          conn.close();
                     }


              %>
    </body>

</html>
