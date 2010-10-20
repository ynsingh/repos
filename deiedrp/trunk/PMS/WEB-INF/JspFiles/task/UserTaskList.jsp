<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page import="in.ac.dei.edrp.pms.task.TaskFields"%>
<%@ page import="in.ac.dei.edrp.pms.task.TaskList;"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
  <head>
    <title>example.jsp</title>
 <link rel="stylesheet" href="style/Displaytagex.css" type="text/css"></link>
 <link rel="stylesheet" href="style/dropdown.css" type="text/css"></link>
 <link rel="stylesheet" href="style/style.css" type="text/css"></link>
  
  <script type="text/javascript">
  function fnrec()
	{
		a=document.getElementsByName("nrec");
		b=document.getElementsByName("projname");
		c=document.getElementsByName("show");
		window.location.href="viewtask.do?key="+a[0].value+"&key1="+b[0].value+"&key2="+c[0].value;
	}
	function sure()
	{
		return(confirm("Are you sure want to Delete this Task?"));
	}
	
	</script>
  </head>
  
  <body>
  
	<%!
	String key=null;
	String key1=null; 
	String key2=null; 
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
 
    <div id=main_title><font color="#0044ff"><bean:message key="title.taskList"/>:</font></div><br>
  <div align="left">
	<bean:message key="label.selectProject"/>:
  <html:select style="color:#0044ff;width: 250px;" indexed="projname" property="projname" name="projname" value="<%=key1 %>" onchange="fnrec();">	
		<html:option value="--Select--"></html:option>
		<sql:query var="projectList" dataSource="jdbc/mydb">
				select distinct p.project_name from project p,
					user_in_org u,validatetab v where p.enable=0 and 
					u.valid_user_id=? and u.valid_orgportal=? 
					and u.valid_key=v.valid_user_key and v.valid_project_code=p.project_code
					and v.valid_role_id=?
					<sql:param value="${sessionScope.uid}"/>
					<sql:param value="${sessionScope.validOrgInPortal}"/>
					<sql:param value="${sessionScope.roleid}"/>
			</sql:query>
			<c:forEach var="row" items="${projectList.rows}">
			<html:option value="${row.project_name}"></html:option>
			</c:forEach>
		 </html:select>
 <html:errors property="projname"/>
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
    <html:option value="25">25</html:option>
        </html:select>
			<html:errors property="nrec"/>
	<div align="right">
		<html:link action="newtask"><bean:message key="label.newTask"/><img border="0" title="Add new task" src="img/user1_add.png" width="15" "height="15" ></html:link>
	</div>
 </div>
    <logic:empty name="taskList" property="list"><br><font color="#0000ff" size="2">
    <bean:message key="label.recordsInfo"/></font>
    <br><br>
    <input type="button" value='<bean:message key="label.back.button" />' class="butStnd" onclick="history.back();" />
    </logic:empty>
   <logic:notEmpty name="taskList" property="list">
   <html:form action="/assignedTask">
   <input type="hidden" name="projname" id="projname" value="<%=key1 %>"/>
 <display:table name="taskList.list" defaultsort="1" id="row" export="true" pagesize="<%=Integer.parseInt(key) %>" requestURI="/viewtask.do" decorator="in.ac.dei.edrp.pms.deco.PmsDecorator" class="dataTable" >
		<display:column title="Task Name" sortable="true" >
		<input type="text" readonly="readonly" name="tname" id="tname" 
		style="border: none;background-color: transparent;" class="dataTable"
					value="<%=((TaskFields)pageContext.getAttribute("row")).getTaskName()%>"/>
		</display:column>
		
		<c:if test="${param.key2!='Not Assigned'}">
			<display:column property="resourceName" title="Assigned To" sortable="true" />
		</c:if>
		<display:column property="no_of_days" title="No_Of_Days" sortable="true" />
		<display:column property="schedule_start_date" title="Schedule Start Date" sortable="true" />
		<display:column property="schedule_end_date" title="Schedule End Date" sortable="true" />
		<display:column property="actual_start_date" title="Actual Start Date" sortable="true" />
		<display:column property="actual_end_date" title="Actual End Date" sortable="true" />
		<display:column title="Gantt Chart Color" sortable="true">
		<font style="padding-left:100%; background-color:<%=((TaskFields)pageContext.getAttribute("row")).getGchart_color()%>;">
		</font></display:column>
		<display:column property="per_completed" title="% Completed" maxLength="10" sortable="true" />
		<display:column property="task_status" title="Status" maxLength="10" sortable="true" />
		<display:column property="dependency" title="Dependency" sortable="true" />
		
		<c:if test="${param.key2=='Not Assigned'}">
		<logic:equal name="row" property="assign_task_permission" value="Allow">
		<display:column title="Assigned To" >
		<select name="assignedTo" id="assignedTo" class="dataTable"
		style="width: 200px;background-color: transparent;">
			<option value="--Select--">--Select--</option>
			<sql:query var="projectList" dataSource="jdbc/mydb">
				select u.valid_user_id,r.role_name 
					from validatetab v,user_in_org u,project p,role r
					 where v.valid_user_key=u.valid_key and
					 p.project_code=v.valid_project_code and
					p.project_name=? and p.valid_org_inportal=? and v.valid_role_id=r.role_id
					 order by u.valid_user_id asc
					<sql:param value="${param.key1}"/>
					<sql:param value="${sessionScope.validOrgInPortal}"/>
			</sql:query>
			<c:forEach var="row1" items="${projectList.rows}">
			<option value="${row1.valid_user_id} (${row1.role_name})"><c:out value="${row1.valid_user_id} (${row1.role_name})"/></option>
			</c:forEach>
		</select>
		</display:column>
		</logic:equal>
		</c:if>
		<logic:equal name="row" property="editauthority" value="Allow">
			 <display:column media="html" title="Actions" property="tasklink"/>
		</logic:equal>
		<display:setProperty name="export.pdf.filename" value="TaskList.pdf"/>
		<display:setProperty name="export.excel.filename" value="TaskList.xls"/>
		<display:setProperty name="export.xml.filename" value="TaskList.xml"/>
		<display:setProperty name="export.csv.filename" value="TaskList.csv"/>
	</display:table>
	<c:if test="${param.key2=='Not Assigned'}">
	<logic:equal name="row" property="assign_task_permission" value="Allow">
	<div align="right">
	<input type="submit" value='<bean:message key="label.done.button" />' class="butStnd"/>
    </div>
    </logic:equal>
   </c:if>
    </html:form>
    </logic:notEmpty>
    
  	</body>
</html:html>



