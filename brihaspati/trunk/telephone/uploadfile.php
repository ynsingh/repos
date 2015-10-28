<?
/* @(#)uploadfile.php
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
 *  	*	This file is for upload telephone bill by administrator only.		*	                            * *      * 									             *
 *      *      								         	*
 *      *                                                                		*
 *      *                                                                		*
 *  	*	@author<a href="raaj16jan@gmailcom">Raj Kumar</a>                   	*
 *	*                                                                		*
 *	*********************************************************************************
 */
session_start(); 
include 'config.php';
include 'opendb.php';

		$logged_userid=$_SESSION['loginuserid'];
?>

<html>
<head>
</head>
<body>


<?if(isset($_SESSION['loginuserid']))
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
   		<td class="txthdmenu"><div id='div_id'><?php include "headerlinks.php"; ?> 
        	<br></div></td></tr>
	</table><br>



	<form method=post action="uploadbill.php" enctype="multipart/form-data"> 
	<table align="center" border="2" cellspacing="3" cellpeding="3" width="55%" height="60%" bgcolor="#fffff">
		<tr><td>
  			<table align="center" border="0" width="80%">

  			<tr>
  			<td width="60%" align="right">
			<font face="Times New Roman" color="#0000ff" size="3">
  			Enter Filename with path</font></td>

  			<td width="40%" align="right" colspan="2">
			<input type="file" name="myfile" id="myfile" /></td>
			</tr>

        		<tr><td colspan="3">&nbsp;
			</td>
        		</tr>

        		<tr><td align="right"><font face="Times New Roman" color="#0000ff" size="4">
			<?echo "<a href=\"adminaction.php\"> Go  Back</a>"?> 
			</td>

  			<td align="right">&nbsp;
			</td>

  			<td align="right">
			<input type="submit" name="upload"  style="width:77px;" value="UPLOAD" /></td>
  			</tr>
  			</table>


	</tr></td>
	</table>

		<table border="0" align="center" cellpadding="0" cellspacing="0" width="100%">

			<tr><td height="40">
			</td></tr>

			<tr align="center" valign="middle">
   			<td class="txthdmenu"><div id='div_id'><? include "footer.php"; ?> 
        		</div></td></tr>
		</table>

</form>	
<?}?>
  </body>
  </html>
  
