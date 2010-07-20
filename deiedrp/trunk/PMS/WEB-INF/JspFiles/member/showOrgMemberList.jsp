<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page import="in.ac.dei.edrp.pms.member.OrgMemberList"%>
<%@ page import="in.ac.dei.edrp.pms.member.MemberBean"%>
<%@page import="in.ac.dei.edrp.pms.deco.PmsDecorator;"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
  <head>
    <title>showOrgMemberList.jsp</title>
  <link rel="stylesheet" href="style/Displaytagex.css" type="text/css"></link>
  <link rel="stylesheet" href="style/dropdown.css" type="text/css"></link>
  <link rel="stylesheet" href="style/style.css" type="text/css"></link>
  </head>
    
  <script language="JavaScript" type="text/javascript">
	function fnrec()
	{
		a=document.getElementsByName("nrec");
		b=document.getElementsByName("nrec1");
		window.location.href="viewmember.do?key="+a[0].value+"&key1="+b[0].value;
	}
	function deleteUser()
	{
		return(confirm("Are you sure to remove this user permanently from this portal and organization?"));
	}
	function deleteUserRole()
	{
	return(confirm("Are you sure to remove this user from the selected project?"));
	}
	</script>
  <body>
  <%! String mysession=null;
  	String key=null;
	String key1=null; %>
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
	 if(key1==null)
	 key1="InActive";
	 
  %>
  <% request.setAttribute("memberList", new OrgMemberList(key1,
  (String)session.getAttribute("validOrgInPortal"),(String)session.getAttribute("uid"),
  (String)session.getAttribute("role_in_org"))); %>
     
  <div id="main_title" align="left">
		    <font color="#0044ff">Organization Member List:</font>
		     </div><br>
	
  	 <div align="left">
	Users to be displayed:
  <html:select style="color:#0044ff" property="nrec1" name="nrec1" value="<%=key1 %>" onchange="fnrec();">	
    <html:option value="Active" >Active</html:option>
    <html:option value="InActive" >Inactive</html:option>
    </html:select>
	<html:errors property="nrec1"/>
	
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
	if(key1.equalsIgnoreCase("inactive"))
	{
	 %>
	
	<html:link action="addmember">New Member<img border="0" title="Add new member" src="img/user1_add.png" width="15" "height="15" ></html:link>
	 <%}
	 else
	 {
	  %>
	  <html:link action="assignproject">New Member<img border="0" title="Add new member" src="img/user1_add.png" width="15" "height="15" ></html:link>
	 <%} %>
	 </div>
	</div>
	<logic:notEmpty name="memberList" property="list">
	<%if(key1.equalsIgnoreCase("Active"))
	{
	new PmsDecorator().setUser((String)session.getAttribute("uid"));
	%>
 
 <display:table name="memberList.list" id="row" defaultsort="3" export="false" pagesize="<%=Integer.parseInt(key) %>" requestURI="/viewmember.do" decorator="in.ac.dei.edrp.pms.deco.PmsDecorator" class="dataTable" >
				   
		<display:column property="userid" group="1" title="User ID" sortable="true" />
		<display:column property="portalname" title="Permitted By" sortable="true" />
		<display:column property="orgname" title="Project Name" sortable="true" />
		<display:column property="rolename" title="Role Name" sortable="true" />
		<logic:equal name="row" property="editPermission" value="Allow">
		<display:column property="activeMemberLink" media="html" title="Actions"/>
			</logic:equal>
</display:table>
    <%}
    else
    {
     %>
<display:table name="memberList.list" id="row" defaultsort="1" export="false" pagesize="<%=Integer.parseInt(key) %>" requestURI="/viewmember.do" decorator="in.ac.dei.edrp.pms.deco.PmsDecorator" class="dataTable" >
				   
		<display:column property="addUserIntoProject" group="1" title="User ID" sortable="true" />
		<display:column property="portalname" title="First Name" sortable="true" />
		<display:column property="orgname" title="Last Name" sortable="true" />
		<display:column property="rolename" title="Skills" sortable="true" />
		<display:column property="valid_key" title="Experince (in years)" sortable="true" />
		<logic:equal name="row" property="editPermission" value="Allow">
		<display:column media="html" title="Actions">
		<%
		if(!new OrgMemberList().checkUserId((String)session.getAttribute("validOrgInPortal"),((MemberBean)pageContext.getAttribute("row")).getUserid()))
		{
		if(!((String)session.getAttribute("uid")).equals(((MemberBean)pageContext.getAttribute("row")).getUserid()))
		{
		 %>
		<html:link href="deleteMember.do" onclick="return deleteUser()" paramProperty="userid" paramId="userid" paramName="row">Delete
		  </html:link>
		  <%}} %>
		</display:column>
		</logic:equal>
</display:table>
<%} %>
    </logic:notEmpty>
    
     <logic:empty name="memberList" property="list">
     <br><font color="#550003" size="2">This type of member doesn't exist in the organization.</font><br><br>
     <html:button property="back" value="Back" styleClass="butStnd" onclick="history.back();" />
    </logic:empty>
  		<%} %>
  </body>
</html:html>
