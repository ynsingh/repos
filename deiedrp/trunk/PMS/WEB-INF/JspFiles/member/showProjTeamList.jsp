<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page import="in.ac.dei.edrp.pms.member.OrgMemberList"%>
<%@ page import="in.ac.dei.edrp.pms.member.MemberBean"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
  <head>
    <title>showProjTeamList.jsp</title>
  <link rel="stylesheet" href="style/Displaytagex.css" type="text/css"></link>
  <link rel="stylesheet" href="style/dropdown.css" type="text/css"></link>
  </head>
    
  <script language="JavaScript" type="text/javascript">
	function fnrec()
	{
		a=document.getElementsByName("nrec");
		b=document.getElementById("proj");
		window.location.href="viewProjTeam.do?key="+a[0].value+"&key1="+b.value;
	}
	function deleteUser()
	{
		return(confirm("Are you sure want to delete this user permanently?"));
	}
	function deleteUserRole()
	{
	return(confirm("Are you sure to remove this user from the selected role from the selected portal and organisation?"));
	}
	</script>
  <body>
  <%! String mysession=null;
  	String key=null;
  	String key1=null;
	 %>
	<%
		//new CustomRequestProcessor().processNoCache(request, response);
		mysession=(String)session.getAttribute("mysession");
		if(mysession==null)
		{
			response.sendRedirect("login.jsp");  
		}
	else{
	%>
  
	<%
	 key=request.getParameter("key");
	 if(key==null)
	 key="10";
	 key1=request.getParameter("key1");
	
	 
  %>
  <% request.setAttribute("projectTeam", new OrgMemberList(key1)); %>
  
  <div id="main_title" align="left">
		    <font color="#0044ff">Project Team :</font>
		     </div><br>
	
  	 <div align="left">
	<input type="hidden" id="proj" name="proj" value="<%=key1 %>">
	Number of records to be displayed:
  <html:select style="color:#0044ff" property="nrec" name="nrec" value="<%=key %>" onchange="fnrec();">	
    <html:option value="5" >5</html:option>
    <html:option value="10" >10</html:option>
    <html:option value="15" >15</html:option>
    <html:option value="20" >20</html:option>
   </html:select>
	<html:errors property="nrec"/>
	<div align="right">
	<%
	if(((String)session.getAttribute("authority")).equalsIgnoreCase("User"))//the authority is user or super admin
		{
	 %>
	  <html:link action="assignproject">New Member<img border="0" title="Edit" src="img/user1_add.png" width="15" "height="15" ></html:link>
	 <%} %>
	 </div>
	</div>
	
	 <logic:notEmpty name="projectTeam" property="list">
	 
 <display:table name="projectTeam.list" id="row" defaultsort="1" export="false" pagesize="<%=Integer.parseInt(key) %>" requestURI="/viewProjTeam.do" decorator="in.ac.dei.edrp.pms.deco.PmsDecorator" class="dataTable" >
				   
		<display:column property="userid" group="1" title="User ID" sortable="true" />
		<display:column property="portalname" title="Permitted By" sortable="true" />
		<display:column property="orgname" title="Project" sortable="true" />
		<display:column title="Role Name" sortable="true">
		<html:link title="click for view the Role detail" href="roleDetail.do" paramProperty="valid_key" paramId="rolekey" paramName="row">
		<%=((MemberBean)pageContext.getAttribute("row")).getRolename()%>
		</html:link>
		</display:column>
				
</display:table>
   
    </logic:notEmpty>
    
     <logic:empty name="projectTeam" property="list">
     <br><font color="#550003" size="2">No member exist on this project.</font><br><br>
     <html:button property="back" value="Back" onclick="history.back();" />
    </logic:empty>
  		<%} %>
  </body>
</html:html>
