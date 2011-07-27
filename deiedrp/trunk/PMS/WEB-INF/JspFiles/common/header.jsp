<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<link rel="stylesheet" href="style/dropdown.css" type="text/css"></link>
<script type="text/javascript" src="javascript/dropdown.js"></script>
<link rel="icon" href="img/logo.ico" type="image/x-icon"/>
</head>
<body>
<table style="border:0px solid #000066;width:100%;height:13%;background-image: url('img/backimage.PNG');background-repeat: repeat;" cellspacing="0px" cellpadding="0px">
<tr>

<td valign="top" ><div>	
					<div style="float:left;width:70%;font-size:30px;font-family:algerian;background-position: left;text-align: center;text-shadow: aqua;">
					<bean:message key="header.title" />
					<br><div style="padding-bottom:15px;font: normal;font-family: Arial, Helvetica, sans-serif;color:#000000;">
					<b><font size="3"><bean:message key="header.subtitle" /></font></b><br>
					<font size="2">(<bean:message key="header.subtitle1" />)</font></div>
					</div>
					</div>
					<div align="right" >
		<b><font size="1"><bean:message key="header.welcome"/></font></b>
		<font size="1">
		  		<c:out value="${sessionScope.uid}"/> | </font>
		 
				<html:link action="logout" styleClass="B"> <font size="-1"><bean:message key="header.logout"/></font> </html:link> |
				<html:link styleClass="B" action="changeOwnpassword"><bean:message key="header.changePassword"/></html:link> </div>		
				
				<div style="padding-top: 5px">
				<b><font size="1"><bean:message key="menu.portal"/>:</font></b><font color="#1425FF"> <c:out value="${sessionScope.portalname}"/> </font>
				<br>
				<b><font size="1"><bean:message key="menu.organization"/>:</font></b><font color="#1425FF"> <c:out value="${sessionScope.orgname}"/></font>
				<br>
				<b><font size="1"><bean:message key="menu.role"/>:</font></b><font color="#1425FF"> <c:out value="${sessionScope.rolename}"/></font>
				
				</div>
		    </td>
		 </tr>
		<tr>
	<td>

    <table cellspacing="0" cellpadding="0" ><tr>
    <td>
<dl class="dropdown">
  <dt id="one1-ddheader" onmouseover="ddMenu('one1',1)" onmouseout="ddMenu('one1',-1)">
  
  <html:link action="welcome" style="padding:0px;background-color:#336699;font-weight:bold;color:#ffffff;"><bean:message key="menu.home"/></html:link>
  </dt>
  </dl>
</td>
<td>
<dl class="dropdown">
  <dt id="one-ddheader" onmouseover="ddMenu('one',1)" onmouseout="ddMenu('one',-1)"><bean:message key="menu.role"/></dt>
  <dd id="one-ddcontent" onmouseover="cancelHide('one')" onmouseout="ddMenu('one',-1)">
    <ul class="ss">
      <li><html:link styleClass="underline" page="/forwardPmsPage.do?parameter=newRole"><bean:message key="submenu.role.addRole"/></html:link></li>
      <li><html:link styleClass="underline" page="/forwardPmsPage.do?parameter=viewRole"><bean:message key="submenu.role.viewRoles"/></html:link></li>
      </ul>
  </dd>
</dl>
</td>
<td>
<dl class="dropdown">
  <dt id="two-ddheader" onmouseover="ddMenu('two',1)" onmouseout="ddMenu('two',-1)"><bean:message key="menu.member"/></dt>
  <dd id="two-ddcontent" onmouseover="cancelHide('two')" onmouseout="ddMenu('two',-1)">
    <ul class="ss">
		<li><html:link styleClass="underline" page="/forwardPmsPage.do?parameter=addMember"> <bean:message key="submenu.member.addMember"/> </html:link></li>
      <li><html:link styleClass="underline" page="/forwardPmsPage.do?parameter=viewMember"> <bean:message key="submenu.member.viewMembers"/> </html:link></li>
      <li><html:link styleClass="underline" action="searchpeople"> <bean:message key="submenu.member.peopleSearch"/> </html:link></li>
    </ul>
  </dd>
