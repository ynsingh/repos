<?
/* @(#)index.php
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
 *   ************************************************************************
 *   * 	This file is used to login for registered users.                    *
 *   *	New users can registered themselves by clicking on NEW USER         *
 *   *	or by clikcing on Telephone logo.  			            *
 *   * 								            *
 *   *								            *
 *   * 	@author<a href="nksinghiitk@gmail.com">Nagendra Kumar Singh</a>	    *
 *   * 	@author<a href="raajkhurana@gmail.com">Raj Kumar</a>	            *
 *   *								            *
 *   ************************************************************************
 */
if(!isset($_SESSION))
{
session_start();
}
//session_start(); 
$errormessage = ''; 
$loginid = '';
$welcome = '';
//$forgotpwd = '';
include 'config.php';
include 'opendb.php';
if(($_POST['uid']) && ($_POST['pword'])) 
	{
   	$login     =mysql_real_escape_string($_POST['login']);
		//********   for server  *******************
   		$loginid   = mysql_real_escape_string($_POST['uid']);
   		$pword     = mysql_real_escape_string($_POST['pword']);
   		$forgotpwd = mysql_real_escape_string($_POST['forgotpwd']); 
   		$mode      = mysql_real_escape_string($_POST['uid']);
		//*****************************************
	/*
   	$loginid   = $_POST['uid'];
   	$pword     = $_POST['pword'];
   	$forgotpwd = $_POST['forgotpwd']; 
   	//$mode      = $_POST['uid'];
	*/

   	$admin     = 'sanchar@iitk.ac.in';
 if($login)   
  	{ 
	//echo "khurana";
    	if($loginid!=$admin)
     		{
    		$que    ="select password, userid, status from userinfo where userid='$loginid'";
    		$result =execute_query($que);
    		while($row = mysql_fetch_array($result))
     	  		{    
			$dbid       = $row['userid'];
			$dbstatus   = $row['status'];
			$dbpassword = $row['password'];
			}
						
				

						
    			if ($_POST['uid'] === ($dbid) && $_POST['pword']=== $dbpassword) 
				{
				if($dbstatus=='a')
					{
					session_register("uid");
					session_register("pword");
        				$_SESSION['loginuserid'] = $loginid; 
        				$_SESSION['loginuserpass'] = $pword; 
        				//header("Location: upwdsuccess.php"); 
        				header("Location: bill.php"); 
        				exit; 
					}
					

				elseif($dbstatus=='y')
					{
       					$errormessage ='Your Registration is not approved till now.';
					}


				elseif($dbstatus=='n')
					{
       					$errormessage='Your Registration has deleted by Administrator.'; 
					}


				}
			elseif($_POST['uid'] !== ($dbid) || $_POST['pword'] !== ($dbpassword)) 
				{
				if(($dbstatus)=='a')
					{
        				$errormessage ='Incorrect user Id or Password '; 
					}


				elseif(($dbstatus !=='y')&&($dbstatus !=='n'))
					{
       					$errormessage ='Incorrect user Id or new user. Please registered youself'; 
					}
	
				}
		}
 	else  
     		{		    
    		$adminqry  = "select password from userinfo where userid='$admin'";
    		$result = execute_query($adminqry);
    		while($row = mysql_fetch_array($result))
     	  		{    
			$dbpassword = $row['password'];
			}
			if(($dbpassword!=null) && ($dbpassword==$pword))
           	 		{      
				session_register("uid");
				session_register("pword");
        			$_SESSION['loginuserid'] = $loginid; 
        			$_SESSION['loginuserpass'] = $pword; 
				header("Location: pwdsuccess.php");
		   		exit;
                         	}

          		else
 	            	 	{	
        			$errormessage = 'Incorrect administrator Password'; 
                     		}
  	  	}
  	} 
}
?>



<html>

<head>
</head>



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
	</table>
	<br>


	
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

	


	function formvalidation(thisform)
		{
		with (thisform)
			{
			if (emailvalidation(userid,"Enter User Id")==false) {userid.focus(); return false;};
			}

		}

   </script>



