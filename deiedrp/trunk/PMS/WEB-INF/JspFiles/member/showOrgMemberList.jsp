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
		window.location.href="forwardPmsPage.do?parameter=viewMember&key="+a[0].value+"&key1="+b[0].value;
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
  <%!
  	String key=null;
	String key1=null; %>
	  
	<%
	 key=request.getParameter("key");
	 if(key==null)
	 key="20";
	 key1=request.getParameter("key1");
	 if(key1==null)
	 key1="All";
	 %>
  <% request.setAttribute("memberList", new OrgMemberList(key1,
  (String)session.getAttribute("validOrgInPortal"),(String)session.getAttribute("uid"),
  (String)session.getAttribute("roleid"))); %>
     
  <div id="main_title" align="left">
		    <font color="#0044ff"><bean:message key="title.showOrgMemberListPage"/>:</font>
		     </div><br>
	
  	 <div align="left">
	<bean:message key="label.typeOfUser"/>:
  <html:select style="color:#0044ff" property="nrec1" name="nrec1" value="<%=key1 %>" onchange="fnrec();">	
    <html:option value="All" >All</html:option>
    <html:option value="Active" >Active</html:option>
    <html:option value="InActive" >Inactive</html:option>
    </html:select>
	<html:errors property="nrec1"/>
	
	<bean:message key="title.numberOfRecords"/>:
  <html:select style="color:#0044ff" property="nrec" name="nrec" value="<%=key %>" onchange="fnrec();">	
    <html:option value="5" >5</html:option>
    <html:option value="10" >10</html:option>
    <html:option value="15" >15</html:option>
    <html:option value="20" >20</html:option>
    <html:option value="25" >25</html:option>
   </html:select>
	<html:errors property="nrec"/>
	<div align="right">
	<%
	if(key1.equalsIgnoreCase("inactive"))
	{
	 %>
	
	<html:link page="/forwardPmsPage.do?parameter=addMember"><bean:message key="label.newMember"/><img border="0" title="Add new member" src="img/user1_add.png" width="15" "height="15" ></html:link>
	 <%}
	 else
	 {
	  %>
	  <html:link action="assignproject"><bean:message key="label.newMember"/><img border="0" title="Add new member" src="img/user1_add.png" width="15" "height="15" ></html:link>
	 <%} %>
	 </div>
	</div>
	<logic:notEmpty name="memberList" property="list">
	<%if(key1.equalsIgnoreCase("Active"))
	{
	new PmsDecorator().setUser((String)session.getAttribute("uid"));
	%>
 
 <display:table name="memberList.list" id="row" defaultsort="3" export="false" pagesize="<%=Integer.parseInt(key) %>" requestURI="/forwardPmsPage.do?parameter=viewMember" decorator="in.ac.dei.edrp.pms.deco.PmsDecorator" class="dataTable" >
				   
		<display:column property="userid" group="1" title="User ID" sortable="true" />
		<display:column property="rolename" title="Role Name" sortable="true" />
		<display:column property="orgname" title="Project Name" sortable="true" />
		<display:column property="portalname" title="Permitted By" sortable="true" />
		<logic:equal name="row" property="editPermission" value="Allow">
		<display:column property="activeMemberLink" media="html" title="Actions"/>
			</logic:equal>
</display:table>
    <%}
    else if(key1.equalsIgnoreCase("InActive"))
    {
     %>
<display:table name="memberList.list" id="row" defaultsort="1" export="false" pagesize="<%=Integer.parseInt(key) %>" requestURI="/forwardPmsPage.do?parameter=viewMember" decorator="in.ac.dei.edrp.pms.deco.PmsDecorator" class="dataTable" >
				   
		<display:column property="addUserIntoProject" group="1" title="User ID" sortable="true" />
		<display:column property="portalname" title="First Name" sortable="true" />
		<display:column property="orgname" title="Last Name" sortable="true" />
		<display:column property="rolename" title="Skills" sortable="true" />
		<display:column property="valid_key" title="Experience (in years)" sortable="true" />
		<logic:equal name="row" property="editPermission" value="Allow">
		<display:column media="html" title="Actions">
		<%
		if(new OrgMemberList().checkUserId((String)session.getAttribute("validOrgInPortal"),((MemberBean)pageContext.getAttribute("row")).getUserid())==true)
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
<%}else 
    {
     %>
<display:table name="memberList.list" id="row" defaultsort="6" export="false" pagesize="<%=Integer.parseInt(key) %>" requestURI="/forwardPmsPage.do?parameter=viewMember" decorator="in.ac.dei.edrp.pms.deco.PmsDecorator" class="dataTable" >
				   
		<display:column property="addUserIntoProject" title="User ID" sortable="true" />
		<display:column property="portalname" title="First Name" sortable="true" />
		<display:column property="orgname" title="Last Name" sortable="true" />
		<display:column property="rolename" title="Skills" sortable="true" />
		<display:column property="valid_key" title="Experience (in years)" sortable="true" />
		<display:column property="status" title="Status" sortable="true"/>
		<logic:equal name="row" property="editPermission" value="Allow">
		<display:column media="html" title="Actions">
		<%
		if(new OrgMemberList().checkUserId((String)session.getAttribute("validOrgInPortal"),((MemberBean)pageContext.getAttribute("row")).getUserid())==true)
		{
		if(!((String)session.getAttribute("uid")).equals(((MemberBean)pageContext.getAttribute("row")).getUserid()))
		{
		 %>
		<html:link href="deleteMember.do" onclick="return deleteUser()" paramProperty="userid" paramId="userid" paramName="row">Delete
		  </html:link>
		  <%}} %>
		</display:column>
		</logic:equal>
		<logic:equal name="row" property="editPasswordPermission" value="Allow">
		<display:column media="html" title="Password">
		<html:link href="resetUserpassword.do" title="click for reset the user password" paramProperty="userid" paramId="userid" paramName="row">Reset
		  </html:link>
		</display:column>
		</logic:equal>
</display:table>
<%} %>
    </logic:notEmpty>
    
     <logic:empty name="memberList" property="list">
     <br><font color="#550003" size="2"><bean:message key="label.memberNotExist"/></font><br><br>
     <input type="button" value='<bean:message key="label.back.button" />' class="butStnd" onclick="history.back();" />
    </logic:empty>
 
  </body>
</html:html>
