<% 
	int boxid=0, threadid=0;
	int curmemid = 0;
	List threads;
	if(session.getAttribute("username") == null) {
 %>
 	<jsp:forward page="index.jsp?page=login"/>
 
<% }
	else {
		username = (String) session.getAttribute("username");
		type = (String) session.getAttribute("type");
		Members.getMember(username);
		curmemid = Members.getMemberid();
		
		if(request.getParameter("threadid") != null) {
			threadid = Integer.parseInt(request.getParameter("threadid"));
			Threads.getThread(threadid);
			boxid = Threads.getBoxid();
			Boxes.getBox(boxid);
			if((request.getParameter("action") != null) && (request.getParameter("id") != null)  && request.getParameter("action").equals("del")) {
				int delid = Integer.parseInt(request.getParameter("id"));
				Threads.getThread(delid);
				int parentid = Threads.getParentid();
				
				if(Threads.delThread(Integer.parseInt(request.getParameter("id")))) {
					if(parentid == 0) {
					
%>
 						<jsp:forward page="index.jsp?page=home&del_thread=done"/>
 
<% 					}
					else {
						request.setAttribute("delete", "Success");
						Threads.getThread(parentid);
					}
				}
				else {
					request.setAttribute("delete", "Error");
				}
			}
		}
		
		if(request.getAttribute("delete") != null) {
			if(request.getAttribute("delete").equals("Success"))
				message = "<img src='images/pass.gif'>&nbsp;<span class=message>Your reply has been deleted. Thank you</span>";
			if(request.getAttribute("delete").equals("Error"))
				message = "<img src='images/fail.gif'>&nbsp;<span class=errormsg>Could not delete your reply. Please try again</span>";
			request.removeAttribute("delete");
		}
	}
%>

<script language="JavaScript">
	function popup(url)
	{
		newwindow=window.open(url,'add','height=550,width=600');
		if (window.focus) {newwindow.focus()}
		return false;
	}
	
	function confirmDelThread(url)
	{
		var agree=confirm("Are you sure to delete this thread and all reply messages?");
		if (agree)
		{
			window.location=url;
			return true ;
		}
		else
			return false ;
	}

	function confirmDelPost()
	{
		var agree=confirm("Are you sure to delete this message?");
		if (agree)
			return true ;
		else
			return false ;
	}
</script>
<tr>
	<td valign="top" align="center">
		<span class="sub"><%= title %>: <%= Threads.getSubject() %></span>
	</td>
</tr>
<tr>
	<td height="10"></td>
</tr>
<tr>
	<td valign="top" align="left">
		<a href="index.jsp">Community Forum</a>&nbsp;&nbsp<img src="images/arrow.gif">
		<a href="index.jsp?page=forum&boxid=<%= boxid %>"><%= Boxes.getBoxname() %></a>&nbsp;&nbsp<img src="images/arrow.gif">
		<a href="index.jsp?page=thread&threadid=<%= threadid %>"><%= Threads.getSubject() %></a>
	</td>
</tr>
<tr>
	<td>
		<span class="msg">Forum moderator: 
		<% if(Boxes.getMemberid() == 0) { %>
			<span class="errormsg">none</span>
		<% } else { 
			memberid = Boxes.getMemberid();
			Members.getMember(memberid);
		%>
			<a href="index.jsp?page=view_profile&memberid=<%= memberid %>"><%= Members.getUsername() %></a>
		<% } %>
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

<tr height="30">
	<td>
		<table width="100%" align="center">
			<tr>
				<td width="50%">
					<input type="button" value="New Thread" onClick="return popup('popup.jsp?action=post&boxid=<%= boxid %>')">
				</td>
				<td align="right">
					<input type="button" value="Reply Thread" onClick="return popup('popup.jsp?action=reply&boxid=<%= boxid %>&parentid=<%= threadid %>')">
				</td>
			</tr>
		</table>
	</td>
</tr>

<tr>
	<td height="10"></td>
</tr>

