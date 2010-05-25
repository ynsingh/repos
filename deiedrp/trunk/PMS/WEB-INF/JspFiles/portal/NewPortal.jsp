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
	
	<%
	String mysession=(String)session.getAttribute("mysession");
	if(mysession==null)
	{
		response.sendRedirect("login.jsp");  
	}
	%>
	
	<html:javascript formName="newportalform" dynamicJavascript="true" staticJavascript="true" />
	<html:form action="/addportal" onsubmit="return validateNewportalform(this);">
	<div id="main_title">
		 <font color="#0044ff"> Create Portal:</font>
	  </div><br>
		  <div align="center">
		  	<html:errors property="portalname"/>
		  	<html:errors property="portalmsg"/>
		   </div>
		  <br>
		
		<table cellspacing="1" cellpadding="6" border="0" align="center">
		<tr ><td> <font color="#0044ff" size="2"> Portal :</font></td></tr>
		<tr class="form-element">
		<td class="form-label">
		
		Portal Name : 
		</td>
		<td class="form-widget">
		<html:text property="portalname" indexed="portalname" size="35" value="" onchange="seePortal()"/><font color="red" size="2">*</font></td></tr>
		<tr class="form-element">
		<td  class="form-label">
			Portal Description :</td><td class="form-widget">
			<html:textarea property="portaldescription" value="" rows="3" cols="33"/>
			<html:errors property="portaldescription"/></td></tr>
			
			</table><br><br>
			<table align="center">
			<tr><td><html:submit value="Add" /></td><td><html:reset value="Reset"/>
			<html:button property="back" value="Back" onclick="history.back();" />

			<input type="hidden" name="portalname2" id="portalname2" value="" size="20" readonly="readonly"/>
			<html:errors property="portalname2"/>
			</td>
			
			</tr></table>
		</html:form>
	</body>
</html>

