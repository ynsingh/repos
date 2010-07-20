<%@ page import="java.sql.*" language="java" pageEncoding="UTF-8"%>
<%@ page import="in.ac.dei.edrp.pms.dataBaseConnection.MyDataSource;"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%> 
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
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
   function clearData()
   {
     DWRUtil.removeAllOptions(document.getElementsByName("assignedTo")[0]);
     DWRUtil.removeAllOptions(document.getElementsByName("taskNameList")[0]);
   }
   
   	function taskListGenerate() {
   	var projectName = DWRUtil.getValue("projectName");
 	var orgportal = DWRUtil.getValue("orgportal");
 	DynamicList.generateTaskList(projectName,orgportal,function(data)
  	{
  		DWRUtil.removeAllOptions(document.getElementsByName("taskNameList")[0]);
  	  	DWRUtil.addOptions(document.getElementsByName("taskNameList")[0],data);
  	  	resourceGenerate();
  	 }
  ); 
 }
   
  function resourceGenerate() {
  var valid_key = DWRUtil.getValue("valid_key");
  var projectName = DWRUtil.getValue("projectName");
  var orgportal = DWRUtil.getValue("orgportal");
  var uname = DWRUtil.getValue("uname");
  DynamicList.resourceList(uname,valid_key,projectName,orgportal,function(data)
  {
   	DWRUtil.removeAllOptions(document.getElementsByName("assignedTo")[0]);
  	DWRUtil.addOptions(document.getElementsByName("assignedTo")[0],data);
  }
  ); 
 }
 </script>
 </head>
  
  <body onload="taskListGenerate()">
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
		<html:javascript formName="assignTaskform"/>
	<html:form action="/assignTask" onsubmit="return validateAssignTaskform(this);">	
	
		<div id="main_title">
		   <font color="#0044ff">  Assign Task:</font>
		  </div>
		  <br>
		  <div align="center">
		  	  <html:errors property="assignTaskMsg"/>
		  </div>
		  <br>
		  <table cellspacing="2" cellpadding="9" border="0" align="center">
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
			ps=con.prepareStatement("select distinct u.valid_key from user_in_org u"+
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
			 onchange="taskListGenerate()">
			<html:option value="--Select--">--Select--</html:option>
			<% 
		try{
			ps=con.prepareStatement("select distinct p.project_name from project p,"+
									"user_in_org u,validatetab v where p.enable=0 and "+
									"u.valid_user_id=? and u.valid_orgportal=? "+
					"and u.valid_key=v.valid_user_key and v.valid_project_code=p.project_code");
			ps.setString(1,(String)session.getAttribute("uid"));
			ps.setString(2,(String)session.getAttribute("validOrgInPortal"));
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
			</td></tr>
		<tr class="form-element">
		<td class="form-label">
		Unassigned Task Name : 
			</td>
		<td class="form-widget">
		<html:select property="taskNameList" style="width: 270px;" value="" >
		</html:select> <font color="red" size="2">*</font>
		 <html:errors property="taskNameList"/></td></tr>
		
		<tr class="form-element">
		<td  class="form-label">
		Assigned To :</td><td class="form-widget">
		<html:select property="assignedTo" style="width: 270px;" value="" >
		</html:select><font color="red" size="2">*</font>
		<html:errors property="assignedTo"/></td></tr>
			<tr><td>
			<input type="hidden" name="taskname2" id="taskname2" value="" size="20" readonly="readonly"/>
			<html:errors property="taskname2"/>
			</td></tr>
			</table>
			<table align="center" style="padding-right: 400px">
			<tr><td><html:submit value="Assign Task" styleClass="butStnd"/></td>
			<td><html:reset onclick="clearData();" styleClass="butStnd"></html:reset></td>
			<td><html:button property="back" value="Back" styleClass="butStnd" onclick="history.back();" /></td>
			</tr>
			
			</table>
		</html:form>
  </body>
</html>
