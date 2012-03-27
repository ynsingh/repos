<%@ page contentType="text/html; charset=utf-8" language="java" import="java.sql.*" errorPage="" %>
<%@ page import = 'org.gjt.mm.mysql.*' %>
<%@page import="java.io.IOException"%>
<%@page import="java.util.Properties"%>
<%@page import="java.io.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%
   response.setHeader( "Pragma", "no-cache" );
   response.setHeader( "Cache-Control", "no-cache" );
   response.setDateHeader( "Expires", 0 );
%>
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	</head>
	<body>
    <%
	String dbname=request.getParameter("dbname");
	String dbserver=request.getParameter("dbserver");
	String user=request.getParameter("adminname");
	String passwd=request.getParameter("adminpassword");
	String dbport=request.getParameter("dbport");
	String webadmin=request.getParameter("webadmin");
	String webpasswd=request.getParameter("webpasswd");

        Properties prop = new Properties();
	String fileName=getServletContext().getRealPath("config.properties");
	prop.setProperty("dbserver", dbserver);
        prop.setProperty("dbuser", user);
        prop.setProperty("dbpassword", passwd);
	prop.setProperty("dbport",dbport);
        prop.setProperty("dbname", dbname);
	prop.setProperty("webadmin",webadmin);
	prop.setProperty("webpasswd",webpasswd);

        //prop.store(new FileOutputStream(fileName), null);

	String driver = "org.gjt.mm.mysql.Driver";
	//Load Database Driver
	try {
	Class.forName(driver).newInstance();
	}
	catch(Exception x){
	out.println(x);
	}
	Connection con=null;
	Statement stmt=null;
	ResultSet rs=null;


