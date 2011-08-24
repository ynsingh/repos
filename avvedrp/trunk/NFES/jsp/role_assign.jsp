
<%@ page contentType="text/html; charset=utf-8" pageEncoding="UTF-8" language="java" import="javax.sql.DataSource,javax.naming.Context,javax.naming.InitialContext,java.sql.*,java.util.*,java.io.FileInputStream" errorPage="" %>

<%@ taglib prefix="app" uri="http://www.springframework.org/security/tags" %>

<jsp:useBean id="db" class="com.erp.nfes.ConnectDB" scope="session"/> 
<jsp:useBean id="getUserDetails" class="com.erp.nfes.GetRecordValue" scope="session"/> 

<%
String msg = request.getParameter("value");
Connection conn=null;
Statement theStatement=null;
ResultSet theResult=null;
String uname="";String prole="";String nrole="";String arole="";String smsg=""; String lc="";
try{
    
     lc=(String) session.getAttribute("language");
     conn = db.getMysqlConnection();
     theStatement=conn.createStatement();
     theResult=theStatement.executeQuery("select control_name,language_string from language_localisation where active_yes_no=1 and file_code=25 and language_code=\'"+lc+"\'");
     theResult.last();int len=theResult.getRow();String cn[]=new String[len];String ls[]=new String[len];
     int i=0;theResult.beforeFirst();
     while(theResult.next()){
          cn[i]=theResult.getString("control_name");
          ls[i]=theResult.getString("language_string");
          i++;
     }
     
          
     for(i=0;i<len;i++){
     	
     	if(cn[i].equals("user_name")){
     		uname=ls[i];
     	}else if(cn[i].equals("present_role")){
     		prole=ls[i];
     	}else if(cn[i].equals("new_role")){
     		nrole=ls[i];
     	}else if(cn[i].equals("assign_role")){
     		arole=ls[i];
     	}else if(cn[i].equals("success_msg")){
     		smsg=ls[i];
     	}
     	
     }
     
     
     request.setCharacterEncoding("UTF-8");
     response.setContentType("text/html; charset=utf-8");
     Locale locale=new Locale(lc,"");
}catch(Exception e){
     e.printStackTrace();
}
theResult.close();
theStatement.close();
conn.close();
%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">

<HTML lang=en-US dir=ltr xmlns="http://www.w3.org/11001/xhtml">

<HEADprofile=http://gmpg.org/xfn/11>
<TITLE>Role Assign</TITLE>
<META http-equiv=Content-Type content="text/html; charset=UTF-8">
<META content="MSHTML 6.00.2900.5694" name=GENERATOR>
<LINK media=screen href="../css/oiostyles.css" type=text/css rel=stylesheet>

<script language="javascript">	 
	
	function getPresentRoll(){	
		var user_name=document.forms[0].userName.value;				
		var role_pos =(user_name.lastIndexOf("+"))+1;		
		var role_name=user_name.substring(role_pos);		
		document.forms[0].present_role.value=role_name;
	}
	
	
	
</script>

</HEAD>
<BODY class="bodystyle">

<form name="role_assign" method="post" action="../RoleAssignServlet">
<div class="listdiv">

<%
if( msg!=null ){
	%>	
	<br>
	<div class="message">			
		<%=smsg%>		
	</div>				
	<%
}%>

<p>
<table class="search_field_div" align="center" width="98%">
<tr>
<td><label  class="labeltext"><%=uname%><label  class="mandatory">*</label> </td>
<td><select name="userName" onchange="getPresentRoll();">

<%    		

Connection conn1=null;
String user_name="";
String role_name="";
try
{	
        conn1 = db.getMysqlConnection();
	//PreparedStatement pst=conn1.prepareStatement("SELECT users.username,authority FROM users LEFT JOIN  authorities ON users.username=authorities.username WHERE enabled=1  AND NOT users.username='admin' ORDER BY users.username");			
	PreparedStatement pst=null;
	
	if(getUserDetails.getRole(request.getUserPrincipal().getName()).equals("ROLE_ADMIN")==false){		                	
	 pst=conn1.prepareStatement("SELECT  staff_profile_masterdetails_v0_values.username,authority FROM  `staff_profile_masterdetails_v0_values` 	 inner JOIN  authorities ON staff_profile_masterdetails_v0_values.username=authorities.username AND university='"+ getUserDetails.getUniversity(request.getUserPrincipal().getName())  +"'  ORDER BY staff_profile_masterdetails_v0_values.username");
	}else{
	pst=conn1.prepareStatement("SELECT  staff_profile_masterdetails_v0_values.username,authority FROM  `staff_profile_masterdetails_v0_values` 	 inner JOIN  authorities ON staff_profile_masterdetails_v0_values.username=authorities.username  ORDER BY staff_profile_masterdetails_v0_values.username");
	}
	
	ResultSet rs=pst.executeQuery();	
	
	while(rs.next())		
	{	
	 user_name=rs.getString(1);	 
	 role_name=rs.getString(2);
	 %>	 
	<option value=<%=user_name%>+<%=role_name%>><%=user_name%></option>	
	 <%
	}	
	conn1.close();			
	%>
	</select>
	</td>
	</tr>
	<tr>
	<tr>
	<td><label class="labeltext"><%=prole%><label  class="mandatory">*</label></td>
	<td><input class="textmedium" type=text name="present_role" value="" disabled="disabled"/>
	<script>getPresentRoll();</script>
	</tr>
	<td><label class="labeltext"><%=nrole%><label  class="mandatory">*</label></td> 	
	<td><select name="role">
	<%
	conn1 = db.getMysqlConnection();
	
	if(getUserDetails.getRole(request.getUserPrincipal().getName()).equals("ROLE_ADMIN")==false){		                	
		pst=conn1.prepareStatement("SELECT role_name FROM roles WHERE active_yesno='1' AND role_name<>'ROLE_ADMIN' ORDER BY role_name");			
	}else{
		pst=conn1.prepareStatement("SELECT role_name FROM roles WHERE active_yesno='1' ORDER BY role_name");			
	}
	
	rs=pst.executeQuery();	
	while(rs.next())		
	{		
	 role_name=rs.getString(1);	 
	 %>	 
	 <option value=<%=role_name%>><%=role_name%></option>
	 <%
	}
			
}catch(Exception e)
{
}
conn1.close();
%>
</td>
</tr>
</select>
<td><img src="../images/role.png"></td>
<td><input type="submit"  name="Save"  value="<%=arole%>"/></td>

</table>
<br/>

</div>
</form>

</BODY>

</HTML>
