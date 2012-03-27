<%@page import="java.util.List"%><!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
   response.setHeader( "Pragma", "no-cache" );
   response.setHeader( "Cache-Control", "no-cache" );
   response.setDateHeader( "Expires", 0 );
%>
<html>
<head>
<title></title>
<link rel="stylesheet" title="Style CSS" href="css/our.css" type="text/css" media="all" />
</head>
<BODY >
	
<%@ include file="includes/header.jsp" %>


	<%
	if(session.getAttribute("alumnilogin") == "true")
	{
	String email= request.getParameter("email");
	ResultSet rst=null;
	rst=stmt.executeQuery("select * from info where LoginMail like '%"+email+"%' ");
	if(rst.next())
	{
	String Firstname = rst.getString("Firstname");
	String Lastname = rst.getString("Lastname");
	String Gender = rst.getString("Gender");
	String Dob = rst.getString("Dob");
	String Street1 = rst.getString("Street1");
	String City1 = rst.getString("City1");
	String State1 = rst.getString("State1");
	String Country1 = rst.getString("Country1");
	String Pin1 = rst.getString("Pin1");
	String Phone1 = rst.getString("Phone1");
	String Mobile1 = rst.getString("Mobile1");
	String Occupation = rst.getString("Occupation");
	String Organization = rst.getString("Organisation");
	String Street2 = rst.getString("Street2");
	String City2 = rst.getString("City2");
	String State2 = rst.getString("State2");
	String Country2 = rst.getString("Country2");
	String Pin2 = rst.getString("Pin2");
	String Phone2 = rst.getString("Phone2");
	String Mobile2 = rst.getString("Mobile2");
	%>
	
<div class="wrapper" style="margin-bottom:50px">
	<h1 class="header" style="background-color:LightBlue;">Alumni Details</h1>
		<div class="catheader">Personal Information</div>
		</br>
		<div class="container1">  
			<div class="linepart">First Name</div>
			<div class="linepart"><%out.println(Firstname);%></div>
		</div>
		
		<div class="container">  
			<div class="linepart">Last Name</div>
			<div class="linepart"><%out.println(Lastname);%></div>
		</div>
		<div class="container1">  
			<div class="linepart">Gender</div>
			<div class="linepart"><%out.println(Gender);%></div>
		</div>
		<div class="container">  
			<div class="linepart">Date of Birth</div>
			<div class="linepart"><%out.println(Dob);%></div>
		</div>
		<div class="container1">  
			<div class="linepart">Residence Address</div>
			<div class="linepart"><%out.println(Street1);%></div>
		</div>
		<div class="container">  
			<div class="linepart">City</div>
			<div class="linepart"><%out.println(City1);%></div>
		</div>
		<div class="container1">  
			<div class="linepart">State</div>
			<div class="linepart"><%out.println(State1);%></div>
		</div>
		<div class="container">  
			<div class="linepart">Country</div>
			<div class="linepart"><%out.println(Country1);%></div>
		</div>
		<div class="container1">  
			<div class="linepart">Pin</div>
			<div class="linepart"><%out.println(Pin1);%></div>
		</div>
		<div class="container">  
			<div class="linepart">Phone</div>
			<div class="linepart"><%out.println(Phone1);%></div>
		</div>
		<div class="container1">  
			<div class="linepart">Mobile</div>
			<div class="linepart"><%out.println(Mobile1);%></div>
		</div>
		
		<div class="catheader">Professional Information</div>
			</br>
		<div class="container1">  
			<div class="linepart">Occupation</div>
			<div class="linepart"><%out.println(Occupation);%></div>
		</div>
		<div class="container">  
			<div class="linepart">Organization</div>
			<div class="linepart"><%out.println(Organization);%></div>
		</div>
		<div class="container1">  
			<div class="linepart">Office Address</div>
			<div class="linepart"><%out.println(Street2);%></div>
		</div>
		<div class="container">  
			<div class="linepart">City</div>
			<div class="linepart"><%out.println(City2);%></div>
		</div>
		<div class="container1">  
			<div class="linepart">State</div>
			<div class="linepart"><%out.println(State2);%></div>
		</div>
		<div class="container">  
			<div class="linepart">Country</div>
			<div class="linepart"><%out.println(Phone2);%></div>
		</div>
		<div class="container1">  
			<div class="linepart">Pin</div>
			<div class="linepart"><%out.println(Mobile2);%></div>
		</div>
		<div class="container">  
			<div class="linepart">Phone</div>
			<div class="linepart"><%out.println(Phone2);%></div>
		</div>
		<div class="container1">  
			<div class="linepart">Mobile</div>
			<div class="linepart"><%out.println(Mobile2);%></div>
		</div>
<%
	}
		rst.close();
		stmt.close();
		con.close();
		}
	else
	response.sendRedirect("index.jsp"); 
%>	
	</div>
	</BODY>
</HTML>