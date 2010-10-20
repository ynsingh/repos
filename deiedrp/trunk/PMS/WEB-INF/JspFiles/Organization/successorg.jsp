<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
  <head>
    <title>search result</title>
 <link rel="stylesheet" href="style/Displaytagex.css" type="text/css"></link>
 <link rel="stylesheet" href="style/dropdown.css" type="text/css"></link>
 <link rel="stylesheet" href="style/style.css" type="text/css"></link>
  </head>
  
  <body>
   <script language="JavaScript" type="text/javascript">
	function fnrec()
	{
		a=document.getElementsByName("nrec");
		window.location.href="orgSearchList.do?key="+a[0].value;
	}
	function sure()
	{
		return(confirm("Are you sure want to Delete?"));
	}
	</script>
   <%!String key=null; %>
 
  <div id="main_title" align="left">
		    <font color="#0044ff"><bean:message key="title.searchResultPage"/>:</font>
		    <font color="#699f00" size="2"><c:out value="${sessionScope.searchOption}"></c:out></font>
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
			<html:errors property="nrec"/><br><br>
	</div>
	 		
 <display:table name="orgSearchList.list" defaultsort="1" id="row" export="false" pagesize="<%=Integer.parseInt(key) %>" requestURI="/orgSearchList.do" decorator="in.ac.dei.edrp.pms.deco.PmsDecorator" class="dataTable" >
		<display:column property="iname" title="Organization Name" sortable="true"/>
		<display:column property="iaddress" title="Organization Address" sortable="true" />
		<display:column property="icity" title="City" sortable="true" />
		<display:column property="istate" title="State" sortable="true" />
		<display:column property="iphoneno" title="Phone No." sortable="true" />
		<display:column property="ifax" title="Fax" sortable="true" />
		<display:column property="iurl" autolink="true" title="URL" sortable="true" />
		 <c:if test="${sessionScope.authority=='Super Admin'}">
		<display:column media="html" title="Actions">
		 <html:link href="editorgpage.do" paramProperty="id" paramId="id" paramName="row">Edit
		  </html:link> <!-- | this link working
		 <html:link href="deleteorg.do" onclick="return sure();" paramProperty="id" paramId="id" paramName="row">Delete
		 </html:link> -->
		</display:column>
			</c:if>
	</display:table>
    <html:form action="/backbutton">
    <br>
  <input type="submit" value='<bean:message key="label.backSearch.button"/>' class="butStnd"/>
  </html:form>
  		
  </body>
</html:html>
