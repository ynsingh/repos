<% 
	if(session.getAttribute("username") == null) {
 %>
 	<jsp:forward page="index.jsp?page=login"/>
 
<% }
	else {
		username = (String) session.getAttribute("username");
		type = (String) session.getAttribute("type");
		Members.getMember(username);
		memberid = Members.getMemberid();
		
		if(request.getParameter("editpro") != null) {
%>
			<jsp:setProperty name="Members" property="password"/>
			<jsp:setProperty name="Members" property="firstname"/>
			<jsp:setProperty name="Members" property="lastname"/>
			<jsp:setProperty name="Members" property="email"/>
<%
			if(Members.setMember(username)) {
				request.setAttribute("edit", "Success");
			} else {
				request.setAttribute("edit", "Error");
			}
		}
		
		if(request.getAttribute("edit") != null) {
			if(request.getAttribute("edit").equals("Success"))
				message = "<img src='images/pass.gif'>&nbsp;<span class=message>Your profile has been updated. Thank you</span>";
			if(request.getAttribute("edit").equals("Error"))
				message = "<img src='images/fail.gif'>&nbsp;<span class=errormsg>Could not update your profile. Please try again</span>";
			request.removeAttribute("edit");
		}
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
			document.edit.email.focus();
			return false;
		}

		if(((emailad.search(exclude) != -1)||(emailad.search(check)) == -1)||(emailad.search(checkend) == -1))
		{
			alert("Please enter a valid email address (eg: user@company.com)");
			document.edit.email.select();
			return false;
		}

		return true;
	}

	function checkForm()
	{
		if(document.edit.password.value != document.edit.cpassword.value) {
			alert("Password and confirmation password not match!");
			document.edit.password.select();
			return false;
		}

		//Prompt if missing First Name
		if(document.edit.firstname.value == "")
		{
			alert("Please enter your First Name");
			document.edit.firstname.focus();
			return false;
		}

		//Prompt if missing Last Name
		if(document.edit.lastname.value == "")
		{
			alert("Please enter your Last Name");
			document.edit.lastname.focus();
			return false;
		}

		//Check for a valid email entered
		var validEmail = validate(document.edit.email.value);
		if(validEmail == false)
			return false;

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
	<td align="center"><%= message %></td>
</tr>
<tr>
	<td height="10"></td>
</tr>
<tr>
	<td align="center">

		<form method="post" name="edit" action="index.jsp?page=edit_profile">
				<table border="0" cellspacing="1" cellpadding="3" align="center">
					<tr bgcolor="#6699CC"> 
						<td colspan="2" align="center"><font color="#FFFFFF">
							&nbsp;&nbsp;<b>Update Profile Form</b></font>
						</td>
					</tr>
					<tr bgcolor="#E7E7E7"> 
						<td width="150">
							&nbsp;Username:
						</td>
						<td>
							<%= Members.getUsername() %>
						</td>
					</tr>
					<tr bgcolor="#E7E7E7"> 
						<td width="150">
							&nbsp;Password:
						</td>
						<td>
							<font face="Verdana"><input type = "password" name = "password" size = 40 maxlength = 100></font>
						</td>
					</tr>
					<tr bgcolor="#E7E7E7"> 
						<td width="150">
							&nbsp;Confirmation Password:
						</td>
						<td>
							<font face="Verdana"><input type = "password" name = "cpassword" size = 40 maxlength = 100></font>
						</td>
					</tr>
					<tr bgcolor="#E7E7E7"> 
						<td width="150">
							&nbsp;First Name:
						</td>
						<td>
							<font face="Verdana"><input type = "text" name = "firstname" size = 40 maxlength = 100 value = "<%= Members.getFirstname() %>"></font>
						</td>
					</tr>
					<tr bgcolor="#E7E7E7"> 
						<td width="150">
							&nbsp;Last Name:
						</td>
						<td>
							<font face="Verdana"><input type = "text" name = "lastname" size = 40 maxlength = 100 value = "<%= Members.getLastname() %>"></font>
						</td>
					</tr>
					<tr bgcolor="#E7E7E7"> 
						<td width="150">
							&nbsp;Email Address:
						</td>
						<td>
							<font face="Verdana"><input type = "text" name = "email" size = 40 maxlength = 100 value = "<%= Members.getEmail() %>"></font>
						</td>
					</tr>
					<tr bgcolor="#E7E7E7">
						<td width="150">
							&nbsp;Registration Date:
						</td>
						<td>
							<jsp:getProperty name="Members" property="regdate"/>
						</td>
					</tr>
					<tr bgcolor="#E7E7E7"> 
						<td width="150">
							&nbsp;Participant type:
						</td>
						<td>
							<%= Members.getType() %>
						</td>
					</tr>
					<tr bgcolor="#D9DBDE"> 
						<td colspan="2" align="right">
							<input name="editpro" type="submit" value="Update" onClick="return checkForm()">
						</td>
					</tr>
				</table>
		</form>

	</td>
</tr>