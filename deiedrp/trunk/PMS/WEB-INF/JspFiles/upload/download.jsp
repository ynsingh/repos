<%@ page language="java"  pageEncoding="UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
  <head>
    <link rel="stylesheet" href="style/main.css" type="text/css"></link>
	<link rel="stylesheet" href="style/Displaytagex.css" type="text/css"></link>
    <script type="text/javascript">
	function sure()
	{
		return(confirm("Are you sure want to Delete?"));
	}
	</script>
	
   </head>
   <body>
    <logic:notEmpty name="fileDownloadList" property="list">
    <div id=main_title><font color="#0044ff">List of Files:</font></div><br>
    </logic:notEmpty>
    <logic:empty name="fileDownloadList" property="list"><br>
    <font color="#0000ff" size="2">You don't have any file to download/remove.</font>
    <br><br>
    <html:button property="back" value="Back" onclick="history.back();" />
    </logic:empty>
   <logic:notEmpty name="fileDownloadList" property="list">
    
  
<display:table name="fileDownloadList.list" export="false" pagesize="5" requestURI="/fileDownloadList.do" decorator="org.dei.edrp.pms.deco.PmsDecorator" class="dataTable">

			<display:column title="Actions" property="link2"/>
					
			<display:column  property="fileName" title="File Name" sortable="true"/>
			<display:column property="projectName" title="Project Name" sortable="true" />
			<display:column property="owner" title="Owner" sortable="true" />
			<display:column property="fdescription" title="Description" sortable="true" />
			<display:column property="size" title="Size in(KB)" sortable="true" />
			</display:table>
			</logic:notEmpty>
    </body>
</html:html>
