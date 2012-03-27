<% 
	String memberidStr = "";
	int curmemid = 0;
	
	if(session.getAttribute("username") == null) {
 %>
 	<jsp:forward page="index.jsp?page=login"/>
 
<% }
	else {
		username = (String) session.getAttribute("username");
		Members.getMember(username);
		curmemid = Members.getMemberid();
		type = (String) session.getAttribute("type");
		memberidStr = (request.getParameter("memberid") == null) ? "" : request.getParameter("memberid");
		memberid = memberidStr.equals("") ? 0 : Integer.parseInt(memberidStr);
		Members.getMember(memberid);
	}
%>
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
<tr>
	<td align="center">
		<table width="550" border="0" cellspacing="1" cellpadding="3" align="center">
			<tr bgcolor="#6699CC"> 
				<td colspan="2" align="center"><font color="#FFFFFF">
					&nbsp;&nbsp;<b>Profile Details</b></font>
				</td>
			</tr>
			<tr bgcolor="#E7E7E7"> 
				<td bgcolor="#E7E7E7" width="150">
					&nbsp;Username:
				</td>
				<td>
					<%= Members.getUsername() %>
				</td>
			</tr>
			<tr bgcolor="#E7E7E7"> 
				<td width="150">
					&nbsp;First Name:
				</td>
				<td>
					<%= Members.getFirstname() %>
				</td>
			</tr>
			<tr bgcolor="#E7E7E7"> 
				<td width="150">
					&nbsp;Last Name:
				</td>
				<td>
					<%= Members.getLastname() %>
				</td>
			</tr>
			<tr bgcolor="#E7E7E7"> 
				<td width="150">
					&nbsp;Email Address:
				</td>
				<td>
					<%= Members.getEmail() %>
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
			<tr bgcolor="#E7E7E7"> 
				<td width="150">
					&nbsp;Registration Date:
				</td>
				<td>
					<%= Members.getRegdate() %>
				</td>
			</tr>
			<tr bgcolor="#E7E7E7"> 
				<td width="150">
					&nbsp;Number of Posts:
				</td>
				<td>
					<%= Members.getNumPosts(memberid) %>
				</td>
			</tr>
		</table>
	</td>
</tr>