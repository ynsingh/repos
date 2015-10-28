<?
/* @(#)forgotpwd.php
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
 *  	*	This file provide space to enter the userid whose password needed       *	                            *      *       by the user.                                                            *
 *      *      	     						            		*
 *      *                                                                		*
 *      *                                                                		*
 *  	*	@author<a href="raajkhurana@gmail.com">Raj Kumar</a>                   	*
 *	*                                                                		*
 *	*********************************************************************************
 */
session_start();
	include 'config.php';
	include 'opendb.php';

	if((isset($_POST['uid'])) && (isset($_POST['ok']))) 
		{
   		$loginid =mysql_real_escape_string($_POST['uid']);
  		$userid  =mysql_real_escape_string($_POST['uid']);
   		$ok   	 =mysql_real_escape_string($_POST['ok']);

		if((isset($loginid))&&(isset($ok)))
			{
			session_register("uid");
        		$_SESSION['loginuserid'] = $loginid; 
			}




  	$userid  = $_POST['uid'];
    	$que     = "select userid from userinfo";
    	$result  = execute_query($que);
  	while($row = mysql_fetch_array($result))
    		{   
	   	$dbid   = $row['userid'];
	   	if($dbid==$userid)
	     		{
			$dbuid=$dbid;
	      		}

     		}




  	if($ok)
     		{ 
		if(($userid==null)||($userid!=$dbuid))
             		{
                	header("Location: uiderror.php");
             		}

 		else
	  		{ 
                	header("Location: enterans.php");
           		}
           			  
     		}

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
   		<td class="txthdmenu"><div id='div_id'><?include "headerlinks.php"; ?> 
        	<br></div></td></tr>
	</table>

</head>
	



<body>
	<script language="javascript" type="text/javascript">
		function emailvalidation(entered, alertbox)
			{
			with (entered)
				{
				apos=value.indexOf("@");
				dotpos=value.lastIndexOf(".");
				lastpos=value.length-1;
				if (apos<1 || dotpos-apos<2 || lastpos-dotpos>3 || lastpos-dotpos<2)
					{
					if (alertbox) 
						{
 						alert(alertbox);
						}
 						return false;

					}



				else
					{
 					return true;
					}

				}


			}

   		</script>




<form action="" name="forgotpwd" method="post" id="forgotpwd" onchange="return formvalidation(this);">
	<p>&nbsp;</p>
	<table align="center" border="2" cellpadding="3" cellspacing="3" width="55%" height="50%" bgcolor="#fffff"> 
  	<tr>
    	<td width="100%">

	<table align="center" border="0" cellpadding="3" cellspacing="3" width="70%" >

		<p align="center">&nbsp;</p>
		<tr><td width="30%" align="right">&nbsp;</td>

		<td width="100%" colspan="2" align="left">
		<font face="times new roman" size="4" color="#003399"><b>ENTER USERID</b></font>
		</td></tr>


		<tr><td width="30%" align="left">&nbsp;</td>
		<td width="70%" align="left" colspan="2">&nbsp;</td>
		</tr>


		<tr><td width="30%" align="right">
		<b><font face="Times New Roman" color="#0000"><b>USER ID </b></font></td>
		<td width="70%" align="left" colspan="2">
		<input type="text" name="uid" size="23" id="uid" onchange="emailvalidation(this,'Invaid User Id (valid eg. raj@iitk.ac.in)');">
		</td></tr>


		<tr><td width="30%" align="left">&nbsp;</td>
		<td width="70%" align="left" colspan="2">&nbsp;</td>
		</tr>


		<tr><td width="30%" align="left">&nbsp;</td>
		<td width="25%" align="left">
		<input type="submit" value="OK" name="ok" style="width:60px;" onsubmit="emailvalidation(this,'Invalid User Id')';">
		</td>

		<td width="40%" align="left">
		<input type="reset" value="RESET " name="reset" style="width:60px;"></td>
		</tr>

		</table>


	<p>&nbsp;</p>
	</td>
	</tr>	

</table>		


		<table border="0" align="center" cellpadding="0" cellspacing="0" width="100%">

			<tr><td height="120">
			</td></tr>

			<tr align="center" valign="middle">
   			<td class="txthdmenu"><div id='div_id'><? include "footer.php"; ?> 
        		</div></td></tr>
		</table>

</form>	
</body>
</html>
