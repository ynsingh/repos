<%@ page language="java" import="java.sql.*" pageEncoding="UTF-8"%>
<%@page import="org.dei.edrp.pms.dataBaseConnection.MyDataSource"%>
<%@page import="org.dei.edrp.pms.task.TaskFields;"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
  <head>
    <title>example.jsp</title>
 <link rel="stylesheet" href="style/main.css" type="text/css"></link>
		
<link rel="stylesheet" href="style/Displaytagex.css" type="text/css"></link>
  </head>
  
  <body>
  <script type="text/javascript">
	function sure()
	{
		return(confirm("Are you sure want to Delete this Task?"));
	}
	</script>
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
	 //out.println("uid="+uid);
	ps=con.prepareStatement("select v.User_Id from validatetab v,project p where v.User_ID=? and "+
	"(p.Project_Name=? and v.Project_ID=p.Project_ID) and "+
	"v.PermittedBy=(select l.User_ID from login l where l.Authority='Admin')");
	ps.setString(1,uid);
	ps.setString(2,(String)session.getAttribute("projectName"));
		 rs=ps.executeQuery();
			if(rs.next())
			{
				//System.out.println("ok");
				flag=1;//if user is project manager
			}
			else
			{
				ps=con.prepareStatement("select v.User_Id from validatetab v,project p,task t where v.User_ID=? and "+
		"(p.Project_Name=? and v.Project_ID=p.Project_ID) and v.Valid_ID=t.Valid_ID");
		ps.setString(1,uid);
		ps.setString(2,(String)session.getAttribute("projectName"));
		 rs=ps.executeQuery();
			 	if(rs.next())
				{
					//System.out.println("not ok");
					flag=2;//if user is not a project manager and only member of this project
				}
				else 
				flag=0;
			}
			MyDataSource.freeConnection(con);
   %>
	
  <logic:notEmpty name="taskList" property="list">
    <div id=main_title><font color="#0044ff">Task List of:</font> <font color="#0970ff" size="2"><%= session.getAttribute("projectName")%></font></div><br>
    </logic:notEmpty>
    <logic:empty name="taskList" property="list"><br><font color="#0000ff" size="2">The Project you have chosen ,Have No Task</font>
    <br><br>
    <html:button property="back" value="Back" onclick="history.back();" />
    </logic:empty>
   <logic:notEmpty name="taskList" property="list">
 <display:table name="taskList.list" defaultsort="2" id="row" export="false" pagesize="10" requestURI="/showTask.do" decorator="org.dei.edrp.pms.deco.PmsDecorator" class="dataTable" >
				   
		<display:column property="taskName" title="Task Name" sortable="true" />
		<display:column property="projectName" title="Project Id" sortable="true" />
		<display:column property="resourceName" title="User Name" sortable="true" />
		<display:column property="orgName" title="Organisation Name" sortable="true" />
		<display:column property="sdate" title="Start Date" sortable="true" />
		<display:column property="fdate" title="Finish Date" sortable="true" />
		<display:column property="pcom" title="% Complete" sortable="true" />
		<display:column title="Gantt Chart Color" sortable="true">
		<font style="padding-left:100%; ;background-color:<%=((TaskFields)pageContext.getAttribute("row")).getInput_field()%>;">
		</font></display:column>

		<display:column property="taskDependency" title="Dependency" sortable="true" />
		<display:column property="darea" title="Description" maxLength="10" sortable="true" />
	<% 
	if(flag==1)//for project manager
	{
	 %>
	 <display:column media="html" title="Actions">
		 <html:link href="editTask.do" paramProperty="taskid" paramId="id" paramName="row">Edit
		  </html:link> | 
		 <html:link href="deleteTask.do" onclick="return sure();" paramProperty="taskid" paramId="id" paramName="row">Delete
		 </html:link>
		</display:column>
		
	<%
	}
	else if(flag==2)//for user
	{
	 %>
	 	<display:column media="html" title="Actions">
		 <html:link href="editTask.do" paramProperty="taskid" paramId="id" paramName="row">Edit
		  </html:link></display:column> 
	
	<%
	}
	%>
	</display:table>
    </logic:notEmpty>
  		
  </body>
</html:html>



