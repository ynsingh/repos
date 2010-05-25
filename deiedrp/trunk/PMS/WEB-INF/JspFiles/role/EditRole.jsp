<%@ page language="java" pageEncoding="ISO-8859-1"%>
<%@ page import="in.ac.dei.edrp.pms.role.RoleList" %>
<%@ page import="javax.sql.rowset.CachedRowSet;" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%> 
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
 
<html> 
	<head>
	<title>Edit Role</title>
				
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
		CachedRowSet crs_userRole=null;
	 %>
	<%
	/*Get the value of 'crs' that is set in request scope in EditForwardPortalAction*/
	crs_userRole=(CachedRowSet)request.getAttribute("crs");
	if(((String)session.getAttribute("authority")).equalsIgnoreCase("User"))//the authority is user or super admin
		{
		request.setAttribute("roleAutority", new RoleList((String)session.getAttribute("uid"),
				Integer.parseInt((String)session.getAttribute("validOrgInPortal")),
				(String)session.getAttribute("role_in_org")));
		}
	%>
	
	 	<html:javascript formName="editroleform" />
		<html:form action="/editingrole" onsubmit="return validateEditroleform(this);">
			<div id="main_title">
		 <font color="#0044ff"> Edit to desired role:</font>
	  </div><br>
		  <div align="center">
		  	<html:errors property="rolename"/>
		  	<html:errors property="rolemsg"/>
		   </div>
		  <br>
		<input type="hidden" name="roleid" value="<%= crs_userRole.getString(1)%>" id="roleid" size="20" readonly="readonly"/>
		<html:errors property="roleid"/>
		<input type="hidden" name="oldrolename" value="<%= crs_userRole.getString(2)%>" id="oldrolename" size="20" readonly="readonly"/>
		<html:errors property="oldrolename"/>
		
		<table cellspacing="1" cellpadding="6" border="0" align="center">
		<tr><td> <font color="#0044ff" size="2"> Role :</font></td></tr>
		<tr class="form-element">
		<td  class="form-label">
		
		Role Name : 
		</td>
		<td class="form-widget">
		<html:text property="rolename" size="35" value="<%=crs_userRole.getString(2)%>"/></td></tr>
		<tr class="form-element">
		<td class="form-label">
			Role Description :</td><td class="form-widget">
			<html:textarea property="roledescription" value="<%=crs_userRole.getString(3)%>" rows="3" cols="33"/>
			<html:errors property="roledescription"/></td></tr>
			<%if(((String)session.getAttribute("authority")).equalsIgnoreCase("User"))
			{
			//System.out.println("user in edit role");
			 %>
			 <logic:iterate id="var" property="list" name="roleAutority" >
		<tr><td> <font color="#0044ff" size="2"> Authorities :</font></td></tr>
	  
	  <tr class="form-element">
	   <logic:equal name="var" property="addrole" value="Allow">
	  <td  class="form-label">
		Add Role :</td><td>
		<INPUT TYPE=RADIO NAME="addrole" id="addrole" VALUE="Allow" <%if(crs_userRole.getString(22).equals("Allow")){%> checked="checked" <%} %>>Allow
		<INPUT TYPE=RADIO NAME="addrole" id="addrole" VALUE="Not Allow" <%if(crs_userRole.getString(22).equals("Not Allow")){%> checked="checked" <%} %> >Not Allow
		</td></logic:equal>
		<logic:equal name="var" property="addrole" value="Not Allow">
	  <td  class="form-label">
		Add Role :</td><td>
		<INPUT TYPE=RADIO NAME="addrole" id="addrole" disabled="disabled" VALUE="Allow" <%if(crs_userRole.getString(22).equals("Allow")){%> checked="checked" <%} %>>Allow
		<INPUT TYPE=RADIO NAME="addrole" id="addrole" disabled="disabled" VALUE="Not Allow" <%if(crs_userRole.getString(22).equals("Not Allow")){%> checked="checked" <%} %> >Not Allow
		</td></logic:equal>
		<logic:equal name="var" property="editrole" value="Allow">
		<td  class="form-label">
		Edit/Remove Role :</td><td>
		<INPUT TYPE=RADIO NAME="editrole" id="editrole" VALUE="Allow" <%if(crs_userRole.getString(23).equals("Allow")){%> checked="checked" <%} %>>Allow
		<INPUT TYPE=RADIO NAME="editrole" id="editrole" VALUE="Not Allow" <%if(crs_userRole.getString(23).equals("Not Allow")){%> checked="checked" <%} %> >Not Allow
		</td></logic:equal>
		<logic:equal name="var" property="editrole" value="Not Allow">
		<td  class="form-label">
		Edit/Remove Role :</td><td>
		<INPUT TYPE=RADIO NAME="editrole" id="editrole" disabled="disabled" VALUE="Allow" <%if(crs_userRole.getString(23).equals("Allow")){%> checked="checked" <%} %>>Allow
		<INPUT TYPE=RADIO NAME="editrole" id="editrole" disabled="disabled" VALUE="Not Allow" <%if(crs_userRole.getString(23).equals("Not Allow")){%> checked="checked" <%} %> >Not Allow
		</td></logic:equal>
		</tr>
		<tr class="form-element">
		<logic:equal name="var" property="addorg" value="Allow">
		<td  class="form-label">
		Add Organization :</td><td>
		<INPUT TYPE=RADIO NAME="addorg" id="addorg" VALUE="Allow" <%if(crs_userRole.getString(9).equals("Allow")){%> checked="checked" <%} %>>Allow
		<INPUT TYPE=RADIO NAME="addorg" id="addorg" VALUE="Not Allow" <%if(crs_userRole.getString(9).equals("Not Allow")){%> checked="checked" <%} %> >Not Allow
		</td></logic:equal>
		<logic:equal name="var" property="addorg" value="Not Allow">
		<td  class="form-label">
		Add Organization :</td><td>
		<INPUT TYPE=RADIO NAME="addorg" id="addorg" disabled="disabled" VALUE="Allow" <%if(crs_userRole.getString(9).equals("Allow")){%> checked="checked" <%} %>>Allow
		<INPUT TYPE=RADIO NAME="addorg" id="addorg" disabled="disabled" VALUE="Not Allow" <%if(crs_userRole.getString(9).equals("Not Allow")){%> checked="checked" <%} %> >Not Allow
		</td></logic:equal>
		<logic:equal name="var" property="editorg" value="Allow">
		<td  class="form-label">
		Edit/Remove Organization :</td><td>
		<INPUT TYPE=RADIO NAME="editorg" id="editorg" VALUE="Allow" <%if(crs_userRole.getString(10).equals("Allow")){%> checked="checked" <%} %>>Allow
		<INPUT TYPE=RADIO NAME="editorg" id="editorg" VALUE="Not Allow" <%if(crs_userRole.getString(10).equals("Not Allow")){%> checked="checked" <%} %> >Not Allow
		</td></logic:equal>
		<logic:equal name="var" property="editorg" value="Not Allow">
		<td  class="form-label">
		Edit/Remove Organization :</td><td>
		<INPUT TYPE=RADIO NAME="editorg" id="editorg" disabled="disabled" VALUE="Allow" <%if(crs_userRole.getString(10).equals("Allow")){%> checked="checked" <%} %>>Allow
		<INPUT TYPE=RADIO NAME="editorg" id="editorg" disabled="disabled" VALUE="Not Allow" <%if(crs_userRole.getString(10).equals("Not Allow")){%> checked="checked" <%} %> >Not Allow
		</td></logic:equal>
		</tr>
		<tr class="form-element">
		<logic:equal name="var" property="addproject" value="Allow">
		<td class="form-label">
		Add Project :</td><td>
		<INPUT TYPE=RADIO NAME="addproject" id="addproject" VALUE="Allow" <%if(crs_userRole.getString(11).equals("Allow")){%> checked="checked" <%} %>>Allow
		<INPUT TYPE=RADIO NAME="addproject" id="addproject" VALUE="Not Allow" <%if(crs_userRole.getString(11).equals("Not Allow")){%> checked="checked" <%} %> >Not Allow
		</td></logic:equal>
		<logic:equal name="var" property="addproject" value="Not Allow">
		<td class="form-label">
		Add Project :</td><td>
		<INPUT TYPE=RADIO NAME="addproject" id="addproject" disabled="disabled" VALUE="Allow" <%if(crs_userRole.getString(11).equals("Allow")){%> checked="checked" <%} %>>Allow
		<INPUT TYPE=RADIO NAME="addproject" id="addproject" disabled="disabled" VALUE="Not Allow" <%if(crs_userRole.getString(11).equals("Not Allow")){%> checked="checked" <%} %> >Not Allow
		</td></logic:equal>
		<logic:equal name="var" property="editproject" value="Allow">
		<td class="form-label">
		Edit/Remove Project :</td><td>
		<INPUT TYPE=RADIO NAME="editproject" id="editproject" VALUE="Allow" <%if(crs_userRole.getString(12).equals("Allow")){%> checked="checked" <%} %>>Allow
		<INPUT TYPE=RADIO NAME="editproject" id="editproject" VALUE="Not Allow" <%if(crs_userRole.getString(12).equals("Not Allow")){%> checked="checked" <%} %> >Not Allow
		</td></logic:equal>
		<logic:equal name="var" property="editproject" value="Not Allow">
		<td class="form-label">
		Edit/Remove Project :</td><td>
		<INPUT TYPE=RADIO NAME="editproject" id="editproject" disabled="disabled" VALUE="Allow" <%if(crs_userRole.getString(12).equals("Allow")){%> checked="checked" <%} %>>Allow
		<INPUT TYPE=RADIO NAME="editproject" id="editproject" disabled="disabled" VALUE="Not Allow" <%if(crs_userRole.getString(12).equals("Not Allow")){%> checked="checked" <%} %> >Not Allow
		</td></logic:equal>
		</tr>
		<tr class="form-element">
		<logic:equal name="var" property="addmember" value="Allow">
		<td class="form-label">
		Add Member :</td><td>
		<INPUT TYPE=RADIO NAME="addmember" id="addmember" VALUE="Allow" <%if(crs_userRole.getString(13).equals("Allow")){%> checked="checked" <%} %>>Allow
		<INPUT TYPE=RADIO NAME="addmember" id="addmember" VALUE="Not Allow" <%if(crs_userRole.getString(13).equals("Not Allow")){%> checked="checked" <%} %> >Not Allow
		</td></logic:equal>
		<logic:equal name="var" property="addmember" value="Not Allow">
		<td class="form-label">
		Add Member :</td><td>
		<INPUT TYPE=RADIO NAME="addmember" id="addmember" disabled="disabled" VALUE="Allow" <%if(crs_userRole.getString(13).equals("Allow")){%> checked="checked" <%} %>>Allow
		<INPUT TYPE=RADIO NAME="addmember" id="addmember" disabled="disabled" VALUE="Not Allow" <%if(crs_userRole.getString(13).equals("Not Allow")){%> checked="checked" <%} %> >Not Allow
		</td></logic:equal>
		<logic:equal name="var" property="editmember" value="Allow">
		<td  class="form-label">
		Edit/Remove Member :</td><td>
		<INPUT TYPE=RADIO NAME="editmember" id="editmember" VALUE="Allow" <%if(crs_userRole.getString(14).equals("Allow")){%> checked="checked" <%} %>>Allow
		<INPUT TYPE=RADIO NAME="editmember" id="editmember" VALUE="Not Allow" <%if(crs_userRole.getString(14).equals("Not Allow")){%> checked="checked" <%} %> >Not Allow
		</td></logic:equal>
		<logic:equal name="var" property="editmember" value="Not Allow">
		<td  class="form-label">
		Edit/Remove Member :</td><td>
		<INPUT TYPE=RADIO NAME="editmember" id="editmember" disabled="disabled" VALUE="Allow" <%if(crs_userRole.getString(14).equals("Allow")){%> checked="checked" <%} %>>Allow
		<INPUT TYPE=RADIO NAME="editmember" id="editmember" disabled="disabled" VALUE="Not Allow" <%if(crs_userRole.getString(14).equals("Not Allow")){%> checked="checked" <%} %> >Not Allow
		</td></logic:equal>
		</tr>
		<tr class="form-element">
		<logic:equal name="var" property="assignproject" value="Allow">
		<td class="form-label">
		Assign Project :</td><td>
		<INPUT TYPE=RADIO NAME="assignproject" id="assignproject" VALUE="Allow" <%if(crs_userRole.getString(15).equals("Allow")){%> checked="checked" <%} %>>Allow
		<INPUT TYPE=RADIO NAME="assignproject" id="assignproject" VALUE="Not Allow" <%if(crs_userRole.getString(15).equals("Not Allow")){%> checked="checked" <%} %> >Not Allow
		</td></logic:equal>
		<logic:equal name="var" property="assignproject" value="Not Allow">
		<td class="form-label">
		Assign Project :</td><td>
		<INPUT TYPE=RADIO NAME="assignproject" id="assignproject" disabled="disabled" VALUE="Allow" <%if(crs_userRole.getString(15).equals("Allow")){%> checked="checked" <%} %>>Allow
		<INPUT TYPE=RADIO NAME="assignproject" id="assignproject" disabled="disabled" VALUE="Not Allow" <%if(crs_userRole.getString(15).equals("Not Allow")){%> checked="checked" <%} %> >Not Allow
		</td></logic:equal>
		<logic:equal name="var" property="editauthority" value="Allow">
		<td  class="form-label">
		Edit Member Authority :</td><td>
		<INPUT TYPE=RADIO NAME="editauthority" id="editauthority" VALUE="Allow" <%if(crs_userRole.getString(16).equals("Allow")){%> checked="checked" <%} %>>Allow
		<INPUT TYPE=RADIO NAME="editauthority" id="editauthority" VALUE="Not Allow" <%if(crs_userRole.getString(16).equals("Not Allow")){%> checked="checked" <%} %> >Not Allow
		</td></logic:equal>
		<logic:equal name="var" property="editauthority" value="Not Allow">
		<td  class="form-label">
		Edit Member Authority :</td><td>
		<INPUT TYPE=RADIO NAME="editauthority" id="editauthority" disabled="disabled" VALUE="Allow" <%if(crs_userRole.getString(16).equals("Allow")){%> checked="checked" <%} %>>Allow
		<INPUT TYPE=RADIO NAME="editauthority" id="editauthority" disabled="disabled" VALUE="Not Allow" <%if(crs_userRole.getString(16).equals("Not Allow")){%> checked="checked" <%} %> >Not Allow
		</td></logic:equal>
		</tr>
		<tr class="form-element">
		<logic:equal name="var" property="assigntask" value="Allow">
		<td class="form-label">
		Assign Task :</td><td>
		<INPUT TYPE=RADIO NAME="assigntask" id="assigntask" VALUE="Allow" <%if(crs_userRole.getString(17).equals("Allow")){%> checked="checked" <%} %>>Allow
		<INPUT TYPE=RADIO NAME="assigntask" id="assigntask" VALUE="Not Allow" <%if(crs_userRole.getString(17).equals("Not Allow")){%> checked="checked" <%} %> >Not Allow
		</td></logic:equal>
		<logic:equal name="var" property="assigntask" value="Not Allow">
		<td class="form-label">
		Assign Task :</td><td>
		<INPUT TYPE=RADIO NAME="assigntask" id="assigntask" disabled="disabled" VALUE="Allow" <%if(crs_userRole.getString(17).equals("Allow")){%> checked="checked" <%} %>>Allow
		<INPUT TYPE=RADIO NAME="assigntask" id="assigntask" disabled="disabled" VALUE="Not Allow" <%if(crs_userRole.getString(17).equals("Not Allow")){%> checked="checked" <%} %> >Not Allow
		</td></logic:equal>
		<logic:equal name="var" property="edittask" value="Allow">
		<td  class="form-label">
		Edit/Remove Task :</td><td>
		<INPUT TYPE=RADIO NAME="edittask" id="edittask" VALUE="Allow" <%if(crs_userRole.getString(18).equals("Allow")){%> checked="checked" <%} %>>Allow
		<INPUT TYPE=RADIO NAME="edittask" id="edittask" VALUE="Not Allow" <%if(crs_userRole.getString(18).equals("Not Allow")){%> checked="checked" <%} %> >Not Allow
		</td></logic:equal>
		<logic:equal name="var" property="edittask" value="Not Allow">
		<td  class="form-label">
		Edit/Remove Task :</td><td>
		<INPUT TYPE=RADIO NAME="edittask" id="edittask" disabled="disabled" VALUE="Allow" <%if(crs_userRole.getString(18).equals("Allow")){%> checked="checked" <%} %>>Allow
		<INPUT TYPE=RADIO NAME="edittask" id="edittask" disabled="disabled" VALUE="Not Allow" <%if(crs_userRole.getString(18).equals("Not Allow")){%> checked="checked" <%} %> >Not Allow
		</td></logic:equal>
		</tr>
		<tr class="form-element">
		<logic:equal name="var" property="uploaddoc" value="Allow">
		<td class="form-label">
		Upload Documents :</td><td>
		<INPUT TYPE=RADIO NAME="uploaddoc" id="uploaddoc" VALUE="Allow" <%if(crs_userRole.getString(19).equals("Allow")){%> checked="checked" <%} %>>Allow
		<INPUT TYPE=RADIO NAME="uploaddoc" id="uploaddoc" VALUE="Not Allow" <%if(crs_userRole.getString(19).equals("Not Allow")){%> checked="checked" <%} %> >Not Allow
		</td></logic:equal>
		<logic:equal name="var" property="uploaddoc" value="Not Allow">
		<td class="form-label">
		Upload Documents :</td><td>
		<INPUT TYPE=RADIO NAME="uploaddoc" id="uploaddoc" disabled="disabled" VALUE="Allow" <%if(crs_userRole.getString(19).equals("Allow")){%> checked="checked" <%} %>>Allow
		<INPUT TYPE=RADIO NAME="uploaddoc" id="uploaddoc" disabled="disabled" VALUE="Not Allow" <%if(crs_userRole.getString(19).equals("Not Allow")){%> checked="checked" <%} %> >Not Allow
		</td></logic:equal>
		<logic:equal name="var" property="downloaddoc" value="Allow">
		<td  class="form-label">
		Download/Remove Documents :</td><td>
		<INPUT TYPE=RADIO NAME="downloaddoc" id="downloaddoc" VALUE="Allow" <%if(crs_userRole.getString(20).equals("Allow")){%> checked="checked" <%} %>>Allow
		<INPUT TYPE=RADIO NAME="downloaddoc" id="downloaddoc" VALUE="Not Allow" <%if(crs_userRole.getString(20).equals("Not Allow")){%> checked="checked" <%} %> >Not Allow
		</td></logic:equal>
		<logic:equal name="var" property="downloaddoc" value="Not Allow">
		<td  class="form-label">
		Download/Remove Documents :</td><td>
		<INPUT TYPE=RADIO NAME="downloaddoc" id="downloaddoc" disabled="disabled" VALUE="Allow" <%if(crs_userRole.getString(20).equals("Allow")){%> checked="checked" <%} %>>Allow
		<INPUT TYPE=RADIO NAME="downloaddoc" id="downloaddoc" disabled="disabled" VALUE="Not Allow" <%if(crs_userRole.getString(20).equals("Not Allow")){%> checked="checked" <%} %> >Not Allow
		</td></logic:equal>
		</tr>
		</logic:iterate>
		<%} 
