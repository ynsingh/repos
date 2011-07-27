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
	<h3><a href="#"><bean:message key="label.addComponentToReportBug"/> -</a></h3>
	<div>
 		 <html:javascript formName="componentConfigFormIntoBugzilla" dynamicJavascript="true"
			staticJavascript="true" />
  		  <html:form action="addComponentIntoBugzilla" onsubmit="return validateComponentConfigFormIntoBugzilla(this)">
     	  <br>
     	  <div align="center">
     	  		<html:errors property="component_into_bugzilla_msg"/>
		   </div>
     	  <br>
      <table cellspacing="1" cellpadding="6" border="0" align="center">
      
      
      <tr class="form-element">
		<td class="form-label"><bean:message key="label.projectName"/> :</td>
		<td class="form-widget">
		<html:select property="projectName" style="width: 270px;">
		<html:option value="--Select--">--Select--</html:option>
			<sql:query var="projectList" dataSource="jdbc/myBugzillaDB">
				select distinct p.name from products p
				where p.isactive > 0 and p.name != 'TestProduct'
				
			</sql:query>
			<c:forEach var="row" items="${projectList.rows}">
			<html:option value="${row.name}"></html:option>
			</c:forEach>
		 </html:select><font color="red" size="2">*</font>
		 <html:errors property="projectName"/>
			 </td></tr>
		<tr class="form-element">
          <td class="form-label"><bean:message key="label.component"/> :</td>
          <td class="form-widget"><html:text property="component" size="42" /><font color="red" size="2">*</font>
         <html:errors property="component"/>
          </td>
          </tr>
        <tr class="form-element">
          <td class="form-label"><bean:message key="label.description"/> :</td>
          <td class="form-widget"><html:textarea property="componentDescription" rows="2" cols="39"/><font color="red" size="2">*</font>
          <html:errors property="componentDescription"/>
          </td>
        </tr>
        
        <tr class="form-element">
          <td class="form-label"><bean:message key="label.defaultAssignedTo"/> :</td>
          <td class="form-widget">
          <html:select property="assignedTo" style="width: 270px;">
		<html:option value="--Select--">--Select--</html:option>
			<sql:query var="userList" dataSource="jdbc/myBugzillaDB">
				select distinct p.login_name from profiles p
				where p.disabledtext ='' order by p.login_name asc
			</sql:query>
			<c:forEach var="row" items="${userList.rows}">
			<html:option value="${row.login_name}"></html:option>
			</c:forEach>
		 </html:select><font color="red" size="2">*</font>
		 
		 <html:errors property="assignedTo"/>
          </td>
        </tr>
        <tr><td></td><td>(person's should be added into bugzilla)</td></tr>
        </table><br>
        <table align="center">
        <tr><td>
			 <input type="submit" value='<bean:message key="label.add.button"/>' class="butStnd"/>
			<input type="button" value='<bean:message key="label.cancel.button" />' 
			class="butStnd" onclick="window.location.href='bugzillaConfig.do?parameter=setupReportBug'" />
			 </td>
			 </tr>
 
      </table>
     
    </html:form>
    </div></div></div>
  </body>
</html:html>