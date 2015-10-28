<?
/* @(#)findpassword.php
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
 *  	*	This file provide space to enter the userid whose password is needed    *
 *  	*	by the administrator. This is userfull incase user forgot password   	*
 *      *      	and answer of question for changing password.              		*
 *      *                                                                		*
 *      *                                                                		*
 *  	*	@author<a href="raajkhurana@gmail.com">Raj Kumar</a>                   	*
 *	*                                                                		*
 *	*********************************************************************************
 */
session_start(); 
include 'config.php';
include 'opendb.php';

		$logged_userid=$_SESSION['loginuserid'];

		$back  =mysql_real_escape_string($_POST['back']);
		$find  =mysql_real_escape_string($_POST['find']);
		$usrid =mysql_real_escape_string($_POST['usrid']);


		if($find)
			{
			if($usrid!=null)
				{
				$findpwd="select userid, password from userinfo where userid='$usrid'";
				//echo"raaj";
				if($result=execute_query($findpwd) or die("Can't execute query $findpwd"))
					{
					while($row=mysql_fetch_array($result))
						{
						$dbpasswd=$row['password'];
						$dbid=$row['userid'];
						}
					
					if(($usrid!=$dbid)||($dbid==null))
						{
				 		$searchedpwd="Entered User Id doesn't match. Try again..";
						}
					
					}	
					
				}


			else
				{
				 $searchedpwd="Please Enter User Id first..";
				}

			}




		elseif($back)
			{
			header("Location: adminaction.php");
			}
?>

<html>
<head>
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

	


	function formvalidation(thisform)
		{
		with (thisform)
			{
			if (emailvalidation(userid,"Enter User Id")==false) {userid.focus(); return false;};
			}

		}
   </script>

<!--************************************************Above-->



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
   		<td class="txthdmenu"><div id='div_id'><?php include "headerlinks.php"; ?> 
        	<br></div></td></tr>
	</table><br>



	<form  name="findpasswd" method=post action=""> 
	<table align="center" border="2" cellspacing="3" cellpeding="3" width="55%" height="60%" bgcolor="#fffff">
		<tr><td>
	<table align="center" border="0" cellpadding="3" cellspacing="3" border-color: #008000 width="55%" >
 	
		<tr><td width="50%" colspan="2" align="right">
    		<font face="times new roman" size="3" color="#003399">WELCOME:</font>
		</td>

 		<td width="50%" colspan="2" align="left">
    		<font face="times new roman" size="3" color="#0000ff">   
		<?echo$_SESSION['loginuserid']?></font> 
		</td></tr>


 		<tr><td width="50%" colspan="2" align="right">
    		<font face="times new roman" size="4" color="#003399">SEARCH</font></td>
 		<td width="50%" colspan="2" align="left">
    		<font face="times new roman" size="4" color="#003399">PASSWORD</font>
		</td></tr>   

	</table><br><br>
	
  			<table align="center" border="0" width="70%">

			<?if(($dbpasswd!="")&&($searchedpwd==""))
				{?>
  				<tr><td width="100%" align="center" colspan="3">
				<font face="times new roman" size="3" color="#0000ff">Searched Password is: 
				<font face="times new roman" size="4" color="00999"> 
				<?echo$dbpasswd?></font>
				</td></tr>
				<?}elseif($searchedpwd!=""){?>
  				<tr><td width="100%" align="center" colspan="3">
				<font face="times new roman" size="3" color="#ff0000">
				<?echo$searchedpwd;?></font>
				</td></tr>
				<?}?>

  			<tr><td width="50%" align="right">
			<font face="Times New Roman" color="#0000ff" size="3">
  			Please Enter User Id : </font></td>

  			<td width="50%" align="left" colspan="2">
			<input type="text" name="usrid" size="22" id="usrid" onchange="emailvalidation(this,'Invalid User Id (valid eg. raj@iitk.ac.in)');"> 
			</td></tr>
			

  			<tr><td width="100%" align="right" colspan="3">&nbsp;
			</td></tr>


        		<tr><td align="right" width="50%">&nbsp;
			</td>

  			<td align="left" width="25%">
			<input type="submit" name="back" style="width:80px;" value="BACK" />
			</td>

        		<td align="center" width="25%">
			<input type="submit" name="find" style="width:80px;" value="FIND" />
			</td>
			</tr>
  			</table>
	</tr></td>
	</table>
		
	</td></tr>
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
  
