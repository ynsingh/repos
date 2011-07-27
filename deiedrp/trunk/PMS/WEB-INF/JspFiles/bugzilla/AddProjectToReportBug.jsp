<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html lang="true">
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
		jQuery("#accordion").accordion({ collapsible: true,
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
	<h3><a href="#"><bean:message key="label.addProjectToReportBug"/> -</a></h3>
	<div>
 		 <html:javascript formName="projectConfigFormIntoBugzilla" dynamicJavascript="true"
			staticJavascript="true" />
  		  <html:form action="addProjectIntoBugzilla" onsubmit="return validateProjectConfigFormIntoBugzilla(this)">
     	  <br>
     	  <div align="center">
     	  		<html:errors  property="project_into_bugzilla_msg"/>
		   </div>
     	  <br>
      <table cellspacing="1" cellpadding="6" border="0" align="center">
      
      
      <tr class="form-element">
		<td><bean:message key="label.projectName"/> :</td>
		<td>
		<html:select property="projectName" style="width: 270px;">
		<html:option value="--Select--">--Select--</html:option>
			<sql:query var="projectList" dataSource="jdbc/mydb">
				select distinct p.project_name from project p,
					user_in_org u,validatetab v where p.enable=0 and 
					u.valid_user_id=? and u.valid_orgportal=? 
					and u.valid_key=v.valid_user_key and v.valid_project_code=p.project_code
					and v.valid_role_id=?
					<sql:param value="${sessionScope.uid}"/>
					<sql:param value="${sessionScope.validOrgInPortal}"/>
					<sql:param value="${sessionScope.roleid}"/>
			</sql:query>
			<c:forEach var="row" items="${projectList.rows}">
			<html:option value="${row.project_name}"></html:option>
			</c:forEach>
		 </html:select><font color="red" size="2">*</font>
		 <html:errors property="projectName"/>
			 </td>
			 <td><bean:message key="label.project.version"/> :
			 <html:text property="projectVersion"></html:text>
			 <html:errors property="projectVersion"/>
			 </td>
			 <td>
			 <input type="submit" value='<bean:message key="label.add.button"/>' class="butStnd"/>
			<input type="button" value='<bean:message key="label.cancel.button" />' class="butStnd" onclick="window.location.href='bugzillaConfig.do?parameter=setupReportBug'" />
			 </td>
			 </tr>
 
      </table>
     
    </html:form>
    </div></div></div>
  </body>
</html:html>