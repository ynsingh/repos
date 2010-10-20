<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%> 
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 
<html> 
	<head>
		<title>JSP for Assign Project</title>
	<link rel="stylesheet" href="style/main.css" type="text/css"></link>
	<link rel="stylesheet" href="style/style.css" type="text/css"></link>
		<!-- You have to include these two JavaScript files -->
        <script type='text/javascript' src='dwr/engine.js'></script>
        <script type='text/javascript' src='dwr/util.js'></script>
	<!-- This JavaScript file is generated specifically for your application -->
         <script type='text/javascript' src='dwr/interface/DynamicList.js'></script>
	
	<script type="text/javascript">
	 
   function roleGenerate() {
   var userId = DWRUtil.getValue("userId");
   var orgportal = DWRUtil.getValue("orgportal");
  DynamicList.roleList(userId,orgportal,function(data)
  {
  	DWRUtil.removeAllOptions("roleName");
  	DWRUtil.addOptions("roleName",data);
   }
  ); 
 }
  </script>
	</head>
		
	<body onload="roleGenerate();">

	<html:javascript formName="projassignform" />
	<html:form action="/assign" onsubmit="return validateProjassignform(this);" >			
	<div id="main_title"><font color="#0044ff">
		   
		        <bean:message key="title.assignProjectPage"/> :
		  	
		 </font> </div>
		 <br><br><br>
		  <div align="center">
		  	  <html:errors property="Create_Project_Team_Msg"/>
		  </div>
		  <br>
		  <table cellspacing="2" cellpadding="10" width="40%" border="0" align="center">
	<tr><td>
	<input type="hidden" name="orgportal" id="orgportal" 
	value="${sessionScope.validOrgInPortal}" size="20" readonly="readonly"/>
		<html:errors property="orgportal"/>
	</td></tr>
			<tr class="form-element">
			<td  class="form-label">
			<bean:message key="label.projectName"/> :</td><td class="form-widget">
			 <html:select property="projectName" style="width: 270px;">
			<html:option value="--Select--"></html:option>
			<sql:query var="project" dataSource="jdbc/mydb">
				select distinct p.project_name from project p where 
			p.enable=0 and p.valid_org_inportal=? order by p.project_name asc
				 <sql:param value="${sessionScope.validOrgInPortal}"/>
			</sql:query>
			<c:forEach var="row" items="${project.rows}">
			<html:option value="${row.project_name}"></html:option>
			</c:forEach>
			</html:select><font color="red" size="2">*</font>
			<html:errors property="projectName"/>
			</td></tr>
			<tr></tr>
		<tr class="form-element">
		<td  class="form-label"><bean:message key="label.emailid"/> :</td>
		<td class="form-widget"> 
		 <html:select property="userId" indexed="userId" value="${param.userid}" onchange="roleGenerate();" style="width: 270px;">
			<html:option value="--Select--"></html:option>
			<sql:query var="userList" dataSource="jdbc/mydb">
				select valid_user_id from user_in_org where 
						valid_orgportal=? order by valid_user_id asc
				 <sql:param value="${sessionScope.validOrgInPortal}"/>
			</sql:query>
			<c:forEach var="row" items="${userList.rows}">
			<html:option value="${row.valid_user_id}"></html:option>
			</c:forEach>
			</html:select>
			<font color="red" size="2">*</font>
		<html:errors property="userId"/>
		</td></tr>
		<tr></tr>
		<tr class="form-element">
		<td  class="form-label"><bean:message key="label.roleName"/> :</td>
		<td class="form-widget">
		<select id="roleName" name="roleName" style="width: 270px;">
		 </select><font color="red" size="2">*</font>
		 <html:errors property="roleName"/>
			 </td></tr></table>
		  <table align="center" style="padding-top: 50px;">	
		 <tr><td>
			<input type="submit" value='<bean:message key="label.assignProject.button"/>' class="butStnd"/>
			<input type="reset" value='<bean:message key="label.reset.button" />' class="butStnd"  onclick="roleGenerate();"/>
			<input type="button" value='<bean:message key="label.back.button" />' class="butStnd" onclick="history.back();" />
          </td></tr>
			</table>
			
			</html:form>
			
	</body>
</html>

			