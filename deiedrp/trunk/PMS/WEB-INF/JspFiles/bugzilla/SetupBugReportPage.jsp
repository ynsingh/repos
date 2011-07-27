<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>

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
	<h3><a href="#"><bean:message key="label.bugStting"/> -</a></h3>
	<div style="padding: 50px;font-size: 20px;">
 	 Before reporting your bugs, first of all you should 
 	 <html:link styleClass="B" page="/bugzillaConfig.do?parameter=addProjectToReportBug">
 	 <bean:message key="label.addProject.forBug"/> </html:link>
 	 in which you want to report a bug  <br>
 	 then after you should 
 	<html:link styleClass="B" page="/bugzillaConfig.do?parameter=addComponentToReportBug">
 	<bean:message key="label.addComponent.forBug"/> </html:link>
 	(part of the project) in which you found the bug.<br>
 	If you have done all the above procedures then for reporting a bug click here
 	 <html:link styleClass="B"  action="reportBug"><bean:message key="submenu.forBugzilla.reportBug"/>
</html:link>
    </div></div></div>
  </body>
</html:html>
 