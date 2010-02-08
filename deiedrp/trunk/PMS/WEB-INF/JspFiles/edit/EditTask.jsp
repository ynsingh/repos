<%@ page import="java.sql.*" language="java" pageEncoding="ISO-8859-1"%>
<%@ page errorPage="/jspException.do" %>
<%@page import="task.DynamicList"%>
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
  	DWRUtil.setValue("taskDependency",DWRUtil.getValue("taskDependencyValue"));
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
  	DWRUtil.setValue("orgName",DWRUtil.getValue("orgValue"));
  	DWRUtil.removeAllOptions(document.getElementsByName("projectName")[0]);
  	DWRUtil.removeAllOptions(document.getElementsByName("taskDependency")[0]);
  	projectGenerate();
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
  	DWRUtil.setValue("projectName",DWRUtil.getValue("projectValue"));
  	DWRUtil.removeAllOptions(document.getElementsByName("taskDependency")[0]);
  	taskGenerate();
  }
  ); 
 }
  </script>
	</head>
	<body onload="orgGenerate();">
	<%
	String mysession=(String)session.getAttribute("mysession");
	if(mysession==null)
	{
		response.sendRedirect("login.jsp");  
	}
	
   %>

	<html:javascript formName="edittaskform" />
	<html:form action="/go4" onsubmit="return validateEdittaskform(this);">	
		  <%!
		Connection con=null;
		 PreparedStatement ps=null;
		 ResultSet rs=null,rs2=null;
		 ResultSet rs1=null;
		 String tdep="No";
		 %>
		 <%
	try{	 
	con=MyDataSource.getConnection();
	//System.out.println("Task Name in EditTask.jsp="+session.getAttribute("taskname"));
	/* fetching to that task from 'task' table for updation which Task_Name,Project_ID,Resource_Name is set in session scope.*/
	PreparedStatement ps=con.prepareStatement("select t.Task_Name,v.User_ID,o.Org_Name,"+
	"p.Project_Name,t.Start_Date,t.Finished_Date,t.Gchart_Color,t.Per_Completed,t.Dependency,"+
	"t.Description,t.Task_ID from task t,project p,organisation o,validatetab v"+
	" where (t.Task_Id=? and v.Valid_ID=t.Valid_ID) and v.Project_ID=p.Project_ID and p.enable=0 and v.Org_ID=o.Org_ID");
    ps.setString(1,(String)request.getParameter("id"));
    //ps.setInt(2,Integer.parseInt((String)request.getParameter("pName")));
    //ps.setString(3,(String)request.getParameter("rName"));
    rs1=ps.executeQuery();
    if(rs1.next())
    {
     %>
		<div id="main_title">
		     Edit To Desired Task
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
		<html:text property="taskName" indexed="taskName" value="<%=rs1.getString(1)%>" onchange="taskGenerate()" size="40" /><font color="red" size="2">*</font>
		<html:errors property="taskName"/></td></tr>
		
		<%
		//For getting database connection.
			//con=MyDataSource.getConnection();
		%>
			<tr class="form-element">
		<td  class="form-label">
		Resource Name :</td><td class="form-widget">
		
		<html:select property="resourceName" indexed="resourceName" value="" style="width: 270px;" onkeypress="orgGenerate()" onchange="orgGenerate()" onclick="orgGenerate()">
			<html:option value="<%=rs1.getString(2)%>"><%=rs1.getString(2)%></html:option>
		
		<% 
		try{
			String uid=(String)session.getAttribute("uid");
			if(!uid.equalsIgnoreCase(rs1.getString(2)))
			{
			%>
			<html:option value="<%= uid%>"><%= uid%></html:option>
			<%
			}
			ps=con.prepareStatement("select distinct User_ID from validatetab where PermittedBy=?");
			ps.setString(1,uid);
			rs=ps.executeQuery();
				while(rs.next())
				{
			if(!rs.getString(1).equalsIgnoreCase(rs1.getString(2)))
			{
			%>
			<html:option value="<%= rs.getString(1)%>"><%=rs.getString(1)%></html:option>
			<%
			}
			}
			}
			catch(SQLException e){}
			 %>
			 </html:select><html:errors property="resourceName"/></td></tr>

			<tr class="form-element">
			<td  class="form-label">
			Organisation Name :</td><td class="form-widget">
			<html:select property="orgName" indexed="orgName" style="width: 270px;" onkeypress="projectGenerate()" onchange="projectGenerate()" onclick="projectGenerate()">
			</html:select><html:errors property="orgName"/>
			<input type="hidden" name="orgValue" id="orgValue" value="<%=rs1.getString(3)%>" size="20" readonly="readonly"/>
		<html:errors property="orgValue"/></td></tr>
		<tr class="form-element">
			<td class="form-label">
			Project Name :</td><td class="form-widget">
			<html:select property="projectName" indexed="projectName" style="width: 270px;" onkeypress="taskGenerate()" onchange="taskGenerate()" onclick="taskGenerate()">
			</html:select><html:errors property="projectName"/>
			<input type="hidden" name="projectValue" id="projectValue" value="<%=rs1.getString(4)%>" size="20" readonly="readonly"/>
		<html:errors property="projectValue"/>
			
			</td></tr>
			
			<html:errors property="prosdate"/><html:errors property="profdate"/>
			<tr class="form-element"><td class="form-label">Project (Start Date)</td>
			<td class="form-label"><input type="text" name="prosdate" id="prosdate" size="8" readonly="readonly" style="background-color:threedface;" value="<%=new DynamicList().pSDate(rs1.getString(4)) %>"/>
			(Finish Date)
			<input type="text" name="profdate" id="profdate" size="8" readonly="readonly" style="background-color:threedface;" value="<%=new DynamicList().pFDate(rs1.getString(4)) %>"/></td></tr>
			
			<tr class="form-element"><td  class="form-label">
			Task Start Date :</td>
			<td class="form-widget">
			<input type="text" name="sdate" id="sdate" value="<%=rs1.getString(5)%>" /><a href="javascript:NewCssCal('sdate','yyyymmdd')"><img src="img/cal.gif" width="16" height="16" border="0" alt="Pick a date"></a>
			(YYYY-MM-DD)<font color="red" size="2">*</font>
			</td></tr>

			<tr class="form-element">
			<td  class="form-label">
			Task Finished Date :</td>
			<td class="form-widget">
			<input type="text" name="fdate" id="fdate" value="<%=rs1.getString(6)%>"/><a href="javascript:NewCssCal('fdate','yyyymmdd')"><img src="img/cal.gif" width="16" height="16" border="0" alt="Pick a date"></a>
			(YYYY-MM-DD)<font color="red" size="2">*</font>
			</td></tr>
		
			<tr class="form-element"><td class="form-label">
			Gantt Chart Color :</td><td class="form-widget"> 
			 <div id="colorpicker201" class="colorpicker201"></div>
	<input type="text" id="input_field" name="ifield" size="9" readonly="readonly" value="<%=rs1.getString(7)%>" />&nbsp;<input type="button" onclick="showColorGrid2('input_field','sample_1');" value="...">&nbsp;<input type="text" ID="sample_1" size="1" value="">
			<html:errors property="input_field"/></td></tr>
			
			<tr class="form-element">
		<td  class="form-label">
			Task Completed : 
			</td>
		<td class="form-widget">
			<html:text property="pcom" size="5" value="<%=rs1.getString(8)%>"/><strong><font color="#0000ff">%</font><font color="red" size="2">*</font></strong><html:errors property="pcom"/></td></tr>
			<tr class="form-element"><td class="form-label">
			Dependency :(if any)</td>
			<td class="form-widget">
			<%
			try{
			PreparedStatement ps1=con.prepareStatement("select Task_Name from task where Task_ID=?");
    ps1.setInt(1,Integer.parseInt(rs1.getString(9)));
        
    rs2=ps1.executeQuery();
    if(rs2.next()){tdep=rs2.getString(1);}
			}
			catch(Exception e){
			tdep="No";
				}
			 %>
			 
			<html:select property="taskDependency" indexed="taskDependency" style="width: 270px;" >
			</html:select>
			<input type="hidden" name="taskDependencyValue" id="taskDependencyValue" value="<%=tdep%>" size="20" readonly="readonly"/>
			<html:errors property="taskDependencyValue"/>
			
			</td></tr>
			<tr class="form-element">
			<td  class="form-label">
			Task Description :</td><td class="form-widget">
			 <html:textarea property="darea"  rows="4" cols="32" value="<%=rs1.getString(10)%>"/><html:errors property="darea"/></td></tr>
			<tr><td></td></tr>
			</table>
			<table align="center">
			<tr><td><html:submit value="Save Change" /></td><td><html:reset onclick="orgGenerate()"></html:reset></td>
			<td><html:button property="back" value="Cancel" onclick="history.back();" /></td>
			</tr>
			</table>
			<%
				}
				MyDataSource.freeConnection(con);
				
			}
		 	catch(Exception e){System.out.println("exception in Edit Task.jsp page="+e);}
		 	%>
		</html:form>
			
	</body>
</html>

