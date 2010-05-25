<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page import="in.ac.dei.edrp.pms.viewer.checkRecord;"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
  <head>
    <title>search result</title>
 <link rel="stylesheet" href="style/Displaytagex.css" type="text/css"></link>
 <link rel="stylesheet" href="style/dropdown.css" type="text/css"></link>
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
   <%!
  	  int flag=0;
   %>
  <%
  	/* Getting the User_iD which is currently logged in. */
	   String uid=(String)session.getAttribute("uid");
		if(checkRecord.duplicacyChecker("Authority","login","login_user_id",uid).equalsIgnoreCase("Super Admin"))
			{
				flag=1;
			}
			else
			{
				flag=0;
			}
	%>
  <div id="main_title" align="left">
		    <font color="#0044ff">Searching Result By:</font><font color="#699f00" size="2"><%= session.getAttribute("searchOption") %></font>
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
	 		
 <display:table name="orgSearchList.list" defaultsort="1" id="row" export="false" pagesize="<%=Integer.parseInt(key) %>" requestURI="/orgSearchList.do" decorator="in.ac.dei.edrp.pms.deco.PmsDecorator" class="dataTable" >
		<display:column property="iname" title="Organisation Name" sortable="true"/>
		<display:column property="iaddress" title="Organisation Address" sortable="true" />
		<display:column property="icity" title="City" sortable="true" />
		<display:column property="istate" title="State" sortable="true" />
		<display:column property="iphoneno" title="Phone No." sortable="true" />
		<display:column property="ifax" title="Fax" sortable="true" />
		<display:column property="iurl" autolink="true" title="URL" sortable="true" />
		 <% 
			if(flag==1)
			{
		 %>
		<display:column media="html" title="Actions">
		 <html:link href="editorgpage.do" paramProperty="id" paramId="id" paramName="row">Edit
		  </html:link> <!-- | this link working
		 <html:link href="deleteorg.do" onclick="return sure();" paramProperty="id" paramId="id" paramName="row">Delete
		 </html:link> -->
		</display:column>
			<%
			}
			%>
	</display:table>
    <html:form action="/backbutton">
    <br>
  <html:submit property="back" value="Back to Search Page"/>
  </html:form>
  		
  </body>
</html:html>
