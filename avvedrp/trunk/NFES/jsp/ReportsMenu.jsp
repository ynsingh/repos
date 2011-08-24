<%@ page contentType="text/html; charset=utf-8" pageEncoding="UTF-8" language="java" import="java.lang.*,java.io.*,java.sql.*,java.util.*" errorPage="" %>

<jsp:useBean id="db" class="com.erp.nfes.ConnectDB" scope="session"/> 
<jsp:useBean id="ml" class="com.erp.nfes.MultiLanguageString" scope="session"/> 

<%


String lc="";
try{
     lc=(String) session.getAttribute("language");         
     request.setCharacterEncoding("UTF-8");
     response.setContentType("text/html; charset=utf-8");
     Locale locale=new Locale(lc,"");  
          
     ml.init("ReportsMenu.jsp", lc);  
             
}catch(Exception e){
     e.printStackTrace();
}

%>
<html>
<head>
<LINK media=screen href="../css/oiostyles.css" type=text/css rel=stylesheet>
<title>NFES</title>
<style type="text/css">

</style>

</head>
<body class="bodystyle">

<div id="Reports_menu_container">
	<div id="Reports_menu_navigation">
		<ul>
			<li><a href="StaffReport.jsp" target="Reportscontent"><%=ml.getValue("faculty_list")%></a></li>
			<!--<li><a href="#" target="Reportscontent">Seminars</a></li>-->			
		</ul>
	</div>	
</div>
</body>
</html>