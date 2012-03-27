<%
   response.setHeader( "Pragma", "no-cache" );
   response.setHeader( "Cache-Control", "no-cache" );
   response.setDateHeader( "Expires", 0 );
%>
<% 

session.removeAttribute("username");
session.removeAttribute("type");
session.removeAttribute("login");

response.sendRedirect("../index.jsp"); 

%>