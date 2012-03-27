<%@ page import="java.util.Calendar"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta NAME="GENERATOR" Content="Microsoft FrontPage 4.0">
<title></title>

<link rel="stylesheet" title="Style CSS" href="css/our.css" type="text/css" media="all" />
<script language="javascript" src = "validation.js">
</script>

</head>

<body >



<%@ include file="includes/header.jsp" %> 




<%
   response.setHeader( "Pragma", "no-cache" );
   response.setHeader( "Cache-Control", "no-cache" );
   response.setDateHeader( "Expires", 0 );
%>


        <%


if(session.getAttribute("login")!= "true")
    {

   String t;
    t = "<div align=center><strong align=center><font color =red> Please Login to Edit Your Information</font></strong></div>";
   out.println(t);

   }

%>

    <%
	
	String username=(String)session.getAttribute("username");
   
	String password=(String)session.getAttribute("pass");
	ResultSet rst=null;
	ResultSet rst1=null;
	ResultSet rst2=null;
	ResultSet rst3=null;
	ResultSet rst4=null;
	ResultSet rst5=null;

	rst4=stmt.executeQuery("select * from info where LoginMail= '"+username+"'");
		if(rst4.next())
		{
			String Firstname = rst4.getString("Firstname");
			String Midname= rst4.getString("Midname");
			String Lastname= rst4.getString("Lastname");
			String MaidenName= rst4.getString("Alias");
			String Nickname= rst4.getString("Nickname");
			String Gender= rst4.getString("Gender");
			String Dob=rst4.getString("Dob");
				String [] splitdob= Dob.split("/");
				String Day= splitdob[0];
				String Month= splitdob[1];
				String Year= splitdob[2];
			String Street1= rst4.getString("Street1");
			String City1= rst4.getString("City1");
			String State1= rst4.getString("State1");
			String Country1= rst4.getString("Country1");
			String Pin1= rst4.getString("Pin1");
			String Phone1= rst4.getString("Phone1");
			String Mobile1= rst4.getString("Mobile1");
			String Fax1= rst4.getString("Fax1");
			String Email= rst4.getString("Email");
			String Url= rst4.getString("Url");
			String Degree1=rst4.getString("Deg1");
			String Department1=rst4.getString("Dep1");
			String Batch1=rst4.getString("Batch1");
			String Hostel1=rst4.getString("Host1");
			String Degree2=rst4.getString("Deg2");
			String Department2=rst4.getString("Dep2");
			String Batch2=rst4.getString("Batch2");
			String Hostel2=rst4.getString("Host2");
			String Degree3=rst4.getString("Deg3");
			String Department3=rst4.getString("Dep3");
			String Batch3=rst4.getString("Batch3");
			String Hostel3=rst4.getString("Host3");
			String Occupation= rst4.getString("Occupation");
			String Organisation= rst4.getString("Organisation");
			String Designation= rst4.getString("Designation");
			String Interest= rst4.getString("Interest");
			String Street2= rst4.getString("Street2");
			String City2= rst4.getString("City2");
			String State2= rst4.getString("State2");
			String Country2= rst4.getString("Country2");
			String Pin2= rst4.getString("Pin2");
			String Phone2= rst4.getString("Phone2");
			String Mobile2= rst4.getString("Mobile2");
			String Fax2= rst4.getString("Fax2");
			String LoginMail= rst4.getString("LoginMail");
			String Password= rst4.getString("Password");
			String F_email1= rst4.getString("F_email1");
			String F_email2= rst4.getString("F_email2");
			String F_email3= rst4.getString("F_email3");
			String F_email4= rst4.getString("F_email4");
	%>

    
	<div class="wrapper" >
	<h1 class="header">Update Yourself</h1>
	<form  style="background-color:#F5F5F5 padding:3em 2em 0.5em 2em;" name="theForm" action="includes/alumniedit1.jsp" method="post" onsubmit= "return Form1_Validator(this)">
		<center>
		<div class="catheader">Personal Information</div>

		<div class="container" style="background-color:#c0c0c0;">
			<div class="linepart">First Name <super style="color:red">*</super></div>
			<div class="linepart">Middle Name</div>
			<div class="linepart">Last Name <super style="color:red">*</super></div>
		</div>
	
		
		<div class="container">
			<div class="linepart"><input class="text"  maxLength="30" name="Firstname"  id="txtFirstname" value="<%=Firstname%>" onBlur="pleaseFill(this)"></div>
			<div class="linepart"><input class="text"  maxLength="30" name="Midname"  id="txtMidname" value="<%=Midname%>"></div>
			<div class="linepart"><input class="text"  maxLength="30" name="Lastname"  id="txtLastname" value="<%=Lastname%>" onBlur="pleaseFill(this)"></div>
		</div>
              
		<div class="container" >
			<div class="linepart">Alias/MaidenName</div>
			<div class="linepart">Nickname</div>
		</div>
         
		<div class="container">  
			<div class="linepart"><input class="text"  name="Priorname" id="txtPriorname" maxLength="30"  ></div>
			<div class="linepart"><input class="text"  name="Nickname" id="txtNickname" maxLength="30"  ></div>
		</div>
             
		<div class="container">  
			<div class="linepart">Gender <super style="color:red">*</super></div>
			<div class="linepart">Date of Birth <super style="color:red">*</super></div>
		</div>
		
		<div class="container">  
			<div class="linepart">
				<select name="Gender" size="1" id="lstGender" >
					<option value="" selected >Select Gender</option>
					<option value="Male" <%
					if(Gender.equals("Male"))
					out.println(" selected ");
					%> >Male</option>
					<option value="Female" <%
					if(Gender.equals("Female"))
					out.println(" selected ");
					%>>Female</option>
				</select>
			</div>
			
			<div class="linepart">
				<select name="day" size="1" id="txtDay">
					<option value="" selected >DD</option>
					<%
						int i;
						for(i=1;i<=31;i++)
					{
						out.println("<option value='"+i+"'");
						if(Day.equals(""+i))
						out.println("selected");
						out.println(">"+i+"</option>");
					}
					%>
				</select>
				<select name="month" size="1" id="txtMonth">
				<option value="" selected >MM</option>
					<%
					for(i=1;i<=12;i++)
					{
						out.println("<option value='"+i+"'");
						if(Month.equals(""+i))
						out.println("selected");
						out.println(">"+i+"</option>");
					}
					%>
					
				</select>
				<select name="year" size="1" id="txtYear"> 
			<option value="" selected >YYYY</option>
				<%
				Calendar cal = Calendar.getInstance(); 
				int currentyear = cal.get(Calendar.YEAR); 
				for(i=1965;i<=currentyear;i++)
				{
					out.println("<option value='"+i+"'");
					if(Year.equals(""+i))
					out.println("selected");
					out.println(">"+i+"</option>");
				}
				%>
				</select>	
			</div>
		</div>
		
		
		<p>
		<div class="container">  
			<div class="linepart">Residence Address</div>
			<div class="linepart"><input class="text"  id="txtRes_street"  name="res_street"  value="<%=Street1%>"></div>
		</div>
		
		<div class="container">  
			<div class="linepart">City <super style="color:red">*</super></div>
			<div class="linepart"><input class="text"  maxLength="30" name="res_City"  id="txtRes_city" value="<%=City1%>" onBlur="pleaseFill(this)"></div>
		</div>
		
		<div class="container">  
			<div class="linepart">State <super style="color:red">*</super></div>
			<div class="linepart"><input class="text"  maxLength="30" name="res_State"  id="txtRes_State" value="<%=State1%>" onBlur="pleaseFill(this)"></div>
		</div>
		
		<div class="container">  
			<div class="linepart">Country <super style="color:red">*</super></div>
			<div class="linepart"><input class="text"  maxLength="30" name="res_Country"  id="txtRes_country"  value="<%=Country1%>" onBlur="pleaseFill(this)"></div>
		</div>
		
		<div class="container">  
			<div class="linepart">Pin <super style="color:red">*</super></div>
			<div class="linepart"><input class="text"  maxLength="20" name="res_pincode"  id="txtRes_Pincode" value="<%=Pin1%>" onBlur="pleaseFill(this)"></div>
		</div>
        
		<div class="container">  
			<div class="linepart">Phone/Mobile</div>
			<div class="linepart"><input class="text"  maxLength="20" name="Res_Phone1"  id="txtRes_Phone1" value="<%=Phone1%>"></div>
			<div class="linepart"><input class="text"  maxLength="20" name="Res_Phone2"  id="txtRes_Phone2" value="<%=Mobile1%>"></div>
		</div>
		
		<div class="container">  
			<div class="linepart">Fax</div>
			<div class="linepart"><input class="text"  maxLength="20" name="Res_fax" id="txtRes_Fax" value="<%=Fax1%>"></div>
		</div>

<br>


		<div class="container">  
			<div class="linepart">Email <super style="color:red" >*</super></div>
			<div class="linepart"><input class="text"  maxLength="70" name="email"  id="txtEmail" value="<%=Email%>" onBlur="pleaseFill(this)"></div>
		</div>
	
		<div class="container">  
			<div class="linepart">Webpage</div>
			<div class="linepart"><input class="text"  maxLength="70" name="URL"   id="txtURL" value="<%out.println(Url);%>" ></div>
		</div>
		</p>
	
		<div class="catheader">Academic Information</div>
	
		<div class="container" style="background-color:#c0c0c0;">
			<div class="linepart1">Degree <super style="color:red" >*</super></div>
			<div class="linepart1">Department/Centre <super style="color:red" >*</super></div>
			<div class="linepart1">Batch(Passing Year) <super style="color:red" >*</super></div>
			<div class="linepart1">Hostel <super style="color:red" >*</super></div>
		</div>
       <div class="container">
			<div class="linepart1">
				<select name="Degree1" size="1" id="lstDegree1">
					<option value="" selected >Select Degree</option>
					
					<% 
							
							rst=stmt.executeQuery("select * from degreelist");
							while (rst.next())
							{
								String degree = rst.getString("degree");
								out.println("<option ");
								if(Degree1.equals(degree))
								{
									out.println(" selected ");
								}
								out.println("Value='"+degree+"'>" +degree+"</option> ");
							}
					%>
				</select>
			</div>
			<div class="linepart1">
				<select name="Dept1" size="1" id="lstDept1" style="width:12em"> 
					<option value="" selected >Select Department</option>
					<% 
							
							rst1=stmt.executeQuery("select * from departmentlist");
							while (rst1.next())
							{
								String department = rst1.getString("department");
								out.println("<option ");
								if(Department1.equals(department))
								{
									out.println(" selected ");
								}
								out.println("Value='"+department+"'>"+department+"</option> ");
							}
					%>
				</select>
			</div>
			<div class="linepart1">
				<select name="Batch1" size="1" id="lstBatch1"> 
					<option value="" selected>Select Batch</option> 
					<% 
							
									for(i=1989;i<=currentyear;i++)
						{
							out.println("<option value='"+i+"'");
							if(Batch1.equals(""+i))
							out.println("selected");
							out.println(">"+i+"</option>");
						}
					   %>
				</select>
			</div>
			<div class="linepart1">
				<select name="Hostel1" size="1" id="lstHostel1">
					<option value="" selected >Select Hostel</option>
					<% 
							
							rst3=stmt.executeQuery("select * from hostellist");
							while (rst3.next())
							{
								String hostel = rst3.getString("hostel");
								out.println("<option ");
								if(Hostel1.equals(hostel))
								{
									out.println(" selected ");
								}
								out.println("Value='"+hostel+"'>"+hostel+"</option> ");
							}
					%>
				</select>	
			</div>
		</div>	
			
			<div class="container">
			<div class="linepart1">
				<select name="Degree2" size="1" id="lstDegree2">
					<option value="" selected >Select Degree</option>
					<% 
							rst=stmt.executeQuery("select * from degreelist");
							while (rst.next())
							{
								String degree = rst.getString("degree");
								out.println("<option ");
								if(Degree2.equals(degree))
								{
									out.println(" selected ");
								}
								out.println("Value='"+degree+"'>" +degree+"</option> ");
							}
					%>
				</select>
			</div>
			<div class="linepart1">
				<select name="Dept2" size="1" id="lstDept2" style="width:12em"> 
					<option value="" selected >Select Department</option>
					<% 
							rst1=stmt.executeQuery("select * from departmentlist");
							while (rst1.next())
							{
								String department = rst1.getString("department");
								out.println("<option ");
								if(Department2.equals(department))
								{
									out.println(" selected ");
								}
								out.println("Value='"+department+"'>"+department+"</option> ");
							}
					%>
				</select>
			</div>
			<div class="linepart1">
				<select name="Batch2" size="1" id="lstBatch2"> 
					<option value="" selected>Select Batch</option> 
					<% 
										for(i=1989;i<=currentyear;i++)
						{
							out.println("<option value='"+i+"'");
							if(Batch2.equals(""+i))
							out.println("selected");
							out.println(">"+i+"</option>");
						}
					   %>
				</select>
			</div>
			<div class="linepart1">
				<select name="Hostel2" size="1" id="lstHostel2">
					<option value="" selected >Select Hostel</option>
					<% 
							rst3=stmt.executeQuery("select * from hostellist");
							while (rst3.next())
							{
								String hostel = rst3.getString("hostel");
								out.println("<option ");
								if(Hostel2.equals(hostel))
								{
									out.println(" selected ");
								}
								out.println("Value='"+hostel+"'>"+hostel+"</option> ");
							}
					%>
				</select>	
			</div>
		</div>
		
		<div class="container">
			<div class="linepart1">
				<select name="Degree3" size="1" id="lstDegree3">
					<option value="" selected >Select Degree</option>
					<% 
							rst=stmt.executeQuery("select * from degreelist");
							while (rst.next())
							{
								String degree = rst.getString("degree");
								out.println("<option ");
								if(Degree3.equals(degree))
								{
									out.println(" selected ");
								}
								out.println("Value='"+degree+"'>" +degree+"</option> ");
							}
						
					%>
				</select>
			</div>
			<div class="linepart1">
				<select name="Dept3" size="1" id="lstDept3" style="width:12em"> 
					<option value="" selected >Select Department</option>
					<% 
							rst1=stmt.executeQuery("select * from departmentlist");
							while (rst1.next())
							{
								String department = rst1.getString("department");
								out.println("<option ");
								if(Department3.equals(department))
								{
									out.println(" selected ");
								}
								out.println("Value='"+department+"'>"+department+"</option> ");
							}
					
					%>
				</select>
			</div>
			<div class="linepart1">
				<select name="Batch3" size="1" id="lstBatch3"> 
					<option value="" selected>Select Batch</option> 
					<% 
									for(i=1989;i<=currentyear;i++)
						{
							out.println("<option value='"+i+"'");
							if(Batch3.equals(""+i))
							out.println("selected");
							out.println(">"+i+"</option>");
						}
					   %>
				</select>
			</div>
			<div class="linepart1">
				<select name="Hostel3" size="1" id="lstHostel3">
					<option value="" selected >Select Hostel</option>
					<% 
							rst3=stmt.executeQuery("select * from hostellist");
							while (rst3.next())
							{
								String hostel = rst3.getString("hostel");
								out.println("<option ");
								if(Hostel3.equals(hostel))
								{
									out.println(" selected ");
								}
								out.println("Value='"+hostel+"'>"+hostel+"</option> ");
							}
					%>
					
				</select>	
			</div>
		</div>
		
		<div class="catheader">Professional Information</div>
		
		<div class="container">  
			<div class="linepart">Occupation <super style="color:red" >*</super></div>
			<div class="linepart">
					<select name="Occupation" size="1" id="txtOccupation" >
				<option value="" selected >Select Occupation</option>
					<option value="Accounting/Finance"<%
					if(Occupation.equals("Accounting/Finance"))
					out.println(" selected ");
					%> >Accounting/Finance</option>
					<option value="Computer related (Internet)"<%
					if(Occupation.equals("Computer related (Internet)"))
					out.println(" selected ");
					%> >Computer related (Internet)</option>
					<option value="Computer related (other)"<%
					if(Occupation.equals("Computer related (other)"))
					out.println(" selected ");
					%> >Computer related (other)</option>
					<option value="Consulting"<%
					if(Occupation.equals("Consulting"))
					out.println(" selected ");
					%> >Consulting</option>
					<option value="Customer service/support"<%
					if(Occupation.equals("Customer service/support"))
					out.println(" selected ");
					%> >Customer service/support</option>
					<option value="Education/training"<%
					if(Occupation.equals("Education/training"))
					out.println(" selected ");
					%> >Education/training</option>
					<option value="Engineering"<%
					if(Occupation.equals("Engineering"))
					out.println(" selected ");
					%> >Engineering</option>
					<option value="Executive/senior management"<%
					if(Occupation.equals("Executive/senior management"))
					out.println(" selected ");
					%> >Executive/senior management</option>
					<option value="General administrative/supervisory"<%
					if(Occupation.equals("General administrative/supervisory"))
					out.println(" selected ");
					%> >General administrative/supervisory</option>
					<option value="Government/Military"<%
					if(Occupation.equals("Government/Military"))
					out.println(" selected ");
					%> >Government/Military</option>
					<option value="Homemaker"<%
					if(Occupation.equals("Homemaker"))
					out.println(" selected ");
					%> >Homemaker</option>
					<option value="Manufacturing/production/operations"<%
					if(Occupation.equals("Manufacturing/production/operations"))
					out.println(" selected ");
					%> >Manufacturing/production/operations</option>
					<option value="Professional (medical, legal, etc.)"<%
					if(Occupation.equals("Professional (medical, legal, etc.)"))
					out.println(" selected ");
					%> >Professional (medical, legal, etc.)</option>
					<option value="Research and development"<%
					if(Occupation.equals("Research and development"))
					out.println(" selected ");
					%> >Research and development</option>
					<option value="Retired"<%
					if(Occupation.equals("Retired"))
					out.println(" selected ");
					%> >Retired</option>
					<option value="Sales/marketing/advertising"<%
					if(Occupation.equals("Sales/marketing/advertising"))
					out.println(" selected ");
					%> >Sales/marketing/advertising</option>
					<option value="Self-employed/owner"<%
					if(Occupation.equals("Self-employed/owner"))
					out.println(" selected ");
					%> >Self-employed/owner</option>
					<option value="Tradesman/craftsman"<%
					if(Occupation.equals("Tradesman/craftsman"))
					out.println(" selected ");
					%> >Tradesman/craftsman</option>
					<option value="Unemployed/Between Jobs"<%
					if(Occupation.equals("Unemployed/Between Jobs"))
					out.println(" selected ");
					%> >Unemployed/Between Jobs</option>
					<option value="Higher Studies"<%
					if(Occupation.equals("Higher Studies"))
					out.println(" selected ");
					%> >Higher Studies</option>
					<option value="Other"<%
					if(Occupation.equals("Other"))
					out.println(" selected ");
					%> >Other</option>
				</select>
			</div>
		</div>

		<div class="container">  
			<div class="linepart">Oganization</div>
			<div class="linepart"><input class="text"  id="txtOrganization" name="Organization" maxLength="50"  value="<%out.println(Organisation);%>"></div>
		</div>
		
		
		
		<div class="container">  
			<div class="linepart">Designation</div>
			<div class="linepart"><input class="text"  id="txtDesg" name="Desg" maxLength="50"  value="<%out.println(Designation);%>"></div>
		</div>
		
		<div class="container">  
			<div class="linepart">Areas of Interest <super style="color:red" >*</super></div>
			<div class="linepart"><input class="text"  id="txtinterest" name="Interest" maxLength="100"  value="<%out.println(Interest);%>" onBlur="pleaseFill(this)"></div>
		</div>
		
		<div class="container">  
			<div class="linepart">Office Address </div>
			<div class="linepart"><input class="text"  id="txtOff_street" name="Off_addr"  value="<%out.println(Street2);%>" </div>
		</div>
		
		<div class="container">  
			<div class="linepart">City <super style="color:red">*</super></div>
			<div class="linepart"><input class="text"  maxLength="30" name="Off_City"  id="txtOff_city"  value="<%out.println(City2);%>" onBlur="pleaseFill(this)"></div>
		</div>
		
		<div class="container">  
			<div class="linepart">State <super style="color:red">*</super></div>
			<div class="linepart"><input class="text"  maxLength="30" name="Off_State"  id="txtOff_state" value="<%out.println(State2);%>" onBlur="pleaseFill(this)"></div>
		</div>
		
		<div class="container">  
			<div class="linepart">Country <super style="color:red">*</super></div>
			<div class="linepart"><input class="text"  maxLength="30" name="Off_Country"  id="txtOff_country" value="<%out.println(Country2);%>" onBlur="pleaseFill(this)"></div>
		</div>
		
		<div class="container">  
			<div class="linepart">Pin <super style="color:red">*</super></div>
			<div class="linepart"><input class="text"  maxLength="20" name="Off_pincode"  id="txtoff_pincode" value="<%out.println(Pin2);%>" onBlur="pleaseFill(this)"></div>
		</div>
		
		<div class="container">  
			<div class="linepart">Phone/Mobile</div>
			<div class="linepart"><input class="text"  maxLength="20" name="Off_Phone1"  id="txtOff_Phone1" value="<%out.println(Phone2);%>"></div>
			<div class="linepart"><input class="text"  maxLength="20" name="Off_Phone2"  id="txtOff_Phone2" value="<%out.println(Mobile2);%>"></div>
		</div>
		
		<div class="container">  
			<div class="linepart">Fax</div>
			<div class="linepart"><input class="text"  maxLength="20" name="Off_fax"  id="txtOff_Fax" value="<%out.println(Fax2);%>"></div>
		</div>

		<hr>
		<div style="text-align:left">
		<strong>
			<font face=arial size="2" color=black >Please give your EMAIL ID as Login and enter a Password for it.</font><br/>
			<font color=red size=1>(For future update of data)</font>
		</strong>
		</div>

		
		<div class="container">  
			<div class="linepart">Email ID <super style="color:red">*</super></div>
			<div class="linepart"><input class="text"  name="LoginEmailid" readonly  maxlength="70" value="<%out.println(LoginMail);%>" onBlur="pleaseFill(this)"><br/><strong><font color="red" size="1" face="arial">(eg:-xyz@yahoo.com)</font></strong></div>
		</div>
		
		
		
		<div class="container">  
			<div class="linepart">Password <super style="color:red">*</super></div>
			<div class="linepart"><input class="text"  maxLength=50  type=password name="Password" onBlur="pleaseFill(this)"></div>
		</div>
		
		<div class="container">  
			<div class="linepart">Confirm Password <super style="color:red">*</super></div>
			<div class="linepart"><input class="text"  type=password  maxLength=50 name="confirmPassword" onBlur="pleaseFill(this)"></div>
		</div>

		<hr>
        <font face="Arial" size="2" color=black style="float:left"><strong >Please suggest e-mail addresses for other alumni so that we can encourage them to register on the website.</strong></font>

		<div class="container">  
			<div class="linepart">Friend's Email</div>
			<div class="linepart"><input class="text"  maxLength="70" name="email1"  id="txtEmail" value="<%out.println(F_email1);%>"></div>
		</div>
		
		<div class="container">  
			<div class="linepart">Friend's Email</div>
			<div class="linepart"><input class="text"  maxLength="70" name="email2" id="txtEmail" value="<%out.println(F_email2);%>"></div>
		</div>
		
		<div class="container">  
			<div class="linepart">Friend's Email</div>
			<div class="linepart"><input class="text"  maxLength="70" name="email3"  id="txtEmail" value="<%out.println(F_email3);%>"></div>
		</div>
		
		<div class="container">  
			<div class="linepart">Friend's Email</div>
			<div class="linepart"><input class="text"  maxLength="70" name="email4"  id="txtEmail" value="<%out.println(F_email4);}%>"></div>
		</div>
		<br/>
		<strong><font size="1"  face="Arial"><input class="text"  name="Submit" type="submit" value="Update Information" id="btnSubmit" style="background-color:Lightgrey"> 
          </font></strong>
		 
		</center>
		    </form> 
      <hr >
      
    <font face=arial size=2 color="#3333CC" style="float:left">All fields marked with an asterisk (<super style="color:red">*</super>)are mandatory. Otherwise your entry will be rejected.</font>
	
  
  <%
  stmt.close();
  con.close();
  %>
</div>
</body>
