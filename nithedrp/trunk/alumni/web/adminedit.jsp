<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
   response.setHeader( "Pragma", "no-cache" );
   response.setHeader( "Cache-Control", "no-cache" );
   response.setDateHeader( "Expires", 0 );
%>


<%


if( session.getAttribute("login") != "true" )
    {

   response.sendRedirect("../index.jsp");

   }

%>



<HTML>
	<HEAD>
		<TITLE> </TITLE>
		<link rel="stylesheet" title="Style CSS" href="css/our.css" type="text/css" media="all" />
	</HEAD>
	<BODY>
	
<script type="text/javascript">

var firstrowoffset = 1; // first data row start at
var tablename = 'ewlistmain'; // table name
var usecss = true; // use css

var rowclass = 'ewTableRow'; // row class
var rowaltclass = 'ewTableAltRow'; // row alternate class
var rowmoverclass = 'ewTableHighlightRow'; // row mouse over class
var rowselectedclass = 'ewTableSelectRow'; // row selected class
var roweditclass = 'ewTableEditRow'; // row edit class
var rowcolor = '#DDA0DD'; // row color
var rowaltcolor = '#FFE4C4'; // row alternate color
var rowmovercolor = '#FFB6C1'; // row mouse over color
var rowselectedcolor = '#FAFAD2'; // row selected color
var roweditcolor = '#6B8E23'; // row edit color

</script>
	
<%@ include file="includes/header.jsp" %>
	<div class="wrapper">
	
	 <font face="Arial" size="4" color=black><strong>Please click  on the link below to update it.</strong></font>
	 <hr>
			<div class="linepart"><a href="degree.jsp"><font size="4" color=blue>&#187;Update Degree</font></a></div>
			<div class="linepart"><a href="department.jsp"><font size="4" color=blue>&#187;Update Department</font></a></div>
			<div class="linepart"><a href="hostel.jsp"><font size="4" color=blue>&#187;Update Hostel</font></a></div></br>
					
			<div class="linepart" >
				<% 
	if((session.getAttribute("username")!=null)&&(session.getAttribute("type").equals("Administrator")))
	{
			ResultSet rst=null;
			rst=stmt.executeQuery("select * from degreelist");
			%>
			<table class="ewTable" style="border-collapse: collapse" bordercolor="#111111" cellpadding="0" cellspacing="0" width="607">
			<tr class="ewTableHeader" >
			<td valign="top" style="white-space: nowrap;" width="110" align="center"><strong>Degree</strong></td>
			</tr>
		<%
			while (rst.next())
			{
				String degree = rst.getString("degree");
				%>
				<tr class="ewTableRow" onmouseover='ew_mouseover(this);' onmouseout='ew_mouseout(this);' onclick='ew_click(this);'>
		<td valign="top" style="white-space: nowrap;" width="110" align="center"><%out.println(""+degree + "</td></tr>");
			}
			out.println("</TABLE>");
			rst.close();
		%>
		
			
			</div>
		<div class="linepart">
			<% 
			ResultSet rst1=null;
			rst1=stmt.executeQuery("select * from departmentlist");
			%>
			<table class="ewTable" style="border-collapse: collapse" bordercolor="#111111" cellpadding="0" cellspacing="0" width="607">
			<tr class="ewTableHeader" >
			<td valign="top" style="white-space: nowrap;" width="110" align="center"><strong>Department</strong></td></tr>
			<%
				while (rst1.next())
			{
				String department = rst1.getString("department");
				%>
				<tr class="ewTableRow" onmouseover='ew_mouseover(this);' onmouseout='ew_mouseout(this);' onclick='ew_click(this);'>
		<td valign="top" style="white-space: nowrap;" width="110" align="center"><%out.println(""+department + "</td></tr>");
			}
			out.println("</TABLE>");
			rst1.close();
		%>
			</div>	
		<div class="linepart">
		<% 
			ResultSet rst3=null;
			rst3=stmt.executeQuery("select * from hostellist");
			
			%>
			<table class="ewTable" style="border-collapse: collapse" bordercolor="#111111" cellpadding="0" cellspacing="0" width="607">
			<tr class="ewTableHeader" >
			<td valign="top" style="white-space: nowrap;" width="110" align="center"><strong>Hostel</strong></td></tr>
			<%
			while (rst3.next())
			{
				String hostel = rst3.getString("hostel");
				%>
				<tr class="ewTableRow" onmouseover='ew_mouseover(this);' onmouseout='ew_mouseout(this);' onclick='ew_click(this);'>
				<td valign="top" style="white-space: nowrap;" width="110" align="center"><%out.println(""+hostel + "</td></tr>");
			}
			out.println("</TABLE>");
			rst3.close();
			stmt.close();
			con.close();
			}
			else
			response.sendRedirect("adminlogin.jsp"); 
%>
			</div>
		</form>
		</div>
	</BODY>
</HTML>