try{
	//String url="jdbc:mysql://localhost:3306/alumni?user=root&password=calendar";
	String url="jdbc:mysql://"+dbserver+":"+dbport;
	con=DriverManager.getConnection(url,user,passwd);
		stmt=con.createStatement();
	}
	catch(Exception e){
		System.out.println(e.getMessage());
	}
	String quer = "create database IF NOT EXISTS "+dbname;
	stmt.executeUpdate(quer);


	try{
         if(stmt!=null)
            stmt.close();
      }catch(SQLException se2){
      }// nothing we can do
      try{
         if(con!=null)
            con.close();
			}catch(SQLException se){
         se.printStackTrace();
      }
	  try{

	 String url1="jdbc:mysql://"+dbserver+":"+dbport+"/"+dbname;
	  con=DriverManager.getConnection(url1,user,passwd);
		stmt=con.createStatement();
		}
	catch(Exception e){
		System.out.println(e.getMessage());
	}
	  quer = "create table IF NOT EXISTS admin(username varchar(50),password varchar(50) )";
	  stmt.executeUpdate(quer);

	  quer = "create table IF NOT EXISTS degreelist(degree varchar(100))";
	  stmt.executeUpdate(quer);

	  quer = "create table IF NOT EXISTS departmentlist(department varchar(100))";
	  stmt.executeUpdate(quer);

	  quer = "create table IF NOT EXISTS hostellist(hostel varchar(100))";
	  stmt.executeUpdate(quer);

	  quer = "create table IF NOT EXISTS info(RID int Auto_Increment primary key,Firstname varchar(50),Midname varchar(50),Lastname varchar(50),Alias varchar(50),Nickname varchar(50),Gender varchar(50),Dob varchar(50),Deg1 varchar(50),Dep1 varchar(50),Batch1 varchar(50),Host1 varchar(50),Deg2 varchar(50),Dep2 varchar(50),Batch2 varchar(50),Host2 varchar(50),Deg3 varchar(50),Dep3 varchar(50),Batch3 varchar(50),Host3 varchar(50),Street1 varchar(200),City1 varchar(50),State1 varchar(50),Country1 varchar(50),Pin1 varchar(50),Phone1 varchar(50),Mobile1 varchar(50),Fax1 varchar(50),Email varchar(50),Url varchar(50),Occupation varchar(100),Organisation varchar(100),Designation varchar(100),Interest varchar(400),Street2 varchar(200),City2 varchar(50),State2 varchar(50),Country2 varchar(50),Pin2 varchar(50),Phone2 varchar(50),Mobile2 varchar(50),Fax2 varchar(50),LoginMail varchar(50) not null unique,Password varchar(20),F_email1 varchar(50),F_email2 varchar(50),F_email3 varchar(50),F_email4 varchar(50))";

	  stmt.executeUpdate(quer);

	  quer = "create table IF NOT EXISTS calendar1 (date date,sdate date,edate date,title varchar(500), ttip varchar(500), message text, flag Boolean)";
	  stmt.executeUpdate(quer);

	  quer = "create table IF NOT EXISTS divtext ( divtype varchar(50),divid varchar(50), title varchar(50), message text)";
	  stmt.executeUpdate(quer);

	  quer = "create table  IF NOT EXISTS boxes (box_id int(10), member_id int(10), box_name text,sort_desc text,primary key(box_id))";
	  stmt.executeUpdate(quer);

	  quer = "create table  IF NOT EXISTS levels ( grade text, min_post int(10), max_post int(10), description text)";
	  stmt.executeUpdate(quer);

	  quer = "create table IF NOT EXISTS members ( member_id int(10), username text, password text, firstname text, lastname text, email text, regdate text, type text)";
	  stmt.executeUpdate(quer);

	  quer = "create table IF NOT EXISTS threads (thread_id int(100), box_id int(100), parent_id int(100), member_id int(100), subject text,post_text text, post_date double )";
	  stmt.executeUpdate(quer);

           quer = "truncate table  divtext";
	   stmt.executeUpdate(quer);

	  quer = "insert into  divtext value('news','news1','','')";
	  stmt.executeUpdate(quer);

	  quer = "insert into divtext value('news','news2','','')";
	  stmt.executeUpdate(quer);

	  quer = "insert into divtext value('news','news3','','')";
	  stmt.executeUpdate(quer);

	  quer = "insert into divtext value('news','news4','','')";
	  stmt.executeUpdate(quer);

	  quer = "insert into divtext value('news','news5','','')";
	  stmt.executeUpdate(quer);

	  quer = "insert into divtext value('news','news6','','')";
	  stmt.executeUpdate(quer);



	  quer = "insert into divtext values('header','collegename','National Institute of Technology,Hamirpur','')";
	  stmt.executeUpdate(quer);

	  quer = "insert into divtext values('header','logourl','includes/logo-nith.png','')";
	  stmt.executeUpdate(quer);

	  quer = "insert into divtext values('center','welcome','Welcome Message','')";
	  stmt.executeUpdate(quer);

          quer = "truncate table admin";
	  stmt.executeUpdate(quer);
          quer = "truncate table members";
	  stmt.executeUpdate(quer);
          quer = "insert into admin values('"+webadmin+"','"+webpasswd+"')";
	  stmt.executeUpdate(quer);



	 int memberid = (int) (1000 + Math.random()* 1000);
		String regdate = new java.util.Date().toString();
		String type = "Administrator";
		String Firstname = "admin";
		String Lastname = "forum";
		String Email = "admin@forum.com";

		String query2 = "INSERT INTO members (member_id, username, password, firstname, lastname, email, regdate, type) " +
						"VALUES (" + memberid + ", " + "'" + webadmin + "', " +
						"'" + webpasswd + "', " +
						"'" + Firstname + "', " +
						"'" + Lastname + "', " +
						"'" + Email + "', " +
						"'" + regdate + "', " +
						"'" + type + "')";
		stmt.executeUpdate(query2);
		stmt.close();
		con.close();

	 /* try{
         if(stmt!=null)
            stmt.close();
      }catch(SQLException se2){
      }// nothing we can do
      try{
         if(con!=null)
            con.close();
			}catch(SQLException se){
         se.printStackTrace();
      }*/
	  prop.setProperty("conf", "true");
	  OutputStream outstream = new FileOutputStream(fileName);
 	//prop.store(new FileOutputStream(fileName), null);
	prop.store(outstream, null);
	if(outstream !=null)
	outstream.close();

	  //out.println(db.getconf());
		response.sendRedirect("index.jsp");


	%>

    </body>
</html>