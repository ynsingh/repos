<%@ include file="connectionstrings.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<body>
<%
	if((session.getAttribute("username")!=null)&&(session.getAttribute("type").equals("Administrator")))
	{
	String hostel= request.getParameter("new_hostel");	
	String hostel1= request.getParameter("old_hostel");	
	
	if(!hostel.equals(""))
	{   
	    String hostel2 = hostel.toUpperCase();
			int flag=0;
			ResultSet rst=null;
			rst=stmt.executeQuery("select * from hostellist");
			while (rst.next())
			{
				String hostel4 = rst.getString("hostel");
				hostel4 = hostel4.toUpperCase();
				if(hostel2.equals(hostel4))
				{
					flag=1;
					break;
				}
			}
		if(flag==0)
		stmt.executeUpdate("insert into hostellist values('"+hostel+"')");
	}
	if(!hostel1.equals(""))
	{   
	    String hostel3 = hostel1.toUpperCase();
			int flag1=0;
			ResultSet rst1=null;
			rst1=stmt.executeQuery("select * from hostellist");
			while (rst1.next())
			{
				String hostel5 = rst1.getString("hostel");
				hostel5 = hostel5.toUpperCase();
				if(hostel5.equals(hostel3))
				{
					flag1=1;
					stmt.executeUpdate("delete from hostellist where hostel ='"+hostel1+"'");
					break;
				}
			}
		if(flag1==0)
		out.println("Hostel not found to be removed");
	}
	stmt.close();
	con.close();
	response.sendRedirect("../hostel.jsp"); 	
		}
	else
	response.sendRedirect("../index.jsp"); 
%>
</body>
</html>