<%@ page language="java" pageEncoding="ISO-8859-1"%>
<%@ page import="javax.sql.rowset.CachedRowSet;" %>
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
	<%!
		CachedRowSet crs_org=null;
	 %>
	<%
	
	/*Get the value of 'crs' that is set in request scope in EditForwardOrgAction*/
	crs_org=(CachedRowSet)request.getAttribute("crs");
	 %>
	 <html:javascript formName="editorgform" />
		<html:form action="editorg" onsubmit="return validateEditorgform(this);">
		<div id="main_title">
		 <font color="#0044ff"> Edit to desired organization:</font>
	  </div><br>
		  <div align="center">
		  	<html:errors property="iname"/>
		  </div>
		  <br><br>
		<input type="hidden" name="orgid" value="<%= crs_org.getString(1)%>" id="orgid" size="20" readonly="readonly"/>
		<html:errors property="orgid"/>
		<input type="hidden" name="oldorgname" value="<%= crs_org.getString(2)%>" id="oldorgname" size="40" readonly="readonly"/>
		<html:errors property="oldorgname"/>
		
		<table cellspacing="1" cellpadding="6" width="40%" border="0" align="center">
		<tr class="form-element">
		<td  class="form-label">
		Organization Name : </td>
			<td class="form-widget"><html:text property="iname" size="40" value="<%=crs_org.getString(2)%>"/></td>
			<tr class="form-element"><td class="form-label">
			Address :</td>
			<td class="form-widget"><html:textarea property="iaddress" rows="2" cols="38" value="<%=crs_org.getString(3)%>"/><html:errors property="iaddress"/>
			</td></tr>
		<tr class="form-element"><td class="form-label">
			City : </td>
			<td class="form-widget"><html:text property="icity" size="40" value="<%=crs_org.getString(4)%>"/><html:errors property="icity"/>
			<tr class="form-element"><td class="form-label">
			State : </td>
			<td class="form-widget"><html:text property="istate" size="40" value="<%=crs_org.getString(5)%>"/><html:errors property="state"/>
			<tr class="form-element"><td class="form-label">
			Phone No : </td>
			<td class="form-widget"><html:text property="iphoneno" size="40" value="<%=crs_org.getString(6)%>"/><html:errors property="iphoneno"/>
			<tr class="form-element"><td class="form-label">
			Fax No. :</td>
			<td class="form-widget"> <html:text property="ifax" size="40" value="<%=crs_org.getString(7)%>"/><html:errors property="ifax"/>
			<tr class="form-element"><td class="form-label">
			WebSite :</td>
			<td class="form-widget"> <html:text property="iurl" size="40" value="<%=crs_org.getString(8)%>"/>
			</td></tr></table><br>
			<table align="center">
		<tr><td>
			<html:submit value="Save Change"/></td><td><html:reset/></td>
			<td><html:button property="back" value="Cancel" onclick="history.back();" /></td>
			</tr></table>
		</html:form>
		
	</body>
</html>
