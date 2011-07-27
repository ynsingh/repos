<%@ page language="java" pageEncoding="UTF-8"%>
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
		window.location.href="portalList.do?key="+a[0].value;
	}
	function sure()
	{
		return(confirm("Are you sure want to delete this Portal?"));
	}
	</script>
	
  <body>
  
  <logic:notEmpty name="portalList" property="list">
  <div id="main_title" align="left">
		    <font color="#0044ff"><bean:message key="title.viewPortalPage"/> :</font></div><br>
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
			<html:link page="/forwardPmsPage.do?parameter=newPortal"><bean:message key="link.newPortal"/><img border="0" title="Edit" src="img/user1_add.png" width="15" "height="15" ></html:link>
	</div>
	</div>
  
 <display:table name="portalList.list" defaultsort="1"  id="row" export="true" pagesize="<%=Integer.parseInt(key) %>" requestURI="/portalList.do" class="dataTable" >
		<display:column property="portalname" sortable="true">
		</display:column>
		<display:column property="portaldescription"  sortable="true" />
		<display:column property="createdby"  sortable="true" />
		<display:column property="createdon" sortable="true" />
		
		<display:column media="html" title="Actions">
		<html:link href="editPortal.do" paramProperty="portalid" paramId="portalkey" paramName="row">
		<img border="0" title="Edit" src="img/write_pen.gif" width="15"  height="10" >
		  </html:link><!--  | this link working
		 <html:link href="deletePortal.do" onclick="return sure();" paramProperty="portalid" paramId="portalkey" paramName="row">Remove
		 </html:link> -->
		</display:column>
		<display:setProperty name="export.pdf.filename" value="PortalList.pdf"/>
		<display:setProperty name="export.excel.filename" value="PortalList.xls"/>
		<display:setProperty name="export.xml.filename" value="PortalList.xml"/>
		<display:setProperty name="export.csv.filename" value="PortalList.csv"/>
		
	</display:table>
	 </logic:notEmpty>
    <logic:empty name="portalList" property="list">
   
   <br><font color="#550003" size="2"><bean:message key="title.portalRecordsNotFound"/>--></font>
    		<html:link action="newportal"><bean:message key="link.newPortal"/><img border="0" title="Edit" src="img/user1_add.png" width="15" "height="15" ></html:link>
	   <br><br>
    <input type="button" value='<bean:message key="label.back.button" />' class="butStnd" onclick="history.back();" />
    </logic:empty>
  		
  </body>
</html:html>


