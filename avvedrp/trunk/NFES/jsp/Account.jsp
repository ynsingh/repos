<%@ page contentType="text/html; charset=utf-8" pageEncoding="UTF-8" language="java" import="javax.sql.DataSource,javax.naming.Context,javax.naming.InitialContext,java.sql.*,java.util.*,java.io.FileInputStream" errorPage="" %>
<jsp:useBean id="db" class="com.erp.nfes.ConnectDB" scope="session"/> 
<jsp:useBean id="getUserDetails" class="com.erp.nfes.GetRecordValue" scope="session"/> 
<jsp:useBean id="ml" class="com.erp.nfes.MultiLanguageString" scope="session"/> 



<%
String lc = "";String action = "";
try{     
     lc=(String) session.getAttribute("language");
     action = request.getParameter("action");
     ml.init(lc);  
     request.setCharacterEncoding("UTF-8");
     response.setContentType("text/html; charset=utf-8");
     Locale locale=new Locale(lc,"");
}catch(Exception e){
     e.printStackTrace();
}
%>

<HTML lang=en-US dir=ltr xmlns="http://www.w3.org/11001/xhtml">
<HEAD profile=http://gmpg.org/xfn/11>
<TITLE>Staff Registration</TITLE>
<link href="../css/oiostyles.css" type="text/css" rel="stylesheet">
<link href="../css/jquery.datepick.css" rel="stylesheet" type="text/css" />
<script language="javascript" src="../js/jquery-1.4.2.min.js"></script>
<script language="JavaScript" src="../js/jquery.datepick.js"></script>
<script language="javascript">
$(document).ready(function(){
$('#joining_date').datepick({maxDate:'',dateFormat: 'dd-mm-yy',closeAtTop: false, showStatus: true,  showOn: 'both', buttonImageOnly: true, buttonImage: '../images/calendar.gif'});
});
</script>

<META content="MSHTML 6.00.2900.5694" name=GENERATOR>
<%@ taglib prefix='c' uri='http://java.sun.com/jstl/core_rt' %>


<script language="javascript">
var ps="";
function createAccount(){
  var frmObj = document.forms['userForm'];	
  if(checkValues()){			
    var frmObj = document.forms['userForm'];
	var answer = confirm ("Create an account?");
	if(answer){			     
	  document.forms['userForm'].submit();			     
	}else{			
	  return false;			
	}		
  }		
}	

function checkValues(){	
	var frmObj = document.forms['userForm'];
	var title = frmObj.title.value;
	var firstName = frmObj.firstName.value.replace(/^\s+|\s+$/g,"");
	//var lastName = frmObj.lastName.value;
	var user = frmObj.userName.value;
	var pwd = frmObj.userPassword.value;
	var email = frmObj.emailId.value;
	var pwd2 = frmObj.userPassword2.value;
	var email2 = frmObj.emailId2.value;
	var institution = frmObj.institution.value;
	var department = frmObj.department.value;
	var designation = frmObj.designation.value;
	var role = frmObj.role.value;
	var joining_date=frmObj.joining_date.value;
	var userName = "";
	var userId = "";
	var f = true;
	//alert(title);	
	if( title=="null" || firstName=="" || user == "" || pwd == "" || email == "" || institution=="null" || department=="null" || designation=="null" || role=="null" || joining_date==""){
		alert("Please enter mandatory fields.");
		return false;
	}
	if(ps=="MC" || ps=="W"){
	        alert( "Weak Password enter new one");
		return false;
	}
	if( pwd != pwd2 ){
		alert( "Passwords you entered are not matching");
		return false;
	}
	if( email != email2 ){
		alert( "Email IDs you entered are not matching");
		return false;
	}
	if(!(validateEmail(user))){
		f = false;alert("Please enter a valid e-mail in Username field");
	}else if(!(validateEmail(email))){
		f = false;alert("Please enter a valid e-mail in Email field");
	}
	
	return f;	
}


function validateEmail(email) {   
    var re = /^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
	//"
    if (! email.match(re)) {
    	return (false);
    }
    return(true);    
}
function passwordChanged()
{
	var strength = document.getElementById("strength");
	var strongRegex = new RegExp("^(?=.{8,})(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])(?=.*\\W).*$", "g");
	var mediumRegex = new RegExp("^(?=.{7,})(((?=.*[A-Z])(?=.*[a-z]))|((?=.*[A-Z])(?=.*[0-9]))|((?=.*[a-z])(?=.*[0-9]))).*$","g");
	var enoughRegex = new RegExp("(?=.{6,}).*", "g");
	var pwd = document.getElementById("userPassword");
	if (pwd.value.length==0) {
		strength.innerHTML = '<%=ml.getValue("type_password")%>';ps="";
	} else if (false == enoughRegex.test(pwd.value)) {
		strength.innerHTML = '<%=ml.getValue("more_characters")%>';ps="MC";
	} else if (strongRegex.test(pwd.value)) {
		strength.innerHTML = '<span style="color:green"><%=ml.getValue("strong")%></span>';ps="S";
	} else if (mediumRegex.test(pwd.value)) {
		strength.innerHTML = '<span style="color:orange"><%=ml.getValue("medium")%></span>';ps="M";
	} else { 
		strength.innerHTML = '<span style="color:red"><%=ml.getValue("weak")%></span>';ps="W";
	}
}

