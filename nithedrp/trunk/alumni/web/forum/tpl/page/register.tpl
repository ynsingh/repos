<% 
	username = (request.getParameter("username") == null) ? "" : request.getParameter("username");
	
	if(request.getParameter("register") != null) {
		if(Members.checkReg(username)) {
%>
			<jsp:setProperty name="Members" property="username"/>
			<jsp:setProperty name="Members" property="password"/>
<%
			if(Members.regMember()) {
				Members.getMember(username);
				session.setAttribute("username", Members.getUsername());
				session.setAttribute("type", Members.getType());			
%>
				<jsp:forward page="index.jsp?page=edit_profile"/>
<% 		
			}
			else {
				request.setAttribute("register", "Error - unsuccessful");
			}
		}
		else {
			request.setAttribute("register", "Error - registered");
		}
	}
	if(request.getAttribute("register") != null) {
		if(request.getAttribute("register").equals("Error - unsuccessful"))
			message = "<img src='images/fail.gif'>&nbsp;<span class='errormsg'>Could not register your profile. Please try again</span>";
		if(request.getAttribute("register").equals("Error - registered"))
			message = "<img src='images/fail.gif'>&nbsp;<span class='errormsg'>This username has been taken. Please try again</span>";
		request.removeAttribute("register");
	}
%>

<script language="javascript">
	function validate(emailad)
	{
		var exclude=/[^@\-\.\w]|^[_@\.\-]|[\._\-]{2}|[@\.]{2}|(@)[^@]*\1/;
		var check=/@[\w\-]+\./;
		var checkend=/\.[a-zA-Z]{2,4}$/;
		if(emailad == "")
		{
			alert("Please enter an email address");
			document.register.email.focus();
			return false;
		}

		if(((emailad.search(exclude) != -1)||(emailad.search(check)) == -1)||(emailad.search(checkend) == -1))
		{
			alert("Please enter a valid email address (eg: user@company.com)");
			document.register.email.select();
			return false;
		}

		return true;
	}

	function checkForm()
	{
		//Prompt if missing username
		if(document.register.username.value == "")
		{
			alert("Please enter your username");
			document.register.username.focus();
			return false;
		}

		//Prompt if missing password
		if(document.register.password.value == "")
		{
			alert("Please enter your password");
			document.register.password.focus();
			return false;
		}

		//Prompt if missing confirmation password
		if(document.register.cpassword.value == "")
		{
			alert("Please enter your confirmation password");
			document.register.cpassword.focus();
			return false;
		}

		if(document.register.password.value != document.register.cpassword.value) {
			alert("Password and confirmation password not match!");
			document.register.password.select();
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
		Please fill in your details below to create your new account. All fields are required.
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

		<form method="post" name="register" action="index.jsp?page=register">
				<table width="350" border="0" cellspacing="1" cellpadding="3" align="center">
					<tr bgcolor="#6699CC"> 
						<td colspan="2" align="center"><font color="#FFFFFF">
							<b>Register Form</b></font>
						</td>
					</tr>
					<tr bgcolor="#E7E7E7"> 
						<td width="150">
							&nbsp;Username:
						</td>
						<td>
							<font face="Verdana"><input type = "text" name = "username" size = 27 maxlength = 100></font>
						</td>
					</tr>
					<tr bgcolor="#E7E7E7"> 
						<td width="150">
							&nbsp;Password:
						</td>
						<td>
							<font face="Verdana"><input type = "password" name = "password" size = 27 maxlength = 100></font>
						</td>
					</tr>
					<tr bgcolor="#E7E7E7"> 
						<td width="150">
							&nbsp;Confirmation Password:
						</td>
						<td>
							<font face="Verdana"><input type = "password" name = "cpassword" size = 27 maxlength = 100></font>
						</td>
					</tr>
					<tr bgcolor="#D9DBDE"> 
						<td colspan="2" align="right">
							<input name="register" type="submit" value="Register" onclick="return checkForm()">
						</td>
					</tr>
				</table>
		</form>

	</td>
</tr>