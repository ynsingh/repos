<%@ include file="connectionstrings.jsp" %>
<%@ page import="javax.servlet.http.HttpSession,java.util.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
   response.setHeader( "Pragma", "no-cache" );
   response.setHeader( "Cache-Control", "no-cache" );
   response.setDateHeader( "Expires", 0 );
%>
<html>
<body>
<%
	String username= request.getParameter("username");
	String password= request.getParameter("password");
	session.setAttribute("user", username);
	session.setAttribute("pass", password);

	HttpSession httpSession = request.getSession();
	ResultSet rst=null;
	rst=stmt.executeQuery("select * from info");
	int flag=0;
	String check="false";
	while (rst.next())
		{
			if((username.equals(rst.getString("LoginMail"))) && (password.equals(rst.getString("Password"))))
			{	
			flag=1;
			check="true";
			httpSession.setAttribute("alumnilogin",check);
				response.sendRedirect("../search.jsp"); 
				break;
			}
		}
		if(flag==0)
		{
			
				response.sendRedirect("../alumnilogin.jsp"); 
		}	
		
		rst.close();
		stmt.close();
		con.close();
%>
</body>
</html>