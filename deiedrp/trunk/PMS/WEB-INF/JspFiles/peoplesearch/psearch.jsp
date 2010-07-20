<%@ page language="java" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%> 
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
 
<html> 
	<head>
		<title>JSP for PeopleSearchForm form</title>
		<link rel="stylesheet" href="style/style.css" type="text/css"></link>
		<link rel="stylesheet" href="<html:rewrite page='/style/main.css'/>" type="text/css"></link>
	</head>
	<body>
	<%
	String mysession=(String)session.getAttribute("mysession");
	if(mysession==null)
	{
		response.sendRedirect("login.jsp");
	 }
	%>
  
		<html:javascript formName="peoplesearchform" />
		<html:form action="/searchaction" onsubmit="return validatePeoplesearchform(this);">
		<div id="main_title"><font color="#0044ff">
		Searching By:</font>
		 </div>
		 <br><br>
		  <br>
		<table cellspacing="2" cellpadding="2" border="0" align="center">
		<tr class="form-element">
		<td  class="form-label">
		<html:radio property="searchOption" value="User eMail">User E-mail id</html:radio>
		<html:radio property="searchOption" value="User Name">User Name</html:radio>		
		</td>
		</tr>
			<tr></tr><tr></tr>
		<tr class="form-element">
		<td  class="form-label">
		Enter Searched String : 
		</td>
		<td class="form-widget">
		<html:text property="keySearch" value="" size="40"/><html:errors property="keySearch"/>
		<td class="form-widget"><html:submit value="Search" styleClass="butStnd"/></td>
		</tr>
	</table>
		</html:form>
	</body>
</html>