function loadDepartments(action){
  title=document.getElementById("title").value;
  firstName=document.getElementById("firstName").value;
  lastName=document.getElementById("lastName").value;
  emailId=document.getElementById("emailId").value;
  designation=document.getElementById("designation").value;
  selinstitution=document.getElementById("institution").value;
  role=document.getElementById("role").value;    
  joining_date=document.getElementById("joining_date").value;
  openId=document.getElementById("openId").value;  
  if(action == "CREATE_USER"){
    userPassword=document.getElementById("userPassword").value;  
    userName=document.getElementById("userName").value;
    userPassword2=document.getElementById("userPassword2").value;  
    emailId2=document.getElementById("emailId2").value;    
    location.href="Account.jsp?action=CREATE_USER&title='"+title+"'&firstName='"+firstName+"'&lastName='"+lastName+"'&userName='"+userName+"'&userPassword='"+userPassword+"'&userPassword2='"+userPassword2+"'&emailId='"+emailId+"'&emailId2='"+emailId2+"'&institution='"+selinstitution+"'&role='"+role+"'&designation='"+designation+"'&joining_date='"+joining_date+"'&openId='"+openId+"'";
  }else if(action == "EDIT_USER"){
	userName = document.getElementById("userName").value;
	userId = document.getElementById("userId").value;
    location.href="Account.jsp?action=EDIT_USER&title="+title+"&firstName="+firstName+"&lastName="+lastName+"&emailId="+emailId+"&institution="+selinstitution+"&role="+role+"&designation="+designation+"&uName="+userName+"&uId="+userId+"&joining_date='"+joining_date+"'&openId='"+openId+"'"; 
  }
}

function getSelectedInstitution(selinstitution)
{
document.getElementById("institution").value=selinstitution;
}

function reAssign(action,title,firstName,lastName,userName,userPassword,userPassword2,emailId,emailId2,designation,role,selinstitution,department,uId,uName,joining_date,openId){
	

  if(action == "EDIT_USER"){
    document.getElementById("title").value=title;
    document.getElementById("firstName").value=firstName;
    document.getElementById("lastName").value=lastName;
    document.getElementById("emailId").value=emailId;  
    document.getElementById("designation").value=designation;
    document.getElementById("role").value=role;
    document.getElementById("institution").value=selinstitution;
    document.getElementById("department").value=department;
    document.getElementById("userId").value=uId;
    document.getElementById("userName").value=uName;
    document.getElementById("openId").value=openId;
  }else if(action == "CREATE_USER"){
    document.getElementById("title").value=title;
    document.getElementById("firstName").value=firstName;
    document.getElementById("lastName").value=lastName;
	document.getElementById("emailId").value=emailId;  
	document.getElementById("designation").value=designation;
    document.getElementById("userName").value=userName;
	document.getElementById("userPassword").value=userPassword;  
	document.getElementById("userPassword2").value=userPassword2;
	document.getElementById("emailId2").value=emailId2;
	document.getElementById("role").value=role;
	document.getElementById("openId").value=openId;
	passwordChanged();
  }
  	if(joining_date!=''){
		alert(joining_date);
	   document.getElementById("joining_date").value=joining_date;
	}else{
		document.getElementById("joining_date").value='';
	}	
}

