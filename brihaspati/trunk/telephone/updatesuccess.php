<?
/* @(#)updatesuccess.php
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
 *  	*	This file is for displaying message that registration has been copleted	*	                              *      *       completed successfully and waiting for administrator approval.	     *
 *      *      								         	*
 *      *                                                                		*
 *      *                                                                		*
 *  	*	@author<a href="raajkhurana@gmail.com">Raj Kumar</a>                   	*
 *	*                                                                		*
 *	*********************************************************************************
 */
session_start(); 	
$logged_userid=$_SESSION['loginuserid'];

	$back=mysql_real_escape_string($_POST['back']);
	$adminaction=mysql_real_escape_string($_POST['adminaction']);

	if($adminaction)
		{
		header("Location: adminaction.php");
		}

	elseif($back)
		{
		header("Location: approvedreq.php");
		}
?>
	<html>
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


	
<body>

	</head>

	<form  name="updatesuccess" action="" method="post">
		<table align="center" border="2" cellpadding="3" cellspacing="3" style="border-collapse: collapse" width="60%"  height="60%" bgcolor="fffff">
     		<tr><td width="100%">
     		<p align="center"><font face="times new roman" size="6">&nbsp;</font></p>
     		<p align="center"><font face="times new roman" size="5" color="#003399"><b>UPDATE SUCCESSFULLY !</b>
		</font></p><br>


     		<p align="center">
		<font face="times new roman"  color="#0000ff">
			Congratulations Your updation completed successfully.<br>
			  </font></p>

     		<p align="center"><br>
     		<input type="submit" value="  BACK  " name="back">
     		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
     		<input type="submit" value="ADMIN" name="adminaction"></p><br>
     		<p>&nbsp;</p>
     		</td></tr></table>

		<table border="0" align="center" cellpadding="0" cellspacing="0" width="100%">

			<tr><td height="80">
			</td></tr>

			<tr align="center" valign="middle">
   			<td class="txthdmenu"><div id='div_id'><? include "footer.php"; ?> 
        		</div></td></tr>
		</table>

	</body>

    	</form>
   	</body>
	</html>
