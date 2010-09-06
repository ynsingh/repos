<%@ page language="java" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%> 
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 
<html>
<head>
<link rel="stylesheet" type="text/css" href="style/jquery-ui.css" />
<script type="text/javascript" src="javascript/jquery.js"></script>
<script type="text/javascript" src="javascript/jquery-ui.min.js"></script>
<script type="text/javascript">
jQuery.noConflict();
jQuery(document).ready(function(){

jQuery(function() {
		jQuery("#accordion").accordion({ collapsible: true,
		header: 'h3',
		fillSpace: true
		});
	});
	jQuery(function() {
		jQuery("#accordion1").accordion({ collapsible: true,
		header: 'h3',
		fillSpace: true
		});
	});
	
});
</script>

</head>
<body> 
<div style="padding-top: 8px;padding-left: 50px;width: 90%;">
	<div id="accordion">
	<h3><a href="#"> Gantt Chart : ${param.key1}-</a></h3>
	<div>

<jsp:include page="/loadGanttChart.do?pname=${param.key1}"/>
</div></div></div>
<div style="padding-top: 5px;padding-left: 50px;width: 90%;">
<div id="accordion1">
	<h3><a href="#"> Task List : ${param.key1}-</a></h3>
	<div>
<jsp:include page="/loadProjectTask.do?key1=${param.key1}"/>
</div></div></div>
</body>
</html>