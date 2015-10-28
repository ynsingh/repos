<?
/* @(#)pwdchange.php
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
 *  	*	This file will dispaly the password changed successfull message		*
 *  	*									     	*
 *      *                                                                		*
 *      *                                                                		*
 *      *                                                                		*
 *  	*	@author<a href="raajkhurana@gmail.com">Raj Kumar</a>                   	*
 *	*                                                                		*
 *	*********************************************************************************
 */
session_start();
	$logged_user=$_SESSION['login_userid'];
	$ok=mysql_real_escape_string($_POST['ok']);
	if($ok)
		{
		if($logged_user=="sanchar@iitk.ac.in")
			{
			header("Location: pwdsuccess.php");
			}
		else
			
			{
			header("Location: bill.php");
			}
		}
	
?>
<html>
<head>
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

	
</head>
<body>

	<div align="center">
	<center>


		<form action="" name="passwordchanged" method="post">
		<table align="center" border="2" cellpadding="3" cellspacing="3"  width="50%" bgcolor="#FFFFF">

 		<tr><td width="100%" colspan="4" align="center">
		<table align="center" border="0" cellpadding="3" cellspacing="3" border-color: #008000 width="65%" >
 	
		<tr><td width="50%" colspan="2" align="right">
    		<font face="times new roman" size="3" color="#00999">WELCOME:</font>
		</td>

 		<td width="50%" colspan="2" align="left">
    		<font face="times new roman" size="3" color="#0000ff">   
		<?echo$_SESSION['loginuserid']?></font> 
		</td></tr>


 		<tr><td width="50%" colspan="4" align="center">
    		<font face="times new roman"  color="#00999" ><b>CONGRATULATIONS !</b></font></td>
		</td></tr>   
		</table>
			<table align="center" border="0" cellpadding="3" cellspacing="3"  width="80%"  bgcolor="#FFFFF">
				<tr><td align="center">
				<font color="#0000FF" face="Times New Roman" size="3">Your Password has been 
				changed successfully.<br> Thank You..!
				</font></td></tr>
				<p>&nbsp;</p>

  				<tr><td align="center"><p>&nbsp;</p>
				<input type="submit" value="   OK   " name="ok">
        			</td></tr>
			</table>

		</td></tr>

		<table border="0" align="center" cellpadding="0" cellspacing="0" width="100%">

			<tr><td height="165">
			</td></tr>

			<tr align="center" valign="middle">
   			<td class="txthdmenu"><div id='div_id'><? include "footer.php"; ?> 
        		</div></td></tr>
		</table>
		</table>
		</form>

	</center>
	</div>
	<?}?>
	</body>
	</html>
