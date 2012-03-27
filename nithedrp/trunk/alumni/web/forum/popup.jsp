<jsp:useBean id="Members" class="forum.Members"/>
<jsp:useBean id="Boxes" class="forum.Boxes"/>
<jsp:useBean id="Threads" class="forum.Threads"/>

<% 
	String action = request.getParameter("action") == null ? "" : request.getParameter("action");
	String message = "";

         String relpath = request.getSession().getServletContext().getRealPath("/config.properties");
        Members.setPath(relpath);
        Boxes.setPath(relpath);
        Threads.setPath(relpath);
	
	if(action.equals("post")) {
		String posttext = request.getParameter("posttext") == null ? "" : request.getParameter("posttext").replaceAll("\n", "<br>");
%>
 	<%@ include file="tpl/popup/post.tpl" %>
<% } if(action.equals("reply")) { %>
	<%@ include file="tpl/popup/reply.tpl" %>
<% } if(action.equals("edit")) {%>
	<%@ include file="tpl/popup/edit.tpl" %>
<% } %>