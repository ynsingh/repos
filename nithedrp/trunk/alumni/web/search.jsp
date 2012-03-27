<%@ page import="javax.servlet.http.HttpSession,java.util.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
   response.setHeader( "Pragma", "no-cache" );
   response.setHeader( "Cache-Control", "no-cache" );
   response.setDateHeader( "Expires", 0 );
%>
<html>

<title></title>
<link rel="stylesheet" title="Style CSS" href="css/our.css" type="text/css" media="all" />
<script language="javascript" 
</script>
</head>

<body>

<%@ include file="includes/header.jsp" %>


<%
			
			ResultSet rst=null;
			List flist = new ArrayList();
			List llist = new ArrayList();
			List dlist = new ArrayList();
			List blist = new ArrayList();
			List elist = new ArrayList();
			List clist = new ArrayList();
			
			HttpSession httpSession = request.getSession();
			rst=stmt.executeQuery("select * from info");
			while (rst.next())
			{	
				String Firstname = rst.getString("Firstname");
				String Lastname = rst.getString("Lastname");
				String Dep = rst.getString("Dep1");
				String Batch = rst.getString("Batch1");
				String Emailid = rst.getString("LoginMail");
				String City = rst.getString("City2");
				flist.add(Firstname);
				llist.add(Lastname);
				dlist.add(Dep);
				blist.add(Batch);
				elist.add(Emailid);
				clist.add(City);
			}
			httpSession.setAttribute("alumniDetails1", flist);
			httpSession.setAttribute("alumniDetails2", llist);
			httpSession.setAttribute("alumniDetails3", dlist);
			httpSession.setAttribute("alumniDetails4", blist);
			httpSession.setAttribute("alumniDetails5", elist);
			httpSession.setAttribute("alumniDetails6", clist);
			response.sendRedirect("alumnisearch.jsp"); 
	
		%>
</div>	
</body>
</html>