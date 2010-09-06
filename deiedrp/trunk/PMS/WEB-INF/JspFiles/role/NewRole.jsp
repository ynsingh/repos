<%@ page language="java" pageEncoding="ISO-8859-1"%>
<%@ page import="in.ac.dei.edrp.pms.role.RoleList" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%> 
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<html> 
	<head>
	
	<title>New Role</title>
	<link rel="stylesheet" href="style/main.css" type="text/css"></link>
	<link rel="stylesheet" href="style/style.css" type="text/css"></link>
	
		<!-- You have to include these two JavaScript files -->
        <script type='text/javascript' src='dwr/engine.js'></script>
        <script type='text/javascript' src='dwr/util.js'></script>
	<!-- This JavaScript file is generated specifically for your application -->
         <script type='text/javascript' src='dwr/interface/DynamicList.js'></script>
	<link rel="stylesheet" type="text/css" href="style/jquery-ui.css" />
<script type="text/javascript" src="javascript/jquery.js"></script>
<script type="text/javascript" src="javascript/jquery-ui.min.js"></script>
<script type="text/javascript">
jQuery.noConflict();
jQuery(document).ready(function(){

jQuery(function() {
		jQuery("#accordion").accordion({ collapsible: true,
		header: 'h3',
		fillSpace: false
		});
				
	});
});
</script>
	<script type="text/javascript">
	function seeRole() {
	var name = DWRUtil.getValue("rolename");
	var orgportal = DWRUtil.getValue("orgportal");
  DynamicList.seeRoleExistence(name,orgportal,function(data)
  {
  	DWRUtil.setValue("rolename2",data);
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
	else
	{
	if(((String)session.getAttribute("authority")).equalsIgnoreCase("User"))//the authority is user or super admin
		{
		request.setAttribute("roleAutority", new RoleList((String)session.getAttribute("uid"),
				Integer.parseInt((String)session.getAttribute("validOrgInPortal")),
				(String)session.getAttribute("roleid")));
		}
	%>
	<div style="padding-left:100px;padding-right:100px;padding-top:40px;">
	<div id="accordion">
	<h3><a href="#">Add New Role -</a></h3>
	<div>
	<html:javascript formName="newroleform" dynamicJavascript="true" staticJavascript="true" />
	<html:form action="/addrole" onsubmit="return validateNewroleform(this);">
		  <br>
		  <div align="center">
		  	<html:errors property="rolename"/>
		  	<html:errors property="rolemsg"/>
		   </div>
		 		
		<table cellspacing="1" cellpadding="6" border="0" align="center">
		<tr class="form-element">
		<td class="form-label">
		<input type="hidden" name="orgportal" id="orgportal" value="<%=(String)session.getAttribute("validOrgInPortal") %>" size="20" readonly="readonly"/>
			<html:errors property="orgportal"/>
		Role Name : 
		</td>
		<td class="form-widget">
		<html:text property="rolename" indexed="rolename" size="35" value="" onchange="seeRole()"/><font color="red" size="2">*</font>
		</td>
		<td class="form-label">
			Role Description :</td><td class="form-widget">
			<html:textarea property="roledescription" value="" rows="2" cols="33"/>
			<html:errors property="roledescription"/></td></tr>
			<%if(((String)session.getAttribute("authority")).equalsIgnoreCase("User"))
			{
			//System.out.println("user");
			 %>
	 <logic:iterate id="var" property="list" name="roleAutority" >
		<tr><td> <font color="#0044ff" size="2"> Authorities :</font></td></tr>
	  
	  <tr class="form-element">
	  <logic:equal name="var" property="addrole" value="Allow">
	  <td  class="form-label">
		Add Role :</td><td>
		<INPUT TYPE=RADIO NAME="addrole" id="addrole" VALUE="Allow"><bean:message key="role.allow" />
		<INPUT TYPE=RADIO NAME="addrole" id="addrole" VALUE="Not Allow" checked="checked"><bean:message key="role.notallow" />
		</td></logic:equal>
		 <logic:equal name="var" property="addrole" value="Not Allow">
	  <td  class="form-label">
		Add Role :</td><td>
		<INPUT TYPE=RADIO NAME="addrole" id="addrole" disabled="disabled" VALUE="Allow"><bean:message key="role.allow" />
		<INPUT TYPE=RADIO NAME="addrole" id="addrole" disabled="disabled" VALUE="Not Allow" checked="checked"><bean:message key="role.notallow" />
		</td></logic:equal>
		<logic:equal name="var" property="editrole" value="Allow">
		<td  class="form-label">
		Edit/Remove Role :</td><td>
		<INPUT TYPE=RADIO NAME="editrole" id="editrole" VALUE="Allow" ><bean:message key="role.allow" />
		<INPUT TYPE=RADIO NAME="editrole" id="editrole" VALUE="Not Allow" checked="checked"><bean:message key="role.notallow" />
		</td></logic:equal>
		<logic:equal name="var" property="editrole" value="Not Allow">
		<td  class="form-label">
		Edit/Remove Role :</td><td>
		<INPUT TYPE=RADIO NAME="editrole" id="editrole" disabled="disabled" VALUE="Allow" ><bean:message key="role.allow" />
		<INPUT TYPE=RADIO NAME="editrole" id="editrole" disabled="disabled" VALUE="Not Allow" checked="checked"><bean:message key="role.notallow" />
		</td></logic:equal>
		</tr>
		<!-- 
		<tr class="form-element">
		<logic:equal name="var" property="addorg" value="Allow">
		<td  class="form-label">
		Add Organization :</td><td>
		<INPUT TYPE=RADIO NAME="addorg" id="addorg" VALUE="Allow">Allow
		<INPUT TYPE=RADIO NAME="addorg" id="addorg" VALUE="Not Allow" checked="checked">Not Allow
		</td></logic:equal>
		<logic:equal name="var" property="addorg" value="Not Allow">
		<td  class="form-label">
		Add Organization :</td><td>
		<INPUT TYPE=RADIO NAME="addorg" id="addorg" disabled="disabled" VALUE="Allow">Allow
		<INPUT TYPE=RADIO NAME="addorg" id="addorg" disabled="disabled" VALUE="Not Allow" checked="checked">Not Allow
		</td></logic:equal>
		<logic:equal name="var" property="editorg" value="Allow">
		<td  class="form-label">
		Edit/Remove Organization :</td><td>
		<INPUT TYPE=RADIO NAME="editorg" id="editorg" VALUE="Allow" >Allow
		<INPUT TYPE=RADIO NAME="editorg" id="editorg" VALUE="Not Allow" checked="checked">Not Allow
		</td>
		</logic:equal>
		<logic:equal name="var" property="editorg" value="Not Allow">
		<td  class="form-label">
		Edit/Remove Organization :</td><td>
		<INPUT TYPE=RADIO NAME="editorg" id="editorg" disabled="disabled" VALUE="Allow" >Allow
		<INPUT TYPE=RADIO NAME="editorg" id="editorg" disabled="disabled" VALUE="Not Allow" checked="checked">Not Allow
		</td>
		</logic:equal>
		</tr>-->
		<tr class="form-element">
		<logic:equal name="var" property="addproject" value="Allow">
		<td class="form-label">
		Add Project :</td><td>
		<INPUT TYPE=RADIO NAME="addproject" id="addproject" VALUE="Allow" ><bean:message key="role.allow" />
		<INPUT TYPE=RADIO NAME="addproject" id="addproject" VALUE="Not Allow" checked="checked"><bean:message key="role.notallow" />
		</td></logic:equal>
		<logic:equal name="var" property="addproject" value="Not Allow">
		<td class="form-label">
		Add Project :</td><td>
		<INPUT TYPE=RADIO NAME="addproject" id="addproject" disabled="disabled" VALUE="Allow" ><bean:message key="role.allow" />
		<INPUT TYPE=RADIO NAME="addproject" id="addproject" disabled="disabled" VALUE="Not Allow" checked="checked"><bean:message key="role.notallow" />
		</td></logic:equal>
		<logic:equal name="var" property="editproject" value="Allow">
		<td  class="form-label">
		Edit/Remove Project :</td><td>
		<INPUT TYPE=RADIO NAME="editproject" id="editproject" VALUE="Allow"><bean:message key="role.allow" />
		<INPUT TYPE=RADIO NAME="editproject" id="editproject" VALUE="Not Allow" checked="checked"><bean:message key="role.notallow" />
		</td></logic:equal>
		<logic:equal name="var" property="editproject" value="Not Allow">
		<td  class="form-label">
		Edit/Remove Project :</td><td>
		<INPUT TYPE=RADIO NAME="editproject" id="editproject" disabled="disabled" VALUE="Allow"><bean:message key="role.allow" />
		<INPUT TYPE=RADIO NAME="editproject" id="editproject" disabled="disabled" VALUE="Not Allow" checked="checked"><bean:message key="role.notallow" />
		</td></logic:equal>
		</tr>
		
		<tr class="form-element">
		<logic:equal name="var" property="addmember" value="Allow">
		<td class="form-label">
		Add Member :</td><td>
		<INPUT TYPE=RADIO NAME="addmember" id="addmember" VALUE="Allow"><bean:message key="role.allow" />
		<INPUT TYPE=RADIO NAME="addmember" id="addmember" VALUE="Not Allow" checked="checked" ><bean:message key="role.notallow" />
		</td></logic:equal>
		<logic:equal name="var" property="addmember" value="Not Allow">
		<td class="form-label">
		Add Member :</td><td>
		<INPUT TYPE=RADIO NAME="addmember" id="addmember" disabled="disabled" VALUE="Allow"><bean:message key="role.allow" />
		<INPUT TYPE=RADIO NAME="addmember" id="addmember" disabled="disabled" VALUE="Not Allow" checked="checked" ><bean:message key="role.notallow" />
		</td></logic:equal>
		<logic:equal name="var" property="editmember" value="Allow">
		<td  class="form-label">
		Edit/Remove Member :</td><td>
		<INPUT TYPE=RADIO NAME="editmember" id="editmember" VALUE="Allow" ><bean:message key="role.allow" />
		<INPUT TYPE=RADIO NAME="editmember" id="editmember" VALUE="Not Allow" checked="checked" ><bean:message key="role.notallow" />
		</td></logic:equal>
		<logic:equal name="var" property="editmember" value="Not Allow">
		<td  class="form-label">
		Edit/Remove Member :</td><td>
		<INPUT TYPE=RADIO NAME="editmember" id="editmember" disabled="disabled" VALUE="Allow" ><bean:message key="role.allow" />
		<INPUT TYPE=RADIO NAME="editmember" id="editmember" disabled="disabled" VALUE="Not Allow" checked="checked" ><bean:message key="role.notallow" />
		</td></logic:equal>
		</tr>
		<tr class="form-element">
		<logic:equal name="var" property="assignproject" value="Allow">
		<td class="form-label">
		Project Team Creation :</td><td>
		<INPUT TYPE=RADIO NAME="assignproject" id="assignproject" VALUE="Allow" ><bean:message key="role.allow" />
		<INPUT TYPE=RADIO NAME="assignproject" id="assignproject" VALUE="Not Allow" checked="checked" ><bean:message key="role.notallow" />
		</td></logic:equal>
		<logic:equal name="var" property="assignproject" value="Not Allow">
		<td class="form-label">
		Project Team Creation :</td><td>
		<INPUT TYPE=RADIO NAME="assignproject" id="assignproject" disabled="disabled" VALUE="Allow" ><bean:message key="role.allow" />
		<INPUT TYPE=RADIO NAME="assignproject" id="assignproject" disabled="disabled" VALUE="Not Allow" checked="checked" ><bean:message key="role.notallow" />
		</td></logic:equal>
		<logic:equal name="var" property="editauthority" value="Allow">
		<td  class="form-label">
		Edit Member Authority :</td><td>
		<INPUT TYPE=RADIO NAME="editauthority" id="editauthority" VALUE="Allow" ><bean:message key="role.allow" />
		<INPUT TYPE=RADIO NAME="editauthority" id="editauthority" VALUE="Not Allow" checked="checked" ><bean:message key="role.notallow" />
		</td></logic:equal>
		<logic:equal name="var" property="editauthority" value="Not Allow">
		<td  class="form-label">
		Edit Member Authority :</td><td>
		<INPUT TYPE=RADIO NAME="editauthority" id="editauthority" disabled="disabled" VALUE="Allow" ><bean:message key="role.allow" />
		<INPUT TYPE=RADIO NAME="editauthority" id="editauthority" disabled="disabled" VALUE="Not Allow" checked="checked" ><bean:message key="role.notallow" />
		</td></logic:equal>
		</tr>
		<tr class="form-element">
		<logic:equal name="var" property="assigntask" value="Allow">
		<td class="form-label">
		Assign Task :</td><td>
		<INPUT TYPE=RADIO NAME="assigntask" id="assigntask" VALUE="Allow" ><bean:message key="role.allow" />
		<INPUT TYPE=RADIO NAME="assigntask" id="assigntask" VALUE="Not Allow"  checked="checked" ><bean:message key="role.notallow" />
		</td></logic:equal>
		<logic:equal name="var" property="assigntask" value="Not Allow">
		<td class="form-label">
		Assign Task :</td><td>
		<INPUT TYPE=RADIO NAME="assigntask" id="assigntask" disabled="disabled" VALUE="Allow" ><bean:message key="role.allow" />
		<INPUT TYPE=RADIO NAME="assigntask" id="assigntask" disabled="disabled" VALUE="Not Allow"  checked="checked" ><bean:message key="role.notallow" />
		</td></logic:equal>
		<logic:equal name="var" property="edittask" value="Allow">
		<td  class="form-label">
		Edit/Remove Task :</td><td>
		<INPUT TYPE=RADIO NAME="edittask" id="edittask" VALUE="Allow"><bean:message key="role.allow" />
		<INPUT TYPE=RADIO NAME="edittask" id="edittask" VALUE="Not Allow" checked="checked"><bean:message key="role.notallow" />
		</td></logic:equal>
		<logic:equal name="var" property="edittask" value="Not Allow">
		<td  class="form-label">
		Edit/Remove Task :</td><td>
		<INPUT TYPE=RADIO NAME="edittask" id="edittask" disabled="disabled" VALUE="Allow"><bean:message key="role.allow" />
		<INPUT TYPE=RADIO NAME="edittask" id="edittask" disabled="disabled" VALUE="Not Allow" checked="checked"><bean:message key="role.notallow" />
		</td></logic:equal>
		</tr>
		<tr class="form-element">
		<logic:equal name="var" property="uploaddoc" value="Allow">
		<td class="form-label">
		Upload Documents :</td><td>
		<INPUT TYPE=RADIO NAME="uploaddoc" id="uploaddoc" VALUE="Allow" ><bean:message key="role.allow" />
		<INPUT TYPE=RADIO NAME="uploaddoc" id="uploaddoc" VALUE="Not Allow" checked="checked"><bean:message key="role.notallow" />
		</td></logic:equal>
		<logic:equal name="var" property="uploaddoc" value="Not Allow">
		<td class="form-label">
		Upload Documents :</td><td>
		<INPUT TYPE=RADIO NAME="uploaddoc" id="uploaddoc" disabled="disabled" VALUE="Allow" ><bean:message key="role.allow" />
		<INPUT TYPE=RADIO NAME="uploaddoc" id="uploaddoc" disabled="disabled" VALUE="Not Allow" checked="checked"><bean:message key="role.notallow" />
		</td></logic:equal>
		<logic:equal name="var" property="downloaddoc" value="Allow">
		<td  class="form-label">
		Download/Remove Documents :</td><td>
		<INPUT TYPE=RADIO NAME="downloaddoc" id="downloaddoc" VALUE="Allow"><bean:message key="role.allow" />
		<INPUT TYPE=RADIO NAME="downloaddoc" id="downloaddoc" VALUE="Not Allow" checked="checked" ><bean:message key="role.notallow" />
		</td></logic:equal>
		<logic:equal name="var" property="downloaddoc" value="Not Allow">
		<td  class="form-label">
		Download/Remove Documents :</td><td>
		<INPUT TYPE=RADIO NAME="downloaddoc" id="downloaddoc" disabled="disabled" VALUE="Allow"><bean:message key="role.allow" />
		<INPUT TYPE=RADIO NAME="downloaddoc" id="downloaddoc" disabled="disabled" VALUE="Not Allow" checked="checked" ><bean:message key="role.notallow" />
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
		<INPUT TYPE=RADIO NAME="addrole" id="addrole" VALUE="Allow"><bean:message key="role.allow" />
		<INPUT TYPE=RADIO NAME="addrole" id="addrole" VALUE="Not Allow" checked="checked"><bean:message key="role.notallow" />
		</td><td  class="form-label">
		Edit/Remove Role :</td><td>
		<INPUT TYPE=RADIO NAME="editrole" id="editrole" VALUE="Allow" ><bean:message key="role.allow" />
		<INPUT TYPE=RADIO NAME="editrole" id="editrole" VALUE="Not Allow" checked="checked"><bean:message key="role.notallow" />
		</td></tr>
		<!-- 
		<tr class="form-element"><td  class="form-label">
		Add Organization :</td><td>
		<INPUT TYPE=RADIO NAME="addorg" id="addorg" VALUE="Allow">Allow
		<INPUT TYPE=RADIO NAME="addorg" id="addorg" VALUE="Not Allow" checked="checked">Not Allow
		</td><td  class="form-label">
		Edit/Remove Organization :</td><td>
		<INPUT TYPE=RADIO NAME="editorg" id="editorg" VALUE="Allow" >Allow
		<INPUT TYPE=RADIO NAME="editorg" id="editorg" VALUE="Not Allow" checked="checked">Not Allow
		</td></tr> -->
		<tr class="form-element"><td class="form-label">
		Add Project :</td><td>
		<INPUT TYPE=RADIO NAME="addproject" id="addproject" VALUE="Allow" ><bean:message key="role.allow" />
		<INPUT TYPE=RADIO NAME="addproject" id="addproject" VALUE="Not Allow" checked="checked"><bean:message key="role.notallow" />
		</td><td  class="form-label">
		Edit/Remove Project :</td><td>
		<INPUT TYPE=RADIO NAME="editproject" id="editproject" VALUE="Allow"><bean:message key="role.allow" />
		<INPUT TYPE=RADIO NAME="editproject" id="editproject" VALUE="Not Allow" checked="checked"><bean:message key="role.notallow" />
		</td></tr>
		<tr class="form-element"><td class="form-label">
		Add Member :</td><td>
		<INPUT TYPE=RADIO NAME="addmember" id="addmember" VALUE="Allow"><bean:message key="role.allow" />
		<INPUT TYPE=RADIO NAME="addmember" id="addmember" VALUE="Not Allow" checked="checked" ><bean:message key="role.notallow" />
		</td><td  class="form-label">
		Edit/Remove Member :</td><td>
		<INPUT TYPE=RADIO NAME="editmember" id="editmember" VALUE="Allow" ><bean:message key="role.allow" />
		<INPUT TYPE=RADIO NAME="editmember" id="editmember" VALUE="Not Allow" checked="checked" ><bean:message key="role.notallow" />
		</td></tr>
		<tr class="form-element"><td class="form-label">
		Project Team Creation :</td><td>
		<INPUT TYPE=RADIO NAME="assignproject" id="assignproject" VALUE="Allow" ><bean:message key="role.allow" />
		<INPUT TYPE=RADIO NAME="assignproject" id="assignproject" VALUE="Not Allow" checked="checked" ><bean:message key="role.notallow" />
		</td><td  class="form-label">
		Edit Member Authority :</td><td>
		<INPUT TYPE=RADIO NAME="editauthority" id="editauthority" VALUE="Allow" ><bean:message key="role.allow" />
		<INPUT TYPE=RADIO NAME="editauthority" id="editauthority" VALUE="Not Allow" checked="checked" ><bean:message key="role.notallow" />
		</td></tr>
		<tr class="form-element"><td class="form-label">
		Assign Task :</td><td>
		<INPUT TYPE=RADIO NAME="assigntask" id="assigntask" VALUE="Allow" ><bean:message key="role.allow" />
		<INPUT TYPE=RADIO NAME="assigntask" id="assigntask" VALUE="Not Allow"  checked="checked" ><bean:message key="role.notallow" />

		</td><td  class="form-label">
		Edit/Remove Task :</td><td>
		<INPUT TYPE=RADIO NAME="edittask" id="edittask" VALUE="Allow"><bean:message key="role.allow" />
		<INPUT TYPE=RADIO NAME="edittask" id="edittask" VALUE="Not Allow" checked="checked"><bean:message key="role.notallow" />
		</td></tr>
		<tr class="form-element"><td class="form-label">
		Upload Documents :</td><td>
		<INPUT TYPE=RADIO NAME="uploaddoc" id="uploaddoc" VALUE="Allow" ><bean:message key="role.allow" />
		<INPUT TYPE=RADIO NAME="uploaddoc" id="uploaddoc" VALUE="Not Allow" checked="checked"><bean:message key="role.notallow" />
		</td><td  class="form-label">
		Download/Remove Documents :</td><td>
		<INPUT TYPE=RADIO NAME="downloaddoc" id="downloaddoc" VALUE="Allow"><bean:message key="role.allow" />
		<INPUT TYPE=RADIO NAME="downloaddoc" id="downloaddoc" VALUE="Not Allow" checked="checked" ><bean:message key="role.notallow" />
		</td></tr>

<%} %>			</table><br>
			<table align="center">
			<tr><td><html:submit value="Add" styleClass="butStnd"/></td>
			<td><html:button property="back" value="Back" styleClass="butStnd" onclick="history.back();" />
			<input type="hidden" name="rolename2" id="rolename2" value="" size="20" readonly="readonly"/>
			<html:errors property="rolename2"/>
			</td>
			</tr></table>
		</html:form>
		<%} %>
		</div>
		</div>
		</div>
	</body>
</html>

