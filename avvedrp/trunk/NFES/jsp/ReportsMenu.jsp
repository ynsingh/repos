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

<script type="text/javascript">
function ShowRreport(optvalue){
parent.Reportscontent.location=optvalue;
}
</script>

</head>
<body class="bodystyle1">

<!--<div id="Reports_menu_container">
	 <div id="Reports_menu_navigation"> -->
		<!-- <ul>
			<li><a href="StaffReport.jsp" target="Reportscontent"><%=ml.getValue("faculty_list")%></a></li>
		</ul>
		-->
		<label><b><%=ml.getValue("select_report")%></b></label> 
		<Select onchange="ShowRreport(this.value);">
		<option value="StaffReport.jsp">Faculty List</option>
		<option value="ApprovalReport.jsp">Approval List</option>
		<option value="InstitutionTransferReport.jsp">Institution Transfer List</option>
		</Select>
		
	<!-- </div>	
</div>-->
</body>
</html>