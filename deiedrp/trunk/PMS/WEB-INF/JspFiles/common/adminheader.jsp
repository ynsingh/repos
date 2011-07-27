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
		<b><font size="1"><bean:message key="header.welcome"/></font></b>
		<font size="1">
		  		<c:out value="${sessionScope.uid}"/> | </font>
		 		<html:link action="logout" styleClass="B"> <font size="-1"><bean:message key="header.logout"/></font> </html:link> |
				<html:link styleClass="B" action="changeOwnpassword"><bean:message key="header.changePassword"/></html:link> 	</div>				
		 </td>
		 </tr>
		<tr>
	<td>

<table cellspacing="0" cellpadding="0" >
<tr>
<td>
<dl class="dropdown">
  <dt>
   <html:link action="welcome" style="padding:0px;background-color:#336699;font-weight:bold;color:#ffffff;"><bean:message key="menu.home"/></html:link>
  </dt>
  </dl>
</td>
<td>
<dl class="dropdown">
  <dt >
   <html:link page="/forwardPmsPage.do?parameter=mailConfigPage" style="padding:0px;background-color:#336699;font-weight:bold;color:#ffffff;"><bean:message key="menu.mailConfiguration"/></html:link>
  </dt>
  </dl>
</td>
<td>
<dl class="dropdown">
  <dt id="two-ddheader" onmouseover="ddMenu('two',1)" onmouseout="ddMenu('two',-1)"><bean:message key="menu.portal"/></dt>
  <dd id="two-ddcontent" onmouseover="cancelHide('two')" onmouseout="ddMenu('two',-1)">
    <ul class="ss">
      <li><html:link styleClass="underline" page="/forwardPmsPage.do?parameter=newPortal"><bean:message key="submenu.portal.createPortal"/></html:link></li>
      <li><html:link styleClass="underline" action="viewportal"><bean:message key="submenu.portal.viewPortal"/></html:link></li>
    </ul>
  </dd>
 </dl>
</td>

<td>
<dl class="dropdown">
  <dt id="three-ddheader" onmouseover="ddMenu('three',1)" onmouseout="ddMenu('three',-1)"><bean:message key="menu.organization"/></dt>
  <dd id="three-ddcontent" onmouseover="cancelHide('three')" onmouseout="ddMenu('three',-1)">
    <ul class="ss">
      <li><html:link styleClass="underline" page="/forwardPmsPage.do?parameter=addOrganization"><bean:message key="submenu.organization.addOrganization"/></html:link></li>
      <li><html:link styleClass="underline" page="/forwardPmsPage.do?parameter=viewOrganization"><bean:message key="submenu.organization.viewOrganization"/></html:link></li>
      <li><html:link styleClass="underline" action="searchorganization"><bean:message key="submenu.organization.searchOrganization"/></html:link></li>
    </ul>
  </dd>
</dl>
</td>

<td>
<dl class="dropdown">
  <dt id="four-ddheader" onmouseover="ddMenu('four',1)" onmouseout="ddMenu('four',-1)"><bean:message key="menu.role"/></dt>
  <dd id="four-ddcontent" onmouseover="cancelHide('four')" onmouseout="ddMenu('four',-1)">
    <ul class="ss">
      <li><html:link styleClass="underline" page="/forwardPmsPage.do?parameter=newRole"><bean:message key="submenu.role.addRole"/></html:link></li>
      <li><html:link styleClass="underline" page="/forwardPmsPage.do?parameter=viewRole"><bean:message key="submenu.role.viewRoles"/></html:link></li>
      </ul>
  </dd>
