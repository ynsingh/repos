<%@ include file="connectionstrings.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
   response.setHeader( "Pragma", "no-cache" );
   response.setHeader( "Cache-Control", "no-cache" );
   response.setDateHeader( "Expires", 0 );
%>
<html>
<body>
<%
	String Firstname= request.getParameter("Firstname");	
	String Midname= request.getParameter("Midname");
	String Lastname= request.getParameter("Lastname");
	String MaidenName= request.getParameter("Priorname");
	String Nickname= request.getParameter("Nickname");
	String Gender= request.getParameter("Gender");
	String day= request.getParameter("day");
	String month= request.getParameter("month");
	String year= request.getParameter("year");
	
	String DOB=day + "/" + month + "/" + year;
	
	String Degree1= request.getParameter("Degree1");
	String Department1= request.getParameter("Dept1");
	String Batch1= request.getParameter("Batch1");
	String Hostel1= request.getParameter("Hostel1");
	
	String Degree2= request.getParameter("Degree2");
	String Department2= request.getParameter("Dept2");
	String Batch2= request.getParameter("Batch2");
	String Hostel2= request.getParameter("Hostel2");
	
	String Degree3= request.getParameter("Degree3");
	String Department3= request.getParameter("Dept3");
	String Batch3= request.getParameter("Batch3");
	String Hostel3= request.getParameter("Hostel3");

	String Street1= request.getParameter("res_street");
	String City1= request.getParameter("res_City");
	String State1= request.getParameter("res_State");
	String Country1= request.getParameter("res_Country");
	String Pin1= request.getParameter("res_pincode");
	String Phone1= request.getParameter("Res_Phone1");
	String Mobile1= request.getParameter("Res_Phone2");
	String Fax1= request.getParameter("Res_fax");
	String Email= request.getParameter("email");
	String Url= request.getParameter("URL");
	String Occupation= request.getParameter("Occupation");
	String Organisation= request.getParameter("Organization");
	String Designation= request.getParameter("Desg");
	String Interest= request.getParameter("Interest");
	String Street2= request.getParameter("Off_addr");
	String City2= request.getParameter("Off_City");
	String State2= request.getParameter("Off_State");
	String Country2= request.getParameter("Off_Country");
	String Pin2= request.getParameter("Off_pincode");
	String Phone2= request.getParameter("Off_Phone1");
	String Mobile2= request.getParameter("Off_Phone2");
	String Fax2= request.getParameter("Off_fax");
	String LoginMail= request.getParameter("LoginEmailid");
	String Password= request.getParameter("Password");
	String F_email1= request.getParameter("email1");
	String F_email2= request.getParameter("email2");
	String F_email3= request.getParameter("email3");
	String F_email4= request.getParameter("email4");
	stmt.executeUpdate("delete from info where LoginMail= '"+LoginMail+"'");
	stmt.executeUpdate("insert into info(Firstname, Midname, Lastname, Alias, Nickname, Gender, Dob, Deg1, Dep1, Batch1, Host1, Deg2, Dep2, Batch2, Host2, Deg3, Dep3, Batch3, Host3, Street1, City1, State1, Country1, Pin1, Phone1, Mobile1, Fax1, Email, Url, Occupation, Organisation, Designation, Interest, Street2, City2, State2, Country2, Pin2, Phone2, Mobile2, Fax2, LoginMail, Password, F_email1, F_email2, F_email3, F_email4) values('"+Firstname+"','"+Midname+"','"+Lastname+"','"+MaidenName+"','"+Nickname+"','"+Gender+"','"+DOB+"','"+Degree1+"','"+Department1+"','"+Batch1+"','"+Hostel1+"','"+Degree2+"','"+Department2+"','"+Batch2+"','"+Hostel2+"','"+Degree3+"','"+Department3+"','"+Batch3+"','"+Hostel3+"','"+Street1+"','"+City1+"','"+State1+"','"+Country1+"','"+Pin1+"','"+Phone1+"','"+Mobile1+"','"+Fax1+"','"+Email+"','"+Url+"','"+Occupation+"','"+Organisation+"','"+Designation+"','"+Interest+"','"+Street2+"','"+City2+"','"+State2+"','"+Country2+"','"+Pin2+"','"+Phone2+"','"+Mobile2+"','"+Fax2+"','"+LoginMail+"','"+Password+"','"+F_email1+"','"+F_email2+"','"+F_email3+"','"+F_email4+"')");
	stmt.close();
	con.close();
	%>
	<p align="center"><font size="4" color="#FF0000"></font></br>
	<%
	out.println("Your information is updated.");
		
%>
</br><a href="../index.jsp"><font size="4" color="#FF0000">Go To Slumni Home</font></a>|<a href="logout1.jsp"><font size="4" color="#FF0000">Logout</font></a>

</body>
</html>