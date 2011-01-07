<%-- 
    Document   : NewMember.jsp
    Created on : Jun 29, 2010, 7:47:35 PM
    Author     : Mayank Saxena
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page errorPage = "ErrorPage.jsp" language="java" import="java.sql.*"%>
<%@ page import="java.util.*"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<%!
String lib_name,lib_id,category,fname,mname,lname,subject,add1,add2,city,state,pin,country;
String email,fax,ph1,ph2,req_date,faculty,dept,memid,rollno,course;
String date;
Connection conn=null;
PreparedStatement stmt=null;
%>
<%Calendar cal = new GregorianCalendar();
    int month = cal.get(Calendar.MONTH);
    int year = cal.get(Calendar.YEAR);
    int day = cal.get(Calendar.DAY_OF_MONTH);
    date=day+"/"+(month+1)+"/"+year;
%>

<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="Mayank Saxena" content="MCA,AMU">
      <title>New Member..</title>
  </head>
  <body>


<%
//Retrieving the values of NewMember form in variables.
lib_name=request.getParameter("TXTLIBNAME");
lib_id=request.getParameter("TXTLIBID");
category=request.getParameter("CMBCAT");
fname=request.getParameter("TXTFNAME");
mname=request.getParameter("TXTMNAME");
lname=request.getParameter("TXTLNAME");
add1=request.getParameter("TXTADD1");
add2=request.getParameter("TXTADD2");
city=request.getParameter("TXTCITY");
state=request.getParameter("TXTSTATE");
pin=request.getParameter("TXTPIN");
country=request.getParameter("TXTCOUNTRY");
email=request.getParameter("TXTEMAIL");
fax=request.getParameter("TXTFAX");
ph1=request.getParameter("TXTPH1");
ph2=request.getParameter("TXTPH2");
req_date=date;
faculty=request.getParameter("TXTFACULTY");
dept=request.getParameter("TXTDEPT");
memid=request.getParameter("TXTID");
rollno=request.getParameter("TXTROLL");
course=request.getParameter("TXTCOURSE");
//out.println(lib_name+lib_id+fname+mname+lname+req_date+course+faculty+dept+memid+add1+add2);
  /* Create string of connection url within specified
   format with machine name,
    port number and database name. Here machine name id
    localhost and database name is library. */
    String connectionURL = "jdbc:mysql://localhost:3307/library";
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
     String queryString = "INSERT INTO member VALUES (?,?,?,?,?,?," +
       "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

/* createStatement() is used for create statement
   object that is used for sending sql statements to the
 *  specified database. */
    stmt = conn.prepareStatement(queryString);
         stmt.setString(1, lib_name);
         stmt.setString(2, lib_id);
         stmt.setString(3, category);
         stmt.setString(4, fname);
         stmt.setString(5, mname);
         stmt.setString(6, lname);
         stmt.setString(7, subject);
         stmt.setString(8, add1);
         stmt.setString(9, add2);
         stmt.setString(10, city);
         stmt.setString(11, state);
	   stmt.setString(12, pin);
         stmt.setString(13, country);
         stmt.setString(14, email);
         stmt.setString(15, fax);
         stmt.setString(16, ph1);
         stmt.setString(17, ph2);
         stmt.setString(18, req_date);
         stmt.setString(19, faculty);
         stmt.setString(20, dept);
         stmt.setString(21, memid);
         stmt.setString(22, rollno);
         stmt.setString(23, course);
             
              updateQuery = stmt.executeUpdate();
                if (updateQuery != 0) {
                             response.sendRedirect("home.jsp?msg=Your Membership Request is sent to Library..");
	                            }
            }catch (Exception ex){
                                  out.println("Unable to connect to " +
                                          "Database."+ex);
                                 }
             finally {
                       // close all the connections.
                          stmt.close();
                          conn.close();
                     }


              %>
    </body>
</html>
