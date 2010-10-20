<%@ page language="java" pageEncoding="ISO-8859-1"%>
<%@ page import="in.ac.dei.edrp.pms.role.RoleList" %>
<%@ page import="javax.sql.rowset.CachedRowSet;" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%> 
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 
<html> 
	<head>
	<title>Edit Role</title>
				
	<link rel="stylesheet" href="style/style.css" type="text/css"></link>
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
	</head>
	<body>

	<%!
		CachedRowSet crs_userRole=null;
		int i=0;
	 %>
	<%
	/*Get the value of 'crs' that is set in request scope in EditForwardPortalAction*/
	crs_userRole=(CachedRowSet)request.getAttribute("crs");
	if(((String)session.getAttribute("authority")).equalsIgnoreCase("User"))//the authority is user or super admin
		{
		request.setAttribute("roleAutority", new RoleList((String)session.getAttribute("uid"),
				Integer.parseInt((String)session.getAttribute("validOrgInPortal")),
				(String)session.getAttribute("roleid")));
		}
	%>
	
	<div style="padding-left:100px;padding-right:100px;padding-top:40px;">
	<div id="accordion">
	<h3><a href="#"><bean:message key="title.editDesiredrole"/> -</a></h3>
	<div>
	 	<html:javascript formName="editroleform" />
		<html:form action="/editingrole" onsubmit="return validateEditroleform(this);">
		  <br>
		  <div align="center">
		  	<html:errors property="rolename"/>
		  	<html:errors property="rolemsg"/>
		   </div>
		  
		<input type="hidden" name="roleid" value="<%= crs_userRole.getString(1)%>" id="roleid" size="20" readonly="readonly"/>
		<html:errors property="roleid"/>
		<input type="hidden" name="oldrolename" value="<%= crs_userRole.getString(2)%>" id="oldrolename" size="20" readonly="readonly"/>
		<html:errors property="oldrolename"/>
		
		<table cellspacing="1" cellpadding="6" border="0" align="center">
		<tr class="form-element">
		<td class="form-label">
		<bean:message key="label.roleName"/> : 
		</td>
		<td class="form-widget">
		<html:text property="rolename" size="35" value="<%=crs_userRole.getString(2)%>"/></td>
		
		<td class="form-label">
			<bean:message key="label.roleDescription"/> :</td><td class="form-widget">
			<html:textarea property="roledescription" value="<%=crs_userRole.getString(3)%>" rows="2" cols="33"/>
			<html:errors property="roledescription"/></td></tr>
<c:choose> 
  <c:when test="${sessionScope.authority == 'User'}" > 
   <!-- when login person is User not a Super Admin -->
   
	 <tr><td class="form-label"><bean:message key="label.authorities"/> :</td>
	<td> <select name="authorities" id="authorities" multiple="multiple">
	
	<option value=""></option>
