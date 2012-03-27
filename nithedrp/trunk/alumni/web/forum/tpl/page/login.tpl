<% 
	if(session.getAttribute("username") != null) {
%>
	<jsp:forward page="index.jsp?page=home"/>
<%	}
	else {
	
	if(request.getParameter("login") != null) {
%>
	<jsp:setProperty name="Members" property="username"/>
	<jsp:setProperty name="Members" property="password"/>
<%
		if(Members.authenticate()) {
			username = request.getParameter("username");
			Members.getMember(username);
			session.setAttribute("username", Members.getUsername());
			session.setAttribute("type", Members.getType());
%>
			<jsp:forward page="index.jsp?page=home"/>
<% 		
		}
		else {
			request.setAttribute("login", "Error");
		}
	}
	
	if(request.getAttribute("login") != null) {
		if(request.getAttribute("login").equals("Error"))
			message = "<img src='images/fail.gif'>&nbsp;<span class=errormsg>Invalid username and/or password. Please try again</span>";
		request.removeAttribute("login");
	}
	}
%>

<script language="javascript">
	function checkForm()
	{
		//Prompt if missing userid and password
		if((document.login.username.value == "") && (document.login.password.value == ""))
		{
			alert("Please enter your username and password");
			document.login.username.focus();
			return false;
		}
		//Prompt if missing userid
		if(document.login.username.value == "")
		{
			alert("Please enter your username");
			document.login.username.focus();
			return false;
		}

		//Prompt if missing password
		if(document.login.password.value == "")
		{
			alert("Please enter your password");
			document.login.password.focus();
			return false;
		}

		return true;
	}
</script>
<tr>
	<td valign="top" align="center">
		<span class="sub"><%= title %></span>
	</td>
</tr>
<tr>
	<td height="10"></td>
</tr>
<tr>
	<td align="center">
		Please login so that you can start viewing and posting your messages.<br>
		If you are new to this forum please <a href="index.jsp?page=register">register</a> to create your free account.
	</td>
</tr>
<tr>
	<td height="10"></td>
</tr>
<tr>
	<td align="center"><%= message %></td>
</tr>
<tr>
	<td height="10"></td>
</tr>
<tr>
	<td align="center">
		<form method=post name="login" action="index.jsp?page=login">
			<table width="250" border="0" cellspacing="1" cellpadding="3" align="center">
					<tr bgcolor="#6699CC"> 
						<td colspan="2" align="center"><font color="#FFFFFF">
							<b>Login Form</b></font>
						</td>
					</tr>
					<tr bgcolor="#E7E7E7"> 
						<td width="150">
							&nbsp;Username:
						</td>
						<td>
							<font face="Verdana"><input type = "text" name = "username" size = 25 maxlength = 100></font>
						</td>
					</tr>
					<tr bgcolor="#E7E7E7"> 
						<td width="150">
							&nbsp;Password:
						</td>
						<td bgcolor="#E7E7E7">
							<font face="Verdana"><input type = "password" name = "password" size = 25 maxlength = 100></font>
						</td>
					</tr>
					<tr bgcolor="#D9DBDE"> 
						<td colspan="2" align="right">
							<input type=submit name="login" value="Login" onClick="return checkForm()">
						</td>
					</tr>
				</table>
		</form>
	</td>
</tr>