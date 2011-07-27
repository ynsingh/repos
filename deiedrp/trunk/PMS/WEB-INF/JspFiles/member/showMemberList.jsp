<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page import="in.ac.dei.edrp.pms.member.MemberList"%>
<%@ page import="in.ac.dei.edrp.pms.member.MemberBean"%>
<%@page import="in.ac.dei.edrp.pms.deco.PmsDecorator;"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
  <head>
    <title>showMemberList.jsp</title>
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
		return(confirm("Do you want to delete this user permanently?"));
	}
	function deleteUserRole()
	{
	return(confirm("Are you sure to remove this user from the selected role from the selected portal and organisation?"));
	}
	</script>
  <body>
  <%! 
  	String key=null;
	String key1=null; %>
	  
	<%
	 key=request.getParameter("key");
	 if(key==null)
	 key="10";
	 key1=request.getParameter("key1");
	 if(key1==null)
	 key1="All";
	 
  %>
  <% request.setAttribute("memberList", new MemberList(key1)); %>
  
  <div id="main_title" align="left">
		    <font color="#0044ff"><bean:message key="title.showMemberListPage"/>:</font>
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
	if(key1.equalsIgnoreCase("Active"))
	{
	 %>
	 <html:link page="/forwardPmsPage.do?parameter=addOrgPortal"><bean:message key="label.newMember"/><img border="0" title="Edit" src="img/user1_add.png" width="15" "height="15" ></html:link>
	 <%}
	 else
	 {
	  %>
	 <html:link page="/forwardPmsPage.do?parameter=addMember"><bean:message key="label.newMember"/><img border="0" title="Edit" src="img/user1_add.png" width="15" "height="15" ></html:link> 
	 <%} %>
	 </div>
	</div>
	
	 <logic:notEmpty name="memberList" property="list">
	<%if(key1.equalsIgnoreCase("Active"))
	{
	new PmsDecorator().setUser("");
	%>
 
 <display:table name="memberList.list" id="row" defaultsort="1" export="false" pagesize="<%=Integer.parseInt(key) %>" requestURI="/forwardPmsPage.do?parameter=viewMember" decorator="in.ac.dei.edrp.pms.deco.PmsDecorator" class="dataTable" >
				   
		<display:column property="userid" group="1" title="User ID" sortable="true" />
		<display:column property="portalname" title="Portal Name" sortable="true" />
		<display:column property="orgname" title="Organization Name" sortable="true" />
		<display:column title="Role Name" sortable="true">
		<html:link title="click for view the Role detail" href="roleDetail.do" paramProperty="editPermission" paramId="rolekey" paramName="row">
		<%=((MemberBean)pageContext.getAttribute("row")).getRolename()%>
		</html:link>
		</display:column>
		<display:column property="activeMemberLink" media="html" title="Actions"/>
</display:table>
    <%}
    else if(key1.equalsIgnoreCase("InActive"))
    {
     %>
<display:table name="memberList.list" id="row" defaultsort="1" export="false" pagesize="<%=Integer.parseInt(key) %>" requestURI="/forwardPmsPage.do?parameter=viewMember" decorator="in.ac.dei.edrp.pms.deco.PmsDecorator" class="dataTable" >
				   
		<display:column property="userIdLink" group="1" title="User ID" sortable="true" />
		<display:column property="portalname" title="First Name" sortable="true" />
		<display:column property="orgname" title="Last Name" sortable="true" />
		<display:column property="rolename" title="Skills" sortable="true" />
		<display:column property="valid_key" title="Experience (in years)" sortable="true" />
		<display:column property="inActiveMemberLink" media="html" title="Actions"/>
</display:table>
<%}
else 
    {
     %>
<display:table name="memberList.list" id="row" defaultsort="1" export="false" pagesize="<%=Integer.parseInt(key) %>" requestURI="/forwardPmsPage.do?parameter=viewMember" decorator="in.ac.dei.edrp.pms.deco.PmsDecorator" class="dataTable" >
				   
		<display:column property="userIdLink" group="1" title="User ID" sortable="true" />
		<display:column property="portalname" title="First Name" sortable="true" />
		<display:column property="orgname" title="Last Name" sortable="true" />
		<display:column property="rolename" title="Skills" sortable="true" />
		<display:column property="valid_key" title="Experince (in years)" sortable="true" />
		<display:column property="status" title="Status" sortable="true"/>
		<display:column media="html" title="Password">
		<%
		if(((MemberBean)pageContext.getAttribute("row")).getStatus().equals("Active"))
		{
		 %>
		<html:link href="resetUserpassword.do" paramProperty="userid" paramId="userid" paramName="row">Reset
		  </html:link>
		  <%} %>
		</display:column>
</display:table>
<%} %>
   </logic:notEmpty>
    
     <logic:empty name="memberList" property="list">
     <br><font color="#550003" size="2"><bean:message key="label.noRecords"/></font><br><br>
     <input type="button" value='<bean:message key="label.back.button" />' class="butStnd" onclick="history.back();" />
    </logic:empty>
 
  </body>
</html:html>
