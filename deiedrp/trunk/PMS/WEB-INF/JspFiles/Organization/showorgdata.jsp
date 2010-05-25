<%@ page language="java" pageEncoding="UTF-8"%>

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
  <head>
    <title>showorgdata.jsp</title>
 <link rel="stylesheet" href="style/Displaytagex.css" type="text/css"></link>
 <link rel="stylesheet" href="style/dropdown.css" type="text/css"></link>
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
  
  <logic:notEmpty name="orgList" property="list">
  <div id="main_title" align="left">
		    <font color="#0044ff">Organization List:</font>
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
			<html:errors property="nrec"/>
		<div align="right">
	<html:link action="neworganization">New Organization<img border="0" title="Add New" src="img/user1_add.png" width="15" "height="15" ></html:link>
		</div>
	</div>
	 		
 <display:table name="orgList.list" defaultsort="1" id="row" export="false" pagesize="<%=Integer.parseInt(key) %>" requestURI="/orgList.do" decorator="in.ac.dei.edrp.pms.deco.PmsDecorator" class="dataTable" >
				   
		<display:column property="iname" title="Organisation Name" sortable="true"/>
		<display:column property="iaddress" title="Organisation Address" sortable="true" />
		<display:column property="icity" title="City" sortable="true" />
		<display:column property="istate" title="State" sortable="true" />
		<display:column property="iphoneno" title="Phone No." sortable="true" />
		<display:column property="ifax" title="Fax" sortable="true" />
		<display:column property="iurl" autolink="true" title="URL" sortable="true" />
		<display:column media="html" title="Actions">
		 <html:link href="editorgpage.do" paramProperty="id" paramId="id" paramName="row">Edit
		  </html:link><!--  | this link working
		 <html:link href="deleteorg.do" onclick="return sure();" paramProperty="id" paramId="id" paramName="row">Delete
		 </html:link> -->
		</display:column>
		</display:table>
   </logic:notEmpty>
  	<logic:empty name="orgList" property="list">
       <br><font color="#550003" size="2">Nothing found to display.foe adding new organization click on this link--></font>
       <html:link action="neworganization">New Organization<img border="0" title="Add New" src="img/user1_add.png" width="15" "height="15" ></html:link>
       <br><br>
       <html:button property="back" value="Back" onclick="history.back();" />
    </logic:empty>
  		
  </body>
</html:html>