<sql:query var="roleauthority" dataSource="jdbc/mydb">
				select authorities from role r,default_authority dfa 
				where r.role_id=dfa.role_id and r.role_id=? 
				<sql:param value="${param.rolekey}"/>
			</sql:query>
			<logic:iterate id="var" property="list" name="roleAutority" >
			<optgroup label='<bean:message key="label.optgroup.role"/>'>
	  <logic:equal name="var" property="roleauthority" value="add_role">
	  <c:forEach var="row"  items="${roleauthority.rows}">
			<c:if test="${row.authorities == 'add_role'}" > 
  			<%i=1;%>
    		<option value="add_role" selected="selected"><bean:message key="label.authorities.addRole"/></option>
 			 </c:if></c:forEach>
  			<%if(i==0){%>
  			<option value="add_role"><bean:message key="label.authorities.addRole"/></option>
  				<%}i=0;%>
	  </logic:equal>
	  
		<logic:equal name="var" property="roleauthority" value="edit_remove_role">
		<c:forEach var="row"  items="${roleauthority.rows}">
			<c:if test="${row.authorities == 'edit_remove_role'}" > 
  			<%i=1;%>
    		<option value="edit_remove_role" selected="selected"><bean:message key="label.authorities.editRole"/> </option>
 			 </c:if></c:forEach>
  			<%if(i==0){%>
  			<option value="edit_remove_role"><bean:message key="label.authorities.editRole"/> </option>
  				<%}i=0;%>
		</logic:equal>
		</optgroup>
		</logic:iterate>
		<logic:iterate id="var" property="list" name="roleAutority" >
		<optgroup label='<bean:message key="label.optgroup.project"/>'>
		<logic:equal name="var" property="roleauthority" value="add_project">
		<c:forEach var="row"  items="${roleauthority.rows}">
			<c:if test="${row.authorities == 'add_project'}" > 
  			<%i=1;%>
    		<option value="add_project" selected="selected"><bean:message key="label.authorities.addProject"/> </option>
 			 </c:if></c:forEach>
  			<%if(i==0){%>
  			<option value="add_project"><bean:message key="label.authorities.addProject"/> </option>
  				<%}i=0;%>   
		</logic:equal>
		<logic:equal name="var" property="roleauthority" value="edit_disable_project">
		<c:forEach var="row"  items="${roleauthority.rows}">
			<c:if test="${row.authorities == 'edit_disable_project'}" > 
  			<%i=1;%>
    		<option value="edit_disable_project" selected="selected"><bean:message key="label.authorities.editProject"/></option>
 			 </c:if></c:forEach>
  			<%if(i==0){%>
  			<option value="edit_disable_project"><bean:message key="label.authorities.editProject"/></option>
  				<%}i=0;%>
		</logic:equal>
		<logic:equal name="var" property="roleauthority" value="assign_project">
		<c:forEach var="row"  items="${roleauthority.rows}">
			<c:if test="${row.authorities == 'assign_project'}" > 
  			<%i=1;%>
    		<option value="assign_project" selected="selected"><bean:message key="label.authorities.projectTeamCreation"/></option>
 			 </c:if></c:forEach>
  			<%if(i==0){%>
  			<option value="assign_project"><bean:message key="label.authorities.projectTeamCreation"/></option>
  				<%}i=0;%>
		</logic:equal>
		</optgroup>
		</logic:iterate>
		<logic:iterate id="var" property="list" name="roleAutority" >
		<optgroup label='<bean:message key="label.optgroup.member"/>'>
		<logic:equal name="var" property="roleauthority" value="add_member">
		<c:forEach var="row"  items="${roleauthority.rows}">
			<c:if test="${row.authorities == 'add_member'}" > 
  			<%i=1;%>
    		<option value="add_member" selected="selected"><bean:message key="label.authorities.addMember"/></option>
 			 </c:if></c:forEach>
  			<%if(i==0){%>
  			<option value="add_member"><bean:message key="label.authorities.addMember"/></option>
  				<%}i=0;%>
		</logic:equal>
		<logic:equal name="var" property="roleauthority" value="edit_remove_member">
		<c:forEach var="row"  items="${roleauthority.rows}">
			<c:if test="${row.authorities == 'edit_remove_member'}" > 
  			<%i=1;%>
    		<option value="edit_remove_member" selected="selected"><bean:message key="label.authorities.editMember"/></option>
 			 </c:if></c:forEach>
  			<%if(i==0){%>
  			<option value="edit_remove_member"><bean:message key="label.authorities.editMember"/></option>
  				<%}i=0;%>
		</logic:equal>
		<logic:equal name="var" property="roleauthority" value="edit_member_authority">
		<c:forEach var="row"  items="${roleauthority.rows}">
			<c:if test="${row.authorities == 'edit_member_authority'}" > 
  			<%i=1;%>
    		<option value="edit_member_authority" selected="selected"><bean:message key="label.authorities.editMemberAuthority"/></option>
 			 </c:if></c:forEach>
  			<%if(i==0){%>
  			<option value="edit_member_authority"><bean:message key="label.authorities.editMemberAuthority"/></option>
  				<%}i=0;%>
		</logic:equal>
		</optgroup>
		</logic:iterate>
		<logic:iterate id="var" property="list" name="roleAutority" >
		<optgroup label='<bean:message key="label.optgroup.task"/>'>
		<logic:equal name="var" property="roleauthority" value="assign_task">
		<c:forEach var="row"  items="${roleauthority.rows}">
			<c:if test="${row.authorities == 'assign_task'}" > 
  			<%i=1;%>
    		<option value="assign_task" selected="selected"><bean:message key="label.authorities.creatingTask"/></option>
 			 </c:if></c:forEach>
  			<%if(i==0){%>
  			<option value="assign_task"><bean:message key="label.authorities.creatingTask"/></option>
  				<%}i=0;%>
		</logic:equal>
		<logic:equal name="var" property="roleauthority" value="edit_remove_task">
		<c:forEach var="row"  items="${roleauthority.rows}">
			<c:if test="${row.authorities == 'edit_remove_task'}" > 
  			<%i=1;%>
    		<option value="edit_remove_task" selected="selected"><bean:message key="label.authorities.editTask"/></option>
 			 </c:if></c:forEach>
  			<%if(i==0){%>
  			<option value="edit_remove_task"><bean:message key="label.authorities.editTask"/></option>
  				<%}i=0;%>
		</logic:equal>
		</optgroup></logic:iterate>
		<logic:iterate id="var" property="list" name="roleAutority" >
		<optgroup label='<bean:message key="label.optgroup.documents"/>'>
		<logic:equal name="var" property="roleauthority" value="upload_documents">
		<c:forEach var="row"  items="${roleauthority.rows}">
			<c:if test="${row.authorities == 'upload_documents'}" > 
  			<%i=1;%>
    		<option value="upload_documents" selected="selected"><bean:message key="label.authorities.uploadDoc"/></option>
 			 </c:if></c:forEach>
  			<%if(i==0){%>
  			<option value="upload_documents"><bean:message key="label.authorities.uploadDoc"/></option>
  				<%}i=0;%>
		</logic:equal>
		<logic:equal name="var" property="roleauthority" value="dwnld_documents	">
		<c:forEach var="row"  items="${roleauthority.rows}">
			<c:if test="${row.authorities == 'dwnld_documents'}" > 
  			<%i=1;%>
    		<option value="dwnld_documents" selected="selected"><bean:message key="label.authorities.downloadDoc"/></option>
 			 </c:if></c:forEach>
  			<%if(i==0){%>
  			<option value="dwnld_documents"><bean:message key="label.authorities.downloadDoc"/></option>
  				<%}i=0;%>
		</logic:equal>
		<logic:equal name="var" property="roleauthority" value="remove_documents">
		<c:forEach var="row"  items="${roleauthority.rows}">
			<c:if test="${row.authorities == 'remove_documents'}" > 
  			<%i=1;%>
    		<option value="remove_documents" selected="selected"><bean:message key="label.authorities.removeDoc"/></option>
 			 </c:if></c:forEach>
  			<%if(i==0){%>
  			<option value="remove_documents"><bean:message key="label.authorities.removeDoc"/></option>
  				<%}i=0;%>
		</logic:equal>
		</optgroup></logic:iterate>
	<logic:iterate id="var" property="list" name="roleAutority" >
		<optgroup label='<bean:message key="label.optgroup.password"/>'>
		<logic:equal name="var" property="roleauthority" value="changed_user_password">
		<c:forEach var="row"  items="${roleauthority.rows}">
			<c:if test="${row.authorities == 'changed_user_password'}" > 
  			<%i=1;%>
    		<option value="changed_user_password" selected="selected"><bean:message key="label.authorities.editPassword"/></option>
 			 </c:if></c:forEach>
  			<%if(i==0){%>
  			<option value="changed_user_password"><bean:message key="label.authorities.editPassword"/></option>
  				<%}i=0;%>
		</logic:equal>
		</optgroup></logic:iterate>
			</select></td></tr>
	</c:when> 
  <c:otherwise> 
   <!-- when login person is Super Admin -->
   
