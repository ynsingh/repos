<%@ page language="java" pageEncoding="ISO-8859-1"%>
<%@ page import="javax.sql.rowset.CachedRowSet;" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%> 
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
 
<html> 
	<head>
	<title>Edit Portal</title>
				
	<link rel="stylesheet" href="style/style.css" type="text/css"></link>
	</head>
	<body>
	<%
	String mysession=(String)session.getAttribute("mysession");
	if(mysession==null)
	{
		response.sendRedirect("login.jsp");
	 }
	%>
	<%!
		CachedRowSet crs_portal=null;
	 %>
	<%
	/*Get the value of 'crs' that is set in request scope in EditForwardPortalAction*/
	crs_portal=(CachedRowSet)request.getAttribute("crs");
	%>
	 	<html:javascript formName="editportalform" />
		<html:form action="/editingportal" onsubmit="return validateEditportalform(this);">
			<div id="main_title">
		 <font color="#0044ff"> Edit to desired portal:</font>
	  </div><br>
		  <div align="center">
		  	<html:errors property="portalname"/>
		  </div>
		  <br>
		<input type="hidden" name="portalid" value="<%= crs_portal.getString(1)%>" id="portalid" size="20" readonly="readonly"/>
		<html:errors property="portalid"/>
		<input type="hidden" name="oldportalname" value="<%= crs_portal.getString(2)%>" id="oldportalname" size="20" readonly="readonly"/>
		<html:errors property="oldportalname"/>
		
		<table cellspacing="1" cellpadding="6" border="0" align="center">
		<tr><td> <font color="#0044ff" size="2"> Portal :</font></td></tr>
		<tr class="form-element">
		<td  class="form-label">
		
		Portal Name : 
		</td>
		<td class="form-widget">
		<html:text property="portalname" size="35" value="<%=crs_portal.getString(2)%>"/></td></tr>
		<tr class="form-element">
		<td class="form-label">
			Portal Description :</td><td class="form-widget">
			<html:textarea property="portaldescription" value="<%=crs_portal.getString(3)%>" rows="3" cols="33"/>
			<html:errors property="portaldescription"/></td></tr>
			</table><br><br>
			<table align="center">
			<tr><td><html:submit value="Save Changes"/></td>
			<td><html:reset></html:reset></td>
			<td><html:button property="back" value="Cancel" onclick="history.back();" /></td>
			</tr></table>
		</html:form>
		
	</body>
</html>
