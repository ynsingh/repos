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
	<h3><a href="#"><bean:message key="label.addMemberInBug"/> -</a></h3>
	<div>
 		 <html:javascript formName="bugConfigForm" dynamicJavascript="true"
			staticJavascript="true" />
  		  <html:form action="addUserIntoBugzilla" onsubmit="return validateBugConfigForm(this)">
     	  <br>
     	  
		  <br>
      <table cellspacing="1" cellpadding="6" border="0" align="center">
      
      
      <tr class="form-element">
		<td  class="form-label"><bean:message key="label.userId"/> :</td>
		<td class="form-widget">
		<html:select property="user_id" style="width: 270px;">
		<html:option value="--Select--"></html:option>
		<c:choose> 
  <c:when test="${sessionScope.authority == 'User'}" > 
  <sql:query var="orgrole" dataSource="jdbc/mydb">
				select distinct uio.valid_user_id from user_in_org uio,
					validatetab v,role r where v.valid_user_key=uio.valid_key
					and uio.valid_orgportal=? and r.role_id=v.valid_role_id 
					and v.Status='Active' order by uio.valid_user_id
				<sql:param value="${sessionScope.validOrgInPortal}"/>
			</sql:query>
			<c:forEach var="row" items="${orgrole.rows}">
			<html:option value="${row.valid_user_id}"></html:option>
			</c:forEach>
  </c:when> 
  <c:otherwise> 
   <!-- when login person is Super Admin -->
   <sql:query var="orgrole" dataSource="jdbc/mydb">
					SELECT distinct u.user_id FROM user_info u,user_in_org uio,
					user_role_in_org uro where (u.user_id=uio.valid_user_id and
					uio.valid_key=uro.valid_key and uro.PermittedBy=
					(select l.login_user_id from login l where l.authority='Super Admin'))
        			or u.user_id not in
				(select l.login_user_id from login l)order by u.user_id asc	
				</sql:query> 
				<c:forEach var="row" items="${orgrole.rows}">
			<html:option value="${row.user_id}"></html:option>
			</c:forEach>
   </c:otherwise> 
</c:choose> 
		
			
		 </html:select><font color="red" size="2">*</font>
		 <html:errors property="user_id"/>
			 </td>
			 <td>
			 <input type="submit" value='<bean:message key="label.addMember.button"/>' class="butStnd"/>
			<input type="button" value='<bean:message key="label.cancel.button" />' class="butStnd" onclick="location.href='mainwelcome.do'" />
			 </td>
			 </tr>
      
    
      </table>
     
    </html:form>
    </div></div></div>
  </body>
</html:html>