<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">
<%@ page contentType="text/html; charset=iso-8859-1" language="java" import="javax.sql.DataSource,javax.naming.Context,javax.naming.InitialContext,java.sql.*,java.util.*,java.io.FileInputStream" errorPage="" %>

<HTML lang=en-US dir=ltr xmlns="http://www.w3.org/11001/xhtml">
<HEAD profile=http://gmpg.org/xfn/11>
<TITLE>Staff Registration</TITLE>
<META http-equiv=Content-Type content="text/html; charset=UTF-8">


<LINK media=screen href="../css/oiostyles.css" type=text/css rel=stylesheet>

<META content="MSHTML 6.00.2900.5694" name=GENERATOR>
<%@ taglib prefix='c' uri='http://java.sun.com/jstl/core_rt' %>


<script>
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
	var user = frmObj.userName.value;
	var pwd = frmObj.userPassword.value;
	var email = frmObj.emailId.value;
	var pwd2 = frmObj.userPassword2.value;
	var email2 = frmObj.emailId2.value;
	var f = true;
	if( user == "" || pwd == "" || email == "" ){
		alert("Required Fields can not be Left Unfilled!!!");
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
		strength.innerHTML = 'Type Password';ps="";
	} else if (false == enoughRegex.test(pwd.value)) {
		strength.innerHTML = 'More Characters';ps="MC";
	} else if (strongRegex.test(pwd.value)) {
		strength.innerHTML = '<span style="color:green">Strong!</span>';ps="S";
	} else if (mediumRegex.test(pwd.value)) {
		strength.innerHTML = '<span style="color:orange">Medium!</span>';ps="M";
	} else { 
		strength.innerHTML = '<span style="color:red">Weak!</span>';ps="W";
	}
}

</script>



</HEAD>
<BODY class="bodystyle">

<form id="userForm" action="../ProfileCreationServlet" method="post"  >
<div  class="listdiv">

<br/>
<%
 if ((request.getParameter("userExists") != "")&&(request.getParameter("userExists") != null)) {%>
	<div class="errorlogin">			
		User already exists..
	</div>		             
<%  } 
 if ((request.getParameter("emailExists") != "")&&(request.getParameter("emailExists") != null)) {%>	             
	<div class="errorlogin">			
	The email you entered already exists..
	</div>	

<%  }
%>
<br>
<table style="background-color: #d5e5ed;" align="center" width="90%">  	
	<!--<tr>
	<td rowspan="7"><img src="../images/StaffReg.gif"></img></td>			
	</tr>
	-->
	<tr>		   
	<td width=><label class="labeltext">Faculty Name</label></td>
	<td><input class="textmedium" id="userFullName" name="userFullName" ></td>		   
	</tr>	
	
	
	<tr>		   
	<td width=><label class="labeltext">  Username</label></td>
	<td><input class="textmedium" id="userName" name="userName" ></td>		   
	</tr> 
				   
	<tr> 		                 
	<td><label class="labeltext">  Password</label></td>
	<td><input type="password" id="userPassword"  name="userPassword" class="textmedium" onkeyup="return passwordChanged();" />
	<span id="strength">Type Password</span>
	</td>
	</tr>
	
	<tr>
	<td><label class="labeltext"> Confirm Password</label></td>
	<td><input type="password" id="userPassword2"  name="userPassword2" class="textmedium"></td>                
	</tr>                 
		 
	<tr>                                  
	<td><label class="labeltext">  Email</label></td>
	<td><input  class="textmedium" id="emailId" name="emailId" ></td>
	</tr>
	
	<tr>
	<td><label class="labeltext"> Confirm Email</label></td>
	<td><input  class="textmedium" id="emailId2" name="emailId2" ></td>                 
	</tr>		
	
	
	<tr>
	<td><label class="labeltext"> Institution</label></td>
	<td><Select name="institution">	
	<%    
		Connection conn1=null;
		String inst_id="";
		String inst_name="";
	try
	{	
			
		Properties properties = new Properties();
		properties.load(new FileInputStream("../conf/db.properties"));	
		String dbname = properties.getProperty("dbname");		
		String username = properties.getProperty("username");
		String password = properties.getProperty("password");				
		Class.forName("org.gjt.mm.mysql.Driver");	
		conn1=DriverManager.getConnection("jdbc:mysql:"+dbname,username,password);		
		PreparedStatement pst=conn1.prepareStatement("SELECT id,name FROM institution_master  where active_yes_no=1 ORDER BY name");				
		ResultSet rs=pst.executeQuery();		
		while(rs.next())		
		{	
		 inst_id=rs.getString(1);	 
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
	</td>
	</tr>			
	<tr>		  
	<td><img src="../images/StaffReg.gif"></td>	
	<td ><input type="button"  onClick="createAccount()"  value="Create Account"/>	</td>
	</tr>	
	<!--<tr>
	<td>&nbsp;</td>
	<td>
	<img src="../images/arrowlback.gif"> <a href="index.jsp">Back</a> </img>
	</td>
	</tr>-->
</table>     
<br/>
<br/>
</div>

 </div>
</form>
</BODY></HTML>
