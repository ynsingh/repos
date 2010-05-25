<%@ page import="java.sql.*" language="java" pageEncoding="UTF-8"%>
<%@ page import="in.ac.dei.edrp.pms.dataBaseConnection.MyDataSource"%>
<%@ page import="in.ac.dei.edrp.pms.projmanage.ProjectList"%>
<%@ page import="in.ac.dei.edrp.pms.viewer.checkRecord" %>
<%@ page import="in.ac.dei.edrp.pms.projmanage.ProjectFields;"%>
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
	<!-- You have to include these two JavaScript files -->
        <script type='text/javascript' src='dwr/engine.js'></script>
        <script type='text/javascript' src='dwr/util.js'></script>
	<!-- This JavaScript file is generated specifically for your application -->
         <script type='text/javascript' src='dwr/interface/DynamicList.js'></script>
	
  <script language="JavaScript" type="text/javascript">
  function orgGenerate() {
   var portalname = DWRUtil.getValue("portalname");
   DynamicList.orgPortalList(portalname,function(data)
  {
  DWRUtil.removeAllOptions(document.getElementsByName("orgname")[0]);
  DWRUtil.addOptions(document.getElementsByName("orgname")[0],data);
 fnrec();
  }
  ); 
 }
	function fnrec()
	{
		a=document.getElementsByName("nrec");
		b=document.getElementsByName("portalname");
		c=document.getElementsByName("orgname");
		window.location.href="viewproject.do?key="+a[0].value+"&key1="+b[0].value+"&key2="+c[0].value;
	}
	function rowchange()
	{
	    var table = document.getElementById("row");    
    	var tbody = table.getElementsByTagName("tbody")[0];
    	var rows = tbody.getElementsByTagName("tr");
    // add event handlers so rows light up and are clickable
    	for (i=0; i < rows.length; i++) {
        	var value = rows[i].getElementsByTagName("td")[0].firstChild.nodeValue;
          if (value.indexOf('N') >= 0) {
            rows[i].style.backgroundColor = "#C0C0C0";
            rows[i].style.textDecoration="line-through";
            rows[i].style.color="#FF6347";
        }
    	}
     }
	
	function deleteProject()
	{
	return(confirm("Are you sure want to disable this project?"));
	}
	</script>
	</head>
  <body onload="rowchange();">
    
  <div id="main_title" align="left"><font color="#0044ff">Project List:</font></div><br>
