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
	 <table cellspacing="1" cellpadding="6" border="0" align="center">
		 
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
			ps=con.prepareStatement("select portal_name from portal");
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
			con=MyDataSource.getConnection();
			ps=con.prepareStatement("select org_name from organisation");
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
		<td class="form-widget"><html:text property="emailid" value="<%=uid%>" size="40"/><font color="red" size="2">*</font>
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
			con=MyDataSource.getConnection();
			ps=con.prepareStatement("select role_name from role where ValidOrgPortal IS NULL");
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
		 <table align="center" >	
			<tr></tr><tr></tr><tr></tr>		  
			<tr><td>
			<html:submit value="Done"/>
			<html:reset/>
			<html:button property="back" value="Back" onclick="history.back();" />
            </td></tr>
			</table>
		</html:form>
	</body>
</html>

