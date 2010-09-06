<%@ page language="java" import="java.sql.*" pageEncoding="UTF-8"%>
<%@ page import="in.ac.dei.edrp.pms.dataBaseConnection.MyDataSource"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%> 
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>

<html>
  <head>
	<link rel="stylesheet" href="style/main.css" type="text/css"></link>
	<link rel="stylesheet" href="style/style.css" type="text/css"></link>
	<link rel="stylesheet" href="style/jsgantt.css" type="text/css"></link>
	<script type="text/javascript" src="javascript/jsgantt.js"></script>
  	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<!-- for refresh this page automatically at every 3 seconds
	<META HTTP-EQUIV="Refresh" CONTENT="3">-->
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is gantt chart page">
  </head>
  
   <body>
   <%
	String mysession=(String)session.getAttribute("mysession");
	if(mysession==null)
	{
		response.sendRedirect("login.jsp");
	}
	%>
	
	<script type="text/javascript">
 	function fnrec()
	{
		a=document.getElementsByName("projselect");
		window.location.href="drawGanttChart.do?pname="+a[0].value;
	}
	</script>
	
	<%!
	String pname=null; 
	Connection con=null;
  	PreparedStatement ps=null;
   	ResultSet rs=null;
    %>
   <%
	 pname=request.getParameter("pname");
	 if(pname==null)
		pname="";
	 if((String)session.getAttribute("mysession")!=null)
	 {
		if(((String)session.getAttribute("authority")).equalsIgnoreCase("Super Admin"))//the authority is user or super admin
			{
			%>
			<div id=main_title><font color="#0044ff">Gantt Chart:</font><%=pname %></div><br>
			<%
			}
			else
			{
		 %>
    
    <div id=main_title><font color="#0044ff">Show Gantt Chart:</font></div><br>
  <div align="left">
	Select Project:
  <html:select style="color:#0044ff;width: 250px;"
   indexed="projselect" property="projselect" name="projselect" 
   value="<%=pname %>" onchange="fnrec()">	
		<html:option value="--Select--"></html:option>
		<% 
			try{
			con=MyDataSource.getConnection();
			ps=con.prepareStatement("select distinct p.project_name from project p,"+
									"user_in_org u,validatetab v where p.enable=0 and "+
									"u.valid_user_id=? and u.valid_orgportal=? "+
					"and u.valid_key=v.valid_user_key and v.valid_project_code=p.project_code"+
					" and v.valid_role_id=?");
			ps.setString(1,(String)session.getAttribute("uid"));
			ps.setString(2,(String)session.getAttribute("validOrgInPortal"));
			ps.setString(3,(String)session.getAttribute("roleid"));
		rs=ps.executeQuery();
				while(rs.next())
				{
			%>
			<html:option value="<%= rs.getString(1)%>"></html:option>
			<%
			}
			}
			catch(SQLException e){System.out.println("error in drawchart in jsp="+e);}
		finally
		{
			MyDataSource.freeConnection(con);
		}
		 %>
			</html:select><html:errors property="projselect"/> 
   </div>
   <%
   }
   }
   if(!pname.equals(""))
   {
    %>
    <div style="position:relative" class="gantt" id="GanttChartDIV"></div>
	<script>
  	var g = new JSGantt.GanttChart('g',document.getElementById('GanttChartDIV'), 'day');
	g.setShowRes(1); // Show/Hide Responsible (0/1)
	g.setShowDur(1); // Show/Hide Duration (0/1)
	g.setShowComp(1); // Show/Hide % Complete(0/1)
   g.setCaptionType('Resource');  // Set to Show Caption (None,Caption,Resource,Duration,Complete)
  if( g ) {
    // Parameters             (pID, pName,                  pStart,      pEnd,        pColor,   pLink,          pMile, pRes,  pComp, pGroup, pParent, pOpen)
	// use the XML file parser 
	
	//JSGantt.parseXML('D:/Struts/MyPMS2.1/.metadata/.plugins/com.genuitec.eclipse.easie.tomcat.myeclipse/tomcat/webapps/PMS/GChart.xml',g)
	JSGantt.parseXML('GChart.xml',g);
	 g.Draw();	
	 g.DrawDependencies();
	 }
  else
  {
    alert("not defined");
  }
	</script>
	<%}
	else
	{
 	%>
 <br>
 <h2><font size="2" color="#0000ff">Please select the project name first.</font></h2>
  <br>
 <%} %>
<br>
<html:button property="back" value="Back" styleClass="butStnd" onclick="history.back();" />
 </body>
</html>
