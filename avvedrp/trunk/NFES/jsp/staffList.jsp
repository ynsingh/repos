<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">

<%@ page contentType="text/html; charset=iso-8859-1" language="java" import="javax.sql.DataSource,javax.naming.Context,javax.naming.InitialContext,java.sql.*,java.util.*,java.io.FileInputStream" errorPage="" %>

<%@ taglib prefix="app" uri="http://www.springframework.org/security/tags" %>

<HTML lang=en-US dir=ltr xmlns="http://www.w3.org/11001/xhtml">

<HEADprofile=http://gmpg.org/xfn/11>
<TITLE>Staff List</TITLE>
<META http-equiv=Content-Type content="text/html; charset=UTF-8">
<META content="MSHTML 6.00.2900.5694" name=GENERATOR>
<LINK media=screen href="../css/oiostyles.css" type=text/css rel=stylesheet>

<script language="javascript">	 
	function search(){	 	   
	   var searchby=document.getElementById('searchby').value;	 	 
	   var searchbyUniversity=document.getElementById('searchbyUniversity').value;	
	   var searchbyInstitution=document.getElementById('searchbyInstitution').value;	
	   window.location.href="staffList.jsp?searchby="+searchby+"&searchbyUniversity="+searchbyUniversity+"&searchbyInstitution="+searchbyInstitution;	   
	}
	

	function showStaffProfile(userId,documentId,mode){
		if(mode=="Edit"){
			var str="../StaffProfileServlet?action=CDOC-OPEN_A_DOCUMENT&entityId="+userId+"&documentId="+documentId+"&entitytype=staff&formName=staff_profile_report_v0";
		}else{	
			var str="../StaffProfileServlet?action=CDOC-OPEN_A_DOCUMENT_FOR_APPROVE&entityId="+userId+"&documentId="+documentId+"&entitytype=staff&formName=staff_profile_report_v0";
		}	
		window.parent.document.location.href = str;
	}

	
</script>
</HEAD>
<BODY class="bodystyle">

<form name="staffList" method="post">
<div align="center" class="listdiv">

<p class="labeltext">
<table class="search_field_div" width=98% align="center"><tr><td>
<table align=center>
<tr>
<td>User Name Starts with :<img src="../images/staffList.jpg"></td>
<td><Input Type="text name="searchby"  id="searchby" />
</tr>
<tr>
<td>University Starts with :</td>
<td><Input Type="text name="searchbyUniversity"  id="searchbyUniversity" /></td>
</tr>
<tr>
<td>Institution Starts with : </td>
<td><Input Type="text name="searchbyInstitution"  id="searchbyInstitution" /></td>
</tr>
<tr>
<td colspan="2" align="center">
<input type="button" name="searchby" value="Search" onclick="search();" /> 
</td>
</tr></table>
</td></tr></table>
<br>


<table  class="ListTable" width="98%" >


<TR>
<TH class="hidetd">User ID</TH>
<TH class="hidetd">Document ID</TH>
<TH class="ListHeader"> University</TH>
<TH class="ListHeader"> Institution</TH>
<TH class="ListHeader"> Faculty Name</TH>
<TH class="ListHeader">Email</TH>
<TH class="ListHeader">Edit</TH>
<TH class="ListHeader">Approve</TH>
<!--<TH class="ListHeader">Role</TH>-->

<TH></TH>
</TR>



<%
String str=null;
String rowclass="1";
String userName = null;
String university=null;
String institution=null;
int userId = 0;
String email = null;
String userFullName = null;
Connection conn1=null;
Connection conn2=null;
int documentId = 0;
int staffCount=0;
try
{	
		
	Properties properties = new Properties();
	properties.load(new FileInputStream("../conf/db.properties"));
	String dbname = properties.getProperty("dbname");		
	String username = properties.getProperty("username");
	String password = properties.getProperty("password");		
	
	String uname_start_with= request.getParameter("searchby")+'%';			
	String university_start_with= request.getParameter("searchbyUniversity")+'%';			
	String institution_start_with= request.getParameter("searchbyInstitution")+'%';			
		
	String role_name="";
	int approvedyn=0;
	Class.forName("org.gjt.mm.mysql.Driver");
	conn1=DriverManager.getConnection("jdbc:mysql:"+dbname,username,password);			
	PreparedStatement pst=conn1.prepareStatement("SELECT users.id,user_full_name,users.username,role_name,university_master.Name,institution_master.name FROM users INNER JOIN institution_master ON institution_master.id=users.institution_id  and institution_master.name like ? INNER JOIN university_master ON university_master.id=institution_master.university_id AND university_master.Name like ? LEFT JOIN  authorities ON users.username=authorities.username  LEFT JOIN roles ON roles.role_id=authorities.authority  WHERE enabled=1 AND NOT users.username='admin'  AND users.username like ?  ORDER BY university_master.Name,institution_master.name,users.username");	

	pst.setString(1,institution_start_with);
	pst.setString(2,university_start_with);
	pst.setString(3,uname_start_with);
	
	
	
	ResultSet rs=pst.executeQuery();	
	while(rs.next())
	{	staffCount=staffCount+1;
		userId=rs.getInt(1);
		userFullName=rs.getString(2); 	
		userName=rs.getString(3); 		
		role_name=rs.getString(4); 
		university=rs.getString(5);
		institution=rs.getString(6);
		conn2=DriverManager.getConnection("jdbc:mysql:"+dbname,username,password);
		PreparedStatement pst1=conn2.prepareStatement("select document_id,approved_yesno from entity_document_master where entity_id="+userId + " and entity_type='staff'");		
		ResultSet rs1=pst1.executeQuery();
		documentId=0;
		while(rs1.next())
		{
			documentId=rs1.getInt("document_id");		
			approvedyn=rs1.getInt("approved_yesno");		

		}
	
		if (rowclass=="1"){
		rowclass="0";	
	%>	
	<tr>
	<%}
	else	{
	rowclass="1";%>
	<tr class="ListRow">
	<%}%>
	<td class="hidetd"><%=userId%> </td>
	<td class="hidetd"><%=documentId%> </td>
	<td><%=university%> </td>
	<td><%=institution%> </td>
	<td><%=userFullName%> </td>
	<td><%=userName%> </td>
	<!--<td><%=role_name%></td>-->
	<%if(approvedyn==0){%>
	<td align="center"><input type=button value="Edit" onClick=showStaffProfile(<%=userId%>,<%=documentId%>,"Edit")></td>
	<td align="center"><input type=button value="Approve" onClick=showStaffProfile(<%=userId%>,<%=documentId%>,"Approve")></td>
	<%}else{%>	
	<td align="center"><input type=button value="View" onClick=showStaffProfile(<%=userId%>,<%=documentId%>,"Edit")></td>
	<td align="center"><input type=button value="Approve" disabled="disabled"/></td>
	<%}%>
	</tr>
		
	<%}
	conn1.close();

	
	conn2.close();
}catch(Exception e)
{
}

%>

</TABLE>
<br>
<table class="ListHeader"><tr><td>Total Faculty(s):<%=staffCount%></td></tr>
</div>
</form>

</BODY>

</HTML>
