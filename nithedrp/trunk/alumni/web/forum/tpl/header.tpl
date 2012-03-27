<jsp:useBean id="Members" class="forum.Members"/>
<jsp:useBean id="Boxes" class="forum.Boxes"/>
<jsp:useBean id="Threads" class="forum.Threads"/>

<%@ page import="java.util.*" %>
<%@ page import="java.io.*" %>
<%@ page import="java.io.FileInputStream" %>
<%@page import="java.util.Properties"%>
<%
        String username = "";
	String type = "";
	String message = "";
	int memberid = 0;
	String body = request.getParameter("page") == null ? "" : request.getParameter("page");
	String title = "";
	String icon = "";
	String leftmsg = "";
        String relpath = request.getSession().getServletContext().getRealPath("/config.properties");
        Members.setPath(relpath);
        Boxes.setPath(relpath);
        Threads.setPath(relpath);

        if(body.equals("logout")) {
		session.removeAttribute("username");
		session.removeAttribute("type");
	}
	
	if(session.getAttribute("username") != null) {
		username = (String) session.getAttribute("username");
		type = (String) session.getAttribute("type");
		Members.getMember(username);
		memberid = Members.getMemberid();
		
		leftmsg = "You are logged in as <a href='index.jsp?page=view_profile&memberid="+ memberid + "'>" + username + "</a>. You are a forum <span class='member'>" + type + "</span>";
		
		if(body.equals("") || body.equals("home")) {
			title = "Forum Home";
			icon = "home.gif";
		}
		else if(body.equals("forum")) {
			title = "View Forum";
			icon = "forum.gif";
		}
		else if(body.equals("thread")) {
			title = "View Thread";
			icon = "thread.gif";
		}
		else if(body.equals("view_profile")) {
			title = "View Profile";
			icon = "profile.gif";
		}
		else if(body.equals("edit_profile")) {
			title = "Edit Profile";
			icon = "profile.gif";
		}
		else if(body.equals("edit_forums")) {
			title = "Edit Forums";
			icon = "admin.gif";
		}
		else if(body.equals("edit_members")) {
			title = "Edit Members";
			icon = "admin.gif";
		}
		else {
			title = "Page not found";
			icon = "error.gif";
		}
	}
	else {
		if(body.equals("") || body.equals("login") || body.equals("logout")) {
			title = "Login";
			icon = "login.gif";
		}
		else if(body.equals("register")) {
			title = "Register";
			icon = "register.gif";
		}
		else {
			title = "Page not found";
			icon = "error.gif";
		}
	}
%>
<html>
	<head>
		<title> Alumni Forum</title>

		<style type="text/css">
			<!--
				@import "css/style.css";
			-->
		</style>
		
	</head>

	<body LEFTMARGIN=0 RIGHTMARGIN=0 TOPMARGIN=0 MARGINWIDTH=0 MARGINHEIGHT=0>

	<a name="top"></a>
		<table BORDER=0 CELLPADDING=0 CELLSPACING=5 align="center">
			<tr>
				<TD>
				  <table border="0" cellspacing="0" cellpadding="0">
					<tr>
					  <td width="74"><img src="../includes/logo.png" align="absmiddle" border="0"></td>



					  <td width="343" valign="middle" align="left"><span class=""><%=session.getAttribute("cname")%>   <br>ALUMNI Forum</span></td>

					  <td width="300" background="images/banner_bg.jpg"></td>
					  <td width="303"><img src="images/banner.jpg"></td>
					</tr>
				  </table>
				</TD>
			</tr>
			<tr bgcolor="#E0E0E0">
				<td height="25" valign="middle">
					<table width="100%">
						<tr> 
						<% if(username.equals("")) { %>
							<td align="right">
								<a href='../index.jsp'>Alumni Home</a>
								&nbsp;|&nbsp;
								<a href='index.jsp?page=login'>Login</a>
							</td>
						<% } else { %>
							<td width="50%" align="left"><span class="msg"><%= leftmsg %></span></td>
							<td align=right>
                            	<a href="../index.jsp">Alumni Home</a>
								&nbsp;|&nbsp;
								<a href="index.jsp?page=home">Forum Home</a>
								&nbsp;|&nbsp;
							<% if (type.equals("Administrator")) { %>
									<a href="index.jsp?page=edit_members">Members</a>
									&nbsp;|&nbsp;
								<% } %>
							<% if (type.equals("Administrator") || type.equals("Moderator")) { %>
									<a href="index.jsp?page=edit_forums">Forums</a>
									&nbsp;|&nbsp;
							<% } %>
								<a href="index.jsp?page=edit_profile">Profile</a>
								&nbsp;|&nbsp;
								<a href="index.jsp?page=logout">Logout</a>
							</td>
						<% } %>
						</tr>
					</table>
				</td>
			</tr>
			
			<tr>
				<td valign="middle" align="left" bgcolor="#C6E3FF">
					<table width="100%" height="50">
						<tr>
							<td valign="middle" align="center" width="60"><img src="images/<%= icon %>"></td>
							<td valign="middle"><span class="head"><%= title %></span></td>
						</tr>
					</table>
				</td>
			</tr>
			<tr>
				<td height="10"></td>
			</tr>
			