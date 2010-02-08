<%@ page language="java" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%> 
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
 
<html> 
	<head>
	<link rel="stylesheet" href="<html:rewrite page='/style/style.css'/>" type="text/css"></link>
	<!-- You have to include these two JavaScript files -->
        <script type='text/javascript' src='dwr/engine.js'></script>
        <script type='text/javascript' src='dwr/util.js'></script>
	<!-- This JavaScript file is generated specifically for your application -->
         <script type='text/javascript' src='dwr/interface/DynamicList.js'></script>
	
	<script type="text/javascript">
	function seeOrganisation() {
   var name = DWRUtil.getValue("iname");
   var info="org";
   DynamicList.seeExistence(name,info,function(data)
  {
  	DWRUtil.setValue("orgname",data);
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
	 <html:javascript formName="orgform" />
		<html:form action="saveorganization" onsubmit="return validateOrgform(this);">
	   <div id=main_title>
	   
	   <font color="blue" size="3">Add New Organisation:</font>
	   </div><br>
		   <div align="center">
		  <html:errors property="iname"/>
		  <html:errors property="iurl"/>
		  </div>
		  <br>
		  		
		<table cellspacing="2" cellpadding="2" border="0" align="center">
		<tr class="form-element">
		<td  class="form-label">
			Organisation Name : </td>
			<td class="form-widget"><html:text property="iname" indexed="iname" size="40" onchange="seeOrganisation()" /><font color="red" size="2">*</font></td>
			<tr class="form-element">
			<td class="form-label">
			Address :</td>
			<td class="form-widget"><html:textarea property="iaddress" rows="3" cols="32"/><font color="red" size="2">*</font><html:errors property="iaddress"/>
			</td></tr>
		<tr class="form-element"><td class="form-label">
			City : </td>
			<td class="form-widget"><html:text property="icity" size="40"/><font color="red" size="2">*</font><html:errors property="icity"/>
			<tr class="form-element"><td class="form-label">
			State : </td>
			<td class="form-widget"><html:text property="istate" size="40"/><font color="red" size="2">*</font><html:errors property="state"/>
			<tr class="form-element"><td class="form-label">
			Pin Code :</td>
			<td class="form-widget"> <html:text property="ipin" size="40" maxlength="6"/><html:errors property="ipin"/>
			<tr class="form-element"><td class="form-label">
			Phone No : </td>
			<td class="form-widget"><html:text property="iphoneno" size="40"/><font color="red" size="2">*</font><html:errors property="iphoneno"/>
			<tr class="form-element"><td class="form-label">
			Fax No. :</td>
			<td class="form-widget"> <html:text property="ifax" size="40"/><html:errors property="ifax"/>
			<tr class="form-element"><td class="form-label">
			WebSite :</td>
			<td class="form-widget"> <html:text property="iurl" size="40"/>
			<tr class="form-element"><td class="form-label">
			Head : </td>
			<td class="form-widget"><html:text property="ihead" size="40"/><font color="red" size="2">*</font><html:errors property="ihead"/>
			<tr class="form-element"><td class="form-label">
			Email_id :</td>
			<td class="form-widget"> <html:text property="ieid" size="40"/><font color="red" size="2">*</font><html:errors property="ieid"/>
			<tr class="form-element"><td class="form-label">
			Description : </td>
			<td class="form-widget"><html:text property="description" size="40"/><html:errors property="description"/>
			<tr><td></td></tr></table>
			<table align="center">
		<tr><td>
			<html:submit value="Add"/></td><td><html:reset/>
			<td><html:button property="back" value="Back" onclick="history.back();" /></td>
			<td>
			<input type="hidden" name="orgname" id="orgname" size="20" readonly="readonly"/>
			<html:errors property="orgname"/>
			</td>
			</tr></table>
		</html:form>
	</body>
</html>

