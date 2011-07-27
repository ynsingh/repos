<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
  <link rel="stylesheet" href="style/main.css" type="text/css"></link>
  <link rel="stylesheet" href="style/style.css" type="text/css"></link>
  <link rel="stylesheet" type="text/css" href="style/jquery-ui.css" />
<script type="text/javascript" src="javascript/jquery.js"></script>
<script type="text/javascript" src="javascript/jquery-ui.min.js"></script>
<script type="text/javascript">
jQuery.noConflict();
jQuery(document).ready(function(){

jQuery(function() {
		jQuery("#accordion").accordion({ collapsible: false,
		header: 'h3',
		fillSpace: false
		});
	});
});
</script>
  </head>
  
  <body>
  <div style="padding-left:100px;padding-right:100px;padding-top:40px;">
	<div id="accordion">
	<h3><a href="#"><bean:message key="title.roleDetails"/> -</a></h3>
	<div>
	 <logic:notEmpty name="roleDetail" property="list">
 	  <br>
 	<table cellspacing="2" cellpadding="4" style="margin-left: 150px;width: 60%">
		<logic:iterate name="roleDetail" length="1" property="list" id="var">
		<tr class="form-element">
			<td  width="26%"><font size="2"><bean:message key="label.roleName"/>:</font></td>
			<td class="hilite"><font size="3"><bean:write name="var" property="rolename"/></font></td>
		</tr>
		<tr class="form-element">
			<td><font size="2"><bean:message key="label.roleDescription"/>:</font></td>
			<td class="hilite"><bean:write name="var" property="roledescription" /></td>
		</tr></logic:iterate>
		<c:choose> 
  <c:when test="${sessionScope.authority == 'User'}" > 
   <!-- when login person is User not a Super Admin -->
   
		<tr><td class="ui-widget-header"> <bean:message key="label.authorities"/> :</td></tr>
		<logic:iterate name="roleDetail" property="list" id="var">
		<logic:equal name="var" property="roleauthority" value="add_role">
		<tr class="form-element"><td>
		<html:link page="/forwardPmsPage.do?parameter=newRole" paramProperty="rolename" paramId="id" paramName="var" styleClass="B">
		<bean:message key="label.authorities.addRole"/></html:link></td>
		<td class="hilite">: <bean:message key="detail.addRole"/></td>
		</tr></logic:equal>
		<logic:equal name="var" property="roleauthority" value="edit_remove_role">
		<tr class="form-element"><td>
		<html:link page="/forwardPmsPage.do?parameter=viewRole" paramProperty="rolename" paramId="id" paramName="var" styleClass="B">
		<bean:message key="label.authorities.editRole"/></html:link></td>
		  <td class="hilite">: <bean:message key="detail.editRole"/></td>
		  </tr></logic:equal>
		<logic:equal name="var" property="roleauthority" value="add_project">
		<tr class="form-element"><td>
		<html:link page="/forwardPmsPage.do?parameter=addProject" paramProperty="rolename" paramId="id" paramName="var" styleClass="B">
		<bean:message key="label.authorities.addProject"/></html:link></td><td>: <bean:message key="detail.addProject"/></td>
		  </tr></logic:equal>
		<logic:equal name="var" property="roleauthority" value="edit_disable_project">
		<tr class="form-element"><td>
		<html:link href="viewproject.do" paramProperty="rolename" paramId="id" paramName="var" styleClass="B">
		<bean:message key="label.authorities.editProject"/></html:link></td><td>: <bean:message key="detail.editProject"/></td>
		 </tr></logic:equal>
		 <logic:equal name="var" property="roleauthority" value="assign_project">
		<tr class="form-element"><td>
		<html:link href="assignproject.do" paramProperty="rolename" paramId="id" paramName="var" styleClass="B">
		<bean:message key="label.authorities.projectTeamCreation"/></html:link></td><td>: <bean:message key="detail.projectTeamCreation"/></td>
		 </tr></logic:equal>
		<logic:equal name="var" property="roleauthority" value="add_member">
		<tr class="form-element"><td>
		<html:link page="/forwardPmsPage.do?parameter=addMember" paramProperty="rolename" paramId="id" paramName="var" styleClass="B">
		<bean:message key="label.authorities.addMember"/></html:link></td><td>: <bean:message key="detail.addMember"/></td>
		  </tr></logic:equal>
		<logic:equal name="var" property="roleauthority" value="edit_remove_member">
		<tr class="form-element"><td>
		<html:link page="/forwardPmsPage.do?parameter=viewMember" paramProperty="rolename" paramId="id" paramName="var" styleClass="B">
		<bean:message key="label.authorities.editMember"/></html:link></td><td>: <bean:message key="detail.editMember"/></td>
		  </tr></logic:equal>
		
		<logic:equal name="var" property="roleauthority" value="edit_member_authority">
		<tr class="form-element"><td>
		<html:link href="editTask.do" paramProperty="rolename" paramId="id" paramName="var" styleClass="B">
		<bean:message key="label.authorities.editMemberAuthority"/> </html:link></td><td>: <bean:message key="detail.editMemberAuthority"/></td>
		  </tr></logic:equal>
		<logic:equal name="var" property="roleauthority" value="assign_task">
		 <tr class="form-element"><td>
		<html:link page="/forwardPmsPage.do?parameter=newTask" paramProperty="rolename" paramId="id" paramName="var" styleClass="B">
		<bean:message key="label.authorities.creatingTask"/> </html:link></td><td>: <bean:message key="detail.creatingTask"/></td>
		</tr></logic:equal>
		<logic:equal name="var" property="roleauthority" value="edit_remove_task">
		<tr class="form-element"><td>
		<html:link href="viewtask.do" paramProperty="rolename" paramId="id" paramName="var" styleClass="B">
		<bean:message key="label.authorities.editTask"/> </html:link></td><td>: <bean:message key="detail.editTask"/></td>
		 </tr></logic:equal>
		<!--  <logic:equal name="var" property="roleauthority" value="upload_documents">
		 <tr class="form-element"><td>
		<html:link href="upload.do" paramProperty="rolename" paramId="id" paramName="var" styleClass="B">
		Upload Documents </html:link></td><td>: Uploads the documents for sharing anything b/w the project members.</td>
		  </tr></logic:equal>
		<logic:equal name="var" property="roleauthority" value="dwnld_remove_documents">
		<tr class="form-element"><td>
		<html:link href="download.do" paramProperty="rolename" paramId="id" paramName="var" styleClass="B">
		Download / Remove Documents </html:link></td><td>: For downloading the documents.</td>
		 </tr></logic:equal> -->
		 <logic:equal name="var" property="roleauthority" value="changed_user_password">
		<tr class="form-element"><td>
		<html:link href="resetUserpassword.do" paramProperty="rolename" paramId="id" paramName="var" styleClass="B">
		<bean:message key="label.authorities.editPassword"/> </html:link></td><td>: <bean:message key="detail.editPassword"/></td>
		 </tr></logic:equal>
		</logic:iterate>
		</c:when>
  <c:otherwise> 
   <!-- when login person is Super Admin -->
		<tr><td class="ui-widget-header"> <bean:message key="label.authorities"/> :</td></tr>
		<logic:iterate name="roleDetail" property="list" id="var">
		<logic:equal name="var" property="roleauthority" value="add_role">
		<tr class="form-element"><td>
		<bean:message key="label.authorities.addRole"/></td>
		<td class="hilite">: <bean:message key="detail.addRole"/></td>
		</tr></logic:equal>
		<logic:equal name="var" property="roleauthority" value="edit_remove_role">
		<tr class="form-element"><td>
		<bean:message key="label.authorities.editRole"/></td>
		  <td class="hilite">: <bean:message key="detail.editRole"/></td>
		  </tr></logic:equal>
		 
		<logic:equal name="var" property="roleauthority" value="add_project">
		<tr class="form-element"><td>
		<bean:message key="label.authorities.addProject"/></td><td>: <bean:message key="detail.addProject"/></td>
		  </tr></logic:equal>
		<logic:equal name="var" property="roleauthority" value="edit_disable_project">
		<tr class="form-element"><td>
		<bean:message key="label.authorities.editProject"/></td><td>: <bean:message key="detail.editProject"/></td>
		 </tr></logic:equal>
		 <logic:equal name="var" property="roleauthority" value="assign_project">
		<tr class="form-element"><td>
		<bean:message key="label.authorities.projectTeamCreation"/></td><td>: <bean:message key="detail.projectTeamCreation"/></td>
		 </tr></logic:equal>
		<logic:equal name="var" property="roleauthority" value="add_member">
		<tr class="form-element"><td>
		<bean:message key="label.authorities.addMember"/></td><td>: <bean:message key="detail.addMember"/></td>
		  </tr></logic:equal>
		<logic:equal name="var" property="roleauthority" value="edit_remove_member">
		<tr class="form-element"><td>
		<bean:message key="label.authorities.editMember"/></td><td>: <bean:message key="detail.editMember"/></td>
		  </tr></logic:equal>
		
		<logic:equal name="var" property="roleauthority" value="edit_member_authority">
		<tr class="form-element"><td>
		<bean:message key="label.authorities.editMemberAuthority"/> </td><td>: <bean:message key="detail.editMemberAuthority"/></td>
		  </tr></logic:equal>
		<logic:equal name="var" property="roleauthority" value="assign_task">
		 <tr class="form-element"><td>
		<bean:message key="label.authorities.creatingTask"/></td><td>: <bean:message key="detail.creatingTask"/></td>
		</tr></logic:equal>
		<logic:equal name="var" property="roleauthority" value="edit_remove_task">
		<tr class="form-element"><td>
		<bean:message key="label.authorities.editTask"/> </td><td>: <bean:message key="detail.editTask"/></td>
		 </tr></logic:equal>
		<!--  <logic:equal name="var" property="roleauthority" value="upload_documents">
		 <tr class="form-element"><td>
		<html:link href="upload.do" paramProperty="rolename" paramId="id" paramName="var" styleClass="B">
		Upload Documents </html:link></td><td>: Uploads the documents for sharing anything b/w the project members.</td>
		  </tr></logic:equal>
		<logic:equal name="var" property="roleauthority" value="dwnld_remove_documents">
		<tr class="form-element"><td>
		<html:link href="download.do" paramProperty="rolename" paramId="id" paramName="var" styleClass="B">
		Download / Remove Documents </html:link></td><td>: For downloading the documents.</td>
		 </tr></logic:equal> -->
		 <logic:equal name="var" property="roleauthority" value="changed_user_password">
		<tr class="form-element"><td>
		<bean:message key="label.authorities.editPassword"/> </td><td>: <bean:message key="detail.editPassword"/></td>
		 </tr></logic:equal>
		</logic:iterate>
		</c:otherwise></c:choose>
		</table>
				
		</logic:notEmpty>
		<logic:empty name="roleDetail" property="list">
	 		<h1><bean:message key="label.roleHaveNoAuthority"/></h1>	
	 	</logic:empty>
	<br><div style="padding-left: 100px;">
	<input type="button" value='<bean:message key="label.back.button" />' class="butStnd" onclick="history.back();" />
    </div>
     
     </div></div></div>
  </body>
</html>
