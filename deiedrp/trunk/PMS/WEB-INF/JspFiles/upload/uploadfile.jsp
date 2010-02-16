<%@ page import="java.sql.*" language="java" pageEncoding="ISO-8859-1"%>
<%@page import="org.dei.edrp.pms.dataBaseConnection.MyDataSource;"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%> 
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>

<html> 
	<head>
		<title>JSP for UploadFile form</title>
	<link rel="stylesheet" href="style/main.css" type="text/css"></link><link rel="stylesheet" href="style/style.css" type="text/css"></link>
	</head>
	
	<body bgcolor="silvergreen">
	<%
	String mysession=(String)session.getAttribute("mysession");
	if(mysession==null)
	{
		response.sendRedirect("login.jsp");
	 }
	%>
	<%!Connection con=null; %>
	<html:javascript formName="uploadfileform" dynamicJavascript="true" staticJavascript="true" />
	<html:form action="/fpath" enctype="multipart/form-data" onsubmit="return validateUploadfileform(this);">
		 <div id=main_title><font color="#0044ff">Upload File:</font></div><br>
		  <br><br>
		  <table align="center" cellspacing="2" cellpadding="2" border="0" bgcolor="#ffffff">
		<tr class="form-element">
		<td  class="form-label">
			Upload File: 
			</td>
		<td class="form-widget">
		<html:file property="uploadedFile"/><html:errors property="uploadedFile"/></td></tr>
		
		<%
			/*Established the database connection.*/
			con=MyDataSource.getConnection();
			/* Getting the User_iD which is currently logged in. */
   			String uid=(String)session.getAttribute("uid");
			 //out.println("uid="+uid);
		PreparedStatement ps=con.prepareStatement("select Authority from login where User_ID=?");
		ps.setString(1,uid);
		 ResultSet rs=ps.executeQuery();
			rs.next();
			//out.println("authority="+rs.getString(1));
			if(rs.getString(1).equals("Admin"))
			{
				ps=con.prepareStatement("select Project_Name from project");
				rs=ps.executeQuery();
			}
			else
			{
			ps=con.prepareStatement("select distinct p.Project_Name from validatetab v,project p"+
			" where v.User_ID=? and p.Project_ID=v.Project_ID");
				ps.setString(1,uid);
				rs=ps.executeQuery();
			}
		%>
			<tr class="form-element">
			<td  class="form-label">
			For The Project :</td><td> <html:select property="projectName"><html:errors property="projectName"/>
			<html:option value="--Select--"></html:option>
		<% 
					try{
			/*Fetching the project name from project table.*/
				while(rs.next())
				{
			%>
			<%--Bind the project name in the drop down list.--%>
			<html:option value="<%= rs.getString(1)%>"></html:option>
			<%
			}
			}
			catch(SQLException e){}
			 %>
			</html:select>
			</td></tr>
		
			<tr class="form-element">
		<td  class="form-label">
			File Description : 
			</td>
		<td class="form-widget">
		
			 <html:textarea property="fdescription"/><html:errors property="fdescription"/>
			 </td></tr>
		
		</table>
		<br/>
		<table align="center">
		<tr><td>
		
			<html:submit value="Upload"/>&nbsp;<html:reset/>
			<html:button property="back" value="Back" onclick="history.back();" />
			</td>
			</tr></table>
		</html:form>
		<%
			MyDataSource.freeConnection(con);
		 %>
	</body>
</html>

