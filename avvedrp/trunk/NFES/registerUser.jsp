<%@ page contentType="text/html; charset=utf-8" pageEncoding="UTF-8" language="java" %>
<%
   String action = request.getParameter("action");
   String code = request.getParameter("code");
   String name = "";
   String userName = "";
   String password = "";
   String confirmPassword = "";
   String email = "";
   String universityName = "";
   String shortName = "";
   String address = "";
   String location = "";
   if(action.equals("ERROR_MESSAGE")){
     name = request.getParameter("name");
     userName = request.getParameter("username");
     password = request.getParameter("password");
     confirmPassword = request.getParameter("confirmpassword");
     email = request.getParameter("email");
     universityName = request.getParameter("universityname");
     shortName = request.getParameter("shortname");
     address = request.getParameter("address");
     location = request.getParameter("location");
   }
%>
<html>
<head>
<title>User Registration Page</title>

<script type="text/javascript">
var ps="";
function passwordChanged()
{
	var strength = document.getElementById("strength");
	var strongRegex = new RegExp("^(?=.{8,})(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])(?=.*\\W).*$", "g");
	var mediumRegex = new RegExp("^(?=.{7,})(((?=.*[A-Z])(?=.*[a-z]))|((?=.*[A-Z])(?=.*[0-9]))|((?=.*[a-z])(?=.*[0-9]))).*$","g");
	var enoughRegex = new RegExp("(?=.{6,}).*", "g");
	var pwd = document.getElementById("password");
	if (pwd.value.length==0) {
		strength.innerHTML = 'Type Password';ps="";
	} else if (false == enoughRegex.test(pwd.value)) {
		strength.innerHTML = 'More Characters';ps="MC";
	} else if (strongRegex.test(pwd.value)) {
		strength.innerHTML = '<span style="color:green;">Strong</span>';ps="S";
	} else if (mediumRegex.test(pwd.value)) {
		strength.innerHTML = '<span style="color:orange;">Medium</span>';ps="M";
	} else { 
		strength.innerHTML = '<span style="color:red;">Weak</span>';ps="W";
	}
}
function validateEmail(email) {   
    var re = /^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
	//"
    if (email.match(re)) {
    	return (false);
    }
    return(true);    
}
function validateForm(){
  if(document.getElementById("name").value==""){
    alert("Name shouldn't left blank");
    document.getElementById("name").focus();
  }else if(document.getElementById("username").value==""){
    alert("User Name shouldn't left blank");
    document.getElementById("username").focus();
  }else if(validateEmail(document.getElementById("username").value)){
    alert("You should enter a valid Email as User Name");
    document.getElementById("username").focus();
  }else if(ps==""){
    alert("Password shouldn't left blank");
     document.getElementById("password").focus();
  }else if(ps=="MC"){
    alert("Password must had 6 characters");
    document.getElementById("password").focus();
  }else if(ps=="W"){
    alert("Password must had medium strength");
  }else if(document.getElementById("confirmpassword").value==""){
    alert("Confirm Password shouldn't left blank");
    document.getElementById("confirmpassword").focus();
  }else if(document.getElementById("password").value!=document.getElementById("confirmpassword").value){
    alert("Confirm Password: Passwords you entered are not matching");
    document.getElementById("confirmpassword").focus();
  }else if(document.getElementById("email").value==""){
    alert("Email shouldn't left blank");
    document.getElementById("email").focus();
  }else if(validateEmail(document.getElementById("email").value)){
    alert("You should enter a valid Email");
    document.getElementById("email").focus();
  }else if(document.getElementById("universityname").value==""){
    alert("University Name shouldn't left blank");
    document.getElementById("universityname").focus();
  }else if(document.getElementById("shortname").value==""){
    alert("Short Name shouldn't left blank");
    document.getElementById("shortname").focus();
  }else if(document.getElementById("location").value==""){
    alert("Location shouldn't left blank");
    document.getElementById("location").focus();
  }else if(document.getElementById("address").value==""){
    alert("Address shouldn't left blank");
    document.getElementById("address").focus();
  }else{
    document.getElementById("action").value = "REGISTER_UNIVERSITY";
    document.userRgistrationForm.submit();
  }
}
function init(){
  if(document.getElementById("action").value=="ERROR_MESSAGE"){
    document.getElementById("name").value='<%=name%>';
    document.getElementById("username").value='<%=userName%>';
    document.getElementById("password").value='<%=password%>';
    document.getElementById("confirmpassword").value='<%=confirmPassword%>';
    document.getElementById("email").value='<%=email%>';
    document.getElementById("universityname").value='<%=universityName%>';
    document.getElementById("shortname").value='<%=shortName%>';
    document.getElementById("address").value='<%=address%>';
    document.getElementById("location").value='<%=location%>';
    if(document.getElementById("code").value=="1"){
      alert("User Name already exists.");
       document.getElementById("username").focus();
       changeBorderColor(document.getElementById("username"));
    }else  if(document.getElementById("code").value=="2"){
      alert("Email already exists.");
      document.getElementById("email").focus();
      changeBorderColor(document.getElementById("email"));
    }else  if(document.getElementById("code").value=="3"){
      alert("University Name already exists.");
      document.getElementById("universityname").focus();
      changeBorderColor(document.getElementById("universityname"));
    }
  }else if(document.getElementById("action").value=="SUCCESS_MESSAGE"){
    if(document.getElementById("code").value=="1"){
      alert("You have Succesfully created an account.");
    }
  }
}
function changeBorderColor(element){
  element.className="textbox-red";
}
</script>

