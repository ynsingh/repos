<?
/* @(#)headerlinks4help.php
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
 *  	*	This file is used for header for home, login, registration and logout   *	                            * *      *       ...etc at each page.                                                    *
 *      *      	     						            		*
 *      *                                                                		*
 *      *                                                                		*
 *  	*	@author<a href="raajkhurana@gmail.com">Raj Kumar</a>                   	*
 *	*                                                                		*
 *	*********************************************************************************
 */
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

<br>
	<table align="center" border="0" cellpadding="0" cellspacing="0" width="100%">
  		<tr>
    		<td align="center" width="90%">
    		<font face="Bodoni MT Black" size="5" color="#008000">
		<img border="0" src="images/otbiitk_banner.gif" width="60%" height="60"></font>
    		</td>
  		</tr>
	</table>
<hr>

	<?
	if(($logged_userid==1)||(!isset($logged_userid)))
		{?>
		<table  align="right" width="100%" border="0" cellpadding="0" cellspacing="0">
  			<tr>
    			<td align="right">
			<a href="http://www.iitk.ac.in" target="raj" class="headerR">Home</a> |&nbsp;  
			<a href="index.php" target="raj" class="headerG">Login</a> |&nbsp;  
			<a href="registration.php" target="raj" class="headerB">Registration</a> |&nbsp; 
			<a href="contactus.php" target="raj" class="headerG">Contact</a> |&nbsp; 
			<a href="help.php" target="raj" class="headerR">Help</a>
  			</tr>
		<?}	
	
	elseif($logged_userid!=1)
		{?>
		<table  align="right" width="100%" border="0" cellpadding="0" cellspacing="0">
  			<tr>
    			<td align="right">
			<a href="http://www.iitk.ac.in" target="raj" class="headerR">Home</a> |&nbsp;  
			<a href="index.php" target="raj" class="headerG">Login</a> |&nbap;  
			<a href="registration.php" target="raj" class="headerB">Registration</a> |&nbsp;  
			<a href="contactus.php" target="raj" class="headerG">Contact</a> |&nbap;  
			<a href="help.php" target="raj" class="headerR">Help</a> |&nbap;  
			<a href="logout.php" target="raj" class="headerG">Logout</a></td>

  			</tr>
		<?}?>		


		</table>
<br>
		</body>
		</html>