<!--*******************************************************************************************-->


        <form action ="" name="indexform" method="post" id="indexform" onsubmit="return formvalidation(this)">
	<div align="center">


<table align="center" border="2" cellpadding="1" cellspacing="1" width="60%" height="65%" bgcolor="#fffff">
	<tr><td align="center">


	<?if(($_SESSION['loginuserid'])!=1)
	{?>
	<table align="center" border="0" cellpadding="3" cellspacing="3" border-color: #008000 width="60%" >
 	
		<tr><td width="50%" colspan="2" align="right">
    		<font face="times new roman" size="3" color="#003399"><b>WELCOME</b></font>
		</td>

 		<td width="50%" colspan="2" align="left">
    		<font face="times new roman" size="3" color="#0000ff">   
		<?echo$_SESSION['loginuserid']?></font> 
		</td></tr>

	</table><?}?>


<!--*******************************************************************************************-->


	 <table align="center" width="100%" border="0" cellspacing="0">
	<?
        	if ($errormessage != '') 
			{
        ?>
        		<td height="40" colspan="5" align="center" valign="top" class="txthd">
       			<font color="0000ff"><?echo $errormessage; ?></font>
			</td>

			<?


	  		}

	  	else
			{
			if(($_SESSION['loginuserid']) || ($_SESSION['loginuserid'] = true)) 
	  			{
				//echo"Raaj";
	  			$user_logged_on = $_SESSION["loginuserid"];
	  			}
			}
		
		
			?>

      </table>

 	<table align="center" valign="center" border="1" cellpadding="3" cellspacing="3" width="70%" height="70%" bgcolor="#fffff">
	
		<tr><td width="60%" align="center">
        	<img border="0" src="images/LOGIN.gif" width="140" height="60"></td>            
		<td width="40%" align="center">
      		<img border="0" src="images/registration.gif" width="140" height="60"></td>
		</tr>

	<tr>
	<td>
 		<table align="center" border="0" cellpadding="3" cellspacing="3" width="70%" height="45%" >
			<tr>
			<td width="35%" align="left">
        		<b><font face="Times New Roman" color="">USER ID</font></b></td>
        		<td width ="50%" align="left">
			<input type="text" name="uid" size="18" id="uid" 
			onchange="emailvalidation(this,'Invalid User Id (valid eg. ynsingh@iitk.ac.in)');"> </td>
			</tr>
    
        		<tr>
			<td width="35%" align="left">
			<b><font face="times new roman" color="">PASSWORD</font></b></td> 
        		<td width="20%"><input type="password" name="pword" size="18"> </font></b></td>
			</tr>


			<tr>
			<td colspan="2" align="left">
			<a href="forgotpwd.php">Forgot Password</a></td>
                	</tr>    

      			<tr><td><p>&nbsp;</p></td>
      			<td><p>&nbsp;</p>
    			</td></tr>


        		<tr>
			<td width="20%" align="left">
       			<b><input type="submit" style="width:80px;" value="LOGIN" name="login" language="javascript"/></b>
			</td>
			<td width="25%" align="left">
       			<b>
        		<input type=reset style="width:80px;" value="RESET" ></b>
			</tr>



			<br>
    		</table>
		</td>


		<td>
    			<table align ="center" border="0" cellpadding="0" cellspacing="0"  width="65%" height="70%">

				<tr>
      				<td align="center" colspan="2" >
      				<a href=registration.php><img src="images/phonelogo.jpg" border="0" 
				   width="90" height="90"></a> 
				</td></tr>
		



				<tr><td align="center" colspan="2">
				<a href="registration.php">New User</a>
				</td></tr>
				</table>
		</td></tr>
		</table>

		</td></tr>
		</table>

		<table border="0" align="center" cellpadding="0" cellspacing="0" width="100%">

			<tr><td height="20">
			</td></tr>

			<tr align="center" valign="middle">
   			<td class="txthdmenu"><div id='div_id'><? include "footer.php"; ?> 
        		</div></td></tr>
		</table>

	</div>
	</body>
	</form>
</html>
