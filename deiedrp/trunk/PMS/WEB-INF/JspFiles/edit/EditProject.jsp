<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page import="javax.sql.rowset.CachedRowSet;" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%> 
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
 
<html> 
	<head>
	<html:javascript formName="editform" />
		<title>Edit Project</title>
	<script type="text/javascript" src="javascript/201a.js"></script>
	<script type="text/javascript" src="javascript/jquery.js"></script>
	<link rel="stylesheet" type="text/css" href="style/jquery.datepick.css" />
	<script type="text/javascript" src="javascript/jquery.datepick.js"></script>
	<link rel="stylesheet" type="text/css" href="style/jquery-ui.css" />
<script type="text/javascript" src="javascript/jquery-ui.min.js"></script>
	<script type="text/javascript">
	
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
	//for datepicker
	dates=document.getElementById('actualStartDate').value;
	
	if(dates!="null"){
	jQuery('#actualEndDate').datepick({ 
	dateFormat: 'yyyy-mm-dd',
	monthsToShow: 2,
	minDate: dates,
	showTrigger: '<img src="img/calendar-blue.gif" alt="Popup" class="trigger">'});
    }
		
});
 </script>
	
	</head>
	<body>
	
	<%!
		CachedRowSet crs_project=null;
	 %>
	<%
	crs_project=(CachedRowSet)request.getAttribute("crs");
  	%>
	<div style="padding-left:100px;padding-right:100px;padding-top:40px;">
	<div id="accordion">
	<h3><a href="#"><bean:message key="title.editProject"/> -</a></h3>
	<div>
		<html:form action="/go3" onsubmit="return validateEditform(this);">
		<div align="center"><html:errors property="sdate"/></div><br>
		<table cellspacing="1" cellpadding="6" border="0" align="center">
		<tr class="form-element"><td class="form-label">
		<input type="hidden" name="maxActualEndDate_task" <%if(crs_project.getString(12)==null){ %> value="<%=crs_project.getString(5)%>" <%}else {%> value="<%=crs_project.getString(12)%>"<%} %> id="maxActualEndDate_task" size="20" readonly="readonly"/>
		<html:errors property="maxActualEndDate_task"/>
		<input type="hidden" name="oldprojectname" value="<%= crs_project.getString(2)%>" id="oldprojectname" size="20" readonly="readonly"/>
		<html:errors property="oldprojectname"/>
			<bean:message key="label.projectCode"/> :</td>
			<td class="form-widget">
			 <html:text property="pcode" style="color: black;" disabled="true" size="40" value="<%=crs_project.getString(1)%>"/><html:errors property="pcode"/>
			 </td>
	
		<td  class="form-label">
		<bean:message key="label.projectName"/> : 
		</td>
		<td class="form-widget">
		<html:text property="pname" size="40" value="<%=crs_project.getString(2)%>"/><html:errors property="pname"/></td></tr>
		 <tr class="form-element"><td  class="form-label">
			<bean:message key="label.scheduleStartDate"/> :</td>
			<td class="form-widget">
			<input type="text" name="scheduleStartDate" id="scheduleStartDate" readonly="readonly"  value="<%=crs_project.getString(3)%>"/>
			<bean:message key="label.dateFormat"/></td>
			<td  class="form-label">
			<bean:message key="label.scheduleEndDate"/> :</td>
			<td class="form-widget">
			<input type="text" name="scheduleEndDate" id="scheduleEndDate" readonly="readonly"  value="<%=crs_project.getString(4)%>"/>
			<bean:message key="label.dateFormat"/>
			</td></tr>
			<tr class="form-element"><td  class="form-label">
			<bean:message key="label.actualStartDate"/> :</td>
			<td class="form-widget">
			<input type="text" name="actualStartDate" id="actualStartDate" readonly="readonly"  value="<%=crs_project.getString(5)%>"/>
			<bean:message key="label.dateFormat"/></td>
			<td  class="form-label">
			<bean:message key="label.actualEndDate"/> :</td>
			<td class="form-widget">
			<input type="text" name="actualEndDate" id="actualEndDate" readonly="readonly" <%if(crs_project.getString(6)==null){ %> value="" <%}else {%> value="<%=crs_project.getString(6)%>"<%} %>/>
			<bean:message key="label.dateFormat"/>
			</td></tr>
			
			<tr class="form-element"><td  class="form-label">
			<bean:message key="label.priority"/> :</td>
			<td class="form-widget"> <html:select property="priority" value="<%=crs_project.getString(8)%>">
			<% 
 	String[] st={"Low","Normal","High"};
	for(int i=0;i<=2;i++)
	{
	%>
	<html:option value="<%=st[i]%>"></html:option>
	<%
	}
	%>
	</html:select><html:errors property="priority"/></td>
	<td  class="form-label">
			<bean:message key="label.status"/> :</td><td class="form-widget"><html:select property="status" value="<%=crs_project.getString(9)%>">
			<% 
 String[] st={"In Progress","Complete","Planning","Canceled"};
for(int i=0;i<=3;i++)
	{
	%>
	<html:option value="<%=st[i]%>"></html:option>
	<%
	}
	%>
	</html:select><html:errors property="status"/></td>
			</tr>
				<tr class="form-element"><td  class="form-label">
			<bean:message key="label.targetBudget"/>:</td><td class="form-widget"> <html:text property="tbudget" size="40" value="<%=crs_project.getString(7)%>"/><html:errors property="tbudget"/></td>
			<td class="form-label">
				<bean:message key="label.ganttChartColor"/> :</td>
			<td class="form-widget">
			<div id="colorpicker201" style="position: fixed;padding-left: 195px"></div>
			<input type="text" name="gcolor" id="gcolor" size="9" readonly="readonly" value="<%=crs_project.getString(10)%>"/>&nbsp;<input type="button" onclick="showColorGrid2('gcolor','sample_1');" value="...">&nbsp;<input type="text" ID="sample_1" size="1" value="">
			 </td></tr>
			<tr class="form-element">
			<td class="form-label">
			<bean:message key="label.projectDescription"/> :</td><td class="form-widget"> <html:textarea property="darea" rows="2" cols="38" value="<%=crs_project.getString(11)%>"/><html:errors property="darea"/></td></tr>
			</table><br>
			<table align="center">
			<tr><td><input type="submit" value='<bean:message key="label.saveChanges.button" />' class="butStnd" /></td>
			<td><input type="reset" value='<bean:message key="label.reset.button" />' class="butStnd" /></td>
			<td><input type="button" value='<bean:message key="label.cancel.button" />' class="butStnd" onclick="history.back();" /></td>
			</tr></table>
		</html:form>
		</div></div></div>
	</body>
</html>
