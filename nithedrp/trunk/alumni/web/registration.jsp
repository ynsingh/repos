<%@ page import="java.util.Calendar"%>



<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
   response.setHeader( "Pragma", "no-cache" );
   response.setHeader( "Cache-Control", "no-cache" );
   response.setDateHeader( "Expires", 0 );
%>
<html>
<head>

<title></title>
<link rel="stylesheet" title="Style CSS" href="css/our.css" type="text/css" media="all" />
<script language="javascript" src = "validation.js">
</script>

</head>
<body>
	<%@ include file="includes/header.jsp" %>            		
     <div class="wrapper" >
	<h1 class="header">Registration</h1>

	<form style="background-color:#F5F5F5 padding:3em 2em 0.5em 2em; " name="theForm" action="includes/registration2.jsp" method="post" onsubmit= "return Form1_Validator(this)">
	<center>
		<div class="catheader">Personal Information</div>
		
		<div class="container" style="background-color:#c0c0c0;" >
			<div class="linepart">First Name <super style="color:red">*</super></div>
			<div class="linepart">Middle Name</div>
			<div class="linepart">Last Name <super style="color:red">*</super></div>
		</div>
		
		<div class="container">
			<div class="linepart"><input class="text"  class="text"  maxLength="30" name="Firstname"  id="txtFirstname" onBlur="pleaseFill(this)"></div>
			<div class="linepart"><input class="text"  maxLength="30" name="Midname"  id="txtMidname"></div>
			<div class="linepart"><input class="text"  maxLength="30" name="Lastname"  id="txtLastname" onBlur="pleaseFill(this)"></div>
		</div>
              
		<div class="container">  
			<div class="linepart">Alias/MaidenName</div>
			<div class="linepart">Nickname</div>
		</div>
         
		<div class="container">  
			<div class="linepart"><input class="text"  name="Priorname" id="txtPriorname" maxLength="30" ></div>
			<div class="linepart"><input class="text"  name="Nickname" id="txtNickname" maxLength="30" ></div>
		</div>
             
		<div class="container">  
			<div class="linepart">Gender <super style="color:red">*</super></div>
			<div class="linepart">Date of Birth <super style="color:red">*</super></div>
		</div>
		
		<div class="container">  
			<div class="linepart">
				<select name="Gender" size="1" id="lstGender">
					<option selected>Select Gender</option>
					<option value="Male">Male</option>
					<option value="Female">Female</option>
				</select>
			</div>
			
			<div class="linepart">
				<select name="day" size="1" id="txtDay">
					<option selected>DD</option>
					<%
						for(int i=1;i<=31;i++)
						{
							out.println("<option value='"+i+"'>"+i+"</option>");
						}
					%>
				</select>
				<select name="month" size="1" id="txtMonth">
					<option selected>MM</option>
					<%
						for(int i=1;i<=12;i++)
						{
							out.println("<option value='"+i+"'>"+i+"</option>");
						}
						%>
				</select>
				<select name="year" size="1" id="txtYear"> 
					<option selected>YYYY</option>
					<%
						Calendar cal = Calendar.getInstance(); 
						int currentyear = cal.get(Calendar.YEAR); 
						for(int i=1965;i<=currentyear;i++)
						{
							out.println("<option value='"+i+"'>"+i+"</option>");
						}
					%>		
				</select>	
			</div>
		</div>
		
		<p>
		<div class="container">  
			<div class="linepart">Residence Address</div>
			<div class="linepart"><textarea class="text"  class="text"  id=txtRes_street  name="res_street" rows=3 cols=29></textarea class="text" ></div>
		</div>
		
		<div class="container">  
			<div class="linepart">City <super style="color:red">*</super></div>
			<div class="linepart"><input class="text"  maxLength="30" name="res_City"  id="txtRes_city" onBlur="pleaseFill(this)"></div>
		</div>
		
		<div class="container">  
			<div class="linepart">State <super style="color:red">*</super></div>
			<div class="linepart"><input class="text"  maxLength="30" name="res_State"  id="txtRes_State" onBlur="pleaseFill(this)"></div>
		</div>
		
		<div class="container">  
			<div class="linepart">Country <super style="color:red">*</super></div>
			<div class="linepart"><input class="text"  maxLength="30" name="res_Country"  id="txtRes_country" onBlur="pleaseFill(this)"></div>
		</div>
		
		<div class="container">  
			<div class="linepart">Pin <super style="color:red">*</super></div>
			<div class="linepart"><input class="text"  maxLength="20" name="res_pincode"  id="txtRes_Pincode" onBlur="pleaseFill(this)"></div>
		</div>
        
		<div class="container">  
			<div class="linepart">Phone/Mobile</div>
			<div class="linepart"><input class="text"  maxLength="20" name="Res_Phone1"  id="txtRes_Phone1"></div>
			<div class="linepart"><input class="text"  maxLength="20" name="Res_Phone2"  id="txtRes_Phone2"></div>
		</div>
		
		<div class="container">  
			<div class="linepart">Fax</div>
			<div class="linepart"><input class="text"  maxLength="20" name="Res_fax"  id="txtRes_Fax"></div>
		</div>
		
		<div class="container">  
			<div class="linepart">Email <super style="color:red">*</super></div>
			<div class="linepart"><input class="text"  maxLength="70" name="email"  id="txtEmail" onBlur="pleaseFill(this)"></div>
		</div>
		
		<div class="container">  
			<div class="linepart">Webpage</div>
			<div class="linepart"><input class="text"  maxLength="70" name="URL"  value="http://" id="txtURL"></div>
		</div>
		</p>
	
		<div class="catheader">Academic Information</div>
		<div >
     
        </div>
		<div class="container" style="background-color:#c0c0c0;">
			<div class="linepart1">Degree <super style="color:red">*</super></div>
			<div class="linepart1">Department/Centre <super style="color:red">*</super></div>
			<div class="linepart1" >Batch(Passing Year) <super style="color:red" >*</super></div>
			<div class="linepart1">Hostel <super style="color:red">*</super></div>
		</div>
            
		<div class="container">
			<div class="linepart1">
				<select name="Degree1" size="1" id="lstDegree1" >
					<option value="" selected >Select Degree</option>
					<% 
							ResultSet rst=null;
							rst=stmt.executeQuery("select * from degreelist");
							while (rst.next())
							{
								String degree = rst.getString("degree");
								out.println("<option Value='"+degree+"'>"+degree+"</option> ");
							}
					%>
				</select>
			</div>
			<div class="linepart1">
				<select name="Dept1" size="1" id="lstDept1" style="width:12em" > 
					<option value="" selected >Select Department </option>
					<% 
							ResultSet rst1=null;
							rst1=stmt.executeQuery("select * from departmentlist");
							while (rst1.next())
							{
								String department = rst1.getString("department");
								out.println("<option Value='"+department+"'>"+department+"</option> ");
							}
					%>
				</select>
			</div>
			<div class="linepart1" >
				<select name="Batch1" size="1" id="lstBatch1"> 
					<option value="" selected>Select Batch</option> 
					<% 
							for(int i=1989;i<=currentyear;i++)
						{
							out.println("<option value='"+i+"'>"+i+"</option>");
						}
					   %>
				</select>
			</div>
			<div class="linepart1">
				<select name="Hostel1" size="1" id="lstHostel1">
					<option value="" selected >Select Hostel</option>
					<% 
							ResultSet rst3=null;
							rst3=stmt.executeQuery("select * from hostellist");
							while (rst3.next())
							{
								String hostel = rst3.getString("hostel");
								out.println("<option Value='"+hostel+"'>"+hostel+"</option> ");
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
								out.println("<option Value='"+degree+"'>"+degree+"</option> ");
							}
					%>
				</select>
			</div>
			<div class="linepart1">
				<select name="Dept2" size="1" id="lstDept2" style="width:12em" > 
					<option value="" selected >Select Department </option>
					<% 
							rst1=stmt.executeQuery("select * from departmentlist");
							while (rst1.next())
							{
								String department = rst1.getString("department");
								out.println("<option Value='"+department+"'>"+department+"</option> ");
							}
					%>
				</select>
			</div>
			<div class="linepart1" >
				<select name="Batch2" size="1" id="lstBatch2"> 
					<option value="" selected>Select Batch</option> 
					<% 
								for(int i=1989;i<=currentyear;i++)
						{
							out.println("<option value='"+i+"'>"+i+"</option>");
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
								out.println("<option Value='"+hostel+"'>"+hostel+"</option> ");
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
								out.println("<option Value='"+degree+"'>"+degree+"</option> ");
							}
							rst.close();
					%>
				</select>
			</div>
			<div class="linepart1">
				<select name="Dept3" size="1" id="lstDept3" style="width:12em" > 
					<option value="" selected >Select Department </option>
					<% 
							rst1=stmt.executeQuery("select * from departmentlist");
							while (rst1.next())
							{
								String department = rst1.getString("department");
								out.println("<option Value='"+department+"'>"+department+"</option> ");
							}
							rst1.close();
					%>
				</select>
			</div>
			<div class="linepart1" >
				<select name="Batch3" size="1" id="lstBatch3"> 
					<option value="" selected>Select Batch</option> 
					<% 
								for(int i=1989;i<=currentyear;i++)
						{
							out.println("<option value='"+i+"'>"+i+"</option>");
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
								out.println("<option Value='"+hostel+"'>"+hostel+"</option> ");
							}
							rst3.close();
							stmt.close();
							con.close();
					%>
				</select>	
			</div>
		</div>
		
		
		<div class="catheader">Professional Information</div>
		
		<div class="container">  
			<div class="linepart">Occupation <super style="color:red">*</super></div>
			<div class="linepart">
				<select name="Occupation" size="1" id="txtOccupation">
					<option selected value="">Select Occupation</option>
					<option value="Accounting/Finance">Accounting/Finance</option>
					<option value="Computer related (Internet)">Computer related (Internet)</option>
					<option value="Computer related (other)">Computer related (other)</option>
					<option value="Consulting">Consulting</option>
					<option value="Customer service/support">Customer service/support</option>
					<option value="Education/training">Education/training</option>
					<option value="Engineering">Engineering</option>
					<option value="Executive/senior management">Executive/senior management</option>
					<option value="General administrative/supervisory">General administrative/supervisory</option>
					<option value="Government/Military">Government/Military</option>
					<option value="Homemaker">Homemaker</option>
					<option value="Manufacturing/production/operations">Manufacturing/production/operations</option>
					<option value="Professional (medical, legal, etc.)">Professional (medical, legal, etc.)</option>
					<option value="Research and development">Research and development</option>
					<option value="Retired">Retired</option>
					<option value="Sales/marketing/advertising">Sales/marketing/advertising</option>
					<option value="Self-employed/owner">Self-employed/owner</option>
					<option value="Tradesman/craftsman">Tradesman/craftsman</option>
					<option value="Unemployed/Between Jobs">Unemployed/Between Jobs</option>
					<option value="Higher Studies">Higher Studies</option>
					<option value="Other">Other</option>
				</select>
			</div>
		</div>

		<div class="container">  
			<div class="linepart">Oganization</div>
			<div class="linepart"><input class="text"  id="txtOrganization" name="Organization" maxLength="50" ></div>
		</div>
		
		<div class="container">  
			<div class="linepart">Designation</div>
			<div class="linepart"><input class="text"  id="txtDesg" name="Desg" maxLength="50" ></div>
		</div>
		
		<div class="container">  
			<div class="linepart">Areas of Interest <super style="color:red">*</super></div>
			<div class="linepart"><input class="text"  id="txtinterest" name="Interest" maxLength="100"  onBlur="pleaseFill(this)"></div>
		</div>
		
		<div class="container">  
			<div class="linepart">Office Address <super style="color:red">*</super></div>
			<div class="linepart"><textarea class="text"  id=txtOff_street name="Off_addr" cols=29 rows="3" onBlur="pleaseFill(this)"></textarea class="text" ></div>
		</div>
		
		<div class="container">  
			<div class="linepart">City <super style="color:red">*</super></div>
			<div class="linepart"><input class="text"  maxLength="30" name="Off_City"  id="txtOff_city" onBlur="pleaseFill(this)"></div>
		</div>
		
		<div class="container">  
			<div class="linepart">State <super style="color:red">*</super></div>
			<div class="linepart"><input class="text"  maxLength="30" name="Off_State"  id="txtOff_state" onBlur="pleaseFill(this)"></div>
		</div>
		
		<div class="container">  
			<div class="linepart">Country <super style="color:red">*</super></div>
			<div class="linepart"><input class="text"  maxLength="30" name="Off_Country"  id="txtOff_country" onBlur="pleaseFill(this)"></div>
		</div>
		
		<div class="container">  
			<div class="linepart">Pin <super style="color:red">*</super></div>
			<div class="linepart"><input class="text"  maxLength="20" name="Off_pincode"  id="txtoff_pincode" onBlur="pleaseFill(this)"></div>
		</div>
		
		<div class="container">  
			<div class="linepart">Phone/Mobile</div>
			<div class="linepart"><input class="text"  maxLength="20" name="Off_Phone1" id="txtOff_Phone1"></div>
			<div class="linepart"><input class="text"  maxLength="20" name="Off_Phone2"  id="txtOff_Phone2"></div>
		</div>
		
		<div class="container">  
			<div class="linepart">Fax</div>
			<div class="linepart"><input class="text"  maxLength="20" name="Off_fax"  id="txtOff_Fax"></div>
		</div>

		<hr >
		<div style="text-align:left">
		<strong>
			<font face=arial size="2" color=black >Please give your EMAIL ID as Login and enter a Password for it.</font><br/> 
			<font color=red size=1>(For future update of data)</font>
		</strong>
		</div>

		
		<div class="container">  
			<div class="linepart">Email ID <super style="color:red">*</super></div>
			<div class="linepart"><input class="text"  name="LoginEmailid"  maxlength="70" onBlur="pleaseFill(this)"><br/><strong><font color="red" size="1" face="arial">(eg:-xyz@yahoo.com)</font></strong></div>
		</div>
		
		
		
		<div class="container">  
			<div class="linepart">Password <super style="color:red">*</super></div>
			<div class="linepart"><input class="text"  maxLength=50  type=password name="Password" onBlur="pleaseFill(this)"></div>
		</div>
		
		<div class="container">  
			<div class="linepart">Confirm Password <super style="color:red">*</super></div>
			<div class="linepart"><input class="text"  type=password  maxLength=50 name="confirmPassword" onBlur="pleaseFill(this)"></div>
		</div>

		<hr >
        <font face="Arial" size="2" color=black style="float:left"><strong >Please suggest e-mail addresses for other alumni so that we can encourage them to register on the website.</strong></font>

		<div class="container">  
			<div class="linepart">Friend's Email</div>
			<div class="linepart"><input class="text"  maxLength="70" name="email1"  id="txtEmail"></div>
		</div>
		
		<div class="container">  
			<div class="linepart">Friend's Email</div>
			<div class="linepart"><input class="text"  maxLength="70" name="email2"  id="txtEmail"></div>
		</div>
		
		<div class="container">  
			<div class="linepart">Friend's Email</div>
			<div class="linepart"><input class="text"  maxLength="70" name="email3"  id="txtEmail"></div>
		</div>
		
		<div class="container">  
			<div class="linepart">Friend's Email</div>
			<div class="linepart"><input class="text"  maxLength="70" name="email4"  id="txtEmail"></div>
		</div>
		<br/>
		<strong><font size="1" face="Arial"><input class="text"  name="Submit" type="submit" value=" Submit " id="btnSubmit" style="background-color:Lightgrey" >&nbsp;&nbsp;&nbsp; <input class="text"  id="btnReset" name="reset1" type="reset" value=" Reset " style="background-color:Lightgrey"> 
          </font></strong>
		  </center>
       </form> 
             <hr>
    <font face=arial size=2 color="#3333CC" style="float:left">All fields marked with an asterisk (<super style="color:red">*</super>)are mandatory. Otherwise your entry will be rejected.</font>
	


</div>
</body>
