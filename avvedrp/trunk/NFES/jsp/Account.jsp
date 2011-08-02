<%@ page contentType="text/html; charset=utf-8" pageEncoding="UTF-8" language="java" import="javax.sql.DataSource,javax.naming.Context,javax.naming.InitialContext,java.sql.*,java.util.*,java.io.FileInputStream" errorPage="" %>

<%
Connection conn=null;
Statement theStatement=null;
ResultSet theResult=null;
String faculty_name="";String user_name="";String password="";String confirm_password="";String email="";String confirm_email=""; String lc="";
String institution="";String create_account="";String type_password="";String more_characters="";String weak="";String medium="";String strong="";
String department1="";String designation1="";
try{
     lc=(String) session.getAttribute("language");
     Properties properties = new Properties();
     properties.load(new FileInputStream("../conf/db.properties"));
     String dbname = properties.getProperty("dbname");
     String username = properties.getProperty("username");
     String password1 = properties.getProperty("password");
     Class.forName("org.gjt.mm.mysql.Driver");
     conn=DriverManager.getConnection("jdbc:mysql:"+dbname+"?characterSetResults=UTF-8&characterEncoding=UTF-8&useUnicode=yes",username,password1);
     theStatement=conn.createStatement();
     theResult=theStatement.executeQuery("select control_name,language_string from language_localisation where active_yes_no=1 and file_code=24 and language_code=\'"+lc+"\'");
     theResult.last();int len=theResult.getRow();String cn[]=new String[len];String ls[]=new String[len];
     int i=0;theResult.beforeFirst();
     while(theResult.next()){
          cn[i]=theResult.getString("control_name");
          ls[i]=theResult.getString("language_string");
          i++;
     }
     
     for(i=0;i<len;i++){
     	if(cn[i].equals("faculty_name")){
     		faculty_name=ls[i];
     	}else if(cn[i].equals("user_name")){
     		user_name=ls[i];
     	}else if(cn[i].equals("password")){
     		password=ls[i];
     	}else if(cn[i].equals("confirm_password")){
     		confirm_password=ls[i];
     	}else if(cn[i].equals("email")){
     		email=ls[i];
     	}else if(cn[i].equals("confirm_email")){
     		confirm_email=ls[i];
     	}else if(cn[i].equals("institution")){
     		institution=ls[i];
     	}else if(cn[i].equals("create_account")){
     		create_account=ls[i];
     	}else if(cn[i].equals("type_password")){
     		type_password=ls[i];
     	}else if(cn[i].equals("more_characters")){
     		more_characters=ls[i];
     	}else if(cn[i].equals("weak")){
     		weak=ls[i];
     	}else if(cn[i].equals("medium")){
     		medium=ls[i];
     	}else if(cn[i].equals("strong")){
     		strong=ls[i];
     	}else if(cn[i].equals("department")){
     		department1=ls[i];
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
<HEAD profile=http://gmpg.org/xfn/11>
<TITLE>Staff Registration</TITLE>


<LINK media=screen href="../css/oiostyles.css" type=text/css rel=stylesheet>

<META content="MSHTML 6.00.2900.5694" name=GENERATOR>
<%@ taglib prefix='c' uri='http://java.sun.com/jstl/core_rt' %>


<script language="javascript">
var ps="";
function createAccount(){
	var frmObj = document.forms['userForm'];	
	if( checkValues() ){			
		var frmObj = document.forms['userForm'];
		var answer = confirm ("Create an account?");
		if (answer){			     
			     document.forms['userForm'].submit();			     
			   }
		else{			
			return false;			
		    }		
		}		
	}	


function checkValues(){	
	var frmObj = document.forms['userForm'];
	var userFullName=frmObj.userFullName.value;
	var user = frmObj.userName.value;
	var pwd = frmObj.userPassword.value;
	var email = frmObj.emailId.value;
	var pwd2 = frmObj.userPassword2.value;
	var email2 = frmObj.emailId2.value;
	var institution=frmObj.institution.value;
	var department=frmObj.department.value;
	var designation=frmObj.designation.value;	
	var f = true;
	if( userFullName=="" || user == "" || pwd == "" || email == "" || institution=="null" || department=="null" || designation=="null" ){
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

<!-- Added by ManuSoman to prevent Special Symbol Entry in UserName Field for Bug#22921 -->
<!-- This fuction allows only alphanumerical characters,undescore,and dot in UserName field --> 
var r={'special':/[^a-zA-Z0-9_.]/g}
function valid(o,w)
{
  o.value = o.value.replace(r[w],'');
} 
<!-- End of Added by ManuSoman -->

function passwordChanged()
{
	var strength = document.getElementById("strength");
	var strongRegex = new RegExp("^(?=.{8,})(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])(?=.*\\W).*$", "g");
	var mediumRegex = new RegExp("^(?=.{7,})(((?=.*[A-Z])(?=.*[a-z]))|((?=.*[A-Z])(?=.*[0-9]))|((?=.*[a-z])(?=.*[0-9]))).*$","g");
	var enoughRegex = new RegExp("(?=.{6,}).*", "g");
	var pwd = document.getElementById("userPassword");
	if (pwd.value.length==0) {
		strength.innerHTML = '<%=type_password%>';ps="";
	} else if (false == enoughRegex.test(pwd.value)) {
		strength.innerHTML = '<%=more_characters%>';ps="MC";
	} else if (strongRegex.test(pwd.value)) {
		strength.innerHTML = '<span style="color:green"><%=strong%></span>';ps="S";
	} else if (mediumRegex.test(pwd.value)) {
		strength.innerHTML = '<span style="color:orange"><%=medium%></span>';ps="M";
	} else { 
		strength.innerHTML = '<span style="color:red"><%=weak%></span>';ps="W";
	}
}

function loadDepartments()

{
facultyname=document.getElementById("userFullName").value;

userPassword=document.getElementById("userPassword").value;  
userName=document.getElementById("userName").value;
userPassword2=document.getElementById("userPassword2").value;  
emailId=document.getElementById("emailId").value;  
emailId2=document.getElementById("emailId2").value;  
selinstitution=document.getElementById("institution").value;
designation=document.getElementById("designation").value;
location.href="Account.jsp?facultyname='"+facultyname+"'&userName='"+userName+"'&userPassword='"+userPassword+"'&userPassword2='"+userPassword2+"'&emailId='"+emailId+"'&emailId2='"+emailId2+"'&institution='"+selinstitution+"'&designation="+designation+"" 
}

function getSelectedInstitution(selinstitution)
{
document.getElementById("institution").value=selinstitution;
}

function reAssign(facultyname,userName,userPassword,userPassword2,emailId,emailId2,designation)
{
document.getElementById("userFullName").value=facultyname;
document.getElementById("userName").value=userName;
document.getElementById("userPassword").value=userPassword;  
document.getElementById("userPassword2").value=userPassword2;  
document.getElementById("emailId").value=emailId;  
document.getElementById("emailId2").value=emailId2;  
document.getElementById("designation").value=designation;

}

</script>



</HEAD>
<BODY class="bodystyle">

<form id="userForm" action="../ProfileCreationServlet" method="post"  >

<div  class="listdiv">

 <% 
 String facultyname="";
 String userName="";
 String userPassword="";
 String userPassword2="";
 String emailId="";
 String emailId2="";
 String selinstitution="";
 String designation="";
 
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
%>
<br>

<table style="background: none repeat scroll 0 0 #CCE6F3;" align="center" width="98%">  	
	<!--<tr>
	<td rowspan="7"><img src="../images/StaffReg.gif"></img></td>			
	</tr>
	-->
	<tr>		   
	<td width=><label class="labeltext"><%=faculty_name%>:</label><label class="mandatory">*</label></td>
	<td><input class="textmedium" id="userFullName" name="userFullName" ></td>		   
	</tr>	
	
	
	<tr>		   
	<td width=><label class="labeltext"><%=user_name%>:</label><label class="mandatory">*</label></td>
	<td><input class="textmedium" id="userName" name="userName" ></td>		   
	</tr> 
				   
	<tr> 		                 
	<td><label class="labeltext"><%=password%>:</label><label class="mandatory">*</label></td>
	<td><input type="password" id="userPassword"  name="userPassword" class="textmedium" onkeyup="return passwordChanged();" />
	<span id="strength" class="labeltext"><%=type_password%></span>
	</td>
	</tr>
	
	<tr>
	<td><label class="labeltext"><%=confirm_password%>:</label><label class="mandatory">*</label></td>
	<td><input type="password" id="userPassword2"  name="userPassword2" class="textmedium"></td>                
	</tr>                 
		 
	<tr>                                  
	<td><label class="labeltext"><%=email%>:</label><label class="mandatory">*</label></td>
	<td><input  class="textmedium" id="emailId" name="emailId" ></td>
	</tr>
	
	<tr>
	<td><label class="labeltext"><%=confirm_email%>:</label><label class="mandatory">*</label></td>
	<td><input  class="textmedium" id="emailId2" name="emailId2" ></td>                 
	</tr>		
		
	<tr>
	<td><label class="labeltext"><%=institution%>:</label><label class="mandatory">*</label></td>
	<td><Select name="institution"  id="institution" onchange="loadDepartments();" >	
	<%    
		Connection conn1=null;
		String inst_id="";
		String inst_name="";%>
		<option value="null">-Select-</option>
	<%
	try
	{	
			
		Properties properties = new Properties();
		properties.load(new FileInputStream("../conf/db.properties"));	
		String dbname = properties.getProperty("dbname");		
		String username = properties.getProperty("username");
		String password2 = properties.getProperty("password");				
		Class.forName("org.gjt.mm.mysql.Driver");	
		conn1=DriverManager.getConnection("jdbc:mysql:"+dbname,username,password2);		
		PreparedStatement pst=conn1.prepareStatement("SELECT id,name FROM institution_master  where active_yes_no=1 ORDER BY name");				
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
		facultyname=request.getParameter("facultyname");
		userName=request.getParameter("userName");
		userPassword=request.getParameter("userPassword");
		userPassword2=request.getParameter("userPassword2");		
		emailId=request.getParameter("emailId");
		emailId2=request.getParameter("emailId2");
		selinstitution=request.getParameter("institution"); 
		designation=request.getParameter("designation");				 		
		%>		
		<script>		
		getSelectedInstitution(<%=selinstitution%>);
		document.forms[0].institution.focus();
		</script>
	<%}%>
	</td>
	</tr>
	
	<tr>
	<td><label class="labeltext"><%=department1%>:</label><label class="mandatory">*</label></td>
	<td><Select name="department" id="department" >
	<%    		
			
			conn1=null;
			String dept_id="";
			String dept_name="";	%>
			<option value="null">-Select-</option>
		<%	
		try
		{	
				
			Properties properties = new Properties();
			properties.load(new FileInputStream("../conf/db.properties"));	
			String dbname = properties.getProperty("dbname");		
			String username = properties.getProperty("username");
			String password2 = properties.getProperty("password");				
			Class.forName("org.gjt.mm.mysql.Driver");	
			conn1=DriverManager.getConnection("jdbc:mysql:"+dbname,username,password2);					
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
		<td><label class="labeltext"><%=designation1%>:</label><label class="mandatory">*</label></td>
		<td><Select name="designation" id="designation">	
		<%    				
				conn1=null;
				String desg_id="";
				String desg_name="";	%>		
				<option value="null">-Select-</option>
		<%				
			try
			{	
					
				Properties properties = new Properties();
				properties.load(new FileInputStream("../conf/db.properties"));	
				String dbname = properties.getProperty("dbname");		
				String username = properties.getProperty("username");
				String password2 = properties.getProperty("password");				
				Class.forName("org.gjt.mm.mysql.Driver");	
				conn1=DriverManager.getConnection("jdbc:mysql:"+dbname,username,password2);		
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
			 }catch(Exception e)
			{
				}
				
		if (request.getParameter("institution")!=null){
		%>
		<script>
		reAssign(<%=facultyname%>,<%=userName%>,<%=userPassword%>,<%=userPassword2%>,<%=emailId%>,<%=emailId2%>,<%=designation%>);</script>		
		<%}%>
		</select></td>
		</tr>
	
	
	<tr>		  
	<td><img src="../images/StaffReg.gif"></td>	
	<td ><input type="button"  onClick="createAccount()"  value="<%=create_account%>" />	</td>
	</tr>	
	<!--<tr>
	<td>&nbsp;</td>
	<td>
	<img src="../images/arrowlback.gif"> <a href="index.jsp">Back</a> </img>
	</td>
	</tr>-->
</table>     
<br/>
</div>

 </div>
</form>
</BODY></HTML>