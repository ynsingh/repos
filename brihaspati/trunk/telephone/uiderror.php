<?
/* @(#)uiderror.php
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
 *	*********************************************************************************
 *      *                                                                               *
 *  	*	This dispaly option wrong user id informaton.		 		*	                            * *      * 									             *
 *      *      								         	*
 *      *                                                                		*
 *      *                                                                		*
 *  	*	@author<a href="raajkhurana@gmail.com">Raj Kumar</a>                   	*
 *	*                                                                		*
 *	*********************************************************************************
 */
session_start();
	include 'opendb.php';
	include 'config.php';
	$ok=mysql_real_escape_string($_POST['ok']);
	if($ok)
	      {
		header("Location: forgotpwd.php");
	       }

	elseif($cancel)
	      {
		header("Location: index.php");

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
	</table>
</head>
<body>

	<p>&nbsp;</p>
	<table align="center" valign="center" border="2" cellpadding="3" cellspacing="3" width="55%"  height="50%" bgcolor="fffff">
    	<form action="" name="uidErroForm" method="post">

	<tr><td width="50%" colspan="2" align="center">
		<font face="new times roman" size="5" color="#00999">ERROR ! </font><br><br>
		<font face="times new roman" size="3" color="#0000ff">Entered User Id did not match .</font><p>&nbsp;</p>
		<input type="submit" name ="ok" value="   OK    " id="ok">
	</td></tr>
    	</form>	
	</table>		

		<table border="0" align="center" cellpadding="0" cellspacing="0" width="100%">

			<tr><td height="110">
			</td></tr>

			<tr align="center" valign="middle">
   			<td class="txthdmenu"><div id='div_id'><? include "footer.php"; ?> 
        		</div></td></tr>
		</table>
</body>
<?}?>
</html>
