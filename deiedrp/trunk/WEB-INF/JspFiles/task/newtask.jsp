<%@ page import="java.sql.*" language="java" pageEncoding="ISO-8859-1"%>
<%@page import="dataBaseConnection.MyDataSource;"%>

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%> 
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
   
<html> 
	<head>
	<title>JSP for NewTask form</title>
	<link rel="stylesheet" href="style/main.css" type="text/css"></link>
	<link rel="stylesheet" href="style/style.css" type="text/css"></link>
	<script type="text/javascript" src="javascript/201a.js"></script>
	<script type="text/javascript" src="javascript/datetimepicker.js"></script>
	<!-- You have to include these two JavaScript files -->
        <script type='text/javascript' src='dwr/engine.js'></script>
        <script type='text/javascript' src='dwr/util.js'></script>
	<!-- This JavaScript file is generated specifically for your application -->
         <script type='text/javascript' src='dwr/interface/DynamicList.js'></script>
	
	<script language="JavaScript" type="text/javascript">
	function taskGenerate() {
   var name = DWRUtil.getValue("projectName");
 	var tname = DWRUtil.getValue("taskName");
 	var rname = DWRUtil.getValue("resourceName");
 	var oname = DWRUtil.getValue("orgName");
  DynamicList.taskList(name,tname,rname,oname,function(data)
  {
  	DWRUtil.removeAllOptions(document.getElementsByName("taskDependency")[0]);
  	DWRUtil.addOptions(document.getElementsByName("taskDependency")[0],data);
 	 sdateGenerate();
	 fdateGenerate();
  }
  ); 
 }
  
  function sdateGenerate() {
   var name = DWRUtil.getValue("projectName");
  DynamicList.pSDate(name,function(data)
  {
  	DWRUtil.setValue("prosdate",data);
  }
  ); 
 }
  
  function fdateGenerate() {
   var name = DWRUtil.getValue("projectName");
  DynamicList.pFDate(name,function(data)
  {
  	DWRUtil.setValue("profdate",data);
  }
  ); 
  }
    
  function orgGenerate() {
  var name = DWRUtil.getValue("resourceName");
  var uname = DWRUtil.getValue("uname");
  DWRUtil.setValue("prosdate","");
  DWRUtil.setValue("profdate","");
  DynamicList.orgList(name,uname,function(data)
  {
   	DWRUtil.removeAllOptions(document.getElementsByName("orgName")[0]);
  	DWRUtil.addOptions(document.getElementsByName("orgName")[0],data);
  	DWRUtil.removeAllOptions(document.getElementsByName("projectName")[0]);
  	DWRUtil.removeAllOptions(document.getElementsByName("taskDependency")[0]);
  }
  ); 
 }
  
  function projectGenerate() {
  
   var name = DWRUtil.getValue("orgName");
   var rname = DWRUtil.getValue("resourceName");
   var uname = DWRUtil.getValue("uname");
 
  DynamicList.projectList(name,rname,uname,function(data)
  {
  
  	DWRUtil.removeAllOptions(document.getElementsByName("projectName")[0]);
  	DWRUtil.addOptions(document.getElementsByName("projectName")[0],data);
  	DWRUtil.removeAllOptions(document.getElementsByName("taskDependency")[0]);
  	taskGenerate();
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

	<html:javascript formName="taskform" />
	<html:form action="/task"  onsubmit="return validateTaskform(this);">			
	
		<div id="main_title"><br>
		   <font color="#0044ff">  Add New Task:</font>
		  </div>
		  <br><br>
		  <div align="center"><html:errors property="sdate"/>
		  	  <html:errors property="fdate"/>
		  	  <html:errors property="taskmsg"/>
		  </div>
		  <br>
		  <table cellspacing="2" cellpadding="2" border="0" align="center">
		  <tr>
		<td>
		<input type="hidden" name="uname" id="uname" value="<%=(String)session.getAttribute("uid") %>" size="20" readonly="readonly"/>
			<html:errors property="uname"/>
</td></tr>
		<tr class="form-element">
		<td  class="form-label">
			Task Name : 
			</td>
		<td class="form-widget">
		<html:text property="taskName" indexed="taskName" onchange="taskGenerate()" size="40" value=""/><font color="red" size="2">*</font>
		<html:errors property="taskName"/></td></tr>
		<%!
		 Connection con=null;
		 PreparedStatement ps=null;
		 ResultSet rs=null;
	    %>
		<%
		//For getting database connection.
			con=MyDataSource.getConnection();
		%>
			<tr class="form-element">
		<td  class="form-label">
		Resource Name :</td><td class="form-widget">
		<html:select property="resourceName" style="width: 270px;" value="" onkeypress="orgGenerate()" onclick="orgGenerate()" onchange="orgGenerate()">
			<html:option value="--Select--">--Select--</html:option>
		
		<% 
		try{
			String uid=(String)this.getServletConfig().getServletContext().getAttribute("uid");
			%>
			<html:option value="<%= uid%>"><%= uid%></html:option>
			<%
			ps=con.prepareStatement("select distinct User_ID from validatetab where PermittedBy=?");
			ps.setString(1,uid);
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
			 </html:select><html:errors property="resourceName"/></td></tr>
			<tr class="form-element">
			<td  class="form-label">
			Organisation Name :</td><td class="form-widget">
			<html:select property="orgName" indexed="orgName" style="width: 270px;" onkeypress="projectGenerate()" onchange="projectGenerate()" onclick="projectGenerate()">
			</html:select><html:errors property="orgName"/><font color="red" size="2">*</font></td></tr>
		<tr class="form-element">
			<td class="form-label">
			Project Name :</td><td class="form-widget">
			<html:select property="projectName" indexed="projectName" style="width: 270px;" onkeypress="taskGenerate()" onchange="taskGenerate()" onclick="taskGenerate()">
			</html:select><html:errors property="projectName"/><font color="red" size="2">*</font>
			</td></tr>
			
			<html:errors property="prosdate"/><html:errors property="profdate"/>
			<tr class="form-element"><td class="form-label">Project (Start Date)</td>
			<td class="form-label"><input type="text" name="prosdate" id="prosdate" size="8" readonly="readonly" style="background-color:threedface;" value=""/>
			(Finish Date)
			<input type="text" name="profdate" id="profdate" size="8" readonly="readonly" style="background-color:threedface;" value=""/></td></tr>
			
			<tr class="form-element"><td  class="form-label">
			Task Start Date :</td>
			<td class="form-widget">
			<input type="text" name="sdate" id="sdate" value="" /><a href="javascript:NewCssCal('sdate','yyyymmdd')"><img src="img/cal.gif" width="16" height="16" border="0" alt="Pick a date"></a>
			(YYYY-MM-DD)<font color="red" size="2">*</font>
			</td></tr>

			<tr class="form-element">
			<td  class="form-label">
			Task Finished Date :</td>
			<td class="form-widget">
			<input type="text" name="fdate" id="fdate" value=""/><a href="javascript:NewCssCal('fdate','yyyymmdd')"><img src="img/cal.gif" width="16" height="16" border="0" alt="Pick a date"></a>
			(YYYY-MM-DD)<font color="red" size="2">*</font>
			</td></tr>
		
			<tr class="form-element"><td  class="form-label">
			Gantt Chart Color :</td><td class="form-widget"> 
			 <div id="colorpicker201" class="colorpicker201"></div>
	<input type="text" id="input_field" name="ifield" size="9" readonly="readonly" value="" />&nbsp;<input type="button" onclick="showColorGrid2('input_field','sample_1');" value="...">&nbsp;<input type="text" ID="sample_1" size="1" value="">
			<html:errors property="input_field"/></td></tr>
			
			<tr class="form-element">
		<td  class="form-label">
			Task Completed : 
			</td>
		<td class="form-widget">
			<html:text property="pcom" size="5" value=""/><strong><font color="#0000ff">%</font><font color="red" size="2">*</font></strong><html:errors property="pcom"/></td></tr>
			<tr class="form-element"><td  class="form-label">
			Dependency :(if any)</td>
			<td class="form-widget"> 
			<html:select property="taskDependency" indexed="taskDependency" style="width: 270px;">
			</html:select>
			</td></tr>
			<tr class="form-element">
			<td  class="form-label">
			Task Description :</td><td class="form-widget">
			 <html:textarea property="darea"  rows="4" cols="32" value=""/><html:errors property="darea"/></td></tr>
			<tr><td></td></tr>
			</table>
			<table align="center">
			<tr><td><html:submit value="Assign Task"/></td><td><html:reset></html:reset></td>
			<td><html:button property="back" value="Back" onclick="history.back();" /></td>
			</tr>
			
			</table>
		</html:form>
	</body>
</html>

