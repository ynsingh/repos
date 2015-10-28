<?
/* @(#)uploadsuccess.php
 *
 *  Copyright (c) 2007, 2011 Brihaspati Team/ETRG,IIT Kanpur.
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
 *  	*	This file is for displaying message that telephone bill had been	*	                              *      *       uploaded successfully.						     *
 *      *      								         	*
 *      *                                                                		*
 *      *                                                                		*
 *  	*	@author<a href="raajkhurana@gmail.com">Raj Kumar</a>                   	*
 *  	*	@author<a href="nksinghiitk@gmail.com">Nagendra Kumar singh</a>                   	*
 *	*                                                                		*
 *	*********************************************************************************
 */
session_start(); 	
	$logged_userid=$_SESSION['loginuserid'];

	$ok  =mysql_real_escape_string($_POST['ok']);
	$back=mysql_real_escape_string($_POST['back']);


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
<body>




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
   		<td class="txthdmenu"><div id='div_id'><?php include "headerlinks.php"; ?> 
        	<br></div></td></tr>
	</table><br>



	<form action="" name="uploadsuccess" method="post">	
	<table align="center" valign="center" border="2" cellpadding="3" cellspacing="3" width="55%" height="55%" 
		bgcolor="#fffff">

	<tr><td width="100%"  align="center">
	<table border="0" align="center" valign="center" border="2" cellpadding="3" cellspacing="3" width="65%" > 

	<tr><td width="50%" colspan="2" align="center">
	<font face="times new roman" size="3" color="#000ff"> Your file has uploaded successfully and mail sent to every user. The specific user detail are given below- 
	</font></td></tr>
		<tr><td align="center" colspan="2">
<?
		$message=" mail sent successfully";
		$message1=" mail not sent sucessfully";
		//Get size of array
		$asize=sizeof($msend);
		for($i=0; $i<$asize; $i++)    {
			print($msend[$i].$message."<br>");
		}
		//Get size of array
		$asize=sizeof($mnotsend);
		for($i=0; $i<$asize; $i++)    {
			print($mnotsend[$i].$message1."<br>");
		}
?>
		&nbsp;</td></tr>

		<tr><td align="right" >
		<input type="submit" name ="back" value="BACK">
		</td>

		<td align="center" >
		<input type="submit" name ="ok" value="   O K   ">
		</td></tr>

	</table>
	</td></tr>		
		<!--<tr><td width="50%" colspan="2" align="center">
		<font face="times new roman" size="3" color="#0000ff"> Your file has uploaded successfully.</font>
		<p>&nbsp;</p>
		<input type="submit" name ="ok" value="  OK  ">
		</td></tr>
		-->
	</table>		
		<table border="0" align="center" cellpadding="0" cellspacing="0" width="100%">

			<tr><td height="100">
			</td></tr>

			<tr align="center" valign="middle">
   			<td class="txthdmenu"><div id='div_id'><? include "footer.php"; ?> 
        		</div></td></tr>
		</table>

</form>	
</body>
<?}?>
</html>
