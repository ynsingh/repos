<%@ page language="java" import="java.sql.*" pageEncoding="ISO-8859-1"%>
<%@page import="control.CustomRequestProcessor"%>
<%@page import="dataBaseConnection.MyDataSource;"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
  <head>
    <html:base />

<title><tiles:getAsString name="title" /></title>
<link rel="stylesheet" href="style/style.css" type="text/css"></link>
<link rel="stylesheet" href="<html:rewrite page='/style/main.css'/>" type="text/css"></link>
</head>
<body>
	<%
		new CustomRequestProcessor().processNoCache(request, response);
	%>

<%
//System.out.println("session new in layout jsp="+session.isNew());
	String mysession=(String)session.getAttribute("mysession");
	if(mysession==null)
	{
		response.sendRedirect("login.jsp");  
	}
%>
<table border="1" cellspacing="0" cellpadding="0" height="100%" width="100%">
<tr><td class="header123" colspan="2" width="100%" height="17%">
<%!
  	Connection con=null;
  	PreparedStatement ps=null;
   	ResultSet rs=null;
   int flag=0;
   %>
  <%
  	con=MyDataSource.getConnection();
  	/* Getting the User_iD which is currently logged in. */
   	String uid=(String)session.getAttribute("uid");
	ps=con.prepareStatement("select Authority from login where User_ID=?");
	ps.setString(1,uid);
		 rs=ps.executeQuery();
			rs.next();
			if(rs.getString(1).equals("Admin"))
			{
			flag=1;
			}
			else
			{
				flag=0;
			}
   if(con!=null)
   {
   ps.close();
   rs.close();
   con.close();
   }
   %>
<%
if(flag==1)
{
 %>
<tiles:insert attribute="adminheader" />
<%
}
else
{
 %>
<tiles:insert attribute="header" />
<%
} 
%>
</td></tr>
<tr><td>
<table border="0" cellspacing="0" height="100%" width="100%">
<tr>

<td class="bodypage" width="100%" height="80%" style="position: relative;overflow: scroll;" valign="top"><tiles:insert attribute="body"/></td>
</tr></table></td></tr>
<tr><td class="footerpage" colspan="2" width="100%" height="7%" ><tiles:insert attribute="footer" /></td></tr>
</table>
</body>
</html:html>

