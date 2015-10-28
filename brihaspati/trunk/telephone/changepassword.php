<?
/* @(#)changepassword.php
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
 *  	*	User can change his/her password with the help of this file.i   	*
 *  	*									      	*
 *      *       	                                                		*
 *      *                                                                		*
 *      *                                                                		*
 *  	*	@author<a href="raajkhurana@gmail.com">Raj Kumar</a>                   	*
 *	*                                                                		*
 *	*********************************************************************************
 */
session_start();
include 'config.php';
include 'opendb.php';

  $getid    = $_SESSION['loginuserid'];

  $cancel =mysql_real_escape_string($_POST['cancel']);
  $back   =mysql_real_escape_string($_POST['back']);
  $submit =mysql_real_escape_string($_POST['submit']);
  $opwd   =mysql_real_escape_string($_POST['oldpwd']); 
  $npwd   =mysql_real_escape_string($_POST['newpwd']); 
  $cpwd   =mysql_real_escape_string($_POST['confirmpwd']); 
  
/* 
  $opwd     = $_POST['oldpwd']; 
  $npwd     = $_POST['newpwd']; 
  $cpwd     = $_POST['confirmpwd']; 
*/
    $que       = "select password from userinfo where userid='$getid'";
    $result    = execute_query($que);
    while($row = mysql_fetch_array($result))
    	{   
	$dbpassword= $row['password'];
     	}

        if($submit)
           {
		
		if(($opwd!="")&&($npwd!="")&&($cpwd!=""))      
			{
			if($opwd==$dbpassword)
				{
				if($npwd==$cpwd)
					{ 
              				$update_pwd = "update userinfo set password='$npwd' where userid='$getid'";
					$result1=execute_query($update_pwd);
								$send2="$getid";
                                                                $subject = "Change password for telephone billing system";
                                                                $message = "Dear Sir,<br>Your password chnaged successfully. <br>Now your new password is $newpwd <br> Thank you for using this system.<br> For any query, please contact<br>  Krishan Pal <br> Telephone Incharge <br>krishanpal@iitk.ac.in";
                                                                $header="sanchar@iitk.ac.in";
                                                                mail($send2, $subject, $message, $header);
	
	  				header("Location: pwdchange.php");
					}
				else
					{
					$errmsg="new password doesn't mathch with confirm password...";
					}
				}

			else
				{
				$errmsg="Old password does not match...";
				}

			}

	    	else
	     		{   
			$errmsg="Please fillup all fields...";
	        	}

		}

		if($cancel)
	  		{
	        		header("Location: changepassword.php");
           		}
		if($back){

			if($getid=="sanchar@iitk.ac.in")
				{
	                        header("Location: pwdsuccess.php");
				}
			else
				{
				header("Location: bill.php");
				}

                 	}

            			
		

?>


<html>
	<script>
	function frmsubmit()
        	{
                document.frm1.submit();
        	}

	</script>
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
   		<td class="txthdmenu"><div id='div_id'><?include "headerlinks.php"; ?> 
        	<br></div></td></tr>
	</table>


<br>
<form action ="" name="frm1" method="post">
<table align ="center" border="1" border="2" cellpadding="3" cellspacing="3" width="45%" height=60% bgcolor="#fffff">

	<tr><td width="100%" colspan="2" align="right">
	<table align="center" border="0" cellpadding="3" cellspacing="3" border-color: #008000 width="85%" bgcolor="#fffff">
 	
		<tr><td width="40%" colspan="2" align="right">
    		<font face="times new roman" size="3" color="#003399">WELCOME:</font>
		</td>

 		<td width="60%" colspan="2" align="left">
    		<font face="times new roman" size="3" color="#0000ff">   
		<?echo$_SESSION['loginuserid']?></font> 
		</td></tr>


 		<tr><td width="40%" colspan="2" align="right">
    		<font face="times new roman"  color="#003399"><b>CHANGE</b></font></td>
 		<td width="60%" colspan="2" align="left">
    		<font face="times new roman"  color="#003399"><b>PASSWORD</b></font>
		</td></tr>   
		</table>
		


			

		
    		<table align ="center" border="0" cellpadding="3" cellspacing="3"  width="85%" height="35%" bgcolor="#fffff">
				<?if($errmsg!="")
				{?>
				<tr>
				<td width="33%" align="center" colspan="3">
    				<font face="times new roman" size="3" color="red">   
				<?echo$errmsg;?></font> 
				</td></tr>


				<?}else{?>
				<tr>
				<td width="33%" align="center" colspan="3">
    				<font face="times new roman" size="3" color="#0000ff"> &nbsp;  
				</font> 
				</td></tr>
				<?}?>

				<tr><td width="50%" align="right">
       				<b><font size="2" face="times new roman" color="#0000">YOUR OLD PASSWORD
    				<b><font color="RED" face="times new roman">*</font></b></td>
			
				<td align="left" width="50%" colspan="2">
				<input type="password" name="oldpwd" size="25">
				</td></tr>


				<tr><td width="50%" align="right">
				<b><font size="2" color="#00000" face="times new roman">YOUR NEW PASSWORD
    				<b><font color="RED" face="times new roman">*</font></b></td>
				
				<td width="50%" align="left" colspan="2">
				<input type="password" name="newpwd" size="25">
				</td></tr>


				<tr><td width="50%" align="right">
				<b><font size="2" color="#00000" face="Times New Roman">CONFIRM 	
				&nbsp;&nbsp; PASSWORD
    				<b><font color="RED" face="times new roman">*</font></b></td>

				<td width="50%" align="left" colspan="2">
        			<input type="password" name="confirmpwd" size="25">
				</td></tr>


				<tr><td width="50%" align="right" colspan="3">&nbsp;</td></tr>

				<tr>
				<td width="50%" align="right">
				<input type="submit" name="back" value=" BACK ">
				</td>

				<td width="22%" align="center">
        			<input type="submit" name="submit" value="SUBMIT">
				</td>

				<td width="28%" align="left">
        			<input type="submit" value="CANCEL" name="cancel">
				</td></tr>				

				<tr><td width="50%" align="right" colspan="3">&nbsp;</td></tr>

				<tr>
				<td width="100%" align="left" colspan="3">
    				<font face="times new roman" size="3" color="#000ff">
				Mandatory fiels are asigned by</font>
    				<font face="times new roman" size="3" color="red">*</font>
				</td>
				
				</table>
      		</td>
     		</tr>
   		</table>
		</td></tr>
   		</form>

     	</td>
	</tr>
  	</table>
		<table border="0" align="center" cellpadding="0" cellspacing="0" width="100%">

			<tr><td height="65">
			</td></tr>

			<tr align="center" valign="middle">
   			<td class="txthdmenu"><div id='div_id'><? include "footer.php"; ?> 
        		</div></td></tr>
		</table>

<?}?>
</body>
</html>
