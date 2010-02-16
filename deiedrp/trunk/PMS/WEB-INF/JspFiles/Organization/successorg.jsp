<%@ page language="java" import="java.sql.*" pageEncoding="UTF-8"%>
<%@page import="org.dei.edrp.pms.dataBaseConnection.MyDataSource;"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
  <head>
    <title>successorg.jsp</title>
 <link rel="stylesheet" href="style/main.css" type="text/css"></link>
	
<link rel="stylesheet" href="style/Displaytagex.css" type="text/css"></link>
  </head>
  
  <body>
   
   <script type="text/javascript">
	function sure()
	{
		return(confirm("Are you sure want to Delete?"));
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
		    <font color="#0044ff">Searching Result By:</font><font color="#699f00" size="2"><%= session.getAttribute("searchOption") %></font>
		     </div><br>
 <display:table name="orgSearchList.list" defaultsort="1" id="row" export="false" pagesize="10" requestURI="/orgSearchList.do" decorator="org.dei.edrp.pms.deco.PmsDecorator" class="dataTable" >
				   
		<display:column property="iname" title="Organisation Name" sortable="true"/>
		<display:column property="iaddress" title="Organisation Address" sortable="true" />
		<display:column property="icity" title="City" sortable="true" />
		<display:column property="istate" title="State" sortable="true" />
		<display:column property="ipin" title="Pin Code" sortable="true" />
		<display:column property="iphoneno" title="Phone No." sortable="true" />
		<display:column property="ifax" title="Fax" sortable="true" />
		<display:column property="iurl" autolink="true" maxLength="15" title="URL" sortable="true" />
		<display:column property="ihead" title="Head Name" sortable="true" />
		<display:column property="ieid" title="Head Email_ID" sortable="true" />
		<display:column property="description" maxLength="10" title="Description" sortable="true" />
		 <% 
			if(flag==1)
			{
			 %>
			
			<display:column media="html" title="Actions">
		 <html:link href="editorgpage.do" paramProperty="id" paramId="id" paramName="row">Edit
		  </html:link> | 
		 <html:link href="deleteorg.do" onclick="return sure();" paramProperty="id" paramId="id" paramName="row">Delete
		 </html:link>
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



