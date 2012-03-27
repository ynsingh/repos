<%@ include file="connectionstrings.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<body>
<%
	if((session.getAttribute("username")!=null)&&(session.getAttribute("type").equals("Administrator")))
	{
	String department= request.getParameter("new_department");	
	String department1= request.getParameter("old_department");	
	
	if(!department.equals(""))
	{   
	    String department2 = department.toUpperCase();
			int flag=0;
			ResultSet rst=null;
			rst=stmt.executeQuery("select * from departmentlist");
			while (rst.next())
			{
				String department4 = rst.getString("department");
				department4 = department4.toUpperCase();
				if(department2.equals(department4))
				{
					flag=1;
					break;
				}
			}
		if(flag==0)
		stmt.executeUpdate("insert into departmentlist values('"+department+"')");
	}
	if(!department1.equals(""))
	{   
	    String department3 = department1.toUpperCase();
			int flag1=0;
			ResultSet rst1=null;
			rst1=stmt.executeQuery("select * from departmentlist");
			while (rst1.next())
			{
				String department5 = rst1.getString("department");
				department5 = department5.toUpperCase();
				if(department5.equals(department3))
				{
					flag1=1;
					stmt.executeUpdate("delete from departmentlist where department ='"+department1+"'");
					break;
				}
			}
		if(flag1==0)
		out.println("Department not found to be removed");
	}
	stmt.close();
	con.close();
	response.sendRedirect("../department.jsp"); 
	}
	else
	response.sendRedirect("../index.jsp"); 	
%>
</body>
</html>