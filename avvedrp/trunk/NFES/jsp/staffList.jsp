
<%@ page contentType="text/html; charset=utf-8" pageEncoding="UTF-8" language="java" import="javax.sql.DataSource,javax.naming.Context,javax.naming.InitialContext,java.sql.*,java.util.*,java.io.FileInputStream" errorPage="" %>

<%
Connection conn=null;
Statement theStatement=null;
ResultSet theResult=null;
String uns="";String us="";String is="";String s="";String university1="";String institution1=""; String lc="";
String email1="";String edit="";String approve="";String tf="";String fn="";String view="";String department1="";
String department="";String designation1="";
try{
     
     lc=(String) session.getAttribute("language");
     Properties properties = new Properties();
     properties.load(new FileInputStream("../conf/db.properties"));
     String dbname = properties.getProperty("dbname");
     String username = properties.getProperty("username");
     String password = properties.getProperty("password");
     Class.forName("org.gjt.mm.mysql.Driver");
     conn=DriverManager.getConnection("jdbc:mysql:"+dbname+"?characterSetResults=UTF-8&characterEncoding=UTF-8&useUnicode=yes",username,password);
     theStatement=conn.createStatement();
     theResult=theStatement.executeQuery("select control_name,language_string from language_localisation where active_yes_no=1 and file_code=23 and language_code=\'"+lc+"\'");
     theResult.last();int len=theResult.getRow();String cn[]=new String[len];String ls[]=new String[len];
     int i=0;theResult.beforeFirst();
     while(theResult.next()){
          cn[i]=theResult.getString("control_name");
          ls[i]=theResult.getString("language_string");
          i++;
     }
     
     for(i=0;i<len;i++){
     	if(cn[i].equals("user_name_starts")){
     		uns=ls[i];
     	}else if(cn[i].equals("university_starts")){
     		us=ls[i];
     	}else if(cn[i].equals("institution_starts")){
     		is=ls[i];
     	}else if(cn[i].equals("search")){
     		s=ls[i];
     	}else if(cn[i].equals("university")){
     		university1=ls[i];
     	}else if(cn[i].equals("institution")){
     		institution1=ls[i];
     	}else if(cn[i].equals("faculty_name")){
     		fn=ls[i];
     	}else if(cn[i].equals("email")){
     		email1=ls[i];
     	}else if(cn[i].equals("edit")){
     		edit=ls[i];
     	}else if(cn[i].equals("approve")){
     		approve=ls[i];
     	}else if(cn[i].equals("total_faculty")){
     		tf=ls[i];
     	}else if(cn[i].equals("view")){
     		view=ls[i];
     	}else if(cn[i].equals("department1")){
     		department1=ls[i];
     	}else if(cn[i].equals("department")){
     		department=ls[i];
     	}else if(cn[i].equals("designation")){
     		designation1=ls[i];
     	}
     	
     }
     
     
     request.setCharacterEncoding("UTF-8");
     response.setContentType("text/html; charset=utf-8");
     Locale locale=new Locale(lc,"");
}catch(Exception e){
     e.printStackTrace();
}
theResult.close();theStatement.close();conn.close();	
%>

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
	   var searchbyDepartment=document.getElementById('searchbyDepartment').value;
	   window.location.href="staffList.jsp?searchby="+searchby+"&searchbyUniversity="+searchbyUniversity+"&searchbyInstitution="+searchbyInstitution+"&searchbyDepartment="+searchbyDepartment;	   
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
<style type="text/css">
<!--
.style1 {color: #000000}
-->
</style>
</HEAD>
<BODY class="bodystyle">

<form name="staffList" method="post">
<div align="center" class="listdiv">

<br>
<table class="search_field_div" width=98% align="center">
<tr><td>
<table >
<tr>
<td class="labeltext"><%=uns%></td>
<td><Input Type="text name="searchby"  id="searchby" />
</tr>
<tr>
<td class="labeltext"><%=us%></td>
<td><Input Type="text name="searchbyUniversity"  id="searchbyUniversity" /></td>
</tr>
<tr>
<td class="labeltext"><%=is%></td>
<td ><Input Type="text name="searchbyInstitution"  id="searchbyInstitution" /></td>
</tr>
<tr>
<td class="labeltext"><%=department1%></td>
<td ><Input Type="text name="searchbyDepartment"  id="searchbyDepartment" /></td>
</tr>
<tr>
<td>&nbsp;</td>
<td class="labeltext">
<input type="button" name="searchby" value=<%=s%> onClick="search();" /> 
</td>
</tr></table>
</td></tr></table>
<br>


<table  class="ListTable" width="98%" >


<TR>
<TH class="hidetd">User ID</TH>
<TH class="hidetd">Document ID</TH>
<TH class="ListHeader"><%=university1%></TH>
<TH class="ListHeader"><%=institution1%></TH>
<TH class="ListHeader"><%=department%></TH>
<TH class="ListHeader"><%=fn%></TH>
<TH class="ListHeader"><%=email1%></TH>
<TH class="ListHeader"><%=designation1%></TH>
<TH class="ListHeader"><%=edit%></TH>
<TH class="hidetd"><%=approve%></TH>
<!--<TH class="ListHeader">Role</TH>-->

<TH></TH>
</TR>



<%
String str=null;
String rowclass="1";
String userName = null;
String university=null;
String institution=null;
String classname=null;
int userId = 0;
String email = null;
String userFullName = null;
String deptname=null;
String designation=null;
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
	String department_start_with= request.getParameter("searchbyDepartment")+'%';			
	
	
	String role_name="";
	int approvedyn=0;
	Class.forName("org.gjt.mm.mysql.Driver");
	conn1=DriverManager.getConnection("jdbc:mysql:"+dbname,username,password);			
	//PreparedStatement pst=conn1.prepareStatement("SELECT users.id,user_full_name,users.username,role_name,university_master.Name,institution_master.name FROM users INNER JOIN institution_master ON institution_master.id=users.institution_id  and institution_master.name like ? INNER JOIN university_master ON university_master.id=institution_master.university_id AND university_master.Name like ? LEFT JOIN  authorities ON users.username=authorities.username  LEFT JOIN roles ON roles.role_id=authorities.authority  WHERE enabled=1 AND NOT users.username='admin'  AND users.username like ?  ORDER BY university_master.Name,institution_master.name,users.username");	
	String qry="SELECT users.id,`university_master`.name AS `University` ,";
	qry=qry + "	institution_master.`name` AS `Institution` ,";
	qry=qry + "	`general_master`.fld_value AS `Department`,";
	qry=qry + "	users.`user_full_name`,users.username ,users.`email`,";
	qry=qry + "	A.fld_value AS `Designation`";
	qry=qry + "	FROM staff_master";
	qry=qry + "	INNER JOIN `department_master` ";
	qry=qry + "	ON `department_master`.`id`=`staff_master`.`department_id`";
	qry=qry + "	INNER JOIN `institution_master` ";
	qry=qry + "	ON `institution_master`.id=`department_master`.`institution_id` and institution_master.name like ?";
	qry=qry + "	INNER JOIN `university_master` ON `university_master`.id=`institution_master`.`university_id` AND university_master.Name like ?";
	qry=qry + "	INNER JOIN `general_master`";
	qry=qry + "	ON `general_master`.id =`department_master`.`department_id` AND category='Department' and fld_value like ?";
	qry=qry + "	INNER JOIN general_master A ON `A`.id =`staff_master`.`designation_id` ";
	qry=qry + "	AND A.category='Designation'";
	qry=qry + "	INNER JOIN users ON `staff_master`.userid=users.id and users.enabled=1 AND NOT users.username='admin'  AND users.username like ?";
	qry=qry + "	ORDER BY university_master.Name,institution_master.name,users.username";
	PreparedStatement pst=conn1.prepareStatement(qry);
	
	pst.setString(1,institution_start_with);
	pst.setString(2,university_start_with);
	pst.setString(3,department_start_with);
	pst.setString(4,uname_start_with);
	
	
	
	
	
	ResultSet rs=pst.executeQuery();	
	while(rs.next())
	{	staffCount=staffCount+1;
		userId=rs.getInt("id");
		userFullName=rs.getString("user_full_name"); 	
		userName=rs.getString("username"); 		
		//role_name=rs.getString(4); 
		university=rs.getString("University");
		institution=rs.getString("Institution");
		deptname=rs.getString("Department");
		designation=rs.getString("Designation");
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
			classname="class='ListRow'";
		}
		else	{
		rowclass="1";
		classname="class='ListRownext'";%>
		
	<%}%>
	<tr>
	<td class="hidetd"><%=userId%> </td>
	<td class="hidetd"><%=documentId%> </td>
	<td <%=classname%>><%=university%> </td>
	<td <%=classname%>><%=institution%> </td>
	<td <%=classname%>><%=deptname%> </td>
	<td <%=classname%>><%=userFullName%> </td>
	<td <%=classname%>><%=userName%> </td>
	<td <%=classname%>><%=designation%> </td>
	<!--<td><%=role_name%></td>-->
	<%if(approvedyn==0){%>
	<td <%=classname%> align="center"><input type=button value=<%=edit%> onClick=showStaffProfile(<%=userId%>,<%=documentId%>,"Edit")></td>
	<td class="hidetd" align="center"><input type=button value=<%=approve%> onClick=showStaffProfile(<%=userId%>,<%=documentId%>,"Approve")></td>
	<%}else{%>	
	<td <%=classname%> align="center"><input type=button value=<%=view%> onClick=showStaffProfile(<%=userId%>,<%=documentId%>,"Edit")></td>
	<td class="hidetd" align="center"><input type=button value=<%=approve%> disabled="disabled"/></td>
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
<table class="search_field_div"><tr><td>&nbsp;&nbsp;&nbsp;<%=tf%><%=staffCount%>&nbsp;&nbsp;&nbsp;</td></tr>
</div>
</form>

</BODY>

</HTML>
