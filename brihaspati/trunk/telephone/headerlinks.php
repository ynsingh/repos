<?
/* @(#)headerlinks.php
 *
 *  Copyright (c) 2007-2008 Brihaspati Team/ETRG,IIT Kanpur.
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
 *  	*	This file is used for header for home, login, registration and logout   *
 *      *       ...etc at each page.                                                    *
 *      *      	     						            		*
 *      *                                                                		*
 *      *                                                                		*
 *  	*	@author<a href="raajkhurana@gmail.com">Raj Kumar</a>                   	*
 *	*                                                                		*
 *	*********************************************************************************
 */
if(!isset($_SESSION))
{
session_start();
}
//session_start();
	$logged_userid=$_SESSION['loginuserid'];
	?>
	<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>Online Telephone Billing System IIT Kanpur</title>
		<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
		<style type="text/css">
		</style>
		</head>

		<body>

	<?
	if(($logged_userid==1)||(!isset($logged_userid)))
		{?>
		<table  align="right" width="100%" border="0" cellpadding="0" cellspacing="0">
  			<tr>
    			<td align="right">
			<a href="http://www.iitk.ac.in/tel/mainpage/" class="headerR">Home</a> |&nbsp; 
			<a href="index.php" class="headerG">Login</a> |&nbsp;  
			<a href="registration.php" class="headerB">Registration</a> |&nbsp;
			<a href="contactus.php" class="headerG">Contact</a> |&nbsp;
			<a href="help.php" class="headerR">Help</a>
  			</tr>
		<?}	
	
	elseif($logged_userid!=1)
		{?>
		<table  align="right" width="970" border="0" cellpadding="0" cellspacing="0">
  			<tr>
    			<td align="right">
			<a href="http://www.iitk.ac.in/tel/mainpage/" class="headerR">Home</a> |&nbsp; 
			<a href="contactus.php" class="headerG">Contact</a> |&nbsp;
			<a href="help.php" class="headerR">Help</a> |&nbsp;
			<a href="logout.php" class="headerG">Logout</a></td>

  			</tr>
		<?}?>		


		</table>
		</body>
		</html>
