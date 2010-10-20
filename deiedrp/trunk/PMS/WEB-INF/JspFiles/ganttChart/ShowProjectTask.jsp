<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page import="in.ac.dei.edrp.pms.task.TaskFields"%>
<%@ page import="in.ac.dei.edrp.pms.task.TaskList;"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
  <head>
    <title>example.jsp</title>
 <link rel="stylesheet" href="style/Displaytagex.css" type="text/css"></link>
 <link rel="stylesheet" href="style/dropdown.css" type="text/css"></link>
 <link rel="stylesheet" href="style/style.css" type="text/css"></link>
  </head>
  
  <body>
  <script type="text/javascript">
  function fnrec()
	{
		a=document.getElementsByName("nrec");
		b=document.getElementById("pname").value;
		c=document.getElementsByName("show");
		window.location.href="projectDetails.do?key="+a[0].value+"&key1="+b+"&key2="+c[0].value;
	}
	function sure()
	{
		return(confirm("Are you sure want to Delete this Task?"));
	}
	</script>
	<%!
	String key=null;
	String key1=null; 
	String key2=null; 
  	int flag=0;
   %>
   <%
	 key=request.getParameter("key");
	 if(key==null)
	 key="10";
	 key1=request.getParameter("key1");
	 if(key1==null)
		key1="";
	key2=request.getParameter("key2");
	 if(key2==null)
		key2="";
 %>
   <% request.setAttribute("taskList", new TaskList(key1,key2,
   (String)session.getAttribute("validOrgInPortal"),(String)session.getAttribute("roleid"))); %>	
 
 <input type="hidden" name="pname" id="pname" value="<%=key1 %>"/>   
  <div align="left">
 <bean:message key="label.showCategory"/>:
 <html:select style="color:#0044ff" property="show" name="show" value="<%=key2 %>" onchange="fnrec();">	
    <html:option value="Assigned">Assigned</html:option>
    <html:option value="Not Assigned">Not Assigned</html:option>
    <html:option value="Completed" >Completed</html:option>
    </html:select>
	<html:errors property="show"/>
	<bean:message key="title.numberOfRecords"/>:
  <html:select property="nrec" name="nrec" value="<%=key %>" onchange="fnrec();">	
    <html:option value="5">5</html:option>
    <html:option value="10">10</html:option>
    <html:option value="15">15</html:option>
    <html:option value="20">20</html:option>
        </html:select>
			<html:errors property="nrec"/>
	<div align="right">
		<html:link action="newtask"><bean:message key="label.newTask"/><img border="0" title="Add new task" src="img/user1_add.png" width="15" "height="15" ></html:link>
	</div>
 </div>
    <logic:empty name="taskList" property="list"><br><font color="#0000ff" size="2">
    <bean:message key="label.recordsInfo"/></font>
    <br><br>
    <input type="button" value='<bean:message key="label.back.button"/>' class="butStnd" onclick="history.back();" />
    </logic:empty>
   <logic:notEmpty name="taskList" property="list">
 <display:table name="taskList.list" defaultsort="1" id="row" export="false" pagesize="<%=Integer.parseInt(key) %>" requestURI="/projectDetails.do" decorator="in.ac.dei.edrp.pms.deco.PmsDecorator" class="dataTable" >
		<display:column property="taskName" title="Task Name" sortable="false" />
		<%if(!key2.equals("Not Assigned")){ %>
		<display:column property="resourceName" title="Assigned To" sortable="false" />
		<%} %>
		<display:column property="no_of_days" title="No_Of_Days" sortable="false" />
		<display:column property="schedule_start_date" title="Schedule Start Date" sortable="false" />
		<display:column property="schedule_end_date" title="Schedule End Date" sortable="false" />
		<display:column property="actual_start_date" title="Actual Start Date" sortable="false" />
		<display:column property="actual_end_date" title="Actual End Date" sortable="false" />
		<display:column title="Gantt Chart Color" sortable="false">
		<font style="padding-left:100%; background-color:<%=((TaskFields)pageContext.getAttribute("row")).getGchart_color()%>;">
		</font></display:column>
		<display:column property="per_completed" title="% Completed" maxLength="10" sortable="true" />
		<display:column property="task_status" title="Status" maxLength="10" sortable="true" />
		<display:column property="dependency" title="Dependency" sortable="false" />
		 <logic:equal name="row" property="editauthority" value="Allow">
		 <display:column media="html" title="Actions" property="tasklink"/>
				
		</logic:equal>
	</display:table>
    </logic:notEmpty>
  	 </body>
</html:html>



