<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
  <head>
    <title>showMemberList.jsp</title>
  <link rel="stylesheet" href="style/main.css" type="text/css"></link>
<link rel="stylesheet" href="style/style.css" type="text/css"></link>
<link rel="stylesheet" href="style/Displaytagex.css" type="text/css"></link>
  </head>
  <script language="JavaScript" type="text/javascript">
	function fnrec()
	{
		a=document.getElementsByName("nrec");
		window.location.href="searchaction.do?key="+a[0].value;
	}
	</script>
  <body>
  
  <div id="main_title" align="left">
		    <font color="#0044ff"><bean:message key="title.searchMemberList"/>:</font>
		     </div><br>
	<%!String key=null; %>
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
			<html:errors property="nrec"/><br><br>
	</div>
 <display:table name="memberList.list" defaultsort="1" export="false" pagesize="<%=Integer.parseInt(key) %>" requestURI="/searchaction.do" decorator="in.ac.dei.edrp.pms.deco.PmsDecorator" class="dataTable" >
				   
		<display:column property="userid" group="1" title="User ID" sortable="true" />
		<display:column property="portalname" title="Portal Name" sortable="true" />
		<display:column property="orgname" title="Organisation Name" sortable="true" />
		<display:column property="rolename" title="Role Name" sortable="true" />
	</display:table>

    <html:form action="/backuserSearchPage">
    <br>
  <input type="submit" value='<bean:message key="label.backSearch.button"/>' class="butStnd"/>
  </html:form>
  </body>
</html:html>
