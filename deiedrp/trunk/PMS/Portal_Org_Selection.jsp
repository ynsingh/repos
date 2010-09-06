<%@ page language="java" pageEncoding="UTF-8"%>

<%@ page import="in.ac.dei.edrp.pms.control.CustomRequestProcessor"%>

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html:html lang="true">
  <head>
   <title>Portal_Org Selection</title>
   <link rel="icon" href="img/logo.ico" type="image/x-icon">
<link rel="stylesheet" href="style/dropdown.css" type="text/css"></link>
<link rel="stylesheet" href="style/style.css" type="text/css"></link>
   <script type='text/javascript' src='dwr/engine.js'></script>
  <script type='text/javascript' src='dwr/util.js'></script>
	<!-- This JavaScript file is generated specifically for your application -->
    <script type='text/javascript' src='dwr/interface/DynamicList.js'></script>
	<script type="text/javascript">
	 
   function organisationPortalList() {
   var portalname = DWRUtil.getValue("portalname");
   var username = DWRUtil.getValue("uname");
  DynamicList.organisationPortalList(portalname,username,function(data)
  {
  		DWRUtil.removeAllOptions(document.getElementsByName("orgname")[0]);
  		DWRUtil.addOptions(document.getElementsByName("orgname")[0],data);
  	  	validUserRoleList();
   }
  ); 
 }
 
 function validUserRoleList(){
  var portalname = DWRUtil.getValue("portalname");
  var orgname = DWRUtil.getValue("orgname");
   var username = DWRUtil.getValue("uname");
   
  DynamicList.userRoleList(portalname,orgname,username,function(data)
  {
  		DWRUtil.removeAllOptions(document.getElementsByName("rolename")[0]);
  	  	DWRUtil.addOptions(document.getElementsByName("rolename")[0],data);
   }
  );
 
 }
  </script>
  </head>
  
  <body bgcolor="#fffffd" onload="organisationPortalList()">
  <noscript><h1>Warning: either you have javascript disabled or your browser does not support javascript. To work properly, this page requires javascript to be enabled.</h1></noscript>
  <%! String mysession=null; %>
	
	<%
		new CustomRequestProcessor().processNoCache(request, response);
		mysession=(String)session.getAttribute("mysession");
		if(mysession==null)
		{
			response.sendRedirect("login.jsp");  
		}
	else{
	%>
  <table height="100%" width="100%" cellspacing="0px" cellpadding="0px">
  <tr height="10%">
  <td width="100%">
  <table style="border:0px solid #000066;width:100%;background-image: url('img/backimage.PNG');background-repeat: repeat;" cellspacing="0px" cellpadding="0px">
<tr>
	<td valign="top"><div>
					<div style="float:left;width:70%;color:#000066;font-size:30px;font-family:algerian;background-image:url(header-gif.rev.gif);background-repeat: no-repeat;background-position: left;height:13%;text-align: center;text-shadow: aqua;">
					<bean:message key="header.title" />
					<br><div style="padding-bottom:15px;font: normal;font-family: Arial, Helvetica, sans-serif;color:#000000;">
					<b><font size="3"><bean:message key="header.subtitle" /></font></b><br>
					<font size="2">(<bean:message key="header.subtitle1" />)</font></div>
					</div>
					</div>
					<div align="right" style="padding-right: 10px;">
		<b><font size="1">Welcome,</font></b>
		<font size="1">
		  		<c:out value="${sessionScope.uid}"/> |</font>
		 
				<html:link action="logout" styleClass="B"><font size="-1"> Logout</font></html:link>
				</div>				
		 </td></tr>
		</table></td></tr>
  
  <tr height="80%">
  <td align="justify" style="color:#000000; width="50%">
	<table width="100%" height="100%" >
		<tr>
		<td class="outerbox">
			
			 <b>PMS Portals<br/><br/>
			 Hello , <b><font color="blue"><c:out value="${sessionScope.uid}"/></font></b><br/>
			<br/>You have access to the following portals. Select the portal and organization you wish to access. <br/><br/>
			   
	   </b>
	  <table class="innerbox" cellpadding="10px;" cellspacing="2px;" align="center" >
	<html:javascript formName="portalnameform" dynamicJavascript="true"	staticJavascript="true" />
	<html:form action="/mainwelcome" onsubmit="return validatePortalnameform(this)">
	  
	  <tr><td>
	   <input type="hidden" name="uname" id="uname" value="${sessionScope.uid}" size="20" readonly="readonly"/>
	   </td></tr>
	  <tr ><td align="left">
	  Portal Name :
	   </td>
	   <td >
	   <html:select property="portalname" indexed="portalname" style="width: 270px;height: 22px;" onchange="organisationPortalList()">
		<html:option value="--Select--"></html:option>
		<sql:query var="userPortal" dataSource="jdbc/mydb">
				select distinct p.portal_name from portal p,org_into_portal oip,
				user_in_org uio where p.portal_id=oip.portal_id and 
				uio.Valid_OrgPortal=oip.valid_org_inportal and uio.valid_user_id=?
				<sql:param value="${sessionScope.uid}"/>
			</sql:query>
			<c:forEach var="row" items="${userPortal.rows}">
			<html:option value="${row.portal_name}"></html:option>
			</c:forEach>
		 </html:select><html:errors property="portalname"/>
		 
		
	   </td></tr>
	   <tr><td align="left">
	   Organization Name :
	  </td>
	   <td >
	   <html:select indexed="orgname" property="orgname" onchange="validUserRoleList()" style="width: 270px;height: 22px;">
		<html:option value="--Select--"></html:option>
		</html:select>
	   <html:errors property="orgname"/>
	   </td>
	   </tr>
	   <tr><td align="left">
	   Role Name :
	  </td>
	   <td >
	   <html:select indexed="rolename" property="rolename" style="width: 270px;height: 22px;">
		<html:option value="--Select--"></html:option>
		</html:select>
	   <html:errors property="rolename"/>
	   </td>
	   </tr>
	   
	   <tr></tr><tr></tr>
	  <tr><td></td><td align="left" >
	   <html:submit styleClass="butStnd"></html:submit></td></tr>
	   </html:form>
	   </table> </td> </tr>
	  </table> </td> </tr> 
   <tr height="10%">
   <td class="footerpage"><jsp:include page="/WEB-INF/JspFiles/common/footer.jsp"></jsp:include> 
   </td></tr> 
   <%} %>
 </table> </body>
</html:html>
