<%@ include file="connectionstrings.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<body>
<%
	
	if((session.getAttribute("username")!=null)&&(session.getAttribute("type").equals("Administrator")))
	{
	String degree= request.getParameter("new_degree");	
	String degree1= request.getParameter("old_degree");
	

	if(!degree.equals(""))
	{   
			 String degree2 = degree.toUpperCase();
			int flag=0;
			ResultSet rst=null;
			rst=stmt.executeQuery("select * from degreelist");
			while (rst.next())
			{
				String degree4 = rst.getString("degree");
				degree4 = degree4.toUpperCase();
				if(degree2.equals(degree4))
				{
					flag=1;
					break;
				}
			}
		if(flag==0)
		stmt.executeUpdate("insert into degreelist values('"+degree+"')");
	}
	if(!degree1.equals(""))
	{   
		String degree3 = degree1.toUpperCase();
			int flag1=0;
			ResultSet rst1=null;
			rst1=stmt.executeQuery("select * from degreelist");
			while (rst1.next())
			{
				String degree5 = rst1.getString("degree");
				degree5 = degree5.toUpperCase();
				if(degree5.equals(degree3))
				{
					flag1=1;
					stmt.executeUpdate("delete from degreelist where degree ='"+degree1+"'");
					break;
				}
			}
		if(flag1==0)
		out.println("Degree not found to be removed");
	}
	
	stmt.close();
	con.close();
	response.sendRedirect("../degree.jsp"); 	
		}
	else
	response.sendRedirect("../index.jsp"); 
%>
</body>
</html>