<?session_start();
 include 'config.php';
 include 'opendb.php';
	
	$getid=mysql_real_escape_string($_GET['updateid']);
	//$updateid=$getid;
	$ok=mysql_real_escape_string($_POST['ok']);
	if($ok)
	{
	header("location: editregistration.php?updateid=$getid");
	}

?>

<html>

<?if($_SESSION['loginuserid']!=1)
	{?>
<head>

	<table align="center" border="0" cellpadding="0" cellspacing="0" width="100%">
  		<tr>
    		<td align="center" width="90%">
    		<font face="Bodoni MT Black" size="5" color="#008000">
		<img border="0" src="images/otbiitk_banner.gif" width="60%" height="60"></font>
    		</td>
  		</tr>
	</table>

	
<hr>



	<table border="0" align="center" cellpadding="0" cellspacing="0" width="100%">
		<tr align="right" valign="middle">
   		<td class="txthdmenu"><div id='div_id'><? include "headerlinks.php"; ?> 
        	<br></div></td></tr>
	</table><br>
</head>

<body>
<form name="updateerr" action="" method="POST">
<table align="center" border="3" cellpadding="3" cellspacing="3"  width="60%" height="55%" bgcolor="#fffff">
  <tr>
    <td width="100%">
	<p align="center">&nbsp;</p>
	<p align="center"><font face="times new roman" size="4" color="red">ERROR !</font></p>
	<p align="center"><font face="vardana" size="3" color="#0000ff">
		You could not update entries successfully . <br>
		   Kindly fill up all mandatory fields.<br>
			Thank you !</font>
	</p>

	<p align="center">
	<input type="submit" style="width:80px;" value="O K" name="ok">
	</p>
    <p>&nbsp;</td>
  </tr>
</table>

		<table border="0" align="center" cellpadding="0" cellspacing="0" width="100%">

			<tr><td height="75">
			</td></tr>

			<tr align="center" valign="middle">
   			<td class="txthdmenu"><div id='div_id'><? include "footer.php"; ?> 
        		</div></td></tr>
			</table>
			
</form>
</form>		
</body>
<?}?>
</html>