<%! 
	String key=null;
	String key1=null; 
	String key2=null; 
	Connection con=null;
	PreparedStatement ps=null;
	ResultSet rs=null;
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
  	<% 
 		 String portal_id=checkRecord.duplicacyChecker("Portal_ID","portal","Portal_Name",key1);
		 String org_id=checkRecord.duplicacyChecker("Org_ID","organisation","Org_Name",key2);
		 session.setAttribute("validOrgInPortal",checkRecord.twoFieldDuplicacyChecker("valid_org_inportal","org_into_portal","org_id",org_id,"portal_id",portal_id));
   		 request.setAttribute("projectList", new ProjectList(key1,key2));
   	%>	
 <div align="left">
	Portal:
  <html:select style="color:#0044ff;width: 250px;" indexed="portalname" property="portalname" name="portalname" value="<%=key1 %>" onchange="orgGenerate();" >	
    <html:option value="--Select--"></html:option>
			<% 
			try{
			con=MyDataSource.getConnection();
				ps=con.prepareStatement("select portal_name from portal order by portal_name asc");
			rs=ps.executeQuery();
			while(rs.next())
				{
			%>
			<html:option value="<%= rs.getString(1)%>"></html:option>
			<%
			}
			}
			catch(SQLException e){}
			finally{MyDataSource.freeConnection(con);}
			 %>
			</html:select>
	<html:errors property="portalname"/>
	Organization:
  <html:select style="color:#0044ff;width: 250px;" indexed="orgname" property="orgname" name="orgname" value="<%=key2 %>" onchange="fnrec();">	
		<% 
			try{
			con=MyDataSource.getConnection();
				ps=con.prepareStatement("select o.org_name from organisation o,portal p,org_into_portal oip "+
				"where oip.portal_id=p.portal_id and oip.org_id=o.org_id and "+
				"p.portal_name=?");
				ps.setString(1,key1);
			rs=ps.executeQuery();
			while(rs.next())
				{
			%>
			<html:option value="<%= rs.getString(1)%>"></html:option>
			<%
			}
			}
			catch(SQLException e){}
			finally{MyDataSource.freeConnection(con);}
			 %>
   </html:select>
 <html:errors property="orgname"/>
	Number of records to be displayed:
  <html:select property="nrec" name="nrec" value="<%=key %>" onchange="fnrec();">	
    <html:option value="5">5</html:option>
    <html:option value="10">10</html:option>
    <html:option value="15">15</html:option>
    <html:option value="20">20</html:option>
        </html:select>
			<html:errors property="nrec"/>
	</div>

   <logic:notEmpty name="projectList" property="list">
 <display:table name="projectList.list" defaultsort="2" id="row" export="true" pagesize="<%=Integer.parseInt(key) %>" requestURI="/viewproject.do" decorator="in.ac.dei.edrp.pms.deco.PmsDecorator" class="dataTable" >
		<display:column title="Active" sortable="true" >
		<logic:equal name="row" property="enable" value="0">Yes</logic:equal>
		<logic:equal name="row" property="enable" value="1">No</logic:equal>
		</display:column>
		<display:column title="Project Name" sortable="true">
		<html:link title="click for view the Task list" href="viewtask.do" paramProperty="project_name" paramId="key1" paramName="row">
		<%=((ProjectFields)pageContext.getAttribute("row")).getProject_name()%>
		</html:link>
		</display:column>
		<display:column property="scheduleStartDate" title="Plan Start Date" sortable="true" />
		<display:column property="scheduleEndDate" title="Plan End Date" sortable="true" />
		<display:column property="actualStartDate" title="Actual Start Date" sortable="true" />
		<display:column property="actualEndDate" title="Actual End Date" sortable="true" />
		<display:column property="tbudget" title="Target Budget (Rs.)" format="{0,number,0,000.00}"
		 sortable="true" />
		<display:column property="priority" title="Priority" sortable="true" />
		<display:column property="status" title="Status" sortable="true" />
		<display:column title="Gantt Chart Color" sortable="true">
		<logic:equal name="row" property="enable" value="0">
			<html:link title="click for view the Gantt Chart" href="drawGanttChart.do" paramProperty="project_name" paramId="pname" paramName="row">
			view<font style="padding-left:70%;background-color:<%=((ProjectFields)pageContext.getAttribute("row")).getGcolor()%>;"></font>
			</html:link>
		</logic:equal>
		<logic:equal name="row" property="enable" value="1">
			<font style="padding-left:100%;background-color:<%=((ProjectFields)pageContext.getAttribute("row")).getGcolor()%>;"></font>
		</logic:equal>
		</display:column>
		<!-- for view the project members -->
		
		<display:column media="html" title="Project Team">
		<logic:equal name="row" property="enable" value="0">
		<html:link href="viewProjTeam.do" paramProperty="project_code" paramId="key1" paramName="row">
		<img border="0" title="View Team" src="img/users3.png" width="20"  height="10" >
		  </html:link>
		  </logic:equal>
		  <logic:equal name="row" property="enable" value="1">
		  <img border="0" title="View Team" src="img/users3.png" width="20"  height="10" >
		  </logic:equal>
		  </display:column>
	
		<!-- end here -->
		<display:column property="description" maxLength="15" title="Description" sortable="true" />
		<display:column media="html" title="Actions">
		<logic:equal name="row" property="enable" value="0">
		<!--  <html:link href="editProject.do" paramProperty="project_code" paramId="project_code" paramName="row"><img border="0" title="Edit" src="img/write_pen.gif" width="15"  height="10" >
		 	</html:link> |-->
			<html:link href="deleteProject.do" onclick="return deleteProject();" paramProperty="project_code" paramId="project_code" paramName="row">Disable
			 </html:link>  
		</logic:equal>
		<logic:equal name="row" property="enable" value="1">
	 		 <html:link href="deleteProject.do" paramProperty="project_code" paramId="project_code" paramName="row">Enable
		 	</html:link>
		</logic:equal>

		</display:column>
		
		<display:setProperty name="export.pdf.filename" value="ProjectDetails.pdf"/>
		<display:setProperty name="export.excel.filename" value="ProjectDetails.xls"/>
		<display:setProperty name="export.xml.filename" value="ProjectDetails.xml"/>
		<display:setProperty name="export.csv.filename" value="ProjectDetails.csv"/>
		
	</display:table>
	 </logic:notEmpty>
    <logic:empty name="projectList" property="list">
   <br><font color="#550003" size="2">No project will found in desired portal and organization.</font><br><br>
    <html:button property="back" value="Back" onclick="history.back();" />
    </logic:empty>
  		
  </body>
</html:html>


