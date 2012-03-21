
<%@ page contentType="text/html; charset=utf-8" pageEncoding="UTF-8" language="java" import="javax.sql.DataSource,javax.naming.Context,javax.naming.InitialContext,java.sql.*,java.util.*,java.io.FileInputStream" errorPage="" %>
<jsp:useBean id="db" class="com.erp.nfes.ConnectDB" scope="session"/> 
<jsp:useBean id="getUserDetails" class="com.erp.nfes.GetRecordValue" scope="session"/> 
<jsp:useBean id="ml" class="com.erp.nfes.MultiLanguageString" scope="session"/> 

<%
String lc="";
try{     
     lc=(String) session.getAttribute("language");
     ml.init(lc);  
     request.setCharacterEncoding("UTF-8");
     response.setContentType("text/html; charset=utf-8");
     Locale locale=new Locale(lc,"");
}catch(Exception e){
     e.printStackTrace();
}
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
	   var searchbytitle=document.getElementById('searchbytitle').value;	 		   
       var searchbylastname=document.getElementById('searchbylastname').value;	 		   
	   var searchbyUniversity=document.getElementById('searchbyUniversity').value;	
	   var searchbyInstitution=document.getElementById('searchbyInstitution').value;	
	   var searchbyDepartment=document.getElementById('searchbyDepartment').value;
	   window.location.href="staffList.jsp?searchby="+searchby+"&searchbyUniversity="+searchbyUniversity+"&searchbyInstitution="+searchbyInstitution+"&searchbyDepartment="+searchbyDepartment+"&search=1&searchbytitle="+searchbytitle+"&searchbylastname="+searchbylastname;	   

	}
	

	function showStaffProfile(userId,documentId,mode){
		if(mode=="Edit"){
			var str="../StaffProfileServlet?action=CDOC-OPEN_A_DOCUMENT&entityId="+userId+"&documentId="+documentId+"&entitytype=staff&formName=staff_profile_report_v0";
		}else{	
			var str="../StaffProfileServlet?action=CDOC-OPEN_A_DOCUMENT_FOR_APPROVE&entityId="+userId+"&documentId="+documentId+"&entitytype=staff&formName=staff_profile_report_v0";
		}	
		window.parent.document.location.href = str;
	}

	function SetReportParams()
	{
	
	document.reports.searchbytitle_report.value=document.getElementById('searchbytitle').value;
	document.reports.searchbylastname_report.value=document.getElementById('searchbylastname').value;	
	document.reports.searchby_report.value=document.getElementById('searchby').value;
	document.reports.searchbyUniversity_report.value=document.getElementById('searchbyUniversity').value;	
	document.reports.searchbyInstitution_report.value=document.getElementById('searchbyInstitution').value;	
	document.reports.searchbyDepartment_report.value=document.getElementById('searchbyDepartment').value;
	document.reports.ReportFormat.value=document.getElementById('ReportFormat').value;
	document.reports.submit();
	}	
</script>
</HEAD>

<BODY class="bodystyle">
<form name="staffList" method="post" >
<div align="center" class="listdiv">
<div style="text-align: center; font-size:14px;font-weight: bold;height:8px;"> <%=ml.getValue("faculty_list")%> </div>
<br>
<table class="search_field_div" width=98% align="center">

<tr><td> 

<table >
<tr>
	<td width="5%" class="labeltext"><%=ml.getValue("university")%></td>	
	<%if(getUserDetails.getRole(request.getUserPrincipal().getName()).equals("ROLE_ADMIN_UNIVERSITY")){%>		
	<td width="20%"><Input Type="text" name="searchbyUniversity"  id="searchbyUniversity" disabled SIZE="40"/></td>
	<%}
	else{
	%>
	<td width="20%"><Input Type="text" name="searchbyUniversity"  id="searchbyUniversity" SIZE="40"  /></td>
	<%}%>
	<td width="5%" class="labeltext"><%=ml.getValue("institution")%></td> 
	<td width="20%"><Input Type="text" name="searchbyInstitution"  id="searchbyInstitution" SIZE="40"/></td>
