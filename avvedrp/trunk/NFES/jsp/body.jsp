<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">

<%@ page contentType="text/html; charset=iso-8859-1" language="java" import="javax.sql.DataSource,javax.naming.Context,javax.naming.InitialContext,java.sql.*,java.util.*,java.io.FileInputStream" errorPage="" %>

<%@ taglib prefix="app" uri="http://www.springframework.org/security/tags" %>

<jsp:useBean id="db" class="com.erp.nfes.ConnectDB" scope="session"/> 

<HTML lang=en-US dir=ltr xmlns="http://www.w3.org/11001/xhtml">
<HEAD profile=http://gmpg.org/xfn/11>
<TITLE>Staff Profile</TITLE>
<META http-equiv=Content-Type content="text/html; charset=UTF-8">
<META content="MSHTML 6.00.2900.5694" name=GENERATOR>
<link href="../css/oiostyles.css" rel="stylesheet" type="text/css"/>

<SCRIPT type="text/javascript">
    window.history.forward();
    function noBack() { window.history.forward(); }
</SCRIPT>

</HEAD>
<BODY class="bodystyle" onLoad="noBack();"   onpageshow="if (event.persisted) noBack();" onUnload="">

<form name="user" method="post" >

<!--<table cellspacing="0" width="100%">
<tr>
<td><img src="../images/loginheader.png" width="100%" height="100%" ></td>
</tr>
<table>-->
<%
String str=null;
String userName = null;
int userId = 0;
String email = null;
String userFullName = null;
//String institution=null;
String university=null;
String imageName="";
Connection conn1=null;
Connection conn2=null;
int documentId = 0;
String Roll="";
try
{
	userName = request.getUserPrincipal().getName();

        conn1 = db.getMysqlConnection();
	//PreparedStatement pst=conn1.prepareStatement("SELECT users.id,email,user_full_name,institution_master.name instname,university_master.name universitynmae FROM users INNER JOIN institution_master ON institution_master.ID=users.`institution_id` and  username=? INNER JOIN university_master ON institution_master.id=university_master.id ");
	PreparedStatement pst=conn1.prepareStatement("SELECT users.id,email,user_full_name FROM users where  username=?  ");
	pst.setString(1,userName);
	ResultSet rs=pst.executeQuery();
	while(rs.next())
	{
		userId=rs.getInt(1);
		email=rs.getString(2); 
		userFullName=rs.getString(3); 
		//institution=rs.getString("instname");
		//university=rs.getString("universitynmae");
	}	
	conn1.close();
	
        conn2 = db.getMysqlConnection();
	PreparedStatement pst1=conn2.prepareStatement("select document_id from entity_document_master where entity_id="+userId + " and entity_type='staff'");
	ResultSet rs1=pst1.executeQuery();
	while(rs1.next())
	{
		documentId=rs1.getInt("document_id");				
	}
	conn2.close();
	
	/* =========== Roll Checking of User 08-02-11 Rajitha ======================= */
        conn1 = db.getMysqlConnection();
        pst=conn1.prepareStatement("SELECT username FROM authorities WHERE authority in ('ROLE_ADMIN','ROLE_ADMIN_UNIVERSITY')  AND  username=?");
	pst.setString(1,userName);
	rs=pst.executeQuery();
	while(rs.next())	{		
		Roll=rs.getString(1); 
		
	}	
	conn1.close();
	/* ================================ End ====================================== */
	
	
	/* =================== To display Staff Image 19-02-11 Rajitha ================ */
        conn1 = db.getMysqlConnection();
	pst=conn1.prepareStatement("SELECT upload_photo FROM staff_profile_report_v0_values WHERE idf=?");
	pst.setInt(1,userId);
	rs=pst.executeQuery();
	while(rs.next())
	{	imageName=userId+"/photo/"+rs.getString(1);					

	}	
	conn1.close();	
	/* ================================ End ======================================= */
	
	
}catch(Exception e)
{
}

if(Roll==""){
   str="'../StaffProfileServlet?action=CDOC-OPEN_A_DOCUMENT&entityId="+userId+"&documentId="+documentId+"&entitytype=staff&formName=staff_profile_report_v0'";
}
else{
  str="'./main.jsp'";  
}
%>


<!--<iframe name="framecontent"  src =<%=str%> width="100%" height=400px frameborder="0"> 
</iframe>-->


<script>
	window.location.href =<%=str%>;
</script>
</form>
</BODY></HTML>