<tr><td class="form-label"><bean:message key="label.authorities"/> :</td>
	  
<td> <select name="authorities" id="authorities" multiple="multiple">
<option value=""></option>
			<sql:query var="roleauthority" dataSource="jdbc/mydb">
				select authorities from role r,default_authority dfa 
				where r.role_id=dfa.role_id and r.role_id=? 
				<sql:param value="${param.rolekey}"/>
			</sql:query>
<optgroup label='<bean:message key="label.optgroup.role"/>'>			
<c:forEach var="row"  items="${roleauthority.rows}">
			<c:if test="${row.authorities == 'add_role'}" > 
  			<%i=1;%>
    		<option value="add_role" selected="selected"><bean:message key="label.authorities.addRole"/></option>
 			 </c:if></c:forEach>
  			<%if(i==0){%>
  			<option value="add_role"><bean:message key="label.authorities.addRole"/></option>
  				<%}i=0;%>
<c:forEach var="row"  items="${roleauthority.rows}">
			<c:if test="${row.authorities == 'edit_remove_role'}" > 
  			<%i=1;%>
    		<option value="edit_remove_role" selected="selected"><bean:message key="label.authorities.editRole"/> </option>
 			 </c:if></c:forEach>
  			<%if(i==0){%>
  			<option value="edit_remove_role"><bean:message key="label.authorities.editRole"/> </option>
  				<%}i=0;%>
  				</optgroup>
  				<optgroup label='<bean:message key="label.optgroup.project"/>'>
 <c:forEach var="row"  items="${roleauthority.rows}">
			<c:if test="${row.authorities == 'add_project'}" > 
  			<%i=1;%>
    		<option value="add_project" selected="selected"><bean:message key="label.authorities.addProject"/> </option>
 			 </c:if></c:forEach>
  			<%if(i==0){%>
  			<option value="add_project"><bean:message key="label.authorities.addProject"/> </option>
  				<%}i=0;%>   
