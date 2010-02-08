
<%@ page language="java" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>

<link rel="stylesheet" href="style/dropdown.css" type="text/css"></link>
<script type="text/javascript" src="javascript/dropdown.js"></script>

<body>
<table bgcolor="c3d9ff" cellpadding="0" cellspacing="0" style="top: 0.0%;position: absolute;" width="100%">
<tr>
	<td nowrap="nowrap">
	<table style="height:100%;width:100%;">
	<tr>
	<td align="right" nowrap="nowrap">
	<div align="right" style="color:#000066;;font-size:30px;font-family:algerian;width:100%;height:100%;text-align: center;text-shadow: aqua;">Project Management System <br><font size=2>An Open Source Initiative of the Ministry of Human Resource Development
<br>
Developed under the National Mission on Education through Information and Communication Technology</font></div>
	</td>
	<td valign="top" nowrap="nowrap">
		<table align="right">
		<tr>
		<td nowrap="nowrap" align="left">
		<strong><font >Welcome,</font></strong>
		<font>
		  		<%
		  		/*The user_id which is currently logged In.*/
		  		String uid=(String)session.getAttribute("uid");
		  		out.println(uid);
		   		%> | </font>
		  </td>
		  <td nowrap="nowrap" valign="top" align="right">
				<html:link action="logout" styleClass="B"> <font size="-1">LogOut</font> </html:link> |
				<html:link styleClass="B" action="changeOwnpassword"> Change Password </html:link>
					
		  </td></tr></table></td></tr>
		</table>
		</td>
</tr>


<tr>
	<td>

    <table cellspacing="0" cellpadding="0" ><tr>
    <td>
<dl class="dropdown">
  <dt id="one-ddheader" onmouseover="ddMenu('one',1)" onmouseout="ddMenu('one',-1)">
  
  <html:link action="welcome" style="padding:0px;background-color:#336699;font-weight:bold;color:#ffffff;"> Home</html:link>
  </dt>
  </dl>
</td>
    
    <td>
   <dl class="dropdown">
  <dt id="two-ddheader" onmouseover="ddMenu('two',1)" onmouseout="ddMenu('two',-1)">Project Management</dt>
  <dd id="two-ddcontent" onmouseover="cancelHide('two')" onmouseout="ddMenu('two',-1)">
    <ul class="ss">
      <li><html:link styleClass="underline" action="viewproject"> View Project </html:link></li>
      <li><html:link styleClass="underline" action="searchproject"> Search Project </html:link></li>
      <li><html:link styleClass="underline" action="updateoperation"> Update Operation </html:link></li>
      <li><html:link styleClass="underline" action="ganttchart">View Gantt Chart </html:link></li>
            
    </ul>
  </dd>
</dl>
</td>
<td>
<dl class="dropdown">
  <dt id="three-ddheader" onmouseover="ddMenu('three',1)" onmouseout="ddMenu('three',-1)">Member</dt>
  <dd id="three-ddcontent" onmouseover="cancelHide('three')" onmouseout="ddMenu('three',-1)">
    <ul class="ss">
		<li><html:link styleClass="underline" action="assignproject"> Add Member </html:link></li>
      	<li><html:link styleClass="underline" action="viewmember"> View Members </html:link></li>
      	<li><html:link styleClass="underline" action="searchpeople"> People Search </html:link></li>
    </ul>
  </dd>
</dl>
</td>

<td>
<dl class="dropdown">
  <dt id="four-ddheader" onmouseover="ddMenu('four',1)" onmouseout="ddMenu('four',-1)">Task</dt>
  <dd id="four-ddcontent" onmouseover="cancelHide('four')" onmouseout="ddMenu('four',-1)">
    <ul class="ss">
      <li><html:link styleClass="underline" action="newtask"> Add Task </html:link></li>
      <li><html:link styleClass="underline" action="viewtask"> View Task </html:link></li>
      
   </ul>
  </dd>
</dl>
</td>
<td>
<dl class="dropdown">
  <dt id="five-ddheader" onmouseover="ddMenu('five',1)" onmouseout="ddMenu('five',-1)">Organisation</dt>
  <dd id="five-ddcontent" onmouseover="cancelHide('five')" onmouseout="ddMenu('five',-1)">
    <ul class="ss">
      <li><html:link styleClass="underline" action="vieworganization"> View Organisation </html:link></li>
     <li><html:link styleClass="underline" action="searchorganization"> Search Organisation</html:link></li>
    </ul>
  </dd>
</dl>
</td>
<td>
<dl class="dropdown">
  <dt id="six-ddheader" onmouseover="ddMenu('six',1)" onmouseout="ddMenu('six',-1)">Documents</dt>
  <dd id="six-ddcontent" onmouseover="cancelHide('six')" onmouseout="ddMenu('six',-1)">
    <ul class="ss">
      <li><html:link styleClass="underline" action="upload" > Upload File </html:link></li>
       <li><html:link styleClass="underline" action="viewdownload" > DownLoad File</html:link></li>
       <li><html:link styleClass="underline" action="download" > Remove file </html:link></li>
           </ul>
  </dd>
</dl>
</td>

    </tr></table>
   </td>
</tr>

  
</table>

</body>
