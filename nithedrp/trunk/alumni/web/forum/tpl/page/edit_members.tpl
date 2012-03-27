<% 
	String curusername = "";
	int curmemid = 0, editmemid = 0;
	List members;
	
	if(session.getAttribute("username") == null) {
 %>
 	<jsp:forward page="index.jsp?page=login"/>
 
<% }
	else {
		type = (String) session.getAttribute("type");
		if(!type.equals("Administrator")) {
 %>
 		<jsp:forward page="index.jsp?page=home"/>
 
<% 		}
		else {
			curusername = (String) session.getAttribute("username");
			Members.getMember(curusername);
			curmemid = Members.getMemberid();
			type = (String) session.getAttribute("type");
			
			if(request.getParameter("delete") != null) {
				memberid = Integer.parseInt(request.getParameter("memberid"));
				if(Members.delMember(memberid))
					request.setAttribute("delete", "Success");
				else
					request.setAttribute("delete", "Error");
			}
			
			if(request.getParameter("save") != null) {
%>
				<jsp:setProperty name="Members" property="username"/>
				<jsp:setProperty name="Members" property="password"/>
				<jsp:setProperty name="Members" property="firstname"/>
				<jsp:setProperty name="Members" property="lastname"/>
				<jsp:setProperty name="Members" property="email"/>
				<jsp:setProperty name="Members" property="type"/>
<%
				memberid = Integer.parseInt(request.getParameter("memberid"));
				if(Members.setMember(memberid)) {
					if(request.getParameter("type").equals("Member") && Boxes.isMod(memberid))
						Boxes.unassignBox(memberid);
					request.setAttribute("save", "Success");
				}
				else
					request.setAttribute("save", "Error");
			}
			
			if(request.getAttribute("delete") != null) {
				if(request.getAttribute("delete").equals("Success"))
					message = "<img src='images/pass.gif'>&nbsp;<span class=message>Selected member has been deleted. Thank you</span>";
				if(request.getAttribute("delete").equals("Error"))
					message = "<img src='images/fail.gif'>&nbsp;<span class=errormsg>Could not delete the selected member. Please try again</span>";
				request.removeAttribute("delete");
			}
			
			if(request.getAttribute("save") != null) {
				if(request.getAttribute("save").equals("Success"))
					message = "<img src='images/pass.gif'>&nbsp;<span class=message>Selected member has been updated. Thank you</span>";
				if(request.getAttribute("save").equals("Error"))
					message = "<img src='images/fail.gif'>&nbsp;<span class=errormsg>Could not update the selected member. Please try again</span>";
				request.removeAttribute("save");
			}
		}
	}
%>

<script language="JavaScript">
	function confirmDel()
	{
		var agree=confirm("Are you sure to delete this member?");
		if (agree)
			return true ;
		else
			return false ;
	}

	function checkSel()
	{
		if(document.selectFrm.editmemid.value == "")
		{
			alert("Please select a member to edit");
			document.selectFrm.editmemid.focus();
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
	<td align="center"><%= message %></td>
</tr>
<tr>
	<td height="10"></td>
</tr>

<form name="selectFrm" method="post" action="index.jsp?page=edit_members">
<tr>
	<td align="center">
		<table border="0" cellspacing="1" cellpadding="3" align="center">
			<tr bgcolor="#6699CC"> 
				<td colspan="3" align="center"><font color="#FFFFFF">
					<b>Select Member to Edit</b></font>
				</td>
			</tr>
			<tr bgcolor="#E7E7E7"> 
				<td width="90">
					&nbsp;Members:
				</td>
				<td align="right">
					<select type="text" name="editmemid" style="width:255px;height:20px;">
						<option value="" selected>Select Member</option>
					<% 
						members = Members.getMembers();
						if(members.size() != 0) {
							Iterator i1 = members.iterator();
							while(i1.hasNext()) {
								memberid = ((Integer) i1.next()).intValue();
								Members.getMember(memberid);

								out.print("<option value=" + Members.getMemberid() + " ");
								if(request.getParameter("editmemid") != null && memberid == Integer.parseInt(request.getParameter("editmemid")))
									out.println("selected=selected ");
								out.println(">" + Members.getUsername() + "</option>");
							}
						}
					%>	
					</select>
				</td>
				<td align="right"><input type="submit" value="Select" onCLick="return checkSel()"></td>
			</tr>						
		</table>
	</td>
</tr>
</form>

<%	if(request.getParameter("editmemid") != null) {
		editmemid = Integer.parseInt(request.getParameter("editmemid"));
		Members.getMember(editmemid);
%>
<form method="post" name="editFrm" action="index.jsp?page=edit_members">
<input type="hidden" name="memberid" value="<%= Members.getMemberid() %>">
<tr>
	<td align="center">
		<table border="0" cellspacing="1" cellpadding="3" align="center">
			<tr bgcolor="#6699CC"> 
				<td colspan="2" align="center"><font color="#FFFFFF">
					&nbsp;&nbsp;<b>Edit Member Form</b></font>
				</td>
			</tr>
			<tr bgcolor="#E7E7E7"> 
				<td width="150">
					&nbsp;Username:
				</td>
				<td>
					<font face="Verdana"><input type = "text" name = "username" size = 40 maxlength = 100 value = "<%= Members.getUsername() %>"></font>
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
				<td width="100">
					&nbsp;Participant type:
				</td>
				<td>
					<input type="radio" name="type" value="Administrator" <% if(Members.getType().equals("Administrator")) out.print("checked"); %>>&nbsp;&nbsp;Administrator<br>
					<input type="radio" name="type" value="Moderator" <% if(Members.getType().equals("Moderator")) out.print("checked"); %>>&nbsp;&nbsp;Moderator<br>
					<input type="radio" name="type" value="Member" <% if(Members.getType().equals("Member")) out.print("checked"); %>>&nbsp;&nbsp;Member<br>
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
			<tr bgcolor="#D9DBDE"> 
				<td colspan="2">
					<table width="100%">
						<tr>
							<td width="50%" align="left"><input name="delete" type="submit" value="Delete" onClick="return confirmDel()"></td>
							<td width="50%" align="right"><input name="save" type="submit" value="Save"></td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
	</td>
</tr>
</form>
<% } %>