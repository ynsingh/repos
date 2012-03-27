<%@ include file="connectionstrings.jsp" %>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.*,java.text.*"%>
<%
   response.setHeader( "Pragma", "no-cache" );
   response.setHeader( "Cache-Control", "no-cache" );
   response.setDateHeader( "Expires", 0 );
%>
<html>
<body>
<%	
	String escapeString="\\\\'";
	String message = request.getParameter("news_message");
	String title = request.getParameter("title");
	String divid = request.getParameter("menu");
	message=message.replaceAll("'",escapeString);
	title=title.replaceAll("'",escapeString);
			String updatestmt = "update divtext set title='"+title+"',message='"+message+"'where divid='"+divid+"'";
			stmt.executeUpdate(updatestmt);	
			response.sendRedirect("../index.jsp");
%>
	</body>
	</html>