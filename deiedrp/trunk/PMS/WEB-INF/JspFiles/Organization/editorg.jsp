<%@ page import="java.sql.*" language="java" pageEncoding="ISO-8859-1"%>
<%@ page import="org.dei.edrp.pms.dataBaseConnection.MyDataSource;"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%> 
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
 
<html> 
	<head>
	
				
	<link rel="stylesheet" href="style/style.css" type="text/css"></link></head>
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
	/*Get the parameter value of 'key' that is assigned in EditOrg.java*/
	String k=(String)request.getAttribute("key");
	/*fetching to that record of organisation table for updation which org_id is assigned in k variable.*/
	PreparedStatement ps=con.prepareStatement("select * from organisation where Org_Id=?");
    ps.setInt(1,Integer.parseInt(k));
    ResultSet rs1=ps.executeQuery();
    if(rs1.next()){}
    
	 %>
	 <html:javascript formName="editorgform" />
		<html:form action="editorg" onsubmit="return validateEditorgform(this);">
		<div id="main_title"><font color="#0044ff">
		     Edit To Desired Organisation:</font>
		  </div>
		<div>
		<html:errors property="iurl"/>
		</div>
		<table cellspacing="2" cellpadding="2" border="0" align="center">
		<tr class="form-element"><td class="form-label">
			Organisation Id :</td>
			<td class="form-widget">
			 <html:text property="orgid" size="40" value="<%=rs1.getString(1)%>" readonly="true"/><html:errors property="orgid"/>
			 </td></tr>
		
		<tr class="form-element">
		<td  class="form-label">
		Organisation Name : </td>
			<td class="form-widget"><html:text property="iname" size="40" value="<%=rs1.getString(2)%>"/><html:errors property="iname"/></td>
			<tr class="form-element"><td class="form-label">
			Address :</td>
			<td class="form-widget"><html:textarea property="iaddress" rows="2" cols="38" value="<%=rs1.getString(3)%>"/><html:errors property="iaddress"/>
			</td></tr>
		<tr class="form-element"><td class="form-label">
			City : </td>
			<td class="form-widget"><html:text property="icity" size="40" value="<%=rs1.getString(4)%>"/><html:errors property="icity"/>
			<tr class="form-element"><td class="form-label">
			State : </td>
			<td class="form-widget"><html:text property="istate" size="40" value="<%=rs1.getString(5)%>"/><html:errors property="state"/>
			<tr class="form-element"><td class="form-label">
			Pin Code :</td>
			<td class="form-widget"> <html:text property="ipin" size="40" value="<%=rs1.getString(6)%>"/><html:errors property="ipin"/>
			<tr class="form-element"><td class="form-label">
			Phone No : </td>
			<td class="form-widget"><html:text property="iphoneno" size="40" value="<%=rs1.getString(11)%>"/><html:errors property="iphoneno"/>
			<tr class="form-element"><td class="form-label">
			Fax No. :</td>
			<td class="form-widget"> <html:text property="ifax" size="40" value="<%=rs1.getString(7)%>"/><html:errors property="ifax"/>
			<tr class="form-element"><td class="form-label">
			WebSite :</td>
			<td class="form-widget"> <html:text property="iurl" size="40" value="<%=rs1.getString(8)%>"/>
			<tr class="form-element"><td class="form-label">
			Head : </td>
			<td class="form-widget"><html:text property="ihead" size="40" value="<%=rs1.getString(9)%>"/><html:errors property="ihead"/>
			<tr class="form-element"><td class="form-label">
			Email_id :</td>
			<td class="form-widget"> <html:text property="ieid" size="40" value="<%=rs1.getString(10)%>"/><html:errors property="ieid"/>
			<tr class="form-element"><td class="form-label">
			Description : </td>
			<td class="form-widget"><html:text property="description" size="40" value="<%=rs1.getString(12)%>"/><html:errors property="description"/>
			<tr><td></td></tr></table>
			<table align="center">
		<tr><td>
			<html:submit value="Save Change"/></td><td><html:reset/></td>
			<td><html:button property="back" value="Cancel" onclick="history.back();" /></td>
			</tr></table>
		</html:form>
		<%
			MyDataSource.freeConnection(con);
		 %>
	</body>
</html>
