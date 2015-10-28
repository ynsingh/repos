<?
/* @(#)uploaderror.php
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
 *  	*	This is used to display the bill upload error.	 			*	                            * *       									             *
 *      *      								         	*
 *      *                                                                		*
 *      *                                                                		*
 *  	*	@author<a href="raajkhurana@gmail.com">Raj Kumar</a>                   	*
 *	*                                                                		*
 *	*********************************************************************************
 */
session_start();

  	$logger_userid    =$_SESSION['loginuserid'];
	include 'config.php';
	include 'opendb.php';


	//$nofile   =mysql_real_escape_string($_GET['nofile']);	
	//$nodir    =mysql_real_escape_string($_POST['nodir']);	
	$ok   	  =mysql_real_escape_string($_POST['ok']);	
	$back     =mysql_real_escape_string($_POST['back']);



	if($ok)
	      {
		header("Location: adminaction.php");

	       }

	elseif($back)
	      {
		header("Location: uploadfile.php");

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


    	<form name="uploaderr" action=""  method="post">
	<table border="1" align="center" valign="center" border="2" cellpadding="3" cellspacing="3" width="55%"  
	height="50%" bgcolor="#fffff">

	<tr><td width="100%"  align="center">
	<table border="0" align="center" valign="center" border="2" cellpadding="3" cellspacing="3" width="65%" > 

	<tr><td width="50%" colspan="2" align="center">
	<font face="times new roman" size="3" color="#000ff"> Your file did not upload because you are not given the corret path. Try again ...
	</font></td></tr>

		<tr><td align="center" colspan="2">
		&nbsp;</td></tr>

		<tr><td align="right" >
		<input type="submit" name ="back" style="width:80px;" value="BACK">
		</td>

		<td align="center" >
		<input type="submit" name ="ok" style="width:80px;" value="O K">
		</td></tr>

	</table>		
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

</body>
<?}?>
</html>
