<?
/* @(#)enterans.php
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
 *  	*	This file provide space to enter the answer of question selected at     *
 *  	*	registration time. This is necessary for changing user password and    	*
 *      *       security purposes.                                      		*
 *      *                                                                		*
 *      *                                                                		*
 *  	*	@author<a href="raajkhurana@gmail.com">Raj Kumar</a>                   	*
 *	*                                                                		*
 *	*********************************************************************************
 */
session_start();
  	include 'config.php';
  	include 'opendb.php';

  	$getid  = $_SESSION['loginuserid'];

  	$answer  =mysql_real_escape_string($_POST['answer']);
  	$ok      =mysql_real_escape_string($_POST['ok']);
  	$cancel  =mysql_real_escape_string($_POST['cancel']);


 
    	$que     = "select userid, password, hint_answer, password_question from userinfo where userid='$getid'";
    	$result  = execute_query($que);
  	while($row = mysql_fetch_array($result))
    		{   
	   	$dbid       = $row['userid'];
	   	$password   = $row['password'];
	   	$dbquestion = $row['password_question'];
	   	$dbanswer   = $row['hint_answer'];
     		}


  	if($ok)
     		{ 
		if($dbanswer!=$answer)
             		{
                	header("Location: anserror.php");
             		}


 		else
	  		{ 
                	header("Location: passwordfile.php?password= $password");
           		}
           			  
     		}



  	if($cancel)
  		{
    		header("Location: index.php");
   		}
 
       			
?>
<html>
<head>
<?if($_SESSION['loginuserid']!=1)
	{?>

	<table align="center" border="0" cellpadding="0" cellspacing="0" width="100%">
  		<tr>
    		<td align="center" width="90%">
    		<font face="Bodoni MT Black" size="5" color="#008000">
		<img border="0" src="images/otbiitk_banner.gif" width="60%" height="60"></marquee></font>
    		</td>
  		</tr>
	</table>

	
<hr>



	<table border="0" align="center" cellpadding="0" cellspacing="0" width="100%">
		<tr align="right" valign="middle">
   		<td class="txthdmenu"><div id='div_id'><?php include "headerlinks.php"; ?> 
        	<br></div></td></tr>
	</table><br>

</head>
	
<body>


<form action="" name="" method="post">
<table align="center" border="2" cellpadding="3" cellspacing="3" width="45%" bgcolor="#fffff" >
  <tr>
    <td width="100%">

	<table align="center" border="0" cellpadding="3" cellspacing="3" width="70%" >

		<p align="center">&nbsp;</p>
		<tr><td width="100%" colspan="3" align="center">
		<font face="times new roman" size="5" color="#003399">ENTER ANSWER</font><p>&nbsp;</p>
		</td></tr>


		<tr><td width="30%" align="right">
		<b><font face="times new roman" color="#0000"><b>USER ID </b></font></td>
		<td width="70%" align="left" colspan="2">
		<input type="text" name="uid" size="23" value="<?echo $getid?>" readonly></td></tr>

 
		<tr><td width="30%" align="right">
		<b><font face="times new roman" color="#0000"><b>QUESTION </b></font></td>
		<td width="70%" align="left" colspan="2">
		<input type="text" name="uid" size="23" value="<?echo $dbquestion?>" readonly></td></tr>


           	<tr><td width="30%" align="right">
		<b><font face="times new roman" color="#0000"><b>ANSWER </b></font></td>
		<td width="70%" align="left" colospan="2">
		<input type="text" name="answer" size="23" ></td>


		<tr><td width="100%" align="left" colspan="3">&nbsp;</td>
		</tr>

		<tr>
		<td width="30%" align="right">&nbsp;</td>
		<td width="30%" align="left">
		<input type="submit" value="    OK    " name="ok">
		&nbsp;
		&nbsp;
		<input type="submit" value="CANCEL" name="cancel"></td>
		</tr>


	</table>
	<p>&nbsp;</p>
	</td>
	</tr>	

</table>		
		<table border="0" align="center" cellpadding="0" cellspacing="0" width="100%">

			<tr><td height="70">
			</td></tr>

			<tr align="center" valign="middle">
   			<td class="txthdmenu"><div id='div_id'><? include "footer.php"; ?> 
        		</div></td></tr>
		</table>

</form>	
<?}?>
</body>
</html>
