<html>

<head>
<meta http-equiv="Content-Language" content="en-us">
<meta http-equiv="Content-Type" content="text/html; charset=windows-1252">
<title>Alumni Management System Configuration</title>
</head>

<body>

<form method="POST" action="configuredb.jsp">
	
	<div align="center">
		<table border="0" cellpadding="0" style="border-collapse: collapse" width="700" id="table1">
			<tr>
				<td colspan="2" bgcolor="#800000">
				<p align="center"><b><font color="#FFFFFF" size="4">Alumni 
				Management System Configuration</font></b></td>
			</tr>
			<tr>
				<td width="271" align="left"><b>Database Server<br>
&nbsp;</b></td>
				<td width="429"><input type="text"  id="dbserver" name="dbserver" value="localhost"  /></td>
			</tr>
			<tr>
				<td width="271" align="left"><b>SQL Database Name<br>
&nbsp;</b></td>
				<td width="429"><input type="text" id="dbname" name="dbname" value="alumni" /></td>
			</tr>
			<tr>
				<td width="271" align="left"><b>Database Server Port<br>
&nbsp;</b></td>
				<td width="429"><input type="text" id="dbport" name="dbport" value="3306" /></td>
			</tr>
			<tr>
				<td width="271" align="left"><b>SQL Admin Name<br>
&nbsp;</b></td>
				<td width="429"><input type="text" id="adminname" name="adminname"  /></td>
			</tr>
			<tr>
				<td width="271" align="left"><b>SQL Admin Password<br>
&nbsp;</b></td>
				<td width="429"><input type="password" name="adminpassword" name="adminpassword" /></td>
			</tr>
			<tr>
				<td width="271" align="left"><b>Website Admin<br>
&nbsp;</b></td>
				<td width="429"><select size="1" name="webadmin">
				<option selected value="admin">admin</option>
				<option value="superadmin">superadmin</option>
				</select></td>
			</tr>
			<tr>
				<td width="271" align="left"><b>Website Admin Password<br>
&nbsp;</b></td>
				<td width="429"><input type="password"  name="webpasswd" name="webpasswd" /></td>
			</tr>
			<tr>
				<td width="271">&nbsp; </td>
				<td width="429">&nbsp;</td>
			</tr>
			<tr>
				<td width="700" colspan="2">
				<p align="center">
				<input type="submit" value="Submit Information" name="submit"><input type="reset" value="Reset" name="B2"></td>
			</tr>
		</table>
	</div>
	<p>&nbsp;</p>
	<p>&nbsp;</p>
	<p align="center">&nbsp;</p>
</form>

</body>

</html>