<%@ page language="java" pageEncoding="UTF-8"%>

<%@ page import="in.ac.dei.edrp.pms.control.CustomRequestProcessor"%>
<%@ page import="in.ac.dei.edrp.pms.viewer.checkRecord;"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles"%>

<html:html>
  <head>
    <html:base />

<title><tiles:getAsString name="title" /></title>
<link rel="stylesheet" href="style/style.css" type="text/css"></link>
<link rel="stylesheet" href="<html:rewrite page='/style/main.css'/>" type="text/css"></link>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is Layout page of Project Management System">
</head>
<link rel="icon" href="img/logo.ico" type="image/x-icon">
<body>
<noscript><h1>Warning: either you have javascript disabled or your browser does not support javascript. To work properly, this page requires javascript to be enabled.</h1></noscript>
	<%!   
  	 String mysession=null;
   %>
	
	<%
		new CustomRequestProcessor().processNoCache(request, response);
	%>

<%
//System.out.println("session new in layout jsp="+session.isNew());
	
	mysession=(String)session.getAttribute("mysession");
	if(mysession==null)
	{
		response.sendRedirect("login.jsp");  
	}
%>
<table border="1" cellspacing="0" cellpadding="0" height="100%" width="100%">
<tr><td class="header123" colspan="2" width="100%" height="13%">

  <%
  	if(mysession!=null)
  	{
  		/* Getting the User_iD which is currently logged in. */
   		String uid=(String)session.getAttribute("uid"); //both lines are commented on 12 april
		if(checkRecord.duplicacyChecker("Authority","login","login_user_id",uid).equalsIgnoreCase("Super Admin"))
		{
 	%>
<tiles:insert attribute="adminheader" />
	<%
	}
	else
	{
 	%>
<tiles:insert attribute="header"/>
	<%
	} 
	}
	%>
</td></tr>
<tr><td>
<table border="0" cellspacing="0" height="100%" width="100%">
<tr>

<td class="bodypage" width="100%" height="80%" style="position: relative;overflow: scroll;" valign="top">
<tiles:insert attribute="body"/></td>
</tr></table></td></tr>
<tr><td class="footerpage" colspan="2" width="100%" height="7%" ><tiles:insert attribute="footer" /></td></tr>
</table>
</body>
</html:html>