else
{
//System.out.println("superadmin");
%>
<tr><td> <font color="#0044ff" size="2"> Authorities :</font></td></tr>
	  
	  <tr class="form-element"><td  class="form-label">
		Add Role :</td><td>
		<INPUT TYPE=RADIO NAME="addrole" id="addrole" VALUE="Allow" <%if(crs_userRole.getString(22).equals("Allow")){%> checked="checked" <%} %>>Allow
		<INPUT TYPE=RADIO NAME="addrole" id="addrole" VALUE="Not Allow" <%if(crs_userRole.getString(22).equals("Not Allow")){%> checked="checked" <%} %> >Not Allow
		</td><td  class="form-label">
		Edit/Remove Role :</td><td>
		<INPUT TYPE=RADIO NAME="editrole" id="editrole" VALUE="Allow" <%if(crs_userRole.getString(23).equals("Allow")){%> checked="checked" <%} %>>Allow
		<INPUT TYPE=RADIO NAME="editrole" id="editrole" VALUE="Not Allow" <%if(crs_userRole.getString(23).equals("Not Allow")){%> checked="checked" <%} %> >Not Allow
		</td></tr>
		<tr class="form-element"><td  class="form-label">
		Add Organization :</td><td>
		<INPUT TYPE=RADIO NAME="addorg" id="addorg" VALUE="Allow" <%if(crs_userRole.getString(9).equals("Allow")){%> checked="checked" <%} %>>Allow
		<INPUT TYPE=RADIO NAME="addorg" id="addorg" VALUE="Not Allow" <%if(crs_userRole.getString(9).equals("Not Allow")){%> checked="checked" <%} %> >Not Allow
		</td><td  class="form-label">
		Edit/Remove Organization :</td><td>
		<INPUT TYPE=RADIO NAME="editorg" id="editorg" VALUE="Allow" <%if(crs_userRole.getString(10).equals("Allow")){%> checked="checked" <%} %>>Allow
		<INPUT TYPE=RADIO NAME="editorg" id="editorg" VALUE="Not Allow" <%if(crs_userRole.getString(10).equals("Not Allow")){%> checked="checked" <%} %> >Not Allow
		</td></tr>
		<tr class="form-element"><td class="form-label">
		Add Project :</td><td>
		<INPUT TYPE=RADIO NAME="addproject" id="addproject" VALUE="Allow" <%if(crs_userRole.getString(11).equals("Allow")){%> checked="checked" <%} %>>Allow
		<INPUT TYPE=RADIO NAME="addproject" id="addproject" VALUE="Not Allow" <%if(crs_userRole.getString(11).equals("Not Allow")){%> checked="checked" <%} %> >Not Allow
		</td><td  class="form-label">
		Edit/Remove Project :</td><td>
		<INPUT TYPE=RADIO NAME="editproject" id="editproject" VALUE="Allow" <%if(crs_userRole.getString(12).equals("Allow")){%> checked="checked" <%} %>>Allow
		<INPUT TYPE=RADIO NAME="editproject" id="editproject" VALUE="Not Allow" <%if(crs_userRole.getString(12).equals("Not Allow")){%> checked="checked" <%} %> >Not Allow
		</td></tr>
		<tr class="form-element"><td class="form-label">
		Add Member :</td><td>
		<INPUT TYPE=RADIO NAME="addmember" id="addmember" VALUE="Allow" <%if(crs_userRole.getString(13).equals("Allow")){%> checked="checked" <%} %>>Allow
		<INPUT TYPE=RADIO NAME="addmember" id="addmember" VALUE="Not Allow" <%if(crs_userRole.getString(13).equals("Not Allow")){%> checked="checked" <%} %> >Not Allow
		</td><td  class="form-label">
		Edit/Remove Member :</td><td>
		<INPUT TYPE=RADIO NAME="editmember" id="editmember" VALUE="Allow" <%if(crs_userRole.getString(14).equals("Allow")){%> checked="checked" <%} %>>Allow
		<INPUT TYPE=RADIO NAME="editmember" id="editmember" VALUE="Not Allow" <%if(crs_userRole.getString(14).equals("Not Allow")){%> checked="checked" <%} %> >Not Allow
		</td></tr>
		<tr class="form-element"><td class="form-label">
		Assign Project :</td><td>
		<INPUT TYPE=RADIO NAME="assignproject" id="assignproject" VALUE="Allow" <%if(crs_userRole.getString(15).equals("Allow")){%> checked="checked" <%} %>>Allow
		<INPUT TYPE=RADIO NAME="assignproject" id="assignproject" VALUE="Not Allow" <%if(crs_userRole.getString(15).equals("Not Allow")){%> checked="checked" <%} %> >Not Allow
		</td><td  class="form-label">
		Edit Member Authority :</td><td>
		<INPUT TYPE=RADIO NAME="editauthority" id="editauthority" VALUE="Allow" <%if(crs_userRole.getString(16).equals("Allow")){%> checked="checked" <%} %>>Allow
		<INPUT TYPE=RADIO NAME="editauthority" id="editauthority" VALUE="Not Allow" <%if(crs_userRole.getString(16).equals("Not Allow")){%> checked="checked" <%} %> >Not Allow
		</td></tr>
		<tr class="form-element"><td class="form-label">
		Assign Task :</td><td>
		<INPUT TYPE=RADIO NAME="assigntask" id="assigntask" VALUE="Allow" <%if(crs_userRole.getString(17).equals("Allow")){%> checked="checked" <%} %>>Allow
		<INPUT TYPE=RADIO NAME="assigntask" id="assigntask" VALUE="Not Allow" <%if(crs_userRole.getString(17).equals("Not Allow")){%> checked="checked" <%} %> >Not Allow

		</td><td  class="form-label">
		Edit/Remove Task :</td><td>
		<INPUT TYPE=RADIO NAME="edittask" id="edittask" VALUE="Allow" <%if(crs_userRole.getString(18).equals("Allow")){%> checked="checked" <%} %>>Allow
		<INPUT TYPE=RADIO NAME="edittask" id="edittask" VALUE="Not Allow" <%if(crs_userRole.getString(18).equals("Not Allow")){%> checked="checked" <%} %> >Not Allow
		</td></tr>
		<tr class="form-element"><td class="form-label">
		Upload Documents :</td><td>
		<INPUT TYPE=RADIO NAME="uploaddoc" id="uploaddoc" VALUE="Allow" <%if(crs_userRole.getString(19).equals("Allow")){%> checked="checked" <%} %>>Allow
		<INPUT TYPE=RADIO NAME="uploaddoc" id="uploaddoc" VALUE="Not Allow" <%if(crs_userRole.getString(19).equals("Not Allow")){%> checked="checked" <%} %> >Not Allow
		</td><td  class="form-label">
		Download/Remove Documents :</td><td>
		<INPUT TYPE=RADIO NAME="downloaddoc" id="downloaddoc" VALUE="Allow" <%if(crs_userRole.getString(20).equals("Allow")){%> checked="checked" <%} %>>Allow
		<INPUT TYPE=RADIO NAME="downloaddoc" id="downloaddoc" VALUE="Not Allow" <%if(crs_userRole.getString(20).equals("Not Allow")){%> checked="checked" <%} %> >Not Allow
		</td></tr>

<%} %>
			</table><br><br>
			<table align="center">
			<tr><td><html:submit value="Save Changes"/></td>
			<td><html:reset></html:reset></td>
			<td><html:button property="back" value="Cancel" onclick="history.back();" /></td>
			</tr></table>
		</html:form>
	</body>
</html>
