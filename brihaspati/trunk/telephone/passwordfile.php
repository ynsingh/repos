<?
/* @(#)passwordfile.php
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
 *   ****************************************************************
 *   * 	This file is used to display the password.                  *
 *   *	User has to enter user id and give answer of one question   *
 *   *	entered at registration time.    			    *
 *   * 								    *
 *   *								    *
 *   * 	@author<a href="raajkhurana@gmail.com">Raj Kumar</a>	    *
 *   *								    *
 *   ****************************************************************
 */
session_start();

	$logged_userid=$_SESSION['loginuserid'];
  	$getpwd=mysql_real_escape_string($_GET['password']);

?>
<html>
<head>
</head>
<body>

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


	<form action="index.php" method="get" >
 	<table align="center" border="2" cellpadding="3" cellspacing="3" width="50%" height="50%" bgcolor="#fffff">
	<tr><td align ="center">
 	<table border="0" cellpadding="3" cellspacing="3" width="70%" height="45%">
    	<p align="center">
    	<font size="4" face="times new roman" color="#003399">Confirmation Of Your Password</font>
	</p>
      		<tr><td width="40%" align="right">
		<font size="3" color="#0000ff" face="times new roman">Your Password is</td>
		<td><input type="text" name="password" size="20" value="<? echo $getpwd ?>" readonly></td></tr>


		<p align="center"><br>
		<tr><td>&nbsp;</td>
        	<td>
            	<input type="submit" value="LOGIN" name="login"></td>
        	</tr>
        </table>
	</td></tr>
        </form>
<?}?>        
</body>
</html>
