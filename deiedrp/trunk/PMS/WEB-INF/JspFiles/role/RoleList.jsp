<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page import="in.ac.dei.edrp.pms.role.RoleFields;"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
  <head>
     	
	<link rel="stylesheet" href="style/Displaytagex.css" type="text/css"></link>
	<link rel="stylesheet" href="style/dropdown.css" type="text/css"></link>
	<link rel="stylesheet" href="style/style.css" type="text/css"></link>
  </head>
  <script language="JavaScript" type="text/javascript">
	function fnrec()
	{
		a=document.getElementsByName("nrec");
		window.location.href="roleList.do?key="+a[0].value;
	}
	function sure()
	{
		return(confirm("Are you sure want to delete this Role?"));
	}
	</script>
	
  <body>
  
  <logic:notEmpty name="roleList" property="list">
  <div id="main_title" align="left">
		    <font color="#0044ff"><bean:message key="title.roleList"/>:</font></div><br>
<%!  String key=null;%>
<%
	 key=request.getParameter("key");
	 if(key==null)
	 key="10";
  %>
 <div align="left">
	<bean:message key="title.numberOfRecords"/>:
  <html:select property="nrec" name="nrec" value="<%=key %>" onchange="fnrec();">	
    <html:option value="5" >5</html:option>
    <html:option value="10" >10</html:option>
    <html:option value="15" >15</html:option>
    <html:option value="20" >20</html:option>
    <html:option value="25" >25</html:option>
        </html:select>
			<html:errors property="nrec"/>
			<div align="right">
	<html:link page="/forwardPmsPage.do?parameter=newRole"><bean:message key="label.newRole"/><img border="0" title="Edit" src="img/user1_add.png" width="15" "height="15" ></html:link>
			</div>
	</div>
  
 <display:table name="roleList.list" defaultsort="1"  id="row" export="true" pagesize="<%=Integer.parseInt(key) %>" requestURI="/roleList.do" class="dataTable" >
		<display:column title="Role Name" sortable="true">
		<html:link title="click for view the Role detail" href="roleDetail.do" paramProperty="roleid" paramId="rolekey" paramName="row">
		<%=((RoleFields)pageContext.getAttribute("row")).getRolename()%>
		</html:link>
		</display:column>
		<display:column property="roledescription" title="Role Description" sortable="true" />
		<display:column property="createdby" title="Created By" sortable="true" />
		<display:column property="createdon" title="Created On" sortable="true" />
		<display:column property="updatedon" title="Updated On" sortable="true" />
		
		<logic:equal name="row" property="authority" value="Super Admin">
		<display:column media="html" title="Actions">
		<html:link href="editRole.do" paramProperty="roleid" paramId="rolekey" paramName="row">
		<img border="0" title="Edit" src="img/write_pen.gif" width="15"  height="10" >
		  </html:link> <!-- | delete role link working
		 <html:link href="deleteRole.do" onclick="return sure();" paramProperty="roleid" paramId="rolekey" paramName="row">Remove
		 </html:link> -->
		 </display:column>
		 </logic:equal>
		 <logic:equal name="row" property="editauthority" value="Allow">
		 <display:column media="html" title="Actions">
		<html:link href="editRole.do" paramProperty="roleid" paramId="rolekey" paramName="row">
		<img border="0" title="Edit" src="img/write_pen.gif" width="15"  height="10" >
		  </html:link> <!--   | delete role link working
		 <html:link href="deleteRole.do" onclick="return sure();" paramProperty="roleid" paramId="rolekey" paramName="row">Remove
		 </html:link>-->
		</display:column>
		</logic:equal>
				
		<display:setProperty name="export.pdf.filename" value="RoleList.pdf"/>
		<display:setProperty name="export.excel.filename" value="RoleList.xls"/>
		<display:setProperty name="export.xml.filename" value="RoleList.xml"/>
		<display:setProperty name="export.csv.filename" value="RoleList.csv"/>
		
	</display:table>
	 </logic:notEmpty>
    <logic:empty name="roleList" property="list">
   <br><font color="#550003" size="2"><bean:message key="label.roleRecordsNotFound"/>--></font>
   <html:link action="newrole"><bean:message key="label.newRole"/><img border="0" title="Edit" src="img/user1_add.png" width="15" "height="15" ></html:link>
   <br><br>
    <input type="button" value='<bean:message key="label.back.button" />' class="butStnd" onclick="history.back();" />
    </logic:empty>
  		
  </body>
</html:html>


