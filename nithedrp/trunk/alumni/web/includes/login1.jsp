<%@ include file="connectionstrings.jsp" %>
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
String login = "false";
ResultSet rst=null;
rst=stmt.executeQuery("select * from admin");
while(rst.next())
	{
		if((username.equals(rst.getString("username"))) && (password.equals(rst.getString("password"))))
		{	
			session.setAttribute("type","Administrator");
			session.setAttribute("username",username);
			session.setAttribute("login","true");
			response.sendRedirect("../index.jsp");
			login = "true";
		}
	
	}
	
ResultSet rst1=null;

rst1=stmt.executeQuery("select LoginMail,Password from info");

	while(rst1.next())
	{
		if((username.equals(rst1.getString("LoginMail"))) && (password.equals(rst1.getString("Password"))))
		{		
			session.setAttribute("username",username);
			session.setAttribute("type","member");
			session.setAttribute("login","true");

                        session.setAttribute("alumnilogin","true");

			login = "true";
			response.sendRedirect("../index.jsp");
		}		
	
	}
if(login.equals("false"))
	{				
				session.setAttribute("login","false");
				response.sendRedirect("../index.jsp");
	}
	stmt.close();
	con.close();
%>
</body>
</html>