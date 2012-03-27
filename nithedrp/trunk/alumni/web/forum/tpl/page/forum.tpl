<% 
	int boxid=0;
	int curmemid = 0;
	List threads = new LinkedList();
	if(session.getAttribute("username") == null) {
 %>
 	<jsp:forward page="index.jsp?page=login"/>
 
<% }
	else {
		username = (String) session.getAttribute("username");
		type = (String) session.getAttribute("type");
		Members.getMember(username);
		curmemid = Members.getMemberid();
		if(request.getParameter("boxid") != null) {
			boxid = Integer.parseInt(request.getParameter("boxid"));
			Boxes.getBox(boxid);
			threads = Threads.getThreads(boxid);
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

	function confirmDel()
	{
		var agree=confirm("Are you sure to delete this thread?");
		if (agree)
			return true ;
		else
			return false ;
	}
</script>
<tr>
	<td valign="top" align="center">
		<span class="sub"><%= title %>: <%= Boxes.getBoxname() %></span>
	</td>
</tr>
<tr>
	<td height="10"></td>
</tr>
<tr>
	<td valign="top" align="left">
		<a href="index.jsp">Community Forum</a>&nbsp;&nbsp<img src="images/arrow.gif">
		<a href="index.jsp?page=forum&boxid=<%= boxid %>"><%= Boxes.getBoxname() %></a>
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
<tr>
	<td align="center">
		<input type="button" value="New Thread" onClick="return popup('popup.jsp?action=post&boxid=<%= boxid %>')">
	</td>
</tr>
<tr>
	<td height="10"></td>
</tr>
<tr>
	<td align="center">
		<table border="0" cellspacing="1" cellpadding="3" align="center">
			<tr bgcolor="#6699CC" height="25"> 
				<td width="700" align="center"><font color="#FFFFFF">
					&nbsp;&nbsp;<b>Threads</b></font>
				</td>
				<td width="100" align="center"><font color="#FFFFFF">
					&nbsp;&nbsp;<b>Author</b></font>
				</td>
				<td width="200" align="center"><font color="#FFFFFF">
					&nbsp;&nbsp;<b>Date and Time</b></font>
				</td>
			</tr>
		<% 
			if(threads.size() == 0) {
		%>
			<tr>
				<td colspan="3" align="center"><span class="errormsg">There are no threads in this forum!</span></td>
			</tr>
		<% } else { 
				int row = 0;
				String rowBg = "";
				Iterator i1 = threads.iterator();
				while(i1.hasNext()) {
					if(row % 2 == 0)
						rowBg = "#E7E7E7";
					else
						rowBg = "#D9DBDE";
					
					int threadid = ((Integer) i1.next()).intValue();
					Threads.getThread(threadid);
					memberid = Threads.getMemberid();
					Members.getMember(memberid);
					row++;
		%>

			<tr height="10" bgcolor="<%= rowBg %>"> 
				<td valign="center">&nbsp;&nbsp<img src="images/bullet.gif">&nbsp;&nbsp;<a href="index.jsp?page=thread&threadid=<%= threadid %>"><%= Threads.getSubject() %></a></td>
				<td valign="center" align="center"><a href="index.jsp?page=view_profile&memberid=<%= Members.getMemberid() %>"><%= Members.getUsername() %></a></td>
				<td valign="center" align="center"><%= new Date(Threads.getPostdate()).toString() %></td>
			</tr>
		<%		}
			}
		%>
		</table>
	</td>
</tr>