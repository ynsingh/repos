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
	String collegename = request.getParameter("collegename");
	String logourl = request.getParameter("logourl");
	collegename=collegename.replaceAll("'",escapeString);
	logourl=logourl.replaceAll("'",escapeString);
	String updatestmt = "update divtext set title='"+collegename+"' where divid = 'collegename' && divtype='header'";
	stmt.executeUpdate(updatestmt);	
	updatestmt="update divtext set title='"+logourl+"' where divid = 'logourl' && divtype='header'";
	stmt.executeUpdate(updatestmt);
	response.sendRedirect("../index.jsp");
%>
	</body>
	</html>