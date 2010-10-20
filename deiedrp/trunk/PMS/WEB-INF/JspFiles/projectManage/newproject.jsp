<%@ page language="java" pageEncoding="UTF-8"%>

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%> 
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<html> 
	<head>
	
	<title>New Project</title>
	<link rel="stylesheet" href="style/main.css" type="text/css"></link>
	<link rel="stylesheet" type="text/css" href="style/jquery-ui.css" />
	<link rel="stylesheet" type="text/css" href="style/jquery.datepick.css" />
	
	<script type="text/javascript" src="javascript/201a.js"></script>
	<!-- You have to include these two JavaScript files -->
        <script type='text/javascript' src='dwr/engine.js'></script>
        <script type='text/javascript' src='dwr/util.js'></script>
	<!-- This JavaScript file is generated specifically for your application -->
         <script type='text/javascript' src='dwr/interface/DynamicList.js'></script>
	<script type="text/javascript" src="javascript/jquery.js"></script>
	<script type="text/javascript" src="javascript/jquery.datepick.js"></script>
	<script type="text/javascript" src="javascript/jquery-ui.min.js"></script>

	<script type="text/javascript">
	function seeProject() {
	
	var pname = DWRUtil.getValue("pname");
	var orgportal = DWRUtil.getValue("orgportal");
  DynamicList.seeProjectExistence(pname,orgportal,function(data)
  {
   	DWRUtil.setValue("prname",data);
  }
  ); 
 }
 
 <!-- for jquery -->
   
  		jQuery.noConflict();
 	jQuery(document).ready(function(){
 	//for accordation
 	jQuery(function() {
		jQuery("#accordion").accordion({ collapsible: true,
		header: 'h3',
		fillSpace: false
		});
	});
 	
 	//for datapicker
	jQuery('#scheduleStartDate,#scheduleEndDate').datepick({ 
	dateFormat: 'yyyy-mm-dd',
	monthsToShow: 2,
	onSelect: customRange, showTrigger: '<img src="img/calendar-blue.gif" alt="Popup" class="trigger">'});
     
	function customRange(dates) { 
    if (this.id == 'scheduleStartDate') { 
        jQuery('#scheduleEndDate').datepick('option', 'minDate', dates[0] || null); 
    } 
    else { 
        jQuery('#scheduleStartDate').datepick('option', 'maxDate', dates[0] || null); 
    } 
	}
});
 </script>
	</head>
	<body>

	<div style="padding-left:100px;padding-right:100px;padding-top:40px;">
	<div id="accordion">
	<h3><a href="#"><bean:message key="title.addProject"/> -</a></h3>
	<div>
	<html:javascript formName="projectform" dynamicJavascript="true" staticJavascript="true" />
	<html:form action="/go" onsubmit="return validateProjectform(this);">
		<br>
		  <div style="text-align: center;color: red;">
		  <html:errors property="scheduleStartDate"/>
		  <html:errors property="scheduleEndDate"/>
		  <html:errors property="actualEndDate"/>
		  <html:errors property="actualStartDate"/>
		  <html:errors property="pname"/>
		  <html:errors property="projmsg"/>
		  </div>
		  <br>
		
		<table cellspacing="1" cellpadding="6" border="0" align="center">
		
		<tr class="form-element">
		<td  class="form-label">
		<input type="hidden" name="orgportal" id="orgportal" value="<%=(String)session.getAttribute("validOrgInPortal") %>" size="20" readonly="readonly"/>
			<html:errors property="orgportal"/>
		<bean:message key="label.projectName"/> : 
		</td>
		<td class="form-widget">
		<html:text property="pname" indexed="pname" size="40" value="" onchange="seeProject()"/><font color="red" size="2">*</font>
		</td>
		<td  class="form-label">
			<bean:message key="label.targetBudget"/>:</td><td class="form-widget"> <html:text property="tbudget" value="" size="40"/><font color="red" size="2">*</font><html:errors property="tbudget"/></td>
			</tr>
		 <tr class="form-element"><td  class="form-label">
			<bean:message key="label.scheduleStartDate"/> :</td>
			<td class="form-widget">
			<input type="text" name="scheduleStartDate" id="scheduleStartDate"/>
			<bean:message key="label.dateFormat"/><font color="red" size="2">*</font></td>
			<td  class="form-label">
			<bean:message key="label.scheduleEndDate"/> :</td>
			<td class="form-widget">
			<input type="text" name="scheduleEndDate" id="scheduleEndDate" value=""/>
			<bean:message key="label.dateFormat"/><font color="red" size="2">*</font>
			</td></tr>
			<!--  <tr class="form-element"><td  class="form-label">
			Actual Start Date :</td>
			<td class="form-widget">
			<input type="text" name="actualStartDate" id="actualStartDate"/><a href="javascript:NewCssCal('actualStartDate','yyyymmdd')"><img src="img/cal.gif" width="16" height="16" border="0" alt="Pick a date"></a>
			(YYYY-MM-DD)<font color="red" size="2">*</font></td></tr>
			<tr class="form-element">
			<td  class="form-label">
			Actual End Date :</td>
			<td class="form-widget">
			<input type="text" name="actualEndDate" id="actualEndDate" value=""/><a href="javascript:NewCssCal('actualEndDate','yyyymmdd')"><img src="<html:rewrite page='/img/cal.gif'/>" width="16" height="16" border="0" alt="Pick a date"></a>
			(YYYY-MM-DD)
			</td></tr> -->
			
			<tr class="form-element"><td  class="form-label">
			<bean:message key="label.priority"/> :</td>
		<td  class="form-widget">
		<html:radio property="priority" value="Low">Low</html:radio>
		<html:radio property="priority" value="Normal">Normal</html:radio>
		<html:radio property="priority" value="High">High</html:radio>
		</td>
		<td  class="form-label">
			<bean:message key="label.status"/> :</td><td class="form-widget">
			 <html:select property="status" value="--Select--" >
			<% 
 String[] st1={"--Select--","In Progress","Complete","Planning","Pending","Canceled"};
for(int i=0;i<=5;i++)
	{
	%>
	<html:option value="<%=st1[i]%>"></html:option>
	<%
	}
	%>
	</html:select><font color="red" size="2">*</font><html:errors property="status"/></td>
			</tr>
			<tr class="form-element"><td class="form-label">
			<bean:message key="label.ganttChartColor"/> :</td>
			<td class="form-widget">
			<div id="colorpicker201" style="position: fixed;padding-left: 12px"></div>
			<input type="text" name="gcolor" id="gcolor" size="9" readonly="readonly"/>&nbsp;<input type="button" onclick="showColorGrid2('gcolor','sample_1');" value="...">&nbsp;<input type="text" ID="sample_1" size="1" value="">
			<font color="red" size="2">*</font>
			 </td>
			<td  class="form-label">
			<bean:message key="label.projectDescription"/> :</td><td class="form-widget">
			<html:textarea property="darea" value="" rows="2" cols="38"/>
			<html:errors property="darea"/></td></tr>
			</table><br>
			<table align="center">
			<tr><td><input type="submit" value='<bean:message key="label.add.button" />' class="butStnd" /></td>
			<td><input type="reset" value='<bean:message key="label.reset.button" />' class="butStnd" />
			<input type="button" value='<bean:message key="label.back.button" />' class="butStnd" onclick="history.back();" />

			<input type="hidden" name="prname" id="prname" value="" size="20" readonly="readonly"/>
			<html:errors property="prname"/>
			</td>
			</tr></table>
		</html:form>
		</div></div></div>
	</body>
</html>