function showStaffProfile(userId,mode){
	if(mode=="Edit"){
		var str="./Account.jsp?action=EDIT_USER&userId="+userId;
		window.location.href = str;
	}	
}
function editUserDetails(){
	var frmObj = document.forms['editUserForm'];
	var title = frmObj.title.value;
	var firstName = frmObj.firstName.value.replace(/^\s+|\s+$/g,"");
	var lastName = frmObj.lastName.value;
	var email = frmObj.emailId.value;
	var institution = frmObj.institution.value;
	var department = frmObj.department.value;
	var designation = frmObj.designation.value;
	var role = frmObj.role.value;
	var joining_date=frmObj.joining_date.value;
	if(title == "null"){
		alert("Please enter Title field");
	}else if(firstName == ""){
		alert("Please enter First Name");
	}else if(email ==""){
		alert("Please enter a valid e-mail in Email field");
	}else if(!(validateEmail(email))){
		alert("Please enter a valid e-mail in Email field");
	}else if(institution == "null"){
		alert("Please select a valid Institution");
	}else if(department == "null"){
		alert("Please select a valid Department");
	}else if(designation == "null"){
		alert("Please select a valid Designation");
	}else if(role == "null"){
		alert("Please select a valid Role");
	}else if(joining_date==""){
		alert("Please select Joing Date");
	}
	else{
		document.forms['editUserForm'].submit();
	}
}
function init(){
 if("<%=action%>" == "CREATE_USER"){
	if(document.getElementById("documentStatus").value == "SAVED"){
	 alert("Saved Successfully");
    }
  }
}
</script>

