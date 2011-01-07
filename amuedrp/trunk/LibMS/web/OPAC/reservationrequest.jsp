<%--
    Document   : ReservationRequest.jsp
    Created on : July 15, 2010, 7:15:37 PM
    Author     : Mayank Saxena
--%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page errorPage = "ErrorPage.jsp" language="java"%>
<%@page import="java.sql.*"%>
<%@ page import="java.util.*"%>

<%!
String lib_name,card_id,title,author,publ,edition;
String req_date,callno,isbn,vol,remark,category;

Connection conn=null;
PreparedStatement stmt=null;
%>
<%Calendar cal = new GregorianCalendar();
    int month = cal.get(Calendar.MONTH);
    int year = cal.get(Calendar.YEAR);
    int day = cal.get(Calendar.DAY_OF_MONTH);
    req_date=day+"/"+(month+1)+"/"+year;
%>

<html>
  <head>
      <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="Mayank Saxena" content="MCA,AMU">
          <title>New Reservation...</title>
          
  </head>
  <body>


<%
//Retrieving the values of NewMember form in variables.
lib_name=request.getParameter("TXTLIBNAME");
card_id=request.getParameter("id");
title=request.getParameter("TXTTITLE");
category=request.getParameter("CMBCAT");
author=request.getParameter("TXTAUTHOR");
publ=request.getParameter("TXTPUBL");
//card_id=request.getParameter("TXTCARDID");
isbn=request.getParameter("TXTISBN");
callno=request.getParameter("TXTCALLNO");
vol=request.getParameter("TXTVOL");
edition=request.getParameter("TXTEDITION");
remark=request.getParameter("TXTREMARKS");

//out.println(lib_name+lib_id+fname+mname+lname+req_date+course+faculty+dept+memid+add1+add2);
  /* Create string of connection url within specified
   format with machine name,
    port number and database name. Here machine name id
    localhost and database name is library. */
    String connectionURL = "jdbc:mysql://localhost:3307/libms";
    // Load JBBC driver "com.mysql.jdbc.Driver"
     Class.forName("com.mysql.jdbc.Driver").newInstance();

     int updateQuery = 0;
               try {
              /* Create a connection by using getConnection()
              method that takes parameters of string type
              connection url, user name and password to connect
		to database. */
              conn = DriverManager.getConnection
              (connectionURL, "root", "");


              // sql query to insert values in the secified table.
     String queryString = "INSERT INTO reservationlist VALUES (?,?,?,?,?,?," +
       "?,?,?,?,?,?,?)";

/* createStatement() is used for create statement
   object that is used for sending sql statements to the
 *  specified database. */
    stmt = conn.prepareStatement(queryString);
         stmt.setString(1, lib_name);
         stmt.setString(2, card_id);
         stmt.setString(3, title);
         stmt.setString(4, category);
         stmt.setString(5, author);
         stmt.setString(6, isbn);
         stmt.setString(7, callno);
         stmt.setString(8, edition);
         stmt.setString(9, vol);
         stmt.setString(10, publ);
         stmt.setString(11, remark);
	 stmt.setString(12, req_date);
         stmt.setString(13, "pending");
         updateQuery = stmt.executeUpdate();
                if (updateQuery != 0) {
                    request.setAttribute("msg","Your reservation request is sent to Library..");
                             response.sendRedirect("home.jsp");
	                            }
            }catch (Exception ex){
                                  out.println("Unable to connect to " +
                                          "Database."+ex);
                                 }
             finally {
                       // close all the connections.
                        //  stmt.close();
                        //  conn.close();
                     }


              %>
    </body>
</html>
