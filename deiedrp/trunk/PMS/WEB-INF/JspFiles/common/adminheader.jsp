<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head><title>Login Form</title>
<link rel="stylesheet" href="style/dropdown.css" type="text/css"/>
<link rel="icon" href="img/logo.ico" type="image/x-icon"/>
<script type="text/javascript" src="javascript/dropdown.js"></script>
</head>
<body>
<table style="border:0px solid #000066;width:100%;height:13%;background-image: url('img/backimage.PNG');background-repeat: repeat;" cellspacing="0px" cellpadding="0px" bgcolor=#C3D9FF>
<tr>
	<td valign="top"><div>
					<div style="float:left;width:70%;color:#000066;font-size:30px;font-family:algerian;height:13%;text-align: center;text-shadow: aqua;">
					<bean:message key="header.title" />
					<br><div style="padding-bottom:15px;font: normal;font-family: Arial, Helvetica, sans-serif;color:#000000;">
					<b><font size="3"><bean:message key="header.subtitle" /></font></b><br>
					<font size="2">(<bean:message key="header.subtitle1" />)</font></div>
					</div>
					</div>
					<div align="right">
		<b><font size="1">Welcome,</font></b>
		<font size="1">
		  		<c:out value="${sessionScope.uid}"/> | </font>
		 		<html:link action="logout" styleClass="B"> <font size="-1">Logout</font> </html:link> |
				<html:link styleClass="B" action="changeOwnpassword"> Change Password </html:link> 	</div>				
		 </td>
		 </tr>
		<tr>
	<td>

<table cellspacing="0" cellpadding="0" >
<tr>
<td>
<dl class="dropdown">
  <dt id="one-ddheader" onmouseover="ddMenu('one',1)" onmouseout="ddMenu('one',-1)">
  
  <html:link action="welcome" style="padding:0px;background-color:#336699;font-weight:bold;color:#ffffff;"> Home</html:link>
  </dt>
  </dl>
</td>
<td>
<dl class="dropdown">
  <dt id="one-ddheader" onmouseover="ddMenu('eight',1)" onmouseout="ddMenu('eight',-1)">
  
  <html:link action="mailconfig" style="padding:0px;background-color:#336699;font-weight:bold;color:#ffffff;"> Mail Configuration</html:link>
  </dt>
  </dl>
</td>
<td>
<dl class="dropdown">
  <dt id="two-ddheader" onmouseover="ddMenu('two',1)" onmouseout="ddMenu('two',-1)">Portal</dt>
  <dd id="two-ddcontent" onmouseover="cancelHide('two')" onmouseout="ddMenu('two',-1)">
    <ul class="ss">
      <li><html:link styleClass="underline" action="newportal">Create Portal</html:link></li>
      <li><html:link styleClass="underline" action="viewportal">View Portal</html:link></li>
    </ul>
  </dd>
 </dl>
</td>

<td>
<dl class="dropdown">
  <dt id="three-ddheader" onmouseover="ddMenu('three',1)" onmouseout="ddMenu('three',-1)">Organization</dt>
  <dd id="three-ddcontent" onmouseover="cancelHide('three')" onmouseout="ddMenu('three',-1)">
    <ul class="ss">
      <li><html:link styleClass="underline" action="neworganization">Add Organization</html:link></li>
      <li><html:link styleClass="underline" action="vieworganization">View Organization</html:link></li>
      <li><html:link styleClass="underline" action="searchorganization">Search Organization</html:link></li>
    </ul>
  </dd>
</dl>
</td>

<td>
<dl class="dropdown">
  <dt id="four-ddheader" onmouseover="ddMenu('four',1)" onmouseout="ddMenu('four',-1)">Roles</dt>
  <dd id="four-ddcontent" onmouseover="cancelHide('four')" onmouseout="ddMenu('four',-1)">
    <ul class="ss">
      <li><html:link styleClass="underline" action="newrole">Add Roles</html:link></li>
      <li><html:link styleClass="underline" action="viewrole">View Roles</html:link></li>
      </ul>
  </dd>
</dl>
</td>
<td>
<dl class="dropdown">
  <dt id="six-ddheader" onmouseover="ddMenu('six',1)" onmouseout="ddMenu('six',-1)">Member</dt>
  <dd id="six-ddcontent" onmouseover="cancelHide('six')" onmouseout="ddMenu('six',-1)">
    <ul class="ss">
      <li><html:link styleClass="underline" action="addmember"> Add Member </html:link></li>
      <li><html:link styleClass="underline" action="viewmember"> View Members </html:link></li>
    <li><html:link styleClass="underline" action="addorg_in_portal">Add Organization into Portal</html:link></li>
     <!--   <li><html:link styleClass="underline" action="assignproject"> Assign Project </html:link></li>-->
      	<li><html:link styleClass="underline" action="searchpeople"> People Search </html:link></li>
      </ul>
  </dd>
</dl>
</td>

<td>
  <dl class="dropdown">
  <dt id="five-ddheader" onmouseover="ddMenu('five',1)" onmouseout="ddMenu('five',-1)">Project Management</dt>
  <dd id="five-ddcontent" onmouseover="cancelHide('five')" onmouseout="ddMenu('five',-1)">
    <ul class="ss">
   <!--   <li><html:link styleClass="underline" action="newproject" > Add Project </html:link></li>-->
      <li><html:link styleClass="underline" action="viewproject"> View Project </html:link></li>
   <!--     <li><html:link styleClass="underline" action="searchproject"> Search Project </html:link></li>
      <li><html:link styleClass="underline" action="updateoperation"> Update Operation </html:link></li>
      <li><html:link styleClass="underline" action="drawGanttChart">View Gantt Chart </html:link></li>-->
            
    </ul>
  </dd>
</dl>
</td>
<!-- 
<td>
<dl class="dropdown">
  <dt id="seven-ddheader" onmouseover="ddMenu('seven',1)" onmouseout="ddMenu('seven',-1)">Documents</dt>
  <dd id="seven-ddcontent" onmouseover="cancelHide('seven')" onmouseout="ddMenu('seven',-1)">
    <ul class="ss">
      <li><html:link styleClass="underline" action="upload" > Upload File </html:link></li>
       <li><html:link styleClass="underline" action="download" >DownLoad/Remove File </html:link></li>
     </ul>
  </dd>
</dl>
</td>
 -->
 <td>
<dl class="dropdown">
  <dt id="one-ddheader" style="width: 100px;" onmouseover="ddMenu('nine',1)" onmouseout="ddMenu('nine',-1)">
  
  <html:link action="help" style="padding:0px;width: 100px;background-color:#336699;font-weight:bold;color:#ffffff;"> Help</html:link>
  </dt>
  </dl>
</td>



</tr></table>
   </td></tr></table>
</body>
</html>
