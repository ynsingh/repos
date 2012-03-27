<%@ include file="tpl/header/header.tpl" %>
<%  if(body.equals("") || body.equals("login") || body.equals("logout")) { %>
	<%@ include file="tpl/page/login.tpl" %>
<%	} else if(body.equals("register")) { %>
	<%@ include file="tpl/page/register.tpl" %>
<%	} else if(body.equals("home")) { %>
	<%@ include file="tpl/page/home.tpl" %>
<%	} else if(body.equals("forum")) { %>
	<%@ include file="tpl/page/forum.tpl" %>
<%	} else if(body.equals("thread")) { %>
	<%@ include file="tpl/page/thread.tpl" %>
<%	} else if(body.equals("view_profile")) { %>
	<%@ include file="tpl/page/view_profile.tpl" %>
<%	} else if(body.equals("edit_profile")) { %>
	<%@ include file="tpl/page/edit_profile.tpl" %>
<%	} else if(body.equals("edit_members")) { %>
	<%@ include file="tpl/page/edit_members.tpl" %>
<%	} else if(body.equals("edit_forums")) { %>
	<%@ include file="tpl/page/edit_forums.tpl" %>
<%  } else {  %>
	<%@ include file="tpl/page/notfound.tpl" %>
<%  } %>

<%@ include file="tpl/footer/footer.tpl" %>