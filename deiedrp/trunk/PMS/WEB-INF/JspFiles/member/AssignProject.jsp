<%@ page import="java.sql.*" language="java" pageEncoding="ISO-8859-1"%>
<%@page import="dataBaseConnection.MyDataSource;"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%> 
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
 
<html> 
	<head>
		<title>JSP for Assign Project</title>
	<link rel="stylesheet" href="style/main.css" type="text/css"></link>
				
 <!-- You have to include these two JavaScript files -->
        <script type='text/javascript' src='dwr/engine.js'></script>
        <script type='text/javascript' src='dwr/util.js'></script>
	<!-- This JavaScript file is generated specifically for your application -->
         <script type='text/javascript' src='dwr/interface/DynamicList.js'></script>
	
	<script type="text/javascript">
	 
   function projectGenerate() {
   var name = DWRUtil.getValue("orgName");
   var uname = DWRUtil.getValue("uname");
  DynamicList.orgProjectList(name,uname,function(data)
  {
  	DWRUtil.removeAllOptions("projectName");
  	DWRUtil.addOptions("projectName",data);
   }
  ); 
 }
  </script>
	</head>
	<body>
	<%
	String mysession=(String)session.getAttribute("mysession");
	if(mysession==null)
	{
		response.sendRedirect("login.jsp");  
	}
	
	%>
	<html:javascript formName="projassignform" />
	<html:form action="/assign" onsubmit="return validateProjassignform(this);">			
		<%!
		Connection con=null;
		 Statement st=null;
		PreparedStatement ps=null;
		 ResultSet rs=null;
		 int flag=0;
	
		 %>
		<%
		String uid=(String)session.getAttribute("uid");
	 	con=MyDataSource.getConnection();
	 	ps=con.prepareStatement("select Authority from login where User_ID=?");
		ps.setString(1,uid);
		rs=ps.executeQuery();
		rs.next();
			
			if(rs.getString(1).equals("Admin"))
			{
				flag=1;
			}
			else
				flag=0;
		%>
		
		<div id="main_title"><font color="#0044ff">
		    <%
		    if(flag==1)
		     {
		     %>
		     Assigning Project:
		     <%
		     }
		     else
		      {
		      %>
		        Add Members:
		  	<%
		  		}
		   	%>
		 </font> </div>
		  <br><br>
		  <table cellspacing="3" cellpadding="3" border="0" align="center">
	
		<%
		//For member/user
		if(flag==0)
		{
		 %>
		 <tr>
		<td>
		<input type="hidden" name="uname" id="uname" value="<%=uid %>" size="20" readonly="readonly"/>
			<html:errors property="uname"/>
</td></tr>
			<tr class="form-element">
		<td  class="form-label">

			User Email ID : 
			</td>
	<td class="form-widget"> 
	<html:text property="userId" size="40"/>
			<html:errors property="userId"/>
	
			</td></tr>
		<tr></tr>
				<tr class="form-element">
				<td  class="form-label">
			Organisation Name :</td><td class="form-widget">
	<select id="orgName" name="orgName" style="width: 270px;" onchange=projectGenerate() onClick=projectGenerate()>
			<option>--Select--</option>
		
		<% 
		try{
			 uid=(String)session.getAttribute("uid");
			%>
			<%
			ps=con.prepareStatement("select distinct o.Org_Name from validatetab v,organisation o"+
			" where v.User_ID=? and v.Org_ID=o.Org_ID order by o.Org_Name asc");
			ps.setString(1,uid);
			rs=ps.executeQuery();
				while(rs.next())
				{
			%>
			<option><%=rs.getString(1)%></option>
			<%
			}
			}
			catch(SQLException e){}
			 %>
			 </select><html:errors property="orgName"/>
			 </td></tr>
			 <tr></tr>
			<tr class="form-element">
			<td class="form-label">
			Project Name :</td><td class="form-widget">
			 <select id="projectName" name="projectName" style="width: 270px;">
			</select>
			</td></tr>
			<%
			}
			else
			{
			//for admin
			Statement st=con.createStatement();
			 %>
		<tr class="form-element">
		<td  class="form-label">
			User Email ID : 
			</td>
	<td class="form-widget"> 
	<html:text property="userId" size="40"/>
			<html:errors property="userId"/>
			</td>
			</tr>
			<tr></tr>
			<tr class="form-element" >
			<td  class="form-label">
			Organisation Name :</td><td class="form-widget"> 
			<html:select property="orgName" size="1" style="width:270px;">
						<html:option value="--Select--"></html:option>
		<% 
					try{
					
			rs=st.executeQuery("select distinct Org_Name from organisation order by Org_Name asc");
				while(rs.next())
				{
			%>
			<html:option value="<%= rs.getString(1)%>"></html:option>
			<%
			}
			}
			catch(SQLException e){}
			 %>
			 </html:select><html:errors property="orgName"/>
			 </td></tr><tr></tr>
			 <tr class="form-element">
			<td  class="form-label">
			Project Name :</td><td class="form-widget">
			 <html:select property="projectName" style="width: 270px;">
			<html:option value="--Select--"></html:option>
		<% 
					try{
			 rs=st.executeQuery("select distinct Project_Name from project where Assigned=0 order by Project_Name asc");
				while(rs.next())
				{
			%>
			<html:option value="<%= rs.getString(1)%>"></html:option>
			<%
			}
			}
			catch(SQLException e){}
			 %>
			</html:select><html:errors property="projectName"/>
			</td></tr>
			
			 <%
			 }
			 %>
			  <tr></tr>
			  </table>
			  <table align="center" >	
			  <tr></tr><tr></tr><tr></tr>		  
			<tr><td>
			<html:submit value="Assign Project"/>
			<html:reset/>
			<html:button property="back" value="Back" onclick="history.back();" />
            </td>
			</tr>
			</table>
			</html:form>
			<%
				MyDataSource.freeConnection(con);
		 	%>
	</body>
</html>

			