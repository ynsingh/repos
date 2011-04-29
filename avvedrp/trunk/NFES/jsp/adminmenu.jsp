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

<body class="bodystyle" bgcolor="#e0edf9">


<table  >
<tr onclick="highlight(this);">

<td height="27px" class="adminmenuSelect" ><span class="class1"> <a href="./staffList.jsp" target="content">Faculty List</a></span> </td>
</tr>
<tr onclick="highlight(this);">

<td height="27px" class="adminmenu"><span class="class1"><a href="./Account.jsp" target="content">Faculty Registration </a></span> </td>
</tr>
<tr onclick="highlight(this);">

<td height="27px" class="adminmenu"><span class="class1"><a href="./role_assign.jsp" target="content">Role Assign </a></span> </td>
</tr>
<tr onclick="highlight(this);">

<td height="27px" class="adminmenu"><span class="class1"><a href="./master_tables.jsp" target="content">Master Tables </a></span> </td>
</tr>
<tr onclick="highlight(this);">
<td height="27px" class="adminmenu"><span class="class1"><a href="../search" target="content">Search </a></span> </td>
</tr>
<tr onclick="highlight(this);">
<td height="27px" class="adminmenu"><span class="class1"><a href="../advanceSearch" target="content">Advance Search </a></span> </td>
</tr>
</table>
</body>
</html>

