<%@ page language="java" pageEncoding="UTF-8"%>
<%@page import="in.ac.dei.edrp.pms.organization.OrgFields;"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
  <head>
    <title>showorgdata.jsp</title>
 <link rel="stylesheet" href="style/Displaytagex.css" type="text/css"></link>
 <link rel="stylesheet" href="style/dropdown.css" type="text/css"></link>
 <link rel="stylesheet" href="style/style.css" type="text/css"></link>
  </head>
  <script language="JavaScript" type="text/javascript">
	function fnrec()
	{
		a=document.getElementsByName("nrec");
		window.location.href="orgList.do?key="+a[0].value;
	}
	function sure()
	{
		return(confirm("Are you sure want to Delete?"));
	}
	</script>
  <body>
  
  <%!
	String key=null;
	%>
  
  <logic:notEmpty name="orgList" property="list">
  <div id="main_title" align="left">
		    <font color="#0044ff"><bean:message key="title.organizationList"/>:</font>
		     </div><br>
	
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
		<c:if test="${sessionScope.authority=='Super Admin'}">
	<html:link page="/forwardPmsPage.do?parameter=addOrganization"><bean:message key="label.newOrganization"/><img border="0" title="Add New" src="img/user1_add.png" width="15" "height="15" ></html:link>
		</c:if>
		</div>
	</div>
	 		
 <display:table name="orgList.list" defaultsort="1" id="row" export="true" pagesize="<%=Integer.parseInt(key) %>" requestURI="/orgList.do" decorator="in.ac.dei.edrp.pms.deco.PmsDecorator" class="dataTable" >
				   
		<display:column property="iname" title="Organization Name" sortable="true"/>
		<display:column property="iaddress" title="Organization Address" sortable="true" />
		<display:column property="icity" title="City" sortable="true" />
		<display:column property="istate" title="State" sortable="true" />
		<display:column property="iphoneno" title="Phone No." sortable="true" />
		<display:column property="ifax" title="Fax" sortable="true" />
		<display:column property="iurl" autolink="true" title="URL" sortable="true" />
		<display:column media="html" title="Actions">
		<c:choose>
		<c:when test="${sessionScope.authority=='User'}">
		<%
		if(((OrgFields)pageContext.getAttribute("row")).getIname().equals(session.getAttribute("orgname")))
		{
		%>
		 <html:link href="editorgpage.do" paramProperty="id" paramId="id" paramName="row">Edit
		  </html:link>
		  <%} %>
		 </c:when>
		 <c:otherwise>
		  <html:link href="editorgpage.do" paramProperty="id" paramId="id" paramName="row">Edit
		  </html:link>
		</c:otherwise></c:choose>
		  <!--  | this link working
		 <html:link href="deleteorg.do" onclick="return sure();" paramProperty="id" paramId="id" paramName="row">Delete
		 </html:link> -->
		</display:column>
		<display:setProperty name="export.pdf.filename" value="OrgList.pdf"/>
		<display:setProperty name="export.excel.filename" value="OrgList.xls"/>
		<display:setProperty name="export.xml.filename" value="OrgList.xml"/>
		<display:setProperty name="export.csv.filename" value="OrgList.csv"/>
		</display:table>
   </logic:notEmpty>
  	<logic:empty name="orgList" property="list">
       <br><font color="#550003" size="2"><bean:message key="label.orgRecordsNotFound"/>--></font>
       <c:if test="${sessionScope.authority=='Super Admin'}">
	<html:link action="neworganization"><bean:message key="label.newOrganization"/><img border="0" title="Add New" src="img/user1_add.png" width="15" "height="15" ></html:link>
		</c:if>
       <br><br>
       <input type="button" value='<bean:message key="label.back.button" />' class="butStnd" onclick="history.back();" />
    </logic:empty>
  		
  </body>
</html:html>