<tr>
	<td align="center">
		<table border="0" cellspacing="1" cellpadding="3" align="center">
		<%
			memberid = Threads.getMemberid();
			Members.getMember(memberid);
		%>
		<tr><td align="center">
			<table>
				<tr bgcolor="#D9DBDE" height="10"> 
					<td width="700" align="left"><span class="cat"><font color="#CC3399">
						<%= Threads.getSubject() %></font></span>
					</td>
					<td widtg="200">
						<b>Date</b>: <%= new Date(Threads.getPostdate()).toString() %>
					</td>
					<td width="50" align="right">
					<% if(type.equals("Administrator") || (type.equals("Moderator") && curmemid == Boxes.getMemberid())) { %>
						<table width="100%">
							<tr>
								<td width=50% align=left><img src="images/edit_off.gif" alt="Edit" style="cursor: hand" onClick="return popup('popup.jsp?action=edit&threadid=<%= Threads.getThreadid() %>')" onMouseOver=this.src="images/edit_on.gif"; onMouseOut=this.src="images/edit_off.gif"></td>
								<td width=50% align=right><img src="images/del_off.gif" alt="Delete" style="cursor: hand" onClick="return confirmDelThread('index.jsp?page=thread&threadid=<%= threadid %>&action=del&id=<%= Threads.getThreadid() %>')" onMouseOver=this.src="images/del_on.gif"; onMouseOut=this.src="images/del_off.gif"></td>
							</tr>
						</table>
					<% }%>
					</td>
				</tr>
				<tr> 
					<td colspan="3" align="left">
						<%= Threads.getPosttext() %>
					</td>
				</tr>
			</table>
		</td></tr>
		<tr height="5"><td></td></tr>
		<tr height="1" bgcolor="#6699CC"><td></td></tr>
		<tr height="5"><td></td></tr>
		<% 
			threads = Threads.getReplies(threadid);

			if(threads.size() != 0) {
				Iterator i1 = threads.iterator();
				while(i1.hasNext()) {
					int threadid_re = ((Integer) i1.next()).intValue();
					Threads.getThread(threadid_re);
					memberid = Threads.getMemberid();
					Members.getMember(memberid);
		%>
			<tr height="30"><td></td></tr>
			<tr><td>
				<a name="<%= threadid_re %>"></a>
				<table>
					<tr bgcolor="#D9DBDE" height="10">
						<td width="700" align="left"><span class="cat"><font color="#6699CC">
							<%= Threads.getSubject() %></font></span>
						</td>
						<td widtg="200">
							<b>Date</b>: <%= new Date(Threads.getPostdate()).toString() %>
						</td>
						<td width="50" align="right">
						<% if(type.equals("Administrator") || (type.equals("Moderator") && curmemid == Boxes.getMemberid())) { %>
							<table width="100%">
								<tr>
									<td width=50% align=left><img src="images/edit_off.gif" alt="Edit" style="cursor: hand" onClick="return popup('popup.jsp?action=edit&replyid=<%= Threads.getThreadid() %>')" onMouseOver=this.src="images/edit_on.gif"; onMouseOut=this.src="images/edit_off.gif"></td>
									<td width=50% align=right><img src="images/del_off.gif" alt="Delete" style="cursor: hand" onClick="return confirmDelThread('index.jsp?page=thread&threadid=<%= threadid %>&action=del&id=<%= Threads.getThreadid() %>')" onMouseOver=this.src="images/del_on.gif"; onMouseOut=this.src="images/del_off.gif"></td>
								</tr>
							</table>
						<% }%>
						</td>
					</tr>
					<tr> 
						<td colspan="3" align="left">
							<%= Threads.getPosttext() %>
						</td>
					</tr>
				</table>
			</td></tr>
			<tr height="5"><td></td></tr>
			<tr height="1" bgcolor="#6699CC"><td></td></tr>
			<tr height="5"><td></td></tr>
		<%		}
			}
		%>
		</table>
	</td>
</tr>