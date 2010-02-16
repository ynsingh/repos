<%@ page language="java" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>

<link rel="stylesheet" href="style/dropdown.css" type="text/css"></link>
<script type="text/javascript" src="javascript/dropdown.js"></script>
<body>
<table style="border:0px solid #000066;width:100%;height:13%;" cellspacing="0px" cellpadding="0px" bgcolor=#C3D9FF>
<tr>
	<td valign="top"><div>
					<div style="float:left;width:70%;color:#000066;background-color:C3D9FF;font-size:30px;font-family:algerian;background-image:url(header-gif.rev.gif);background-repeat: no-repeat;background-position: left;height:13%;text-align: center;text-shadow: aqua;">PROJECT MANAGEMENT SYSTEM
					<br><b><div style="padding-bottom:15px;font: normal;font-family: Arial, Helvetica, sans-serif;color:#000000;"><font size=3>An Open Source Initiative of the Ministry of Human Resource Development</font></b><br>
					<font size=2>(Developed under the National Mission on Education through Information and Communication Technology)</font></div>
					
					</div>
					<div align="right">
		<strong><font >Welcome,</font></strong>
		<font >
		  		<%
		  		/*The user_id which is currently logged In.*/
		  		String uid=(String)session.getAttribute("uid");
		  		out.println(uid);
		   		%> | </font>
		 
				<html:link action="logout" styleClass="B"> <font size="-1">LogOut</font> </html:link> |
				<html:link styleClass="B" action="changeOwnpassword"> Change Password </html:link> 	</div>				
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
  <dt id="four-ddheader" onmouseover="ddMenu('four',1)" onmouseout="ddMenu('four',-1)">Organisation</dt>
  <dd id="four-ddcontent" onmouseover="cancelHide('four')" onmouseout="ddMenu('four',-1)">
    <ul class="ss">
      <li><html:link styleClass="underline" action="neworganization">Add Organisation</html:link></li>
      <li><html:link styleClass="underline" action="vieworganization">View Organisation</html:link></li>
       <li><html:link styleClass="underline" action="searchorganization">Search Organisation</html:link></li>
    </ul>
  </dd>
</dl>
</td>
<td>
  <dl class="dropdown">
  <dt id="two-ddheader" onmouseover="ddMenu('two',1)" onmouseout="ddMenu('two',-1)">Project Management</dt>
  <dd id="two-ddcontent" onmouseover="cancelHide('two')" onmouseout="ddMenu('two',-1)">
    <ul class="ss">
    <li><html:link styleClass="underline" action="newproject" > Add Project </html:link></li>
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
      <li><html:link styleClass="underline" action="assignproject"> Assign Project </html:link></li>
      <li><html:link styleClass="underline" action="viewmember"> View Members </html:link></li>
      	<li><html:link styleClass="underline" action="searchpeople"> People Search </html:link></li>
      </ul>
  </dd>
</dl>
</td>

<td>
<dl class="dropdown">
  <dt id="five-ddheader" onmouseover="ddMenu('five',1)" onmouseout="ddMenu('five',-1)">Documents</dt>
  <dd id="five-ddcontent" onmouseover="cancelHide('five')" onmouseout="ddMenu('five',-1)">
    <ul class="ss">
      <li><html:link styleClass="underline" action="upload" > Upload File </html:link></li>
       <li><html:link styleClass="underline" action="download" >DownLoad/Remove File </html:link></li>
           </ul>
  </dd>
</dl>
</td>

    </tr></table>
   </td>
<tr>
  
</table>

</body>
