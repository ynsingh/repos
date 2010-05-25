<%@ page language="java" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%> 
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>

<html>
  <head>
    
    <title>My JSP '/search.jsp' starting page</title>
    <html:javascript formName="projectsearchform" />
<link rel="stylesheet" href="<html:rewrite page='/style/main.css'/>" type="text/css"></link>    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
  <%
	String mysession=(String)session.getAttribute("mysession");
	if(mysession==null)
	{
		response.sendRedirect("login.jsp");
	 }
	%>
  
  	<html:form action="/go2" onsubmit="return validateProjectsearchform(this);">
  		<div id="main_title"><font color="#0044ff">
		Searching By:</font>
		 </div>
		 <br><br>
		  <br>
		<table cellspacing="1" cellpadding="6" border="0" align="center">
		<tr class="form-element">
		<td  class="form-label">
		<html:radio property="searchOption" value="Project Name">Project Name</html:radio>
		<html:radio property="searchOption" value="Project Owner">Project Owner</html:radio>
		<html:radio property="searchOption" value="Organisation Name">Organisation Name</html:radio>
		</td>
		</tr>
		
		<tr></tr><tr></tr>
		<tr class="form-element">
		<td  class="form-label">
		Enter Searched String : 
		</td>
		<td class="form-widget">
		<html:text property="keysearch" size="40"/>
		<html:errors property="keysearch"/></td>
		<td class="form-widget"><html:submit value="Search"/></td>
		</tr>
	</table>
    </html:form>
    </body>
</html>
