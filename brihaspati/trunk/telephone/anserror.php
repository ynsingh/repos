<?
/* @(#)anserror.php
 *
 *  Copyright (c) 2007 Brihaspati Team/ETRG,IIT Kanpur.
 *  All Rights Reserved.
 *
 *  Redistribution and use in source and binary forms, with or
 *  without modification, are permitted provided that the following
 *  conditions are met:
 *
 *  Redistributions of source code must retain the above copyright
 *  notice, this  list of conditions and the following disclaimer.
 *
 *  Redistribution in binary form must reproducuce the above copyright
 *  notice, this list of conditions and the following disclaimer in
 *  the documentation and/or other materials provided with the
 *  distribution.
 *
 *
 *  Contributors: Members of ETRG, I.I.T. Kanpur
 *
 *	
 *    ***********************************************************	
 *    * 							*
 *    *	This file is used to use to display the Answer error	*
 *    *	filled up at registration. This answer is needed fot	*
 *    *	changing password/security.				*
 *    *								*
 *    *	@author<a href="raajkhurana@gmail.com">Raj Kumar</a>	*
 *    *								*
 *    ***********************************************************	
 *	
 */
session_start();
	$loggeduser=$_SESSION['loginuserid'];
	include 'opendb.php';
	include 'config.php';

	//$ok=$_POST['ok'];
	$ok=mysql_real_escape_string($_POST['ok']);

	if($ok)
	      {
		header("Location: enterans.php");

	       }

	elseif($cancel)
	      {
		header("Location: index.php");

	       }
?>
<html>
<head>
</head>
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

<body>

    	<form action="" name="uidErroForm" method="post">

	<table align="center" valign="center" border="2" cellpadding="3" cellspacing="3" width="55%"  height="50%" bgcolor="fffff">

	<tr><td width="50%" colspan="2" align="center">
		<font face="times new roman" size="5" color="red">ERROR ! </font><br><br>
		<font face="times new roman" size="3" color="#0000ff">Your answer did not match.</font><p>&nbsp;</p>
		<input type="submit" name ="ok" value="   OK    " id="ok">
	</td></tr>
	</table>		

		<table border="0" align="center" cellpadding="0" cellspacing="0" width="100%">
			
			<tr><td height="130">
			</td></tr>

			<tr align="center" valign="middle">
   			<td class="txthdmenu"><div id='div_id'><? include "footer.php"; ?> 
        		</div></td></tr>
		</table>

    	</form>	
<?}?>
</body>
</html>
