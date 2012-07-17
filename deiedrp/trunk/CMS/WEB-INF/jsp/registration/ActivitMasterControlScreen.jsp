<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Activity Master Control </title>
<style>
.error {
color: #ff0000;
font-style: italic;
}
</style>
</head>
  
  <body>
 		<div align="center">
		<p style="font: bold;color:blue;">Activity Master Control</p>   
 		<form:form method="POST" commandName="activityMasterBean" action="startActivityForProcess.htm">
 		<table border="1" bgcolor="#ffffcc">
 		    	
	  	<tr>
			<td><spring:message code="activityMasterBean.entityName"/></td>
			<td><form:input path="entityId" /></td>
			<td><form:errors path="entityId" cssClass="error" /></td>
		</tr>
    	
    	<tr>
	        <td><spring:message code="activityMasterBean.processName"/></td>
	       <td>
	           <form:select path="processList">
	            <form:option value="0" label="Select" />
	           <form:options items="${processList}" itemValue="processId" itemLabel="processName" />
	        </form:select>
	        </td>
    	</tr>
    
    	<tr>
        <td><spring:message code="activityMasterBean.activityName"/></td>
	        <td>
	        
	           <form:select path="activityList">
	            <form:option value="0" label="Select" />
	           	<form:options items="${activityList}" itemValue="activityId" itemLabel="activityName" />
	           </form:select>
	        </td>
    	</tr>
    
    	<tr>
			<td><spring:message code="activityMasterBean.programName"/></td>
			<td><form:input path="programId" /></td>
			<td><form:errors path="programId" cssClass="error" /></td>
		</tr>
		
		<tr>
			<td><spring:message code="activityMasterBean.semesterCode"/></td>
			<td><form:input path="semesterCode" /></td>
			<td><form:errors path="semesterCode" cssClass="error" />
		</tr>
		
		<tr>
			<td><spring:message code="activityMasterBean.semesterStartDate"/></td>
			<td><form:input path="semesterStartDate" /></td>
			<td><form:errors path="semesterStartDate" cssClass="error" />
			
		</tr>
		
		<tr>
			<td><spring:message code="activityMasterBean.semesterEndDate"/></td>
			<td><form:input path="semesterEndDate" /></td>
			<td><form:errors path="semesterEndDate" cssClass="error" />
		</tr>
	  
	    <tr>
	    <td></td>
	    <td><input type="submit" value="<spring:message code="save.button"/>"/></td>
	    </tr>
    
    </table>
    
    </form:form>
    </div>
  </body>
</html>
