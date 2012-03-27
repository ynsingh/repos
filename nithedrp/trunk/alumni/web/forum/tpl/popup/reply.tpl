<% 
	String username = "", parent_sbj = "";
	boolean done = false;
	int boxid = 0;
	int parentid = 0;
	int threadid = (int) (1000 + Math.random()* 1000);
	if(session.getAttribute("username") == null) {
%>
 	<jsp:forward page="login.jsp"/>
 
<% }
	else {
		username = (String) session.getAttribute("username");
		Members.getMember(username);
		
		if(request.getParameter("boxid") != null) {
			boxid = Integer.parseInt(request.getParameter("boxid"));
			Boxes.getBox(boxid);
		}
		
		if(request.getParameter("parentid") != null) {
			parentid = Integer.parseInt(request.getParameter("parentid"));
			Threads.getThread(parentid);
			parent_sbj = Threads.getSubject();
		}
		
		if(request.getParameter("reply") != null) {
			if(request.getParameter("subject").equals("") || request.getParameter("posttext").equals("")) {
				request.setAttribute("done", "Incomplete");
			} else {
%>
				<jsp:setProperty name="Threads" property="subject"/>
				<jsp:setProperty name="Threads" property="posttext"/>
				<jsp:setProperty name="Threads" property="threadid" value="<%= threadid %>"/>
				<jsp:setProperty name="Threads" property="parentid" value="<%= parentid %>"/>
				<jsp:setProperty name="Threads" property="boxid" value="<%= boxid %>"/>
				<jsp:setProperty name="Threads" property="memberid" value="<%= Members.getMemberid() %>"/>
<%
				if(Threads.replyThread())
					done = true;
				else
					request.setAttribute("done", "Error");
			}
		}

		if(request.getAttribute("done") != null) {
			if(request.getAttribute("done").equals("Incomplete"))
				message = "<img src='images/fail.gif'>&nbsp;<span class=errormsg>Please complete all fields and try again</span>";
			if(request.getAttribute("done").equals("Error"))
				message = "<img src='images/fail.gif'>&nbsp;<span class=errormsg>Could not post your message. Please try again</span>";
			request.removeAttribute("done");
		}
	}
%>
<html>
<head>
<title>Reply Message</title>

<style type="text/css">
	@import "css/style.css";
</style>

<script language="javascript" type="text/javascript" src="editor/tiny_mce.js"></script>

<script language="javascript" type="text/javascript">
	tinyMCE.init({
		mode : "textareas",
		theme : "advanced",
		language : "english",
		editor_css : "editor/css/editor.css",
		force_p_newlines: "false",
		force_br_newlines: "true"
	});
	
	function closeMe()
	{
		t = setTimeout("self.close()",500);
	}

	function refreshParent()
	{
		var randomnumber=Math.floor(Math.random()*1001);
		var location = 'index.jsp?page=thread&threadid=<%= parentid %>#<%= Threads.getThreadid() %>';
		parent.window.opener.location.reload();
		parent.window.opener.location.href(location);
	}
</script>

</head>
	<body background="images/popup_bg.jpg" LEFTMARGIN=0 RIGHTMARGIN=0 TOPMARGIN=0 MARGINWIDTH=0 MARGINHEIGHT=0 <% if(done == true) out.print("onLoad='closeMe()' onUnload='refreshParent()'"); %>>
		<form name="reply" method="post">
			<table>
				<tr bgcolor="#C6E3FF">
					<td colspan="2" valign="middle">
						<table width="100%" height="40">
							<tr>
								<td valign="middle" align="center" width="50"><img src="images/post.gif"></td>
								<td valign="middle"><span class="head">Reply Message</span></td>
							</tr>
						</table>
					</td>
				</tr>
				
				<tr height="30">
					<td colspan="2" valign="middle" align="center"><%= message %></td>
				</tr>
				<tr>
					<td width="100" valign="middle"><span class="sub">Forum:</span></td>
					<td width="500" valign="middle"><b><%= Boxes.getBoxname() %></b></td>
				</tr>
				<tr>
					<td height="25" valign="middle"><span class="sub">Author:</span></td>
					<td valign="middle"><b><%= Members.getUsername() %></b></td>
				</tr>
				<tr>
					<td valign="middle"><span class="sub">Subject:</span></td>
					<td valign="middle"><input type=text name="subject" size="80" value="RE: <%= parent_sbj %>">
				</tr>
				<tr>
					<td valign="top"><span class="sub">Content:</span></td>
					<td colspan="4">
						<textarea name="posttext" cols="60" rows="20"><%= request.getParameter("posttext") == null ? "" : request.getParameter("posttext") %></textarea>
					</td>
				</tr>
				<tr height="10">
					<td colspan="2" valign="middle" align="center"></td>
				</tr>
				<tr>
					<td colspan="2" align="right" valign=middle><input type="submit" name="reply" value="Reply" onClick="return checkForm()"></td>
				</tr>
				
			</table>
		</form>
	</body>
</html>