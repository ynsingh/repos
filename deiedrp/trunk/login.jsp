<%@ page language="java" pageEncoding="ISO-8859-1"%>
<%@page import="control.CustomRequestProcessor;"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%> 
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
 
<html> 
	<head>
	
		<title>Login Form</title>
		
	<link rel="stylesheet" href="style/style.css" type="text/css"></link></head>
	
	
	
	<body>
	<%
	//System.out.println("session new in login jsp="+session.isNew());
	try
	{
		session.removeAttribute("mysession");
	}
	catch(Exception e){}
		new CustomRequestProcessor().processNoCache(request, response);
	 %>
	<body>

<table style="border:1px solid #000066;width:100%;height:100%;" cellspacing="0px" cellpadding="0px" bgcolor=#C3D9FF>
<tr style="height:20%;width:100%;">
	<td>
					<table bgcolor="#c3d9ff" style="height:100%;width:100%;">
					<tr>
					<td align="center"><div style="width:100%;color:#000066;background-color:C3D9FF;font-size:30px;font-family:algerian;background-image:url(header-gif.rev.gif);background-repeat: no-repeat;background-position: left;width:100%;height:100%;text-align: center;text-shadow: aqua;">PROJECT MANAGEMENT SYSTEM</div></td></tr>
					<tr>
					<td align="center" style="font: normal;font-family: Arial, Helvetica, sans-serif;font-size:14px;color:#000000;" >
					<br><b><font size=3>An Open Source Initiative of the Ministry of Human Resource Development</font></b><br><br>
					<font size=2>Developed under the National Mission on Education through Information and Communication Technology</font><br>
					<br><br> 
					</tr>
					</table>
					
	</td>
</tr>
		




<tr valign="top" bgcolor="white">
<td  width=90%>	
		<table><tr>
		<td valign="top"><table><tr>	<td style="float:none;" align="left">
					
			</td></tr>
			<tr>		
			<td valign="middle" style="float:none;width:50%;" align="left">
								<div style="font: normal;font-family: Arial, Helvetica, sans-serif;color:#000000;" align="justify">
								<font size=-1>
								<br><br><b>Features</b><br/>
								<a href="#"><font color="blue">Project Monitoring</font></a> Admin User can add new project, view all project details, update project details according to authority.<br/><br/>
								<a href="#"><font color="blue">Task Monitoring</font></a> User adds task under desired project.<br/><br/>
								<a href="#"><font color="blue">Organisation Managing</font></a> Admin User can add new organisation, view organisation details,update organisation details.<br/><br/>
								<a href="#"><font color="blue">Gantt Chart</font></a> It shows the project status with graphical interface.<br/><br/>
								<a href="#"><font color="blue">Resource Manging</font></a> User can assign task to his resources.<br/><br/>
								<a href="#"><font color="blue">Document Manging</font></a> User can share documents for their projects.<br/><br/>
								<a href="#"><font color="blue">N-Level User</font></a> User can be created upto n-level.<br/><br/>
								<a href="#"><font color="blue">Reporting</font></a> Project details with its staus i.e according to completion,planned or cancelled<br/>
								</font>
								</div>
			</td>
			</tr>
		</table>
		</td>
		
	
			<td width=10%>
			<table><tr><td valign="top"></td></tr>
			<tr><td valign="middle" bgcolor="#e8eefa" bordercolor=#000000 style="border:1px solid #ccc;">	
				<div style="float:left;padding-top:10px">
								<div style="font: normal;font-family: Arial, Helvetica, sans-serif;font-size:14px;text-align: center;" align="center" style="color:#000000;">
							   <font size=-1>
							   Sign in to PMS<br>
							    </font></div>
							   <div align="center" style="background-color:#e8eefa">
							    <html:errors property="login"/>
							    <html:errors property="uid"/><br>
								<html:errors property="pass"/>
								</div>
			<html:form action="/login" focus="uid" onsubmit="return validateLoginform(this);">
					
			<table cellspacing="2" cellpadding="3" border="0" align="right" style="background-color: #e8eefa;font: normal;font-family: Arial, Helvetica, sans-serif;font-size: 14px;">
				
						<tr></tr>
						<tr >
				
							<td valign="top" style="font: normal;font-family: Arial, Helvetica, sans-serif;font-size:14px;text-align: center" nowrap="nowrap"><font size="-1">User Id: </font></td>
							<td><html:text property="uid" value="" size="30"/></td>
						</tr>
						<tr>
							<td valign="top" style="font: normal;font-family: Arial, Helvetica, sans-serif;font-size:14px;text-align: center" nowrap="nowrap"><font size="-1">Password:</font> </td>
							<td><html:password property="pass" redisplay="false" value="" size="30"/></td>
						</tr>
						<tr>
							<td></td><td valign="top" style="font: normal;font-family: Arial, Helvetica, sans-serif;font-size:14px;text-align: center" nowrap="nowrap"><html:submit value="Log In"/></td>
						</tr>
						<tr>
			 			</tr>
			</table>
			
			<tr>
							
		  	 </tr>
				</html:form></div></td></tr>
				<tr><td valign="middle" bgcolor="#e8eefa" bordercolor=#000099 style="border:1px solid #ccc;">
				<p style="font: normal;font-family: Arial, Helvetica, sans-serif;font-size:14px;text-align:left;color:#000000;" align="justify">	
<font size=-1>
<strong>Note:-</strong>For admin User Id is 'pms123' and password='admin'
</font>
			</p></td></tr>
				</table>	
</tr></table>
</td>
</tr>
					
<tr>
		<td>	
	
				<table width="100%" >
				

<tr><td valign="top" style="font: normal;font-family: Arial, Helvetica, sans-serif;font-size:12px;text-align: center" nowrap="nowrap">
				<div align="center">
   <jsp:include page="WEB-INF/JspFiles/common/footer.jsp"/>
  	</div>
  </td>
				

</tr>
				


</table>
<!-- Table ends here -->
</body>
	
		
	
</html>

