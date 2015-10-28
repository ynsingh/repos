<html>

<head>
<?if($_SESSION['loginuserid']!=1)
	{?>

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
<form name="regiserr" action="registration.php" method="get">
<table align="center" border="3" cellpadding="3" cellspacing="3"  width="60%" height="55%" bgcolor="#fffff">
  <tr>
    <td width="100%">
	<p align="center">&nbsp;</p>
	<p align="center"><font face="times new roman" size="4" color="red">ERROR !</font></p>
	<p align="center"><font face="vardana" size="3" color="#0000ff">
		Your registration could not be completed.<br>Because User id already exist. <br>
		   Kindly use email id.<br>
			Thank you !</font>
	</p>

	<p align="center">
	<input type="submit" value="   OK   " name="ok">
	</p>
    <p>&nbsp;</td>
  </tr>
</table>
</form>		
<?}?>
</body>
</html>
