<%@ page import="java.sql.*" language="java" pageEncoding="UTF-8"%>
<%@ page import="javax.sql.rowset.CachedRowSet" %>
<%@ page import="in.ac.dei.edrp.pms.dataBaseConnection.MyDataSource;"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%> 
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
   
<html> 
	<head>
	<title>JSP for NewTask form</title>
	<link rel="stylesheet" href="style/main.css" type="text/css"></link>
	<script type="text/javascript" src="javascript/201a.js"></script>
	
	<!-- You have to include these two JavaScript files -->
        <script type='text/javascript' src='dwr/engine.js'></script>
        <script type='text/javascript' src='dwr/util.js'></script>
	<!-- This JavaScript file is generated specifically for your application -->
         <script type='text/javascript' src='dwr/interface/DynamicList.js'></script>
	<script type="text/javascript" src="javascript/jquery.js"></script>
	<link rel="stylesheet" type="text/css" href="style/jquery.datepick.css" />
	<script type="text/javascript" src="javascript/jquery.datepick.js"></script>
	<link rel="stylesheet" type="text/css" href="style/jquery-ui.css" />
<script type="text/javascript" src="javascript/jquery-ui.min.js"></script>
	<script type="text/javascript">
	
	 <!-- for jquery -->
    jQuery.noConflict();
 	jQuery(document).ready(function(){
 		//for progress bar
 		jQuery("#task_percentage_completion").change(function() {  
        jQuery("#progressbar").progressbar({ value: Number(document.getElementById('task_percentage_completion').value) });  
       	});

//for accordation
 	jQuery(function() {
		jQuery("#accordion").accordion({ collapsible: true,
		header: 'h3',
		fillSpace: false
		});
		jQuery("#progressbar").progressbar({ value: Number(document.getElementById('task_percentage_completion').value) });
	});
	//for datapicker
	jQuery('#actualEndDate').datepick({ 
	dateFormat: 'yyyy-mm-dd',
	monthsToShow: 2,
	minDate: document.getElementById('actualStartDate').value,
	onSelect: customRange,
	showTrigger: '<img src="img/calendar-blue.gif" alt="Popup" class="trigger">'});
    
	jQuery('#actualStartDate').datepick({ 
	dateFormat: 'yyyy-mm-dd',
	monthsToShow: 2,
	minDate: document.getElementById('taskStartDate').value,
	onSelect: customRange,
	showTrigger: '<img src="img/calendar-blue.gif" alt="Popup" class="trigger">'});
    	
   	function customRange(dates) { 
    if (this.id == 'actualStartDate') { 
        jQuery('#actualEndDate').datepick('option', 'minDate', dates[0] || null); 
    } 
    else { 
        jQuery('#actualStartDate').datepick('option', 'maxDate', dates[0] || null); 
    } 
	}
	
	
});
 </script>
  <script language="JavaScript" type="text/javascript">
   
  	function taskGenerate() {
  
   	var taskName = DWRUtil.getValue("taskName");
   	var projectName = DWRUtil.getValue("projectName");
 	var orgportal = DWRUtil.getValue("orgportal");
 	
  DynamicList.taskList(taskName,projectName,orgportal,function(data)
  {
  	DWRUtil.removeAllOptions(document.getElementsByName("taskDependency")[0]);
  	DWRUtil.addOptions(document.getElementsByName("taskDependency")[0],data);
  	DWRUtil.setValue("taskDependency",DWRUtil.getValue("taskDependencyValue"));
  	}
  ); 
 }
 </script>
	</head>
	<body onload="taskGenerate()">
	
	<%!
		Connection con=null;
		ResultSet rs=null;
		String tdep="No";
		CachedRowSet crs_task=null;
	 %>
	<div style="padding-left:100px;padding-right:100px;padding-top:40px;">
	<div id="accordion">
	<h3><a href="#"><bean:message key="title.editTask"/> -</a></h3>
	<div>
	<html:javascript formName="edittaskform" />
	<html:form action="/go4" onsubmit="return validateEdittaskform(this);">	
		
		 <%
			crs_task=(CachedRowSet)request.getAttribute("crs");
	    	con=MyDataSource.getConnection();
		 %>
		  <div align="center">
		  	  <html:errors property="taskmsg"/>
		  </div>
		  <table cellspacing="1" cellpadding="6" border="0" align="center">
		  <tr>
		<td>
		<input type="hidden" name="orgportal" id="orgportal" value="${sessionScope.validOrgInPortal}" size="20" readonly="readonly"/>
			<html:errors property="orgportal"/>
		<input type="hidden" name="uname" id="uname" value="${sessionScope.uid}" size="20" readonly="readonly"/>
			<html:errors property="uname"/>
		<!-- <input type="hidden" name="projAStartDate" id="projAStartDate" value="<%=crs_task.getString(14)%>" size="20" readonly="readonly"/>
			<html:errors property="projAStartDate"/>
		<input type="hidden" name="projAEndDate" id="projAEndDate" value="<%=crs_task.getString(15)%>" size="20" readonly="readonly"/>
			<html:errors property="projAEndDate"/> -->
			<input type="hidden" name="project_code" id="project_code" value="<%=crs_task.getString(16)%>" size="20" readonly="readonly"/>
			<html:errors property="project_code"/>
		</td></tr>
		<tr class="form-element">
		<td  class="form-label">
			<bean:message key="label.projectName"/> : 
			</td>
		<td class="form-widget">
		<html:text property="projectName" style="color: blue;" value="<%=crs_task.getString(1)%>" disabled="true" size="40" />
		<html:errors property="projectName"/></td></tr>
		<tr class="form-element">
		<td  class="form-label">
			<bean:message key="label.taskId"/> : 
			</td>
		<td class="form-widget">
		<html:text property="taskId" style="color: black;" indexed="taskId" value="<%=crs_task.getString(2)%>" disabled="true" size="40" />
		<html:errors property="taskId"/></td>
		<td  class="form-label">
			<bean:message key="label.taskName"/> : 
			</td>
		<td class="form-widget">
		<html:text property="taskName" style="color: black;" disabled="true" indexed="taskName" value="<%=crs_task.getString(3)%>"  size="40" />
		<html:errors property="taskName"/></td></tr>
		<tr class="form-element">
		<td class="form-label">
			<bean:message key="label.numberOfDays"/>: 
			</td>
		<td class="form-widget">
		<html:text property="no_of_days" style="color: black;" disabled="true" indexed="no_of_days" size="10" value="<%=crs_task.getString(4)%>"/>
		<html:errors property="no_of_days"/></td><td class="form-label">
			<bean:message key="label.ganttChartColor"/> :</td><td class="form-widget"> 
			 <div id="colorpicker201" style="position: fixed;padding-left: 195px"></div>
	<input type="text" id="gantt_chart_color" name="gantt_chart_color" size="9" readonly="readonly" value="<%=crs_task.getString(9)%>" />&nbsp;<input type="button" onclick="showColorGrid2('gantt_chart_color','sample_1');" value="...">&nbsp;<input type="text" ID="sample_1" size="1" value="">
			<html:errors property="gantt_chart_color"/></td></tr>
		<tr class="form-element"><td  class="form-label">
			<bean:message key="label.scheduleStartDate"/> :</td>
			<td class="form-widget">
			<input type="text" name="taskStartDate" id="taskStartDate" readonly="readonly" value="<%=crs_task.getString(5)%>" />
			<bean:message key="label.dateFormat"/>
			</td>
			<td  class="form-label">
			<bean:message key="label.scheduleEndDate"/> :</td>
			<td class="form-widget">
			<input type="text" name="taskEndDate" id="taskEndDate" readonly="readonly" value="<%=crs_task.getString(6)%>"/>
			<bean:message key="label.dateFormat"/>
			</td></tr>
		 <tr class="form-element"><td  class="form-label">
			<bean:message key="label.actualStartDate"/> :</td>
			<td class="form-widget">
			<input type="text" name="actualStartDate" id="actualStartDate" value="<%=crs_task.getString(7)%>"/>
			<bean:message key="label.dateFormat"/><font color="red" size="2">*</font></td>
			<td  class="form-label">
			<bean:message key="label.actualEndDate"/> :</td>
			<td class="form-widget">
			<input type="text" name="actualEndDate" id="actualEndDate" <%if(crs_task.getString(8)==null){ %> value="" <%}else {%> value="<%=crs_task.getString(8)%>"<%} %>/>
			<bean:message key="label.dateFormat"/>
			</td></tr>
						
			<tr class="form-element">
		<td  class="form-label">
			<bean:message key="label.taskCompleted"/> : 
			</td>
		<td class="form-widget">
   			<div id="progressbar"></div>
   			<input type="text" name="task_percentage_completion" id="task_percentage_completion"
			 size="5" value="<%=crs_task.getString(10)%>"/><strong><font color="#0000ff">%</font></strong>
			<font color="red" size="2">*</font><html:errors property="task_percentage_completion"/>
			
			</td>
			<td class="form-label">
			<bean:message key="label.status"/> :
			</td>
			<td class="form-widget">
			<html:select property="status" value="<%=crs_task.getString(11)%>" >
			<% 
 	String[] sttus={"--Select--","Completed","Not Completed","Planning","Pending","Canceled"};
	for(int i=0;i<=5;i++)
	{
	%>
	<html:option value="<%=sttus[i]%>"></html:option>
	<%
	}
	%>
	</html:select><font color="red" size="2">*</font><html:errors property="status"/></td>
			</tr>
			<tr class="form-element"><td class="form-label">
			<bean:message key="label.dependency"/> :</td>
			<td class="form-widget">
			<%
			try{
			//System.out.println("dependency="+crs_task.getString(12));
			tdep="No";
			PreparedStatement ps1=con.prepareStatement("select Task_Name from task where Task_Id=?");
    		ps1.setString(1,crs_task.getString(12));
    		rs=ps1.executeQuery();
    		if(rs.next())
    		{
   	 			tdep=rs.getString(1);
    		}
			}
			catch(Exception e){
			tdep="No";
				}
				finally
				{
					MyDataSource.freeConnection(con);
				}
			 %>
			 
			<html:select property="taskDependency" indexed="taskDependency" style="width: 260px;" >
			</html:select>
			<input type="hidden" name="taskDependencyValue" id="taskDependencyValue" value="<%=tdep%>" size="20" readonly="readonly"/>
			<html:errors property="taskDependencyValue"/>
			</td>
			<td  class="form-label">
			<bean:message key="label.taskDescription"/> :</td><td class="form-widget">
			 <html:textarea property="remark"  rows="2" cols="38" value="<%=crs_task.getString(13)%>"/>
			 <html:errors property="remark"/></td></tr>
			</table>
			<table align="center">
			<tr><td><input type="submit" value='<bean:message key="label.saveChanges.button" />' class="butStnd" /></td>
			<td><input type="reset" value='<bean:message key="label.reset.button" />' class="butStnd" onclick="taskGenerate()"/></td>
			<td><input type="button" value='<bean:message key="label.cancel.button" />' class="butStnd" onclick="history.back();" /></td>
			</tr>
			</table>
		</html:form>
		</div></div></div>
	</body>
</html>