</tr>
<tr>
	<td width="5%" class="labeltext"><%=ml.getValue("department")%></td>
	<td width="20%"><Input Type="text" name="searchbyDepartment"  id="searchbyDepartment" SIZE="40" /></td>
	
	<td width="5%" class="labeltext"><%=ml.getValue("title")%></td>
	<td width="20%">
	<Select name="searchbytitle"  id="searchbytitle">
	<option value="" SELECTED>-Select-</option>
	<%
		try
		{	
			Connection conn1=null;	
	        conn1 = db.getMysqlConnection();
	        PreparedStatement pst=null;
			pst=conn1.prepareStatement("SELECT fld_value FROM `general_master` WHERE category='Title' AND  active_yes_no=1 ORDER BY fld_value");						
			ResultSet rs=pst.executeQuery();		
			while(rs.next())		
			{%>
			<option value=<%=rs.getString("fld_value")%>><%=rs.getString("fld_value")%></option>;		 			
			<%}
		 }catch(Exception e){}		
		%>
	</select>
	</td>
</tr>
<tr>
	<td width="5%" class="labeltext"><%=ml.getValue("first_name")%></td>
	<td width="20%"><Input Type="text" name="searchby"  id="searchby" SIZE="40" /></td>	
	<td width="5%" class="labeltext"><%=ml.getValue("last_name")%></td>
	<td width="20%"><Input Type="text" name="searchbylastname"  id="searchbylastname" SIZE="40" /></td>
</tr>




<tr>
<td>&nbsp;</td>
<td class="labeltext">
<input type="button" name="searchby" value=<%=ml.getValue("search")%> onClick="search();" /> 
<input type="button" value="<%=ml.getValue("print")%>"   onClick="SetReportParams();"/>

<!--  Reports Format Selection Block -->
<table class="ReportFormatTable"><tr>
<script language="javascript">	
function setReporFormat(value){
	document.getElementById("ReportFormat").value=value;
}
</script>
<td class="labeltext">
<input type="HIDDEN" NAME ="ReportFormat" ID="ReportFormat" value="pdf">
<%=ml.getValue("format")%> :
<input id="Report_Format" name="Report_Format" type="radio" value="pdf" checked="checked" onClick="setReporFormat(this.value)";/>PDF
<input type="radio" id="Report_Format" name="Report_Format" value="csv" onClick="setReporFormat(this.value)" /> CSV
</td>  
</tr>
</table>
<!--  End of Report Format Selection Block -->

</td>
</tr></table>
</td></tr></table>
<br>
</form>
<div style="align:right">
<FORM action="../ReportsServlet" method="POST" name="reports" target="_blank">
<Input Type="HIDDEN" name="searchby_report"  id="searchby_report" />
<Input Type="HIDDEN" name="searchbytitle_report"  id="searchbytitle_report" />
<Input Type="HIDDEN" name="searchbylastname_report"  id="searchbylastname_report" />
<Input Type="HIDDEN" name="searchbyUniversity_report"  id="searchbyUniversity_report" />
<Input Type="HIDDEN" name="searchbyInstitution_report"  id="searchbyInstitution_report" />
<Input Type="HIDDEN" name="searchbyDepartment_report"  id="searchbyDepartment_report" />
<Input Type="HIDDEN" name="action"  id="action" value="FacultyList" />
<Input Type="HIDDEN" name="ReportFormat" id="ReportFormat" value="pdf"/>
</FORM>
</div>
<table  class="ListTable" width="98%" >

