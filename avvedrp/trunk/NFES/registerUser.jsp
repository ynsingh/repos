<%@ page contentType="text/html; charset=utf-8" pageEncoding="UTF-8" language="java" import="java.sql.*" %>
<jsp:useBean id="db" class="com.erp.nfes.ConnectDB" scope="session"/>
<jsp:useBean id="ml" class="com.erp.nfes.MultiLanguageString" scope="session"/> 

<%

   String action = request.getParameter("action");
   String code = request.getParameter("code");
   String title = "";
   String name = "";
   String lastName = "";
   String userName = "";
   //String password = "";
   //String confirmPassword = "";
   String email = "";
   String universityName = "";
   String shortName = "";
   String address = "";
   String location = "";
   String openId="";
   if(action.equals("ERROR_MESSAGE")){
     title = request.getParameter("title");
     name = request.getParameter("name");
     lastName = request.getParameter("lastname");
     userName = request.getParameter("username");
     //password = request.getParameter("password");
     //confirmPassword = request.getParameter("confirmpassword");
     email = request.getParameter("email");
     universityName = request.getParameter("universityname");
     shortName = request.getParameter("shortname");
     address = request.getParameter("address");
     location = request.getParameter("location");
     openId=request.getParameter("openId");
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
		strength.innerHTML ='<%=ml.getValue("type_password")%>';ps="";
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
function validateEmail(email) {   
    var re = /^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
	//"
    if (email.match(re)) {
    	return (false);
    }
    return(true);    
}
function validateForm(){
  if(document.getElementById("title").value=="null"){
    alert("Please Select Tiltle");
    document.getElementById("title").focus();
  }else if(document.getElementById("name").value==""){
    alert("First Name shouldn't left blank");
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
    document.getElementById("title").value='<%=title%>';
    document.getElementById("name").value='<%=name%>';
    document.getElementById("lastname").value='<%=lastName%>'
    document.getElementById("username").value='<%=userName%>';
    document.getElementById("email").value='<%=email%>';
    document.getElementById("universityname").value='<%=universityName%>';
    document.getElementById("shortname").value='<%=shortName%>';
    document.getElementById("address").value='<%=address%>';
    document.getElementById("location").value='<%=location%>';
    document.getElementById("openId").value='<%=openId%>';
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
	else  if(document.getElementById("code").value=="4"){
      alert("OpenId already Exists!!!");
      document.getElementById("openId").focus();
      changeBorderColor(document.getElementById("openId"));
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

.header{
   width:100%;
   height:21%;
}
.content{
   width:100%;
   height:71%;
}
#footer {position: absolute; bottom: 0; left: 0px; right:0px; width: 100%; }
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
   font-family: Verdana, Helvetica, sans-serif;
   font-size: 12px;	
}
.table-right{
   margin-top:2%;
   margin-left:5%;
   width:90%;
   height:93%; 
   font-family: Verdana, Helvetica, sans-serif;
   font-size: 12px;	   
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
<LINK media=screen href="./css/oiostyles.css" type=text/css rel=stylesheet>
</head>
<body  onload="init();" class="bodystyle">
<div style="background-color: #FFFFFF; margin: 10px -6px;">
<table width=100% style="background-color: #FFFFFF; border=1; margin: 0px;">
<tr>
<td width=5% rowspan="2"><img src="./images/loginheader_logo.PNG" height="80px"  ></td>
</tr>	

<tr>
<td colspan=2 width=95%  align="right" valign="bottom"><img src="./images/loginheader_NFES.PNG" ></td>	
<td width=10%></td>
</tr>
</table>
<div style="background-image: url('./images/innerpageheaderhr.jpg');repeat-x;scroll 0 0 #ef9e00;">&nbsp;</div>
<div class="loginLink_header">
<span><font color="#174664">&nbsp;&nbsp;<img alt="Help" title="Help" onclick="window.open('./UserGuides/UserGuides.html','mywindow','width=1000,height=700,left=0,top=100,screenX=0,screenY=100')" src="/nfes/images/help.gif"><b>&nbsp;&nbsp;|</b></font></span>
<span><font color="#174664">&nbsp;&nbsp;<img alt="About Us" title="About Us" onclick="window.open('./images/AboutUs_NFES.html','mywindow','width=600,height=300,left=0,top=100,screenX=0,screenY=100')" src="/nfes/images/aboutUs.jpg"><b>&nbsp;&nbsp;|</b></font></span>
<span class="classLink">&nbsp;<a href='./login.jsp' target="body" title="Home"><font color="#174664"><b><%=ml.getValue("home")%></a>&nbsp;|</b></font></span>
</div>
</div>
</div>

<div id="body" class="body">
  <form name="userRgistrationForm" action="./ProfileCreationActivationServlet" method="post">
  <input type="hidden" name="action" id="action" value='<%=action%>' />
  <input type="hidden" name="code" id="code" value='<%=code%>' />
  <div id="content" class="content" style="margin:200px 0px 0px 0px;">
    <div id="content-in" class="content-in">
    <% 
      if(action.equals("REGISTER_UNIVERSITY")||action.equals("ERROR_MESSAGE")){  
    %>
      <div class="content-title"><h3><%=ml.getValue("university_registration")%></h3></div>
      <div class="content-in-left">
        <table class="table-left" cellspacing="0" cellpadding="0">
          <tr>
	    <td width="100%" colspan="2">
	    <%
	       if(action.equals("ERROR_MESSAGE")){
	         if(code.equals("1")){
	         %>
	            <div class="error-message"><%=ml.getValue("user_name_already_exists")%>.</div>
	         <%
	         }if(code.equals("4")){
	         %>
	            <div class="error-message"><%=ml.getValue("open_id_already_exists")%>!!!</div>
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
	    <tr><td  colspan="2"><h4><u><%=ml.getValue("user_details")%></u></h4></td> </tr>
          </tr>
          <tr>
          <td width="30%"><%=ml.getValue("title")%><label style="color:red;">*</label></td><td width="70%"><select id="title" name="title">
          <% 
          Connection conn = null;
          String title_value = "";
           %>
          <option value="null">-Select-</option>
          <%
          try {
          conn = db.getMysqlConnection();
          PreparedStatement pst = conn.prepareStatement("SELECT fld_value FROM general_master WHERE category='title' AND active_yes_no=1 ORDER BY fld_value");
          ResultSet rs = pst.executeQuery();
          while(rs.next()){
          title_value = rs.getString("fld_value");
          %>
          <option value="<%=title_value%>"><%=title_value%></option>
          <%
          }
          } catch(Exception e) {}
          %>
          </select></td>
          </tr>
          <tr>
	      <td width="30%"><%=ml.getValue("first_name")%><label style="color: red;">*</label></td><td width="70%"><input type="text" id="name" name="name" class="textbox" /></td>
          </tr>
          <tr>
          <td width="30%"><%= ml.getValue("last_name")%></td><td width="70%"><input type="text" id="lastname" name="lastname" class="textbox"></td>
          </tr>
          <tr>
            <td width="30%"><%=ml.getValue("user_name")%><label style="color: red;">*</label></td><td width="70%"><input type="text" id="username" name="username" class="textbox" /></td>
          </tr>
          <tr>
	    <td width="30%"><%=ml.getValue("password")%><label style="color: red;">*</label></td><td width="70%"><input type="password" name="password" id="password" class="textbox" onCopy="return false"  onPaste="return false" onkeyup="return passwordChanged();" /><span id="strength"></span></td>
          </tr>
          <tr>
	    <td width="30%"><%=ml.getValue("confirm_password")%><label style="color: red;">*</label></td><td width="70%"><input type="password" name="confirmpassword" id="confirmpassword" onCopy="return false"  onPaste="return false" class="textbox" /></td>
          </tr>
          <tr>
         <td width="30%"><%=ml.getValue("openId")%></td> <td width="70%"><input class="textbox" type="text"  id="openId" name="openId" value=""  /></td>
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
	          <div class="error-message"><%=ml.getValue("university_name_already_exists")%>.</div>
	        <%
	        }else  if(code.equals("2")){
	         %>
	            <div class="error-message"><%=ml.getValue("email_already_exists")%>.</div>
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
 	  <tr> <td colspan="2"><h4><u><%=ml.getValue("university_details")%></u></h4></td> </tr>
	  <tr>
	  <td width="30%"><%=ml.getValue("email")%><label style="color: red;">*</label></td><td width="70%"><input type="text" id="email" name="email" class="textbox" /></td>
      </tr>
	  <tr>
	    <td width="40%"><%=ml.getValue("university_name")%><label style="color: red;">*</label></td><td width="60%"><input type="text" id="universityname" name="universityname" class="textbox" /></td>
	  </tr>
	  <tr>
	    <td width="40%" ><%=ml.getValue("short_name")%><label style="color: red;">*</label></td><td width="60%" ><input type="text" id="shortname" name="shortname" class="textbox" /></td>
	  </tr>
	  <tr>
	    <td width="40%" valign="bottom"><%=ml.getValue("location")%><label style="color: red;">*</label></td><td width="60%" valign="bottom"><input type="text" id="location" name="location" class="textbox" /></td>
	  </tr>
	  <tr>
	    <td width="40%"><%=ml.getValue("address")%><label style="color: red;">*</label></td><td width="60%"><textarea id="address" name="address" class="textarea"></textarea></td>
	  </tr>
	  <tr>
	  <td></td>
	  <tr>
	    <td colspan="2" width="100%" style="text-align: right;padding-right:8%;" valign="top"><input type="button" id="createaccount" name="createaccount" value='<%=ml.getValue("create_account")%>' onclick="validateForm();" />
	    <input type="button" id="cancel" name="cancel" value='<%= ml.getValue("cancel")%>' onclick="window.location.href='login.jsp';" /></td>
	  </tr>
        </table>
      </div>
    <% 
      }else if(action.equals("SUCCESS_MESSAGE")){
        if(code.equals("1")){
        %>
          <div class="success-message">
            <!-- You have Succesfully created an account.<br /><br /> Account Information details sent to the user's email. -->
          	<%=ml.getValue("account_creation_info")%>
          </div>
           <br /><br />
           <a style="text-decoration:none;padding-left:12%;font-size:20px;font-weight:bold;" href="login.jsp"><%=ml.getValue("login")%></a>
        <%
        } 
      }
    %>  
    </div>
  </div>
  </form>
<div id="footer">
<center>
<div class="footerdiv" > 
Developed by Amrita University under ERP, NME ICT, MHRD
</div>
</center>
</div> 
</div>
</body>
</html>