<c:forEach var="row"  items="${roleauthority.rows}">
			<c:if test="${row.authorities == 'edit_disable_project'}" > 
  			<%i=1;%>
    		<option value="edit_disable_project" selected="selected"><bean:message key="label.authorities.editProject"/></option>
 			 </c:if></c:forEach>
  			<%if(i==0){%>
  			<option value="edit_disable_project"><bean:message key="label.authorities.editProject"/></option>
  				<%}i=0;%>
  				<c:forEach var="row"  items="${roleauthority.rows}">
			<c:if test="${row.authorities == 'assign_project'}" > 
  			<%i=1;%>
    		<option value="assign_project" selected="selected"><bean:message key="label.authorities.projectTeamCreation"/></option>
 			 </c:if></c:forEach>
  			<%if(i==0){%>
  			<option value="assign_project"><bean:message key="label.authorities.projectTeamCreation"/></option>
  				<%}i=0;%>
  				</optgroup>
  				<optgroup label='<bean:message key="label.optgroup.member"/>'>
<c:forEach var="row"  items="${roleauthority.rows}">
			<c:if test="${row.authorities == 'add_member'}" > 
  			<%i=1;%>
    		<option value="add_member" selected="selected"><bean:message key="label.authorities.addMember"/></option>
 			 </c:if></c:forEach>
  			<%if(i==0){%>
  			<option value="add_member"><bean:message key="label.authorities.addMember"/></option>
  				<%}i=0;%>
<c:forEach var="row"  items="${roleauthority.rows}">
			<c:if test="${row.authorities == 'edit_remove_member'}" > 
  			<%i=1;%>
    		<option value="edit_remove_member" selected="selected"><bean:message key="label.authorities.editMember"/></option>
 			 </c:if></c:forEach>
  			<%if(i==0){%>
  			<option value="edit_remove_member"><bean:message key="label.authorities.editMember"/></option>
  				<%}i=0;%>
  				<c:forEach var="row"  items="${roleauthority.rows}">
			<c:if test="${row.authorities == 'edit_member_authority'}" > 
  			<%i=1;%>
    		<option value="edit_member_authority" selected="selected"><bean:message key="label.authorities.editMemberAuthority"/></option>
 			 </c:if></c:forEach>
  			<%if(i==0){%>
  			<option value="edit_member_authority"><bean:message key="label.authorities.editMemberAuthority"/></option>
  				<%}i=0;%>
  				</optgroup>


  				<optgroup label='<bean:message key="label.optgroup.task"/>'>