<TR>
<TH class="hidetd">User ID</TH>
<TH class="hidetd">Document ID</TH>
<TH class="ListHeader"><%=ml.getValue("faculty_name")%></TH>
<TH class="ListHeader"><%=ml.getValue("university")%></TH>
<TH class="ListHeader"><%=ml.getValue("institution")%></TH>
<TH class="ListHeader"><%=ml.getValue("department")%></TH>
<TH class="ListHeader"><%=ml.getValue("email")%></TH>
<TH class="ListHeader"><%=ml.getValue("designation")%></TH>
<TH class="ListHeader"><%=ml.getValue("edit")%></TH>
<TH class="hidetd"><%=ml.getValue("approve")%></TH>
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
		
	String uname_start_with= request.getParameter("searchby")+'%';		
	if(uname_start_with.equals("null%")==false){%>
	<script language="javascript">document.getElementById('searchby').value="<%=request.getParameter("searchby")%>";</script>
	<%	
	}

	String title_start_with= request.getParameter("searchbytitle")+'%';			
	if(title_start_with.equals("null%")==false){%>
	<script language="javascript">document.getElementById('searchbytitle').value="<%=request.getParameter("searchbytitle")%>";</script>
	<%	
	}
	
	String last_name_start_with= request.getParameter("searchbylastname")+'%';			
	if(last_name_start_with.equals("null%")==false){%>
	<script language="javascript">document.getElementById('searchbylastname').value="<%=request.getParameter("searchbylastname")%>";</script>
	<%	
	}
	
	String university_start_with= request.getParameter("searchbyUniversity")+'%';				
	if(university_start_with.equals("null%")==false){%>	
	<script language="javascript">				
	<%if(getUserDetails.getRole(request.getUserPrincipal().getName()).equals("ROLE_ADMIN_UNIVERSITY")){%>	
		document.getElementById('searchbyUniversity').value="<%=getUserDetails.getUniversity(request.getUserPrincipal().getName())%>";
	<%}else{%>
		document.getElementById('searchbyUniversity').value="<%=request.getParameter("searchbyUniversity")%>";
	<%}	%>
	</script>
	<%	
	}
	String institution_start_with= request.getParameter("searchbyInstitution")+'%';			
	
	if(institution_start_with.equals("null%")==false){%>
		<script language="javascript">				
			document.getElementById('searchbyInstitution').value="<%=request.getParameter("searchbyInstitution")%>";</script>
		<%	
	}
	String department_start_with= request.getParameter("searchbyDepartment")+'%';				
	if(department_start_with.equals("null%")==false){%>
		<script language="javascript">				
			document.getElementById('searchbyDepartment').value="<%=request.getParameter("searchbyDepartment")%>";</script>
		<%	
	}
	String role_name="";
	int approvedyn=0;
        conn1 = db.getMysqlConnection();
	//PreparedStatement pst=conn1.prepareStatement("SELECT users.id,user_full_name,users.username,role_name,university_master.Name,institution_master.name FROM users INNER JOIN institution_master ON institution_master.id=users.institution_id  and institution_master.name like ? INNER JOIN university_master ON university_master.id=institution_master.university_id AND university_master.Name like ? LEFT JOIN  authorities ON users.username=authorities.username  LEFT JOIN roles ON roles.role_id=authorities.authority  WHERE enabled=1 AND NOT users.username='admin'  AND users.username like ?  ORDER BY university_master.Name,institution_master.name,users.username");	
	String qry="SELECT users.id,`university_master`.name AS `University` ,";
	qry=qry + "	institution_master.`name` AS `Institution` ,";
	qry=qry + "	`general_master`.fld_value AS `Department`,";
	qry=qry + "	trim(CONCAT(IFNULL(title,''),' ',user_full_name,' ',IFNULL(LAST_NAME,''))) user_full_name,users.username ,users.`email`,";
	qry=qry + "	A.fld_value AS `Designation`";
	qry=qry + "	FROM staff_master";
	qry=qry + "	INNER JOIN `department_master` ";
	qry=qry + "	ON `department_master`.`id`=`staff_master`.`department_id` and staff_master.active_yesno=1 ";
	qry=qry + "	INNER JOIN `institution_master` ";
	qry=qry + "	ON `institution_master`.id=`department_master`.`institution_id` and institution_master.name like ?";
	qry=qry + "	INNER JOIN `university_master` ON `university_master`.id=`institution_master`.`university_id` AND university_master.Name like ?";
	qry=qry + "	INNER JOIN `general_master`";
	qry=qry + "	ON `general_master`.id =`department_master`.`department_id` AND category='Department' and fld_value like ?";
	qry=qry + "	INNER JOIN general_master A ON `A`.id =`staff_master`.`designation_id` ";
	qry=qry + "	AND A.category='Designation'";
	qry=qry + "	INNER JOIN users ON `staff_master`.userid=users.id and users.enabled=1 AND NOT users.username='admin'  AND users.user_full_name like ?";
	qry=qry + "	AND users.title like ? AND users.last_name like ?	";
	if(getUserDetails.getRole(request.getUserPrincipal().getName()).equals("ROLE_ADMIN_UNIVERSITY")){		
		qry=qry + " and	university_master.name='"+ getUserDetails.getUniversity(request.getUserPrincipal().getName())+"'";
	}
	
	qry=qry + "	ORDER BY university_master.Name,institution_master.name,user_full_name";
		
	PreparedStatement pst=conn1.prepareStatement(qry);	
	pst.setString(1,institution_start_with);
	pst.setString(2,university_start_with);
	pst.setString(3,department_start_with);
	pst.setString(4,uname_start_with);
	pst.setString(5,title_start_with);
	pst.setString(6,last_name_start_with);
		
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
		conn2 = db.getMysqlConnection();
		PreparedStatement pst1=conn2.prepareStatement("select document_id,approved_yesno from entity_document_master where entity_id="+userId + " and entity_type='staff'");		
		ResultSet rs1=pst1.executeQuery();
		documentId=0;
		while(rs1.next())
		{
			documentId=rs1.getInt("document_id");		
			approvedyn=rs1.getInt("approved_yesno");		

		}
		conn2.close();
		
		if(rowclass=="1"){
		  rowclass="0";			
		  classname="class='ListRow'";
		}else{
		  rowclass="1";
		  classname="class='ListRownext'";
		}%>
	<tr>
	  <td class="hidetd"><%=userId%> </td>
	  <td class="hidetd"><%=documentId%> </td>
	  <td <%=classname%>> <a href=../webpage/<%=userId%> target="_blank"><%=userFullName%></a></td>
	  <td <%=classname%>><%=university%> </td>
	  <td <%=classname%>><%=institution%> </td>
	  <td <%=classname%>><%=deptname%> </td>
	  <td <%=classname%>><%=userName%> </td>
	  <td <%=classname%>><%=designation%> </td>
	  <td <%=classname%> align="center"><input type=button value=<%=ml.getValue("edit")%> onClick=showStaffProfile(<%=userId%>,<%=documentId%>,"Edit")>	  </td>
	  <!--  
	  <%if(approvedyn==0){%>
	    <td <%=classname%> align="center"><input type=button value=<%=ml.getValue("edit")%> onClick=showStaffProfile(<%=userId%>,<%=documentId%>,"Edit")></td>
	    <td class="hidetd" align="center"><input type=button value=<%=ml.getValue("approve")%> onClick=showStaffProfile(<%=userId%>,<%=documentId%>,"Approve")></td>
	  <%}else{%>	
	    <td <%=classname%> align="center"><input type=button value=<%=ml.getValue("view")%> onClick=showStaffProfile(<%=userId%>,<%=documentId%>,"Edit")></td>
	    <td class="hidetd" align="center"><input type=button value=<%=ml.getValue("approve")%> disabled="disabled"/></td>
	  <%}%>-->
	</tr>
	
	<%}
	conn1.close();
	
}catch(Exception e)
{
}

%>
</TABLE>
<table  align="left" ><tr><td class="search_resul_category">&nbsp;&nbsp;&nbsp;<%=ml.getValue("total_faculty")%><%=staffCount%>&nbsp;&nbsp;&nbsp;</td></tr></table>
<br>
</div>
</BODY>

</HTML>
