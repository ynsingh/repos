<%@ page import="java.sql.*" language="java" pageEncoding="ISO-8859-1"%>
<%@ page import="in.ac.dei.edrp.pms.dataBaseConnection.MyDataSource"%>
<%@ page import="in.ac.dei.edrp.pms.viewer.checkRecord;"%>
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
	 
   function roleGenerate() {
   var userId = DWRUtil.getValue("userId");
   var orgportal = DWRUtil.getValue("orgportal");
  DynamicList.roleList(userId,orgportal,function(data)
  {
  	DWRUtil.removeAllOptions("roleName");
  	DWRUtil.addOptions("roleName",data);
   }
  ); 
 }
  </script>
	</head>
		
	<body onload="roleGenerate();">
	<%!
		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		int flag=0;
		String uid=null;
		String user=null;
		 %>
	<%
	String mysession=(String)session.getAttribute("mysession");
	if(mysession==null)
	{
		response.sendRedirect("login.jsp");  
	}
	else
	{
	user=request.getParameter("userid");
	%>
	<html:javascript formName="projassignform" />
	<html:form action="/assign" onsubmit="return validateProjassignform(this);" >			
	
		<%
		uid=(String)session.getAttribute("uid");
		con=MyDataSource.getConnection();
	 	if(checkRecord.duplicacyChecker("Authority","login","login_User_ID",uid).equalsIgnoreCase("Super Admin"))
			{
				flag=1;
			}
			else flag=0;
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
		        Add Members into project:
		  	<%
		  	 }
		   	%>
		 </font> </div>
		 <%
		//For member/user
		if(flag==0)
		{
		%>
		  <br><br><br><br>
		  <table cellspacing="2" cellpadding="10" width="40%" border="0" align="center">
	<tr><td>
	<input type="hidden" name="orgportal" id="orgportal" 
	value="<%=(String)session.getAttribute("validOrgInPortal") %>" size="20" readonly="readonly"/>
		<html:errors property="orgportal"/>
	</td></tr>
			
		<tr class="form-element">
		<td  class="form-label">User E-mail id :</td>
		<td class="form-widget"> 
		 <html:select property="userId" indexed="userId" value="<%=user%>" onchange="roleGenerate();" style="width: 270px;">
			<html:option value="--Select--"></html:option>
			<% 
			try{
				ps=con.prepareStatement("select valid_user_id from user_in_org where "+
			"valid_orgportal=? order by valid_user_id asc");
			ps.setString(1,(String)session.getAttribute("validOrgInPortal"));
			rs=ps.executeQuery();
			while(rs.next())
				{
			%>
			<html:option value="<%= rs.getString(1)%>"></html:option>
			<%
			}
			}
			catch(SQLException e){}
			 %>
			</html:select>
			<font color="red" size="2">*</font>
		<html:errors property="userId"/>
		</td></tr>
		<tr></tr>
		<tr class="form-element">
		<td  class="form-label">Role Name :</td>
		<td class="form-widget">
		<select id="roleName" name="roleName" style="width: 270px;">
		 </select><font color="red" size="2">*</font>
		 <html:errors property="roleName"/>
			 </td></tr>
			 <tr></tr>
			<tr class="form-element">
			<td  class="form-label">
			Project Name :</td><td class="form-widget">
			 <html:select property="projectName" style="width: 270px;">
			<html:option value="--Select--"></html:option>
			<% 
			try{
				ps=con.prepareStatement("select p.project_name from project p where "+
			"p.enable=0 and p.valid_org_inportal=? order by p.project_name asc");
			ps.setString(1,(String)session.getAttribute("validOrgInPortal"));
			rs=ps.executeQuery();
			while(rs.next())
				{
			%>
			<html:option value="<%= rs.getString(1)%>"></html:option>
			<%
			}
			}
			catch(SQLException e){}
			 %>
			</html:select><font color="red" size="2">*</font>
			<html:errors property="projectName"/>
			</td></tr>
		  </table>
		  <table align="center" style="padding-top: 50px;">	
		 <tr><td>
			<html:submit value="Assign Project"/>
			<html:reset onclick="roleGenerate();"/>
			<html:button property="back" value="Back" onclick="history.back();" />
          </td></tr>
			</table>
			<%}
			else
			{
			%>
			 <input type="hidden" name="orgportal" id="orgportal" 
		value="<%=(String)session.getAttribute("validOrgInPortal") %>"/>
		 <html:hidden indexed="userId" property="userId" 
		value="<%=user%>"/>
		<html:errors property="userId"/>
		<select id="roleName" name="roleName" style="visibility: hidden">
		 </select>
			 <%} %>
			</html:form>
			<%
				MyDataSource.freeConnection(con);
				}
		 	%>
	</body>
</html>

			