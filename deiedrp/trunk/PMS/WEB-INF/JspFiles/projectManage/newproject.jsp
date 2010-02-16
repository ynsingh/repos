<%@ page language="java" pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%> 
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<html> 
	<head>
	
	<title>New Project</title>
	<link rel="stylesheet" href="style/main.css" type="text/css"></link>
	<link rel="stylesheet" href="style/style.css" type="text/css"></link>
	<script type="text/javascript" src="javascript/201a.js"></script>
	<script type="text/javascript" src="javascript/datetimepicker.js"></script>
		<!-- You have to include these two JavaScript files -->
        <script type='text/javascript' src='dwr/engine.js'></script>
        <script type='text/javascript' src='dwr/util.js'></script>
	<!-- This JavaScript file is generated specifically for your application -->
         <script type='text/javascript' src='dwr/interface/DynamicList.js'></script>
	
	<script type="text/javascript">
	function seeProject() {
	
	var name = DWRUtil.getValue("pname");
	var info="project";
   DynamicList.seeExistence(name,info,function(data)
  {
  	DWRUtil.setValue("prname",data);
  }
  ); 
 }
 </script>
	</head>
	<body>
	
	<%
	String mysession=(String)session.getAttribute("mysession");
	if(mysession==null)
	{
		response.sendRedirect("login.jsp");  
	}
	%>
	
	<html:javascript formName="projectform" dynamicJavascript="true" staticJavascript="true" />
	<html:form action="/go" onsubmit="return validateProjectform(this);">
	<div id="main_title">
		 <font color="#0044ff"> Add New Project:</font>
	  </div><br>
		  <div align="center">
		  <html:errors property="fdate"/>
		  <html:errors property="pname"/>
		  <html:errors property="projmsg"/>
		  
		  </div>
		  <br>
		
		<table cellspacing="2" cellpadding="2" border="0" align="center">
		<tr class="form-element">
	
		</tr>
		<tr class="form-element">
		<td  class="form-label">
		
		Project Name : 
		</td>
		<td class="form-widget">
		<html:text property="pname" indexed="pname" size="40" value="" onchange="seeProject()"/><font color="red" size="2">*</font></td></tr>
		 <tr class="form-element"><td  class="form-label">
			Start Date :</td>
			<td class="form-widget">
			<input type="text" name="sdate" id="sdate"/><a href="javascript:NewCssCal('sdate','yyyymmdd')"><img src="img/cal.gif" width="16" height="16" border="0" alt="Pick a date"></a>
			(YYYY-MM-DD)<font color="red" size="2">*</font></td></tr>
			<tr class="form-element">
			<td  class="form-label">
			Finished Date :</td>
			<td class="form-widget">
			<input type="text" name="fdate" id="fdate" value=""/><a href="javascript:NewCssCal('fdate','yyyymmdd')"><img src="<html:rewrite page='/img/cal.gif'/>" width="16" height="16" border="0" alt="Pick a date"></a>
			(YYYY-MM-DD)<font color="red" size="2">*</font>
			</td></tr>
			<tr class="form-element"><td  class="form-label">
			Target Budget(Rs.):</td><td class="form-widget"> <html:text property="tbudget" value="" size="40"/><font color="red" size="2">*</font><html:errors property="tbudget"/></td>
			</tr>
			<tr class="form-element"><td  class="form-label">
			Priority :</td>
		<td  class="form-widget">
		<html:radio property="priority" value="Low">Low</html:radio>
		<html:radio property="priority" value="Normal">Normal</html:radio>
		<html:radio property="priority" value="High">High</html:radio>
		</td>
		</tr>
			<tr class="form-element"><td  class="form-label">
			Status :</td><td class="form-widget">
			 <html:select property="status" value="--Select--" >
			<% 
 String[] st1={"--Select--","In Progress","Complete","Planning","Canceled"};
for(int i=0;i<=4;i++)
	{
	%>
	<html:option value="<%=st1[i]%>"></html:option>
	<%
	}
	%>
	</html:select><font color="red" size="2">*</font><html:errors property="status"/></td>
			</tr>
			
			<tr class="form-element"><td  class="form-label">
			View Permission :</td><td  class="form-widget">
		<html:radio property="viewPermission" value="For All">For All</html:radio>
		<html:radio property="viewPermission" value="Only For Project Members">Only For Project Members</html:radio>
		</td>
		</tr>
		
			<tr class="form-element"><td class="form-label">
			Gantt Chart Color :</td>
			<td class="form-widget">
			<div id="colorpicker201" class="colorpicker201"></div>
			<input type="text" name="gcolor" id="gcolor" size="9" readonly="readonly"/>&nbsp;<input type="button" onclick="showColorGrid2('gcolor','sample_1');" value="...">&nbsp;<input type="text" ID="sample_1" size="1" value="">
			<font color="red" size="2">*</font>
			 </td></tr>
			<tr class="form-element">
			<td  class="form-label">
			Project Description :</td><td class="form-widget">
			<html:textarea property="darea" value="" rows="3" cols="38"/>
			<html:errors property="darea"/></td></tr>
			<tr><td></td></tr></table>
			<table align="center">
			<tr><td><html:submit value="Add" /></td><td><html:reset value="Reset"/>
			<html:button property="back" value="Back" onclick="history.back();" />

			<input type="hidden" name="prname" id="prname" value="" size="20" readonly="readonly"/>
			<html:errors property="prname"/>
			</td>
			
			</tr></table>
		</html:form>
	</body>
</html>

