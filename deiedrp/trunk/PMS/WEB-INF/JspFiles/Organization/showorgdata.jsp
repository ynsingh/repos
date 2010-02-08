<%@ page language="java" pageEncoding="UTF-8"%>

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
  <head>
    <title>showorgdata.jsp</title>
 <link rel="stylesheet" href="style/main.css" type="text/css"></link>
	
<link rel="stylesheet" href="style/Displaytagex.css" type="text/css"></link>
  </head>
  <script language="JavaScript" type="text/javascript">
	function fnrec()
	{
		a=document.getElementsByName("nrec");
		window.location.href="orgList.do?key="+a[0].value;
	}
	</script>
  <body>
  
  <logic:notEmpty name="orgList" property="list">
  <div id="main_title" align="left">
		    <font color="#0044ff">Organisation List:</font>
		     </div><br>
	<%!String key=null; %>
	<%
	 key=request.getParameter("key");
	 if(key==null)
	 key="10";
  %>
	<div align="left">
	Number of records to be displayed:
  <html:select property="nrec" name="nrec" value="<%=key %>" onchange="fnrec();">	
    <html:option value="5" >5</html:option>
    <html:option value="10" >10</html:option>
    <html:option value="15" >15</html:option>
    <html:option value="20" >20</html:option>
        </html:select>
			<html:errors property="nrec"/><br><br>
	</div>

	 </logic:notEmpty>		
 <display:table name="orgList.list" defaultsort="1" export="false" pagesize="<%=Integer.parseInt(key) %>" requestURI="/orgList.do" decorator="deco.PmsDecorator" class="dataTable" >
				   
		<display:column property="iname" title="Organisation Name" sortable="true"/>
		<display:column property="iaddress" title="Organisation Address" sortable="true" />
		<display:column property="icity" title="City" sortable="true" />
		<display:column property="istate" title="State" sortable="true" />
		<display:column property="ipin" title="Pin Code" sortable="true" />
		<display:column property="iphoneno" title="Phone No." sortable="true" />
		<display:column property="ifax" title="Fax" sortable="true" />
		<display:column property="iurl" autolink="true" maxLength="15" title="URL" sortable="true" />
		<display:column property="ihead" title="Head Name" sortable="true" />
		<display:column property="emailId" title="Head Email_ID" sortable="true"/>
		<display:column property="description" maxLength="10" title="Description" sortable="true" />
		
	</display:table>
   
  	<logic:empty name="orgList" property="list">
       <html:button property="back" value="Back" onclick="history.back();" />
    </logic:empty>
  		
  </body>
</html:html>



