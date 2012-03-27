<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
   response.setHeader( "Pragma", "no-cache" );
   response.setHeader( "Cache-Control", "no-cache" );
   response.setDateHeader( "Expires", 0 );
%>
<HTML>
	<HEAD>
		<TITLE> </TITLE>
			<link rel="stylesheet" title="Style CSS" href="css/our.css" type="text/css" media="all" />
	</HEAD>
	
	<BODY style="text-align:center">
      <%@ include file="includes/header.jsp" %>
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

		<form action="includes/updatehostel.jsp" method="post" name="Update">
		
		<div align="center">
  <center>
<%
	if((session.getAttribute("username")!=null)&&(session.getAttribute("type").equals("Administrator")))
	{
			ResultSet rst=null;
			rst=stmt.executeQuery("select * from hostellist");
			
			%>
			</br>
			<table class="ewTable" style="border-collapse: collapse" bordercolor="#111111" cellpadding="0" cellspacing="0" width="607">
			<tr class="ewTableHeader" >
			<td valign="top" style="white-space: nowrap;" width="110" align="center"><strong>Hostel</strong></td></tr>
			<%
			while (rst.next())
			{
				String hostel = rst.getString("hostel");
				%>
				<tr class="ewTableRow" onmouseover='ew_mouseover(this);' onmouseout='ew_mouseout(this);' onclick='ew_click(this);'>
		<td valign="top" style="white-space: nowrap;" width="110" align="center"><%out.println(""+hostel + "</td></tr>");
			}
			out.println("</TABLE>");
			rst.close();
		%>
</br></br>
				<tr>			
					<td>Add Hostel </td>
					<td>: <input type="text" name="new_hostel" size="30"></input></td>
							
					<td>Remove Hostel </td>
					<td>: <select name="old_hostel" size="1" >
					<option value="" selected >Select Hostel</option>
					<% 
							ResultSet rst1=null;
							rst1=stmt.executeQuery("select * from hostellist");
							while (rst1.next())
							{
								String hostel = rst1.getString("hostel");
								out.println("<option Value='"+hostel+"'>"+hostel+"</option> ");
							}
							rst1.close();
							stmt.close();
							con.close();
		}
	else
	response.sendRedirect("index.jsp"); 
					%>
						</select>	
						</td>
				</tr>
			
		</br></br>
		<input type="submit" value="Update"></input>
		</center>
</form>
	</BODY>
</HTML>