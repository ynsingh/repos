<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Frameset//EN" "http://www.w3.org/TR/html4/frameset.dtd">
<%
String lc=(String) request.getSession().getAttribute("language");
	if(lc==null ||lc.equals("")){		  
		  lc="en";
		  session.setAttribute("language",lc);
	}
	
%>	
<html>
<title>NFES</title>
<head>
<link rel="stylesheet" type="text/css" href="../css/oiostyles.css" />
</head>
<div>
<frameset  rows="20%,75%,5%"  frameborder="NO"  border="0" style="overflow:hidden">
	<frame name="header" src="header.jsp" scrolling="no" noresize="noresize" style="z-index:-1;overflow:hidden; height:100%; width:100%;" >
	<frame name="body" src="body.jsp">
	<frame name="footer" src="footer.jsp"scrolling="no" noresize="noresize" style="z-index:-1;overflow:hidden; height:100%; width:100%;" >
<noframes>
    Sorry, your browser does not handle frames!
  </noframes>
	
</frameset>
</div>
<body class="bodystyle">
</body>

</html>