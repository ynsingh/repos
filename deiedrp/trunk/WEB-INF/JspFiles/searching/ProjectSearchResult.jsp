<%@ page language="java" import="java.sql.*" pageEncoding="UTF-8"%>
<%@page import="dataBaseConnection.MyDataSource"%>
<%@page import="projmanage.ProjectFields;"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
  <head>
    <title>example.jsp</title>
 <link rel="stylesheet" href="<html:rewrite page='/style/main.css'/>" type="text/css"></link>
  
<link rel="stylesheet" href="style/Displaytagex.css" type="text/css"></link>
  </head>
  
  <body onload="rowchange();">
   <script type="text/javascript">
	function sure()
	{
		return(confirm("Are you sure want to Delete?"));
	}
	function rowchange()
	{
	    var table = document.getElementById("row");    
    	var tbody = table.getElementsByTagName("tbody")[0];
    	var rows = tbody.getElementsByTagName("tr");
    // add event handlers so rows light up and are clickable
    	for (i=0; i < rows.length; i++) {
        	var value = rows[i].getElementsByTagName("td")[0].firstChild.nodeValue;
          if (value.indexOf('N') == 6) {
            rows[i].style.backgroundColor = "#C0C0C0";
            rows[i].style.textDecoration="line-through";
            rows[i].style.color="#FF6347";
        }
    	}
	}
	</script>
   <%!
  	Connection con=null;
  	PreparedStatement ps=null;
   	ResultSet rs=null;
   int flag=0;
   %>
  <%
  	con=MyDataSource.getConnection();
  	/* Getting the User_iD which is currently logged in. */
   	String uid=(String)session.getAttribute("uid");
	 //out.println("uid="+uid);
	ps=con.prepareStatement("select Authority from login where User_ID=?");
	ps.setString(1,uid);
		 rs=ps.executeQuery();
			rs.next();
			//out.println("authority="+rs.getString(1));
			if(rs.getString(1).equals("Admin"))
			{
			flag=1;
			}
			else
			{
				flag=0;
			}
			MyDataSource.freeConnection(con);
   %>
   
    
  <div id="main_title" align="left">
		    <font color="#0044ff">Searching Result By:</font><font color="#699f00" size="2"><%= session.getAttribute("soption") %></font>
		     </div><br>
 <display:table name="projectSearchList.list" defaultsort="2" id="row" export="false" pagesize="10" requestURI="/projectSearchList.do" decorator="deco.PmsDecorator" class="dataTable" >
	
		<display:column title="Active Status" sortable="true" >
		<logic:equal name="row" property="enable" value="0">Yes</logic:equal>
		<logic:equal name="row" property="enable" value="1">No</logic:equal>
		</display:column>				   
		<display:column property="id" group="2" title="Project ID" sortable="true" />
		<display:column property="name" group="3" title="Project Name" sortable="true" />
		<display:column property="startDate" group="4" title="Start Date" sortable="true" />
		<display:column property="finishDate" group="5" title="Finished Date" sortable="true" />
		<display:column property="tbudget" group="6" title="Target Budget (Rs.)" format="{0,number,0,000.00}" sortable="true" />
		<display:column property="status" group="7" title="Status" sortable="true" />
		<%
			if(((String)session.getAttribute("soption")).equals("Project Owner") ||((String)session.getAttribute("soption")).equals("Organisation Name"))
			{
			 %>
		<display:column property="priority" title="UserID" sortable="true" />
		<display:column property="viewPermission" title="Authority" sortable="true" />
		<display:column property="gcolor" title="Permitted By" sortable="true" />
		<display:column title="Organisation Name" sortable="true">
		<html:link title="click for view the Organisation detail" href="orgInfo.do" paramProperty="darea" paramId="orgkey" paramName="row">
		<%=((ProjectFields)pageContext.getAttribute("row")).getDarea()%>
		</html:link>
		</display:column>
		<%
			}
			else
			{
			 %>
			 <display:column property="priority" title="Priority" sortable="true" />
		<display:column property="viewPermission" title="View Permission" sortable="true" />
		<display:column title="Gantt Chart Color" sortable="true">
		<font style="padding-left:100%;background-color:<%=((ProjectFields)pageContext.getAttribute("row")).getGcolor()%>;">
		</font></display:column>

		<display:column property="darea" maxLength="15" title="Description" sortable="true" />
			 <%
			}
			 %>
			 <% 
			if(flag==1)
			{
			 %>
			
			<display:column media="html" title="Actions">
		 	<logic:equal name="row" property="enable" value="0">
			<html:link href="editProject.do" paramProperty="id" paramId="id" paramName="row"><img border="0" title="Edit" src="img/write_pen.gif" width="15"  height="10" >
		 	</html:link> | 
			<html:link href="deleteProject.do" onclick="return sure();" paramProperty="id" paramId="id" paramName="row">Disable
			 </html:link>  
		</logic:equal>
		<logic:equal name="row" property="enable" value="1">
	 		 <html:link href="deleteProject.do" paramProperty="id" paramId="id" paramName="row">Enable
		 	</html:link>
		</logic:equal>

		</display:column>

			<%}
			 %>
	</display:table>
    <html:form action="/button">
    <br>
  <html:submit property="back" value="Back to Search Page"/>
  </html:form>
  </body>
</html:html>




