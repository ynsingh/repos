<%@ page language="java" pageEncoding="ISO-8859-1"%>
<%@ page import="java.sql.*"%>
<%@page import="in.ac.dei.edrp.pms.dataBaseConnection.MyDataSource;"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%> 
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
 
<html> 
	<head>
		<title>JSP for OrgPOrtalForm form</title>
		<link rel="stylesheet" href="style/main.css" type="text/css"></link>
		<link rel="stylesheet" href="style/style.css" type="text/css"></link>
		
	</head>
	<body>
	<%!
	Connection con=null;
	ResultSet rs=null;
	PreparedStatement ps=null;
	String uid=null;
	%>
	<html:javascript formName="addorginportalform" dynamicJavascript="true"	staticJavascript="true" />
	<html:form action="/add_org_in_portal" onsubmit="return validateAddorginportalform(this)">
		<div id="main_title">
			<font color="#0044ff"><bean:message key="addportalmessage"/> :</font>
		</div><br><br><div align="center">
		  	<html:errors property="addorgportal"/>
		   </div>
		  <br><br>
	 <table cellspacing="2" cellpadding="10" width="40%" border="0" align="center">
		 
		<tr></tr>
		<tr class="form-element">
		 <td class="form-label">
			<bean:message key="addportalname"/> :</td>
		<td class="form-widget">
			<select id="portalname" name="portalname" style="width: 270px;">
			<option>--Select--</option>
			<%
			uid=request.getParameter("userid");
			try{
			con=MyDataSource.getConnection();
			ps=con.prepareStatement("select distinct portal_name from portal");
			rs=ps.executeQuery();
			while(rs.next())
			{
			 %>
			<option><%=rs.getString(1)%></option>
			<%
			}
			}catch(Exception e){
			System.out.println("Error in addorginportal jsp!!!"+e);
			}
			 %>
			 </select><font color="red" size="2">*</font>
			<html:errors property="portalname"/>
		</td></tr>
		<tr></tr>		
		<tr class="form-element">
		 <td class="form-label">
			<bean:message key="addportalorg"/> :</td>
		<td class="form-widget">
			<select id="organisation" name="organisation" style="width: 270px;">
			<option>--Select--</option>
			<%
			try{
			//con=MyDataSource.getConnection();
			ps=con.prepareStatement("select distinct org_name from organisation");
			rs=ps.executeQuery();
			while(rs.next())
			{
			 %>
			<option><%=rs.getString(1)%></option>
			<%
			}
			}catch(Exception e)
			{
			System.out.println("Error in addorginportal jsp!!!"+e);
			}
			 %>
			 </select><font color="red" size="2">*</font>
			<html:errors property="organisation"/>
		</td></tr><tr></tr>	
		<tr class="form-element">
		 <td class="form-label">
		<bean:message key="addportalemailid"/> :</td>
		<td class="form-widget"> 
		 <html:select property="emailid" indexed="emailid" value="<%=uid%>" style="width: 270px;">
			<html:option value="--Select--"></html:option>
			<% 
			try{
			ps=con.prepareStatement("SELECT valid_user_id FROM user_in_org");
				ResultSet rs1=ps.executeQuery();
				if(rs1.next())
				ps=con.prepareStatement("SELECT distinct u.user_id FROM user_info u,user_in_org uio,"+
				"user_role_in_org uro where u.user_id=uio.valid_user_id and "+
				"uio.valid_key=uro.valid_key or u.user_id not in "+
				"(select l.login_user_id from login l)order by u.user_id asc");
				else
				ps=con.prepareStatement("SELECT distinct u.user_id FROM user_info u"+
				" where u.user_id not in "+
				"(select l.login_user_id from login l)order by u.user_id asc");
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
		<html:errors property="emailid"/>
		</td></tr>
		<tr></tr>
		<tr class="form-element">
		 <td class="form-label">
			<bean:message key="addportalrole"/> :</td>
		<td class="form-widget">
			<select id="role" name="role" style="width: 270px;">
			<option>--Select--</option>
			<%
			try{
			//con=MyDataSource.getConnection();
			ps=con.prepareStatement("select distinct role_name from role where ValidOrgPortal IS NULL");
			rs=ps.executeQuery();
			while(rs.next())
			{
			 %>
			<option><%=rs.getString(1)%></option>
			<%
			}
			}catch(Exception e){
			System.out.println("Error in addorginportal jsp!!!"+e);
			}
			 %>
			</select><font color="red" size="2">*</font><html:errors property="role"/>
			 </td></tr>
		</table>
		 <table style="padding-top: 40px;padding-left: 370px" >	
			<tr></tr><tr></tr><tr></tr>		  
			<tr><td>
			<html:submit value="Done" styleClass="butStnd"/>
			<html:reset styleClass="butStnd"/>
			<html:button property="back" value="Back" styleClass="butStnd" onclick="history.back();" />
            </td></tr>
			</table>
		</html:form>
				<%
					MyDataSource.freeConnection(con);
				%>
	</body>
</html>







