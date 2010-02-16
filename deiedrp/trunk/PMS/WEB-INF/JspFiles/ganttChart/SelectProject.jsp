<%@ page language="java" import="java.sql.*" pageEncoding="ISO-8859-1"%>
<%@page import="org.dei.edrp.pms.dataBaseConnection.MyDataSource;"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%> 
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'gselect.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	 
  <link rel="stylesheet" href="style/main.css" type="text/css"></link></head>
  
  <body>
     <%
    String mysession=(String)session.getAttribute("mysession");
	if(mysession==null)
	{
		response.sendRedirect("login.jsp");
	 }
	%>
  <html:javascript formName="gcform" />
  <html:form action="/DrawGanttChart" onsubmit="return validateGcform(this);">
  <table cellspacing="2" cellpadding="2" border="0"  style="margin-left: 40px">
		<tr></tr>
     <tr class="form-element"><td class="form-label">
		Project Name
		</td>
			<td class="form-widget">
  <html:select property="pselect">
			<html:option value="--Select--"></html:option>
		<% Connection con=null;
					try{
		String uid=(String)session.getAttribute("uid");
	 	con=MyDataSource.getConnection();
		PreparedStatement ps=con.prepareStatement("select Authority from login where User_ID=?");
		ps.setString(1,uid);
		ResultSet rs=ps.executeQuery();
		rs.next();
			/* Fetching the Project Name from project table.*/	
			if(rs.getString(1).equals("Admin"))
			{
				ps=con.prepareStatement("select p.Project_Name from project p where p.enable=0 order by p.Project_Name asc");
			}
			else
			{
		 	ps=con.prepareStatement("select distinct p.Project_Name from project p,validatetab v "+
		 		"where p.enable=0 and (p.View_Permission='For All' or (v.Project_ID=p.Project_ID and v.User_ID=?)) order by p.Project_Name asc");//,userinfo where userinfo.User_ID=?");
				ps.setString(1,uid);
			}
		rs=ps.executeQuery();
		
				while(rs.next())
				{
			%>
			<html:option value="<%= rs.getString(1)%>"></html:option>
			<%
			}
			}
			catch(SQLException e){}
		finally
		{
			MyDataSource.freeConnection(con);
		}
			 %>
			</html:select><html:errors property="pselect"/>
		
    
     </td> </tr>
     <tr>
    <td>
    <input type="submit" value="Submit"/>
    </td>
       </tr> 
    </table>
  
  </html:form>
  </body>
</html>
