
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<HTML lang=en-US dir=ltr xmlns="http://www.w3.org/11001/xhtml">
<HEAD profile=http://gmpg.org/xfn/11>
<TITLE>Staff Profile Login</TITLE>
<META http-equiv=Content-Type content="text/html; charset=UTF-8">

<META content="MSHTML 6.00.2900.5694" name=GENERATOR>


<%@ taglib prefix='c' uri='http://java.sun.com/jstl/core_rt' %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<script>
function doLogin(){
	document.forms[0].submit();
}
</script>
<title>Login NFES</title>
<link href="./css/main.css" rel="stylesheet" type="text/css" />
<link href="./css/dark_blue.css" rel="stylesheet" type="text/css" />
<script src="./js/jquery-1.4.2.min.js" type="text/javascript"></script>
</HEAD>

<BODY onload="document.f.j_username.focus();" >

<form name="f" action="<c:url value='j_spring_security_check'/>" method="POST">
		

<img src="./images/loginheader.png"  width="100%" />

<div id="login">
	<h2 class="head-alt">Login</h2>
	<ul class="tabs">
		<li><a href="Account.jsp">Staff Registration</a></li>
		<li><a href="ForgotPassword.jsp">Lost Password ?</a></li>
	</ul>
	<div class="panes">
		<div>
			<form action="index.html" method="post">
				<fieldset>
				<legend>Please enter user information!</legend>
				<label for="username">Username:</label><input id="j_username" name="j_username" type="text" />
				<label for="password">Password:</label><input id="j_password" name="j_password" type="password" />
				<a name="button" onclick="doLogin()" class="button">Log in!</a>
				</fieldset>
			</form>
		</div>
	</div>
</div>
<%
if ((request.getParameter("login_error") != "")&&(request.getParameter("login_error") != null)) {%>
<div id="validation-summary">
	
	<div class="notification errors closable">
		<h3>Please correct the errors!</h3>
		<ul>
			<li>Wrong username!</li>
			<li>Wrong password!</li>
		</ul>
	</div>
</div>		
<%} 		 
%>	
</form>
	
</BODY>
</HTML>






	