</HEAD>
<BODY class="bodystyle" onload="init();">
<div  class="listdiv">
<% if(action.equals("CREATE_USER")){%>
<form id="userForm" action="../ProfileCreationActivationServlet?action=REGISTER_USER" method="post"  >
 <% 
 String title="";
 String firstName="";
 String lastName="";
 String userName="";
 String userPassword="";
 String userPassword2="";
 String emailId="";
 String emailId2="";
 String selinstitution="";
 String designation="";
 String documentStatus = "";
 String joining_date="";
 String role="";
 String openId="";
 if(request.getParameter("DocumentStatus") != null)
 {
 documentStatus = request.getParameter("DocumentStatus");
 }
  if(request.getParameter("openId") != null)
   {	 
	openId = request.getParameter("openId");	
 }
  if ((request.getParameter("userExists") != "")&&(request.getParameter("userExists") != null)) {%>
	<div class="message">			
		User already exists..
	</div>		             
<%  } 
 if ((request.getParameter("emailExists") != "")&&(request.getParameter("emailExists") != null)) {%>	             
	<div class="message">			
	The email you entered already exists..
	</div>	

<%  }
 if ((request.getParameter("openIdExists") != "")&&(request.getParameter("openIdExists") != null)) {%>	
	<div class="message"><%=ml.getValue("open_id_already_exists")%>!!!</div>	

<%  }
%>
	  <div style="text-align: center; font-size:14px;font-weight: bold;height:8px;">
	  <%=ml.getValue("faculty_registration")%>
	  </div>
<br>
<input type="hidden" name="documentStatus" id="documentStatus" value="<%=documentStatus%>" />
<table class="search_field_div" align="center" width="98%">  	
	<!--<tr>
	<td rowspan="7"><img src="../images/StaffReg.gif"></img></td>			
	</tr>
	-->
	<tr>
	<td width="5%"><label class="labeltext"><%=ml.getValue("title")%>:</label><label class="mandatory">*</label></td>
	<td width="20%"><select id="title" name="title" >
	<%
	Connection conn1 = null;
	String title_value = "";
	%>
	<option value="null">-Select-</option>
	<%
	try
	{	
	 conn1 = db.getMysqlConnection();
	 PreparedStatement pst = conn1.prepareStatement("SELECT fld_value FROM general_master WHERE category='title' AND active_yes_no=1 ORDER BY fld_value");
	 ResultSet rs=pst.executeQuery();
	 while(rs.next()){
		 title_value = rs.getString("fld_value"); 
	%>
	<option value=<%=title_value%>><%=title_value%></option>
	<%	
	  }
	 }catch(Exception e){}
	 %>
	</td>			   
	<td width="5%"><label class="labeltext"><%=ml.getValue("first_name")%>:</label><label class="mandatory">*</label></td>
	<td width="20%"><input class="textmedium" id="firstName" name="firstName" ></td>		   
	</tr>
		
	<tr>		   
	<td width="5%"><label class="labeltext"><%=ml.getValue("last_name")%>:</label></td>
	<td width="20%"><input class="textmedium" id="lastName" name="lastName" ></td>		   	   
	<td width="5%"><label class="labeltext"><%=ml.getValue("user_name")%>:</label><label class="mandatory">*</label></td>
	<td width="20%"><input class="textmedium" id="userName" name="userName" ></td>		   
	</tr> 
				   
	<tr> 		                 
	<td width="5%"><label class="labeltext"><%=ml.getValue("password")%>:</label><label class="mandatory">*</label></td>
	<td width="20%"><input type="password" id="userPassword"  name="userPassword" class="textmedium"  onCopy="return false"  onPaste="return false" onkeyup="return passwordChanged();" />
	<span id="strength" class="labeltext"></span>
	</td>
	<td width="5%"><label class="labeltext" onCopy="return false"  onPaste="return false"><%=ml.getValue("confirm_password")%>:</label><label class="mandatory">*</label></td>
	<td width="20%"><input type="password" id="userPassword2"  name="userPassword2" class="textmedium"></td>                
	</tr>                 
		 
	<tr>                                  
	<td width="5%"><label class="labeltext"><%=ml.getValue("email")%>:</label><label class="mandatory">*</label></td>
	<td width="20%"><input  class="textmedium" id="emailId" name="emailId"></td>
	<td width="5%"><label class="labeltext"><%=ml.getValue("confirm_email")%>:</label><label class="mandatory">*</label></td>
	<td width="20%"><input  class="textmedium" id="emailId2" name="emailId2" onCopy="return false"  onPaste="return false"></td>                 
	</tr>		
		
	<tr>
	<td width="5%"><label class="labeltext"><%=ml.getValue("institution")%>:</label><label class="mandatory">*</label></td>
	<td width="20%"><Select name="institution"  id="institution" style="width:200px" onchange="loadDepartments('CREATE_USER');" >	
	<%    
		conn1.close();
		conn1=null;
		String inst_id="";
		String inst_name="";%>
		<option value="null">-Select-</option>
	<%
	try
	{	
			
        conn1 = db.getMysqlConnection();
        PreparedStatement pst=null;
        if(getUserDetails.getRole(request.getUserPrincipal().getName()).equals("ROLE_ADMIN_UNIVERSITY")){		
			pst=conn1.prepareStatement("SELECT id,name FROM institution_master  where active_yes_no=1 and university_id="+ getUserDetails.getUniversityID(request.getUserPrincipal().getName())+" ORDER BY name");
		}else{
		 pst=conn1.prepareStatement("SELECT id,name FROM institution_master  where active_yes_no=1 ORDER BY name");				
		}	
		ResultSet rs=pst.executeQuery();		
		while(rs.next())		
		{		 
		 inst_id=rs.getString(1);	 		 
		 if(selinstitution==""){		 	
		 	selinstitution=inst_id;
		 }
		 inst_name=rs.getString(2);
		 %>	 
		<option value=<%=inst_id%>><%=inst_name%></option>	
		 <%			
		}
	 }catch(Exception e)
	{
		}
		
	%>
	</select>
	<%  
	if (request.getParameter("institution")!=null){
		title=request.getParameter("title");
		firstName=request.getParameter("firstName");
		lastName=request.getParameter("lastName");
		userName=request.getParameter("userName");
		userPassword=request.getParameter("userPassword");
		userPassword2=request.getParameter("userPassword2");		
		emailId=request.getParameter("emailId");
		emailId2=request.getParameter("emailId2");
		selinstitution=request.getParameter("institution"); 
		designation=request.getParameter("designation");	
		joining_date=request.getParameter("joining_date");	
		role=request.getParameter("role");		 		
		%>		
		<script>		
		getSelectedInstitution(<%=selinstitution%>);
		document.forms[0].institution.focus();
		</script>
	<%}%>
	</td>
	<td width="5%"><label class="labeltext"><%=ml.getValue("department")%></label><label class="mandatory">*</label></td>
	<td width="20%"><Select name="department" id="department"  style="width:200px">
	<%    		
			conn1.close();
			conn1=null;
			String dept_id="";
			String dept_name="";	%>
			<option value="null">-Select-</option>
		<%	
		try
		{	
				
            conn1 = db.getMysqlConnection();
			//PreparedStatement pst=conn1.prepareStatement("SELECT id,fld_value FROM `general_master` WHERE category='Department' AND  active_yes_no=1 AND id IN(SELECT department_id FROM `department_master` WHERE institution_id="+selinstitution + ")  ORDER BY fld_value");				
			PreparedStatement pst=conn1.prepareStatement("SELECT department_master.id,fld_value,institution_id FROM `general_master` INNER JOIN `department_master` ON  `general_master`.id=department_master.department_id  AND department_master.institution_id="+selinstitution + "  AND  department_master.active_yes_no=1 ORDER BY fld_value");
			ResultSet rs=pst.executeQuery();		
			
			while(rs.next())		
			{	
			 dept_id=rs.getString(1);	 
			 dept_name=rs.getString(2);
			 %>	 
			<option value=<%=dept_id%>><%=dept_name%></option>	
			 <%			
			}
		 }catch(Exception e)
		{
			}
			
	%>
	</select></td>
	</tr>
	
	<tr>
		<td width="5%"><label class="labeltext"><%=ml.getValue("designation")%>:</label><label class="mandatory">*</label></td>
		<td width="20%"><Select name="designation"  style="width:200px" id="designation">	
		<%    		conn1.close();		
				conn1=null;
				String desg_id="";
				String desg_name="";	%>		
				<option value="null">-Select-</option>
		<%				
			 try
			 {	
			     conn1 = db.getMysqlConnection();
				PreparedStatement pst=conn1.prepareStatement("SELECT id,fld_value FROM `general_master` WHERE category='Designation' AND  active_yes_no=1  ORDER BY fld_value");				
				
				ResultSet rs=pst.executeQuery();		
				
				while(rs.next())		
				{	
				 desg_id=rs.getString(1);	 
				 desg_name=rs.getString(2);
				 %>	 
				<option value=<%=desg_id%>><%=desg_name%></option>	
				 <%			
				}
			 }catch(Exception e){
			 }
			 conn1.close();
			%> 
		</select></td>
	<td width="5%"><label class="labeltext"><%=ml.getValue("role")%>:</label><label class="mandatory">*</label></td>
	<td width="20%">
	  <Select name="role" id="role"  style="width:200px">
	    <option value="null">-Select-</option>
	      <%
	      try{	
		    conn1 = db.getMysqlConnection();
		    PreparedStatement pst = null;
		    String role_name = "";
		    if(getUserDetails.getRole(request.getUserPrincipal().getName()).equals("ROLE_ADMIN")==false){		                	
				pst=conn1.prepareStatement("SELECT role_name FROM roles WHERE active_yesno='1' AND role_name<>'ROLE_ADMIN' ORDER BY role_name");			
			}else{
				pst=conn1.prepareStatement("SELECT role_name FROM roles WHERE active_yesno='1' ORDER BY role_name");			
			}
		    ResultSet rs=pst.executeQuery();
		    while(rs.next()){		
			  role_name=rs.getString(1);	 
			  %>	 
			  <option value=<%=role_name%>><%=role_name%></option>
			  <%
			}
		  }catch(Exception e){
		  }
		  %>
	  </Select>
	</td>
	</tr>
	<tr>
	    <td><%=ml.getValue("joining_date")%><label class="mandatory">*</label></td>	    
	    <td><input type="text" id="joining_date" name="joining_date" value="" readonly="readonly" size="10" /></td>
	    <td><%=ml.getValue("openId")%></td>	    
	    <td><input class="textmedium"  id="openId" name="openId" value=""  /></td>
	    
	</tr>
		<%if (request.getParameter("institution")!=null){
		%>
		<script>
		reAssign("CREATE_USER",<%=title%>,<%=firstName%>,<%=lastName%>,<%=userName%>,<%=userPassword%>,<%=userPassword2%>,<%=emailId%>,<%=emailId2%>,<%=designation%>,<%=role%>,"","","","",<%=joining_date%>,<%=openId%>);
		</script>		
		<%}%>
	<tr>		  
	<td></td>	
	<td colspan="2"><input type="button"  onClick="createAccount()"  value="<%=ml.getValue("create_account")%>" />	</td>
	</tr>	
</table>
</form>     
<br/>
<% 
}else if(action.equals("EDIT_USER")){
	Connection conn1=null;
	ResultSet rs=null;
	PreparedStatement pst=null;
	String selinstitution = "";
	String department="";
	String title="";
	String firstName="";
	String lastName="";
	String emailId="";
	String designation="";
	String role="";
	String userName="";
	String userId = request.getParameter("userId");
	String error_msg = request.getParameter("error_msg");
	if(error_msg!=null){
    	out.println("<script>alert('"+ error_msg+"')</script>");
    }	
	String	joining_date="";
	String openId="";
	if(userId!=null){
	  conn1 = db.getMysqlConnection();
	  pst=conn1.prepareStatement("SELECT username,user_full_name,title,last_name,email,openid FROM users  LEFT JOIN user_openID_map ON user_openID_map.user_id=users.id WHERE users.id="+userId);
	  rs=pst.executeQuery();rs.first();
	  title=rs.getString("title");
	  firstName=rs.getString("user_full_name");
	  lastName=rs.getString("last_name");
	  emailId=rs.getString("email");
	  userName=rs.getString("username");	  
	  openId=rs.getString("openId");	   
	  if(openId==null)	{
	   openId="";
	  	}
	  pst=conn1.prepareStatement("SELECT authority from authorities where username='"+userName+"'");
	  rs=pst.executeQuery();rs.first();
	  role=rs.getString("authority");
	  pst=conn1.prepareStatement("SELECT department_id,designation_id from staff_master where userid="+userId +" and staff_master.active_yesno=1");
	  rs=pst.executeQuery();rs.first();
	  designation=rs.getString("designation_id");
	  department=rs.getString("department_id");
	  pst=conn1.prepareStatement("SELECT institution_id from department_master where id="+department);
	  rs=pst.executeQuery();rs.first();
	  selinstitution=rs.getString("institution_id");
	  rs.close();pst.close();
	}
	%>
	<form id="editUserForm" name="editUserForm" action="../UserDetailsServlet?action=EDIT_USER" method="post">
	  <input type="hidden" name="userId" id="userId" value="<%=userId%>" />
	  <input type="hidden" name="userName" id="userName" value="<%=userName%>" />
	  <div style="text-align:center;font-size:150%;font-weight:bold;margin-top:1%;">
		<%=ml.getValue("edit_faculty_details")%>
	  </div>
	  <br/>
	  <table class="search_field_div" align="center" width="98%">  	
	   <tr>
		<td width="25%"><label class="labeltext"><%=ml.getValue("title")%>:</label><label class="mandatory">*</label></td>
		<td width="73%"><select id="title" name="title" >
		<%
			String title_value = "";
		%>
		<option value="null">-Select-</option>
		<%
		try
		{	
	 		conn1 = db.getMysqlConnection();
	 		pst = conn1.prepareStatement("SELECT fld_value FROM general_master WHERE category='title' AND active_yes_no=1 ORDER BY fld_value");
	 		rs=pst.executeQuery();
	 		while(rs.next()){
		 	title_value = rs.getString("fld_value"); 
		%>
		<option value=<%=title_value%>><%=title_value%></option>
		<%	
	  		}
	 	}catch(Exception e){}
	 	%>
		</td>	
		</tr>
		<tr>		   
		<td width=><label class="labeltext"><%=ml.getValue("first_name")%>:</label><label class="mandatory">*</label></td>
		<td><input class="textmedium" id="firstName" name="firstName" ></td>		   
		</tr>	
	
		<tr>		   
		<td width=><label class="labeltext"><%=ml.getValue("last_name")%>:</label></td>
		<td><input class="textmedium" id="lastName" name="lastName" ></td>		   
		</tr>	
		<tr>                                  
	      <td><label class="labeltext"><%=ml.getValue("email")%>:</label><label class="mandatory">*</label></td>
	      <td><input  class="textmedium" id="emailId" name="emailId" ></td>
	    </tr>
	    <tr>
	      <td><label class="labeltext"><%=ml.getValue("institution")%>:</label><label class="mandatory">*</label></td>
	      <td><Select name="institution"  id="institution"  style="width:200px" Disabled onchange="loadDepartments('EDIT_USER');" >	
	      <%    
		  String inst_id="";
		  String inst_name="";%>
		  <option value="null">-Select-</option>
	      <%
	      try{	
		    conn1 = db.getMysqlConnection();
            if(getUserDetails.getRole(request.getUserPrincipal().getName()).equals("ROLE_ADMIN_UNIVERSITY")){		
			  pst=conn1.prepareStatement("SELECT id,name FROM institution_master  where active_yes_no=1 and university_id="+ getUserDetails.getUniversityID(request.getUserPrincipal().getName())+" ORDER BY name");
		    }else{
		      pst=conn1.prepareStatement("SELECT id,name FROM institution_master  where active_yes_no=1 ORDER BY name");				
		    }	
		    rs=pst.executeQuery();
		    while(rs.next()){		 
		      inst_id=rs.getString(1);	 		 
		      if(selinstitution==""){		 	
		 	    selinstitution=inst_id;
		      }
		      inst_name=rs.getString(2);
		  %>	 
		  <option value=<%=inst_id%>><%=inst_name%></option>	
		  <%			
		    }
	      }catch(Exception e){
		  }
		  %>
	      </select>
	      <%  
	      if (request.getParameter("institution")!=null){
		    title=request.getParameter("title");
		    firstName=request.getParameter("firstName");
		    lastName=request.getParameter("lastName");
			emailId=request.getParameter("emailId");
			selinstitution=request.getParameter("institution"); 
		    designation=request.getParameter("designation");
		    role=request.getParameter("role");
		    userId=request.getParameter("uId"); 
		    userName=request.getParameter("uName"); 
		    joining_date=request.getParameter("joining_date"); 
	      %>		
		<script>		
		getSelectedInstitution(<%=selinstitution%>);
		document.forms[0].institution.focus();
		</script>
	<%}%>
	    </td>
	    </tr>  
	    <tr>
	      <td><label class="labeltext"><%=ml.getValue("department")%></label><label class="mandatory">*</label></td>
	      <td><Select name="department" id="department"  style="width:200px">
	        <%    		
			conn1.close();
			conn1=null;
			String dept_id="";
			String dept_name="";
			%>
			<option value="null">-Select-</option>
		    <%	
		    try{	
			  conn1 = db.getMysqlConnection();
			  pst=conn1.prepareStatement("SELECT department_master.id,fld_value,institution_id FROM `general_master` INNER JOIN `department_master` ON  `general_master`.id=department_master.department_id  AND department_master.institution_id="+selinstitution + "  AND  department_master.active_yes_no=1 ORDER BY fld_value");
			  rs=pst.executeQuery();		
			  while(rs.next()){	
			    dept_id=rs.getString(1);	 
			    dept_name=rs.getString(2);
			%>	 
			<option value=<%=dept_id%>><%=dept_name%></option>	
			<%			
			  }
		    }catch(Exception e){
			}
			%>
	      </select></td>
     	</tr>
     	<tr>
		  <td><label class="labeltext"><%=ml.getValue("designation")%>:</label><label class="mandatory">*</label></td>
		  <td><Select name="designation" id="designation"  style="width:200px">	
		  <%
		  conn1.close();		
		  conn1=null;
		  String desg_id="";
		  String desg_name="";	%>		
		  <option value="null">-Select-</option>
		  <%				
		  try{	
		    conn1 = db.getMysqlConnection();
			pst=conn1.prepareStatement("SELECT id,fld_value FROM `general_master` WHERE category='Designation' AND  active_yes_no=1  ORDER BY fld_value");				
			rs=pst.executeQuery();		
			while(rs.next()){	
			  desg_id=rs.getString(1);	 
			  desg_name=rs.getString(2);
		  %>	 
		  <option value=<%=desg_id%>><%=desg_name%></option>	
		  <%			
			}
		  }catch(Exception e){
		  }
		  conn1.close();
		  %>
		  </select></td>
		</tr>
	    <tr>
	      <td><label class="labeltext"><%=ml.getValue("role")%>:</label><label class="mandatory">*</label></td>
	      <td>
	      <select name="role" id="role"  style="width:200px">
	        <option value="null">-Select-</option>
	        <%
	        try{	
		      conn1 = db.getMysqlConnection();
		      pst = null;
		      String role_name = "";
		      if(getUserDetails.getRole(request.getUserPrincipal().getName()).equals("ROLE_ADMIN")==false){		                	
				  pst=conn1.prepareStatement("SELECT role_name FROM roles WHERE active_yesno='1' AND role_name<>'ROLE_ADMIN' ORDER BY role_name");			
			  }else{
				  pst=conn1.prepareStatement("SELECT role_name FROM roles WHERE active_yesno='1' ORDER BY role_name");			
		      }
		      rs=pst.executeQuery();
		      while(rs.next()){		
			    role_name=rs.getString(1);	 
			    %>	 
			    <option value=<%=role_name%>><%=role_name%></option>
			    <%
			  }
		    }catch(Exception e){
		    }
		    %>
	      </select>
	      <% 
	      if (request.getParameter("institution")!=null || selinstitution!=null){
		  %>
		    <script>
			  reAssign("EDIT_USER",'<%=title%>','<%=firstName%>','<%=lastName%>',"","","",'<%=emailId%>',"",'<%=designation%>','<%=role%>','<%=selinstitution%>','<%=department%>','<%=userId%>','<%=userName%>','<%=joining_date%>','<%=openId%>');
			</script>		
		  <%
		  }
		  %>
	      </td>
	    </tr>
	<tr>
	<%
		try
		{	
			conn1 = db.getMysqlConnection();
	 		pst = conn1.prepareStatement("SELECT join_date FROM staff_master WHERE userid="+userId+" AND active_yesno=1");
	 		ResultSet rs_join_date=null;
	 		rs_join_date=pst.executeQuery();
			rs_join_date.next();
			if (rs_join_date.getString("join_date")!=null){			
				joining_date=rs_join_date.getString("join_date");	  		
			}else{
				joining_date="''";
			}	
	 	}catch(Exception e){}
	 	 conn1.close();
	 	%>
	    <td><%=ml.getValue("joining_date")%><label class="mandatory">*</label></td>	    
	    <td><input type="text" id="joining_date" name="joining_date" value=<%=joining_date%> readonly="readonly" size="10" /></td>
	  </tr>
	  <tr>  
	    <td><%=ml.getValue("openId")%></td>	    
	    <td><input type="text" id="openId" name="openId" value='<%=openId%>' class="textmedium" /></td>	    
	</tr>    
	    <tr>		  
	      <td></td>	
	      <td ><input type="button"  onclick="editUserDetails()"  value="<%=ml.getValue("save")%>" />                 
              </td>
	    </tr>	
	  </table> 	
	</form>
	<%
}
String str=null;
String rowclass="1";
String userName = null;
String university=null;
String institution=null;
String classname=null;
int userId = 0;
String email=null;
String title=null;
String firstName=null;
String lastName=null;
String deptname=null;
String designation=null;
Connection conn1=null;
Connection conn2=null;
int documentId = 0;
int staffCount=0;
%>
<table style="margin-left:1%;" class="ListTable" width="98%" >
<TR>
<TH class="hidetd">User ID</TH>
<TH class="hidetd">Document ID</TH>
<TH class="ListHeader"><%=ml.getValue("title")%></TH>
<TH class="ListHeader"><%=ml.getValue("first_name")%></TH>
<TH class="ListHeader"><%=ml.getValue("last_name")%></TH>
<TH class="ListHeader"><%=ml.getValue("university")%></TH>
<TH class="ListHeader"><%=ml.getValue("institution")%></TH>
<TH class="ListHeader"><%=ml.getValue("department")%></TH>
<TH class="ListHeader"><%=ml.getValue("email")%></TH>
<TH class="ListHeader"><%=ml.getValue("designation")%></TH>
<TH class="ListHeader"><%=ml.getValue("edit")%></TH>
<TH class="hidetd"><%=ml.getValue("approve")%></TH>
<TH></TH>
</TR>
<%
try{
  String role_name="";
  int approvedyn=0;
  conn1 = db.getMysqlConnection();
  String qry="SELECT users.id,`university_master`.name AS `University` ,";
	qry=qry + "	institution_master.`name` AS `Institution` ,";
	qry=qry + "	`general_master`.fld_value AS `Department`,";
	qry=qry + "	users.title,users.`user_full_name`,";
	qry=qry + " users.last_name,users.username ,users.`email`,";
	qry=qry + "	A.fld_value AS `Designation`";
	qry=qry + "	FROM staff_master";
	qry=qry + "	INNER JOIN `department_master` ";
	qry=qry + "	ON `department_master`.`id`=`staff_master`.`department_id` and staff_master.active_yesno=1";
	qry=qry + "	INNER JOIN `institution_master` ";
	qry=qry + "	ON `institution_master`.id=`department_master`.`institution_id` and institution_master.name like ?";
	qry=qry + "	INNER JOIN `university_master` ON `university_master`.id=`institution_master`.`university_id` AND university_master.Name like ?";
	qry=qry + "	INNER JOIN `general_master`";
	qry=qry + "	ON `general_master`.id =`department_master`.`department_id` AND category='Department' and fld_value like ?";
	qry=qry + "	INNER JOIN general_master A ON `A`.id =`staff_master`.`designation_id` ";
	qry=qry + "	AND A.category='Designation'";
	qry=qry + "	INNER JOIN users ON `staff_master`.userid=users.id and users.enabled=1 AND NOT users.username='admin'  AND users.user_full_name like ?";
	
	if(getUserDetails.getRole(request.getUserPrincipal().getName()).equals("ROLE_ADMIN_UNIVERSITY")){		
		qry=qry + " and	university_master.name='"+ getUserDetails.getUniversity(request.getUserPrincipal().getName())+"'";
	}
	qry=qry + "	ORDER BY university_master.Name,institution_master.name,users.username";
	PreparedStatement pst=conn1.prepareStatement(qry);
	
	pst.setString(1,"%");
	pst.setString(2,"%");
	pst.setString(3,"%");
	pst.setString(4,"%");
		
	ResultSet rs=pst.executeQuery();
	while(rs.next())
	{	staffCount=staffCount+1;
		userId=rs.getInt("id");
		title=rs.getString("title");
		firstName=rs.getString("user_full_name"); 	
		lastName=rs.getString("last_name");
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
		  <td <%=classname%>><%=title%></td>
		  <td <%=classname%>><%=firstName%></td>
		  <td <%=classname%>><%=lastName%></td>
		  <td <%=classname%>><%=university%> </td>
		  <td <%=classname%>><%=institution%> </td>
		  <td <%=classname%>><%=deptname%> </td>
		  <td <%=classname%>><%=userName%> </td>
		  <td <%=classname%>><%=designation%> </td>
		  <td <%=classname%> align="center">
		    <input type="button" value="<%=ml.getValue("edit")%>" onclick="showStaffProfile(<%=userId%>,'Edit')">
		  </td>
		  </tr>
	
	<%}
	conn1.close();
	
}catch(Exception e){
	e.printStackTrace();
}  
%>
</table>

<table  align="left" >
<tr>
<td class="search_resul_category">&nbsp;&nbsp;&nbsp;<%=ml.getValue("total_faculty")%>:&nbsp;<%=staffCount%>&nbsp;&nbsp;&nbsp;</td>
</tr>
</table>
<br>

</div>

</BODY></HTML>