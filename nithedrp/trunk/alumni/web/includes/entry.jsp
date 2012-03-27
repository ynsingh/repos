<%@ include file="connectionstrings.jsp" %>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Calendar"%>
<%@page import="java.util.Date"%>
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
	String message = request.getParameter("message");
	String title = request.getParameter("title");
	String ttip = request.getParameter("ttip");
	message=message.replaceAll("'",escapeString);
	Calendar cal = Calendar.getInstance();
	Calendar calin = Calendar.getInstance();
	Calendar calout = Calendar.getInstance();
	
	
	SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
	
	Date date = formatter.parse(request.getParameter("in"));
	Date idate = formatter.parse(request.getParameter("in"));
	Date odate = formatter.parse(request.getParameter("out"));
	
	cal.setTime(date);
	calin.setTime(idate);
	calout.setTime(odate);
	
	java.sql.Date sqlidate = new java.sql.Date(idate.getTime());
	java.sql.Date sqlodate = new java.sql.Date(odate.getTime());
	
	int flag=0;
	while((calout.after(cal))||(calout.equals(cal)))
	{
		if(calin.equals(cal))
			flag=1;
		else
		flag=0;
		Date d = cal.getTime();
		java.sql.Date sqldate = new java.sql.Date(d.getTime());
		String insertEvent = "insert into calendar1 values('"+sqldate+"','"+sqlidate+"','"+sqlodate+"','"+title+"','"+ttip+"','"+message+"',"+flag+")";
		stmt.executeUpdate(insertEvent);
		cal.add (Calendar.DATE, +1);
	}
		response.sendRedirect("../index.jsp");
%>
	</body>
	</html>