<style type="text/css">
.bg{
   background-color:#CCE6F3;
}
.body{
   width:100%;
   height:98%;
}
.header{
   width:100%;
   height:21%;
}
.content{
   width:100%;
   height:71%;
}
.footer{
   width:100%;
   height:3%;
   border:1px solid #82888F;
   border-radius:8px;
   padding-top:4px;
   color: #333333;
   font-family: Verdana, Helvetica, sans-serif;font-size: 10px;font-weight: bold;
   background-color:#9DC6D9;    
   text-align:center;
}
.content-in{
   background:url("./images/tablebg.jpg") repeat-x scroll 0 0 #9DC6D9;
   margin-left:2%;
   margin-right:2%;
   margin-top:2%;
   margin-bottom:2%;
   border:1px solid #9DC6D9;
   border-radius:10px;
   width:96%;
   height:96%;
}
.content-title{
   width:100%;
   text-align:center;
   font-family: Verdana, Helvetica, sans-serif;
   font-size: 15px;
   font-weight: bold;
}
.content-in-left{
   float:left;
   margin-left:2%;
   margin-top:1%;
   margin-bottom:3%;
   border:1px solid #CCE6F3;
   border-radius:10px;
   width:47%;
   height:75%;
}
.content-in-right{
   float:right;
   margin-left:2%;
   margin-right:2%;
   margin-top:1%;
   margin-bottom:3%;
   border:1px solid #CCE6F3;
   border-radius:10px;
   width:45%;
   height:75%;
}
.table-left{
   margin-top:2%;
   margin-left:4%;
   width:96%;
   height:85%; 
}
.table-right{
   margin-top:2%;
   margin-left:5%;
   width:90%;
   height:93%; 
}
.textbox{
   color: #3A3A3A;
   font-family: Georgia,serif;
   font-size: 13px;
   padding: 0;
   width: 200px;
}
.textarea{
   color: #3A3A3A;
   font-family: Georgia,serif;
   font-size: 13px;
   padding: 0;
   width: 200px;
   height:70px;
   resize: none;
}
.error-message{
   width:100%;
   text-align:center;
   font-family: Verdana, Helvetica, sans-serif;
   font-size: 15px;
  // font-weight: bold;
   color:red;
   margin-top:1px;
   margin-bottom:1px;
}
.success-message{
   width:100%;
   height:25%;
   text-align:center;
   font-family: Verdana, Helvetica, sans-serif;
   font-size: 25px;
   font-weight: bold;
   color:green;
   margin-top:11%;
}
.textbox-red{
  color: #3A3A3A;
  font-family: Georgia,serif;
  font-size: 13px;
  padding: 0;
  width: 200px;
  border:1px solid red;
}
</style>
</head>
<body class="bg" onload="init();">
<div id="body" class="body">
  <div id="header" class="header">
    <table width=101% style="background-color:#FFFFFF;margin-left:-5px;margin-right:0px;">
      <tr>
        <td width="5%" rowspan="2"><img src="./images/loginheader_logo.PNG" ></td>
      </tr>	
      <tr>
        <td colspan="2" width="85%" align="right" valign="bottom"><img src="./images/loginheader_NFES.PNG" ></td>	
        <td width="10%"></td>
      </tr>
    </table>
    <div style="background-image: url('./images/innerpageheaderhr.jpg');background-repeat:repeat-x;margin-left: -8px; margin-right: -8px;">&nbsp;</div>
  </div>
  <form name="userRgistrationForm" action="./ProfileCreationActivationServlet" method="post">
  <input type="hidden" name="action" id="action" value='<%=action%>' />
  <input type="hidden" name="code" id="code" value='<%=code%>' />
  <div id="content" class="content">
    <div id="content-in" class="content-in">
    <% 
      if(action.equals("REGISTER_UNIVERSITY")||action.equals("ERROR_MESSAGE")){  
    %>
      <div class="content-title"><h3>User Registration</h3></div>
      <div class="content-in-left">
        <table class="table-left" cellspacing="0" cellpadding="0">
          <tr>
	    <td width="100%" colspan="2">
	    <%
	       if(action.equals("ERROR_MESSAGE")){
	         if(code.equals("1")){
	         %>
	            <div class="error-message">User Name already exists.</div>
	         <%
	         }else  if(code.equals("2")){
	         %>
	            <div class="error-message">Email already exists.</div>
	         <%
	         }else{
	         %>
	            <div>&nbsp;</div>
	         <%
	         }
	       }else{
	       %>
	          <div>&nbsp;</div>
	       <%
	       }
            %>
	    </td>
          </tr>
          <tr>
	    <td width="30%">Name:<label style="color: red;">*</label></td><td width="70%"><input type="text" id="name" name="name" class="textbox" /></td>
          </tr>
          <tr>
            <td width="30%">User Name:<label style="color: red;">*</label></td><td width="70%"><input type="text" id="username" name="username" class="textbox" /></td>
          </tr>
          <tr>
	    <td width="30%">Password:<label style="color: red;">*</label></td><td width="70%"><input type="password" name="password" id="password" class="textbox" onkeyup="return passwordChanged();" /><span id="strength"></span></td>
          </tr>
          <tr>
	    <td width="30%">Confirm Password:<label style="color: red;">*</label></td><td width="70%"><input type="password" name="confirmpassword" id="confirmpassword" class="textbox" /></td>
          </tr>
          <tr>
	    <td width="30%">Email:<label style="color: red;">*</label></td><td width="70%"><input type="text" id="email" name="email" class="textbox" /></td>
          </tr>
        </table>
      </div>
      <div class="content-in-right">
        <table class="table-right" cellspacing="0" cellpadding="0">
	  <tr>
	    <td width="100%" colspan="2">
	    <%
	      if(action.equals("ERROR_MESSAGE")){
	        if(code.equals("3")){
	        %>
	          <div class="error-message">University Name already exists.</div>
	        <%
	        }else{
	      	%>
	      	  <div>&nbsp;</div>
	      	<%
	        }
	      }else{
	      %>
	        <div>&nbsp;</div>
	      <%
	      }  
            %>
	    </td>
	  </tr>
	  <tr>
	    <td width="40%">University Name:<label style="color: red;">*</label></td><td width="60%"><input type="text" id="universityname" name="universityname" class="textbox" /></td>
	  </tr>
	  <tr>
	    <td width="40%" >Short Name:<label style="color: red;">*</label></td><td width="60%" ><input type="text" id="shortname" name="shortname" class="textbox" /></td>
	  </tr>
	  <tr>
	    <td width="40%" valign="bottom">Location:<label style="color: red;">*</label></td><td width="60%" valign="bottom"><input type="text" id="location" name="location" class="textbox" /></td>
	  </tr>
	  <tr>
	    <td width="40%">Address:<label style="color: red;">*</label></td><td width="60%"><textarea id="address" name="address" class="textarea"></textarea></td>
	  </tr>
	  <tr>
	    <td colspan="2" width="100%" style="text-align: right;padding-right:8%;" valign="top"><input type="button" id="createaccount" name="createaccount" value="Create Account" onclick="validateForm();" /></td>
	  </tr>
        </table>
      </div>
    <% 
      }else if(action.equals("SUCCESS_MESSAGE")){
        if(code.equals("1")){
        %>
          <div class="success-message">
            You have Succesfully created an account.<br /><br /> Account Information details sent to the user's email.
          </div>
        <%
        } 
      }
    %>  
    </div>
  </div>
  </form>
  <div id="footer" class="footer">
    Developed by Amrita University under ERP, NME ICT, MHRD
  </div>
</div>
</body>
</html>