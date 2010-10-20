<%@ page language="java" pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%> 
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<html> 
	<head>
	
	<title>Create Portal</title>
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
	<script type="text/javascript">
	function seePortal() {
	var name = DWRUtil.getValue("portalname");
	var info="portal";
   DynamicList.seeExistence(name,info,function(data)
  {
  	DWRUtil.setValue("portalname2",data);
  }
  ); 
 }
 </script>
	</head>
	<body>

<div style="padding-left:100px;padding-right:100px;padding-top:40px;">
	<div id="accordion">
	<h3><a href="#"><bean:message key="title.portalPage"/> -</a></h3>
	<div>
	<html:javascript formName="newportalform" dynamicJavascript="true" staticJavascript="true" />
	<html:form action="/addportal" onsubmit="return validateNewportalform(this);">
	  <br>
	  <div align="center">
		  	<html:errors property="portalname"/>
		  	<html:errors property="portalmsg"/>
		   </div>
		  <br>
		
		<table cellspacing="1" cellpadding="6" width="50%" border="0" align="center">
		<tr class="form-element">
		<td class="form-label">
		
		<bean:message key="label.portalName"/> : 
		</td>
		<td class="form-widget">
		<html:text property="portalname" indexed="portalname" size="35" value="" onchange="seePortal()"/><font color="red" size="2">*</font></td></tr>
		<tr class="form-element">
		<td  class="form-label">
			<bean:message key="label.portalDescription"/> :</td><td class="form-widget">
			<html:textarea property="portaldescription" value="" rows="3" cols="33"/>
			<html:errors property="portaldescription"/></td></tr>
			</table><br><br>
			<table align="center">
			<tr><td>
			<input type="submit" value='<bean:message key="label.add.button" />' class="butStnd" /></td>
			<td><input type="reset" value='<bean:message key="label.reset.button" />' class="butStnd"/>
			<input type="button" value='<bean:message key="label.back.button" />' class="butStnd" onclick="history.back();" />

			<input type="hidden" name="portalname2" id="portalname2" value="" size="20" readonly="readonly"/>
			<html:errors property="portalname2"/>
			</td>
			</tr></table>
		</html:form>
	</div>
	</div>
	</div>
	
	</body>
</html>

