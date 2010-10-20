<%@ page language="java" pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html lang="true">
  <head>
    <html:base />
    
    <title>orgsearchlink.jsp</title>

	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" href="style/style.css" type="text/css"></link>
  </head>
  
  <body>
 	
 <html:form action="/searchorg">
  		<div id="main_title">
	<font color="#0044ff"><bean:message key="title.searchPage"/>:</font>
		 </div>
		 <br><br><br>
		<table cellspacing="2" cellpadding="2" border="0" align="center">
		<tr class="form-element">
		<td  class="form-label">
		<html:radio property="searchOption" value="Organisation Name"><bean:message key="label.orgName"/></html:radio>
		<html:radio property="searchOption" value="Organisation City"><bean:message key="label.searchOrgCity"/></html:radio>
		<html:radio property="searchOption" value="Organisation State"><bean:message key="label.searchOrgState"/></html:radio>
		</td>
		</tr>
		<tr></tr><tr></tr>
		<tr class="form-element">
		<td  class="form-label">
		<bean:message key="label.searchString"/> : 
		</td>
		<td class="form-widget">
		<html:text property="keysearch" size="40" value=""/><html:errors property="keysearch"/></td>
		<td class="form-widget"><input type="submit" value='<bean:message key="label.search.button" />' class="butStnd"/></td>
		</tr>
	</table>
    </html:form>
  </body>
  </html:html>