<%@ page language="java" import="java.sql.*" pageEncoding="ISO-8859-1"%>
<%@page import="dataBaseConnection.MyDataSource;"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'orgdetail.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	
	-->

  <link rel="stylesheet" href="style/main.css" type="text/css"></link>
  <link rel="stylesheet" href="style/style.css" type="text/css"></link>
  </head>
  
  <body >
  <%
	String mysession=(String)session.getAttribute("mysession");
	if(mysession==null)
	{
		response.sendRedirect("login.jsp");
	 }
	 %>
  <div id="main_title"><font color="#0044ff">Organisation Detail: </font></div>
  <br><br>
 <% 
   Connection con=MyDataSource.getConnection();
  	PreparedStatement ps=con.prepareStatement("select * from organisation where Org_Name like ?");
	/* The request.getParameter method returns the Organisation Name.*/
	//ps.setString(1,(String)session.getAttribute("orgkey"));
	ps.setString(1,(String)request.getParameter("orgkey"));
	ResultSet rs=ps.executeQuery();
	while(rs.next())
	{
	%>
	<table cellspacing="2" cellpadding="2" style="margin-left: 150px;width: 50%">

		<tr class="form-element">
			<td nowrap="nowrap" bgcolor="#95B9C7">ID:</td>
			<td class="hilite">
	<% 
	out.println(rs.getString(1));
	%></td>
		</tr>
		<tr>
			<td  nowrap="nowrap" bgcolor="#95B9C7">Organisation:</td>
			<td class="hilite" >
		<% 
	out.println(rs.getString(2));
	%></td>
		</tr>
				<tr>
			<td  nowrap="nowrap"  bgcolor="#95B9C7">Address:</td>
			<td class="hilite" >
			<%
				out.println(rs.getString(3));
				%></td>
		</tr>
		<tr>
			<td  nowrap="nowrap"  bgcolor="#95B9C7">City:</td>
			<td class="hilite" >
			<%
	out.println(rs.getString(4));
	
	%>
	</td>
		</tr>
		<tr>
			<td  nowrap="nowrap"  bgcolor="#95B9C7">State:</td>
			<td class="hilite" >
			<% out.println(rs.getString(5));
			
			%>
	</td>
		</tr>
		<tr>
			<td  nowrap="nowrap"  bgcolor="#95B9C7">Pin Code:</td>
			<td class="hilite" >
			<%
			 out.println(rs.getString(6));
			%>
	</td>
		</tr>
		<tr>
			<td  nowrap="nowrap" bgcolor="#95B9C7">Fax:</td>
			<td class="hilite" >
			<%
			out.println(rs.getString(7));
			%>
	</td>
		</tr>
		<tr>
			<td  nowrap="nowrap"  bgcolor="#95B9C7">Website URL:</td>
			<td class="hilite" >
			<%
			 //out.println(rs.getString(8));
			%>
			<a href="<%=rs.getString(8)%>/" target="_blank"><font color="#0000ff"><%=rs.getString(8)%></font></a>
			
	<br></td>
		</tr>
		<tr>
			<td  nowrap="nowrap"  bgcolor="#95B9C7">Head:</td>
			<td class="hilite" >
			<% out.println(rs.getString(9));
			
			%>
	</td>
		</tr>
		<tr>
			<td  nowrap="nowrap"  bgcolor="#95B9C7">Email:</td>
			<td class="hilite" >
			<% out.println(rs.getString(10));
			
			%>
	</td>
		</tr>
		<tr>
			<td  nowrap="nowrap"  bgcolor="#95B9C7">Phone No:</td>
			<td class="hilite" >
			<% out.println(rs.getString(11));
			
			%>
	</td>
		</tr>
		<tr>
			<td  nowrap="nowrap"  bgcolor="#95B9C7">Discription:</td>
			<td class="hilite" >
			<% out.println(rs.getString(12));
			}
		
			MyDataSource.freeConnection(con);
		
			%>
			</td></tr></table>
	<html:form action="/button">
    <br>
  <html:submit property="back" value="Back to Search Page" style="margin-left: 150px;"/>
  <html:button property="back" value="Back to Previous Page" onclick="history.back();" />
  </html:form>
  </body>
</html>
