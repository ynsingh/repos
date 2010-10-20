<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%> 
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

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
   	
	<script type="text/javascript">
 	function fnrec()
	{
		a=document.getElementsByName("projselect");
		window.location.href="drawGanttChart.do?pname="+a[0].value;
	}
	</script>
	
  <c:choose> 
  <c:when test="${sessionScope.authority == 'Super Admin'}" > 
   <!-- when login person is Super Admin -->
			<div id=main_title><font color="#0044ff">
			<bean:message key="title.ganttChart"/> :
			</font>
			<c:out value="${param.pname}"></c:out>
			</div><br>
	</c:when> 
  <c:otherwise> 
   <!-- when login person is User -->
  <div id=main_title><font color="#0044ff"><bean:message key="title.ganttChart"/> :</font></div><br>
  <div align="left">
	<bean:message key="label.selectProject"/>:
  <html:select style="color:#0044ff;width: 250px;"
   indexed="projselect" property="projselect" name="projselect" 
   value="${param.pname}" onchange="fnrec()">	
		<html:option value="--Select--"></html:option>
		<sql:query var="project" dataSource="jdbc/mydb">
				select distinct p.project_name from project p,
					user_in_org u,validatetab v where p.enable=0 and 
					u.valid_user_id=? and u.valid_orgportal=? 
					and u.valid_key=v.valid_user_key and v.valid_project_code=p.project_code
					 and v.valid_role_id=?
					 <sql:param value="${sessionScope.uid}"/>
					 <sql:param value="${sessionScope.validOrgInPortal}"/>
				<sql:param value="${sessionScope.roleid}"/>
			</sql:query>
			<c:forEach var="row" items="${project.rows}">
			<html:option value="${row.project_name}"></html:option>
			</c:forEach>
			</html:select><html:errors property="projselect"/> 
   </div>
   </c:otherwise></c:choose>
   
  
    <c:choose> 
  <c:when test="${param.pname != null}" > 
   <div style="position:relative" class="gantt" id="GanttChartDIV"></div>
	<script type="text/javascript">
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
	</c:when> 
  <c:otherwise>
 <br>
 <h2><font size="2" color="#0000ff"><bean:message key="label.message"/></font></h2>
  <br>
 </c:otherwise></c:choose>
<br>
<html:button property="back" value="Back" styleClass="butStnd" onclick="history.back();" />
 </body>
</html>
