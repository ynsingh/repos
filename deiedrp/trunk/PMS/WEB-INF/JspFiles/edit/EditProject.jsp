<%@ page import="java.sql.*" language="java" pageEncoding="ISO-8859-1"%>
<%@page import="dataBaseConnection.MyDataSource;"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%> 
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
 
<html> 
	<head>
	<html:javascript formName="editform" />
		<title>Edit Project</title>
				
	<link rel="stylesheet" href="style/style.css" type="text/css"></link>
	<script type="text/javascript" src="javascript/datetimepicker.js"></script>
	<script type="text/javascript" src="javascript/201a.js"></script>
	<script type="text/javascript" src="jscolor/jscolor.js"></script>
	</head>
	<body>
	<%
	String mysession=(String)session.getAttribute("mysession");
	if(mysession==null)
	{
		response.sendRedirect("login.jsp");
	 }
	%>
	<%!Connection con=null; %>
	<%
	con=MyDataSource.getConnection();
	/*Get the value of 'id' that is set in request scope and assigned in EditProjectAction.java*/
	String k=(String)request.getParameter("id");
	/*fetching to that record of project table for updation which project_id is assigned in k variable.*/
	PreparedStatement ps=con.prepareStatement("select * from project where Project_Id=? and Enable=0");
    ps.setString(1,k);
    ResultSet rs1=ps.executeQuery();
    if(rs1.next()){}
  		
	 %>
		<html:form action="/go3" onsubmit="return validateEditform(this);">
		<div id="main_title" align="left">
		    <font color="#0044ff">Edit to desired Project:</font>
		     </div>
		<div align="center"><html:errors property="sdate"/></div>
		<table cellspacing="2" cellpadding="2" border="0" align="center">
		<tr class="form-element"><td class="form-label">
			Project Id :</td>
			<td class="form-widget">
			 <html:text property="pid" size="40" value="<%=rs1.getString(1)%>" readonly="true" style="background-color:threedface;"/><html:errors property="pid"/>
			 </td></tr>
		
		<tr class="form-element">
		<td  class="form-label">
		Project Name : 
		</td>
		<td class="form-widget">
		<html:text property="pname" size="40" value="<%=rs1.getString(2)%>"/><html:errors property="pname"/></td></tr>
		 <tr class="form-element"><td  class="form-label">
			Start Date :</td>
			<td class="form-widget">
			<input type="text" name="sdate" id="sdate" value="<%=rs1.getString(3)%>"/><a href="javascript:NewCssCal('sdate','yyyymmdd')"><img src="img/cal.gif" width="16" height="16" border="0" alt="Pick a date"></a>
			(YYYY-MM-DD)</td></tr>
			<tr class="form-element">
			<td  class="form-label">
			Finished Date :</td>
			<td class="form-widget">
			<input type="text" name="fdate" id="fdate" value="<%=rs1.getString(4)%>"/><a href="javascript:NewCssCal('fdate','yyyymmdd')"><img src="img/cal.gif" width="16" height="16" border="0" alt="Pick a date"></a>
			(YYYY-MM-DD)
			</td></tr>
				<tr class="form-element"><td  class="form-label">
			Target Budget :</td><td> <html:text property="tbudget" size="40" value="<%=rs1.getString(5)%>"/><html:errors property="tbudget"/></td>
			</tr>
			<tr class="form-element"><td  class="form-label">
			Priority :</td>
			<td class="form-widget"> <html:select property="priority" value="<%=rs1.getString(6)%>">
			<% 
 	String[] st={"Low","Normal","High"};
	for(int i=0;i<=2;i++)
	{
	%>
	<html:option value="<%=st[i]%>"></html:option>
	<%
	}
	%>
	</html:select><html:errors property="priority"/></td>
			</tr>
			<tr class="form-element"><td  class="form-label">
			Status :</td><td class="form-widget"> <html:select property="status" value="<%=rs1.getString(7)%>">
			<% 
 String[] st={"In Progress","Complete","Planning","Canceled"};
for(int i=0;i<=3;i++)
	{
	%>
	<html:option value="<%=st[i]%>"></html:option>
	<%
	}
	%>
	</html:select><html:errors property="status"/></td>
			</tr>
		
		<tr class="form-element"><td  class="form-label">
			View Permission :</td><td class="form-widget"> <html:select property="viewPermission" value="<%=rs1.getString(8)%>">
			<% 
 String[] st={"For All","Only For Project Members"};
for(int i=0;i<=1;i++)
	{
	%>
	<html:option value="<%=st[i]%>"></html:option>
	<%
	}
	%>
	</html:select><html:errors property="viewPermission"/></td>
			</tr>
				<tr class="form-element"><td class="form-label">
				Gantt Chart Color :</td>
			<td class="form-widget">
			<div id="colorpicker201" class="colorpicker201"></div>
			<input type="text" name="gcolor" id="gcolor" size="9" readonly="readonly" value="<%=rs1.getString(9)%>"/>&nbsp;<input type="button" onclick="showColorGrid2('gcolor','sample_1');" value="...">&nbsp;<input type="text" ID="sample_1" size="1" value="">
			 </td></tr>
			<tr class="form-element">
			<td  class="form-label">
			Project Description :</td><td class="form-widget"> <html:textarea property="darea" rows="4" cols="32" value="<%=rs1.getString(10)%>"/><html:errors property="darea"/></td></tr>
			<tr><td></td></tr></table>
			<table align="center">
			<tr><td><html:submit value="Save Changes"/></td>
			<td><html:reset></html:reset></td>
			<td><html:button property="back" value="Cancel" onclick="history.back();" /></td>
			</tr></table>
		</html:form>
		<%
			MyDataSource.freeConnection(con);
		 %>
	</body>
</html>
