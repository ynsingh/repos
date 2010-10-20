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
	  
		<html:javascript formName="peoplesearchform" />
		<html:form action="/searchaction" onsubmit="return validatePeoplesearchform(this);">
		<div id="main_title"><font color="#0044ff">
		<bean:message key="title.searchPage"/>:</font>
		 </div>
		 <br><br>
		  <br>
		<table cellspacing="2" cellpadding="2" border="0" align="center">
		<tr class="form-element">
		<td  class="form-label">
		<html:radio property="searchOption" value="User eMail"><bean:message key="label.userEmailid"/></html:radio>
		<html:radio property="searchOption" value="User Name"><bean:message key="label.userName"/></html:radio>		
		</td>
		</tr>
			<tr></tr><tr></tr>
		<tr class="form-element">
		<td  class="form-label">
		<bean:message key="label.searchString"/> : 
		</td>
		<td class="form-widget">
		<html:text property="keySearch" value="" size="40"/><html:errors property="keySearch"/>
		<td class="form-widget"><input type="submit" value='<bean:message key="label.search.button" />' class="butStnd"/></td>
		</tr>
	</table>
		</html:form>
	</body>
</html>