</dl>
</td>
<td>
<dl class="dropdown">
  <dt id="five-ddheader" onmouseover="ddMenu('five',1)" onmouseout="ddMenu('five',-1)"><bean:message key="menu.member"/></dt>
  <dd id="five-ddcontent" onmouseover="cancelHide('five')" onmouseout="ddMenu('five',-1)">
    <ul class="ss">
      <li><html:link styleClass="underline" page="/forwardPmsPage.do?parameter=addMember"> <bean:message key="submenu.member.addMember"/> </html:link></li>
      <li><html:link styleClass="underline" page="/forwardPmsPage.do?parameter=viewMember"> <bean:message key="submenu.member.viewMembers"/> </html:link></li>
    <li><html:link styleClass="underline" page="/forwardPmsPage.do?parameter=addOrgPortal"><bean:message key="submenu.member.addOrganizationIntoPortal"/></html:link></li>
     <!--   <li><html:link styleClass="underline" action="assignproject"> Assign Project </html:link></li>-->
      	<li><html:link styleClass="underline" action="searchpeople"> <bean:message key="submenu.member.peopleSearch"/> </html:link></li>
      </ul>
  </dd>
</dl>
</td>

<td>
  <dl class="dropdown">
  <dt id="six-ddheader" onmouseover="ddMenu('six',1)" onmouseout="ddMenu('six',-1)"><bean:message key="menu.projectManagement"/></dt>
  <dd id="six-ddcontent" onmouseover="cancelHide('six')" onmouseout="ddMenu('six',-1)">
    <ul class="ss">
   <!--   <li><html:link styleClass="underline" action="newproject" > Add Project </html:link></li>-->
      <li><html:link styleClass="underline" action="viewproject"><bean:message key="submenu.projectManagement.viewProject"/></html:link></li>
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
 <!--  changes done by nupur/11/02/2011 -->
 <td>
   <dl class="dropdown">
  <dt id="seven-ddheader" onmouseover="ddMenu('seven',1)" onmouseout="ddMenu('seven',-1)"><bean:message key="menu.forBugzilla"/></dt>
  <dd id="seven-ddcontent" onmouseover="cancelHide('seven')" onmouseout="ddMenu('seven',-1)">
    <ul class="ss">    
    <li><html:link styleClass="underline" page="/bugzillaConfig.do?parameter=urlCreationPage"> <bean:message key="submenu.forBugzilla.urlToBugzilla"/> </html:link></li>
    <!--<li><html:link styleClass="underline" page="/bugzillaConfig.do?parameter=loginPage"> <bean:message key="submenu.forBugzilla.gointoBugzilla"/> </html:link></li>-->
    <li><html:link styleClass="underline" target="_blank" action="loginBugzilla"><bean:message key="submenu.forBugzilla.gointoBugzilla"/></html:link></li>
<!--     <li><a style="underline" target="_blank" href="<bean:message bundle="myResource" key="url"/>/index.cgi?Bugzilla_login=${sessionScope.uid}&Bugzilla_password=&GoAheadAndLogIn=Log+in"> <bean:message key="submenu.forBugzilla.gointoBugzilla"/> </a></li>      -->
    <li><html:link styleClass="underline" page="/bugzillaConfig.do?parameter=accountCreationPage"><bean:message key="submenu.forBugzilla.createAccountIntoBugzilla"/> </html:link></li>
    <li><html:link styleClass="underline" page="/bugzillaConfig.do?parameter=setupReportBug"><bean:message key="submenu.forBugzilla.setupReportbug"/> </html:link></li>      
<!--    <li><a style="underline" href="<bean:message bundle="myResource" key="url"/>/enter_bug.cgi"><bean:message key="submenu.forBugzilla.reportBug"/></a></li>     -->
    <li><html:link styleClass="underline" action="reportBug"><bean:message key="submenu.forBugzilla.reportBug"/></html:link></li>
    </ul>
  </dd>
</dl>
</td>
 <td>
<dl class="dropdown">
  <dt>
   <html:link page="/forwardPmsPage.do?parameter=pmsHelpPage" style="padding:0px;width: 100px;background-color:#336699;font-weight:bold;color:#ffffff;"><bean:message key="menu.help"/></html:link>
  </dt>
  </dl>
</td>



</tr></table>
   </td></tr></table>
</body>
</html>
