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
       
     ml.init("adminmenu.jsp", lc);    
           
}catch(Exception e){
     e.printStackTrace();
}

%>

<html>
<head>
<LINK media=screen href="../css/oiostyles.css" type=text/css rel=stylesheet>
<title>NFES</title>


<script type="text/javascript">

function highlight(obj) {	
	
      var rows = document.getElementsByTagName("tr");      
      for (var i = 0; i < rows.length; i++){      	
	rows[i].cells[0].className = "adminmenu";		
	      }	
	obj.cells[0].className = "adminmenuSelect";	
}

</script>
<style type="text/css">
.class1 A:link {text-decoration: none;color: #174664;}
.class1 A:visited {text-decoration: none;color:  #174664;}
.class1 A:active {text-decoration: none}
.class1 A:hover {text-decoration: none; color: yellow;}
</style>

</head>

<body class="bodystyle1">


<table  width="100%" >
<tr onclick="highlight(this);">

<td height="27px" class="adminmenuSelect" ><span class="class1"> <a href="./staffList.jsp?searchby=&searchbyUniversity=&searchbyInstitution=&searchbyDepartment=&search=1" target="content"><%=ml.getValue("faculty_list")%></a></span> </td>
</tr>
<tr onclick="highlight(this);">

<td height="27px" class="adminmenu"><span class="class1"><a href="./Account.jsp" target="content"><%=ml.getValue("faculty_registration")%></a></span> </td>
</tr>
<tr onclick="highlight(this);">

<td height="27px" class="adminmenu"><span class="class1"><a href="./role_assign.jsp" target="content"><%=ml.getValue("role_assign")%></a></span> </td>
</tr>
<tr onclick="highlight(this);">

<td height="27px" class="adminmenu"><span class="class1"><a href="./master_tables.jsp" target="content"><%=ml.getValue("master_tables")%></a></span> </td>
</tr>
<tr onclick="highlight(this);">
<td height="27px" class="adminmenu"><span class="class1"><a href="../SearchFaculty" target="content"><%=ml.getValue("search")%></a></span> </td>
</tr>
<tr onclick="highlight(this);">
<td height="27px" class="adminmenu"><span class="class1"><a href="../FacultyAdvanceSearch" target="content"><%=ml.getValue("advanced_search")%></a></span> </td>
</tr>
<tr onclick="highlight(this);">
<td height="27px" class="adminmenu"><span class="class1"><a href="../ApprovalServlet?action=initPage" target="content"><%=ml.getValue("approval")%></a></span> </td>
</tr>
<tr onclick="highlight(this);">
<td height="27px" class="adminmenu"><span class="class1"><a href="../RecordTypeSettingServlet?action=initPage" target="content"><%=ml.getValue("public_private_records")%></a></span> </td>
</tr>

<tr onclick="highlight(this);">
<td height="27px" class="adminmenu"><span class="class1"><a href="Reports_main.jsp" target="content"><%=ml.getValue("Reports")%></a></span> </td>
</tr>
</table>
</body>
</html>

