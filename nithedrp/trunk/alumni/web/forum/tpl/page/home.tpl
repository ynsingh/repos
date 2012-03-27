<% 
	List boxes;
	List threads;
	if(session.getAttribute("username") == null) {
 %>
 	<jsp:forward page="index.jsp?page=login"/>
 
<% }
	else {		
		if((request.getParameter("del_thread") != null) && request.getParameter("del_thread").equals("done"))
			message = "<img src='images/pass.gif'>&nbsp;<span class='message'>Your thread and all reply messages have been deleted. Thank you</span>";		

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
</tr>
<tr>
	<td align="center">
		<table border="0" cellspacing="1" cellpadding="3" align="center">
			<tr bgcolor="#6699CC" height="25"> 
				<td width="900" align="center"><font color="#FFFFFF">
					&nbsp;&nbsp;<b>Forums</b></font>
				</td>
				<td width="100" align="center"><font color="#FFFFFF">
					&nbsp;&nbsp;<b>Posts</b></font>
				</td>
			</tr>
		<% 
			boxes = Boxes.getBoxes();
			if(boxes.size() == 0) {
		%>
			<tr>
				<td colspan="3" align="center"><span class="errormsg">There are no forums have been created!</span></td>
			</tr>
		<% } else { 
				Iterator i1 = boxes.iterator();
				while(i1.hasNext()) {
					int boxid = ((Integer) i1.next()).intValue();
					Boxes.getBox(boxid);
					memberid = Boxes.getMemberid();
					Members.getMember(memberid);
		%>

			<tr height="25"> 
				<td valign="top"><a href="index.jsp?page=forum&boxid=<%= boxid %>"><%= Boxes.getBoxname() %></a><br>
				Moderator: 
				<% if(Boxes.getMemberid() == 0) { %>
					<span class="errormsg">unassigned</span>
				<% } else { %>
					<a href="index.jsp?page=view_profile&memberid=<%= Members.getMemberid() %>"><%= Members.getUsername() %></a>
				<% } %><br>
				Description: <i><%= Boxes.getSortdesc() %></i><br>
				Latest threads:
		<%	threads = Threads.getLastThreads(boxid);
			if(threads.size() == 0) { 
		%>
				&nbsp;<span class="errormsg">none</span>
		<%	} else {
				out.println("<br>");
				Iterator i2 = threads.iterator();
				while(i2.hasNext()) {
					int threadid = ((Integer) i2.next()).intValue();
					Threads.getThread(threadid);
					memberid = Threads.getMemberid();
					Members.getMember(memberid);
		%>
				&nbsp;&nbsp;<img src="images/arrow.gif">&nbsp<a href="index.jsp?page=thread&threadid=<%= threadid %>"><%= Threads.getSubject() %></a> 
				by <a href="index.jsp?page=view_profile&memberid=<%= Members.getMemberid() %>"><%= Members.getUsername() %></a><br>
		<% 		}
			} %>
				</td>
				<td valign="top" align="center"><%= Threads.getNumMsgs(boxid) %></td>
			</tr>
			<tr height="10"><td colspan="2"></td></tr>
			<tr height="1" bgcolor="#6699CC"><td colspan="2"></td></tr>
			<tr height="10"><td colspan="2"></td></tr>
		<%		}
			}
		%>
		</table>
	</td>
</tr>