<c:forEach var="row"  items="${roleauthority.rows}">
			<c:if test="${row.authorities == 'assign_task'}" > 
  			<%i=1;%>
    		<option value="assign_task" selected="selected"><bean:message key="label.authorities.creatingTask"/></option>
 			 </c:if></c:forEach>
  			<%if(i==0){%>
  			<option value="assign_task"><bean:message key="label.authorities.creatingTask"/></option>
  				<%}i=0;%>
<c:forEach var="row"  items="${roleauthority.rows}">
			<c:if test="${row.authorities == 'edit_remove_task'}" > 
  			<%i=1;%>
    		<option value="edit_remove_task" selected="selected"><bean:message key="label.authorities.editTask"/></option>
 			 </c:if></c:forEach>
  			<%if(i==0){%>
  			<option value="edit_remove_task"><bean:message key="label.authorities.editTask"/></option>
  				<%}i=0;%>
  				</optgroup>
  				<optgroup label='<bean:message key="label.optgroup.documents"/>'>
 <c:forEach var="row"  items="${roleauthority.rows}">
			<c:if test="${row.authorities == 'upload_documents'}" > 
  			<%i=1;%>
    		<option value="upload_documents" selected="selected"><bean:message key="label.authorities.uploadDoc"/></option>
 			 </c:if></c:forEach>
  			<%if(i==0){%>
  			<option value="upload_documents"><bean:message key="label.authorities.uploadDoc"/></option>
  				<%}i=0;%>

<c:forEach var="row"  items="${roleauthority.rows}">
			<c:if test="${row.authorities == 'dwnld_documents'}" > 
  			<%i=1;%>
    		<option value="dwnld_documents" selected="selected"><bean:message key="label.authorities.downloadDoc"/></option>
 			 </c:if></c:forEach>
  			<%if(i==0){%>
  			<option value="dwnld_documents"><bean:message key="label.authorities.downloadDoc"/></option>
  				<%}i=0;%>
<c:forEach var="row"  items="${roleauthority.rows}">
			<c:if test="${row.authorities == 'remove_documents'}" > 
  			<%i=1;%>
    		<option value="remove_documents" selected="selected"><bean:message key="label.authorities.removeDoc"/></option>
 			 </c:if></c:forEach>
  			<%if(i==0){%>
  			<option value="remove_documents"><bean:message key="label.authorities.removeDoc"/></option>
  				<%}i=0;%>
  				</optgroup>
			
  				<optgroup label='<bean:message key="label.optgroup.password"/>'>			
<c:forEach var="row"  items="${roleauthority.rows}">
			<c:if test="${row.authorities == 'changed_user_password'}" > 
  			<%i=1;%>
    		<option value="changed_user_password" selected="selected"><bean:message key="label.authorities.editPassword"/></option>
 			 </c:if></c:forEach>
  			<%if(i==0){%>
  			<option value="changed_user_password"><bean:message key="label.authorities.editPassword"/></option>
  				<%}i=0;%>
 				</optgroup>
</select></td></tr>
 </c:otherwise> 
</c:choose> 
			</table><br>
			<table align="center">
			<tr><td><input type="submit" value='<bean:message key="label.saveChanges.button" />' class="butStnd"/></td>
			<td><input type="reset" value='<bean:message key="label.reset.button" />' class="butStnd" onclick="location.href='editRole.do?rolekey=<%=request.getParameter("rolekey")%>'"/></td>
			<td><input type="button" value='<bean:message key="label.cancel.button" />' class="butStnd" onclick="history.back();" /></td>
			</tr></table>
		</html:form>
		</div></div></div>
	</body>
</html>
