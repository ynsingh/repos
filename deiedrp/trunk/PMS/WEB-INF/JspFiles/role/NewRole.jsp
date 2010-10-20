<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page import="in.ac.dei.edrp.pms.role.RoleList" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%> 
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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

<link rel="stylesheet" type="text/css" href="style/jquery.multiselect.css" />
<script type="text/javascript" src="javascript/jquery.multiselect.js"></script>

<script type="text/javascript">
jQuery.noConflict();
jQuery(document).ready(function(){

jQuery(function() {
		jQuery("#accordion").accordion({ collapsible: true,
		header: 'h3',
		fillSpace: false,
		autoHeight: false
		});
	});

	jQuery("#authorities").multiselect({
 	  selectedList: 2 // 0-based index
});

  jQuery("#authorities").multiselect({
   show:"blind",
   hide:"blind",
   height:"175",
   selectedText:"# of # selected"
});

jQuery('form').submit(function() {
var array_of_checked_values = jQuery("#authorities").multiselect("getChecked").map(function(){
   //alert(this.value);
   return this.value;	
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
	if(((String)session.getAttribute("authority")).equalsIgnoreCase("User"))//the authority is user or super admin
		{
		request.setAttribute("roleAutority", new RoleList((String)session.getAttribute("uid"),
				Integer.parseInt((String)session.getAttribute("validOrgInPortal")),
				(String)session.getAttribute("roleid")));
		}
	%>
	<div style="padding-left:100px;padding-right:100px;padding-top:40px;">
	<div id="accordion">
	<h3><a href="#"><bean:message key="title.addNewRole"/> -</a></h3>
	<div>
	<html:javascript formName="newroleform" />
	<html:form action="/addrole" onsubmit="return validateNewroleform(this);">
		  <br>
		  <div align="center">
		  		<html:errors property="rolemsg"/>
		   </div>
		 		
		<table cellspacing="1" cellpadding="6" border="0" align="center">
		<tr class="form-element">
		<td class="form-label">
		<input type="hidden" name="orgportal" id="orgportal" value="<%=(String)session.getAttribute("validOrgInPortal") %>" size="20" readonly="readonly"/>
			<html:errors property="orgportal"/>
		<bean:message key="label.roleName"/> : 
		</td>
		<td class="form-widget">
		<html:text property="rolename" indexed="rolename" size="35" value="" onchange="seeRole()"/><font color="red" size="2">*</font>
		<html:errors property="rolename"/>
		</td>
		<td class="form-label">
			<bean:message key="label.roleDescription"/> :</td><td class="form-widget">
			<html:textarea property="roledescription" value="" rows="2" cols="33"/>
			<html:errors property="roledescription"/></td></tr>
<c:choose> 
  <c:when test="${sessionScope.authority == 'User'}" > 
   <!-- when login person is User not a Super Admin -->
   
		<tr><td class="form-label"> <bean:message key="label.authorities"/> :</td>
	<td> <select name="authorities" id="authorities" multiple="multiple">
	<logic:iterate id="var" property="list" name="roleAutority" >
	<option value=""></option>
	<optgroup label='<bean:message key="label.optgroup.role"/>'>
	  <logic:equal name="var" property="roleauthority" value="add_role">
	  <option value="add_role"><bean:message key="label.authorities.addRole"/></option></logic:equal>
		<logic:equal name="var" property="roleauthority" value="edit_remove_role">
		<option value="edit_remove_role"><bean:message key="label.authorities.editRole"/> </option></logic:equal>
		</optgroup></logic:iterate>
		<logic:iterate id="var" property="list" name="roleAutority" >
		<optgroup label='<bean:message key="label.optgroup.project"/>'>
		<logic:equal name="var" property="roleauthority" value="add_project">
		<option value="add_project"><bean:message key="label.authorities.addProject"/> </option></logic:equal>
		<logic:equal name="var" property="roleauthority" value="edit_disable_project">
		<option value="edit_disable_project"><bean:message key="label.authorities.editProject"/></option></logic:equal>
		<logic:equal name="var" property="roleauthority" value="assign_project">
		<option value="assign_project"><bean:message key="label.authorities.projectTeamCreation"/></option></logic:equal>
		</optgroup></logic:iterate>
		<logic:iterate id="var" property="list" name="roleAutority" >
		<optgroup label='<bean:message key="label.optgroup.member"/>'>
		<logic:equal name="var" property="roleauthority" value="add_member">
		<option value="add_member"><bean:message key="label.authorities.addMember"/></option></logic:equal>
		<logic:equal name="var" property="roleauthority" value="edit_remove_member">
		<option value="edit_remove_member"><bean:message key="label.authorities.editMember"/></option></logic:equal>
		<logic:equal name="var" property="roleauthority" value="edit_member_authority">
		<option value="edit_member_authority"><bean:message key="label.authorities.editMemberAuthority"/></option></logic:equal>
		</optgroup></logic:iterate>
		
		<logic:iterate id="var" property="list" name="roleAutority" >
		<optgroup label='<bean:message key="label.optgroup.task"/>'>
		<logic:equal name="var" property="roleauthority" value="assign_task">
		<option value="assign_task"><bean:message key="label.authorities.creatingTask"/></option></logic:equal>
		<logic:equal name="var" property="roleauthority" value="edit_remove_task">
		<option value="edit_remove_task"><bean:message key="label.authorities.editTask"/></option></logic:equal>
		</optgroup></logic:iterate>
		<logic:iterate id="var" property="list" name="roleAutority" >
		<optgroup label='<bean:message key="label.optgroup.documents"/>'>
		<logic:equal name="var" property="roleauthority" value="upload_documents">
		<option value="upload_documents"><bean:message key="label.authorities.uploadDoc"/></option></logic:equal>
		<logic:equal name="var" property="roleauthority" value="dwnld_documents	">
		<option value="dwnld_documents"><bean:message key="label.authorities.downloadDoc"/></option></logic:equal>
		<logic:equal name="var" property="roleauthority" value="remove_documents">
		<option value="remove_documents"><bean:message key="label.authorities.removeDoc"/></option></logic:equal>
		</optgroup></logic:iterate>
		<logic:iterate id="var" property="list" name="roleAutority" >
		<optgroup label='<bean:message key="label.optgroup.password"/>'>
		<logic:equal name="var" property="roleauthority" value="changed_user_password">
		<option value="changed_user_password"><bean:message key="label.authorities.editPassword"/></option></logic:equal>
		</optgroup></logic:iterate>
			</select></td></tr>
		
</c:when> 
  <c:otherwise> 
   <!-- when login person is Super Admin -->
<tr><td class="form-label"> <bean:message key="label.authorities"/> :</td>
	  
<td> <select name="authorities" id="authorities" multiple="multiple" size="5" style="width:400px;">
<option value=""></option>
<optgroup label='<bean:message key="label.optgroup.role"/>'>
<option value="add_role"><bean:message key="label.authorities.addRole"/></option>
<option value="edit_remove_role"><bean:message key="label.authorities.editRole"/></option>
</optgroup>
<optgroup label='<bean:message key="label.optgroup.project"/>'>
<option value="add_project"><bean:message key="label.authorities.addProject"/> </option>
<option value="edit_disable_project"><bean:message key="label.authorities.editProject"/></option>
<option value="assign_project"><bean:message key="label.authorities.projectTeamCreation"/></option>
</optgroup>
<optgroup label='<bean:message key="label.optgroup.member"/>'>
<option value="add_member"><bean:message key="label.authorities.addMember"/></option>
<option value="edit_remove_member"><bean:message key="label.authorities.editMember"/></option>
<option value="edit_member_authority"><bean:message key="label.authorities.editMemberAuthority"/></option>
</optgroup>

<optgroup label='<bean:message key="label.optgroup.task"/>'>
<option value="assign_task"><bean:message key="label.authorities.creatingTask"/></option>
<option value="edit_remove_task"><bean:message key="label.authorities.editTask"/></option>
</optgroup>
<optgroup label='<bean:message key="label.optgroup.documents"/>'>
<option value="upload_documents"><bean:message key="label.authorities.uploadDoc"/></option>
<option value="dwnld_documents"><bean:message key="label.authorities.downloadDoc"/></option>
<option value="remove_documents"><bean:message key="label.authorities.removeDoc"/></option>
</optgroup>
<optgroup label='<bean:message key="label.optgroup.password"/>'>
<option value="changed_user_password"><bean:message key="label.authorities.editPassword"/></option>
</optgroup>
</select></td></tr>
</c:otherwise></c:choose>
</table><br>
			<table align="center">
			<tr><td><input type="submit" value='<bean:message key="label.add.button" />' class="butStnd"/></td>
			<td><input type="reset" value='<bean:message key="label.reset.button" />' class="butStnd" onclick="location.href='newrole.do'"/></td>
			<td><c:if test="${sessionScope.authority=='User'}">
			<input type="button" value='<bean:message key="label.cancel.button" />' class="butStnd" onclick="location.href='mainwelcome.do'" />
			</c:if>
			<c:if test="${sessionScope.authority=='Super Admin'}">
			<input type="button" value='<bean:message key="label.cancel.button" />' class="butStnd" onclick="location.href='welcome.do'" />
			</c:if>
			<input type="hidden" name="rolename2" id="rolename2" value="" size="20" readonly="readonly"/>
			<html:errors property="rolename2"/>
			</td></tr></table>
		</html:form>
		
		</div>
		</div>
		</div>
	</body>
</html>

