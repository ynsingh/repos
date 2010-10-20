<%@ page language="java" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%> 
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 
<html> 
	<head>
		<title>JSP for OrgPOrtalForm form</title>
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
	<h3><a href="#"><bean:message key="title.addOrgIntoPortal"/> -</a></h3>
	<div>
	<html:javascript formName="addorginportalform" dynamicJavascript="true"	staticJavascript="true" />
	<html:form action="/add_org_in_portal" onsubmit="return validateAddorginportalform(this)">
		<br>
		<div align="center">
		  	<html:errors property="addOrgIntoPortalMessage"/>
		   </div>
		  <br><br>
	 <table cellspacing="2" cellpadding="10" width="50%" border="0" align="center">
		 <tr></tr>
		<tr class="form-element">
		 <td class="form-label">
			<bean:message key="label.selectPortal"/> :</td>
		<td class="form-widget">
			<select id="portalname" name="portalname" style="width: 270px;">
			<option>--Select--</option>
			<sql:query var="sysportal" dataSource="jdbc/mydb">
				select distinct portal_name from portal order by portal_name asc
			</sql:query>
			<c:forEach var="row" items="${sysportal.rows}">
			<option><c:out value="${row.portal_name}"/></option>
			</c:forEach>
			 </select><font color="red" size="2">*</font>
			<html:errors property="portalname"/>
		</td></tr>
		<tr></tr>		
		<tr class="form-element">
		 <td class="form-label">
			<bean:message key="label.selectOrganization"/> :</td>
		<td class="form-widget">
			<select id="organisation" name="organisation" style="width: 270px;">
			<option>--Select--</option>
			<sql:query var="sysorg" dataSource="jdbc/mydb">
				select distinct org_name from organisation order by org_name asc
			</sql:query>
			<c:forEach var="row" items="${sysorg.rows}">
			<option><c:out value="${row.org_name}"/></option>
			</c:forEach>
			</select><font color="red" size="2">*</font>
			<html:errors property="organisation"/>
		</td></tr><tr></tr>	
		<tr class="form-element">
		 <td class="form-label">
		<bean:message key="label.emailid"/> :</td>
		<td class="form-widget"> 
		 <html:select property="emailid" indexed="emailid" value="${param.userid}" style="width: 270px;">
			<html:option value="--Select--"></html:option>
			<sql:query var="user1" dataSource="jdbc/mydb">
				SELECT valid_user_id FROM user_in_org
			</sql:query>
			<c:if test="${empty user1.rows}">
				<sql:query var="user" dataSource="jdbc/mydb">
					SELECT distinct u.user_id FROM user_info u 
					where u.user_id not in (select l.login_user_id from login l)
					order by u.user_id asc
					</sql:query>
 				</c:if>
 				<c:if test="${!empty user1.rows}">
 				<sql:query var="user" dataSource="jdbc/mydb">
					SELECT distinct u.user_id FROM user_info u,user_in_org uio,
					user_role_in_org uro where (u.user_id=uio.valid_user_id and
					uio.valid_key=uro.valid_key and uro.PermittedBy=
					(select l.login_user_id from login l where l.authority='Super Admin'))
        			or u.user_id not in
				(select l.login_user_id from login l)order by u.user_id asc	
				</sql:query> 
  				</c:if>
			<c:forEach var="row" items="${user.rows}">
			<html:option value="${row.user_id}"></html:option>
			</c:forEach>
			</html:select>
			<font color="red" size="2">*</font>
		<html:errors property="emailid"/>
		</td></tr>
		<tr></tr>
		<tr class="form-element">
		 <td class="form-label">
			<bean:message key="label.selectRole"/> :</td>
		<td class="form-widget">
			<select id="role" name="role" style="width: 270px;">
			<option>--Select--</option>
			<sql:query var="sysrole" dataSource="jdbc/mydb">
				select distinct role_name from role where ValidOrgPortal IS NULL order by role_name asc
			</sql:query>
			<c:forEach var="row" items="${sysrole.rows}">
			<option><c:out value="${row.role_name}"/></option>
			</c:forEach>
			</select><font color="red" size="2">*</font><html:errors property="role"/>
			 </td></tr>
		</table>
		 <table style="padding-top: 30px;" align="center">	
			<tr></tr><tr></tr><tr></tr>		  
			<tr><td>
			<input type="submit" value='<bean:message key="label.done.button" />' class="butStnd"/>
			<input type="reset" value='<bean:message key="label.reset.button" />' class="butStnd" onclick="location.href='addorg_in_portal.do'"/>
			<input type="button" value='<bean:message key="label.cancel.button" />' class="butStnd" onclick="location.href='welcome.do'" />
            </td></tr>
			</table>
		</html:form>
		</div></div></div>		
	</body>
</html>