</dl>
</td>
  <td>
   <dl class="dropdown">
  <dt id="three-ddheader" onmouseover="ddMenu('three',1)" onmouseout="ddMenu('three',-1)"><bean:message key="menu.projectManagement"/></dt>
  <dd id="three-ddcontent" onmouseover="cancelHide('three')" onmouseout="ddMenu('three',-1)">
    <ul class="ss">
    <li><html:link styleClass="underline" page="/forwardPmsPage.do?parameter=addProject" > <bean:message key="submenu.projectManagement.addProject"/> </html:link></li>
      <li><html:link styleClass="underline" action="viewproject"> <bean:message key="submenu.projectManagement.viewProject"/> </html:link></li>
     <!-- <li><html:link styleClass="underline" action="assignproject"> Create Project Team </html:link></li>
        <li><html:link styleClass="underline" action="searchproject"> Search Project </html:link></li>
      <li><html:link styleClass="underline" action="updateoperation"> Update Operation </html:link></li>-->
      <li><html:link styleClass="underline" action="drawGanttChart"><bean:message key="submenu.projectManagement.viewProject.viewGanttChart"/> </html:link></li>
     </ul>
  </dd>
</dl>
</td>


<td>
<dl class="dropdown">
  <dt id="four-ddheader" onmouseover="ddMenu('four',1)" onmouseout="ddMenu('four',-1)"><bean:message key="menu.task"/></dt>
  <dd id="four-ddcontent" onmouseover="cancelHide('four')" onmouseout="ddMenu('four',-1)">
    <ul class="ss">
      <li><html:link styleClass="underline" page="/forwardPmsPage.do?parameter=newTask"> <bean:message key="submenu.task.createTask"/> </html:link></li>
      <li><html:link styleClass="underline" action="viewtask"> <bean:message key="submenu.task.viewTask"/> </html:link></li>
      
   </ul>
  </dd>
</dl>
</td>
<td>
<dl class="dropdown">
  <dt id="five-ddheader" onmouseover="ddMenu('five',1)" onmouseout="ddMenu('five',-1)"><bean:message key="menu.organization"/></dt>
  <dd id="five-ddcontent" onmouseover="cancelHide('five')" onmouseout="ddMenu('five',-1)">
    <ul class="ss">
      <li><html:link styleClass="underline" page="/forwardPmsPage.do?parameter=viewOrganization"> <bean:message key="submenu.organization.viewOrganization"/> </html:link></li>
     <li><html:link styleClass="underline" action="searchorganization"><bean:message key="submenu.organization.searchOrganization"/></html:link></li>
    </ul>
  </dd>
</dl>
</td>
<!-- 
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
 -->
 <td>
   <dl class="dropdown">
  <dt id="six-ddheader" onmouseover="ddMenu('six',1)" onmouseout="ddMenu('six',-1)"><bean:message key="menu.forBugzilla"/></dt>
  <dd id="six-ddcontent" onmouseover="cancelHide('six')" onmouseout="ddMenu('six',-1)">
    <ul class="ss">
    <li><html:link styleClass="underline" target="_blank" action="loginBugzilla"><bean:message key="submenu.forBugzilla.gointoBugzilla"/></html:link></li>
<!--    <li><html:link styleClass="underline" target="_blank" href="http://localhost:81/index.cgi?Bugzilla_login=${sessionScope.uid}&Bugzilla_password=&GoAheadAndLogIn=Log+in"> <bean:message key="submenu.forBugzilla.gointoBugzilla"/> </html:link></li>     -->
    <li><html:link styleClass="underline" page="/bugzillaConfig.do?parameter=accountCreationPage"><bean:message key="submenu.forBugzilla.createAccountIntoBugzilla"/> </html:link></li>
    <li><html:link styleClass="underline" page="/bugzillaConfig.do?parameter=setupReportBug"><bean:message key="submenu.forBugzilla.setupReportbug"/> </html:link></li>
    <li><html:link styleClass="underline" action="reportBug"><bean:message key="submenu.forBugzilla.reportBug"/></html:link></li>
<!--    <li><html:link styleClass="underline" href="http://localhost/enter_bug.cgi"><bean:message key="submenu.forBugzilla.reportBug"/></html:link></li>-->
    </ul>
  </dd>
</dl>
</td>
 <td>
<dl class="dropdown">
  <dt id="one-ddheader" style="width: 100px;" onmouseover="ddMenu('nine',1)" onmouseout="ddMenu('nine',-1)">
  <html:link page="/forwardPmsPage.do?parameter=pmsHelpPage" style="padding:0px;width: 100px;background-color:#336699;font-weight:bold;color:#ffffff;"><bean:message key="menu.help"/></html:link>
  </dt>
  </dl>
</td>
 </tr></table>
   </td>
</tr>
</table>
</body>
</html>