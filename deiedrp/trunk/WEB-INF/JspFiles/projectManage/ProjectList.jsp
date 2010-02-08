<%@ page language="java" pageEncoding="UTF-8"%>
<%@page import="projmanage.ProjectFields;"%>
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
  </head>
  <script language="JavaScript" type="text/javascript">
	function fnrec()
	{
		a=document.getElementsByName("nrec");
		window.location.href="projectList.do?key="+a[0].value;
	}
	function rowchange()
	{
	    var table = document.getElementById("row");    
    	var tbody = table.getElementsByTagName("tbody")[0];
    	var rows = tbody.getElementsByTagName("tr");
    // add event handlers so rows light up and are clickable
    	for (i=0; i < rows.length; i++) {
        	var value = rows[i].getElementsByTagName("td")[0].firstChild.nodeValue;
          if (value.indexOf('N') == 6) {
            rows[i].style.backgroundColor = "#C0C0C0";
            rows[i].style.textDecoration="line-through";
            rows[i].style.color="#FF6347";
        }
    	}
	}
	</script>
  <body onload="rowchange();">
  
  <logic:notEmpty name="projectList" property="list">
  <div id="main_title" align="left">
		    <font color="#0044ff">Project List:</font></div><br>
<%!  String key=null;%>
<%
	 key=request.getParameter("key");
	 if(key==null)
	 key="10";
  %>
 <div align="left">
	Number of records to be displayed:
  <html:select property="nrec" name="nrec" value="<%=key %>" onchange="fnrec();">	
    <html:option value="5" >5</html:option>
    <html:option value="10" >10</html:option>
    <html:option value="15" >15</html:option>
    <html:option value="20" >20</html:option>
        </html:select>
			<html:errors property="nrec"/><br><br>
	</div>
  </logic:notEmpty>
 <display:table name="projectList.list" defaultsort="2" id="row" export="true" pagesize="<%=Integer.parseInt(key) %>" requestURI="/projectList.do" class="dataTable" >
		<display:column title="Active Status" sortable="true" >
		<logic:equal name="row" property="enable" value="0">Yes</logic:equal>
		<logic:equal name="row" property="enable" value="1">No</logic:equal>
		</display:column>
		<display:column property="id" title="Project ID" sortable="true" />
		<display:column property="name" title="Project Name" sortable="true" />
		<display:column property="startDate" title="Start Date" sortable="true" />
		<display:column property="finishDate" title="Finished Date" sortable="true" />
		<display:column property="tbudget" title="Target Budget (Rs.)" format="{0,number,0,000.00}"
		 sortable="true" />
		<display:column property="priority" title="Priority" sortable="true" />
		<display:column property="status" title="Status" sortable="true" />
		<display:column property="viewPermission" title="View Permission" sortable="true" />
		<display:column title="Gantt Chart Color" sortable="true">
		<logic:equal name="row" property="enable" value="0">
			<html:link title="click for view the Gantt Chart" href="drawGanttChart.do" paramProperty="name" paramId="pname" paramName="row">
			<font style="padding-left:100%;background-color:<%=((ProjectFields)pageContext.getAttribute("row")).getGcolor()%>;">
			</font></html:link>
		</logic:equal>
		<logic:equal name="row" property="enable" value="1">
			<font style="padding-left:100%;background-color:<%=((ProjectFields)pageContext.getAttribute("row")).getGcolor()%>;">
			</font>
		</logic:equal>
		
		</display:column>
		
		<display:column property="darea" maxLength="15" title="Description" sortable="true" />
		
		<display:setProperty name="export.pdf.filename" value="ProjectDetails.pdf"/>
		<display:setProperty name="export.excel.filename" value="ProjectDetails.xls"/>
		<display:setProperty name="export.xml.filename" value="ProjectDetails.xml"/>
		<display:setProperty name="export.csv.filename" value="ProjectDetails.csv"/>
		
	</display:table>
	 
    <logic:empty name="projectList" property="list">
    
    <html:button property="back" value="Back" onclick="history.back();" />
    </logic:empty>
  		
  </body>
</html:html>


