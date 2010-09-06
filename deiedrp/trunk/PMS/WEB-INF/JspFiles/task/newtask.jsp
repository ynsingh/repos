<%@ page import="java.sql.*" language="java" pageEncoding="UTF-8"%>
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
	 	jQuery.noConflict();
 		jQuery(document).ready(function(){
 		//for accordation
 	jQuery(function() {
		jQuery("#accordion").accordion({ collapsible: true,
		header: 'h3',
		fillSpace: false
		});
	});
 });
	 </script>
	  
  <script language="JavaScript" type="text/javascript">
   function clearData()
   {
     DWRUtil.removeAllOptions(document.getElementsByName("assignedTo")[0]);
     DWRUtil.removeAllOptions(document.getElementsByName("taskDependency")[0]);
   }
   
   	function taskGenerate() {
   	var taskName = DWRUtil.getValue("taskName");
   	var projectName = DWRUtil.getValue("projectName");
 	var orgportal = DWRUtil.getValue("orgportal");
 	
  DynamicList.taskList(taskName,projectName,orgportal,function(data)
  {
  	DWRUtil.removeAllOptions(document.getElementsByName("taskDependency")[0]);
  	DWRUtil.addOptions(document.getElementsByName("taskDependency")[0],data);
  	seeTask();
 	}
  ); 
 }
   
  function resourceGenerate() {
  var valid_key = DWRUtil.getValue("valid_key");
  var projectName = DWRUtil.getValue("projectName");
  var orgportal = DWRUtil.getValue("orgportal");
  var uname = DWRUtil.getValue("uname");
  DWRUtil.setValue("taskName","");
  //DynamicList.resourceList(uname,valid_key,projectName,orgportal,function(data)
  DynamicList.generateProjectTeamList(projectName,orgportal,1,function(data)
  {
   	DWRUtil.removeAllOptions(document.getElementsByName("assignedTo")[0]);
   	DWRUtil.addOptions(document.getElementsByName("assignedTo")[0],data);
  	DWRUtil.removeAllOptions(document.getElementsByName("taskDependency")[0]);
  	sdateGenerate();
   }
  ); 
 }
  
  function sdateGenerate() {
   var name = DWRUtil.getValue("projectName");
   var orgportal = DWRUtil.getValue("orgportal");
  DynamicList.pSDate(name,orgportal,function(data)
  {
  	DWRUtil.setValue("projSStartDate",data);
  	fdateGenerate();
  }
  ); 
 }
  
  function fdateGenerate() {
   var name = DWRUtil.getValue("projectName");
   var orgportal = DWRUtil.getValue("orgportal");
  DynamicList.pFDate(name,orgportal,function(data)
  {
  	DWRUtil.setValue("projSEndDate",data);
  	//jQuery start from here
	jQuery('#taskStartDate').datepick({ 
	dateFormat: 'yyyy-mm-dd',
	monthsToShow: 2,
	onSelect: customRange,
	showTrigger: '<img src="img/calendar-blue.gif" alt="Popup" class="trigger">'});
    customRange();
   	function customRange(dates) { 
        jQuery('#taskStartDate').datepick('option', 'minDate', document.getElementById('projSStartDate').value || null); 
   		jQuery('#taskStartDate').datepick('option', 'maxDate', document.getElementById('projSEndDate').value || null);
  	}
	//end here
  }
  ); 
  }
  
  function endDateGenerate(){
  
  var no_of_days = DWRUtil.getValue("no_of_days");
   var taskStartDate = DWRUtil.getValue("taskStartDate");
   if(taskStartDate!='')
   {
    DynamicList.genTaskEndDate(no_of_days,taskStartDate,function(data)
  	{
   		DWRUtil.setValue("taskEndDate",data);
  	}
  	); 
  	}
  }
  
  function seeTask() {
 	var projectName = DWRUtil.getValue("projectName");
	var taskname = DWRUtil.getValue("taskName");
	var orgportal = DWRUtil.getValue("orgportal");
  DynamicList.seeTaskExistence(projectName,taskname,orgportal,function(data)
  {
   	DWRUtil.setValue("taskname2",data);
  }
  ); 
 }
  </script>
	</head>
	<body onload="resourceGenerate()">
	<%
	String mysession=(String)session.getAttribute("mysession");
	if(mysession==null)
	{
		response.sendRedirect("login.jsp");  
	}
   %>
	<%!
		 Connection con=null;
		 PreparedStatement ps=null;
		 ResultSet rs=null;
	%>
		<%
		//For getting database connection.
			con=MyDataSource.getConnection();
		%>
	<div style="padding-left:100px;padding-right:100px;padding-top:40px;">
	<div id="accordion">
	<h3><a href="#">Create New Task -</a></h3>
	<div>	
	<html:javascript formName="taskform"/>
	<html:form action="/task" onsubmit="return validateTaskform(this);">	
	   <br>
	   <div style="text-align: center;color: red;">
		  <html:errors property="taskStartDate"/>
		  	  <html:errors property="taskEndDate"/>
		  	  <html:errors property="taskmsg"/>
		  </div>
		  <br>
		  <table cellspacing="1" cellpadding="6" border="0" align="center">
		  <tr>
		<td>
		<input type="hidden" name="orgportal" id="orgportal" value="<%=(String)session.getAttribute("validOrgInPortal") %>" size="20" readonly="readonly"/>
			<html:errors property="orgportal"/>
		<input type="hidden" name="uname" id="uname" value="<%=(String)session.getAttribute("uid") %>" size="20" readonly="readonly"/>
			<html:errors property="uname"/>
		<%
			//al.clear();
			String uid=(String)session.getAttribute("uid");
			//al.add(uid);
			try{
			ps=con.prepareStatement("select u.valid_key from user_in_org u"+
			" where u.valid_user_id=? and u.valid_orgportal=?");
			ps.setString(1,(String)session.getAttribute("uid"));
			ps.setString(2,(String)session.getAttribute("validOrgInPortal"));
			rs=ps.executeQuery();
			while(rs.next())
			{
			uid=rs.getString(1);
			}
			 %>
			<input type="hidden" name="valid_key" id="valid_key" value="<%=uid %>" size="20" readonly="readonly"/>
			<html:errors property="valid_key"/>
			<%
			}catch(Exception e){System.out.println(e);}
		
			 %>
			 </td></tr>
		<tr class="form-element">
			<td class="form-label">
			Project Name :</td><td class="form-widget">
			<html:select property="projectName" indexed="projectName" style="width: 270px;" 
			 onchange="resourceGenerate()">
			<html:option value="--Select--">--Select--</html:option>
			<% 
		try{
			ps=con.prepareStatement("select distinct p.project_name from project p,"+
									"user_in_org u,validatetab v where p.enable=0 and "+
									"u.valid_user_id=? and u.valid_orgportal=? "+
					"and u.valid_key=v.valid_user_key and v.valid_project_code=p.project_code"+
					" and v.valid_role_id=?");
			ps.setString(1,(String)session.getAttribute("uid"));
			ps.setString(2,(String)session.getAttribute("validOrgInPortal"));
			ps.setString(3,(String)session.getAttribute("roleid"));
			rs=ps.executeQuery();
			while(rs.next())
			{
			%>
			<html:option value="<%=rs.getString(1)%>"><%=rs.getString(1)%></html:option>
			<%
			}
			}
			catch(SQLException e){}
		finally
		{
			MyDataSource.freeConnection(con);
		}
		 %>
		</html:select><html:errors property="projectName"/><font color="red" size="2">*</font>
			</td>
			<html:errors property="projSStartDate"/><html:errors property="projSEndDate"/>
			<td class="form-label">Project (Start Date)</td>
			<td class="form-label"><input type="text" name="projSStartDate" id="projSStartDate" size="11" readonly="readonly" style="background-color:threedface;" value=""/>
			(End Date)
			<input type="text" name="projSEndDate" id="projSEndDate" size="11" readonly="readonly" style="background-color:threedface;" value=""/></td></tr>
			
		<tr class="form-element">
		<td class="form-label">
			Task Name : 
			</td>
		<td class="form-widget">
		<html:text property="taskName" indexed="taskName" size="40" value=""
		onchange="taskGenerate()" />
		 <font color="red" size="2">*</font>
		<html:errors property="taskName"/></td>
		<td  class="form-label">
		Assigned To :</td><td class="form-widget">
		<html:select property="assignedTo" style="width: 270px;" value="" >
		</html:select><html:errors property="assignedTo"/></td></tr>
		<tr class="form-element">
		<td class="form-label">
			No of days to do this task: 
			</td>
		<td class="form-widget">
		<html:text property="no_of_days" indexed="no_of_days" size="10" value="" onchange="endDateGenerate()"/><font color="red" size="2">*</font>
		<html:errors property="no_of_days"/></td>
		<td  class="form-label">
			Gantt Chart Color :</td><td class="form-widget"> 
			 <div id="colorpicker201" style="position: fixed;padding-left: 180px"></div>
	<input type="text" id="gantt_chart_color" name="gantt_chart_color" size="9" readonly="readonly" value="" />&nbsp;<input type="button" onclick="showColorGrid2('gantt_chart_color','sample_1');" value="...">&nbsp;<input type="text" ID="sample_1" size="1" value="">
			<html:errors property="gantt_chart_color"/></td></tr>
			<tr class="form-element"><td  class="form-label">
			Schedule Start Date :</td>
			<td class="form-widget">
			<input type="text" name="taskStartDate" id="taskStartDate" value="" onfocus="endDateGenerate()" />
			(YYYY-MM-DD)<font color="red" size="2">*</font>
			</td>
			<td  class="form-label">
			Schedule End Date :</td>
			<td class="form-widget">
			<html:text property="taskEndDate" indexed="taskEndDate" readonly="true" value=""/>
			(YYYY-MM-DD)<font color="red" size="2">*</font>
			</td></tr>
			
			<tr class="form-element"><td  class="form-label">
			Dependency :(if any)</td>
			<td class="form-widget"> 
			<html:select property="taskDependency" indexed="taskDependency" style="width: 270px;">
			</html:select>
			</td>
			<td  class="form-label">
			Task Description :</td><td class="form-widget">
			 <html:textarea property="remark"  rows="2" cols="39" value=""/>
			 <html:errors property="remark"/></td></tr>
			<tr><td>
			<input type="hidden" name="taskname2" id="taskname2" value="" size="20" readonly="readonly"/>
			<html:errors property="taskname2"/>
			</td></tr>
			</table>
			<table align="center">
			<tr><td><html:submit value="Add Task" styleClass="butStnd"/></td>
			<td><html:reset onclick="clearData();" styleClass="butStnd"></html:reset></td>
			<td><html:button property="back" value="Back" styleClass="butStnd" onclick="history.back();" /></td>
			</tr>
			</table>
		</html:form>
		</div></div></div>
	</body>
</html>
