
<%@ page contentType="text/html; charset=utf-8" pageEncoding="UTF-8" language="java" import="java.lang.*,java.io.*,java.sql.*,java.util.*" errorPage="" %>
<%
Connection conn=null;
Statement theStatement=null;
ResultSet theResult=null;
String fl="";String fr="";String ra="";String mt="";String s="";String as=""; String lc="";
try{
     lc=(String) session.getAttribute("language");
     Properties properties = new Properties();
     properties.load(new FileInputStream("../conf/db.properties"));
     String dbname = properties.getProperty("dbname");
     String username = properties.getProperty("username");
     String password = properties.getProperty("password");
     Class.forName("org.gjt.mm.mysql.Driver");
     conn=DriverManager.getConnection("jdbc:mysql:"+dbname+"?characterSetResults=UTF-8&characterEncoding=UTF-8&useUnicode=yes",username,password);
     theStatement=conn.createStatement();
     theResult=theStatement.executeQuery("select control_name,language_string from language_localisation where active_yes_no=1 and file_code=22 and language_code=\'"+lc+"\'");
     theResult.last();int len=theResult.getRow();String cn[]=new String[len];String ls[]=new String[len];
     int i=0;theResult.beforeFirst();
     while(theResult.next()){
          cn[i]=theResult.getString("control_name");
          ls[i]=theResult.getString("language_string");
          i++;
     }
     
     for(i=0;i<len;i++){
     	if(cn[i].equals("faculty_list")){
     		fl=ls[i];
     	}else if(cn[i].equals("faculty_registration")){
     		fr=ls[i];
     	}else if(cn[i].equals("role_assign")){
     		ra=ls[i];
     	}else if(cn[i].equals("master_tables")){
     		mt=ls[i];
     	}else if(cn[i].equals("search")){
     		s=ls[i];
     	}else if(cn[i].equals("advanced_search")){
     		as=ls[i];
     	}
     	
     }
     
     
     request.setCharacterEncoding("UTF-8");
     response.setContentType("text/html; charset=utf-8");
     Locale locale=new Locale(lc,"");
}catch(Exception e){
     e.printStackTrace();
}
theResult.close();theStatement.close();conn.close();	
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
	//rows[i].cells[1].className = "adminmenu";	
	      }	
	obj.cells[0].className = "adminmenuSelect";
	//obj.cells[1].className = "adminmenuSelect";
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

<td height="27px" class="adminmenuSelect" ><span class="class1"> <a href="./staffList.jsp" target="content"><%=fl%></a></span> </td>
</tr>
<tr onclick="highlight(this);">

<td height="27px" class="adminmenu"><span class="class1"><a href="./Account.jsp" target="content"><%=fr%></a></span> </td>
</tr>
<tr onclick="highlight(this);">

<td height="27px" class="adminmenu"><span class="class1"><a href="./role_assign.jsp" target="content"><%=ra%></a></span> </td>
</tr>
<tr onclick="highlight(this);">

<td height="27px" class="adminmenu"><span class="class1"><a href="./master_tables.jsp" target="content"><%=mt%></a></span> </td>
</tr>
<tr onclick="highlight(this);">
<td height="27px" class="adminmenu"><span class="class1"><a href="../search" target="content"><%=s%></a></span> </td>
</tr>
<tr onclick="highlight(this);">
<td height="27px" class="adminmenu"><span class="class1"><a href="../advanceSearch" target="content"><%=as%></a></span> </td>
</tr>
<tr onclick="highlight(this);">
<td height="27px" class="adminmenu"><span class="class1"><a href="../ApprovalServlet?action=initPage" target="content">Approval</a></span> </td>
</tr>
</table>
</body>
</html>

